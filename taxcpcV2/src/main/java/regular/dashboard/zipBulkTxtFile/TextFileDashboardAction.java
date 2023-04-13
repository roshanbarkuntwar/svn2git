
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.zipBulkTxtFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class TextFileDashboardAction extends ActionSupport implements SessionAware {

    public TextFileDashboardAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnView = "success";
            System.out.println("Svn Configuration......................");
        try {
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment assesment = (Assessment) session.get("ASSESSMENT");
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
            UserMast user = (UserMast) session.get("LOGINUSER");

            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");

            l_client_loginid_seq = (Long) sessionId;

            if (getAction() != null && getAction().equalsIgnoreCase("textFileView")) {
                if (viewClientMast != null && assesment != null) {
                    String buttonEnableFlag = (String) session.get("ENABLEBUTTONVFG") != null ? (String) session.get("ENABLEBUTTONVFG") : "";
                    utl.generateLog("buttonEnableFlag-->", buttonEnableFlag);
                    GetTokenTransactions gtt = new GetTokenTransactions();
                    tokenUploadObj = gtt.getTokenSatus(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), assesment, user.getUser_code(), "BULK_FVU_TEXT_GEN", l_client_loginid_seq);
                    
                    if (!utl.isnull(tokenUploadObj.getProcessType())) {
                        utl.generateLog("get ProcessType", tokenUploadObj.getProcessType());
                        int proc_type = Integer.parseInt(tokenUploadObj.getProcessType());
                        if (proc_type == 0) {
                            tokenUploadObj.setProcessTypeName("Login Level");
                        } else {
                            tokenUploadObj.setProcessTypeName("All (Login & Branch Level)");
                        }

                    }else{
                     utl.generateLog("tokenUploadObj.getProcessType()", "null");   
                    }

                }

                returnView = "successEntry";

            } else if (getAction() != null && getAction().equalsIgnoreCase("textFileDashboard")) {

                returnView = "successDashboard";
            }

        } catch (Exception e) {

        }

        return returnView;
    }//End Method

    private Map<String, Object> session;
    private Util utl;
    private InputStream inputStream;
    private String tokenNo;
    private String fileNameToDownload;
    private String downloadedFileName;
    private TokenStatusAttribs tokenUploadObj;
    private String action;
    private String processLevel;

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public void setFileNameToDownload(String fileNameToDownload) {
        this.fileNameToDownload = fileNameToDownload;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

}
