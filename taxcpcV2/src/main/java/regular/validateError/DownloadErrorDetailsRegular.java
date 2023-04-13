/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbQueryExecutorAsList;
import dao.ViewDeducteeTemplateDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanFilterEntity;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadErrorDetailsRegular extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    DateTimeUtil dateTime;
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

    private ChallanFilterEntity tranChallanFltrSrch;
    private String templeteCode;
    private String errorTypeCode;
    private int no_of_column;

    public DownloadErrorDetailsRegular() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            setTempleteCode("TAXCPC01");
            String fileLocation = "";
            if (!utl.isnull(getTempleteCode())) {
                ViewDeducteeTemplateDAO viewDeducteeTemplate = factory.getViewDeducteeTemplateDAO();
                ViewClientTemplate deducteetemp = viewDeducteeTemplate.getDataAsTempleteCode(getTempleteCode());
                if (deducteetemp != null) {
                    fileLocation = deducteetemp.getFile_location();
                }
            }
            String file_ext = (String) session.get("EXCELFORMAT");
            String savedFilePath = "";
            if (!utl.isnull(fileLocation)) {
                savedFilePath = RenameAndSaveUploadedFile(fileLocation, getTempleteCode(), file_ext);

                if (!utl.isnull(savedFilePath)) {

                    Assessment asmt = (Assessment) session.get("ASSESSMENT");

                    String accYear = asmt.getAccYear();
                    String tds_type_code = asmt.getTdsTypeCode();
                    String quarterNumber = asmt.getQuarterNo();
                    String acc_year1 = accYear.replace("-", "");
                    String quarterNo = "Q" + Integer.parseInt(quarterNumber);
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
                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
                    String clientCode = viewClientMast.getClient_code();

                    String filename = ("TAXCPC_" + viewClientMast.getTanno() + "_" + acc_year1 + "_" + quarterNo + "_" + tds_type_code);

                    String file_dwl_time = dateTime.get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");

                    ProcessErrorsDB proc_err = new ProcessErrorsDB();
                    String count_record_query = proc_err.downloadErrorDetailCount15GHQuery(asmt, clientCode, clientCode, quarterNo);
                    utl.generateSpecialLog("RCN-0001 (Download Error Detail Count Query)----", count_record_query);

                    long total = 0l;
                    DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                    total = objDBListCount.execute_oracle_function_as_integar(count_record_query);
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

                            String l_record_detail_query = proc_err.downloadErrorDetailDataGridQuery15GH(asmt, clientCode, clientCode, quarterNumber);
                            utl.generateSpecialLog("RCN-0002 (Download Error Detail Data Grid Query)----", l_record_detail_query);

                            DbQueryExecutorAsList objData = new DbQueryExecutorAsList();

                            ArrayList<ArrayList<String>> tempDataRecordList = objData.execute_oracle_query_as_list(l_record_detail_query);

                            String real_filename = filename + file_ext;
                            setFileName(real_filename);//User File Name 
                            int l_noCol = 32;
                            setNo_of_column(l_noCol);
                            boolean savedFile = generateErrorFile(getNo_of_column(), savedFilePath, tempDataRecordList);
                            if (savedFile) {
                                File obj_dwl_file = new File(savedFilePath);
                                if (obj_dwl_file.exists()) {
                                    gen_files += savedFilePath + "#";
                                }
                            } else {
                                l_return_value = "input";
                                session.put("ERRORCLASS", ErrorType.errorMessage);
                                session.put("session_result", "Some Error Occured, Could Not Download File");
                            }

                        } else {
                            l_return_value = "input";
                            session.put("ERRORCLASS", ErrorType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File");
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
                                String real_filename = filename + "_" + file_dwl_time + ".zip";
                                setFileName(real_filename);//User File Name 
                                l_return_value = "downloadsuccess";
                            } else if (!utl.isnull(l_files_list[0])) {
                                File obj_dwl_file = new File(l_files_list[0]);
                                if (obj_dwl_file.exists()) {
                                    fileInputStream = new FileInputStream(obj_dwl_file);
                                    l_return_value = "downloadsuccess";
                                } else {
                                    l_return_value = "input";
                                    session.put("ERRORCLASS", ErrorType.errorMessage);
                                    session.put("session_result", "Some Error Occured, Could Not Download File");
                                }
                            } else {
                                l_return_value = "input";
                                session.put("ERRORCLASS", ErrorType.errorMessage);
                                session.put("session_result", "Some Error Occured, Could Not Download File");
                            }
                        } else {
                            l_return_value = "input";
                            session.put("ERRORCLASS", ErrorType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    l_return_value = "input";
                    session.put("ERRORCLASS", ErrorType.errorMessage);
                    session.put("session_result", "Some Error Occured, Could Not Download File");
                }
            } else {
                l_return_value = "input";
                session.put("ERRORCLASS", ErrorType.errorMessage);
                session.put("session_result", "Some Error Occured, Could Not Download File");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }//end method

    private boolean generateErrorFile(int no_of_column, String savedFilePath, ArrayList<ArrayList<String>> tempDataRecordList) {
        boolean l_write_data = true;
        try {
            Workbook workbookCopy = new XSSFWorkbook(new FileInputStream(new File(savedFilePath)));
            Sheet sheet = workbookCopy.createSheet("Error Details");

            if (tempDataRecordList.size() > 0) {// used to generate cell data value for sheet no 2
                try {
                    for (int i = 0; i < tempDataRecordList.size(); i++) {//rows
                        Row row1 = sheet.createRow(i + 1);
                        Cell cell2 = null;

                        for (int j = 0; j < no_of_column; j++) {//cols
                            String contentString = utl.isnull(tempDataRecordList.get(i).get(j)) ? "" : tempDataRecordList.get(i).get(j);
                            if (!utl.isnull(contentString)) {
                                cell2 = row1.createCell(j);
                                cell2.setCellValue(contentString);
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }//end 

            if (sheet != null) {
                try (FileOutputStream outputStream = new FileOutputStream(savedFilePath)) {
                    workbookCopy.write(outputStream);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_write_data;
    }//end method

    private String RenameAndSaveUploadedFile(String fileLocation, String tempCode, String file_ext) {
        String filePath;
        try {
            String file_name = tempCode + file_ext;
            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + dateTime.get_sysdate("dd_MM_yyyy_hh_mm_ss") + file_name;
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String destPath = javaDriveName + "/UPLOADED_EXCEL/";
            File source = new File(fileLocation);
            File destFile = new File(destPath, file_name);
            FileUtils.copyFile(source, destFile);
            filePath = destFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            filePath = null;
        }
        return filePath;
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

    public ChallanFilterEntity getTranChallanFltrSrch() {
        return tranChallanFltrSrch;
    }

    public void setTranChallanFltrSrch(ChallanFilterEntity tranChallanFltrSrch) {
        this.tranChallanFltrSrch = tranChallanFltrSrch;
    }

    public String getTempleteCode() {
        return templeteCode;
    }

    public void setTempleteCode(String templeteCode) {
        this.templeteCode = templeteCode;
    }

    public String getErrorTypeCode() {
        return errorTypeCode;
    }

    public void setErrorTypeCode(String errorTypeCode) {
        this.errorTypeCode = errorTypeCode;
    }

    public int getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(int no_of_column) {
        this.no_of_column = no_of_column;
    }

}
