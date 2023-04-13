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
 * @author ayushi.jain
 */
public class ProcTdsReturnStatusInsert {

    String proc_out_parameter = "0";//for return value if procedure already return some value the remove this
    Util utl;

    public ProcTdsReturnStatusInsert() {
        utl = new Util();
    }

    public String execute_procedure(final String entity_code, final String client_code, final String acc_year, final int quarter_no, final String tds_type,
            final String a_tds_return_type, final String a_process_type, final String a_process_level) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {

                        utl.generateLog("entity_code..", entity_code);
                        utl.generateLog("client_code..", client_code);
                        utl.generateLog("acc_year..", acc_year);
                        utl.generateLog("quarter_no..", quarter_no);
                        utl.generateLog("tds_type..", tds_type);
                        utl.generateLog("a_tds_return_type..", a_tds_return_type);
                        utl.generateLog("a_process_type..", a_process_type);
                        utl.generateLog("a_process_level..", a_process_level);

                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_tds_return_status_insert(?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, client_code);//client code
                        clst.setString(3, acc_year);//acc_year

                        clst.setInt(4, quarter_no);//quarter no.
                        clst.setString(5, tds_type);//tds type
                        clst.setString(6, a_tds_return_type);//tds type
                        clst.setString(7, a_process_type);//a_process_type
                        clst.registerOutParameter(8, java.sql.Types.CHAR);//register output parameter
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(8);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            proc_out_parameter = "-1";
                        }
                    } catch (SQLException ex) {//Handle Exception According to DB
                        ex.printStackTrace();
                        proc_out_parameter = "-1";
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            proc_out_parameter = "-1";
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return proc_out_parameter;//return 1 then no error 
    }//end method   
}
