/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateobjects;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author gaurav.khanzode
 */
@Embeddable
public class TdsRateMastId implements java.io.Serializable {

    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "tds_code", length = 10, nullable = false)
    private String tds_code;
    @Column(name = "country_code", length = 5, nullable = false)
    private String country_code;
    @Column(name = "deductee_catg_str", length = 4000, nullable = true)
    private String deductee_catg_str;
    @Column(name = "client_country_type", length = 1, nullable = true)
    private String client_country_type;
//    @Column(name = "rowid", length = 100, nullable = true)
//    private String rowid;

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

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public TdsRateMastId() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.from_date != null ? this.from_date.hashCode() : 0);
        hash = 79 * hash + (this.tds_code != null ? this.tds_code.hashCode() : 0);
        hash = 79 * hash + (this.country_code != null ? this.country_code.hashCode() : 0);
        hash = 79 * hash + (this.deductee_catg_str != null ? this.deductee_catg_str.hashCode() : 0);
        hash = 79 * hash + (this.client_country_type != null ? this.client_country_type.hashCode() : 0);
//        hash = 79 * hash + (this.rowid != null ? this.rowid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TdsRateMastId other = (TdsRateMastId) obj;
        if (this.from_date != other.from_date && (this.from_date == null || !this.from_date.equals(other.from_date))) {
            return false;
        }
        if ((this.tds_code == null) ? (other.tds_code != null) : !this.tds_code.equals(other.tds_code)) {
            return false;
        }
        if ((this.country_code == null) ? (other.country_code != null) : !this.country_code.equals(other.country_code)) {
            return false;
        }
        if ((this.deductee_catg_str == null) ? (other.deductee_catg_str != null) : !this.deductee_catg_str.equals(other.deductee_catg_str)) {
            return false;
        }
        if ((this.client_country_type == null) ? (other.client_country_type != null) : !this.client_country_type.equals(other.client_country_type)) {
            return false;
        }
//        if ((this.rowid == null) ? (other.rowid != null) : !this.rowid.equals(other.rowid)) {
//            return false;
//        }
        return true;
    }
}
