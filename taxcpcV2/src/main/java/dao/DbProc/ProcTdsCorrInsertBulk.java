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
public class ProcTdsCorrInsertBulk {

    String proc_out_parameter = "0";//for return value if procedure already return some value the remove this
    Util utl;

    public String execute_procedure(final String entity_code, final String client_code, final String acc_year, final String assement_acc_year, final int quarter_no, final Date from_date, final Date To_date, final String tds_type, final long client_login_session_seq_no, final String a_process_type, final String a_template_code, final String file_name) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {

                        utl.generateLog("entity_code..", entity_code);
                        utl.generateLog("client_code..", client_code);
                        utl.generateLog("acc_year..", acc_year);
                        utl.generateLog("assement_acc_year..", assement_acc_year);
                        utl.generateLog("quarter_no..", quarter_no);
                        utl.generateLog("from_date..", from_date);
                        utl.generateLog("To_date..", To_date);
                        utl.generateLog("tds_type..", tds_type);
                        utl.generateLog("client_login_session_seq_no..", client_login_session_seq_no);
                        utl.generateLog("a_process_type..", a_process_type);
                        utl.generateLog("a_template_code..", a_template_code);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_imp_corr.proc_tds_corr_insert_bulk(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, entity_code);//entity_code
                        clst.setString(2, client_code);//client code
                        clst.setString(3, acc_year);//acc_year
                        clst.setString(4, assement_acc_year);//assement_acc_year
                        clst.setInt(5, quarter_no);//quarter no.
                        clst.setDate(6, (java.sql.Date) from_date);//from date
                        clst.setDate(7, (java.sql.Date) To_date);//to date
                        clst.setString(8, tds_type);//tds type
                        clst.setLong(9, client_login_session_seq_no);//client_login_session_seq_no
                        clst.setString(10, a_process_type);//a_process_type
                        clst.setString(11, a_template_code);//a_template_code                    
                        clst.setString(12, file_name);//file_name
                        clst.registerOutParameter(13, java.sql.Types.CHAR);//register output parameter
                        clst.executeUpdate();
                        proc_out_parameter = clst.getString(13);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            proc_out_parameter = "-1";
                        }
                    } catch (SQLException ex) {//Handle Exception According to DB
                        ex.printStackTrace();
                        proc_out_parameter = "-1";
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (JDBCException e) {
            proc_out_parameter = "-1";
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return proc_out_parameter;//return 1 then no error 
    }//end method

    public ProcTdsCorrInsertBulk() {
    utl=new Util();
    }
}
