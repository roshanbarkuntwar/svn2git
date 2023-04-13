/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsDeductReason;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ViewTdsDeductReasonDAO extends GenericDAO<ViewTdsDeductReason, Serializable> {

    LinkedHashMap<String, String> getDeductReasonData(String tds_type_code);

    HashMap<String, String> getAllDeductReasonData();

    List<ViewTdsDeductReason> getDeductReasonList(String tds_code_str, Util utl);

}//end class
