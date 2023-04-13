/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author gaurav.khanzode
 */
public class MisReportConfigMastDAOImpl extends GenericHibernateDAO<MisReportConfigMast, Serializable> implements MisReportConfigMastDAO {

    @Override
    public ArrayList<String> getDistinctReportGroup(Util utl) {
        System.out.println("mis report queryyyy 1-->");
        ArrayList<String> readByCriteria = null;
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMast.class);
            crit.setProjection(Projections.distinct(Projections.property("rep_group")));
            crit.add(Restrictions.sqlRestriction("rep_app_type='V2'"));
            crit.addOrder(Order.asc("rep_group"));
            readByCriteria = (ArrayList<String>) crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

    @Override
    public List<MisReportConfigMast> getReportList(String report_group, Util utl, String entity_code,String tds_type_code) {
        List<MisReportConfigMast> readByCriteria = null;
        try {
            utl.generateLog("MIS reports tds_type_code", tds_type_code);
            utl.generateLog("MIS reports query for group", report_group);
           
            Criteria crit = getSession().createCriteria(MisReportConfigMast.class);
            crit.add(Restrictions.sqlRestriction("nvl(rep_group, '#') = nvl('" + report_group + "', '#')"));
            //crit.add(Restrictions.sqlRestriction("(entity_code_str is null or instr(ENTITY_CODE_STR, '" + entity_code.trim() + "') <> 0)"));
            crit.add(Restrictions.sqlRestriction("(instr(tds_type_code_str,'"+tds_type_code+"')<>0 or tds_type_code_str is null)"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entity_code));
            //crit.add(disjunction);
            //crit.addOrder(Order.asc("rep_desc"));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MisReportConfigMast> getReportListAsSeqId(Long seq_id, Util utl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getReportListAsSeqIdCount(Long seq_id, Util utl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MisReportConfigMast getReportAsSeqId(Long seq_id, Util utl) {
        List<MisReportConfigMast> LhssysAlertDirectEmailList = null;
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMast.class);
            crit.add(Restrictions.eq("rep_seq_id", seq_id));
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
    public MisReportConfigMast getDeducteeSalaryParams(Long seq_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValueByParamNameAndSeqId(Long seq_id, String param_name) {
        String paramValue = "";
        try {
            Criteria crit = getSession().createCriteria(MisReportConfigMast.class);
            crit.setProjection(Projections.property(param_name));
            crit.add(Restrictions.eq("rep_seq_id", seq_id));
            paramValue = crit.list().get(0) != null ? String.valueOf(crit.list().get(0)) : "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramValue;
    }//end method
    
    
    
    @Override
    public List<MisReportConfigMast> getReportListByAppType(String rep_app_type) {
        List<MisReportConfigMast> readByCriteria = null;
        try {
         
            Criteria crit = getSession().createCriteria(MisReportConfigMast.class);
            crit.add(Restrictions.eq("rep_app_type", rep_app_type));
          
            readByCriteria = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }//end method

}
