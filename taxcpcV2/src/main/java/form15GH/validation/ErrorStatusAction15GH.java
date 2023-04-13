/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ErrorStatusAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    private String l_error_process_type;

    private String killBtn;
    private String refreshBtn;
    private String processBtn;
    private String viewBtn;
    private String fvuBtn;
    private String fvuTxtBtn;
    private String loginLevel;
    private String clientLoginLevel;

    private String sessionResult;

    private TokenStatusAttribs tokenUploadObj;

    Util utl;

    public ErrorStatusAction15GH() {
        utl = new Util();
        tokenUploadObj = new TokenStatusAttribs();
    }

    @Override
    public String execute() throws Exception {
        try {

            session.put("ACTIVE_TAB", "15GHValidation");
            String resultValue = (String) session.get("SESSIONRESULT");
            resultValue = utl.isnull(resultValue) ? "" : resultValue;
            if (!utl.isnull(resultValue)) {
                setSessionResult(resultValue);
                session.remove("SESSIONRESULT");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            clientLoginLevel = client.getCode_level();

            String l_client_code = "";
            String l_entity_code = "";
            String l_acc_year = "";
            int l_quarter_no = 0;
            Date l_from_date = null;
            Date l_to_date = null;
            String l_tds_type_code = "";
//            int execute_procedure_result = 1;// in case of error

            l_client_code = client.getClient_code();
            l_entity_code = client.getEntity_code();

            l_acc_year = assesment.getAccYear();
            String quarterNo = assesment.getQuarterNo();
            l_quarter_no = Integer.parseInt(quarterNo);
            l_from_date = assesment.getQuarterFromDate();
            l_to_date = assesment.getQuarterToDate();
            l_tds_type_code = assesment.getTdsTypeCode();

            int l_process_type_value = 0;
            try {
                if (!utl.isnull(getL_error_process_type())) {
                    l_process_type_value = Integer.parseInt(getL_error_process_type());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            GetTokenTransactions gtt = new GetTokenTransactions();
            tokenUploadObj = gtt.getTokenSatus(client.getEntity_code(), client.getClient_code(), assesment, user.getUser_code(), "PROCESS_ERROR_15GH", l_client_loginid_seq);
            utl.generateSpecialLog("15GHPE-0001 (Token Status)----", tokenUploadObj);
        }

        if (tokenUploadObj != null && !utl.isnull(tokenUploadObj.getProcessStatus())) {
            if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("EA")) {
//                setKillBtn(" ");
//                setRefreshBtn(" ");
//                setViewBtn(" disabled='true' ");
//                setProcessBtn(" disabled='true' ");
//                setFvuTxtBtn(" disabled='true' ");
//                setFvuBtn(" disabled='true' ");

            } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("EB")) {
//                setKillBtn(" ");
//                setRefreshBtn(" ");
//                setViewBtn(" disabled='true' ");
//                setProcessBtn(" ");
//                setFvuTxtBtn(" disabled='true' ");

            } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("EC")) {
//                setKillBtn(" ");
//                setRefreshBtn(" ");
//                setViewBtn(" ");
//                setProcessBtn(" disabled='true' ");
//                setFvuTxtBtn(" ");

            } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("EF")) {
//                setKillBtn(" disabled='true' ");
//                setRefreshBtn(" disabled='true' ");
//                setViewBtn(" disabled='true' ");
//                setProcessBtn(" ");
//                setFvuTxtBtn(" disabled='true' ");
//                setFvuBtn(" ");

            } else {
//                setKillBtn(" ");
//                setRefreshBtn(" ");
//                setViewBtn(" disabled='true' ");
//                setProcessBtn(" disabled='true' ");
//                setFvuTxtBtn(" disabled='true' ");
//                setFvuBtn(" disabled='true' ");
            }
        }
        return SUCCESS;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(String loginLevel) {
        this.loginLevel = loginLevel;
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

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public String getClientLoginLevel() {
        return clientLoginLevel;
    }

    public void setClientLoginLevel(String clientLoginLevel) {
        this.clientLoginLevel = clientLoginLevel;
    }

}//end class
