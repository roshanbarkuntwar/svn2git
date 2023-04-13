/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author akash.dev
 */
public interface LhssysTemplateDAO extends GenericDAO<hibernateObjects.LhssysTemplate, Serializable> {

    Session getHibernateSession();

//    Long getTempDataCount();

//    List<LhssysTemplate> getReadAllData();

    public boolean getTempDetailDelete(String l_query, Util utl);

    public ArrayList<ArrayList<String>> getTempDataErrorDetail(String l_query, Util utl);

//    public int getTotalRecordCount(String l_query, Util utl);

    public Long getTempDataCount(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String client_loginid_seq, String template_code);

    public Long getTempDataCountAsErrorOrAllData(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String template_code, String errorType, String client_loginid_seq, Util utl);
}//end class
