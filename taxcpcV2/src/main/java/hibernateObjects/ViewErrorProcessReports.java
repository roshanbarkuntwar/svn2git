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
 * @author akash.meshram
 */
@Entity
@Table(name = "view_error_process_reports")
public class ViewErrorProcessReports implements java.io.Serializable {

    @Id
    @Column(name = "process_seqno", nullable = false)
    private Long process_seqno;
    @Column(name = "parent_process_seqno", nullable = true)
    private Long parent_process_seqno;
    @Column(name = "report_name", nullable = true)
    private String report_name;
    @Column(name = "fvu_txt_file_name", nullable = true)
    private String fvu_txt_file_name;
    @Column(name = "process_remark", nullable = true)
    private String process_remark;

    public Long getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Long process_seqno) {
        this.process_seqno = process_seqno;
    }

    public Long getParent_process_seqno() {
        return parent_process_seqno;
    }

    public void setParent_process_seqno(Long parent_process_seqno) {
        this.parent_process_seqno = parent_process_seqno;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
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

}
