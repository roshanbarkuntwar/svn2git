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
import java.sql.Types;
import java.util.Date;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author ayushi.jain
 */
public class ProcTdsChallanRebuild {

    Util utl;
    String result = "0";

    public ProcTdsChallanRebuild() {
        utl = new Util();
    }

    public String executeProcedure(final String a_entity_code, final String a_client_code, final String a_acc_year, final String a_quarter_no, final Date a_from_date, final Date a_to_date, final String a_tds_type_code, final Integer a_tds_challan_rowid_seq, final String flag) {
        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                      utl.generateLog("procedure call Lhs_tds.proc_tds_challan_rebuild", "");
                        utl.generateLog("a_entity_code---", a_entity_code);
                        utl.generateLog("a_client_code---", a_client_code);
                        utl.generateLog("a_acc_year---", a_acc_year);
                        utl.generateLog("a_quarter_no---", a_quarter_no);
                        utl.generateLog("a_from_date---", a_from_date);
                        utl.generateLog("a_to_date---", a_to_date);
                        utl.generateLog("a_tds_type_code---", a_tds_type_code);
                        utl.generateLog("a_tds_challan_rowid_seq--", a_tds_challan_rowid_seq);
                        utl.generateLog("flag--", flag);

                        CallableStatement cs;
                        String proc = "{call Lhs_tds.proc_tds_challan_rebuild(?,?,?,?,?,?,?,?,?)}";
                        cs = cnctn.prepareCall(proc);
                        cs.setString(1, a_entity_code);//entity code
                        cs.setString(2, a_client_code);//client code
                        cs.setString(3, a_acc_year);//acc_year
                        cs.setString(4, a_quarter_no);//quarter no.
                        java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                        cs.setDate(5, sql_a_from_date);//from date
                        java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                        cs.setDate(6, sql_a_to_date);//to date
                        cs.setString(7, a_tds_type_code);//tds type
                        if (flag.equalsIgnoreCase("D")) {
                            cs.setNull(8, java.sql.Types.INTEGER);
                        } else {
                            cs.setInt(8, a_tds_challan_rowid_seq);//challan seq no.
                        }
                        cs.registerOutParameter(9, Types.VARCHAR);//out parameter
                        cs.executeUpdate();

                        result = cs.getString(9);
                        
                        System.out.println("procedure result---"+result);
                        try {
                            cs.close();
                        } catch (SQLException e) {
                            result = "-1";
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            };

            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        } catch (JDBCException ex) {
            result = "-1";
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
}
