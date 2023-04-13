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
public class ProcInsertTempData {

    String procOutParameter;
    Util utl;

    public ProcInsertTempData() {
        utl = new Util();
    }

    public String execute_procedure(final String entity_code, final String client_code, final String acc_year, final int quarter_no, final Date from_date, final Date to_date, final String tds_type, final String template_code, final Long client_login_session_seqno, final String userCode, final Long processSeqNo, final String processType) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        /**
                         * **TEMPLATE INSERT PARAMETER
                         */

                        utl.generateSpecialLog("proc_insert_temp_data parameters--", "");
                        utl.generateSpecialLog("entity_code---", entity_code);
                        utl.generateSpecialLog("client_code---", client_code);
                        utl.generateSpecialLog("acc_year---", acc_year);
                        utl.generateSpecialLog("quarter_no---", quarter_no);
                        utl.generateSpecialLog("tds_type---", tds_type);
                        utl.generateSpecialLog("template_code---", template_code);
                        utl.generateSpecialLog("user_code---", userCode);
                        utl.generateSpecialLog("process_seq_no---", processSeqNo);
                        utl.generateSpecialLog("client_login_session_seqno---", client_login_session_seqno);
                        utl.generateSpecialLog("process_type---", processType);

                        String executeProc = "{call LHS_TDS_IMP_TEMPLATE.proc_insert_temp_data(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, client_code);//client code
                        clst.setString(3, acc_year);//acc_year
                        clst.setInt(4, quarter_no);//quarter no.
                        java.sql.Date fromDate = new java.sql.Date(from_date.getTime());
                        clst.setDate(5, fromDate);//from date
                        java.sql.Date toDate = new java.sql.Date(to_date.getTime());
                        clst.setDate(6, toDate);//to date
                        clst.setString(7, tds_type);//tds type
                        clst.setString(8, template_code);//template code
                        clst.setLong(9, client_login_session_seqno);//client login sequence no.//SAME AS PROCESS SEQ NO LOGIC IS SET ON DB PROCEDURE
//                        clst.registerOutParameter(10, java.sql.Types.VARCHAR);//register output parameter
                        clst.setNull(10, java.sql.Types.VARCHAR);
                        clst.setString(11, userCode);//user_code
                        clst.setLong(12, processSeqNo);//client login sequence no.
                        clst.setString(13, processType);//process type code.
                        clst.execute();
//                        procOutParameter = clst.getString(10);
                        utl.generateLog("called LHS_TDS_IMP_TEMPLATE.proc_insert_temp_data---", "Upload type:EXCEL");
//                        utl.generateSpecialLog("LHS_TDS_IMP_TEMPLATE.proc_insert_temp_data ::: Return_Result_Value_by_Procedure->", procOutParameter);
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
        return "0";
    }//End Method

}//End Class
