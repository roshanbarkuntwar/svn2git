/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbQueryExecutorAsList;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 * @author gaurav.khanzode
 */
public class DownloadDeductee15GHErrorDetails extends ActionSupport implements SessionAware {

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
    private String l_error_type_code;
    private String l_error_type_name;
    private String l_error_name;
    private String l_error_code;
    private String table_name;
    private String ReadonlyFlag;

    private ArrayList<String> listLhssysEngineCols;
    private ArrayList<ArrayList<String>> tempDataRecordList;

    public DownloadDeductee15GHErrorDetails() {
        utl = new Util();
        dateTime = new DateTimeUtil();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        try {
            String l_entity_code = "";
            String l_client_code = "";
            String l_acc_year = "";
            String l_quarter_no = "";
            String l_tds_type_code = "";

            Assessment asmt = null;
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            if (viewClientMast != null) {
                l_entity_code = viewClientMast.getEntity_code();
                l_client_code = viewClientMast.getClient_code();
                asmt = (Assessment) session.get("ASSESSMENT");
                l_acc_year = asmt.getAccYear();
                l_quarter_no = asmt.getQuarterNo();
                l_tds_type_code = asmt.getTdsTypeCode();
            }

            ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();

            String l_error_code = (String) session.get("deducteeErrorCode");
            String l_query = "";

            l_query = proc_err.excelDeducteeErrorDetailQuery15GH(asmt, l_entity_code, l_client_code, l_error_code);

            String file_ext = (String) session.get("EXCELFORMAT");
            String filename = ("Deductee15ghErrorListDetails_").concat(l_quarter_no).concat(l_tds_type_code);
            String file_dwl_time = dateTime.get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");

            DbQueryExecutorAsList objData = new DbQueryExecutorAsList();
            tempDataRecordList = objData.execute_oracle_query_as_list(l_query);

            listLhssysEngineCols = new ArrayList<String>();
            listLhssysEngineCols.add("Sr No.");
            listLhssysEngineCols.add("Deductee Name");
            listLhssysEngineCols.add("Deductee Pan No");
            listLhssysEngineCols.add("Reference No.");
            listLhssysEngineCols.add("Date Of Birth");
            listLhssysEngineCols.add("Column Value");
            listLhssysEngineCols.add("Bank Branch");

            String filepath = generateFile(filename, file_dwl_time, tempDataRecordList, listLhssysEngineCols);
            String real_filename = filename + "_" + file_dwl_time + "." + file_ext;
//            //System.out.println("real_filename.." + real_filename);
            setFileName(real_filename);//User File Name

            if (!utl.isnull(filepath)) {
                File obj_dwl_file = new File(filepath);
                fileInputStream = new FileInputStream(obj_dwl_file);
                l_return_value = "downloadsuccess";
            }//end if

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }//end method

    public String generateFile(String filename, String l_file_dwl_time, ArrayList<ArrayList<String>> tempDataRecordList, ArrayList<String> listLhssysEngineCols) throws FileNotFoundException, IOException {
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
            File obj_tempfile = File.createTempFile(l_new_filename + l_file_dwl_time, downloadTypeExtension);
            if (!obj_tempfile.exists()) {
                obj_tempfile.createNewFile();
            }

            l_return_filepath = obj_tempfile.getAbsolutePath();
            FileOutputStream obj_output_file = new FileOutputStream(obj_tempfile, false);

            try {
                writeFile(obj_tempfile, tempDataRecordList, listLhssysEngineCols);
            } catch (Exception ex) {
                l_return_filepath = "";
            }

        } catch (IOException ex) {
            l_return_filepath = "";
        }
        return l_return_filepath;
    }//end method

    public boolean writeFile(File obj_file, ArrayList<ArrayList<String>> DetailList, ArrayList<String> listLhssysEngineCols) throws FileNotFoundException, IOException {
        String l_realpath = obj_file.getAbsolutePath();
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

            int sheetNumber = 1;

            String title = "EXCEL DATA SHEET ";
            if (DetailList.size() > 0) {
                int totRec = 0;
                int totRecPerPage = 65535;
                double tot = (float) DetailList.size() / totRecPerPage;
                double sheetNumberDbl = Math.ceil(tot);
                sheetNumber = (int) sheetNumberDbl;

                for (int h = 1; h <= sheetNumber; h++) {
                    obj_writableSheet = obj_writableWorkbook.createSheet(title);
                    Row row = obj_writableSheet.createRow(0);

                    if (listLhssysEngineCols.size() > 0) {// used to generate heading                        
                        Cell cell = null;

                        for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                            if (j < (listLhssysEngineCols.size())) {
                                try {

                                    String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                    cell = row.createCell(j);
                                    cell.setCellValue(headerString);
                                    cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
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

                    try {
                        for (int i = totRec; i < (totRec + totRecPerPage); i++) {//rows
                            Row row1 = obj_writableSheet.createRow(i + 1);

                            Cell cell2 = null;
                            for (int j = 0; j < listLhssysEngineCols.size(); j++) {//cols
                                if (j < (listLhssysEngineCols.size())) {
                                    String contentString = utl.isnull(DetailList.get(i).get(j)) ? "" : DetailList.get(i).get(j);
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
                        }
                    } catch (Exception e) {
                        //System.out.println("message outer..." + e.getMessage());
                    }
//                }//end 
                    totRec += totRecPerPage;
                }
            } else {
                obj_writableSheet = obj_writableWorkbook.createSheet(title);
                Row row = obj_writableSheet.createRow(0);
                if (listLhssysEngineCols.size() > 0) {// used to generate heading
                    Cell cell = null;
                    for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                        if (j < (listLhssysEngineCols.size())) {
                            try {
                                String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                cell = row.createCell(j);
                                cell.setCellValue(headerString);
                                cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
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
            }
            if (obj_writableSheet != null) {
                try (FileOutputStream outputStream = new FileOutputStream(l_realpath)) {
                    obj_writableWorkbook.write(outputStream);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//end function

    private CellStyle getHeadingCellStyle(Workbook obj_writableWorkbook) {
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
        dataFont.setFontHeightInPoints((short) 12);
        dataCellStyle.setFont(dataFont);

        if (isNumericValue) {
            dataCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        }
        return dataCellStyle;
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

    public ArrayList<String> getListLhssysEngineCols() {
        return listLhssysEngineCols;
    }

    public void setListLhssysEngineCols(ArrayList<String> listLhssysEngineCols) {
        this.listLhssysEngineCols = listLhssysEngineCols;
    }

    public ArrayList<ArrayList<String>> getTempDataRecordList() {
        return tempDataRecordList;
    }

    public void setTempDataRecordList(ArrayList<ArrayList<String>> tempDataRecordList) {
        this.tempDataRecordList = tempDataRecordList;
    }

    public String getL_error_type_code() {
        return l_error_type_code;
    }

    public void setL_error_type_code(String l_error_type_code) {
        this.l_error_type_code = l_error_type_code;
    }

    public String getL_error_type_name() {
        return l_error_type_name;
    }

    public void setL_error_type_name(String l_error_type_name) {
        this.l_error_type_name = l_error_type_name;
    }

    public String getL_error_name() {
        return l_error_name;
    }

    public void setL_error_name(String l_error_name) {
        this.l_error_name = l_error_name;
    }

    public String getL_error_code() {
        return l_error_code;
    }

    public void setL_error_code(String l_error_code) {
        this.l_error_code = l_error_code;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end
}//end class
