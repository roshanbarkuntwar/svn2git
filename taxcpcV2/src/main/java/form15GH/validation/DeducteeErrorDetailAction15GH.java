/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DeducteeErrorDetailAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    InputStream inputStream;
    private String action;
    private String l_error_type_code;
    private String l_error_type_name;
    private String l_error_name;
    private String l_error_code;
    private String table_name;
    private String processCnfChkBx;
    private String ReadonlyFlag;
    private String upload_purpose;
    private String l_error_process_type;
    private String clientLoginLevel;
    private String selectedValue;
    private String reprocessFlag;

    private int recordCount;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String dataGridAction;
    private String sessionResult;
    private String defaultColumnValue;
    private String selectedUserLevel;

    public DeducteeErrorDetailAction15GH() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {

        String l_return_value = "success";
        try {
            String result_value = (String) session.get("session_result");
            result_value = utl.isnull(result_value) ? "" : result_value;
            if (!utl.isnull(result_value)) {
                setSessionResult(result_value);
                session.remove("session_result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("showDeductee15ghErrDetails")) {
                setDataGridAction("deducteeErrorDetailDataAction");

                String l_entity_code = "";
                String l_client_code = "";

                ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                if (viewClientMast != null) {
                    l_entity_code = viewClientMast.getEntity_code();
                    l_client_code = viewClientMast.getClient_code();
                    Assessment asmt = (Assessment) session.get("ASSESSMENT");

                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                    Long total = 0l;
                    try {
                        String l_deductee_15gh_error_query = proc_err.getSecondScreenDeducteeErrCount15GHQuery(asmt, l_entity_code, l_client_code, getL_error_code());

                        total = objDBFunction.execute_oracle_function_as_integar(l_deductee_15gh_error_query);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setPageNumber("0");
                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {

                        String recNumber = getRecordsPerPage();
                        if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                            recNumber = (String) session.get("SHOWDEDUCTEEERRORDETAILSRCHPG");
                        } else {
                            recNumber = getRecordsPerPage();

                        }

                        session.put("deducteeErrorCode", getL_error_code());
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
                        int pageNoInt = Integer.parseInt(utl.isnull(getPageNumber()) ? "0" : getPageNumber());
                        if (utl.isnull(getPageNumber()) || pageNoInt > pages || pageNoInt == 0) {
                            setPageNumber("1");
                        }
                    } else {
                        setPageNumber("0");
                    }
                    setTotalPages(pages);
                    String columnName = "";
                    try {
                        String columnNameQuery = proc_err.get15GHColumnNameQuery(asmt, l_entity_code, l_client_code, getL_error_code());

                        DbFunctionExecutorAsString efforrefno = new DbFunctionExecutorAsString();
                        columnName = efforrefno.execute_oracle_function_as_string(columnNameQuery);
                    } catch (Exception e) {
                        columnName = "";
                    }
                    if (!utl.isnull(columnName)) {
                        ErrorManipulationAction15GH obj = new ErrorManipulationAction15GH();
                        String defaultColumnVa = obj.getDefaultColumnValue(columnName, viewClientMast, asmt);
                        setDefaultColumnValue(defaultColumnVa);
                    }
                    l_return_value = "success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
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

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getDefaultColumnValue() {
        return defaultColumnValue;
    }

    public void setDefaultColumnValue(String defaultColumnValue) {
        this.defaultColumnValue = defaultColumnValue;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public String getL_error_process_type() {
        return l_error_process_type;
    }

    public void setL_error_process_type(String l_error_process_type) {
        this.l_error_process_type = l_error_process_type;
    }

    public String getClientLoginLevel() {
        return clientLoginLevel;
    }

    public void setClientLoginLevel(String clientLoginLevel) {
        this.clientLoginLevel = clientLoginLevel;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public String getSelectedUserLevel() {
        return selectedUserLevel;
    }

    public void setSelectedUserLevel(String selectedUserLevel) {
        this.selectedUserLevel = selectedUserLevel;
    }

}
