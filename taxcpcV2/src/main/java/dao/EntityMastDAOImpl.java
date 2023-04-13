/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.EntityMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author gaurav.khanzode
 */
public class EntityMastDAOImpl extends GenericHibernateDAO<hibernateObjects.EntityMast, Serializable> implements EntityMastDAO {

    @Override
    public EntityMast readEntityByEntityName(String entity_name) {
        Criterion entity = Restrictions.eq("entity_name", entity_name.trim().toLowerCase()).ignoreCase();
        List<EntityMast> entityList = readByCriteria(entity);
        return (entityList != null && entityList.size() > 0) ? entityList.get(0) : null;
    }

    @Override
    public LinkedHashMap<String, String> readAllEntitiesAsHashMap() {
        LinkedHashMap<String, String> entities_map = new LinkedHashMap<>();
        try {
            List<EntityMast> data = readAll();
            if (data != null) {
                for (EntityMast type : data) {
                    entities_map.put(type.getEntity_code().trim(), type.getEntity_code().trim() + " - " + type.getEntity_name());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities_map;
    }//end 

    @Override
    public LinkedHashMap<String, String> readAllEntitiesAsHashMapFromClientMast() {
        LinkedHashMap<String, String> entities_map = new LinkedHashMap<>();
        try {
//            System.out.println("Entity details from client mast query: ");
            List<Object[]> data = getSession().createSQLQuery("SELECT A.ENTITY_CODE, UPPER(A.ENTITY_CODE || ' - ' || B.ENTITY_NAME) ENTITY_NAME\n"
                    + "  FROM CLIENT_MAST A, ENTITY_MAST B\n"
                    + " WHERE A.ENTITY_CODE = B.ENTITY_CODE\n"
                    + " GROUP BY A.ENTITY_CODE, B.ENTITY_NAME").list();
            if (data != null) {
                for (Object[] entity : data) {
//                    System.out.println("entity code:" + entity[0]);
//                    System.out.println("entity name:" + entity[1]);
                    entities_map.put(String.valueOf(entity[0]), String.valueOf(entity[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities_map;
    }//end method

}//end class
