/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.TdsTranCorr;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class TdsTranCorrDAOImpl extends GenericHibernateDAO<hibernateObjects.TdsTranCorr, Serializable> implements TdsTranCorrDAO {

    @Override
    public List<TdsTranCorr> readTdsChallanCorrectionAmount(String mapChallanRowSeq, String entity_code, String client_code, String acc_year, String quarterNo, String tdsTypeCode) {
        List<TdsTranCorr> tdsTranList;
        try {
            Criteria crit = getSession().createCriteria(TdsTranCorr.class);
            crit.add(Restrictions.eq("tds_challan_rowid_seq", mapChallanRowSeq));
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            tdsTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsTranList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsTranList != null && tdsTranList.size() > 0 ? tdsTranList : null);
    }

}
