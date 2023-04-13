/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.EntityMast;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author gaurav.khanzode
 */
public interface EntityMastDAO extends GenericDAO<hibernateObjects.EntityMast, Serializable> {

    public EntityMast readEntityByEntityName(String entity_name);

    public LinkedHashMap<String, String> readAllEntitiesAsHashMap();
    
    public LinkedHashMap<String, String> readAllEntitiesAsHashMapFromClientMast();
}
