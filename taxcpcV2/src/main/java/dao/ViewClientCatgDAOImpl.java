/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientCatg;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class ViewClientCatgDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewClientCatg, Serializable> implements ViewClientCatgDAO {

    @Override
    public List<ViewClientCatg> getClientCatgAsList(String client_type) {
        List<ViewClientCatg> ClientCatgList;

        try {
            Criteria crit = getSession().createCriteria(ViewClientCatg.class);
            if (!client_type.equalsIgnoreCase("PANNOTREQD")) {
                crit.add(Restrictions.eq("client_type_str", client_type));
            } else {
                crit.add(Restrictions.eq("client_type_str", client_type));
            }
            crit.addOrder(Order.asc("client_catg_name"));
            ClientCatgList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            ClientCatgList = null;
            getSession().getTransaction().rollback();
        }

        ClientCatgList = ClientCatgList != null && ClientCatgList.size() > 0 ? ClientCatgList : null;
        return ClientCatgList;
    }//end method

    @Override
    public ViewClientCatg getClientCatgFromCode(String catg_code) {
        Criterion criterion = Restrictions.eq("client_catg_code", catg_code);
        List<ViewClientCatg> readByCriteria = this.readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<ViewClientCatg> getClientCatgList(String client_type, Util utl) {
        List<ViewClientCatg> ClientCatgList;

        try {
            Criteria crit = getSession().createCriteria(ViewClientCatg.class);
            if (!utl.isnull(client_type)) {
                if (!client_type.equalsIgnoreCase("PANNOTREQD")) {
//                    crit.add(Restrictions.eq("client_type_str", client_type));
                    crit.add(Restrictions.sqlRestriction("instr('" + client_type + "',client_type_str) <> 0"));
                }
            }
            crit.addOrder(Order.asc("client_catg_name"));
            ClientCatgList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            ClientCatgList = null;
            getSession().getTransaction().rollback();
        }

        ClientCatgList = ClientCatgList != null && ClientCatgList.size() > 0 ? ClientCatgList : null;
        return ClientCatgList;
    }//end method

    @Override
    public ViewClientCatg getClientTypeAsCatg(String deductor_type_catg, Util utl) {
        Criterion pwdcrtry = Restrictions.eq("client_catg_code", deductor_type_catg);
        List<ViewClientCatg> uselist = readByCriteria(pwdcrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }//end method
}//end class
