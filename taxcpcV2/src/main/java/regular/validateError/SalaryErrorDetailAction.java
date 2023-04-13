/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryErrorDetailAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    InputStream inputStream;
    private String action;
    private String l_error_type_code;
    private String l_error_type_name;
    private String l_error_name;
    private String l_error_code;
    private String table_name;
    private String ReadonlyFlag;

    private int recordCount;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String dataGridAction;
    private String processLevel;
    private String bulkDownloadFor;
    private String tokenNo;

    public SalaryErrorDetailAction() {
        utl = new Util();

    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        try {
            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("showSalaryErrSummaryDetails")) {
                    setDataGridAction("getSalaryErrDetailSummaryAction");
                    setBulkDownloadFor("error_" + getL_error_code());
                    
                    String l_entity_code = "";
                    String l_client_code = "";
                    Assessment asmt = null;
                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");

                    if (viewClientMast != null) {
                        l_entity_code = viewClientMast.getEntity_code();
                        l_client_code = viewClientMast.getClient_code();
                        asmt = (Assessment) session.get("ASSESSMENT");
                    }
                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    Long total = 0l;
                    try {
                        SalaryErrorDetailsDB sal_db = new SalaryErrorDetailsDB();
                        String l_query = sal_db.salaryErrorDataCountQuery(l_client_code, l_entity_code, getL_error_code(), asmt);
                        utl.generateSpecialLog("RPE-0008 (salary second screen count query)----", l_query);
                        
                        total = objDBFunction.execute_oracle_function_as_integar(l_query);
                    } catch (Exception e) {
                        total = 0l;
                        e.printStackTrace();
                    }
                    setPageNumber("0");
                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {
                        String recNumber = (String) session.get("SHOWSALARYERRORDETAILSRCHPG");
                        session.put("SalaryErrorCode", getL_error_code());
                        setRecordsPerPage(utl.isnull(recNumber) ? "10" : recNumber);
                        if (!getRecordsPerPage().equalsIgnoreCase("all")) {
                            int recVal = Integer.parseInt(getRecordsPerPage());

                            long mod = total % recVal;
                            int rem = 0;
                            if (mod > 0) {
                                rem = 1;
                            }
                            pages = (int) Math.ceil(total / recVal) + rem;
                        } else {
                            pages = 0;
                        }
                        setPageNumber("1");
                    }
                    setTotalPages(pages);
                    l_return_value = "success";

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }//end method

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

    public String getL_error_type_code() {
        return l_error_type_code;
    }

    public void setL_error_type_code(String l_error_type_code) {
        this.l_error_type_code = l_error_type_code;
    }

    public String getL_error_type_name() {
        return l_error_type_name;
    }

    public void setL_error_type_name(String l_error_type_name) {
        this.l_error_type_name = l_error_type_name;
    }

    public String getL_error_name() {
        return l_error_name;
    }

    public void setL_error_name(String l_error_name) {
        this.l_error_name = l_error_name;
    }

    public String getL_error_code() {
        return l_error_code;
    }

    public void setL_error_code(String l_error_code) {
        this.l_error_code = l_error_code;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(String fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

//    public String getBrowseAction() {
//        return browseAction;
//    }
//
//    public void setBrowseAction(String browseAction) {
//        this.browseAction = browseAction;
//    }
    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

}//end class
