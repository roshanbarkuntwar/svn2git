/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsSplRateMastId;
import hibernateObjects.ViewClientMast;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class TdsSplRateMastDAOImpl extends GenericHibernateDAO<TdsSplRateMast, TdsSplRateMastId>
        implements TdsSplRateMastDAO {

    @Override
    public Long getTdsSplRateCount(TdsSplRateMast tdsSplRateFilterSearch, String search, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (tdsSplRateFilterSearch != null) {
                    if (!utl.isnull(tdsSplRateFilterSearch.getTds_type_code())) {
                        crit.add(Restrictions.eq("tds_type_code", tdsSplRateFilterSearch.getTds_type_code().trim()).ignoreCase());
                    }
                    if (!utl.isnull(tdsSplRateFilterSearch.getDeductee_panno())) {
                        crit.add(Restrictions.like("deductee_panno", tdsSplRateFilterSearch.getDeductee_panno()).ignoreCase());
                    }
                    if (!utl.isnull(tdsSplRateFilterSearch.getCertificate_no())) {
                        crit.add(Restrictions.like("certificate_no", tdsSplRateFilterSearch.getCertificate_no()).ignoreCase());
                    }
                }
            }
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
    public List<TdsSplRateMast> getTdsSplRateByType(TdsSplRateMast tdsSplRateFilterSearch, String search, int minVal, int maxVal, Util utl) {
        List<TdsSplRateMast> tdsSplRateList;
        try {
            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.desc("from_date"));
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (tdsSplRateFilterSearch != null) {
                    if (!utl.isnull(tdsSplRateFilterSearch.getTds_type_code())) {
                        crit.add(Restrictions.eq("tds_type_code", tdsSplRateFilterSearch.getTds_type_code().trim()).ignoreCase());
                    }
                    if (!utl.isnull(tdsSplRateFilterSearch.getDeductee_panno())) {
                        crit.add(Restrictions.like("deductee_panno", tdsSplRateFilterSearch.getDeductee_panno()).ignoreCase());
                    }
                    if (!utl.isnull(tdsSplRateFilterSearch.getCertificate_no())) {
                        crit.add(Restrictions.like("certificate_no", tdsSplRateFilterSearch.getCertificate_no()).ignoreCase());
                    }
                }
            }
            tdsSplRateList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsSplRateList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsSplRateList != null && tdsSplRateList.size() > 0 ? tdsSplRateList : null);
    }

    @Override
    public TdsSplRateMast readTdsSplRateById(TdsSplRateMastId tdsSplRateMastId) {
//        Criterion frmdtcrit = Restrictions.eq("from_date", tdsSplRateMastId.getFrom_date());
        Criterion tdscddrit = Restrictions.eq("tds_code", tdsSplRateMastId.getTds_code());
        Criterion dedctgcrit = Restrictions.eq("entity_code", tdsSplRateMastId.getEntity_code());
        Criterion certnocrit = Restrictions.eq("certificate_no", tdsSplRateMastId.getCertificate_no());
        Criterion certnocrit1 = Restrictions.eq("deductee_panno", tdsSplRateMastId.getDeductee_panno());
        Criterion certnocrit2 = Restrictions.eq("client_code", tdsSplRateMastId.getClient_code());
        Criterion certnocrit3 = Restrictions.eq("acc_year", tdsSplRateMastId.getAcc_year());
        List<TdsSplRateMast> readByCriteria = readByCriteria(tdscddrit, dedctgcrit, certnocrit, certnocrit1, certnocrit2, certnocrit3);

        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public TdsSplRateMast getDeducteeCertificateNo(TdsSplRateMast tdsSplRateMastSearch) {

        List<TdsSplRateMast> tdsSplRateList;

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        // String formatFromDate = formatter.format(tdsSplRateMastSearch.getFrom_date());
        try {

            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);

            crit.add(Restrictions.eq("tds_code", tdsSplRateMastSearch.getTds_code()));
            crit.add(Restrictions.eq("deductee_code", tdsSplRateMastSearch.getDeductee_code()));
//            crit.add(Restrictions.eq("from_date", tdsSplRateMastSearch.getFrom_date()));
            crit.add(Restrictions.eq("certificate_no", tdsSplRateMastSearch.getCertificate_no()).ignoreCase());

            tdsSplRateList = crit.list();

            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tdsSplRateList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsSplRateList != null && tdsSplRateList.size() > 0 ? tdsSplRateList.get(0) : null);
    }

    @Override
    public Boolean checkUniquePrimaryKey(TdsSplRateMast tdsSplRateMastSearch) {
        boolean unique = false;

        try {

            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
//            System.out.println("tdsSplRateMastSearch.getTds_code()==" + tdsSplRateMastSearch.getTds_code());
//            System.out.println("tdsSplRateMastSearch.getDeductee_code()==" + tdsSplRateMastSearch.getDeductee_code());
//            System.out.println("tdsSplRateMastSearch.getFrom_date()==" + tdsSplRateMastSearch.getFrom_date());
//            System.out.println("tdsSplRateMastSearch.getCertificate_no()==" + tdsSplRateMastSearch.getCertificate_no());
            crit.add(Restrictions.eq("tds_code", tdsSplRateMastSearch.getTds_code()));
            crit.add(Restrictions.eq("deductee_code", tdsSplRateMastSearch.getDeductee_code()));
            crit.add(Restrictions.eq("certificate_no", tdsSplRateMastSearch.getCertificate_no()));

            List<TdsSplRateMast> tdsSplRateMast = crit.list();

            if (tdsSplRateMast != null && tdsSplRateMast.size() > 0) {
                unique = false;
            } else {
                unique = true;
            }
            getSession().getTransaction().commit();
        } catch (Exception e) {
            unique = false;
            getSession().getTransaction().rollback();
        }

        return unique;
    }

    @Override
    public List<TdsSplRateMast> readTdsSplRateForLowDeduCertifiAlloca(TdsSplRateMast tdsSplRateMast) {
        List<TdsSplRateMast> tdsSplRateList = null;
        try {


            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
            crit.add(Restrictions.eq("entity_code", tdsSplRateMast.getEntity_code()));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getClient_code()));
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getParent_code()));
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getG_parent_code()));
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getSg_parent_code()));
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getSsg_parent_code()));
            disjunction.add(Restrictions.eq("client_code", tdsSplRateMast.getSssg_parent_code()));

            crit.add(disjunction);
            crit.add(Restrictions.eq("acc_year", tdsSplRateMast.getAcc_year()));
            crit.add(Restrictions.eq("tds_type_code", tdsSplRateMast.getTds_type_code()));
            crit.add(Restrictions.eq("deductee_panno", tdsSplRateMast.getDeductee_panno()));
            crit.add(Restrictions.eq("tds_code", tdsSplRateMast.getTds_code()));

            crit.addOrder(Order.asc("certificate_no"));

            tdsSplRateList = crit.list();

            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tdsSplRateList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsSplRateList != null && tdsSplRateList.size() > 0 ? tdsSplRateList : null);
    }//End Method

    @Override
    public List<TdsSplRateMast> readTdsSplRateForDeducteeList(ViewClientMast client, Assessment asmt, String tdsCode,
            String deducteePanno) {
          //System.out.println("printling query"+client.getEntity_code());
          //  System.out.println("printling query"+client.getClient_code());
           /// System.out.println("printling query"+asmt.getAccYear());
           //// System.out.println("printling query"+asmt.getTdsTypeCode());
           // System.out.println("printling query"+tdsCode);
            //  System.out.println("printling query"+deducteePanno);
            
        List<TdsSplRateMast> tdsSplRateList = null;
        try {
            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
            crit.add(Restrictions.eq("entity_code", client.getEntity_code()));
            crit.add(Restrictions.eq("client_code", client.getClient_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            crit.add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()));
            crit.add(Restrictions.eq("tds_code", tdsCode));
            crit.add(Restrictions.eq("deductee_panno", deducteePanno));
//            crit.addOrder(Order.desc("certificate_no"));
            crit.addOrder(Order.desc("lastupdate"));

            tdsSplRateList = crit.list();
        } catch (Exception e) {
            tdsSplRateList = null;

        }
        return (tdsSplRateList != null && tdsSplRateList.size() > 0 ? tdsSplRateList : null);
    }//End Method

    @Override
    public TdsSplRateMast getCertificateDetails(ViewClientMast client, Assessment asmt, TdsSplRateMast obj) {
        TdsSplRateMast tdsSplRateMast = null;
        try {
            Criteria crit = getSession().createCriteria(TdsSplRateMast.class);
            crit.add(Restrictions.eq("tds_code", obj.getTds_code()));
            crit.add(Restrictions.eq("deductee_panno", obj.getDeductee_panno()));
            crit.add(Restrictions.eq("certificate_no", obj.getCertificate_no()).ignoreCase());
            crit.add(Restrictions.eq("entity_code", client.getEntity_code()));
            crit.add(Restrictions.eq("client_code", client.getClient_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            crit.add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()));

            tdsSplRateMast = (TdsSplRateMast) crit.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            tdsSplRateMast = null;
        }
        return tdsSplRateMast;
    }//end

}
