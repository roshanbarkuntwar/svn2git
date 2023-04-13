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
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcTdsDownload {

    Util utl;

    public ProcTdsDownload() {
        utl = new Util();
    }

    public void executeProcedure(final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_assement_acc_year,
            final String a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final Long a_client_login_session_seqno,
            final Long a_client_level,
            final String a_download_type/*TDS_CHALLANS, TDS_TRAN_LOAD_ERROR, TDS_TRANSACTIONS, TDS_DEDUCTEE, 15GH_TRANSACTION , LOWER_DEDUCTION*/,
            final String a_user_code,
            final Long a_process_Seqno) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****proc_tds_download parameters******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_assement_acc_year---", a_assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_client_login_session_seqno---", a_client_login_session_seqno);
                    utl.generateLog("a_client_level---", a_client_level);
                    utl.generateLog("a_download_type---", a_download_type);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_Seqno);

                    CallableStatement cs;
                    String proc = "{call lhs_tds_download.proc_tds_download(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                    cs.setLong(9, a_client_login_session_seqno);
                    cs.setLong(10, a_client_level);
                    cs.setString(11, a_download_type);
                    cs.registerOutParameter(12, java.sql.Types.VARCHAR);
                    cs.setString(13, a_user_code);

                    if (a_process_Seqno != null) {
                        cs.setLong(14, a_process_Seqno);
                    } else {
                        cs.setNull(14, java.sql.Types.INTEGER);
                    }

                    cs.registerOutParameter(15, java.sql.Types.VARCHAR);
                    cs.executeUpdate();

                }
            };
            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();

        } catch (JDBCException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
