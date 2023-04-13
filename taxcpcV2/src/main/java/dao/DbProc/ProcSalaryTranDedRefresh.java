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
public class ProcSalaryTranDedRefresh {

    int proc_out_parameter;
    Util utl;
    public ProcSalaryTranDedRefresh() {
        utl = new Util();
    }
    public int executeProcedure(final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final int a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final Long a_rowid_seq,
            final String a_user_code,
            final Long a_process_Seqno,
            final String a_process_error,
            final String backup) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****Proc_salary_tran_ded_refresh******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_rowid_seq---", a_rowid_seq);
                    utl.generateLog("a_user_code---", a_user_code);
                    utl.generateLog("a_process_Seqno---", a_process_Seqno);
                    utl.generateLog("a_process_error---", a_process_error);
                    utl.generateLog("backup---", backup);

                    CallableStatement cs;
                    String proc = "{call lhs_itp.proc_salary_tran_ded_refresh(?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setInt(4, a_quarter_no);

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(5, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(6, sql_a_to_date);

                    cs.setString(7, a_tds_type_code);
                    cs.setLong(8, a_rowid_seq);
                    cs.setString(9, a_user_code);

                    if (a_process_Seqno != null) {
                        cs.setLong(10, a_process_Seqno);
                    } else {
                        cs.setNull(10, java.sql.Types.INTEGER);
                    }

                    cs.registerOutParameter(11, java.sql.Types.INTEGER);

                    cs.setString(12, backup);
                    cs.executeUpdate();
                    proc_out_parameter = cs.getInt(11);
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
        utl.generateLog("Proc_salary_tran_ded_refresh proc_out_parameter is", proc_out_parameter);
        return proc_out_parameter;
    }//end method
}
