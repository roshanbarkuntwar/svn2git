/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ImportSatusAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        try {

            try {
                String resultValue = (String) session.get("SESSIONRESULT");
                resultValue = utl.isnull(resultValue) ? "" : resultValue;
                if (!utl.isnull(resultValue)) {
                    setSessionResult(resultValue);
                    session.remove("SESSIONRESULT");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.put("ACTIVE_TAB", "tdsImport");
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
            GetTokenTransactions gtt = new GetTokenTransactions();
            tokenUploadObj = gtt.getTokenSatus(client.getEntity_code(), client.getClient_code(), assesment, user.getUser_code(), "TEMPLATE_INSERT", l_client_loginid_seq);
            if (tokenUploadObj != null && !utl.isnull(tokenUploadObj.getProcessStatus())) {
                if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TA")) {
                    setKillBtn(" ");
                    setImportBtn("disabled='true'");
                    setViewBtn("disabled='true'");
                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TB")) {
                    setKillBtn("disabled='true'");
                    setImportBtn(" ");
                    setViewBtn("disabled='true'");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TC")) {
                    setKillBtn("disabled='true'");
                    setImportBtn("disabled='true'");
                    setViewBtn("");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TD")) {
                    setKillBtn("disabled='true'");
                    setImportBtn("disabled='true'");
                    setViewBtn("disabled='true'");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TE")) {
                    setKillBtn(" ");//ENABLE TRUE
                    setImportBtn(" ");
                    setViewBtn("disabled='true'");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("TF")) {
                    setKillBtn("disabled='true'");
                    setImportBtn(" ");
                    setViewBtn("disabled='true'");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("RA")) {
                    setKillBtn("");
                    setImportBtn("disabled='true'");
                    setViewBtn("disabled='true'");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("RC")) {
                    setKillBtn("");
                    setImportBtn("disabled='true'");
                    setViewBtn("");

                } else if (tokenUploadObj.getProcessStatus().equalsIgnoreCase("RB")) {
                    setKillBtn("");
                    setImportBtn("disabled='true'");
                    setViewBtn("disabled='true'");

                } else {
                    setKillBtn(" ");
                    setImportBtn(" ");
                    setViewBtn(" ");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return "success";
    }

    Util utl;

    public ImportSatusAction() {
        utl = new Util();
    }
    private TokenStatusAttribs tokenUploadObj;
    private Map<String, Object> session;
    private String sessionResult;
    private String killBtn;
    private String importBtn;
    private String viewBtn;

    public String getKillBtn() {
        return killBtn;
    }

    public void setKillBtn(String killBtn) {
        this.killBtn = killBtn;
    }

    public String getImportBtn() {
        return importBtn;
    }

    public void setImportBtn(String importBtn) {
        this.importBtn = importBtn;
    }

    public String getViewBtn() {
        return viewBtn;
    }

    public void setViewBtn(String viewBtn) {
        this.viewBtn = viewBtn;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
