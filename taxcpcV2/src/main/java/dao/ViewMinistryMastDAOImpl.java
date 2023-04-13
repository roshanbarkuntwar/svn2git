/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewMinistryMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author aniket.naik
 */
public class ViewMinistryMastDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewMinistryMast, Serializable> implements ViewMinistryMastDAO {

    @Override
    public LinkedHashMap<String, String> getMinistryCodeAsList() {
        LinkedHashMap<String, String> listData = new LinkedHashMap<String, String>();
        List<ViewMinistryMast> ministryData = null;
        try {
            Criteria crit = getSession().createCriteria(ViewMinistryMast.class);
            crit.addOrder(Order.asc("ministry_name"));
            ministryData = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
        }
        listData.put("", "select");
        if (ministryData != null) {
            for (ViewMinistryMast viewMinistryMast : ministryData) {
                listData.put(viewMinistryMast.getMinstry_code(), viewMinistryMast.getMinistry_name());
            }
        }
        return listData;
    }//end method
}
