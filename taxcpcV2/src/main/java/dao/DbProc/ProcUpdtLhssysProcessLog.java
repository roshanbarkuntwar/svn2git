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
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcUpdtLhssysProcessLog {

    Util utl;

    public ProcUpdtLhssysProcessLog() {
        utl = new Util();
    }

    public void executeProcedure(final Long a_process_seqno, final String a_column_name, final String a_column_value) {
        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****proc_updt_lhssys_process_log parameters******", "");
                    utl.generateLog("a_process_seqno---", a_process_seqno);
                    utl.generateLog("a_column_name---", a_column_name);
                    utl.generateLog("a_column_value---", a_column_value);

                    CallableStatement cs;
                    String proc = "{call lhs_utility.proc_updt_lhssys_process_log(?,?,?)}";
                    cs = cnctn.prepareCall(proc);
                    if (a_process_seqno != null) {
                        cs.setLong(1, a_process_seqno);
                    } else {
                        cs.setNull(1, java.sql.Types.INTEGER);
                    }
                    cs.setString(2, a_column_name);//Column name
                    cs.setString(3, a_column_value);//Column value
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
    }//end method
}//end class
