/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.CountryMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface CountryMastDAO extends GenericDAO<CountryMast, Serializable> {

    HashMap<String, String> getCountryCodeAsHashMap();

//    ROshan Makhe COde starts
    Long getCountryCount(CountryMast countryFilterSearch, String search, Util utl);

    List<CountryMast> getCountryByType(CountryMast countryFilterSearch, String search, int minVal, int maxVal, Util utl);

    CountryMast getCountryById(String countryCode);

    List<CountryMast> getCountryListAsResident(String resident_status, Util utl);

    List<CountryMast> readCountryCodeAutocomplete(String term, String value, Util utl);

    List<CountryMast> readCountryNameAutocomplete(String term, String value, Util utl);
}//end class
