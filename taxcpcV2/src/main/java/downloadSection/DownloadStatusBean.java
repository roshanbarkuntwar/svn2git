/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadStatusBean {

    private String process_seqno;
    private String process_type;
    private String process_type_name;
    private String report_name;
    private String process_start_timestamp;
    private String process_end_timestamp;
    private String process_status;
    private String process_status_name;
    private String fvu_txt_file_name;
    private String fvu_files_path;
    private String flag;
    private String process_remark;
    private String slno;

    public DownloadStatusBean() {
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getProcess_type() {
        return process_type;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
    }

    public String getProcess_type_name() {
        return process_type_name;
    }

    public void setProcess_type_name(String process_type_name) {
        this.process_type_name = process_type_name;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getProcess_start_timestamp() {
        return process_start_timestamp;
    }

    public void setProcess_start_timestamp(String process_start_timestamp) {
        this.process_start_timestamp = process_start_timestamp;
    }

    public String getProcess_end_timestamp() {
        return process_end_timestamp;
    }

    public void setProcess_end_timestamp(String process_end_timestamp) {
        this.process_end_timestamp = process_end_timestamp;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getProcess_status_name() {
        return process_status_name;
    }

    public void setProcess_status_name(String process_status_name) {
        this.process_status_name = process_status_name;
    }

    public String getFvu_txt_file_name() {
        return fvu_txt_file_name;
    }

    public void setFvu_txt_file_name(String fvu_txt_file_name) {
        this.fvu_txt_file_name = fvu_txt_file_name;
    }

    public String getProcess_remark() {
        return process_remark;
    }

    public void setProcess_remark(String process_remark) {
        this.process_remark = process_remark;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getFvu_files_path() {
        return fvu_files_path;
    }

    public void setFvu_files_path(String fvu_files_path) {
        this.fvu_files_path = fvu_files_path;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
