/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

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
@Table(name = "acc_year_mast")
public class AccYearMast {

    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Column(name = "yrbegdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date yrbegdate;
    @Column(name = "yrenddate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date yrenddate;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "flag", length = 1)
    private String flag;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;

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

    public Date getYrbegdate() {
        return yrbegdate;
    }

    public void setYrbegdate(Date yrbegdate) {
        this.yrbegdate = yrbegdate;
    }

    public Date getYrenddate() {
        return yrenddate;
    }

    public void setYrenddate(Date yrenddate) {
        this.yrenddate = yrenddate;
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

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

}
