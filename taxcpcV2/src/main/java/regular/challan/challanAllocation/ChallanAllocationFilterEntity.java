/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

/**
 *
 * @author ayushi.jain
 */
public class ChallanAllocationFilterEntity {

    private String allocationFilter;
    private String pan_no;
    private String tds_section;
    private String tds_amt_betwn;
    private String tds_amt_from;
    private String tds_amt_to;
    private String deductee_name;
    private String from_date;
    private String to_date;
    private String pan_4th_char;
    private String deducteeLevel;

    private String other_filter;
    private String ParaRowidSeq;
    private String identifyFlag;
    private String amountBetweenFlag;
    private String challan_date;

    // New added filters
    private String correctionStatus;
    private String deducteeRefNo;
    private String accountNo;
    private String bankBranchCode;
    private String tdsDeductReason;

    public String getDeducteeRefNo() {
        return deducteeRefNo;
    }

    public void setDeducteeRefNo(String deducteeRefNo) {
        this.deducteeRefNo = deducteeRefNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getCorrectionStatus() {
        return correctionStatus;
    }

    public void setCorrectionStatus(String correctionStatus) {
        this.correctionStatus = correctionStatus;
    }

    public String getDeducteeLevel() {
        return deducteeLevel;
    }

    public void setDeducteeLevel(String deducteeLevel) {
        this.deducteeLevel = deducteeLevel;
    }

    public String getOther_filter() {
        return other_filter;
    }

    public void setOther_filter(String other_filter) {
        this.other_filter = other_filter;
    }

    public String getParaRowidSeq() {
        return ParaRowidSeq;
    }

    public void setParaRowidSeq(String ParaRowidSeq) {
        this.ParaRowidSeq = ParaRowidSeq;
    }

    public String getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(String identifyFlag) {
        this.identifyFlag = identifyFlag;
    }

    public String getAllocationFilter() {
        return allocationFilter;
    }

    public void setAllocationFilter(String allocationFilter) {
        this.allocationFilter = allocationFilter;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getTds_amt_betwn() {
        return tds_amt_betwn;
    }

    public void setTds_amt_betwn(String tds_amt_betwn) {
        this.tds_amt_betwn = tds_amt_betwn;
    }

    public String getAmountBetweenFlag() {
        return amountBetweenFlag;
    }

    public void setAmountBetweenFlag(String amountBetweenFlag) {
        this.amountBetweenFlag = amountBetweenFlag;
    }

    public String getTds_amt_to() {
        return tds_amt_to;
    }

    public void setTds_amt_to(String tds_amt_to) {
        this.tds_amt_to = tds_amt_to;
    }

    public String getTds_amt_from() {
        return tds_amt_from;
    }

    public void setTds_amt_from(String tds_amt_from) {
        this.tds_amt_from = tds_amt_from;
    }

    public String getChallan_date() {
        return challan_date;
    }

    public void setChallan_date(String challan_date) {
        this.challan_date = challan_date;
    }

    public String getPan_4th_char() {
        return pan_4th_char;
    }

    public void setPan_4th_char(String pan_4th_char) {
        this.pan_4th_char = pan_4th_char;
    }
}
