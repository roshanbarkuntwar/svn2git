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
@Table(name = "view_client_catg")
public class ViewClientCatg implements java.io.Serializable {

    @Id
    @Column(name = "client_catg_code", length = 2, nullable = true)
    private String client_catg_code;
    @Column(name = "client_catg_name", length = 36, nullable = true)
    private String client_catg_name;
    @Column(name = "client_type_str", length = 1, nullable = true)
    private String client_type_str;

    public ViewClientCatg() {

    }

    public ViewClientCatg(String client_catg_code, String client_catg_name, String client_type_str) {
        this.client_catg_code = client_catg_code;
        this.client_catg_name = client_catg_name;
        this.client_type_str = client_type_str;
    }

    public String getClient_catg_code() {
        return client_catg_code;
    }

    public void setClient_catg_code(String client_catg_code) {
        this.client_catg_code = client_catg_code;
    }

    public String getClient_catg_name() {
        return client_catg_name;
    }

    public void setClient_catg_name(String client_catg_name) {
        this.client_catg_name = client_catg_name;
    }

    public String getClient_type_str() {
        return client_type_str;
    }

    public void setClient_type_str(String client_type_str) {
        this.client_type_str = client_type_str;
    }

}//end class
