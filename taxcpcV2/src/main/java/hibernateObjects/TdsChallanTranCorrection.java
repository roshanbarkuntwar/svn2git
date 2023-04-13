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
@Table(name = "TDS_CHALLAN_TRAN_CORR")
public class TdsChallanTranCorrection implements java.io.Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
//    @SequenceGenerator(name = "id_sequence", sequenceName = "TDS_CHALLAN_CORR_ROWID_SEQ", allocationSize = 1)
    @Column(name = "rowid_seq", length = 22, nullable = false)
    private Long rowid_seq;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", length = 5, nullable = true)
    private String assesment_acc_year;
    @Column(name = "bank_address", length = 4000, nullable = true)
    private String bank_address;
    @Column(name = "bank_branch", length = 50, nullable = true)
    private String bank_branch;
    @Column(name = "bank_branch_code", length = 40, nullable = true)
    private String bank_branch_code;
    @Column(name = "bank_bsr_code", length = 15, nullable = true)
    private String bank_bsr_code;
    @Column(name = "bank_ifsc_code", length = 50, nullable = true)
    private String bank_ifsc_code;
    @Column(name = "bank_name", length = 50, nullable = true)
    private String bank_name;
    @Column(name = "bank_swift_code", length = 40, nullable = true)
    private String bank_swift_code;
    @Column(name = "book_entry_flag", length = 1, nullable = true)
    private String book_entry_flag;
    @Column(name = "cess_allocated_amt", length = 22, nullable = true)
    private Long cess_allocated_amt;
    @Column(name = "challan_error_status1", length = 22, nullable = true)
    private Long challan_error_status1;
    @Column(name = "challan_error_status2", length = 22, nullable = true)
    private Long challan_error_status2;
    @Column(name = "challan_error_status3", length = 22, nullable = true)
    private Long challan_error_status3;
    @Column(name = "challan_error_status4", length = 22, nullable = true)
    private Long challan_error_status4;
    @Column(name = "challan_mismatch_flag", length = 1, nullable = true)
    private String challan_mismatch_flag;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "correction_flag", length = 1, nullable = true)
    private String correction_flag;
    @Column(name = "correction_type", length = 5, nullable = true)
    private String correction_type;
    @Column(name = "csi_line_no", length = 22, nullable = true)
    private Long csi_line_no;
    @Column(name = "csi_verify_flag", length = 1, nullable = true)
    private String csi_verify_flag;
    @Column(name = "csi_verify_timestamp", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date csi_verify_timestamp;
    @Column(name = "data_entry_mode", length = 2, nullable = true)
    private String data_entry_mode;
    @Column(name = "ddo_slno", length = 22, nullable = true)
    private Long ddo_slno;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "file_doc_code", length = 15, nullable = true)
    private String file_doc_code;
    @Column(name = "file_seqno", length = 22, nullable = true)
    private Long file_seqno;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "from_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "insert_flag", length = 1, nullable = true)
    private String insert_flag;
    @Column(name = "inst_amt", length = 22, nullable = true)
    private Long inst_amt;
    @Column(name = "inst_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inst_date;
    @Column(name = "inst_no", length = 20, nullable = true)
    private String inst_no;
    @Column(name = "inst_type", length = 2, nullable = true)
    private String inst_type;
    @Column(name = "justification_flag", length = 1, nullable = true)
    private String justification_flag;
    @Column(name = "lastupdate", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "nil_challan_flag", length = 1, nullable = true)
    private String nil_challan_flag;
    @Column(name = "pending_amt", length = 22, nullable = true)
    private Long pending_amt;
    @Column(name = "quarter_no", length = 22, nullable = true)
    private Long quarter_no;

    @Column(name = "surcharge_allocated_amt", length = 22, nullable = true)
    private Long surcharge_allocated_amt;
    @Column(name = "tds_allocated_amt", length = 22, nullable = true)
    private Long tds_allocated_amt;
    @Column(name = "tds_challan_cess_amt", length = 22, nullable = true)
    private Long tds_challan_cess_amt;
    @Column(name = "tds_challan_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_challan_date;
    @Column(name = "tds_challan_insert_timestamp", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tds_challan_insert_timestamp;
    @Column(name = "tds_challan_int_amt", length = 22, nullable = true)
    private Long tds_challan_int_amt;
    @Column(name = "tds_challan_minor_head", length = 3, nullable = true)
    private String tds_challan_minor_head;
    @Column(name = "tds_challan_no", length = 50, nullable = true)
    private String tds_challan_no;
    @Column(name = "tds_challan_other_amt", length = 22, nullable = true)
    private Long tds_challan_other_amt;
    @Column(name = "tds_challan_slno", length = 22, nullable = true)
    private Long tds_challan_slno;
    @Column(name = "tds_challan_surcharge_amt", length = 22, nullable = true)
    private Long tds_challan_surcharge_amt;
    @Column(name = "tds_challan_tds_amt", length = 22, nullable = true)
    private Long tds_challan_tds_amt;
    @Column(name = "tds_challan_trantype", length = 1, nullable = true)
    private String tds_challan_trantype;
    @Column(name = "tds_challan_tran_load_rowid", length = 22, nullable = true)
    private Long tds_challan_tran_load_rowid;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_line_no", length = 22, nullable = true)
    private Long tds_line_no;
    @Column(name = "tds_section", length = 100, nullable = true)
    private String tds_section;
    @Column(name = "tds_tran_iud_flag", length = 1, nullable = true)
    private String tds_tran_iud_flag;
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Column(name = "total_allocated_amount", length = 22, nullable = true)
    private Long total_allocated_amount;
    @Column(name = "total_allocated_count", length = 22, nullable = true)
    private Long total_allocated_count;
    @Column(name = "to_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "upload_template_code", length = 30, nullable = true)
    private String upload_template_code;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "oltas_challan_intt_amt", length = 8, nullable = false)
    private String oltas_challan_intt_amt;
    @Column(name = "oltas_challan_other_amt", length = 8, nullable = false)
    private String oltas_challan_other_amt;
    @Column(name = "last_total_tds_amt", nullable = true)
    private String last_total_tds_amt;
    @Column(name = "last_total_cess_amt", nullable = true)
    private String last_total_cess_amt;
    @Column(name = "last_total_surcharge_amt", nullable = true)
    private String last_total_surcharge_amt;
    @Column(name = "late_fee_amt", nullable = true)
    private String late_fee_amt;

    public TdsChallanTranCorrection() {
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
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

    public String getBank_address() {
        return bank_address;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getBank_bsr_code() {
        return bank_bsr_code;
    }

    public void setBank_bsr_code(String bank_bsr_code) {
        this.bank_bsr_code = bank_bsr_code;
    }

    public String getBank_ifsc_code() {
        return bank_ifsc_code;
    }

    public void setBank_ifsc_code(String bank_ifsc_code) {
        this.bank_ifsc_code = bank_ifsc_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_swift_code() {
        return bank_swift_code;
    }

    public void setBank_swift_code(String bank_swift_code) {
        this.bank_swift_code = bank_swift_code;
    }

    public String getBook_entry_flag() {
        return book_entry_flag;
    }

    public void setBook_entry_flag(String book_entry_flag) {
        this.book_entry_flag = book_entry_flag;
    }

    public Long getCess_allocated_amt() {
        return cess_allocated_amt;
    }

    public void setCess_allocated_amt(Long cess_allocated_amt) {
        this.cess_allocated_amt = cess_allocated_amt;
    }

    public Long getChallan_error_status1() {
        return challan_error_status1;
    }

    public void setChallan_error_status1(Long challan_error_status1) {
        this.challan_error_status1 = challan_error_status1;
    }

    public Long getChallan_error_status2() {
        return challan_error_status2;
    }

    public void setChallan_error_status2(Long challan_error_status2) {
        this.challan_error_status2 = challan_error_status2;
    }

    public Long getChallan_error_status3() {
        return challan_error_status3;
    }

    public void setChallan_error_status3(Long challan_error_status3) {
        this.challan_error_status3 = challan_error_status3;
    }

    public Long getChallan_error_status4() {
        return challan_error_status4;
    }

    public void setChallan_error_status4(Long challan_error_status4) {
        this.challan_error_status4 = challan_error_status4;
    }

    public String getChallan_mismatch_flag() {
        return challan_mismatch_flag;
    }

    public void setChallan_mismatch_flag(String challan_mismatch_flag) {
        this.challan_mismatch_flag = challan_mismatch_flag;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
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

    public Long getCsi_line_no() {
        return csi_line_no;
    }

    public void setCsi_line_no(Long csi_line_no) {
        this.csi_line_no = csi_line_no;
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

    public String getData_entry_mode() {
        return data_entry_mode;
    }

    public void setData_entry_mode(String data_entry_mode) {
        this.data_entry_mode = data_entry_mode;
    }

    public Long getDdo_slno() {
        return ddo_slno;
    }

    public void setDdo_slno(Long ddo_slno) {
        this.ddo_slno = ddo_slno;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getFile_doc_code() {
        return file_doc_code;
    }

    public void setFile_doc_code(String file_doc_code) {
        this.file_doc_code = file_doc_code;
    }

    public Long getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(Long file_seqno) {
        this.file_seqno = file_seqno;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getInsert_flag() {
        return insert_flag;
    }

    public void setInsert_flag(String insert_flag) {
        this.insert_flag = insert_flag;
    }

    public Long getInst_amt() {
        return inst_amt;
    }

    public void setInst_amt(Long inst_amt) {
        this.inst_amt = inst_amt;
    }

    public Date getInst_date() {
        return inst_date;
    }

    public void setInst_date(Date inst_date) {
        this.inst_date = inst_date;
    }

    public String getInst_no() {
        return inst_no;
    }

    public void setInst_no(String inst_no) {
        this.inst_no = inst_no;
    }

    public String getInst_type() {
        return inst_type;
    }

    public void setInst_type(String inst_type) {
        this.inst_type = inst_type;
    }

    public String getJustification_flag() {
        return justification_flag;
    }

    public void setJustification_flag(String justification_flag) {
        this.justification_flag = justification_flag;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNil_challan_flag() {
        return nil_challan_flag;
    }

    public void setNil_challan_flag(String nil_challan_flag) {
        this.nil_challan_flag = nil_challan_flag;
    }

    public Long getPending_amt() {
        return pending_amt;
    }

    public void setPending_amt(Long pending_amt) {
        this.pending_amt = pending_amt;
    }

    public Long getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(Long quarter_no) {
        this.quarter_no = quarter_no;
    }

    public Long getSurcharge_allocated_amt() {
        return surcharge_allocated_amt;
    }

    public void setSurcharge_allocated_amt(Long surcharge_allocated_amt) {
        this.surcharge_allocated_amt = surcharge_allocated_amt;
    }

    public Long getTds_allocated_amt() {
        return tds_allocated_amt;
    }

    public void setTds_allocated_amt(Long tds_allocated_amt) {
        this.tds_allocated_amt = tds_allocated_amt;
    }

    public Long getTds_challan_cess_amt() {
        return tds_challan_cess_amt;
    }

    public void setTds_challan_cess_amt(Long tds_challan_cess_amt) {
        this.tds_challan_cess_amt = tds_challan_cess_amt;
    }

    public Date getTds_challan_date() {
        return tds_challan_date;
    }

    public void setTds_challan_date(Date tds_challan_date) {
        this.tds_challan_date = tds_challan_date;
    }

    public Date getTds_challan_insert_timestamp() {
        return tds_challan_insert_timestamp;
    }

    public void setTds_challan_insert_timestamp(Date tds_challan_insert_timestamp) {
        this.tds_challan_insert_timestamp = tds_challan_insert_timestamp;
    }

    public Long getTds_challan_int_amt() {
        return tds_challan_int_amt;
    }

    public void setTds_challan_int_amt(Long tds_challan_int_amt) {
        this.tds_challan_int_amt = tds_challan_int_amt;
    }

    public String getTds_challan_minor_head() {
        return tds_challan_minor_head;
    }

    public void setTds_challan_minor_head(String tds_challan_minor_head) {
        this.tds_challan_minor_head = tds_challan_minor_head;
    }

    public String getTds_challan_no() {
        return tds_challan_no;
    }

    public void setTds_challan_no(String tds_challan_no) {
        this.tds_challan_no = tds_challan_no;
    }

    public Long getTds_challan_other_amt() {
        return tds_challan_other_amt;
    }

    public void setTds_challan_other_amt(Long tds_challan_other_amt) {
        this.tds_challan_other_amt = tds_challan_other_amt;
    }

    public Long getTds_challan_slno() {
        return tds_challan_slno;
    }

    public void setTds_challan_slno(Long tds_challan_slno) {
        this.tds_challan_slno = tds_challan_slno;
    }

    public Long getTds_challan_surcharge_amt() {
        return tds_challan_surcharge_amt;
    }

    public void setTds_challan_surcharge_amt(Long tds_challan_surcharge_amt) {
        this.tds_challan_surcharge_amt = tds_challan_surcharge_amt;
    }

    public Long getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(Long tds_challan_tds_amt) {
        this.tds_challan_tds_amt = tds_challan_tds_amt;
    }

    public String getTds_challan_trantype() {
        return tds_challan_trantype;
    }

    public void setTds_challan_trantype(String tds_challan_trantype) {
        this.tds_challan_trantype = tds_challan_trantype;
    }

    public Long getTds_challan_tran_load_rowid() {
        return tds_challan_tran_load_rowid;
    }

    public void setTds_challan_tran_load_rowid(Long tds_challan_tran_load_rowid) {
        this.tds_challan_tran_load_rowid = tds_challan_tran_load_rowid;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public Long getTds_line_no() {
        return tds_line_no;
    }

    public void setTds_line_no(Long tds_line_no) {
        this.tds_line_no = tds_line_no;
    }

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public String getTds_tran_iud_flag() {
        return tds_tran_iud_flag;
    }

    public void setTds_tran_iud_flag(String tds_tran_iud_flag) {
        this.tds_tran_iud_flag = tds_tran_iud_flag;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public Long getTotal_allocated_amount() {
        return total_allocated_amount;
    }

    public void setTotal_allocated_amount(Long total_allocated_amount) {
        this.total_allocated_amount = total_allocated_amount;
    }

    public Long getTotal_allocated_count() {
        return total_allocated_count;
    }

    public void setTotal_allocated_count(Long total_allocated_count) {
        this.total_allocated_count = total_allocated_count;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getUpload_template_code() {
        return upload_template_code;
    }

    public void setUpload_template_code(String upload_template_code) {
        this.upload_template_code = upload_template_code;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getOltas_challan_intt_amt() {
        return oltas_challan_intt_amt;
    }

    public void setOltas_challan_intt_amt(String oltas_challan_intt_amt) {
        this.oltas_challan_intt_amt = oltas_challan_intt_amt;
    }

    public String getOltas_challan_other_amt() {
        return oltas_challan_other_amt;
    }

    public void setOltas_challan_other_amt(String oltas_challan_other_amt) {
        this.oltas_challan_other_amt = oltas_challan_other_amt;
    }

    public String getLast_total_tds_amt() {
        return last_total_tds_amt;
    }

    public void setLast_total_tds_amt(String last_total_tds_amt) {
        this.last_total_tds_amt = last_total_tds_amt;
    }

    public String getLast_total_cess_amt() {
        return last_total_cess_amt;
    }

    public void setLast_total_cess_amt(String last_total_cess_amt) {
        this.last_total_cess_amt = last_total_cess_amt;
    }

    public String getLast_total_surcharge_amt() {
        return last_total_surcharge_amt;
    }

    public void setLast_total_surcharge_amt(String last_total_surcharge_amt) {
        this.last_total_surcharge_amt = last_total_surcharge_amt;
    }

    public String getLate_fee_amt() {
        return late_fee_amt;
    }

    public void setLate_fee_amt(String late_fee_amt) {
        this.late_fee_amt = late_fee_amt;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
