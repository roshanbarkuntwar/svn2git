/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.DbProc;

import static dao.generic.HibernateUtil.getSessionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.dev
 */
public class ProcTdsTranLoadErrorIud {

    int return_result;

    public int Exceute_Proc_Tds_Tran_Load_Error_Iud_function(final String l_entity_code, final String l_client_code, final String l_acc_year, final int l_quarter_no, final Date l_from_date, final Date l_to_date, final String l_tds_type_code, final Long file_seqno, final int l_lineno, final int a_tds_tran_rowid_seq, final Long a_client_login_session_seqno, final String a_tran_table, final String a_error_type, final String a_error_remark, final String a_column_name, final String a_column_data, final String a_column_confirmed_data) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
//                        //System.out.println("l_entity_code......" + l_entity_code);
//                        //System.out.println("l_client_code......" + l_client_code);
//                        //System.out.println("l_acc_year......" + l_acc_year);
//                        //System.out.println("l_quarter_no......" + l_quarter_no);
//                        //System.out.println("l_from_date......" + l_from_date);
//                        //System.out.println("l_to_date......" + l_to_date);
//                        //System.out.println("l_tds_type_code......" + l_tds_type_code);
//                        //System.out.println("file_seqno......" + file_seqno);
//                        //System.out.println("a_file_line_no......" + l_lineno);
//                        //System.out.println("a_tds_tran_rowid_seq......" + a_tds_tran_rowid_seq);
//                        //System.out.println("a_client_login_session_seqno......" + a_client_login_session_seqno);
//                        //System.out.println("a_tran_table......" + a_tran_table);
//                        //System.out.println("a_error_type......" + a_error_type);
//                        //System.out.println("a_error_remark......" + a_error_remark);
//                        //System.out.println("a_column_name......" + a_column_name);
//                        //System.out.println("a_column_data......" + a_column_data);
//                        //System.out.println("a_column_confirmed_data......" + a_column_confirmed_data);
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_tds_tran_load_error_iud(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code data
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no
                        clst.setDate(5, (java.sql.Date) l_from_date);//from_date
                        clst.setDate(6, (java.sql.Date) l_to_date);//to_date
                        clst.setString(7, l_tds_type_code);//tds_type_code
                        if (file_seqno == 0) {
                            clst.setNull(8, java.sql.Types.INTEGER);
                        } else {
                            clst.setLong(8, file_seqno);//file seqno
                        }
                        clst.setNull(9, java.sql.Types.INTEGER);//file line no
                        clst.setInt(10, a_tds_tran_rowid_seq);//rowid sequence
                        clst.setLong(11, a_client_login_session_seqno);//login session seqno
                        clst.setString(12, a_tran_table);//table name
                        clst.setString(13, a_error_type);//error type
                        clst.setString(14, a_error_remark);//error remark
                        clst.setString(15, a_column_name);//column name
                        clst.setString(16, a_column_data);//column data
                        clst.setString(17, a_column_confirmed_data);//a_column_confirmed_data
                        clst.registerOutParameter(18, java.sql.Types.INTEGER);
                        clst.executeUpdate();
                        return_result = clst.getInt(18);
                        //System.out.println("return_result value....." + return_result);
                        //System.out.println("Procedure call");
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
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
            //System.out.println("exception message........" + e.getMessage());
        } finally {
            ssn.close();
        }
//        //System.out.println("return_result......" + return_result);
        return return_result;
    }//end method

    int return_blk_result;

    public int Exceute_Bulk_Proc_Tds_Tran_Load_Error_Iud_function(final String l_entity_code, final String l_client_code, final String l_acc_year, final int l_quarter_no, final Date l_from_date, final Date l_to_date, final String l_tds_type_code, final Long file_seqno, final int l_lineno, final int a_tds_tran_rowid_seq, final Long a_client_login_session_seqno, final String a_tran_table, final String a_error_type, final String a_error_remark, final String a_column_name, final String a_column_data, final String a_column_confirmed_data) {
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {

                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        //System.out.println("l_entity_code......" + l_entity_code);
                        //System.out.println("l_client_code......" + l_client_code);
                        //System.out.println("l_acc_year......" + l_acc_year);
                        //System.out.println("l_quarter_no......" + l_quarter_no);
                        //System.out.println("l_from_date......" + l_from_date);
                        //System.out.println("l_to_date......" + l_to_date);
                        //System.out.println("l_tds_type_code......" + l_tds_type_code);
                        //System.out.println("file_seqno......" + file_seqno);
                        //System.out.println("a_file_line_no......" + l_lineno);
                        //System.out.println("a_tds_tran_rowid_seq......" + a_tds_tran_rowid_seq);
                        //System.out.println("a_client_login_session_seqno......" + a_client_login_session_seqno);
                        //System.out.println("a_tran_table......" + a_tran_table);
                        //System.out.println("a_error_type......" + a_error_type);
                        //System.out.println("a_error_remark......" + a_error_remark);
                        //System.out.println("a_column_name......" + a_column_name);
                        //System.out.println("a_column_data......" + a_column_data);
                        //System.out.println("a_column_confirmed_data......" + a_column_confirmed_data);
                        CallableStatement clst;
                        String executeProc = "{call LHS_TDS.proc_tds_tran_load_error_iud(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        clst = cnctn.prepareCall(executeProc);
                        clst.setString(1, l_entity_code);//entity_code data
                        clst.setString(2, l_client_code);//client_code
                        clst.setString(3, l_acc_year);//acc_year
                        clst.setInt(4, l_quarter_no);//quarter_no
                        clst.setDate(5, (java.sql.Date) l_from_date);//from_date
                        clst.setDate(6, (java.sql.Date) l_to_date);//to_date
                        clst.setString(7, l_tds_type_code);//tds_type_code
                        clst.setNull(8, java.sql.Types.INTEGER);
                        clst.setNull(9, java.sql.Types.INTEGER);//file line no
                        clst.setNull(10, java.sql.Types.INTEGER);//rowid sequence
                        clst.setLong(11, a_client_login_session_seqno);//login session seqno
                        clst.setString(12, a_tran_table);//table name
                        clst.setNull(13, java.sql.Types.CHAR);//error type
                        clst.setString(14, a_error_remark);//error remark
                        clst.setString(15, a_column_name);//column name
                        clst.setNull(16, java.sql.Types.CHAR);//column data
                        clst.setString(17, a_column_confirmed_data);//a_column_confirmed_data
                        clst.registerOutParameter(18, java.sql.Types.INTEGER);
                        clst.executeUpdate();
                        return_blk_result = clst.getInt(18);
                        //System.out.println("return_result value....." + return_blk_result);
                        //System.out.println("Procedure call");
                        try {
                            clst.close();
                        } catch (SQLException e) {
                            //System.out.println(e.getMessage());
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
            //System.out.println("exception message........" + e.getMessage());
        } finally {
            ssn.close();
        }
//        //System.out.println("return_result......" + return_result);
        return return_blk_result;
    }//end method

}//end class
