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
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.jdbc.Work;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcDeleteTranLoad {

    private Util utl;

    public ProcDeleteTranLoad() {
        utl = new Util();
    }
    String proc_out_parameter;

    public void executeProcedure(
            final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_assement_acc_year,
            final String a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final long a_client_login_session_seqno,
            final String a_process_type,
            final String a_template_code,
            final String a_user_code,
            final Long a_process_seqno) {

        Session session = getSessionFactory().openSession();

        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****proc_del_tran_load_process_seq******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_assement_acc_year---", a_assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seqno---", a_client_login_session_seqno);
                    utl.generateLog("a_process_type---", a_process_type);
                    utl.generateLog("a_template_code---", a_template_code);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_seqno);
                    
                    CallableStatement cs;
                    String proc = "{call lhs_tds_imp_template.proc_del_tran_load_process_seq(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);
                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, a_assement_acc_year);
                    cs.setInt(5, Integer.parseInt(a_quarter_no));

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(6, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(7, sql_a_to_date);

                    cs.setString(8, a_tds_type_code);
                    cs.setLong(9, a_client_login_session_seqno);
                    cs.setString(10, a_process_type);
                    cs.setString(11, "TEMPLATE_ALL");//a_template_code
                    cs.setString(12, a_user_code);

                    if (a_process_seqno != null) {
                        cs.setLong(13, a_process_seqno);
                    } else {
                        cs.setNull(13, java.sql.Types.INTEGER);
                    }
                  //  cs.registerOutParameter(14, Types.VARCHAR);
                    cs.executeUpdate();
                  //  proc_out_parameter = cs.getString(14);
                }
            };
            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        
        }catch (GenericJDBCException e) {
             utl.generateLog("Generic JDBC Exception", "");
             e.printStackTrace(); 
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            session.close();
            utl.generateLog("*****proc_del_tran_load_process_seq  END******", "");
        }

       
    }//end
}//end
