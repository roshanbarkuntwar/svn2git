/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import form15GH.transaction.PinCodeFilterData;
import globalUtilities.Util;
import hibernateObjects.PinCodeMast;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author seema.mourya
 */
public interface PinCodeMastDAO extends GenericDAO<PinCodeMast,  Serializable>{
    
    //   public List<PinCodeMast> readCityAndStateByPincode(String value);
    public PinCodeMast readCityAndStateByPincode(String value);

    Long getPinCodeCount(PinCodeFilterData panFilterSrch, String search, Util utl);

    List<PinCodeMast> readAllPinCodeData(PinCodeFilterData panFilterSrch, String search, int minVal, int maxVal, Util utl);

    PinCodeMast readPinCodeById(String PinCode);

    public List<String> readCityNameAutocomplete(String term);

    public List<String> readDistrictNameAutocomplete(String term);

    public List<String> readStateNameAutocomplete(String term);
}
