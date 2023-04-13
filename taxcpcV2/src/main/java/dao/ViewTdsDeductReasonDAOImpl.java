/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsDeductReason;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ViewTdsDeductReasonDAOImpl extends GenericHibernateDAO<ViewTdsDeductReason, Serializable> implements ViewTdsDeductReasonDAO {

    @Override
    public LinkedHashMap<String, String> getDeductReasonData(String tds_type_code) {
        LinkedHashMap<String, String> deductCode;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsDeductReason.class);
            crit.add(Restrictions.sqlRestriction("instr(tds_type_code_str,'#'||'" + tds_type_code + "'||'#')<>0"));
            crit.addOrder(Order.asc("tds_deduct_type_flag"));
            List<ViewTdsDeductReason> allCode = crit.list();
            deductCode = new LinkedHashMap<>();
            if (allCode != null) {
                for (ViewTdsDeductReason viewTdsDeductReason : allCode) {
                    deductCode.put(viewTdsDeductReason.getTds_deduct_reason(), viewTdsDeductReason.getTds_deduct_reason_name());
                }
            }
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            deductCode = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return deductCode;
    }//end method

    @Override
    public HashMap<String, String> getAllDeductReasonData() {
        HashMap<String, String> deductCode;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsDeductReason.class);
            crit.addOrder(Order.asc("tds_deduct_type_flag"));
            List<ViewTdsDeductReason> allCode = crit.list();
            deductCode = new LinkedHashMap<>();
            if (allCode != null) {
                for (ViewTdsDeductReason viewTdsDeductReason : allCode) {
                    deductCode.put(viewTdsDeductReason.getTds_deduct_reason(), viewTdsDeductReason.getTds_deduct_reason_name());
                }
            }
            getSession().getTransaction().commit();
        } catch (Exception e) {
            deductCode = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return deductCode;
    }

    @Override
    public List<ViewTdsDeductReason> getDeductReasonList(String tds_type_code, Util utl) {
        List<ViewTdsDeductReason> data;
        try {
//            System.out.println("tds_code_str..." + tds_type_code);
            Criteria crit = getSession().createCriteria(ViewTdsDeductReason.class);
//            crit.add(Restrictions.sqlRestriction("INSTR('" + tds_code_str + "', tds_deduct_type_flag) <> 0"));
            crit.add(Restrictions.sqlRestriction("instr(tds_type_code_str,'#'||'" + tds_type_code + "'||'#')<>0"));
            crit.addOrder(Order.asc("tds_deduct_type_flag"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (data != null && data.size() > 0 ? data : null);
    }//end methods
}//end class

