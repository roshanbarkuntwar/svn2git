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
 * @author user
 */
public class ProcGenerateTdsTextFile {

    String proc_out_parameter;
    Util utl;

    public ProcGenerateTdsTextFile() {
        utl = new Util();
    }

    public void execute_procedure(final String entity_code, final String Client_code, final String acc_year, final String Assessment_acc_year, final int quarter_no,
            final String month, final Date from_date, final Date To_date, final String tds_type, final String file_upload_purpose, final int file_seq_no,
            final Long Client_login_seq_no, final String template_code, final String Filename, final String import_flag, final String file_header_type, final String user_code, final String process_seqno) {

        Session ssn = getSessionFactory().openSession();
//        boolean result = false;
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("procedure called---->>>proc_generate_tds_txt_file", "");
                        utl.generateLog("entity_code..", entity_code);
                        utl.generateLog("Client_code..", Client_code);
                        utl.generateLog("acc_year..", acc_year);
                        utl.generateLog("Assessment_acc_year..", Assessment_acc_year);
                        utl.generateLog("quarter_no..", quarter_no);
                        utl.generateLog("month..", month);
                        utl.generateLog("from_date..", from_date);
                        utl.generateLog("To_date..", To_date);
                        utl.generateLog("tds_type..", tds_type);
                        utl.generateLog("file_upload_purpose..", file_upload_purpose);
                        utl.generateLog("file_seq_no..", file_seq_no);
                        utl.generateLog("Client_login_seq_no..", Client_login_seq_no);
                        utl.generateLog("template_code..", template_code);
                        utl.generateLog("import_flag..", import_flag);
                        utl.generateLog("file_header_type..", file_header_type);
                        utl.generateLog("user_code..", user_code);
                        utl.generateLog("process_seqno..", process_seqno);
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS_GEN_FVUTXT.proc_generate_tds_txt_file(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, Client_code);//client code
                        clst.setString(3, acc_year);//acc_year
                        clst.setString(4, Assessment_acc_year);//assessment year
                        clst.setInt(5, quarter_no);//quarter no.
                        clst.setNull(6, java.sql.Types.CHAR);//month

                        java.sql.Date sql_a_from_date = new java.sql.Date(from_date.getTime());
                        clst.setDate(7, sql_a_from_date);//from date
                        java.sql.Date sql_a_to_date = new java.sql.Date(To_date.getTime());
                        clst.setDate(8, sql_a_to_date);//to date

                        clst.setString(9, tds_type);//tds type
                        clst.setString(10, file_upload_purpose);//file upload purpose
                        clst.setNull(11, java.sql.Types.INTEGER);//file seq no
                        clst.setLong(12, Client_login_seq_no);//client login sequence no.
                        clst.setNull(13, java.sql.Types.CHAR);//template code
                        clst.setNull(14, java.sql.Types.CHAR);//file name
                        clst.setString(15, import_flag);//import flag
                        if (!utl.isnull(file_header_type)) {
                            clst.setString(16, file_header_type);//file_header_type
                        } else {
                            clst.setNull(16, java.sql.Types.CHAR);//file_header_type
                        }
                        clst.setNull(17, java.sql.Types.VARCHAR);//out parameterl
                        clst.setString(18, user_code);//user_code
                        clst.setString(19, process_seqno);//process_seqno
                        clst.executeUpdate();
//                        proc_out_parameter = clst.getString(17);

                        //utl.generateLog("Procedure call");
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            e.printStackTrace(); //utl.generateLog(e.getMessage());
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
//        if (!utl.isnull(proc_out_parameter) && proc_out_parameter.equalsIgnoreCase("0")) {
//            result = true;
//        }
//        return result;
    }//end method
}
