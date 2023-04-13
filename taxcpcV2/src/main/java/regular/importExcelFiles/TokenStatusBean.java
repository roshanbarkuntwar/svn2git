/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

/**
 *
 * @author kapil.gupta
 */
public class TokenStatusBean {

    private String slno;
    private String process_seqno;
    private String process_insert_mode;
    private String process_start_timestamp;
    private String process_end_timestamp;
    private String process_remark;
    private String process_status;
    private String record_insert_flag;
    private String template_name;
    private String template_code;

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }
    public String getRecord_insert_flag() {
        return record_insert_flag;
    }

    public void setRecord_insert_flag(String record_insert_flag) {
        this.record_insert_flag = record_insert_flag;
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

    public String getProcess_insert_mode() {
        return process_insert_mode;
    }

    public void setProcess_insert_mode(String process_insert_mode) {
        this.process_insert_mode = process_insert_mode;
    }

    public String getProcess_remark() {
        return process_remark;
    }

    public void setProcess_remark(String process_remark) {
        this.process_remark = process_remark;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

}
