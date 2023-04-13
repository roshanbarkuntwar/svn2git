/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericHibernateDAO;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class LhssysProcessLogDAOImpl extends GenericHibernateDAO<LhssysProcessLog, Serializable> implements LhssysProcessLogDAO {

    @Override
    public Long saveAndReturnTokenIdentifier(UserMast user, Assessment asmt, String processType, Long loginIdSeqNo) {
        Long seqId = null;
        try {
            if (user != null && asmt != null) {
                LhssysProcessLog log = new LhssysProcessLog();
                log.setAcc_year(asmt.getAccYear());
                log.setClient_code(user.getClient_code());
                log.setClient_login_session_seqno(loginIdSeqNo);
                log.setEntity_code(user.getEntity_code());
                log.setFrom_date(asmt.getQuarterFromDate());
                log.setTo_date(asmt.getQuarterToDate());
                log.setLastupdate(new Date());
                log.setProcess_start_timestamp(new Date());
                log.setUser_code(user.getUser_code());
                log.setProcess_type(processType);
                log.setQuarter_no(Long.parseLong(asmt.getQuarterNo()));
                log.setTds_type_code(asmt.getTdsTypeCode());

                getSession().persist(log);
                getSession().flush();
                seqId = (Long) getSession().getIdentifier(log);
                getSession().getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            seqId = null;
            getSession().getTransaction().rollback();
        }
        return seqId;
    }

    @Override
    public Map<String, String> processSequenceNumbersList(String entityCode, Assessment asmt, String templateCode) {
        Map<String, String> returnMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        try {
            List<LhssysProcessLog> seqNosList = getSession().createCriteria(LhssysProcessLog.class)
                    .add(Restrictions.eq("entity_code", entityCode))
                    .add(Restrictions.eq("acc_year", asmt.getAccYear()))
                    .add(Restrictions.eq("quarter_no", Long.valueOf(asmt.getQuarterNo())))
                    .add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()))
                    //                    .add(Restrictions.disjunction()
                    .add(Restrictions.eq("template_code", templateCode))
                    //                            .add(Restrictions.isNull("template_code")))
                    .list();

//            System.out.println("entityCode= " + entityCode);
//            System.out.println("AccYear= " + asmt.getAccYear());
//            System.out.println("QuarterNo= " + asmt.getQuarterNo());
//            System.out.println("TdsTypeCode= " + asmt.getTdsTypeCode());
//            System.out.println("templateCode= " + templateCode);
            returnMap = seqNosList
                    .stream()
                    .collect(
                            Collectors.toMap(lplog1 -> lplog1.getProcess_seqno().toString(),
                                    lplog2 -> sdf.format(lplog2.getLastupdate()))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }
    
    @Override
    public LhssysProcessLog readProcessbySeqNo(Long process_seq_no) {
        Criterion processLog = Restrictions.eq("process_seqno", process_seq_no);
        List<LhssysProcessLog> LhssysProcessLog = readByCriteria(processLog);
        return (LhssysProcessLog != null && LhssysProcessLog.size() > 0) ? LhssysProcessLog.get(0) : null;
    }
    
    @Override
    public LhssysProcessLog readPRocessLogByProcessSeqNoAndParent(Long process_seqno ,Long parent_process_seqno) {
      System.out.println("calling readPRocessLogByProcessSeqNoAndParent");
          LhssysProcessLog object = null;
        try {
              Criteria crit = getSession().createCriteria(LhssysProcessLog.class);
              crit.add(Restrictions.eq("process_seqno", process_seqno));
              crit.add(Restrictions.eq("parent_process_seqno", parent_process_seqno));
              object = (LhssysProcessLog) crit.uniqueResult();
            
           System.out.println("getting data from db     "+object);
        } catch (Exception e) {
            e.printStackTrace();
            object = null;
            throw e;
        }
          return object;
    }
    
    
    
     @Override
    public List<LhssysProcessLog> getLhssysProcessLogbyParentProcessSeqno(Long parent_process_seqno) {
        List<LhssysProcessLog> objList = null;
        try {
            Criteria criteria = getSession().createCriteria(LhssysProcessLog.class);
            criteria.add(Restrictions.eq("parent_process_seqno", parent_process_seqno));

            objList = criteria.list();
            getSession().beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().beginTransaction().rollback();
        }
        return objList != null && objList.size() > 0 ? objList : null;
    }//End Method

      
}
       
     