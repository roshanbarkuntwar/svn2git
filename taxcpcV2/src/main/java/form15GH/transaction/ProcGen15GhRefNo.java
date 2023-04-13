/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

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
 * @author aniket.naik
 */
public class ProcGen15GhRefNo {

    String proc_out_parameter;
    Util utl;

    public ProcGen15GhRefNo() {

        utl = new Util();
    }

    public String executepProcGen15ghRefno(final String entity_code, final String client_code, final String accYear, final String assessmentYear, final int l_quarter_no, final String month, final Date l_from_date, final Date l_to_date, final String tdsTypeCode, final String nextGen15GNo, final String nextGen15HNo, final String processType, final Long l_client_loginid_seq) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("l_entity_code......", entity_code);
                        utl.generateLog("l_client_code......", client_code);
                        utl.generateLog("l_acc_year......", accYear);
                        utl.generateLog("l_assessmant_year......", assessmentYear);
                        utl.generateLog("l_quarter_no......", l_quarter_no);
                        utl.generateLog("month......", month);
                        utl.generateLog("l_from_date......", l_from_date);
                        utl.generateLog("l_to_date......", l_to_date);
                        utl.generateLog("l_tds_type_code......", tdsTypeCode);
                        utl.generateLog("nextGen15GNo......", nextGen15GNo);
                        utl.generateLog("nextGen15HNo......", nextGen15HNo);
                        utl.generateLog("a_process_type......", processType);
                        utl.generateLog("a_client_login_session_seqno......", l_client_loginid_seq);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_15gh.proc_gen_15gh_refno(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code data
                        clst.setString(2, client_code);//client_code
                        clst.setString(3, accYear);//acc_year
                        clst.setString(4, assessmentYear);//acc_year
                        clst.setInt(5, l_quarter_no);//quarter_no
                        clst.setString(6, month);//month  
                        java.sql.Date fromDate = new java.sql.Date(l_from_date.getTime());
                        clst.setDate(7, fromDate);//from_date
                        java.sql.Date ToDate = new java.sql.Date(l_to_date.getTime());
                        clst.setDate(8, ToDate);//to_date
                        clst.setString(9, tdsTypeCode);//tds_type_code                       
                        clst.setString(10, nextGen15GNo);//nextGen15GNo                       
                        clst.setString(11, nextGen15HNo);//nextGen15HNo                       
                        clst.setString(12, processType);//a_process_type
                        clst.setLong(13, l_client_loginid_seq);//login session seqno                      
                        clst.registerOutParameter(14, java.sql.Types.CHAR);
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(14);
                        utl.generateSpecialLog("Procedure call lhs_tds_15gh.proc_gen_15gh_refno  ", proc_out_parameter + "\n");
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
            if (!utl.isnull(proc_out_parameter) && proc_out_parameter.equalsIgnoreCase("0")) {
                ssn.getTransaction().commit();
            } else {
                ssn.getTransaction().rollback();
            }
        } catch (JDBCException e) {
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return proc_out_parameter;

    }

}
