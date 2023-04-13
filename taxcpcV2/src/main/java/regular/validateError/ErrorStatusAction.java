/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysProcessLogDAO;
import dao.ViewDisplayReportListDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.LhssysParameters;
import hibernateObjects.LhssysProcessLog;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ErrorStatusAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    private String tokenNo;
    private String errorTypeProc;
    private String loginLevelFlag;
    private String loginLevel;
    private String reprocessFlag;
    private String validate;
    private String h_vauation_right;
    private String processCnfChkBxError;
    private String upload_purpose;
    private String l_error_process_type;

    private String killBtn;
    private String viewBtn;
    private String fvuTextBtn;
    private String fvuBulkTxtBtn;
    private String fvuBtn;
    private String fvuDownloadBtn;
    private String processBtn;
    private String fvuDownloadClobBtn;
    private String sessionResult;
    private String clobFlag;
    private TokenStatusAttribs tokenUploadObj;
    private Long errorProcessReportCount;
    private String errorProReportDwnFlag;
    private String NEW_FVU_GEN_FLAG;

    Util utl;

    public ErrorStatusAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
         
        session.put("ACTIVE_TAB", "errorStatus");
        String NEW_FVU_GEN_FLAG = (String) session.get("NEW_FVU_GEN_FLAG") != null ? (String) session.get("NEW_FVU_GEN_FLAG") : "";
        setNEW_FVU_GEN_FLAG(NEW_FVU_GEN_FLAG);
        String clobLogFlag = (String) session.get("CLOB_LOG_FLAG") != null ? (String) session.get("CLOB_LOG_FLAG") : "";
        utl.generateLog("CLOB_LOG_FLAG", clobLogFlag);
        if(utl.isnull(clobLogFlag)){
         try{   
         String queryToFullcsiFileName = "select t.parameter_value from lhssys_parameters t where t.parameter_name LIKE 'CLOB_LOG_FLAG'";
         clobLogFlag = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFullcsiFileName);
         utl.generateLog("Clob value by using Query",clobLogFlag);     
         }catch(Exception e){
             
         }
        }
        setClobFlag(clobLogFlag);   
        String resultValue = (String) session.get("SESSIONRESULT");
        if (!utl.isnull(resultValue)) {
            setSessionResult(resultValue);
            session.remove("SESSIONRESULT");
        }

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment assesment = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");

        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
        try {
            l_client_loginid_seq = (Long) sessionId;
        } catch (Exception e) {
            l_client_loginid_seq = 0l;
        }

        if (client != null && assesment != null) {
            String buttonEnableFlag = (String) session.get("ENABLEBUTTONVFG") != null ? (String) session.get("ENABLEBUTTONVFG") : "";
            utl.generateLog("ENABLE_FUNCTIONAL_BUTTON_VFG Flag ", buttonEnableFlag);
            GetTokenTransactions gtt = new GetTokenTransactions();
            tokenUploadObj = gtt.getTokenSatus(client.getEntity_code(), client.getClient_code(), assesment, user.getUser_code(), "PROCESS_ERROR", l_client_loginid_seq);
            if (!utl.isnull(tokenUploadObj.getProcessType())) {
                int proc_type = Integer.parseInt(tokenUploadObj.getProcessType());
                if (proc_type == 0) {
                    tokenUploadObj.setProcessTypeName("Login Level");
                } else {
                    tokenUploadObj.setProcessTypeName("All (Login & Branch Level)");
                }
                if (buttonEnableFlag.equalsIgnoreCase("T")) {
                    setButtonsActive(tokenUploadObj.getProcessStatus(), Integer.valueOf(client.getCode_level()));
                }
                if (tokenUploadObj.getProcessTypeName().equalsIgnoreCase("Login Level")) {
                   // setFvuBulkTxtBtn("disabled='disabled'");
                }
            }
            System.out.println("tokenUploadObj.getTokenNo()"+tokenUploadObj.getTokenNo());
            if(!utl.isnull(tokenUploadObj.getTokenNo())){
                geterrorProcessReportCount(tokenUploadObj.getTokenNo());
            }
        }
        
        

        return SUCCESS;
    }//end method

    public void setButtonsActive(String processStatus, int clientLoginLevel) {
        switch (processStatus) {
            case "EA":  //Error Processing Started
            case "EB":   //Error Processing Fail
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                setFvuBulkTxtBtn("disabled=true");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EC":   //Error Processing successfully completed
                setKillBtn("disabled=true");
                setViewBtn("");
                setFvuTextBtn("");
                setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "ED":    //FVU Text file Creation Started
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
               // setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EE":   //FVU Text file Creation Fail
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
//                setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EF":     //FVU Text file Creation successfully completed
                setKillBtn("disabled=true");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EG":   //FVU Text file (Bulk) Creation Started
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EH":    //FVU Text file (Bulk) Creation Failed
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "EI":   //FVU Text file (Bulk) Creation successfully completed
                setKillBtn("disabled=true");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "FA":  //FVU file(s) Creation Started
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "FB":  //FVU file(s) Creation Failed
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                setFvuDownloadClobBtn("disabled=true");
                break;

            case "FC":  //FVU 27A PDF file Creation successfully completed
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                //setFvuBulkTxtBtn("");
                setFvuBtn("");
                setFvuDownloadBtn("disabled=true");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("");
                break;

            case "FD":    //FVU -Error File Generated
                setKillBtn("");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
               // setFvuBulkTxtBtn("");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("");
                setProcessBtn("disabled=true");
                setFvuDownloadClobBtn("");
                break;
            case "ZK":  //Session killed successfully
                setKillBtn("disabled=true");
                setViewBtn("disabled=true");
                setFvuTextBtn("disabled=true");
                setFvuBulkTxtBtn("disabled=true");
                setFvuBtn("disabled=true");
                setFvuDownloadBtn("");
                setProcessBtn("");
                setFvuDownloadClobBtn("disabled=true");

            default:
                break;
        }//end switch case

        if (clientLoginLevel == 6) {
            setFvuBulkTxtBtn("disabled='disabled'");
        }
    }//end method
    
    //code for generate error process reports
    void geterrorProcessReportCount(String tokenNo) {
    
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try{
        Long parent_process_seqno = Long.parseLong(tokenNo);
        fvuPid_Tflag_count = 0L;
        fvuPid_Fflag_count = 0L;
        lhssysLog_listsize = 0L;
        setErrorProcessReportCount(lhssysLog_listsize);
        LhssysProcessLogDAO lhssysProcessLogDAO = factory.getLhssysProcessLogDAO();
        List<LhssysProcessLog> lhssysLog_list = lhssysProcessLogDAO.getLhssysProcessLogbyParentProcessSeqno(parent_process_seqno);
        utl.generateLog("query--- ","");
        if (lhssysLog_list != null) {
            lhssysLog_listsize = (long) lhssysLog_list.size();
            utl.generateLog("lhssysLog_listsize ",lhssysLog_listsize);
            if (lhssysLog_listsize > 0) {
             setErrorProcessReportCount(lhssysLog_listsize);   
                fvuPid_Tflag_count = lhssysLog_list
                        .stream()
                        .filter(c -> c.getFvu_pid_flag() != null)
                        .filter(c -> c.getFvu_pid_flag().equalsIgnoreCase("T"))
                        .count();
                utl.generateLog("fvu_pid_Tflag_counting " , fvuPid_Tflag_count);

                fvuPid_Fflag_count = lhssysLog_list
                        .stream()
                        .filter(c -> c.getFvu_pid_flag() != null)
                        .filter(c -> c.getFvu_pid_flag().equalsIgnoreCase("F"))
                        .count();
               utl.generateLog("fvu_pid_flag_Fcounting " , fvuPid_Fflag_count);

            }
         
        }

        }catch(Exception e){
            
        }
        
        
            
    }
    
  private Long fvuPid_Tflag_count;
  private Long fvuPid_Fflag_count;
  private Long lhssysLog_listsize;
    
    
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getFvuBulkTxtBtn() {
        return fvuBulkTxtBtn;
    }

    public void setFvuBulkTxtBtn(String fvuBulkTxtBtn) {
        this.fvuBulkTxtBtn = fvuBulkTxtBtn;
    }

    public String getFvuTextBtn() {
        return fvuTextBtn;
    }

    public void setFvuTextBtn(String fvuTextBtn) {
        this.fvuTextBtn = fvuTextBtn;
    }

    public String getFvuDownloadBtn() {
        return fvuDownloadBtn;
    }

    public void setFvuDownloadBtn(String fvuDownloadBtn) {
        this.fvuDownloadBtn = fvuDownloadBtn;
    }

    public String getFvuBtn() {
        return fvuBtn;
    }

    public void setFvuBtn(String fvuBtn) {
        this.fvuBtn = fvuBtn;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getKillBtn() {
        return killBtn;
    }

    public void setKillBtn(String killBtn) {
        this.killBtn = killBtn;
    }

    public String getProcessBtn() {
        return processBtn;
    }

    public void setProcessBtn(String processBtn) {
        this.processBtn = processBtn;
    }

    public String getErrorTypeProc() {
        return errorTypeProc;
    }

    public void setErrorTypeProc(String errorTypeProc) {
        this.errorTypeProc = errorTypeProc;
    }

    public String getLoginLevelFlag() {
        return loginLevelFlag;
    }

    public void setLoginLevelFlag(String loginLevelFlag) {
        this.loginLevelFlag = loginLevelFlag;
    }

    public String getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(String loginLevel) {
        this.loginLevel = loginLevel;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getH_vauation_right() {
        return h_vauation_right;
    }

    public void setH_vauation_right(String h_vauation_right) {
        this.h_vauation_right = h_vauation_right;
    }

    public String getProcessCnfChkBxError() {
        return processCnfChkBxError;
    }

    public void setProcessCnfChkBxError(String processCnfChkBxError) {
        this.processCnfChkBxError = processCnfChkBxError;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public String getViewBtn() {
        return viewBtn;
    }

    public void setViewBtn(String viewBtn) {
        this.viewBtn = viewBtn;
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
   public String getClobFlag() {
        return clobFlag;
    }

    public void setClobFlag(String clobFlag) {
        this.clobFlag = clobFlag;
    }

    public String getFvuDownloadClobBtn() {
        return fvuDownloadClobBtn;
    }

    public void setFvuDownloadClobBtn(String fvuDownloadClobBtn) {
        this.fvuDownloadClobBtn = fvuDownloadClobBtn;
    }

    public Long getErrorProcessReportCount() {
        return errorProcessReportCount;
    }

    public void setErrorProcessReportCount(Long errorProcessReportCount) {
        this.errorProcessReportCount = errorProcessReportCount;
    }

    public String getErrorProReportDwnFlag() {
        return errorProReportDwnFlag;
    }

    public void setErrorProReportDwnFlag(String errorProReportDwnFlag) {
        this.errorProReportDwnFlag = errorProReportDwnFlag;
    }

    public Long getFvuPid_Tflag_count() {
        return fvuPid_Tflag_count;
    }

    public void setFvuPid_Tflag_count(Long fvuPid_Tflag_count) {
        this.fvuPid_Tflag_count = fvuPid_Tflag_count;
    }

    public Long getFvuPid_Fflag_count() {
        return fvuPid_Fflag_count;
    }

    public void setFvuPid_Fflag_count(Long fvuPid_Fflag_count) {
        this.fvuPid_Fflag_count = fvuPid_Fflag_count;
    }

    public Long getLhssysLog_listsize() {
        return lhssysLog_listsize;
    }

    public void setLhssysLog_listsize(Long lhssysLog_listsize) {
        this.lhssysLog_listsize = lhssysLog_listsize;
    }

    public String getNEW_FVU_GEN_FLAG() {
        return NEW_FVU_GEN_FLAG;
    }

    public void setNEW_FVU_GEN_FLAG(String NEW_FVU_GEN_FLAG) {
        this.NEW_FVU_GEN_FLAG = NEW_FVU_GEN_FLAG;
    }

   

   
    
    
    
   
}//end class
