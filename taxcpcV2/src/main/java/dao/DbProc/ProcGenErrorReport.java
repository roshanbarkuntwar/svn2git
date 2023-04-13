/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.DbProc;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.meshram
 */
public class ProcGenErrorReport {
    
    Util utl;
    

    public ProcGenErrorReport() {
        utl = new Util();
    }

    public void executeProcedure(final String a_entity_code,
                                    final String a_client_code,
                                    final String a_acc_year,
                                    final String assement_acc_year,
                                    final int a_quarter_no,
                                    final Date a_from_date,
                                    final Date a_to_date,
                                    final String a_tds_type_code,
                                    final String a_cust_id,
                                    final Long a_client_login_session_seq_no,
                                    final int a_client_level,
                                    final String a_download_type,
                                    final String a_error_code,
                                    final String a_user_code,
                                    final Long a_process_seqno
           
           
    ) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                    utl.generateLog("*****proc_generation_error_report ******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("assement_acc_year---", assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seq_no---", a_client_login_session_seq_no);
                    utl.generateLog("a_client_level---", a_client_level);
                    utl.generateLog("a_download_type---", a_download_type);
                    utl.generateLog("a_error_code---", a_error_code);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_seqno);
                 

                    CallableStatement cs;
                    String proc = "{call lhs_tds_download.proc_generation_error_report(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, assement_acc_year);//assement_acc_year
                    cs.setInt(5, a_quarter_no);
                    if(a_from_date !=null){
                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(6, sql_a_from_date);
                    }
                     if(a_to_date !=null){
                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(7, sql_a_to_date);
                     }
                    cs.setString(8, a_tds_type_code);
                    cs.setString(9, a_cust_id);
                    
                    cs.setLong(10, a_client_login_session_seq_no);
                    cs.setInt(11, a_client_level);
                    cs.setString(12, a_download_type);
                    cs.setString(13, a_error_code);
                    cs.setString(14, a_user_code);
                    cs.setLong(15, a_process_seqno);
                    cs.executeUpdate();
                    cs.close();
                    
                    } catch (SQLException ex) {
                       
                        ex.printStackTrace();
                    }
                }
            };
            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            session.close();
        }
        utl.generateLog("lhs_tds_download.proc_generation_error_report end","");
        
    }//end method
    
}
    
    
    