/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "view_tds_challan_tran_corr")
public class ViewTdsChallanTranCor implements Serializable {

    @Id
    @Column(name = "rowid_seq", length = 22, nullable = false)
    private String rowid_seq;
    @Column(name = "inst_amt", length = 22, nullable = true)
    private String inst_amt;
    @Column(name = "challan_error_status1", length = 22, nullable = true)
    private String challan_error_status1;
    @Column(name = "challan_error_status2", length = 22, nullable = true)
    private String challan_error_status2;
    @Column(name = "challan_error_status3", length = 22, nullable = true)
    private String challan_error_status3;
    @Column(name = "challan_error_status4", length = 22, nullable = true)
    private String challan_error_status4;

    @Column(name = "total_allocated_count", length = 22, nullable = true)
    private String total_allocated_count;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "bank_address", length = 4000, nullable = true)
    private String bank_address;
    @Column(name = "bank_branch_code", length = 40, nullable = true)
    private String bank_branch_code;
    @Column(name = "bank_ifsc_code", length = 50, nullable = true)
    private String bank_ifsc_code;
    @Column(name = "bank_bsr_code", length = 15, nullable = true)
    private String bank_bsr_code;
    @Column(name = "inst_type", length = 2, nullable = true)
    private String inst_type;
    @Column(name = "inst_no", length = 20, nullable = true)
    private String inst_no;
    @Column(name = "inst_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inst_date;
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_section", length = 100, nullable = true)
    private String tds_section;
    @Column(name = "tds_challan_minor_head", length = 3, nullable = true)
    private String tds_challan_minor_head;
    @Column(name = "tds_challan_tds_amt", length = 22, nullable = true)
    private Long tds_challan_tds_amt;
    @Column(name = "tds_challan_cess_amt", length = 22, nullable = true)
    private String tds_challan_cess_amt;
    @Column(name = "tds_challan_surcharge_amt", length = 22, nullable = true)
    private String tds_challan_surcharge_amt;
    @Column(name = "tds_challan_int_amt", length = 22, nullable = true)
    private Long tds_challan_int_amt;
    @Column(name = "tds_challan_other_amt", length = 22, nullable = true)
    private Long tds_challan_other_amt;
    @Column(name = "total_tds_challan_amt", length = 22, nullable = true)
    private Long total_tds_challan_amt;
    @Column(name = "bank_name", length = 50, nullable = true)
    private String bank_name;
    @Column(name = "bank_branch", length = 50, nullable = true)
    private String bank_branch;
    @Column(name = "bank_swift_code", length = 40, nullable = true)
    private String bank_swift_code;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", length = 5, nullable = true)
    private String assesment_acc_year;
    @Column(name = "quarter_no", length = 22, nullable = true)
    private String quarter_no;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "from_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "data_entry_mode", length = 2, nullable = true)
    private String data_entry_mode;
    @Column(name = "file_doc_code", length = 15, nullable = true)
    private String file_doc_code;
    @Column(name = "upload_template_code", length = 30, nullable = true)
    private String upload_template_code;
    @Column(name = "tds_challan_no", length = 50, nullable = true)
    private String tds_challan_no;
    @Column(name = "tds_challan_date", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_challan_date;
    @Column(name = "ddo_slno", length = 22, nullable = true)
    private String ddo_slno;
    @Column(name = "parent_code", length = 15, nullable = true)
    private String parent_code;
    @Column(name = "g_parent_code", length = 15, nullable = true)
    private String g_parent_code;
    @Column(name = "sg_parent_code", length = 15, nullable = true)
    private String ssg_parent_code;
    @Column(name = "ssg_parent_code", length = 15, nullable = true)
    private String sssg_parent_code;
    @Column(name = "sssg_parent_code", length = 15, nullable = true)
    private String surcharge_allocated_amt;
    @Column(name = "surcharge_allocated_amt")
    private String cess_allocated_amt;
    @Column(name = "cess_allocated_amt")
    private String interest_allocated_amt;
    @Column(name = "interest_allocated_amt")
    private String other_allocated_amt;
    @Column(name = "other_allocated_amt")
    private String tds_allocated_count;
    @Column(name = "surcharge_allocated_count")
    private String surcharge_allocated_count;
    @Column(name = "cess_allocated_count")
    private String cess_allocated_count;
    @Column(name = "interest_allocated_count")
    private String interest_allocated_count;
    @Column(name = "tds_allocated_diff")
    private String tds_allocated_diff;
    @Column(name = "cess_allocated_diff")
    private String cess_allocated_diff;
    @Column(name = "surchage_allocated_diff")
    private String surchage_allocated_diff;
    @Column(name = "interest_allocated_diff")
    private String interest_allocated_diff;
    @Column(name = "other_allocated_diff")
    private String other_allocated_diff;
    @Column(name = "total_allocated_amt")
    private String total_allocated_amt;
    @Column(name = "total_allocated_diff")
    private String total_allocated_diff;
    @Column(name = "nil_challan_flag")
    private String nil_challan_flag;
    @Column(name = "book_entry_flag")
    private String book_entry_flag;
    @Column(name = "file_seqno")
    private String file_seqno;
    @Column(name = "tds_challan_tran_load_rowid")
    private String tds_challan_tran_load_rowid;
    @Column(name = "user_code")
    private String user_code;
    @Column(name = "csi_verify_flag")
    private String csi_verify_flag;

    private String tds_line_no;
    private String tds_challan_slno;
    private String challan_mismatch_flag;
    private Double pending_amt;
    private Double oltas_challan_intt_amt;
    private Double oltas_challan_other_amt;

    @Column(name = "update_flag")
    private int update_flag;

    public ViewTdsChallanTranCor() {

    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getInst_amt() {
        return inst_amt;
    }

    public void setInst_amt(String inst_amt) {
        this.inst_amt = inst_amt;
    }

    public String getChallan_error_status1() {
        return challan_error_status1;
    }

    public void setChallan_error_status1(String challan_error_status1) {
        this.challan_error_status1 = challan_error_status1;
    }

    public String getChallan_error_status2() {
        return challan_error_status2;
    }

    public void setChallan_error_status2(String challan_error_status2) {
        this.challan_error_status2 = challan_error_status2;
    }

    public String getChallan_error_status3() {
        return challan_error_status3;
    }

    public void setChallan_error_status3(String challan_error_status3) {
        this.challan_error_status3 = challan_error_status3;
    }

    public String getChallan_error_status4() {
        return challan_error_status4;
    }

    public void setChallan_error_status4(String challan_error_status4) {
        this.challan_error_status4 = challan_error_status4;
    }

//    public String getTotal_allocated_amount() {
//        return total_allocated_amount;
//    }
//
//    public void setTotal_allocated_amount(String total_allocated_amount) {
//        this.total_allocated_amount = total_allocated_amount;
//    }
    public String getTotal_allocated_count() {
        return total_allocated_count;
    }

    public void setTotal_allocated_count(String total_allocated_count) {
        this.total_allocated_count = total_allocated_count;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBank_address() {
        return bank_address;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getBank_ifsc_code() {
        return bank_ifsc_code;
    }

    public void setBank_ifsc_code(String bank_ifsc_code) {
        this.bank_ifsc_code = bank_ifsc_code;
    }

    public String getBank_bsr_code() {
        return bank_bsr_code;
    }

    public void setBank_bsr_code(String bank_bsr_code) {
        this.bank_bsr_code = bank_bsr_code;
    }

    public String getInst_type() {
        return inst_type;
    }

    public void setInst_type(String inst_type) {
        this.inst_type = inst_type;
    }

    public String getInst_no() {
        return inst_no;
    }

    public void setInst_no(String inst_no) {
        this.inst_no = inst_no;
    }

    public Date getInst_date() {
        return inst_date;
    }

    public void setInst_date(Date inst_date) {
        this.inst_date = inst_date;
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

    public String getTds_challan_minor_head() {
        return tds_challan_minor_head;
    }

    public void setTds_challan_minor_head(String tds_challan_minor_head) {
        this.tds_challan_minor_head = tds_challan_minor_head;
    }

    public Long getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(Long tds_challan_tds_amt) {
        this.tds_challan_tds_amt = tds_challan_tds_amt;
    }

    public String getTds_challan_cess_amt() {
        return tds_challan_cess_amt;
    }

    public void setTds_challan_cess_amt(String tds_challan_cess_amt) {
        this.tds_challan_cess_amt = tds_challan_cess_amt;
    }

    public String getTds_challan_surcharge_amt() {
        return tds_challan_surcharge_amt;
    }

    public void setTds_challan_surcharge_amt(String tds_challan_surcharge_amt) {
        this.tds_challan_surcharge_amt = tds_challan_surcharge_amt;
    }

    public Long getTds_challan_int_amt() {
        return tds_challan_int_amt;
    }

    public void setTds_challan_int_amt(Long tds_challan_int_amt) {
        this.tds_challan_int_amt = tds_challan_int_amt;
    }

    public Long getTds_challan_other_amt() {
        return tds_challan_other_amt;
    }

    public void setTds_challan_other_amt(Long tds_challan_other_amt) {
        this.tds_challan_other_amt = tds_challan_other_amt;
    }

    public Long getTotal_tds_challan_amt() {
        return total_tds_challan_amt;
    }

    public void setTotal_tds_challan_amt(Long total_tds_challan_amt) {
        this.total_tds_challan_amt = total_tds_challan_amt;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_swift_code() {
        return bank_swift_code;
    }

    public void setBank_swift_code(String bank_swift_code) {
        this.bank_swift_code = bank_swift_code;
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

    public String getTds_challan_no() {
        return tds_challan_no;
    }

    public void setTds_challan_no(String tds_challan_no) {
        this.tds_challan_no = tds_challan_no;
    }

    public Date getTds_challan_date() {
        return tds_challan_date;
    }

    public void setTds_challan_date(Date tds_challan_date) {
        this.tds_challan_date = tds_challan_date;
    }

    public String getDdo_slno() {
        return ddo_slno;
    }

    public void setDdo_slno(String ddo_slno) {
        this.ddo_slno = ddo_slno;
    }

//    public String getJustification_flag() {
//        return justification_flag;
//    }
//
//    public void setJustification_flag(String justification_flag) {
//        this.justification_flag = justification_flag;
//    }
    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getG_parent_code() {
        return g_parent_code;
    }

    public void setG_parent_code(String g_parent_code) {
        this.g_parent_code = g_parent_code;
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

    public String getSurcharge_allocated_amt() {
        return surcharge_allocated_amt;
    }

    public void setSurcharge_allocated_amt(String surcharge_allocated_amt) {
        this.surcharge_allocated_amt = surcharge_allocated_amt;
    }

    public String getCess_allocated_amt() {
        return cess_allocated_amt;
    }

    public void setCess_allocated_amt(String cess_allocated_amt) {
        this.cess_allocated_amt = cess_allocated_amt;
    }

    public String getInterest_allocated_amt() {
        return interest_allocated_amt;
    }

    public void setInterest_allocated_amt(String interest_allocated_amt) {
        this.interest_allocated_amt = interest_allocated_amt;
    }

    public String getOther_allocated_amt() {
        return other_allocated_amt;
    }

    public void setOther_allocated_amt(String other_allocated_amt) {
        this.other_allocated_amt = other_allocated_amt;
    }

    public String getTds_allocated_count() {
        return tds_allocated_count;
    }

    public void setTds_allocated_count(String tds_allocated_count) {
        this.tds_allocated_count = tds_allocated_count;
    }

    public String getSurcharge_allocated_count() {
        return surcharge_allocated_count;
    }

    public void setSurcharge_allocated_count(String surcharge_allocated_count) {
        this.surcharge_allocated_count = surcharge_allocated_count;
    }

    public String getCess_allocated_count() {
        return cess_allocated_count;
    }

    public void setCess_allocated_count(String cess_allocated_count) {
        this.cess_allocated_count = cess_allocated_count;
    }

    public String getInterest_allocated_count() {
        return interest_allocated_count;
    }

    public void setInterest_allocated_count(String interest_allocated_count) {
        this.interest_allocated_count = interest_allocated_count;
    }

    public String getTds_allocated_diff() {
        return tds_allocated_diff;
    }

    public void setTds_allocated_diff(String tds_allocated_diff) {
        this.tds_allocated_diff = tds_allocated_diff;
    }

    public String getCess_allocated_diff() {
        return cess_allocated_diff;
    }

    public void setCess_allocated_diff(String cess_allocated_diff) {
        this.cess_allocated_diff = cess_allocated_diff;
    }

    public String getSurchage_allocated_diff() {
        return surchage_allocated_diff;
    }

    public void setSurchage_allocated_diff(String surchage_allocated_diff) {
        this.surchage_allocated_diff = surchage_allocated_diff;
    }

    public String getInterest_allocated_diff() {
        return interest_allocated_diff;
    }

    public void setInterest_allocated_diff(String interest_allocated_diff) {
        this.interest_allocated_diff = interest_allocated_diff;
    }

    public String getOther_allocated_diff() {
        return other_allocated_diff;
    }

    public void setOther_allocated_diff(String other_allocated_diff) {
        this.other_allocated_diff = other_allocated_diff;
    }

    public String getTotal_allocated_amt() {
        return total_allocated_amt;
    }

    public void setTotal_allocated_amt(String total_allocated_amt) {
        this.total_allocated_amt = total_allocated_amt;
    }

    public String getTotal_allocated_diff() {
        return total_allocated_diff;
    }

    public void setTotal_allocated_diff(String total_allocated_diff) {
        this.total_allocated_diff = total_allocated_diff;
    }

    public String getNil_challan_flag() {
        return nil_challan_flag;
    }

    public void setNil_challan_flag(String nil_challan_flag) {
        this.nil_challan_flag = nil_challan_flag;
    }

    public String getBook_entry_flag() {
        return book_entry_flag;
    }

    public void setBook_entry_flag(String book_entry_flag) {
        this.book_entry_flag = book_entry_flag;
    }

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
    }

    public String getTds_challan_tran_load_rowid() {
        return tds_challan_tran_load_rowid;
    }

    public void setTds_challan_tran_load_rowid(String tds_challan_tran_load_rowid) {
        this.tds_challan_tran_load_rowid = tds_challan_tran_load_rowid;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getCsi_verify_flag() {
        return csi_verify_flag;
    }

    public void setCsi_verify_flag(String csi_verify_flag) {
        this.csi_verify_flag = csi_verify_flag;
    }

    public String getTds_line_no() {
        return tds_line_no;
    }

    public void setTds_line_no(String tds_line_no) {
        this.tds_line_no = tds_line_no;
    }

    public String getTds_challan_slno() {
        return tds_challan_slno;
    }

    public void setTds_challan_slno(String tds_challan_slno) {
        this.tds_challan_slno = tds_challan_slno;
    }

    public String getChallan_mismatch_flag() {
        return challan_mismatch_flag;
    }

    public void setChallan_mismatch_flag(String challan_mismatch_flag) {
        this.challan_mismatch_flag = challan_mismatch_flag;
    }

    public Double getPending_amt() {
        return pending_amt;
    }

    public void setPending_amt(Double pending_amt) {
        this.pending_amt = pending_amt;
    }

    public Double getOltas_challan_intt_amt() {
        return oltas_challan_intt_amt;
    }

    public void setOltas_challan_intt_amt(Double oltas_challan_intt_amt) {
        this.oltas_challan_intt_amt = oltas_challan_intt_amt;
    }

    public Double getOltas_challan_other_amt() {
        return oltas_challan_other_amt;
    }

    public void setOltas_challan_other_amt(Double oltas_challan_other_amt) {
        this.oltas_challan_other_amt = oltas_challan_other_amt;
    }

    public int getUpdate_flag() {
        return update_flag;
    }

    public void setUpdate_flag(int update_flag) {
        this.update_flag = update_flag;
    }

    @Override
    public String toString() {
        return new Util().printObjectAsString(this);
    }

}
