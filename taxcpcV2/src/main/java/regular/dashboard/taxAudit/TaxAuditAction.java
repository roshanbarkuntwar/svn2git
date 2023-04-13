/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.taxAudit;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class TaxAuditAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnView = "input";
        try {
//            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//            LhssysParametersDAO lhssysParametersDAO;
            TaxAuditDB db = new TaxAuditDB();
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment ass = (Assessment) session.get("ASSESSMENT");
            String staticFolder = "TAX_AUDIT";
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String query = db.firstReportQuery(clientMast, ass);
            String extention = null;
            File f = new File(javaDriveName);
            if (f.exists()) {
                String filePath = javaDriveName + File.separator + staticFolder + File.separator + clientMast.getClient_code() + File.separator + ass.getAccYear() + File.separator + new DateTimeUtil().get_sysdate("ddMMyyyy_HHmmss") + File.separator;
                File path = new File(filePath);
                if (!path.exists()) {
                    path.mkdirs();
                }

                try {
                    DbFunctionExecutorAsString dbFunction = new DbFunctionExecutorAsString();
                    dataRecordList = dbFunction.execute_oracle_query_as_list(query);
                } catch (Exception e) {

                }
                try {
                    DbFunctionExecutorAsString dbFunction1 = new DbFunctionExecutorAsString();
//                    secondDataRecordList = dbFunction1.execute_oracle_query_as_list(query);
                    secondDataRecordList = dbFunction1.execute_oracle_query_as_list(db.secondReportQuery(clientMast, ass));
                } catch (Exception e) {

                }
                try {

                    DbFunctionExecutorAsString dbFunction2 = new DbFunctionExecutorAsString();
//                    thirdDataRecordList = dbFunction2.execute_oracle_query_as_list(query);
                    thirdDataRecordList = dbFunction2.execute_oracle_query_as_list(db.thirdReportQuery(clientMast, ass));
                } catch (Exception e) {

                }

                try {
//                    lhssysParametersDAO = factory.getLhssysParametersDAO();
//                    LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("EXCEL_FORMAT", "");
//                    if (readExternalDriveName != null) {
                    extention = (String) session.get("EXCELFORMAT");
//                    }
                } catch (Exception e) {

                }

                if (dataRecordList != null && dataRecordList.size() > 0) {

                    String excelFileName = "TAX_AUDIT" + extention;
                    String filepath = generateFile(filePath + excelFileName, "", dataRecordList, extention);

                    if (!utl.isnull(filepath)) {
                        TaxAuditSupport sb = new TaxAuditSupport();
                        String copyFiles = sb.copyFiles(filePath, clientMast, ass);
                        if (!utl.isnull(copyFiles)) {
                            File obj_dwl_file = new File(copyFiles);
                            setFileName(obj_dwl_file.getName());
                            if (obj_dwl_file.exists()) {
                                fileInputStream = new FileInputStream(obj_dwl_file);
                                returnView = "downloadsuccess";
                            } else {
                                returnView = "input";
                                setErrorMessage("Some Error Occured, Could Not Download File");
                            }
                        }
                    }//end if
                } else {
                    setErrorMessage("Records Not Found !");

                }
            } else {

                setErrorMessage("Please Configure Java Drive !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnView;
    }

    public String generateFile(String filename, String l_file_dwl_time, ArrayList<ArrayList<String>> tempDataRecordList, String extension) throws FileNotFoundException, IOException {
        String l_return_filepath = "";
        File obj_file = new File(filename);
        l_return_filepath = obj_file.getAbsolutePath();
        try {
            boolean writeFile = writeFile(obj_file, tempDataRecordList, new TaxAuditDB().getSheetsHeading("1"), extension);

        } catch (Exception ex) {
            l_return_filepath = "";
        }
        return l_return_filepath;
    }//end method

    private boolean writeFile(File obj_file, ArrayList<ArrayList<String>> DetailList, ArrayList<String> listLhssysEngineCols, String extension) throws FileNotFoundException, IOException {
        String l_realpath = obj_file.getAbsolutePath();
        Workbook obj_writableWorkbook = null;
        Sheet obj_writableSheet = null;
        int sheetNumber = 1;
        try {
            if (extension.endsWith("xlsx")) {

                obj_writableWorkbook = new XSSFWorkbook();
            } else {
                obj_writableWorkbook = new HSSFWorkbook();

            }
            //remove as no use 
//            obj_writableSheet = obj_writableWorkbook.createSheet("NOTES");

            int totRec = 0;
            int totRecPerPage = 65535;
            double tot = (float) DetailList.size() / totRecPerPage;
            double sheetNumberDbl = Math.ceil(tot);
            sheetNumber = (int) sheetNumberDbl;

            String title = "TAX AUDIT SUMMARY ";
            if (DetailList.size() > 0) {

                for (int h = 1; h <= sheetNumber; h++) {
                    obj_writableSheet = obj_writableWorkbook.createSheet(title);
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                    Row row = obj_writableSheet.createRow(0);
//
//                    WritableCellFormat contentCellFormat = null;
                    if (listLhssysEngineCols.size() > 0) {// used to generate heading
                        int i = 0;
                        int columnCount = 0;

                        Cell cell = null;

                        for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                            if (j < (listLhssysEngineCols.size())) {
                                try {

                                    String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                    cell = row.createCell(j);
                                    cell.setCellValue(headerString);
                                    cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));

//                                    //Set cell width in CHARS
                                    int col = j;
                                    int widthInChars = (headerString.length() * 600);

                                    obj_writableSheet.setColumnWidth(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }//end heading if

                    try {
                        int index = 0;
                        for (int i = totRec; i < (totRec + totRecPerPage); i++) {//rows

                            Row row1 = obj_writableSheet.createRow(i + 1);

                            Cell cell2 = null;
                            for (int j = 0; j < listLhssysEngineCols.size(); j++) {//cols
                                if (j < (listLhssysEngineCols.size())) {
                                    String contentString = utl.isnull(DetailList.get(i).get(j)) ? "" : DetailList.get(i).get(j);
                                    if (!utl.isnull(contentString)) {
                                        cell2 = row1.createCell(j);
                                        cell2.setCellValue(contentString);

                                    }
                                }
                            }
                            index++;
                        }
                    } catch (Exception e) {
                        //System.out.println("message outer..." + e.getMessage());
                    }
//                }//end 
                    totRec += totRecPerPage;
                }
            } else {
                obj_writableSheet = obj_writableWorkbook.createSheet(title);
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                Row row = obj_writableSheet.createRow(0);
//
//                    WritableCellFormat contentCellFormat = null;
                if (listLhssysEngineCols.size() > 0) {// used to generate heading

                    Cell cell = null;
                    for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                        if (j < (listLhssysEngineCols.size())) {
                            try {

                                String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                cell = row.createCell(j);
                                cell.setCellValue(headerString);
                                cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
//                                    //Set cell width in CHARS
                                int col = j;
                                int widthInChars = (headerString.length() * 600);
                                obj_writableSheet.setColumnWidth(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }//end heading if

            }

            writeSecondSheet(new TaxAuditDB().getSheetsHeading("2"), sheetNumber, obj_writableWorkbook, obj_writableSheet);
            if (obj_writableSheet != null) {

                try (FileOutputStream outputStream = new FileOutputStream(l_realpath)) {

                    obj_writableWorkbook.write(outputStream);

//                    workbook.close();
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

    private boolean writeSecondSheet(ArrayList<String> listLhssysEngineCols, int lastSheetNo, Workbook obj_writableWorkbook, Sheet obj_writableSheet) throws FileNotFoundException, IOException {


        try {
            ArrayList<ArrayList<String>> DetailList = secondDataRecordList;

            int totRec = 0;
            int totRecPerPage = 65535;
            double tot = (float) DetailList.size() / totRecPerPage;
            double sheetNumberDbl = Math.ceil(tot);
            int sheetNumber = (int) sheetNumberDbl + lastSheetNo + 1;
//            System.out.println("sheetNumber--"+sheetNumber);

            String title = "REPORTED 94A";

            if (DetailList.size() > 0) {
                int tempCount = 0;
                for (int h = lastSheetNo + 1; h < sheetNumber; h++) {

                    obj_writableSheet = obj_writableWorkbook.createSheet(title + "_" + tempCount);
                    tempCount++;
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                    Row row = obj_writableSheet.createRow(0);
//

//                    WritableCellFormat contentCellFormat = null;
                    if (listLhssysEngineCols.size() > 0) {// used to generate heading
                        int i = 0;
                        int columnCount = 0;

                        Cell cell = null;
                        for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                            if (j < (listLhssysEngineCols.size())) {
                                try {

                                    String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                    cell = row.createCell(j);
                                    cell.setCellValue(headerString);
                                    cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
//                                    //Set cell width in CHARS
//                                    int col = j;
//                                    int widthInChars = (headerString.length() * 3);
//                                    obj_writableSheet.setColumnView(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }//end heading if

                    try {
                        int index = 0;
                        for (int i = totRec; i < (totRec + totRecPerPage); i++) {//rows

                            Row row1 = obj_writableSheet.createRow(i + 1);

                            Cell cell1 = null;
                            for (int j = 0; j < listLhssysEngineCols.size(); j++) {//cols
                                if (j < (listLhssysEngineCols.size())) {
                                    String contentString = utl.isnull(DetailList.get(i).get(j)) ? "" : DetailList.get(i).get(j);
                                    if (!utl.isnull(contentString)) {
                                        cell1 = row1.createCell(j);
                                        cell1.setCellValue(contentString);
//
                                    }
                                }
                            }
                            index++;
                        }
                    } catch (Exception e) {
                        //System.out.println("message outer..." + e.getMessage());
                    }
//                }//end 
                    totRec += totRecPerPage;
                }
            } else {
                obj_writableSheet = obj_writableWorkbook.createSheet(title);
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                Row row = obj_writableSheet.createRow(0);
//
//                    WritableCellFormat contentCellFormat = null;
                if (listLhssysEngineCols.size() > 0) {// used to generate heading

                    Cell cell = null;
                    for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                        if (j < (listLhssysEngineCols.size())) {
                            try {

                                String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                cell = row.createCell(j);
                                cell.setCellValue(headerString);
                                cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
//                                    //Set cell width in CHARS
                                int col = j;
                                int widthInChars = (headerString.length() * 600);
                                obj_writableSheet.setColumnWidth(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }//end heading if

            }
            writeThirdSheet(new TaxAuditDB().getSheetsHeading("3"), sheetNumber, obj_writableWorkbook, obj_writableSheet);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return true;

    }//end function

    private boolean writeThirdSheet(ArrayList<String> listLhssysEngineCols, int lastSheetNo, Workbook obj_writableWorkbook, Sheet obj_writableSheet) throws FileNotFoundException, IOException {


        try {
            ArrayList<ArrayList<String>> DetailList = thirdDataRecordList;

            int totRec = 0;
            int totRecPerPage = 65535;
            double tot = (float) DetailList.size() / totRecPerPage;
            double sheetNumberDbl = Math.ceil(tot);
            int sheetNumber = (int) sheetNumberDbl + lastSheetNo + 1;
            String title = "NOT REPORTABLE INT";

            if (DetailList.size() > 0) {
                int tempCount = 0;
                for (int h = lastSheetNo + 1; h < sheetNumber; h++) {
                    obj_writableSheet = obj_writableWorkbook.createSheet(title + "_" + tempCount);
                    tempCount++;
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                    Row row = obj_writableSheet.createRow(0);
//
//                    WritableCellFormat contentCellFormat = null;
                    if (listLhssysEngineCols.size() > 0) {// used to generate heading

                        Cell cell = null;

                        for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                            if (j < (listLhssysEngineCols.size())) {
                                try {

                                    String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                    cell = row.createCell(j);
                                    cell.setCellValue(headerString);
                                    cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
//                                    //Set cell width in CHARS
                                    int col = j;
                                    int widthInChars = (headerString.length() * 600);
                                    obj_writableSheet.setColumnWidth(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }//end heading if

                    try {
                        int index = 0;
                        for (int i = totRec; i < (totRec + totRecPerPage); i++) {//rows

                            Row row1 = obj_writableSheet.createRow(i + 1);
                            int columnCount = 0;
                            Cell cell1 = null;
                            for (int j = 0; j < listLhssysEngineCols.size(); j++) {//cols
                                if (j < (listLhssysEngineCols.size())) {
                                    String contentString = utl.isnull(DetailList.get(i).get(j)) ? "" : DetailList.get(i).get(j);
                                    if (!utl.isnull(contentString)) {
                                        cell1 = row1.createCell(j);
                                        cell1.setCellValue(contentString);
//                                        contentCellFormat = getContentCellFormat(contentString);
//                                        try {
//
//                                            Label l_label = new Label(j, (index + 1), contentString, contentCellFormat);
//                                            obj_writableSheet.addCell(l_label);
//                                        } catch (Exception e) {
//
//                                        }
                                    }
                                }
                            }
                            index++;
                        }
                    } catch (Exception e) {
                        //System.out.println("message outer..." + e.getMessage());
                    }
//                }//end 
                    totRec += totRecPerPage;
                }
            } else {
                obj_writableSheet = obj_writableWorkbook.createSheet(title);
//                    WritableCellFormat headingCellFormat = getHeadingCellFormat();
                Row row = obj_writableSheet.createRow(0);
//
//                    WritableCellFormat contentCellFormat = null;
                if (listLhssysEngineCols.size() > 0) {// used to generate heading

                    Cell cell = null;
                    for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                        if (j < (listLhssysEngineCols.size())) {
                            try {

                                String headerString = utl.isnull(listLhssysEngineCols.get(j)) ? "" : listLhssysEngineCols.get(j);

                                cell = row.createCell(j);
                                cell.setCellValue(headerString);
                                cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));
//                                    //Set cell width in CHARS
                                int col = j;
                                int widthInChars = (headerString.length() * 600);
                                obj_writableSheet.setColumnWidth(col, widthInChars);
//                                    // End Set cell width in CHARS
//                                    Label l_label = new Label(j, i, headerString, headingCellFormat);
//                                    obj_writableSheet.addCell(l_label);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }//end heading if

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return true;

    }//end function
//

    private CellStyle getHeadingCellStyle(Workbook obj_writableWorkbook) {
        CellStyle headerCellStyle = obj_writableWorkbook.createCellStyle();
        Font headerFont = obj_writableWorkbook.createFont();
        headerFont.setBoldweight((short) 40);
        headerFont.setFontHeightInPoints((short) 15);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
    }

    Util utl;

    public TaxAuditAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    private String action;
    private String errorMessage;
    private ArrayList<String> headingCols;
    private ArrayList<ArrayList<String>> dataRecordList;
    private ArrayList<ArrayList<String>> secondDataRecordList;
    private ArrayList<ArrayList<String>> thirdDataRecordList;
    private String fileName;
    private InputStream fileInputStream;

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

    public ArrayList<String> getHeadingCols() {
        return headingCols;
    }

    public void setHeadingCols(ArrayList<String> headingCols) {
        this.headingCols = headingCols;
    }

    public ArrayList<ArrayList<String>> getDataRecordList() {
        return dataRecordList;
    }

    public void setDataRecordList(ArrayList<ArrayList<String>> dataRecordList) {
        this.dataRecordList = dataRecordList;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
