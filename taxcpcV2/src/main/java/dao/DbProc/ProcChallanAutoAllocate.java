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
public class ProcChallanAutoAllocate {

    private Util utl;

    public ProcChallanAutoAllocate() {
        utl = new Util();
    }
    static String ReturnValue = "";

    public String executeSetChallanAllocationAmtFunction(final String a_where_clause, final double a_allocate_amt, final int a_challan_seq_id, final String alloc_type) throws SQLException {
        Session ssn = getSessionFactory().openSession();

        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {

                        utl.generateLog("***Challan All allocation parameters***", "");
                        utl.generateLog("a_where_clause--", a_where_clause);
                        utl.generateLog("a_allocate_amt--", a_allocate_amt);
                        utl.generateLog("a_challan_seq_id--", a_challan_seq_id);
                        utl.generateLog("alloc_type--", alloc_type);

                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_challan_auto_alloc(?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, a_where_clause);//a_where_clause
                        clst.setDouble(2, a_allocate_amt);//a_allocate_amt
                        clst.setInt(3, a_challan_seq_id);//a_challan_seq_id                      
                        clst.setString(4, alloc_type);//a_challan_seq_id                      
                        clst.registerOutParameter(5, java.sql.Types.CHAR);//o
                        clst.executeUpdate();
                        ReturnValue = clst.getString(5);
                        utl.generateLog("ReturnValue...", ReturnValue);
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
        } finally {
            ssn.close();
        }

        return ReturnValue;
    }//end method 

}//end class
