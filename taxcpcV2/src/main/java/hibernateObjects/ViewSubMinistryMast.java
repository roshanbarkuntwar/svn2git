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
@Table(name = "view_sub_ministry_mast")
public class ViewSubMinistryMast implements java.io.Serializable {

    @Id
    @Column(name = "sub_ministry_code", length = 2, nullable = true)
    private String sub_ministry_code;//	char(2)	y
    @Column(name = "sub_ministry_name", length = 58, nullable = true)
    private String sub_ministry_name;

    public ViewSubMinistryMast() {

    }

    public ViewSubMinistryMast(String sub_ministry_code, String sub_ministry_name) {
        this.sub_ministry_code = sub_ministry_code;
        this.sub_ministry_name = sub_ministry_name;
    }

    public String getSub_ministry_code() {
        return sub_ministry_code;
    }

    public void setSub_ministry_code(String sub_ministry_code) {
        this.sub_ministry_code = sub_ministry_code;
    }

    public String getSub_ministry_name() {
        return sub_ministry_name;
    }

    public void setSub_ministry_name(String sub_ministry_name) {
        this.sub_ministry_name = sub_ministry_name;
    }

}
