/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysAlertDirectEmailPara;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysAlertDirectEmailParaDAOImpl extends GenericHibernateDAO<LhssysAlertDirectEmailPara, Serializable> implements LhssysAlertDirectEmailParaDAO {

    @Override
    public List<LhssysAlertDirectEmailPara> getListDataAsSeqId(Long seq_id, Util utl) {
        List<LhssysAlertDirectEmailPara> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmailPara.class);
            crit.add(Restrictions.eq("seq_id", seq_id));
            crit.addOrder(Order.asc("slno"));
            readByCriteria = crit.list();
//            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
//            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

    @Override
    public LhssysAlertDirectEmailPara getListDataAsSeqIdAndSlno(Long seq_id, Util utl, String slno) {
        List<LhssysAlertDirectEmailPara> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmailPara.class);
            crit.add(Restrictions.eq("seq_id", seq_id));
            crit.add(Restrictions.eq("slno", slno));
            readByCriteria = crit.list();
//            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
//            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null);
    }//end method

}//end class
