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
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author ayushi.jain
 */
public class ProcInsertSalaryAmtTran {

    String proc_out_parameter;
    Util utl;

    public String execureProcedure(final String entityCode, final String clientCode, final String accYear, final String tdsTypeCode, final Long quarterNo, final String deducteeCode, final Long rowidSeq, final String iudFlag) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateLog("entityCode--", entityCode);
                        utl.generateLog("clientCode--", clientCode);
                        utl.generateLog("accYear--", accYear);
                        utl.generateLog("tdsTypeCode--", tdsTypeCode);
                        utl.generateLog("quarterNo--", quarterNo);
                        utl.generateLog("deducteeCode--", deducteeCode);
                        utl.generateLog("rowidSeq--", rowidSeq);
                        utl.generateLog("iudFlag--", iudFlag);
                        CallableStatement clst;
                        String executeProc = "{call lhs_hrd.proc_insert_salary_amt_Tran(?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entityCode);//a_entity_code
                        clst.setString(2, clientCode);//a_client_code
                        clst.setString(3, accYear);//a_acc_year
                        clst.setString(4, tdsTypeCode);//a_assement_acc_year
                        clst.setLong(5, quarterNo);//a_quarter_no
                        clst.setString(6, deducteeCode);//deducteeCode
                        clst.setLong(7, rowidSeq);//rowidSeq
                        clst.setString(9, iudFlag);//a_tds_type_code

                        clst.registerOutParameter(8, java.sql.Types.CHAR);//o
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(8);
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
    }// function end 

    public ProcInsertSalaryAmtTran() {
        utl = new Util();
    }

}//class end
