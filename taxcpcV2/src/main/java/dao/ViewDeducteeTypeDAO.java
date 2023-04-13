/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewDeducteeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ViewDeducteeTypeDAO extends GenericDAO<hibernateObjects.ViewDeducteeType, Serializable> {

    ArrayList<ArrayList<String>> getDeducteeCatgAsLinkedPanno(String panno_letter);

    HashMap<String, String> getDeducteeTypeAsHashMap();

    ArrayList<ArrayList<String>> getDeducteeTypeAsArrayList();

    List<ViewDeducteeType> getDeducteeTypeList(String panno_letter, Util utl);

    List<ViewDeducteeType> getAllDeducteeTypeList();

    LinkedHashMap<String, String> getDeducteeTypeAsLinkedHashMap();

    List<ViewDeducteeType> DeducteeTypeListAsPanNO(String panno_letter, Util utl);

    LinkedHashMap<String, String> getAllDeducteeTypeListForTdsRateMaster();
}
