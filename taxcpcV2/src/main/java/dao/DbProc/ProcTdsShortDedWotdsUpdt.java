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
public class ProcTdsShortDedWotdsUpdt {

    String proc_out_parameter;
    Util utl;

    public ProcTdsShortDedWotdsUpdt() {
        utl = new Util();
    }

    public String exceuteProcedure(final String entity_code, final String client_code, final String accYear, final String l_quarter_no, final Date l_from_date, final Date l_to_date, final String tdsTypeCode, final Integer rowidseq) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("l_entity_code......", entity_code);
                        utl.generateLog("l_client_code......", client_code);
                        utl.generateLog("l_acc_year......", accYear);
                        utl.generateLog("l_quarter_no......", l_quarter_no);
                        utl.generateLog("l_from_date......", l_from_date);
                        utl.generateLog("l_to_date......", l_to_date);
                        utl.generateLog("l_tds_type_code......", tdsTypeCode);
                        utl.generateLog("rowidseq......", null);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds.proc_tds_short_ded_wotds_updt(?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code data
                        clst.setString(2, client_code);//client_code
                        clst.setString(3, accYear);//acc_year

                        clst.setString(4, l_quarter_no);//quarter_no
                        java.sql.Date sqlFromDate = new java.sql.Date(l_from_date.getTime());                        
                        clst.setDate(5, (java.sql.Date) sqlFromDate);//from_date
                        
                        java.sql.Date sqlToDate = new java.sql.Date(l_to_date.getTime());
                        clst.setDate(6, (java.sql.Date) sqlToDate);//to_date
                        clst.setString(7, tdsTypeCode);//tds_type_code                       
                        clst.setNull(8, java.sql.Types.INTEGER);

                        clst.registerOutParameter(9, java.sql.Types.CHAR);
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(14);
                        utl.generateSpecialLog("Procedure call proc_tds_short_ded_wotds_updt---  ", proc_out_parameter + "\n");
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
    }//End Method

}
