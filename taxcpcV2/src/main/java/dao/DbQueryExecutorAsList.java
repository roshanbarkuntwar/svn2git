/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.generic.HibernateUtil.getSessionFactory;
import form15GH.transaction.ViewDeducteeMastDetailPOJO15GH;
import globalUtilities.Util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author aniket.naik
 */
public class DbQueryExecutorAsList {

    public DbQueryExecutorAsList() {
        utl = new Util();
    }
    private Util utl;

    ArrayList<ViewDeducteeMastDetailPOJO15GH> all_deductee_details_15gh = new ArrayList<ViewDeducteeMastDetailPOJO15GH>();
    ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();

    public ArrayList<ViewDeducteeMastDetailPOJO15GH> getDeductee15GHDetailsList(final String l_query, Util utl) {
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
                            ViewDeducteeMastDetailPOJO15GH result_data = new ViewDeducteeMastDetailPOJO15GH();
                            try {
                                Field[] tim_tbl_flds = result_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    String fld_name = fld.getName();
//                                    System.out.println("fld_name--"+fld_name);
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
                                all_deductee_details_15gh.add(result_data);
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
            e.printStackTrace();
            // all_deductee_details = null;
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return all_deductee_details_15gh;

    }//end method
    
    
    
    
    public ArrayList<ArrayList<String>> execute_oracle_query_as_list(final String para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(para_query);
                        rs = pstmt.executeQuery(para_query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int column_count = rsmd.getColumnCount();
                        while (rs.next()) {
                            ArrayList<String> arr = new ArrayList<String>();
                            for (int i = 0; i < column_count; i++) {
                                String col_value = rs.getString(i + 1);
                                col_value = utl.isnull(col_value) ? "" : col_value;
                                arr.add(col_value);
                            }
                            dataList.add(arr);
                        }
                    } catch (SQLException ex) {
                        dataList = null;
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
            dataList = null;
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return dataList;
    }//end method
    

}
