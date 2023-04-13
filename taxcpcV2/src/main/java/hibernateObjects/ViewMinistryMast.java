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
@Table(name = "view_ministry_mast")
public class ViewMinistryMast implements java.io.Serializable {

    @Id
    @Column(name = "minstry_code", length = 2, nullable = true)
    private String minstry_code;
    @Column(name = "ministry_name", length = 60, nullable = true)
    private String ministry_name;

    public ViewMinistryMast() {

    }

    public ViewMinistryMast(String minstry_code, String ministry_name) {
        this.minstry_code = minstry_code;
        this.ministry_name = ministry_name;
    }

    public String getMinstry_code() {
        return minstry_code;
    }

    public void setMinstry_code(String minstry_code) {
        this.minstry_code = minstry_code;
    }

    public String getMinistry_name() {
        return ministry_name;
    }

    public void setMinistry_name(String ministry_name) {
        this.ministry_name = ministry_name;
    }

}
