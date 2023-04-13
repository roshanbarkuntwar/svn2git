/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.ViewDeducteeCatg;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author aniket.naik
 */
public interface ViewDeducteeCatgDAO extends GenericDAO<ViewDeducteeCatg, Serializable> {

    LinkedHashMap<String, String> getDeducteeCatgAsTdsType(String tds_type_code);

}
