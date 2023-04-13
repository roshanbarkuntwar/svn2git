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
 * @author aniket.naik
 */
@Entity
@Table(name = "view_remittance_nature")
public class ViewRemittanceNature implements java.io.Serializable {

    @Id
    @Column(name = "remittance_nature_code", length = 2, nullable = true)
    private String remittance_nature_code;
    @Column(name = "remittance_nature_name", length = 54, nullable = true)
    private String remittance_nature_name;

    public ViewRemittanceNature() {

    }

    public ViewRemittanceNature(String remittance_nature_code, String remittance_nature_name) {
        this.remittance_nature_code = remittance_nature_code;
        this.remittance_nature_name = remittance_nature_name;
    }

    public String getRemittance_nature_code() {
        return remittance_nature_code;
    }

    public void setRemittance_nature_code(String remittance_nature_code) {
        this.remittance_nature_code = remittance_nature_code;
    }

    public String getRemittance_nature_name() {
        return remittance_nature_name;
    }

    public void setRemittance_nature_name(String remittance_nature_name) {
        this.remittance_nature_name = remittance_nature_name;
    }

}
