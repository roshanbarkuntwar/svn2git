/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysCorrFileUploadTran;
import hibernateObjects.LhssysTemplate;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import regular.deductorInfo.GeneratedBflagPojo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akash.dev
 */
public class DbFunctionExecutorAsString {

    String function_output = "";
    ArrayList<String> list = null;
    boolean tokenNoExist = false;
    Util utl;
    ArrayList<ArrayList<String>> headingAndDataList;
    ArrayList<ArrayList<String>> dataList;
    ArrayList<GeneratedBflagPojo> showFlagList;
    boolean b1 = false;
      int retvalue_row;    

    public DbFunctionExecutorAsString() {
        utl = new Util();
    }

    public String execute_oracle_function_as_string(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
//                        System.out.println("function_name_para_query--"+function_name_para_query);
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        rs = pstmt.executeQuery(function_name_para_query);
                        while (rs.next()) {
                            function_output = rs.getString(1);
                        }
                    }catch (SQLException ex) {
                        function_output = "";
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (Exception e) {
            function_output = "";
            ssn.beginTransaction().rollback();
            utl.generateLog(null, "Error in Executing Sequence..");
            utl.generateLog(null, e.getMessage());
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method

    public boolean execute_oracle_update_function_as_string(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;

                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        int retvalue = pstmt.executeUpdate(function_name_para_query);
                        if (retvalue > 0) {
                            b1 = true;

                        } else {
                            b1 = false;
                        }
                    } catch (SQLException ex) {
                        b1 = false;
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
            b1 = false;
            ssn.beginTransaction().rollback();
            utl.generateLog("Error in Executing Sequence--", e.getMessage());
//            System.out.println();
        } finally {
            ssn.close();
        }
        return b1;
    }//end method

    public int execute_oracle_update_function_returnAffected_rows(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
       retvalue_row=0;       
        try {
            
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    try {
                        
                       pstmt = cnctn.prepareStatement(function_name_para_query);
                       retvalue_row = pstmt.executeUpdate(function_name_para_query);
                        
                       
                    } catch (SQLException ex) {
//                        b1 = false;
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
//            b1 = false;
            ssn.beginTransaction().rollback();
            utl.generateLog("Error in Executing Sequence--", e.getMessage());
//            System.out.println();
        } finally {
            ssn.close();
        }
        return retvalue_row;
    }//end method
    
    public boolean execute_oracle_update_function_as_string_using_external_session(final String function_name_para_query, Session ssn, Transaction tx) {
//        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;

                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        int retvalue = pstmt.executeUpdate(function_name_para_query);
                        if (retvalue > 0) {
                            b1 = true;

                        } else {
                            b1 = false;
                        }
                    } catch (SQLException ex) {
                        b1 = false;
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                }
            };
            //  ssn.beginTransaction();
            ssn.doWork(work);
//            ssn.beginTransaction().commit();
        } catch (Exception e) {
            b1 = false;
            tx.rollback();
            utl.generateLog("Error in Executing Sequence--", e.getMessage());
//            System.out.println();
        }
        return b1;
    }//end method

    public String get_name(final String a_code_name, final String a_code) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement("select lhs_tds.get_name(?,?) from dual");
                        pstmt.setString(1, a_code_name);
                        pstmt.setString(2, a_code);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            function_output = rs.getString(1);
                        }
                    } catch (SQLException ex) {
                        function_output = "";
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            function_output = "";
            ssn.beginTransaction().rollback();
            //System.out.println("Error in Getting Function GET_NAME");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method

    public ArrayList<String> getResultAsList(final String function_name_para_query) {
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
                        if (rs != null) {
                            list = new ArrayList<String>();
                            while (rs.next()) {
                                list.add(rs.getString(1));
                            }
                        }

                    } catch (SQLException ex) {
                        list = null;
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
            list = null;
            //System.out.println("Error in Executing Sequence");
            System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return list;
    }//end method
    boolean result = false;

    public boolean execute_oracle_update_query_for_panDetail(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    // //System.out.println("cnctn == "+cnctn+" function_name_para_query == "+function_name_para_query);
                    PreparedStatement pstmt = null;

                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        ////System.out.println("pstmt == "+pstmt);
                        int retvalue = pstmt.executeUpdate();
                        // //System.out.println("retvalue == "+retvalue);
                        if (retvalue > 0) {
                            result = true;

                        } else {
                            result = false;
                        }
                    } catch (Exception ex) {
                        result = false;
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
            result = false;
            ssn.beginTransaction().rollback();
            //System.out.println("Error in Executing Sequence");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        ////System.out.println(result);
        return result;
    }//end method

    public boolean execute_function_For_file_tran(final String function_name_para_query) {
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
                            String tokenNo = "";
                            String tokenDate = "";
                            //System.out.println("values =="+rs.getString(1)+" "+rs.getString(2));
                            tokenNo = rs.getString(1);
                            tokenDate = rs.getString(2);

                            if (tokenNo == null || tokenDate == null) {
                                tokenNoExist = false;
                            } else if (!tokenNo.equals("") && !tokenDate.equals("")) {
                                tokenNoExist = true;
                            } else {
                                tokenNoExist = false;
                            }
                        }
                    } catch (SQLException ex) {
                        tokenNoExist = true;
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (Exception e) {
            tokenNoExist = true;
            ssn.beginTransaction().rollback();
            //System.out.println("Error in Executing Sequence");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return tokenNoExist;
    }//end method

    public ArrayList<ArrayList<String>> execute_oracle_query_as_list(final String para_query) {
        dataList = new ArrayList<ArrayList<String>>();
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

    public ArrayList<ArrayList<String>> getHeadingsAndDataAsListOfList(String query) {
        headingAndDataList = new ArrayList<>();
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        ArrayList<String> headings = new ArrayList<String>();
                        pstmt = cnctn.prepareStatement(query);
                        rs = pstmt.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int column_count = rsmd.getColumnCount();
//                        System.out.println("column_count--" + column_count);
                        for (int i = 1; i <= column_count; i++) {
                            headings.add(rsmd.getColumnName(i));
                        }
                        headingAndDataList.add(headings);
                        while (rs.next()) {
                            ArrayList<String> data = new ArrayList<String>();
                            for (int i = 1; i <= column_count; i++) {
                                String col_value = rs.getString(i);
                                col_value = (col_value == null) ? "" : col_value;
                                data.add(col_value);
                            }
                            headingAndDataList.add(data);
                        }
                    } catch (SQLException ex) {
                        headingAndDataList = null;
                        ex.printStackTrace();
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (Exception e) {
            headingAndDataList = null;
            ssn.getTransaction().rollback();
            e.printStackTrace();
        }

        return headingAndDataList;
    }//end method

    public ArrayList<GeneratedBflagPojo> showFlagList(final String para_query) {
        showFlagList = new ArrayList<>();
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
                        while (rs.next()) {
                            boolean record_fetched = true;
                            GeneratedBflagPojo grid_data = new GeneratedBflagPojo();
                            try {
                                Field[] tim_tbl_flds = grid_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    String fld_name = fld.getName();
                                    String fld_value = rs.getString(fld_name);
                                    fld_value = utl.isnull(fld_value) ? "" : fld_value;
                                    fld.set(grid_data, fld_value);
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
                                showFlagList.add(grid_data);
                            }
                        }
                    } catch (SQLException ex) {
                        showFlagList = null;
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
            showFlagList = null;
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return showFlagList;
    }//end method

    List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList = new ArrayList<LhssysCorrFileUploadTran>();

    public List<LhssysCorrFileUploadTran> execute_oracle_query_for_lhssysCorrFileUploadTranList(final String para_query) {
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
                        if (rs != null) {

                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");

                            while (rs.next()) {
                                LhssysCorrFileUploadTran lhssysCorrFileUploadTran = new LhssysCorrFileUploadTran();
                                lhssysCorrFileUploadTran.setFile_seqno(rs.getString("FILE_SEQNO"));
                                lhssysCorrFileUploadTran.setClient_code(rs.getString("CLIENT_CODE"));
                                lhssysCorrFileUploadTran.setTds_type_code(rs.getString("TDS_TYPE_CODE"));
                                lhssysCorrFileUploadTran.setAcc_year(rs.getString("ACC_YEAR"));
                                lhssysCorrFileUploadTran.setQuarter_no(rs.getDouble("QUARTER_NO"));
                                lhssysCorrFileUploadTran.setFile_name(rs.getString("FILE_NAME"));
                                lhssysCorrFileUploadTran.setStorage_file_path(rs.getString("STORAGE_FILE_PATH"));
                                lhssysCorrFileUploadTran.setFile_size(rs.getDouble("FILE_SIZE"));
                                lhssysCorrFileUploadTran.setFlag(rs.getString("FLAG"));
                                lhssysCorrFileUploadTran.setRemove_record_flag(rs.getString("REMOVE_RECORD_FLAG"));

                                try {
                                    lhssysCorrFileUploadTran.setLoad_start_timestamp(sdf.parse(rs.getString("load_start_timestamp")));
                                } catch (Exception e) {
                                }

                                try {
                                    lhssysCorrFileUploadTran.setLoad_start_timestamp(sdf.parse(rs.getString("load_end_timestamp")));
                                } catch (Exception e) {
                                }
                                lhssysCorrFileUploadTran.setLoad_status(rs.getString("load_status"));
                                lhssysCorrFileUploadTran.setLoad_remark(rs.getString("load_remark"));

                                if (utl.isnull(lhssysCorrFileUploadTran.getLoad_status())) {
                                    lhssysCorrFileUploadTran.setLoad_status("");

                                } else if (lhssysCorrFileUploadTran.getLoad_status().equalsIgnoreCase("C")) {
                                    lhssysCorrFileUploadTran.setLoad_status("Complete");

                                } else if (lhssysCorrFileUploadTran.getLoad_status().equalsIgnoreCase("F")) {
                                    lhssysCorrFileUploadTran.setLoad_status("Failed");

                                }

                                lhssysCorrFileUploadTranList.add(lhssysCorrFileUploadTran);
                            }
                        }
                    } catch (SQLException ex) {
                        lhssysCorrFileUploadTranList = null;
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
            lhssysCorrFileUploadTranList = null;
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return lhssysCorrFileUploadTranList;
    }//end method

    public boolean execute_oracle_update_lhssysTemplate_function_by_batch(ArrayList<LhssysTemplate> qry_values_list, final String batch_para_query, final int column_range) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;

                    try {
//                        PropertyDescriptor obj;
                        pstmt = cnctn.prepareStatement(batch_para_query);

                        for (LhssysTemplate tempdatalist : qry_values_list) {//iterating template list for batch query
                            Field[] fields = tempdatalist.getClass().getDeclaredFields();
                            String methodValue;

                            Field fieldValue = null;
                            int psIndex = 1;
                            for (int colIndex = 11; colIndex <= column_range; colIndex++) {
                                String colNameIndex = String.valueOf(colIndex);

                                //Java Stream API
                                Stream<Field> fieldStream = Stream.of(fields);
                                fieldValue = fieldStream.filter(field -> field.getName().endsWith(colNameIndex)).findAny().get();//getting field which will match the column name.

                                if (fieldValue != null) {
//                                    obj = new PropertyDescriptor(fieldValue.getName(), tempdatalist.getClass());
//                                    methodValue = (String) obj.getReadMethod().invoke(tempdatalist, null);
                                    methodValue = (String) PropertyUtils.getProperty(tempdatalist, fieldValue.getName());
                                    pstmt.setString((psIndex++), (!utl.isnull(methodValue) ? methodValue : ""));//setting values for psmt to add in batch.
                                }
                            }
                            pstmt.setString((psIndex++), tempdatalist.getRowid_seq());//setting last parameter in query where rowid_seq clause.
                            pstmt.addBatch();
                        }
                        int resultvalue[] = pstmt.executeBatch();
                        if (resultvalue != null && resultvalue.length > 0) {
                            b1 = true;
                            for (int sts : resultvalue) {
                                System.out.println("if query execute properly it returns 0 or +ve number " + sts);
                                if (sts != 1)// REPLACE VALUE  TO 1 TO -2 BECAUSE IT'S NOT WORKING 15-05-2021 BY KAPIL
                                {
                                    b1 = false;
                                    break;
                                }
                            }
                        } else {
                            b1 = false;
                        }
                    } catch (Exception ex) {
                        b1 = false;
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
            b1 = false;
            ssn.beginTransaction().rollback();
            utl.generateLog("Error in Executing Sequence--", e.getMessage());
        } finally {
            ssn.close();
        }
        return b1;
    }//end method

    public String executeAsList(final String function_name_para_query) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
//                        System.out.println("function_name_para_query--"+function_name_para_query);
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        rs = pstmt.executeQuery(function_name_para_query);
                        while (rs.next()) {
                            function_output = rs.getString(2);
                        }
                    } catch (SQLException ex) {
                        function_output = "";
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.getTransaction().commit();
        } catch (Exception e) {
            function_output = "";
            ssn.beginTransaction().rollback();
            utl.generateLog(null, "Error in Executing Sequence..");
            utl.generateLog(null, e.getMessage());
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method

    public boolean save_file_clob(final Long processSeqNo, final String filePath) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    try {
                        String insertQuery = "INSERT INTO lhssys_process_log_clob_file\n"
                                + "  (PROCESS_SEQNO, IMPORT_CLOB_FILE)\n"
                                + "VALUES\n"
                                + "  (?, ?)";
                        pstmt = cnctn.prepareStatement(insertQuery);
                        File file = new File(filePath);
                        FileReader fr = new FileReader(file);
                        if (file.exists()) {
                            pstmt.setLong(1, processSeqNo);
                            pstmt.setCharacterStream(2, fr, (int) file.length());
                            int retvalue = pstmt.executeUpdate();
                            if (retvalue > 0) {
                                b1 = true;
//                                System.out.println("CLob Data :" + get_file_clob(processSeqNo));
                            } else {
                                b1 = false;
                            }
                            fr.close();
                        } else {
                            System.out.println("File Not Found at : " + filePath);
                        }
                    } catch (SQLException ex) {
                        b1 = false;
                        ex.printStackTrace();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
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
            b1 = false;
            ssn.beginTransaction().rollback();
            utl.generateLog("Error in Executing query--", e.getMessage());
//            System.out.println();
        } finally {
            ssn.close();
        }
        return b1;
    }//end method

    public String get_clob_data(final Long processSeqNo, String processType) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select " + processType + " from Lhssys_Process_Log  WHERE process_seqno =?";
                    try {
                        pstmt = cnctn.prepareStatement(query);
                        //System.out.println("Query->"+query);
                        pstmt.setLong(1, processSeqNo);//47729
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            Clob clob = rs.getClob(1);
                            function_output = clob.getSubString(1, (int) clob.length());
                        }
                    }catch(NullPointerException e){
                      utl.generateLog("Log Not Found In Databas","Empty data in Log_File_Name3 column");  
                      function_output = "Log Not Found In Database";
                    } catch (SQLException ex) {
                        //ex.printStackTrace();
                        function_output = "";

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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            function_output = "";
            ssn.beginTransaction().rollback();
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method

    public String get_clob_file(final Long processSeqNo, String fileName, String fileExtension) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select t.import_clob_file from LHSSYS_PROCESS_LOG_CLOB_FILE t WHERE t.process_seqno =?";
                    try {
                        pstmt = cnctn.prepareStatement(query);
                        pstmt.setLong(1, processSeqNo);//47894
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            Reader r = rs.getCharacterStream(1);
                            File file = new File(fileName + fileExtension);
                            try (FileWriter writer = new FileWriter(file)) {
                                int i;
                                while ((i = r.read()) != -1) {
                                    writer.write(i);
                                }
                                writer.close();
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    } catch (IOException ex) {
                        Logger.getLogger(DbFunctionExecutorAsString.class.getName()).log(Level.SEVERE, null, ex);
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            function_output = "";
            ssn.beginTransaction().rollback();
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method
    //new DbFunctionExecutorAsString().get_clob_file(getTokenNo(),"E:\\akash\\akash",".csv");
    

    
    public String get_text_file(final Long processSeqNo, String fileName, String fileExtension) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select t.LOG_FILE_NAME5 from LHSSYS_PROCESS_LOG t WHERE t.process_seqno =?";
                    try {
                        pstmt = cnctn.prepareStatement(query);
                        pstmt.setLong(1, processSeqNo);//47894
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            Reader r = rs.getCharacterStream(1);
                            File file = new File(fileName + fileExtension);
                            try (FileWriter writer = new FileWriter(file)) {
                                int i;
                                while ((i = r.read()) != -1) {
                                    writer.write(i);
                                }
                                writer.close();
                            }
                        }
                        function_output = "T";
                    } catch (SQLException ex) {
                        function_output = "F";
                        ex.printStackTrace();

                    } catch (IOException ex) {
                        function_output = "F";
                        Logger.getLogger(DbFunctionExecutorAsString.class.getName()).log(Level.SEVERE, null, ex);
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
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            function_output = "F";
            ssn.beginTransaction().rollback();
        } finally {
            ssn.close();
        }
        return function_output;
    }//end method
    
   

    
}           



         