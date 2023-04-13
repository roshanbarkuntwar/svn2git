/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Kapil Gupta
 */
public class misUploadFileAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String l_return_value = SUCCESS;
        String l_return_msg = "";

        try {
            session.put("ACTIVE_TAB", "reportAction");
            if (!utl.isnull(getModule())) {
                session.put("MODULE", getModule());
            }

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user = (UserMast) session.get("LOGINUSER");
            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";

            if (!utl.isnull(getAction()) && getAction().equals("upload")) {
                utl.generateLog("--upload file start--", "");
                String templateCode = getRep_upload_template_code();
                String filePath = null;
                String fileName = getMisUploadFileFileName();
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                String newFileName = fileoptUtl.setTaxcpcUploadFilename(client, asmt, templateCode) + "." + extension;
                String oraclePath = oracleDriveName + File.separator + "FVU_TEXT_IMPORT";
                String javaPath = javaDriveName + "/MIS_FILE_UPLOAD/";
                try {
                    // make file for java drive
                    if (!utl.isnull(getRep_upload_dir()) && getRep_upload_dir().equalsIgnoreCase("j")) {
                        File javaPathFile = new File(javaPath);
                        if (!javaPathFile.exists()) {
                            javaPathFile.mkdir();
                        }
                        File destFile = new File(javaPathFile, newFileName);
                        FileUtils.copyFile(getMisUploadFile(), destFile);
                    } else {
                        //make for file for oracle drive
                        File oraclePathFile = new File(oraclePath);
                        if (!oraclePathFile.exists()) {
                            oraclePathFile.mkdir();
                        }
                        File copyforOracle = new File(oraclePathFile, newFileName);
                        FileUtils.copyFile(getMisUploadFile(), copyforOracle);
                    }
                    utl.generateLog("copied file in physical drive--", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                GetTokenTransactions tokenObj = new GetTokenTransactions();
                String tokenNo = tokenObj.generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "", "", "Process Initiated by user !",
                        "MIS_REPORT_DOWNLOAD", "PROCESS_INSERT", getReport_header_text(), "");
                try {
                    l_return_msg = tokenNo + "#" + filename;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                l_return_value = "ajaxResult";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
        return l_return_value;
    }

    public misUploadFileAction() {
        utl = new Util();
        fileoptUtl = new FileOptUtil();
    }

    private final Util utl;
    private FileOptUtil fileoptUtl;
    private Map<String, Object> session;
    private String filename;
    private String module;
    private String action;
    private String seqId;
    private String report_header_text;
    private String rep_upload_template_code;
    private String reportGroup;
    private File misUploadFile;
    private String misUploadFileContentType;
    private String misUploadFileFileName;
    private String rep_upload_dir;
    private String callproc;
    private InputStream inputStream;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRep_upload_dir() {
        return rep_upload_dir;
    }

    public void setRep_upload_dir(String rep_upload_dir) {
        this.rep_upload_dir = rep_upload_dir;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getCallproc() {
        return callproc;
    }

    public void setCallproc(String callproc) {
        this.callproc = callproc;
    }

    public File getMisUploadFile() {
        return misUploadFile;
    }

    public void setMisUploadFile(File misUploadFile) {
        this.misUploadFile = misUploadFile;
    }

    public String getMisUploadFileContentType() {
        return misUploadFileContentType;
    }

    public void setMisUploadFileContentType(String misUploadFileContentType) {
        this.misUploadFileContentType = misUploadFileContentType;
    }

    public String getMisUploadFileFileName() {
        return misUploadFileFileName;
    }

    public void setMisUploadFileFileName(String misUploadFileFileName) {
        this.misUploadFileFileName = misUploadFileFileName;
    }

    public String getRep_upload_template_code() {
        return rep_upload_template_code;
    }

    public void setRep_upload_template_code(String rep_upload_template_code) {
        this.rep_upload_template_code = rep_upload_template_code;
    }

    public String getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }

    public String getReport_header_text() {
        return report_header_text;
    }

    public void setReport_header_text(String report_header_text) {
        this.report_header_text = report_header_text;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
