/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author aniket.naik
 */
public interface ViewResidentTypeDAO extends GenericDAO<hibernateObjects.ViewResidentType, Serializable> {

    public LinkedHashMap<String, String> getViewResidentList();

}
