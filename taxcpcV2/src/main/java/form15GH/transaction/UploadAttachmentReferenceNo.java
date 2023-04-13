/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.DateTimeUtil;
import globalUtilities.ExcelUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class UploadAttachmentReferenceNo extends ActionSupport implements SessionAware {

    public UploadAttachmentReferenceNo() {
        utl = new Util();
        dateTimeUtl = new DateTimeUtil();
        excelUtl = new ExcelUtil();
    }

    @Override
    public String execute() throws Exception {
        String return_result = "success";
        String return_value = "fail";

        if (!utl.isnull(getValidate()) && getValidate().equals("true")) {
            int upload_count = 0;
            try {
                upload_count = (Integer) session.get("session_upload_15GH_ref_count");
            } catch (Exception e) {
                upload_count = 1;
            }
            if (upload_count < 5) {
                upload_count = upload_count + 1;
                session.put("session_upload_15GH_ref_count", upload_count);

                String savedFilePath = RenameAndSaveUploadedFile();

                if (!utl.isnull(savedFilePath)) {
                    String l_quarter_no = "";
                    String l_valide_file_code = "TAXCPC1.0013";
                    int l_strt_col = 0;
                    int l_end_col = 0;
                    try {
                        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure

                        Assessment asmt = (Assessment) session.get("ASSESSMENT");
                        String tdsTypeCode = asmt.getTdsTypeCode();
                        String acc_year = asmt.getAccYear();
                        Date s_from_date = asmt.getQuarterFromDate();
                        Date s_to_date = asmt.getQuarterToDate();
                        String quarterNo = asmt.getQuarterNo();

                        String SheetHiddenFiledValue = "";
                        FileInputStream inputStream = null;
                        try {
                            inputStream = new FileInputStream(new File(savedFilePath));
                            Workbook excelworkbook = excelUtl.getExcelPoiWorkbook(inputStream, savedFilePath);

                            if (excelworkbook != null) {
                                Sheet sheet = excelworkbook.getSheetAt(0);// Get the first sheet
                                Row row = sheet.getRow(0);
                                Cell cell = row.getCell(0);
                                if (cell != null) {
                                    try {
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING:
                                                SheetHiddenFiledValue = cell.getStringCellValue();
                                                break;
                                            case Cell.CELL_TYPE_FORMULA:
                                                SheetHiddenFiledValue = cell.getCellFormula();
                                                break;
                                            case Cell.CELL_TYPE_NUMERIC:
                                                if (DateUtil.isCellDateFormatted(cell)) {
                                                    SheetHiddenFiledValue = cell.getDateCellValue().toString();
                                                } else {
                                                    SheetHiddenFiledValue = Double.toString(cell.getNumericCellValue());
                                                }
                                                break;
                                            case Cell.CELL_TYPE_BLANK:
                                                SheetHiddenFiledValue = "";
                                                break;
                                            case Cell.CELL_TYPE_BOOLEAN:
                                                SheetHiddenFiledValue = Boolean.toString(cell.getBooleanCellValue());
                                                break;
                                        }//end switch
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }

                        String templateTypeFlag = "Deductee15GHRefNo";
                        l_strt_col = 2;
                        l_end_col = 9;

                        if (!utl.isnull(l_valide_file_code) && !utl.isnull(SheetHiddenFiledValue) && l_valide_file_code.equalsIgnoreCase(SheetHiddenFiledValue)) {

                            String entity_code = viewClientMast.getEntity_code();

                            ArrayList<String> bankBranchCodeList = getBankBranchCodeList(entity_code, acc_year, l_quarter_no, tdsTypeCode, getGenClientCode());

                            ProcessUploadFileReferenceNo prccFileReferenceNo = new ProcessUploadFileReferenceNo(this.utl);
                            int savedResult = prccFileReferenceNo.saveImportMastUploadedTemplate(savedFilePath, getObj_exl_html().getReadLineNo(), viewClientMast, asmt, getObj_exl_html().getTemplateFileName(), templateTypeFlag, l_strt_col, l_end_col, l_valide_file_code, acc_year, l_quarter_no, s_from_date, s_to_date, tdsTypeCode, session, getGenClientCode(), bankBranchCodeList);
                            if (savedResult > 0) {
                                return_value = "uploadSuccess";
                            } else {
                                return_value = "Could not upload file, please try again";
                            }
                        } else {
                            return_value = "Please Insert a valid file";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return_value = "Could not upload file, please try again";
                }
            } else {
                try {
                    session.remove("session_upload_count");
                } catch (Exception e) {
                }
                //setMx_upd_res("TBC");
                return_value = "uploadMaxResult";
            }
        } else {
            return_value = "Could not upload file, please try again";

        }

        return return_result; //To change body of generated methods, choose Tools | Templates.
    }

    private ArrayList<String> getBankBranchCodeList(String entity_code, String acc_year, String l_quarter_no, String tdsTypeCode, String clientCode) {
        ArrayList<String> list = new ArrayList<String>();
        try {

            Form15GHDB form15GHDB = new Form15GHDB();
            String l_refno_dtl_query = form15GHDB.show15GHReferanceNoDetail(entity_code, acc_year, l_quarter_no, tdsTypeCode, clientCode,null,0,null,clientCode);
            utl.generateSpecialLog("15GH- (Upload Attachment RefranceNo)--130--", l_refno_dtl_query);
            GetDeductee15GHDataListThroughWorkbook workbookObj = new GetDeductee15GHDataListThroughWorkbook();
            ArrayList<ReferenceNoDetailPOJO> refNoList = workbookObj.getDeducteeRefranceNoDetailsList(l_refno_dtl_query, utl);
            if (refNoList != null) {
                for (ReferenceNoDetailPOJO obj : refNoList) {
                    list.add(obj.getBank_branch_code());
                }//End For
            }
        } catch (Exception e) {
        }
        return list;
    }

    private String RenameAndSaveUploadedFile() {
        String filePath = null;
        try {
            String file_name = getObj_exl_html().getTemplateFileName();
            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + dateTimeUtl.get_sysdate("dd_MM_yyyy_hh_mm_ss") + file_name;
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String destPath = javaDriveName + "/UPLOADED_EXCEL/";

            File destFile = new File(destPath, file_name);
            FileUtils.copyFile(getObj_exl_html().getTemplate(), destFile);
            filePath = destFile.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            filePath = null;
        }
        return filePath;

    }

    private Map<String, Object> session;
    private final Util utl;
    private ImportFileAttribs obj_exl_html;
    private InputStream inputStream;
    private String validate;
    private String mx_upd_res;
    private String GenClientCode;
    DateTimeUtil dateTimeUtl;
    ExcelUtil excelUtl;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public ImportFileAttribs getObj_exl_html() {
        return obj_exl_html;
    }

    public void setObj_exl_html(ImportFileAttribs obj_exl_html) {
        this.obj_exl_html = obj_exl_html;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getMx_upd_res() {
        return mx_upd_res;
    }

    public void setMx_upd_res(String mx_upd_res) {
        this.mx_upd_res = mx_upd_res;
    }

    public String getGenClientCode() {
        return GenClientCode;
    }

    public void setGenClientCode(String GenClientCode) {
        this.GenClientCode = GenClientCode;
    }

}
