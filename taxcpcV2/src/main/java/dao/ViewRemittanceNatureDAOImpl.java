/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ViewRemittanceNature;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public class ViewRemittanceNatureDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewRemittanceNature, Serializable> implements ViewRemittanceNatureDAO {

    @Override
    public LinkedHashMap<String, String> getRemittanceAsLinkedHashMap() {
        List<ViewRemittanceNature> data = readAll();
        LinkedHashMap<String, String> forms = new LinkedHashMap<String, String>();
        if (data != null) {
            for (ViewRemittanceNature type : data) {
                forms.put(type.getRemittance_nature_code(), type.getRemittance_nature_name());
            }
        }
        return forms;
    }

}
