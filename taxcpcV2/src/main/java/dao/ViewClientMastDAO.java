/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ViewClientMastDAO extends GenericDAO<ViewClientMast, Serializable> {

    ViewClientMast readClientByClientCode(String loginid);

    LinkedHashMap<String, String> getBranchCodeAsLinkedHashMap(String clientCode, String entityCode);

    public Long getForm16ExpressProcessCount(String clientCode, String entity_code, Util utl, String search, String branchCode);

    public List<ViewClientMast> getForm16ExpressProcessList(String clientCode, String entity_code, int minVal, int maxVal, Util utl, String search, String branchCode);

}
