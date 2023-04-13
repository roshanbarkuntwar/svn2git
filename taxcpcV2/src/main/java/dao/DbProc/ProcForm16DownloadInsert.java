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
 * @author ayushi.jain
 */
public class ProcForm16DownloadInsert {

    String return_result;
    private Util utl;

    public ProcForm16DownloadInsert() {
        utl = new Util();
    }

    public String executeProcedure(final String l_entity_code,
            final String l_client_code,
            final String l_acc_year,
            final int l_quarter_no,
            final Date l_from_date,
            final Date l_to_date,
            final String l_tds_type_code,
            final Long a_client_login_session_seqno,
            final String a_process_type,
            final String a_tds_return_type,
            final String userCode,
            final Long processSeqNo) {
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
                        utl.generateLog("a_client_login_session_seqno......", a_client_login_session_seqno);
                        utl.generateLog("a_process_type......", a_process_type);

                        utl.generateLog("userCode......", userCode);
                        utl.generateLog("a_tds_return_type......", a_tds_return_type);
                        utl.generateLog("processSeqNo......", processSeqNo);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_gen_fvutxt.proc_form16_download_insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code data
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year

                        clst.setInt(4, l_quarter_no);//quarter_no
//                        clst.setDate(6, (java.sql.Date) l_from_date);//from_date
//                        clst.setDate(7, (java.sql.Date) l_to_date);//to_date
                        java.util.Date utilFromDate = l_from_date;
                        java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                        //System.out.println("sqlFromDate.." + sqlFromDate);
                        clst.setDate(5, sqlFromDate);//from_date
                        java.util.Date utilToDate = l_to_date;
                        java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                        //System.out.println("sqlToDate.." + sqlToDate);
                        clst.setDate(6, sqlToDate);//to_date
                        clst.setString(7, l_tds_type_code);//tds_type_code                       
                        clst.setLong(8, a_client_login_session_seqno);//login session seqno                      
                        clst.setString(9, a_tds_return_type);//a_tds_return_type
                        clst.setString(10, a_process_type);//a_process_type
                        clst.registerOutParameter(11, java.sql.Types.CHAR);

                        clst.setString(12, userCode);//a_templete_code
                        clst.setLong(13, processSeqNo);//a_templete_code
                        clst.executeUpdate();
                        return_result = clst.getString(11);
                        utl.generateLog("lhs_tds_gen_fvutxt.proc_form16_download_insert......", return_result);
                        //System.out.println("Procedure call");
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
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
            //System.out.println("exception message........" + e.getMessage());
        } finally {
            ssn.close();
        }
//        //System.out.println("return_result......" + return_result);
        return return_result;
    }//end method

}//end class
