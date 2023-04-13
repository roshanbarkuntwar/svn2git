/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.UserMast;
import java.io.Serializable;

/**
 *
 * @author ayushi.jain
 */
public interface UserMastDAO extends GenericDAO<hibernateObjects.UserMast, Serializable> {

    UserMast getUserByLoginAndPasswordOnly(String loginID, String password);

    UserMast readUserByLoginId(String loginID);

    boolean saveUser(UserMast entity);

    UserMast readUserByClientCode(String clientCode);
    
    UserMast readUserByUserCode(String userCode);
}
