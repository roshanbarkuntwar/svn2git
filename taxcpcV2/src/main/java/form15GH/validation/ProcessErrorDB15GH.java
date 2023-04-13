/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessErrorDB15GH {

    private final Util utl;

    public ProcessErrorDB15GH() {
        utl = new Util();
    }

    public String getUpdatePANColumnQuery(String entity_code, String client_code) {
        String l_update_pan_query
                = "update deductee_mast_15gh a set a.deductee_panno_verified=(select lhs_utility.is_panno_verified(a.panno) from dual),\n"
                + "                           a.deductee_panno_valid=(select lhs_utility.is_valid_panno(a.panno) from dual),\n"
                + "                           a.name_as_panno=(select lhs_utility.get_name('panmast_name',panno) from dual)\n"
                + "where entity_code='" + entity_code + "'\n"
                + "and nvl(deductee_panno_verified,'N')<>'Y'\n"
                + "and   exists (select 1\n"
                + "              from   client_mast b\n"
                + "              where  b.client_code=a.client_code\n"
                + "              and    (b.client_code = '" + client_code + "' or b.parent_code = '" + client_code + "' or\n"
                + "                      b.g_parent_code = '" + client_code + "' or b.sg_parent_code = '" + client_code + "' or\n"
                + "                      b.ssg_parent_code = '" + client_code + "' or b.sssg_parent_code = '" + client_code + "')\n"
                + "              )";

        return l_update_pan_query;
    }//end method

    public String getUnverifiedPANCountQuery(String entity_code, String client_code) {
        String l_pan_unverified_query
                = "select count(*)\n"
                + "from pan_mast_unverified a, client_mast b\n"
                + "where b.entity_code=a.entity_code\n"
                + "and b.client_code=a.client_code\n"
                + "and  b.entity_code='" + entity_code + "'\n"
                + "and (b.client_code='" + client_code + "' or\n"
                + "     b.parent_code='" + client_code + "' or\n"
                + "     b.g_parent_code='" + client_code + "' or\n"
                + "     b.sg_parent_code='" + client_code + "' or\n"
                + "     b.ssg_parent_code='" + client_code + "' or\n"
                + "     b.sssg_parent_code='" + client_code + "'\n"
                + "    )";

        return l_pan_unverified_query;
    }//end method

    public String getFirstScreen15GHSummaryQuery(Assessment asmt, String l_entity_code, String l_client_code, String l_process_level) {

        String processLevelWhereCls = "";
//        if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("0")) { // for login level
//            processLevelWhereCls = " and t.validation_client_code='" + l_client_code + "' \n";
//
//        } else 
        if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("1")) { // for all level
            processLevelWhereCls = " and   (w1.client_code='" + l_client_code + "' or\n"
                    + "                 w1.parent_code='" + l_client_code + "' or\n"
                    + "                 w1.g_parent_code='" + l_client_code + "' or\n"
                    + "                 w1.sg_parent_code='" + l_client_code + "' or\n"
                    + "                 w1.ssg_parent_code='" + l_client_code + "' or\n"
                    + "                 w1.sssg_parent_code='" + l_client_code + "')\n \n";
        } else {
            processLevelWhereCls = " and t.validation_client_code='" + l_client_code + "' \n";
        }

        String l_query = "";
        //          OLD query--
        //                = "select quarter_no,\n"
        //                + "       tds_type_code,\n"
        //                + "       table_name,\n"
        //                + "       error_type_code,\n"
        //                + "       error_type_name,\n"
        //                + "       sum(nvl(record_count,0)) record_count\n"
        //                + "  from (select t.entity_code,\n"
        //                + "               t.client_code,\n"
        //                + "               t.acc_year,\n"
        //                + "               t.quarter_no,\n"
        //                + "               t.tds_type_code,\n"
        //                + "               t.error_type_code,\n"
        //                + "               t.error_type_name,\n"
        //                + "               null table_name,\n"
        //                + "               null column_name,\n"
        //                + "               t.error_code,\n"
        //                + "               vlm.error_description error_name,\n"
        //                + "               vlm.popup_shown,\n"
        //                + "               vlm.updation_allow_flag,\n"
        //                + "               vlm.show_detail_required,\n"
        //                + "               vlm.bulk_pan_verification_flag,\n"
        //                + "               vlm.Review_required,\n"
        //                + "               nvl(t.record_count, 0) record_count\n"
        //                + "          from TRAN_LOAD_ERROR_15GH_PART2 t, view_lhssys_error_mast_table vlm, client_mast w1\n"
        //                + "          where w1.client_code = t.client_code\n"
        //                + processLevelWhereCls
        //                //                + "          and   (w1.client_code='" + l_client_code + "' or\n"
        //                //                + "                 w1.parent_code='" + l_client_code + "' or\n"
        //                //                + "                 w1.g_parent_code='" + l_client_code + "' or\n"
        //                //                + "                 w1.sg_parent_code='" + l_client_code + "' or\n"
        //                //                + "                 w1.ssg_parent_code='" + l_client_code + "' or\n"
        //                //                + "                 w1.sssg_parent_code='" + l_client_code + "')\n"
        //                + "   and vlm.error_code=t.error_code\n"
        //                + "   and t.entity_code = '" + l_entity_code + "'\n"
        //                + "   and t.acc_year = '" + asmt.getAccYear() + "'\n"
        //                + "   and t.quarter_no = " + asmt.getQuarterNo() + "\n"
        //                + "   and t.tds_type_code = '" + asmt.getTdsTypeCode() + "')\n"
        //                + "group by quarter_no,\n"
        //                + "       tds_type_code,\n"
        //                + "       table_name,\n"
        //                + "       error_type_code,\n"
        //                + "       error_type_name";

        //  Updated query - by Girish Sir - 16-12-2019
        l_query = "select quarter_no,\n"
                + "       tds_type_code,\n"
                + "       table_name,\n"
                + "       error_type_code,\n"
                + "       error_type_name,\n"
                + "       sum(nvl(record_count, 0)) record_count,\n"
                + "       SUM(nvl(review_error_record, 0)) review_error_record,\n"
                + "       (sum(nvl(record_count, 0)) - SUM(nvl(review_error_record, 0))) pending_for_review\n"
                + "  from (select t.entity_code,\n"
                + "               t.client_code,\n"
                + "               t.acc_year,\n"
                + "               t.quarter_no,\n"
                + "               t.tds_type_code,\n"
                + "               vlm.error_type_code,\n"
                + "               vlm.error_type_name,\n"
                + "               null table_name,\n"
                + "               null column_name,\n"
                + "               t.error_code,\n"
                + "               vlm.error_description error_name,\n"
                + "               vlm.popup_shown,\n"
                + "               vlm.updation_allow_flag,\n"
                + "               vlm.show_detail_required,\n"
                + "               vlm.bulk_pan_verification_flag,\n"
                + "               vlm.Review_required,\n"
                + "               1 record_count,\n"
                + "               decode(t.error_review_on, null, 0, 1) review_error_record\n"
                + "          from TRAN_LOAD_ERROR_15GH         t,\n"
                + "               view_lhssys_error_mast_table vlm,\n"
                + "               client_mast                  w1\n"
                + "         where w1.entity_code = t.entity_code\n"
                + "           and w1.client_code = t.client_code\n"
                + processLevelWhereCls
                + "   and vlm.error_code=t.error_code\n";

        if (!utl.isnull(asmt.getAccYear()) && !asmt.getAccYear().equalsIgnoreCase("all")) {
            l_query += " and t.acc_year = '" + asmt.getAccYear() + "'\n";
        }
        if (!utl.isnull(asmt.getQuarterNo()) && !asmt.getQuarterNo().equalsIgnoreCase("all")) {
            l_query += " and t.quarter_no = '" + asmt.getQuarterNo() + "'\n";
        }
        if (!utl.isnull(asmt.getTdsTypeCode()) && !asmt.getTdsTypeCode().equalsIgnoreCase("all")) {
            l_query += " and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n";
        }

        l_query += "  ) group by quarter_no,\n"
                + "          tds_type_code,\n"
                + "          table_name,\n"
                + "          error_type_code,\n"
                + "          error_type_name";

        return l_query;
    }//end method

    public String getDynamicFileDashboardDetails15GHQuery(Assessment asmt, String l_entity_code, String l_client_code) {
        String l_query
                = " select user_seq, data_type, data_type_name, rec_count\n"
                + "  from (select '01' user_seq,\n"
                + "               'DEDUCTEE_MAST_15GH' data_type,\n"
                + "               'Deductee 15GH Detail(s)' data_type_name,\n"
                + "               lhs_tds.get_tds_reco_details('" + l_entity_code + "',\n"
                + "                                            '" + l_client_code + "',\n"
                + "                                            '" + asmt.getAccYear() + "',\n"
                + "                                            '" + asmt.getQuarterNo() + "',\n"
                + "                                            null,\n"
                + "                                            null,\n"
                + "                                            '" + asmt.getTdsTypeCode() + "',\n"
                + "                                            null,\n"
                + "                                            'DEDUCTEE_MAST_15GH',\n"
                + "                                            'NO_OF_DEDUCTEE') rec_count\n"
                + "          from dual\n"
                + "        union all\n"
                + "        select '01' user_seq,\n"
                + "               'DEDUCTEE_BFLAG_AMT_TRAN' data_type,\n"
                + "               'Deductee 15GH Amount Transaction(s)' data_type_name,\n"
                + "               lhs_tds.get_tds_reco_details('" + l_entity_code + "',\n"
                + "                                            '" + l_client_code + "',\n"
                + "                                            '" + asmt.getAccYear() + "',\n"
                + "                                            '" + asmt.getQuarterNo() + "',\n"
                + "                                            null,\n"
                + "                                            null,\n"
                + "                                            '" + asmt.getTdsTypeCode() + "',\n"
                + "                                            null,\n"
                + "                                            'DEDUCTEE_BFLAG_AMT_TRAN',\n"
                + "                                            'NO_OF_DEDUCTEE') rec_count\n"
                + "          from dual) ";

        return l_query;
    }//end method

    public String getFirstScreenErrorDetails15GHQuery(Assessment asmt, String l_entity_code, String l_client_code, String ertp, String l_process_level) {

        String processLevelWhereCls1 = "";
        if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("0")) { // for login level
            processLevelWhereCls1 = " and t.client_code = '" + l_client_code + "' \n";

        } else if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("1")) { // for all level
            processLevelWhereCls1 = " and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                    + "                w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                    + "                w1.ssg_parent_code = '" + l_client_code + "' or\n"
                    + "                w1.sssg_parent_code = '" + l_client_code + "') \n";
        }

        String processLevelWhereCls2 = "";
        if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("0")) { // for login level
            processLevelWhereCls2 = " and a.client_code = '" + l_client_code + "' \n";

        } else if (l_process_level != null && !l_process_level.isEmpty() && l_process_level.equalsIgnoreCase("1")) { // for all level
            processLevelWhereCls2 = " and (cm.client_code = '" + l_client_code + "' or cm.parent_code = '" + l_client_code + "' or\n"
                    + "                        cm.g_parent_code = '" + l_client_code + "' or\n"
                    + "                        cm.sg_parent_code = '" + l_client_code + "' or\n"
                    + "                        cm.ssg_parent_code = '" + l_client_code + "' or\n"
                    + "                        cm.sssg_parent_code = '" + l_client_code + "') \n";
        }

        String l_query
                = "select t.quarter_no,\n"
                + "        t.tds_type_code,\n"
                + "        t.error_type_code,\n"
                + "        t.error_type_name,\n"
                + "        t.table_name,\n"
                + "        t.column_name,\n"
                + "        t.error_code,\n"
                + "        t.error_name,\n"
                + "        t.popup_shown,\n"
                + "        t.updation_allow_flag,\n"
                + "        t.show_detail_required,\n"
                + "        t.bulk_pan_verification_flag,\n"
                + "        t.error_reviewed,\n"
                + "        t.record_count ,t.review_required\n"
                + "from (        \n"
                + "select t.quarter_no,\n"
                + "        t.tds_type_code,\n"
                + "        t.error_type_code,\n"
                + "        t.error_type_name,\n"
                + "        t.table_name,\n"
                + "        t.column_name,\n"
                + "        t.error_code,\n"
                + "        t.error_name,\n"
                + "        t.popup_shown,\n"
                + "        t.updation_allow_flag,\n"
                + "        t.show_detail_required,\n"
                + "        t.bulk_pan_verification_flag,\n"
                + "        sign(tlm.rec) error_reviewed,\n"
                + "        sum(nvl(t.record_count, 0)) record_count,t.review_required\n"
                + "from (        \n"
                + "select  t.entity_code,\n"
                + "        t.client_code,\n"
                + "        t.acc_year,\n"
                + "        t.quarter_no,\n"
                + "        t.tds_type_code,\n"
                + "        t.error_type_code,\n"
                + "        t.error_type_name,\n"
                + "        t.table_name,\n"
                + "        t.column_name,\n"
                + "        t.error_code,\n"
                + "        t.error_name,\n"
                + "        t.popup_shown,\n"
                + "        t.updation_allow_flag,\n"
                + "        t.show_detail_required,\n"
                + "        t.bulk_pan_verification_flag,\n"
                + "        sum(nvl(t.record_count, 0)) record_count,t.review_required\n"
                + "   from (select t.entity_code,\n"
                + "                t.client_code,\n"
                + "                t.acc_year,\n"
                + "                t.quarter_no,\n"
                + "                t.tds_type_code,\n"
                + "                t.error_type_code,\n"
                + "                t.error_type_name,\n"
                + "                t.table_name,\n"
                + "                t.column_name,\n"
                + "                t.error_code,\n"
                + "                vlm.error_description error_name,\n"
                + "                vlm.popup_shown,\n"
                + "                vlm.updation_allow_flag,\n"
                + "                vlm.show_detail_required,\n"
                + "                vlm.bulk_pan_verification_flag,\n"
                + "                vlm.review_required,\n"
                + "                nvl(t.record_count, 0) record_count\n"
                + "           from tran_load_error_15gh_part2 t,\n"
                + "                view_lhssys_error_mast_table vlm,\n"
                + "                client_mast w1\n"
                + "          where vlm.error_code = t.error_code\n"
                + "          and w1.entity_code = t.entity_code\n"
                + "            and w1.client_code = t.client_code\n"
                + processLevelWhereCls1
                + "            and t.entity_code = '" + l_entity_code + "'\n";

        if (!utl.isnull(asmt.getAccYear()) && !asmt.getAccYear().equalsIgnoreCase("all")) {
            l_query += " and t.acc_year = '" + asmt.getAccYear() + "'\n";
        }
        if (!utl.isnull(asmt.getQuarterNo()) && !asmt.getQuarterNo().equalsIgnoreCase("all")) {
            l_query += " and t.quarter_no = '" + asmt.getQuarterNo() + "'\n";
        }
        if (!utl.isnull(asmt.getTdsTypeCode()) && !asmt.getTdsTypeCode().equalsIgnoreCase("all")) {
            l_query += " and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n";
        }

        l_query += "     and t.error_type_code = '" + ertp + "') t\n"
                + "  group by t.entity_code,\n"
                + "        t.client_code,\n"
                + "        t.acc_year,\n"
                + "        t.quarter_no,\n"
                + "           t.tds_type_code,\n"
                + "           t.error_type_code,\n"
                + "           t.error_type_name,\n"
                + "           t.error_code,\n"
                + "           t.error_name,\n"
                + "           t.popup_shown,\n"
                + "           t.updation_allow_flag,\n"
                + "           t.show_detail_required,\n"
                + "           t.bulk_pan_verification_flag,\n"
                + "           t.review_required,\n"
                + "           t.table_name,\n"
                + "           t.column_name\n"
                + ") t, (select a.entity_code,\n"
                + "                        a.client_code,\n"
                + "                        a.acc_year,\n"
                + "                        a.quarter_no,\n"
                + "                        a.tds_type_code,\n"
                + "                        a.error_code,\n"
                + "                        sum(decode(a.error_review_remark, null, -1, 1)) rec\n"
                + "                   from tran_load_error_15gh a, client_mast cm\n"
                + "                  where cm.entity_code = a.entity_code\n"
                + "                    and cm.client_code = a.client_code\n"
                + processLevelWhereCls2
                + "                    and a.entity_code = '" + l_entity_code + "'\n";

        if (!utl.isnull(asmt.getAccYear()) && !asmt.getAccYear().equalsIgnoreCase("all")) {
            l_query += " and a.acc_year = '" + asmt.getAccYear() + "'\n";
        }
        if (!utl.isnull(asmt.getQuarterNo()) && !asmt.getQuarterNo().equalsIgnoreCase("all")) {
            l_query += " and a.quarter_no = '" + asmt.getQuarterNo() + "'\n";
        }
        if (!utl.isnull(asmt.getTdsTypeCode()) && !asmt.getTdsTypeCode().equalsIgnoreCase("all")) {
            l_query += " and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n";
        }
        
        l_query += "                    --and a.error_review_remark is null\n"
                + "                  group by a.entity_code,\n"
                + "                           a.client_code,\n"
                + "                           a.acc_year,\n"
                + "                           a.quarter_no,\n"
                + "                           a.tds_type_code,\n"
                + "                           a.error_code) tlm\n"
                + "where  tlm.entity_code = t.entity_code\n"
                + "            and tlm.client_code = t.client_code\n"
                + "            and tlm.acc_year = t.acc_year\n"
                + "            and tlm.quarter_no = t.quarter_no\n"
                + "            and tlm.tds_type_code = t.tds_type_code\n"
                + "            and tlm.error_code = t.error_code \n"
                + "group by t.quarter_no,\n"
                + "        t.tds_type_code,\n"
                + "        t.error_type_code,\n"
                + "        t.error_type_name,\n"
                + "        t.table_name,\n"
                + "        t.column_name,\n"
                + "        t.error_code,\n"
                + "        t.error_name,\n"
                + "        t.popup_shown,\n"
                + "        t.updation_allow_flag,\n"
                + "        t.show_detail_required,\n"
                + "        t.bulk_pan_verification_flag,t.review_required,\n"
                + "        sign(tlm.rec)\n"
                + ") t";

        return l_query;
    }//end method

    public String getReviewUpdate15GHQuery(Assessment asmt, String l_entity_code, String l_client_code, String reviewValue,
            String ertp) {

        String l_query
                = " update tran_load_error_15gh a\n"
                + "   set a.error_review_flag   = 'Y',\n"
                + "       a.error_review_on     = sysdate,\n"
                + "       a.error_review_remark = '" + reviewValue + "'\n"
                + " where entity_code = '" + l_entity_code + "'\n"
                + "   and exists (select 1\n"
                + "          from client_mast w1\n"
                + "         where w1.client_code = a.client_code\n"
                + "           and (w1.client_code = '" + l_client_code + "' or\n"
                + "               w1.parent_code = '" + l_client_code + "' or\n"
                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sssg_parent_code = '" + l_client_code + "'))\n"
                + "   and acc_year = '" + asmt.getAccYear() + "'\n"
                + "   and quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and error_code = '" + ertp + "' ";

        return l_query;
    }//end method

    public String getSecondScreenDeducteeErrCount15GHQuery(Assessment asmt, String l_entity_code, String l_client_code, String ertp) {
        String l_query
                = "select count(*)\n"
                + "  from (select t.deductee_code,\n"
                + "               t.deductee_name,\n"
                + "               t.dob,\n"
                + "               t.reference_no,\n"
                + "               t.deductee_panno panno,\n"
                + "               t.column_name,\n"
                + "               t.column_data\n"
                + "          from tran_load_error_15gh_part2 t, client_mast w1\n"
                + "         where w1.client_code = t.client_code\n"
                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sssg_parent_code = '" + l_client_code + "')\n"
                + "           and t.entity_code = '" + l_entity_code + "'\n"
                + "           and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "           and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           and t.error_code = '" + ertp + "')\n";

        return l_query;
    }//end method

    public String get15GHColumnNameQuery(Assessment asmt, String l_entity_code, String l_client_code, String errorCode) {
        String l_query
                = "select distinct (t.column_name)column_name\n"
                + "  from tran_load_error_15gh_part2 t, client_mast w1\n"
                + " where w1.client_code = t.client_code\n"
                + "   and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                + "       w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "       w1.ssg_parent_code = '" + l_client_code + "' or w1.sssg_parent_code = '" + l_client_code + "')\n"
                + "   and t.entity_code = '" + l_entity_code + "'\n"
                + "   and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "   and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and t.error_code = '" + errorCode + "'";

        return l_query;
    }//end method

    public String get15GHDeducteeDetailsQuery(Assessment asmt, String l_entity_code, String l_client_code, String errorCode, int l_record_MNL, int l_record_MXL) {
        String l_query
                = "select *\n"
                + "  from (select rownum slno,\n"
                + "               t.deductee_code,\n"
                + "               t.deductee_name,\n"
                + "               to_char(t.dob,'dd-MON-yyyy')dob,\n"
                + "               t.reference_no,\n"
                + "               t.deductee_panno panno,\n"
                + "               t.column_name,\n"
                + "               t.column_data,\n"
                + "                w1.bank_branch_code\n"
                + "          from tran_load_error_15gh_part2 t, client_mast w1\n"
                + "         where w1.client_code = t.client_code\n"
                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sssg_parent_code = '" + l_client_code + "')\n"
                + "           and t.entity_code = '" + l_entity_code + "'\n"
                + "           and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "           and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           and t.error_code = '" + errorCode + "')\n"
                + " where slno between " + l_record_MNL + " and " + l_record_MXL + "";

        return l_query;
    }//end method

    public String excelErrorSummaryQuery15GH(Assessment asmt, String l_entity_code, String l_client_code) {
        String l_query
                = "select rownum slno,nvl(error_code || '-'|| error_name,'')error, RECORD_COUNT from (SELECT T.QUARTER_NO,\n"
                + "       T.TDS_TYPE_CODE,\n"
                + "       T.ERROR_TYPE_CODE,\n"
                + "       T.ERROR_TYPE_NAME,\n"
                + "       T.TABLE_NAME,\n"
                + "       t.COLUMN_NAME,\n"
                + "       T.ERROR_CODE,\n"
                + "       T.ERROR_NAME,\n"
                + "       T.POPUP_SHOWN,\n"
                + "       T.UPDATION_ALLOW_FLAG,\n"
                + "       T.SHOW_DETAIL_REQUIRED,\n"
                + "       T.BULK_PAN_VERIFICATION_FLAG,\n"
                + "       T.REVIEW_REQUIRED,\n"
                + "       SUM(NVL(T.RECORD_COUNT, 0)) RECORD_COUNT,\n"
                + "       ERROR_REVIEWED\n"
                + "  FROM (SELECT T.ENTITY_CODE,\n"
                + "               T.CLIENT_CODE,\n"
                + "               T.ACC_YEAR,\n"
                + "               T.QUARTER_NO,\n"
                + "               T.TDS_TYPE_CODE,\n"
                + "               T.ERROR_TYPE_CODE,\n"
                + "               T.ERROR_TYPE_NAME,\n"
                + "               T.TABLE_NAME,\n"
                + "               t.COLUMN_NAME,\n"
                + "               T.ERROR_CODE,\n"
                + "               VLM.ERROR_DESCRIPTION ERROR_NAME,\n"
                + "               VLM.POPUP_SHOWN,\n"
                + "               VLM.UPDATION_ALLOW_FLAG,\n"
                + "               VLM.SHOW_DETAIL_REQUIRED,\n"
                + "               VLM.BULK_PAN_VERIFICATION_FLAG,\n"
                + "               VLM.REVIEW_REQUIRED,\n"
                + "               LHS_UTILITY.IS_TRAN_LOAD_ERROR_REVIEWED(T.ENTITY_CODE,T.CLIENT_CODE,T.ACC_YEAR,T.QUARTER_NO,T.TDS_TYPE_CODE,T.ERROR_CODE) ERROR_REVIEWED,\n"
                + "               NVL(T.RECORD_COUNT, 0) RECORD_COUNT\n"
                + "          FROM TRAN_LOAD_ERROR_15GH_PART2 T, VIEW_LHSSYS_ERROR_MAST_TABLE VLM, CLIENT_MAST W1\n"
                + "          WHERE W1.CLIENT_CODE = T.CLIENT_CODE\n"
                + "          AND   (W1.CLIENT_CODE='" + l_client_code + "' OR\n"
                + "                 W1.PARENT_CODE='" + l_client_code + "' OR\n"
                + "                 W1.G_PARENT_CODE='" + l_client_code + "' OR\n"
                + "                 W1.SG_PARENT_CODE='" + l_client_code + "' OR\n"
                + "                 W1.SSG_PARENT_CODE='" + l_client_code + "' OR\n"
                + "                 W1.SSSG_PARENT_CODE='" + l_client_code + "')\n"
                + "   AND VLM.ERROR_CODE=T.ERROR_CODE\n"
                + "   AND T.ENTITY_CODE = '" + l_entity_code + "'\n"
                + "   AND T.ACC_YEAR = '" + asmt.getAccYear() + "'\n"
                + "   AND T.QUARTER_NO = '" + asmt.getQuarterNo() + "'\n"
                + "   AND T.TDS_TYPE_CODE = '" + asmt.getTdsTypeCode() + "'\n"
                + "          ) T\n"
                + " GROUP BY T.QUARTER_NO,\n"
                + "          T.TDS_TYPE_CODE,\n"
                + "          T.ERROR_TYPE_CODE,\n"
                + "          T.ERROR_TYPE_NAME,\n"
                + "          T.ERROR_CODE,\n"
                + "          T.ERROR_NAME,\n"
                + "          T.POPUP_SHOWN,\n"
                + "          T.UPDATION_ALLOW_FLAG,\n"
                + "          T.SHOW_DETAIL_REQUIRED,\n"
                + "          T.BULK_PAN_VERIFICATION_FLAG,\n"
                + "          T.REVIEW_REQUIRED,\n"
                + "           t.ERROR_REVIEWED,\n"
                + "          T.TABLE_NAME,\n"
                + "          T.COLUMN_NAME\n"
                + "ORDER BY T.ERROR_CODE,TDS_TYPE_CODE)";

        return l_query;
    }//end method

    public String excelErrorDetailQuery15GH(Assessment asmt, String l_entity_code, String l_client_code) {
        String l_query
                = "select rownum slno,\n"
                + "               (t.entity_code || '-' || t.error_name) error,\n"
                + "               t.deductee_name,\n"
                + "               t.deductee_panno panno,\n"
                + "               t.reference_no,\n"
                + "               to_char(t.dob,'dd-mm-yyyy')dob,\n"
                + "               t.column_name,\n"
                + "               t.column_data\n"
                + "          from tran_load_error_15gh_part2 t, client_mast w1\n"
                + "         where w1.client_code = t.client_code\n"
                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sssg_parent_code = '" + l_client_code + "')\n"
                + "           and t.entity_code = '" + l_entity_code + "'\n"
                + "           and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "           and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'";

        return l_query;
    }//end method

    public String excelDeducteeErrorDetailQuery15GH(Assessment asmt, String l_entity_code, String l_client_code, String l_error_code) {
        String l_query
                = "select rownum slno,\n"
                + "               t.deductee_name,\n"
                + "               t.deductee_panno panno,\n"
                + "               t.reference_no,\n"
                + "               to_char(t.dob,'dd-mm-yyyy')dob,\n"
                + "               t.column_data,\n"
                + "               w1.bank_branch_code\n"
                + "          from tran_load_error_15gh_part2 t, client_mast w1\n"
                + "         where w1.client_code = t.client_code\n"
                + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                + "               w1.g_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.ssg_parent_code = '" + l_client_code + "' or\n"
                + "               w1.sssg_parent_code = '" + l_client_code + "')\n"
                + "           and t.entity_code = '" + l_entity_code + "'\n"
                + "           and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "           and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           and t.error_code = '" + l_error_code + "'";

        return l_query;
    }//end method

    public String updateDefaultValueQuery15GH(Assessment asmt, String l_entity_code, String l_client_code, String l_error_code, String defaultColumnName,
            String defaultColumnValue, Util utl) {

        String updateDefaultValueQuery = "";
        if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-ZDE-20030")) {
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
                    + "   and b.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "   and b.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                    + "   and b.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "   and nvl(b.amount, 0) < 0\n"
                    + "   and exists (select 1\n"
                    + "          from tran_load_error_15gh c\n"
                    + "         where c.entity_code = b.entity_code\n"
                    + "           and c.client_code = b.client_code\n"
                    + "           and c.acc_year = b.acc_year\n"
                    + "           and c.quarter_no = b.quarteR_no\n"
                    + "           and c.tds_type_code = b.tds_type_code\n"
                    + "           and c.rowid_seq = b.deductee_mast_15gh_rowid_seq\n"
                    + "           and c.error_code = '" + l_error_code + "'\n"
                    + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";

        } else if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-ZDE-20029")) {
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
                    + "   and b.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "   and b.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                    + "   and b.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "   and exists (select 1\n"
                    + "          from tran_load_error_15gh c\n"
                    + "         where c.entity_code = b.entity_code\n"
                    + "           and c.client_code = b.client_code\n"
                    + "           and c.acc_year = b.acc_year\n"
                    + "           and c.quarter_no = b.quarteR_no\n"
                    + "           and c.tds_type_code = b.tds_type_code\n"
                    + "           and c.rowid_seq = b.rowid_seq\n"
                    + "           and c.error_code = '" + l_error_code + "'\n"
                    + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";
        } else {
            String l_value = "'" + defaultColumnValue + "'";
            if (!utl.isnull(defaultColumnName) && defaultColumnName.equalsIgnoreCase("dob") || defaultColumnName.equalsIgnoreCase("INCOME_PAID_DATE") || defaultColumnName.equalsIgnoreCase("declaration_date")) {
                l_value = "to_date('" + defaultColumnValue + "','dd-mm-rrrr')";
            }
            updateDefaultValueQuery = "update deductee_mast_15gh b\n"
                    + "   set " + defaultColumnName + " = " + l_value + "\n"
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
                    + "   and b.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "   and b.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                    + "   and b.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "   and exists (select 1\n"
                    + "          from tran_load_error_15gh c\n"
                    + "         where c.entity_code = b.entity_code\n"
                    + "           and c.client_code = b.client_code\n"
                    + "           and c.acc_year = b.acc_year\n"
                    + "           and c.quarter_no = b.quarteR_no\n"
                    + "           and c.tds_type_code = b.tds_type_code\n"
                    + "           and c.rowid_seq = b.rowid_seq\n"
                    + "           and c.error_code = '" + l_error_code + "'\n"
                    + "           and c.table_name = 'DEDUCTEE_MAST_15GH')";
        }

        return updateDefaultValueQuery;
    }//end method

    public String delete15GHErrorQuery(Assessment asmt, String l_entity_code, String l_client_code, String l_error_code) {

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
                + "   and a.acc_year = '" + asmt.getAccYear() + "'\n"
                + "   and a.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and a.error_code = '" + l_error_code + "'";

        return delete_15gh_error_query;
    }//end method

    public String deleteTranLoadError15GHQuery(Assessment asmt, String l_entity_code, String l_client_code, String l_error_code) {

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
                + "   and a.acc_year = '" + asmt.getAccYear() + "'\n"
                + "   and a.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and a.error_code = '" + l_error_code + "'";

        return delete_tran_load_error_15gh_query;
    }//end method    
}//end class
