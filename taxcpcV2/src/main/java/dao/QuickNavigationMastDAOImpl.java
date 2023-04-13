/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.QuickNavigationMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class QuickNavigationMastDAOImpl extends GenericHibernateDAO<hibernateObjects.QuickNavigationMast, Serializable> implements QuickNavigationMastDAO {

    @Override
//    public List<QuickNavigationMast> getQuickNavigationListByEntityCode(String entityCode, String module) {
    public List<QuickNavigationMast> getQuickNavigationListByEntityCode(String entityCode, String module, int startIndex) {
        List<QuickNavigationMast> quickNavigationMastList = null;

        try {
            Criteria criteria = getSession().createCriteria(QuickNavigationMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entityCode));
            criteria.add(disjunction);
            criteria.add(Restrictions.eq("module_type", module));
            criteria.add(Restrictions.eq("active_flag", "A"));
            criteria.addOrder(Order.asc("order_by"));

            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(16);

            quickNavigationMastList = criteria.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            quickNavigationMastList = null;
        }
        return (quickNavigationMastList != null && quickNavigationMastList.size() > 0 ? quickNavigationMastList : null);
    }

    @Override
    public int getQuickNavigationMenuCount(String entityCode, String module) {
        Long quickNavigationCount = 0l;
        try {
            Criteria criteria = getSession().createCriteria(QuickNavigationMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entityCode));
            criteria.add(disjunction);
            criteria.add(Restrictions.eq("module_type", module));
            criteria.add(Restrictions.eq("active_flag", "A"));

            criteria.setProjection(Projections.rowCount());

            quickNavigationCount = (Long) criteria.uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            quickNavigationCount = 0l;
        }
        return quickNavigationCount.intValue();
    }

    @Override
    public List<QuickNavigationMast> getQuickNavigationList() {
        List<QuickNavigationMast> quickNavigationMastList = null;

        try {
            Criteria criteria = getSession().createCriteria(QuickNavigationMast.class);
           
            criteria.addOrder(Order.asc("order_by"));
            quickNavigationMastList = criteria.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            quickNavigationMastList = null;
        }
        return (quickNavigationMastList != null && quickNavigationMastList.size() > 0 ? quickNavigationMastList : null);
    }

}
