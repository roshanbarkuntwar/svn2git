/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.MisReportConfigMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import hibernateObjects.ViewClientMast;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ReportDB implements SessionAware {

    Map<String, Object> session;
    Util utl;
    boolean report_access_failed = false;
    private HashMap<String, String> report_para_processed;
    public ArrayList<ReportHeadAttributes> report_attributes;
    private String report_header;
    public String l_query;
    public int l_column_count_value;

    public ArrayList<ReportHeadAttributes> getReport_attributes() {
        return report_attributes;
    }

    public void setReport_attributes(ArrayList<ReportHeadAttributes> report_attributes) {
        this.report_attributes = report_attributes;
    }

    public boolean isReport_access_failed() {
        return report_access_failed;
    }

    public void setReport_access_failed(boolean report_access_failed) {
        this.report_access_failed = report_access_failed;
    }

    public HashMap<String, String> getReport_para_processed() {
        return report_para_processed;
    }

    public void setReport_para_processed(HashMap<String, String> report_para_processed) {
        this.report_para_processed = report_para_processed;
    }

    public String getReport_header() {
        return report_header;
    }

    public void setReport_header(String report_header) {
        this.report_header = report_header;
    }

    public String getL_query() {
        return l_query;
    }

    public void setL_query(String l_query) {
        this.l_query = l_query;
    }

    public int getL_column_count_value() {
        return l_column_count_value;
    }

    public void setL_column_count_value(int l_column_count_value) {
        this.l_column_count_value = l_column_count_value;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

    public ReportDB(Map<String, Object> session) {
        utl = new Util();
        this.session = session;
    }//end constructor

    public String select_single_vlaue_fromDB(String query, Connection conn) throws SQLException {
        String L_result = null;
        if (!utl.isnull(query)) {
            try {
                Statement L_stmt = conn.createStatement();
                ResultSet L_rset = L_stmt.executeQuery(query);
                int count = 0;
                while (L_rset.next()) {
                    count++;
                    if (count > 1) {
                        break;
                    }
                    L_result = L_rset.getString(1);
                }//end while
                try {
                    L_rset.close();
                    L_stmt.close();
                } catch (SQLException e) {
                    //System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                L_result = null;
                //System.out.println(e.getMessage() + "\nReason : " + e.getCause() + "\nfor query : " + query);
            }
        }//end if
        return L_result;
    }//end method

    public long get_report_data_count(Connection conn, HashMap<String, String> all_report_inputs, Long a_report_seq, boolean select_record) {
        long return_count = 0;
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            MisReportConfigMastDAO reportConfigMastDao = factory.getMisReportConfigMastDAO();
            MisReportConfigMast objData = reportConfigMastDao.getReportAsSeqId(a_report_seq, utl);
            if (objData != null) {
                //*******************************************************
//                String pre_execute_query = objData.getRep_pl_sql_text();
//                pre_execute_query = process_report_input_parameter_replacement(pre_execute_query, all_report_inputs);
//                boolean pre_exe_executed = execute_pre_exe_query(conn, pre_execute_query);
                boolean pre_exe_executed = true;

                //*******************************************************
                String report_header_str = objData.getEmail_body_text();
                setReport_header(make_report_header(conn, report_header_str, all_report_inputs));
                //*******************************************************
                int l_column_count = 0;
                try {
                    l_column_count = Integer.parseInt(objData.getNo_of_column());
                } catch (Exception e) {
                    l_column_count = 0;
                    e.printStackTrace();
                }

                //*******************************************************
                if (pre_exe_executed && l_column_count > 0) {
                    String report_query = objData.getRep_sql_text();
                    report_query = process_report_input_parameter_replacement(report_query, all_report_inputs);
                    report_query = replaceDefaultValues(report_query, objData.getRep_assesm_fltr_flag());

                    this.l_query = report_query;
                    report_query = "select count(*) from ( select rownum slno, t.* from ( " + report_query + " ) t )  ";

                    utl.generateSpecialLog("MIS report data count query", report_query);

                    if (!utl.isnull(report_query)) {
                        Statement l_stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ResultSet l_rs = l_stmt.executeQuery(report_query);

                        if (l_rs.next()) {
                            return_count = l_rs.getLong(1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return_count = 0;
            e.printStackTrace();
        }
        return return_count;
    }//end method

    public ArrayList<ArrayList<String>> get_report_data(Connection conn, HashMap<String, String> all_report_inputs, Long a_report_seq, boolean select_record,
            int minVal, int maxVal, String pagination_flag) {

        ArrayList<ArrayList<String>> available_data = new ArrayList<>();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            MisReportConfigMastDAO reportConfigMastDao = factory.getMisReportConfigMastDAO();
            MisReportConfigMast objData = reportConfigMastDao.getReportAsSeqId(a_report_seq, utl);
            if (objData != null) {
                //*******************************************************
//                String pre_execute_query = objData.getRep_pl_sql_text();
//                pre_execute_query = process_report_input_parameter_replacement(pre_execute_query, all_report_inputs);
//                boolean pre_exe_executed = execute_pre_exe_query(conn, pre_execute_query);
                boolean pre_exe_executed = true;

                //*******************************************************
                String report_header_str = objData.getEmail_body_text();
                setReport_header(make_report_header(conn, report_header_str, all_report_inputs));
                //*******************************************************
                int l_column_count = 0;
                try {
                    l_column_count = Integer.parseInt(objData.getNo_of_column());
                } catch (Exception e) {
                    l_column_count = 0;
                    e.printStackTrace();
                }
                utl.generateLog("Report_Columns---", l_column_count);
                this.l_column_count_value = l_column_count;
                //*******************************************************
                if (pre_exe_executed && l_column_count > 0) {
                    String report_query = objData.getRep_sql_text();
                    report_query = process_report_input_parameter_replacement(report_query, all_report_inputs);
                    report_query = replaceDefaultValues(report_query, objData.getRep_assesm_fltr_flag());
                    this.l_query = report_query;

                    utl.generateSpecialLog("REPORT_QUERY_BEFORE_PREPARE_BY_JAVA_METHOD", report_query);
                    report_query = "select * from ( select row_number() over(order by 1) slno, t.* from ( " + report_query + " ) t )  ";

                    if (pagination_flag.equalsIgnoreCase("T")) {
                        report_query += " where slno between " + minVal + " and " + maxVal;
                    }

                    utl.generateSpecialLog("REPORT_QUERY_AFTER_PREPARE_BY_JAVA_METHOD", report_query);

                    if (!utl.isnull(report_query)) {
                        Statement l_stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ResultSet l_rs = l_stmt.executeQuery(report_query);

                        if (select_record) {//record will be fetched only if it has to be displayed @ UI
                            while (l_rs.next()) {
                                ArrayList<String> single_row = new ArrayList<String>();
                                for (int i = 1; i <= l_column_count; i++) {
                                    String column_data = l_rs.getString(i);
                                    column_data = utl.isnull(column_data) ? "" : column_data;
                                    single_row.add(column_data);
                                }
                                available_data.add(single_row);
                            }//end while
                            try {
                                l_rs.close();
                                l_stmt.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }//end if select row

                    }//end if
                    //***********************************************   

                    try {
                        this.report_attributes = new ArrayList<>();
                        for (int i = 1; i <= l_column_count; i++) {//column count is configured in report head for query to be run qgainst report data
                            String caption_name;
                            ReportHeadAttributes attributes = new ReportHeadAttributes();
                            try {
                                Field caption = objData.getClass().getDeclaredField("heading" + String.valueOf(i));
                                caption.setAccessible(true);
                                caption_name = (String) caption.get(objData);
                                //System.out.println("caption_name 1" + caption_name);
                                attributes.setReport_header_captions(caption_name);
                                Field caption2 = objData.getClass().getDeclaredField("head_width" + String.valueOf(i));
                                caption2.setAccessible(true);
                                caption_name = (String) caption2.get(objData);
                                //System.out.println("caption_name 2" + caption_name);
                                attributes.setReport_header_width(caption_name);
                                Field caption3 = objData.getClass().getDeclaredField("data_type" + String.valueOf(i));
                                caption3.setAccessible(true);
                                caption_name = (String) caption3.get(objData);
                                //System.out.println("caption_name 1" + caption_name);
                                attributes.setReport_header_data_type(caption_name);
                            } catch (Exception e) {
                                attributes.clear_fields();
                                e.printStackTrace();
                            }
                            this.report_attributes.add(attributes);
                            attributes.to_String();
                        }//end for
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //**********************************************  

                } else {
                    setReport_access_failed(true);
                }
            } else {
                setReport_access_failed(true);
            }
            //System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return available_data;
    }//end method

    public String process_report_input_parameter_replacement(String a_query, HashMap<String, String> a_input_paras) {
        try {
            Set allparas = a_input_paras.keySet();
            Object[] keys = allparas.toArray();
            for (Object key : keys) {
                boolean para_retrived = false;
                String para_name = "";
                String para_value = "";
                try {
                    para_name = key.toString();
                    //System.out.println("para_name 0..." + para_name);
                    para_value = a_input_paras.get(para_name);
                    //System.out.println("para_value 0..." + para_value);
                    para_retrived = true;
                } catch (Exception e) {
                    para_name = "";
                    para_value = "";
                    e.printStackTrace();
                }
                if (para_retrived) {
                    a_query = a_query.replaceAll(para_name, para_value);
                }
            }//end for

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a_query;
    }//end method

    public boolean execute_pre_exe_query(Connection conn, String pre_execute_query) {
        boolean executed = true;
        try {
            String pre_exe_qur = "BEGIN " + pre_execute_query + "  END;";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(pre_exe_qur);
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            executed = false;
            e.printStackTrace();
        }
        return executed;
    }//end method

    private String make_report_header(Connection conn, String report_header_str, HashMap<String, String> all_report_inputs) {
        String hdr = report_header_str;
        try {
            hdr = process_report_input_parameter_replacement(hdr, all_report_inputs);
            //System.out.println("hdr...1..." + hdr);
            hdr = execute_from_dual(conn, hdr);
            //System.out.println("hdr...2..." + hdr);
        } catch (Exception e) {
            hdr = report_header_str;
            e.printStackTrace();
        }
        return hdr;
    }//end method

    private String execute_from_dual(Connection conn, String hdr) {
        String res = "";
        try {
            String quer = "select " + hdr + " from dual";
            //System.out.println("execute_from_dual --- > " + quer);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(quer);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count > 1) {
                    break;
                }
                res = rs.getString(1);
            }//end while
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            res = "";
            e.printStackTrace();
        }
        return res;
    }//end method   

//    String get_report_query(Connection conn, HashMap<String, String> all_report_inputs, String a_report_seq) {
//        String l_data_query = new String();
//        try {
//            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//            LhssysAlertDirectEmailDAO lhssysalertemaildao = factory.getLhssysAlertDirectEmailDAO();
//            LhssysAlertDirectEmail head_status = lhssysalertemaildao.getReportAsSeqId(Long.parseLong(a_report_seq), utl);
//            if (head_status != null) {
//
//                String pre_execute_query = head_status.getPl_sql_text();
//                //System.out.println("pre_execute_query...." + pre_execute_query);
//                pre_execute_query = process_report_input_parameter_replacement(pre_execute_query, all_report_inputs);
//                boolean pre_exe_executed = execute_pre_exe_query(conn, pre_execute_query);
//                //*******************************************************
//
//                String report_header_str = head_status.getBody_text();
//                setReport_header(make_report_header(conn, report_header_str, all_report_inputs));
//                //*******************************************************
//                int l_column_count = 0;
//                try {
//                    l_column_count = Integer.parseInt(head_status.getNo_of_column());
//                } catch (Exception e) {
//                    l_column_count = 0;
//                    e.printStackTrace();
//                }
//                //*******************************************************
//                //System.out.println("l_column_count...." + l_column_count);
//                if (pre_exe_executed && l_column_count > 0) {
//                    String report_query = head_status.getSql_text();
//                    report_query = process_report_input_parameter_replacement(report_query, all_report_inputs);
//                    report_query = "select * from ( select t.* from ( " + report_query + " ) t )  ";
//                    l_data_query = report_query;
//                    //System.out.println("Report Query : " + l_data_query);
//                }
//                //***********************************************       
//            }//end if
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return l_data_query;
//    }//end method
    public String replaceDefaultValues(String paraQuery, String assesmFlag) {

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");

        if (client != null && asmt != null) {
            try {
                if (utl.isnull(assesmFlag) || !assesmFlag.equals("T")) {
                    paraQuery = paraQuery.replaceAll("accYear", asmt.getAccYear());
                    paraQuery = paraQuery.replaceAll("quarterNo", asmt.getQuarterNo());
                    paraQuery = paraQuery.replaceAll("tdsTypeCode", asmt.getTdsTypeCode());
                }
                paraQuery = paraQuery.replaceAll("entityCode", client.getEntity_code());
                paraQuery = paraQuery.replaceAll("clientCode", client.getClient_code());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return paraQuery;
    }//end method

}//end class
