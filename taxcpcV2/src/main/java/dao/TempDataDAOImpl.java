/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import java.io.Serializable;
import org.hibernate.Session;

/**
 *
 * @author aniket.naik
 */
public class TempDataDAOImpl extends GenericHibernateDAO<hibernateObjects.TempData, Serializable> implements TempDataDAO {

    @Override
    public Session getHibernateSession() {
        return getSession();
    }

}
