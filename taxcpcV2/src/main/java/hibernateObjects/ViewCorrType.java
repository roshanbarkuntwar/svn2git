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
 * @author sandeep.bowade
 */
@Entity
@Table(name = "view_corr_type")
public class ViewCorrType implements java.io.Serializable {

    @Id
    @Column(name = "pan_type")
    private String pan_type;
    @Column(name = "pan_type_name")
    private String pan_type_name;

    public String getPan_type() {
        return pan_type;
    }

    public void setPan_type(String pan_type) {
        this.pan_type = pan_type;
    }

    public String getPan_type_name() {
        return pan_type_name;
    }

    public void setPan_type_name(String pan_type_name) {
        this.pan_type_name = pan_type_name;
    }

}//End Class
