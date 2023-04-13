/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.LhssysClientLoginTran;
import java.io.Serializable;

/**
 *
 * @author akash.dev
 */
public interface LhssysClientLoginTranDAO extends GenericDAO<LhssysClientLoginTran, Serializable> {

    LhssysClientLoginTran readClientLoginTranBySessionId(Long sessionId);
//    public Long getLhssysClientLoginTranCount(LhssysClientLoginTranFilter lhssysClientLoginTranFilterSearch, String search, Util utl);
//    List<LhssysClientLoginTran> readClientLoginTranListByLoginTime(LhssysClientLoginTranFilter lhssysClientLoginTranFilterSearch, String search, int minVal, int maxVal, Util utl);
}
