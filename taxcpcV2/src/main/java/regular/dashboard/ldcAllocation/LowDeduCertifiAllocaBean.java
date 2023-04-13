/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.ldcAllocation;

/**
 *
 * @author aniket.naik
 */
public class LowDeduCertifiAllocaBean {

    public String entity_code;
    public String client_code;
    public String parent_code;
    public String g_parent_code;
    public String sg_parent_code;
    public String ssg_parent_code;
    public String sssg_parent_code;

    public String bank_branch_code;
    public String acc_year;
    public String quarter_no;
    public String tds_type_code;
    public String deductee_panno;
    public String deductee_name;
    public String total_Records;
    public String certificate_no;
    public String tds_code;
    public String tds_section;
    public String tran_amt;
    public String mast_certificate_no;

    //New added fields for LD allocation status
    public String from_date;
    public String to_date;
    public String tds_rate;
    public String tds_limit_amt;
    public String bal_limit;
    public String certificate_valid_upto;
    public String page_no;
    public String certificate_date;
    public String deductee_code;
    public String amount_consumed;
    
    public String rowid_seq;
    public String challan_bank_branch_code;
    public String deductee_ref_code1;
    public String tran_ref_no;
    public String tran_ref_date;
    public String tds_amt;
    public String tds_deduct_reason;
   
    
    

    public String getAmount_consumed() {
        return amount_consumed;
    }

    public void setAmount_consumed(String amount_consumed) {
        this.amount_consumed = amount_consumed;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
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

    public String getTds_rate() {
        return tds_rate;
    }

    public void setTds_rate(String tds_rate) {
        this.tds_rate = tds_rate;
    }

    public String getTds_limit_amt() {
        return tds_limit_amt;
    }

    public void setTds_limit_amt(String tds_limit_amt) {
        this.tds_limit_amt = tds_limit_amt;
    }

    public String getBal_limit() {
        return bal_limit;
    }

    public void setBal_limit(String bal_limit) {
        this.bal_limit = bal_limit;
    }

    public String getCertificate_valid_upto() {
        return certificate_valid_upto;
    }

    public void setCertificate_valid_upto(String certificate_valid_upto) {
        this.certificate_valid_upto = certificate_valid_upto;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getCertificate_date() {
        return certificate_date;
    }

    public void setCertificate_date(String certificate_date) {
        this.certificate_date = certificate_date;
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

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getTotal_Records() {
        return total_Records;
    }

    public void setTotal_Records(String total_Records) {
        this.total_Records = total_Records;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
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

    public String getTran_amt() {
        return tran_amt;
    }

    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }

    public String getMast_certificate_no() {
        return mast_certificate_no;
    }

    public void setMast_certificate_no(String mast_certificate_no) {
        this.mast_certificate_no = mast_certificate_no;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getChallan_bank_branch_code() {
        return challan_bank_branch_code;
    }

    public void setChallan_bank_branch_code(String challan_bank_branch_code) {
        this.challan_bank_branch_code = challan_bank_branch_code;
    }

    public String getDeductee_ref_code1() {
        return deductee_ref_code1;
    }

    public void setDeductee_ref_code1(String deductee_ref_code1) {
        this.deductee_ref_code1 = deductee_ref_code1;
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

    public String getTds_amt() {
        return tds_amt;
    }

    public void setTds_amt(String tds_amt) {
        this.tds_amt = tds_amt;
    }

    public String getTds_deduct_reason() {
        return tds_deduct_reason;
    }

    public void setTds_deduct_reason(String tds_deduct_reason) {
        this.tds_deduct_reason = tds_deduct_reason;
    }

}
