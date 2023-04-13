/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aniket.naik
 */
@Embeddable
public class ClientMastId implements java.io.Serializable {

    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;

    @Override
    public boolean equals(Object o) {
        boolean state = true;
        if (o == null) {
            state = false;
        } else if (!(o instanceof ClientMastId)) {
            state = false;
        } else {
            ClientMastId other = (ClientMastId) o;
            if (!this.client_code.equals(other.client_code)) {
                state = false;
            }
        }
        return state;
    }//end method

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (getClient_code() == null ? 0 : this.getClient_code().hashCode());

        return result;
    }

    public ClientMastId() {
    }//end constructor

    public ClientMastId(String client_code) {
//        this.entity_code = entity_code;
        this.client_code = client_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

}
