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
public class ViewTdsChallanTranLoad implements java.io.Serializable {

    @Id
    @Column(name = "rowid_seq", nullable = false)
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
//    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "from_date", length = 4000, nullable = true)
    private String from_date;
//    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "to_date", length = 4000, nullable = true)
    private String to_date;
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
    @Column(name = "ddo_slno", length = 4000, nullable = true)
    private String ddo_slno;
    @Column(name = "tds_type_code", length = 4000, nullable = true)
    private String tds_type_code;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "tds_section", length = 4000, nullable = true)
    private String tds_section;
    @Column(name = "tds_challan_minor_head", length = 4000, nullable = true)
    private String tds_challan_minor_head;
    @Column(name = "tds_challan_tds_amt", length = 4000, nullable = true)
    private Double tds_challan_tds_amt;
    @Column(name = "tds_challan_cess_amt", length = 4000, nullable = true)
    private Double tds_challan_cess_amt;
    @Column(name = "tds_challan_surcharge_amt", length = 4000, nullable = true)
    private Double tds_challan_surcharge_amt;
    @Column(name = "tds_challan_int_amt", length = 4000, nullable = true)
    private Double tds_challan_int_amt;
    @Column(name = "tds_challan_other_amt", length = 4000, nullable = true)
    private Double tds_challan_other_amt;
    @Column(name = "total_tds_challan_amt", nullable = true)
    private Double total_tds_challan_amt;
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
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "inst_date", nullable = true)
    private Date inst_date;
    @Column(name = "inst_amt", length = 4000, nullable = true)
    private Double inst_amt;
    @Column(name = "challan_error_status1", nullable = true)
    private String challan_error_status1;
    @Column(name = "challan_error_status2", nullable = true)
    private String challan_error_status2;
    @Column(name = "challan_error_status3", nullable = true)
    private String challan_error_status3;
    @Column(name = "challan_error_status4", nullable = true)
    private String challan_error_status4;
    @Column(name = "allocated_amount", nullable = true)
    private Double allocated_amount;
    @Column(name = "total_allocated_count", nullable = false)
    private Long total_allocated_count;
    @Column(name = "balance_to_allocate", nullable = false)
    private Double balance_to_allocate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "csi_verify_flag", length = 1, nullable = true)
    private String csi_verify_flag;
    @Column(name = "nil_challan_flag", length = 4000, nullable = true)
    private String nil_challan_flag;
    @Column(name = "book_entry_flag", length = 4000, nullable = true)
    private String book_entry_flag;
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

    public ViewTdsChallanTranLoad() {
    }//end constructor

    public ViewTdsChallanTranLoad(Long rowid_seq, String entity_code, String client_code, String acc_year, String assesment_acc_year, String quarter_no, String month, String from_date, String to_date, String data_entry_mode, String file_doc_code, String upload_template_code, String tds_challan_no, String tds_challan_date, String ddo_slno, String tds_type_code, String tds_code, String tds_section, String tds_challan_minor_head, Double tds_challan_tds_amt, Double tds_challan_cess_amt, Double tds_challan_surcharge_amt, Double tds_challan_int_amt, Double tds_challan_other_amt, Double total_tds_challan_amt, String bank_name, String bank_branch, String bank_swift_code, String bank_address, String bank_branch_code, String bank_ifsc_code, String bank_bsr_code, String inst_type, String inst_no, Date inst_date, Double inst_amt, String challan_error_status1, String challan_error_status2, String challan_error_status3, String challan_error_status4, Double allocated_amount, Long total_allocated_count, Double balance_to_allocate, String flag, Date lastupdate, String user_code, String csi_varify_flag, String parent_code, String g_parent_code, String sg_parent_code, String ssg_parent_code, String sssg_parent_code) {
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
        this.ddo_slno = ddo_slno;
        this.tds_type_code = tds_type_code;
        this.tds_code = tds_code;
        this.tds_section = tds_section;
        this.tds_challan_minor_head = tds_challan_minor_head;
        this.tds_challan_tds_amt = tds_challan_tds_amt;
        this.tds_challan_cess_amt = tds_challan_cess_amt;
        this.tds_challan_surcharge_amt = tds_challan_surcharge_amt;
        this.tds_challan_int_amt = tds_challan_int_amt;
        this.tds_challan_other_amt = tds_challan_other_amt;
        this.total_tds_challan_amt = total_tds_challan_amt;
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
        this.challan_error_status1 = challan_error_status1;
        this.challan_error_status2 = challan_error_status2;
        this.challan_error_status3 = challan_error_status3;
        this.challan_error_status4 = challan_error_status4;
        this.allocated_amount = allocated_amount;
        this.total_allocated_count = total_allocated_count;
        this.balance_to_allocate = balance_to_allocate;
        this.flag = flag;
        this.lastupdate = lastupdate;
        this.user_code = user_code;
        this.csi_verify_flag = csi_varify_flag;
        this.g_parent_code = g_parent_code;
        this.parent_code = parent_code;
        this.sg_parent_code = sg_parent_code;
        this.ssg_parent_code = ssg_parent_code;
        this.sssg_parent_code = sssg_parent_code;
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

    public String getDdo_slno() {
        return ddo_slno;
    }

    public void setDdo_slno(String ddo_slno) {
        this.ddo_slno = ddo_slno;
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

    public Double getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(Double tds_challan_tds_amt) {
        this.tds_challan_tds_amt = tds_challan_tds_amt;
    }

    public Double getTds_challan_cess_amt() {
        return tds_challan_cess_amt;
    }

    public void setTds_challan_cess_amt(Double tds_challan_cess_amt) {
        this.tds_challan_cess_amt = tds_challan_cess_amt;
    }

    public Double getTds_challan_surcharge_amt() {
        return tds_challan_surcharge_amt;
    }

    public void setTds_challan_surcharge_amt(Double tds_challan_surcharge_amt) {
        this.tds_challan_surcharge_amt = tds_challan_surcharge_amt;
    }

    public Double getTds_challan_int_amt() {
        return tds_challan_int_amt;
    }

    public void setTds_challan_int_amt(Double tds_challan_int_amt) {
        this.tds_challan_int_amt = tds_challan_int_amt;
    }

    public Double getTds_challan_other_amt() {
        return tds_challan_other_amt;
    }

    public void setTds_challan_other_amt(Double tds_challan_other_amt) {
        this.tds_challan_other_amt = tds_challan_other_amt;
    }

    public Double getTotal_tds_challan_amt() {
        return total_tds_challan_amt;
    }

    public void setTotal_tds_challan_amt(Double total_tds_challan_amt) {
        this.total_tds_challan_amt = total_tds_challan_amt;
    }

    public Double getInst_amt() {
        return inst_amt;
    }

    public void setInst_amt(Double inst_amt) {
        this.inst_amt = inst_amt;
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

    public Double getAllocated_amount() {
        return allocated_amount;
    }

    public void setAllocated_amount(Double allocated_amount) {
        this.allocated_amount = allocated_amount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getTotal_allocated_count() {
        return total_allocated_count;
    }

    public void setTotal_allocated_count(Long total_allocated_count) {
        this.total_allocated_count = total_allocated_count;
    }

    public Double getBalance_to_allocate() {
        return balance_to_allocate;
    }

    public void setBalance_to_allocate(Double balance_to_allocate) {
        this.balance_to_allocate = balance_to_allocate;
    }
 
 

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
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

    public void setCsi_varify_flag(String csi_verify_flag) {
        this.csi_verify_flag = csi_verify_flag;
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

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
