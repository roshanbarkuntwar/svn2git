/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.CityMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class CityMastDAOImpl extends GenericHibernateDAO<CityMast, Serializable> implements CityMastDAO {

    @Override
    public HashMap<String, String> getCityCodeAsHashMap() {
        List<CityMast> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (CityMast type : data) {
                forms.put(type.getCity_code(), type.getCity_name());
            }
        }
        return forms;
    }//end method

    @Override
    public List<CityMast> getCityCodeAsStateCode(String state_code) {
        Criterion parntcrt = Restrictions.eq("state_code", state_code);
        return readByCriteria(parntcrt);
    }//end method

    @Override
    public Long getCityCount(CityMast cityFilterSearch, String search, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(CityMast.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (cityFilterSearch != null) {
                    if (!utl.isnull(cityFilterSearch.getCity_name())) {
                        crit.add(Restrictions.eq("city_name", cityFilterSearch.getCity_name().trim()).ignoreCase());
                    }
                    if (!utl.isnull(cityFilterSearch.getDistrict_code())) {
                        crit.add(Restrictions.like("district_code", cityFilterSearch.getDistrict_code()).ignoreCase());
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
    public List<CityMast> getCityByType(CityMast cityFilterSearch, String search, int minVal, int maxVal, Util utl) {
        List<CityMast> cityList;
        try {
            Criteria crit = getSession().createCriteria(CityMast.class);
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("city_name").ignoreCase());
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (cityFilterSearch != null) {
                    if (!utl.isnull(cityFilterSearch.getCity_name())) {
                        crit.add(Restrictions.eq("city_name", cityFilterSearch.getCity_name().trim()).ignoreCase());
                    }
                    if (!utl.isnull(cityFilterSearch.getDistrict_code())) {
                        crit.add(Restrictions.like("district_code", cityFilterSearch.getDistrict_code()).ignoreCase());
                    }
                }
            }
            cityList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            cityList = null;
            getSession().getTransaction().rollback();
        }
        return (cityList != null && cityList.size() > 0 ? cityList : null);
    }

    @Override
    public CityMast readCityById(String cityCode) {
        Criterion criterion = Restrictions.eq("city_code", cityCode);
        List<CityMast> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

}
