/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewSubMinistryMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author aniket.naik
 */
public class ViewSubMinistryMastDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewSubMinistryMast, Serializable> implements ViewSubMinistryMastDAO {

    @Override
    public LinkedHashMap<String, String> getSubMinistryCodeAsList() {
        LinkedHashMap<String, String> listData = new LinkedHashMap<String, String>();
        List<ViewSubMinistryMast> subministryData = null;
        try {
            Criteria crit = getSession().createCriteria(ViewSubMinistryMast.class);
            crit.addOrder(Order.asc("sub_ministry_name"));
            subministryData = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
        }
        listData.put("", "-------select-------");
        if (subministryData != null) {
            for (ViewSubMinistryMast viewSubMinistryMast : subministryData) {
                listData.put(viewSubMinistryMast.getSub_ministry_code(), viewSubMinistryMast.getSub_ministry_name());
            }
        }
        return listData;
    }

}
