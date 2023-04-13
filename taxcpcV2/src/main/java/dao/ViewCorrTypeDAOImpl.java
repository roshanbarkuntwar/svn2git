/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewCorrType;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author sandeep.bowade
 */
public class ViewCorrTypeDAOImpl extends GenericHibernateDAO<ViewCorrType, Serializable> implements ViewCorrTypeDAO {

    @Override
    public List<ViewCorrType> getObjList() {
        List<ViewCorrType> list = null;
        try {
            Criteria crit = getSession().createCriteria(ViewCorrType.class);
            list = crit.list();
            getSession().beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().beginTransaction().rollback();
        }
        return list;
    }//End Method

}//End Class
