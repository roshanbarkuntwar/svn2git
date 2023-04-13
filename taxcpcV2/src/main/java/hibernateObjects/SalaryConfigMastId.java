/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author bhawna.agrawal
 */
@Embeddable
public class SalaryConfigMastId implements java.io.Serializable {

    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "itax_catg", length = 2, nullable = false, unique = true)
    private String itax_catg;
    @Column(name = "afno", length = 5, nullable = false, unique = true)
    private String afno;

    public SalaryConfigMastId() {
    }

    public SalaryConfigMastId(Date from_date, String itax_catg, String afno) {
        this.from_date = from_date;
        this.itax_catg = itax_catg;
        this.afno = afno;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getItax_catg() {
        return itax_catg;
    }

    public void setItax_catg(String itax_catg) {
        this.itax_catg = itax_catg;
    }

    public String getAfno() {
        return afno;
    }

    public void setAfno(String afno) {
        this.afno = afno;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.from_date != null ? this.from_date.hashCode() : 0);
        hash = 23 * hash + (this.itax_catg != null ? this.itax_catg.hashCode() : 0);
        hash = 23 * hash + (this.afno != null ? this.afno.hashCode() : 0);
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
        final SalaryConfigMastId other = (SalaryConfigMastId) obj;
        if (this.from_date != other.from_date && (this.from_date == null || !this.from_date.equals(other.from_date))) {
            return false;
        }
        if ((this.itax_catg == null) ? (other.itax_catg != null) : !this.itax_catg.equals(other.itax_catg)) {
            return false;
        }
        if ((this.afno == null) ? (other.afno != null) : !this.afno.equals(other.afno)) {
            return false;
        }
        return true;
    }
}
