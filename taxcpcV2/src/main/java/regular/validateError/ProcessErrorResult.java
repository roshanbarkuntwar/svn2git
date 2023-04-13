/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhssysProcessLogIud;
import dao.LhssysProcessLogDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ProcessErrorResult extends ActionSupport implements SessionAware {

    Util utl;
    private Map<String, Object> session;

    private LinkedHashMap<String, String> selectProcessErrorType;

    private ArrayList<ViewTranLoadErrorBean> viewTranLoadErrorsSummery;

    private String action;
    private String tokenNo;
    private String fileSeqNo;
    private String tableName;
    private String errorType;
    private String validate;
    private String ckeckRecordFlag;
    private String BckAction;
    private String file_Name;
    private String fileSize;
    private String upload_purpose;
    private String sessionResult;
    private String l_error_process_type;
    private String errorTypeProc;
    private String parentFlag;
    private String childFlag;
    private String processCnfChkBx;
    private String reprocessFlag;
    private String loginLevel;
    private String selectedUserLevel;
    private String loginLevelFlag;
    private String summaryRefreshFlag;
    private long codeLevel;
    private Long errorProcessReportCount;
    private String err_process_rep_btn;
    private String refreshFlag;

    InputStream inputStream;

    public ProcessErrorResult() {
        utl = new Util();
        selectProcessErrorType = new LinkedHashMap<>();
        selectProcessErrorType.put("1", "All");
        selectProcessErrorType.put("0", "Exclude Review Error");
    }

    @Override
    public String execute() throws UnsupportedEncodingException {
        String l_return_value = "input";
        String return_msg = "";

        setUpload_purpose("R");
        String result_value = (String) session.get("session_result");
        result_value = utl.isnull(result_value) ? "" : result_value;
        if (!utl.isnull(result_value)) {
            setSessionResult(result_value);
            session.remove("session_result");
        }
        if (!utl.isnull(getParentFlag()) && !utl.isnull(getChildFlag())) {
            session.put("PARENTFLAG", getParentFlag());
            session.put("CHILDFLAG", getChildFlag());
        }
        int l_process_type_value = 0;
        try {
            if (!utl.isnull(getL_error_process_type())) {
                l_process_type_value = Integer.parseInt(getL_error_process_type());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            setProcessCnfChkBx(getProcessCnfChkBx());
        } catch (Exception e) {
        }
        int execute_procedure_result = 1;// in case of error
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String l_client_code = "";
        String l_entity_code = "";
        String l_acc_year = "";
        int l_quarter_no = 0;
        Date l_from_date = null;
        Date l_to_date = null;
        String l_tds_type_code = "";
        ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
        ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
        UserMast userMast = (UserMast) session.get("LOGINUSER");

        Assessment asmt = null;
        
        try{
         LhssysProcessLogDAO lhssysProcessLogDAO = factory.getLhssysProcessLogDAO();
         Long Token_No =Long.parseLong(getTokenNo());
         utl.generateLog(" Token_No ",Token_No);
         LhssysProcessLog lhssysLogObj = lhssysProcessLogDAO.readProcessbySeqNo(Token_No);
         utl.generateLog(" lhssysLogObj ",lhssysLogObj);
         String fvu_pid_flag = lhssysLogObj.getFvu_pid_flag()!=null? lhssysLogObj.getFvu_pid_flag() :"";
         utl.generateLog(" fvu_pid_flag root ",fvu_pid_flag);
         if(!utl.isnull(fvu_pid_flag) && fvu_pid_flag.equalsIgnoreCase("S")){
             if(errorProcessReportCount == 0){
               setErr_process_rep_btn("disabled=true");
             }
         }
         
        }catch(Exception e){
            
        }  
         
        
        if (client != null) {
            l_client_code = client.getClient_code();
            l_entity_code = client.getEntity_code();
            asmt = (Assessment) session.get("ASSESSMENT");
            l_acc_year = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();
            l_quarter_no = Integer.parseInt(quarterNo);
            l_from_date = asmt.getQuarterFromDate();
            l_to_date = asmt.getQuarterToDate();
            l_tds_type_code = asmt.getTdsTypeCode();

            if (utl.isnull(getAction()) && !utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
                String genURlPanVerify = "DynamicProcessUploadResultAction?validate=true&upload_purpose=" + getUpload_purpose() + "&l_error_process_type=" + getL_error_process_type() + "processCnfChkBx=" + getProcessCnfChkBx();
                session.put("GENURlPANVERIFY", genURlPanVerify);
                setTableName(null);
                setFileSeqNo(null);

                if (!utl.isnull(getReprocessFlag()) && getReprocessFlag().equalsIgnoreCase("T")) {
                    int l_fileseqno = 0;
                    try {
                        setLoginLevelFlag(utl.isnull(getLoginLevelFlag()) ? "" + client.getCode_level() + "" : getLoginLevelFlag());
                        int rowIdSeq = 0;
                        execute_procedure_result = executeProcForErrorProcessonOkBtn(getTokenNo(), l_entity_code, l_client_code, l_acc_year, l_quarter_no, l_from_date, l_to_date,
                                l_tds_type_code, l_fileseqno, rowIdSeq, getTableName(), getUpload_purpose(), l_process_type_value, Integer.parseInt(getLoginLevelFlag()),
                                getErrorTypeProc(), userMast.getUser_code());
                        utl.generateSpecialLog("RPE-0001 (Execute Process Error3 Result)----", execute_procedure_result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                ProcessErrorsDB procErr = new ProcessErrorsDB();
                String l_query = procErr.getProcessErrorSummaryQuery(l_entity_code, l_client_code, getErrorTypeProc(), asmt, getLoginLevelFlag());

                utl.generateSpecialLog("RPE-0002 (1st Screen Summary Query)--219--", l_query);

                GetDataListThroughWorkbook1 objGetDataListThroughWorkbook = new GetDataListThroughWorkbook1();
                ArrayList<ViewTranLoadErrorBean> viewloaderrorsummery = objGetDataListThroughWorkbook.getTranLoadErrorSummaryDetailsList(l_query, utl);

                if (viewloaderrorsummery != null) {
                    setViewTranLoadErrorsSummery(viewloaderrorsummery);
                    setCkeckRecordFlag("true");
                } else {
                    setCkeckRecordFlag("false");
                }

                if (viewloaderrorsummery != null && !utl.isnull(getSummaryRefreshFlag()) && getSummaryRefreshFlag().equalsIgnoreCase("true")) {
                    l_return_value = "ajax_result";
                } else {
                    l_return_value = "success";
                }
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getToken")) {

                Long l_client_loginid_seq = null;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }
                return_msg = this.generateErrorProcessTokenNo(client, asmt, userMast, l_client_loginid_seq, getLoginLevelFlag());
                l_return_value = "token_result";
                inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));

            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("refresh")) {
                try {
                    String processType = "PROCESS_ERROR";
                    if(!utl.isnull(getRefreshFlag()) && getRefreshFlag().equalsIgnoreCase("bulkFVUText")){
                      processType = "BULK_FVU_TEXT_GEN";  
                    }
                    
                    GetTokenTransactions gb = new GetTokenTransactions();
                    TokenStatusAttribs tokenSatus = gb.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, userMast.getUser_code(), processType, Long.parseLong(getTokenNo()));
                    if (tokenSatus != null) {
                        return_msg = new Gson().toJson(tokenSatus);
                        l_return_value = "token_refreshed";
                        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("downloadError")) {
                Long l_client_loginid_seq = null;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }
//                String asmt_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
                GetTokenTransactions generateToken = new GetTokenTransactions();
                String token = generateToken.generateTokenGlobalMethod(asmt, client, userMast, l_client_loginid_seq, (null == userMast.getUser_level()) ? "" : userMast.getUser_level() + "", "DA",
                        "Process Initiated by user !", "PROCESS_ERROR_DOWNLOAD", "PROCESS_INSERT", "", "");
                if (!utl.isnull(token)) {
//                    String a_download_type = "DA";
//
//                    if ("4".equals(asmt.getQuarterNo()) && "24Q".equals(asmt.getTdsTypeCode())) {
//                        a_download_type = "SALARY_TRAN_ERROR";
//                    }
//
//                    ProcTranLoadErrorDwnld pre = new ProcTranLoadErrorDwnld();
//
//                    String proc_err_dwnld_status = pre.executeProcedure(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt_acc_year,
//                            asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(),
//                            l_client_loginid_seq, getCodeLevel(),
//                            a_download_type, // Process type
//                            "", userMast.getUser_code(), Long.parseLong(token));
//                    if (!utl.isnull(proc_err_dwnld_status) && proc_err_dwnld_status.equalsIgnoreCase("0")) {
                    return_msg = token;
//                    } else {
//                        return_msg = "error";
//                    }

                }

                l_return_value = "token_refreshed";
                inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
//
            } else {
                l_return_value = "input";
            }
        }
        utl.generateLog("l_return_value...", l_return_value);
        return l_return_value;

    }//End method

    public String generateErrorProcessTokenNo(ViewClientMast client, Assessment asmt, UserMast userMast, Long l_client_loginid_seq, String login_level_flag) {
        String return_msg = "";

        ProcLhssysProcessLogIud proc = new ProcLhssysProcessLogIud();
        String procResult = proc.executeProcedure(null, client.getEntity_code(), client.getClient_code(), asmt.getAccYear(),
                asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(),
                l_client_loginid_seq, null, null, null, null, null, null, null, null, null, null, "PROCESS_ERROR", userMast.getUser_code(),
                "PROCESS_INSERT", //use for proccess insert in lhssys_process_log indicate process is start
                null, login_level_flag, "", "","");

        if (!utl.isnull(procResult) && !procResult.equalsIgnoreCase("-1")) {
            GetTokenTransactions gt = new GetTokenTransactions();
            TokenStatusAttribs tokenSatus = gt.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, userMast.getUser_code(),
                    "PROCESS_ERROR", l_client_loginid_seq);

            if (tokenSatus != null) {
                return_msg = "success##" + tokenSatus.getTokenNo();
            } else {
                return_msg = "error##null";
            }
        }
        return return_msg;
    }//end method

    public int executeProcForErrorProcessonOkBtn(String tokenNo, String l_entity_code, String l_client_code, String l_acc_year, int l_quarter_no, Date l_from_date, Date l_to_date,
            String l_tds_type_code, int l_fileseqno, int rowIdSeq, String tableName, String uploadPurpose, int l_process_type_value, int client_login_level, String errorTypePro, String user_code) {
        int result = 1;
        try {
            ProcessTdsTranLoadError call_db_pr2 = new ProcessTdsTranLoadError();
            result = call_db_pr2.execute_procedure(tokenNo, l_entity_code, l_client_code, l_acc_year, l_quarter_no, l_from_date, l_to_date, l_tds_type_code,
                    l_fileseqno, rowIdSeq, tableName, uploadPurpose, l_process_type_value, client_login_level, errorTypePro, user_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }//End method

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getLoginLevelFlag() {
        return loginLevelFlag;
    }

    public void setLoginLevelFlag(String loginLevelFlag) {
        this.loginLevelFlag = loginLevelFlag;
    }

    public String getFileSeqNo() {
        return fileSeqNo;
    }

    public void setFileSeqNo(String fileSeqNo) {
        this.fileSeqNo = fileSeqNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getCkeckRecordFlag() {
        return ckeckRecordFlag;
    }

    public String getSummaryRefreshFlag() {
        return summaryRefreshFlag;
    }

    public void setSummaryRefreshFlag(String summaryRefreshFlag) {
        this.summaryRefreshFlag = summaryRefreshFlag;
    }

    public void setCkeckRecordFlag(String ckeckRecordFlag) {
        this.ckeckRecordFlag = ckeckRecordFlag;
    }

    public String getBckAction() {
        return BckAction;
    }

    public void setBckAction(String BckAction) {
        this.BckAction = BckAction;
    }

    public String getFile_Name() {
        return file_Name;
    }

    public void setFile_Name(String file_Name) {
        this.file_Name = file_Name;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getL_error_process_type() {
        return l_error_process_type;
    }

    public void setL_error_process_type(String l_error_process_type) {
        this.l_error_process_type = l_error_process_type;
    }

    public String getErrorTypeProc() {
        return errorTypeProc;
    }

    public void setErrorTypeProc(String errorTypeProc) {
        this.errorTypeProc = errorTypeProc;
    }

    public LinkedHashMap<String, String> getSelectProcessErrorType() {
        return selectProcessErrorType;
    }

    public void setSelectProcessErrorType(LinkedHashMap<String, String> selectProcessErrorType) {
        this.selectProcessErrorType = selectProcessErrorType;
    }

    public ArrayList<ViewTranLoadErrorBean> getViewTranLoadErrorsSummery() {
        return viewTranLoadErrorsSummery;
    }

    public void setViewTranLoadErrorsSummery(ArrayList<ViewTranLoadErrorBean> viewTranLoadErrorsSummery) {
        this.viewTranLoadErrorsSummery = viewTranLoadErrorsSummery;
    }

    public String getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(String parentFlag) {
        this.parentFlag = parentFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public String getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(String loginLevel) {
        this.loginLevel = loginLevel;
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

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public long getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(long codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getSelectedUserLevel() {
        return selectedUserLevel;
    }

    public void setSelectedUserLevel(String selectedUserLevel) {
        this.selectedUserLevel = selectedUserLevel;
    }

    public Long getErrorProcessReportCount() {
        return errorProcessReportCount;
    }

    public void setErrorProcessReportCount(Long errorProcessReportCount) {
        this.errorProcessReportCount = errorProcessReportCount;
    }

    public String getErr_process_rep_btn() {
        return err_process_rep_btn;
    }

    public void setErr_process_rep_btn(String err_process_rep_btn) {
        this.err_process_rep_btn = err_process_rep_btn;
    }

    public String getRefreshFlag() {
        return refreshFlag;
    }

    public void setRefreshFlag(String refreshFlag) {
        this.refreshFlag = refreshFlag;
    }

}
