/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author karan.karwat
 */
@Entity
@Table(name = "VIEW_SALARY_EMPLOYEE")
public class ViewSalaryEmployee implements java.io.Serializable {

    @Id
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Id
    @Column(name = "deductee_code", length = 15, nullable = false)
    private String deductee_code;
    @Column(name = "deductee_name", length = 100, nullable = true)
    private String deductee_name;
    @Column(name = "panno", length = 10, nullable = true)
    private String panno;
    @Id
    @Column(name = "sex", length = 1, nullable = true)
    private String sex;
    @Id
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Id
    @Column(name = "itax_catg", length = 2, nullable = true)
    private String itax_catg;
    @Column(name = "itax_catg_name", length = 20, nullable = true)
    private String itax_catg_name;
    @Column(name = "deductee_catg", length = 5, nullable = true)
    private String deductee_catg;
    @Column(name = "deductee_catg_name", length = 4000, nullable = true)
    private String deductee_catg_name;
    @Column(name = "deduction_ref_no", length = 100, nullable = true)
    private String deduction_ref_no;
    @Column(name = "taxable_salary", nullable = true)
    private Long taxable_salary;
    @Column(name = "total_tax", nullable = true)
    private Long total_tax;
    @Column(name = "total_tds", nullable = true)
    private Long total_tds;
    @Column(name = "excess_shortfall", nullable = true)
    private Long excess_shortfall;
    @Column(name = "from_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;

    public ViewSalaryEmployee() {
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

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getItax_catg() {
        return itax_catg;
    }

    public void setItax_catg(String itax_catg) {
        this.itax_catg = itax_catg;
    }

    public String getItax_catg_name() {
        return itax_catg_name;
    }

    public void setItax_catg_name(String itax_catg_name) {
        this.itax_catg_name = itax_catg_name;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getDeductee_catg_name() {
        return deductee_catg_name;
    }

    public void setDeductee_catg_name(String deductee_catg_name) {
        this.deductee_catg_name = deductee_catg_name;
    }

    public String getDeduction_ref_no() {
        return deduction_ref_no;
    }

    public void setDeduction_ref_no(String deduction_ref_no) {
        this.deduction_ref_no = deduction_ref_no;
    }

    public Long getTaxable_salary() {
        return taxable_salary;
    }

    public void setTaxable_salary(Long taxable_salary) {
        this.taxable_salary = taxable_salary;
    }

    public Long getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(Long total_tax) {
        this.total_tax = total_tax;
    }

    public Long getTotal_tds() {
        return total_tds;
    }

    public void setTotal_tds(Long total_tds) {
        this.total_tds = total_tds;
    }

    public Long getExcess_shortfall() {
        return excess_shortfall;
    }

    public void setExcess_shortfall(Long excess_shortfall) {
        this.excess_shortfall = excess_shortfall;
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

    
}
