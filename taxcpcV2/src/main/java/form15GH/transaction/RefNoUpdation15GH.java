/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.DateTimeUtil;
import globalUtilities.ExcelUtil;
import globalUtilities.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class RefNoUpdation15GH extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;

    private ImportFileAttribs obj_exl_html;
    private InputStream inputStream;
    private String validate;
    private String mx_upd_res;
    private String GenClientCode;

    DateTimeUtil dateTimeUtl;
    ExcelUtil excelUtl;

    public RefNoUpdation15GH() {
        utl = new Util();
        dateTimeUtl = new DateTimeUtil();
        excelUtl = new ExcelUtil();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "success";

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
                    try {
                        FileInputStream fileInputStream = null;
                        try {
//                            utl.generateLog("savedFilePath--" + savedFilePath);
                            fileInputStream = new FileInputStream(new File(savedFilePath));
                            Workbook excelworkbook = excelUtl.getExcelPoiWorkbook(fileInputStream, savedFilePath);

                            ArrayList<ReferenceNoDetailPOJO> refNoListFromExcel = this.getRefNoListFromExcel(excelworkbook);

                            if (refNoListFromExcel != null) {
                                Deductee15GHSupport deductee15GHSupport = new Deductee15GHSupport();
                                return_msg = "success#" + deductee15GHSupport.getDeducteeVerifiyDtl(refNoListFromExcel);
//                                return_view = "uploadSuccess";
                            }
                        } catch (Exception e) {
                            return_msg = "error";
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        return_msg = "error";
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    session.remove("session_upload_count");
                } catch (Exception e) {
                }
                //setMx_upd_res("TBC");
                return_msg = "uploadMaxResult";
                return_view = "uploadMaxResult";
            }
        }

        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view; //To change body of generated methods, choose Tools | Templates.
    }//end method

    private String RenameAndSaveUploadedFile() {
        String filePath = null;
        try {
            String file_name = getObj_exl_html().getTemplateFileName();
//            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + dateTimeUtl.get_sysdate("dd_MM_yyyy_hh_mm_ss") + file_name;
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

    }//end method

    public ArrayList<ReferenceNoDetailPOJO> getRefNoListFromExcel(Workbook excelworkbook) {
        ArrayList<ReferenceNoDetailPOJO> refNoList = null;

        if (excelworkbook != null) {
            Sheet sheet = excelworkbook.getSheetAt(0);// Get the first sheet
            if (sheet != null) {
                refNoList = new ArrayList<>();
                utl.generateLog("No of rows:\t", sheet.getPhysicalNumberOfRows());

                for (int i = 2; i <= sheet.getPhysicalNumberOfRows(); i++) {
                    ReferenceNoDetailPOJO refNoDto = new ReferenceNoDetailPOJO();
                    try {

                        Row row = sheet.getRow(i);
                        if (row != null) {
                            Cell cell = null;

                            cell = row.getCell(1);
                            refNoDto.setClient_code(cell != null ? cell.toString() : "");

                            cell = row.getCell(2);
                            refNoDto.setBank_branch_code(cell != null ? cell.toString() : "");

                            cell = row.getCell(3);
                            refNoDto.setClient_name(cell != null ? cell.toString() : "");

                            cell = row.getCell(4);
                            refNoDto.setTanno(cell != null ? cell.toString() : "");

                            cell = row.getCell(5);
                            refNoDto.setLast_gen_15g(cell != null ? cell.toString() : "");

                            cell = row.getCell(6);
                            refNoDto.setLast_gen_15h(cell != null ? cell.toString() : "");

                            cell = row.getCell(7);
                            refNoDto.setNext_gen_15g(cell != null ? cell.toString() : "");

                            cell = row.getCell(8);
                            refNoDto.setNext_gen_15h(cell != null ? cell.toString() : "");

                            refNoList.add(refNoDto);

                        }
                    } catch (Exception e) {
                    }
                }

            }
        }
        return refNoList;
    }//end method

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

    public DateTimeUtil getDateTimeUtl() {
        return dateTimeUtl;
    }

    public void setDateTimeUtl(DateTimeUtil dateTimeUtl) {
        this.dateTimeUtl = dateTimeUtl;
    }

    public ExcelUtil getExcelUtl() {
        return excelUtl;
    }

    public void setExcelUtl(ExcelUtil excelUtl) {
        this.excelUtl = excelUtl;
    }

}
