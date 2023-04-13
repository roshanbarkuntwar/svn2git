/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadActionDB {

    Util utl;

    public DownloadActionDB() {
        utl = new Util();
    }

    public String getDownloadStatusDataCount(Assessment asmt, ViewClientMast client, String additional_whereclause, String pagination_clause, String moduleFlag, String downloadFlag) {
        String dataCountQuery = "select count(*) from ( " + getDownloadStatusData(asmt, client, additional_whereclause, pagination_clause, moduleFlag, downloadFlag) + " ) ";
        return dataCountQuery;
    }//end method

    public String getDownloadStatusData(Assessment asmt, ViewClientMast client, String additional_whereclause, String pagination_clause, String moduleFlag, String downloadFlag) {

        String dataQuery = " select * from (select a.process_seqno,\n"
                + "       a.process_type,\n"
                + "       a.process_type_name,\n a.report_name,\n"
                + "       a.process_start_timestamp,\n"
                + "       a.process_end_timestamp,\n"
                + "       a.process_status,\n"
                + "       a.process_status_name,\n"
                + "       a.fvu_txt_file_name,\n"
                + "       a.fvu_files_path  ,\n"
                + "       a.flag  ,\n"
                + "       a.process_remark,\n"
                + "       rownum slno\n"
                + "  from (select a.process_seqno,\n"
                + "               b.process_type,\n"
                + "               b.process_type_name process_type_name,\n a.report_name,\n"
                + "               to_char(a.process_start_timestamp, 'dd-MON-yyyy hh24:mi:ss') process_start_timestamp,\n"
                + "               to_char(a.process_end_timestamp, 'dd-MON-yyyy hh24:mi:ss') process_end_timestamp,\n"
                + "               a.process_status, \n"
                + "               c.process_status_name,\n"
                + "               a.fvu_txt_file_name,\n"
                + "               a.fvu_files_path  ,\n"
                + "               a.flag  ,\n"
                + "               a.process_remark \n"
                + "        from   lhssys_process_log a, view_lhssys_process_type b, view_lhssys_process_log_status c\n"
                + "        where  b.process_type=a.process_type\n"
                + "        and    b.module_type = '" + moduleFlag + "'\n"
                + "        and    b.process_log_type = '" + downloadFlag + "'\n"
                + "        and    c.process_status=a.process_status\n"
                + "        and    a.entity_code = '" + client.getEntity_code() + "'\n"
                + "        and    a.client_code = '" + client.getClient_code() + "'\n"
                + "        and    a.acc_year = '" + asmt.getAccYear() + "'\n"
                + "        and    a.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "        and    a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + additional_whereclause
                + "   order by a.process_seqno desc  ) a)\n";

        dataQuery += pagination_clause;

        return dataQuery;
    }//end method

    public String getDownloadTypeListQry() {

        String listQuery = " select REPORT_ENGINE, ENGINE_NAME\n"
                + "  from lhs_tds_master.lhssys_engines t\n"
                + " WHERE T.ENGINE_TYPE = 'DWNLD'\n"
                + "   AND T.PERIOD_TYPE = 'R' ";

        return listQuery;
    }//end method

    public String getDownloadStatusQry() {

        String listQuery = " select *\n"
                + "  from view_lhssys_process_log_status T\n"
                + " WHERE T.process_status LIKE 'D%' ";

        return listQuery;
    }//end method

    public String getDownloadTypeQry() {

        String listQuery = " select *\n"
                + "  from view_lhssys_process_type T\n";

        return listQuery;
    }//end method

    public String getProcessTypeQry(String flag, String insert_mode_flag, String token_flag) {
        String listQuery = "SELECT * FROM VIEW_LHSSYS_PROCESS_TYPE T WHERE ";
        if (!utl.isnull(flag)) {
            listQuery += "T.module_type ='" + flag + "'";
        }
        if (!utl.isnull(insert_mode_flag) && insert_mode_flag != "tokenStatus") {
            String checkInsertModeFlag = insert_mode_flag.equals("importStatus") ? "I" : insert_mode_flag.equals("fvuStatus") ? "P" : insert_mode_flag.equals("download") ? "D" : "";
            listQuery += "and T.process_log_type ='" + checkInsertModeFlag + "'";
        }
        if (!utl.isnull(token_flag) && (token_flag.equals("I") || token_flag.equals("P"))) {
            listQuery += "and T.process_log_type ='" + token_flag + "'";
        }
        utl.generateSpecialLog("process type query is   ", listQuery);
        return listQuery;

    }
}//end class
