/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.PanMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class PanMastDAOImpl extends GenericHibernateDAO<hibernateObjects.PanMast, Serializable> implements PanMastDAO {

    @Override
    public PanMast readPanCodeById(String PanCode) {
        Criterion criterion = Restrictions.eq("panno", PanCode);
        List<PanMast> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public Long getPanCountAsPanCode(String pan_code) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(PanMast.class);
            crit.add(Restrictions.eq("panno", pan_code));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return rowcount;
    }//end method

    @Override
    public PanMast getRecordByPanCode(String pan_code) {
        Criterion crit = Restrictions.eq("panno", pan_code);
        List<PanMast> readByCriteria = readByCriteria(crit);
        return (readByCriteria != null && readByCriteria.size() > 0) ? readByCriteria.get(0) : null;
    }

}//end class
