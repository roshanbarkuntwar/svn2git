/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.BankMast;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class BankMastDAOImpl extends GenericHibernateDAO<hibernateObjects.BankMast, Serializable> implements BankMastDAO {

    @Override
    public List<BankMast> readChallanAutocomplete(String term) {
        List<BankMast> deducteeMastList;
        try {
            Criteria criteria = getSession().createCriteria(BankMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bsr_code", term, MatchMode.START).ignoreCase());
            disjunction.add(Restrictions.like("bank_name", term, MatchMode.START).ignoreCase());
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("bank_name"));
            deducteeMastList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            deducteeMastList = null;
        }
        return deducteeMastList;
    }//end method

    @Override
    public List<BankMast> getBankDetails(String bsrCode) {
        Criteria crtrn = getSession().createCriteria(BankMast.class);
        crtrn.add(Restrictions.eq("bsr_code", bsrCode).ignoreCase());
        List<BankMast> bankdata = crtrn.list();
        getSession().getTransaction().commit();
        return bankdata;
    }
}
