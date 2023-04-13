/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "view_pan_status")
public class ViewPanStatus implements Serializable {

    @Id
    @Column(name = "status_code")
    private String status_code;
    @Column(name = "status_name")
    private String status_name;
    @Column(name = "status_code_15g")
    private String status_code_15g;
    @Column(name = "pan_card_type")
    private String pan_card_type;

    public ViewPanStatus() {
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getPan_card_type() {
        return pan_card_type;
    }

    public void setPan_card_type(String pan_card_type) {
        this.pan_card_type = pan_card_type;
    }

    public String getStatus_code_15g() {
        return status_code_15g;
    }

    public void setStatus_code_15g(String status_code_15g) {
        this.status_code_15g = status_code_15g;
    }

    @Override
    public String toString() {
        return new globalUtilities.Util().printObjectAsString(this);
    }

}
