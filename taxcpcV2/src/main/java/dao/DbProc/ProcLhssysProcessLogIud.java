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
import java.sql.Types;
import java.util.Date;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author ayushi.jain
 */
public class ProcLhssysProcessLogIud {

    String result = "0";
    Util utl;

    public ProcLhssysProcessLogIud() {
        utl = new Util();
    }

    public String executeProcedure(final Long a_process_seqno, final String a_entity_code,
            final String a_client_code,
            final String a_acc_year,
            final String a_quarter_no,
            final Date a_from_date,
            final Date a_to_date,
            final String a_tds_type_code,
            final Long a_client_login_session_seqno,
            final String a_month,
            final Date a_process_start_timestamp,
            final Date a_process_end_timestamp,
            final String a_process_status,
            final String a_process_remark,
            final String a_file_name,
            final String a_file_no_of_lines,
            final String a_file_no_of_record,
            final Long a_process_sid,
            final Long a_process_serial,
            final String a_process_type,
            final String a_user_code,
            final String a_iud_type,
            final String templateCode,
            final String a_record_insert_flag,
            final String a_report_name,
            final String a_proc_string,
            final String a_module_type) {
        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        utl.generateSpecialLog("*****token generation parameter Start******", "");
                        utl.generateSpecialLog("a_process_seqno", a_process_seqno);
                        utl.generateSpecialLog("a_entity_code", a_entity_code);
                        utl.generateSpecialLog("a_client_code", a_client_code);
                        utl.generateSpecialLog("a_acc_year", a_acc_year);
                        utl.generateSpecialLog("a_quarter_no", a_quarter_no);
                        utl.generateSpecialLog("a_from_date", a_from_date);
                        utl.generateSpecialLog("a_to_date", a_to_date);
                        utl.generateSpecialLog("a_tds_type_code", a_tds_type_code);
                        utl.generateSpecialLog("a_client_login_session_seqno", a_client_login_session_seqno);
                        utl.generateSpecialLog("a_month", a_month);
                        utl.generateSpecialLog("a_process_start_timestamp", a_process_start_timestamp);
                        utl.generateSpecialLog("a_process_end_timestamp", a_process_end_timestamp);
                        utl.generateSpecialLog("a_process_status", a_process_status);
                        utl.generateSpecialLog("a_process_remark", a_process_remark);
                        utl.generateSpecialLog("a_file_name", a_file_name);
                        utl.generateSpecialLog("a_file_no_of_line", a_file_no_of_lines);
                        utl.generateSpecialLog("a_file_no_of_record", a_file_no_of_record);
                        utl.generateSpecialLog("a_process_sid", a_process_sid);
                        utl.generateSpecialLog("a_process_serial", a_process_serial);
                        utl.generateSpecialLog("a_process_type", a_process_type);
                        utl.generateSpecialLog("a_user_code", a_user_code);
                        utl.generateSpecialLog("a_iud_type", a_iud_type);
                        utl.generateSpecialLog("templateCode", templateCode);
                        utl.generateSpecialLog("a_record_insert_flag", a_record_insert_flag);
                        utl.generateSpecialLog("a_report_name", a_report_name);
                         utl.generateSpecialLog("a_module_type", a_module_type);
                        utl.generateSpecialLog("a_proc_string", a_proc_string);
                        utl.generateSpecialLog("*****token generation parameter end******", "");
                        CallableStatement cs;
                        String proc = "{call lhs_utility.proc_lhssys_process_log_iud(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        cs = cnctn.prepareCall(proc);
                        if (a_process_seqno != null) {
                            cs.setLong(1, a_process_seqno);
                        } else {
                            cs.setNull(1, java.sql.Types.INTEGER);

                        }

                        cs.setString(2, a_entity_code);//entity code
                        cs.setString(3, a_client_code);//client code
                        cs.setString(4, a_acc_year);//acc_year
                        cs.setString(5, a_quarter_no);//quarter no.
                        java.sql.Date sql_a_from_date = new java.sql.Date(a_from_date.getTime());
                        cs.setDate(6, sql_a_from_date);//from date
                        java.sql.Date sql_a_to_date = new java.sql.Date(a_to_date.getTime());
                        cs.setDate(7, sql_a_to_date);//to date
                        cs.setString(8, a_tds_type_code);
                        cs.setLong(9, a_client_login_session_seqno);
                        cs.setString(10, a_month);
//                        java.sql.Date sql_a_process_start_timestamp = new java.sql.Date(a_process_start_timestamp.getTime());
                        cs.setDate(11, null);
//                        java.sql.Date sql_a_process_end_timestamp = new java.sql.Date(a_process_end_timestamp.getTime());
                        cs.setDate(12, null);
                        cs.setString(13, a_process_status);
                        cs.setString(14, a_process_remark);
                        cs.setString(15, a_file_name);
                        cs.setString(16, a_file_no_of_lines);
                        cs.setString(17, a_file_no_of_record);
                        if (a_process_sid != null) {
                            cs.setLong(18, a_process_sid);
                        } else {
                            cs.setLong(18, java.sql.Types.INTEGER);

                        }
                        if (a_process_serial != null) {

                            cs.setLong(19, a_process_serial);
                        } else {
                            cs.setLong(19, java.sql.Types.INTEGER);

                        }
                        cs.setString(20, a_process_type);
                        cs.setString(21, a_user_code);
                        cs.setString(22, a_iud_type);//use for proccess insert in lhssys_process_log indicate process is start
                        cs.setString(23, templateCode);
                        cs.setString(24, a_record_insert_flag);
                        cs.registerOutParameter(25, Types.VARCHAR);//out parameter
                        cs.setString(26, null);
                        cs.setString(27, null);
                        cs.setString(28, null);
                        cs.setString(29, a_report_name);
                        cs.setString(30, a_module_type);
//                      cs.setString(30, a_proc_string);

                        cs.executeUpdate();
                        result = cs.getString(25);

                        try {
                            cs.close();
                        } catch (SQLException e) {
                            result = "-1";
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            };

            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        } catch (JDBCException ex) {
            result = "-1";
            ex.printStackTrace();
        } finally {
            session.close();
        }

        utl.generateLog("Procedure Result...", result);
        return result;
    }
}
