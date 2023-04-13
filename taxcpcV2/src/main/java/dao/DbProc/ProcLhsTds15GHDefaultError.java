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
public class ProcLhsTds15GHDefaultError {

    int proc_out_parameter;
    Util utl;

    public ProcLhsTds15GHDefaultError() {
        utl = new Util();
    }

    public int executeProcedure(
            final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final String a_process_level,
            final String a_error_code) {

        Session session = getSessionFactory().openSession();

        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****Lhs_tds_15gh_default_error parameters******", "");
                    utl.generateLog("a_entity_code---", a_entity_code);
                    utl.generateLog("a_client_code---", a_client_code);
                    utl.generateLog("a_acc_year---", a_acc_year);
                    utl.generateLog("a_quarter_no---", a_quarter_no);
                    utl.generateLog("a_from_date---", a_from_date);
                    utl.generateLog("a_to_date---", a_to_date);
                    utl.generateLog("a_tds_type_code---", a_tds_type_code);
                    utl.generateLog("a_process_level---", a_process_level);
                    utl.generateLog("a_error_code---", a_error_code);

                    CallableStatement cs;
                    String proc = "{call lhs_tds_15gh.lhs_tds_15gh_default_error(?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, a_entity_code);
                    cs.setString(2, a_client_code);
                    cs.setString(3, a_acc_year);
                    cs.setString(4, a_quarter_no);

                    java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                    cs.setDate(5, sql_a_from_date);

                    java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                    cs.setDate(6, sql_a_to_date);

                    cs.setString(7, a_tds_type_code);
                    cs.setString(8, a_process_level);
                    cs.setString(9, a_error_code);
                    cs.registerOutParameter(10, java.sql.Types.INTEGER);

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

        return proc_out_parameter;
    }//end method
}//end class
