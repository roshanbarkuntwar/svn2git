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
 * @author akash.dev
 */
public class ProcPanMastInsert {

    Util utl;
    static Boolean procPanMastInsertReturnValue = false;

    public ProcPanMastInsert() {
        utl = new Util();
    }

    public Boolean execute_procedure(final String entity_code, final String Client_code, final String acc_year, final Integer quarter_no, final String tds_type_code, final String deductee_panno, final String deductee_name) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("a_entity_code---", entity_code);
                        utl.generateLog("a_client_code---", Client_code);
                        utl.generateLog("a_acc_year---", acc_year);
                        utl.generateLog("a_quarter_no---", quarter_no);
                        utl.generateLog("tds_type_code---", tds_type_code);
                        utl.generateLog("deductee_panno---", deductee_panno);
                        utl.generateLog("deductee_name---", deductee_name);

                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_pan_mast_insert(?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, Client_code);//Client_code
                        clst.setString(3, acc_year);//acc_year
                        clst.setInt(4, quarter_no);//quarter_no
                        clst.setString(5, tds_type_code);//tds_type_code
                        clst.setString(6, deductee_panno);//deductee_panno
                        clst.setString(7, deductee_name);//deductee_name
                        clst.execute();
                        try {
                            procPanMastInsertReturnValue = true;
                            clst.close();
                            cnctn.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            procPanMastInsertReturnValue = false;
                            cnctn.rollback();
                            //utl.generateLog(e.getMessage());
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        procPanMastInsertReturnValue = false;
                        cnctn.rollback();
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            procPanMastInsertReturnValue = false;
        } finally {
            ssn.close();
        }

        return procPanMastInsertReturnValue;
    }//end method 
}
