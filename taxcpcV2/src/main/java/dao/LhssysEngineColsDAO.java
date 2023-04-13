/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysEngineCols;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ayushi.jain
 */
public interface LhssysEngineColsDAO extends GenericDAO<hibernateObjects.LhssysEngineCols, Serializable> {

    Session getHibernateSession();

    List<LhssysEngineCols> getDataAsEngineCode(String engineCode);

    public ArrayList<ArrayList<String>> getEngineColDataAsArrayList(String l_query, Util utl);

    public ArrayList<String> getEngineColDataAsList(String l_query, Util utl);
}//end class
