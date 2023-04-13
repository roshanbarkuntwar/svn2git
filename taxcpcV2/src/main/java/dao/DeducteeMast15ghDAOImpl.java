/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.DeducteeMast15gh;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class DeducteeMast15ghDAOImpl extends GenericHibernateDAO<hibernateObjects.DeducteeMast15gh, Serializable> implements DeducteeMast15ghDAO {

    @Override
    public DeducteeMast15gh readById(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, Long rowid_seq) {
        List<DeducteeMast15gh> deducteeMastList = null;
        try {
            Criteria criteria = getSession().createCriteria(DeducteeMast15gh.class);
            criteria.add(Restrictions.eq("entity_code", entity_code));
            // criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + client_code + "' or w1.parent_code = '" + client_code + "' or\n"
                    + "                        w1.g_parent_code = '" + client_code + "' or w1.sg_parent_code = '" + client_code + "' or\n"
                    + "                        w1.ssg_parent_code = '" + client_code + "' or w1.sssg_parent_code = '" + client_code + "'))"));
            criteria.add(Restrictions.eq("acc_year", acc_year));
            criteria.add(Restrictions.eq("quarter_no", quarter_no));
            criteria.add(Restrictions.eq("tds_type_code", tds_type_code));
            criteria.add(Restrictions.eq("rowid_seq", rowid_seq));
            deducteeMastList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            deducteeMastList = null;
        }
        return (deducteeMastList != null && deducteeMastList.size() > 0) ? deducteeMastList.get(0) : null;
    }

    @Override
    public DeducteeMast15gh validateDeducteePanAvailbility(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String deductee_name, String PanCode) {
        List<DeducteeMast15gh> deducteeMastList = null;
        if (PanCode != null && !PanCode.equalsIgnoreCase("PANNOTAVBL")) {
            try {
                Criteria criteria = getSession().createCriteria(DeducteeMast15gh.class);
                criteria.add(Restrictions.eq("entity_code", entity_code));
                // criteria.add(Restrictions.eq("client_code", client_code));
                criteria.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + client_code + "' or w1.parent_code = '" + client_code + "' or\n"
                        + "                        w1.g_parent_code = '" + client_code + "' or w1.sg_parent_code = '" + client_code + "' or\n"
                        + "                        w1.ssg_parent_code = '" + client_code + "' or w1.sssg_parent_code = '" + client_code + "'))"));
                criteria.add(Restrictions.eq("acc_year", acc_year));
                criteria.add(Restrictions.eq("quarter_no", quarter_no));
                criteria.add(Restrictions.eq("tds_type_code", tds_type_code));
                criteria.add(Restrictions.eq("panno", PanCode).ignoreCase());
                criteria.add(Restrictions.eq("deductee_name", deductee_name).ignoreCase());
                criteria.add(
                        Restrictions.not(
                                Restrictions.in("panno", new String[]{"PANNOTAVBL", "PANAPPLIED"})
                        )
                );

                deducteeMastList = criteria.list();
                getSession().getTransaction().commit();
            } catch (Exception e) {
                getSession().getTransaction().rollback();
                e.printStackTrace();
                deducteeMastList = null;
            }
        }
        return (deducteeMastList != null && deducteeMastList.size() > 0) ? deducteeMastList.get(0) : null;
    }

    @Override
    public DeducteeMast15gh validateDeducteeForPANNOTAVBL(String entity_code, String client_code, String acc_year, String quarterNo, String tdsTypeCode, String deductee_name, String panno) {
        List<DeducteeMast15gh> deducteeMastList = null;
        try {
            Criteria criteria = getSession().createCriteria(DeducteeMast15gh.class);
            criteria.add(Restrictions.eq("entity_code", entity_code));
            //criteria.add(Restrictions.eq("client_code", client_code));
            criteria.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + client_code + "' or w1.parent_code = '" + client_code + "' or\n"
                    + "                        w1.g_parent_code = '" + client_code + "' or w1.sg_parent_code = '" + client_code + "' or\n"
                    + "                        w1.ssg_parent_code = '" + client_code + "' or w1.sssg_parent_code = '" + client_code + "'))"));
            criteria.add(Restrictions.eq("acc_year", acc_year));
            criteria.add(Restrictions.eq("quarter_no", quarterNo));
            criteria.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            criteria.add(Restrictions.in("panno", new String[]{"PANNOTAVBL", "PANAPPLIED"}));
            criteria.add(Restrictions.eq("deductee_name", deductee_name).ignoreCase());

            deducteeMastList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            deducteeMastList = null;
        }
        return (deducteeMastList != null && deducteeMastList.size() > 0) ? deducteeMastList.get(0) : null;
    }

    @Override
    public Long saveAndReturnDeducteeCode(DeducteeMast15gh deducteemast) {
         Long id;
        try {
            getSession().persist(deducteemast);
            getSession().flush();
            id = (Long) getSession().getIdentifier(deducteemast);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            id = null;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        //String idd = "";
        return id;
    }

}
