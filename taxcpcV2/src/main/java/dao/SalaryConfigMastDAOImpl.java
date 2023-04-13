/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.SalaryConfigMast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class SalaryConfigMastDAOImpl extends GenericHibernateDAO<hibernateObjects.SalaryConfigMast, hibernateObjects.SalaryConfigMastId>
        implements SalaryConfigMastDAO {

    @Override
    public List<SalaryConfigMast> readSalaryConfigMastForDynamicCols(SalaryConfigMast salaryConfigMast, Date yearBeginDate, Date yearEndDate) {
        List<SalaryConfigMast> salaryConfigList = new ArrayList<SalaryConfigMast>();
        try {
            Criteria crit = getSession().createCriteria(SalaryConfigMast.class);
            crit.add(Restrictions.eq("itax_catg", salaryConfigMast.getItax_catg()));
            crit.add(Restrictions.eq("applicable_flag", salaryConfigMast.getApplicable_flag()));
            crit.add(Restrictions.le("from_date", yearBeginDate));

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("to_date"));
            disjunction.add(Restrictions.ge("to_date", yearEndDate));

            crit.add(disjunction);
            crit.addOrder(Order.asc("itax_order"));
            crit.addOrder(Order.asc("itax_catg"));
            salaryConfigList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return salaryConfigList != null && salaryConfigList.size() > 0 ? salaryConfigList : null;
    }

    @Override
    public List<SalaryConfigMast> getConfigColsForRefreshingAmount(String itaxCatg) {
        List<SalaryConfigMast> list = new ArrayList<SalaryConfigMast>();
        ArrayList afcodes = new ArrayList();
        afcodes.add("TDSCE");
        afcodes.add("TDS4Q");
        afcodes.add("TDS3Q");
        afcodes.add("TATPE");
        try {
            list = getSession().createCriteria(SalaryConfigMast.class)
                    .add(Restrictions.in("afcode", afcodes))
                    .add(Restrictions.eq("itax_catg", itaxCatg))
                    .addOrder(Order.asc("itax_order")).list();
        } catch (Exception e) {
        }
        return list;
    }
}
