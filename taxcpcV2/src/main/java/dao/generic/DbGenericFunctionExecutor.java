/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import regular.dashboard.processDetails.ProcessDetailBean;

/**
 *
 * @author ayushi.jain
 */
public class DbGenericFunctionExecutor {

    Util utl;

    public DbGenericFunctionExecutor() {
        utl = new Util();
    }
    public <T> ArrayList<T> getGenericList(final T object, final String l_query) {
        final ArrayList<T> all_deductee_details = new ArrayList<>();
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //format of date got from database.
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            
                            boolean record_fetched = true;
                            T result_data = null;
                            try {
                                result_data = (T) object.getClass().newInstance();
                            } catch (InstantiationException ex) {
                                Logger.getLogger(DbGenericFunctionExecutor.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(DbGenericFunctionExecutor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                Field[] tim_tbl_flds = result_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    fld.setAccessible(true);

                                    String fld_name = fld.getName();
                                    String fld_value = "";
                                    try {
                                        fld_value = rs.getString(fld_name);
                                    } catch (Exception e) {
                                    }
                                    fld_value = utl.isnull(fld_value) ? "" : fld_value;

                                    Class fldType = fld.getType();
                                    if (fldType.toString().equals("class java.util.Date")) {
                                        try {
                                            fld.set(result_data, sdf.parse(fld_value));
                                        } catch (ParseException ex) {
                                            //System.out.println("Error " + ex.getCause());
                                        }
                                    } else if (fldType.toString().equals("class java.lang.Double")) {
                                        try {
                                            fld.set(result_data, Double.valueOf(fld_value));
                                        } catch (Exception e) {
                                        }
                                    } else {
                                        fld.set(result_data, fld_value);
                                    }
                                }//end for
                            } catch (SecurityException e) {
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
                                all_deductee_details.add(result_data);
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

        } finally {
            ssn.close();
        }
        return all_deductee_details;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public <T> T getGenericWorkInterfaceExecuter(final T entity, final String para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        pstmt = cnctn.prepareStatement(para_query);
                        rs = pstmt.executeQuery(para_query);
                        while (rs.next()) {
                            Field[] all_dtls = entity.getClass().getDeclaredFields();
                            for (Field dtl : all_dtls) {
                                try {
                                    String detail_name = dtl.getName();
                                    String detail_value = rs.getString(detail_name);
                                    detail_value = utl.isnull(detail_value) ? "" : detail_value;
                                    dtl.set(entity, detail_value);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }//end for
                        }
                    } catch (SQLException ex) {
                    }
                }
            };
            ssn.doWork(work);
            // commented due to logic changes ...  HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ssn.close();
        }

        return (T) entity;
    }//end method

}
