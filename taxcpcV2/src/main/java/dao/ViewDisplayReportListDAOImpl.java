/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import dao.generic.HibernateUtil;
import hibernateObjects.ViewErrorProcessReports;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.meshram
 */
public class ViewDisplayReportListDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewErrorProcessReports, Serializable> implements ViewDisplayReportListDAO {

    @Override
    public List<ViewErrorProcessReports> getViewDisplayReportList(Long process_seqno) {
           List<ViewErrorProcessReports> viewDisplayReportList;
        try {
            Criteria crit = getSession().createCriteria(ViewErrorProcessReports.class);
            crit.add(Restrictions.eq("parent_process_seqno", process_seqno));
            
          
            viewDisplayReportList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            viewDisplayReportList = null;
            getSession().getTransaction().rollback();
            
        }
        return (viewDisplayReportList != null && viewDisplayReportList.size() > 0 ? viewDisplayReportList : null);
    }

    @Override
    public Long getViewDisplayReportListCount(Long process_seqno) {
       Long count = 0L;
        Session session = getSession();
        try {
            String sql = "select count(*) from view_error_process_reports v where v.parent_process_seqno="+process_seqno;
            SQLQuery query = session.createSQLQuery(sql);
            Object obj = query.uniqueResult();
            count = Long.parseLong(obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    
    }
}
