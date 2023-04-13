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
public class ProcRefreshSalaryTdsAmt {

    String proc_out_parameter;
    Util utl;

    public String execureProcedure(final String entityCode, final String clientCode, final String accYear, final int quarterNo, final String tdsTypeCode) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("proc_refresh_salary_tds_amt..", "");
                        utl.generateLog("entityCode--", entityCode);
                        utl.generateLog("clientCode--", clientCode);
                        utl.generateLog("accYear--", accYear);
                        utl.generateLog("quarterNo--", quarterNo);
                        utl.generateLog("tdsTypeCode--", tdsTypeCode);

                        CallableStatement clst;
                        String executeProc = "{call lhs_hrd.proc_refresh_salary_tds_amt(?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entityCode);//a_entity_code
                        clst.setString(2, clientCode);//a_client_code
                        clst.setString(3, accYear);//a_acc_year
                        clst.setInt(4, quarterNo);//a_quarter_no
                        clst.setString(5, tdsTypeCode);//tdsTypeCode

                        clst.registerOutParameter(6, java.sql.Types.VARCHAR);//o
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(6);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException ex) {//Handle Exception According to DB
                        ex.printStackTrace();
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();

        } catch (JDBCException e) {
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return proc_out_parameter;
    }

    public ProcRefreshSalaryTdsAmt() {
        utl = new Util();
    }

}
