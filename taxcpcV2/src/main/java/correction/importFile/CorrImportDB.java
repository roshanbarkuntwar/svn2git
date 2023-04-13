/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.LhssysCorrFileUploadTran;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class CorrImportDB {

    public List<LhssysCorrFileUploadTran> getDataGridQuery(String client_code, String tds_type_code, String l_acc_year, Double l_period_no, int l_record_MNL, int l_record_MXL, String showAll, LhssysCorrFileUploadTran filterObj) {
        List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList = null;
        try {
            StringBuilder l_query = new StringBuilder();
            l_query.append(" select * from (select rownum slno,\n");
            l_query.append("l.FILE_SEQNO,\n");
            l_query.append("               l.CLIENT_CODE,\n");
            l_query.append("               l.TDS_TYPE_CODE,\n");
            l_query.append("               l.ACC_YEAR,\n");
            l_query.append("               l.QUARTER_NO,\n");
            l_query.append("               l.FILE_NAME,\n");
            l_query.append("               l.STORAGE_FILE_PATH,\n");
            l_query.append("               l.FILE_SIZE,\n");
            l_query.append("               l.FLAG,\n");
            l_query.append("               l.REMOVE_RECORD_FLAG,\n");
            l_query.append("               to_CHAR(l.load_start_timestamp,'DD-MM-RRRR HH24:MI:SS')load_start_timestamp,\n");
            l_query.append("               to_CHAR(l.load_end_timestamp,'DD-MM-RRRR HH24:MI:SS')load_end_timestamp,\n");
            l_query.append("               l.load_status,\n");
            l_query.append("               l.load_remark \n");

            l_query.append("  from lhssys_corr_file_upload_tran l\n");

            l_query.append(" where l.client_code = '" + client_code + "'\n");
            l_query.append(" and l.tds_type_code = '" + tds_type_code + "'\n");
            l_query.append(" and l.acc_year = '" + l_acc_year + "'\n");
            l_query.append(" and l.quarter_no = '" + l_period_no + "'\n");

            if (filterObj != null) {
                if (!utl.isnull(filterObj.getFile_name())) {
                    l_query.append("  and l.file_name='" + filterObj.getFile_name() + "'\n");
                }
            }

            if (utl.isnull(showAll)) {
                l_query.append(" ) where slno between " + l_record_MNL + " and " + l_record_MXL);

            } else {
                l_query.append(" )  ");

            }
            DbFunctionExecutorAsString dbQueryList = new DbFunctionExecutorAsString();
            lhssysCorrFileUploadTranList = dbQueryList.execute_oracle_query_for_lhssysCorrFileUploadTranList(l_query.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lhssysCorrFileUploadTranList;
    }//End Method

    Util utl;

    public CorrImportDB() {
        utl = new Util();
    }

}
