/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import globalUtilities.Util;

/**
 *
 * @author ayushi.jain
 */
public class DownloadInTemplateDB {

    public String getCombinedQuery(String client_code, String acc_year, String quarter_no, String tds_type_code, String NewwhrCls) {
        String l_return_query = "";
        try {

            // setNo_of_column(32);
            l_return_query = "select   a.bank_bsr_code,\n"
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
            String query = " where (a.client_code = '" + client_code + "' OR a.parent_code = '" + client_code + "' OR\n"
                    + "               a.g_parent_code = '" + client_code + "' OR a.sg_parent_code = '" + client_code + "' OR\n"
                    + "               a.ssg_parent_code = '" + client_code + "' OR a.sssg_parent_code = '" + client_code + "')\n"
                    + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                    + "           and a.acc_year = '" + acc_year + "'\n"
                    + "           and a.quarter_no = '" + quarter_no + "'";
            l_return_query = l_return_query.concat(query);
            if (!utl.isnull(NewwhrCls)) {
                l_return_query = l_return_query.concat(NewwhrCls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_query;
    }//end method

    public String getCombinedQueryRecord(String client_code, String acc_year, String quarter_no, String tds_type_code, int startInd, int endInd, String NewwhrCls) {
        String l_return_query = "";
        try {

            l_return_query = "select bank_bsr_code,\n"
                    + "        tds_challan_date,\n"
                    + "         tds_challan_no,\n"
                    + "         tds_challan_tds_amt,\n"
                    + "         tds_challan_other_amt,\n"
                    + "         tds_challan_int_amt,\n"
                    + "         deductee_ref_code1,\n"
                    + "         deductee_panno,\n"
                    + "         deductee_name,\n"
                    + "         account_no,\n"
                    + "         tds_section,\n"
                    + "         tran_amt,\n"
                    + "         tran_ref_date,\n"
                    + "         tds_deduct_date,\n"
                    + "         tds_amt,\n"
                    + "         tds_deduct_reason,\n"
                    + "         certificate_no,\n"
                    + "         country_code,\n"
                    + "         rate_type,\n"
                    + "         nature_of_remittance,\n"
                    + "         tin_uin_no,\n"
                    + "         deductee_address,\n"
                    + "         deductee_email_id,\n"
                    + "         deductee_phoneno,\n"
                    + "         bank_branch_code,\n"
                    + "         challan_bank_branch_code,\n"
                    + "         tds_challan_rowid_seq,\n"
                    + "          filler2,\n"
                    + "          filler3,\n"
                    + "          filler4,\n"
                    + "          filler5,\n"
                    + "          filler6 from ( select  rownum slno, a.bank_bsr_code,\n"
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
                    + "        null filler2,\n"
                    + "         lhs_utility.is_valid_panno('deductee_panno') filler3,\n"
                    + "         null filler4,\n"
                    + "         null filler5,\n"
                    + "         null filler6\n"
                    + " from    view_tran_data_excel_all a ";

            String query = " where (a.client_code = '" + client_code + "' OR a.parent_code = '" + client_code + "' OR\n"
                    + "               a.g_parent_code = '" + client_code + "' OR a.sg_parent_code = '" + client_code + "' OR\n"
                    + "               a.ssg_parent_code = '" + client_code + "' OR a.sssg_parent_code = '" + client_code + "')\n"
                    + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                    + "           and a.acc_year = '" + acc_year + "'\n"
                    + "           and a.quarter_no = '" + quarter_no + "'";
            l_return_query = l_return_query.concat(query);
            if (!utl.isnull(NewwhrCls)) {
                l_return_query = l_return_query.concat(NewwhrCls);
            }
            l_return_query = l_return_query.concat(") where slno between " + startInd + " and " + endInd + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l_return_query;
    }//end method

    Util utl;

    public DownloadInTemplateDB() {
        utl = new Util();
    }
}
