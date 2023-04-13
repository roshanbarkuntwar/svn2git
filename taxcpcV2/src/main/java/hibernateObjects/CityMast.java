/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

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
 * @author akash.dev
 */
@Entity
@Table(name = "city_mast")
public class CityMast implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "city_mast_code_seq")})
    @Id
    @Column(name = "city_code", length = 6, nullable = false)
    private String city_code;
    @Column(name = "city_name", length = 50, nullable = false)
    private String city_name;
    @Column(name = "city_rank", length = 1, nullable = true)
    private String city_rank;
    @Column(name = "city_mpv", length = 14, precision = 2, nullable = true)
    private String city_mpv;	//number(14,2)	y
    @Column(name = "off_day", length = 3, nullable = true)
    private String off_day;
    @Column(name = "district_code", length = 4, nullable = true)
    private String district_code;
    @Column(name = "hq_code", length = 3, nullable = true)
    private String hq_code;
    @Column(name = "state_code", length = 2, nullable = true)
    private String state_code;
    @Column(name = "parent_code", length = 3, nullable = true)
    private String parent_code;
    @Column(name = "code_level", length = 2, nullable = true)
    private String code_level;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "country_code", length = 3, nullable = true)
    private String country_code;

    public CityMast() {
        this.user_code = "SHASHANK";
        this.lastupdate = new Date();
    }

    public CityMast(String city_code, String city_name, String city_rank, String city_mpv, String off_day, String district_code, String hq_code, String state_code, String parent_code, String code_level, String user_code, Date lastupdate, String flag, String country_code) {
        this.city_code = city_code;
        this.city_name = city_name;
        this.city_rank = city_rank;
        this.city_mpv = city_mpv;
        this.off_day = off_day;
        this.district_code = district_code;
        this.hq_code = hq_code;
        this.state_code = state_code;
        this.parent_code = parent_code;
        this.code_level = code_level;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.country_code = country_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_rank() {
        return city_rank;
    }

    public void setCity_rank(String city_rank) {
        this.city_rank = city_rank;
    }

    public String getCity_mpv() {
        return city_mpv;
    }

    public void setCity_mpv(String city_mpv) {
        this.city_mpv = city_mpv;
    }

    public String getOff_day() {
        return off_day;
    }

    public void setOff_day(String off_day) {
        this.off_day = off_day;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getHq_code() {
        return hq_code;
    }

    public void setHq_code(String hq_code) {
        this.hq_code = hq_code;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
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

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
