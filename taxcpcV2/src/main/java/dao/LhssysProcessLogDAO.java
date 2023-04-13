/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ayushi.jain
 */
public interface LhssysProcessLogDAO extends GenericDAO<hibernateObjects.LhssysProcessLog, Serializable> {

    Long saveAndReturnTokenIdentifier(UserMast user, Assessment asmt, String processType, Long loginIdSeqNo);

    Map<String, String> processSequenceNumbersList(String entity_code, Assessment asmt, String processType);

    public LhssysProcessLog readProcessbySeqNo(Long process_seq_no);
    
    public LhssysProcessLog readPRocessLogByProcessSeqNoAndParent(Long process_seq_no ,Long parent_process_seq_no);
    
    public List<LhssysProcessLog> getLhssysProcessLogbyParentProcessSeqno(Long parent_process_seqno);
}
