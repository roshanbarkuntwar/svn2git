/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
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
@Table(name = "state_mast")
public class StateMast implements java.io.Serializable {

    @Id
    @Column(name = "state_code", length = 2, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "state_mast_code_seq")})
    private String state_code;
    @Column(name = "state_name", length = 40, nullable = false)
    private String state_name;
    @Column(name = "parent_code", length = 2, nullable = true)
    private String parent_code;
    @Column(name = "codetype", length = 1, nullable = false, insertable = false, columnDefinition = "number(1) default 1")
    private String codetype;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "country_code", length = 3, nullable = true)
    private String country_code;

    public StateMast() {
        this.user_code = "SHASHANK";
    }

    public StateMast(String state_code, String state_name, String parent_code, String codetype, String user_code, String flag, Date lastupdate, String country_code) {
        this.state_code = state_code;
        this.state_name = state_name;
        this.parent_code = parent_code;
        this.codetype = codetype;
        this.user_code = user_code;
        this.flag = flag;
        this.lastupdate = lastupdate;
        this.country_code = country_code;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getCodetype() {
        return codetype;
    }

    public void setCodetype(String codetype) {
        this.codetype = codetype;
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

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
