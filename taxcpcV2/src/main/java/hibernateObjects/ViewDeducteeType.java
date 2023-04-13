/*
 * To change this template, choose Tools | Templates
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
 * @author ayushi.jain
 */
@Entity
@Table(name = "view_deductee_type")
public class ViewDeducteeType implements java.io.Serializable {

    @Column(name = "deductee_type_code", length = 1, nullable = true)
    @Id
    private String deductee_type_code;
    @Column(name = "deductee_type_name", length = 26, nullable = true)
    private String deductee_type_name;
    @Column(name = "pan_card_type", length = 1, nullable = true)
    private String pan_card_type;

    public String getDeductee_type_code() {
        return deductee_type_code;
    }

    public void setDeductee_type_code(String deductee_type_code) {
        this.deductee_type_code = deductee_type_code;
    }

    public String getDeductee_type_name() {
        return deductee_type_name;
    }

    public void setDeductee_type_name(String deductee_type_name) {
        this.deductee_type_name = deductee_type_name;
    }

    public String getPan_card_type() {
        return pan_card_type;
    }

    public void setPan_card_type(String pan_card_type) {
        this.pan_card_type = pan_card_type;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
