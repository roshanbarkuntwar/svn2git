/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.ViewCorrType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sandeep.bowade
 */
public interface ViewCorrTypeDAO extends GenericDAO<ViewCorrType, Serializable> {

    public List<ViewCorrType> getObjList();

}//End 
