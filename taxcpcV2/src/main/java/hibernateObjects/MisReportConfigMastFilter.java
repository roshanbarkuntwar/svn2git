/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "mis_report_config_mast_filter")
public class MisReportConfigMastFilter implements Serializable {

    @Column(name = "entity_code")
    private String entity_code;
    @Id
    @Column(name = "rep_seq_id")
    private Long rep_seq_id;
    @Id
    @Column(name = "filter_slno")
    private String filter_slno;
    @Column(name = "filter_desc")
    private String filter_desc;
    @Column(name = "filter_column")
    private String filter_column;
    @Column(name = "filter_size")
    private String filter_size;
    @Column(name = "filter_data_type")
    private String filter_data_type;
    @Column(name = "filter_default_value")
    private String filter_default_value;
    @Column(name = "nullable_flag")
    private String nullable_flag;
    @Column(name = "status")
    private String status;
    @Column(name = "filter_ena_dis_flag")
    private String filter_ena_dis_flag;
    @Column(name = "filter_attribute")
    private String filter_attribute;
    @Column(name = "filter_attribute_value")
    private String filter_attribute_value;

    public MisReportConfigMastFilter() {
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public Long getRep_seq_id() {
        return rep_seq_id;
    }

    public void setRep_seq_id(Long rep_seq_id) {
        this.rep_seq_id = rep_seq_id;
    }

    public String getFilter_slno() {
        return filter_slno;
    }

    public void setFilter_slno(String filter_slno) {
        this.filter_slno = filter_slno;
    }

    public String getFilter_desc() {
        return filter_desc;
    }

    public void setFilter_desc(String filter_desc) {
        this.filter_desc = filter_desc;
    }

    public String getFilter_column() {
        return filter_column;
    }

    public void setFilter_column(String filter_column) {
        this.filter_column = filter_column;
    }

    public String getFilter_size() {
        return filter_size;
    }

    public void setFilter_size(String filter_size) {
        this.filter_size = filter_size;
    }

    public String getFilter_data_type() {
        return filter_data_type;
    }

    public void setFilter_data_type(String filter_data_type) {
        this.filter_data_type = filter_data_type;
    }

    public String getFilter_default_value() {
        return filter_default_value;
    }

    public void setFilter_default_value(String filter_default_value) {
        this.filter_default_value = filter_default_value;
    }

    public String getNullable_flag() {
        return nullable_flag;
    }

    public void setNullable_flag(String nullable_flag) {
        this.nullable_flag = nullable_flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilter_ena_dis_flag() {
        return filter_ena_dis_flag;
    }

    public void setFilter_ena_dis_flag(String filter_ena_dis_flag) {
        this.filter_ena_dis_flag = filter_ena_dis_flag;
    }

    public String getFilter_attribute() {
        return filter_attribute;
    }

    public void setFilter_attribute(String filter_attribute) {
        this.filter_attribute = filter_attribute;
    }

    public String getFilter_attribute_value() {
        return filter_attribute_value;
    }

    public void setFilter_attribute_value(String filter_attribute_value) {
        this.filter_attribute_value = filter_attribute_value;
    }

}
