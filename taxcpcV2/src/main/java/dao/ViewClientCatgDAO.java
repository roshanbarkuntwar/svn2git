/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientCatg;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface ViewClientCatgDAO extends GenericDAO<hibernateObjects.ViewClientCatg, Serializable> {

    List<hibernateObjects.ViewClientCatg> getClientCatgAsList(String client_type);

    ViewClientCatg getClientCatgFromCode(String catg_code);

    List<ViewClientCatg> getClientCatgList(String client_type, Util utl);

    public ViewClientCatg getClientTypeAsCatg(String deductor_type_catg, Util utl);

}//end class
