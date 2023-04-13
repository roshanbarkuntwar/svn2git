/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "view_deductee_catg")
public class ViewDeducteeCatg implements java.io.Serializable {

    @Column(name = "deductee_catg_code", length = 1, nullable = false)
    @Id
    private String deductee_catg_code;
    @Column(name = "deductee_catg_name", length = 31, nullable = true)
    private String deductee_catg_name;
    @Column(name = "tds_type_str", length = 12, nullable = true)
    private String tds_type_str;

    public ViewDeducteeCatg() {
    }

    public String getDeductee_catg_code() {
        return deductee_catg_code;
    }

    public void setDeductee_catg_code(String deductee_catg_code) {
        this.deductee_catg_code = deductee_catg_code;
    }

    public String getDeductee_catg_name() {
        return deductee_catg_name;
    }

    public void setDeductee_catg_name(String deductee_catg_name) {
        this.deductee_catg_name = deductee_catg_name;
    }

    public String getTds_type_str() {
        return tds_type_str;
    }

    public void setTds_type_str(String tds_type_str) {
        this.tds_type_str = tds_type_str;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
