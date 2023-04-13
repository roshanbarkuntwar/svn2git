/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author akash.dev
 */
public class ExcelUtil {

    private static List<List<HSSFCell>> cellGrid;

    public Workbook getExcelPoiWorkbook(FileInputStream inputStream, String excelFilePath) {
        Workbook workbook = null;
        try {
            if (excelFilePath.endsWith("xlsx")) {
//                workbook = new XSSFWorkbook(inputStream);
                workbook = WorkbookFactory.create(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
//                workbook = new HSSFWorkbook(inputStream);
                workbook = WorkbookFactory.create(inputStream);
            }

        } catch (Exception e) {
            workbook = null;
            e.printStackTrace();
        }
        return workbook;
    }//end method

    public static void convertExcelToCsv(File inputFile, File outputFile) throws IOException {
        try {
            cellGrid = new ArrayList<List<HSSFCell>>();
            FileInputStream myInput = new FileInputStream(inputFile);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<?> rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<?> cellIter = myRow.cellIterator();
                List<HSSFCell> cellRowList = new ArrayList<HSSFCell>();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellRowList.add(myCell);
                }
                cellGrid.add(cellRowList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = new FileOutputStream(outputFile);
        PrintStream stream = new PrintStream(fos);
        for (int i = 0; i < cellGrid.size(); i++) {
            List<HSSFCell> cellRowList = cellGrid.get(i);
            for (int j = 0; j < cellRowList.size(); j++) {
                HSSFCell myCell = cellRowList.get(j);
                String stringCellValue = myCell.toString();
                stream.print(stringCellValue + ",");
            }
            stream.println("");
        }
    }

    public static void xlsTocsv(File inputFile, File outputFile) {
        StringBuilder data = new StringBuilder();
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            // Get the workbook object for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);//we have only one shhet to read otherwise we create a loop for it
            Cell cell;
            Row row;
            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell).append(",");
                    }

                    data.append('\n');
                }
            }

            fos.write(data.toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    static void xlsxTocsv(File inputFile, File outputFile) {
        StringBuilder cellValue = new StringBuilder();
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);

            // Get the workbook instance for XLSX file
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));

            // Get first sheet from the workbook
            XSSFSheet sheet = wb.getSheetAt(0);

            Row row;
            Cell cell;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();

                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_BOOLEAN:
                            cellValue.append(cell.getBooleanCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            cellValue.append(cell.getNumericCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            cellValue.append(cell.getStringCellValue()).append(",");
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            cellValue.append("" + ",");
                            break;

                        default:
                            cellValue.append(cell).append(",");

                    }
                }
            }

            fos.write(cellValue.toString().getBytes());
            fos.close();

        } catch (IOException e) {
        }
    }

    public static String csvToExcel(File inputFile, File outputFile) {
        String bulkExcelPath = "";
        try {
            if (inputFile.isFile()) {
                try (FileReader file_Reader = new FileReader(inputFile);
                        FileOutputStream fout = new FileOutputStream(outputFile);) {

                    Workbook workbook = null;
                    if (outputFile.getName().endsWith("xlsx")) {
                        workbook = new XSSFWorkbook();
                    } else if (outputFile.getName().endsWith("xls")) {
                        workbook = new HSSFWorkbook();
                    }
                    if (workbook != null) {
                        Sheet exSheet = workbook.createSheet();

                        BufferedReader br = new BufferedReader(file_Reader);
                        String line;

                        int rowCount = 0;
                        while ((line = br.readLine()) != null) {
                            Row row = exSheet.createRow(rowCount++);
                            String[] line_str = line.split("\\^");
                            if (line_str != null) {
                                for (int i = 0; i < line_str.length; i++) {
                                    Cell cell = row.createCell(i);
                                    cell.setCellValue(line_str[i]);
                                    exSheet.autoSizeColumn(i);
                                }
                            }
                        }
                        workbook.write(fout);
                        bulkExcelPath = outputFile.getAbsolutePath();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bulkExcelPath;
    }//end method
}//end class
