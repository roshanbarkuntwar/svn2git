/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "VIEW_SALARY_DATA_VALIDATION")
public class ViewSalaryDataValidation implements java.io.Serializable {

    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;

    @Column(name = "deductee_code", length = 15, nullable = false)
    private String deductee_code;
    @Column(name = "deductee_name", length = 100, nullable = true)
    private String deductee_name;

    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "sal_current_empl")
    private String sal_current_empl;
    @Column(name = "sal_previous_empl")
    private String sal_previous_empl;
    @Column(name = "dedu_entr_alw")
    private String dedu_entr_alw;
    @Column(name = "dedu_prof_tax")
    private String dedu_prof_tax;
    @Column(name = "inc_oth_sal")
    private String inc_oth_sal;
    @Column(name = "gross_total_sal")
    private String gross_total_sal;
    @Column(name = "dedu_und_80cc")
    private String dedu_und_80cc;
    @Column(name = "dedu_und_80ccg")
    private String dedu_und_80ccg;
    @Column(name = "dedu_und_oth")
    private String dedu_und_oth;
    @Column(name = "total_tax_income")
    private String total_tax_income;
    @Column(name = "inc_tax_on_incm")
    private String inc_tax_on_incm;
    @Column(name = "surcharge")
    private String surcharge;
    @Column(name = "education_cess")
    private String education_cess;
    @Column(name = "relief_und_89")
    private String relief_und_89;
    @Column(name = "total_tax_deductible")
    private String total_tax_deductible;
    @Column(name = "tds_current_empl")
    private String tds_current_empl;
    @Column(name = "tds_previous_empl")
    private String tds_previous_empl;
    @Column(name = "inc_tax_on_incm_calc")
    private String inc_tax_on_incm_calc;
    @Column(name = "shortfall_excess_deduction")
    private String shortfall_excess_deduction;
    @Column(name = "itax_catg", length = 1)
    private String itax_catg;
    @Column(name = "itax_catg_name", length = 1)
    private String itax_catg_name;
    @Column(name = "deductee_catg", length = 20, nullable = true)
    private String deductee_catg;
    @Column(name = "panno", length = 10)
    private String panno;
    @Column(name = "deduction_ref_no", length = 100)
    private String deduction_ref_no;
    @Column(name = "identification_no", length = 50, nullable = true)
    private String identification_no;
    @Column(name = "bank_branch_code", length = 15)
    private String bank_branch_code;

//    @Column(name = "deductee_panno_verified", length = 50, nullable = true)
//    private String deductee_panno_verified;
    @Id
    @Column(name = "rowid_seq", nullable = true)
    private Long rowid_seq;

    @Column(name = "deductee_panno_verified", length = 50, nullable = true)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 50, nullable = true)
    private String deductee_panno_valid;

    @Transient
    private String duplicateFlag;

    public ViewSalaryDataValidation() {

    }

//    public String getDeductee_panno_verified() {
//        return deductee_panno_verified;
//    }
//
//    public void setDeductee_panno_verified(String deductee_panno_verified) {
//        this.deductee_panno_verified = deductee_panno_verified;
//    }
    public String getDuplicateFlag() {
        return duplicateFlag;
    }

    public void setDuplicateFlag(String duplicateFlag) {
        this.duplicateFlag = duplicateFlag;
    }

    public String getIdentification_no() {
        return identification_no;
    }

    public void setIdentification_no(String identification_no) {
        this.identification_no = identification_no;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
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

    public String getSal_current_empl() {
        return sal_current_empl;
    }

    public void setSal_current_empl(String sal_current_empl) {
        this.sal_current_empl = sal_current_empl;
    }

    public String getSal_previous_empl() {
        return sal_previous_empl;
    }

    public void setSal_previous_empl(String sal_previous_empl) {
        this.sal_previous_empl = sal_previous_empl;
    }

    public String getDedu_entr_alw() {
        return dedu_entr_alw;
    }

    public void setDedu_entr_alw(String dedu_entr_alw) {
        this.dedu_entr_alw = dedu_entr_alw;
    }

    public String getDedu_prof_tax() {
        return dedu_prof_tax;
    }

    public void setDedu_prof_tax(String dedu_prof_tax) {
        this.dedu_prof_tax = dedu_prof_tax;
    }

    public String getInc_oth_sal() {
        return inc_oth_sal;
    }

    public void setInc_oth_sal(String inc_oth_sal) {
        this.inc_oth_sal = inc_oth_sal;
    }

    public String getGross_total_sal() {
        return gross_total_sal;
    }

    public void setGross_total_sal(String gross_total_sal) {
        this.gross_total_sal = gross_total_sal;
    }

    public String getDedu_und_80cc() {
        return dedu_und_80cc;
    }

    public void setDedu_und_80cc(String dedu_und_80cc) {
        this.dedu_und_80cc = dedu_und_80cc;
    }

    public String getDedu_und_80ccg() {
        return dedu_und_80ccg;
    }

    public void setDedu_und_80ccg(String dedu_und_80ccg) {
        this.dedu_und_80ccg = dedu_und_80ccg;
    }

    public String getDedu_und_oth() {
        return dedu_und_oth;
    }

    public void setDedu_und_oth(String dedu_und_oth) {
        this.dedu_und_oth = dedu_und_oth;
    }

    public String getTotal_tax_income() {
        return total_tax_income;
    }

    public void setTotal_tax_income(String total_tax_income) {
        this.total_tax_income = total_tax_income;
    }

    public String getInc_tax_on_incm() {
        return inc_tax_on_incm;
    }

    public void setInc_tax_on_incm(String inc_tax_on_incm) {
        this.inc_tax_on_incm = inc_tax_on_incm;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getEducation_cess() {
        return education_cess;
    }

    public void setEducation_cess(String education_cess) {
        this.education_cess = education_cess;
    }

    public String getRelief_und_89() {
        return relief_und_89;
    }

    public void setRelief_und_89(String relief_und_89) {
        this.relief_und_89 = relief_und_89;
    }

    public String getTotal_tax_deductible() {
        return total_tax_deductible;
    }

    public void setTotal_tax_deductible(String total_tax_deductible) {
        this.total_tax_deductible = total_tax_deductible;
    }

    public String getTds_current_empl() {
        return tds_current_empl;
    }

    public void setTds_current_empl(String tds_current_empl) {
        this.tds_current_empl = tds_current_empl;
    }

    public String getTds_previous_empl() {
        return tds_previous_empl;
    }

    public void setTds_previous_empl(String tds_previous_empl) {
        this.tds_previous_empl = tds_previous_empl;
    }

    public String getInc_tax_on_incm_calc() {
        return inc_tax_on_incm_calc;
    }

    public void setInc_tax_on_incm_calc(String inc_tax_on_incm_calc) {
        this.inc_tax_on_incm_calc = inc_tax_on_incm_calc;
    }

    public String getShortfall_excess_deduction() {
        return shortfall_excess_deduction;
    }

    public void setShortfall_excess_deduction(String shortfall_excess_deduction) {
        this.shortfall_excess_deduction = shortfall_excess_deduction;
    }

    public String getItax_catg() {
        return itax_catg;
    }

    public void setItax_catg(String itax_catg) {
        this.itax_catg = itax_catg;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getItax_catg_name() {
        return itax_catg_name;
    }

    public void setItax_catg_name(String itax_catg_name) {
        this.itax_catg_name = itax_catg_name;
    }

    public String getDeduction_ref_no() {
        return deduction_ref_no;
    }

    public void setDeduction_ref_no(String deduction_ref_no) {
        this.deduction_ref_no = deduction_ref_no;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
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

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }
        
    @Override
    public String toString() {
        return new Util().printObjectAsString(this);
    }

}
