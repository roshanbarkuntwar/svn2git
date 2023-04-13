/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ValidationAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String fileSeqNo;
    private String tokenNo;
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
    private LinkedHashMap<String, String> selectProcessErrorType;
    private LinkedHashMap<String, String> loginLevelList;
    private int loginLevel;

    private String parentFlag;
    private String childFlag;
    private String processCnfChkBx;
    private String clientLoginLevel;
    private String selectedValue;
    private String reprocessFlag;
    private String processLevel;
    private String selectedUserLevel;

    public ValidationAction15GH() {
        utl = new Util();
        loginLevelList = new LinkedHashMap<String, String>();
        selectProcessErrorType = new LinkedHashMap<String, String>();
        selectProcessErrorType.put("1", "All");
        selectProcessErrorType.put("0", "Exclude Review Error");
    }//end constructor

    @Override
    public String execute() throws Exception {

        session.put("ACTIVE_TAB", "15GHValidation");

        String l_return_value = "input";
        setUpload_purpose("R");
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

            try {
                ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
                String isClientTeanLevel = "1";
                String clientLevelType = "1";

                try {
                    isClientTeanLevel = (String) session.get("CLIENT_LOGIN_LEVEL");
                    clientLevelType = clientMast.getClient_level_type();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!utl.isnull(getSelectedValue()) && getSelectedValue().equalsIgnoreCase("R")) {
                    setProcessLevel(clientLevelType);// branch level
                } else {
                    setProcessLevel(isClientTeanLevel);
                }

                try {
                    int level = Integer.valueOf((String) session.get("CLIENT_LOGIN_LEVEL"));
                    for (int i = level; i <= 6; i++) {
                        loginLevelList.put(i + "", "Level-" + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String loginLevel = "";
                if (Integer.valueOf(isClientTeanLevel) == Integer.valueOf(clientLevelType)) {
                    loginLevel = "LastLevel";
                } else {
                    loginLevel = "upperLevel";
                }
                setClientLoginLevel(loginLevel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
                l_return_value = "success";
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

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
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

    public LinkedHashMap<String, String> getLoginLevelList() {
        return loginLevelList;
    }

    public void setLoginLevelList(LinkedHashMap<String, String> loginLevelList) {
        this.loginLevelList = loginLevelList;
    }

    public int getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(int loginLevel) {
        this.loginLevel = loginLevel;
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

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public String getSelectedUserLevel() {
        return selectedUserLevel;
    }

    public void setSelectedUserLevel(String selectedUserLevel) {
        this.selectedUserLevel = selectedUserLevel;
    }

}//end class
