/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author ayushi.jain
 */
public class ChallanDB {

    public String challanCountRecordQuery(ViewClientMast client, Assessment asmt) {
        String count_record_query = "select count(*) from (select  rownum slno, a.bank_bsr_code,\n"
                + "         a.tds_challan_date,\n"
                + "         a.tds_challan_no,\n"
                + "         a.tds_challan_tds_amt,\n"
                + "         a.tds_challan_other_amt,\n"
                + "         a.tds_challan_int_amt,\n"
                + "         a.deductee_ref_code1,\n"
                + "         a.deductee_panno,\n"
                + "         a.deductee_name,\n"
                + "         a.account_no,\n"
                + "         a.tds_name tds_section,\n"
                + "         a.tran_amt,\n"
                + "         a.tran_ref_date,\n"
                + "         a.tds_deduct_date,\n"
                + "         a.tds_amt,\n"
                + "         a.tds_deduct_reason,\n"
                + "         a.certificate_no,\n"
                + "         a.country_code,\n"
                + "         a.rate_type,\n"
                + "         a.nature_of_remittance,\n"
                + "         a.tin_uin_no,\n"
                + "         a.deductee_address,\n"
                + "         a.deductee_email_id,\n"
                + "         a.deductee_phoneno,\n"
                + "         a.bank_branch_code,\n"
                + "         a.challan_bank_branch_code,\n"
                + "         a.tds_challan_rowid_seq,\n"
                + "         null filler2,\n"
                + "         null filler3,\n"
                + "         null filler4,\n"
                + "         null filler5,\n"
                + "         null filler6\n"
                + " from    view_tran_data_excel_all a ";

        count_record_query += " where (a.client_code = '" + client.getClient_code() + "' OR a.parent_code = '" + client.getClient_code() + "' OR\n"
                + "               a.g_parent_code = '" + client.getClient_code() + "' OR a.sg_parent_code = '" + client.getClient_code() + "' OR\n"
                + "               a.ssg_parent_code = '" + client.getClient_code() + "' OR a.sssg_parent_code = '" + client.getClient_code() + "')\n"
                + "           and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           and a.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and a.quarter_no = '" + asmt.getQuarterNo() + "') ";

        return count_record_query;
    }

    public String challanRecordQuery(ViewClientMast client, Assessment asmt, int minVal, int maxVal) {
        String record_detail_query = "select bank_bsr_code,\n"
                + "        tds_challan_date,\n"
                + "         tds_challan_no,\n"
                + "         tds_challan_tds_amt,\n"
                + "         tds_challan_other_amt,\n"
                + "         tds_challan_int_amt from ( select  rownum slno, a.bank_bsr_code,\n"
                + "         a.tds_challan_date,\n"
                + "         a.tds_challan_no,\n"
                + "         a.tds_challan_tds_amt,\n"
                + "         a.tds_challan_other_amt,\n"
                + "         a.tds_challan_int_amt,\n"
                + "         a.deductee_ref_code1,\n"
                + "         a.deductee_panno,\n"
                + "         a.deductee_name,\n"
                + "         a.account_no,\n"
                + "         a.tds_name tds_section,\n"
                + "         a.tran_amt,\n"
                + "         a.tran_ref_date,\n"
                + "         a.tds_deduct_date,\n"
                + "         a.tds_amt,\n"
                + "         a.tds_deduct_reason,\n"
                + "         a.certificate_no,\n"
                + "         a.country_code,\n"
                + "         a.rate_type,\n"
                + "         a.nature_of_remittance,\n"
                + "         a.tin_uin_no,\n"
                + "         a.deductee_address,\n"
                + "         a.deductee_email_id,\n"
                + "         a.deductee_phoneno,\n"
                + "         a.bank_branch_code,\n"
                + "         a.challan_bank_branch_code,\n"
                + "         a.tds_challan_rowid_seq,\n"
                + "         null filler2,\n"
                + "         null filler3,\n"
                + "         null filler4,\n"
                + "         null filler5,\n"
                + "         null filler6\n"
                + " from    view_tran_data_excel_all a ";

        record_detail_query += " where (a.client_code = '" + client.getClient_code() + "' OR a.parent_code = '" + client.getClient_code() + "' OR\n"
                + "               a.g_parent_code = '" + client.getClient_code() + "' OR a.sg_parent_code = '" + client.getClient_code() + "' OR\n"
                + "               a.ssg_parent_code = '" + client.getClient_code() + "' OR a.sssg_parent_code = '" + client.getClient_code() + "')\n"
                + "           and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           and a.acc_year = '" + asmt.getAccYear() + "'\n"
                + "           and a.quarter_no = '" + asmt.getQuarterNo() + "') where slno between " + minVal + " and " + maxVal;

        return record_detail_query;

    }

    }
