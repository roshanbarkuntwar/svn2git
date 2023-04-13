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
 * @author akash.dev
 */
@Entity
@Table(name = "lhssys_engine_cols")
public class LhssysEngineCols implements java.io.Serializable {

    @Id
    @Column(name = "engine_name", length = 15, nullable = false)
    private String engine_name;
    @Column(name = "datatype", length = 1, nullable = true)
    private String datatype;
    @Id
    @Column(name = "disp_column", length = 100, nullable = false)
    private String disp_column;
    @Column(name = "value_column", length = 500, nullable = true)
    private String value_column;
    @Column(name = "column_size", nullable = true)
    private String column_size;
    @Column(name = "grp1_seq", nullable = true)
    private String grp1_seq;
    @Column(name = "grp2_seq", nullable = true)
    private String grp2_seq;
    @Column(name = "grp3_seq", nullable = true)
    private String grp3_seq;
    @Id
    @Column(name = "blockname", length = 20, nullable = false)
    private String blockname;
    @Column(name = "filter_column", length = 30, nullable = true)
    private String filter_column;
    @Column(name = "filter_list_other_columns", length = 250, nullable = true)
    private String filter_list_other_columns;
    @Column(name = "filter_prompt_str", length = 100, nullable = true)
    private String filter_prompt_str;
    @Column(name = "filter_table", length = 30, nullable = true)
    private String filter_table;
    @Column(name = "filter_column_list_seq", length = 2, nullable = true)
    private String filter_column_list_seq;
    @Column(name = "parent_disp_column", length = 50, nullable = true)
    private String parent_disp_column;
    @Column(name = "filter_table_column", length = 30, nullable = true)
    private String filter_table_column;

    public LhssysEngineCols() {

    }

    public LhssysEngineCols(String engine_name, String datatype, String disp_column, String value_column, String column_size, String grp1_seq, String grp2_seq, String grp3_seq, String blockname, String filter_column, String filter_list_other_columns, String filter_prompt_str, String filter_table, String filter_column_list_seq, String parent_disp_column, String filter_table_column) {
        this.engine_name = engine_name;
        this.datatype = datatype;
        this.disp_column = disp_column;
        this.value_column = value_column;
        this.column_size = column_size;
        this.grp1_seq = grp1_seq;
        this.grp2_seq = grp2_seq;
        this.grp3_seq = grp3_seq;
        this.blockname = blockname;
        this.filter_column = filter_column;
        this.filter_list_other_columns = filter_list_other_columns;
        this.filter_prompt_str = filter_prompt_str;
        this.filter_table = filter_table;
        this.filter_column_list_seq = filter_column_list_seq;
        this.parent_disp_column = parent_disp_column;
        this.filter_table_column = filter_table_column;
    }

    public String getEngine_name() {
        return engine_name;
    }

    public void setEngine_name(String engine_name) {
        this.engine_name = engine_name;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDisp_column() {
        return disp_column;
    }

    public void setDisp_column(String disp_column) {
        this.disp_column = disp_column;
    }

    public String getValue_column() {
        return value_column;
    }

    public void setValue_column(String value_column) {
        this.value_column = value_column;
    }

    public String getColumn_size() {
        return column_size;
    }

    public void setColumn_size(String column_size) {
        this.column_size = column_size;
    }

    public String getGrp1_seq() {
        return grp1_seq;
    }

    public void setGrp1_seq(String grp1_seq) {
        this.grp1_seq = grp1_seq;
    }

    public String getGrp2_seq() {
        return grp2_seq;
    }

    public void setGrp2_seq(String grp2_seq) {
        this.grp2_seq = grp2_seq;
    }

    public String getGrp3_seq() {
        return grp3_seq;
    }

    public void setGrp3_seq(String grp3_seq) {
        this.grp3_seq = grp3_seq;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getFilter_column() {
        return filter_column;
    }

    public void setFilter_column(String filter_column) {
        this.filter_column = filter_column;
    }

    public String getFilter_list_other_columns() {
        return filter_list_other_columns;
    }

    public void setFilter_list_other_columns(String filter_list_other_columns) {
        this.filter_list_other_columns = filter_list_other_columns;
    }

    public String getFilter_prompt_str() {
        return filter_prompt_str;
    }

    public void setFilter_prompt_str(String filter_prompt_str) {
        this.filter_prompt_str = filter_prompt_str;
    }

    public String getFilter_table() {
        return filter_table;
    }

    public void setFilter_table(String filter_table) {
        this.filter_table = filter_table;
    }

    public String getFilter_column_list_seq() {
        return filter_column_list_seq;
    }

    public void setFilter_column_list_seq(String filter_column_list_seq) {
        this.filter_column_list_seq = filter_column_list_seq;
    }

    public String getParent_disp_column() {
        return parent_disp_column;
    }

    public void setParent_disp_column(String parent_disp_column) {
        this.parent_disp_column = parent_disp_column;
    }

    public String getFilter_table_column() {
        return filter_table_column;
    }

    public void setFilter_table_column(String filter_table_column) {
        this.filter_table_column = filter_table_column;
    }

}//end class
