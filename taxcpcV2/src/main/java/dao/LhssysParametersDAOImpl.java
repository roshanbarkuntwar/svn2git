/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class LhssysParametersDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysParameters, Serializable> implements LhssysParametersDAO {

    public LhssysParametersDAOImpl() {
        utl = new Util();
    }

    @Override
    public LhssysParameters readDataAsParameterAndEntity(String param_name, String entity_code) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            criteria.add(Restrictions.eq("parameter_name", param_name).ignoreCase());
            if (!utl.isnull(entity_code)) {
                Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(Restrictions.isNull("entity_code"));
                disjunction.add(Restrictions.eq("entity_code", entity_code));
                criteria.add(disjunction);
                criteria.addOrder(Order.asc("entity_code"));
            }
            readByCriteria = criteria.list();
        } catch (Exception e) {
        }

        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }//End Method

    private Util utl;

    @Override
    public List<LhssysParameters> readParametersForSession(String entity_code) {

        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            if (!utl.isnull(entity_code)) {
                criteria.add(Restrictions.eq("session_flag", "T"));
                Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(Restrictions.isNull("entity_code"));
                disjunction.add(Restrictions.eq("entity_code", entity_code));
                criteria.add(disjunction);
                criteria.addOrder(Order.asc("entity_code"));
            }
            readByCriteria = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null;
    }// end method

    @Override
    public LhssysParameters readCsiWaitTime(String entityCode) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            criteria.add(Restrictions.eq("parameter_name", "CSI_DWNLD_WAIT_TIME"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entityCode));
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("entity_code"));
            readByCriteria = criteria.list();
        } catch (Exception e) {
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }// end method 

    @Override
    public LhssysParameters readFvuFileVersion(String entityCode) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            criteria.add(Restrictions.eq("parameter_name", "FVU_FILE_VERSION"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entityCode));
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("entity_code"));
            readByCriteria = criteria.list();
        } catch (Exception e) {
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }//end method

    @Override
    public LhssysParameters readOnlineFlag(String entityCode) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            criteria.add(Restrictions.eq("parameter_name", "ONLINE_FLAG"));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("entity_code"));
            disjunction.add(Restrictions.eq("entity_code", entityCode));
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("entity_code"));
            readByCriteria = criteria.list();
        } catch (Exception e) {
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }//end method

    @Override
    public List<LhssysParameters> readServerParameters(String serverType) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("parameter_name", serverType.trim() + "%", MatchMode.ANYWHERE));
            criteria.add(disjunction);
            readByCriteria = criteria.list();

            readByCriteria.forEach(item -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readByCriteria;
    }

    @Override
    public LhssysParameters readParametersBy(String parameterName) {
        Criteria criteria = getSession().createCriteria(LhssysParameters.class);
        criteria.add(Restrictions.eq("parameter_name", parameterName));
        List<LhssysParameters> readByCriteria = criteria.list();
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<LhssysParameters> readParametersByParameterType(String parameterType) {
        List<LhssysParameters> readByCriteria = new ArrayList<LhssysParameters>();
        try {
            Criteria criteria = getSession().createCriteria(LhssysParameters.class);
            criteria.add(Restrictions.eq("parameter_type", parameterType));
            readByCriteria = criteria.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return readByCriteria;
    }
    
  

}//End Class
