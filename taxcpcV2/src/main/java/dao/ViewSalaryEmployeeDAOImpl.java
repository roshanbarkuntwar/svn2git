    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewSalaryEmployee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ViewSalaryEmployeeDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewSalaryEmployee, Serializable> implements ViewSalaryEmployeeDAO {

    @Override
    public Long getSalaryCount(ViewSalaryEmployee salaryFilterSearch, String search, ViewClientMast clientMast,Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(ViewSalaryEmployee.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (salaryFilterSearch != null) {
                    if (!utl.isnull(salaryFilterSearch.getDeductee_name())) {
                        crit.add(Restrictions.like("deductee_name", salaryFilterSearch.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                    }
                    if (!utl.isnull(salaryFilterSearch.getItax_catg())) {
                        crit.add(Restrictions.eq("itax_catg", salaryFilterSearch.getItax_catg()));
                    }
                    if (!utl.isnull(salaryFilterSearch.getPanno())) {
                        crit.add(Restrictions.eq("panno", salaryFilterSearch.getPanno()));
                    }
                }
            }
            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }

    @Override
    public List<ViewSalaryEmployee> getSalaryByType(ViewSalaryEmployee salaryFilterSearch, ViewClientMast clientMast, String search, int minVal, int maxVal, Util utl) {
        List<ViewSalaryEmployee> salaryList;
        try {
            Criteria crit = getSession().createCriteria(ViewSalaryEmployee.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("deductee_name").ignoreCase());
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (salaryFilterSearch != null) {
                    if (!utl.isnull(salaryFilterSearch.getDeductee_name())) {
                        crit.add(Restrictions.like("deductee_name", salaryFilterSearch.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                    }
                    if (!utl.isnull(salaryFilterSearch.getItax_catg())) {
                        crit.add(Restrictions.eq("itax_catg", salaryFilterSearch.getItax_catg()));
                    }
                    if (!utl.isnull(salaryFilterSearch.getPanno())) {
                        crit.add(Restrictions.eq("panno", salaryFilterSearch.getPanno()));
                    }
                }
            }
            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            salaryList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            salaryList = null;
            getSession().getTransaction().rollback();
        }
        return (salaryList != null && salaryList.size() > 0 ? salaryList : null);
    }
    
    @Override
    public List<ViewSalaryEmployee> readDeducteePanAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String deducteeName, String deducteeCatg) {
        List<ViewSalaryEmployee> deducteeMastList = new ArrayList<ViewSalaryEmployee>();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryEmployee.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            criteria.add(Restrictions.eq("deductee_catg", "E"));
            if (!utl.isnull(deducteeName)) {
                criteria.add(Restrictions.like("deductee_name", deducteeName).ignoreCase());
            }
            if (!utl.isnull(deducteeCatg)) {
                criteria.add(Restrictions.like("itax_catg", deducteeCatg).ignoreCase());
            }
            criteria.add(
                    Restrictions.not(
                            Restrictions.in("panno", new String[]{"PANNOTAVBL", "PANAPPLIED"})
                    )
            );
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("panno", term, MatchMode.START).ignoreCase());
            disjunction.add(Restrictions.like("deductee_name", term, MatchMode.START).ignoreCase());
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("deductee_name"));
            deducteeMastList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            deducteeMastList = null;
        }
        return deducteeMastList;
    }
    
    @Override
    public List<ViewSalaryEmployee> readDeducteeNameAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String panno, String deducteeCatg) {
        List<ViewSalaryEmployee> deducteeMastList = new ArrayList<ViewSalaryEmployee>();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryEmployee.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            if (!utl.isnull(panno)) {
                criteria.add(Restrictions.like("panno", panno).ignoreCase());
            }
            if (!utl.isnull(deducteeCatg)) {
                criteria.add(Restrictions.like("itax_catg", deducteeCatg).ignoreCase());
            }
            criteria.add(
                    Restrictions.not(
                            Restrictions.in("panno", new String[]{"PANNOTAVBL", "PANAPPLIED"})
                    )
            );
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("deductee_name", term, MatchMode.START).ignoreCase());
            disjunction.add(Restrictions.like("deductee_code", term, MatchMode.START).ignoreCase());
            criteria.add(disjunction);
            criteria.add(Restrictions.eq("deductee_catg", "E"));
            criteria.addOrder(Order.asc("deductee_name"));
            deducteeMastList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            deducteeMastList = null;
        }
        return deducteeMastList;
    }

    @Override
    public ViewSalaryEmployee readDeducteeByDeducteeName(Util utl, String entity_code, String client_code, String deducteeName) {
        ViewSalaryEmployee viewSalaryEmployee = new ViewSalaryEmployee();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryEmployee.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            if (!utl.isnull(deducteeName)) {
//                criteria.add(Restrictions.eq("deductee_name", deducteeName).ignoreCase());
                criteria.add(Restrictions.sqlRestriction("trim(upper(deductee_name))=trim(upper('"+deducteeName+"'))"));
            }
            viewSalaryEmployee = (ViewSalaryEmployee) criteria.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            viewSalaryEmployee = null;
        }
        return viewSalaryEmployee;
    }

    @Override
    public ViewSalaryEmployee readDeducteeByPanno(Util utl, String entity_code, String client_code, String panno) {
        ViewSalaryEmployee viewSalaryEmployee = new ViewSalaryEmployee();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryEmployee.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            if (!utl.isnull(panno)) {
                criteria.add(Restrictions.eq("panno", panno).ignoreCase());
            }
            viewSalaryEmployee = (ViewSalaryEmployee) criteria.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            viewSalaryEmployee = null;
        }
        return viewSalaryEmployee;
    }

    @Override
    public ViewSalaryEmployee readDeducteeByDeducteeNameOrPanno(Util utl, String entity_code, String client_code, String deducteeName, String panno) {
        ViewSalaryEmployee viewSalaryEmployee = new ViewSalaryEmployee();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryEmployee.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.eq("deductee_name", deducteeName).ignoreCase());
            disjunction.add(Restrictions.eq("panno", panno).ignoreCase());
            criteria.add(disjunction);
            viewSalaryEmployee = (ViewSalaryEmployee) criteria.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            viewSalaryEmployee = null;
        }
        return viewSalaryEmployee;
    }
}

