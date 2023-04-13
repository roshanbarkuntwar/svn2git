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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author ayushi.jain
 */
@Entity
@Table(name = "lhssys_process_log")
public class LhssysProcessLog implements java.io.Serializable {

    @Id
    @Column(name = "process_seqno", nullable = false)
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
            = @Parameter(name = "sequence", value = "lhssys_process_log_seqno"))
    @GeneratedValue(generator = "generator")

    private Long process_seqno;
    
@Column(name = "parent_process_seqno", nullable = true)
private Long parent_process_seqno;
@Column(name = "module_type", nullable = true)
private String module_type;
@Column(name = "fuv_text_filename", nullable = true)
private String fuv_text_filename;
@Column(name = "import_filename", nullable = true)
private String import_filename;
@Column(name = "log_file_name4", nullable = true)
private String log_file_name4;
@Column(name = "log_file_name5", nullable = true)
private String log_file_name5;
@Column(name = "log_file_name2", nullable = true)
private String log_file_name2;
@Column(name = "fvu_csi_filename", nullable = true)
private String fvu_csi_filename;
@Column(name = "log_file_name1", nullable = true)
private String log_file_name1;
@Column(name = "fvu_text_file_data", nullable = true)
private String fvu_text_file_data;
@Column(name = "fvu_text_file_clob_folder", nullable = true)
private String fvu_text_file_clob_folder;
@Column(name = "log_file_name3", nullable = true)
private String log_file_name3;
@Column(name = "client_login_session_seqno", nullable = false)
private Long client_login_session_seqno;
@Column(name = "entity_code", nullable = true)
private String entity_code;
@Column(name = "client_code", nullable = false)
private String client_code;
@Column(name = "acc_year", nullable = false)
private String acc_year;
@Column(name = "quarter_no", nullable = false)
private Long quarter_no;
@Column(name = "month", nullable = true)
private String month;
@Column(name = "from_date", nullable = false)
@Temporal(javax.persistence.TemporalType.DATE)private Date from_date;
@Column(name = "to_date", nullable = false)
@Temporal(javax.persistence.TemporalType.DATE)private Date to_date;
@Column(name = "tds_type_code", nullable = false)
private String tds_type_code;
@Column(name = "process_type", nullable = false)
private String process_type;
@Column(name = "process_start_timestamp", nullable = false)
@Temporal(javax.persistence.TemporalType.DATE)private Date process_start_timestamp;
@Column(name = "process_end_timestamp", nullable = true)
@Temporal(javax.persistence.TemporalType.DATE)private Date process_end_timestamp;
@Column(name = "process_remark", nullable = true)
private String process_remark;
@Column(name = "user_code", nullable = true)
private String user_code;
@Column(name = "lastupdate", nullable = true)
@Temporal(javax.persistence.TemporalType.DATE)private Date lastupdate;
@Column(name = "flag", nullable = true)
private String flag;
@Column(name = "remove_record_flag", nullable = true)
private String remove_record_flag;
@Column(name = "remove_login_session_seqno", nullable = true)
private Long remove_login_session_seqno;
@Column(name = "file_no_of_lines", nullable = true)
private Long file_no_of_lines;
@Column(name = "file_no_of_record", nullable = true)
private Long file_no_of_record;
@Column(name = "db_process_session_id", nullable = true)
private Long db_process_session_id;
@Column(name = "file_no_of_excep_record", nullable = true)
private Long file_no_of_excep_record;
@Column(name = "process_sid", nullable = true)
private Long process_sid;
@Column(name = "process_serial", nullable = true)
private Long process_serial;
@Column(name = "process_status", nullable = true)
private String process_status;
@Column(name = "template_code", nullable = true)
private String template_code;
@Column(name = "record_insert_flag", nullable = true)
private String record_insert_flag;
@Column(name = "fvu_txt_file_name", nullable = true)
private String fvu_txt_file_name;
@Column(name = "fvu_files_name_str", nullable = true)
private String fvu_files_name_str;
@Column(name = "fvu_files_path", nullable = true)
private String fvu_files_path;
@Column(name = "fvu_pid", nullable = true)
private String fvu_pid;
@Column(name = "fvu_pid_flag", nullable = true)
private String fvu_pid_flag;
@Column(name = "filter_values", nullable = true)
private String filter_values;
@Column(name = "xml_file_name", nullable = true)
private String xml_file_name;
@Column(name = "report_name", nullable = true)
private String report_name;

    public Long getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Long process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public String getFuv_text_filename() {
        return fuv_text_filename;
    }

    public String getImport_filename() {
        return import_filename;
    }

    public void setImport_filename(String import_filename) {
        this.import_filename = import_filename;
    }

    public void setFuv_text_filename(String fuv_text_filename) {
        this.fuv_text_filename = fuv_text_filename;
    }

    public String getLog_file_name4() {
        return log_file_name4;
    }

    public void setLog_file_name4(String log_file_name4) {
        this.log_file_name4 = log_file_name4;
    }

    public String getLog_file_name5() {
        return log_file_name5;
    }

    public void setLog_file_name5(String log_file_name5) {
        this.log_file_name5 = log_file_name5;
    }

    public String getLog_file_name2() {
        return log_file_name2;
    }

    public void setLog_file_name2(String log_file_name2) {
        this.log_file_name2 = log_file_name2;
    }

    public String getFvu_csi_filename() {
        return fvu_csi_filename;
    }

    public void setFvu_csi_filename(String fvu_csi_filename) {
        this.fvu_csi_filename = fvu_csi_filename;
    }

    public String getLog_file_name1() {
        return log_file_name1;
    }

    public void setLog_file_name1(String log_file_name1) {
        this.log_file_name1 = log_file_name1;
    }

    public String getFvu_text_file_data() {
        return fvu_text_file_data;
    }

    public void setFvu_text_file_data(String fvu_text_file_data) {
        this.fvu_text_file_data = fvu_text_file_data;
    }

    public String getFvu_text_file_clob_folder() {
        return fvu_text_file_clob_folder;
    }

    public void setFvu_text_file_clob_folder(String fvu_text_file_clob_folder) {
        this.fvu_text_file_clob_folder = fvu_text_file_clob_folder;
    }

    public String getLog_file_name3() {
        return log_file_name3;
    }

    public void setLog_file_name3(String log_file_name3) {
        this.log_file_name3 = log_file_name3;
    }

    public Long getClient_login_session_seqno() {
        return client_login_session_seqno;
    }

    public void setClient_login_session_seqno(Long client_login_session_seqno) {
        this.client_login_session_seqno = client_login_session_seqno;
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

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getProcess_type() {
        return process_type;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
    }

    public Date getProcess_start_timestamp() {
        return process_start_timestamp;
    }

    public void setProcess_start_timestamp(Date process_start_timestamp) {
        this.process_start_timestamp = process_start_timestamp;
    }

    public Date getProcess_end_timestamp() {
        return process_end_timestamp;
    }

    public void setProcess_end_timestamp(Date process_end_timestamp) {
        this.process_end_timestamp = process_end_timestamp;
    }

    public String getProcess_remark() {
        return process_remark;
    }

    public void setProcess_remark(String process_remark) {
        this.process_remark = process_remark;
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

    public Long getDb_process_session_id() {
        return db_process_session_id;
    }

    public void setDb_process_session_id(Long db_process_session_id) {
        this.db_process_session_id = db_process_session_id;
    }

    public Long getFile_no_of_excep_record() {
        return file_no_of_excep_record;
    }

    public void setFile_no_of_excep_record(Long file_no_of_excep_record) {
        this.file_no_of_excep_record = file_no_of_excep_record;
    }

    public Long getProcess_sid() {
        return process_sid;
    }

    public void setProcess_sid(Long process_sid) {
        this.process_sid = process_sid;
    }

    public Long getProcess_serial() {
        return process_serial;
    }

    public void setProcess_serial(Long process_serial) {
        this.process_serial = process_serial;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getRecord_insert_flag() {
        return record_insert_flag;
    }

    public void setRecord_insert_flag(String record_insert_flag) {
        this.record_insert_flag = record_insert_flag;
    }

    public String getFvu_txt_file_name() {
        return fvu_txt_file_name;
    }

    public void setFvu_txt_file_name(String fvu_txt_file_name) {
        this.fvu_txt_file_name = fvu_txt_file_name;
    }

    public String getFvu_files_name_str() {
        return fvu_files_name_str;
    }

    public void setFvu_files_name_str(String fvu_files_name_str) {
        this.fvu_files_name_str = fvu_files_name_str;
    }

    public String getFvu_files_path() {
        return fvu_files_path;
    }

    public void setFvu_files_path(String fvu_files_path) {
        this.fvu_files_path = fvu_files_path;
    }

    public String getFvu_pid() {
        return fvu_pid;
    }

    public void setFvu_pid(String fvu_pid) {
        this.fvu_pid = fvu_pid;
    }

    public String getFvu_pid_flag() {
        return fvu_pid_flag;
    }

    public void setFvu_pid_flag(String fvu_pid_flag) {
        this.fvu_pid_flag = fvu_pid_flag;
    }

    public String getFilter_values() {
        return filter_values;
    }

    public void setFilter_values(String filter_values) {
        this.filter_values = filter_values;
    }

    public String getXml_file_name() {
        return xml_file_name;
    }

    public void setXml_file_name(String xml_file_name) {
        this.xml_file_name = xml_file_name;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public Long getParent_process_seqno() {
        return parent_process_seqno;
    }

    public void setParent_process_seqno(Long parent_process_seqno) {
        this.parent_process_seqno = parent_process_seqno;
    }



}
