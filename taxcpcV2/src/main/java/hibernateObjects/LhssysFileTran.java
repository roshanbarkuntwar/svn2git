/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "lhssys_file_tran")
public class LhssysFileTran implements java.io.Serializable {

    @Id
    @Column(name = "file_seqno", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "file_tran_seq")})
    private String file_seqno;
    @Column(name = "client_login_session_seqno", nullable = false)
    private Long client_login_session_seqno;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "tds_type_code", length = 5, nullable = false)
    private String tds_type_code;
    @Column(name = "acc_year", length = 5, nullable = false)
    private String acc_year;
    @Column(name = "quarter_no", nullable = false)
    private Double quarter_no;
    @Column(name = "month", length = 3, nullable = true)
    private String month;
    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "template_code", length = 15, nullable = true)
    private String template_code;
    @Column(name = "upload_purpose", length = 1, nullable = false)
    private String upload_purpose;
    @Column(name = "upload_method", nullable = false)
    private Long upload_method;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_start_timestamp", nullable = true)
    private Date upload_start_timestamp;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_end_timestamp", nullable = true)
    private Date upload_end_timestamp;
    @Column(name = "remove_record_flag", length = 1, nullable = true)
    private String remove_record_flag;
    @Column(name = "remove_login_session_seqno", nullable = true)
    private Long remove_login_session_seqno;
    @Column(name = "file_name", length = 1000, nullable = true)
    private String file_name;
    @Column(name = "storage_file_path", length = 1000, nullable = true)
    private String storage_file_path;
    @Column(name = "file_size", nullable = true)
    private Double file_size;
    @Column(name = "load_approx_time", nullable = true)
    private String load_approx_time;
    @Column(name = "file_no_of_lines", nullable = true)
    private Integer file_no_of_lines;
    @Column(name = "file_no_of_record", nullable = true)
    private Integer file_no_of_record;
    @Column(name = "load_by_flag", length = 1, nullable = true)
    private String load_by_flag;
    @Column(name = "load_status", length = 1, nullable = true)
    private String load_status;
    @Column(name = "load_remark", length = 4000, nullable = true)
    private String load_remark;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "load_start_timestamp", nullable = false)
    private Date load_start_timestamp;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "load_end_timestamp", nullable = true)
    private Date load_end_timestamp;
    @Column(name = "alert_status", length = 1, nullable = true)
    private String alert_status;
    @Column(name = "import_flag", length = 2, nullable = false)
    private String import_flag;
    @Column(name = "file_type", length = 1, nullable = false)
    private String file_type;
    @Column(name = "ref_no", nullable = true)
    private Long ref_no;
    @Column(name = "ref_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ref_date;
    @Column(name = "download_file_name", length = 1000, nullable = true)
    private String download_file_name;
    @Column(name = "download_storage_file_path", length = 1000, nullable = true)
    private String download_storage_file_path;
    @Column(name = "download_file_size", nullable = true)
    private Long download_file_size;
    @Column(name = "fvu_file_status", length = 1, nullable = true)
    private String fvu_file_status;
    @Column(name = "fvu_file_name_str", length = 4000, nullable = true)
    private String fvu_file_name_str;
    @Column(name = "fvu_storage_file_path", length = 4000, nullable = true)
    private String fvu_storage_file_path;
    @Column(name = "upload_ref_no", length = 50, nullable = true)
    private String upload_ref_no;
    @Column(name = "upload_ref_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date upload_ref_date;
    @Column(name = "fvu_file_name", length = 50, nullable = true)
    private String fvu_file_name;
    @Column(name = "pid_no", length = 50, nullable = true)
    private String pid_no;
    @Column(name = "pid_flag", length = 1, nullable = true)
    private String pid_flag;

    public LhssysFileTran() {
    }//end constructor

    public String getFile_seqno() {
        return file_seqno;
    }

    public void setFile_seqno(String file_seqno) {
        this.file_seqno = file_seqno;
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

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public Double getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(Double quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public Long getUpload_method() {
        return upload_method;
    }

    public void setUpload_method(Long upload_method) {
        this.upload_method = upload_method;
    }

    public Date getUpload_start_timestamp() {
        return upload_start_timestamp;
    }

    public void setUpload_start_timestamp(Date upload_start_timestamp) {
        this.upload_start_timestamp = upload_start_timestamp;
    }

    public Date getUpload_end_timestamp() {
        return upload_end_timestamp;
    }

    public void setUpload_end_timestamp(Date upload_end_timestamp) {
        this.upload_end_timestamp = upload_end_timestamp;
    }

    public String getRemove_record_flag() {
        return remove_record_flag;
    }

    public void setRemove_record_flag(String remove_record_flag) {
        this.remove_record_flag = remove_record_flag;
    }

    public Long getRemove_login_session_seqno() {
        return remove_login_session_seqno;
    }

    public void setRemove_login_session_seqno(Long remove_login_session_seqno) {
        this.remove_login_session_seqno = remove_login_session_seqno;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getStorage_file_path() {
        return storage_file_path;
    }

    public void setStorage_file_path(String storage_file_path) {
        this.storage_file_path = storage_file_path;
    }

    public Double getFile_size() {
        return file_size;
    }

    public void setFile_size(Double file_size) {
        this.file_size = file_size;
    }

    public String getLoad_approx_time() {
        return load_approx_time;
    }

    public void setLoad_approx_time(String load_approx_time) {
        this.load_approx_time = load_approx_time;
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

    public Integer getFile_no_of_lines() {
        return file_no_of_lines;
    }

    public void setFile_no_of_lines(Integer file_no_of_lines) {
        this.file_no_of_lines = file_no_of_lines;
    }

    public Integer getFile_no_of_record() {
        return file_no_of_record;
    }

    public void setFile_no_of_record(Integer file_no_of_record) {
        this.file_no_of_record = file_no_of_record;
    }

    public String getImport_flag() {
        return import_flag;
    }

    public void setImport_flag(String import_flag) {
        this.import_flag = import_flag;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Long getRef_no() {
        return ref_no;
    }

    public void setRef_no(Long ref_no) {
        this.ref_no = ref_no;
    }

    public Date getRef_date() {
        return ref_date;
    }

//    public void setRef_date(Date ref_date) {
//        this.ref_date = ref_date;
//    }
    public void setRef_date(String ref_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(ref_date);
        } catch (Exception e) {
            d = null;
        }
        this.ref_date = d;
    }

    public String getDownload_file_name() {
        return download_file_name;
    }

    public void setDownload_file_name(String download_file_name) {
        this.download_file_name = download_file_name;
    }

    public String getDownload_storage_file_path() {
        return download_storage_file_path;
    }

    public void setDownload_storage_file_path(String download_storage_file_path) {
        this.download_storage_file_path = download_storage_file_path;
    }

    public Long getDownload_file_size() {
        return download_file_size;
    }

    public void setDownload_file_size(Long download_file_size) {
        this.download_file_size = download_file_size;
    }

    public String getFvu_file_status() {
        return fvu_file_status;
    }

    public void setFvu_file_status(String fvu_file_status) {
        this.fvu_file_status = fvu_file_status;
    }

    public String getFvu_file_name_str() {
        return fvu_file_name_str;
    }

    public void setFvu_file_name_str(String fvu_file_name_str) {
        this.fvu_file_name_str = fvu_file_name_str;
    }

    public String getFvu_storage_file_path() {
        return fvu_storage_file_path;
    }

    public void setFvu_storage_file_path(String fvu_storage_file_path) {
        this.fvu_storage_file_path = fvu_storage_file_path;
    }

    public String getUpload_ref_no() {
        return upload_ref_no;
    }

    public void setUpload_ref_no(String upload_ref_no) {
        this.upload_ref_no = upload_ref_no;
    }

    public Date getUpload_ref_date() {
        return upload_ref_date;
    }

    public void setUpload_ref_date(String upload_ref_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(upload_ref_date);
        } catch (Exception e) {
            d = null;
        }
        this.upload_ref_date = d;
    }

    public String getFvu_file_name() {
        return fvu_file_name;
    }

    public void setFvu_file_name(String fvu_file_name) {
        this.fvu_file_name = fvu_file_name;
    }

    public String getPid_no() {
        return pid_no;
    }

    public void setPid_no(String pid_no) {
        this.pid_no = pid_no;
    }

    public String getPid_flag() {
        return pid_flag;
    }

    public void setPid_flag(String pid_flag) {
        this.pid_flag = pid_flag;
    }

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}//end class
