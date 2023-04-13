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
 * @author gaurav.khanzode
 */
public class Proc15GHErrorDwnld {

    String proc_out_parameter;
    private Util utl;

    public Proc15GHErrorDwnld() {
        utl = new Util();
    }

    public String executeProcedure(
            final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_assement_acc_year,
            final String a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final long a_client_login_session_seqno,
            final long client_level,
            final String a_process_type,
            final String a_error_code,
            final String a_user_code,
            final Long a_process_seqno) {

        Session session = getSessionFactory().openSession();

        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****Proc_15gh_error_dwnld parameters******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_assement_acc_year---", a_assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seqno---", a_client_login_session_seqno);
                    utl.generateLog("client_level---", client_level);
                    utl.generateLog("a_process_type---", a_process_type);
                    utl.generateLog("a_error_code---", a_error_code);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_seqno);

                    CallableStatement cs;
                    String proc = "{call lhs_tds_download.proc_15gh_error_dwnld(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, a_assement_acc_year);
                    cs.setString(5, a_quarter_no);

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(6, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(7, sql_a_to_date);

                    cs.setString(8, a_tds_type_code);
//                    cs.setString(9, a_month);
                    cs.setLong(9, a_client_login_session_seqno);
                    cs.setLong(10, client_level);
                    cs.setString(11, a_process_type);
                    cs.setString(12, a_error_code);
                    cs.setString(13, a_user_code);

                    if (a_process_seqno != null) {
                        cs.setLong(14, a_process_seqno);
                    } else {
                        cs.setNull(14, java.sql.Types.INTEGER);
                    }
                    cs.registerOutParameter(15, java.sql.Types.VARCHAR);
                    cs.registerOutParameter(16, java.sql.Types.VARCHAR);
                    cs.executeUpdate();
                    proc_out_parameter = cs.getString(16);
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
        return proc_out_parameter;
    }

}
