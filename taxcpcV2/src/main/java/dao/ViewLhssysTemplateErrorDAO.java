/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface ViewLhssysTemplateErrorDAO extends GenericDAO<hibernateObjects.ViewLhssysTemplateError, Serializable> {

    public int getDestinctErrorRow(String entity_code, String client_code, String acc_year, String QuarterNo, String tds_type_code, String template_code, String l_client_loginid_seq);

    public List<Object[]> getErrorData(String entity_code, String client_code, String acc_year, String QuarterNo, String tds_type_code, String template_code, String client_loginid_seq);

}//end class
