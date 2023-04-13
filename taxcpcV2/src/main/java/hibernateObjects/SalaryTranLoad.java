/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "salary_tran_load")
public class SalaryTranLoad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "salary_tran_rowid_seq")
    })
    @Column(name = "rowid_seq", nullable = false)
    private Long rowid_seq;
    @Column(name = "entity_code", length = 2)
    private String entity_code;
    @Column(name = "client_code", length = 15)
    private String client_code;
    @Column(name = "acc_year", length = 5)
    private String acc_year;
    @Column(name = "tds_type_code", length = 3)
    private String tds_type_code;
    @Column(name = "quarter_no")
    private Long quarter_no;
    @Column(name = "month", length = 10)
    private String month;
    @Column(name = "from_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "session_id", length = 10)
    private String session_id;
    @Column(name = "process_seqno")
    private Double process_seqno;
    @Column(name = "deductee_code", length = 15)
    private String deductee_code;
    @Column(name = "deductee_name", length = 75)
    private String deductee_name;
    @Column(name = "deductee_catg", length = 5)
    private String deductee_catg;
    @Column(name = "deductee_panno", length = 10)
    private String deductee_panno;
    @Column(name = "deductee_phoneno", length = 15)
    private String deductee_phoneno;
    @Column(name = "deductee_address", length = 150)
    private String deductee_address;
    @Column(name = "deductee_account_no", length = 25)
    private String deductee_account_no;
    @Column(name = "deductee_ppo_no", length = 25)
    private String deductee_ppo_no;
    @Column(name = "deductee_bank_branch_code", length = 15)
    private String deductee_bank_branch_code;
    @Column(name = "validation_client_code", length = 15)
    private String validation_client_code;
    @Column(name = "identification_no", length = 50)
    private String identification_no;
    @Column(name = "deductee_panno_verified", length = 1)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 1)
    private String deductee_panno_valid;
    @Column(name = "deductee_ref_code1", length = 10)
    private String deductee_ref_code1;
    @Column(name = "deductee_ref_code2", length = 10)
    private String deductee_ref_code2;
    @Column(name = "salary_from_date")
    private String salary_from_date;
    @Column(name = "salary_to_date")
    private String salary_to_date;
    @Column(name = "afg01_pes_amt")
    private Double afg01_pes_amt;
    @Column(name = "afg01_ces_amt")
    private Double afg01_ces_amt;
    @Column(name = "afg01_vpce_amt")
    private Double afg01_vpce_amt;
    @Column(name = "afg01_plsce_amt")
    private Double afg01_plsce_amt;
    @Column(name = "afg01_total_amt")
    private Double afg01_total_amt;
    @Column(name = "afg05_tca_amt")
    private Double afg05_tca_amt;
    @Column(name = "afg05_drg_amt")
    private Double afg05_drg_amt;
    @Column(name = "afg05_cvp_amt")
    private Double afg05_cvp_amt;
    @Column(name = "afg05_celse_amt")
    private Double afg05_celse_amt;
    @Column(name = "afg05_hra_amt")
    private Double afg05_hra_amt;
    @Column(name = "afg05_other_amt")
    private Double afg05_other_amt;
    @Column(name = "afg05_total_amt")
    private Double afg05_total_amt;
    @Column(name = "afgr1_total_amt")
    private Double afgr1_total_amt;
    @Column(name = "afg10_pt_amt")
    private Double afg10_pt_amt;
    @Column(name = "afg10_eag_amt")
    private Double afg10_eag_amt;
    @Column(name = "afg10_sd_amt")
    private Double afg10_sd_amt;
    @Column(name = "afg10_total_amt")
    private Double afg10_total_amt;
    @Column(name = "afgr2_total_amt")
    private Double afgr2_total_amt;
    @Column(name = "afg15_riot_amt")
    private Double afg15_riot_amt;
    @Column(name = "afg15_iphl_amt")
    private Double afg15_iphl_amt;
    @Column(name = "afg15_total_amt")
    private Double afg15_total_amt;
    @Column(name = "afg20_ios_amt")
    private Double afg20_ios_amt;
    @Column(name = "afg20_total_amt")
    private Double afg20_total_amt;
    @Column(name = "afgr3_total_amt")
    private Double afgr3_total_amt;
    @Column(name = "afg25_lic_gross_amt")
    private Double afg25_lic_gross_amt;
    @Column(name = "afg25_ccpf_gross_amt")
    private Double afg25_ccpf_gross_amt;
    @Column(name = "afg25_ctnps_gross_amt")
    private Double afg25_ctnps_gross_amt;
    @Column(name = "afg25_adnps_gross_amt")
    private Double afg25_adnps_gross_amt;
    @Column(name = "afg25_ecnps_gross_amt")
    private Double afg25_ecnps_gross_amt;
    @Column(name = "afg25_hip_gross_amt")
    private Double afg25_hip_gross_amt;
    @Column(name = "afg25_ilhe_gross_amt")
    private Double afg25_ilhe_gross_amt;
    @Column(name = "afg25_dcf_gross_amt")
    private Double afg25_dcf_gross_amt;
    @Column(name = "afg25_idsa_gross_amt")
    private Double afg25_idsa_gross_amt;
    @Column(name = "afg25_other_gross_amt")
    private Double afg25_other_gross_amt;
    @Column(name = "afg25_total_gross_amt")
    private Double afg25_total_gross_amt;
    @Column(name = "afg26_lic_deduct_amt")
    private Double afg26_lic_deduct_amt;
    @Column(name = "afg26_ccpf_deduct_amt")
    private Double afg26_ccpf_deduct_amt;
    @Column(name = "afg26_ctnps_deduct_amt")
    private Double afg26_ctnps_deduct_amt;
    @Column(name = "afg26_adnps_deduct_amt")
    private Double afg26_adnps_deduct_amt;
    @Column(name = "afg26_ecnps_deduct_amt")
    private Double afg26_ecnps_deduct_amt;
    @Column(name = "afg26_hip_deduct_amt")
    private Double afg26_hip_deduct_amt;
    @Column(name = "afg26_ilhe_deduct_amt")
    private Double afg26_ilhe_deduct_amt;
    @Column(name = "afg27_dcf_qualify_amt")
    private Double afg27_dcf_qualify_amt;
    @Column(name = "afg26_dcf_deduct_amt")
    private Double afg26_dcf_deduct_amt;
    @Column(name = "afg27_idsa_qualify_amt")
    private Double afg27_idsa_qualify_amt;
    @Column(name = "afg26_idsa_deduct_amt")
    private Double afg26_idsa_deduct_amt;
    @Column(name = "afg27_other_qualify_amt")
    private Double afg27_other_qualify_amt;
    @Column(name = "afg26_other_deduct_amt")
    private Double afg26_other_deduct_amt;
    @Column(name = "afg26_total_deduct_amt")
    private Double afg26_total_deduct_amt;
    @Column(name = "afg27_total_qualify_amt")
    private Double afg27_total_qualify_amt;
    @Column(name = "afgr4_total_amt")
    private Double afgr4_total_amt;
    @Column(name = "afg30_tds_amt")
    private Double afg30_tds_amt;
    @Column(name = "afg30_sur_amt")
    private Double afg30_sur_amt;
    @Column(name = "afg30_ces_amt")
    private Double afg30_ces_amt;
    @Column(name = "afg30_total_amt")
    private Double afg30_total_amt;
    @Column(name = "afg35_rus_amt")
    private Double afg35_rus_amt;
    @Column(name = "afg35_total_amt")
    private Double afg35_total_amt;
    @Column(name = "afg40_itr_amt")
    private Double afg40_itr_amt;
    @Column(name = "afg40_total_amt")
    private Double afg40_total_amt;
    @Column(name = "afgr5_total_amt")
    private Double afgr5_total_amt;
    @Column(name = "afg45_ttce_amt")
    private Double afg45_ttce_amt;
    @Column(name = "afg45_ttpe_amt")
    private Double afg45_ttpe_amt;
    @Column(name = "afg45_total_amt")
    private Double afg45_total_amt;
    @Column(name = "afg46_sftd_amt")
    private Double afg46_sftd_amt;
    @Column(name = "afg46_total_amt")
    private Double afg46_total_amt;
    @Column(name = "afg50_trpepy_flag", length = 1)
    private String afg50_trpepy_flag;
    @Column(name = "afg50_trpepy_panno1", length = 10)
    private String afg50_trpepy_panno1;
    @Column(name = "afg50_trpepy_pname1", length = 75)
    private String afg50_trpepy_pname1;
    @Column(name = "afg50_trpepy_panno2", length = 10)
    private String afg50_trpepy_panno2;
    @Column(name = "afg50_trpepy_pname2", length = 75)
    private String afg50_trpepy_pname2;
    @Column(name = "afg50_trpepy_panno3", length = 10)
    private String afg50_trpepy_panno3;
    @Column(name = "afg50_trpepy_pname3", length = 75)
    private String afg50_trpepy_pname3;
    @Column(name = "afg50_trpepy_panno4", length = 10)
    private String afg50_trpepy_panno4;
    @Column(name = "afg50_trpepy_pname4", length = 75)
    private String afg50_trpepy_pname4;
    @Column(name = "afg50_iplihp_flag", length = 1)
    private String afg50_iplihp_flag;
    @Column(name = "afg50_iplihp_panno1", length = 10)
    private String afg50_iplihp_panno1;
    @Column(name = "afg50_iplihp_pname1", length = 75)
    private String afg50_iplihp_pname1;
    @Column(name = "afg50_iplihp_panno2", length = 10)
    private String afg50_iplihp_panno2;
    @Column(name = "afg50_iplihp_pname2", length = 75)
    private String afg50_iplihp_pname2;
    @Column(name = "afg50_iplihp_panno3", length = 10)
    private String afg50_iplihp_panno3;
    @Column(name = "afg50_iplihp_pname3", length = 75)
    private String afg50_iplihp_pname3;
    @Column(name = "afg50_iplihp_panno4", length = 10)
    private String afg50_iplihp_panno4;
    @Column(name = "afg50_iplihp_pname4", length = 75)
    private String afg50_iplihp_pname4;
    @Column(name = "afg55_cptas_flag", length = 1)
    private String afg55_cptas_flag;
    @Column(name = "afg55_nsaf_name", length = 75)
    private String afg55_nsaf_name;
    @Column(name = "afg55_nsaf_from_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date afg55_nsaf_from_date;
    @Column(name = "afg55_nsaf_to_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date afg55_nsaf_to_date;
    @Column(name = "afg55_crpsf_amt")
    private Double afg55_crpsf_amt;
    @Column(name = "afg55_adp3y_rate")
    private Double afg55_adp3y_rate;
    @Column(name = "afg55_tdrsf_amt")
    private Double afg55_tdrsf_amt;
    @Column(name = "afg55_girsf_amt")
    private Double afg55_girsf_amt;
    @Column(name = "user_code", length = 8)
    private String user_code;
    @Column(name = "lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastupdate;
    @Column(name = "flag", length = 1)
    private String flag;
    @Column(name = "afg50_trpepy_nos")
    private Double afg50_trpepy_nos;
    @Column(name = "afg50_iplihp_nos")
    private Double afg50_iplihp_nos;
    @Column(name = "tax_115bac_flag", length = 1)
    private String tax_115bac_flag;

    public String getTax_115bac_flag() {
        return tax_115bac_flag;
    }

    public void setTax_115bac_flag(String tax_115bac_flag) {
        this.tax_115bac_flag = tax_115bac_flag;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
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

    public Long getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(Long quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Double getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Double process_seqno) {
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
        Date tempDate = null;
        try {
            SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            tempDate = sdp.parse(salary_from_date);
            salary_from_date = sdf.format(tempDate);
        } catch (Exception e) {
        }
        return salary_from_date;
    }

    public void setSalary_from_date(String salary_from_date) {
//        Date tempdate = null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            tempdate = sdf.parse(salary_from_date);
//
//        } catch (Exception e) {
//            tempdate = null;
//        }
        this.salary_from_date = salary_from_date;
    }

    public String getSalary_to_date() {
        Date tempDate = null;
        try {
            SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            tempDate = sdp.parse(salary_to_date);
            salary_to_date = sdf.format(tempDate);
        } catch (Exception e) {
        }
        return salary_to_date;
    }

    public void setSalary_to_date(String salary_to_date) {
//        Date tempdate = null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            tempdate = sdf.parse(salary_to_date);
//
//        } catch (Exception e) {
//            tempdate = null;
//        }
        this.salary_to_date = salary_to_date;
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

    public Date getAfg55_nsaf_from_date() {
        return afg55_nsaf_from_date;
    }

    public void setAfg55_nsaf_from_date(String afg55_nsaf_from_date) {
        Date tempdate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tempdate = sdf.parse(afg55_nsaf_from_date);

        } catch (Exception e) {
            tempdate = null;
        }
        this.afg55_nsaf_from_date = tempdate;
    }

    public Date getAfg55_nsaf_to_date() {
        return afg55_nsaf_to_date;
    }

    public void setAfg55_nsaf_to_date(String afg55_nsaf_to_date) {
        Date tempdate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tempdate = sdf.parse(afg55_nsaf_to_date);

        } catch (Exception e) {
            tempdate = null;
        }
        this.afg55_nsaf_to_date = tempdate;
    }

    public Double getAfg55_crpsf_amt() {
        return afg55_crpsf_amt;
    }

    public void setAfg55_crpsf_amt(Double afg55_crpsf_amt) {
        this.afg55_crpsf_amt = afg55_crpsf_amt;
    }

    public Double getAfg55_adp3y_rate() {
        return afg55_adp3y_rate;
    }

    public void setAfg55_adp3y_rate(Double afg55_adp3y_rate) {
        this.afg55_adp3y_rate = afg55_adp3y_rate;
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

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getValidation_client_code() {
        return validation_client_code;
    }

    public void setValidation_client_code(String validation_client_code) {
        this.validation_client_code = validation_client_code;
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

    public Double getAfgr5_total_amt() {
        return afgr5_total_amt;
    }

    public void setAfgr5_total_amt(Double afgr5_total_amt) {
        this.afgr5_total_amt = afgr5_total_amt;
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

    public Double getAfg50_trpepy_nos() {
        return afg50_trpepy_nos;
    }

    public void setAfg50_trpepy_nos(Double afg50_trpepy_nos) {
        this.afg50_trpepy_nos = afg50_trpepy_nos;
    }

    public Double getAfg50_iplihp_nos() {
        return afg50_iplihp_nos;
    }

//    public Long getAfg01_vpce_amt() {
//        return afg01_vpce_amt;
//    }
//
//    public void setAfg01_vpce_amt(Long afg01_vpce_amt) {
//        this.afg01_vpce_amt = afg01_vpce_amt;
//    }
//
//    public Long getAfg01_plsce_amt() {
//        return afg01_plsce_amt;
//    }
//
//    public void setAfg01_plsce_amt(Long afg01_plsce_amt) {
//        this.afg01_plsce_amt = afg01_plsce_amt;
//    }
//
//    public Long getAfg26_lic_deduct_amt() {
//        return afg26_lic_deduct_amt;
//    }
//
//    public void setAfg26_lic_deduct_amt(Long afg26_lic_deduct_amt) {
//        this.afg26_lic_deduct_amt = afg26_lic_deduct_amt;
//    }
//
//    public Long getAfg26_ccpf_deduct_amt() {
//        return afg26_ccpf_deduct_amt;
//    }
//
//    public void setAfg26_ccpf_deduct_amt(Long afg26_ccpf_deduct_amt) {
//        this.afg26_ccpf_deduct_amt = afg26_ccpf_deduct_amt;
//    }
//
//    public Long getAfg26_ctnps_deduct_amt() {
//        return afg26_ctnps_deduct_amt;
//    }
//
//    public void setAfg26_ctnps_deduct_amt(Long afg26_ctnps_deduct_amt) {
//        this.afg26_ctnps_deduct_amt = afg26_ctnps_deduct_amt;
//    }
//
//    public Long getAfg26_adnps_deduct_amt() {
//        return afg26_adnps_deduct_amt;
//    }
//
//    public void setAfg26_adnps_deduct_amt(Long afg26_adnps_deduct_amt) {
//        this.afg26_adnps_deduct_amt = afg26_adnps_deduct_amt;
//    }
//
//    public Long getAfg26_ecnps_deduct_amt() {
//        return afg26_ecnps_deduct_amt;
//    }
//
//    public void setAfg26_ecnps_deduct_amt(Long afg26_ecnps_deduct_amt) {
//        this.afg26_ecnps_deduct_amt = afg26_ecnps_deduct_amt;
//    }
//
//    public Long getAfg26_hip_deduct_amt() {
//        return afg26_hip_deduct_amt;
//    }
//
//    public void setAfg26_hip_deduct_amt(Long afg26_hip_deduct_amt) {
//        this.afg26_hip_deduct_amt = afg26_hip_deduct_amt;
//    }
//
//    public Long getAfg26_ilhe_deduct_amt() {
//        return afg26_ilhe_deduct_amt;
//    }
//
//    public void setAfg26_ilhe_deduct_amt(Long afg26_ilhe_deduct_amt) {
//        this.afg26_ilhe_deduct_amt = afg26_ilhe_deduct_amt;
//    }
//
//    public Long getAfg27_dcf_qualify_amt() {
//        return afg27_dcf_qualify_amt;
//    }
//
//    public void setAfg27_dcf_qualify_amt(Long afg27_dcf_qualify_amt) {
//        this.afg27_dcf_qualify_amt = afg27_dcf_qualify_amt;
//    }
//
//    public Long getAfg26_dcf_deduct_amt() {
//        return afg26_dcf_deduct_amt;
//    }
//
//    public void setAfg26_dcf_deduct_amt(Long afg26_dcf_deduct_amt) {
//        this.afg26_dcf_deduct_amt = afg26_dcf_deduct_amt;
//    }
//
//    public Long getAfg27_idsa_qualify_amt() {
//        return afg27_idsa_qualify_amt;
//    }
//
//    public void setAfg27_idsa_qualify_amt(Long afg27_idsa_qualify_amt) {
//        this.afg27_idsa_qualify_amt = afg27_idsa_qualify_amt;
//    }
//
//    public Long getAfg26_idsa_deduct_amt() {
//        return afg26_idsa_deduct_amt;
//    }
//
//    public void setAfg26_idsa_deduct_amt(Long afg26_idsa_deduct_amt) {
//        this.afg26_idsa_deduct_amt = afg26_idsa_deduct_amt;
//    }
//
//    public Long getAfg27_other_qualify_amt() {
//        return afg27_other_qualify_amt;
//    }
//
//    public void setAfg27_other_qualify_amt(Long afg27_other_qualify_amt) {
//        this.afg27_other_qualify_amt = afg27_other_qualify_amt;
//    }
//
//    public Long getAfg26_other_deduct_amt() {
//        return afg26_other_deduct_amt;
//    }
//
//    public void setAfg26_other_deduct_amt(Long afg26_other_deduct_amt) {
//        this.afg26_other_deduct_amt = afg26_other_deduct_amt;
//    }
//
//    public Long getAfg26_total_deduct_amt() {
//        return afg26_total_deduct_amt;
//    }
//
//    public void setAfg26_total_deduct_amt(Long afg26_total_deduct_amt) {
//        this.afg26_total_deduct_amt = afg26_total_deduct_amt;
//    }
//
//    public Long getAfg27_total_qualify_amt() {
//        return afg27_total_qualify_amt;
//    }
//
//    public void setAfg27_total_qualify_amt(Long afg27_total_qualify_amt) {
//        this.afg27_total_qualify_amt = afg27_total_qualify_amt;
//    }
//
//    public Long getAfgr5_total_amt() {
//        return afgr5_total_amt;
//    }
//
//    public void setAfgr5_total_amt(Long afgr5_total_amt) {
//        this.afgr5_total_amt = afgr5_total_amt;
//    }
//
//    public Long getAfg46_sftd_amt() {
//        return afg46_sftd_amt;
//    }
//
//    public void setAfg46_sftd_amt(Long afg46_sftd_amt) {
//        this.afg46_sftd_amt = afg46_sftd_amt;
//    }
//
//    public Long getAfg46_total_amt() {
//        return afg46_total_amt;
//    }
//
//    public void setAfg46_total_amt(Long afg46_total_amt) {
//        this.afg46_total_amt = afg46_total_amt;
//    }
    public void setAfg50_iplihp_nos(Double afg50_iplihp_nos) {
        this.afg50_iplihp_nos = afg50_iplihp_nos;
    }

    @Override
    public String toString() {
        return "SalaryTranLoad{" + "rowid_seq=" + rowid_seq + ",\n entity_code=" + entity_code + ",\n client_code=" + client_code + ",\n acc_year=" + acc_year + ",\n tds_type_code=" + tds_type_code + ",\n quarter_no=" + quarter_no + ",\n month=" + month + ",\n from_date=" + from_date + ",\n to_date=" + to_date + ",\n session_id=" + session_id + ",\n process_seqno=" + process_seqno + ",\n deductee_code=" + deductee_code + ",\n deductee_name=" + deductee_name + ",\n deductee_catg=" + deductee_catg + ",\n deductee_panno=" + deductee_panno + ",\n deductee_phoneno=" + deductee_phoneno + ",\n deductee_address=" + deductee_address + ",\n deductee_account_no=" + deductee_account_no + ",\n deductee_ppo_no=" + deductee_ppo_no + ",\n deductee_bank_branch_code=" + deductee_bank_branch_code + ",\n validation_client_code=" + validation_client_code + ",\n identification_no=" + identification_no + ",\n deductee_panno_verified=" + deductee_panno_verified + ",\n deductee_panno_valid=" + deductee_panno_valid + ",\n deductee_ref_code1=" + deductee_ref_code1 + ",\n deductee_ref_code2=" + deductee_ref_code2 + ",\n salary_from_date=" + salary_from_date + ",\n salary_to_date=" + salary_to_date + ",\n afg01_pes_amt=" + afg01_pes_amt + ",\n afg01_ces_amt=" + afg01_ces_amt + ",\n afg01_vpce_amt=" + afg01_vpce_amt + ",\n afg01_plsce_amt=" + afg01_plsce_amt + ",\n afg01_total_amt=" + afg01_total_amt + ",\n afg05_tca_amt=" + afg05_tca_amt + ",\n afg05_drg_amt=" + afg05_drg_amt + ",\n afg05_cvp_amt=" + afg05_cvp_amt + ",\n afg05_celse_amt=" + afg05_celse_amt + ",\n afg05_hra_amt=" + afg05_hra_amt + ",\n afg05_other_amt=" + afg05_other_amt + ",\n afg05_total_amt=" + afg05_total_amt + ",\n afgr1_total_amt=" + afgr1_total_amt + ",\n afg10_pt_amt=" + afg10_pt_amt + ",\n afg10_eag_amt=" + afg10_eag_amt + ",\n afg10_sd_amt=" + afg10_sd_amt + ",\n afg10_total_amt=" + afg10_total_amt + ",\n afgr2_total_amt=" + afgr2_total_amt + ",\n afg15_riot_amt=" + afg15_riot_amt + ",\n afg15_iphl_amt=" + afg15_iphl_amt + ",\n afg15_total_amt=" + afg15_total_amt + ",\n afg20_ios_amt=" + afg20_ios_amt + ",\n afg20_total_amt=" + afg20_total_amt + ",\n afgr3_total_amt=" + afgr3_total_amt + ",\n afg25_lic_gross_amt=" + afg25_lic_gross_amt + ",\n afg25_ccpf_gross_amt=" + afg25_ccpf_gross_amt + ",\n afg25_ctnps_gross_amt=" + afg25_ctnps_gross_amt + ",\n afg25_adnps_gross_amt=" + afg25_adnps_gross_amt + ",\n afg25_ecnps_gross_amt=" + afg25_ecnps_gross_amt + ",\n afg25_hip_gross_amt=" + afg25_hip_gross_amt + ",\n afg25_ilhe_gross_amt=" + afg25_ilhe_gross_amt + ",\n afg25_dcf_gross_amt=" + afg25_dcf_gross_amt + ",\n afg25_idsa_gross_amt=" + afg25_idsa_gross_amt + ",\n afg25_other_gross_amt=" + afg25_other_gross_amt + ",\n afg25_total_gross_amt=" + afg25_total_gross_amt + ",\n afg26_lic_deduct_amt=" + afg26_lic_deduct_amt + ",\n afg26_ccpf_deduct_amt=" + afg26_ccpf_deduct_amt + ",\n afg26_ctnps_deduct_amt=" + afg26_ctnps_deduct_amt + ",\n afg26_adnps_deduct_amt=" + afg26_adnps_deduct_amt + ",\n afg26_ecnps_deduct_amt=" + afg26_ecnps_deduct_amt + ",\n afg26_hip_deduct_amt=" + afg26_hip_deduct_amt + ",\n afg26_ilhe_deduct_amt=" + afg26_ilhe_deduct_amt + ",\n afg27_dcf_qualify_amt=" + afg27_dcf_qualify_amt + ",\n afg26_dcf_deduct_amt=" + afg26_dcf_deduct_amt + ",\n afg27_idsa_qualify_amt=" + afg27_idsa_qualify_amt + ",\n afg26_idsa_deduct_amt=" + afg26_idsa_deduct_amt + ",\n afg27_other_qualify_amt=" + afg27_other_qualify_amt + ",\n afg26_other_deduct_amt=" + afg26_other_deduct_amt + ",\n afg26_total_deduct_amt=" + afg26_total_deduct_amt + ",\n afg27_total_qualify_amt=" + afg27_total_qualify_amt + ",\n afgr4_total_amt=" + afgr4_total_amt + ",\n afg30_tds_amt=" + afg30_tds_amt + ",\n afg30_sur_amt=" + afg30_sur_amt + ",\n afg30_ces_amt=" + afg30_ces_amt + ",\n afg30_total_amt=" + afg30_total_amt + ",\n afg35_rus_amt=" + afg35_rus_amt + ",\n afg35_total_amt=" + afg35_total_amt + ",\n afg40_itr_amt=" + afg40_itr_amt + ",\n afg40_total_amt=" + afg40_total_amt + ",\n afgr5_total_amt=" + afgr5_total_amt + ",\n afg45_ttce_amt=" + afg45_ttce_amt + ",\n afg45_ttpe_amt=" + afg45_ttpe_amt + ",\n afg45_total_amt=" + afg45_total_amt + ",\n afg46_sftd_amt=" + afg46_sftd_amt + ",\n afg46_total_amt=" + afg46_total_amt + ",\n afg50_trpepy_flag=" + afg50_trpepy_flag + ",\n afg50_trpepy_panno1=" + afg50_trpepy_panno1 + ",\n afg50_trpepy_pname1=" + afg50_trpepy_pname1 + ",\n afg50_trpepy_panno2=" + afg50_trpepy_panno2 + ",\n afg50_trpepy_pname2=" + afg50_trpepy_pname2 + ",\n afg50_trpepy_panno3=" + afg50_trpepy_panno3 + ",\n afg50_trpepy_pname3=" + afg50_trpepy_pname3 + ",\n afg50_trpepy_panno4=" + afg50_trpepy_panno4 + ",\n afg50_trpepy_pname4=" + afg50_trpepy_pname4 + ",\n afg50_iplihp_flag=" + afg50_iplihp_flag + ",\n afg50_iplihp_panno1=" + afg50_iplihp_panno1 + ",\n afg50_iplihp_pname1=" + afg50_iplihp_pname1 + ",\n afg50_iplihp_panno2=" + afg50_iplihp_panno2 + ",\n afg50_iplihp_pname2=" + afg50_iplihp_pname2 + ",\n afg50_iplihp_panno3=" + afg50_iplihp_panno3 + ",\n afg50_iplihp_pname3=" + afg50_iplihp_pname3 + ",\n afg50_iplihp_panno4=" + afg50_iplihp_panno4 + ",\n afg50_iplihp_pname4=" + afg50_iplihp_pname4 + ",\n afg55_cptas_flag=" + afg55_cptas_flag + ",\n afg55_nsaf_name=" + afg55_nsaf_name + ",\n afg55_nsaf_from_date=" + afg55_nsaf_from_date + ",\n afg55_nsaf_to_date=" + afg55_nsaf_to_date + ",\n afg55_crpsf_amt=" + afg55_crpsf_amt + ",\n afg55_adp3y_rate=" + afg55_adp3y_rate + ",\n afg55_tdrsf_amt=" + afg55_tdrsf_amt + ",\n afg55_girsf_amt=" + afg55_girsf_amt + ",\n user_code=" + user_code + ",\n lastupdate=" + lastupdate + ",\n flag=" + flag + ",\n afg50_trpepy_nos=" + afg50_trpepy_nos + ",\n afg50_iplihp_nos=" + afg50_iplihp_nos + '}';
    }

}
