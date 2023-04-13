/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.HibernateDBCredentials;
import com.opensymphony.xwork2.ActionSupport;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.DateTimeUtil;
import globalUtilities.FileOptUtil;
import globalUtilities.ReadHibernateCfgXML;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class Form15GHOtherAjaxAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    private final Util utl;
    private final DateTimeUtil dateUtl;
    private String action;
    private InputStream inputStream;
    private String processType;
    private String tokenNo;
    private String loginLevel;

    public Form15GHOtherAjaxAction() {
        this.dateUtl = new DateTimeUtil();
        this.utl = new Util();
    }//end constructor

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnValue = "success";
        String returnMsg = "error";
        try {
            if (!utl.isnull(getAction())) {

                Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
                UserMast user = (UserMast) session.get("LOGINUSER");
                String oracleDrive = (String) session.get("ORACLEDRIVE");
                String entity_code = client.getEntity_code();
                String client_code = client.getClient_code();
                long client_level = Long.valueOf(client.getCode_level());

                String acc_year = asmt.getAccYear();
                String asmt_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
                int quarter_no = Integer.valueOf(asmt.getQuarterNo());
                Date from_date = asmt.getQuarterFromDate();
                Date to_date = asmt.getQuarterToDate();
                String tds_type_code = asmt.getTdsTypeCode();

                String user_code = user.getUser_code();
                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

                if (getAction().equalsIgnoreCase("readStatus")) {
                    try {

                        returnMsg = "Some Error Occurred ! Could Not fetch information  ";
//                        String arr[] = asmt.getAccYear().split("-");
//                        String newAccYear = arr[0] + "_" + arr[1];
                        if (!utl.isnull(getProcessType())) {
                            String filePath = oracleDrive + File.separator + "TEXT_FILES" + File.separator;
//                            String processType = "";
//                            if (getProcessType().equalsIgnoreCase("TA") || getProcessType().equalsIgnoreCase("TB") || getProcessType().equalsIgnoreCase("TC")) {
//                                processType = "template_insert";
//                            } else if (getProcessType().equalsIgnoreCase("TD") || getProcessType().equalsIgnoreCase("TE") || getProcessType().equalsIgnoreCase("TF")) {
//                                processType = "tran_load";
//                            }

//                            String fileName = client.getEntity_code() + "_" + client.getClient_code() + "_" + newAccYear + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode() + "_" + processType + "_" + getTokenNo() + ".log";
                            String fileName = utl.getLogFileName(asmt, client.getClient_code(), client.getEntity_code(), "15G", getTokenNo(), "");

                            File f = new File(filePath + fileName);

                            if (f.exists()) {

                                Long millsec = f.lastModified();
                                Date lastModified = new Date(millsec);
                                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(lastModified);
                                String fileSize = Math.ceil(f.length() / 1024) + " KB";

                                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                                    StringBuilder sb = new StringBuilder();
                                    String line = br.readLine();

                                    while (line != null) {
                                        sb.append(line);
                                        sb.append(System.lineSeparator());
                                        line = br.readLine();
                                    }
                                    returnMsg = date + "#" + fileSize + "#" + sb.toString();
                                }
                            } else {
                                returnMsg = "File Not Exists :" + fileName;
                            }
                        }

                    } catch (Exception e) {
                        returnMsg = "Some Error Occurred ! Could Not fetch information : " + e.getMessage();
                        e.printStackTrace();

                    }

                } else if (getAction().equalsIgnoreCase("generateXML")) {
                    if (!utl.isnull(getTokenNo())) {
//                        String batchFilePath = javaDriveName + File.separator + "15G_H_XML" + File.separator + client.getClient_code() + File.separator + asmt.getAccYear() + File.separator + "Q" + asmt.getQuarterNo() + File.separator + asmt.getTdsTypeCode() + File.separator;
                        String current_timestamp = dateUtl.get_sysdate("dd_MM_yyyy_hh_mm_ss_SSS");

                        String batchFilePath = javaDriveName + File.separator + "15GH_XML_EXTERNAL" + File.separator + client.getClient_code()
                                + File.separator + asmt.getAccYear() + File.separator
                                + "Q" + asmt.getQuarterNo() + File.separator + asmt.getTdsTypeCode()
                                + File.separator + l_client_loginid_seq + File.separator
                                + current_timestamp;

                        FileOptUtil optUtil = new FileOptUtil();
                        String fileName = optUtil.getTaxcpcDownloadFilename(client, asmt) + ".xml";
//                        String srcPath = batchFilePath;
                        String destPath = batchFilePath + optUtil.getTaxcpcDownloadFilename(client, asmt) + ".zip";

                        HibernateDBCredentials login_params = new ReadHibernateCfgXML().readXml();
//                        System.out.println("login_params--" + login_params.toString());
                        Form15GHXMLGenerationThread process = new Form15GHXMLGenerationThread(batchFilePath, client, asmt, getTokenNo(), destPath, fileName,
                                getLoginLevel(), javaDriveName, login_params, l_client_loginid_seq);
                        Thread t = new Thread(process);
                        t.start();
                        returnMsg = "success";
                    } else {
                        returnMsg = "error";
                    }
                } else if (getAction().equalsIgnoreCase("generateCSV")) {
                    if (!utl.isnull(getTokenNo())) {

                        String processSeqno = new GetTokenTransactions().generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "",
                                "GD", "", "BULK_15GH_CSV_GEN", "PROCESS_INSERT", "", "");
                        utl.generateLog("token number is", processSeqno);
                        returnMsg = "success" + "#" + processSeqno;
                    } else {
                        returnMsg = "error";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputStream = new ByteArrayInputStream((returnMsg).getBytes("UTF-8"));
        return returnValue;

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

//    public String getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getImportSeqNo() {
//        return importSeqNo;
//    }
//
//    public void setImportSeqNo(String importSeqNo) {
//        this.importSeqNo = importSeqNo;
//    }
//
    public String getTokenNo() {
        return tokenNo;
    }
//

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }
//
//    public String getTemplateCode() {
//        return templateCode;
//    }
//
//    public void setTemplateCode(String templateCode) {
//        this.templateCode = templateCode;
//    }
//

    public String getProcessType() {
        return processType;
    }
//

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end  
}
