/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryTranLoadDB {

    public String getSalaryTranLoadQuery(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {
        String query = "select * from (select rownum slno,\n"
                + "               a. rowid_seq,\n"
                + "               a. entity_code,\n"
                + "               a. client_code,\n"
                + "               a. acc_year,\n"
                + "               a. tds_type_code,\n"
                + "               a. quarter_no,\n"
                + "               a. month,\n"
                + "               a. from_date,\n"
                + "               a. to_date,\n"
                + "               a. session_id,\n"
                + "               a. process_seqno,\n"
                + "               a. deductee_code,\n"
                + "               a. deductee_name,\n"
                + "               a. deductee_catg,\n"
                + "               decode(a.tax_115bac_flag,'Y','Yes','N','No','')tax_115bac_flag,\n"
                + "               a. deductee_panno,\n"
                + "               a. deductee_phoneno,\n"
                + "               a. deductee_address,\n"
                + "               a. deductee_account_no,\n"
                + "               a. deductee_ppo_no,\n"
                + "               a. deductee_bank_branch_code,\n"
                + "               a. validation_client_code,\n"
                + "               a. identification_no,\n"
                + "               a. deductee_panno_verified,\n"
                + "               a. deductee_panno_valid,\n"
                + "               a. deductee_ref_code1,\n"
                + "               a. deductee_ref_code2,\n"
                + "               to_char(a.salary_from_date, 'dd-MM-rrrr') salary_from_date,\n"
                + "               to_char(a.salary_to_date, 'dd-MM-rrrr') salary_to_date,\n"
                + "               a. afg01_pes_amt,\n"
                + "               a. afg01_ces_amt,\n"
                + "               a. afg01_vpce_amt,\n"
                + "               a. afg01_plsce_amt,\n"
                + "               a. afg01_total_amt,\n"
                + "               a. afg05_tca_amt,\n"
                + "               a. afg05_drg_amt,\n"
                + "               a. afg05_cvp_amt,\n"
                + "               a. afg05_celse_amt,\n"
                + "               a. afg05_hra_amt,\n"
                + "               a. afg05_other_amt,\n"
                + "               a. afg05_total_amt,\n"
                + "               a. afgr1_total_amt,\n"
                + "               a. afg10_pt_amt,\n"
                + "               a. afg10_eag_amt,\n"
                + "               a. afg10_sd_amt,\n"
                + "               a. afg10_total_amt,\n"
                + "               a. afgr2_total_amt,\n"
                + "               a. afg15_riot_amt,\n"
                + "               a. afg15_iphl_amt,\n"
                + "               a. afg15_total_amt,\n"
                + "               a. afg20_ios_amt,\n"
                + "               a. afg20_total_amt,\n"
                + "               a. afgr3_total_amt,\n"
                + "               a. afg25_lic_gross_amt,\n"
                + "               a. afg25_ccpf_gross_amt,\n"
                + "               a. afg25_ctnps_gross_amt,\n"
                + "               a. afg25_adnps_gross_amt,\n"
                + "               a. afg25_ecnps_gross_amt,\n"
                + "               a. afg25_hip_gross_amt,\n"
                + "               a. afg25_ilhe_gross_amt,\n"
                + "               a. afg25_dcf_gross_amt,\n"
                + "               a. afg25_idsa_gross_amt,\n"
                + "               a. afg25_other_gross_amt,\n"
                + "               a. afg25_total_gross_amt,\n"
                + "               a. afg26_lic_deduct_amt,\n"
                + "               a. afg26_ccpf_deduct_amt,\n"
                + "               a. afg26_ctnps_deduct_amt,\n"
                + "               a. afg26_adnps_deduct_amt,\n"
                + "               a. afg26_ecnps_deduct_amt,\n"
                + "               a. afg26_hip_deduct_amt,\n"
                + "               a. afg26_ilhe_deduct_amt,\n"
                + "               a. afg27_dcf_qualify_amt,\n"
                + "               a. afg26_dcf_deduct_amt,\n"
                + "               a. afg27_idsa_qualify_amt,\n"
                + "               a. afg26_idsa_deduct_amt,\n"
                + "               a. afg27_other_qualify_amt,\n"
                + "               a. afg26_other_deduct_amt,\n"
                + "               a. afg26_total_deduct_amt,\n"
                + "               a. afg27_total_qualify_amt,\n"
                + "               a. afgr4_total_amt,\n"
                + "               a. afg30_tds_amt,\n"
                + "               a. afg30_sur_amt,\n"
                + "               a. afg30_ces_amt,\n"
                + "               a. afg30_total_amt,\n"
                + "               a. afg35_rus_amt,\n"
                + "               a. afg35_total_amt,\n"
                + "               a. afg40_itr_amt,\n"
                + "               a. afg40_total_amt,\n"
                + "               a. afgr5_total_amt,\n"
                + "               a. afg45_ttce_amt,\n"
                + "               a. afg45_ttpe_amt,\n"
                + "               a. afg45_total_amt,\n"
                + "               a. afg46_sftd_amt,\n"
                + "               a. afg46_total_amt,\n"
                + "               a. afg50_trpepy_flag,\n"
                + "               a. afg50_trpepy_panno1,\n"
                + "               a. afg50_trpepy_pname1,\n"
                + "               a. afg50_trpepy_panno2,\n"
                + "               a. afg50_trpepy_pname2,\n"
                + "               a. afg50_trpepy_panno3,\n"
                + "               a. afg50_trpepy_pname3,\n"
                + "               a. afg50_trpepy_panno4,\n"
                + "               a. afg50_trpepy_pname4,\n"
                + "               a. afg50_iplihp_flag,\n"
                + "               a. afg50_iplihp_panno1,\n"
                + "               a. afg50_iplihp_pname1,\n"
                + "               a. afg50_iplihp_panno2,\n"
                + "               a. afg50_iplihp_pname2,\n"
                + "               a. afg50_iplihp_panno3,\n"
                + "               a. afg50_iplihp_pname3,\n"
                + "               a. afg50_iplihp_panno4,\n"
                + "               a. afg50_iplihp_pname4,\n"
                + "               a. afg55_cptas_flag,\n"
                + "               a. afg55_nsaf_name,\n"
                + "               a. afg55_nsaf_from_date,\n"
                + "               a. afg55_nsaf_to_date,\n"
                + "               a. afg55_crpsf_amt,\n"
                + "               a. afg55_adp3y_rate,\n"
                + "               a. afg55_tdrsf_amt,\n"
                + "               a. afg55_girsf_amt,\n"
                + "               a. user_code,\n"
                + "               a. lastupdate,\n"
                + "               a. flag,\n"
                + "               a. afg50_trpepy_nos,\n"
                + "               a. afg50_iplihp_nos,\n"
                + "               a. afg01_total_count,\n"
                + "               a. afg05_total_count,\n"
                + "               a. afg10_total_count,\n"
                + "               a. afg15_total_count,\n"
                + "               a. afg20_total_count,\n"
                + "               a. afg25_total_gross_count,\n"
                + "               a. afg26_total_deduct_count,\n"
                + "               a. afg27_total_qualify_count,\n"
                + "               a. afg30_total_count,\n"
                + "               a. afg35_total_count,\n"
                + "               a. afg40_total_count,\n"
                + "               a. afg45_total_count,\n"
                + "               a. afg46_total_count,\n"
                + "               a. afgr_fvu_col23,\n"
                + "               a. afg60_cms_amt,\n"
                + "               a. afg60_upms_amt,\n"
                + "               a. afg60_stpic_amt,\n"
                + "               a. afg60_avgtax_rate,\n"
                + "               a. afg60_pcmtds_amt\n"
                + "          from salary_tran_load a, client_mast b\n"
                + "         where b.entity_code = a.entity_code\n"
                + "           and b.client_code = a.client_code\n"
                + getWhereClause(filters, clientMast, asmt, minVal, maxVal, utl)
                + "   ) where slno between " + minVal + " and " + maxVal;
        System.out.println("SalaryTranquery--"+query);
        return query;
    }

    public String getWhereClause(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {
        String whrClause = "";
        DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
        String data_client_code = "";
        String data_validation_client_code = "";
        if (!utl.isnull(filters.getClient_bank_branch_code())) {
            data_client_code = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                    "select client_code from client_mast where entity_code='" + clientMast.getEntity_code() + "' and bank_branch_code='" + filters.getClient_bank_branch_code() + "'");
        }
        if (!utl.isnull(filters.getChallan_bank_branch_code())) {
            data_validation_client_code = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                    "select client_code from client_mast where entity_code='" + clientMast.getEntity_code() + "' and bank_branch_code='" + filters.getChallan_bank_branch_code() + "'");
        }
        if (filters != null) {
            if (!utl.isnull(filters.getDeductee_paano())) {
                whrClause += "and a.deductee_panno = '" + filters.getDeductee_paano() + "'\n";
            }
            if (!utl.isnull(filters.getClient_bank_branch_code())) {
                whrClause += "and a.client_code = '" + data_client_code + "'\n";
            }
            if (!utl.isnull(filters.getChallan_bank_branch_code())) {
                whrClause += "and a.validation_client_code = '" + data_validation_client_code + "'\n";
            }
            if (!utl.isnull(filters.getTax_115bac_flag())) {
                whrClause += "and a.tax_115bac_flag = '" + filters.getTax_115bac_flag() + "'\n";
            }
            if (!utl.isnull(filters.getDeductee_name())) {
                whrClause += "and lower(a.deductee_name) like lower('%" + filters.getDeductee_name().trim() + "%')\n";
            }
            if (!utl.isnull(filters.getDeductee_catg())) {
                whrClause += "and a.deductee_catg = '" + filters.getDeductee_catg() + "'\n";
            }
            if (!utl.isnull(filters.getDeduction_catg()) && filters.getDeduction_catg().equalsIgnoreCase("S")) {
                whrClause += "and nvl(a.AFG46_SFTD_AMT,0)>=1 \n";
            }
            if (!utl.isnull(filters.getDuplicate_record_flag()) && filters.getDuplicate_record_flag().equalsIgnoreCase("T")) {
                whrClause += "and a.deductee_panno_verified='Y'\n";
                whrClause += "and exists (select 1        \n"
                        + "          from (                \n"
                        + "                select entity_code,\n"
                        + "                        client_code,\n"
                        + "                        acc_year,\n"
                        + "                        quarter_no,\n"
                        + "                        tds_type_code,\n"
                        + "                        deductee_panno,\n"
                        + "                        rowid_Seq                \n"
                        + "                  from (select b.entity_code,\n"
                        + "                                b.client_code,\n"
                        + "                                b.acc_year,\n"
                        + "                                b.quarter_no,\n"
                        + "                                b.tds_type_code,\n"
                        + "                                b.deductee_panno,\n"
                        + "                                b.rowid_Seq,                                \n"
                        + "                                count(deductee_panno) over(partition by b.entity_code,"
                        + " b.client_code, b.acc_year, b.quarter_no, b.tds_type_code, b.deductee_panno) dupl_deductee_panno\n"
                        + "                           from salary_tran_load b,client_mast b1                         \n"
                        + "                         where b1.entity_code=b.entity_code\n"
                        + "                          and   b1.client_code=b.client_code\n"
                        + "                          and   b.entity_code = '" + clientMast.getEntity_code() + "'\n"
                        + "                            and (b1.client_code = '" + clientMast.getClient_code() + "' or\n"
                        + "                                 b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                        + "                                 b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                        + "                                 b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                        + "                                 b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                        + "                                 b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                        + "                                )\n"
                        + "                            and b.acc_year = '" + asmt.getAccYear() + "') b             \n"
                        + "                 where nvl(dupl_deductee_panno, 0) > 1                \n"
                        + "                ) b        \n"
                        + "         where b.entity_code = a.entity_code              \n"
                        + "           and b.client_code = a.client_code             \n"
                        + "           and b.acc_year = a.acc_year              \n"
                        + "         and b.deductee_panno = a.deductee_panno "
                        + "        )    and a.entity_code = '" + clientMast.getEntity_code() + "'\n";
            }
        }
        whrClause += " and exists (select 1 from client_mast w1\n"
                + "                   where w1.client_code = a.client_code\n"
                + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))\n"
                + " and a.entity_code='" + clientMast.getEntity_code() + "'\n"
                + " and a.acc_year='" + asmt.getAccYear() + "'\n"
                + " and a.quarter_no=" + asmt.getQuarterNo() + "\n"
                + " and a.tds_type_code='" + asmt.getTdsTypeCode() + "'";
        return whrClause;
    }

    public String getSalSevaarthProjDataGridQuery(ViewClientMast client, Assessment assment, String deducteePanno) {
        String query
                = "select a.entity_code,\n"
                + "       a.client_code,\n"
                + "       a.acc_year,\n"
                + "       a.assesment_acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.month,\n"
                + "       a.salary_from_date,\n"
                + "       a.salary_to_date,\n"
                + "       a.sevarth_id,\n"
                + "       a.deductee_panno,\n"
                + "       a.deductee_name,\n"
                + "       a.deductee_catg,\n"
                + "       a.deductee_catg||'-'||c.emp_catg_name deductee_catg_name,\n"
                + "       a.data_type,\n"
                + "       a.bill_gross_amt,\n"
                + "       a.netamt,\n"
                + "       a.basic_pay,\n"
                + "       a.nps_employer_contr,\n"
                + "       a.total_gross,\n"
                + "       a.nps_employer_cont,\n"
                + "       a.pli,\n"
                + "       a.it,\n"
                + "       a.exe_pay_rec,\n"
                + "       a.total_deduction,\n"
                + "       a.net_pay,\n"
                + "       a.da,\n"
                + "       a.hra,\n"
                + "       a.ta,\n"
                + "       a.partially_fully_tax_free,\n"
                + "       a.other_taxable,\n"
                + "       a.gpf,\n"
                + "       a.nps_employee_contr,\n"
                + "       a.gis,\n"
                + "       a.pt,\n"
                + "       a.hba_principal,\n"
                + "       a.hba_interest,\n"
                + "       a.lic,\n"
                + "       a.other_deduction,\n"
                + "       a.washing_allowance,\n"
                + "       a.uniform_allowance,\n"
                + "       a.donations,\n"
                + "       a.naxal_area_allowance,\n"
                + "       a.pg_allowance,\n"
                + "       a.medical_education_allowance,\n"
                + "       a.medical_insurance_premium\n"
                + "from   view_salary_itp_proj_detail a, client_mast b,\n"
                + "       view_emp_catg c\n"
                + "where  b.entity_code=a.entity_code\n"
                + "and    b.client_code=a.client_code\n"
                + "and    c.emp_catg_code=a.deductee_catg\n"
                + "and    a.entity_code='" + client.getEntity_code() + "'\n"
                + "and    (b.client_code='" + client.getClient_code() + "' or\n"
                + "        b.parent_code='" + client.getClient_code() + "' or\n"
                + "        b.g_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.sg_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.ssg_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.sssg_parent_code='" + client.getClient_code() + "'\n"
                + "        )\n"
                + "and    a.tds_type_code='" + assment.getTdsTypeCode() + "'\n"
                + "and    a.deductee_panno='" + deducteePanno + "'\n"
                + "order by a.salary_from_date";
        return query;
    }
}
