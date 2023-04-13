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
 * @author akash.dev
 */
public class ProcLhsFuvTxtInsert {

    String proc_out_parameter;
    Util utl;

    public String execute_procedure(final String a_entity_code, final String a_client_code, final String a_acc_year, final String a_assement_acc_year, final int a_quarter_no,
            final Date a_from_date, final Date a_to_date, final String a_tds_type_code, final Long a_client_login_session_seqno, final String a_process_type,
            final String a_template_code, final String a_file_name, final String userCode, final Long processSeqNo) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        utl.generateSpecialLog("proc_lhs_fvutxt_insert parameters--", "");
                        utl.generateSpecialLog("a_entity_code---", a_entity_code);
                        utl.generateSpecialLog("a_client_code---", a_client_code);
                        utl.generateSpecialLog("a_acc_year---", a_acc_year);
                        utl.generateSpecialLog("a_assement_acc_year---", a_assement_acc_year);
                        utl.generateSpecialLog("a_quarter_no---", a_quarter_no);
                        utl.generateSpecialLog("a_from_date---", a_from_date);
                        utl.generateSpecialLog("a_to_date---", a_to_date);
                        utl.generateSpecialLog("a_tds_type_code---", a_tds_type_code);
                        utl.generateSpecialLog("a_client_login_session_seqno---", a_client_login_session_seqno);
                        utl.generateSpecialLog("a_process_type---", a_process_type);//replace or append
                        utl.generateSpecialLog("a_template_code---", a_template_code);//replace or append
                        utl.generateSpecialLog("a_file_name---", a_file_name);
                        utl.generateSpecialLog("userCode---", userCode);
                        utl.generateSpecialLog("processSeqNo---", processSeqNo);
                        String executeProc = "{call lhs_tds_imp_fvutxt.proc_lhs_fvutxt_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, a_entity_code);//a_entity_code
                        clst.setString(2, a_client_code);//a_client_code
                        clst.setString(3, a_acc_year);//a_acc_year
                        clst.setString(4, a_assement_acc_year);//a_assement_acc_year
                        clst.setInt(5, a_quarter_no);//a_quarter_no
                        java.sql.Date fromDate = new java.sql.Date(a_from_date.getTime());
                        clst.setDate(6, fromDate);//from date
                        java.sql.Date toDate = new java.sql.Date(a_to_date.getTime());
                        clst.setDate(7, toDate);//to date
                        clst.setString(8, a_tds_type_code);//a_tds_type_code
                        clst.setLong(9, a_client_login_session_seqno);//a_client_login_session_seqno
                        clst.setString(10, a_process_type);//a_process_type
                        clst.setString(11, a_template_code);//a_template_code
                        clst.setString(12, a_file_name);//a_file_name
                        clst.registerOutParameter(13, java.sql.Types.CHAR);//o
                        clst.setString(14, userCode);//a_USERCODE
                        clst.setLong(15, processSeqNo);//a_templete_code
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(13);

                        utl.generateLog("lhs_tds_imp_fvutxt.proc_lhs_fvutxt_insert...", "Upload type:FVUTEXT");
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
    }//end method

    public ProcLhsFuvTxtInsert() {
        utl = new Util();
    }//end constructor
}
