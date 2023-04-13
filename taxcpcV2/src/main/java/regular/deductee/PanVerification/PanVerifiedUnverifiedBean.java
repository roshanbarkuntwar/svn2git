/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

/**
 *
 * @author kapil.gupta
 */
public class PanVerifiedUnverifiedBean {

    private String panno;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String pan_allot_date;
    private String pan_aadhar_status;
    private String ab206_status;
    private String n194_status;
    private String remark;
    private String verified_date;

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPan_allot_date() {
        return pan_allot_date;
    }

    public void setPan_allot_date(String pan_allot_date) {
        this.pan_allot_date = pan_allot_date;
    }

    public String getPan_aadhar_status() {
        return pan_aadhar_status;
    }

    public void setPan_aadhar_status(String pan_aadhar_status) {
        this.pan_aadhar_status = pan_aadhar_status;
    }

    public String getAb206_status() {
        return ab206_status;
    }

    public void setAb206_status(String ab206_status) {
        this.ab206_status = ab206_status;
    }

    public String getN194_status() {
        return n194_status;
    }

    public void setN194_status(String n194_status) {
        this.n194_status = n194_status;
    }

    public void setVerified_date(String verified_date) {
        this.verified_date = verified_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
