/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.SalaryAmtTran;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class SalaryAmtTranDAOImpl extends GenericHibernateDAO<SalaryAmtTran, Serializable>
        implements SalaryAmtTranDAO{
    
  @Override
    public Session getHibernateSession() {
        return getSession();
    }

    @Override
    public ArrayList<SalaryAmtTran> readSalaryAmtTranByDeducteeCode(Date fromDate, Date toDate, String flag, String deductee_code, String month, String entity_code,String client_code, Util utl) {
        ArrayList<SalaryAmtTran> salaryList = new ArrayList<SalaryAmtTran>();
        try {
            Criteria crit = getSession().createCriteria(SalaryAmtTran.class);
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("deductee_code", deductee_code));
            crit.add(Restrictions.le("from_date", fromDate));
            crit.add(Restrictions.eq("flag", flag));

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("to_date"));
            disjunction.add(Restrictions.ge("to_date", toDate));
            crit.add(disjunction);
            if (utl.isnull(month)) {
                crit.add(Restrictions.isNull("month"));
            } else {
                crit.add(Restrictions.eq("month", month));
            }
            crit.addOrder(Order.asc("itax_order"));

            salaryList = (ArrayList<SalaryAmtTran>) crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return salaryList != null && salaryList.size() > 0 ? salaryList : null;
    }
    
    @Override
    public ArrayList<SalaryAmtTran> readSalaryAmtTranByRowIdSeq(Date fromDate, Date toDate, String flag, String deductee_code, String month, ViewClientMast clientMast, Long rowidSeq, Util utl) {
        ArrayList<SalaryAmtTran> salaryList = new ArrayList<SalaryAmtTran>();
        try {
            Criteria crit = getSession().createCriteria(SalaryAmtTran.class);
            crit.add(Restrictions.eq("flag", flag));
            crit.add(Restrictions.eq("deductee_code", deductee_code));
            crit.add(Restrictions.le("from_date", fromDate));
            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("rowid_seq", rowidSeq));

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("to_date"));
            disjunction.add(Restrictions.ge("to_date", toDate));
            crit.add(disjunction);
            if (utl.isnull(month)) {
                crit.add(Restrictions.isNull("month"));
            } else {
                crit.add(Restrictions.eq("month", month));
            }
            crit.addOrder(Order.asc("itax_order"));

            salaryList = (ArrayList<SalaryAmtTran>) crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return salaryList != null && salaryList.size() > 0 ? salaryList : null;
    }

    @Override
    public Long getSalaryCount(Date fromDate, Date toDate, String flag, String deductee_code, String month, String entity_code,String client_code, Util utl) {
        long rowcount = 0l;
        try {
            Criteria crit = getSession().createCriteria(SalaryAmtTran.class);
            crit.add(Restrictions.eq("flag", flag));
            crit.add(Restrictions.eq("deductee_code", deductee_code));
            crit.add(Restrictions.le("from_date", fromDate));
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("to_date"));
            disjunction.add(Restrictions.ge("to_date", toDate));
            crit.add(disjunction);
            if (utl.isnull(month)) {
                crit.add(Restrictions.isNull("month"));
            } else {
                crit.add(Restrictions.eq("month", month));
            }

            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }

    @Override
    public ArrayList<SalaryAmtTran> readSalaryAmtTranByDeducteeCode(String deductee_code, String itax_catg, String acc_year, ViewClientMast clientMast) {
        ArrayList<SalaryAmtTran> salaryList = new ArrayList<SalaryAmtTran>();
        try {
            Criteria crit = getSession().createCriteria(SalaryAmtTran.class);
            crit.add(Restrictions.eq("deductee_code", deductee_code));
            crit.add(Restrictions.eq("itax_catg", itax_catg));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("client_code", clientMast.getClient_code()));
            salaryList = (ArrayList<SalaryAmtTran>) crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return salaryList != null && salaryList.size() > 0 ? salaryList : null;
    }

    @Override
    public ArrayList<SalaryAmtTran> readSalaryAmtTranForRefreshingAmount(String deductee_code, String acc_year, String clientCode) {
        ArrayList<SalaryAmtTran> list = new ArrayList<SalaryAmtTran>();
        try {
            ArrayList afcodes = new ArrayList();
            afcodes.add("TDSCE");
            afcodes.add("TDS4Q");
            afcodes.add("TDS3Q");
            afcodes.add("TATPE");
            list = (ArrayList<SalaryAmtTran>) getSession().createCriteria(SalaryAmtTran.class).add(Restrictions.eq("acc_year", acc_year))
                    .add(Restrictions.in("afcode", afcodes))
                      .add(Restrictions.sqlRestriction("exists\n"
                            + " (select 1\n"
                            + "          from client_mast w1\n"
                            + "         where w1.client_code = this_.client_code\n"
                            + "           and (w1.client_code = '" + clientCode + "' or w1.parent_code = '" + clientCode + "' or\n"
                            + "               w1.g_parent_code = '" +clientCode + "' or\n"
                            + "               w1.sg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.ssg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sssg_parent_code = '" + clientCode + "'))"))
                    .add(Restrictions.eq("deductee_code", deductee_code)).list();
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ArrayList<String> readDistinctDeducteeCodesForRefreshingAmount(String itax_catg, String acc_year, String clientCode) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = (ArrayList<String>) getSession().createCriteria(SalaryAmtTran.class)
                    .add(Restrictions.eq("acc_year", acc_year))
                    .add(Restrictions.eq("afcode", "TDS3Q"))
                    .add(Restrictions.eq("itax_catg", itax_catg))
                     .add(Restrictions.sqlRestriction("exists\n"
                            + " (select 1\n"
                            + "          from client_mast w1\n"
                            + "         where w1.client_code = this_.client_code\n"
                            + "           and (w1.client_code = '" + clientCode + "' or w1.parent_code = '" + clientCode + "' or\n"
                            + "               w1.g_parent_code = '" +clientCode + "' or\n"
                            + "               w1.sg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.ssg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sssg_parent_code = '" + clientCode + "'))"))
                    .add(Restrictions.disjunction().add(Restrictions.isNull("earn_amt")).add(Restrictions.eq("earn_amt", "0.00")))
                    .setProjection(Projections.distinct(Projections.property("deductee_code"))).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Long getPositiveShortDeductionCount(String clientCode, String entityCode, String accYear) {
        Long rowCount = 0l;
        try {
            rowCount = (Long) getSession().createCriteria(SalaryAmtTran.class)
                    .add(Restrictions.eq("acc_year", accYear))
                    .add(Restrictions.eq("entity_code", entityCode))
                    .add(Restrictions.eq("afcode", "STD_1"))
                    .add(Restrictions.sqlRestriction(" earn_amt>0.99 "))
                    .add(Restrictions.sqlRestriction("exists\n"
                            + " (select 1\n"
                            + "          from client_mast w1\n"
                            + "         where w1.client_code = this_.client_code\n"
                            + "           and (w1.client_code = '" + clientCode + "' or w1.parent_code = '" + clientCode + "' or\n"
                            + "               w1.g_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.ssg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sssg_parent_code = '" + clientCode + "'))"))
                    .setProjection(Projections.rowCount())
                    .uniqueResult();

        } catch (Exception e) {
        }
        return rowCount;
    }

    @Override
    public List<SalaryAmtTran> getPositiveShortDeduction(String clientCode, String entityCode, String accYear) {
        List<SalaryAmtTran> list = new ArrayList<SalaryAmtTran>();
        try {
            list = getSession().createCriteria(SalaryAmtTran.class)
                    .add(Restrictions.eq("acc_year", accYear))
                    .add(Restrictions.eq("entity_code", entityCode))
                    .add(Restrictions.eq("afcode", "STD_1"))
                    .add(Restrictions.sqlRestriction(" earn_amt>0.99 "))
                    .add(Restrictions.sqlRestriction("exists\n"
                            + " (select 1\n"
                            + "          from client_mast w1\n"
                            + "         where w1.client_code = this_.client_code\n"
                            + "           and (w1.client_code = '" + clientCode + "' or w1.parent_code = '" + clientCode + "' or\n"
                            + "               w1.g_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.ssg_parent_code = '" + clientCode + "' or\n"
                            + "               w1.sssg_parent_code = '" + clientCode + "'))"))
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SalaryAmtTran getPreviousEmployerRecord(SalaryAmtTran tran) {
        List<SalaryAmtTran> list = new ArrayList<SalaryAmtTran>();
        try {
            list = getSession().createCriteria(SalaryAmtTran.class)
                    .add(Restrictions.eq("acc_year", tran.getAcc_year()))
                    .add(Restrictions.eq("entity_code", tran.getEntity_code()))
                    .add(Restrictions.eq("deductee_code", tran.getDeductee_code()))
                    .add(Restrictions.eq("afcode", "TDSPE"))
                    .add(Restrictions.eq("itax_catg", tran.getItax_catg()))
                    .add(Restrictions.eq("from_date", tran.getFrom_date()))
                    .add(Restrictions.eq("to_date", tran.getTo_date()))
//                    .add(Restrictions.sqlRestriction(" earn_amt>0.99 "))
                    .add(Restrictions.sqlRestriction("exists\n"
                            + " (select 1\n"
                            + "          from client_mast w1\n"
                            + "         where w1.client_code = this_.client_code\n"
                            + "           and (w1.client_code = '" + tran.getClient_code() + "' or w1.parent_code = '" + tran.getClient_code() + "' or\n"
                            + "               w1.g_parent_code = '" + tran.getClient_code() + "' or\n"
                            + "               w1.sg_parent_code = '" + tran.getClient_code() + "' or\n"
                            + "               w1.ssg_parent_code = '" + tran.getClient_code() + "' or\n"
                            + "               w1.sssg_parent_code = '" + tran.getClient_code() + "'))"))
                    .list();

        } catch (Exception e) {
        }
        return list!=null && list.size()>0?list.get(0):null;
    }
}

