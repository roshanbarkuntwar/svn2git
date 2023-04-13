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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "deductee_bflag_refinfo_seq_no")
public class DeducteeBflagRefinfoSeqNo implements java.io.Serializable {

    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Id
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Id
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Id
    @Column(name = "bflag", length = 1, nullable = false)
    private String bflag;
    @Column(name = "reference_no", length = 10)
    private String reference_no;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "auto_gen_ref_no", length = 1, nullable = true)
    private String auto_gen_ref_no;

    public DeducteeBflagRefinfoSeqNo() {

    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getBflag() {
        return bflag;
    }

    public void setBflag(String bflag) {
        this.bflag = bflag;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
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

    public String getAuto_gen_ref_no() {
        return auto_gen_ref_no;
    }

    public void setAuto_gen_ref_no(String auto_gen_ref_no) {
        this.auto_gen_ref_no = auto_gen_ref_no;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
