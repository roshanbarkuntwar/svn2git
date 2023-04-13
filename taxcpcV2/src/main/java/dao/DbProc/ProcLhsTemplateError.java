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
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.dev
 */
public class ProcLhsTemplateError {

    int return_result;
    Util utl;

    public ProcLhsTemplateError() {
        utl = new Util();
    }
    
    public int execute_procedure(final String l_entity_code, final String l_client_code, final String l_acc_year, final int l_quarter_no, final Date l_from_date, final Date l_to_date, final String l_tds_type_code, final String l_templete_code, final Long a_client_login_session_seqno, final String userCode, final long processSeqNo, final String processType) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("l_entity_code......", l_entity_code);
                        utl.generateLog("l_client_code......", l_client_code);
                        utl.generateLog("l_acc_year......", l_acc_year);
                        utl.generateLog("l_quarter_no......", l_quarter_no);
                        utl.generateLog("l_from_date......", l_from_date);
                        utl.generateLog("l_to_date......", l_to_date);
                        utl.generateLog("l_tds_type_code......", l_tds_type_code);
                        utl.generateLog("l_templete_code......", l_templete_code);
                        utl.generateLog("a_client_login_session_seqno......", a_client_login_session_seqno);
                        utl.generateLog("userCode......", userCode);
                        utl.generateLog("processSeqNo......", processSeqNo);

                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS_IMP_TEMPLATE.proc_lhs_template_error(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code data
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no
                        java.util.Date utilFromDate = l_from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                        clst.setDate(5, sqlFromDate);//from_date
                        java.util.Date utilToDate = l_to_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                        clst.setDate(6, sqlToDate);//to_date
                        clst.setString(7, l_tds_type_code);//tds_type_code                       
                        clst.setString(8, l_templete_code);//l_templete_code
                        clst.setLong(9, a_client_login_session_seqno);//login session seqno                      
                        clst.setNull(10,  java.sql.Types.VARCHAR);//set null as no use of out parametrer
                        clst.setString(11, userCode);//l                    
                        clst.setLong(12, processSeqNo);//                     
                        clst.setString(13, processType);//                  
                        clst.executeUpdate();
//                        return_result = clst.getInt(10);
                        utl.generateLog("LHS_TDS_IMP_TEMPLATE.proc_lhs_template_error   ::: Return_Result_Value_by_Procedure->", return_result);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            //utl.generateLog("exception message........" , e.getMessage());
        } finally {
            ssn.close();
        }
//        //utl.generateLog("return_result......" , return_result);
        return return_result;
    }//end method

}//end class
