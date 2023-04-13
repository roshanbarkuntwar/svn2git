/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.salarySevaarth;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class SalarySevaarthDataGridAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "grid_success";
        String return_value;
//        session.put("ERRORCLASS", MessageType.errorMessage);
        StringBuilder sb = new StringBuilder();
        String flag = "";
        try {

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch())) {

                    if (getTotalRecords() > 0) {

                        if (getRecordsPerPage() > 0) {
                            if (getPageNumber() > 0) {

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

                                if (clientMast != null) {

                                    SalarySevaarthDB salDB = new SalarySevaarthDB();
                                    String l_query = salDB.getSalarySevaarthDataGridQuery(clientMast, asmt, getSalSevaarthFilterData(), l_record_MNL, l_record_MXL);
                                    utl.generateSpecialLog("Salary Sevaarth  data query: ", l_query);
                                    DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                                    List<SalarySevaarthBrowseEntity> salSevaarthBrowseEntityList = dbQueryList.getGenericList(new SalarySevaarthBrowseEntity(), l_query);

                                    SalarySevaarthSupport salDg = new SalarySevaarthSupport();
                                    sb = salDg.getBrowseGrid(salSevaarthBrowseEntityList, clientMast, sb, getStartIndex(), getPageNumber());
                                } else {
                                    addActionError("some error occured");
                                    return_view = "input";
                                }
                            }
                        }
                    } else {

                        sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                    }

                    sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");

                    inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

                } else if (getSetRecPerPage() > 0) {
                    session.put("SALSEVAARTHRECPERPG", String.valueOf(getSetRecPerPage()));
                    return_value = "success";
                    return_view = "input_success";
                    inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
                }

            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }

    public SalarySevaarthDataGridAction() {
        utl = new Util();
        salSevaarthFilterData = new SalarySevaarthFilterEntity();
    }
    Util utl;
    private Map<String, Object> session;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private String filterFlag;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private SalarySevaarthFilterEntity salSevaarthFilterData;

    public SalarySevaarthFilterEntity getSalSevaarthFilterData() {
        return salSevaarthFilterData;
    }

    public void setSalSevaarthFilterData(SalarySevaarthFilterEntity salSevaarthFilterData) {
        this.salSevaarthFilterData = salSevaarthFilterData;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
