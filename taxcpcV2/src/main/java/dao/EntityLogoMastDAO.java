/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.EntityLogoMast;
import java.io.Serializable;

/**
 *
 * @author dinesh.satpute
 */
public interface EntityLogoMastDAO extends GenericDAO<hibernateObjects.EntityLogoMast, Serializable>{
    
    public EntityLogoMast readEntityLogoByEntityCode(String entity_name);
    
}
