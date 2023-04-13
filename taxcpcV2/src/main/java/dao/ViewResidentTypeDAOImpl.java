/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewResidentType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author aniket.naik
 */
public class ViewResidentTypeDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewResidentType, Serializable> implements ViewResidentTypeDAO {

    @Override
    public LinkedHashMap<String, String> getViewResidentList() {
        LinkedHashMap<String, String> obj = new LinkedHashMap<String, String>();
        List<ViewResidentType> viesList = null;
        try {
            Criteria crit = getSession().createCriteria(ViewResidentType.class);
            viesList = crit.list();
            if (viesList != null && viesList.size() > 0) {
                for (ViewResidentType list : viesList) {
                    obj.put(list.getResdent_type_code(), list.getResdent_type_name());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }//end method

}
