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
public class ErrorDetailSummaryAction extends ActionSupport implements SessionAware {

    private final Util utl;

    private Map<String, Object> session;

    InputStream inputStream;

    private int recordCount;
    private int totalPages;
    private long totalRecordsCount;

    private String action;
    private String l_error_type_code;
    private String l_error_type_name;
    private String l_error_name;
    private String l_error_code;
    private String table_name;
    private String ReadonlyFlag;
    private String e2;

    private String search;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String processLevel;
    private String dataGridAction;
    private String bulkDownloadFor;
    

    public ErrorDetailSummaryAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("showErrSummaryDetails")) {
            setDataGridAction("getErrDetailSummaryDataAction");
            setBulkDownloadFor("error_" + getL_error_code());

            String l_entity_code = "";
            String l_client_code = "";
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = null;
            if (viewClientMast != null) {
                l_entity_code = viewClientMast.getEntity_code();
                l_client_code = viewClientMast.getClient_code();
                asmt = (Assessment) session.get("ASSESSMENT");
            }

            DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
            Long total = 0l;
            try {
                ProcessErrorsDB proc_err = new ProcessErrorsDB();
                String l_query = proc_err.getErrorDetailSammaryCountQry(asmt, l_entity_code, l_client_code, getL_error_code(), getProcessLevel());

                utl.generateSpecialLog("RPE-0004 (deductee transaction second screen summary count query)--134--", l_query);
                total = objDBFunction.execute_oracle_function_as_integar(l_query);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setPageNumber("0");
            setTotalRecordsCount(total);
            int pages = 0;
            //System.out.println("total pg count is-->"+total);
            if (total > 0) {
                String recNumber = (String) session.get("SHOWERRORDETAILSRCHPG");
                session.put("SummaryErrorCode", getL_error_code());
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
            return_view = "success";
        }System.out.println("return_viewreturn_view-"+return_view);
        return return_view;
    }//end method

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public String getAction() {
        return action;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
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

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(String fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

   

}//end class
