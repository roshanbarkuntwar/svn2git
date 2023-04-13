/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.EntityLogoMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dinesh.satpute
 */
public class EntityLogoMastDAOImpl extends GenericHibernateDAO<hibernateObjects.EntityLogoMast, Serializable> implements EntityLogoMastDAO {

    @Override
    public EntityLogoMast readEntityLogoByEntityCode(String entity_code) {
        
        Criterion entity = Restrictions.eq("entity_code", entity_code).ignoreCase();
        List<EntityLogoMast> entityList = readByCriteria(entity);
        return (entityList != null && entityList.size() > 0) ? entityList.get(0) : null;

    }

}
