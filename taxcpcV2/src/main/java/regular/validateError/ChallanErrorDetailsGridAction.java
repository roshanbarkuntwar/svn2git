/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ChallanErrorDetailsGridAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;
    private String action;

    private ArrayList<ShowChallanErrorDetailsBean> showChallanErrorDetailBeanList;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private String ReadonlyFlag;
    private LinkedHashMap<String, String> selectSection;
    private TreeMap<String, String> minorHead;

    public ChallanErrorDetailsGridAction() {
        utl = new Util();
        selectSection = new LinkedHashMap<String, String>();

        minorHead = new TreeMap<String, String>();
        minorHead.put("200", "Normal Tax");
        minorHead.put("400", "Assessment Tax");
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        StringBuilder sb = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            try {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                setSelectSection(factory.getTdsMastDAO().getSectionAsHashMap(asmt.getTdsTypeCode(), asmt.getQuarterToDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                if (getTotalRecords() > 0 && getRecordsPerPage() > 0 && getPageNumber() > 0) {
                    int maxVal = getTotalRecords();
                    int minVal = 1;

                    if (getTotalRecords() > getRecordsPerPage()) {
                        maxVal = getPageNumber() * getRecordsPerPage();
                        minVal = maxVal - getRecordsPerPage() + 1;
                        if (maxVal > getTotalRecords()) {
                            maxVal = getTotalRecords();
                        }
                    }
                    setStartIndex(minVal - 1);
                    setEndIndex((maxVal - 1));

                    int l_start_page = 0;
                    if (getPageNumber() == 0) {
                        l_start_page = 1;
                    } else {
                        l_start_page = getPageNumber();
                    }

                    int l_records_to_add = getRecordsPerPage();
                    int l_record_MXL = (l_start_page * l_records_to_add);
                    int l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;

                    String l_entity_code = "";
                    String l_client_code = "";
                    String l_acc_year = "";
                    String l_quarter_no = "";
                    String l_tds_type_code = "";
                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                    Assessment asmt = null;
                    if (viewClientMast != null) {
                        l_entity_code = viewClientMast.getEntity_code();
                        l_client_code = viewClientMast.getClient_code();
                        asmt = (Assessment) session.get("ASSESSMENT");
                        l_acc_year = asmt.getAccYear();
                        l_quarter_no = asmt.getQuarterNo();
                        l_tds_type_code = asmt.getTdsTypeCode();
                    }
                    ProcessErrorsDB proc_err = new ProcessErrorsDB();
                    String l_error_code = (String) session.get("challanErrorCode");

                    String l_query = proc_err.getChallanSecScreenErrorDetailsQry(l_client_code, l_entity_code, l_error_code, asmt, l_record_MNL, l_record_MXL);
                    utl.generateSpecialLog("RPE-0011 (Challan error detail screen 2nd data grid query)----", l_query);

                    DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();
                    ArrayList<ShowChallanErrorDetailsBean> listchallantranLoadError = objList.getGenericList(new ShowChallanErrorDetailsBean(), l_query);
                    ValidateErrorSupport support = new ValidateErrorSupport();
                    sb = support.getChallanErrorDetailGrid(listchallantranLoadError, l_error_code, getSelectSection(), l_entity_code, l_client_code, asmt, l_record_MNL, l_record_MXL, getMinorHead());

                } else {
                    sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                l_return_value = "challan_grid";
            } else {
                session.put("SHOWERRORDETAILSRCHPG", String.valueOf(getSetRecPerPage()));// records per page
                sb.append("success");
                l_return_value = "ajax_result";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ShowChallanErrorDetailsBean> getShowChallanErrorDetailBeanList() {
        return showChallanErrorDetailBeanList;
    }

    public void setShowChallanErrorDetailBeanList(ArrayList<ShowChallanErrorDetailsBean> showChallanErrorDetailBeanList) {
        this.showChallanErrorDetailBeanList = showChallanErrorDetailBeanList;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(int setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
    }

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public TreeMap<String, String> getMinorHead() {
        return minorHead;
    }

    public void setMinorHead(TreeMap<String, String> minorHead) {
        this.minorHead = minorHead;
    }

}
