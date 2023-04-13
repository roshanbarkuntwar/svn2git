/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.LhssysTdsReturnTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class LhssysTdsReturnTranDAOImpl extends GenericHibernateDAO<LhssysTdsReturnTran, Serializable> implements LhssysTdsReturnTranDAO {

    @Override
    public LhssysTdsReturnTran readByRowIdSeq(Long rowIdSeq) {
        System.out.println("rowid_seq-"+rowIdSeq);
        Criterion criteria = Restrictions.eq("rowid_seq", rowIdSeq);
        List<LhssysTdsReturnTran> tranlist = readByCriteria(criteria);
        return (tranlist != null && tranlist.size() > 0) ? tranlist.get(0) : null;
    }

   
}
