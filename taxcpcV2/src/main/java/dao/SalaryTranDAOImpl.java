/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.SalaryTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class SalaryTranDAOImpl extends GenericHibernateDAO<SalaryTran, Serializable> implements SalaryTranDAO {

    @Override
    public SalaryTran findByRowidAndDeducteeCode(String rowId) {

        Criterion criterion1 = Restrictions.eq("rowid_seq", rowId);

        List<SalaryTran> readByCriteria = readByCriteria(criterion1);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;

    }
    @Override
    public SalaryTran findByDeducteePannoAndClientCodeAndEntityCode(String panno,String clientCode,String entityCode) {

        Criterion criterion1 = Restrictions.eq("deductee_panno", panno);
        Criterion criterion2 = Restrictions.eq("client_code", clientCode);
        Criterion criterion3 = Restrictions.eq("entity_code", entityCode);

        List<SalaryTran> readByCriteria = readByCriteria(criterion1,criterion2,criterion3);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;

    }
}
