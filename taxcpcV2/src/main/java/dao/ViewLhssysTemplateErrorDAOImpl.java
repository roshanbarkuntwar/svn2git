/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewLhssysTemplateError;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class ViewLhssysTemplateErrorDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewLhssysTemplateError, Serializable> implements ViewLhssysTemplateErrorDAO {

    @Override
    public int getDestinctErrorRow(String entity_code, String client_code, String acc_year, String QuarterNo, String tds_type_code, String template_code, String l_client_loginid_seq) {
        int rowcount;
        try {
            Criteria crit = getSession().createCriteria(ViewLhssysTemplateError.class);
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", QuarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.add(Restrictions.eq("process_seqno", l_client_loginid_seq));

            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.countDistinct("rowid_seq"));
            crit.setProjection(projList);
            rowcount = ((Number) crit.uniqueResult()).intValue();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public List<Object[]> getErrorData(String entity_code, String client_code, String acc_year, String QuarterNo, String tds_type_code, String template_code, String client_loginid_seq) {
        List<Object[]> result = null;
        try {
            Criteria crit = getSession().createCriteria(ViewLhssysTemplateError.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.property("error_code_str"));
            projList.add(Projections.property("error_name"));
            projList.add(Projections.rowCount());
            projList.add(Projections.groupProperty("error_code_str"));
            projList.add(Projections.groupProperty("error_name"));
            crit.setProjection(projList);

            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", QuarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.add(Restrictions.eq("process_seqno", client_loginid_seq));

            result = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }//end method

}//end class
