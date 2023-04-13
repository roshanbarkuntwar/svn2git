/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysAlertDirectEmailPara;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface LhssysAlertDirectEmailParaDAO extends GenericDAO<LhssysAlertDirectEmailPara, Serializable> {

    public List<LhssysAlertDirectEmailPara> getListDataAsSeqId(Long seq_id, Util utl);

    public LhssysAlertDirectEmailPara getListDataAsSeqIdAndSlno(Long seq_id, Util utl, String slno);

}//end class
