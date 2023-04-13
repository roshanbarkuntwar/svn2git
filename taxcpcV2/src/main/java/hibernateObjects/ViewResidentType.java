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
@Table(name = "view_resident_type")
public class ViewResidentType implements java.io.Serializable {

    @Id
    @Column(name = "resdent_type_code")
    private String resdent_type_code;
    @Column(name = "resdent_type_name")
    private String resdent_type_name;
    @Column(name = "bflag_code")
    private String bflag_code;

    public ViewResidentType() {

    }//end

    public ViewResidentType(String resdent_type_code, String resdent_type_name, String bflag_code) {
        this.resdent_type_code = resdent_type_code;
        this.resdent_type_name = resdent_type_name;
        this.bflag_code = bflag_code;
    }//end

    public String getResdent_type_code() {
        return resdent_type_code;
    }

    public void setResdent_type_code(String resdent_type_code) {
        this.resdent_type_code = resdent_type_code;
    }

    public String getResdent_type_name() {
        return resdent_type_name;
    }

    public void setResdent_type_name(String resdent_type_name) {
        this.resdent_type_name = resdent_type_name;
    }

    public String getBflag_code() {
        return bflag_code;
    }

    public void setBflag_code(String bflag_code) {
        this.bflag_code = bflag_code;
    }

}
