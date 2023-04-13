/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryTranLoadBean {

    public SalaryTranLoadBean() {
    }

    private String slno;
    private String rowid_seq;
    private String entity_code;
    private String client_code;
    private String acc_year;
    private String tds_type_code;
    private String quarter_no;
    private String month;
    private String from_date;
    private String to_date;
    private String session_id;
    private String process_seqno;
    private String deductee_code;
    private String deductee_name;
    private String deductee_catg;
    private String tax_115bac_flag;
    private String deductee_panno;
    private String deductee_phoneno;
    private String deductee_address;
    private String deductee_account_no;
    private String deductee_ppo_no;
    private String deductee_bank_branch_code;
    private String validation_client_code;
    private String identification_no;
    private String deductee_panno_verified;
    private String deductee_panno_valid;
    private String deductee_ref_code1;
    private String deductee_ref_code2;
    private String salary_from_date;
    private String salary_to_date;
    private String afg50_trpepy_flag;
    private String afg50_trpepy_panno1;
    private String afg50_trpepy_pname1;
    private String afg50_trpepy_panno2;
    private String afg50_trpepy_pname2;
    private String afg50_trpepy_panno3;
    private String afg50_trpepy_pname3;
    private String afg50_trpepy_panno4;
    private String afg50_trpepy_pname4;
    private String afg50_iplihp_flag;
    private String afg50_iplihp_panno1;
    private String afg50_iplihp_pname1;
    private String afg50_iplihp_panno2;
    private String afg50_iplihp_pname2;
    private String afg50_iplihp_panno3;
    private String afg50_iplihp_pname3;
    private String afg50_iplihp_panno4;
    private String afg50_iplihp_pname4;
    private String afg55_cptas_flag;
    private String afg55_nsaf_name;
    private String afg55_nsaf_from_date;
    private String afg55_nsaf_to_date;

    private String user_code;
    private String afg55_adp3y_rate;
    private String lastupdate;
    private String flag;
    private String afg50_trpepy_nos;
    private String afg50_iplihp_nos;
    private String afg01_total_count;
    private String afg05_total_count;
    private String afg10_total_count;
    private String afg15_total_count;
    private String afg20_total_count;
    private String afg25_total_gross_count;
    private String afg26_total_deduct_count;
    private String afg27_total_qualify_count;
    private String afg30_total_count;
    private String afg35_total_count;
    private String afg40_total_count;
    private String afg45_total_count;
    private String afg46_total_count;
    private String afgr_fvu_col23;
   
    //Double type fields
    private Double afg01_pes_amt;
    private Double afg01_ces_amt;
    private Double afg01_vpce_amt;
    private Double afg01_plsce_amt;
    private Double afg01_total_amt;
    private Double afg05_tca_amt;
    private Double afg05_drg_amt;
    private Double afg05_cvp_amt;
    private Double afg05_celse_amt;
    private Double afg05_hra_amt;
    private Double afg05_other_amt;
    private Double afg05_total_amt;
    private Double afgr1_total_amt;
    private Double afg10_pt_amt;
    private Double afg10_eag_amt;
    private Double afg10_sd_amt;
    private Double afg10_total_amt;
    private Double afgr2_total_amt;
    private Double afg15_riot_amt;
    private Double afg15_iphl_amt;
    private Double afg15_total_amt;
    private Double afg20_ios_amt;
    private Double afg20_total_amt;
    private Double afgr3_total_amt;
    private Double afg25_lic_gross_amt;
    private Double afg25_ccpf_gross_amt;
    private Double afg25_ctnps_gross_amt;
    private Double afg25_adnps_gross_amt;
    private Double afg25_ecnps_gross_amt;
    private Double afg25_hip_gross_amt;
    private Double afg25_ilhe_gross_amt;
    private Double afg25_dcf_gross_amt;
    private Double afg25_idsa_gross_amt;
    private Double afg25_other_gross_amt;
    private Double afg25_total_gross_amt;
    private Double afg26_lic_deduct_amt;
    private Double afg26_ccpf_deduct_amt;
    private Double afg26_ctnps_deduct_amt;
    private Double afg26_adnps_deduct_amt;
    private Double afg26_ecnps_deduct_amt;
    private Double afg26_hip_deduct_amt;
    private Double afg26_ilhe_deduct_amt;
    private Double afg27_dcf_qualify_amt;
    private Double afg26_dcf_deduct_amt;
    private Double afg27_idsa_qualify_amt;
    private Double afg26_idsa_deduct_amt;
    private Double afg27_other_qualify_amt;
    private Double afg26_other_deduct_amt;
    private Double afg26_total_deduct_amt;
    private Double afg27_total_qualify_amt;
    private Double afgr4_total_amt;
    private Double afg30_tds_amt;
    private Double afg30_sur_amt;
    private Double afg30_ces_amt;
    private Double afg30_total_amt;
    private Double afg35_rus_amt;
    private Double afg35_total_amt;
    private Double afg40_itr_amt;
    private Double afg40_total_amt;
    private Double afgr5_total_amt;
    private Double afg45_ttce_amt;
    private Double afg45_ttpe_amt;
    private Double afg45_total_amt;
    private Double afg46_sftd_amt;
    private Double afg46_total_amt;
    private Double afg55_crpsf_amt;
    private Double afg55_tdrsf_amt;
    private Double afg55_girsf_amt;
     private Double afg60_cms_amt;
    private Double afg60_upms_amt;
    private Double afg60_stpic_amt;
    private Double afg60_avgtax_rate;
    private Double afg60_pcmtds_amt;


    public String getTax_115bac_flag() {
        return tax_115bac_flag;
    }

    public void setTax_115bac_flag(String tax_115bac_flag) {
        this.tax_115bac_flag = tax_115bac_flag;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getDeductee_phoneno() {
        return deductee_phoneno;
    }

    public void setDeductee_phoneno(String deductee_phoneno) {
        this.deductee_phoneno = deductee_phoneno;
    }

    public String getDeductee_address() {
        return deductee_address;
    }

    public void setDeductee_address(String deductee_address) {
        this.deductee_address = deductee_address;
    }

    public String getDeductee_account_no() {
        return deductee_account_no;
    }

    public void setDeductee_account_no(String deductee_account_no) {
        this.deductee_account_no = deductee_account_no;
    }

    public String getDeductee_ppo_no() {
        return deductee_ppo_no;
    }

    public void setDeductee_ppo_no(String deductee_ppo_no) {
        this.deductee_ppo_no = deductee_ppo_no;
    }

    public String getDeductee_bank_branch_code() {
        return deductee_bank_branch_code;
    }

    public void setDeductee_bank_branch_code(String deductee_bank_branch_code) {
        this.deductee_bank_branch_code = deductee_bank_branch_code;
    }

    public String getValidation_client_code() {
        return validation_client_code;
    }

    public void setValidation_client_code(String validation_client_code) {
        this.validation_client_code = validation_client_code;
    }

    public String getIdentification_no() {
        return identification_no;
    }

    public void setIdentification_no(String identification_no) {
        this.identification_no = identification_no;
    }

    public String getDeductee_panno_verified() {
        return deductee_panno_verified;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public String getDeductee_panno_valid() {
        return deductee_panno_valid;
    }

    public void setDeductee_panno_valid(String deductee_panno_valid) {
        this.deductee_panno_valid = deductee_panno_valid;
    }

    public String getDeductee_ref_code1() {
        return deductee_ref_code1;
    }

    public void setDeductee_ref_code1(String deductee_ref_code1) {
        this.deductee_ref_code1 = deductee_ref_code1;
    }

    public String getDeductee_ref_code2() {
        return deductee_ref_code2;
    }

    public void setDeductee_ref_code2(String deductee_ref_code2) {
        this.deductee_ref_code2 = deductee_ref_code2;
    }

    public String getSalary_from_date() {
        return salary_from_date;
    }

    public void setSalary_from_date(String salary_from_date) {
        this.salary_from_date = salary_from_date;
    }

    public String getSalary_to_date() {
        return salary_to_date;
    }

    public void setSalary_to_date(String salary_to_date) {
        this.salary_to_date = salary_to_date;
    }

    public String getAfg50_trpepy_flag() {
        return afg50_trpepy_flag;
    }

    public void setAfg50_trpepy_flag(String afg50_trpepy_flag) {
        this.afg50_trpepy_flag = afg50_trpepy_flag;
    }

    public String getAfg50_trpepy_panno1() {
        return afg50_trpepy_panno1;
    }

    public void setAfg50_trpepy_panno1(String afg50_trpepy_panno1) {
        this.afg50_trpepy_panno1 = afg50_trpepy_panno1;
    }

    public String getAfg50_trpepy_pname1() {
        return afg50_trpepy_pname1;
    }

    public void setAfg50_trpepy_pname1(String afg50_trpepy_pname1) {
        this.afg50_trpepy_pname1 = afg50_trpepy_pname1;
    }

    public String getAfg50_trpepy_panno2() {
        return afg50_trpepy_panno2;
    }

    public void setAfg50_trpepy_panno2(String afg50_trpepy_panno2) {
        this.afg50_trpepy_panno2 = afg50_trpepy_panno2;
    }

    public String getAfg50_trpepy_pname2() {
        return afg50_trpepy_pname2;
    }

    public void setAfg50_trpepy_pname2(String afg50_trpepy_pname2) {
        this.afg50_trpepy_pname2 = afg50_trpepy_pname2;
    }

    public String getAfg50_trpepy_panno3() {
        return afg50_trpepy_panno3;
    }

    public void setAfg50_trpepy_panno3(String afg50_trpepy_panno3) {
        this.afg50_trpepy_panno3 = afg50_trpepy_panno3;
    }

    public String getAfg50_trpepy_pname3() {
        return afg50_trpepy_pname3;
    }

    public void setAfg50_trpepy_pname3(String afg50_trpepy_pname3) {
        this.afg50_trpepy_pname3 = afg50_trpepy_pname3;
    }

    public String getAfg50_trpepy_panno4() {
        return afg50_trpepy_panno4;
    }

    public void setAfg50_trpepy_panno4(String afg50_trpepy_panno4) {
        this.afg50_trpepy_panno4 = afg50_trpepy_panno4;
    }

    public String getAfg50_trpepy_pname4() {
        return afg50_trpepy_pname4;
    }

    public void setAfg50_trpepy_pname4(String afg50_trpepy_pname4) {
        this.afg50_trpepy_pname4 = afg50_trpepy_pname4;
    }

    public String getAfg50_iplihp_flag() {
        return afg50_iplihp_flag;
    }

    public void setAfg50_iplihp_flag(String afg50_iplihp_flag) {
        this.afg50_iplihp_flag = afg50_iplihp_flag;
    }

    public String getAfg50_iplihp_panno1() {
        return afg50_iplihp_panno1;
    }

    public void setAfg50_iplihp_panno1(String afg50_iplihp_panno1) {
        this.afg50_iplihp_panno1 = afg50_iplihp_panno1;
    }

    public String getAfg50_iplihp_pname1() {
        return afg50_iplihp_pname1;
    }

    public void setAfg50_iplihp_pname1(String afg50_iplihp_pname1) {
        this.afg50_iplihp_pname1 = afg50_iplihp_pname1;
    }

    public String getAfg50_iplihp_panno2() {
        return afg50_iplihp_panno2;
    }

    public void setAfg50_iplihp_panno2(String afg50_iplihp_panno2) {
        this.afg50_iplihp_panno2 = afg50_iplihp_panno2;
    }

    public String getAfg50_iplihp_pname2() {
        return afg50_iplihp_pname2;
    }

    public void setAfg50_iplihp_pname2(String afg50_iplihp_pname2) {
        this.afg50_iplihp_pname2 = afg50_iplihp_pname2;
    }

    public String getAfg50_iplihp_panno3() {
        return afg50_iplihp_panno3;
    }

    public void setAfg50_iplihp_panno3(String afg50_iplihp_panno3) {
        this.afg50_iplihp_panno3 = afg50_iplihp_panno3;
    }

    public String getAfg50_iplihp_pname3() {
        return afg50_iplihp_pname3;
    }

    public void setAfg50_iplihp_pname3(String afg50_iplihp_pname3) {
        this.afg50_iplihp_pname3 = afg50_iplihp_pname3;
    }

    public String getAfg50_iplihp_panno4() {
        return afg50_iplihp_panno4;
    }

    public void setAfg50_iplihp_panno4(String afg50_iplihp_panno4) {
        this.afg50_iplihp_panno4 = afg50_iplihp_panno4;
    }

    public String getAfg50_iplihp_pname4() {
        return afg50_iplihp_pname4;
    }

    public void setAfg50_iplihp_pname4(String afg50_iplihp_pname4) {
        this.afg50_iplihp_pname4 = afg50_iplihp_pname4;
    }

    public String getAfg55_cptas_flag() {
        return afg55_cptas_flag;
    }

    public void setAfg55_cptas_flag(String afg55_cptas_flag) {
        this.afg55_cptas_flag = afg55_cptas_flag;
    }

    public String getAfg55_nsaf_name() {
        return afg55_nsaf_name;
    }

    public void setAfg55_nsaf_name(String afg55_nsaf_name) {
        this.afg55_nsaf_name = afg55_nsaf_name;
    }

    public String getAfg55_nsaf_from_date() {
        return afg55_nsaf_from_date;
    }

    public void setAfg55_nsaf_from_date(String afg55_nsaf_from_date) {
        this.afg55_nsaf_from_date = afg55_nsaf_from_date;
    }

    public String getAfg55_nsaf_to_date() {
        return afg55_nsaf_to_date;
    }

    public void setAfg55_nsaf_to_date(String afg55_nsaf_to_date) {
        this.afg55_nsaf_to_date = afg55_nsaf_to_date;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getAfg55_adp3y_rate() {
        return afg55_adp3y_rate;
    }

    public void setAfg55_adp3y_rate(String afg55_adp3y_rate) {
        this.afg55_adp3y_rate = afg55_adp3y_rate;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAfg50_trpepy_nos() {
        return afg50_trpepy_nos;
    }

    public void setAfg50_trpepy_nos(String afg50_trpepy_nos) {
        this.afg50_trpepy_nos = afg50_trpepy_nos;
    }

    public String getAfg50_iplihp_nos() {
        return afg50_iplihp_nos;
    }

    public void setAfg50_iplihp_nos(String afg50_iplihp_nos) {
        this.afg50_iplihp_nos = afg50_iplihp_nos;
    }

    public String getAfg01_total_count() {
        return afg01_total_count;
    }

    public void setAfg01_total_count(String afg01_total_count) {
        this.afg01_total_count = afg01_total_count;
    }

    public String getAfg05_total_count() {
        return afg05_total_count;
    }

    public void setAfg05_total_count(String afg05_total_count) {
        this.afg05_total_count = afg05_total_count;
    }

    public String getAfg10_total_count() {
        return afg10_total_count;
    }

    public void setAfg10_total_count(String afg10_total_count) {
        this.afg10_total_count = afg10_total_count;
    }

    public String getAfg15_total_count() {
        return afg15_total_count;
    }

    public void setAfg15_total_count(String afg15_total_count) {
        this.afg15_total_count = afg15_total_count;
    }

    public String getAfg20_total_count() {
        return afg20_total_count;
    }

    public void setAfg20_total_count(String afg20_total_count) {
        this.afg20_total_count = afg20_total_count;
    }

    public String getAfg25_total_gross_count() {
        return afg25_total_gross_count;
    }

    public void setAfg25_total_gross_count(String afg25_total_gross_count) {
        this.afg25_total_gross_count = afg25_total_gross_count;
    }

    public String getAfg26_total_deduct_count() {
        return afg26_total_deduct_count;
    }

    public void setAfg26_total_deduct_count(String afg26_total_deduct_count) {
        this.afg26_total_deduct_count = afg26_total_deduct_count;
    }

    public String getAfg27_total_qualify_count() {
        return afg27_total_qualify_count;
    }

    public void setAfg27_total_qualify_count(String afg27_total_qualify_count) {
        this.afg27_total_qualify_count = afg27_total_qualify_count;
    }

    public String getAfg30_total_count() {
        return afg30_total_count;
    }

    public void setAfg30_total_count(String afg30_total_count) {
        this.afg30_total_count = afg30_total_count;
    }

    public String getAfg35_total_count() {
        return afg35_total_count;
    }

    public void setAfg35_total_count(String afg35_total_count) {
        this.afg35_total_count = afg35_total_count;
    }

    public String getAfg40_total_count() {
        return afg40_total_count;
    }

    public void setAfg40_total_count(String afg40_total_count) {
        this.afg40_total_count = afg40_total_count;
    }

    public String getAfg45_total_count() {
        return afg45_total_count;
    }

    public void setAfg45_total_count(String afg45_total_count) {
        this.afg45_total_count = afg45_total_count;
    }

    public String getAfg46_total_count() {
        return afg46_total_count;
    }

    public void setAfg46_total_count(String afg46_total_count) {
        this.afg46_total_count = afg46_total_count;
    }

    public String getAfgr_fvu_col23() {
        return afgr_fvu_col23;
    }

    public void setAfgr_fvu_col23(String afgr_fvu_col23) {
        this.afgr_fvu_col23 = afgr_fvu_col23;
    }

    public Double getAfg01_pes_amt() {
        return afg01_pes_amt;
    }

    public void setAfg01_pes_amt(Double afg01_pes_amt) {
        this.afg01_pes_amt = afg01_pes_amt;
    }

    public Double getAfg01_ces_amt() {
        return afg01_ces_amt;
    }

    public void setAfg01_ces_amt(Double afg01_ces_amt) {
        this.afg01_ces_amt = afg01_ces_amt;
    }

    public Double getAfg01_vpce_amt() {
        return afg01_vpce_amt;
    }

    public void setAfg01_vpce_amt(Double afg01_vpce_amt) {
        this.afg01_vpce_amt = afg01_vpce_amt;
    }

    public Double getAfg01_plsce_amt() {
        return afg01_plsce_amt;
    }

    public void setAfg01_plsce_amt(Double afg01_plsce_amt) {
        this.afg01_plsce_amt = afg01_plsce_amt;
    }

    public Double getAfg01_total_amt() {
        return afg01_total_amt;
    }

    public void setAfg01_total_amt(Double afg01_total_amt) {
        this.afg01_total_amt = afg01_total_amt;
    }

    public Double getAfg05_tca_amt() {
        return afg05_tca_amt;
    }

    public void setAfg05_tca_amt(Double afg05_tca_amt) {
        this.afg05_tca_amt = afg05_tca_amt;
    }

    public Double getAfg05_drg_amt() {
        return afg05_drg_amt;
    }

    public void setAfg05_drg_amt(Double afg05_drg_amt) {
        this.afg05_drg_amt = afg05_drg_amt;
    }

    public Double getAfg05_cvp_amt() {
        return afg05_cvp_amt;
    }

    public void setAfg05_cvp_amt(Double afg05_cvp_amt) {
        this.afg05_cvp_amt = afg05_cvp_amt;
    }

    public Double getAfg05_celse_amt() {
        return afg05_celse_amt;
    }

    public void setAfg05_celse_amt(Double afg05_celse_amt) {
        this.afg05_celse_amt = afg05_celse_amt;
    }

    public Double getAfg05_hra_amt() {
        return afg05_hra_amt;
    }

    public void setAfg05_hra_amt(Double afg05_hra_amt) {
        this.afg05_hra_amt = afg05_hra_amt;
    }

    public Double getAfg05_other_amt() {
        return afg05_other_amt;
    }

    public void setAfg05_other_amt(Double afg05_other_amt) {
        this.afg05_other_amt = afg05_other_amt;
    }

    public Double getAfg05_total_amt() {
        return afg05_total_amt;
    }

    public void setAfg05_total_amt(Double afg05_total_amt) {
        this.afg05_total_amt = afg05_total_amt;
    }

    public Double getAfgr1_total_amt() {
        return afgr1_total_amt;
    }

    public void setAfgr1_total_amt(Double afgr1_total_amt) {
        this.afgr1_total_amt = afgr1_total_amt;
    }

    public Double getAfg10_pt_amt() {
        return afg10_pt_amt;
    }

    public void setAfg10_pt_amt(Double afg10_pt_amt) {
        this.afg10_pt_amt = afg10_pt_amt;
    }

    public Double getAfg10_eag_amt() {
        return afg10_eag_amt;
    }

    public void setAfg10_eag_amt(Double afg10_eag_amt) {
        this.afg10_eag_amt = afg10_eag_amt;
    }

    public Double getAfg10_sd_amt() {
        return afg10_sd_amt;
    }

    public void setAfg10_sd_amt(Double afg10_sd_amt) {
        this.afg10_sd_amt = afg10_sd_amt;
    }

    public Double getAfg10_total_amt() {
        return afg10_total_amt;
    }

    public void setAfg10_total_amt(Double afg10_total_amt) {
        this.afg10_total_amt = afg10_total_amt;
    }

    public Double getAfgr2_total_amt() {
        return afgr2_total_amt;
    }

    public void setAfgr2_total_amt(Double afgr2_total_amt) {
        this.afgr2_total_amt = afgr2_total_amt;
    }

    public Double getAfg15_riot_amt() {
        return afg15_riot_amt;
    }

    public void setAfg15_riot_amt(Double afg15_riot_amt) {
        this.afg15_riot_amt = afg15_riot_amt;
    }

    public Double getAfg15_iphl_amt() {
        return afg15_iphl_amt;
    }

    public void setAfg15_iphl_amt(Double afg15_iphl_amt) {
        this.afg15_iphl_amt = afg15_iphl_amt;
    }

    public Double getAfg15_total_amt() {
        return afg15_total_amt;
    }

    public void setAfg15_total_amt(Double afg15_total_amt) {
        this.afg15_total_amt = afg15_total_amt;
    }

    public Double getAfg20_ios_amt() {
        return afg20_ios_amt;
    }

    public void setAfg20_ios_amt(Double afg20_ios_amt) {
        this.afg20_ios_amt = afg20_ios_amt;
    }

    public Double getAfg20_total_amt() {
        return afg20_total_amt;
    }

    public void setAfg20_total_amt(Double afg20_total_amt) {
        this.afg20_total_amt = afg20_total_amt;
    }

    public Double getAfgr3_total_amt() {
        return afgr3_total_amt;
    }

    public void setAfgr3_total_amt(Double afgr3_total_amt) {
        this.afgr3_total_amt = afgr3_total_amt;
    }

    public Double getAfg25_lic_gross_amt() {
        return afg25_lic_gross_amt;
    }

    public void setAfg25_lic_gross_amt(Double afg25_lic_gross_amt) {
        this.afg25_lic_gross_amt = afg25_lic_gross_amt;
    }

    public Double getAfg25_ccpf_gross_amt() {
        return afg25_ccpf_gross_amt;
    }

    public void setAfg25_ccpf_gross_amt(Double afg25_ccpf_gross_amt) {
        this.afg25_ccpf_gross_amt = afg25_ccpf_gross_amt;
    }

    public Double getAfg25_ctnps_gross_amt() {
        return afg25_ctnps_gross_amt;
    }

    public void setAfg25_ctnps_gross_amt(Double afg25_ctnps_gross_amt) {
        this.afg25_ctnps_gross_amt = afg25_ctnps_gross_amt;
    }

    public Double getAfg25_adnps_gross_amt() {
        return afg25_adnps_gross_amt;
    }

    public void setAfg25_adnps_gross_amt(Double afg25_adnps_gross_amt) {
        this.afg25_adnps_gross_amt = afg25_adnps_gross_amt;
    }

    public Double getAfg25_ecnps_gross_amt() {
        return afg25_ecnps_gross_amt;
    }

    public void setAfg25_ecnps_gross_amt(Double afg25_ecnps_gross_amt) {
        this.afg25_ecnps_gross_amt = afg25_ecnps_gross_amt;
    }

    public Double getAfg25_hip_gross_amt() {
        return afg25_hip_gross_amt;
    }

    public void setAfg25_hip_gross_amt(Double afg25_hip_gross_amt) {
        this.afg25_hip_gross_amt = afg25_hip_gross_amt;
    }

    public Double getAfg25_ilhe_gross_amt() {
        return afg25_ilhe_gross_amt;
    }

    public void setAfg25_ilhe_gross_amt(Double afg25_ilhe_gross_amt) {
        this.afg25_ilhe_gross_amt = afg25_ilhe_gross_amt;
    }

    public Double getAfg25_dcf_gross_amt() {
        return afg25_dcf_gross_amt;
    }

    public void setAfg25_dcf_gross_amt(Double afg25_dcf_gross_amt) {
        this.afg25_dcf_gross_amt = afg25_dcf_gross_amt;
    }

    public Double getAfg25_idsa_gross_amt() {
        return afg25_idsa_gross_amt;
    }

    public void setAfg25_idsa_gross_amt(Double afg25_idsa_gross_amt) {
        this.afg25_idsa_gross_amt = afg25_idsa_gross_amt;
    }

    public Double getAfg25_other_gross_amt() {
        return afg25_other_gross_amt;
    }

    public void setAfg25_other_gross_amt(Double afg25_other_gross_amt) {
        this.afg25_other_gross_amt = afg25_other_gross_amt;
    }

    public Double getAfg25_total_gross_amt() {
        return afg25_total_gross_amt;
    }

    public void setAfg25_total_gross_amt(Double afg25_total_gross_amt) {
        this.afg25_total_gross_amt = afg25_total_gross_amt;
    }

    public Double getAfg26_lic_deduct_amt() {
        return afg26_lic_deduct_amt;
    }

    public void setAfg26_lic_deduct_amt(Double afg26_lic_deduct_amt) {
        this.afg26_lic_deduct_amt = afg26_lic_deduct_amt;
    }

    public Double getAfg26_ccpf_deduct_amt() {
        return afg26_ccpf_deduct_amt;
    }

    public void setAfg26_ccpf_deduct_amt(Double afg26_ccpf_deduct_amt) {
        this.afg26_ccpf_deduct_amt = afg26_ccpf_deduct_amt;
    }

    public Double getAfg26_ctnps_deduct_amt() {
        return afg26_ctnps_deduct_amt;
    }

    public void setAfg26_ctnps_deduct_amt(Double afg26_ctnps_deduct_amt) {
        this.afg26_ctnps_deduct_amt = afg26_ctnps_deduct_amt;
    }

    public Double getAfg26_adnps_deduct_amt() {
        return afg26_adnps_deduct_amt;
    }

    public void setAfg26_adnps_deduct_amt(Double afg26_adnps_deduct_amt) {
        this.afg26_adnps_deduct_amt = afg26_adnps_deduct_amt;
    }

    public Double getAfg26_ecnps_deduct_amt() {
        return afg26_ecnps_deduct_amt;
    }

    public void setAfg26_ecnps_deduct_amt(Double afg26_ecnps_deduct_amt) {
        this.afg26_ecnps_deduct_amt = afg26_ecnps_deduct_amt;
    }

    public Double getAfg26_hip_deduct_amt() {
        return afg26_hip_deduct_amt;
    }

    public void setAfg26_hip_deduct_amt(Double afg26_hip_deduct_amt) {
        this.afg26_hip_deduct_amt = afg26_hip_deduct_amt;
    }

    public Double getAfg26_ilhe_deduct_amt() {
        return afg26_ilhe_deduct_amt;
    }

    public void setAfg26_ilhe_deduct_amt(Double afg26_ilhe_deduct_amt) {
        this.afg26_ilhe_deduct_amt = afg26_ilhe_deduct_amt;
    }

    public Double getAfg27_dcf_qualify_amt() {
        return afg27_dcf_qualify_amt;
    }

    public void setAfg27_dcf_qualify_amt(Double afg27_dcf_qualify_amt) {
        this.afg27_dcf_qualify_amt = afg27_dcf_qualify_amt;
    }

    public Double getAfg26_dcf_deduct_amt() {
        return afg26_dcf_deduct_amt;
    }

    public void setAfg26_dcf_deduct_amt(Double afg26_dcf_deduct_amt) {
        this.afg26_dcf_deduct_amt = afg26_dcf_deduct_amt;
    }

    public Double getAfg27_idsa_qualify_amt() {
        return afg27_idsa_qualify_amt;
    }

    public void setAfg27_idsa_qualify_amt(Double afg27_idsa_qualify_amt) {
        this.afg27_idsa_qualify_amt = afg27_idsa_qualify_amt;
    }

    public Double getAfg26_idsa_deduct_amt() {
        return afg26_idsa_deduct_amt;
    }

    public void setAfg26_idsa_deduct_amt(Double afg26_idsa_deduct_amt) {
        this.afg26_idsa_deduct_amt = afg26_idsa_deduct_amt;
    }

    public Double getAfg27_other_qualify_amt() {
        return afg27_other_qualify_amt;
    }

    public void setAfg27_other_qualify_amt(Double afg27_other_qualify_amt) {
        this.afg27_other_qualify_amt = afg27_other_qualify_amt;
    }

    public Double getAfg26_other_deduct_amt() {
        return afg26_other_deduct_amt;
    }

    public void setAfg26_other_deduct_amt(Double afg26_other_deduct_amt) {
        this.afg26_other_deduct_amt = afg26_other_deduct_amt;
    }

    public Double getAfg26_total_deduct_amt() {
        return afg26_total_deduct_amt;
    }

    public void setAfg26_total_deduct_amt(Double afg26_total_deduct_amt) {
        this.afg26_total_deduct_amt = afg26_total_deduct_amt;
    }

    public Double getAfg27_total_qualify_amt() {
        return afg27_total_qualify_amt;
    }

    public void setAfg27_total_qualify_amt(Double afg27_total_qualify_amt) {
        this.afg27_total_qualify_amt = afg27_total_qualify_amt;
    }

    public Double getAfgr4_total_amt() {
        return afgr4_total_amt;
    }

    public void setAfgr4_total_amt(Double afgr4_total_amt) {
        this.afgr4_total_amt = afgr4_total_amt;
    }

    public Double getAfg30_tds_amt() {
        return afg30_tds_amt;
    }

    public void setAfg30_tds_amt(Double afg30_tds_amt) {
        this.afg30_tds_amt = afg30_tds_amt;
    }

    public Double getAfg30_sur_amt() {
        return afg30_sur_amt;
    }

    public void setAfg30_sur_amt(Double afg30_sur_amt) {
        this.afg30_sur_amt = afg30_sur_amt;
    }

    public Double getAfg30_ces_amt() {
        return afg30_ces_amt;
    }

    public void setAfg30_ces_amt(Double afg30_ces_amt) {
        this.afg30_ces_amt = afg30_ces_amt;
    }

    public Double getAfg30_total_amt() {
        return afg30_total_amt;
    }

    public void setAfg30_total_amt(Double afg30_total_amt) {
        this.afg30_total_amt = afg30_total_amt;
    }

    public Double getAfg35_rus_amt() {
        return afg35_rus_amt;
    }

    public void setAfg35_rus_amt(Double afg35_rus_amt) {
        this.afg35_rus_amt = afg35_rus_amt;
    }

    public Double getAfg35_total_amt() {
        return afg35_total_amt;
    }

    public void setAfg35_total_amt(Double afg35_total_amt) {
        this.afg35_total_amt = afg35_total_amt;
    }

    public Double getAfg40_itr_amt() {
        return afg40_itr_amt;
    }

    public void setAfg40_itr_amt(Double afg40_itr_amt) {
        this.afg40_itr_amt = afg40_itr_amt;
    }

    public Double getAfg40_total_amt() {
        return afg40_total_amt;
    }

    public void setAfg40_total_amt(Double afg40_total_amt) {
        this.afg40_total_amt = afg40_total_amt;
    }

    public Double getAfgr5_total_amt() {
        return afgr5_total_amt;
    }

    public void setAfgr5_total_amt(Double afgr5_total_amt) {
        this.afgr5_total_amt = afgr5_total_amt;
    }

    public Double getAfg45_ttce_amt() {
        return afg45_ttce_amt;
    }

    public void setAfg45_ttce_amt(Double afg45_ttce_amt) {
        this.afg45_ttce_amt = afg45_ttce_amt;
    }

    public Double getAfg45_ttpe_amt() {
        return afg45_ttpe_amt;
    }

    public void setAfg45_ttpe_amt(Double afg45_ttpe_amt) {
        this.afg45_ttpe_amt = afg45_ttpe_amt;
    }

    public Double getAfg45_total_amt() {
        return afg45_total_amt;
    }

    public void setAfg45_total_amt(Double afg45_total_amt) {
        this.afg45_total_amt = afg45_total_amt;
    }

    public Double getAfg46_sftd_amt() {
        return afg46_sftd_amt;
    }

    public void setAfg46_sftd_amt(Double afg46_sftd_amt) {
        this.afg46_sftd_amt = afg46_sftd_amt;
    }

    public Double getAfg46_total_amt() {
        return afg46_total_amt;
    }

    public void setAfg46_total_amt(Double afg46_total_amt) {
        this.afg46_total_amt = afg46_total_amt;
    }

    public Double getAfg55_crpsf_amt() {
        return afg55_crpsf_amt;
    }

    public void setAfg55_crpsf_amt(Double afg55_crpsf_amt) {
        this.afg55_crpsf_amt = afg55_crpsf_amt;
    }

    public Double getAfg55_tdrsf_amt() {
        return afg55_tdrsf_amt;
    }

    public void setAfg55_tdrsf_amt(Double afg55_tdrsf_amt) {
        this.afg55_tdrsf_amt = afg55_tdrsf_amt;
    }

    public Double getAfg55_girsf_amt() {
        return afg55_girsf_amt;
    }

    public void setAfg55_girsf_amt(Double afg55_girsf_amt) {
        this.afg55_girsf_amt = afg55_girsf_amt;
    }

    public Double getAfg60_cms_amt() {
        return afg60_cms_amt;
    }

    public void setAfg60_cms_amt(Double afg60_cms_amt) {
        this.afg60_cms_amt = afg60_cms_amt;
    }

    public Double getAfg60_upms_amt() {
        return afg60_upms_amt;
    }

    public void setAfg60_upms_amt(Double afg60_upms_amt) {
        this.afg60_upms_amt = afg60_upms_amt;
    }

    public Double getAfg60_stpic_amt() {
        return afg60_stpic_amt;
    }

    public void setAfg60_stpic_amt(Double afg60_stpic_amt) {
        this.afg60_stpic_amt = afg60_stpic_amt;
    }

    public Double getAfg60_avgtax_rate() {
        return afg60_avgtax_rate;
    }

    public void setAfg60_avgtax_rate(Double afg60_avgtax_rate) {
        this.afg60_avgtax_rate = afg60_avgtax_rate;
    }

    public Double getAfg60_pcmtds_amt() {
        return afg60_pcmtds_amt;
    }

    public void setAfg60_pcmtds_amt(Double afg60_pcmtds_amt) {
        this.afg60_pcmtds_amt = afg60_pcmtds_amt;
    }

    }
