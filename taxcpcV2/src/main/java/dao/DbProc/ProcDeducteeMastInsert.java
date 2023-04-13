/*
 * To change this template, choose Tools | Templates
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
 * @author bhawna.agrawal
 */
public class ProcDeducteeMastInsert {

    private Util utl;

    public ProcDeducteeMastInsert() {
        utl = new Util();
    }

    static Boolean procDeducteeMastInsertReturnValue = false;

    public Boolean execute_procedure(final String entity_code, final String Client_code, final String deductee_name, final String deductee_panno, final Long tds_tran_rowid_seq, final String deductee_type, final String tds_type_code) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_deductee_mast_insert(?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, Client_code);//client code
                        clst.setString(3, deductee_name);//acc_year
                        clst.setString(4, deductee_panno);//deductee_panno
                        clst.setLong(5, tds_tran_rowid_seq);//tds_tran_rowid_seq
                        clst.setString(6, deductee_type);//deductee type
                        clst.setString(7, tds_type_code);//tds_type_code
                        clst.execute();
                        utl.generateLog("entity_code====", entity_code);
                        utl.generateLog("Client_code====", Client_code);
                        utl.generateLog("deductee_name====", deductee_name);
                        utl.generateLog("deductee_panno====", deductee_panno);
                        utl.generateLog("tds_tran_rowid_seq====", tds_tran_rowid_seq);
                        utl.generateLog("deductee_type====", deductee_type);
                        utl.generateLog("tds_type_code====", tds_type_code);
                        utl.generateLog(null, "Procedure call");
                        try {
                            procDeducteeMastInsertReturnValue = true;
                            clst.close();
                            cnctn.commit();
                        } catch (SQLException e) {
                            procDeducteeMastInsertReturnValue = false;
                            cnctn.rollback();
                        }
                    } catch (SQLException ex) {
                        procDeducteeMastInsertReturnValue = false;
                        cnctn.rollback();
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            procDeducteeMastInsertReturnValue = false;
        } finally {
            ssn.close();
        }

        return procDeducteeMastInsertReturnValue;
    }//end method
}
