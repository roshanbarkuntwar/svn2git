/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.QuickNavigationMast;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface QuickNavigationMastDAO extends GenericDAO<hibernateObjects.QuickNavigationMast, Serializable> {

//    List<QuickNavigationMast> getQuickNavigationListByEntityCode(String entityCode, String module);
    List<QuickNavigationMast> getQuickNavigationListByEntityCode(String entityCode, String module, int startIndex);

    int getQuickNavigationMenuCount(String entityCode, String module);
    
    List<QuickNavigationMast> getQuickNavigationList();

}
