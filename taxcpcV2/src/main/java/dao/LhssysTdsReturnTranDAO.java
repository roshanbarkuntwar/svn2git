/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.LhssysTdsReturnTran;
import java.io.Serializable;

/**
 *
 * @author ayushi.jain
 */
public interface LhssysTdsReturnTranDAO extends GenericDAO<LhssysTdsReturnTran, Serializable> {
    
     LhssysTdsReturnTran readByRowIdSeq(Long rowIdSeq);

}
