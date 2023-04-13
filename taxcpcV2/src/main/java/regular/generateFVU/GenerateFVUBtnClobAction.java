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
 * @author akash.meshram
 */
public class GenerateFVUBtnClobAction extends ActionSupport implements SessionAware {

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
            String clob_fvu_gen_flag = (String) session.get("CLOB_FVU_GEN_FLAG") != null ? (String) session.get("CLOB_FVU_GEN_FLAG") : "";
            setCLOB_FVU_GEN_FLAG(clob_fvu_gen_flag);
            String blob_file_download_flag = (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") != null ? (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") : "";
            setBLOB_FILE_DOWNLOAD_FLAG(blob_file_download_flag);
            oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";

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
                //  LhssysProcessLog lhssys_logg = factory.getLhssysProcessLogDAO().readById(getTokenNo(), false);
                  LhssysProcessLog lhssys_logg = factory.getLhssysProcessLogDAO().readProcessbySeqNo(getTokenNo());
                   
                   if (lhssys_logg != null) {
                        lhssys_logg.setProcess_status("FA");
                        lhssys_logg.setProcess_start_timestamp(Date.from(Instant.now()));
                        LhssysProcessLogDAO fileTranDAO1 = factory.getLhssysProcessLogDAO();
                        boolean updateData = fileTranDAO1.update(lhssys_logg);
                        if (updateData) {

                        }
                    }
                    GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                    String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                    File f = new File(javaDriveName);
                    utl.generateLog("Drive Name is->", javaDriveName);
                    if (!utl.isnull(javaDriveName)) {
                        // create path of all required directories
                        String clientDir = javaDriveName + File.separator + "FVU_RELATED_FILES" + File.separator + viewClientMast.getClient_code();

                        utl.generateLog("FVU Genearation Directory", clientDir);

                        String accYearDir = clientDir + File.separator + asmt.getAccYear();
                        String qtrDir = accYearDir + File.separator + "Q" + asmt.getQuarterNo();
                        String finalFVUDirectory = qtrDir + File.separator + asmt.getTdsTypeCode();
                        String generateFileLoc = finalFVUDirectory;
                        //  setDwnldFileLoc(generateFileLoc);
                        String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                                + File.separator + viewClientMast.getClient_code()
                                + File.separator + asmt.getAccYear()
                                + File.separator + asmt.getTdsTypeCode();
                        String zipFileName = viewClientMast.getTanno() + "_" + asmt.getAccYear() + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode() + ".zip";
                        String zipFileLoc = generateFileLoc + File.separator + zipFileName;

                        String client_Dir = javaDriveName + File.separator + "TEXT_FILES" + File.separator + viewClientMast.getClient_code();
                        utl.generateLog("client Dir is--->", clientDir);
                        String accYear_Dir = client_Dir + File.separator + asmt.getAccYear();
                        String qtr_Dir = accYear_Dir + File.separator + "Q" + asmt.getQuarterNo();
                        String finalTextDirectory = qtr_Dir + File.separator + asmt.getTdsTypeCode();
                        File fileLoc = null;
                        File csiFileLoc = null;
                        fVUFileSupport.createDirectory(finalFVUDirectory);
                        fVUFileSupport.createDirectory(finalTextDirectory);
                        fVUFileSupport.createDirectory(generateCsiFileLoc);
                        fileLoc = new File(generateFileLoc);
                        csiFileLoc = new File(generateCsiFileLoc);
                        setCsiFilePath(csiFileLoc.getAbsolutePath());
                        // CHECK IF FILE IS PRESENT AT CSI LOCATION
                        //get csi file name
                        boolean isCsiFilePresent = false;
                        String csiName = fVUFileSupport.getCsiFileName(viewClientMast.getTanno());
                        csiName = !utl.isnull(csiName) ? csiName : "";
                        File[] listFiles = csiFileLoc.listFiles();
                       
                        if (listFiles != null && listFiles.length > 0) {
                            for (File listFile : listFiles) {
                                String oldCsiName = listFile.getName();
                                utl.generateLog("Found already generated CSI File--", oldCsiName);
                                setCsiFileName(oldCsiName);
                                if (oldCsiName.equalsIgnoreCase(csiName)) {
                                    isCsiFilePresent = true;
                                    boolean auto_update_csifile_path_flag = false;
                                    String query = "UPDATE lhssys_process_log SET fvu_csi_filename = '" + generateCsiFileLoc + File.separator + csiName + "' WHERE process_seqno=" + getTokenNo();
                                    auto_update_csifile_path_flag = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(query);
                                    if (auto_update_csifile_path_flag) {
                                        utl.generateLog("CSI auto_update_path_flag updated Success", auto_update_csifile_path_flag);
                                    } else {
                                        utl.generateLog("CSI auto_update_path_flag updated Failed", auto_update_csifile_path_flag);
                                        utl.generateLog("CSI File Name flag set to Null", "");
                                        setCsiFileName(null);
                                        isCsiFilePresent = false;
                                    }
                                    break;
                                }
                            }
                        }else{
                            utl.generateLog("Already generated CSI File length", listFiles.length); 
                        }
                        checkTextCsiPresent();
                        if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getTextFile")) {
                            System.out.println("GET TEXT FILE STARTED");
                            System.out.println("finalTextDirectory----- " + finalTextDirectory);
                            try {
                                if (!utl.isnull(javaDriveName)) {
                                    fVUFileSupport.deleteFilesInDirectory(finalTextDirectory);
                                    System.out.println("DELETED ALL FILES IN TEXT_FILE DIRECTORY");
                                    DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                                    String flag = db.get_text_file(getTokenNo(), finalTextDirectory + File.separator + textFileName, "");
                                    setTextFileName(textFileName);
                                    setTextFilepath(finalFVUDirectory + File.separator + textFileName);
                                    if (!utl.isnull(flag) && flag.equalsIgnoreCase("T")) {
                                        boolean updateflag = false;
                                        System.out.println("update textfilepath---" + finalTextDirectory + File.separator + textFileName);
                                        String query = "UPDATE lhssys_process_log SET fuv_text_filename = '" + finalTextDirectory + File.separator + textFileName + "' WHERE process_seqno=" + getTokenNo();
                                        System.out.println("queryquery->" + query);
                                        updateflag = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(query);
                                        if (updateflag) {
                                            checkTextCsiPresent();
                                            utl.generateLog("Text file path updated in fvu_text_filename coloum -", updateflag);
                                        }
                                        System.out.println("Text File Generated Successfully in Text_File(s) Directory");
                                        setFvuSessionResult("Text File Generated Successfully in Text_File(s) Directory");
                                        session.put("ERRORCLASS", MessageType.successMessage);

                                    }
                                } else {
                                    System.out.println("Drive not configured ! Contact to support person ");
                                    setFvuSessionResult("Drive not configured ! Contact to support person !");
                                    session.put("ERRORCLASS", MessageType.errorMessage);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            returnView = "success";

                        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getCSIfromOltas")) {
                            System.out.println("GET CSI FILE FROM OLTAS STARTED");
                            boolean isCsiFileDownloaded = false;
                            try {
                                fVUFileSupport.deleteFilesInDirectory(generateCsiFileLoc);
                                System.out.println("DELETED ALL FILES IN CSI DIRECTORY");
                                OltasCSIUtil oltasCsiUtil = new OltasCSIUtil();
                                String downloadFileLocation = oltasCsiUtil.getCsiDownloadUrl(viewClientMast.getTanno(), viewClientMast.getClient_code(), asmt.getAccYear(), asmt.getTdsTypeCode(), asmt.getQuarterNo());
                                utl.generateLog("Regular FVU Url----------", downloadFileLocation);
                                utl.generateLog("Generate CSI Location----", generateCsiFileLoc);

                                String downloadFile = HttpDownloadUtility.downloadFile(downloadFileLocation, generateCsiFileLoc);
                                if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                                    isCsiFileDownloaded = true;
                                    isCsiFilePresent = true;
                                    boolean flag = false;
                                    System.out.println("update csifilepath---" + generateCsiFileLoc + File.separator + csiName);
                                    String query = "UPDATE lhssys_process_log SET fvu_csi_filename = '" + generateCsiFileLoc + File.separator + csiName + "' WHERE process_seqno=" + getTokenNo();
                                    flag = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(query);
                                    if (flag) {
                                        utl.generateLog("CSI file path updated in fvu_csi_filename coloum -", flag);
                                        checkTextCsiPresent();
                                    }
                                    setCsiFileName(csiName);
                                    setCsiFilePath(generateCsiFileLoc + File.separator + csiName);
                                    setFvuSessionResult("CSI File Generated Successfully From OLTAS");
                                    session.put("ERRORCLASS", MessageType.successMessage);

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
                                        utl.generateLog(null, "---CSI FILE DOWNLOADED IN DIRECTRY-----");

                                    }
                                    if (!isCsiFileDownloaded) {
                                        // set flag to view upload div
                                        setAllowCsiUpload("T");
                                        setProcessBtn("disabled");
                                        setFvuSessionResult("CSI File Not Generated From OLTAS");
                                        session.put("ERRORCLASS", MessageType.errorMessage);
                                        utl.generateLog(null, "---CSI FILE NOT GENERATED-----");
                                    }
                                }
                            } catch (Exception e) {
                                returnMsg = "Some Error Occured, Could Not Generate CSI File";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                setFvuSessionResult(returnMsg);
                            }

                        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("generateFvuFileCall")) {
                            System.out.println("GENERATE FVU FILE PROCESS STARTED");
                            String textFileLocation = "";

                            if (getTextFileFlag().equalsIgnoreCase("T")) {
                                if (getCsiFileFlag().equalsIgnoreCase("T")) {
                                    fVUFileSupport.deleteFilesInDirectory(finalFVUDirectory);
                                    // fVUFileSupport.deleteFilesInDirectory(zipFileLoc);
                                    System.out.println("text file path->" + getTextFilepath());
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
                                        System.out.println("fvuFileDirectory->" + fvuFileDirectory);
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
                                            if (getTextFileFlag().equalsIgnoreCase("T")) {
                                                if (!utl.isnull(javaDriveName)) {
                                                    File fO = new File(javaDriveName);
                                                    if (fO.exists()) {
                                                        textFileLocation = getTextFilepath();
                                                        File f_textFile = new File(textFileLocation);
                                                        File desti = new File(generateFileLoc);
                                                        System.out.println("f_textFile->" + f_textFile);
                                                        System.out.println("desti->" + desti);
                                                        FileUtils.copyFileToDirectory(f_textFile, desti);

                                                    } else {
                                                        returnView = "drive_error";
                                                        setErrorMessage("Java Drive not configured ! Contact to support person !");
                                                    }
                                                }
                                            }

                                            String generatedtextFilePathName = "";
                                            String textFilePathName = "select t.fuv_text_filename from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                                            generatedtextFilePathName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(textFilePathName);
                                            System.out.println("generatedtextFilePathName==" + textFilePathName);
                                            System.out.println("generatedtextFilePathName==" + generatedtextFilePathName);
                                            //generate all file loc
                                            File newGeneratedTextFile = new File(generatedtextFilePathName);// text file
                                            File newGeneratedCsiFile = new File(csiFileLocation);// csi file
                                            File newGeneratedRtfFile = new File(rtfFileLocation);// rtf file
//                                                  File[] listFiles3 = fileLoc.listFiles();
//                                                  Boolean zipFiles = false;
                                            String tempTextFile = textFileName;
                                            System.out.println("textFileLocation ==" + generatedtextFilePathName);
                                            System.out.println("csiFileLocation  ==" + csiFileLocation);
                                            System.out.println("rtfFileLocation  ==" + rtfFileLocation);
                                            String storageFileLocation = fileLoc.getAbsolutePath();
                                            System.out.println("storageFileLocation==" + storageFileLocation);
                                            System.out.println("generateFileLoc->" + generateFileLoc);
                                            if (newGeneratedCsiFile.exists() && newGeneratedTextFile.exists() && newGeneratedRtfFile.exists()) {
                                                boolean generateBatchFile = fVUFileSupport.generateBatchFile(fvuFileDirectory, generateFileLoc, rtfFileLocation, newGeneratedTextFile, newGeneratedCsiFile, newGeneratedRtfFile, errorFile, fvuVersion, getTokenNo().toString());
                                                if (generateBatchFile) {

                                                    String fvuFile = "";
                                                    String storageFile = "";
                                                    setFileTypeFound("G");// set any dummy value so that variable is not null
                                                    boolean validateMsgFlag = false;
                                                    for (File file : fileLoc.listFiles()) {

                                                        if (file.getName().endsWith(".pdf")) {
                                                            setFileTypeFound("P");// pdf
//                                                                setGeneratedFileLink(generateFileLoc);
//                                                                setGeneratedFileName(file.getName());
                                                            validateMsgFlag = true;
                                                        }

                                                        storageFile += file.getName() + "#";
                                                    }

                                                    if (validateMsgFlag) {

                                                        storageFile += zipFileName + "#";
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

                                                        storageFile += zipFileName + "#";
                                                        // update file name & location if fvu generated : START
                                                        LhssysProcessLogDAO fileTranDAO2 = factory.getLhssysProcessLogDAO();
                                                        LhssysProcessLog log = fileTranDAO2.readProcessbySeqNo(getTokenNo());
                                                        if (log != null) {
                                                            log.setFvu_files_path(storageFileLocation);
                                                            log.setFvu_files_name_str(storageFile);
                                                            log.setProcess_status("FD");
                                                            log.setProcess_end_timestamp(Date.from(Instant.now()));
//                                                                            log.setProcesste_nd_timestamp(process_end_timestamp);
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

    void checkTextCsiPresent() {
        try {
            String textFileName = "";
            String textFullFileName = "";
            String csiFullFileName = "";
            String queryToFileFileName = "select t.fvu_txt_file_name from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
            textFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFileFileName);
            setTextFileName(textFileName);
            //String queryToFullFileName = "select t.fuv_text_filename from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
            //textFullFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFullFileName);
            //setTextFilepath(textFullFileName);
            String queryToFullcsiFileName = "select t.fvu_csi_filename from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
            csiFullFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFullcsiFileName);
            setCsiFilePath(csiFullFileName);
            String oraTextFilefullPath = getOracleDriveName() + File.separator + "TEXT_FILES" + File.separator + textFileName;
            setOraTextFilePath(oraTextFilefullPath);
            setTextFilepath(oraTextFilefullPath);
            boolean updateFlag = false;
            String query = "UPDATE lhssys_process_log SET fuv_text_filename = '" + oraTextFilefullPath + "' WHERE process_seqno=" + getTokenNo();
            updateFlag = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(query);
            if (updateFlag) {
                utl.generateLog("Text file path updated in fuv_text_filename coloum -", updateFlag);

            }
            System.out.println("oraTextFilefullPath-->" + oraTextFilefullPath);
            System.out.println("textFileName-->" + textFileName);
            System.out.println("textFileNamepath-->" + textFullFileName);
            System.out.println("csiFullFileName-->" + csiFullFileName);
            if (!utl.isnull(oraTextFilefullPath)) {
                File file1 = new File(oraTextFilefullPath);
                if (file1.exists()) {
                    System.out.println("text File present in system");
                    setTextFileFlag("T");
                } else {
                    System.out.println("text File not present in system");
                    setTextFileFlag("F");
                }
            }
            if (!utl.isnull(csiFullFileName)) {
                File file2 = new File(csiFullFileName);
                if (file2.exists()) {
                    System.out.println("CSI File present in system");
                    setCsiFileFlag("T");
                } else {
                    System.out.println("CSI File not present in system");
                    setCsiFileFlag("F");
                }
            } else {
                setCsiFileFlag("F");
            }

        } catch (Exception e) {

        }

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
    private String textFilepath;
    private String textFileFlag;
    private String csiFileName;
    private String csiFilePath;
    private String csiFileFlag;
    private String CLOB_FVU_GEN_FLAG;
    private String BLOB_FILE_DOWNLOAD_FLAG;
    private String oraTextFilePath;
    private String oracleDriveName;

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

    public GenerateFVUBtnClobAction() {
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

    public String getCsiFileName() {
        return csiFileName;
    }

    public void setCsiFileName(String csiFileName) {
        this.csiFileName = csiFileName;
    }

    public String getCsiFilePath() {
        return csiFilePath;
    }

    public void setCsiFilePath(String csiFilePath) {
        this.csiFilePath = csiFilePath;
    }

    public String getTextFilepath() {
        return textFilepath;
    }

    public void setTextFilepath(String textFilepath) {
        this.textFilepath = textFilepath;
    }

    public String getTextFileFlag() {
        return textFileFlag;
    }

    public void setTextFileFlag(String textFileFlag) {
        this.textFileFlag = textFileFlag;
    }

    public String getCsiFileFlag() {
        return csiFileFlag;
    }

    public void setCsiFileFlag(String csiFileFlag) {
        this.csiFileFlag = csiFileFlag;
    }

    public String getCLOB_FVU_GEN_FLAG() {
        return CLOB_FVU_GEN_FLAG;
    }

    public void setCLOB_FVU_GEN_FLAG(String CLOB_FVU_GEN_FLAG) {
        this.CLOB_FVU_GEN_FLAG = CLOB_FVU_GEN_FLAG;
    }

    public String getOraTextFilePath() {
        return oraTextFilePath;
    }

    public void setOraTextFilePath(String oraTextFilePath) {
        this.oraTextFilePath = oraTextFilePath;
    }

    public String getOracleDriveName() {
        return oracleDriveName;
    }

    public void setOracleDriveName(String oracleDriveName) {
        this.oracleDriveName = oracleDriveName;
    }

    public String getBLOB_FILE_DOWNLOAD_FLAG() {
        return BLOB_FILE_DOWNLOAD_FLAG;
    }

    public void setBLOB_FILE_DOWNLOAD_FLAG(String BLOB_FILE_DOWNLOAD_FLAG) {
        this.BLOB_FILE_DOWNLOAD_FLAG = BLOB_FILE_DOWNLOAD_FLAG;
    }

}
