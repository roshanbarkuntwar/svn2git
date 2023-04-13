/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewDeducteeCatg;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class ViewDeducteeCatgDAOImpl extends GenericHibernateDAO<ViewDeducteeCatg, Serializable> implements ViewDeducteeCatgDAO {

    @Override
    public LinkedHashMap<String, String> getDeducteeCatgAsTdsType(String tds_type_code) {
        LinkedHashMap<String, String> deducteeCatgMap = new LinkedHashMap<String, String>();
        List<ViewDeducteeCatg> deducteeCatgList;

        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeCatg.class);
            crit.add(Restrictions.sqlRestriction("instr(tds_type_str,'" + tds_type_code + "') <> 0"));
            crit.addOrder(Order.asc("deductee_catg_name"));
            deducteeCatgList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            deducteeCatgList = null;
            getSession().getTransaction().rollback();
        }

        deducteeCatgList = deducteeCatgList != null && deducteeCatgList.size() > 0 ? deducteeCatgList : null;
        if (deducteeCatgList != null) {
            for (ViewDeducteeCatg deducteecatglist : deducteeCatgList) {
                deducteeCatgMap.put(deducteecatglist.getDeductee_catg_code(), deducteecatglist.getDeductee_catg_name());
            }
        }
        return deducteeCatgMap;
    }
}
