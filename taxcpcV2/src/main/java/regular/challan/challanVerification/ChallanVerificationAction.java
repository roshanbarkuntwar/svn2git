/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanVerification;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysFileTranDAO;
import dao.LhssysParametersDAO;
import dao.TdsChallanTranLoadDAO;
import dao.ViewClientMastDAO;
import dao.ViewTdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewTdsChallanTranLoad;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import regular.generateFVU.GenerateFVUFileSupport;
import regular.generateFVU.ReadFromConsolidatedTDSFile;

/**
 *
 * @author user
 */
public class ChallanVerificationAction extends ActionSupport implements SessionAware, ServletRequestAware {

    public ChallanVerificationAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    HttpServletRequest servletRequest;
    private InputStream fileInputStream;
    private String fileTypeFound;
    private String generatedFileLink;
    private String generatedFileName;
    private static final int BUFFER_SIZE = 4096;
    private String fileseqno;
    Util utl;
    private InputStream inputStream;
    Document doc;
    String butnDivToHide;
    private Long totalChallanCount;
    private Long matchedChallanCount;
    private Long unMatchedChallanCount;
    private List<ViewTdsChallanTranLoad> unMatchedChallansList;
    private boolean validateFlag;
    private String displayFlag;
    private long total;
    private String readOnlineFlag;
    private String allowCsiUpload;// flag to allow/ not allow upload of csi file when readonline flag is true--08-12-16

    @Override
    public String execute() throws Exception {
        String returnView = "challanVerified";
        JSONObject challanetailsJsonObject = new JSONObject();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysParametersDAO lhssysParametersDAO;
        try {
            ViewClientMast client = null;
            try {
                client = (ViewClientMast) session.get("WORKINGUSER");
            } catch (Exception e) {
                e.printStackTrace();
            }
            GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
            if (client != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {
                    UserMast userMast = (UserMast) session.get("LOGINUSER");
                    int quarter_no = 0;
                    String acc_year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();
                    if (!utl.isnull(quarterNo) && quarterNo.length() > 1) {
                        quarterNo = quarterNo.substring(1, 2);
                    }
                    quarter_no = Integer.parseInt(quarterNo);
                    Date from_date = asmt.getQuarterFromDate();
                    Date to_date = asmt.getQuarterToDate();
                    String tds_type_code = asmt.getTdsTypeCode();
                    ViewTdsChallanTranLoadDAO chalantran = factory.getViewTdsChallanTranLoadDAO();
//                    ChallanFilterEntity tdsChallanTranFilterSrch = session.get("MANAGETDSCHALLANSRCH") == null ? null : (ChallanFilterEntity) session.get("MANAGETDSCHALLANSRCH");
                    Long tdsChallanTranCount = chalantran.getTdsChallanTranCount(client.getClient_code(), acc_year, quarterNo, tds_type_code, null, "false", utl);
                    utl.generateLog("tdsChallanTranCount-----", tdsChallanTranCount);
                    if (tdsChallanTranCount > 0l) {// if challans found
                        setTotal(tdsChallanTranCount);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                        -------- : DO NOT DELETE THIS LINE
//                        String textFileGeneratedFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'TXT_FILE_GEN') from dual";
                        String fileNameFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'TXT_FILE_NAME_CH') from dual";
                        utl.generateLog("fileNameFunction---", fileNameFunction);
                        String fileSeqNoFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'FILE_SEQNO_CH') from dual";
                        utl.generateLog("fileSeqNoFunction---", fileSeqNoFunction);
                        //get session values
                        Long l_client_loginid_seq;
//                        long l_upload_method = 0l;   -------- : DO NOT DELETE THIS LINE
                        Object sessionId = session.get("LOGINSESSIONID");
                        try {
                            l_client_loginid_seq = (Long) sessionId;
                        } catch (NumberFormatException e) {
                            l_client_loginid_seq = 0l;
                        }

                        // get JAVA and oracle drive names
                        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                        String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
                        if (validateFlag) {

                            if (!utl.isnull(javaDriveName) && !utl.isnull(oracleDriveName)) {

                                String clientDir = javaDriveName + File.separator + "CHALLAN_VERIFICATION_CSI" + File.separator + client.getClient_code();
                                String accYearDir = clientDir + File.separator + asmt.getAccYear();
                                String qtrDir = accYearDir + File.separator + "Q" + quarterNo;
                                String tdsTypeCodeDir = qtrDir + File.separator + asmt.getTdsTypeCode();
                                String generateFileLoc = tdsTypeCodeDir;
//                                String generateCsiFileLoc = qtrDir + File.separator + "CSI";
                                String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                                        + File.separator + client.getClient_code()
                                        + File.separator + asmt.getAccYear()
                                        + File.separator + asmt.getTdsTypeCode();
                                File fileLoc = null;
                                File csiFileLoc = null;
                                fVUFileSupport.createDirectory(clientDir);
                                fVUFileSupport.createDirectory(accYearDir);
                                fVUFileSupport.createDirectory(qtrDir);
                                fVUFileSupport.createDirectory(tdsTypeCodeDir);
                                fVUFileSupport.createDirectory(generateCsiFileLoc);
                                try {
                                    fVUFileSupport.deleteFilesInDirectory(generateFileLoc);
                                } catch (Exception e) {
                                }
                                fileLoc = new File(generateFileLoc);
                                csiFileLoc = new File(generateCsiFileLoc);
                                // CHECK IF FILE IS PRESENT AT CSI LOCATION 
                                //get csi file name
                                boolean isCsiFilePresent = false;
                                String csiName = fVUFileSupport.getCsiFileName(client.getTanno());
                                csiName = !utl.isnull(csiName) ? csiName : "";
                                File[] listFiles = csiFileLoc.listFiles();
                                for (File listFile : listFiles) {
                                    String oldCsiName = listFile.getName();
                                    utl.generateLog("oldCsiName--", oldCsiName);
                                    if (oldCsiName.equalsIgnoreCase(csiName)) {
                                        isCsiFilePresent = true;
                                        break;
                                    }
                                }
                                boolean textFileGenerated;
                                textFileGenerated = fVUFileSupport.generateTextFile(client, acc_year, quarter_no, from_date, to_date, tds_type_code, l_client_loginid_seq, userMast.getUser_code(), null);
                                utl.generateLog("textFileGenerated---", textFileGenerated);
                                String textFileName = "";
                                String textFileSeqNo = "";
                                String textFileLocation = "";

                                if (textFileGenerated) {
                                    // fetch text file name
                                    textFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileNameFunction);
                                    textFileSeqNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileSeqNoFunction);
                                    if (!utl.isnull(textFileName)) {
                                        try {
                                            String storageLoction = oracleDriveName + File.separator + "TEXT_FILES";
                                            textFileLocation = storageLoction + File.separator + textFileName;
//                                        //System.out.println("textFileLocation--" + textFileLocation);
                                            File f_textFile = new File(textFileLocation);
                                            FileUtils.copyFileToDirectory(f_textFile, fileLoc);
                                        } catch (Exception e) {
                                            e.printStackTrace();
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
                                                utl.generateLog("csiFileLocation--", csiFileLocation);
                                                String clientNameFromCsiFile = dSFile.getClientNameFromCsiFile(csiFileLocation);
                                                ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
                                                ViewClientMast client2 = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                                                String client_name = client2.getClient_name();
                                                if (client_name.equalsIgnoreCase(clientNameFromCsiFile)) {
                                                    // COPY RTF FILE--------------
                                                    String fvuVersion = "";
                                                    try {
                                                        lhssysParametersDAO = factory.getLhssysParametersDAO();
                                                        LhssysParameters readFvuFileVersion = lhssysParametersDAO.readFvuFileVersion(client.getEntity_code());
                                                        if (readFvuFileVersion != null) {
                                                            fvuVersion = readFvuFileVersion.getParameter_value();
                                                        }
                                                    } catch (Exception e) {
                                                    }

//                                                    String fvuFileDirectory = javaDriveName + File.separator + "TAXCPC" + File.separator + "FVU_5.1";//get fvu directory path
                                                    String fvuFileDirectory = javaDriveName + File.separator + "TAXCPC" + File.separator + "FVU_" + fvuVersion;//get fvu directory path
                                                    utl.generateLog("fvuFileDirectory----", fvuFileDirectory);
                                                    boolean rtfFileGenerated = fVUFileSupport.generateRtfFile(fvuFileDirectory, generateFileLoc);
                                                    if (rtfFileGenerated) {
                                                        String rtfFileLocation = generateFileLoc + File.separator + "Q126QC.rtf";
                                                        //------------generate error file

                                                        String errorFileLocation = generateFileLoc + File.separator + textFileName.split("\\.")[0] + ".txt";
                                                        File errorFile = new File(errorFileLocation);
                                                        if (!errorFile.exists()) {
                                                            errorFile.createNewFile();
                                                        }
                                                        String storageFileLocation = "";
                                                        String storageFile = "";
                                                        String fvuFile = "";

                                                        //generate all file loc
                                                        File newGeneratedTextFile = new File(textFileLocation);// text file
                                                        File newGeneratedCsiFile = new File(csiFileLocation);// csi file
                                                        File newGeneratedRtfFile = new File(rtfFileLocation);// rtf file
                                                        if (newGeneratedCsiFile.exists() && newGeneratedTextFile.exists() && newGeneratedRtfFile.exists()) {
                                                            boolean generateBatchFile = fVUFileSupport.generateBatchFile(fvuFileDirectory, generateFileLoc, rtfFileLocation, newGeneratedTextFile, newGeneratedCsiFile, newGeneratedRtfFile, errorFile, fvuVersion, client.getEntity_code());
                                                            if (generateBatchFile) {
                                                                setFileTypeFound("G");
                                                                File[] listFiles2 = fileLoc.listFiles();
                                                                boolean validateMsgFlag = false;
                                                                for (File file : listFiles2) {

                                                                    if (!file.getName().endsWith("png") && !file.getName().endsWith("bat") && !file.getName().endsWith(".rtf")) {
                                                                        storageFile += file.getName() + "#";
                                                                        storageFileLocation = fileLoc.getAbsolutePath();

                                                                    }

                                                                    if (file.getName().endsWith(".pdf")) {
                                                                        setFileTypeFound("P");// pdf
                                                                        setGeneratedFileLink(generateFileLoc);
                                                                        setGeneratedFileName(file.getName());
                                                                        validateMsgFlag = true;
                                                                    }
                                                                    // fvu file
                                                                    if (file.getName().endsWith("fvu")) {
                                                                        fvuFile = file.getName();
                                                                    }
                                                                    if (file.getName().endsWith("err.html")) {
                                                                        session.put("genertedFileLoc", generateFileLoc + File.separator);
                                                                        session.put("genertedFileName", file.getName());
                                                                    }
                                                                }

                                                                // update file name & location if fvu generated : START
                                                                LhssysFileTranDAO fileTranDAO2 = factory.getLhssysFileTranDAO();
                                                                int updateData = fileTranDAO2.updateData(textFileSeqNo, storageFile, storageFileLocation, "", fvuFile);
                                                                if (updateData > 0) {
                                                                    utl.generateLog(null, "Record update successfully");
                                                                } else {
                                                                    utl.generateLog(null, "record not update successfully");
                                                                }
                                                                // END

                                                                if (validateMsgFlag) {
                                                                    boolean htmlFilefound = false;
                                                                    String errorFilePath = "";
                                                                    File[] fileLocList = fileLoc.listFiles();
                                                                    for (File file : fileLocList) {
                                                                        try {
                                                                            if (file.getName().endsWith("html")) {
                                                                                String genErrFileName = textFileName.split("\\.")[0] + "_Electronic_Statement_Warning_File.html";
                                                                                if (genErrFileName.equalsIgnoreCase(file.getName())) {
                                                                                    htmlFilefound = true;
                                                                                    errorFilePath = file.getAbsolutePath();
                                                                                    setGeneratedFileLink(generateFileLoc);
                                                                                    setGeneratedFileName(file.getName());
                                                                                    session.put("genertedFileLoc", generateFileLoc);
                                                                                    session.put("genertedFileName", file.getName());
                                                                                    File input = new File(errorFilePath);
                                                                                    doc = Jsoup.parse(input, "UTF-8", "");

                                                                                    // CODE to UPDATE fvu file status in  csi_verify_flag of tds_challan_tran :: START
                                                                                    try {
                                                                                        fVUFileSupport.updateChallanRecords(client.getClient_code(), client.getEntity_code(), acc_year, quarterNo, tds_type_code, doc);
                                                                                    } catch (Exception eq) {
                                                                                        utl.generateLog("Exception errorFilePath=============", eq);
                                                                                    }
                                                                                    // END
                                                                                }
                                                                            }
                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();

                                                                        }
                                                                    }
                                                                    if (!htmlFilefound) {
                                                                        try {
                                                                            utl.generateLog(null, "/------------------------------------------------------------------------");
                                                                            utl.generateLog(null, "/------------------------------------------------------------------------");
                                                                            utl.generateLog(null, "_Electronic_Statement_Warning_File.html not found, \nso all challans are to be considered as 'MATCHED'....\nas per Mr. Sapan Jain  27.07.2016");
                                                                            utl.generateLog(null, "/------------------------------------------------------------------------");
                                                                            utl.generateLog(null, "/------------------------------------------------------------------------");
                                                                            TdsChallanTranLoadDAO tdsChallanTranLoadDAO = factory.getTdsChallanTranLoadDAO();
                                                                            TdsChallanTranLoad challanTranLoad = new TdsChallanTranLoad();
                                                                            utl.generateLog("clientCode--", client.getClient_code());
                                                                            utl.generateLog("accYear--", asmt.getAccYear());
                                                                            utl.generateLog("quarterNo--", quarterNo);
                                                                            utl.generateLog("tdsTypeCode--", asmt.getTdsTypeCode());
                                                                            challanTranLoad.setClient_code(client.getClient_code());
                                                                            challanTranLoad.setAcc_year(asmt.getAccYear());
                                                                            challanTranLoad.setQuarter_no(quarterNo);
                                                                            challanTranLoad.setTds_type_code(asmt.getTdsTypeCode());

                                                                            List<TdsChallanTranLoad> tdsChallanTranLoadList = tdsChallanTranLoadDAO.getFVUFileDetails(challanTranLoad);
                                                                            tdsChallanTranLoadDAO = factory.getTdsChallanTranLoadDAO();
                                                                            Session dbssn = getSessionFactory().openSession();
                                                                            int updatecount = 0;
                                                                            for (TdsChallanTranLoad tdsChallanTranLoad : tdsChallanTranLoadList) {
                                                                                tdsChallanTranLoad.setCsi_verify_flag("M");
                                                                                dbssn.update(tdsChallanTranLoad);
                                                                                updatecount++;
                                                                            }
                                                                            if (updatecount % 25 == 0) {
                                                                                dbssn.flush();
                                                                            }
                                                                            if (updatecount == tdsChallanTranLoadList.size()) {
                                                                                dbssn.getTransaction().commit();
                                                                            } else {
                                                                                dbssn.getTransaction().rollback();
                                                                            }
                                                                        } catch (Exception e) {
                                                                            utl.generateLog(null, "\nError in updating all records as Matched......\n");
                                                                            e.printStackTrace();
                                                                        }
                                                                    }
                                                                    // code for challan details : START
                                                                    setRecordValues(factory, client.getClient_code(), client.getEntity_code(), acc_year, quarterNo, tds_type_code);
                                                                    //END
                                                                    challanetailsJsonObject.accumulate("Verification", "success");
                                                                } else {
                                                                    utl.generateLog(null, "Error File Generated");
                                                                    challanetailsJsonObject.accumulate("Verification", "errorFileGenerated");
                                                                }
                                                            } else {
                                                                challanetailsJsonObject.accumulate("Verification", "failed");
                                                                utl.generateLog(null, "Some Error Occured, Could Not Generate Batch File");
                                                            }
                                                        } else {
                                                            challanetailsJsonObject.accumulate("Verification", "failed");
                                                            addActionError("Some Error Occured, Could Not Generate FVU File3");
                                                            session.put("ERRORCLASS", ErrorType.errorMessage);
                                                            utl.generateLog(null, "Some Error Occured, Could Not Generate FVU File");
                                                        }
                                                    } else {
                                                        challanetailsJsonObject.accumulate("Verification", "failed");
                                                        utl.generateLog(null, "Some Error Occured, Could Not Generate RTF File");
                                                    }
                                                } else {
                                                    challanetailsJsonObject.accumulate("Verification", "deducteeNameNotMatch");
                                                    addActionError("Client Name is Different From ITD, Could Not Generate Challan File");
                                                    session.put("ERRORCLASS", ErrorType.errorMessage);
                                                    utl.generateLog(null, "Client Name is Different From ITD, Could Not Generate Challan File");
                                                }
                                            } else {
                                                challanetailsJsonObject.accumulate("Verification", "csiFileNotGenerated");
                                                utl.generateLog(null, "Some Error Occured, Could Not Download CSI File");
                                            }
                                        } else {
                                            challanetailsJsonObject.accumulate("Verification", "failed");
                                            utl.generateLog(null, "TEXT FILE NOT FOUND");
                                        }
                                    } else {
                                        challanetailsJsonObject.accumulate("Verification", "failed");
                                        utl.generateLog(null, "TEXT FILE NOT GENERATED");
                                    }
                                } else {
                                    challanetailsJsonObject.accumulate("Verification", "failed");
                                    utl.generateLog(null, "TEXT FILE NOT GENERATED");
                                }
                            } else {
                                challanetailsJsonObject.accumulate("Verification", "driveError");
                                utl.generateLog(null, "external drive not mapped");
                            }
                        } else {
                            challanetailsJsonObject.accumulate("Verification", "failed");
                        }
                    } else {
                        challanetailsJsonObject.accumulate("Verification", "nochallan");
                        utl.generateLog(null, "nochallan");
                    }
                } else {
                    returnView = "setassessment";
                }
            } else {
                returnView = "getlogin";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        challanetailsJsonObject.accumulate("TotalChallanCount", getTotalChallanCount());
        challanetailsJsonObject.accumulate("MatchedChallanCount", getMatchedChallanCount());
        challanetailsJsonObject.accumulate("UnMatchedChallanCount", getUnMatchedChallanCount());
        inputStream = new ByteArrayInputStream(challanetailsJsonObject.toString().getBytes("UTF-8"));
        return returnView;
    }//END METHOD

    private void setRecordValues(DAOFactory factory, String clientCode, String entityCode, String accYear, String quarterNo, String tdsTypeCode) {
        ViewTdsChallanTranLoadDAO viewTdsChallanTranLoadDAO = factory.getViewTdsChallanTranLoadDAO();
        ViewTdsChallanTranLoad challanTran = new ViewTdsChallanTranLoad();
        utl.generateLog("clientCode--", clientCode);
        utl.generateLog("accYear--", accYear);
        utl.generateLog("quarterNo--", quarterNo);
        utl.generateLog("tdsTypeCode--", tdsTypeCode);
        challanTran.setClient_code(clientCode);
        challanTran.setAcc_year(accYear);
        challanTran.setQuarter_no(quarterNo);
        challanTran.setTds_type_code(tdsTypeCode);
        setTotalChallanCount(viewTdsChallanTranLoadDAO.getChallanCount(challanTran, utl));

        utl.generateLog(null, "matched----Csi_varify_flag--M");
        viewTdsChallanTranLoadDAO = factory.getViewTdsChallanTranLoadDAO();
        challanTran.setCsi_varify_flag("M");
        setMatchedChallanCount(viewTdsChallanTranLoadDAO.getChallanCount(challanTran, utl));

        utl.generateLog(null, "unmatched----Csi_varify_flag--U");
        viewTdsChallanTranLoadDAO = factory.getViewTdsChallanTranLoadDAO();
        challanTran.setCsi_varify_flag("U");
        setUnMatchedChallanCount(viewTdsChallanTranLoadDAO.getChallanCount(challanTran, utl));

        viewTdsChallanTranLoadDAO = factory.getViewTdsChallanTranLoadDAO();
        setUnMatchedChallansList(viewTdsChallanTranLoadDAO.readChallansByCSIFlag(challanTran, utl));
    }// END METHOD

    public String getAllowCsiUpload() {
        return allowCsiUpload;
    }

    public void setAllowCsiUpload(String allowCsiUpload) {
        this.allowCsiUpload = allowCsiUpload;
    }

    public String getReadOnlineFlag() {
        return readOnlineFlag;
    }

    public void setReadOnlineFlag(String readOnlineFlag) {
        this.readOnlineFlag = readOnlineFlag;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public String getButnDivToHide() {
        return butnDivToHide;
    }

    public void setButnDivToHide(String butnDivToHide) {
        this.butnDivToHide = butnDivToHide;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
    }

    public String getGeneratedFileName() {
        return generatedFileName;
    }

    public void setGeneratedFileName(String generatedFileName) {
        this.generatedFileName = generatedFileName;
    }

    public String getGeneratedFileLink() {
        return generatedFileLink;
    }

    public void setGeneratedFileLink(String generatedFileLink) {
        this.generatedFileLink = generatedFileLink;
    }

    public String getFileTypeFound() {
        return fileTypeFound;
    }

    public void setFileTypeFound(String fileTypeFound) {
        this.fileTypeFound = fileTypeFound;
    }

    public String getFileseqno() {
        return fileseqno;
    }

    public void setFileseqno(String fileseqno) {
        this.fileseqno = fileseqno;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public boolean getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean validateFlag) {
        this.validateFlag = validateFlag;
    }

    public Long getTotalChallanCount() {
        return totalChallanCount;
    }

    public void setTotalChallanCount(Long totalChallanCount) {
        this.totalChallanCount = totalChallanCount;
    }

    public Long getMatchedChallanCount() {
        return matchedChallanCount;
    }

    public void setMatchedChallanCount(Long matchedChallanCount) {
        this.matchedChallanCount = matchedChallanCount;
    }

    public Long getUnMatchedChallanCount() {
        return unMatchedChallanCount;
    }

    public void setUnMatchedChallanCount(Long unMatchedChallanCount) {
        this.unMatchedChallanCount = unMatchedChallanCount;
    }

    public List<ViewTdsChallanTranLoad> getUnMatchedChallansList() {
        return unMatchedChallansList;
    }

    public void setUnMatchedChallansList(List<ViewTdsChallanTranLoad> unMatchedChallansList) {
        this.unMatchedChallansList = unMatchedChallansList;
    }
}
