/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewEmpCatg;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public class ViewEmpCatgDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewEmpCatg, Serializable> implements ViewEmpCatgDAO {

    @Override
    public HashMap<String, String> getEmpCatgAsLinkedHashMap() {
      
        List<ViewEmpCatg> data = readAll();
        
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null && !data.isEmpty()) {
            
            for (ViewEmpCatg type : data) {
                forms.put(type.getEmp_catg_code(), type.getEmp_catg_name());
            }
        }
        return forms;
    }

}
