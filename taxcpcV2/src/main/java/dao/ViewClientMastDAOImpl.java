/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ViewClientMastDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewClientMast, Serializable> implements ViewClientMastDAO {

    @Override
    public ViewClientMast readClientByClientCode(String clientCode) {
        Criterion usrcrtry = Restrictions.eq("client_code", clientCode);
        List<ViewClientMast> uselist = readByCriteria(usrcrtry);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }

    @Override
    public LinkedHashMap<String, String> getBranchCodeAsLinkedHashMap(String clientCode, String entityCode) {
        LinkedHashMap<String, String> codeMap = new LinkedHashMap<String, String>();
        codeMap.put("", "Select");
        List<ViewClientMast> clientMast;

        try {
            Criteria crit = getSession().createCriteria(ViewClientMast.class);
            crit.add(Restrictions.eq("entity_code", entityCode));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.eq("client_code", clientCode));
            disjunction.add(Restrictions.eq("parent_code", clientCode));
            disjunction.add(Restrictions.eq("g_parent_code", clientCode));
            disjunction.add(Restrictions.eq("sg_parent_code", clientCode));
            disjunction.add(Restrictions.eq("ssg_parent_code", clientCode));
            disjunction.add(Restrictions.eq("sssg_parent_code", clientCode));
            crit.add(disjunction);

            clientMast = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            clientMast = null;
            getSession().getTransaction().rollback();
        }

        clientMast = clientMast != null && clientMast.size() > 0 ? clientMast : null;
        if (clientMast != null) {
            for (ViewClientMast client : clientMast) {
                codeMap.put(client.getClient_code(), client.getClient_name() + "(" + client.getBank_branch_code() + ")");
            }
        }
        return codeMap;
    }

    @Override
    public Long getForm16ExpressProcessCount(String clientCode, String entity_code, Util utl, String search, String branchCode) {
        Long rowcount;

        try {
            Criteria crit = getSession().createCriteria(ViewClientMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            if (!utl.isnull(branchCode)) {
                crit.add(Restrictions.eq("entity_code", entity_code));
                disjunction.add(Restrictions.eq("client_code", branchCode));
                disjunction.add(Restrictions.eq("parent_code", branchCode));
                disjunction.add(Restrictions.eq("g_parent_code", branchCode));
                disjunction.add(Restrictions.eq("sg_parent_code", branchCode));
                disjunction.add(Restrictions.eq("ssg_parent_code", branchCode));
                disjunction.add(Restrictions.eq("sssg_parent_code", branchCode));
            } else {
                if (!utl.isnull(clientCode) && !utl.isnull(entity_code)) {
                    crit.add(Restrictions.eq("entity_code", entity_code));
                    disjunction.add(Restrictions.eq("client_code", clientCode));
                    disjunction.add(Restrictions.eq("parent_code", clientCode));
                    disjunction.add(Restrictions.eq("g_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("sg_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("ssg_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("sssg_parent_code", clientCode));

                }
            }
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
    public List<ViewClientMast> getForm16ExpressProcessList(String clientCode, String entity_code, int minVal, int maxVal, Util utl, String search, String branchCode) {
        List<ViewClientMast> readByCriteria = null;
        
        try {
            Criteria crit = getSession().createCriteria(ViewClientMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            if (!utl.isnull(branchCode)) {
                
                crit.add(Restrictions.eq("entity_code", entity_code));
                disjunction.add(Restrictions.eq("client_code", branchCode));//branch code is client code(Filter)
                disjunction.add(Restrictions.eq("parent_code", branchCode));
                disjunction.add(Restrictions.eq("g_parent_code", branchCode));
                disjunction.add(Restrictions.eq("sg_parent_code", branchCode));
                disjunction.add(Restrictions.eq("ssg_parent_code", branchCode));
                disjunction.add(Restrictions.eq("sssg_parent_code", branchCode));
            } else {
                if (!utl.isnull(clientCode) && !utl.isnull(entity_code)) {
                    
                    crit.add(Restrictions.eq("entity_code", entity_code));
                    disjunction.add(Restrictions.eq("client_code", clientCode));
                    disjunction.add(Restrictions.eq("parent_code", clientCode));
                    disjunction.add(Restrictions.eq("g_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("sg_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("ssg_parent_code", clientCode));
                    disjunction.add(Restrictions.eq("sssg_parent_code", clientCode));

                }
            }
            crit.add(disjunction);
//            crit.addOrder(Order.asc("logout_time"));
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            readByCriteria = crit.list();

            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        
        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
    }

}
