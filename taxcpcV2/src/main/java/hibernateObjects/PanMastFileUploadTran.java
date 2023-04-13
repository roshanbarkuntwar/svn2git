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
 * @author kapil.gupta
 */
@Entity
@Table(name = "pan_mast_file_upload_tran")
public class PanMastFileUploadTran implements java.io.Serializable {

    @Id
    @Column(name = "rowid_seq", nullable = false)
    private Long rowid_seq;
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Column(name = "quarter_no", nullable = false)
    private Long quarter_no;
    @Column(name = "tds_type_code", length = 5, nullable = false)
    private String tds_type_code;
    @Column(name = "storage_file_path", length = 1000, nullable = false)
    private String storage_file_path;
    @Column(name = "file_type", length = 5, nullable = false)
    private String file_type;
    @Column(name = "file_name", length = 1000, nullable = false)
    private String file_name;
    @Column(name = "file_size")
    private Long file_size;
    @Column(name = "load_approx_time")
    private Long load_approx_time;
    @Column(name = "file_no_of_lines")
    private Long file_no_of_lines;
    @Column(name = "file_no_of_record")
    private Long file_no_of_record;
    @Column(name = "load_by_flag", length = 1, nullable = false)
    private String load_by_flag;
    @Column(name = "load_status", length = 1, nullable = false)
    private String load_status;
    @Column(name = "load_remark", length = 4000)
    private String load_remark;
    @Column(name = "load_start_timestamp", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date load_start_timestamp;
    @Column(name = "load_end_timestamp")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date load_end_timestamp;
    @Column(name = "alert_status", length = 1)
    private String alert_status;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1)
    private String flag;
    @Column(name = "process_seqno")
    private Long process_seqno;
    @Column(name = "upload_file_name", length = 400)
    private String upload_file_name;

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
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

    public Long getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(Long quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getStorage_file_path() {
        return storage_file_path;
    }

    public void setStorage_file_path(String storage_file_path) {
        this.storage_file_path = storage_file_path;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Long getFile_size() {
        return file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public Long getLoad_approx_time() {
        return load_approx_time;
    }

    public void setLoad_approx_time(Long load_approx_time) {
        this.load_approx_time = load_approx_time;
    }

    public Long getFile_no_of_lines() {
        return file_no_of_lines;
    }

    public void setFile_no_of_lines(Long file_no_of_lines) {
        this.file_no_of_lines = file_no_of_lines;
    }

    public Long getFile_no_of_record() {
        return file_no_of_record;
    }

    public void setFile_no_of_record(Long file_no_of_record) {
        this.file_no_of_record = file_no_of_record;
    }

    public String getLoad_by_flag() {
        return load_by_flag;
    }

    public void setLoad_by_flag(String load_by_flag) {
        this.load_by_flag = load_by_flag;
    }

    public String getLoad_status() {
        return load_status;
    }

    public void setLoad_status(String load_status) {
        this.load_status = load_status;
    }

    public String getLoad_remark() {
        return load_remark;
    }

    public void setLoad_remark(String load_remark) {
        this.load_remark = load_remark;
    }

    public Date getLoad_start_timestamp() {
        return load_start_timestamp;
    }

    public void setLoad_start_timestamp(Date load_start_timestamp) {
        this.load_start_timestamp = load_start_timestamp;
    }

    public Date getLoad_end_timestamp() {
        return load_end_timestamp;
    }

    public void setLoad_end_timestamp(Date load_end_timestamp) {
        this.load_end_timestamp = load_end_timestamp;
    }

    public String getAlert_status() {
        return alert_status;
    }

    public void setAlert_status(String alert_status) {
        this.alert_status = alert_status;
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

    public Long getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Long process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getUpload_file_name() {
        return upload_file_name;
    }

    public void setUpload_file_name(String upload_file_name) {
        this.upload_file_name = upload_file_name;
    }

}
