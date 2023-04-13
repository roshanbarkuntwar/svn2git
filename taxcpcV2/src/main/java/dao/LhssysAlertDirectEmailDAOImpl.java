/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysAlertDirectEmail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysAlertDirectEmailDAOImpl extends GenericHibernateDAO<LhssysAlertDirectEmail, Serializable> implements LhssysAlertDirectEmailDAO {

    @Override
    public ArrayList<String> getDistinctReportGroup(Util utl) {
        ArrayList<String> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.setProjection(Projections.distinct(Projections.property("report_group")));
            crit.addOrder(Order.asc("report_group"));
            readByCriteria = (ArrayList<String>) crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

    @Override
    public List<LhssysAlertDirectEmail> getReportList(String report_group, Util utl, String entity_code) {
        List<LhssysAlertDirectEmail> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.add(Restrictions.sqlRestriction("nvl(report_group, '#') = nvl('" + report_group + "', '#')"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entity_code));
            crit.add(disjunction);
            crit.addOrder(Order.asc("alert_desc"));
            readByCriteria = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

    @Override
    public Long getReportGroupCount(String reportGroup, Util utl, String entity_code) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.add(Restrictions.sqlRestriction("nvl(report_group, '#') = nvl('" + reportGroup + "', '#')"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entity_code));
            crit.add(disjunction);

            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public List<LhssysAlertDirectEmail> getReportListAsSeqId(Long seq_id, Util utl) {
        List<LhssysAlertDirectEmail> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.add(Restrictions.eq("seq_id", seq_id));
            crit.addOrder(Order.asc("alert_desc"));
            readByCriteria = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

    @Override
    public Long getReportListAsSeqIdCount(Long seq_id, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.add(Restrictions.eq("seq_id", seq_id));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public LhssysAlertDirectEmail getReportAsSeqId(Long seq_id, Util utl) {
        List<LhssysAlertDirectEmail> LhssysAlertDirectEmailList = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysAlertDirectEmail.class);
            crit.add(Restrictions.eq("seq_id", seq_id));
            LhssysAlertDirectEmailList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            LhssysAlertDirectEmailList = null;
        }
        return (LhssysAlertDirectEmailList != null && LhssysAlertDirectEmailList.size() > 0) ? LhssysAlertDirectEmailList.get(0) : null;
    }//end method

    @Override
    public LhssysAlertDirectEmail getDeducteeSalaryParams(Long seq_id) {
        Criterion seqIdCrit = Restrictions.eq("seq_id", seq_id);
        List<LhssysAlertDirectEmail> readByCriteria = readByCriteria(seqIdCrit);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }
    
}//end class
