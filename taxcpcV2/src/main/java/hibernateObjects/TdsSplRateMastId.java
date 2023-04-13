/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bhawna.agrawal
 */
@Embeddable
public class TdsSplRateMastId implements Serializable {

//    @Column(name = "from_date", length = 7, nullable = false)
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date from_date;
    @Column(name = "tds_code", length = 10, nullable = false)
    private String tds_code;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Column(name = "client_code", length = 10, nullable = true)
    private String client_code;
    @Column(name = "deductee_panno", length = 15, nullable = true)
    private String deductee_panno;

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

//    @Column(name = "deductee_code", length = 15, nullable = false)
//    private String deductee_code;
    @Column(name = "certificate_no", length = 50, nullable = false)
    private String certificate_no;

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

//    public Date getFrom_date() {
//        return from_date;
//    }
//
//    public void setFrom_date(Date from_date) {
//        this.from_date = from_date;
//    }
    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

//    public String getDeductee_code() {
//        return deductee_code;
//    }
//
//    public void setDeductee_code(String deductee_code) {
//        this.deductee_code = deductee_code;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
//        hash = 71 * hash + (this.from_date != null ? this.from_date.hashCode() : 0);
        hash = 71 * hash + (this.tds_code != null ? this.tds_code.hashCode() : 0);
//        hash = 71 * hash + (this.deductee_code != null ? this.deductee_code.hashCode() : 0);
        hash = 71 * hash + (this.certificate_no != null ? this.certificate_no.hashCode() : 0);
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
        final TdsSplRateMastId other = (TdsSplRateMastId) obj;
//        if (this.from_date != other.from_date && (this.from_date == null || !this.from_date.equals(other.from_date))) {
//            return false;
//        }
        if ((this.tds_code == null) ? (other.tds_code != null) : !this.tds_code.equals(other.tds_code)) {
            return false;
        }
//        if ((this.deductee_code == null) ? (other.deductee_code != null) : !this.deductee_code.equals(other.deductee_code)) {
//            return false;
//        }
        if ((this.certificate_no == null) ? (other.certificate_no != null) : !this.certificate_no.equals(other.certificate_no)) {
            return false;
        }
        return true;
    }

}
