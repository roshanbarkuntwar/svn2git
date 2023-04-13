/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewTdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewTdsChallanTranLoad;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class ChallanDataGridAction extends ActionSupport implements SessionAware {

    public ChallanDataGridAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String return_view = "grid_success";
        String return_value;
        session.put("ERRORCLASS", ErrorType.errorMessage);
        StringBuilder sb = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewTdsChallanTranLoadDAO chalantran = factory.getViewTdsChallanTranLoadDAO();
            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment assessment = (Assessment) session.get("ASSESSMENT");
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
                                    String entity_code = clientMast.getEntity_code();
                                    String client_code = clientMast.getClient_code();
                                   // System.out.println("viewtdschallantranloadx--");
                                   //List<ViewTdsChallanTranLoad> viewtdschallantranloadx = chalantran.getTdsChallanTranLoadData(entity_code, client_code, assessment, getTranChallanFltrSrch(), getSearch(), l_record_MNL - 1, l_record_MXL, utl, false, isFromAllocatedChallan());
                                    ChallanDashboardQuery query =new ChallanDashboardQuery();
                                    String sumqueryy = query.getamountQuery(clientMast,assessment);
                                    String getmultiSum=  new DbFunctionExecutorAsString().execute_oracle_function_as_string(sumqueryy);
                                    String datalistquery = query.challanRecordQuery(entity_code, client_code, assessment, getTranChallanFltrSrch(), getSearch(), l_record_MNL, l_record_MXL, utl, false, isFromAllocatedChallan());
                                    List<ChallanBrowseEntity> viewtdschallantranload  = new DbGenericFunctionExecutor().getGenericList(new ChallanBrowseEntity(), datalistquery);
                                   

                                   
                                    //ViewTdsChallanTranLoadDAO chalantran1 = factory.getViewTdsChallanTranLoadDAO();
                                   // System.out.println("listchalantranData--");
                                   // ChallanGrossTotalSumList listchalantranData = chalantran1.readAllChallanDataSum(client_code, assessment, getTranChallanFltrSrch(), getSearch(), utl);
                                   // String sumAmoutquery = query.gettdsChallanAmounts(clientMast);
                                   // String getsumAmout=  new DbFunctionExecutorAsString().execute_oracle_function_as_string(sumAmoutquery);
                                   
                                    ChallanSupport cs = new ChallanSupport();
                                    sb = cs.getChallanBrowseGrid(viewtdschallantranload, getStartIndex(), getPageNumber(), getTranChallanFltrSrch(), getSearch(), assessment, client_code, clientMast, isFromAllocatedChallan(),getmultiSum);
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
                    session.put("MANAGECHALLANRECPERPG", String.valueOf(getSetRecPerPage()));
                    return_value = "success";
                    return_view = "input_success";
                    inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
                }

            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);

                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    Util utl;
    private Map<String, Object> session;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private String filterFlag;
    private ChallanFilterEntity tranChallanFltrSrch;
    private boolean fromAllocatedChallan;

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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ChallanFilterEntity getTranChallanFltrSrch() {
        return tranChallanFltrSrch;
    }

    public void setTranChallanFltrSrch(ChallanFilterEntity tranChallanFltrSrch) {
        this.tranChallanFltrSrch = tranChallanFltrSrch;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public boolean isFromAllocatedChallan() {
        return fromAllocatedChallan;
    }

    public void setFromAllocatedChallan(boolean fromAllocatedChallan) {
        this.fromAllocatedChallan = fromAllocatedChallan;
    }

}
