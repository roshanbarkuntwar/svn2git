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
 * @author seema.mourya
 */

@Entity
@Table(name = "pin_code_mast")
public class PinCodeMast implements Serializable{
    
    @Id
    @Column(name = "pin_code", length = 6, nullable = false)
    private String pin_code;
    
    @Column(name = "city", length = 100, nullable = true)
    private String city;
    
    @Column(name = "district", length = 100, nullable = true)
    private String district;
    
    @Column(name = "state_name", length = 100, nullable = true)
    private String state_name;
    
    @Column(name = "state_code", length = 2, nullable = true)
    private String state_code;

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }
    
    
    
}
