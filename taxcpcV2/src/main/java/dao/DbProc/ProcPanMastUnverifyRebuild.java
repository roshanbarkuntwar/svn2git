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
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author kapil.gupta
 */
public class ProcPanMastUnverifyRebuild {

    Util utl;
    int proc_out_parameter;

    public ProcPanMastUnverifyRebuild() {
        utl = new Util();
    }

    public int executeProcedure(final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String assement_acc_year,
            final int a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final Long a_client_login_session_seq_no,
            final String a_process_error,
            final String a_user_code,
            final Long a_process_Seqno
    ) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****Proc_pan_mast_unverify_rebuild******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("assement_acc_year---", assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seq_no---", a_client_login_session_seq_no);
                    utl.generateLog("a_process_error---", a_process_error);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_Seqno);

                    CallableStatement cs;
                    String proc = "{call LHS_TDS.proc_pan_mast_unverify_rebuild(?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, assement_acc_year);//assement_acc_year
                    cs.setInt(5, a_quarter_no);

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(6, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(7, sql_a_to_date);

                    cs.setString(8, a_tds_type_code);
                    cs.setLong(9, a_client_login_session_seq_no);
                    cs.registerOutParameter(10, java.sql.Types.INTEGER);
                    cs.setString(11, a_user_code);

                    if (a_process_Seqno != null) {
                        cs.setLong(12, a_process_Seqno);
                    } else {
                        cs.setNull(12, java.sql.Types.INTEGER);
                    }
                    cs.executeUpdate();
                    proc_out_parameter = cs.getInt(10);
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
        utl.generateLog("Proc_pan_mast_unverify_rebuild proc_out_parameter is", proc_out_parameter);
        return proc_out_parameter;
    }//end method
}
