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
 * @author aniket.naik
 */
@Entity
@Table(name = "tds_challan_tran_load")
public class TdsChallanTranLoad implements java.io.Serializable {

    @Id
    @Column(name = "rowid_seq", nullable = false)
//    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
//            = @Parameter(name = "sequence", value = "temp_tds_challan_rowid_seq"))
//    @GeneratedValue(generator = "generator")//old sequencer
//    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
//            = @Parameter(name = "sequence", value = "tds_challan_rowid"))
//    @GeneratedValue(generator = "generator")
    private Long rowid_seq;
    @Column(name = "entity_code", length = 4000, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 4000, nullable = true)
    private String client_code;
    @Column(name = "acc_year", length = 4000, nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", length = 4000, nullable = true)
    private String assesment_acc_year;
    @Column(name = "quarter_no", nullable = true)
    private String quarter_no;
    @Column(name = "month", length = 4000, nullable = true)
    private String month;
    @Column(name = "from_date", length = 4000, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", length = 4000, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "data_entry_mode", length = 4000, nullable = true)
    private String data_entry_mode;
    @Column(name = "file_doc_code", length = 4000, nullable = true)
    private String file_doc_code;
    @Column(name = "upload_template_code", length = 4000, nullable = true)
    private String upload_template_code;
    @Column(name = "tds_challan_no", length = 4000, nullable = true)
    private String tds_challan_no;
    @Column(name = "tds_challan_date", length = 4000, nullable = true)
    private String tds_challan_date;
    @Column(name = "tds_section", length = 4000, nullable = true)
    private String tds_section;
    @Column(name = "tds_type_code", length = 4000, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_challan_tds_amt", length = 4000, nullable = true)
    private String tds_challan_tds_amt;
    @Column(name = "tds_challan_cess_amt", length = 4000, nullable = true)
    private String tds_challan_cess_amt;
    @Column(name = "tds_challan_surcharge_amt", length = 4000, nullable = true)
    private String tds_challan_surcharge_amt;
    @Column(name = "tds_challan_int_amt", length = 4000, nullable = true)
    private String tds_challan_int_amt;
    @Column(name = "bank_name", length = 4000, nullable = true)
    private String bank_name;
    @Column(name = "bank_branch", length = 4000, nullable = true)
    private String bank_branch;
    @Column(name = "bank_swift_code", length = 4000, nullable = true)
    private String bank_swift_code;
    @Column(name = "bank_address", length = 4000, nullable = true)
    private String bank_address;
    @Column(name = "bank_branch_code", length = 4000, nullable = true)
    private String bank_branch_code;
    @Column(name = "bank_ifsc_code", length = 4000, nullable = true)
    private String bank_ifsc_code;
    @Column(name = "bank_bsr_code", length = 4000, nullable = true)
    private String bank_bsr_code;
    @Column(name = "inst_type", length = 4000, nullable = true)
    private String inst_type;
    @Column(name = "inst_no", length = 4000, nullable = true)
    private String inst_no;
    @Column(name = "inst_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inst_date;
    @Column(name = "inst_amt", length = 4000, nullable = true)
    private String inst_amt;
    @Column(name = "user_code", length = 4000, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "challan_error_status1", nullable = true)
    private String challan_error_status1;
    @Column(name = "challan_error_status2", nullable = true)
    private String challan_error_status2;
    @Column(name = "challan_error_status3", nullable = true)
    private String challan_error_status3;
    @Column(name = "challan_error_status4", nullable = true)
    private String challan_error_status4;
    @Column(name = "tds_allocated_amt", nullable = true)
    private String tds_allocated_amt;
    @Column(name = "cess_allocated_amt", nullable = true)
    private String cess_allocated_amt;
    @Column(name = "surcharge_allocated_amt", nullable = true)
    private String surcharge_allocated_amt;
    @Column(name = "file_seqno", nullable = true)
    private String file_seqno;
    @Column(name = "tds_challan_insert_timestamp", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_challan_insert_timestamp;
    @Column(name = "tds_challan_other_amt", length = 4000, nullable = true)
    private String tds_challan_other_amt;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_challan_minor_head", length = 4000, nullable = true)
    private String tds_challan_minor_head;
    @Column(name = "ddo_slno", length = 4000, nullable = true)
    private String ddo_slno;
    @Column(name = "book_entry_flag", length = 4000, nullable = true)
    private String book_entry_flag;
    @Column(name = "nil_challan_flag", length = 4000, nullable = true)
    private String nil_challan_flag;
    @Column(name = "tds_challan_trantype", length = 1, nullable = true)
    private String tds_challan_trantype;
    @Column(name = "tds_tran_iud_flag", length = 1, nullable = true)
    private String tds_tran_iud_flag;
    @Column(name = "tds_line_no", nullable = true)
    private String tds_line_no;
    @Column(name = "tds_challan_slno", nullable = true)
    private String tds_challan_slno;
    @Column(name = "csi_line_no", nullable = true)
    private Long csi_line_no;

    @Column(name = "total_allocated_amount", length = 50, nullable = true)
    private Long total_allocated_amount;

    @Column(name = "csi_verify_flag", length = 1, nullable = true)
    private String csi_verify_flag;
    @Column(name = "csi_verify_timestamp", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date csi_verify_timestamp;
    @Column(name = "insert_flag", length = 1, nullable = true)
    private String insert_flag;

    public TdsChallanTranLoad() {
    }

    public TdsChallanTranLoad(Long rowid_seq, String entity_code, String client_code, String acc_year, String assesment_acc_year, String quarter_no, String month, Date from_date, Date to_date, String data_entry_mode, String file_doc_code, String upload_template_code, String tds_challan_no, String tds_challan_date, String tds_section, String tds_type_code, String tds_challan_tds_amt, String tds_challan_cess_amt, String tds_challan_surcharge_amt, String tds_challan_int_amt, String bank_name, String bank_branch, String bank_swift_code, String bank_address, String bank_branch_code, String bank_ifsc_code, String bank_bsr_code, String inst_type, String inst_no, Date inst_date, String inst_amt, String user_code, Date lastupdate, String flag, String challan_error_status1, String challan_error_status2, String challan_error_status3, String challan_error_status4, String tds_allocated_amt, String cess_allocated_amt, String surcharge_allocated_amt, String file_seqno, Date tds_challan_insert_timestamp, String tds_challan_other_amt, String tds_code, String tds_challan_minor_head, String ddo_slno, String book_entry_flag, String nil_challan_flag, String tds_challan_trantype, String tds_tran_iud_flag, String tds_line_no, String tds_challan_slno, Long csi_line_no, Long total_allocated_amount, String csi_verify_flag, Date csi_verify_timestamp, String insert_flag) {
        this.rowid_seq = rowid_seq;
        this.entity_code = entity_code;
        this.client_code = client_code;
        this.acc_year = acc_year;
        this.assesment_acc_year = assesment_acc_year;
        this.quarter_no = quarter_no;
        this.month = month;
        this.from_date = from_date;
        this.to_date = to_date;
        this.data_entry_mode = data_entry_mode;
        this.file_doc_code = file_doc_code;
        this.upload_template_code = upload_template_code;
        this.tds_challan_no = tds_challan_no;
        this.tds_challan_date = tds_challan_date;
        this.tds_section = tds_section;
        this.tds_type_code = tds_type_code;
        this.tds_challan_tds_amt = tds_challan_tds_amt;
        this.tds_challan_cess_amt = tds_challan_cess_amt;
        this.tds_challan_surcharge_amt = tds_challan_surcharge_amt;
        this.tds_challan_int_amt = tds_challan_int_amt;
        this.bank_name = bank_name;
        this.bank_branch = bank_branch;
        this.bank_swift_code = bank_swift_code;
        this.bank_address = bank_address;
        this.bank_branch_code = bank_branch_code;
        this.bank_ifsc_code = bank_ifsc_code;
        this.bank_bsr_code = bank_bsr_code;
        this.inst_type = inst_type;
        this.inst_no = inst_no;
        this.inst_date = inst_date;
        this.inst_amt = inst_amt;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.challan_error_status1 = challan_error_status1;
        this.challan_error_status2 = challan_error_status2;
        this.challan_error_status3 = challan_error_status3;
        this.challan_error_status4 = challan_error_status4;
        this.tds_allocated_amt = tds_allocated_amt;
        this.cess_allocated_amt = cess_allocated_amt;
        this.surcharge_allocated_amt = surcharge_allocated_amt;
        this.file_seqno = file_seqno;
        this.tds_challan_insert_timestamp = tds_challan_insert_timestamp;
        this.tds_challan_other_amt = tds_challan_other_amt;
        this.tds_code = tds_code;
        this.tds_challan_minor_head = tds_challan_minor_head;
        this.ddo_slno = ddo_slno;
        this.book_entry_flag = book_entry_flag;
        this.nil_challan_flag = nil_challan_flag;
        this.tds_challan_trantype = tds_challan_trantype;
        this.tds_tran_iud_flag = tds_tran_iud_flag;
        this.tds_line_no = tds_line_no;
        this.tds_challan_slno = tds_challan_slno;
        this.csi_line_no = csi_line_no;

        this.total_allocated_amount = total_allocated_amount;
        this.csi_verify_flag = csi_verify_flag;
        this.csi_verify_timestamp = csi_verify_timestamp;
        this.insert_flag = insert_flag;
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

    public String getTds_challan_no() {
        return tds_challan_no;
    }

    public void setTds_challan_no(String tds_challan_no) {
        this.tds_challan_no = tds_challan_no;
    }

    public String getTds_challan_date() {
        return tds_challan_date;
    }

    public void setTds_challan_date(String tds_challan_date) {
        this.tds_challan_date = tds_challan_date;
    }

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(String tds_challan_tds_amt) {
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

    public String getTds_challan_int_amt() {
        return tds_challan_int_amt;
    }

    public void setTds_challan_int_amt(String tds_challan_int_amt) {
        this.tds_challan_int_amt = tds_challan_int_amt;
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

    public String getInst_amt() {
        return inst_amt;
    }

    public void setInst_amt(String inst_amt) {
        this.inst_amt = inst_amt;
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

    public String getTds_allocated_amt() {
        return tds_allocated_amt;
    }

    public void setTds_allocated_amt(String tds_allocated_amt) {
        this.tds_allocated_amt = tds_allocated_amt;
    }

    public String getCess_allocated_amt() {
        return cess_allocated_amt;
    }

    public void setCess_allocated_amt(String cess_allocated_amt) {
        this.cess_allocated_amt = cess_allocated_amt;
    }

    public String getSurcharge_allocated_amt() {
        return surcharge_allocated_amt;
    }

    public void setSurcharge_allocated_amt(String surcharge_allocated_amt) {
        this.surcharge_allocated_amt = surcharge_allocated_amt;
    }

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
    }

    public Date getTds_challan_insert_timestamp() {
        return tds_challan_insert_timestamp;
    }

    public void setTds_challan_insert_timestamp(Date tds_challan_insert_timestamp) {
        this.tds_challan_insert_timestamp = tds_challan_insert_timestamp;
    }

    public String getTds_challan_other_amt() {
        return tds_challan_other_amt;
    }

    public void setTds_challan_other_amt(String tds_challan_other_amt) {
        this.tds_challan_other_amt = tds_challan_other_amt;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_challan_minor_head() {
        return tds_challan_minor_head;
    }

    public void setTds_challan_minor_head(String tds_challan_minor_head) {
        this.tds_challan_minor_head = tds_challan_minor_head;
    }

    public String getDdo_slno() {
        return ddo_slno;
    }

    public void setDdo_slno(String ddo_slno) {
        this.ddo_slno = ddo_slno;
    }

    public String getBook_entry_flag() {
        return book_entry_flag;
    }

    public void setBook_entry_flag(String book_entry_flag) {
        this.book_entry_flag = book_entry_flag;
    }

    public String getNil_challan_flag() {
        return nil_challan_flag;
    }

    public void setNil_challan_flag(String nil_challan_flag) {
        this.nil_challan_flag = nil_challan_flag;
    }

    public String getTds_challan_trantype() {
        return tds_challan_trantype;
    }

    public void setTds_challan_trantype(String tds_challan_trantype) {
        this.tds_challan_trantype = tds_challan_trantype;
    }

    public String getTds_tran_iud_flag() {
        return tds_tran_iud_flag;
    }

    public void setTds_tran_iud_flag(String tds_tran_iud_flag) {
        this.tds_tran_iud_flag = tds_tran_iud_flag;
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

    public Long getCsi_line_no() {
        return csi_line_no;
    }

    public void setCsi_line_no(Long csi_line_no) {
        this.csi_line_no = csi_line_no;
    }

    public Long getTotal_allocated_amount() {
        return total_allocated_amount;
    }

    public void setTotal_allocated_amount(Long total_allocated_amount) {
        this.total_allocated_amount = total_allocated_amount;
    }

    public String getCsi_verify_flag() {
        return csi_verify_flag;
    }

    public void setCsi_verify_flag(String csi_verify_flag) {
        this.csi_verify_flag = csi_verify_flag;
    }

    public Date getCsi_verify_timestamp() {
        return csi_verify_timestamp;
    }

    public void setCsi_verify_timestamp(Date csi_verify_timestamp) {
        this.csi_verify_timestamp = csi_verify_timestamp;
    }

    public String getInsert_flag() {
        return insert_flag;
    }

    public void setInsert_flag(String insert_flag) {
        this.insert_flag = insert_flag;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

    




 
    
  
    

}
