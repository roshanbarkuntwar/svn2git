/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "tds_rate_mast")
public class TdsRateMast implements java.io.Serializable {

    @Id
    @Column(name = "from_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Id
    @Column(name = "tds_code", length = 10, nullable = false)
    private String tds_code;
    @Column(name = "tds_rate", length = 7, precision = 3, nullable = false)
    private String tds_rate;
    @Column(name = "surcharge_rate", length = 7, precision = 3, nullable = true)
    private String surcharge_rate;
    @Column(name = "cess_rate", length = 7, precision = 3, nullable = true)
    private String cess_rate;
    @Column(name = "tds_base_amt", length = 14, precision = 2, nullable = true)
    private String tds_base_amt;
    @Column(name = "surcharge_base_amt", length = 14, precision = 2, nullable = true)
    private String surcharge_base_amt;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "deductee_catg", length = 5, nullable = true)
    private String deductee_catg;
    @Id
    @Column(name = "country_code", length = 5, nullable = true)
    private String country_code;
    @Column(name = "hecess_rate", length = 7, precision = 3, nullable = true)
    private String hecess_rate;
    @Id
    @Column(name = "deductee_catg_str", length = 4000, nullable = true)
    private String deductee_catg_str;
    @Id
    @Column(name = "client_country_type", length = 1, nullable = true)
    private String client_country_type;

    public TdsRateMast() {
        this.user_code = "SHASHANK";
    }

    public TdsRateMast(Date from_date, Date to_date, String tds_code, String tds_rate, String surcharge_rate, String cess_rate, String tds_base_amt, String surcharge_base_amt, String user_code, Date lastupdate, String flag, String deductee_catg, String country_code, String hecess_rate, String deductee_catg_str, String client_country_type) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.tds_code = tds_code;
        this.tds_rate = tds_rate;
        this.surcharge_rate = surcharge_rate;
        this.cess_rate = cess_rate;
        this.tds_base_amt = tds_base_amt;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.deductee_catg = deductee_catg;
        this.country_code = country_code;
        this.hecess_rate = hecess_rate;
        this.surcharge_base_amt = surcharge_base_amt;
        this.deductee_catg_str = deductee_catg_str;
        this.client_country_type = client_country_type;
//        this.rowid = rowid;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(from_date);
        } catch (Exception e) {
            d = null;
        }
        this.from_date = d;
    }

    public Date getTo_date() {
//        Date d;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            d = sdf.parse(to_date);
//            String format = sdf.format(to_date);
//        } catch (Exception e) {
//            d = null;
//        }
        return to_date;
    }

    public void setTo_date(String to_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(to_date);
        } catch (Exception e) {
            d = null;
        }
        this.to_date = d;
//        this.to_date = to_date;
    }

//    public String getRowid() {
//        return rowid;
//    }
//
//    public void setRowid(String rowid) {
//        this.rowid = rowid;
//    }

    public String getDeductee_catg_str() {
        return deductee_catg_str;
    }

    public void setDeductee_catg_str(String deductee_catg_str) {
        this.deductee_catg_str = deductee_catg_str;
    }

    public String getClient_country_type() {
        return client_country_type;
    }

    public void setClient_country_type(String client_country_type) {
        this.client_country_type = client_country_type;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_rate() {
        return tds_rate;
    }

    public void setTds_rate(String tds_rate) {
        this.tds_rate = tds_rate;
    }

    public String getSurcharge_rate() {
        return surcharge_rate;
    }

    public void setSurcharge_rate(String surcharge_rate) {
        this.surcharge_rate = surcharge_rate;
    }

    public String getCess_rate() {
        return cess_rate;
    }

    public void setCess_rate(String cess_rate) {
        this.cess_rate = cess_rate;
    }

    public String getTds_base_amt() {
        return tds_base_amt;
    }

    public void setTds_base_amt(String tds_base_amt) {
        this.tds_base_amt = tds_base_amt;
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

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getHecess_rate() {
        return hecess_rate;
    }

    public void setHecess_rate(String hecess_rate) {
        this.hecess_rate = hecess_rate;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

    public String getSurcharge_base_amt() {
        return surcharge_base_amt;
    }

    public void setSurcharge_base_amt(String surcharge_base_amt) {
        this.surcharge_base_amt = surcharge_base_amt;
    }

}
