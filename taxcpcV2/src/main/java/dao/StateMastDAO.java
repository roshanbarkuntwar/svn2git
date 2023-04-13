/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.StateMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author akash.dev
 */
public interface StateMastDAO extends GenericDAO<hibernateObjects.StateMast, Serializable> {

    HashMap<String, String> getStateCodeAsHashMap();

    LinkedHashMap<String, String> getStateCodeAsLinkedHashMap();

    Session getHibernateSession();

    StateMast readStateById(String stateCode);

    List<StateMast> getStateAsCountryCode(String country_code);
}
