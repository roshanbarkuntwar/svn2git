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
public class ProcGenerateTdsBulkTxtFile {

    Util utl;

    public ProcGenerateTdsBulkTxtFile() {
        utl = new Util();
    }

    public void execute_procedure(final String entity_code, final String Client_code, final String acc_year, final String Assessment_acc_year, final int quarter_no, final String month, final Date from_date, final Date To_date, final String tds_type, final String file_upload_purpose, final int file_seq_no, final Long Client_login_seq_no, final String template_code, final String Filename, final String import_flag, final String file_header_type, final Integer process_level, final String user_code, final Long process_seq_no, final Util utl) {
      utl.generateLog("start ProcGenerateTdsBulkTxtFile","");     

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
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
                        utl.generateLog("process_level..", process_level);
                        utl.generateLog("user_code..", user_code);
                        utl.generateLog("call LHS_TDS_GEN_FVUTXT.proc_gen_tds_bulk_txt_file(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)","");
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS_GEN_FVUTXT.proc_gen_tds_bulk_txt_file(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);
                        clst.setString(2, Client_code);
                        clst.setString(3, acc_year);
                        clst.setString(4, Assessment_acc_year);
                        clst.setInt(5, quarter_no);
                        clst.setNull(6, java.sql.Types.CHAR);

                        java.util.Date utilFromDate = from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                        clst.setDate(7, sqlFromDate);

                        java.util.Date utilToDate = To_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                        clst.setDate(8, sqlToDate);

                        clst.setString(9, tds_type);
                        clst.setString(10, file_upload_purpose);
                        clst.setNull(11, java.sql.Types.INTEGER);
                        clst.setLong(12, Client_login_seq_no);
                        clst.setNull(13, java.sql.Types.CHAR);
                        clst.setNull(14, java.sql.Types.CHAR);
                        clst.setString(15, import_flag);
                        if (!utl.isnull(file_header_type)) {
                            clst.setString(16, file_header_type);
                        } else {
                            clst.setNull(16, java.sql.Types.CHAR);
                        }
                        if (process_level != null) {
                            clst.setInt(17, process_level);
                        } else {
                            clst.setNull(17, java.sql.Types.INTEGER);
                        }
//                        clst.setInt(17, process_level);
                        clst.setNull(18, java.sql.Types.VARCHAR);
                        clst.setString(19, user_code);//user code
                        clst.setLong(20, process_seq_no);//process seq no
                        clst.executeUpdate();

                        try {
                            clst.close();
                        } catch (SQLException e) {

                        }
                    } catch (SQLException ex) {
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
        
         utl.generateLog("end ProcGenerateTdsBulkTxtFile",""); 
    }//end method
}
