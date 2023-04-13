/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class PanVerificationOfflineAction extends ActionSupport implements SessionAware {

    private final Util utl;
    private Map<String, Object> session;

    private InputStream inputStream;

    private String action;
    private String flag;
    private String downloadedFileName;
    private String sessionResult;
    private String unverifiedPanCount;
    private String errorMessage;

    private long contentLength;

    public PanVerificationOfflineAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String returnView = "success";
        String l_return_message = "";

//        PAN verififcation count
        String l_unverified = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        if (!utl.isnull(getFlag()) && getFlag().equalsIgnoreCase("true")) {
        } else {
            String l_entity_code = !utl.isnull(client.getEntity_code()) ? client.getEntity_code() : "";
            String l_client_code = !utl.isnull(client.getClient_code()) ? client.getClient_code() : "";

            PANVerificationDB pan_db = new PANVerificationDB();
            DbFunctionExecutorAsString dbcon = new DbFunctionExecutorAsString();

            String count_qry = pan_db.getPanVerificationCountQry(l_client_code, l_entity_code);
            utl.generateSpecialLog("pan verification count query", count_qry);

            l_unverified = dbcon.execute_oracle_function_as_string(count_qry);
            setUnverifiedPanCount(l_unverified);
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));
        return returnView;
    }//end method

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getUnverifiedPanCount() {
        return unverifiedPanCount;
    }

    public void setUnverifiedPanCount(String unverifiedPanCount) {
        this.unverifiedPanCount = unverifiedPanCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}//end class
