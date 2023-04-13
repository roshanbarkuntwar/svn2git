/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhsTds15ghError;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessErrorResult15GH extends ActionSupport implements SessionAware {

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

    private ArrayList<ViewTranLoadErrorSummaryBean15GH> viewTranLoadErrorsSummery;
    private String parentFlag;
    private String childFlag;
    private String processCnfChkBx;
    private String clientLoginLevel;
    private String selectedValue;
    private String reprocessFlag;
    private String refreshFlag;
    private int loginLevel;
    private String processLevel;
    private String selectedUserLevel;

    public ProcessErrorResult15GH() {
        utl = new Util();
        selectProcessErrorType = new LinkedHashMap<String, String>();
        loginLevelList = new LinkedHashMap<String, String>();
        selectProcessErrorType.put("1", "All");
        selectProcessErrorType.put("0", "Exclude Review Error");
    }//end constructor

    @Override
    public String execute() throws Exception {

        String l_return_value = "input";

        setUpload_purpose("R");
        String result_value = (String) session.get("session_result");
        result_value = utl.isnull(result_value) ? "" : result_value;
        if (!utl.isnull(result_value)) {
            setSessionResult(result_value);
            session.remove("session_result");
        }

        try {
            setProcessCnfChkBx(getProcessCnfChkBx());
            setClientLoginLevel(getClientLoginLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (utl.isnull(getReprocessFlag())) {
            setReprocessFlag("T");
        }

        String execute_procedure_result = "1";// in case of error
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String l_client_code = "";
        String l_entity_code = "";
        String l_acc_year = "";
        int l_quarter_no = 0;
        Date l_from_date = null;
        Date l_to_date = null;
        String l_tds_type_code = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        UserMast user_mast = (UserMast) session.get("LOGINUSER");
        Assessment asmt = null;

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
        }
        try {
            if (!utl.isnull(getClientLoginLevel())) {
                int level = Integer.valueOf((String) session.get("CLIENT_LOGIN_LEVEL"));

                for (int i = level; i <= 6; i++) {
                    loginLevelList.put(i + "", "Level-" + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
            if (!utl.isnull(getReprocessFlag()) && getReprocessFlag().equalsIgnoreCase("T")) {
                ProcLhsTds15ghError call_db_pr2 = new ProcLhsTds15ghError();
                try {
                    execute_procedure_result = call_db_pr2.executeProcedure15GH(l_entity_code, l_client_code, l_acc_year, l_quarter_no, l_from_date, l_to_date, l_tds_type_code, getLoginLevel(), user_mast.getUser_code(), getTokenNo());
                    utl.generateSpecialLog("15GH-PE-0001 (Execute Process Error3 Result)----", execute_procedure_result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!utl.isnull(getRefreshFlag()) && getRefreshFlag().equalsIgnoreCase("true")) {
                ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                String l_query = proc_err.getFirstScreen15GHSummaryQuery(asmt, l_entity_code, l_client_code, getProcessLevel());

                utl.generateSpecialLog("15GH-PE-0002 (1st Screen Summary Query)--164--", l_query);

                DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();
                ArrayList<ViewTranLoadErrorSummaryBean15GH> viewloaderrorsummery = objList.getGenericList(new ViewTranLoadErrorSummaryBean15GH(), l_query);

                if (viewloaderrorsummery != null) {
                    setViewTranLoadErrorsSummery(viewloaderrorsummery);
                    setCkeckRecordFlag("true");
                    utl.generateLog(null, "query data set");
                } else {
                    setCkeckRecordFlag("false");
                }

                l_return_value = "ajax_result";
            } else {
                l_return_value = "success";
            }

        } else {
            l_return_value = "input";
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

    public ArrayList<ViewTranLoadErrorSummaryBean15GH> getViewTranLoadErrorsSummery() {
        return viewTranLoadErrorsSummery;
    }

    public void setViewTranLoadErrorsSummery(ArrayList<ViewTranLoadErrorSummaryBean15GH> viewTranLoadErrorsSummery) {
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

    public int getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(int loginLevel) {
        this.loginLevel = loginLevel;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public String getRefreshFlag() {
        return refreshFlag;
    }

    public void setRefreshFlag(String refreshFlag) {
        this.refreshFlag = refreshFlag;
    }

    public String getSelectedUserLevel() {
        return selectedUserLevel;
    }

    public void setSelectedUserLevel(String selectedUserLevel) {
        this.selectedUserLevel = selectedUserLevel;
    }

}
