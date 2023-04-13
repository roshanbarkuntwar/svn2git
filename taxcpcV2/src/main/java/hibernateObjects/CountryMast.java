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
 * @author ayushi.jain
 */
@Entity
@Table(name = "country_mast")
public class CountryMast implements java.io.Serializable {

    @Id
    @Column(name = "country_code", length = 3, nullable = false)
    private String country_code;
    @Column(name = "country_name", length = 50, nullable = false)
    private String country_name;
    @Column(name = "parent_code", length = 2, nullable = true)
    private String parent_code;
    @Column(name = "code_level", length = 2, nullable = true)
    private String code_level;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "nationality", length = 50, nullable = true)
    private String nationality;

    public CountryMast() {

    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
