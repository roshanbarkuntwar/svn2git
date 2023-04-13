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
@Table(name = "LHSSYS_CORR_FILE_UPLOAD_TRAN")
public class LhssysCorrFileUploadTran implements java.io.Serializable {

    @Id
    @Column(name = "file_seqno", length = 22, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "LHSSYS_CORR_FILE_UPLOAD_SEQNO")})
    private String file_seqno;

    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Column(name = "alert_status", length = 1, nullable = true)
    private String alert_status;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "client_login_session_seqno", length = 22, nullable = false)
    private Long client_login_session_seqno;
    @Column(name = "file_name", length = 1000, nullable = true)
    private String file_name;
    @Column(name = "file_no_of_lines", length = 22, nullable = true)
    private Long file_no_of_lines;
    @Column(name = "file_no_of_record", length = 22, nullable = true)
    private Long file_no_of_record;

    @Column(name = "file_size", length = 22, nullable = true)
    private Double file_size;
    @Column(name = "file_type", length = 5, nullable = false)
    private String file_type;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "from_date", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "load_approx_time", length = 22, nullable = true)
    private Long load_approx_time;
    @Column(name = "load_by_flag", length = 1, nullable = true)
    private String load_by_flag;
    @Column(name = "load_end_timestamp", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date load_end_timestamp;
    @Column(name = "load_remark", length = 4000, nullable = true)
    private String load_remark;
    @Column(name = "load_start_timestamp", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date load_start_timestamp;
    @Column(name = "load_status", length = 1, nullable = true)
    private String load_status;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "quarter_no", length = 22, nullable = false)
    private Double quarter_no;
    @Column(name = "remove_login_session_seqno", length = 22, nullable = true)
    private Long remove_login_session_seqno;
    @Column(name = "remove_record_flag", length = 1, nullable = true)
    private String remove_record_flag;
    @Column(name = "storage_file_path", length = 1000, nullable = true)
    private String storage_file_path;
    @Column(name = "tds_type_code", length = 5, nullable = false)
    private String tds_type_code;
    @Column(name = "to_date", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;

    @Column(name = "panno_corr_file_name", length = 1000, nullable = true)
    private String panno_corr_file_name;

    @Column(name = "justification_file_name", length = 1000, nullable = true)
    private String justification_file_name;

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getAlert_status() {
        return alert_status;
    }

    public void setAlert_status(String alert_status) {
        this.alert_status = alert_status;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public Long getClient_login_session_seqno() {
        return client_login_session_seqno;
    }

    public void setClient_login_session_seqno(Long client_login_session_seqno) {
        this.client_login_session_seqno = client_login_session_seqno;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
    }

    public Double getFile_size() {
        return file_size;
    }

    public void setFile_size(Double file_size) {
        this.file_size = file_size;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Long getLoad_approx_time() {
        return load_approx_time;
    }

    public void setLoad_approx_time(Long load_approx_time) {
        this.load_approx_time = load_approx_time;
    }

    public String getLoad_by_flag() {
        return load_by_flag;
    }

    public void setLoad_by_flag(String load_by_flag) {
        this.load_by_flag = load_by_flag;
    }

    public Date getLoad_end_timestamp() {
        return load_end_timestamp;
    }

    public void setLoad_end_timestamp(Date load_end_timestamp) {
        this.load_end_timestamp = load_end_timestamp;
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

    public String getLoad_status() {
        return load_status;
    }

    public void setLoad_status(String load_status) {
        this.load_status = load_status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getRemove_login_session_seqno() {
        return remove_login_session_seqno;
    }

    public void setRemove_login_session_seqno(Long remove_login_session_seqno) {
        this.remove_login_session_seqno = remove_login_session_seqno;
    }

    public String getRemove_record_flag() {
        return remove_record_flag;
    }

    public void setRemove_record_flag(String remove_record_flag) {
        this.remove_record_flag = remove_record_flag;
    }

    public String getStorage_file_path() {
        return storage_file_path;
    }

    public void setStorage_file_path(String storage_file_path) {
        this.storage_file_path = storage_file_path;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Double getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(Double quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getPanno_corr_file_name() {
        return panno_corr_file_name;
    }

    public void setPanno_corr_file_name(String panno_corr_file_name) {
        this.panno_corr_file_name = panno_corr_file_name;
    }

    public String getJustification_file_name() {
        return justification_file_name;
    }

    public void setJustification_file_name(String justification_file_name) {
        this.justification_file_name = justification_file_name;
    }

}
