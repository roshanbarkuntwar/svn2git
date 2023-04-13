/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author harshad.umredkar
 */
@Entity
@Table(name = "deductee_bflag_refinfo_tran")
public class DeducteeBflagRefinfoTran implements Serializable {

    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Id
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "deductee_code", length = 15, nullable = false)
    private String deductee_code;
    @Id
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
//    @Column(name = "reference_no", length = 10, nullable = false)
    @Column(name = "reference_no", length = 10, nullable = true)
    private String reference_no;
    @Column(name = "bflag", length = 1, nullable = false)
    private String bflag;
    @Column(name = "assessed_tax_flag", length = 1, nullable = true)
    private String assessed_tax_flag;
    @Column(name = "latest_assessment_year", length = 5, nullable = true)
    private String latest_assessment_year;
    @Column(name = "est_declaration_income", nullable = true)
    private String est_declaration_income;
    @Column(name = "est_total_income", nullable = true)
    private String est_total_income;
    @Column(name = "total_no_form", nullable = true)
    private String total_no_form;
    @Column(name = "aggregate_income_amt", nullable = true)
    private String aggregate_income_amt;
    @Column(name = "declaration_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date declaration_date;
    @Column(name = "income_amount_paid", nullable = true)
    private String income_amount_paid;
    @Column(name = "income_paid_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date income_paid_date;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Id
    @Column(name = "panno", length = 10, nullable = false)
    private String panno;
    @Column(name = "pan_status", length = 1, nullable = true)
    private String pan_status;

    public DeducteeBflagRefinfoTran() {

    }

    public DeducteeBflagRefinfoTran(String entity_code, String client_code, String deductee_code, String acc_year, String reference_no, String bflag, String assessed_tax_flag, String latest_assessment_year, String est_declaration_income, String est_total_income, String total_no_form, String aggregate_income_amt, Date declaration_date, String income_amount_paid, Date income_paid_date, String user_code, Date lastupdate, String flag, String panno) {
        this.entity_code = entity_code;
        this.client_code = client_code;
        this.deductee_code = client_code;
        this.acc_year = acc_year;
        this.reference_no = reference_no;
        this.bflag = bflag;
        this.assessed_tax_flag = assessed_tax_flag;
        this.latest_assessment_year = latest_assessment_year;
        this.est_declaration_income = est_declaration_income;
        this.est_total_income = est_total_income;
        this.total_no_form = total_no_form;
        this.aggregate_income_amt = aggregate_income_amt;
        this.declaration_date = declaration_date;
        this.income_amount_paid = income_amount_paid;
        this.income_paid_date = income_paid_date;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.panno = panno;
    }

    public String getPan_status() {
        return pan_status;
    }

    public void setPan_status(String pan_status) {
        this.pan_status = pan_status;
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

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getBflag() {
        return bflag;
    }

    public void setBflag(String bflag) {
        this.bflag = bflag;
    }

    public String getAssessed_tax_flag() {
        return assessed_tax_flag;
    }

    public void setAssessed_tax_flag(String assessed_tax_flag) {
        this.assessed_tax_flag = assessed_tax_flag;
    }

    public String getLatest_assessment_year() {
        return latest_assessment_year;
    }

    public void setLatest_assessment_year(String latest_assessment_year) {
        this.latest_assessment_year = latest_assessment_year;
    }

    public void setDeclaration_date(String declaration_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(declaration_date);
        } catch (Exception e) {
            d = null;
        }
        this.declaration_date = d;
    }

    public Date getIncome_paid_date() {
        return income_paid_date;
    }

    public void setIncome_paid_date(String income_paid_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(income_paid_date);
        } catch (Exception e) {
            d = null;
        }
        this.income_paid_date = d;
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

    public String getEst_declaration_income() {
        return est_declaration_income;
    }

    public void setEst_declaration_income(String est_declaration_income) {
        this.est_declaration_income = est_declaration_income;
    }

    public String getEst_total_income() {
        return est_total_income;
    }

    public void setEst_total_income(String est_total_income) {
        this.est_total_income = est_total_income;
    }

    public String getTotal_no_form() {
        return total_no_form;
    }

    public void setTotal_no_form(String total_no_form) {
        this.total_no_form = total_no_form;
    }

    public String getAggregate_income_amt() {
        return aggregate_income_amt;
    }

    public void setAggregate_income_amt(String aggregate_income_amt) {
        this.aggregate_income_amt = aggregate_income_amt;
    }

    public Date getDeclaration_date() {
        return declaration_date;
    }

//    public void setDeclaration_date(Date declaration_date) {
//        this.declaration_date = declaration_date;
//    }
    public String getIncome_amount_paid() {
        return income_amount_paid;
    }

    public void setIncome_amount_paid(String income_amount_paid) {
        this.income_amount_paid = income_amount_paid;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
