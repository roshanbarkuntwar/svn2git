/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ViewClientTypeDAO extends GenericDAO<ViewClientType, Serializable> {

    HashMap<String, String> getDeductorTypeAsHashMap();

    HashMap<String, String> getClientTypeAsHashMap();

    LinkedHashMap<String, String> getDeductorTypeAsLinkedHashMap();

    ArrayList<ArrayList<String>> getDeductorTypeAsArrayList();

    ArrayList<ArrayList<String>> getDeductorCatgAsLinkedPanno(String panno);

    ViewClientType getClientTypeFromCatg(String clientTypeCode);

    public List<ViewClientType> ClientTypeListAsPanNO(String toUpperCase, Util utl);
}
