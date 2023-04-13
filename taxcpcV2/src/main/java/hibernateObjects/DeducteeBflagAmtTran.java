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
 * @author aniket.naik
 */
@Entity
@Table(name = "deductee_bflag_amt_tran")
public class DeducteeBflagAmtTran implements java.io.Serializable {

    @Column(name = "bflag_tran_seq_id", nullable = true)
    private Long bflag_tran_seq_id;
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "deductee_code", length = 15)
    private String deductee_code;
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Column(name = "reference_no", length = 10)
    private String reference_no;
    @Column(name = "panno", length = 10, nullable = false)
    private String panno;
    @Column(name = "account_no", length = 125, nullable = false)
    private String account_no;
    @Column(name = "section", length = 5, nullable = false)
    private String section;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "user_code", length = 8)
    private String user_code;
    @Column(name = "lastupdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1)
    private String flag;
    @Column(name = "nature_of_income", length = 125)
    private String nature_of_income;
    @Column(name = "file_seqno")
    private Long file_seqno;
    @Column(name = "ref_file_seqno")
    private Long ref_file_seqno;
    @Column(name = "ref_file_flag", length = 1)
    private String ref_file_flag;
    @Column(name = "bank_branch_code", length = 15)
    private String bank_branch_code;
    @Column(name = "entry_client_code", length = 15)
    private String entry_client_code;
    @Column(name = "quarter_no", nullable = false)
    private String quarter_no;
    @Column(name = "tds_type_code", length = 5, nullable = false)
    private String tds_type_code;
    @Column(name = "deductee_mast_15gh_rowid_seq")
    private Long deductee_mast_15gh_rowid_seq;
    @Column(name = "rowid_seq", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence", parameters = {
        @Parameter(name = "sequence", value = "deductee_bflag_amt_tran_seq")})
    private Long rowid_seq;
    @Column(name = "month", length = 3)
    private String month;
    @Column(name = "session_id", length = 10)
    private String session_id;
    @Column(name = "process_seqno")
    private Long process_seqno;

    public DeducteeBflagAmtTran() {
        this.user_code = "SHASHANK";
    }

    public Long getBflag_tran_seq_id() {
        return bflag_tran_seq_id;
    }

    public void setBflag_tran_seq_id(Long bflag_tran_seq_id) {
        this.bflag_tran_seq_id = bflag_tran_seq_id;
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

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public String getNature_of_income() {
        return nature_of_income;
    }

    public void setNature_of_income(String nature_of_income) {
        this.nature_of_income = nature_of_income;
    }

    public Long getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(Long file_seqno) {
        this.file_seqno = file_seqno;
    }

    public Long getRef_file_seqno() {
        return ref_file_seqno;
    }

    public void setRef_file_seqno(Long ref_file_seqno) {
        this.ref_file_seqno = ref_file_seqno;
    }

    public String getRef_file_flag() {
        return ref_file_flag;
    }

    public void setRef_file_flag(String ref_file_flag) {
        this.ref_file_flag = ref_file_flag;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getEntry_client_code() {
        return entry_client_code;
    }

    public void setEntry_client_code(String entry_client_code) {
        this.entry_client_code = entry_client_code;
    }

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public Long getDeductee_mast_15gh_rowid_seq() {
        return deductee_mast_15gh_rowid_seq;
    }

    public void setDeductee_mast_15gh_rowid_seq(Long deductee_mast_15gh_rowid_seq) {
        this.deductee_mast_15gh_rowid_seq = deductee_mast_15gh_rowid_seq;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Long getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Long process_seqno) {
        this.process_seqno = process_seqno;
    }

}
