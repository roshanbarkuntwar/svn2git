/*
 * To change this template, choose Tools | Templates
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
 * @author aniket.naik
 */
@Entity
@Table(name = "bank_mast")
public class BankMast implements java.io.Serializable {

    @Column(name = "bank_code", length = 5, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "bank_mast_code_seq")})
    private String bank_code;
    @Column(name = "bank_name", length = 100, nullable = false)
    private String bank_name;
    @Column(name = "branch", length = 50, nullable = true)
    private String branch;
    @Column(name = "parent_code", length = 5, nullable = true)
    private String parent_code;
    @Column(name = "codetype", length = 1, nullable = true)
    private String codetype;
    @Column(name = "address", length = 500, nullable = true)
    private String address;
    @Column(name = "branch_code", length = 50, nullable = true)
    private String branch_code;
    @Column(name = "ifsc_code", length = 50, nullable = true)
    private String ifsc_code;
    @Column(name = "swift_code", length = 50, nullable = true)
    private String swift_code;
    @Column(name = "bsr_code", length = 50, nullable = true)
    private String bsr_code;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;

    public BankMast() {
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getSwift_code() {
        return swift_code;
    }

    public void setSwift_code(String swift_code) {
        this.swift_code = swift_code;
    }

    public String getBsr_code() {
        return bsr_code;
    }

    public void setBsr_code(String bsr_code) {
        this.bsr_code = bsr_code;
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
}
