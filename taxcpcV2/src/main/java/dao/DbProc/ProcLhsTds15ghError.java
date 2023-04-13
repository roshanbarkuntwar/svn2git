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
import java.text.ParseException;
import java.util.Date;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.dev
 */
public class ProcLhsTds15ghError {

    int proc_out_parameter;
    String proc_out_parameter_15gh;
    Util utl;

    public String executeProcedure15GH(final String l_entity_code, final String l_client_code, final String l_acc_year, final int l_quarter_no,
            final Date l_from_date, final Date l_to_date, final String l_tds_type_code, final int l_process_level, final String user_code, final String process_seq_no) throws ParseException {
        utl.generateLog("l_entity_code---", l_entity_code);
        utl.generateLog("l_client_code---", l_client_code);
        utl.generateLog("l_acc_year---", l_acc_year);
        utl.generateLog("l_quarter_no---", l_quarter_no);
        utl.generateLog("l_from_date---", l_from_date);
        utl.generateLog("l_to_date---", l_to_date);
        utl.generateLog("l_tds_type_code---", l_tds_type_code);
        utl.generateLog("l_process_level---", l_process_level);
        utl.generateLog("a_user_code---", user_code);
        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
//                        String executeProc = "{call LHS_TDS_15GH.lhs_tds_15gh_error(?,?,?,?,to_date(?,'dd-mon-rrrr'),to_date(?,'dd-mon-rrrr'),?,?,?)}";
                        String executeProc = "{call LHS_TDS_15GH.lhs_tds_15gh_error(?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no

                        java.sql.Date fromDate = new java.sql.Date(l_from_date.getTime());
                        clst.setDate(5, fromDate);//from_date

                        java.sql.Date ToDate = new java.sql.Date(l_to_date.getTime());
                        clst.setDate(6, ToDate);//to_date

                        clst.setString(7, l_tds_type_code);//tds_type_code                       
                        clst.setNull(8, java.sql.Types.VARCHAR);//tds_type_code                       
//                        clst.registerOutParameter(8, java.sql.Types.CHAR);//o
                        clst.setInt(9, l_process_level);//process level
                        clst.setString(10, user_code);//tds_type_code
                        clst.setString(11, process_seq_no);//tds_type_code
                        clst.executeUpdate();
//                        proc_out_parameter_15gh = clst.getString(8);

//                        utl.generateSpecialLog("LHS_TDS_15GH.lhs_tds_15gh_error--125--", proc_out_parameter_15gh);
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
        return "0";
    }//end method

    public ProcLhsTds15ghError() {
        utl = new Util();
    }//end constructor
}//end class
