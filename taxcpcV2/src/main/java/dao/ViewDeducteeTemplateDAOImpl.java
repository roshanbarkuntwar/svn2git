/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import static dao.generic.HibernateUtil.getSessionFactory;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class ViewDeducteeTemplateDAOImpl extends GenericHibernateDAO<ViewClientTemplate, Serializable> implements ViewDeducteeTemplateDAO {

    @Override
    public List<ViewClientTemplate> getAllTemplatesAsKeyCode(ViewClientMast viewClientMast, String key_code, String moduleType) {
        List<ViewClientTemplate> TemplateList;
        try {
            Criteria criteria = getSession().createCriteria(ViewClientTemplate.class);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.eq("client_code", viewClientMast.getClient_code()).ignoreCase());
            disjunction.add(Restrictions.isNull("client_code"));

            criteria.add(disjunction);
            criteria.add(Restrictions.sqlRestriction("instr(key_code,'" + key_code + "') <> 0"));
            criteria.add(Restrictions.isNotNull("read_column_dtl"));
            if (moduleType != null && !moduleType.isEmpty()) {
                criteria.add(Restrictions.eq("module_type", moduleType));
            }
            criteria.add(Restrictions.disjunction()
                    .add(Restrictions.sqlRestriction("(6=" + viewClientMast.getCode_level() + " and this_.template_code<>'CLIENT_MAST') OR\n"
                            + "             (6<>" + viewClientMast.getCode_level() + " and 1=1)")));
            criteria.addOrder(Order.asc("template_seq"));
            TemplateList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            TemplateList = null;
            getSession().getTransaction().rollback();
        }

        return (TemplateList != null && TemplateList.size() > 0 ? TemplateList : null);
    }//End Method


    
    @Override
    public List<ViewClientTemplate> getAllTemplatesAsKeyCodeForDeletePros(ViewClientMast viewClientMast, String key_code, String moduleType) {
        List<ViewClientTemplate> TemplateList;
        try {
            Criteria criteria = getSession().createCriteria(ViewClientTemplate.class);

//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.eq("client_code", viewClientMast.getClient_code()).ignoreCase());
//            disjunction.add(Restrictions.isNull("client_code"));
//
//           criteria.add(disjunction);
            criteria.add(Restrictions.sqlRestriction("view_name ='Y'"));
            criteria.add(Restrictions.sqlRestriction("instr(key_code,'" + key_code + "') <> 0"));
            //criteria.add(Restrictions.isNotNull("read_column_dtl"));
//            if (moduleType != null && !moduleType.isEmpty()) {
//                criteria.add(Restrictions.eq("module_type", moduleType));
//            }
            criteria.add(Restrictions.disjunction()
                    .add(Restrictions.sqlRestriction("(6=" + viewClientMast.getCode_level() + " and this_.template_code<>'CLIENT_MAST') OR\n"
                            + "             (6<>" + viewClientMast.getCode_level() + " and 1=1)")));
            criteria.addOrder(Order.asc("template_seq"));
            TemplateList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            TemplateList = null;
            getSession().getTransaction().rollback();
        }

        return (TemplateList != null && TemplateList.size() > 0 ? TemplateList : null);
    }//End Method

    
    @Override
    public ViewClientTemplate getDataAsTempleteCode(String templete_code) {
        Criterion criterion = Restrictions.eq("template_code", templete_code);
        List<ViewClientTemplate> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }//End Method

    public List<ViewClientTemplate> getAllTemplatesAsKeyCodeNew(){
     List<ViewClientTemplate> TemplateList = null;
     Session ssn = getSessionFactory().openSession();
    try{
        String strQuery = " select * from view_client_template c where c.view_name ='Y'";
    PreparedStatement pstmt = ssn.connection().prepareStatement(strQuery);
            ResultSet resultSet = pstmt.executeQuery(strQuery);
    }catch(Exception e){
        e.printStackTrace();
    }
     
     return TemplateList;
    }
    
}//End Class
