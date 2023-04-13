/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author akash.dev.singh
 */
@Entity
@Table(name = "pan_mast")
public class PanMast implements Serializable {

    @Id
    @Column(name = "panno", length = 10, nullable = false)
    private String panno;
    @Column(name = "first_name", length = 100, nullable = true)
    private String first_name;
    @Column(name = "middle_name", length = 100, nullable = true)
    private String middle_name;
    @Column(name = "last_name", length = 100, nullable = true)
    private String last_name;
    @Column(name = "dob", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Column(name = "pan_type", length = 2, nullable = true)
    private String pan_type;
    @Column(name = "juridiction", length = 100, nullable = true)
    private String juridiction;
    @Column(name = "pan_status", length = 1, nullable = true)
    private String pan_status;
    @Column(name = "created_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_date;
    @Column(name = "closed_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closed_date;
    @Column(name = "verified_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date verified_date;
    @Column(name = "verifiedby_flag", length = 1, nullable = true)
    private String verifiedby_flag;
    @Column(name = "remark", length = 250, nullable = true)
    private String remark;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "upload_batchno", nullable = true)
    private Long upload_batchno;

    public PanMast() {
        this.user_code = "SHASHANK";
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(String dob) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(dob);
        } catch (Exception e) {
            d = null;
        }
        this.dob = d;
    }

    public String getPan_type() {
        return pan_type;
    }

    public void setPan_type(String pan_type) {
        this.pan_type = pan_type;
    }

    public String getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(String juridiction) {
        this.juridiction = juridiction;
    }

    public String getPan_status() {
        return pan_status;
    }

    public void setPan_status(String pan_status) {
        this.pan_status = pan_status;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(Date closed_date) {
        this.closed_date = closed_date;
    }

    public Date getVerified_date() {
        return verified_date;
    }

    public void setVerified_date(String verified_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(verified_date);
        } catch (Exception e) {
            d = null;
        }
        this.verified_date = d;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getVerifiedby_flag() {
        return verifiedby_flag;
    }

    public void setVerifiedby_flag(String verifiedby_flag) {
        this.verifiedby_flag = verifiedby_flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getUpload_batchno() {
        return upload_batchno;
    }

    public void setUpload_batchno(Long upload_batchno) {
        this.upload_batchno = upload_batchno;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
