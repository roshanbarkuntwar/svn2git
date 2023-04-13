/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import regular.challan.ChallanFilterEntity;

/**
 *
 * @author akash.meshram
 *
 */
public class ChallanAllocationDB {

    public String challanCountRecordQuery(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, String paraRowIdSeq,
            ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, Util utl) {
        String count_record_query
                = "select count (*)\n"
                + "     from \n"
                + "    ( select\n"
                + "a.rowid_seq,\n"
                + "a.entity_code,\n"
                + "a.client_code,\n"
                + "cm.parent_code,\n"
                + "cm.g_parent_code,\n"
                + "cm.sg_parent_code,\n"
                + "cm.ssg_parent_code,\n"
                + "cm.sssg_parent_code,\n"
                + "cm.bank_branch_code,\n"
                + "cm.client_name,\n"
                + "cm.client_type_code client_type_code,\n"
                + "a.acc_year,\n"
                + "a.assesment_acc_year,\n"
                + "a.quarter_no,\n"
                + "a.month,\n"
                + "a.from_date,\n"
                + "a.to_date,\n"
                + "a.data_entry_mode,\n"
                + "a.file_doc_code,\n"
                + "a.upload_template_code,\n"
                + "a.client_panno,\n"
                + "a.client_tanno,\n"
                + "a.deductee_code,\n"
                + "a.deductee_name,\n"
                + "a.deductee_panno,\n"
                + "decode(substr(A.DEDUCTEE_PANNO, 4, 1), 'C', 'C', 'O') pan_4_char_C_NC,\n"
                + "a.dob,\n"
                + "a.tds_type_code,\n"
                + "a.tds_code,\n"
                + "B.TDS_NAME tds_section,\n"
                + "B.GOVT_TDS_CODE,\n"
                + "a.tran_ref_no,\n"
                + "a.tran_ref_date,\n"
                + "a.tran_amt,\n"
                + "a.tds_deduct_date,\n"
                + "a.tds_deduct_reason,\n"
                + "(select y.tds_deduct_reason_name from  view_tds_deduct_reason y where  y.tds_deduct_reason = a.tds_deduct_reason  and y.tds_type_code_str like ('%'||a.tds_type_code||'%'))tds_deduct_reason_name,\n"
                + "--(select y.tds_deduct_reason_name from  view_tds_deduct_reason y where  y.tds_deduct_reason = a.tds_deduct_reason )tds_deduct_reason_name\n"
                + "a.tds_rate,\n"
                + "a.cess_rate,\n"
                + "a.surcharge_rate,\n"
                + "a.tds_base_amt,\n"
                + "a.tds_amt,\n"
                + "a.cess_amt,\n"
                + "a.hecess_amt,\n"
                + "a.surcharge_amt,\n"
                + "(nvl(a.tds_amt,0)+nvl(a.surcharge_amt,0)+nvl(a.cess_amt,0)) total_tds_amt,\n"
                + "a.tds_error_status1,\n"
                + "a.tds_error_status2,\n"
                + "a.tds_error_status3,\n"
                + "a.tds_error_status4,\n"
                + "a.tds_error_status5,\n"
                + "a.tds_error_status6,\n"
                + "a.tds_error_status7,\n"
                + "a.deductee_ref_code1,\n"
                + "a.deductee_ref_code2,\n"
                + "a.file_seqno,\n"
                + "a.user_code,\n"
                + "a.lastupdate,\n"
                + "a.certificate_no,\n"
                + "a.flag,a.itax_rate, a.tds_challan_rowid_seq, (SELECT C.tds_challan_tds_amt FROM TDS_CHALLAN_TRAN_LOAD C WHERE C.rowid_seq = A.tds_challan_rowid_seq) tds_challan_tds_amt,\n"
                + "--(select d.deductee_catg from deductee_mast d where d.deductee_code = a.deductee_code) deductee_catg\n"
                + "decode(a.tds_type_code, '24Q', 'E', 'O') Deductee_Catg,\n"
                + "a.account_no,\n"
                + "a.deductee_ref_code1 identification_no,\n"
                + "a.validation_client_code,'TAXCPC_DB_0412211015' taxcpc_db_ver\n"
                + "from tds_tran_load a, tds_mast b, client_mast cm\n"
                + "where a.tds_code=b.tds_code(+)\n"
                + "and cm.entity_code=a.entity_code\n"
                + "and cm.client_code=a.client_code";

        //System.out.println("searchhhh---" + search);
        if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                    if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                        count_record_query += " and( a.tds_challan_rowid_seq =" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                        count_record_query += "OR a.tds_challan_rowid_seq is null )";

                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {
                        count_record_query += " and a.tds_challan_rowid_seq =" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                        count_record_query += " and a.tds_challan_rowid_seq is null ";
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                        count_record_query += " and a.tds_code =" + mapTdsChallanFilterSrchData.getTds_section().trim();//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getDeductee_name()..." + mapTdsChallanFilterSrchData.getDeductee_name());
                        count_record_query += " and a.deductee_name ='" + mapTdsChallanFilterSrchData.getDeductee_name() + "'";//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
                        System.out.println("mapTdsChallanFilterSrchData.getPan_no()..." + mapTdsChallanFilterSrchData.getPan_no());
                        count_record_query += " and a.deductee_panno ='" + mapTdsChallanFilterSrchData.getPan_no() + "'";//deductee_panno
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {

                        count_record_query += " and TO_DATE(tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')";
                    } else if (!utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        count_record_query += " and TO_DATE(tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')";
                    } else if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        count_record_query += "and TO_DATE(tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')";
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                        if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                            count_record_query += " and to_number(a.tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                            count_record_query += " and to_number(a.tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                            count_record_query += " and to_number(a.tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                            count_record_query += " and to_number(a.tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to();
                        }
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                        count_record_query += " and a.pan_4_char_c_nc ='" + mapTdsChallanFilterSrchData.getPan_4th_char()+"'";//on dropdown value is tds_code
                    }

                    // New added filters--from transaction browse page
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeRefNo())) {
                        count_record_query += " and a.deductee_ref_code1 ='" + mapTdsChallanFilterSrchData.getDeducteeRefNo()+"'";// Deductee Ref No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getAccountNo())) {
                        count_record_query += " and a.account_no ='" + mapTdsChallanFilterSrchData.getAccountNo() + "'";// Account No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getBankBranchCode())) {
                        count_record_query += " and cm.bank_branch_code ='" + mapTdsChallanFilterSrchData.getBankBranchCode() + "'";// Bank Branch Code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTdsDeductReason())) {
                        count_record_query += " and a.tds_deduct_reason ='" + mapTdsChallanFilterSrchData.getTdsDeductReason() + "'";// Tds Deduct Reason
                    }

                } else {
//                        System.out.println("mapTdsChallanFilterSrchData.getParaRowidSeq()..." + mapTdsChallanFilterSrchData.getParaRowidSeq());
                    count_record_query += " and( a.tds_challan_rowid_seq=" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                    count_record_query += "or a.tds_challan_rowid_seq is null )";

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                        count_record_query += " and a.mapTdsChallanFilterSrchData.getTds_section()";//on dropdown value is tds_code
                    }
                }
            }
        }//end if

        if (mapTdsChallanFilterSrchData != null) {
            if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                count_record_query += " and a.validation_client_code ='" + l_workinguser+"'";
            } else {
                count_record_query += " and exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = a.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))";
            }
        } else {
            count_record_query += " and( exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = a.client_code\n"
                    + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                    + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                    + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))";
        }
        count_record_query += ")";
        System.out.println("count_record_query--  " + count_record_query);
        return count_record_query;
    }

    public String challanRecordQuery(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code,
            ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, int minVal, int maxVal, Util utl) {
        String record_detail_query = "select *\n"
                + "     from \n"
                + " (select row_number() over (order by 1) SLNO,\n"
                + "a.rowid_seq,\n"
                + "a.entity_code,\n"
                + "a.client_code,\n"
                + "cm.parent_code,\n"
                + "cm.g_parent_code,\n"
                + "cm.sg_parent_code,\n"
                + "cm.ssg_parent_code,\n"
                + "cm.sssg_parent_code,\n"
                + "cm.bank_branch_code,\n"
                + "cm.client_name,\n"
                + "cm.client_type_code client_type_code,\n"
                + "a.acc_year,\n"
                + "a.assesment_acc_year,\n"
                + "a.quarter_no,\n"
                + "a.month,\n"
                + "a.from_date,\n"
                + "a.to_date,\n"
                + "a.data_entry_mode,\n"
                + "a.file_doc_code,\n"
                + "a.upload_template_code,\n"
                + "a.client_panno,\n"
                + "a.client_tanno,\n"
                + "a.deductee_code,\n"
                + "a.deductee_name,\n"
                + "a.deductee_panno,\n"
                + "decode(substr(A.DEDUCTEE_PANNO, 4, 1), 'C', 'C', 'O') pan_4_char_C_NC,\n"
                + "a.dob,\n"
                + "a.tds_type_code,\n"
                + "a.tds_code,\n"
                + "B.TDS_NAME tds_section,\n"
                + "B.GOVT_TDS_CODE,\n"
                + "a.tran_ref_no,\n"
                + "a.tran_ref_date,\n"
                + "a.tran_amt,\n"
                + "a.tds_deduct_date,\n"
                + "a.tds_deduct_reason,\n"
                + "(select y.tds_deduct_reason_name from  view_tds_deduct_reason y where  y.tds_deduct_reason = a.tds_deduct_reason  and y.tds_type_code_str like ('%'||a.tds_type_code||'%'))tds_deduct_reason_name,\n"
                + "--(select y.tds_deduct_reason_name from  view_tds_deduct_reason y where  y.tds_deduct_reason = a.tds_deduct_reason )tds_deduct_reason_name\n"
                + "a.tds_rate,\n"
                + "a.cess_rate,\n"
                + "a.surcharge_rate,\n"
                + "a.tds_base_amt,\n"
                + "a.tds_amt,\n"
                + "a.cess_amt,\n"
                + "a.hecess_amt,\n"
                + "a.surcharge_amt,\n"
                + "(nvl(a.tds_amt,0)+nvl(a.surcharge_amt,0)+nvl(a.cess_amt,0)) total_tds_amt,\n"
                + "a.tds_error_status1,\n"
                + "a.tds_error_status2,\n"
                + "a.tds_error_status3,\n"
                + "a.tds_error_status4,\n"
                + "a.tds_error_status5,\n"
                + "a.tds_error_status6,\n"
                + "a.tds_error_status7,\n"
                + "a.deductee_ref_code1,\n"
                + "a.deductee_ref_code2,\n"
                + "a.file_seqno,\n"
                + "a.user_code,\n"
                + "a.lastupdate,\n"
                + "a.certificate_no,\n"
                + "a.flag,a.itax_rate, a.tds_challan_rowid_seq, (SELECT C.tds_challan_tds_amt FROM TDS_CHALLAN_TRAN_LOAD C WHERE C.rowid_seq = A.tds_challan_rowid_seq) tds_challan_tds_amt,\n"
                + "--(select d.deductee_catg from deductee_mast d where d.deductee_code = a.deductee_code) deductee_catg\n"
                + "decode(a.tds_type_code, '24Q', 'E', 'O') Deductee_Catg,\n"
                + "a.account_no,\n"
                + "a.deductee_ref_code1 identification_no,\n"
                + "a.validation_client_code,'TAXCPC_DB_0412211015' taxcpc_db_ver\n"
                + "from tds_tran_load a, tds_mast b, client_mast cm\n"
                + "where a.tds_code=b.tds_code(+)\n"
                + "and cm.entity_code=a.entity_code\n"
                + "and cm.client_code=a.client_code";

        if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                    if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                        record_detail_query += " and( a.tds_challan_rowid_seq =" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                        record_detail_query += "OR a.tds_challan_rowid_seq is null )";

                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {
                        record_detail_query += " and a.tds_challan_rowid_seq =" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                        record_detail_query += " and a.tds_challan_rowid_seq is null ";
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                        record_detail_query += " and a.tds_code =" + mapTdsChallanFilterSrchData.getTds_section().trim();//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getDeductee_name()..." + mapTdsChallanFilterSrchData.getDeductee_name());
                        record_detail_query += " and a.deductee_name ='" + mapTdsChallanFilterSrchData.getDeductee_name() + "'";//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getPan_no()..." + mapTdsChallanFilterSrchData.getPan_no());
                        record_detail_query += " and a.deductee_panno ='" + mapTdsChallanFilterSrchData.getPan_no() + "'";//deductee_panno
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {

                        record_detail_query += " and TO_DATE(tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')";
                    } else if (!utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        record_detail_query += " and TO_DATE(tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')";
                    } else if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        record_detail_query += " and TO_DATE(tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')";
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                        if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                            record_detail_query += " and to_number(a.tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                            record_detail_query += " and to_number(a.tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                            record_detail_query += " and to_number(a.tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from();
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                            record_detail_query += " and to_number(a.tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to();
                        }
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                        record_detail_query += " and a.pan_4_char_c_nc ='" + mapTdsChallanFilterSrchData.getPan_4th_char()+"'";//on dropdown value is tds_code
                    }

                    // New added filters--from transaction browse page
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeRefNo())) {
                        record_detail_query += " and a.deductee_ref_code1 ='" + mapTdsChallanFilterSrchData.getDeducteeRefNo()+"'";// Deductee Ref No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getAccountNo())) {
                        record_detail_query += " and a.account_no ='" + mapTdsChallanFilterSrchData.getAccountNo() + "'";// Account No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getBankBranchCode())) {
                        record_detail_query += " and cm.bank_branch_code ='" + mapTdsChallanFilterSrchData.getBankBranchCode() + "'";// Bank Branch Code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTdsDeductReason())) {
                        record_detail_query += " and a.tds_deduct_reason ='" + mapTdsChallanFilterSrchData.getTdsDeductReason() + "'";// Tds Deduct Reason
                    }

                } else {
//                        System.out.println("mapTdsChallanFilterSrchData.getParaRowidSeq()..." + mapTdsChallanFilterSrchData.getParaRowidSeq());
                    record_detail_query += " and( a.tds_challan_rowid_seq=" + mapTdsChallanFilterSrchData.getParaRowidSeq();
                    record_detail_query += "or a.tds_challan_rowid_seq is null )";

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                        record_detail_query += " and a.mapTdsChallanFilterSrchData.getTds_section()";//on dropdown value is tds_code
                    }
                }
            }
        }//end if

        if (mapTdsChallanFilterSrchData != null) {
            if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                record_detail_query += " and a.validation_client_code ='" + l_workinguser+"'";
            } else {
                record_detail_query += " and exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = a.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))";
            }
        } else {
            record_detail_query += " and( exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = a.client_code\n"
                    + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                    + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                    + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))";
        }
        record_detail_query += ") where slno BETWEEN " + minVal + " AND " + maxVal;

        System.out.println("datalistquery--" + record_detail_query);
        return record_detail_query;

    }

}
