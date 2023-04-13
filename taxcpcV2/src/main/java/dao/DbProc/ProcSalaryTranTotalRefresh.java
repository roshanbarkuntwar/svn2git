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
 * @author gaurav.khanzode
 */
public class ProcSalaryTranTotalRefresh {

    String proc_out_parameter;
    Util utl;

    public String execureProcedure(final String entityCode, final String clientCode, final String accYear,
            final int quarterNo, final Date from_date, Date to_date, final String tdsTypeCode, final Long rowid_seq,
            final String user_code, final Long proc_seq_no) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("***proc_salary_tran_total_refresh parameters***", "");
                        utl.generateLog("entityCode--", entityCode);
                        utl.generateLog("clientCode--", clientCode);
                        utl.generateLog("accYear--", accYear);
                        utl.generateLog("quarterNo--", quarterNo);
                        utl.generateLog("from_date--", from_date);
                        utl.generateLog("to_date--", to_date);
                        utl.generateLog("tdsTypeCode--", tdsTypeCode);
                        utl.generateLog("rowid_seq--", rowid_seq);
                        utl.generateLog("user_code--", user_code);
                        utl.generateLog("proc_seq_no--", proc_seq_no);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds.proc_salary_tran_total_refresh(?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entityCode);//a_entity_code
                        clst.setString(2, clientCode);//a_client_code
                        clst.setString(3, accYear);//a_acc_year
                        clst.setInt(4, quarterNo);//a_quarter_no

                        java.util.Date utilFromDate = from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                        clst.setDate(5, sqlFromDate);//tdsTypeCode

                        java.util.Date utilToDate = to_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                        clst.setDate(6, sqlToDate);//to_date

                        clst.setString(7, tdsTypeCode);//to_date
                        clst.setLong(8, rowid_seq);//to_date
                        clst.setString(9, user_code);//to_date

                        if (proc_seq_no != null) {
                            clst.setLong(10, proc_seq_no);//to_date
                        } else {
                            clst.setLong(10, 0l);//to_date

                        }
                        clst.setNull(11, java.sql.Types.VARCHAR);//o
                        clst.setNull(12, java.sql.Types.VARCHAR);//o
//o
                        clst.executeUpdate();
//                        proc_out_parameter = clst.getString(6);
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

    public ProcSalaryTranTotalRefresh() {
        utl = new Util();
    }

}
