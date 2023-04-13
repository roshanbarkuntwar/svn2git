/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysCorrFileUploadTranDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysCorrFileUploadTran, Serializable> implements LhssysCorrFileUploadTranDAO {

    @Override
    public String saveAndReturnFileSeqno(LhssysCorrFileUploadTran lhssysCorrFileUploadTran) {
        String id;
        try {
            getSession().persist(lhssysCorrFileUploadTran);
            getSession().flush();
            id = (String) getSession().getIdentifier(lhssysCorrFileUploadTran);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            id = null;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return id;
    }

    @Override
    public List<LhssysCorrFileUploadTran> readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo) {
        Long uploadMethod = 2L;
        Criterion usrclient = Restrictions.eq("client_code", clientCode);
        Criterion usrtds = Restrictions.eq("tds_type_code", tdsTypeCode);
        Criterion usraccyr = Restrictions.eq("acc_year", acc_year);
        Criterion usrqtrno = Restrictions.eq("quarter_no", quarterNo);
        List<LhssysCorrFileUploadTran> uselist = readByCriteria(usrclient, usrtds, usraccyr, usrqtrno);
        return (uselist != null && uselist.size() > 0) ? uselist : null;
    }//end method

    @Override
    public LhssysCorrFileUploadTran updateData(String fileSeqno, String flag) {
        LhssysCorrFileUploadTran obj = null;
        try {
            LhssysCorrFileUploadTran lhssysCorrFileUploadTran = (LhssysCorrFileUploadTran) getSession().get(LhssysCorrFileUploadTran.class, fileSeqno);
            lhssysCorrFileUploadTran.setFlag("F");

            getSession().update(lhssysCorrFileUploadTran);
            obj = lhssysCorrFileUploadTran;

            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            obj = null;
            ex.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return obj;
    }

    @Override
    public List<LhssysCorrFileUploadTran> getDataForDelete(String client_code, String acc_year, Double quarter_no, String tds_type_code, Long client_login_session_seqno) {
        List<LhssysCorrFileUploadTran> uselist = null;
        try {

            Criteria crit = getSession().createCriteria(LhssysCorrFileUploadTran.class);
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarter_no));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.add(Restrictions.eq("client_login_session_seqno", client_login_session_seqno));
            uselist = crit.list();
            getSession().beginTransaction().commit();
        } catch (Exception e) {
            uselist = null;
            e.printStackTrace();
            getSession().beginTransaction().rollback();
        }

        return uselist;
    }//end method

    @Override
    public List<LhssysCorrFileUploadTran> readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, LhssysCorrFileUploadTran filterObj, Util utl) {
        List<LhssysCorrFileUploadTran> uselist = null;

        try {
            Criteria crit = getSession().createCriteria(LhssysCorrFileUploadTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            if (filterObj != null) {
                if (!utl.isnull(filterObj.getFile_name())) {
                    crit.add(Restrictions.eq("file_name", filterObj.getFile_name()));
                }
            }
            uselist = crit.list();
            getSession().beginTransaction().commit();
        } catch (Exception e) {
            uselist = null;
            e.printStackTrace();
            getSession().beginTransaction().rollback();
        }

        return (uselist != null && uselist.size() > 0) ? uselist : null;
    }//end method

}//End Class
