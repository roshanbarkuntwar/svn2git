package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class ChallanDownloadFileAction implements SessionAware {

//    public String downloadChallanFileData() {
//        String returnResult = "downloadSuccess";
//        String savedFilePath = "";
////        int maxCount = 0;
//        File file_path = null;
//        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//        Assessment asmt = (Assessment) session.get("ASSESSMENT");
//        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
//        try {
//            setTempleteCode("TAXCPC01");
//            String fileLocation = "";
//
//            Long count;
//            if (!utl.isnull(getTempleteCode())) {
//                ViewDeducteeTemplateDAO viewDeducteeTemplate = factory.getViewDeducteeTemplateDAO();
//                ViewClientTemplate deducteetemp = viewDeducteeTemplate.getDataAsTempleteCode(getTempleteCode());
//                if (deducteetemp != null) {
//                    fileLocation = deducteetemp.getFile_location();
//                }
//            }
//
//            if (!utl.isnull(fileLocation)) {
//             //   System.out.println("fileLocation---->"+fileLocation);
//                savedFilePath = RenameAndSaveUploadedFile(fileLocation, getTempleteCode(), file_ext);
//                if (!utl.isnull(savedFilePath)) {
//                    //System.out.println("savedFilePath------>"+savedFilePath);
//                    ChallanDB challanDb = new ChallanDB();
////                    String countQuery = challanDb.challanCountRecordQuery();
////                    String query = " where (a.client_code = '" + viewClientMast.getClient_code() + "' OR a.parent_code = '" + viewClientMast.getClient_code() + "' OR\n"
////                            + "               a.g_parent_code = '" + viewClientMast.getClient_code() + "' OR a.sg_parent_code = '" + viewClientMast.getClient_code() + "' OR\n"
////                            + "               a.ssg_parent_code = '" + viewClientMast.getClient_code() + "' OR a.sssg_parent_code = '" + viewClientMast.getClient_code() + "')\n"
////                            + "           and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
////                            + "           and a.acc_year = '" + asmt.getAccYear() + "'\n"
////                            + "           and a.quarter_no = '" + asmt.getQuarterNo() + "') ";
//
//                    //count_record_query = count_record_query.concat(query);
//                  //  countQuery += query;
//                    DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
//                    //count = objDBListCount.execute_oracle_function_as_integar(countQuery);
//                    //  System.out.println("QueryCount--------->"+count);
//                    //int finalCount = checkCount(count);
//                    // System.out.println("finalCount----->"+finalCount);               
////                    String challanQuery = challanDb.challanRecordQuery();
////                    String query1 = " where (a.client_code = '" + viewClientMast.getClient_code() + "' OR a.parent_code = '" + viewClientMast.getClient_code() + "' OR\n"
////                            + "               a.g_parent_code = '" + viewClientMast.getClient_code() + "' OR a.sg_parent_code = '" + viewClientMast.getClient_code() + "' OR\n"
////                            + "               a.ssg_parent_code = '" + viewClientMast.getClient_code() + "' OR a.sssg_parent_code = '" + viewClientMast.getClient_code() + "')\n"
////                            + "           and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
////                            + "           and a.acc_year = '" + asmt.getAccYear() + "'\n"
////                            + "           and a.quarter_no = '" + asmt.getQuarterNo() + "') where slno between 1 and " + finalCount;
//
//                    // record_detail_query = record_detail_query.concat(query1);
//                    //challanQuery += query1;
//
//                    DbFunctionExecutorAsString objChallanData = new DbFunctionExecutorAsString();
////                ArrayList<ArrayList<String>> challanFileData = objChallanData.execute_oracle_query_as_list(l_record_detail_query);
////                //    ArrayList<ArrayList<String>> challanFileData = objChallanData.getHeadingsAndDataAsListOfList(challanQuery);
////                    System.out.println("challanFileData--No of Records--->" + challanFileData.size());
////                    String filePath = generateChallanFile(challanFileData, viewClientMast, asmt , savedFilePath);
//                 
//                    String filename = ("TAXCPC_" + viewClientMast.getTanno() + "_" + asmt.getAccYear() + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode());
//                    String real_filename = filename + file_ext;
//                    if (!utl.isnull(filePath)) {
//                        setFileName(real_filename);
//                        file_path = new File(filePath);
//                    }
//                    fileInputStream = new FileInputStream(file_path);
//                }
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return returnResult;
//    }
    private int checkCount(Long count) {
        int maxCount = 0;
        int maxValue = Integer.valueOf(count.intValue());
        int diff = 0;
        if (maxValue > 1000) {
            diff = maxValue - 1000;
            if (diff > 20) {
                maxCount = 1000;
            } else {
                maxCount = maxValue;
            }
        } else {
            maxCount = maxValue;
        }

        return maxCount;
    }

    private String generateChallanFile(ArrayList<ArrayList<String>> challanFileData, ViewClientMast viewClientMast, Assessment asmt, String savedFilePath) {
        String filePath = "";
        String file_ext = (String) session.get("EXCELFORMAT");

        try {
            String filename = ("TAXCPC_" + viewClientMast.getTanno() + "_" + asmt.getAccYear() + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode());
            String real_filename = filename + file_ext;

            Workbook workbook = new XSSFWorkbook();
            //  Workbook workbook = new XSSFWorkbook(new FileInputStream(savedFilePath));
            Sheet sheet = workbook.createSheet("ChallanRecord");

            CellStyle headingStyle = workbook.createCellStyle();

            Font headingFont = workbook.createFont();
            headingFont.setFontHeightInPoints((short) 12);
            headingFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headingFont.setFontName("Times New Roman");

            headingStyle.setFont(headingFont);
            headingStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headingStyle.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
            headingStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
            headingStyle.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
            headingStyle.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);

            int rowIndex = 0;
            int cellIndex = 0;

            Row headingRow = sheet.createRow(rowIndex);
            Cell headingCell = null;

            ArrayList<String> headingData = challanFileData.get(0);
            for (String head : headingData) {
                headingCell = headingRow.createCell(cellIndex);
                headingCell.setCellValue(head);
                headingCell.setCellStyle(headingStyle);
                cellIndex++;
            }

            rowIndex++;
            CellStyle dataStyle_Left = workbook.createCellStyle();
            CellStyle dataStyle_Center = workbook.createCellStyle();
            CellStyle dataStyle_Right = workbook.createCellStyle();

            Font dataFont = workbook.createFont();
            dataFont.setFontHeightInPoints((short) 10);
            dataFont.setFontName("Times New Roman");

            dataStyle_Left.setFont(dataFont);
            dataStyle_Left.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Left.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Left.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Left.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Left.setAlignment(CellStyle.ALIGN_LEFT);

            dataStyle_Center.setFont(dataFont);
            dataStyle_Center.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Center.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Center.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Center.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Center.setAlignment(CellStyle.ALIGN_CENTER);

            dataStyle_Right.setFont(dataFont);
            dataStyle_Right.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Right.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Right.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Right.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
            dataStyle_Right.setAlignment(CellStyle.ALIGN_RIGHT);
            // Loop for Data
            Row dataRow = null;
            Cell dataCell = null;

            int rowCount = 0;
            for (ArrayList<String> data : challanFileData) {
                if (rowCount > 0) {
                    dataRow = sheet.createRow(rowIndex);
                    cellIndex = 0;
                    //    dataCell.setCellStyle(dataStyle_Center);
                    for (String s : data) {
                        dataCell = dataRow.createCell(cellIndex);
                        dataCell.setCellValue(s);
                        dataCell.setCellStyle(dataStyle_Center);
                        cellIndex++;
                    }
                    rowIndex++;
                }
                rowCount++;
            }

            for (int i = 0; i <= headingData.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream(real_filename);
            workbook.write(outputStream);
            outputStream.close();

            File final_path = new File(real_filename);
            filePath = final_path.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("filePath---In Method-->"+filePath);
        return filePath;
    }

    private String RenameAndSaveUploadedFile(String fileLocation, String tempCode, String file_ext) {
        String filePath;
        try {
            String file_name = tempCode + file_ext;
            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + dateUtil.get_sysdate("dd_MM_yyyy_hh_mm_ss") + file_name;
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

    private ChallanFilterEntity tranChallanFltrSrch;
    private String templeteCode;
    Map<String, Object> session;
    Util utl;
    private String fileName;
    DateTimeUtil dateUtil;
    private InputStream fileInputStream;

    public ChallanDownloadFileAction() {
        dateUtil = new DateTimeUtil();
        utl = new Util();
    }

    public String getTempleteCode() {
        return templeteCode;
    }

    public void setTempleteCode(String templeteCode) {
        this.templeteCode = templeteCode;
    }

    public ChallanFilterEntity getTranChallanFltrSrch() {
        return tranChallanFltrSrch;
    }

    public void setTranChallanFltrSrch(ChallanFilterEntity tranChallanFltrSrch) {
        this.tranChallanFltrSrch = tranChallanFltrSrch;
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
