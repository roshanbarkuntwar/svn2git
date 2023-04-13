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
 * @author ayushi.jain
 */
@Entity
@Table(name = "LHSSYS_TDS_RETURN_TRAN")
public class LhssysTdsReturnTran implements java.io.Serializable {

    @Id
    @Column(name = "rowid_seq", length = 22, nullable = false)
    private Long rowid_seq;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "quarter_no", length = 22, nullable = true)
    private String quarter_no;
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Column(name = "tanno", length = 12, nullable = true)
    private String tanno;
    @Column(name = "tds_challan_rec", length = 22, nullable = true)
    private String tds_challan_rec;
    @Column(name = "tds_challan_amt", length = 22, nullable = true)
    private String tds_challan_amt;
    @Column(name = "deductee_rec", length = 22, nullable = true)
    private String deductee_rec;
    @Column(name = "deductee_tran_amt", length = 22, nullable = true)
    private String deductee_tran_amt;
    @Column(name = "deductee_tds_amt", length = 22, nullable = true)
    private String deductee_tds_amt;
    @Column(name = "file_upload_ack_no", length = 25, nullable = true)
    private String file_upload_ack_no;
    @Column(name = "file_upload_ack_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date file_upload_ack_date;
    @Column(name = "file_upload_ack_pdf_path", length = 100, nullable = true)
    private String file_upload_ack_pdf_path;
    @Column(name = "file_upload_ack_pdf_name", length = 100, nullable = true)
    private String file_upload_ack_pdf_name;
    @Column(name = "form16_file_status", length = 1, nullable = true)
    private String form16_file_status;
    @Column(name = "form16_file_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date form16_file_date;
    @Column(name = "form16_deductee_rec", length = 22, nullable = true)
    private String form16_deductee_rec;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "fvu_file_generated_flag", length = 1, nullable = true)
    private String fvu_file_generated_flag;

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

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getTds_challan_rec() {
        return tds_challan_rec;
    }

    public void setTds_challan_rec(String tds_challan_rec) {
        this.tds_challan_rec = tds_challan_rec;
    }

    public String getTds_challan_amt() {
        return tds_challan_amt;
    }

    public void setTds_challan_amt(String tds_challan_amt) {
        this.tds_challan_amt = tds_challan_amt;
    }

    public String getDeductee_rec() {
        return deductee_rec;
    }

    public void setDeductee_rec(String deductee_rec) {
        this.deductee_rec = deductee_rec;
    }

    public String getDeductee_tran_amt() {
        return deductee_tran_amt;
    }

    public void setDeductee_tran_amt(String deductee_tran_amt) {
        this.deductee_tran_amt = deductee_tran_amt;
    }

    public String getDeductee_tds_amt() {
        return deductee_tds_amt;
    }

    public void setDeductee_tds_amt(String deductee_tds_amt) {
        this.deductee_tds_amt = deductee_tds_amt;
    }

    public String getFile_upload_ack_no() {
        return file_upload_ack_no;
    }

    public void setFile_upload_ack_no(String file_upload_ack_no) {
        this.file_upload_ack_no = file_upload_ack_no;
    }

    public Date getFile_upload_ack_date() {
        return file_upload_ack_date;
    }

    public void setFile_upload_ack_date(Date file_upload_ack_date) {
        this.file_upload_ack_date = file_upload_ack_date;
    }

    public String getFile_upload_ack_pdf_path() {
        return file_upload_ack_pdf_path;
    }

    public void setFile_upload_ack_pdf_path(String file_upload_ack_pdf_path) {
        this.file_upload_ack_pdf_path = file_upload_ack_pdf_path;
    }

    public String getFile_upload_ack_pdf_name() {
        return file_upload_ack_pdf_name;
    }

    public void setFile_upload_ack_pdf_name(String file_upload_ack_pdf_name) {
        this.file_upload_ack_pdf_name = file_upload_ack_pdf_name;
    }

    public String getForm16_file_status() {
        return form16_file_status;
    }

    public void setForm16_file_status(String form16_file_status) {
        this.form16_file_status = form16_file_status;
    }

    public Date getForm16_file_date() {
        return form16_file_date;
    }

    public void setForm16_file_date(Date form16_file_date) {
        this.form16_file_date = form16_file_date;
    }

    public String getForm16_deductee_rec() {
        return form16_deductee_rec;
    }

    public void setForm16_deductee_rec(String form16_deductee_rec) {
        this.form16_deductee_rec = form16_deductee_rec;
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

    public String getFvu_file_generated_flag() {
        return fvu_file_generated_flag;
    }

    public void setFvu_file_generated_flag(String fvu_file_generated_flag) {
        this.fvu_file_generated_flag = fvu_file_generated_flag;
    }

}
