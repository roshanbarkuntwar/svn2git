/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbQueryExecutorAsList;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class DownloadErrorDetailsAction15GH extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    Util utl;
    DateTimeUtil dateTime;
    private String tableName;
    private String fileSeqNo;
    private String fileName;
    private String BckAction;
    private InputStream fileInputStream;
    private HttpServletRequest servletRequest;
    private String dwn_type;
    private ImportFileAttribs obj_exl_html;
    private File inputFile;
    private String inputFileContentType;
    private String inputFileFileName;
    private String contentDisposition;
    private String processCnfChkBx;
    private String errorTypeCode;

    public DownloadErrorDetailsAction15GH() {
        utl = new Util();
        dateTime = new DateTimeUtil();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        try {
            //DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

            String file_ext = (String) session.get("EXCELFORMAT");
            String filename = ("ErrorDetailSummery_");
            String file_dwl_time = dateTime.get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");

            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String acc_year = asmt.getAccYear();
            String quarterNumber = asmt.getQuarterNo();
            int quarter_no = Integer.parseInt(quarterNumber);
            String l_tds_type_code = asmt.getTdsTypeCode();

            ArrayList<String> summaryHeaderDeatail = new ArrayList<String>();
            summaryHeaderDeatail.add("Sr No.");
            summaryHeaderDeatail.add("Error Name");
            summaryHeaderDeatail.add("Record Count");

            ArrayList<String> panWithErrorsummaryHeaderDeatail = new ArrayList<String>();
            panWithErrorsummaryHeaderDeatail.add("Sr No.");
            panWithErrorsummaryHeaderDeatail.add("Error Name");
            panWithErrorsummaryHeaderDeatail.add("Deductee Name");
            panWithErrorsummaryHeaderDeatail.add("Deductee Pan No");
            panWithErrorsummaryHeaderDeatail.add("Reference No.");
            panWithErrorsummaryHeaderDeatail.add("Date Of Birth");
            panWithErrorsummaryHeaderDeatail.add("Column Name");
            panWithErrorsummaryHeaderDeatail.add("Column Value");

            ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();

            ArrayList<ArrayList<String>> errorSummaryList = new ArrayList<ArrayList<String>>();

            String l_error_query = proc_err.excelErrorSummaryQuery15GH(asmt, l_entity_code, l_client_code);
            DbQueryExecutorAsList objBDQueryList = new DbQueryExecutorAsList();
            errorSummaryList = objBDQueryList.execute_oracle_query_as_list(l_error_query);

            ArrayList<ArrayList<String>> panWitherrorSummaryDetailList = new ArrayList<ArrayList<String>>();

            String l_errorDetailQuery = proc_err.excelErrorDetailQuery15GH(asmt, l_entity_code, l_client_code);
            DbQueryExecutorAsList objBDQuery = new DbQueryExecutorAsList();
            panWitherrorSummaryDetailList = objBDQuery.execute_oracle_query_as_list(l_errorDetailQuery);

            String filepath = generateErrorFile(filename, file_dwl_time, summaryHeaderDeatail, errorSummaryList, panWithErrorsummaryHeaderDeatail, panWitherrorSummaryDetailList);
            String real_filename = filename + "_" + file_dwl_time + "." + file_ext;

            setFileName(real_filename);//User File Name

            if (!utl.isnull(filepath)) {
                File obj_dwl_file = new File(filepath);
                if (obj_dwl_file.exists()) {
                    fileInputStream = new FileInputStream(obj_dwl_file);
                    return_view = "downloadsuccess";
                } else {
                    return_view = "downloadfailed";
                }
            }//end if

        } catch (Exception e) {
            return_view = "downloadfailed";
            e.printStackTrace();
        }
        return return_view;
    }//end method

    private String generateErrorFile(String filename, String file_dwl_time, ArrayList<String> summaryHeaderDeatail, ArrayList<ArrayList<String>> errorSummaryList, ArrayList<String> panWithErrorsummaryHeaderDeatail, ArrayList<ArrayList<String>> panWitherrorSummaryDetailList) {
        String l_return_filepath = "";
        try {
            filename = utl.isnull(filename) ? "" : filename;
            String downloadTypeExtension = (String) session.get("EXCELFORMAT");

            filename = filename.replaceAll(" ", "_");
            filename = filename + ".";
            File obj_file = new File(filename);
            //-----filename-----
            String l_filename = obj_file.getName();
            String l_new_filename = l_filename.substring(0, l_filename.indexOf(".")) + "_";

            //--------create new File--------
            File obj_tempfile = File.createTempFile(l_new_filename + file_dwl_time, downloadTypeExtension);
            if (!obj_tempfile.exists()) {
                obj_tempfile.createNewFile();
            }

            l_return_filepath = obj_tempfile.getAbsolutePath();
            FileOutputStream obj_output_file = new FileOutputStream(obj_tempfile, false);

            try {
                writeErrorFile(obj_tempfile, summaryHeaderDeatail, errorSummaryList, panWithErrorsummaryHeaderDeatail, panWitherrorSummaryDetailList);
            } catch (Exception ex) {
                l_return_filepath = "";
            }

        } catch (IOException ex) {
            l_return_filepath = "";
        }
        return l_return_filepath;
    }//end method

    private boolean writeErrorFile(File obj_tempfile, ArrayList<String> summaryHeaderDeatail, ArrayList<ArrayList<String>> errorSummaryList, ArrayList<String> panWithErrorsummaryHeaderDeatail, ArrayList<ArrayList<String>> panWitherrorSummaryDetailList) {
        String l_realpath = obj_tempfile.getAbsolutePath();
        String extension = (String) session.get("EXCELFORMAT");
        try {
            final String REGEXP = "^[0-9]\\d*(\\.[0-9]\\d*)?$";// Regular expression for allowing digits.
            Pattern pattern = Pattern.compile(REGEXP);
            Matcher matcher = null;

            Workbook obj_writableWorkbook = null;
            Sheet obj_writableSheet = null;

            if (extension.endsWith("xlsx")) {
                obj_writableWorkbook = new XSSFWorkbook();
            } else {
                obj_writableWorkbook = new HSSFWorkbook();
            }
            CellStyle cellStyle = obj_writableWorkbook.createCellStyle();

            String title1 = "Error Summary";
            String title2 = "Detail Errors";
            obj_writableSheet = obj_writableWorkbook.createSheet(title1);

            if (summaryHeaderDeatail.size() > 0) {// used to generate heading
                Row row = obj_writableSheet.createRow(0);//
                Cell cell = null;

                for (int j = 0; j < summaryHeaderDeatail.size(); j++) {
                    if (j < (summaryHeaderDeatail.size())) {
                        try {

                            String headerString = utl.isnull(summaryHeaderDeatail.get(j)) ? "" : summaryHeaderDeatail.get(j);

                            cell = row.createCell(j);
                            cell.setCellValue(headerString);
                            cell.setCellStyle(getHeaderCellStyle(obj_writableWorkbook));

                            //Set cell width in CHARS
                            int col = j;
                            int widthInChars = (headerString.length() * 600);

                            obj_writableSheet.setColumnWidth(col, widthInChars);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }//end heading if

            if (errorSummaryList.size() > 0) {// used to generate cell data value for sheet no 1
                try {
                    for (int i = 0; i < errorSummaryList.size(); i++) {//rows
                        Row row1 = obj_writableSheet.createRow(i + 1);
                        Cell cell2 = null;

                        for (int j = 0; j < summaryHeaderDeatail.size(); j++) {//cols
                            String contentString = utl.isnull(errorSummaryList.get(i).get(j)) ? "" : errorSummaryList.get(i).get(j);
                            if (!utl.isnull(contentString)) {
                                cell2 = row1.createCell(j);
                                cell2.setCellValue(contentString);
                                cell2.setCellStyle(getDataCellStyle(obj_writableWorkbook, false));

                                matcher = pattern.matcher(contentString);
                                if (matcher.find() && matcher.group().equals(contentString)) {// formatting text alignment to right for numeric values.                                        
                                    cell2.setCellStyle(getDataCellStyle(obj_writableWorkbook, true));//second param is true for numeric value
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }//end 

            obj_writableSheet = obj_writableWorkbook.createSheet(title2);
            cellStyle = obj_writableWorkbook.createCellStyle();

            if (panWithErrorsummaryHeaderDeatail.size() > 0) {// used to generate heading                
                Row row = obj_writableSheet.createRow(0);
                Cell cell = null;

                for (int j = 0; j < panWithErrorsummaryHeaderDeatail.size(); j++) {
                    if (j < (panWithErrorsummaryHeaderDeatail.size())) {
                        try {
                            String headerString = utl.isnull(panWithErrorsummaryHeaderDeatail.get(j)) ? "" : panWithErrorsummaryHeaderDeatail.get(j);

                            cell = row.createCell(j);
                            cell.setCellValue(headerString);
                            cell.setCellStyle(getHeaderCellStyle(obj_writableWorkbook));

                            //Set cell width in CHARS
                            int col = j;
                            int widthInChars = (headerString.length() * 600);
                            obj_writableSheet.setColumnWidth(col, widthInChars);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }//end heading if

            if (panWitherrorSummaryDetailList.size() > 0) {// used to generate cell data value for sheet no 2
                try {
                    for (int i = 0; i < panWitherrorSummaryDetailList.size(); i++) {//rows
                        Row row1 = obj_writableSheet.createRow(i + 1);
                        Cell cell2 = null;

                        for (int j = 0; j < panWithErrorsummaryHeaderDeatail.size(); j++) {//cols
                            String contentString = utl.isnull(panWitherrorSummaryDetailList.get(i).get(j)) ? "" : panWitherrorSummaryDetailList.get(i).get(j);
                            if (!utl.isnull(contentString)) {
                                cell2 = row1.createCell(j);
                                cell2.setCellValue(contentString);
                                cell2.setCellStyle(getDataCellStyle(obj_writableWorkbook, false));

                                matcher = pattern.matcher(contentString);
                                if (matcher.find() && matcher.group().equals(contentString)) {// formatting text alignment to right for numeric values.                                        
                                    cell2.setCellStyle(getDataCellStyle(obj_writableWorkbook, true));//second param is true for numeric value
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }//end 
            if (obj_writableSheet != null) {
                try (FileOutputStream outputStream = new FileOutputStream(l_realpath)) {
                    obj_writableWorkbook.write(outputStream);
                }
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }//end method

    public CellStyle getHeaderCellStyle(Workbook obj_writableWorkbook) {
        CellStyle headerCellStyle = obj_writableWorkbook.createCellStyle();
        Font headerFont = obj_writableWorkbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setFontName("Times New Roman");
        headerCellStyle.setFont(headerFont);
        
        return headerCellStyle;
    }

    private CellStyle getDataCellStyle(Workbook obj_writableWorkbook, boolean isNumericValue) {
        CellStyle dataCellStyle = obj_writableWorkbook.createCellStyle();
        Font dataFont = obj_writableWorkbook.createFont();
        dataFont.setFontName("Times New Roman");
        dataCellStyle.setFont(dataFont);
        if (isNumericValue) {
            dataCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        }
        return dataCellStyle;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFileSeqNo() {
        return fileSeqNo;
    }

    public void setFileSeqNo(String fileSeqNo) {
        this.fileSeqNo = fileSeqNo;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBckAction() {
        return BckAction;
    }

    public void setBckAction(String BckAction) {
        this.BckAction = BckAction;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public String getErrorTypeCode() {
        return errorTypeCode;
    }

    public void setErrorTypeCode(String errorTypeCode) {
        this.errorTypeCode = errorTypeCode;
    }

}//end class
