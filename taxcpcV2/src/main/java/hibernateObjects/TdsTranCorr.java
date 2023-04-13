/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "tds_tran_corr")
public class TdsTranCorr implements java.io.Serializable {

    @Column(name = "rowid_seq", nullable = false)
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
            = @Parameter(name = "sequence", value = "TDS_TRAN_LOAD_ROWID_SEQ"))
    @GeneratedValue(generator = "generator")
    @Id
    private Long rowid_seq;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", length = 5, nullable = true)
    private String assesment_acc_year;
    @Column(name = "quarter_no", nullable = true)
    private String quarter_no;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "from_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "data_entry_mode", length = 2, nullable = true)
    private String data_entry_mode;
    @Column(name = "file_doc_code", length = 15, nullable = true)
    private String file_doc_code;
    @Column(name = "upload_template_code", length = 30, nullable = true)
    private String upload_template_code;
    @Column(name = "client_panno", length = 10, nullable = true)
    private String client_panno;
    @Column(name = "client_tanno", length = 12, nullable = true)
    private String client_tanno;
    @Column(name = "deductee_code", length = 15, nullable = true)
    private String deductee_code;
    @Column(name = "deductee_name", length = 100, nullable = true)
    private String deductee_name;
    @Column(name = "deductee_panno", length = 10, nullable = true)
    private String deductee_panno;
    @Column(name = "dob", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_section", length = 100, nullable = true)
    private String tds_section;
    @Column(name = "tran_ref_no", length = 100, nullable = true)
    private String tran_ref_no;
    @Column(name = "tran_ref_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tran_ref_date;
    @Column(name = "tran_amt", nullable = true)
    private String tran_amt;
    @Column(name = "tds_deduct_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_deduct_date;
    @Column(name = "tds_rate", nullable = true)
    private String tds_rate;
    @Column(name = "cess_rate", nullable = true)
    private String cess_rate;
    @Column(name = "surcharge_rate", nullable = true)
    private String surcharge_rate;
    @Column(name = "tds_base_amt", nullable = true)
    private String tds_base_amt;
    @Column(name = "tds_amt", nullable = true)
    private String tds_amt;
    @Column(name = "cess_amt", nullable = true)
    private String cess_amt;
    @Column(name = "hecess_amt", nullable = true)
    private String hecess_amt;
    @Column(name = "tds_challan_rowid_seq", nullable = true)
    private String tds_challan_rowid_seq;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "tds_error_status1", nullable = true)
    private String tds_error_status1;
    @Column(name = "tds_error_status2", nullable = true)
    private String tds_error_status2;
    @Column(name = "tds_error_status3", nullable = true)
    private String tds_error_status3;
    @Column(name = "tds_error_status4", nullable = true)
    private String tds_error_status4;
    @Column(name = "tds_error_status5", nullable = true)
    private String tds_error_status5;
    @Column(name = "tds_error_status6", nullable = true)
    private String tds_error_status6;
    @Column(name = "tds_error_status7", nullable = true)
    private String tds_error_status7;
    @Column(name = "deductee_ref_code1", length = 30, nullable = true)
    private String deductee_ref_code1;
    @Column(name = "deductee_ref_code2", length = 30, nullable = true)
    private String deductee_ref_code2;
    @Column(name = "surcharge_amt", nullable = true)
    private String surcharge_amt;
    @Column(name = "file_seqno", nullable = true)
    private Long file_seqno;
    @Column(name = "tds_deduct_reason", length = 1, nullable = true)
    private String tds_deduct_reason;
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_tran_load_rowid_seq", nullable = true)
    private String tds_tran_load_rowid_seq;
    @Column(name = "certificate_no", length = 50, nullable = true)
    private String certificate_no;
    @Column(name = "emp_code", length = 15, nullable = true)
    private String emp_code;
    @Column(name = "itax_rate", nullable = true)
    private String itax_rate;
    @Column(name = "rate_type", length = 5, nullable = true)
    private String rate_type;
    @Column(name = "nature_of_remittance", length = 5, nullable = true)
    private String nature_of_remittance;
    @Column(name = "country_code", length = 5, nullable = true)
    private String country_code;
    @Column(name = "tds_line_no", nullable = true)
    private Long tds_line_no;
    @Column(name = "tds_challan_slno", nullable = true)
    private Long tds_challan_slno;
    @Column(name = "tds_challan_deductee_slno", nullable = true)
    private Long tds_challan_deductee_slno;
    @Column(name = "tds_trantype", length = 1, nullable = true)
    private String tds_trantype;
    @Column(name = "tds_tran_iud_flag", length = 1, nullable = true)
    private String tds_tran_iud_flag;
    @Column(name = "file_line_no", nullable = true)
    private Long file_line_no;
    @Column(name = "merged_pan_no_str", length = 4000, nullable = true)
    private String merged_pan_no_str;
    @Column(name = "deductee_panno_verified", length = 1, nullable = true)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 1, nullable = true)
    private String deductee_panno_valid;
    @Column(name = "justification_flag", length = 2, nullable = true)
    private String justification_flag;
    @Column(name = "correction_flag", length = 2, nullable = true)
    private String correction_flag;
    @Column(name = "correction_type", length = 2, nullable = true)
    private String correction_type;
    @Column(name = "panno_correction_flag", length = 2, nullable = true)
    private String panno_correction_flag;
    @Column(name = "total_tds_deposited", nullable = true)
    private String total_tds_deposited;
    @Column(name = "tran_purchase_amt", length = 2, nullable = true)
    private String tran_purchase_amt;
    @Column(name = "book_entry_flag", length = 2, nullable = true)
    private String book_entry_flag;
    @Column(name = "remittance_certificate_no", length = 50, nullable = true)
    private String remittance_certificate_no;
    @Column(name = "remittance_address", length = 150, nullable = true)
    private String remittance_address;
    @Column(name = "remittance_phoneno", length = 100, nullable = true)
    private String remittance_phoneno;
    @Column(name = "remittance_email_id", length = 125, nullable = true)
    private String remittance_email_id;

    public TdsTranCorr() {
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

    public String getAssesment_acc_year() {
        return assesment_acc_year;
    }

    public void setAssesment_acc_year(String assesment_acc_year) {
        this.assesment_acc_year = assesment_acc_year;
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

    public String getData_entry_mode() {
        return data_entry_mode;
    }

    public void setData_entry_mode(String data_entry_mode) {
        this.data_entry_mode = data_entry_mode;
    }

    public String getFile_doc_code() {
        return file_doc_code;
    }

    public void setFile_doc_code(String file_doc_code) {
        this.file_doc_code = file_doc_code;
    }

    public String getUpload_template_code() {
        return upload_template_code;
    }

    public void setUpload_template_code(String upload_template_code) {
        this.upload_template_code = upload_template_code;
    }

    public String getClient_panno() {
        return client_panno;
    }

    public void setClient_panno(String client_panno) {
        this.client_panno = client_panno;
    }

    public String getClient_tanno() {
        return client_tanno;
    }

    public void setClient_tanno(String client_tanno) {
        this.client_tanno = client_tanno;
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

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public String getTran_ref_no() {
        return tran_ref_no;
    }

    public void setTran_ref_no(String tran_ref_no) {
        this.tran_ref_no = tran_ref_no;
    }

    public Date getTran_ref_date() {
        return tran_ref_date;
    }

    public void setTran_ref_date(String tran_ref_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(tran_ref_date);
        } catch (Exception e) {
            d = null;
        }
        this.tran_ref_date = d;
    }

    public String getTran_amt() {
        return tran_amt;
    }

    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }

    public Date getTds_deduct_date() {
        return tds_deduct_date;
    }

    public void setTds_deduct_date(String tds_deduct_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(tds_deduct_date);
        } catch (Exception e) {
            d = null;
        }
        this.tds_deduct_date = d;
    }

    public String getTds_rate() {
        return tds_rate;
    }

    public void setTds_rate(String tds_rate) {
        this.tds_rate = tds_rate;
    }

    public String getCess_rate() {
        return cess_rate;
    }

    public void setCess_rate(String cess_rate) {
        this.cess_rate = cess_rate;
    }

    public String getSurcharge_rate() {
        return surcharge_rate;
    }

    public void setSurcharge_rate(String surcharge_rate) {
        this.surcharge_rate = surcharge_rate;
    }

    public String getTds_base_amt() {
        return tds_base_amt;
    }

    public void setTds_base_amt(String tds_base_amt) {
        this.tds_base_amt = tds_base_amt;
    }

    public String getTds_amt() {
        return tds_amt;
    }

    public void setTds_amt(String tds_amt) {
        this.tds_amt = tds_amt;
    }

    public String getCess_amt() {
        return cess_amt;
    }

    public void setCess_amt(String cess_amt) {
        this.cess_amt = cess_amt;
    }

    public String getHecess_amt() {
        return hecess_amt;
    }

    public void setHecess_amt(String hecess_amt) {
        this.hecess_amt = hecess_amt;
    }

    public String getTds_challan_rowid_seq() {
        return tds_challan_rowid_seq;
    }

    public void setTds_challan_rowid_seq(String tds_challan_rowid_seq) {
        this.tds_challan_rowid_seq = tds_challan_rowid_seq;
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

    public String getTds_error_status1() {
        return tds_error_status1;
    }

    public void setTds_error_status1(String tds_error_status1) {
        this.tds_error_status1 = tds_error_status1;
    }

    public String getTds_error_status2() {
        return tds_error_status2;
    }

    public void setTds_error_status2(String tds_error_status2) {
        this.tds_error_status2 = tds_error_status2;
    }

    public String getTds_error_status3() {
        return tds_error_status3;
    }

    public void setTds_error_status3(String tds_error_status3) {
        this.tds_error_status3 = tds_error_status3;
    }

    public String getTds_error_status4() {
        return tds_error_status4;
    }

    public void setTds_error_status4(String tds_error_status4) {
        this.tds_error_status4 = tds_error_status4;
    }

    public String getTds_error_status5() {
        return tds_error_status5;
    }

    public void setTds_error_status5(String tds_error_status5) {
        this.tds_error_status5 = tds_error_status5;
    }

    public String getTds_error_status6() {
        return tds_error_status6;
    }

    public void setTds_error_status6(String tds_error_status6) {
        this.tds_error_status6 = tds_error_status6;
    }

    public String getTds_error_status7() {
        return tds_error_status7;
    }

    public void setTds_error_status7(String tds_error_status7) {
        this.tds_error_status7 = tds_error_status7;
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

    public String getSurcharge_amt() {
        return surcharge_amt;
    }

    public void setSurcharge_amt(String surcharge_amt) {
        this.surcharge_amt = surcharge_amt;
    }

    public Long getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(Long file_seqno) {
        this.file_seqno = file_seqno;
    }

    public String getTds_deduct_reason() {
        return tds_deduct_reason;
    }

    public void setTds_deduct_reason(String tds_deduct_reason) {
        this.tds_deduct_reason = tds_deduct_reason;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getTds_tran_load_rowid_seq() {
        return tds_tran_load_rowid_seq;
    }

    public void setTds_tran_load_rowid_seq(String tds_tran_load_rowid_seq) {
        this.tds_tran_load_rowid_seq = tds_tran_load_rowid_seq;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getItax_rate() {
        return itax_rate;
    }

    public void setItax_rate(String itax_rate) {
        this.itax_rate = itax_rate;
    }

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public String getNature_of_remittance() {
        return nature_of_remittance;
    }

    public void setNature_of_remittance(String nature_of_remittance) {
        this.nature_of_remittance = nature_of_remittance;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Long getTds_line_no() {
        return tds_line_no;
    }

    public void setTds_line_no(Long tds_line_no) {
        this.tds_line_no = tds_line_no;
    }

    public Long getTds_challan_slno() {
        return tds_challan_slno;
    }

    public void setTds_challan_slno(Long tds_challan_slno) {
        this.tds_challan_slno = tds_challan_slno;
    }

    public Long getTds_challan_deductee_slno() {
        return tds_challan_deductee_slno;
    }

    public void setTds_challan_deductee_slno(Long tds_challan_deductee_slno) {
        this.tds_challan_deductee_slno = tds_challan_deductee_slno;
    }

    public String getTds_trantype() {
        return tds_trantype;
    }

    public void setTds_trantype(String tds_trantype) {
        this.tds_trantype = tds_trantype;
    }

    public String getTds_tran_iud_flag() {
        return tds_tran_iud_flag;
    }

    public void setTds_tran_iud_flag(String tds_tran_iud_flag) {
        this.tds_tran_iud_flag = tds_tran_iud_flag;
    }

    public Long getFile_line_no() {
        return file_line_no;
    }

    public void setFile_line_no(Long file_line_no) {
        this.file_line_no = file_line_no;
    }

    public String getMerged_pan_no_str() {
        return merged_pan_no_str;
    }

    public void setMerged_pan_no_str(String merged_pan_no_str) {
        this.merged_pan_no_str = merged_pan_no_str;
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

    public String getJustification_flag() {
        return justification_flag;
    }

    public void setJustification_flag(String justification_flag) {
        this.justification_flag = justification_flag;
    }

    public String getCorrection_flag() {
        return correction_flag;
    }

    public void setCorrection_flag(String correction_flag) {
        this.correction_flag = correction_flag;
    }

    public String getCorrection_type() {
        return correction_type;
    }

    public void setCorrection_type(String correction_type) {
        this.correction_type = correction_type;
    }

    public String getPanno_correction_flag() {
        return panno_correction_flag;
    }

    public void setPanno_correction_flag(String panno_correction_flag) {
        this.panno_correction_flag = panno_correction_flag;
    }

    public String getTotal_tds_deposited() {
        return total_tds_deposited;
    }

    public void setTotal_tds_deposited(String total_tds_deposited) {
        this.total_tds_deposited = total_tds_deposited;
    }

    public String getTran_purchase_amt() {
        return tran_purchase_amt;
    }

    public void setTran_purchase_amt(String tran_purchase_amt) {
        this.tran_purchase_amt = tran_purchase_amt;
    }

    public String getBook_entry_flag() {
        return book_entry_flag;
    }

    public void setBook_entry_flag(String book_entry_flag) {
        this.book_entry_flag = book_entry_flag;
    }

    public String getRemittance_certificate_no() {
        return remittance_certificate_no;
    }

    public void setRemittance_certificate_no(String remittance_certificate_no) {
        this.remittance_certificate_no = remittance_certificate_no;
    }

    public String getRemittance_address() {
        return remittance_address;
    }

    public void setRemittance_address(String remittance_address) {
        this.remittance_address = remittance_address;
    }

    public String getRemittance_phoneno() {
        return remittance_phoneno;
    }

    public void setRemittance_phoneno(String remittance_phoneno) {
        this.remittance_phoneno = remittance_phoneno;
    }

    public String getRemittance_email_id() {
        return remittance_email_id;
    }

    public void setRemittance_email_id(String remittance_email_id) {
        this.remittance_email_id = remittance_email_id;
    }

    @Override
    public String toString() {
        return new Util().printObjectAsString(this);
    }

}
