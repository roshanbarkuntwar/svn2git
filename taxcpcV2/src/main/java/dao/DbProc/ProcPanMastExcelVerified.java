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
 * @author trainee
 */
public class ProcPanMastExcelVerified {

    String proc_out_parameter = "0";
    Util utl;

    public String execute_procedure(final String entity_code, final String Client_code, final String acc_year, final String Assessment_acc_year, final int quarter_no,
            final Date from_date, final Date To_date, final String tds_type, final Long loginSeqNo, final String process_type, final String template_type,
            final String excelFileName, final Long process_seq_no) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("\n***Proc PAN mast excel verified parameters***", "");
                        utl.generateLog("entity_code--", entity_code);
                        utl.generateLog("Client_code--", Client_code);
                        utl.generateLog("acc_year--", acc_year);
                        utl.generateLog("Assessment_acc_year--", Assessment_acc_year);
                        utl.generateLog("quarter_no--", quarter_no);
                        utl.generateLog("from_date--", from_date);
                        utl.generateLog("To_date--", To_date);
                        utl.generateLog("tds_type--", tds_type);
                        utl.generateLog("loginSeqNo--", loginSeqNo);
                        utl.generateLog("process_type--", process_type);
                        utl.generateLog("template_type--", template_type);
                        utl.generateLog("excelFileName--", excelFileName);
                        utl.generateLog("process_seq_no--", process_seq_no);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds.proc_pan_mast_excel_verified(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, Client_code);//client code
                        clst.setString(3, acc_year);//acc_year
                        clst.setString(4, Assessment_acc_year);//assessment year
                        clst.setInt(5, quarter_no);//quarter no.

                        java.util.Date utilFromDate = from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());

                        clst.setDate(6, sqlFromDate);//from date

                        java.util.Date utilToDate = To_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());

                        clst.setDate(7, sqlToDate);//to date
                        clst.setString(8, tds_type);//tds type
                        clst.setLong(9, loginSeqNo);//tds type
//                        clst.setString(10, process_type);//tds type
                        clst.setNull(10, java.sql.Types.VARCHAR);
                        clst.registerOutParameter(11, java.sql.Types.VARCHAR);//register output parameter
//                        clst.setNull(12, template_type);
                        clst.setNull(12, java.sql.Types.VARCHAR);
                        clst.setNull(13, java.sql.Types.VARCHAR);
                        clst.setLong(14, process_seq_no);

                        clst.executeUpdate();

                        proc_out_parameter = clst.getString(11);

                        try {
                            clst.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception ex) {//Handle Exception According to DB
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
    }//end method

    public ProcPanMastExcelVerified() {
        utl = new Util();
    }//end constructor
}
