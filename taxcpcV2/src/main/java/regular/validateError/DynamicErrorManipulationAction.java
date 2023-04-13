/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.LhsTdsDefaultErrorUpdt;
import dao.DbProc.ProcTdsTranLoadErrorIud;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DynamicErrorManipulationAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    Util utl;
    private String action;
    private InputStream inputStream;
    private String ErrorCode;
    private String ColumnCnfData;
    private String TableName;
    private String ColumnName;
    private String ReviewValue;
    private String processCnfChkBx;
    private String errType;
    private String errorType;
    private String errorFlagProc;

    public DynamicErrorManipulationAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";

        if (!utl.isnull(getAction())) {
            ProcessErrorsDB proc_err = new ProcessErrorsDB();
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String l_acc_year = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();
            int l_quarter_no = Integer.parseInt(quarterNo);
            Date l_from_date = asmt.getQuarterFromDate();
            Date l_to_date = asmt.getQuarterToDate();
            String l_tds_type_code = asmt.getTdsTypeCode();

            Long l_client_loginid_seq;//use for procedure
            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }

            if (getAction().equalsIgnoreCase("BlkUpdate")) {
                try {
                    Long fileSeqno = 0L;
                    int l_lineno = 0;
                    int l_rowidSeq = 0;
                    String l_columnData = "";
                    String errorType = "";

                    ProcTdsTranLoadErrorIud objTdsTranLoadErrorIud = new ProcTdsTranLoadErrorIud();
                    int result = objTdsTranLoadErrorIud.Exceute_Bulk_Proc_Tds_Tran_Load_Error_Iud_function(l_entity_code, l_client_code, l_acc_year, l_quarter_no, l_from_date, l_to_date, l_tds_type_code, fileSeqno, l_lineno, l_rowidSeq, l_client_loginid_seq, getTableName(), errorType, getErrorCode(), getColumnName(), l_columnData, getColumnCnfData());
                    if (result == 0) {
                        l_return_message = "update";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    l_return_message = "error";
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("BulkReview")) {
                try {
                    String l_review_query = proc_err.getReviewUpdateQuery(asmt, l_entity_code, l_client_code, getReviewValue(), getErrorCode());
                    utl.generateSpecialLog("Bulk Review  Query 1", l_review_query);
                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                    boolean reviewUpdateResult = objDbFunctionClass.execute_oracle_update_function_as_string(l_review_query);
                    if (reviewUpdateResult) {
                        try {
                            String l_refresh_query = proc_err.singleRecordRefreshAfterReviewUpdate(asmt, l_entity_code, l_client_code, getErrorCode());
                            utl.generateSpecialLog("Bulk Review  Query 2", l_refresh_query);
                            objDbFunctionClass.execute_oracle_update_function_as_string(l_refresh_query);
                        } catch (Exception e) {
                        }

                        l_return_message = "updateReview";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    l_return_message = "error";
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("BulkReviewAll")) {
                try {
                    String l_review_query = proc_err.getAllReviewUpdateQuery(asmt, l_entity_code, l_client_code, getReviewValue(), getErrType());
                    utl.generateSpecialLog("Bulk Review All Query 1", l_review_query);
                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                    boolean bulkUpdateResult = objDbFunctionClass.execute_oracle_update_function_as_string(l_review_query);
                    if (bulkUpdateResult) {
                        try {
                            String l_bulk_refresh_query = proc_err.bulkRecordRefreshAfterReviewUpdate(asmt, l_entity_code, l_client_code, getErrType());
                            utl.generateSpecialLog("Bulk Review All Query 2", l_bulk_refresh_query);
                            boolean execute_oracle_update_function_as_string = objDbFunctionClass.execute_oracle_update_function_as_string(l_bulk_refresh_query);
                        } catch (Exception e) {
                        }

                        l_return_message = "updateReview";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    l_return_message = "error";
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("checkReprocessError")) {
                try {
                    String l_check_process_error = proc_err.getCheckReprocessErrorQuery(asmt, l_entity_code, l_client_code, getReviewValue(), getErrorCode());
                    utl.generateSpecialLog("l_check_process_error query..254.. ", l_check_process_error);
                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    Long total = objDBFunction.execute_oracle_function_as_integar(l_check_process_error);

                    if (total > 0) {
                        l_return_message = "F";
                    } else {
                        l_return_message = "F";
                    }
                } catch (Exception e) {
                    l_return_message = "F";
                }
            } else if (getAction().equalsIgnoreCase("callProcedure")) {

                if (!utl.isnull(getErrorType())) {
                    LhsTdsDefaultErrorUpdt proc = new LhsTdsDefaultErrorUpdt();
                    String result = proc.exceuteProcedure(l_entity_code, l_client_code, l_acc_year, quarterNo, l_from_date, l_to_date, l_tds_type_code, 0, getErrorType(), getErrorFlagProc());
                    if (result.equalsIgnoreCase("0")) {
                        l_return_message = "success";
                    } else {
                        l_return_message = "eror";
                    }
                }
            }
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getColumnCnfData() {
        return ColumnCnfData;
    }

    public void setColumnCnfData(String ColumnCnfData) {
        this.ColumnCnfData = ColumnCnfData;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(String ReviewValue) {
        this.ReviewValue = ReviewValue;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorFlagProc() {
        return errorFlagProc;
    }

    public void setErrorFlagProc(String errorFlagProc) {
        this.errorFlagProc = errorFlagProc;
    }

}
