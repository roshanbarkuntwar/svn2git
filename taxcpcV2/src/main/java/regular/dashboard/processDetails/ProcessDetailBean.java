/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.processDetails;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessDetailBean {

    public String process_seqno;
    public String template_code;
    public String bank_branch_code;
    public String record_count;
    public String slno;
    public String user_code;
    public String template_name;
    public String file_no_of_record;
    public String process_start_timestamp;
    public String process_end_timestamp;
    public String import_filename;
    public String process_remark;
    public String record_insert_flag;
    public String process_status;

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }
    

   

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getProcess_end_timestamp() {
        return process_end_timestamp;
    }

    public void setProcess_end_timestamp(String process_end_timestamp) {
        this.process_end_timestamp = process_end_timestamp;
    }

  
    public String getRecord_count() {
        return record_count;
    }

    public void setRecord_count(String record_count) {
        this.record_count = record_count;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getFile_no_of_record() {
        return file_no_of_record;
    }

    public void setFile_no_of_record(String file_no_of_record) {
        this.file_no_of_record = file_no_of_record;
    }

    public String getProcess_start_timestamp() {
        return process_start_timestamp;
    }

    public void setProcess_start_timestamp(String process_start_timestamp) {
        this.process_start_timestamp = process_start_timestamp;
    }

    public String getImport_filename() {
        return import_filename;
    }

    public void setImport_filename(String import_filename) {
        this.import_filename = import_filename;
    }

    public String getProcess_remark() {
        return process_remark;
    }

    public void setProcess_remark(String process_remark) {
        this.process_remark = process_remark;
    }

    public String getRecord_insert_flag() {
        return record_insert_flag;
    }

    public void setRecord_insert_flag(String record_insert_flag) {
        this.record_insert_flag = record_insert_flag;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }
    
    
}
