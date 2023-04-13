/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.TdsChallanTranLoad;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class TdsChallanTranLoadDAOImpl extends GenericHibernateDAO<hibernateObjects.TdsChallanTranLoad, hibernateObjects.TdsChallanTranLoadId> implements TdsChallanTranLoadDAO {

    @Override
    public Long saveAndReturnSeqnoNO(TdsChallanTranLoad tdschallantran) {
        Long id=0l;
        try {
            getSession().persist(tdschallantran);
            getSession().flush();
            id = (Long) getSession().getIdentifier(tdschallantran);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            id = 0l;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return id;
    }

    @Override
    public TdsChallanTranLoad readChallanBySequenceID(Long rowid_seq) {

        List<TdsChallanTranLoad> uselist = null;
        try {
            Criteria crit = getSession().createCriteria(TdsChallanTranLoad.class);

            crit.add(Restrictions.eq("rowid_seq", rowid_seq));
            uselist = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
//        List<TdsChallanTranLoad> uselist = readByCriteria(crit);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }//end method

    @Override
    public List<TdsChallanTranLoad> getFVUFileDetails(TdsChallanTranLoad tdsChallanTranLoad) {
        List<TdsChallanTranLoad> tdsChallanTranLoadList = null;
        try {
            Criteria crit = getSession().createCriteria(TdsChallanTranLoad.class);
            crit.add(Restrictions.eq("client_code", tdsChallanTranLoad.getClient_code()));
            crit.add(Restrictions.eq("tds_type_code", tdsChallanTranLoad.getTds_type_code()));
            crit.add(Restrictions.eq("acc_year", tdsChallanTranLoad.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", tdsChallanTranLoad.getQuarter_no()));

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("csi_verify_flag"));
            disjunction.add(Restrictions.eq("csi_verify_flag", "P").ignoreCase());
            disjunction.add(Restrictions.eq("csi_verify_flag", "U").ignoreCase());
            crit.add(disjunction);

            crit.addOrder(Order.desc("rowid_seq"));
            tdsChallanTranLoadList = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tdsChallanTranLoadList = null;
            getSession().getTransaction().rollback();
        }
        return tdsChallanTranLoadList != null && tdsChallanTranLoadList.size() > 0 ? tdsChallanTranLoadList : null;
    }//end method
}//end class
