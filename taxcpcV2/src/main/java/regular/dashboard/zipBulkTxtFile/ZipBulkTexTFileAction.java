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
 * @author kapil.gupta
 */
public class ZipBulkTexTFileAction extends ActionSupport implements SessionAware {

    public ZipBulkTexTFileAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnView = "success";
        session.put("ACTIVE_TAB", "dashboard");
        StringBuilder sb = new StringBuilder();
        try {
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment assesment = (Assessment) session.get("ASSESSMENT");
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
            UserMast user = (UserMast) session.get("LOGINUSER");
            utl.generateLog("buttonEnableFlag-->", oracleDriveName);
        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
       
            l_client_loginid_seq = (Long) sessionId;
       
             if (viewClientMast != null && assesment != null) {
            String buttonEnableFlag = (String) session.get("ENABLEBUTTONVFG") != null ? (String) session.get("ENABLEBUTTONVFG") : "";
            utl.generateLog("buttonEnableFlag-->", buttonEnableFlag);
            GetTokenTransactions gtt = new GetTokenTransactions();
            tokenUploadObj = gtt.getTokenSatus(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), assesment, user.getUser_code(), "PROCESS_ERROR", l_client_loginid_seq);
             }
            if (!utl.isnull(tokenNo)) {
                String queryOfLogFileName = "select t.log_file_name2 from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                String logFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryOfLogFileName);
                String filePath = oracleDriveName + File.separator + "TEXT_FILES" + File.separator + logFileName;
                File checkFilePathDir = new File(filePath);
                File checkOracleDir = new File(oracleDriveName);
                if (!utl.isnull(oracleDriveName) && checkOracleDir.exists()) {
                    File checkJavaDir = new File(javaDriveName);
                    if (!utl.isnull(logFileName) && checkFilePathDir.exists()) {
                        if (!utl.isnull(javaDriveName) && checkJavaDir.exists()) {
                            File oraclePath = null;

                            String clientDir = javaDriveName + File.separator + "BULK_TEXT_ZIP" + File.separator + viewClientMast.getClient_code();
                            String accYearDir = clientDir + File.separator + assesment.getAccYear();
                            String qtrDir = accYearDir + File.separator + "Q" + assesment.getQuarterNo();
                            String tdsTypeCodeDir = qtrDir + File.separator + assesment.getTdsTypeCode();
                            String generateFileLoc = tdsTypeCodeDir;
                            File javaLocationPath = new File(generateFileLoc);
                            if (!javaLocationPath.exists()) {
                                javaLocationPath.mkdirs();
                            }
                            try {
                                FileUtils.cleanDirectory(javaLocationPath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Path path = Paths.get(filePath);
                            try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {

                                List<String> collectFiles = stream.collect(Collectors.toList());
                                for (String textFile : collectFiles) {
                                    String fileLoction = oracleDriveName + File.separator + "TEXT_FILES" + File.separator + textFile;
                                    oraclePath = new File(fileLoction);
                                    if (oraclePath.exists()) {
                                        FileUtils.copyFileToDirectory(oraclePath, javaLocationPath);
                                    }
                                }
                                File listOfStoredFiles[] = javaLocationPath.listFiles();
                                if (listOfStoredFiles != null && listOfStoredFiles.length > 0) {

                                    String zipFileName = viewClientMast.getTanno() + "_" + assesment.getAccYear() + "_" + assesment.getQuarterNo() + "_" + assesment.getTdsTypeCode() + ".zip";
                                    String zipFileLoc = generateFileLoc + File.separator + zipFileName;
                                    FileOutputStream fos = new FileOutputStream(zipFileLoc);
                                    ZipOutputStream zos = new ZipOutputStream(fos);

                                    for (File file : listOfStoredFiles) {
                                        if (file.getName().endsWith(".txt") || file.getName().endsWith(".csv") || file.getName().endsWith(".CSV")) {
                                            addToZipFile(file, zos);
                                            FileUtils.forceDelete(file);
                                        }
                                    }
                                    zos.close();
                                    fos.close();
                                    File fileToDownload = new File(zipFileLoc);
                                    if (fileToDownload != null && fileToDownload.exists()) {
                                        downloadedFileName = fileToDownload.getAbsolutePath();
                                    }
                                    returnView = "ajax_success";
                                    sb.append("success#" + downloadedFileName);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                sb.append("error");
                            }

                        } else {
                            returnView = "ajax_success";
                            sb.append("error#Java Drive not configured ! Contact to support person !");
                        }
                    } else {
                        sb.append("error#File not Found !!!");
                        returnView = "ajax_success";

                    }
                } else {
                    returnView = "ajax_success";
                    sb.append("error#Oracle Drive not configured ! Contact to support person !");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnView;
    }//End Method

    private Map<String, Object> session;
    private Util utl;
    private InputStream inputStream;
    private String tokenNo;
    private String fileNameToDownload;
    private String downloadedFileName;
    private TokenStatusAttribs tokenUploadObj;

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
    
   
    public void addToZipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
