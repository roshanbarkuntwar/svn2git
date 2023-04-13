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
public class ProcPanUnverifiedTxt {
//    String fileName="";
//     String proc_out_parameter = "0";//for return value if procedure already return some value the remove this

    Util utl;

    public void execute_procedure(final String entity_code,
            final String client_code,
            final String acc_year,
            final String assement_acc_year,
            final int quarter_no,
            final Date from_date,
            final Date To_date,
            final String tds_type,
            final String month,
            final Long client_login_session_seq_no,
            final String a_process_type,
            final String userCode,
            final long process_seqno,
            final String out_file_name,
            final long unverifiedPanCount,
            final String process_error) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("*****Proc_Pan_Unverified_Txt parameters******", "");
                        utl.generateLog("entity_code..", entity_code);
                        utl.generateLog("client_code..", client_code);
                        utl.generateLog("acc_year..", acc_year);
                        utl.generateLog("assement_acc_year..", assement_acc_year);
                        utl.generateLog("quarter_no..", quarter_no);
                        utl.generateLog("from_date..", from_date);
                        utl.generateLog("To_date..", To_date);
                        utl.generateLog("month..", month);
                        utl.generateLog("client_login_session_seq_no..", client_login_session_seq_no);
                        utl.generateLog("a_process_type..", a_process_type);
                        utl.generateLog("userCode..", userCode);
                        utl.generateLog("tds_type..", tds_type);
                        utl.generateLog("process_seqno..", process_seqno);
                        utl.generateLog("out_file_name..", out_file_name);
                        utl.generateLog("unverifiedPanCount..", unverifiedPanCount);
                        utl.generateLog("process_error..", process_error);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_download.proc_pan_unverified_txt(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, client_code);//client code
                        clst.setString(3, acc_year);//acc_year
                        clst.setString(4, assement_acc_year);//assement_acc_year
                        clst.setInt(5, quarter_no);//quarter no.
                        java.sql.Date sqlFromDate = new java.sql.Date(from_date.getTime());
                        clst.setDate(6, sqlFromDate);//from date
                        java.sql.Date sQLTo_date = new java.sql.Date(To_date.getTime());
                        clst.setDate(7, sQLTo_date);//to date
                        clst.setString(8, tds_type);//tds type
                        clst.setString(9, month);//month
                        clst.setLong(10, client_login_session_seq_no);//client_login_session_seq_no
                        clst.setString(11, a_process_type);//process type
                        clst.setString(12, userCode);
                        clst.setLong(13, process_seqno);

                        clst.setString(14, out_file_name);
                        clst.setLong(15, unverifiedPanCount);
//                        clst.registerOutParameter(15, java.sql.Types.NUMERIC);//register output parameter
                        clst.setString(16, process_error);
                        clst.executeUpdate();
//                        fileName = clst.getString(14) , "#" , clst.getString(15);
////                        proc_out_parameter = clst.getString(14);
//                        try {
//                            clst.close();
//                        } catch (SQLException e) {
//                            proc_out_parameter = "-1";
//                        }
                    } catch (SQLException ex) {//Handle Exception According to DB
                        ex.printStackTrace();
//                        proc_out_parameter = "-1";
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
//            proc_out_parameter = "-1";
            e.printStackTrace();
        } finally {
            ssn.close();
        }
//        if(!proc_out_parameter.equalsIgnoreCase("0")){
//            fileName ="";
//        }

//        return fileName;//return 1 then no error 
    }//end method

    public ProcPanUnverifiedTxt() {
        utl=new Util();
    }

}
