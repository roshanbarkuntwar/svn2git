/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author aniket.naik
 */
class GetMasterExcelDataListThroughWorkbook {

    boolean DeleteResult = true;

    public boolean getTempDetailDelete(final String l_query, final Util utl) {
        Session ssn = getSessionFactory().openSession();
//        ssn.beginTransaction();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        int result = pstmt.executeUpdate();
                        if (result > 0) {
                            //System.out.println("record deleted");
                        } else {
                            DeleteResult = false;
                            //System.out.println("some error occured");
                        }
                    } catch (SQLException ex) {
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }

                    }
                }

            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();// added for session problem
        } catch (Exception e) {
            DeleteResult = true;
            ssn.getTransaction().rollback();// added for session problem
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return DeleteResult;
    }//end method

}
