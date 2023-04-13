/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.CityMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface CityMastDAO extends GenericDAO<CityMast, Serializable> {

    HashMap<String, String> getCityCodeAsHashMap();

    List<hibernateObjects.CityMast> getCityCodeAsStateCode(String state_code);

    Long getCityCount(CityMast cityFilterSearch, String search, Util utl);

    List<CityMast> getCityByType(CityMast cityFilterSearch, String search, int minVal, int maxVal, Util utl);

    CityMast readCityById(String cityCode);

}
