/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.opensymphony.xwork2.ActionSupport;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ProcessErrorAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    Util utl;

    private String tokenNo;
    private String fileSeqNo;
    private String tableName;
    private String errorType;
    private String errorTypeProc;
    private String validate;
    private String ckeckRecordFlag;
    private String BckAction;
    private String file_Name;
    private String fileSize;
    private String upload_purpose;
    private String sessionResult;
    private String l_error_process_type;
    private LinkedHashMap<String, String> selectProcessErrorType;

    private String parentFlag;
    private String childFlag;
    private String processCnfChkBx;
    private String reprocessFlag;
    private String loginLevel;
    private String loginLevelFlag;
    private String selectedUserLevel;
    private Long errorProcessReportCount;

    public ProcessErrorAction() {
        utl = new Util();
        selectProcessErrorType = new LinkedHashMap<String, String>();
        selectProcessErrorType.put("1", "All");
        selectProcessErrorType.put("0", "Exclude Review Error");
    }

    @Override
    public String execute() {
        System.out.println("errorProcessReportCount--"+errorProcessReportCount);
        utl.generateLog(" New Error Process Request", "");
        session.put("ACTIVE_TAB", "errorStatus");
        
        String l_return_value = "input";
        setUpload_purpose("R");
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
        ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
        setLoginLevel(client.getCode_level());
        try {
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

            if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
                l_return_value = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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

    public String getErrorTypeProc() {
        return errorTypeProc;
    }

    public void setErrorTypeProc(String errorTypeProc) {
        this.errorTypeProc = errorTypeProc;
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

    public LinkedHashMap<String, String> getSelectProcessErrorType() {
        return selectProcessErrorType;
    }

    public void setSelectProcessErrorType(LinkedHashMap<String, String> selectProcessErrorType) {
        this.selectProcessErrorType = selectProcessErrorType;
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

    public String getLoginLevelFlag() {
        return loginLevelFlag;
    }

    public void setLoginLevelFlag(String loginLevelFlag) {
        this.loginLevelFlag = loginLevelFlag;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
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

}
