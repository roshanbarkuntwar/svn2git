/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "view_lhssys_template_error")
public class ViewLhssysTemplateError implements java.io.Serializable {

//    @Id
    @Column(name = "entity_code", length = 4000, nullable = true)
    private String entity_code;
//    @Id
    @Column(name = "client_code", length = 4000, nullable = true)
    private String client_code;
//    @Id
    @Column(name = "acc_year", length = 4000, nullable = true)
    private String acc_year;
//    @Id
    @Column(name = "assesment_acc_year", length = 4000, nullable = true)
    private String assesment_acc_year;
//    @Id
    @Column(name = "quarter_no", length = 4000, nullable = true)
    private String quarter_no;
//    @Id
    @Column(name = "from_date", length = 4000, nullable = true)
    private String from_date;
//    @Id
    @Column(name = "to_date", length = 4000, nullable = true)
    private String to_date;
//    @Id
    @Column(name = "process_seqno", length = 4000, nullable = true)
    private String process_seqno;
//    @Id
    @Column(name = "tds_type_code", length = 4000, nullable = true)
    private String tds_type_code;
//    @Id
    @Column(name = "template_code", length = 4000, nullable = true)
    private String template_code;
    @Id
    @Column(name = "rowid_seq", nullable = true)
    private int rowid_seq;
    @Column(name = "lhssys_template_rowid_seqno", nullable = true)
    private int lhssys_template_rowid_seqno;
//    @Id
    @Column(name = "error_code_str", length = 4000, nullable = true)
    private String error_code_str;
    @Column(name = "error_name", length = 4000, nullable = true)
    private String error_name;
    @Column(name = "column_name", length = 4000, nullable = true)
    private String column_name;
    @Column(name = "column_data", length = 4000, nullable = true)
    private String column_data;

    public ViewLhssysTemplateError() {

    }//end

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

    public String getAssesment_acc_year() {
        return assesment_acc_year;
    }

    public void setAssesment_acc_year(String assesment_acc_year) {
        this.assesment_acc_year = assesment_acc_year;
    }

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public int getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(int rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public int getLhssys_template_rowid_seqno() {
        return lhssys_template_rowid_seqno;
    }

    public void setLhssys_template_rowid_seqno(int lhssys_template_rowid_seqno) {
        this.lhssys_template_rowid_seqno = lhssys_template_rowid_seqno;
    }

    public String getError_code_str() {
        return error_code_str;
    }

    public void setError_code_str(String error_code_str) {
        this.error_code_str = error_code_str;
    }

    public String getError_name() {
        return error_name;
    }

    public void setError_name(String error_name) {
        this.error_name = error_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_data() {
        return column_data;
    }

    public void setColumn_data(String column_data) {
        this.column_data = column_data;
    }

}//end class
