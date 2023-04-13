/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.CountryMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class CountryMastDAOImpl extends GenericHibernateDAO<CountryMast, Serializable> implements CountryMastDAO {

    @Override
    public HashMap<String, String> getCountryCodeAsHashMap() {
        List<CountryMast> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (CountryMast type : data) {
                forms.put(type.getCountry_code(), type.getCountry_name());
            }
        }
        return forms;
    }//end method

    //    ROshan Makhe COde starts
    @Override
    public Long getCountryCount(CountryMast countryFilterSearch, String search, Util utl) {

        Long rowcount;

        try {

            Criteria crit = getSession().createCriteria(CountryMast.class);

            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (countryFilterSearch != null) {

                    if (!utl.isnull(countryFilterSearch.getCountry_code())) {
                        crit.add(Restrictions.eq("country_code", countryFilterSearch.getCountry_code()).ignoreCase());
                    }

                    if (!utl.isnull(countryFilterSearch.getCountry_name())) {
                        crit.add(Restrictions.eq("country_name", countryFilterSearch.getCountry_name()).ignoreCase());
                    }
                }
            }

            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();

        } catch (HibernateException e) {
            rowcount = 0L;
            e.getMessage();
        }

        return rowcount;
    }

    @Override
    public List<CountryMast> getCountryByType(CountryMast countryFilterSearch, String search, int minVal, int maxVal, Util utl) {

        List<CountryMast> countryList;
        try {

            Criteria crit = getSession().createCriteria(CountryMast.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("country_name").ignoreCase());

            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (countryFilterSearch != null) {

                    if (!utl.isnull(countryFilterSearch.getCountry_code())) {
                        crit.add(Restrictions.eq("country_code", countryFilterSearch.getCountry_code()).ignoreCase());
                    }

                    if (!utl.isnull(countryFilterSearch.getCountry_name())) {
                        crit.add(Restrictions.eq("country_name", countryFilterSearch.getCountry_name()).ignoreCase());
                    }
                }
            }

            countryList = crit.list();

            getSession().getTransaction().commit();

        } catch (HibernateException e) {
            countryList = null;
            getSession().getTransaction().rollback();
            e.getMessage();
        }

        return (countryList != null && countryList.size() > 0 ? countryList : null);

    }//end method

    @Override
    public CountryMast getCountryById(String countryCode) {
        Criterion crtn = Restrictions.eq("country_code", countryCode);
        List<CountryMast> data = readByCriteria(crtn);
        return data != null && data.size() > 0 ? data.get(0) : null;
    }//end method

    @Override
    public List<CountryMast> getCountryListAsResident(String resident_status, Util utl) {
        List<CountryMast> countryList;
        try {

            Criteria crit = getSession().createCriteria(CountryMast.class);

            crit.addOrder(Order.asc("country_name").ignoreCase());

            if (!utl.isnull(resident_status)) {
                String l_country_name = "INDIA";
                if (resident_status.equalsIgnoreCase("R")) {
                    crit.add(Restrictions.eq("country_name", l_country_name).ignoreCase());
                } else if (resident_status.equalsIgnoreCase("N")) {
                    crit.add(Restrictions.not(Restrictions.eq("country_name", l_country_name).ignoreCase()));
                }
            }
            countryList = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            countryList = null;
            getSession().getTransaction().rollback();
            e.getMessage();
        }
        return (countryList != null && countryList.size() > 0 ? countryList : null);
    }//end method

    @Override
    public List<CountryMast> readCountryCodeAutocomplete(String term, String value, Util utl) {
        List<CountryMast> countryCodeList = null;
        try {
            Criteria criteria = getSession().createCriteria(CountryMast.class);

            criteria.add(Restrictions.like("country_code", term, MatchMode.START).ignoreCase());

            if (!utl.isnull(value)) {
                criteria.add(Restrictions.eq("country_name", value));
            }

//            ProjectionList projectionList = Projections.projectionList();
//            projectionList.add(Projections.distinct(Projections.property("country_code")));
//            criteria.setProjection(projectionList);
            countryCodeList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            countryCodeList = null;
        }
        return countryCodeList;
    }//end method

    @Override
    public List<CountryMast> readCountryNameAutocomplete(String term, String value, Util utl) {
        List<CountryMast> countryNameList = null;
        try {
            Criteria criteria = getSession().createCriteria(CountryMast.class);

            criteria.add(Restrictions.like("country_name", term, MatchMode.START).ignoreCase());

            if (!utl.isnull(value)) {
                criteria.add(Restrictions.eq("country_code", value));
            }

//            ProjectionList projectionList = Projections.projectionList();
//            projectionList.add(Projections.distinct(Projections.property("country_name")));
//            criteria.setProjection(projectionList);
            countryNameList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            countryNameList = null;
        }
        return countryNameList;
    }//end method
}
