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
 * @author aniket.naik
 */
public class ProcLhsTdsTranInsert {

    int proc_out_parameter;
    Util utl;

    public int execute_procedure(final String Client_code, final String entity_code, final int quarter_no, final Date from_date, final Date To_date, final String acc_year, final String tds_type, final int file_seq_no, final Long tds_tran_rowid_seq_no, final String tran_table) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        //System.out.println("Client_code.."+Client_code);
                        //System.out.println("entity_code.."+entity_code);
                        //System.out.println("quarter_no.."+quarter_no);
                        //System.out.println("from_date.."+from_date);
                        //System.out.println("To_date.."+To_date);
                        //System.out.println("acc_year.."+acc_year);
                        //System.out.println("tds_type.."+tds_type);
                        String executeProc = "{call LHS_TDS.proc_lhs_tds_tran_insert(?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, Client_code);//client code
                        clst.setString(2, entity_code);//entity_code
                        clst.setInt(3, quarter_no);//quarter no.
                        java.sql.Date sql_a_from_date = new java.sql.Date(from_date.getTime());
                        clst.setDate(4, sql_a_from_date);//from date
                        java.sql.Date sql_a_to_date = new java.sql.Date(To_date.getTime());
                        clst.setDate(5, sql_a_to_date);//to date
                        clst.setString(6, acc_year);//acc_year
                        clst.setString(7, tds_type);//tds type
//                        clst.setInt(8, file_seq_no);//file sequence no.
                        clst.setNull(8, java.sql.Types.INTEGER);//file sequence no.
                        clst.setNull(9, java.sql.Types.INTEGER);//ROWID SEQ NO
                        clst.setNull(10, java.sql.Types.CHAR);//TRAN TABLE
                        clst.registerOutParameter(11, java.sql.Types.NUMERIC);//register output parameter
                        clst.executeUpdate();
                        proc_out_parameter = clst.getInt(11);
                        //System.out.println("Return_Result_Value_by_Procedure->" + proc_out_parameter);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                        }
                    } catch (SQLException ex) {//Handle Exception According to DB
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
        return proc_out_parameter;
    }//end method

    public ProcLhsTdsTranInsert() {
     utl = new Util();
    }

}
