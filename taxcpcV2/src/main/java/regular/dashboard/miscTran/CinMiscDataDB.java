/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author pratik.barahate
 */
public class CinMiscDataDB {

    Util utl;

    public CinMiscDataDB() {
        this.utl = new Util();
    }

    String getCinMiscDataQuery(CinMiscFilterEntity filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {
        String sql
                = "select * from (select rownum slno, c.bank_branch_code,c.client_name bank_branch_name, t.rowid_seq,"
                + "t.tds_challan_rowid_seq,t.deductee_panno,t.deductee_name ,"
                + "t.invoice_date,t.invoice_no, t.tran_ref_date, t.flag,t.tds_section,decode(t.tds_trantype, 'Y', 'Yes', 'N', 'NO') tds_tran_type, t.tds_rate, "
                + "t.certificate_no, t.tran_amt,t.tds_amt, t.approvedby, t.approveddate, "
                + "decode(t.approvedby, null, 'Unapprove','Approved' ) Status , "
                + "t.cgst_amt, t.sgst_amt, t.igst_amt,\n"
                + "t.total_g_b_amt total_amt,\n"
                + "(nvl(t.total_g_b_amt, 0) - nvl(t.tds_amt, 0)) net_amt"
                + " from TDS_MISC_TRAN_LOAD t , client_mast c\n"
                + "where t.entity_code =  c.entity_code\n"
                + " and  t.client_code = c.client_code\n"
                + " and t.entity_code = '" + clientMast.getEntity_code() + "'\n"
                + " and (c.client_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.parent_code = '" + clientMast.getClient_code() + "' or\n"
                + "      c.g_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.sg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.ssg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.sssg_parent_code= '" + clientMast.getClient_code() + "' )\n"
                + "\n"
                + "and  t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "and t.data_entry_mode ='M'\n"
                + getWhereCls(filters)
                + "   ) where slno between " + minVal + " and " + maxVal;

        this.utl.generateSpecialLog("Misc browse page Query", sql);
        return sql;
    }

    public String getWhereCls(CinMiscFilterEntity filters) {
        String whrClause = "";
        if (filters != null) {
            if (!utl.isnull(filters.getDeductee_panno())) {
                whrClause += "and t.deductee_panno = '" + filters.getDeductee_panno() + "'\n";
            }
            if (!utl.isnull(filters.getDeductee_name())) {
                whrClause += "and lower(t.deductee_name) like lower('%" + filters.getDeductee_name().trim() + "%')\n";
            }
            if (!utl.isnull(filters.getBgl_code())) {
                whrClause += "and t.flag = '" + filters.getBgl_code() + "'\n";
            }
            if (!utl.isnull(filters.getTds_code())) {
                whrClause += "and t.tds_code = '" + filters.getTds_code() + "'\n";
            }
            if (!utl.isnull(filters.getMonth())) {
                whrClause += "and t.month = '" + filters.getMonth() + "'\n";
            }
            if (!utl.isnull(filters.getAuthStatusFlag())) {
                if ("A".equals(filters.getAuthStatusFlag())) {
                    whrClause += "and t.approveddate is not null\n";
                } else if ("R".equals(filters.getAuthStatusFlag())) {
                    whrClause += "and t.approveddate is null\n";
                }
            }
            if (!utl.isnull(filters.getFrom_date()) && !utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate between to_date('" + filters.getFrom_date() + "','dd-mm-rrrr') "
                        + "and to_date('" + filters.getTo_date() + "','dd-mm-rrrr')\n";
            }
            if (!utl.isnull(filters.getFrom_date()) && utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate >= to_date('" + filters.getFrom_date() + "','dd-mm-rrrr')\n";
            }
            if (utl.isnull(filters.getFrom_date()) && !utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate <= to_date('" + filters.getTo_date() + "','dd-mm-rrrr')\n";
            }
            if (!utl.isnull(filters.getTds_amt_from()) && !utl.isnull(filters.getTds_amt_to())) {
                if (!utl.isnull(filters.getTdsAmtFlag()) && filters.getTdsAmtFlag().equals("between")) {
                    whrClause += "and t.tds_amt between " + filters.getTds_amt_from() + " and " + filters.getTds_amt_to() + "\n";
                } else if (!utl.isnull(filters.getTdsAmtFlag())) {
                    whrClause += "and (t.tds_amt " + filters.getTdsAmtFlag() + " " + filters.getTds_amt_from() + "";
                    whrClause += " or t.tds_amt " + filters.getTdsAmtFlag() + " " + filters.getTds_amt_to() + ")\n";

                }
            }
            if (!utl.isnull(filters.getTdsAmtFlag()) && utl.isnull(filters.getTds_amt_from()) && !utl.isnull(filters.getTds_amt_to())) {
                whrClause += "and t.tds_amt " + filters.getTdsAmtFlag() + " '" + filters.getTds_amt_to() + "'\n";
            }
            if (!utl.isnull(filters.getTdsAmtFlag()) && !utl.isnull(filters.getTds_amt_from()) && utl.isnull(filters.getTds_amt_to())) {
                whrClause += "and t.tds_amt  " + filters.getTdsAmtFlag() + " '" + filters.getTds_amt_to() + "'\n";
            }
            if (!utl.isnull(filters.getAccount_number())) {
                whrClause += "and t.account_number  = '" + filters.getAccount_number() + "'\n";
            }

        }
        return whrClause;
    }

    String getCheckerMakerDataQuery(TdsTranLoad filter, String authFlag, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {

        String query
                = "select * from (select rownum slno, c.bank_branch_code, c.client_name bank_branch_name, t.rowid_seq,"
                + "t.tds_challan_rowid_seq,t.deductee_panno,t.deductee_name ,"
                + "t.invoice_date, t.invoice_no, t.tran_ref_date, t.flag,t.tds_section,decode(t.tds_trantype, 'Y', 'Yes', 'N', 'NO') tds_tran_type, t.tds_rate,"
                + "               t.certificate_no, t.tran_amt,t.tds_amt , "
                + "decode(t.approvedby, null, 'Unapproved','Approved' ) Status, "
                + "cgst_amt, sgst_amt, igst_amt,\n"
                + "t.total_g_b_amt total_amt,\n"
                + "(nvl(t.total_g_b_amt, 0) - nvl(t.tds_amt, 0)) net_amt"
                + " from Tds_misc_Tran_Load t , client_mast c\n"
                + "where t.entity_code =  c.entity_code\n"
                + " and  t.client_code = c.client_code\n"
                + " and t.entity_code = '" + clientMast.getEntity_code() + "'\n"
                + " and (c.client_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.parent_code = '" + clientMast.getClient_code() + "' or\n"
                + "      c.g_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.sg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.ssg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                + "      c.sssg_parent_code= '" + clientMast.getClient_code() + "' )\n"
                + "and  t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "and t.data_entry_mode ='M'\n"
                + getWhereClsForChkr(filter, authFlag)
                + "   ) where slno between " + minVal + " and " + maxVal;
        utl.generateSpecialLog("checker maker browse page query", query);
        return query;

    }

    public String getWhereClsForChkr(TdsTranLoad filters, String authFlag) {
        String whrClause = "";
        if (filters != null) {
            if (!utl.isnull(filters.getDeductee_panno())) {
                whrClause += "and t.deductee_panno = '" + filters.getDeductee_panno() + "'\n";
            }
            if (!utl.isnull(filters.getDeductee_name())) {
                whrClause += "and lower(t.deductee_name) like lower('%" + filters.getDeductee_name().trim() + "%')\n";
            }
            if (!utl.isnull(authFlag)) {
                if (authFlag.equals("AL")) {
                    whrClause += "and ('" + authFlag + "' = 'AL' and 1=1)\n";
                } else if (authFlag.equals("A")) {
                    whrClause += "and ('" + authFlag + "' = 'A' and t.approvedby is not null)  \n";
                } else {
                    whrClause += "and ('" + authFlag + "' = 'R' and t.approvedby is null) \n";
                }
            }
        }
        return whrClause;
    }

}
