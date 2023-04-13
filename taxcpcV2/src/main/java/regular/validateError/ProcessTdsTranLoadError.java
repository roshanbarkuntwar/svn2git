/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

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
public class ProcessTdsTranLoadError {

    int proc_out_parameter;
    Util utl;

    public ProcessTdsTranLoadError() {
        utl = new Util();
    }

    public int execute_procedure(final String processSeqId, final String l_entity_code, final String l_client_code, final String l_acc_year,
            final int l_quarter_no, final Date l_from_date, final Date l_to_date, final String l_tds_type_code, final int l_fileseqno,
            final int l_rowidSeq, final String tableName, final String uploadPurpose, final int a_refresh_ack_error,
            final int client_login_level, final String errorType, final String user_code) {

        utl.generateLog("l_entity_code---", l_entity_code);
        utl.generateLog("l_client_code---", l_client_code);
        utl.generateLog("l_acc_year---", l_acc_year);
        utl.generateLog("l_quarter_no---", l_quarter_no);
        utl.generateLog("l_from_date---", l_from_date);
        utl.generateLog("l_to_date---", l_to_date);
        utl.generateLog("l_tds_type_code---", l_tds_type_code);
        utl.generateLog("l_fileseqno---", "");
        utl.generateLog("l_rowidSeq---", "");
        utl.generateLog("tableName---", tableName);
        utl.generateLog("uploadPurpose---", uploadPurpose);
        utl.generateLog("process_error---", "");
        utl.generateLog("a_refresh_ack_error---", a_refresh_ack_error);
        utl.generateLog("client_login_level---", client_login_level);
        utl.generateLog("errorType---", errorType);
        utl.generateLog("user_code---", user_code);
        utl.generateLog("processSeqId---", processSeqId);
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS_ERROR_N.proc_tds_tran_load_error(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        /**
                         * The following line is commented due to change in
                         * procedure name by girish sir
                         */
//                        String executeProc = "{call LHS_TDS_ERROR_N.proc_tds_tran_load_error_temp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no
                        java.sql.Date sqlFromDate = new java.sql.Date(l_from_date.getTime());
                        clst.setDate(5, sqlFromDate);//from_date
                        java.sql.Date sqlToDate = new java.sql.Date(l_to_date.getTime());
                        clst.setDate(6, sqlToDate);//to_date
                        clst.setString(7, l_tds_type_code);//tds_type_code
                        if (l_fileseqno == 0) {
                            clst.setNull(8, java.sql.Types.INTEGER);//file_seq_no null
                        } else {
                            clst.setInt(8, l_fileseqno);//file_seq_no
                        }
                        clst.setNull(9, java.sql.Types.INTEGER);//l_rowidSeq
                        clst.setNull(10, java.sql.Types.VARCHAR);//tran_table
                        clst.setString(11, uploadPurpose);//upload_purpose
                        clst.setNull(12, java.sql.Types.VARCHAR);//process_error
                        clst.setInt(13, a_refresh_ack_error);//refresh_ack_error
                        clst.setInt(14, client_login_level);//client_login_level
                        clst.setString(15, errorType);//errorType
                        clst.setString(16, user_code);//user_code
                        clst.setLong(17, Long.valueOf(processSeqId));//processSeqId
                        clst.executeUpdate();

                        utl.generateLog(null, "execute_procedure LHS_TDS_ERROR_N.proc_tds_tran_load_error--->");
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            utl.generateLog(null, e.getMessage());
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
        utl.generateLog(null, "procedure called");
        return proc_out_parameter;
    }//end method
}
