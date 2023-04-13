/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.StateMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class StateMastDAOImpl extends GenericHibernateDAO<hibernateObjects.StateMast, Serializable> implements StateMastDAO {

    @Override
    public Session getHibernateSession() {
        return getSession();
    }

    @Override
    public HashMap<String, String> getStateCodeAsHashMap() {
        List<StateMast> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (StateMast type : data) {
                forms.put(type.getState_code(), type.getState_name());
            }
        }
        return forms;
    }//end method

    @Override
    public LinkedHashMap<String, String> getStateCodeAsLinkedHashMap() {
        LinkedHashMap<String, String> stateMap = new LinkedHashMap<String, String>();
        List<StateMast> stateMastList;

        try {
            Criteria crit = getSession().createCriteria(StateMast.class);
            crit.addOrder(Order.asc("state_name"));
            stateMastList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            stateMastList = null;
            getSession().getTransaction().rollback();
        }

        stateMastList = stateMastList != null && stateMastList.size() > 0 ? stateMastList : null;
        if (stateMastList != null) {
            for (StateMast stateMast : stateMastList) {
                stateMap.put(stateMast.getState_code(), stateMast.getState_name());
            }
        }
        return stateMap;
    }

    @Override
    public StateMast readStateById(String stateCode) {
        Criterion criterion = Restrictions.eq("state_code", stateCode);
        List<StateMast> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<StateMast> getStateAsCountryCode(String country_code) {
        Criterion parntcrt = Restrictions.eq("country_code", country_code);
        return readByCriteria(parntcrt);
    }//end method

}//end class
