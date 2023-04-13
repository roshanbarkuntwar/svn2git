/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;

/**
 *
 * @author ayushi.jain
 */
public class TransactionFilterEntity {

    private String panno;
    private String deducteeRefNo;
    private String deducteeName;
    private String accountNo;
    private String fromDate;
    private String toDate;
    private String section;
    private String correctionStatus;
    private String tdsAmountOperat;
    private String tdsAmountFrom;
    private String tdsAmountTo;
    private Assessment asmt;
    private String rowIdSeq;

    //New added filters
    private String bankBranchCode;
    private String tdsDeductReason;
    private String challan_bank_branch_code;
    //New added filters
    private long totalRecord;
    private String entity_code;
    private String client_code;
    private String acc_year;
    private String quarter_no;
    private String tds_type_code;


    public String getChallan_bank_branch_code() {
        return challan_bank_branch_code;
    }

    public void setChallan_bank_branch_code(String challan_bank_branch_code) {
        this.challan_bank_branch_code = challan_bank_branch_code;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getTdsDeductReason() {
        return tdsDeductReason;
    }

    public void setTdsDeductReason(String tdsDeductReason) {
        this.tdsDeductReason = tdsDeductReason;
    }

    public Assessment getAsmt() {
        return asmt;
    }

    public void setAsmt(Assessment asmt) {
        this.asmt = asmt;
    }

    public String getTdsAmountOperat() {
        return tdsAmountOperat;
    }

    public void setTdsAmountOperat(String tdsAmountOperat) {
        this.tdsAmountOperat = tdsAmountOperat;
    }

    public String getTdsAmountFrom() {
        return tdsAmountFrom;
    }

    public void setTdsAmountFrom(String tdsAmountFrom) {
        this.tdsAmountFrom = tdsAmountFrom;
    }

    public String getTdsAmountTo() {
        return tdsAmountTo;
    }

    public void setTdsAmountTo(String tdsAmountTo) {
        this.tdsAmountTo = tdsAmountTo;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getDeducteeRefNo() {
        return deducteeRefNo;
    }

    public void setDeducteeRefNo(String deducteeRefNo) {
        this.deducteeRefNo = deducteeRefNo;
    }

    public String getDeducteeName() {
        return deducteeName;
    }

    public void setDeducteeName(String deducteeName) {
        this.deducteeName = deducteeName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCorrectionStatus() {
        return correctionStatus;
    }

    public void setCorrectionStatus(String correctionStatus) {
        this.correctionStatus = correctionStatus;
    }

    public String getRowIdSeq() {
        return rowIdSeq;
    }

    public void setRowIdSeq(String rowIdSeq) {
        this.rowIdSeq = rowIdSeq;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
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

   
    
}
