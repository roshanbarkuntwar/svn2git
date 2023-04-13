/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.opensymphony.xwork2.ActionSupport;
import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author aniket.naik
 */
public class GetDeductee15GHDataList extends ActionSupport {

    Util utl;

    public GetDeductee15GHDataList() {

        utl = new Util();

    }

    ArrayList<ReferenceNoDetailPOJO> all_details = new ArrayList<ReferenceNoDetailPOJO>();

    public ArrayList<ReferenceNoDetailPOJO> getDeducteeRefranceNoDetailsList(final String l_query, final Util utl) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            boolean record_fetched = true;
                            ReferenceNoDetailPOJO result_data = new ReferenceNoDetailPOJO();
                            try {
                                Field[] tim_tbl_flds = result_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    String fld_name = fld.getName();
                                    String fld_value = rs.getString(fld_name);
                                    fld_value = utl.isnull(fld_value) ? "" : fld_value;
                                    fld.set(result_data, fld_value);
                                }//end for
                            } catch (SecurityException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (SQLException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            }
                            if (record_fetched) {
                                all_details.add(result_data);
                            }
                        }//end while
                    } catch (SQLException ex) {
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
            all_details = null;
        } finally {
            ssn.close();
        }
        return all_details;

    }

}
