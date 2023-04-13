/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.DeducteeManage15ghDetail.GenerateXML15GH;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author seema.mourya
 */
public class DynamicErrorManipulationAction15GH1 extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String ErrorCode;
    private String ColumnCnfData;
    private String TableName;
    private String ColumnName;
    private String ReviewValue;
    private String processCnfChkBx;
    private String errType;
    private String defaultColumnName;
    private String defaultColumnValue;

    public DynamicErrorManipulationAction15GH1() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {

        String l_return_value = "success";
        String l_return_message = "error";
        if (!utl.isnull(getAction())) {
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String l_acc_year = asmt.getAccYear();
            String tds_quarter = asmt.getQuarterNo();
            //String[] quarterNameSplit = tds_quarter.split("-");
            //String quarterNo = quarterNameSplit[2];
            int l_quarter_no = Integer.parseInt(tds_quarter);
            String l_tds_type_code = asmt.getTdsTypeCode();

            if (getAction().equalsIgnoreCase("updateDefaultValue")) {

//                String defaultColumnValue = getDefaultColumnValue(getDefaultColumnName(), viewClientMast, asmt);
//                System.out.println("defaultColumnValue..." + defaultColumnValue);
                try {
                    String updateDefaultValueQuery = "";
                    if (!utl.isnull(getErrorCode()) && getErrorCode().equalsIgnoreCase("L-ZDE-20030")) {
                        updateDefaultValueQuery
                                = "update deductee_bflag_amt_tran b\n"
                                + "   set amount = 0\n"
                                + " where b.entity_code = '" + l_entity_code + "'\n"
                                + "   and exists\n"
                                + " (select 1\n"
                                + "          from client_mast w1\n"
                                + "         where w1.client_code = b.client_code\n"
                                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                                + "               w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.sssg_parent_code = '" + l_client_code + "'))\n"
                                + "   and b.acc_year = '" + l_acc_year + "'\n"
                                + "   and b.quarter_no = '" + l_quarter_no + "'\n"
                                + "   and b.tds_type_code = '" + l_tds_type_code + "'\n"
                                + "   and nvl(b.amount, 0) < 0\n"
                                + "   and exists (select 1\n"
                                + "          from tran_load_error_15gh c\n"
                                + "         where c.entity_code = b.entity_code\n"
                                + "           and c.client_code = b.client_code\n"
                                + "           and c.acc_year = b.acc_year\n"
                                + "           and c.quarter_no = b.quarteR_no\n"
                                + "           and c.tds_type_code = b.tds_type_code\n"
                                + "           and c.rowid_seq = b.deductee_mast_15gh_rowid_seq\n"
                                + "           and c.error_code = '" + getErrorCode() + "'\n"
                                + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";
                    } else if (!utl.isnull(getErrorCode()) && getErrorCode().equalsIgnoreCase("L-ZDE-20029")) {
                        updateDefaultValueQuery
                                = "update deductee_mast_15gh b\n"
                                + "   set income_amount_paid=0\n"
                                + " where b.entity_code = '" + l_entity_code + "'\n"
                                + "   and exists\n"
                                + " (select 1\n"
                                + "          from client_mast w1\n"
                                + "         where w1.client_code = b.client_code\n"
                                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                                + "               w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.sssg_parent_code = '" + l_client_code + "'))\n"
                                + "   and b.acc_year = '" + l_acc_year + "'\n"
                                + "   and b.quarter_no = '" + l_quarter_no + "'\n"
                                + "   and b.tds_type_code = '" + l_tds_type_code + "'\n"
                                + "   and exists (select 1\n"
                                + "          from tran_load_error_15gh c\n"
                                + "         where c.entity_code = b.entity_code\n"
                                + "           and c.client_code = b.client_code\n"
                                + "           and c.acc_year = b.acc_year\n"
                                + "           and c.quarter_no = b.quarteR_no\n"
                                + "           and c.tds_type_code = b.tds_type_code\n"
                                + "           and c.rowid_seq = b.rowid_seq\n"
                                + "           and c.error_code = '" + getErrorCode() + "'\n"
                                + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";
                    } else {
                        String l_value = "'" + getDefaultColumnValue() + "'";
                        if (!utl.isnull(getDefaultColumnName()) && getDefaultColumnName().equalsIgnoreCase("dob") || getDefaultColumnName().equalsIgnoreCase("INCOME_PAID_DATE") || getDefaultColumnName().equalsIgnoreCase("declaration_date")) {
                            l_value = "to_date('" + getDefaultColumnValue() + "','dd-mm-rrrr')";
                        }
//                        System.out.println("l_value..." + l_value);
                        updateDefaultValueQuery = "update deductee_mast_15gh b\n"
                                + "   set " + getDefaultColumnName() + " = " + l_value + "\n"
                                + " where b.entity_code = '" + l_entity_code + "'\n"
                                + "   and exists\n"
                                + " (select 1\n"
                                + "          from client_mast w1\n"
                                + "         where w1.client_code = b.client_code\n"
                                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                                + "               w1.sssg_parent_code = '" + l_client_code + "'))\n"
                                + "   and b.acc_year = '" + l_acc_year + "'\n"
                                + "   and b.quarter_no = '" + l_quarter_no + "'\n"
                                + "   and b.tds_type_code = '" + l_tds_type_code + "'\n"
                                + "   and exists (select 1\n"
                                + "          from tran_load_error_15gh c\n"
                                + "         where c.entity_code = b.entity_code\n"
                                + "           and c.client_code = b.client_code\n"
                                + "           and c.acc_year = b.acc_year\n"
                                + "           and c.quarter_no = b.quarteR_no\n"
                                + "           and c.tds_type_code = b.tds_type_code\n"
                                + "           and c.rowid_seq = b.rowid_seq\n"
                                + "           and c.error_code = '" + getErrorCode() + "'\n"
                                + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";
                    }
                    utl.generateSpecialLog("15GH-PE-0006 (Update Deductee With Default Value Query)----", updateDefaultValueQuery);
                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                    boolean result = objDbFunctionClass.execute_oracle_update_function_as_string(updateDefaultValueQuery);
                    //System.out.println("result---" + result);
                    if (result) {

                        try {

                            String delete_15gh_error_query
                                    = "delete from tran_load_error_15gh_part2 a\n"
                                    + " where a.entity_code = '" + l_entity_code + "'\n"
                                    + "   and exists\n"
                                    + " (select 1\n"
                                    + "          from client_mast b\n"
                                    + "         where b.entity_code = a.entity_code\n"
                                    + "           and b.client_code = b.client_code\n"
                                    + "           and (b.client_code = '" + l_client_code + "' or b.parent_code = '" + l_client_code + "' or\n"
                                    + "               b.g_parent_code = '" + l_client_code + "' or b.sg_parent_code = '" + l_client_code + "' or\n"
                                    + "               b.ssg_parent_code = '" + l_client_code + "' or b.sssg_parent_code = '" + l_client_code + "'))\n"
                                    + "\n"
                                    + "   and a.acc_year = '" + l_acc_year + "'\n"
                                    + "   and a.quarter_no = '" + l_quarter_no + "'\n"
                                    + "   and a.tds_type_code = '" + l_tds_type_code + "'\n"
                                    + "   and a.error_code = '" + getErrorCode() + "'";
                            //System.out.println("updateDefaultValueQuery...." + updateDefaultValueQuery);
                            DbFunctionExecutorAsString objDbFunctionClass1 = new DbFunctionExecutorAsString();
                            boolean delresult1 = objDbFunctionClass1.execute_oracle_update_function_as_string(delete_15gh_error_query);
                            if (delresult1) {
                                String delete_tran_load_error_15gh_query
                                        = "delete tran_load_error_15gh a\n"
                                        + " where a.entity_code = '" + l_entity_code + "'\n"
                                        + "   and exists\n"
                                        + " (select 1\n"
                                        + "          from client_mast b\n"
                                        + "         where b.entity_code = a.entity_code\n"
                                        + "           and b.client_code = b.client_code\n"
                                        + "           and (b.client_code = '" + l_client_code + "' or b.parent_code = '" + l_client_code + "' or\n"
                                        + "               b.g_parent_code = '" + l_client_code + "' or b.sg_parent_code = '" + l_client_code + "' or\n"
                                        + "               b.ssg_parent_code = '" + l_client_code + "' or b.sssg_parent_code = '" + l_client_code + "'))\n"
                                        + "\n"
                                        + "   and a.acc_year = '" + l_acc_year + "'\n"
                                        + "   and a.quarter_no = '" + l_quarter_no + "'\n"
                                        + "   and a.tds_type_code = '" + l_tds_type_code + "'\n"
                                        + "   and a.error_code = '" + getErrorCode() + "'";
                                //System.out.println("delete_tran_load_error_15gh_query...." + delete_tran_load_error_15gh_query);
                                DbFunctionExecutorAsString objDbFunctionClass2 = new DbFunctionExecutorAsString();
                                boolean delresult2 = objDbFunctionClass2.execute_oracle_update_function_as_string(delete_tran_load_error_15gh_query);
//                                System.out.println("Delete tran_load_error_15gh result..." + delresult2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        l_return_message = "update";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                }
            } else if (getAction().equalsIgnoreCase("callXMLGen")) {
                String l_parent_client_code = "";
                try {
                    String l_client_query = "select lhs_utility.is_client_bflag_gen_level_code('" + l_entity_code + "','" + l_client_code + "','" + l_acc_year + "') from dual";
//                    System.out.println("l_client_query..."+l_client_query);
                    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                    l_parent_client_code = ef.execute_oracle_function_as_string(l_client_query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!utl.isnull(l_parent_client_code)) {
                    l_return_message = "F";
                } else {
                    l_return_message = "T";
                }
            } else if (getAction().equalsIgnoreCase("getRecCount")) {

                try {
                    String l_count_query
                            = "select count(*)\n"
                            + "  from tran_load_error_15gh a\n"
                            + " where a.entity_code = '" + l_entity_code + "'\n"
                            + "   and exists\n"
                            + " (select 1\n"
                            + "          from client_mast b\n"
                            + "         where b.entity_code = a.entity_code\n"
                            + "           and b.client_code = b.client_code\n"
                            + "           and (b.client_code = '" + l_client_code + "' or b.parent_code = '" + l_client_code + "' or\n"
                            + "               b.g_parent_code = '" + l_client_code + "' or b.sg_parent_code = '" + l_client_code + "' or\n"
                            + "               b.ssg_parent_code = '" + l_client_code + "' or b.sssg_parent_code = '" + l_client_code + "'))\n"
                            + "\n"
                            + "   and a.acc_year = '" + l_acc_year + "'\n"
                            + "   and a.quarter_no = '" + l_quarter_no + "'\n"
                            + "   and a.tds_type_code = '" + l_tds_type_code + "' \n"
                            + "and exists (select rowid_seq\n"
                            + "          from tran_load_error_15gh x\n"
                            + "         where x.entity_code = a.entity_code\n"
                            + "           and x.client_code = a.client_code\n"
                            + "           and x.acc_year = a.acc_year\n"
                            + "           and x.quarter_no = a.quarter_no\n"
                            + "           and x.tds_type_code = a.tds_type_code\n"
                            + "           and x.deductee_code = a.deductee_code\n"
                            + "           and x.rowid_seq = a.rowid_seq\n"
                            + "           and nvl(x.error_review_flag, 'N') = 'N')";

                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    Long total = objDBFunction.execute_oracle_function_as_integar(l_count_query);
                    if (total > 0) {
                        l_return_message = "F";

                    } else {

                        l_return_message = "T";
                    }
                } catch (Exception e) {
                    l_return_message = "T";
                }
            } else if (getAction().equalsIgnoreCase("BlkReview")) {
                try {
                    String l_review_query
                            = "update tran_load_error_15gh a\n"
                            + "set a.error_review_flag='Y',\n"
                            + "a.error_review_on=sysdate,\n"
                            + "a.error_review_remark='" + getReviewValue() + "'\n"
                            + "where entity_code='" + l_entity_code + "'\n"
                            + " and exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = a.client_code\n"
                            + "                   and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                            + "                        w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                            + "                        w1.ssg_parent_code = '" + l_client_code + "' or w1.sssg_parent_code = '" + l_client_code + "')) \n"
                            + "and acc_year='" + l_acc_year + "'\n"
                            + "and quarter_no= '" + l_quarter_no + "'\n"
                            + "and tds_type_code='" + l_tds_type_code + "'\n"
                            + "and error_code='" + getErrorCode() + "'";
//                    //System.out.println("l_review_query..." + l_review_query);
                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                    boolean result = objDbFunctionClass.execute_oracle_update_function_as_string(l_review_query);
                    if (result) {
                        l_return_message = "updateReview";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    l_return_message = "error";
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("checkReprocessError")) {
                try {
                    String l_check_process_error
                            = "select count(*)\n"
                            + "  from (select quarter_no,\n"
                            + "               tds_type_code,\n"
                            + "               table_name,\n"
                            + "               error_type_code,\n"
                            + "               error_type_name,\n"
                            + "               sum(nvl(record_count, 0)) record_count\n"
                            + "          from (select t.entity_code,\n"
                            + "                       t.client_code,\n"
                            + "                       t.acc_year,\n"
                            + "                       t.quarter_no,\n"
                            + "                       t.tds_type_code,\n"
                            + "                       t.error_type_code,\n"
                            + "                       t.error_type_name,\n"
                            + "                       null table_name,\n"
                            + "                       null column_name,\n"
                            + "                       t.error_code,\n"
                            + "                       vlm.error_description error_name,\n"
                            + "                       vlm.popup_shown,\n"
                            + "                       vlm.updation_allow_flag,\n"
                            + "                       vlm.show_detail_required,\n"
                            + "                       vlm.bulk_pan_verification_flag,\n"
                            + "                       vlm.Review_required,\n"
                            + "                       nvl(t.record_count, 0) record_count\n"
                            + "                  from TRAN_LOAD_ERROR_15GH_PART2   t,\n"
                            + "                       view_lhssys_error_mast_table vlm,\n"
                            + "                       client_mast                  w1\n"
                            + "                 where w1.client_code = t.client_code\n"
                            + "                   and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                            + "                       w1.g_parent_code = '" + l_client_code + "' or\n"
                            + "                       w1.sg_parent_code = '" + l_client_code + "' or\n"
                            + "                       w1.ssg_parent_code = '" + l_client_code + "' or\n"
                            + "                       w1.sssg_parent_code = '" + l_client_code + "')\n"
                            + "                   and vlm.error_code = t.error_code\n"
                            + "                   and t.entity_code = '" + l_entity_code + "'\n"
                            + "                   and t.acc_year = '" + l_acc_year + "'\n"
                            + "                   and t.quarter_no = '" + l_quarter_no + "'\n"
                            + "                   and t.tds_type_code = '" + l_tds_type_code + "')\n"
                            + "         group by quarter_no,\n"
                            + "                  tds_type_code,\n"
                            + "                  table_name,\n"
                            + "                  error_type_code,\n"
                            + "                  error_type_name)";
                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    Long total = objDBFunction.execute_oracle_function_as_integar(l_check_process_error);
                    if (total > 0) {
                        l_return_message = "F";
                    } else {
                        l_return_message = "T";
                    }
                } catch (Exception e) {
                    l_return_message = "T";
                }

            }
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getDefaultColumnValue(String defaultColumnName, ViewClientMast viewClientMast, Assessment asmt) {
        String finalColumnName = "";
        String finalDefaultValue = "";
        try {
            if (!utl.isnull(defaultColumnName)) {
                if (defaultColumnName.equalsIgnoreCase("identification_no")) {
                    finalColumnName = "UNIQUEIDNUMBER";
                } else if (defaultColumnName.equalsIgnoreCase("deductee_name")) {
                    finalColumnName = "ASSESSEENAME";
                } else if (defaultColumnName.equalsIgnoreCase("panno")) {
                    finalColumnName = "ASSESSEEPAN";
                } else if (defaultColumnName.equalsIgnoreCase("dob")) {
                    finalColumnName = "DOB";
                } //                else if (defaultColumnName.equalsIgnoreCase("")) {
                //                    finalColumnName = "STATUS";
                //                } 
                else if (defaultColumnName.equalsIgnoreCase("resident_status")) {
                    finalColumnName = "RESIDENTIALSTATUS";
                } else if (defaultColumnName.equalsIgnoreCase("ADD1")) {
                    finalColumnName = "FLATDOORNO";
                } else if (defaultColumnName.equalsIgnoreCase("ADD2")) {
                    finalColumnName = "PREMISESNAME";
                } else if (defaultColumnName.equalsIgnoreCase("ADD3")) {
                    finalColumnName = "ROADORSTREET";
                } else if (defaultColumnName.equalsIgnoreCase("ADD4")) {
                    finalColumnName = "LOCALITYORAREA";
                } else if (defaultColumnName.equalsIgnoreCase("city_code")) {
                    finalColumnName = "CITYORTOWNORDISTRICT";
                } else if (defaultColumnName.equalsIgnoreCase("state_code")) {
                    finalColumnName = "STATECODE";
                } else if (defaultColumnName.equalsIgnoreCase("pin")) {
                    finalColumnName = "PINCODE";
                } else if (defaultColumnName.equalsIgnoreCase("email_id")) {
                    finalColumnName = "EMAILADDRESS";
                } else if (defaultColumnName.equalsIgnoreCase("stdcode")) {
                    finalColumnName = "STDCODE";
                } else if (defaultColumnName.equalsIgnoreCase("phoneno")) {
                    finalColumnName = "PHONENO";
                } else if (defaultColumnName.equalsIgnoreCase("mobileno")) {
                    finalColumnName = "MOBILENO";
                } //                else if (defaultColumnName.equalsIgnoreCase("")) {
                //                    finalColumnName = "TAXASSESSEDFLAG";
                //                } 
                else if (defaultColumnName.equalsIgnoreCase("latest_assessment_year")) {
                    finalColumnName = "LATESTASSTYR";
                } else if (defaultColumnName.equalsIgnoreCase("est_declaration_income")) {
                    finalColumnName = "ESTIMATEDINC";
                } else if (defaultColumnName.equalsIgnoreCase("est_total_income")) {
                    finalColumnName = "ESTIMATEDTOTALINCPRVYR";
                } else if (defaultColumnName.equalsIgnoreCase("total_no_form")) {
                    finalColumnName = "TOTALNOOFFORM15G";
                } else if (defaultColumnName.equalsIgnoreCase("aggregate_income_amt")) {
                    finalColumnName = "AGGREGATEAMTFORM15G";
                } else if (defaultColumnName.equalsIgnoreCase("declaration_date")) {
                    finalColumnName = "DECLARATIONDATE";
                } else if (defaultColumnName.equalsIgnoreCase("income_amount_paid")) {
                    finalColumnName = "AMTOFINCPAID";
                } else if (defaultColumnName.equalsIgnoreCase("income_paid_date")) {
                    finalColumnName = "DATEINCPAID";
                } else if (defaultColumnName.equalsIgnoreCase("account_no")) {
                    finalColumnName = "IDENFICATIONNUM";
                } else if (defaultColumnName.equalsIgnoreCase("nature_of_income")) {
                    finalColumnName = "NATUREOFINC";
                } else if (defaultColumnName.equalsIgnoreCase("section")) {
                    finalColumnName = "DEDUCTSECTION";
                } else if (defaultColumnName.equalsIgnoreCase("amount")) {
                    finalColumnName = "AMTOFINC";
                }

                String defaultValue = "";
                try {
                    DbFunctionExecutorAsString efforrefno = new DbFunctionExecutorAsString();
                    String defaultValueQuery = "select " + finalColumnName + " from view_bf15gh_default_set_value";
                    defaultValue = efforrefno.execute_oracle_function_as_string(defaultValueQuery);
                } catch (Exception e) {
                    defaultValue = "";
                }

                try {
                    if (!utl.isnull(defaultValue)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        if (defaultValue.equalsIgnoreCase("Deductor_State_code")) {
                            finalDefaultValue = viewClientMast.getDeductor_state_code();
                        } else if (defaultValue.equalsIgnoreCase("Deductor_City_code")) {
                            finalDefaultValue = viewClientMast.getDeductor_state_code();
                        } else if (defaultValue.equalsIgnoreCase("Deductor_Pin")) {
                            finalDefaultValue = viewClientMast.getDeductor_pin();
                        } else if (defaultValue.equalsIgnoreCase("Qtr_First_date")) {
                            finalDefaultValue = sdf.format(asmt.getQuarterFromDate());
                        } else if (defaultValue.equalsIgnoreCase("DEDUCTEE_ESTIMATEDINC")) {
                            finalDefaultValue = "";
                        } else if (defaultValue.equalsIgnoreCase("Qtr_Last_date")) {
                            finalDefaultValue = sdf.format(asmt.getQuarterToDate());
                        } else if (defaultValue.equalsIgnoreCase("cur_Year")) {
                            finalDefaultValue = "";
                        } else {
                            finalDefaultValue = defaultValue;
                        }
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalDefaultValue;
    }//end method

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getColumnCnfData() {
        return ColumnCnfData;
    }

    public void setColumnCnfData(String ColumnCnfData) {
        this.ColumnCnfData = ColumnCnfData;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(String ReviewValue) {
        this.ReviewValue = ReviewValue;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getDefaultColumnName() {
        return defaultColumnName;
    }

    public void setDefaultColumnName(String defaultColumnName) {
        this.defaultColumnName = defaultColumnName;
    }

    public String getDefaultColumnValue() {
        return defaultColumnValue;
    }

    public void setDefaultColumnValue(String defaultColumnValue) {
        this.defaultColumnValue = defaultColumnValue;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end
}
