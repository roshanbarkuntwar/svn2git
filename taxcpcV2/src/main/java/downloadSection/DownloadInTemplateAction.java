/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewDeducteeTemplateDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanDB;
import regular.transaction.TransactionDB;
import regular.transaction.TransactionFilterEntity;

/**
 *
 * @author ayushi.jain
 */
public class DownloadInTemplateAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
            String fileLocation = "";
            ViewDeducteeTemplateDAO viewDeducteeTemplate = factory.getViewDeducteeTemplateDAO();
            ViewClientTemplate deducteetemp = viewDeducteeTemplate.getDataAsTempleteCode("TAXCPC01");
            if (deducteetemp != null) {
                fileLocation = deducteetemp.getFile_location();
            }
            String acc_year1 = asmt.getAccYear().replace("-", "");

            String file_ext = null;
//            LhssysParametersDAO lhssysParametersDAO;
            try {
//                lhssysParametersDAO = factory.getLhssysParametersDAO();
//                LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("EXCEL_FORMAT", "");
//                if (readExternalDriveName != null) {
                file_ext = (String) session.get("EXCELFORMAT");
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String savedFilePath = "";
            if (!utl.isnull(fileLocation) && !utl.isnull(getDownloadFlag())) {
                savedFilePath = RenameAndSaveUploadedFile(fileLocation, getTempleteCode(), file_ext);
                if (!utl.isnull(savedFilePath)) {

                    String quarterNo = "Q" + Integer.parseInt(asmt.getQuarterNo());
                    Long l_client_loginid_seq;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }

                    String loginSeqno = "0";
                    try {
                        loginSeqno = String.valueOf(l_client_loginid_seq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String countRecordQuery = "";
                    String dataGridQuery = "";

                    if (getDownloadFlag().equalsIgnoreCase("transaction")) {
                        TransactionDB db = new TransactionDB();
                        countRecordQuery = "select count(*) from(\n" + db.getTransactionDownloadCountQuery(client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), db.filterWhereClause(getTranFilter(), client)) + ")";

                    } else if (getDownloadFlag().equalsIgnoreCase("challan")) {
                        ChallanDB chalanDb = new ChallanDB();
                        countRecordQuery = chalanDb.challanCountRecordQuery(client, asmt);
                    }

                    utl.generateSpecialLog("count_record_query---", countRecordQuery);
                    long total = 0l;
                    DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                    total = objDBListCount.execute_oracle_function_as_integar(countRecordQuery);

                    int record_per_sheet = 65500;

                    double tot = (float) total / record_per_sheet;
                    double sheetNumberDbl = Math.ceil(tot);
                    int total_page = (int) sheetNumberDbl;

                    String gen_files = "";

                    for (int i = 0; i < total_page; i++) {

                        savedFilePath = RenameAndSaveUploadedFile(fileLocation, getTempleteCode(), file_ext);
                        if (!utl.isnull(savedFilePath)) {

                            int l_start_page = 0;
                            if (i == 0) {
                                l_start_page = 1;
                            } else {
                                l_start_page = (i + 1);
                            }

                            int maxVal = (l_start_page * record_per_sheet);
                            int minVal = ((l_start_page * record_per_sheet) - record_per_sheet) + 1;

                            if ((total > 1000 && total < 1020) || total < 1000) {
                                maxVal = (int) total;
                                minVal = 1;
                            } else {
                                maxVal = 1000;
                                minVal = 1;
                            }

                            if (getDownloadFlag().equalsIgnoreCase("transaction")) {
                                TransactionDB db = new TransactionDB();
                                dataGridQuery = db.getTransactionDownloadDataGridQuery(client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), minVal, maxVal, db.filterWhereClause(getTranFilter(), client));

                            } else if (getDownloadFlag().equalsIgnoreCase("challan")) {
                                ChallanDB db = new ChallanDB();
                                dataGridQuery = db.challanRecordQuery(client, asmt, minVal, maxVal);
                            }
                            DbFunctionExecutorAsString objData = new DbFunctionExecutorAsString();
                            ArrayList<ArrayList<String>> tempDataRecordList = objData.execute_oracle_query_as_list(dataGridQuery);
                            String filename = ("TAXCPC_" + client.getTanno() + "_" + acc_year1 + "_" + quarterNo + "_" + asmt.getTdsTypeCode());
                            String real_filename = filename + file_ext;
                            setFileName(real_filename);//User File Name 
                            boolean savedFile = new FileOptUtil().writeDataInExistingExcel(getNoOfColumn(), savedFilePath, tempDataRecordList);
                            if (savedFile) {
                                File obj_dwl_file = new File(savedFilePath);
                                if (obj_dwl_file.exists()) {
                                    gen_files += savedFilePath + "#";
                                }
                            } else {
                                l_return_value = "input";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                session.put("session_result", "Some Error Occured, Could Not Download File");
                                setErrorMessage("Some Error Occured, Could Not Download File");
                            }

                        } else {
                            l_return_value = "input";
                            session.put("ERRORCLASS", MessageType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File");
                            setErrorMessage("Some Error Occured, Could Not Download File");
                        }
                    }//end for

                    try {

                        if (!utl.isnull(gen_files)) {
                            String[] l_files_list = new String[0];
                            if (gen_files.contains("#")) {
                                l_files_list = gen_files.split("#");
                            }

                            if (l_files_list.length > 1) {
                                ZipFileUtil zipfileobj = new ZipFileUtil();
                                byte[] zip = zipfileobj.getZipFileData(l_files_list);
                                fileInputStream = new ByteArrayInputStream(zip);
                                String filename = ("TAXCPC_" + client.getTanno() + "_" + acc_year1 + "_" + quarterNo + "_" + asmt.getTdsTypeCode());

                                String real_filename = filename + file_ext;
                                setFileName(real_filename);//User File Name 
                                l_return_value = "downloadsuccess";
                            } else if (!utl.isnull(l_files_list[0])) {
                                File obj_dwl_file = new File(l_files_list[0]);
                                if (obj_dwl_file.exists()) {
                                    fileInputStream = new FileInputStream(obj_dwl_file);
                                    l_return_value = "downloadsuccess";
                                } else {
                                    l_return_value = "input";
                                    session.put("ERRORCLASS", MessageType.errorMessage);
                                    session.put("session_result", "Some Error Occured, Could Not Download File");
                                    setErrorMessage("Some Error Occured, Could Not Download File");
                                }
                            } else {
                                l_return_value = "input";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                session.put("session_result", "Some Error Occured, Could Not Download File");
                                setErrorMessage("Some Error Occured, Could Not Download File");
                            }
                        } else {
                            l_return_value = "input";
                            session.put("ERRORCLASS", MessageType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File");
                            setErrorMessage("Some Error Occured, Could Not Download File");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    l_return_value = "input";
                    session.put("ERRORCLASS", MessageType.errorMessage);
                    session.put("session_result", "Some Error Occured, Could Not Download File");
                    setErrorMessage("Some Error Occured, Could Not Download File");
                }
            } else {
                l_return_value = "input";
                session.put("ERRORCLASS", MessageType.errorMessage);
                session.put("session_result", "Some Error Occured, Could Not Download File");
                setErrorMessage("Some Error Occured, Could Not Download File");
            }

        } catch (Exception e) {

        }
        return l_return_value;
    }//end execute method

    private String RenameAndSaveUploadedFile(String fileLocation, String tempCode, String file_ext) {
        String filePath;
        try {
            String file_name = tempCode + file_ext;
            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + new DateTimeUtil().get_sysdate("dd_MM_yyyy_hh_mm_ss") + file_name;
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

            String destPath = javaDriveName + "/UPLOADED_EXCEL/";
            File source = new File(fileLocation);
            File destFile = new File(destPath, file_name);
            FileUtils.copyFile(source, destFile);
            filePath = destFile.getAbsolutePath();
        } catch (Exception e) {
            filePath = null;
        }
        return filePath;
    }//end method

    Map<String, Object> session;
    Util utl;
    private String action;
    private String fileName;
    private InputStream fileInputStream;
    private HttpServletRequest servletRequest;
    private String dwn_type;
    private ImportFileAttribs obj_exl_html;
    private File inputFile;
    private String inputFileContentType;
    private String inputFileFileName;
    private String contentDisposition;
    private String ErrorTypeCode;
    private String templeteCode;
    private String downloadFlag;
    private int noOfColumn;
    private String errorMessage;
    private String downloadWhereCls;
    private TransactionFilterEntity tranFilter;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getTempleteCode() {
        return templeteCode;
    }

    public void setTempleteCode(String templeteCode) {
        this.templeteCode = templeteCode;
    }

    public int getNoOfColumn() {
        return noOfColumn;
    }

    public void setNoOfColumn(int noOfColumn) {
        this.noOfColumn = noOfColumn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getDwn_type() {
        return dwn_type;
    }

    public void setDwn_type(String dwn_type) {
        this.dwn_type = dwn_type;
    }

    public ImportFileAttribs getObj_exl_html() {
        return obj_exl_html;
    }

    public void setObj_exl_html(ImportFileAttribs obj_exl_html) {
        this.obj_exl_html = obj_exl_html;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public String getInputFileContentType() {
        return inputFileContentType;
    }

    public void setInputFileContentType(String inputFileContentType) {
        this.inputFileContentType = inputFileContentType;
    }

    public String getInputFileFileName() {
        return inputFileFileName;
    }

    public void setInputFileFileName(String inputFileFileName) {
        this.inputFileFileName = inputFileFileName;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public String getErrorTypeCode() {
        return ErrorTypeCode;
    }

    public void setErrorTypeCode(String ErrorTypeCode) {
        this.ErrorTypeCode = ErrorTypeCode;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public DownloadInTemplateAction() {
        utl = new Util();
    }

    public String getDownloadWhereCls() {
        return downloadWhereCls;
    }

    public void setDownloadWhereCls(String downloadWhereCls) {
        this.downloadWhereCls = downloadWhereCls;
    }

    public TransactionFilterEntity getTranFilter() {
        return tranFilter;
    }

    public void setTranFilter(TransactionFilterEntity tranFilter) {
        this.tranFilter = tranFilter;
    }

}//end class
