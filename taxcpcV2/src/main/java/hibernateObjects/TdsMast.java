/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author ayushi.jain
 */
@Entity
@Table(name = "tds_mast")
public class TdsMast implements java.io.Serializable {

    @Column(name = "tds_code", length = 10, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "TDS_CODE_SEQ")})
    private String tds_code;
    @Column(name = "tds_name", length = 50, nullable = false)
    private String tds_name;
    @Column(name = "tds_type_code", length = 5, nullable = false)
    private String tds_type_code;
    @Column(name = "created_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_date;
    @Column(name = "closed_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closed_date;
    @Column(name = "remark", length = 250, nullable = true)
    private String remark;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "govt_tds_code", length = 10, nullable = true)
    private String govt_tds_code;
    @Column(name = "company_flag", nullable = true)
    private String company_flag;
    @Column(name = "deductee_catg_code_str", length = 10, nullable = true)
    private String deductee_catg_code_str;
    @Column(name = "tds_deduct_reason_flag_str", length = 50, nullable = true)
    private String tds_deduct_reason_flag_str;

    public TdsMast() {
        this.created_date = new Date();
        this.user_code = "SHASHANK";
    }

    public TdsMast(String tds_code, String tds_name, String tds_type_code, Date created_date, Date closed_date, String remark, String user_code, Date lastupdate, String flag, String govt_tds_code, String company_flag, String deductee_catg_code_str, String tds_deduct_reason_flag_str) {
        this.tds_code = tds_code;
        this.tds_name = tds_name;
        this.tds_type_code = tds_type_code;
        this.created_date = created_date;
        this.closed_date = closed_date;
        this.remark = remark;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.govt_tds_code = govt_tds_code;
        this.company_flag = company_flag;
        this.deductee_catg_code_str = deductee_catg_code_str;
        this.tds_deduct_reason_flag_str = tds_deduct_reason_flag_str;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_name() {
        return tds_name;
    }

    public void setTds_name(String tds_name) {
        this.tds_name = tds_name;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
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

    public void setClosed_date(String closed_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(closed_date);
        } catch (Exception e) {
            d = null;
        }
        this.closed_date = d;
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

    public String getGovt_tds_code() {
        return govt_tds_code;
    }

    public void setGovt_tds_code(String govt_tds_code) {
        this.govt_tds_code = govt_tds_code;
    }

    public String getCompany_flag() {
        return company_flag;
    }

    public void setCompany_flag(String company_flag) {
        this.company_flag = company_flag;
    }

    public String getDeductee_catg_code_str() {
        return deductee_catg_code_str;
    }

    public void setDeductee_catg_code_str(String deductee_catg_code_str) {
        this.deductee_catg_code_str = deductee_catg_code_str;
    }

    public String getTds_deduct_reason_flag_str() {
        return tds_deduct_reason_flag_str;
    }

    public void setTds_deduct_reason_flag_str(String tds_deduct_reason_flag_str) {
        this.tds_deduct_reason_flag_str = tds_deduct_reason_flag_str;
    }

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
