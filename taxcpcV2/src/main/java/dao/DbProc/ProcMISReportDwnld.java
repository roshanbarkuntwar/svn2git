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
public class ProcMISReportDwnld {

    Util utl;

    public ProcMISReportDwnld() {
        utl = new Util();
    }

    public void executeProcedure(final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_assement_acc_year,
            final int a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final String a_cust_id,
            final String a_deductee_name,
            final Date a_challan_date,
            final Long a_client_login_session_seqno,
            final Long a_client_level,
            final String a_download_type/*TDS_CHALLANS, TDS_TRAN_LOAD_ERROR, TDS_TRANSACTIONS, TDS_DEDUCTEE*/,
            final String a_error_code/*TDS_CHALLANS, TDS_TRAN_LOAD_ERROR, TDS_TRANSACTIONS, TDS_DEDUCTEE*/,
            //            final String a_file_name,
            final String a_user_code,
            final Long a_process_Seqno,
            final Long a_rep_seq_id) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****proc_mis_report_download parameters******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_assement_acc_year---", a_assement_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_cust_id---", a_cust_id);
                    utl.generateLog("a_deductee_name---", a_deductee_name);
                    utl.generateLog("a_challan_date---", a_challan_date);
                    utl.generateLog("a_client_login_session_seqno---", a_client_login_session_seqno);
                    utl.generateLog("a_client_level---", a_client_level);
                    utl.generateLog("a_download_type---", a_download_type);
                    utl.generateLog("a_error_code---", a_error_code);
//                    utl.generateLog("a_file_name---" , a_file_name);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_Seqno);
                    utl.generateLog("a_rep_seq_id---", a_rep_seq_id);

                    CallableStatement cs;
                    String proc = "{call lhs_tds_download.proc_mis_report_dwnld(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, a_assement_acc_year);
                    cs.setInt(5, a_quarter_no);

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(6, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(7, sql_a_to_date);

                    cs.setString(8, a_tds_type_code);
                    cs.setString(9, a_cust_id);
                    cs.setString(10, a_deductee_name);

                    if (a_challan_date != null) {
                        java.sql.Date sql_a_challan_date = new java.sql.Date(a_challan_date.getTime());
                        cs.setDate(11, sql_a_challan_date);
                    } else {
                        cs.setDate(11, null);
                    }
                    cs.setLong(12, a_client_login_session_seqno);
                    cs.setLong(13, a_client_level);
                    cs.setString(14, a_download_type);
                    cs.setString(15, a_error_code);
//                    cs.setString(15, a_file_name);
                    cs.setString(16, a_user_code);
                    cs.setLong(17, a_process_Seqno);
                    cs.setLong(18, a_rep_seq_id);

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
