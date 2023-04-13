/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import static dao.generic.HibernateUtil.getSessionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.dev
 */
public class DbFunctionExecutorAsIntegar {

    Long function_output = 0l;
    int function_output_int = 0;

    public Long execute_oracle_function_as_integar(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        rs = pstmt.executeQuery(function_name_para_query);
                        while (rs.next()) {
                            function_output = rs.getLong(1);
                        }
                    } catch (SQLException ex) {
                        function_output = 0l;
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (rs != null) {
                            rs.close();
                        }
                    }
                }
            };
            ssn.doWork(work);
        } catch (Exception e) {
            function_output = 0l;
            //System.out.println("Error in Getting Function Return Value");
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method

    public int execute_oracle_update_query_integar(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    // //System.out.println("cnctn == "+cnctn+" function_name_para_query == "+function_name_para_query);
                    PreparedStatement pstmt = null;

                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        function_output_int = pstmt.executeUpdate();

                    } catch (Exception ex) {
                        function_output_int = 0;
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            function_output_int = 0;
            ssn.beginTransaction().rollback();
            //System.out.println("Error in Executing Sequence");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        ////System.out.println(result);
        return function_output_int;
    }//end method

}//End Class
