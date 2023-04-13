/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.TdsChallanTranLoad;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public interface TdsChallanTranLoadDAO extends GenericDAO<hibernateObjects.TdsChallanTranLoad, hibernateObjects.TdsChallanTranLoadId> {

    Long saveAndReturnSeqnoNO(TdsChallanTranLoad tdschallantran);

    TdsChallanTranLoad readChallanBySequenceID(Long rowid_seq);
    
    List<TdsChallanTranLoad> getFVUFileDetails(TdsChallanTranLoad challanTran);

}
