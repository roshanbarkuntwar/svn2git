/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.TdsMast;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class TdsMastDAOImpl extends GenericHibernateDAO<TdsMast, Serializable> implements TdsMastDAO {

    @Override
    public LinkedHashMap<String, String> getTdsAsLinkedHashMap(String TdsType, Date quarterToDate) {
        LinkedHashMap<String, String> tdsMap = new LinkedHashMap<String, String>();
        tdsMap.put("", "Select Section");
        List<TdsMast> tdsMastList;

        try {
            java.sql.Date sqlQuarterToDate = new java.sql.Date(quarterToDate.getTime());
            Criteria crit = getSession().createCriteria(TdsMast.class);
            crit.add(Restrictions.eq("tds_type_code", TdsType));
            crit.add(Restrictions.sqlRestriction("nvl(this_.closed_date, to_date('" + sqlQuarterToDate + "', 'rrrr-mm-dd')) >= to_date('" + sqlQuarterToDate + "', 'rrrr-mm-dd')"));
            crit.addOrder(Order.asc("tds_name"));
            tdsMastList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsMastList = null;
            getSession().getTransaction().rollback();
        }

        tdsMastList = tdsMastList != null && tdsMastList.size() > 0 ? tdsMastList : null;
        if (tdsMastList != null) {
            for (TdsMast tdsMast : tdsMastList) {
                tdsMap.put(tdsMast.getTds_code(), tdsMast.getTds_name() + "(" + tdsMast.getRemark() + ")");
            }
        }
        return tdsMap;
    }

    @Override
    public TdsMast getAsDeductReasonAsCode(String tdsCode, Util utl) {
        Criterion usrcrtry = Restrictions.eq("tds_name", tdsCode).ignoreCase();
        List<TdsMast> uselist = readByCriteria(usrcrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }//end method

    @Override
    public LinkedHashMap<String, String> getSectionAsHashMap(String tds_type_code, Date qtrToDate) {
        LinkedHashMap<String, String> forms;
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Criteria crit = getSession().createCriteria(TdsMast.class);
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            //crit.add(Restrictions.sqlRestriction("(closed_date is null or closed_date >= trim(to_date('" + sdf.format(qtrToDate) + "','dd-mm-rrrr')))"));
            crit.addOrder(Order.asc("tds_name"));
            List<TdsMast> data = crit.list();
            forms = new LinkedHashMap<String, String>();
            forms.put("", "Select TDS Section");
            if (data != null) {
                for (TdsMast type : data) {
                    forms.put(type.getTds_code(), type.getTds_name() + " - " + type.getRemark());
                }
            }
            getSession().getTransaction().commit();
        } catch (Exception e) {
            forms = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return forms;
    }

    @Override
    public LinkedHashMap<String, String> getSectionAsHashMapForDeductee(String tds_type_code, Date qtrToDate) {
        LinkedHashMap<String, String> forms;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Criteria crit = getSession().createCriteria(TdsMast.class);
            crit.add(Restrictions.eq("flag", "B"));
            crit.add(Restrictions.sqlRestriction("(closed_date is null or closed_date >= trim(to_date('" + sdf.format(qtrToDate) + "','dd-mm-rrrr')))"));
            crit.addOrder(Order.asc("tds_code"));
            List<TdsMast> data = crit.list();
            forms = new LinkedHashMap<String, String>();
            if (data != null) {
                for (TdsMast type : data) {
                    forms.put(type.getTds_name(), type.getTds_name() + " (" + type.getRemark() + ")");
                }
            }
            getSession().getTransaction().commit();
        } catch (Exception e) {
            forms = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return forms;
    }

    @Override
    public List<TdsMast> getTdsCodeList(String tds_code, String tds_type_code, String Deducteeflag, Util utl) {
        List<TdsMast> data;
        try {
            Criteria crit = getSession().createCriteria(TdsMast.class);
            if (!utl.isnull(tds_code)) {
                crit.add(Restrictions.eq("tds_code", tds_code));
            }
            if (!utl.isnull(Deducteeflag)) {
                crit.add(Restrictions.sqlRestriction("instr(deductee_catg_code_str,'" + Deducteeflag + "') <> 0"));
            }
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.addOrder(Order.asc("tds_code"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (data != null && data.size() > 0 ? data : null);
    }

    @Override
    public ArrayList<String> getAsDeductReasonAsString(String tdsCode, Util utl) {
        ArrayList<String> reasonflag;
        try {
            Criteria criteria = getSession().createCriteria(TdsMast.class);
            criteria.setProjection(Projections.property("tds_deduct_reason_flag_str"));
            criteria.add(Restrictions.eq("tds_code", tdsCode));
            reasonflag = (ArrayList<String>) criteria.list();
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            reasonflag = null;
            getSession().getTransaction().rollback();
        }
        return reasonflag;
    }//end method

}
