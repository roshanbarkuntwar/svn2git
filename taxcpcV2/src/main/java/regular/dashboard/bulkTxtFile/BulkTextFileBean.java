/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.bulkTxtFile;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;

/**
 *
 * @author akash.meshram
 */
public class BulkTextFileBean {

    public String process_seqno;
    public String client_code;
    public String bank_branch_code;
    public Date process_start_timestamp;
    public Date process_end_timestamp;
    public String fvu_txt_file_name;

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
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

    public String getFvu_txt_file_name() {
        return fvu_txt_file_name;
    }

    public void setFvu_txt_file_name(String fvu_txt_file_name) {
        this.fvu_txt_file_name = fvu_txt_file_name;
    }

}
