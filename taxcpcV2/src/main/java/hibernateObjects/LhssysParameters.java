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
 * @author ayushi.jain
 */
@Entity
@Table(name = "lhssys_parameters")
public class LhssysParameters implements Serializable {

    @Column(name = "parameter_name", length = 30, nullable = false)
    private String parameter_name;
    @Id
    @Column(name = "parameter_value", length = 200, nullable = true)
    private String parameter_value;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "session_flag", length = 1, nullable = true)
    private String session_flag;
    @Column(name = "remark", length = 200, nullable = true)
    private String remark;
    
    @Column(name = "parameter_type", length = 8, nullable = true)
    private String parameter_type;
    
    @Column(name = "rowid",nullable = false)
    private String rowid;

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public String getParameter_value() {
        return parameter_value;
    }

    public void setParameter_value(String parameter_value) {
        this.parameter_value = parameter_value;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getSession_flag() {
        return session_flag;
    }

    public void setSession_flag(String session_flag) {
        this.session_flag = session_flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParameter_type() {
        return parameter_type;
    }

    public void setParameter_type(String parameter_type) {
        this.parameter_type = parameter_type;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }
    
    

    @Override
    public String toString() {
        return "LhssysParameters{" + "parameter_name=" + parameter_name + ", parameter_value=" + parameter_value + ", entity_code=" + entity_code + ", session_flag=" + session_flag + ", remark=" + remark + ", parameter_type=" + parameter_type + '}';
    }

    
}//end class
