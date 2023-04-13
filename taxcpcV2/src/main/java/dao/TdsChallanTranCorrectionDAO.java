/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranCorrection;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author aniket.naik
 */
public interface TdsChallanTranCorrectionDAO extends GenericDAO<TdsChallanTranCorrection, Serializable> {

    public TdsChallanTranCorrection readChallanBySequenceID(Long rowid_seq);

    public LinkedHashMap<String, String> getChallanCorrAsListFilter(Assessment asmt, ViewClientMast clientmast, String justification_flag, Util utl);

}
