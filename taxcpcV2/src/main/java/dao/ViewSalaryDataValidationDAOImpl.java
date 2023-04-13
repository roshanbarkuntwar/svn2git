/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewSalaryDataValidation;
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
public class ViewSalaryDataValidationDAOImpl extends GenericHibernateDAO<ViewSalaryDataValidation, Serializable>
        implements ViewSalaryDataValidationDAO {

    @Override
    public Long getSalaryDataValidationCount(ViewSalaryDataValidation salaryDataFilterSearch, String search, ViewClientMast clientMast, Assessment asmt, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(ViewSalaryDataValidation.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (salaryDataFilterSearch != null) {
//                    if (!utl.isnull(salaryDataFilterSearch.getInc_tax_on_incm_calc())) {
//                        crit.add(Restrictions.eq("inc_tax_on_incm_calc", salaryDataFilterSearch.getInc_tax_on_incm_calc()));
//                    }
                    if (!utl.isnull(salaryDataFilterSearch.getShortfall_excess_deduction())) {
                        if (salaryDataFilterSearch.getShortfall_excess_deduction().equalsIgnoreCase("S")) {
                            crit.add(Restrictions.sqlRestriction("shortfall_excess_deduction  >=1 "));
                        }
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getDeductee_name())) {
                        crit.add(Restrictions.like("deductee_name", salaryDataFilterSearch.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getItax_catg())) {
                        crit.add(Restrictions.eq("itax_catg", salaryDataFilterSearch.getItax_catg()));
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getPanno())) {
                        crit.add(Restrictions.eq("panno", salaryDataFilterSearch.getPanno()));
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getDuplicateFlag()) && salaryDataFilterSearch.getDuplicateFlag().equalsIgnoreCase("T")) {
                        crit.add(Restrictions.eq("deductee_panno_verified", "Y"));
                        crit.add(Restrictions.sqlRestriction(" exists (select 1        \n"
                                + "          from (                \n"
                                + "                select entity_code,\n"
                                + "                        client_code,\n"
                                + "                        acc_year,\n"
                                + "                        quarter_no,\n"
                                + "                        tds_type_code,\n"
                                + "                        deductee_panno,\n"
                                + "                        rowid_Seq                \n"
                                + "                  from (select b.entity_code,\n"
                                + "                                b.client_code,\n"
                                + "                                b.acc_year,\n"
                                + "                                b.quarter_no,\n"
                                + "                                b.tds_type_code,\n"
                                + "                                b.deductee_panno,\n"
                                + "                                b.rowid_Seq,                                \n"
                                + "                                count(deductee_panno) over(partition by b.entity_code,"
                                + " b.client_code, b.acc_year, b.quarter_no, b.tds_type_code, b.deductee_panno) dupl_deductee_panno\n"
                                + "                         \n"
                                + "                           from salary_tran b,client_mast b1                         \n"
                                + "                         where b1.entity_code=b.entity_code\n"
                                + "                          and   b1.client_code=b.client_code\n"
                                + "                          and   b.entity_code = '" + clientMast.getEntity_code() + "'\n"
                                + "                            and (b1.client_code = '" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                                + "                                )\n"
                                + "                            and b.acc_year = '" + asmt.getAccYear() + "') b             \n"
                                + "                 where nvl(dupl_deductee_panno, 0) > 1                \n"
                                + "                ) b        \n"
                                + "         where b.entity_code = this_.entity_code              \n"
                                + "           and b.client_code = this_.client_code             \n"
                                + "           and b.acc_year = this_.acc_year              \n"
                                + "         and b.deductee_panno = this_.panno "
                                + "        )    and this_.entity_code = '" + clientMast.getEntity_code() + "'"));
//                        crit.add(Restrictions.sqlRestriction(" exists (select 1 from\n"
//                                + "(\n"
//                                + "select deductee_code,count(*)\n"
//                                + "from salary_tran a, client_mast b\n"
//                                + "where a.entity_code=b.entity_code\n"
//                                + "and   a.client_code=b.client_code\n"
//                                + "and   (b.client_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.sssg_parent_code='" + clientMast.getClient_code() + "')\n"
//                                + "and a.entity_code='" + clientMast.getEntity_code() + "'\n"
//                                + "and a.acc_year='" + asmt.getAccYear().getAcc_year() + "'\n"
//                                + "and a.quarter_no='" + Integer.parseInt(asmt.getQuarter().getPeriod_name().split("-")[2]) + "'\n"
//                                + "and a.tds_type_code='" + asmt.getForm().getTds_type_code() + "'\n"
//                                + "group by deductee_code\n"
//                                + "having count(*)>1) xx where xx.deductee_code=this_.deductee_code)"));
                    }
                }
            }
//            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            //execute only when it is for salry individual as RRb salary module
//            crit.add(Restrictions.eq("identification_no", clientMast.getLogin_id()));
            //
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
    public List<ViewSalaryDataValidation> getSalaryDataByType(ViewSalaryDataValidation salaryDataFilterSearch, ViewClientMast clientMast, Assessment asmt, String search, int minVal, int maxVal, Util utl) {

        List<ViewSalaryDataValidation> salaryList;
        try {
            // int myMinVal=0;
            Criteria crit = getSession().createCriteria(ViewSalaryDataValidation.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("deductee_name").ignoreCase());
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (salaryDataFilterSearch != null) {
//                    if (!utl.isnull(salaryDataFilterSearch.getInc_tax_on_incm_calc())) {
//                        crit.add(Restrictions.eq("inc_tax_on_incm_calc", salaryDataFilterSearch.getInc_tax_on_incm_calc()));
//                    }
                    if (!utl.isnull(salaryDataFilterSearch.getShortfall_excess_deduction())) {
                        if (salaryDataFilterSearch.getShortfall_excess_deduction().equalsIgnoreCase("S")) {
                            crit.add(Restrictions.sqlRestriction("shortfall_excess_deduction   >= 1  "));
                        }
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getDeductee_name())) {
                        crit.add(Restrictions.like("deductee_name", salaryDataFilterSearch.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getItax_catg())) {
                        crit.add(Restrictions.eq("itax_catg", salaryDataFilterSearch.getItax_catg()));
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getPanno())) {
                        crit.add(Restrictions.eq("panno", salaryDataFilterSearch.getPanno()));
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getDuplicateFlag()) && salaryDataFilterSearch.getDuplicateFlag().equalsIgnoreCase("T")) {
                        crit.add(Restrictions.eq("deductee_panno_verified", "Y"));
                        crit.add(Restrictions.sqlRestriction(" exists (select 1        \n"
                                + "          from (                \n"
                                + "                select entity_code,\n"
                                + "                        client_code,\n"
                                + "                        acc_year,\n"
                                + "                        quarter_no,\n"
                                + "                        tds_type_code,\n"
                                + "                        deductee_panno,\n"
                                + "                        rowid_Seq                \n"
                                + "                  from (select b.entity_code,\n"
                                + "                                b.client_code,\n"
                                + "                                b.acc_year,\n"
                                + "                                b.quarter_no,\n"
                                + "                                b.tds_type_code,\n"
                                + "                                b.deductee_panno,\n"
                                + "                                b.rowid_Seq,                                \n"
                                + "                                count(deductee_panno) over(partition by b.entity_code,"
                                + " b.client_code, b.acc_year, b.quarter_no, b.tds_type_code, b.deductee_panno) dupl_deductee_panno\n"
                                + "                         \n"
                                + "                           from salary_tran b,client_mast b1                         \n"
                                + "                         where b1.entity_code=b.entity_code\n"
                                + "                          and   b1.client_code=b.client_code\n"
                                + "                          and   b.entity_code = '" + clientMast.getEntity_code() + "'\n"
                                + "                            and (b1.client_code = '" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "                                 b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                                + "                                )\n"
                                + "                            and b.acc_year = '" + asmt.getAccYear() + "') b             \n"
                                + "                 where nvl(dupl_deductee_panno, 0) > 1                \n"
                                + "                ) b        \n"
                                + "         where b.entity_code = this_.entity_code              \n"
                                + "           and b.client_code = this_.client_code             \n"
                                + "           and b.acc_year = this_.acc_year              \n"
                                + "         and b.deductee_panno = this_.panno "
                                + "        )    and this_.entity_code = '" + clientMast.getEntity_code() + "'"));
//                        crit.add(Restrictions.sqlRestriction(" exists (select 1 from\n"
//                                + "(\n"
//                                + "select deductee_code,count(*)\n"
//                                + "from salary_tran a, client_mast b\n"
//                                + "where a.entity_code=b.entity_code\n"
//                                + "and   a.client_code=b.client_code\n"
//                                + "and   (b.client_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
//                                + "       b.sssg_parent_code='" + clientMast.getClient_code() + "')\n"
//                                + "and a.entity_code='" + clientMast.getEntity_code() + "'\n"
//                                + "and a.acc_year='" + asmt.getAccYear().getAcc_year() + "'\n"
//                                + "and a.quarter_no='" + Integer.parseInt(asmt.getQuarter().getPeriod_name().split("-")[2]) + "'\n"
//                                + "and a.tds_type_code='" + asmt.getForm().getTds_type_code() + "'\n"
//                                + "group by deductee_code\n"
//                                + "having count(*)>1) xx where xx.deductee_code=this_.deductee_code)"));
                    }
                }
            }
//            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            //execute only when it is for salry individual as RRb salary module
//            crit.add(Restrictions.eq("identification_no", clientMast.getLogin_id()));
            salaryList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            salaryList = null;
            getSession().getTransaction().rollback();
        }

        return (salaryList != null && salaryList.size() > 0 ? salaryList : null);

    }

    @Override
    public ViewSalaryDataValidation readDeducteeByDeducteeNameOrPanno(Util utl, String entity_code, String client_code, String deducteeName, String panno) {
        ViewSalaryDataValidation viewSalaryEmployee = new ViewSalaryDataValidation();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryDataValidation.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.eq("deductee_name", deducteeName).ignoreCase());
            disjunction.add(Restrictions.eq("panno", panno).ignoreCase());
            criteria.add(disjunction);
            viewSalaryEmployee = (ViewSalaryDataValidation) criteria.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            viewSalaryEmployee = null;
        }
        return viewSalaryEmployee;
    }

    @Override
    public List<ViewSalaryDataValidation> readDeducteeNameAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String panno, String deducteeCatg) {
        List<ViewSalaryDataValidation> deducteeMastList = new ArrayList<ViewSalaryDataValidation>();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryDataValidation.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            criteria.add(Restrictions.eq("deductee_catg", "E"));
            criteria.add(Restrictions.like("deductee_name", term, MatchMode.ANYWHERE).ignoreCase());

            if (!utl.isnull(panno)) {
                criteria.add(Restrictions.like("panno", panno, MatchMode.ANYWHERE).ignoreCase());
            }
            if (!utl.isnull(deducteeCatg)) {
                criteria.add(Restrictions.like("itax_catg", deducteeCatg, MatchMode.ANYWHERE).ignoreCase());
            }
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
    public List<ViewSalaryDataValidation> readDeducteePanAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String deducteeName, String deducteeCatg) {
        List<ViewSalaryDataValidation> deducteeMastList = new ArrayList<ViewSalaryDataValidation>();
        try {
            Criteria criteria = getSession().createCriteria(ViewSalaryDataValidation.class);

            criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.eq("entity_code", entity_code));
            criteria.add(Restrictions.eq("deductee_catg", "E"));
            if (!utl.isnull(deducteeName)) {
                criteria.add(Restrictions.like("deductee_name", deducteeName).ignoreCase());
            }
            if (!utl.isnull(deducteeCatg)) {
                criteria.add(Restrictions.like("itax_catg", deducteeCatg).ignoreCase());
            }
            criteria.add(Restrictions.like("panno", term, MatchMode.START).ignoreCase());
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
    public Long getSalaryDataValidationDuplicateCount(ViewSalaryDataValidation salaryDataFilterSearch, String search, ViewClientMast clientMast, Assessment asmt, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(ViewSalaryDataValidation.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (salaryDataFilterSearch != null) {
                    if (!utl.isnull(salaryDataFilterSearch.getShortfall_excess_deduction())) {
                        if (salaryDataFilterSearch.getShortfall_excess_deduction().equalsIgnoreCase("S")) {
                            crit.add(Restrictions.sqlRestriction("shortfall_excess_deduction   >= 1  "));
                        }
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getDeductee_name())) {
                        crit.add(Restrictions.like("deductee_name", salaryDataFilterSearch.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getItax_catg())) {
                        crit.add(Restrictions.eq("itax_catg", salaryDataFilterSearch.getItax_catg()));
                    }
                    if (!utl.isnull(salaryDataFilterSearch.getPanno())) {
                        crit.add(Restrictions.eq("panno", salaryDataFilterSearch.getPanno()));
                    }
                }
            }
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            crit.add(Restrictions.sqlRestriction("and exists (select 1 from\n"
                    + "(\n"
                    + "select deductee_code,count(*)\n"
                    + "from salary_tran a, client_mast b\n"
                    + "where a.entity_code=b.entity_code\n"
                    + "and   a.client_code=b.client_code\n"
                    + "and   (b.client_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.sssg_parent_code='" + clientMast.getClient_code() + "')\n"
                    + "and a.entity_code='" + clientMast.getEntity_code() + "'\n"
                    + "and a.acc_year='" + asmt.getAccYear() + "'\n"
                    + "and a.quarter_no='" + Integer.parseInt(asmt.getQuarterNo()) + "'\n"
                    + "and a.tds_type_code='" + asmt.getTdsTypeCode() + "'\n"
                    + "group by deductee_code\n"
                    + "having count(*)>1) xx where xx.deductee_code=this_.deductee_code)"));
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
    public List<ViewSalaryDataValidation> getSalaryDuplicateDataByType(ViewClientMast clientMast, Assessment asmt, Util utl) {
        List<ViewSalaryDataValidation> salaryList;
        try {
            Criteria crit = getSession().createCriteria(ViewSalaryDataValidation.class);
            crit.addOrder(Order.asc("deductee_name").ignoreCase());
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from\n"
                    + "(\n"
                    + "select deductee_code,count(*)\n"
                    + "from salary_tran a, client_mast b\n"
                    + "where a.entity_code=b.entity_code\n"
                    + "and   a.client_code=b.client_code\n"
                    + "and   (b.client_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                    + "       b.sssg_parent_code='" + clientMast.getClient_code() + "')\n"
                    + "and a.entity_code='" + clientMast.getEntity_code() + "'\n"
                    + "and a.acc_year='" + asmt.getAccYear() + "'\n"
                    + "and a.quarter_no='" + Integer.parseInt(asmt.getQuarterNo()) + "'\n"
                    + "and a.tds_type_code='" + asmt.getTdsTypeCode() + "'\n"
                    + "group by deductee_code\n"
                    + "having count(*)>1) xx where xx.deductee_code=this_.deductee_code)"));
            salaryList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            salaryList = null;
            getSession().getTransaction().rollback();
        }
        return (salaryList != null && salaryList.size() > 0 ? salaryList : null);
    }

}
