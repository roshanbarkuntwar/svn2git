/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMastFilter;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author gaurav.khanzode
 */
public class MisReportConfigMastFilterDAOImpl extends GenericHibernateDAO<MisReportConfigMastFilter, Serializable> implements MisReportConfigMastFilterDAO {

    @Override
    public MisReportConfigMastFilter getListDataAsSeqIdAndSlno(Long seq_id, Util utl, String slno) {
        List<MisReportConfigMastFilter> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMastFilter.class);
            crit.add(Restrictions.eq("rep_seq_id", seq_id));
            crit.add(Restrictions.eq("filter_slno", slno));
            readByCriteria = crit.list();
//            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
//            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null);
    }//end method

    @Override
    public List<MisReportConfigMastFilter> getListDataAsSeqId(Long seq_id, Util utl) {
        List<MisReportConfigMastFilter> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMastFilter.class);
            crit.add(Restrictions.eq("rep_seq_id", seq_id));
            crit.addOrder(Order.asc("filter_slno"));
            readByCriteria = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return readByCriteria != null && !readByCriteria.isEmpty() ? readByCriteria : null;
    }//end method
    
    @Override
    public MisReportConfigMastFilter getReportAsSeqIdAndFilterColumn(Long seq_id, String filter_column, Util utl) {
        MisReportConfigMastFilter reportConfig = null;
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMastFilter.class);
            crit.add(Restrictions.eq("rep_seq_id", seq_id));
            crit.add(Restrictions.eq("filter_column", filter_column));
            reportConfig = (MisReportConfigMastFilter) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            reportConfig = null;
        }
        return (reportConfig != null) ? reportConfig : null;
    }//end method

}//end class
