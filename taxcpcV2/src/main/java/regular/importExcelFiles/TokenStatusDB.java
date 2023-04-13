/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author kapil.gupta
 */
public class TokenStatusDB {

    Util utl;

    public TokenStatusDB() {
        utl = new Util();
    }

    public String getTokenStatusDataCount(Assessment asmt, ViewClientMast client, String pagination_clause, String additional_whereclause, String moduleFlag, String msgFlag) {
        String checkInsertModeFlag = msgFlag.equals("importStatus") ? "I" : msgFlag.equals("fvuStatus") ? "P" : "";
        String dataCountQuery
                = "select count(*)\n"
                + "  from lhssys_process_log v,view_lhssys_process_type a\n"
                + " where v.entity_code = '" + client.getEntity_code() + "'\n"
                + "   and a.process_type=v.process_type\n"
                + "   and a.module_type = '" + moduleFlag + "'\n";
        if (!utl.isnull(checkInsertModeFlag)) {
            dataCountQuery += "   and a.process_log_type = '" + checkInsertModeFlag + "'\n";
        }
        dataCountQuery += "   and v.client_code = '" + client.getClient_code() + "'\n"
                + "   and v.acc_year='" + asmt.getAccYear() + "'\n"
                + "   and v.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and v.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and to_date(v.process_start_timestamp,'dd-mm-yyyy') between to_date(sysdate,'dd-mm-yyyy')-7 and  trunc(to_date(sysdate,'dd-mm-yyyy'))"
                + additional_whereclause
                + "   order by v.process_seqno desc";
        dataCountQuery += pagination_clause;
        return dataCountQuery;
    }//end method

    public String getTokenStatusStatusData(Assessment asmt, ViewClientMast client, String pagination_clause, String additional_whereclause, String moduleFlag, String msgFlag) {
        String checkInsertModeFlag = msgFlag.equals("importStatus") ? "I" : msgFlag.equals("fvuStatus") ? "P" : "";
        String dataQuery
                = "select * from (select\n"
                + "       row_number() over (order by 1) SLNO,\n"
                + "      v.process_seqno,\n"
                + "       lhs_utility.get_name('RECORD_INSERT_FLAG',RECORD_INSERT_FLAG) process_insert_mode,\n"
                + "       to_char(v.process_start_timestamp,'DD-MM-RRRR HH:MI:SS' ) process_start_timestamp,\n"
                + "       to_char(v.process_end_timestamp,'DD-MM-RRRR HH:MI:SS') process_end_timestamp,\n"
                + "       lhs_utility.get_name('PROCESS_STATUS', v.PROCESS_STATUS) process_remark ,\n"
                + "       v.process_status,\n"
                + "       v.record_insert_flag,\n"
                + "       v.template_code,\n"
                + "       lhs_utility.get_name('report_engine', v.template_code) template_name\n"
                + "  from lhssys_process_log v,view_lhssys_process_type a\n"
                + " where v.entity_code = '" + client.getEntity_code() + "'\n"
                + "   and a.process_type=v.process_type\n"
                + "   and a.module_type = '" + moduleFlag + "'\n";
        if (!utl.isnull(checkInsertModeFlag)) {
            dataQuery += "   and a.process_log_type = '" + checkInsertModeFlag + "'\n";
        }
        dataQuery += "   and v.client_code = '" + client.getClient_code() + "'\n"
                + "   and v.acc_year='" + asmt.getAccYear() + "'\n"
                + "   and v.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and v.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "   and to_date(v.process_start_timestamp,'dd-mm-yyyy') between to_date(sysdate,'dd-mm-yyyy')-7 and  trunc(to_date(sysdate,'dd-mm-yyyy'))"
                + additional_whereclause
                + "   order by v.process_seqno desc)\n";

        dataQuery += pagination_clause;
        return dataQuery;
    }//end method

}
