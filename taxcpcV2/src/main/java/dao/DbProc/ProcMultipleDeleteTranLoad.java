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
 * @author akash.mehram
 */
public class ProcMultipleDeleteTranLoad {
    
    Util utl;
    String proc_out_parameter;

    public ProcMultipleDeleteTranLoad() {
        utl = new Util();
    }

    public String executeProcedure(final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String assement_acc_year,
            final int a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final Long a_client_login_session_seq_no,
            final String a_process_type,
            final String a_user_code,
            final Long a_process_seqno,
            String jsonobj
           
    ) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                    utl.generateLog("*****Multiple Transaction Delete ******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("assement_acc_year---", assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seq_no---", a_client_login_session_seq_no);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_seqno);
                 
                    //String b = "fbnvjf";
                    CallableStatement cs;
                    String proc = "{call LHS_TDS_IMP_TEMPLATE. proc_delete_tran_load(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                    cs.setLong(9, a_client_login_session_seq_no);
                    cs.setString(10, a_process_type);
                    cs.setString(11, a_user_code);
                    cs.setLong(12, a_process_seqno);
                    cs.setString(13, jsonobj);
                    cs.registerOutParameter(14, Types.VARCHAR);//out parameter
               
                    
                    cs.executeUpdate();
                    proc_out_parameter = cs.getString(14);
                    
                    try {
                            cs.close();
                        } catch (SQLException e) {
                            proc_out_parameter = "-1";
                        }
                    
                   
                    
                    } catch (SQLException ex) {
                        proc_out_parameter = "-1";
                        ex.printStackTrace();
                    }
                }
            };
            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        } catch (Exception e) {
            proc_out_parameter = "-1";
            e.printStackTrace();
        } finally {
            session.close();
        }
        utl.generateLog("LHS_TDS_IMP_TEMPLATE. proc_delete_tran_load  is", proc_out_parameter);
        return proc_out_parameter;
    }//end method
    
    
    
   
}
