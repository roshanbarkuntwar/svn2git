/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import form15GH.transaction.PinCodeFilterData;
import globalUtilities.Util;
import hibernateObjects.PinCodeMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author seema.mourya
 */
public class PinCodeMastDAOImpl extends GenericHibernateDAO<PinCodeMast, Serializable> implements PinCodeMastDAO {

    @Override
    public PinCodeMast readCityAndStateByPincode(String value) {
        Criterion criterion = Restrictions.eq("pin_code", value);
        List<PinCodeMast> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public Long getPinCodeCount(PinCodeFilterData panFilterSrch, String search, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(PinCodeMast.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {

                if (panFilterSrch != null) {

                    if (!utl.isnull(panFilterSrch.getPin_code())) {
                        crit.add(Restrictions.eq("pin_code", panFilterSrch.getPin_code()));
                    }
                    if (!utl.isnull(panFilterSrch.getCity())) {
                        crit.add(Restrictions.eq("city", panFilterSrch.getCity()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getDistrict())) {
                        crit.add(Restrictions.eq("district", panFilterSrch.getDistrict()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getState_name())) {
                        crit.add(Restrictions.like("state_name", panFilterSrch.getState_name()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getState_code())) {
                        crit.add(Restrictions.like("state_code", panFilterSrch.getState_code(), MatchMode.START));
                    }

                }
            } else {

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
    public List<PinCodeMast> readAllPinCodeData(PinCodeFilterData panFilterSrch, String search, int minVal, int maxVal, Util utl) {
        List<PinCodeMast> PanList;
        try {
            Criteria crit = getSession().createCriteria(PinCodeMast.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("pin_code").ignoreCase());
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (panFilterSrch != null) {

                    if (!utl.isnull(panFilterSrch.getPin_code())) {
                        crit.add(Restrictions.eq("pin_code", panFilterSrch.getPin_code()));
                    }
                    if (!utl.isnull(panFilterSrch.getCity())) {
                        crit.add(Restrictions.eq("city", panFilterSrch.getCity()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getDistrict())) {
                        crit.add(Restrictions.eq("district", panFilterSrch.getDistrict()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getState_name())) {
                        crit.add(Restrictions.like("state_name", panFilterSrch.getState_name()).ignoreCase());
                    }
                    if (!utl.isnull(panFilterSrch.getState_code())) {
                        crit.add(Restrictions.like("state_code", panFilterSrch.getState_code(), MatchMode.START));
                    }
                }

            }

            PanList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            PanList = null;
            getSession().getTransaction().rollback();
        }
        return (PanList != null && PanList.size() > 0 ? PanList : null);
    }

    @Override
    public PinCodeMast readPinCodeById(String PinCode) {
        Criterion criterion = Restrictions.eq("pin_code", PinCode);
        List<PinCodeMast> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<String> readCityNameAutocomplete(String term) {
        List<String> nameList;
        try {
            Criteria criteria = getSession().createCriteria(PinCodeMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("city", term, MatchMode.START).ignoreCase());

            criteria.add(disjunction);
            ProjectionList projectionList = Projections.projectionList();
//            projectionList.add(Projections.distinct(projectionList.add(Projections.property("state_name"), "state_name")));
            projectionList.add(Projections.distinct(Projections.property("city")));
            criteria.setProjection(projectionList);
            criteria.addOrder(Order.asc("city"));
            nameList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            nameList = null;
        }
        return nameList;
    }

    @Override
    public List<String> readDistrictNameAutocomplete(String term) {
        List<String> nameList;
        try {
            Criteria criteria = getSession().createCriteria(PinCodeMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("district", term, MatchMode.START).ignoreCase());

            criteria.add(disjunction);
            ProjectionList projectionList = Projections.projectionList();
//            projectionList.add(Projections.distinct(projectionList.add(Projections.property("state_name"), "state_name")));
            projectionList.add(Projections.distinct(Projections.property("district")));
            criteria.setProjection(projectionList);
            criteria.addOrder(Order.asc("district"));
            nameList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            nameList = null;
        }
        return nameList;
    }

    @Override
    public List<String> readStateNameAutocomplete(String term) {
        List<String> nameList;
        try {

            Criteria criteria = getSession().createCriteria(PinCodeMast.class);
            criteria.add(Restrictions.like("state_name", term, MatchMode.START).ignoreCase());
            //   criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            ProjectionList projectionList = Projections.projectionList();
//            projectionList.add(Projections.distinct(projectionList.add(Projections.property("state_name"), "state_name")));
            projectionList.add(Projections.distinct(Projections.property("state_name")));
            criteria.setProjection(projectionList);
            criteria.addOrder(Order.asc("state_name"));
            nameList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            nameList = null;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return nameList;
    }

}
