/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

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
 * @author gaurav.khanzode
 */
public class DynamicFileUploadDownloadErrorDetailsAction extends ActionSupport implements SessionAware {

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
    private String sessionResult;
    //for  errors

    private String processCnfChkBx;
    private String errorTypeCode;

    public DynamicFileUploadDownloadErrorDetailsAction() {
        utl = new Util();
        dateTime = new DateTimeUtil();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        try {
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
            summaryHeaderDeatail.add("Financial Potential Error");
            summaryHeaderDeatail.add("Record Count");

            ArrayList<String> panWithErrorsummaryHeaderDeatail = new ArrayList<String>();
            panWithErrorsummaryHeaderDeatail.add("Sr No.");
            panWithErrorsummaryHeaderDeatail.add("Bank Branch");
            panWithErrorsummaryHeaderDeatail.add("Section Name");
            panWithErrorsummaryHeaderDeatail.add("Deductee Name");
            panWithErrorsummaryHeaderDeatail.add("Deductee Pan No");
            panWithErrorsummaryHeaderDeatail.add("Transaction Amount");
            panWithErrorsummaryHeaderDeatail.add("TDS Amount");
            panWithErrorsummaryHeaderDeatail.add("Calc TDS Amount");
            panWithErrorsummaryHeaderDeatail.add("Diff TDS Amount");
            panWithErrorsummaryHeaderDeatail.add("Exist TDS Upto Last Quarter");

            ArrayList<String> headerDeatail = new ArrayList<String>();
            headerDeatail.add("Sr No.");
            headerDeatail.add("Error Name");
            headerDeatail.add("Section");
            headerDeatail.add("Deductee Name");
            headerDeatail.add("Deductee PAN No");
            headerDeatail.add("Transaction Date");
            headerDeatail.add("Transaction Amount");
            headerDeatail.add("TDS Deduct Date");
            headerDeatail.add("TDS Deduct Reason");
            headerDeatail.add("Certificate No");
            headerDeatail.add("Tax Rate");
            headerDeatail.add("Total TDS Amount");

            String l_error_query
                    = "select rownum slno, error_name, diff_tds_amt, record_count\n"
                    + "  from (SELECT T.QUARTER_NO,\n"
                    + "               T.TDS_TYPE_CODE,\n"
                    + "               T.ERROR_TYPE_CODE,\n"
                    + "               T.ERROR_TYPE_NAME,\n"
                    + "               T.TABLE_NAME,\n"
                    + "               NULL COLUMN_NAME,\n"
                    + "               T.ERROR_CODE,\n"
                    + "               T.ERROR_NAME,\n"
                    + "               T.POPUP_SHOWN,\n"
                    + "               T.UPDATION_ALLOW_FLAG,\n"
                    + "               T.SHOW_DETAIL_REQUIRED,\n"
                    + "               T.BULK_PAN_VERIFICATION_FLAG,\n"
                    + "               T.REVIEW_REQUIRED,\n"
                    + "               SUM(NVL(T.RECORD_COUNT, 0)) RECORD_COUNT,\n"
                    + "               SUM(NVL(T.DIFF_TDS_AMT, 0)) DIFF_TDS_AMT,\n"
                    + "               ERROR_REVIEWED\n"
                    + "          FROM (SELECT T.ENTITY_CODE,\n"
                    + "                       T.CLIENT_CODE,\n"
                    + "                       T.ACC_YEAR,\n"
                    + "                       T.QUARTER_NO,\n"
                    + "                       T.TDS_TYPE_CODE,\n"
                    + "                       T.ERROR_TYPE_CODE,\n"
                    + "                       T.ERROR_TYPE_NAME,\n"
                    + "                       T.TABLE_NAME,\n"
                    + "                       NULL COLUMN_NAME,\n"
                    + "                       T.ERROR_CODE,\n"
                    + "                       VLM.ERROR_DESCRIPTION ERROR_NAME,\n"
                    + "                       VLM.POPUP_SHOWN,\n"
                    + "                       VLM.UPDATION_ALLOW_FLAG,\n"
                    + "                       VLM.SHOW_DETAIL_REQUIRED,\n"
                    + "                       VLM.BULK_PAN_VERIFICATION_FLAG,\n"
                    + "                       VLM.REVIEW_REQUIRED,\n"
                    + "                       LHS_UTILITY.IS_TRAN_LOAD_ERROR_REVIEWED(T.ENTITY_CODE,\n"
                    + "                                                               T.CLIENT_CODE,\n"
                    + "                                                               T.ACC_YEAR,\n"
                    + "                                                               T.QUARTER_NO,\n"
                    + "                                                               T.TDS_TYPE_CODE,\n"
                    + "                                                               T.ERROR_CODE) ERROR_REVIEWED,\n"
                    + "                       NVL(T.RECORD_COUNT, 0) RECORD_COUNT,\n"
                    + "                       NVL(T.DIFF_TDS_AMT, 0) DIFF_TDS_AMT\n"
                    + "                  FROM TRAN_LOAD_ERROR_PART2_TEMP   T,\n"
                    + "                       VIEW_LHSSYS_ERROR_MAST_TABLE VLM,\n"
                    + "                       CLIENT_MAST                  W1\n"
                    + "                 WHERE W1.CLIENT_CODE = T.CLIENT_CODE\n"
                    + "                   AND (W1.CLIENT_CODE = '" + l_client_code + "' OR "
                    + "                     W1.PARENT_CODE = '" + l_client_code + "' OR\n"
                    + "                       W1.G_PARENT_CODE = '" + l_client_code + "' "
                    + "                         OR W1.SG_PARENT_CODE = '" + l_client_code + "' OR\n"
                    + "                       W1.SSG_PARENT_CODE = '" + l_client_code + "' OR\n"
                    + "                       W1.SSSG_PARENT_CODE = '" + l_client_code + "')\n"
                    + "                   AND VLM.ERROR_CODE = T.ERROR_CODE\n"
                    + "                   AND T.ENTITY_CODE = '" + l_entity_code + "'\n"
                    + "                   AND T.ACC_YEAR = '" + acc_year + "'\n"
                    + "                   AND T.QUARTER_NO = '" + quarter_no + "'\n"
                    + "                   AND T.TDS_TYPE_CODE = '" + l_tds_type_code + "'\n"
                    + "                   /*AND T.ERROR_TYPE_CODE = 'TVE'*/) T\n"
                    + "         GROUP BY T.QUARTER_NO,\n"
                    + "                  T.TDS_TYPE_CODE,\n"
                    + "                  T.ERROR_TYPE_CODE,\n"
                    + "                  T.ERROR_TYPE_NAME,\n"
                    + "                  T.ERROR_CODE,\n"
                    + "                  T.ERROR_NAME,\n"
                    + "                  T.POPUP_SHOWN,\n"
                    + "                  T.UPDATION_ALLOW_FLAG,\n"
                    + "                  T.SHOW_DETAIL_REQUIRED,\n"
                    + "                  T.BULK_PAN_VERIFICATION_FLAG,\n"
                    + "                  T.REVIEW_REQUIRED,\n"
                    + "                  t.ERROR_REVIEWED,\n"
                    + "                  T.TABLE_NAME\n"
                    + "         ORDER BY T.ERROR_CODE, TDS_TYPE_CODE)";

            ArrayList<ArrayList<String>> errorSummaryList = new ArrayList<ArrayList<String>>();
            DbQueryExecutorAsList objBDQueryList = new DbQueryExecutorAsList();
            errorSummaryList = objBDQueryList.execute_oracle_query_as_list(l_error_query);

            String l_panWithErrorSummaryQuery
                    = "select slno,\n"
                    + "       bank_branch_code,\n"
                    + "       tds_name,\n"
                    + "       deductee_name,\n"
                    + "       deductee_panno,\n"
                    + "       tran_amt,\n"
                    + "       tds_amt,\n"
                    + "       calc_tds_amt,\n"
                    + "       diff_tds_amt,\n"
                    + "       uptolq_diff_tds_amt\n"
                    + "  from (select rownum slno,\n"
                    + "               client_code,\n"
                    + "               bank_branch_code,\n"
                    + "               tds_code,\n"
                    + "               tds_name,\n"
                    + "               deductee_code,\n"
                    + "               deductee_name,\n"
                    + "               deductee_panno,\n"
                    + "               tran_amt,\n"
                    + "               tds_amt,\n"
                    + "               calc_tds_amt,\n"
                    + "               diff_tds_amt,\n"
                    + "               uptolq_tds_amt,\n"
                    + "               uptolq_calc_tds_amt,\n"
                    + "               uptolq_diff_tds_amt,\n"
                    + "               net_diff_tds_amt,\n"
                    + "               late_fee_amt,\n"
                    + "               late_payment_amt,\n"
                    + "               late_payment_ramt,\n"
                    + "               late_payment_bamt\n"
                    + "          from (select a.client_code,\n"
                    + "                       w1.bank_branch_code,\n"
                    + "                       tds_code,\n"
                    + "                       tds_name,\n"
                    + "                       deductee_code,\n"
                    + "                       deductee_name,\n"
                    + "                       deductee_panno,\n"
                    + "                       tran_amt,\n"
                    + "                       tds_amt,\n"
                    + "                       calc_tds_amt,\n"
                    + "                       diff_tds_amt,\n"
                    + "                       uptolq_tds_amt,\n"
                    + "                       uptolq_calc_tds_amt,\n"
                    + "                       nvl(lhs_tds_error.lhssys_is_short_deduction(a.entity_code,\n"
                    + "                                                                   a.client_code,\n"
                    + "                                                                   a.acc_year,\n"
                    + "                                                                   a.quarter_no,\n"
                    + "                                                                   null,\n"
                    + "                                                                   a.deductee_panno,\n"
                    + "                                                                   a.tds_type_code,\n"
                    + "                                                                   a.tds_code,\n"
                    + "                                                                   'UPTOLQ_CALC_TDS_AMT_DIFF'),\n"
                    + "                           0) uptolq_diff_tds_amt,\n"
                    + "                       net_diff_tds_amt,\n"
                    + "                       late_fee_amt,\n"
                    + "                       late_payment_amt,\n"
                    + "                       late_payment_ramt,\n"
                    + "                       late_payment_bamt\n"
                    + "                  from tran_load_error_part2_temp a, client_mast w1\n"
                    + "                 where a.entity_code = '" + l_entity_code + "'\n"
                    + "                   and w1.entity_code = a.entity_code\n"
                    + "                   and w1.client_code = a.client_code\n"
                    + "                   and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                    + "                       w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                    + "                       w1.ssg_parent_code = '" + l_client_code + "' or\n"
                    + "                       w1.sssg_parent_code = '" + l_client_code + "')\n"
                    + "                   and a.acc_year = '" + acc_year + "'\n"
                    + "                   and a.quarter_no = '" + quarterNumber + "'\n"
                    + "                   and a.tds_type_code = '" + l_tds_type_code + "'\n"
                    + "                   and a.error_type_code = 'TVE'\n"
                    + "                 order by deductee_panno))";
//            System.out.println("l_panWithErrorSummaryQuery--->"+l_panWithErrorSummaryQuery);
            ArrayList<ArrayList<String>> panWitherrorSummaryDetailList = new ArrayList<ArrayList<String>>();
            DbQueryExecutorAsList objBDQuery = new DbQueryExecutorAsList();
            panWitherrorSummaryDetailList = objBDQuery.execute_oracle_query_as_list(l_panWithErrorSummaryQuery);

            String l_query
                    = " select rownum slno, \n"
                    + "       (select lhs_utility.get_name('error_code', b.error_code) from dual) error_name,\n"
                    + "       lhs_utility.get_name('tds_code',tds_code)tds_section,\n"
                    + "       a.deductee_name,\n"
                    + "       a.deductee_panno,\n"
                    + "       a.tran_ref_date,\n"
                    + "       a.tran_amt,\n"
                    + "       a.tds_deduct_date,\n"
                    + "       lhs_utility.get_name('tds_deduct_reason', a.tds_deduct_reason) tds_deduct_reason_name,\n"
                    + "       a.certificate_no,\n"
                    + "       a.itax_rate,\n"
                    + "       a.tds_amt\n"
                    + "  from tds_tran_load a, tran_load_error b, view_lhssys_error_mast v\n"
                    + " where b.entity_code = a.entity_code\n"
                    + "   and b.client_code = a.client_code\n"
                    + "   and b.acc_year = a.acc_year\n"
                    + "   and b.quarter_no = a.quarter_no\n"
                    + "   and b.tds_type_code = a.tds_type_code\n"
                    + "   and b.rowid_seq = a.rowid_Seq\n"
                    + "   and b.error_code = v.error_code\n"
                    + "   and a.entity_code = '" + l_entity_code + "'\n"
                    + " and exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = a.client_code\n"
                    + "                   and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                    + "                        w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                    + "                        w1.ssg_parent_code = '" + l_client_code + "' or w1.sssg_parent_code = '" + l_client_code + "')) \n"
                    + "   and a.acc_year = '" + acc_year + "'\n"
                    + "   and a.quarter_no = '" + quarterNumber + "'\n"
                    + "   and a.tds_type_code = '" + l_tds_type_code + "'";

//            System.out.println("l_query.k.." + l_query);
            ArrayList<ArrayList<String>> errorDetailList = new ArrayList<ArrayList<String>>();
            DbQueryExecutorAsList objBDQuery1 = new DbQueryExecutorAsList();
            errorDetailList = objBDQuery1.execute_oracle_query_as_list(l_query);

            String filepath = generateErrorFile(filename, file_dwl_time, summaryHeaderDeatail, errorSummaryList, panWithErrorsummaryHeaderDeatail, panWitherrorSummaryDetailList, headerDeatail, errorDetailList);
            String real_filename = filename + "_" + file_dwl_time + "." + file_ext;

            setFileName(real_filename);//User File Name

            if (!utl.isnull(filepath)) {
                File obj_dwl_file = new File(filepath);
                if (obj_dwl_file.exists()) {
                    fileInputStream = new FileInputStream(obj_dwl_file);
                    return_view = "dwltemplatesuccess";
                } else {
                    return_view = "dwltemplatefailed";
                }
            } else {
                return_view = "dwltemplatefailed";
                setSessionResult("Some error occurred, Unable to download error template.");
            }

        } catch (Exception e) {
            return_view = "dwltemplatefailed";
            e.printStackTrace();
        }
        return return_view;
    }//end method

    private String generateErrorFile(String filename, String file_dwl_time, ArrayList<String> summaryHeaderDeatail, ArrayList<ArrayList<String>> errorSummaryList, ArrayList<String> panWithErrorsummaryHeaderDeatail, ArrayList<ArrayList<String>> panWitherrorSummaryDetailList, ArrayList<String> headerDeatail, ArrayList<ArrayList<String>> errorDetailList) {
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
                writeErrorFile(obj_tempfile, summaryHeaderDeatail, errorSummaryList, panWithErrorsummaryHeaderDeatail, panWitherrorSummaryDetailList, headerDeatail, errorDetailList);
            } catch (Exception ex) {
                l_return_filepath = "";
                ex.printStackTrace();
            }

        } catch (IOException ex) {
            l_return_filepath = "";
        }
        return l_return_filepath;
    }//end method

    private boolean writeErrorFile(File obj_tempfile, ArrayList<String> summaryHeaderDeatail, ArrayList<ArrayList<String>> errorSummaryList, 
            ArrayList<String> panWithErrorsummaryHeaderDeatail, ArrayList<ArrayList<String>> panWitherrorSummaryDetailList, 
            ArrayList<String> headerDeatail, ArrayList<ArrayList<String>> errorDetailList) {
        
        String l_realpath = obj_tempfile.getAbsolutePath();
        String extension = (String) session.get("EXCELFORMAT");
        try {
            final String REGEXP = "^[0-9]\\d*(\\.[0-9]\\d*)?$";// Regular expression for allowing digits.
            Pattern pattern = Pattern.compile(REGEXP);
            Matcher matcher = null;

            Workbook obj_writableWorkbook = null;
            Sheet obj_writableSheet = null;

            String title1 = "Error Summary";
            String title2 = "Pan With Error Summary";
            String title3 = "Detail Errors";

            if (extension.endsWith("xlsx")) {
                obj_writableWorkbook = new XSSFWorkbook();
            } else {
                obj_writableWorkbook = new HSSFWorkbook();
            }
            obj_writableSheet = obj_writableWorkbook.createSheet(title1);

            if (summaryHeaderDeatail != null && summaryHeaderDeatail.size() > 0) {// used to generate heading
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

            if (errorSummaryList != null && errorSummaryList.size() > 0) {// used to generate cell data value for sheet no 2
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

            if (panWithErrorsummaryHeaderDeatail != null && panWithErrorsummaryHeaderDeatail.size() > 0) {// used to generate heading                
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

            if (panWitherrorSummaryDetailList != null && panWitherrorSummaryDetailList.size() > 0) {// used to generate cell data value for sheet no 2
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

            obj_writableSheet = obj_writableWorkbook.createSheet(title3);

            if (headerDeatail != null && headerDeatail.size() > 0) {// used to generate heading                
                Row row = obj_writableSheet.createRow(0);
                Cell cell = null;

                for (int j = 0; j < headerDeatail.size(); j++) {
                    if (j < (headerDeatail.size())) {
                        try {
                            String headerString = utl.isnull(headerDeatail.get(j)) ? "" : headerDeatail.get(j);

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

            if (errorDetailList != null && errorDetailList.size() > 0) {// used to generate cell data value for sheet no 2
                try {
                    for (int i = 0; i < errorDetailList.size(); i++) {//rows
                        Row row1 = obj_writableSheet.createRow(i + 1);
                        Cell cell2 = null;

                        for (int j = 0; j < headerDeatail.size(); j++) {//cols
                            String contentString = utl.isnull(errorDetailList.get(i).get(j)) ? "" : errorDetailList.get(i).get(j);
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

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

}//end class
