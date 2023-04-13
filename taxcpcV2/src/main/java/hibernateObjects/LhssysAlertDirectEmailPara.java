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
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "lhssys_alert_direct_email_para")
public class LhssysAlertDirectEmailPara implements java.io.Serializable {

    @Id
    @Column(name = "seq_id", nullable = false)
    private Long seq_id;
    @Id
    @Column(name = "slno", nullable = false)
    private String slno;
    @Column(name = "para_desc", length = 50, nullable = false)
    private String para_desc;
    @Column(name = "para_column", length = 30, nullable = false)
    private String para_column;
    @Column(name = "column_size", nullable = false)
    private String column_size;
    @Column(name = "column_slno", nullable = false)
    private String column_slno;
    @Column(name = "data_type", length = 10, nullable = false)
    private String data_type;
    @Column(name = "para_default_value", length = 50, nullable = true)
    private String para_default_value;
    @Column(name = "nullable", length = 1, nullable = false)
    private String nullable;
    @Column(name = "status", length = 1, nullable = false)
    private String status;
    @Column(name = "entry_by_user", length = 1, nullable = false)
    private String entry_by_user;
    @Column(name = "item_help_property", length = 1, nullable = false)
    private String item_help_property;
    @Column(name = "ref_lov_table_col", length = 250, nullable = true)
    private String ref_lov_table_col;
    @Column(name = "ref_lov_where_clause", length = 4000, nullable = true)
    private String ref_lov_where_clause;
    @Column(name = "column_select_list_value", length = 500, nullable = true)
    private String column_select_list_value;
    @Column(name = "portlet_id", length = 10, nullable = true)
    private String portlet_id;
    @Column(name = "order_clause", length = 500, nullable = true)
    private String order_clause;
    @Column(name = "rep_para_name", length = 100, nullable = true)
    private String rep_para_name;
    @Column(name = "dependent_row", length = 100, nullable = true)
    private String dependent_row;
    @Column(name = "column_desc", length = 75, nullable = true)
    private String column_desc;
    @Column(name = "column_value", length = 4000, nullable = true)
    private String column_value;
    @Column(name = "sql_text", length = 4000, nullable = true)
    private String sql_text;
    @Column(name = "column_count", nullable = true)
    private String column_count;

    public LhssysAlertDirectEmailPara() {

    }//end constructor

    public LhssysAlertDirectEmailPara(Long seq_id, String slno, String para_desc, String para_column, String column_size, String column_slno, String data_type, String para_default_value, String nullable, String status, String entry_by_user, String item_help_property, String ref_lov_table_col, String ref_lov_where_clause, String column_select_list_value, String portlet_id, String order_clause, String rep_para_name, String dependent_row, String column_desc, String column_value, String sql_text, String column_count) {
        this.seq_id = seq_id;
        this.slno = slno;
        this.para_desc = para_desc;
        this.para_column = para_column;
        this.column_size = column_size;
        this.column_slno = column_slno;
        this.data_type = data_type;
        this.para_default_value = para_default_value;
        this.nullable = nullable;
        this.status = status;
        this.entry_by_user = entry_by_user;
        this.item_help_property = item_help_property;
        this.ref_lov_table_col = ref_lov_table_col;
        this.ref_lov_where_clause = ref_lov_where_clause;
        this.column_select_list_value = column_select_list_value;
        this.portlet_id = portlet_id;
        this.order_clause = order_clause;
        this.rep_para_name = rep_para_name;
        this.dependent_row = dependent_row;
        this.column_desc = column_desc;
        this.column_value = column_value;
        this.sql_text = sql_text;
        this.column_count = column_count;
    }//end constructor

    public Long getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(Long seq_id) {
        this.seq_id = seq_id;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getPara_desc() {
        return para_desc;
    }

    public void setPara_desc(String para_desc) {
        this.para_desc = para_desc;
    }

    public String getPara_column() {
        return para_column;
    }

    public void setPara_column(String para_column) {
        this.para_column = para_column;
    }

    public String getColumn_size() {
        return column_size;
    }

    public void setColumn_size(String column_size) {
        this.column_size = column_size;
    }

    public String getColumn_slno() {
        return column_slno;
    }

    public void setColumn_slno(String column_slno) {
        this.column_slno = column_slno;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getPara_default_value() {
        return para_default_value;
    }

    public void setPara_default_value(String para_default_value) {
        this.para_default_value = para_default_value;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntry_by_user() {
        return entry_by_user;
    }

    public void setEntry_by_user(String entry_by_user) {
        this.entry_by_user = entry_by_user;
    }

    public String getItem_help_property() {
        return item_help_property;
    }

    public void setItem_help_property(String item_help_property) {
        this.item_help_property = item_help_property;
    }

    public String getRef_lov_table_col() {
        return ref_lov_table_col;
    }

    public void setRef_lov_table_col(String ref_lov_table_col) {
        this.ref_lov_table_col = ref_lov_table_col;
    }

    public String getRef_lov_where_clause() {
        return ref_lov_where_clause;
    }

    public void setRef_lov_where_clause(String ref_lov_where_clause) {
        this.ref_lov_where_clause = ref_lov_where_clause;
    }

    public String getColumn_select_list_value() {
        return column_select_list_value;
    }

    public void setColumn_select_list_value(String column_select_list_value) {
        this.column_select_list_value = column_select_list_value;
    }

    public String getPortlet_id() {
        return portlet_id;
    }

    public void setPortlet_id(String portlet_id) {
        this.portlet_id = portlet_id;
    }

    public String getOrder_clause() {
        return order_clause;
    }

    public void setOrder_clause(String order_clause) {
        this.order_clause = order_clause;
    }

    public String getRep_para_name() {
        return rep_para_name;
    }

    public void setRep_para_name(String rep_para_name) {
        this.rep_para_name = rep_para_name;
    }

    public String getDependent_row() {
        return dependent_row;
    }

    public void setDependent_row(String dependent_row) {
        this.dependent_row = dependent_row;
    }

    public String getColumn_desc() {
        return column_desc;
    }

    public void setColumn_desc(String column_desc) {
        this.column_desc = column_desc;
    }

    public String getColumn_value() {
        return column_value;
    }

    public void setColumn_value(String column_value) {
        this.column_value = column_value;
    }

    public String getSql_text() {
        return sql_text;
    }

    public void setSql_text(String sql_text) {
        this.sql_text = sql_text;
    }

    public String getColumn_count() {
        return column_count;
    }

    public void setColumn_count(String column_count) {
        this.column_count = column_count;
    }

}//end class
