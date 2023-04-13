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
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcForm16ATxt {

    String return_result;
    private final Util utl;

    public ProcForm16ATxt(Util utl) {
        this.utl = utl;
    }

    public String executeProcedure(final String l_entity_code,
            final String l_client_code,
            final String l_acc_year,
            final int l_quarter_no,
            final String l_tds_type_code,
            final String a_file_name,
            final String a_where_clause,
            final int a_process_seqno) {

        Session ssn = getSessionFactory().openSession();

        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {

                        utl.generateLog("Proc_form16a_txt parameters", "");
                        utl.generateLog("l_entity_code......", l_entity_code);
                        utl.generateLog("l_client_code......", l_client_code);
                        utl.generateLog("l_acc_year......", l_acc_year);
                        utl.generateLog("l_quarter_no......", l_quarter_no);
                        utl.generateLog("l_tds_type_code......", l_tds_type_code);
                        utl.generateLog("a_file_name......", a_file_name);
                        utl.generateLog("a_where_clause......", a_where_clause);
                        utl.generateLog("a_process_seqno......", a_process_seqno);

                        CallableStatement clst;
                        String executeProc = "{call lhs_tds_gen_fvutxt.proc_form16a_txt(?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);

                        clst.setString(1, l_entity_code);//entity_code data
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no
                        clst.setString(5, l_tds_type_code);//tds_type_code                       
                        clst.setString(6, a_file_name);//a_file_name                       
                        clst.setString(7, a_where_clause);//a_where_clause                       
                        clst.registerOutParameter(8, java.sql.Types.VARCHAR);
                        clst.setInt(9, a_process_seqno);//a_process_seqno                       

                        clst.executeUpdate();
                        return_result = clst.getString(8);

                        utl.generateLog("Procedure called lhs_tds_gen_fvutxt.proc_form16a_txt......", return_result);
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
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
        } finally {
            ssn.close();
        }

        return return_result;
    }//end method

}
