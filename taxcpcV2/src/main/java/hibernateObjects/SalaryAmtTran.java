/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author bhawna.agrawal
 */
@Entity
@Table(name = "salary_amt_tran")

public class SalaryAmtTran implements java.io.Serializable {

    private String deductee_code;
    @Id
    private String afcode;
    @Column(name = "remark", length = 240, nullable = true)
    private String remark;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true, unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "earn_amt", length = 14, precision = 2, nullable = true)
    private String earn_amt;
    @Column(name = "deduct_amt", length = 14, precision = 2, nullable = true)
    private String deduct_amt;
    @Column(name = "emp_catg", length = 2, nullable = true)
    private String emp_catg;
    @Column(name = "payroll_series", length = 2, nullable = true)
    private String payroll_series;
    @Column(name = "afrate", length = 10, precision = 2, nullable = true)
    private String afrate;
    @Column(name = "paydays", length = 8, nullable = true)
    private Long paydays;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "cal_year", length = 4, nullable = true)
    private String cal_year;
    @Column(name = "ctc_amt", nullable = true)
    private Long ctc_amt;
    @Column(name = "resume_id", length = 5, nullable = true)
    private String resume_id;
    @Column(name = "jobid", length = 11, nullable = true)
    private String jobid;
    @Column(name = "acc_vrno", length = 11, nullable = true)
    private String acc_vrno;
    @Column(name = "acc_slno", length = 3, nullable = true)
    private Long acc_slno;
    @Column(name = "acc_tcode", length = 1, nullable = true)
    private String acc_tcode;
    @Column(name = "pays", length = 8, nullable = true)
    private Long pays;
    @Column(name = "approvedby", length = 8, nullable = true)
    private String approvedby;
    @Column(name = "approveddate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveddate;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "itax_order", length = 4, nullable = true)
    private Long itax_order;
    @Column(name = "afamt_limit", length = 9, precision = 2, nullable = true)
    private Long afamt_limit;
    @Column(name = "proposed_amt", length = 14, precision = 2, nullable = true)
    private Long proposed_amt;
    @Id
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "itax_catg", length = 2, nullable = true)
    private String itax_catg;
    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Id
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "data_entry_flag", length = 1, nullable = true)
    private String data_entry_flag;
    @Column(name = "update_seq",  nullable = true)
    private Long update_seq;
    @Column(name = "panno", length = 10, nullable = true)
    private String panno;
    @Column(name = "landlord_name", length = 75, nullable = true)
    private String landlord_name;
    @Column(name = "tran_from_date",nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tran_from_date;
    @Column(name = "tran_to_date",nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tran_to_date;
    @Id
    @Column(name = "rowid_seq", nullable = true)
    private Long rowid_seq;

    public SalaryAmtTran() {
        this.user_code = "SHASHANK";
        this.lastupdate = new Date();
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getAfcode() {
        return afcode;
    }

    public void setAfcode(String afcode) {
        this.afcode = afcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        Date d;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(from_date);
        } catch (Exception e) {
            d = null;
        }
        this.from_date = d;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(to_date);
        } catch (Exception e) {
            d = null;
        }
        this.to_date = d;
    }

    public String getEarn_amt() {
        return earn_amt;
    }

    public void setEarn_amt(String earn_amt) {
        this.earn_amt = earn_amt;
    }

    public String getDeduct_amt() {
        return deduct_amt;
    }

    public void setDeduct_amt(String deduct_amt) {
        this.deduct_amt = deduct_amt;
    }

    public String getEmp_catg() {
        return emp_catg;
    }

    public void setEmp_catg(String emp_catg) {
        this.emp_catg = emp_catg;
    }

    public String getPayroll_series() {
        return payroll_series;
    }

    public void setPayroll_series(String payroll_series) {
        this.payroll_series = payroll_series;
    }

    public String getAfrate() {
        return afrate;
    }

    public void setAfrate(String afrate) {
        this.afrate = afrate;
    }

    public Long getPaydays() {
        return paydays;
    }

    public void setPaydays(Long paydays) {
        this.paydays = paydays;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCal_year() {
        return cal_year;
    }

    public void setCal_year(String cal_year) {
        this.cal_year = cal_year;
    }

    public Long getCtc_amt() {
        return ctc_amt;
    }

    public void setCtc_amt(Long ctc_amt) {
        this.ctc_amt = ctc_amt;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getAcc_vrno() {
        return acc_vrno;
    }

    public void setAcc_vrno(String acc_vrno) {
        this.acc_vrno = acc_vrno;
    }

    public Long getAcc_slno() {
        return acc_slno;
    }

    public void setAcc_slno(Long acc_slno) {
        this.acc_slno = acc_slno;
    }

    public String getAcc_tcode() {
        return acc_tcode;
    }

    public void setAcc_tcode(String acc_tcode) {
        this.acc_tcode = acc_tcode;
    }

    public Long getPays() {
        return pays;
    }

    public void setPays(Long pays) {
        this.pays = pays;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Date getApproveddate() {
        return approveddate;
    }

    public void setApproveddate(Date approveddate) {
        this.approveddate = approveddate;
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

    public Long getItax_order() {
        return itax_order;
    }

    public void setItax_order(Long itax_order) {
        this.itax_order = itax_order;
    }

    public Long getAfamt_limit() {
        return afamt_limit;
    }

    public void setAfamt_limit(Long afamt_limit) {
        this.afamt_limit = afamt_limit;
    }

    public void setProposed_amt(Long proposed_amt) {
        this.proposed_amt = proposed_amt;
    }

    public Long getProposed_amt() {
        return proposed_amt;
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

    public String getData_entry_flag() {
        return data_entry_flag;
    }

    public void setData_entry_flag(String data_entry_flag) {
        this.data_entry_flag = data_entry_flag;
    }

    public Long getUpdate_seq() {
        return update_seq;
    }

    public void setUpdate_seq(Long update_seq) {
        this.update_seq = update_seq;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getLandlord_name() {
        return landlord_name;
    }

    public void setLandlord_name(String landlord_name) {
        this.landlord_name = landlord_name;
    }

    public Date getTran_from_date() {
        return tran_from_date;
    }

    public void setTran_from_date(String tran_from_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(tran_from_date);
        } catch (Exception e) {
            d = null;
        }
        this.tran_from_date = d;
    }

    public Date getTran_to_date() {
        return tran_to_date;
    }

    public void setTran_to_date(String tran_to_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(tran_to_date);
        } catch (Exception e) {
            d = null;
        }
        this.tran_to_date = d;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
