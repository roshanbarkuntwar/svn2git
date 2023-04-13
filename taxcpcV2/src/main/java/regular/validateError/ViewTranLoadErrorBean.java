/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

/**
 *
 * @author gaurav.khanzode
 */
public class ViewTranLoadErrorBean {

    public String quarter_no;
    public String tds_type_code;
    public String table_name;
    public String error_type_code;
    public String error_type_name;
    public String record_count;

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getError_type_code() {
        return error_type_code;
    }

    public void setError_type_code(String error_type_code) {
        this.error_type_code = error_type_code;
    }

    public String getError_type_name() {
        return error_type_name;
    }

    public void setError_type_name(String error_type_name) {
        this.error_type_name = error_type_name;
    }

    public String getRecord_count() {
        return record_count;
    }

    public void setRecord_count(String record_count) {
        this.record_count = record_count;
    }

}
