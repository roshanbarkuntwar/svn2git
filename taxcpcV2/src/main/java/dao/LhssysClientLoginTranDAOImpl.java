/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import LhssysClientLoginTran.LhssysClientLoginTranFilter;
import dao.generic.GenericHibernateDAO;
import hibernateObjects.LhssysClientLoginTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysClientLoginTranDAOImpl extends GenericHibernateDAO<LhssysClientLoginTran, Serializable> implements LhssysClientLoginTranDAO {

    @Override
    public LhssysClientLoginTran readClientLoginTranBySessionId(Long sessionId) {
        Criterion sessioncrtry = Restrictions.eq("client_session_seqno", sessionId);
        List<LhssysClientLoginTran> uselist = readByCriteria(sessioncrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }//end method    

//    @Override
//    public List<LhssysClientLoginTran> readClientLoginTranListByLoginTime(LhssysClientLoginTranFilter lhssysClientLoginTranFilterSearch, String search, int minVal, int maxVal, Util utl) {
//        List<LhssysClientLoginTran> readByCriteria;
//        SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY");
//        try {
//            Criteria crit = getSession().createCriteria(LhssysClientLoginTran.class);
//           if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
//                if (lhssysClientLoginTranFilterSearch != null) {
////                    if (!utl.isnull(sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time().getTime()))) {
//                    if (lhssysClientLoginTranFilterSearch.getLogin_time() != null) {
////                        String loginDate = sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time());
////                        //System.out.println("loginDate---" + loginDate);
////                        crit.add(Restrictions.sqlRestriction(sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time().getTime())));
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(login_time, 'DD-MM-RRRR') = TO_DATE('" + lhssysClientLoginTranFilterSearch.getLogin_time() + "', 'DD-MM-RRRR')"));
//                    }
//                }
//            }
//            crit.addOrder(Order.asc("logout_time"));
//            crit.setFirstResult(minVal);
//            crit.setMaxResults(maxVal);
//            readByCriteria = crit.list();
//            getSession().getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            readByCriteria = null;
//            getSession().getTransaction().rollback();
//        }
//        return (readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null);
//    }
//
//    @Override
//    public Long getLhssysClientLoginTranCount(LhssysClientLoginTranFilter lhssysClientLoginTranFilterSearch, String search, Util utl) {
//        Long rowcount;
//        SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY");
//        //System.out.println("lhssysClientLoginTranFilterSearch.getLogin_time() 1111....  " + lhssysClientLoginTranFilterSearch.getLogin_time());
//        try {
//            Criteria crit = getSession().createCriteria(LhssysClientLoginTran.class);
//            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
//                if (lhssysClientLoginTranFilterSearch != null) {
////                    if (!utl.isnull(sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time().getTime()))) {
//                    if (lhssysClientLoginTranFilterSearch.getLogin_time() != null) {
////                        String loginDate = sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time());
////                        //System.out.println("loginDate---" + loginDate);
////                        crit.add(Restrictions.sqlRestriction(sdf.format(lhssysClientLoginTranFilterSearch.getLogin_time().getTime())));
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(login_time, 'DD-MM-RRRR') = TO_DATE('" + lhssysClientLoginTranFilterSearch.getLogin_time() + "', 'DD-MM-RRRR')"));
//                    }
//                }
//            }
//            crit.setProjection(Projections.rowCount());
//            rowcount = (Long) crit.uniqueResult();
//            //System.out.println("rowecoutn.....  " + rowcount);
//            getSession().getTransaction().commit();
//        } catch (Exception e) {
//            rowcount = 0L;
//            e.printStackTrace();
//            getSession().getTransaction().rollback();
//        }
//        return rowcount;
//    }//end method
}
