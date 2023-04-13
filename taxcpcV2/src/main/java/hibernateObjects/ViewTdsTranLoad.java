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
import org.hibernate.annotations.Formula;

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "tds_tran_load")
public class ViewTdsTranLoad implements java.io.Serializable {

    @Id
    @Column(name = "rowid_seq", nullable = false)
    private Long rowid_seq;
    @Column(name = "entity_code", length = 4000, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 4000, nullable = true)
    private String client_code;
    @Column(name = "client_name", length = 4000, nullable = true)
    private String client_name;
    @Column(name = "client_type_code", length = 5, nullable = true)
    private String client_type_code;
    @Column(name = "acc_year", length = 4000, nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", length = 4000, nullable = true)
    private String assesment_acc_year;
    @Column(name = "quarter_no", length = 4000, nullable = true)
    private String quarter_no;
    @Column(name = "month", length = 4000, nullable = true)
    private String month;
    @Column(name = "from_date", length = 4000, nullable = true)
    private String from_date;
    @Column(name = "to_date", length = 4000, nullable = true)
    private String to_date;
    @Column(name = "data_entry_mode", length = 4000, nullable = true)
    private String data_entry_mode;
    @Column(name = "file_doc_code", length = 4000, nullable = true)
    private String file_doc_code;
    @Column(name = "upload_template_code", length = 4000, nullable = true)
    private String upload_template_code;
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
    @Column(name = "pan_4_char_c_nc", length = 1, nullable = true)
    private String pan_4_char_c_nc;
    @Column(name = "dob", length = 4000, nullable = true)
    private String dob;
    @Column(name = "tds_type_code", length = 4000, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_section", length = 50, nullable = true)
    private String tds_section;
    @Column(name = "govt_tds_code", length = 10, nullable = true)
    private String govt_tds_code;
    @Column(name = "tran_ref_no", length = 4000, nullable = true)
    private String tran_ref_no;
    @Column(name = "tran_ref_date", length = 4000, nullable = true)
    private String tran_ref_date;
    @Column(name = "tran_amt", length = 4000, nullable = true)
    private Double tran_amt;
    @Column(name = "tds_deduct_date", length = 4000, nullable = true)
    private String tds_deduct_date;
    @Column(name = "tds_deduct_reason", length = 4000, nullable = true)
    private String tds_deduct_reason;
    @Column(name = "tds_deduct_reason_name", length = 25, nullable = true)
    private String tds_deduct_reason_name;
    @Column(name = "tds_rate", length = 4000, nullable = true)
    private Double tds_rate;
    @Column(name = "cess_rate", length = 4000, nullable = true)
    private Double cess_rate;
    @Column(name = "surcharge_rate", length = 4000, nullable = true)
    private Double surcharge_rate;
    @Column(name = "tds_base_amt", length = 4000, nullable = true)
    private Double tds_base_amt;
    @Formula(value="to_number(tds_amt)")
    @Column(name = "tds_amt", length = 4000, nullable = true)
    private Double tds_amt;
    @Column(name = "cess_amt", length = 4000, nullable = true)
    private Double cess_amt;
    @Column(name = "hecess_amt", length = 4000, nullable = true)
    private Double hecess_amt;
    @Column(name = "surcharge_amt", length = 4000, nullable = true)
    private Double surcharge_amt;
    @Column(name = "total_tds_amt", nullable = true)
    private Double total_tds_amt;
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
    @Column(name = "file_seqno", nullable = true)
    private String file_seqno;
    @Column(name = "user_code", length = 4000, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", length = 4000, nullable = true)
    private String lastupdate;
    @Column(name = "certificate_no", length = 50, nullable = true)
    private String certificate_no;
    @Column(name = "flag", length = 4000, nullable = true)
    private String flag;
    @Column(name = "itax_rate", length = 4000, nullable = true)
    private Double itax_rate;
    @Column(name = "tds_challan_rowid_seq", length = 4000, nullable = true)
    private String tds_challan_rowid_seq;
    @Column(name = "tds_challan_tds_amt", length = 4000, nullable = true)
    private Double tds_challan_tds_amt;
    @Column(name = "deductee_catg", length = 5, nullable = true)
    private String deductee_catg;
    @Column(name = "PARENT_CODE", length = 15, nullable = true)
    private String parent_code;
    @Column(name = "G_PARENT_CODE", length = 15, nullable = true)
    private String g_parent_code;
    @Column(name = "SG_PARENT_CODE", length = 15, nullable = true)
    private String sg_parent_code;
    @Column(name = "SSG_PARENT_CODE", length = 15, nullable = true)
    private String ssg_parent_code;
    @Column(name = "SSSG_PARENT_CODE", length = 15, nullable = true)
    private String sssg_parent_code;
    @Column(name = "BANK_BRANCH_CODE", length = 15, nullable = true)
    private String bank_branch_code;
    @Column(name = "account_no", length = 15, nullable = true)
    private String account_no;
    @Column(name = "identification_no", length = 50, nullable = true)
    private String identification_no;
    
    @Column(name = "tds_tran_insert_timestamp", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_tran_insert_timestamp;
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

    public ViewTdsTranLoad() {

    }

    public ViewTdsTranLoad(Long rowid_seq, String entity_code, String client_code, String client_name, String client_type_code, String acc_year, String pan_4_char_c_nc, String assesment_acc_year, String quarter_no, String month, String from_date, String to_date, String data_entry_mode, String file_doc_code, String upload_template_code, String client_panno, String client_tanno, String deductee_code, String deductee_name, String deductee_panno, String dob, String tds_type_code, String tds_code, String tds_section, String govt_tds_code, String tran_ref_no, String tran_ref_date, Double tran_amt, String tds_deduct_date, String tds_deduct_reason, String tds_deduct_reason_name, Double tds_rate, Double cess_rate, Double surcharge_rate, Double tds_base_amt, Double tds_amt, Double cess_amt, Double hecess_amt, Double surcharge_amt, Double total_tds_amt, String tds_error_status1, String tds_error_status2, String tds_error_status3, String tds_error_status4, String tds_error_status5, String tds_error_status6, String tds_error_status7, String deductee_ref_code1, String deductee_ref_code2, String file_seqno, String user_code, String lastupdate, String certificate_no, String flag, Double itax_rate, String tds_challan_rowid_seq, Double tds_challan_tds_amt, String deductee_catg, String parent_code, String g_parent_code, String sg_parent_code, String ssg_parent_code, String sssg_parent_code, String bank_branch_code, String account_no, String identification_no) {
        this.rowid_seq = rowid_seq;
        this.entity_code = entity_code;
        this.client_code = client_code;
        this.client_name = client_name;
        this.client_type_code = client_type_code;
        this.acc_year = acc_year;
        this.assesment_acc_year = assesment_acc_year;
        this.quarter_no = quarter_no;
        this.month = month;
        this.from_date = from_date;
        this.to_date = to_date;
        this.data_entry_mode = data_entry_mode;
        this.file_doc_code = file_doc_code;
        this.upload_template_code = upload_template_code;
        this.client_panno = client_panno;
        this.client_tanno = client_tanno;
        this.deductee_code = deductee_code;
        this.deductee_name = deductee_name;
        this.deductee_panno = deductee_panno;
        this.pan_4_char_c_nc = pan_4_char_c_nc;
        this.dob = dob;
        this.tds_type_code = tds_type_code;
        this.tds_code = tds_code;
        this.tds_section = tds_section;
        this.govt_tds_code = govt_tds_code;
        this.tran_ref_no = tran_ref_no;
        this.tran_ref_date = tran_ref_date;
        this.tran_amt = tran_amt;
        this.tds_deduct_date = tds_deduct_date;
        this.tds_deduct_reason = tds_deduct_reason;
        this.tds_deduct_reason_name = tds_deduct_reason_name;
        this.tds_rate = tds_rate;
        this.cess_rate = cess_rate;
        this.surcharge_rate = surcharge_rate;
        this.tds_base_amt = tds_base_amt;
        this.tds_amt = tds_amt;
        this.cess_amt = cess_amt;
        this.hecess_amt = hecess_amt;
        this.surcharge_amt = surcharge_amt;
        this.total_tds_amt = total_tds_amt;
        this.tds_error_status1 = tds_error_status1;
        this.tds_error_status2 = tds_error_status2;
        this.tds_error_status3 = tds_error_status3;
        this.tds_error_status4 = tds_error_status4;
        this.tds_error_status5 = tds_error_status5;
        this.tds_error_status6 = tds_error_status6;
        this.tds_error_status7 = tds_error_status7;
        this.deductee_ref_code1 = deductee_ref_code1;
        this.deductee_ref_code2 = deductee_ref_code2;
        this.file_seqno = file_seqno;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.certificate_no = certificate_no;
        this.flag = flag;
        this.itax_rate = itax_rate;
        this.tds_challan_rowid_seq = tds_challan_rowid_seq;
        this.tds_challan_tds_amt = tds_challan_tds_amt;
        this.deductee_catg = deductee_catg;
        this.parent_code = parent_code;
        this.g_parent_code = g_parent_code;
        this.sg_parent_code = sg_parent_code;
        this.ssg_parent_code = ssg_parent_code;
        this.sssg_parent_code = sssg_parent_code;
        this.bank_branch_code = bank_branch_code;
        this.account_no = account_no;
        this.identification_no = identification_no;
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

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_type_code() {
        return client_type_code;
    }

    public void setClient_type_code(String client_type_code) {
        this.client_type_code = client_type_code;
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

    public String getGovt_tds_code() {
        return govt_tds_code;
    }

    public void setGovt_tds_code(String govt_tds_code) {
        this.govt_tds_code = govt_tds_code;
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

    public Double getTran_amt() {
        return tran_amt;
    }

    public void setTran_amt(Double tran_amt) {
        this.tran_amt = tran_amt;
    }

    public String getTds_deduct_date() {
        return tds_deduct_date;
    }

    public void setTds_deduct_date(String tds_deduct_date) {
        this.tds_deduct_date = tds_deduct_date;
    }

    public String getTds_deduct_reason() {
        return tds_deduct_reason;
    }

    public void setTds_deduct_reason(String tds_deduct_reason) {
        this.tds_deduct_reason = tds_deduct_reason;
    }

    public Double getTds_rate() {
        return tds_rate;
    }

    public void setTds_rate(Double tds_rate) {
        this.tds_rate = tds_rate;
    }

    public Double getCess_rate() {
        return cess_rate;
    }

    public void setCess_rate(Double cess_rate) {
        this.cess_rate = cess_rate;
    }

    public Double getSurcharge_rate() {
        return surcharge_rate;
    }

    public void setSurcharge_rate(Double surcharge_rate) {
        this.surcharge_rate = surcharge_rate;
    }

    public Double getTds_base_amt() {
        return tds_base_amt;
    }

    public void setTds_base_amt(Double tds_base_amt) {
        this.tds_base_amt = tds_base_amt;
    }

    public Double getTds_amt() {
        return tds_amt;
    }

    public void setTds_amt(Double tds_amt) {
        this.tds_amt = tds_amt;
    }

    public Double getCess_amt() {
        return cess_amt;
    }

    public void setCess_amt(Double cess_amt) {
        this.cess_amt = cess_amt;
    }

    public Double getHecess_amt() {
        return hecess_amt;
    }

    public void setHecess_amt(Double hecess_amt) {
        this.hecess_amt = hecess_amt;
    }

    public Double getSurcharge_amt() {
        return surcharge_amt;
    }

    public void setSurcharge_amt(Double surcharge_amt) {
        this.surcharge_amt = surcharge_amt;
    }

    public Double getTotal_tds_amt() {
        return total_tds_amt;
    }

    public void setTotal_tds_amt(Double total_tds_amt) {
        this.total_tds_amt = total_tds_amt;
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

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
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

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTds_deduct_reason_name() {
        return tds_deduct_reason_name;
    }

    public void setTds_deduct_reason_name(String tds_deduct_reason_name) {
        this.tds_deduct_reason_name = tds_deduct_reason_name;
    }

    public Double getItax_rate() {
        return itax_rate;
    }

    public void setItax_rate(Double itax_rate) {
        this.itax_rate = itax_rate;
    }

    public String getTds_challan_rowid_seq() {
        return tds_challan_rowid_seq;
    }

    public void setTds_challan_rowid_seq(String tds_challan_rowid_seq) {
        this.tds_challan_rowid_seq = tds_challan_rowid_seq;
    }

    public Double getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(Double tds_challan_tds_amt) {
        this.tds_challan_tds_amt = tds_challan_tds_amt;
    }

    public String getPan_4_char_c_nc() {
        return pan_4_char_c_nc;
    }

    public void setPan_4_char_c_nc(String pan_4_char_c_nc) {
        this.pan_4_char_c_nc = pan_4_char_c_nc;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getG_parent_code() {
        return g_parent_code;
    }

    public void setG_parent_code(String g_parent_code) {
        this.g_parent_code = g_parent_code;
    }

    public String getSg_parent_code() {
        return sg_parent_code;
    }

    public void setSg_parent_code(String sg_parent_code) {
        this.sg_parent_code = sg_parent_code;
    }

    public String getSsg_parent_code() {
        return ssg_parent_code;
    }

    public void setSsg_parent_code(String ssg_parent_code) {
        this.ssg_parent_code = ssg_parent_code;
    }

    public String getSssg_parent_code() {
        return sssg_parent_code;
    }

    public void setSssg_parent_code(String sssg_parent_code) {
        this.sssg_parent_code = sssg_parent_code;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getIdentification_no() {
        return identification_no;
    }

    public void setIdentification_no(String identification_no) {
        this.identification_no = identification_no;
    }

    public Date getTds_tran_insert_timestamp() {
        return tds_tran_insert_timestamp;
    }

    public void setTds_tran_insert_timestamp(Date tds_tran_insert_timestamp) {
        this.tds_tran_insert_timestamp = tds_tran_insert_timestamp;
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

    public Long getPartybillamt() {
        return partybillamt;
    }

    public void setPartybillamt(Long partybillamt) {
        this.partybillamt = partybillamt;
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

    public String getTds_challan_bank_bsr_code() {
        return tds_challan_bank_bsr_code;
    }

    public void setTds_challan_bank_bsr_code(String tds_challan_bank_bsr_code) {
        this.tds_challan_bank_bsr_code = tds_challan_bank_bsr_code;
    }

    
    
  
}//end class
