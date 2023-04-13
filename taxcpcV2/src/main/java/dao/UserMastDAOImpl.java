/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.UserMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class UserMastDAOImpl extends GenericHibernateDAO<hibernateObjects.UserMast, Serializable> implements UserMastDAO {

    @Override
    public UserMast getUserByLoginAndPasswordOnly(String loginID, String password) {
        Criterion crtrn = Restrictions.eq("login_id", loginID).ignoreCase();
        Criterion crtrp = Restrictions.eq("login_pwd", password);

        List<UserMast> data = readByCriteria(crtrn, crtrp);

        return data != null && data.size() > 0 ? data.get(0) : null;
    }

    @Override
    public boolean saveUser(UserMast entity) {
        boolean save_status = false;
        try {
            getSession().save(entity);
            getSession().getTransaction().commit();
            save_status = true;
        } catch (Exception e) {
            save_status = false;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return save_status;
    }

    @Override
    public UserMast readUserByLoginId(String user_name) {
        Criterion user = Restrictions.eq("login_id", user_name.trim().toLowerCase()).ignoreCase();
        List<UserMast> userList = readByCriteria(user);
        return (userList != null && userList.size() > 0) ? userList.get(0) : null;
    }

    @Override
    public UserMast readUserByClientCode(String clientCode) {
        Criterion user = Restrictions.eq("client_code", clientCode.trim().toLowerCase()).ignoreCase();
        List<UserMast> userList = readByCriteria(user);
        return (userList != null && userList.size() > 0) ? userList.get(0) : null;
    }
    
     @Override
    public UserMast readUserByUserCode(String userCode) {
        Criterion user = Restrictions.eq("user_code", userCode.trim().toLowerCase()).ignoreCase();
        List<UserMast> userList = readByCriteria(user);
        return (userList != null && userList.size() > 0) ? userList.get(0) : null;
    }
}
