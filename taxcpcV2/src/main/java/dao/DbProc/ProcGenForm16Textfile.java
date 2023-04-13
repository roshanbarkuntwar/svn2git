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
 * @author ayushi.jain
 */
public class ProcGenForm16Textfile {

    String proc_out_parameter = "0";

    Util utl;

    public ProcGenForm16Textfile() {
        utl = new Util();
    }

    public String execute_procedure(final String a_entity_code, final String a_client_code, final String a_acc_year, final int a_quarter_no, final Date a_from_date, final Date a_To_date, final String a_tds_type_code, final int client_login_session_seq_no, final int processFlag, final String client_code) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {

                        utl.generateLog("entity_code.f16e-", a_entity_code);
                        utl.generateLog("client_code.f16e-", a_client_code);
                        utl.generateLog("acc_year.f16e-", a_acc_year);
                        utl.generateLog("quarter_no.f16e-", a_quarter_no);
                        utl.generateLog("from_date.f16e-", a_from_date);
                        utl.generateLog("To_date.f16e-", a_To_date);
                        utl.generateLog("tds_type_code.f16e-", a_tds_type_code);
                        utl.generateLog("client_login_session_seq_no.f16e-", client_login_session_seq_no);
                        utl.generateLog("process Flag-", processFlag);
                        utl.generateLog("filter.client_code.f16e-", client_code);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_gen_fvutxt.proc_gen_form16_textfile(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, a_entity_code);//entity_code
                        clst.setString(2, a_client_code);//client code
                        clst.setString(3, a_acc_year);//acc_year
                        clst.setInt(4, a_quarter_no);//quarter no.     
                        java.util.Date utilFromDate = a_from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                        clst.setDate(5, sqlFromDate);//from date
                        java.util.Date utilToDate = a_To_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                        clst.setDate(6, sqlToDate);//to date

                        clst.setString(7, a_tds_type_code);//tds type
                        clst.setInt(8, client_login_session_seq_no);//tds type                  
                        clst.registerOutParameter(9, java.sql.Types.VARCHAR);//register output parameter
                        clst.setInt(10, 0);
                        clst.setString(11, client_code);//register output parameter
                        clst.setString(12, null);
                        clst.setInt(13, processFlag);//register output parameter
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(9);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            proc_out_parameter = "-1";
                            e.printStackTrace();
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
}//end class
