/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.DeducteeBflagAmtTran;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class DeducteeBflagAmtTranDAOImpl extends GenericHibernateDAO<DeducteeBflagAmtTran, Serializable>
        implements DeducteeBflagAmtTranDAO {

    @Override
    public Session getHibernateSession() {
        return getSession();
    }

    @Override
    public List<DeducteeBflagAmtTran> readListForDeductee15gh(String entityCode, String clientCode, String accYear, String quarterNo, String tdsTypeCode, Long rowid_seq, String panno) {
        List<DeducteeBflagAmtTran> DeducteeBflagAmtTranList = new ArrayList<DeducteeBflagAmtTran>();
        System.out.println("get list of data");
        try {
            Criteria criteria = getSession().createCriteria(DeducteeBflagAmtTran.class);
            criteria.add(Restrictions.eq("deductee_mast_15gh_rowid_seq", rowid_seq));
            criteria.addOrder(Order.asc("bflag_tran_seq_id"));
            DeducteeBflagAmtTranList = criteria.list();

            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            DeducteeBflagAmtTranList = null;
        }

        return (DeducteeBflagAmtTranList != null && DeducteeBflagAmtTranList.size() > 0 ? DeducteeBflagAmtTranList : null);
    }

    @Override
    public List<DeducteeBflagAmtTran> getAmountTranDataList(String entity_code, String client_code, String accYear, String quarterNo, String tdsTypeCode, Long rowid_seq, String panno) {
        Criterion crit1 = Restrictions.eq("client_code", client_code);
        Criterion crit2 = Restrictions.eq("entity_code", entity_code);
        Criterion crit3 = Restrictions.eq("acc_year", accYear);
        Criterion crit4 = Restrictions.eq("quarter_no", quarterNo);
        Criterion crit5 = Restrictions.eq("tds_type_code", tdsTypeCode);
        Criterion crit6 = Restrictions.eq("deductee_mast_15gh_rowid_seq", rowid_seq);
        Criterion crit7 = Restrictions.eq("panno", panno);
        List<DeducteeBflagAmtTran> readByCriteria = readByCriteria(crit1, crit2, crit3, crit4, crit5, crit6, crit7);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null;
    }

    @Override
    public DeducteeBflagAmtTran readAmtTranById(Long deducteeBflagSeqNo) {
        Criterion crit1 = Restrictions.eq("bflag_tran_seq_id", deducteeBflagSeqNo);
        List<DeducteeBflagAmtTran> readByCriteria = readByCriteria(crit1);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

}
