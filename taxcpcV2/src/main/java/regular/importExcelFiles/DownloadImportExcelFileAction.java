/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
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
import javax.servlet.http.HttpServletRequest;
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
 * @author akash.dev
 */
public class DownloadImportExcelFileAction extends ActionSupport implements SessionAware {

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
    private String templateCode;
    private Long importSeqNo;

    private ArrayList<String> listLhssysEngineCols;
    private ArrayList<ArrayList<String>> tempDataRecordList;

    public DownloadImportExcelFileAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";

        try {

            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//            String TdsTypeCode = (String) session.get("IMPORTMASTERTDSTYPE");
//            String AccYear = (String) session.get("IMPORTMASTERACCYEAR");

            String TempleteCode = getTemplateCode();
            String l_column_query = "";
//            String QuarterNo = (String) session.get("IMPORTMASTERQUARTERNO");

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
            Long l_import_seq;
            Object importSeqNo = session.get("IMPORTSEQNO");
            try {
                l_import_seq = (Long) importSeqNo;
            } catch (Exception e) {
                l_import_seq = 0l;
            }
            String file_ext = null;
//            LhssysParametersDAO lhssysParametersDAO;
            try {
//                lhssysParametersDAO = factory.getLhssysParametersDAO();
//                LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("EXCEL_FORMAT", "");
//                if (readExternalDriveName != null) {
                file_ext = (String) session.get("EXCELFORMAT");
//                }
            } catch (Exception e) {

            }
            String filename = ("ImportExcelDetail_").concat(asmt.getQuarterNo()).concat(getImportSeqNo() + "");
//            String filename = ("ImportExcelDetail_").concat(QuarterNo).concat(loginSeqno);
            String file_dwl_time = new DateTimeUtil().get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");
            System.out.println("TempleteCode--"+TempleteCode);
            try {// used to create list of heading
                if (TempleteCode.equalsIgnoreCase("24QBV1") || TempleteCode.equalsIgnoreCase("24QBV1A")) {
                    l_column_query
                            = "select t.disp_column from lhssys_engine_cols t where t.engine_name = '" + TempleteCode + "' order by nvl(t.grp2_seq,t.grp1_seq)";
                } else {
                    l_column_query
                            = "select t.disp_column from lhssys_engine_cols t where t.engine_name = '" + TempleteCode + "' order by t.grp1_seq";
                }
                GetMasterExcelDataListThroughWorkbook objworkbook = new GetMasterExcelDataListThroughWorkbook();
                listLhssysEngineCols = objworkbook.getEngineColDataAsList(l_column_query, utl);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String whereClause = "";
            if (!utl.isnull(getErrorTypeCode()) && !getErrorTypeCode().equalsIgnoreCase("AE")) {

                whereClause = " and b.error_code_str ='" + getErrorTypeCode() + "'";

            }//end if

            String l_query
                    = "select t.*\n"
                    + "                  from lhssys_template t\n"
                    + "                 where t.entity_code = '" + l_entity_code + "'\n"
                    + "                   and t.client_code = '" + l_client_code + "'\n"
                    + "                   and t.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "                   and t.quarter_no = " + asmt.getQuarterNo() + " and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "                   and t.process_seqno = '" + getImportSeqNo() + "'\n"
                    + "                   and exists\n"
                    + "                 (select 1\n"
                    + "                          from view_lhssys_template_error b\n"
                    + "                         where b.process_seqno = '" + getImportSeqNo() + "' and b.lhssys_template_rowid_seq = t.rowid_seq  " + whereClause + " )\n"
                    + "                 order by to_number(t.rowid_seq) ";
            GetMasterExcelDataListThroughWorkbook objdataworkbook = new GetMasterExcelDataListThroughWorkbook();
            tempDataRecordList = objdataworkbook.getTempDataErrorDetail(l_query, utl);
            System.out.println("query--"+l_query);
            String filepath = generateErrorFile(filename, file_dwl_time, tempDataRecordList, listLhssysEngineCols, file_ext);
            String real_filename = filename + "_" + file_dwl_time + "." + file_ext;
            setFileName(real_filename);//User File Name

            if (!utl.isnull(filepath)) {
                File obj_dwl_file = new File(filepath);
                if (obj_dwl_file.exists()) {
                    fileInputStream = new FileInputStream(obj_dwl_file);
                    l_return_value = "downloadsuccess";
                } else {
                    l_return_value = "input";
                    session.put("ERRORCLASS", ErrorType.errorMessage);
                    session.put("session_result", "Some Error Occured, Could Not Download File");
                }
            }//end if

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }//end method

    public String generateErrorFile(String filename, String l_file_dwl_time, ArrayList<ArrayList<String>> tempDataRecordList, ArrayList<String> listLhssysEngineCols, String file_ext) throws FileNotFoundException, IOException {
        String l_return_filepath = "";
        try {
            filename = utl.isnull(filename) ? "" : filename;
            String downloadTypeExtension = file_ext;

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
                writeErrorFile(obj_tempfile, tempDataRecordList, listLhssysEngineCols);
            } catch (Exception ex) {
                l_return_filepath = "";
            }

        } catch (IOException ex) {
            l_return_filepath = "";
        }
        return l_return_filepath;
    }//end method

    private boolean writeErrorFile(File obj_file, ArrayList<ArrayList<String>> errorDetailList, ArrayList<String> listLhssysEngineCols) throws FileNotFoundException, IOException {
        String l_realpath = obj_file.getAbsolutePath();
        try {
            File obj_fos = new File(l_realpath);
            Workbook obj_writableWorkbook = null;
            Sheet obj_writableSheet = null;
            int sheetNumber = 0;
            if (l_realpath.endsWith("xlsx")) {

                obj_writableWorkbook = new XSSFWorkbook();
            } else {
                obj_writableWorkbook = new HSSFWorkbook();

            }
            String title = "EXCEL DATA SHEET";
            obj_writableSheet = obj_writableWorkbook.createSheet(title);

            if (listLhssysEngineCols.size() > 0) {// used to generate heading
                int i = 0;
                Row row = obj_writableSheet.createRow(0);
                Cell cell = null;
                for (int j = 0; j < listLhssysEngineCols.size(); j++) {
                    if (j < (listLhssysEngineCols.size() - 9)) {
                        try {
                            String headerString = utl.isnull(listLhssysEngineCols.get(j + 9)) ? "" : listLhssysEngineCols.get(j + 9);

                            cell = row.createCell(j);
                            cell.setCellValue(headerString);
                            cell.setCellStyle(getHeadingCellStyle(obj_writableWorkbook));

                            //Set cell width in CHARS
                            int col = j;
                            int widthInChars = (headerString.length() * 600);
                            obj_writableSheet.setColumnWidth(col, widthInChars);
                            // End Set cell width in CHARS

//                            Label l_label = new Label(j, i, headerString, headingCellFormat);
//                            obj_writableSheet.addCell(l_label);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }//end heading if

            if (errorDetailList.size() > 0) {// used to generate cell data value
                try {
                    for (int i = 0; i < errorDetailList.size(); i++) {//rows
                        Cell cell1 = null;
                        Row row1 = obj_writableSheet.createRow(i + 1);
                        for (int j = 0; j < listLhssysEngineCols.size(); j++) {//cols

                            if (j < (listLhssysEngineCols.size() - 9)) {
                                String contentString = utl.isnull(errorDetailList.get(i).get(j + 9)) ? "" : errorDetailList.get(i).get(j + 9);
                                if (!utl.isnull(contentString)) {
                                    try {
                                        cell1 = row1.createCell(j);
                                        cell1.setCellValue(contentString);
//                                        //System.out.println("contentString..." + contentString);
//                                        contentCellFormat = getContentCellFormat(contentString);
//                                        Label l_label = new Label(j, (i + 1), contentString, contentCellFormat);
//                                        obj_writableSheet.addCell(l_label);
                                    } catch (Exception e) {
                                        //System.out.println("message inner..." + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    //System.out.println("message outer..." + e.getMessage());
                }
            }//end
            if (obj_writableSheet != null) {
                try (FileOutputStream outputStream = new FileOutputStream(l_realpath)) {

                    obj_writableWorkbook.write(outputStream);

//                    workbook.close();
                }

                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }//end function

    private CellStyle getHeadingCellStyle(Workbook obj_writableWorkbook) {
        CellStyle headerCellStyle = obj_writableWorkbook.createCellStyle();
        Font headerFont = obj_writableWorkbook.createFont();
        headerFont.setBoldweight((short) 40);
        headerFont.setFontHeightInPoints((short) 15);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

}//end class
