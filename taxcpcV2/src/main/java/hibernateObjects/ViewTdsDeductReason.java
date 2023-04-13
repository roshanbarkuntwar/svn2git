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
 * @author ayushi.jain
 */
@Entity
@Table(name = "view_tds_deduct_reason")
public class ViewTdsDeductReason implements java.io.Serializable {

    @Id
    @Column(name = "tds_deduct_reason", length = 1, nullable = true)
    private String tds_deduct_reason;
    @Id
    @Column(name = "tds_deduct_reason_name", length = 19, nullable = true)
    private String tds_deduct_reason_name;
    @Id
    @Column(name = "tds_deduct_type_flag", length = 1, nullable = true)
    private String tds_deduct_type_flag;
    @Id
    @Column(name = "tds_type_code_str", length = 1, nullable = true)
    private String tds_type_code_str;

    public ViewTdsDeductReason() {

    }

    public ViewTdsDeductReason(String tds_deduct_reason, String tds_deduct_reason_name, String tds_deduct_type_flag) {
        this.tds_deduct_reason = tds_deduct_reason;
        this.tds_deduct_reason_name = tds_deduct_reason_name;
        this.tds_deduct_type_flag = tds_deduct_type_flag;
        this.tds_type_code_str =tds_type_code_str;
    }

    public String getTds_deduct_reason() {
        return tds_deduct_reason;
    }

    public void setTds_deduct_reason(String tds_deduct_reason) {
        this.tds_deduct_reason = tds_deduct_reason;
    }

    public String getTds_deduct_reason_name() {
        return tds_deduct_reason_name;
    }

    public void setTds_deduct_reason_name(String tds_deduct_reason_name) {
        this.tds_deduct_reason_name = tds_deduct_reason_name;
    }

    public String getTds_deduct_type_flag() {
        return tds_deduct_type_flag;
    }

    public void setTds_deduct_type_flag(String tds_deduct_type_flag) {
        this.tds_deduct_type_flag = tds_deduct_type_flag;
    }

    public String getTds_type_code_str() {
        return tds_type_code_str;
    }

    public void setTds_type_code_str(String tds_type_code_str) {
        this.tds_type_code_str = tds_type_code_str;
    }

}//end class

