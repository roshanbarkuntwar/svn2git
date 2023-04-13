/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
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
 * @author akash.dev
 */
public class GetMasterExcelDataListThroughWorkbook {

    int Result = 0;

//    public int getTotalRecordCount(final String l_query, final Util utl) {
//        Session ssn = getSessionFactory().openSession();
//        try {
//            Work work = new Work() {
//                @Override
//                public void execute(Connection cnctn) throws SQLException {
//                    PreparedStatement pstmt = null;
//                    ResultSet rs = null;
//                    try {
//                        pstmt = cnctn.prepareStatement(l_query);
//                        rs = pstmt.executeQuery();
//                        while (rs.next()) {
//                            String l_totalCount = rs.getString(1);
//                            l_totalCount = utl.isnull(l_totalCount) ? "0" : l_totalCount;
//                            Result = Integer.parseInt(l_totalCount);
//                        }
//                    } catch (SQLException ex) {
//                    } finally {
//                        if (pstmt != null) {
//                            pstmt.close();
//                        }
//                        if (rs != null) {
//                            rs.close();
//                        }
//                    }
//                }
//            };
//            ssn.beginTransaction();// added for session problem
//            ssn.doWork(work);
//            ssn.getTransaction().commit();// added for session problem
//        } catch (Exception e) {
//            ssn.getTransaction().rollback();// added for session problem
//            return 0;
//            //System.out.println("Error in Getting Query Data");
//            //System.out.println(e.getMessage());
//        } finally {
//            ssn.close();
//        }
//        return Result;
//
//    }//end method
    ArrayList<ArrayList<String>> tempDataErrordataList = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> getTempDataErrorDetail(final String l_query, final Util utl) {
        Session ssn = getSessionFactory().openSession();
        //System.out.println("l_query..." + l_query);
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        rs = pstmt.executeQuery();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int column_count = rsmd.getColumnCount();
                        //System.out.println("column_count..." + column_count);
                        while (rs.next()) {
                            ArrayList<String> arr = new ArrayList<String>();
                            for (int i = 0; i < column_count; i++) {
                                //System.out.println("i value..." + i);
                                String col_value = rs.getString(i + 1);
                                col_value = utl.isnull(col_value) ? "" : col_value;
                                arr.add(col_value);
                            }
                            tempDataErrordataList.add(arr);
                        }
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
            ssn.beginTransaction();// added for session problem
            ssn.doWork(work);
            ssn.getTransaction().commit();// added for session problem
        } catch (Exception e) {
            tempDataErrordataList = null;
            ssn.getTransaction().rollback();// added for session problem
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return tempDataErrordataList;
    }//end method 

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

    ArrayList<ArrayList<String>> EnginedataList = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> getEngineColDataAsArrayList(final String l_query, final Util utl) {
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
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int column_count = rsmd.getColumnCount();
//            //System.out.println("column_count..." + column_count);
                        while (rs.next()) {
                            ArrayList<String> arr = new ArrayList<String>();
                            for (int i = 0; i < column_count; i++) {
//                    //System.out.println("i value..." + i);
                                String col_value = rs.getString(i + 1);
                                col_value = utl.isnull(col_value) ? "" : col_value;
                                arr.add(col_value);
                            }
                            EnginedataList.add(arr);
                        }
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
            ssn.beginTransaction();// added for session problem
            ssn.doWork(work);
            ssn.getTransaction().commit();// added for session problem
        } catch (Exception e) {
            tempDataErrordataList = null;
            ssn.getTransaction().rollback();// added for session problem
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return EnginedataList;
    }//end method

    ArrayList<String> engColmDataList = new ArrayList<String>();

    public ArrayList<String> getEngineColDataAsList(final String l_query, final Util utl) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        rs = pstmt.executeQuery(l_query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int column_count = rsmd.getColumnCount();
                        while (rs.next()) {
                            for (int i = 0; i < column_count; i++) {
                                String col_value = rs.getString(i + 1);
                                col_value = utl.isnull(col_value) ? "" : col_value;
                                engColmDataList.add(col_value);
                            }
                        }
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
            ssn.beginTransaction();// added for session problem
            ssn.doWork(work);
            ssn.getTransaction().commit();// added for session problem
        } catch (Exception e) {
            engColmDataList = null;
            ssn.getTransaction().rollback();// added for session problem
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return engColmDataList;
    }//end method

}//end class
