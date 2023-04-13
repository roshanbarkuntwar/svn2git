/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author gaurav.khanzode
 */
public class WriteExcelFileUtil {

    Util utl;

    public WriteExcelFileUtil(Util utl) {
        this.utl = utl;
    }//end constructior

    public boolean writeFile(File obj_file, ArrayList<List<String>> DetailList,
            ArrayList<String> listLhssysEngineCols, String extension) throws FileNotFoundException, IOException {

        String l_realpath = obj_file.getAbsolutePath();
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
    }//end main method

    private CellStyle getHeadingCellStyle(Workbook obj_writableWorkbook) {
        CellStyle headerCellStyle = obj_writableWorkbook.createCellStyle();
        Font headerFont = obj_writableWorkbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setFontName("Times New Roman");
        headerCellStyle.setFont(headerFont);

        return headerCellStyle;
    }//end method

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
    }//end method
}
