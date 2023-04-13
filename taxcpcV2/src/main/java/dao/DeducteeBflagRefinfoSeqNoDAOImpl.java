/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.DeducteeBflagRefinfoSeqNo;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class DeducteeBflagRefinfoSeqNoDAOImpl extends GenericHibernateDAO<DeducteeBflagRefinfoSeqNo, Serializable> implements DeducteeBflagRefinfoSeqNoDAO {

    @Override
    public DeducteeBflagRefinfoSeqNo readDeducteeBflagRefinfoByBFlag(String entityCode, String clientCode, String accYear, String Bflag) {
        Criterion crtre = Restrictions.eq("entity_code", entityCode).ignoreCase();
        Criterion crtrc = Restrictions.eq("client_code", clientCode);
        Criterion crtra = Restrictions.eq("acc_year", accYear);
        Criterion crtrb = Restrictions.eq("bflag", Bflag);
        List<DeducteeBflagRefinfoSeqNo> data = readByCriteria(crtre, crtrc, crtra, crtrb);

        return data != null && data.size() > 0 ? data.get(0) : null;
    }

}
