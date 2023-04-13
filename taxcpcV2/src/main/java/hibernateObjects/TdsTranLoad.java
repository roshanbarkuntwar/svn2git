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

/**
 *
 * @author ayushi.jain
 */ 
@Entity
@Table(name = "tds_tran_load")
public class TdsTranLoad implements java.io.Serializable {

 
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TDS_TRAN_LOAD_ROWID_SEQ")
//    @SequenceGenerator(name="TDS_TRAN_LOAD_ROWID_SEQ", sequenceName="TDS_TRAN_LOAD_ROWID_SEQ", allocationSize=1)
    @Id
    @Column(name = "rowid_seq", nullable = false)
    private Long rowid_seq;
    @Column(name = "file_seqno", nullable = true)
    private String file_seqno;
    @Column(name = "quarter_no", length = 4000, nullable = true)
    private String quarter_no;
    @Column(name = "month", length = 4000, nullable = true)
    private String month;
    @Column(name = "from_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "tds_tran_insert_timestamp", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_tran_insert_timestamp;
    @Column(name = "client_panno", length = 4000, nullable = true)
    private String client_panno;
    @Column(name = "client_tanno", length = 4000, nullable = true)
    private String client_tanno;
    @Column(name = "deductee_code", length = 4000, nullable = true)
    private String deductee_code;
    @Column(name = "deductee_name", length = 4000, nullable = true)
    private String deductee_name;
    @Column(name = "deductee_panno", length = 4000, nullable = true)
    private String deductee_panno;
    @Column(name = "dob", length = 4000, nullable = true)
    private String dob;
    @Column(name = "tds_type_code", length = 4000, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_section", length = 4000, nullable = true)
    private String tds_section;
    @Column(name = "tran_ref_no", length = 4000, nullable = true)
    private String tran_ref_no;
    @Column(name = "tran_ref_date", length = 4000, nullable = true)
    private String tran_ref_date;
    @Column(name = "tran_amt", length = 4000, nullable = true)
    private String tran_amt;
    @Column(name = "tds_deduct_date", length = 4000, nullable = true)
    private String tds_deduct_date;
    @Column(name = "tds_rate", length = 4000, nullable = true)
    private String tds_rate;
    @Column(name = "cess_rate", length = 4000, nullable = true)
    private String cess_rate;
    @Column(name = "surcharge_rate", length = 4000, nullable = true)
    private String surcharge_rate;
    @Column(name = "tds_base_amt", length = 4000, nullable = true)
    private String tds_base_amt;
    @Column(name = "tds_amt", length = 4000, nullable = true)
    private String tds_amt;
    @Column(name = "cess_amt", length = 4000, nullable = true)
    private String cess_amt;
    @Column(name = "hecess_amt", length = 4000, nullable = true)
    private String hecess_amt;
    @Column(name = "tds_challan_rowid_seq", length = 4000, nullable = true)
    private String tds_challan_rowid_seq;
    @Column(name = "user_code", length = 4000, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = true)
    private String lastupdate;
    @Column(name = "flag", length = 4000, nullable = true)
    private String flag;
    @Column(name = "tds_error_status1", length = 4000, nullable = true)
    private String tds_error_status1;
    @Column(name = "tds_error_status2", length = 4000, nullable = true)
    private String tds_error_status2;
    @Column(name = "tds_error_status3", length = 4000, nullable = true)
    private String tds_error_status3;
    @Column(name = "tds_error_status4", length = 4000, nullable = true)
    private String tds_error_status4;
    @Column(name = "tds_error_status5", length = 4000, nullable = true)
    private String tds_error_status5;
    @Column(name = "tds_error_status6", length = 4000, nullable = true)
    private String tds_error_status6;
    @Column(name = "tds_error_status7", length = 4000, nullable = true)
    private String tds_error_status7;
    @Column(name = "deductee_ref_code1", length = 4000, nullable = true)
    private String deductee_ref_code1;
    @Column(name = "deductee_ref_code2", length = 4000, nullable = true)
    private String deductee_ref_code2;
    @Column(name = "entity_code", length = 4000, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 4000, nullable = true)
    private String client_code;
    @Column(name = "acc_year", length = 4000, nullable = true)
    private String acc_year;

    @Column(name = "assesment_acc_year", length = 4000, nullable = true)
    private String assesment_acc_year;
    @Column(name = "surcharge_amt", length = 4000, nullable = true)
    private String surcharge_amt;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_deduct_reason", length = 4000, nullable = true)
    private String tds_deduct_reason;
    @Column(name = "data_entry_mode", length = 4000, nullable = true)
    private String data_entry_mode;
    @Column(name = "file_doc_code", length = 4000, nullable = true)
    private String file_doc_code;
    @Column(name = "upload_template_code", length = 4000, nullable = true)
    private String upload_template_code;
    @Column(name = "certificate_no", length = 50, nullable = true)
    private String certificate_no;
    @Column(name = "emp_code", length = 15, nullable = true)
    private String emp_code;
    @Column(name = "file_line_no", nullable = true)
    private Long file_line_no;
    @Column(name = "partybillamt", nullable = true)
    private Long partybillamt;
    @Column(name = "tds_trantype", length = 1, nullable = true)
    private String tds_trantype;
    @Column(name = "tds_tran_iud_flag", length = 1, nullable = true)
    private String tds_tran_iud_flag;
    @Column(name = "tds_line_no", nullable = true)
    private Long tds_line_no;
    @Column(name = "tds_challan_slno", nullable = true)
    private Long tds_challan_slno;
    @Column(name = "tds_challan_deductee_slno", nullable = true)
    private Long tds_challan_deductee_slno;
    @Column(name = "itax_rate", length = 4000, nullable = true)
    private String itax_rate;
    @Column(name = "rate_type", length = 5, nullable = true)
    private String rate_type;
    @Column(name = "nature_of_remittance", length = 5, nullable = true)
    private String nature_of_remittance;
    @Column(name = "country_code", length = 5, nullable = true)
    private String country_code;
    @Column(name = "insert_flag", length = 1, nullable = true)
    private String insert_flag;
    @Column(name = "tin_uin_no", length = 25, nullable = true)
    private String tin_uin_no;
    @Column(name = "account_no", length = 25, nullable = true)
    private String account_no;
    @Column(name = "deductee_address", length = 2000, nullable = true)
    private String deductee_address;
    @Column(name = "deductee_phoneno", length = 15, nullable = true)
    private String deductee_phoneno;
    @Column(name = "deductee_email_id", length = 15, nullable = true)
    private String deductee_email_id;
    @Column(name = "tds_tran_corr_rowid_seq", length = 15, nullable = true)
    private Long tds_tran_corr_rowid_seq;
    @Column(name = "tds_tran_corr_approvedby", length = 15, nullable = true)
    private String tds_tran_corr_approvedby;
    @Column(name = "tds_tran_corr_approveddate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_tran_corr_approveddate;
    @Column(name = "approvedby", length = 15, nullable = true)
    private String approvedby;
    @Column(name = "approveddate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveddate;
    @Column(name = "deductee_panno_verified", length = 1, nullable = true)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 1, nullable = true)
    private String deductee_panno_valid;
    
    @Column(name="tds_challan_bank_bsr_code", length = 2000, nullable = true)
    private String tds_challan_bank_bsr_code;
  
    @Column(name = "validation_client_code", length = 1, nullable = true) 
    private String validation_client_code;

    public String getTds_challan_bank_bsr_code() {
        return tds_challan_bank_bsr_code;
    }

    public void setTds_challan_bank_bsr_code(String tds_challan_bank_bsr_code) {
        this.tds_challan_bank_bsr_code = tds_challan_bank_bsr_code;
    }
    
    

    public TdsTranLoad() {
    }

    public TdsTranLoad(Long rowid_seq, String file_seqno, String quarter_no, String month, Date from_date, Date to_date, Date tds_tran_insert_timestamp, String client_panno, String client_tanno, String deductee_code, String deductee_name, String deductee_panno, String dob, String tds_type_code, String tds_section, String tran_ref_no, String tran_ref_date, String tran_amt, String tds_deduct_date, String tds_rate, String cess_rate, String surcharge_rate, String tds_base_amt, String tds_amt, String cess_amt, String hecess_amt, String tds_challan_rowid_seq, String user_code, String lastupdate, String flag, String tds_error_status1, String tds_error_status2, String tds_error_status3, String tds_error_status4, String tds_error_status5, String tds_error_status6, String tds_error_status7, String deductee_ref_code1, String deductee_ref_code2, String entity_code, String client_code, String acc_year, String assesment_acc_year, String surcharge_amt, String tds_code, String tds_deduct_reason, String data_entry_mode, String file_doc_code, String upload_template_code, String certificate_no, String emp_code, Long file_line_no, String tds_trantype, String tds_tran_iud_flag, Long tds_line_no, Long tds_challan_slno, Long tds_challan_deductee_slno, String itax_rate, String rate_type, String nature_of_remittance, String country_code, String insert_flag, String tin_uin_no, String account_no, String deductee_address, String deductee_phoneno, String deductee_email_id, Long tds_tran_corr_rowid_seq, String tds_tran_corr_approvedby, Date tds_tran_corr_approveddate, String approvedby, Date approveddate, String deductee_panno_verified, String deductee_panno_valid, String tds_challan_bank_bsr_code) {
        this.rowid_seq = rowid_seq;
        this.file_seqno = file_seqno;
        this.quarter_no = quarter_no;
        this.month = month;
        this.from_date = from_date;
        this.to_date = to_date;
        this.tds_tran_insert_timestamp = tds_tran_insert_timestamp;
        this.client_panno = client_panno;
        this.client_tanno = client_tanno;
        this.deductee_code = deductee_code;
        this.deductee_name = deductee_name;
        this.deductee_panno = deductee_panno;
        this.dob = dob;
        this.tds_type_code = tds_type_code;
        this.tds_section = tds_section;
        this.tran_ref_no = tran_ref_no;
        this.tran_ref_date = tran_ref_date;
        this.tran_amt = tran_amt;
        this.tds_deduct_date = tds_deduct_date;
        this.tds_rate = tds_rate;
        this.cess_rate = cess_rate;
        this.surcharge_rate = surcharge_rate;
        this.tds_base_amt = tds_base_amt;
        this.tds_amt = tds_amt;
        this.cess_amt = cess_amt;
        this.hecess_amt = hecess_amt;
        this.tds_challan_rowid_seq = tds_challan_rowid_seq;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.tds_error_status1 = tds_error_status1;
        this.tds_error_status2 = tds_error_status2;
        this.tds_error_status3 = tds_error_status3;
        this.tds_error_status4 = tds_error_status4;
        this.tds_error_status5 = tds_error_status5;
        this.tds_error_status6 = tds_error_status6;
        this.tds_error_status7 = tds_error_status7;
        this.deductee_ref_code1 = deductee_ref_code1;
        this.deductee_ref_code2 = deductee_ref_code2;
        this.entity_code = entity_code;
        this.client_code = client_code;
        this.acc_year = acc_year;
        this.assesment_acc_year = assesment_acc_year;
        this.surcharge_amt = surcharge_amt;
        this.tds_code = tds_code;
        this.tds_deduct_reason = tds_deduct_reason;
        this.data_entry_mode = data_entry_mode;
        this.file_doc_code = file_doc_code;
        this.upload_template_code = upload_template_code;
        this.certificate_no = certificate_no;
        this.emp_code = emp_code;
        this.file_line_no = file_line_no;
        this.tds_trantype = tds_trantype;
        this.tds_tran_iud_flag = tds_tran_iud_flag;
        this.tds_line_no = tds_line_no;
        this.tds_challan_slno = tds_challan_slno;
        this.tds_challan_deductee_slno = tds_challan_deductee_slno;
        this.itax_rate = itax_rate;
        this.rate_type = rate_type;
        this.nature_of_remittance = nature_of_remittance;
        this.country_code = country_code;
        this.insert_flag = insert_flag;
        this.tin_uin_no = tin_uin_no;
        this.account_no = account_no;
        this.deductee_address = deductee_address;
        this.deductee_phoneno = deductee_phoneno;
        this.deductee_email_id = deductee_email_id;
        this.tds_tran_corr_rowid_seq = tds_tran_corr_rowid_seq;
        this.tds_tran_corr_approvedby = tds_tran_corr_approvedby;
        this.tds_tran_corr_approveddate = tds_tran_corr_approveddate;
        this.approvedby = approvedby;
        this.approveddate = approveddate;
        this.deductee_panno_verified = deductee_panno_verified;
        this.deductee_panno_valid = deductee_panno_valid;
        this.tds_challan_bank_bsr_code = tds_challan_bank_bsr_code;
    }

    

    public Long getTds_tran_corr_rowid_seq() {
        return tds_tran_corr_rowid_seq;
    }

    public void setTds_tran_corr_rowid_seq(Long tds_tran_corr_rowid_seq) {
        this.tds_tran_corr_rowid_seq = tds_tran_corr_rowid_seq;
    }

    public String getTds_tran_corr_approvedby() {
        return tds_tran_corr_approvedby;
    }

    public void setTds_tran_corr_approvedby(String tds_tran_corr_approvedby) {
        this.tds_tran_corr_approvedby = tds_tran_corr_approvedby;
    }

    public Date getTds_tran_corr_approveddate() {
        return tds_tran_corr_approveddate;
    }

    public void setTds_tran_corr_approveddate(Date tds_tran_corr_approveddate) {
        this.tds_tran_corr_approveddate = tds_tran_corr_approveddate;
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

    public String getDeductee_address() {
        return deductee_address;
    }

    public void setDeductee_address(String deductee_address) {
        this.deductee_address = deductee_address;
    }

    public String getDeductee_phoneno() {
        return deductee_phoneno;
    }

    public void setDeductee_phoneno(String deductee_phoneno) {
        this.deductee_phoneno = deductee_phoneno;
    }

    public String getDeductee_email_id() {
        return deductee_email_id;
    }

    public void setDeductee_email_id(String deductee_email_id) {
        this.deductee_email_id = deductee_email_id;
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
        if (month != null) {
            this.month = month.toUpperCase();
        } else {
            this.month = month;
        }
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

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
    }

    public Date getTds_tran_insert_timestamp() {
        return tds_tran_insert_timestamp;
    }

    public void setTds_tran_insert_timestamp(Date tds_tran_insert_timestamp) {
        this.tds_tran_insert_timestamp = tds_tran_insert_timestamp;
    }

    public String getClient_panno() {
        return client_panno;
    }

    public void setClient_panno(String client_panno) {
        if (client_panno != null) {
            this.client_panno = client_panno.toUpperCase();
        } else {
            this.client_panno = client_panno;
        }
    }

    public String getClient_tanno() {
        return client_tanno;
    }

    public void setClient_tanno(String client_tanno) {
        if (client_tanno != null) {
            this.client_tanno = client_tanno.toUpperCase();
        } else {
            this.client_tanno = client_tanno;
        }
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
        if (deductee_name != null) {
            this.deductee_name = deductee_name.toUpperCase();
        } else {
            this.deductee_name = deductee_name;
        }
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        if (deductee_panno != null) {
            this.deductee_panno = deductee_panno.toUpperCase();
        } else {
            this.deductee_panno = deductee_panno;
        }
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
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

    public String getTran_ref_date() {
        return tran_ref_date;
    }

    public void setTran_ref_date(String tran_ref_date) {
        this.tran_ref_date = tran_ref_date;
    }

    public String getTran_amt() {
        return tran_amt;
    }

    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }

    public String getTds_deduct_date() {
        return tds_deduct_date;
    }

    public void setTds_deduct_date(String tds_deduct_date) {
        this.tds_deduct_date = tds_deduct_date;
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
        if (entity_code != null) {
            this.entity_code = entity_code.toUpperCase();
        } else {
            this.entity_code = entity_code;
        }
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

    public String getSurcharge_amt() {
        return surcharge_amt;
    }

    public void setSurcharge_amt(String surcharge_amt) {
        this.surcharge_amt = surcharge_amt;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_deduct_reason() {
        return tds_deduct_reason;
    }

    public void setTds_deduct_reason(String tds_deduct_reason) {
        if (tds_deduct_reason != null) {
            this.tds_deduct_reason = tds_deduct_reason.toUpperCase();
        } else {
            this.tds_deduct_reason = tds_deduct_reason;
        }
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

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        if (certificate_no != null) {
            this.certificate_no = certificate_no.toUpperCase();
        } else {
            this.certificate_no = certificate_no;
        }
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public Long getFile_line_no() {
        return file_line_no;
    }

    public void setFile_line_no(Long file_line_no) {
        this.file_line_no = file_line_no;
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

    public String getInsert_flag() {
        return insert_flag;
    }

    public void setInsert_flag(String insert_flag) {
        this.insert_flag = insert_flag;
    }

    public String getTin_uin_no() {
        return tin_uin_no;
    }

    public void setTin_uin_no(String tin_uin_no) {
        this.tin_uin_no = tin_uin_no;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public void setDeductee_panno_valid(String deductee_panno_valid) {
        this.deductee_panno_valid = deductee_panno_valid;
    }

    public Long getPartybillamt() {
        return partybillamt;
    }

    public void setPartybillamt(Long partybillamt) {
        this.partybillamt = partybillamt;
    }

    public String getValidation_client_code() {
        return validation_client_code;
    }

    public void setValidation_client_code(String validation_client_code) {
        this.validation_client_code = validation_client_code;
    }
    
    

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
