/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import static dao.generic.HibernateUtil.getSessionFactory;
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
 * @author akash.dev
 */
public class GetDataListThroughWorkbook1 {

    Util utl;

    public GetDataListThroughWorkbook1() {
        utl = new Util();
    }//end constructor

    ArrayList<ViewTranLoadErrorBean> all_details = new ArrayList<ViewTranLoadErrorBean>();

    public ArrayList<ViewTranLoadErrorBean> getTranLoadErrorSummaryDetailsList(final String l_query, final Util utl) {
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
                            ViewTranLoadErrorBean result_data = new ViewTranLoadErrorBean();
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

    }//end method  

    ArrayList<ViewTranLoadErrorDetailsBean> all_Load_error_details = new ArrayList<ViewTranLoadErrorDetailsBean>();

    public ArrayList<ViewTranLoadErrorDetailsBean> readAllErrorDetailAsErrorType(final String l_query, final Util utl) {

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
                            ViewTranLoadErrorDetailsBean result_data = new ViewTranLoadErrorDetailsBean();
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
                                all_Load_error_details.add(result_data);
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
            all_Load_error_details = null;
        } finally {
            ssn.close();
        }
        return all_Load_error_details;

    }//end method

    ArrayList<ShowErrorDetailsBean> all_error_list_details = new ArrayList<ShowErrorDetailsBean>();

    public ArrayList<ShowErrorDetailsBean> getErrorDetailsList(final String l_query, final Util utl) {
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
                            ShowErrorDetailsBean result_data = new ShowErrorDetailsBean();
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
                                all_error_list_details.add(result_data);
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
            all_error_list_details = null;
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return all_error_list_details;

    }//end method 

    ArrayList<ShowErrorSummaryDetailsBean> all_error_details = new ArrayList<ShowErrorSummaryDetailsBean>();

    public ArrayList<ShowErrorSummaryDetailsBean> getErrorSummaryDetailsList(final String l_query, final Util utl) {
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
                            ShowErrorSummaryDetailsBean result_data = new ShowErrorSummaryDetailsBean();
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
                                all_error_details.add(result_data);
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
            all_error_details = null;
        } finally {
            ssn.close();
        }
        return all_error_details;

    }//end method 

    ArrayList<ShowSalaryErrorSummaryDetailsBean> all_salary_error_details = new ArrayList<ShowSalaryErrorSummaryDetailsBean>();

    public ArrayList<ShowSalaryErrorSummaryDetailsBean> getSalaryErrorSummaryDetailsList(final String l_query, final Util utl) {
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
                            ShowSalaryErrorSummaryDetailsBean result_data = new ShowSalaryErrorSummaryDetailsBean();
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
                                all_salary_error_details.add(result_data);
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
            all_salary_error_details = null;
        } finally {
            ssn.close();
        }
        return all_salary_error_details;

    }//end method 

    ArrayList<ShowChallanErrorDetailsBean> all_challan_error_details = new ArrayList<ShowChallanErrorDetailsBean>();

    public ArrayList<ShowChallanErrorDetailsBean> getChallanErrorDetailsList(final String l_query, final Util utl) {
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
                            ShowChallanErrorDetailsBean result_data = new ShowChallanErrorDetailsBean();
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
                                all_challan_error_details.add(result_data);
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
            all_challan_error_details = null;
        } finally {
            ssn.close();
        }
        return all_challan_error_details;

    }//end method 

    ArrayList<String> arrList = new ArrayList<String>();

    public ArrayList<String> execute_oracle_query_as_arrlist(final String para_query) {
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
                            for (int i = 0; i < column_count; i++) {
                                String col_value = rs.getString(i + 1);
                                col_value = utl.isnull(col_value) ? "" : col_value;
                                arrList.add(col_value);
                            }
                        }
                    } catch (SQLException ex) {
                        arrList = null;
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
            arrList = null;
            e.printStackTrace();
        } finally {
            ssn.close();
        }
        return arrList;
    }//end method

}//end class
