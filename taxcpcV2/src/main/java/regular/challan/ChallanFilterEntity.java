/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

/**
 *
 * @author aniket.naik
 */
public class ChallanFilterEntity {

    private String client_code;
    private String acc_year;
    private String quarter_no;
    private String tds_type_code;
    private String from_date;
    private String to_date;
    private String tds_section;
    private String other_filter;
    private String f_month;
    private String allocationStatus;
    private String challanStatus;
    private String challanLevel;
    private String deductee_name;
    private String deductee_panno;
    private String tdsAmountOperat;
    private String tdsAmt_From;
    private String tdsAmt_to;
    private String tds_challan_no;
    
    // New added filters
    private String bankBranchCode;

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

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public String getOther_filter() {
        return other_filter;
    }

    public void setOther_filter(String other_filter) {
        this.other_filter = other_filter;
    }

    public String getF_month() {
        return f_month;
    }

    public void setF_month(String f_month) {
        this.f_month = f_month;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public String getChallanStatus() {
        return challanStatus;
    }

    public void setChallanStatus(String challanStatus) {
        this.challanStatus = challanStatus;
    }

    public String getChallanLevel() {
        return challanLevel;
    }

    public void setChallanLevel(String challanLevel) {
        this.challanLevel = challanLevel;
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

    public String getTdsAmountOperat() {
        return tdsAmountOperat;
    }

    public void setTdsAmountOperat(String tdsAmountOperat) {
        this.tdsAmountOperat = tdsAmountOperat;
    }

    public String getTdsAmt_From() {
        return tdsAmt_From;
    }

    public void setTdsAmt_From(String tdsAmt_From) {
        this.tdsAmt_From = tdsAmt_From;
    }

    public String getTdsAmt_to() {
        return tdsAmt_to;
    }

    public void setTdsAmt_to(String tdsAmt_to) {
        this.tdsAmt_to = tdsAmt_to;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getTds_challan_no() {
        return tds_challan_no;
    }

    public void setTds_challan_no(String tds_challan_no) {
        this.tds_challan_no = tds_challan_no;
    }

}
