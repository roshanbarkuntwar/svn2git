/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.LhssysProcessLogDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.HttpDownloadUtility;
import globalUtilities.OltasCSIUtil;
import globalUtilities.SFTPProcessFile;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.LhssysParameters;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class GenerateFVUBtnAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException, IOException {
        String returnView = "success";
        String returnMsg = "success";
        try {
            String fvuResult = (String) session.get("fvuSessionResult");
            if (!utl.isnull(fvuResult)) {
                setFvuSessionResult(fvuResult);
                session.remove("fvuSessionResult");
            }

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            LhssysParametersDAO lhssysParametersDAO;
            ViewClientMast viewClientMast = null;
            try {
                viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (viewClientMast != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {
//                    FA AND TIMESTAMP
//                    LhssysProcessLog lhssys_log = factory.getLhssysProcessLogDAO().readById(getTokenNo(), true);
                     LhssysProcessLog lhssys_log = factory.getLhssysProcessLogDAO().readProcessbySeqNo(getTokenNo());
                    if (lhssys_log != null) {
                        lhssys_log.setProcess_status("FA");
                        lhssys_log.setProcess_start_timestamp(Date.from(Instant.now()));
                        LhssysProcessLogDAO fileTranDAO1 = factory.getLhssysProcessLogDAO();

                        boolean updateData = fileTranDAO1.update(lhssys_log);
                        if (updateData) {
                        }
                    }

                    GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                    String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                    String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
                    File f = new File(javaDriveName);
//                    File f1 = new File(oracleDriveName);
//                    if (!utl.isnull(javaDriveName) && !utl.isnull(oracleDriveName) && f.exists() && f1.exists()) {
                    if (!utl.isnull(javaDriveName) && f.exists()) {
                        // create path of all required directories
                        String clientDir = javaDriveName + File.separator + "FVU_RELATED_FILES" + File.separator + viewClientMast.getClient_code();
                        String accYearDir = clientDir + File.separator + asmt.getAccYear();
                        String qtrDir = accYearDir + File.separator + "Q" + asmt.getQuarterNo();
                        String tdsTypeCodeDir = qtrDir + File.separator + asmt.getTdsTypeCode();
                        String generateFileLoc = tdsTypeCodeDir;
                        //  setDwnldFileLoc(generateFileLoc);
                        String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                                + File.separator + viewClientMast.getClient_code()
                                + File.separator + asmt.getAccYear()
                                + File.separator + asmt.getTdsTypeCode();
                        String zipFileName = viewClientMast.getTanno() + "_" + asmt.getAccYear() + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode() + ".zip";
                        String zipFileLoc = generateFileLoc + File.separator + zipFileName;
                        File fileLoc = null;
                        File csiFileLoc = null;
                        fVUFileSupport.createDirectory(tdsTypeCodeDir);
                        fVUFileSupport.createDirectory(generateCsiFileLoc);
                        String textFileName = "";

                        fileLoc = new File(generateFileLoc);
                        csiFileLoc = new File(generateCsiFileLoc);
                       // CHECK IF FILE IS PRESENT AT CSI LOCATION
                        //get csi file name
                        boolean isCsiFilePresent = false;

                        String csiName = fVUFileSupport.getCsiFileName(viewClientMast.getTanno());
                        csiName = !utl.isnull(csiName) ? csiName : "";
                        File[] listFiles = csiFileLoc.listFiles();
                        if (listFiles != null && listFiles.length > 0) {
                            for (File listFile : listFiles) {
                                String oldCsiName = listFile.getName();
                                utl.generateLog("oldCsiName--", oldCsiName);
                                if (oldCsiName.equalsIgnoreCase(csiName)) {
                                    isCsiFilePresent = true;
                                    utl.generateLog(null, "CSI FOUND IN FOLDER-----");
                                    break;
                                }
                            }
                        }

                        if (!isCsiFilePresent) {

                            boolean isCsiFileDownloaded = false;

                            try {
                                OltasCSIUtil oltasCsiUtil = new OltasCSIUtil();

                                // delete all files in csi file location
                                fVUFileSupport.deleteFilesInDirectory(generateCsiFileLoc);
                                //end
                                String downloadFileLocation = oltasCsiUtil.getCsiDownloadUrl(viewClientMast.getTanno(), viewClientMast.getClient_code(),
                                        asmt.getAccYear(), asmt.getTdsTypeCode(), asmt.getQuarterNo());

                                utl.generateLog("Regular FVU Url----" + viewClientMast.getClient_code(), downloadFileLocation);
                                utl.generateLog("Generate CSI Location----" + viewClientMast.getClient_code(), generateCsiFileLoc);

                                String downloadFile = HttpDownloadUtility.downloadFile(downloadFileLocation, generateCsiFileLoc);
                                if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                                    isCsiFileDownloaded = true;
                                    isCsiFilePresent = true;
                                }
                                if (!isCsiFileDownloaded) {
                                    lhssysParametersDAO = factory.getLhssysParametersDAO();
                                    LhssysParameters csiFileTime = lhssysParametersDAO.readDataAsParameterAndEntity("CSI_DWNLD_WAIT_TIME", viewClientMast.getEntity_code());
                                    long csiDwndwait = 3000; // csiDwndwait is used to wait thread for few minutes to get proper response . if parameter is empty than default 3 sec is used
                                    if (csiFileTime != null) {
                                        try {
                                            csiDwndwait = Long.parseLong(csiFileTime.getParameter_value());
                                        } catch (Exception e) {
                                            csiDwndwait = 3000;
                                        }
                                    }
                                    downloadFile = HttpDownloadUtility.downloadFile(downloadFileLocation, generateCsiFileLoc, csiDwndwait);
                                    if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                                        isCsiFileDownloaded = true;
                                        isCsiFilePresent = true;
                                        utl.generateLog(null, "CSI FILE DOWNLOADED-----");
                                    }
                                    if (!isCsiFileDownloaded) {
                                        // set flag to view upload div
                                        setAllowCsiUpload("T");
                                        setProcessBtn("disabled");
                                        utl.generateLog(null, "CSI NOT FOUND PLEASE UPLOAD-----");
                                    }
                                }
                            } catch (Exception e) {
                                returnMsg = "Some Error Occured, Could Not Generate FVU File";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                setFvuSessionResult(returnMsg);
                            }
                        }
                        if (validateFlag) {
                            if (getTokenNo() != null) {
                                try {
                                    fVUFileSupport.deleteFilesInDirectory(generateFileLoc);
                                } catch (Exception e) {
                                }
                                String queryToFileFileName = "select t.fvu_txt_file_name from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                                textFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFileFileName);
                                String textFileLocation = "";

                                setProcessBtn("disabled");
                                if (!utl.isnull(textFileName)) {
                                    lhssysParametersDAO = factory.getLhssysParametersDAO();
                                    LhssysParameters lhssysParameters = lhssysParametersDAO.readParametersBy("SFTP_FLAG");

                                    String ftp_flag = "";
                                    if (lhssysParameters != null) {
                                        ftp_flag = lhssysParameters.getParameter_value();
                                    }
//
                                    if (!utl.isnull(ftp_flag) && ftp_flag.equalsIgnoreCase("T")) {
                                        try {
                                            lhssysParametersDAO = factory.getLhssysParametersDAO();
                                            List<LhssysParameters> parameters_list = lhssysParametersDAO.readServerParameters("SFTP");
                                            int port = 0;
                                            String serverip = "";
                                            String username = "";
                                            String password = "";
//
                                            if (parameters_list != null && !parameters_list.isEmpty()) {
                                                for (LhssysParameters param : parameters_list) {
                                                    try {
                                                        if (!utl.isnull(param.getParameter_name()) && !utl.isnull(param.getParameter_value())) {
                                                            if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_IP")) {
                                                                serverip = param.getParameter_value();
                                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_PORT")) {
                                                                port = Integer.parseInt(param.getParameter_value());
                                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_USERNAME")) {
                                                                username = param.getParameter_value();
                                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_PASSWORD")) {
                                                                password = param.getParameter_value();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }
                                            if (!utl.isnull(serverip) && !utl.isnull(username) && !utl.isnull(password) && !utl.isnull(generateFileLoc)) {
//                                                /**
//                                                 * Download file from SFTP
//                                                 * server default location...
//                                                 */
                                                String destination_location = generateFileLoc + File.separator + textFileName;
                                                new SFTPProcessFile().downloadFileFromSFTP(serverip, port, username, password, textFileName, destination_location);

                                                /**
                                                 * Download file from FTP server
                                                 * default location...
                                                 */
////                                                FTPProcessFile.downloadFile(serverip, port, username, password, textFileName, destination_location);
                                            } else {
                                                utl.generateLog("Insuficient parameters to download file from SFTP server...", "");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        if (!utl.isnull(oracleDriveName)) {
                                            File fO = new File(oracleDriveName);
                                            if (fO.exists()) {
                                                // Code for copy files directory to directory direct....(4 lines)
                                                String storageLoction = oracleDriveName + File.separator + "TEXT_FILES";
                                                textFileLocation = storageLoction + File.separator + textFileName;
                                                File f_textFile = new File(textFileLocation);
                                                FileUtils.copyFileToDirectory(f_textFile, fileLoc);
                                            } else {
                                                returnView = "drive_error";
                                                setErrorMessage("Oracle Drive not configured ! Contact to support person !");
                                            }
                                        } else {
                                            returnView = "drive_error";
                                            setErrorMessage("Oracle Drive not configured ! Contact to support person !");
                                        }
                                    }
                                    textFileLocation = generateFileLoc + File.separator + textFileName;

                                    boolean isTextFilePresent = false;
                                    if (!utl.isnull(textFileLocation)) {
                                        File[] listFiles1 = fileLoc.listFiles();
                                        for (File file : listFiles1) {
                                            if (file.getName().endsWith(".txt")) {
                                                if (textFileName.equals(file.getName())) {
                                                    isTextFilePresent = true;
                                                }
                                            }
                                        }
                                    }
                                    if (isTextFilePresent) {
                                        if (isCsiFilePresent) {

                                            String csiFileLocation = generateCsiFileLoc + File.separator + csiName;

                                            ReadFromConsolidatedTDSFile dSFile = new ReadFromConsolidatedTDSFile();
                                            String clientNameFromCsiFile = dSFile.getClientNameFromCsiFile(csiFileLocation);
                                            ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
                                            ViewClientMast client2 = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                                            String client_name = client2.getClient_name();
                                            if (client_name.equalsIgnoreCase(clientNameFromCsiFile)) {
                                                // COPY RTF FILE--------------
                                                String fvuVersion = "";
                                                try {
                                                    lhssysParametersDAO = factory.getLhssysParametersDAO();
                                                    LhssysParameters readFvuFileVersion = lhssysParametersDAO.readDataAsParameterAndEntity("FVU_FILE_VERSION", viewClientMast.getEntity_code());
                                                    if (readFvuFileVersion != null) {
                                                        fvuVersion = readFvuFileVersion.getParameter_value();
                                                    }
                                                } catch (Exception e) {
                                                }
                                                String fvuFileDirectory = javaDriveName + File.separator + "TAXCPC" + File.separator + "FVU_" + fvuVersion;//get fvu directory path
                                              //  System.out.println("fvuFileDirectory->"+fvuFileDirectory);
                                                boolean rtfFileGenerated = fVUFileSupport.generateRtfFile(fvuFileDirectory, generateFileLoc);
                                                if (rtfFileGenerated) {
                                                    String rtfFileLocation = generateFileLoc + File.separator + "Q126QC.rtf";
                                                    utl.generateLog("rtfFileLocation--", rtfFileLocation);

                                                    //------------generate error file
                                                    String errorFileLocation = generateFileLoc + File.separator + textFileName.split("\\.")[0] + ".err";
                                                    File errorFile = new File(errorFileLocation);
                                                    if (!errorFile.exists()) {
                                                        errorFile.createNewFile();
                                                    }

                                                    //generate all file loc
                                                    File newGeneratedTextFile = new File(textFileLocation);// text file
                                                    File newGeneratedCsiFile = new File(csiFileLocation);// csi file
                                                    File newGeneratedRtfFile = new File(rtfFileLocation);// rtf file
//                                                    File[] listFiles3 = fileLoc.listFiles();
//                                                    Boolean zipFiles = false;
                                                    String tempTextFile = textFileName;

                                                    String storageFileLocation = fileLoc.getAbsolutePath();
                                                    if (newGeneratedCsiFile.exists() && newGeneratedTextFile.exists() && newGeneratedRtfFile.exists()) {
                                                        boolean generateBatchFile = fVUFileSupport.generateBatchFile(fvuFileDirectory, generateFileLoc, rtfFileLocation, newGeneratedTextFile, newGeneratedCsiFile, newGeneratedRtfFile, errorFile, fvuVersion, getTokenNo().toString());
                                                        if (generateBatchFile) {
//                                                            Thread task = new Thread() {
//                                                                @Override
//                                                                public void run() {
                                                            String fvuFile = "";
                                                            String storageFile = "";

                                                            //to be converted in thread....
                                                            setFileTypeFound("G");// set any dummy value so that variable is not null

                                                            for (File file : fileLoc.listFiles()) {
                                                                // fvu file
                                                                if (file.getName().endsWith("fvu")) {
                                                                    fvuFile = file.getName();
                                                                    try {
                                                                        // create zip file
                                                                        utl.generateLog("fvuFile---", fvuFile);
                                                                        utl.generateLog("fvuFile---", generateFileLoc + File.separator + fvuFile);

                                                                        new ZipFileUtil().ZipIt(fvuFile, generateFileLoc + File.separator + fvuFile, generateFileLoc + File.separator + fvuFile + ".zip");
                                                                        storageFile += fvuFile + ".zip" + "#";
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    try {
                                                                        new ZipFileUtil().ZipIt(fvuFile + ".log", generateFileLoc + File.separator + fvuFile + ".log", generateFileLoc + File.separator + fvuFile.replace(".fvu", "") + ".zip");
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                            }

                                                            boolean validateMsgFlag = false;
                                                            for (File file : fileLoc.listFiles()) {

                                                                if (file.getName().endsWith(".pdf")) {
                                                                    setFileTypeFound("P");// pdf
//                                                                setGeneratedFileLink(generateFileLoc);
//                                                                setGeneratedFileName(file.getName());
                                                                    validateMsgFlag = true;
                                                                }

                                                                if (!file.getName().endsWith("png") && !file.getName().endsWith("bat") && !file.getName().endsWith(".rtf")) {
                                                                    storageFile += file.getName() + "#";
//                                                                            storageFileLocation = fileLoc.getAbsolutePath();
                                                                }
                                                            }

                                                            if (validateMsgFlag) {
                                                                // zip fvu files
                                                                if (fVUFileSupport.zipFiles(generateFileLoc, zipFileLoc, tempTextFile, "FVU")) {
                                                                    storageFile += zipFileName + "#";
                                                                }
//                                                            addActionError("FVU File Generated Successfully");
                                                                // update file name & location if fvu generated : START
                                                                LhssysProcessLogDAO fileTranDAO2 = factory.getLhssysProcessLogDAO();
                                                                LhssysProcessLog log = fileTranDAO2.readProcessbySeqNo(getTokenNo());
                                                                if (log != null) {
                                                                    log.setFvu_files_path(storageFileLocation);
                                                                    log.setFvu_files_name_str(storageFile);
                                                                    log.setProcess_status("FC");
                                                                    log.setProcess_end_timestamp(Date.from(Instant.now()));
                                                                    LhssysProcessLogDAO fileTranDAO1 = factory.getLhssysProcessLogDAO();

                                                                    boolean updateData = fileTranDAO1.update(log);
                                                                    if (updateData) {
                                                                    } else {
                                                                    }
                                                                }

                                                                setFvuSessionResult("FVU File Generated Successfully");
                                                                session.put("ERRORCLASS", MessageType.successMessage);
                                                            } else {
//                                                        addActionError("Some Error Occured, Could Not Generate File");
                                                            }
                                                            if (!utl.isnull(getFileTypeFound()) && !getFileTypeFound().equalsIgnoreCase("P")) {
                                                                // zip error files
                                                                if (fVUFileSupport.zipFiles(generateFileLoc, zipFileLoc, tempTextFile, "error")) {
                                                                    storageFile += zipFileName + "#";
                                                                }
                                                                // update file name & location if fvu generated : START
                                                                LhssysProcessLogDAO fileTranDAO2 = factory.getLhssysProcessLogDAO();
                                                                LhssysProcessLog log = fileTranDAO2.readProcessbySeqNo(getTokenNo());
                                                                if (log != null) {
                                                                    log.setFvu_files_path(storageFileLocation);
                                                                    log.setFvu_files_name_str(storageFile);
                                                                    log.setProcess_status("FD");
                                                                    log.setProcess_end_timestamp(Date.from(Instant.now()));
//                                                                            log.setProcess_end_timestamp(process_end_timestamp);
                                                                    LhssysProcessLogDAO fileTranDAO1 = factory.getLhssysProcessLogDAO();

                                                                    boolean updateData = fileTranDAO1.update(log);
                                                                    if (updateData) {
                                                                    } else {
                                                                    }
                                                                }
                                                                addActionError("FVU-Error File Generated");
                                                                setFvuSessionResult("Error File Generated");
                                                                session.put("ERRORCLASS", MessageType.successMessage);
                                                            }

//                                                                }
//                                                            };
//                                                            task.start();
//                                                            setFvuSessionResult("Process Initiated Please Visit After Some Time !");
//                                                            session.put("ERRORCLASS", MessageType.successMessage);
//                                                            returnView = "processStart";
                                                            returnView = "fvuGenSuccess";//When FVU generation completed

                                                        } else {
                                                            returnMsg = "Some Error Occured, Could Not Generate Batch File";
                                                            session.put("ERRORCLASS", MessageType.errorMessage);
                                                            setFvuSessionResult(returnMsg);
                                                        }
                                                    } else {
                                                        returnMsg = "Some Error Occured, Could Not Generate FVU File";
                                                        session.put("ERRORCLASS", MessageType.errorMessage);
                                                        setFvuSessionResult(returnMsg);
                                                    }

                                                } else {
                                                    returnMsg = "Some Error Occured, Could Not Generate RTF File";
                                                    session.put("ERRORCLASS", MessageType.errorMessage);
                                                    setFvuSessionResult(returnMsg);
                                                }

                                            } else {
                                                returnMsg = "Client Name is Different From ITD, Could Not Generate FVU File";
                                                session.put("ERRORCLASS", MessageType.errorMessage);
                                                setFvuSessionResult(returnMsg);
                                            }

                                        } else {
                                            setAllowCsiUpload("T");
                                            returnMsg = "CSI File Not Present! Please Upload !";
                                            session.put("ERRORCLASS", MessageType.errorMessage);
                                            setFvuSessionResult(returnMsg);
                                        }

                                    } else {

                                        returnMsg = "Text File Not Present!";
                                        session.put("ERRORCLASS", MessageType.errorMessage);
                                        setFvuSessionResult(returnMsg);
                                    }

                                } else {
                                    returnMsg = "Text File Not Found!";
                                    session.put("ERRORCLASS", MessageType.errorMessage);
                                    setFvuSessionResult(returnMsg);
                                }
                            } else {
                                returnMsg = "Token No. Not Found!";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                setFvuSessionResult(returnMsg);
                            }
                        } else if (!validateFlag) {
                            String queryToFileFileName = "select t.fvu_txt_file_name from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                            textFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFileFileName);
                            setTextFileName(textFileName);

                            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("fvuTextDownload")) {
                                String textFileLocation = "";
                                if (!utl.isnull(oracleDriveName)) {
                                    if (!utl.isnull(textFileName)) {
                                        try {
                                            String storageLoction = oracleDriveName + File.separator + "TEXT_FILES";
                                            textFileLocation = storageLoction + File.separator + textFileName;
                                            returnMsg = textFileName + "##" + textFileLocation;
                                            returnView = "file_success";

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    returnView = "drive_error";
                                    setErrorMessage("Drive not configured ! Contact to support person !");
                                }
                            }
                        }
                    } else {
                        returnView = "drive_error";
                        setErrorMessage("Drive not configured ! Contact to support person !");
                    }
                } else {
                    returnView = "drive_error";
                    setErrorMessage("Session Time Out!");
                }
            } else {
                returnMsg = "error#Session Time Out ! Please login again !";
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnView;

    }
    Map<String, Object> session;
    private InputStream inputStream;
    Util utl;
    private String errorMessage;
    private String allowCsiUpload;
    private boolean validateFlag;
    private String fileTypeFound;
    private String fvuSessionResult;
    private Long tokenNo;
    private String processBtn;
    private String action;
    private String textFileName;
    
    public String getTextFileName() {
        return textFileName;
    }

    public void setTextFileName(String textFileName) {
        this.textFileName = textFileName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getFileTypeFound() {
        return fileTypeFound;
    }

    public void setFileTypeFound(String fileTypeFound) {
        this.fileTypeFound = fileTypeFound;
    }

    public boolean getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean validateFlag) {
        this.validateFlag = validateFlag;
    }

    public String getAllowCsiUpload() {
        return allowCsiUpload;
    }

    public void setAllowCsiUpload(String allowCsiUpload) {
        this.allowCsiUpload = allowCsiUpload;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public GenerateFVUBtnAction() {
        utl = new Util();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFvuSessionResult() {
        return fvuSessionResult;
    }

    public void setFvuSessionResult(String fvuSessionResult) {
        this.fvuSessionResult = fvuSessionResult;
    }

    public String getProcessBtn() {
        return processBtn;
    }

    public void setProcessBtn(String processBtn) {
        this.processBtn = processBtn;
    }

}
