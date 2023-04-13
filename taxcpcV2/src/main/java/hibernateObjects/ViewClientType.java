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
 * @author ayushi.jain
 */
@Entity
@Table(name = "view_client_type")
public class ViewClientType implements java.io.Serializable {

    @Id
    @Column(name = "client_type_code", length = 5, nullable = true)
    private String client_type_code;
    @Column(name = "client_type_name", length = 26, nullable = true)
    private String client_type_name;
    @Column(name = "pan_card_type", length = 1, nullable = true)
    private String pan_card_type;

    public ViewClientType() {

    }

    public ViewClientType(String client_type_code, String client_type_name, String pan_card_type) {
        this.client_type_code = client_type_code;
        this.client_type_name = client_type_name;
        this.pan_card_type = pan_card_type;
    }

    public String getClient_type_code() {
        return client_type_code;
    }

    public void setClient_type_code(String client_type_code) {
        this.client_type_code = client_type_code;
    }

    public String getClient_type_name() {
        return client_type_name;
    }

    public void setClient_type_name(String client_type_name) {
        this.client_type_name = client_type_name;
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

}//end class
