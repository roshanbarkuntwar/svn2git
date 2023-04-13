/*
 * To change this template, choose Tools | Templates
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
 * @author bhawna.agrawal
 */
public class SetDatabaseValues {

    boolean clientCodeReturn = true;
    boolean entityReturn = true;
    boolean accYearReturn = true;
    boolean quarterReturn = true;
    boolean tdsTypeReturn = true;

    public boolean setClientCode(final String clientCode) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_UTILITY.set_client_code(?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, clientCode);//clientCode      
                        clst.executeUpdate();
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                            clientCodeReturn = false;
                        }
                    } catch (SQLException ex) {                        //System.out.println(ex.getMessage());
                        clientCodeReturn = false;
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            //System.out.println(e.getMessage());
            clientCodeReturn = false;
        } finally {
            ssn.close();
        }
        return clientCodeReturn;
    }// end method

    public boolean setEntityCode(final String entityCode) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_UTILITY.set_entity_code(?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entityCode);//entity_code      
                        clst.executeUpdate();
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                            entityReturn = false;
                        }
                    } catch (SQLException ex) {                        //System.out.println(ex.getMessage());
                        entityReturn = false;
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            //System.out.println(e.getMessage());
            entityReturn = false;
        } finally {
            ssn.close();
        }
        return entityReturn;
    }// end method

    public boolean setAccYear(final String accYear) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_UTILITY.set_acc_year(?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, accYear);//entity_code      
                        clst.executeUpdate();
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                            accYearReturn = false;
                        }
                    } catch (SQLException ex) {                        //System.out.println(ex.getMessage());
                        accYearReturn = false;
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            //System.out.println(e.getMessage());
            accYearReturn = false;
        } finally {
            ssn.close();
        }
        return accYearReturn;
    }// end method

    public boolean setQuarterNo(final int quarterNo) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_UTILITY.set_quarter_no(?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setInt(1, quarterNo);//entity_code      
                        clst.executeUpdate();
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                            quarterReturn = false;
                        }
                    } catch (SQLException ex) {                        //System.out.println(ex.getMessage());
                        quarterReturn = false;
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            //System.out.println(e.getMessage());
            quarterReturn = false;
        } finally {
            ssn.close();
        }
        return quarterReturn;
    }// end method

    public boolean setTdsTypeCode(final String tdsTypeCode) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        CallableStatement clst;
                        String executeProc = "{call LHS_UTILITY.set_tds_type_code(?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, tdsTypeCode);//tds_type_code      
                        clst.executeUpdate();
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
                            tdsTypeReturn = false;
                        }
                    } catch (SQLException ex) {                        //System.out.println(ex.getMessage());
                        tdsTypeReturn = false;
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            //System.out.println(e.getMessage());
            tdsTypeReturn = false;
        } finally {
            ssn.close();
        }
        return tdsTypeReturn;
    }// end method

    public SetDatabaseValues() {
        utl = new Util();
    }
    Util utl;
}
