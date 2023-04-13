/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.DeducteeMast15gh;
import java.io.Serializable;

/**
 *
 * @author aniket.naik
 */
public interface DeducteeMast15ghDAO extends GenericDAO<hibernateObjects.DeducteeMast15gh, Serializable> {

    public DeducteeMast15gh readById(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, Long rowid_seq);

    public DeducteeMast15gh validateDeducteePanAvailbility(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String deductee_name, String PanCode);

    public DeducteeMast15gh validateDeducteeForPANNOTAVBL(String entity_code, String client_code, String acc_year, String quarterNo, String tdsTypeCode, String deductee_name, String panno);

    Long saveAndReturnDeducteeCode(DeducteeMast15gh deducteemast);
}
