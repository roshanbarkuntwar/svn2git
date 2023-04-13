/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dinesh.satpute
 */

@Entity
@Table(name = "Entity_Logo_Mast")
public class EntityLogoMast implements java.io.Serializable{
    
    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    
    @Column(name = "logo", nullable = true)
    private byte[] logo;

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    
    @Column(name = "lastupdate", nullable = false)
    private Date lastupdate;
    
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;    
    
    
}
