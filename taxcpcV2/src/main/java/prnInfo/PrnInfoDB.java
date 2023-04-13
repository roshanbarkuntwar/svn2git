/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prnInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author ayushi.jain
 */
public class PrnInfoDB {

    public String getPrnCountQuery(Assessment asmt, ViewClientMast client, Util utl, String moduleFlag, String prnFlag, String tanno, String processLevel) {

        String whereCls = getWhereClause(utl, prnFlag, tanno, moduleFlag, processLevel);
        String query
                = "SELECT count(*)\n"
                + "  FROM LHSSYS_TDS_RETURN_TRAN t, client_mast c\n"
                + " where "
                + "c.entity_code = t.entity_code"
                + " and c.client_code = t.client_code"
                + " and t.entity_code = '" + client.getEntity_code() + "'\n"
                + " and t.client_code = c.client_code"
                + "           and "
                + "(c.client_code = '" + client.getClient_code() + "' or c.parent_code = '" + client.getClient_code() + "' or\n"
                + "               c.g_parent_code = '" + client.getClient_code() + "' or c.sg_parent_code = '" + client.getClient_code() + "' or\n"
                + "               c.ssg_parent_code = '" + client.getClient_code() + "' or c.sssg_parent_code = '" + client.getClient_code() + "')\n"
                + "   and t.acc_year = '" + asmt.getAccYear() + "' "
                + "   and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                //                + " and t.tds_return_type = '" + moduleFlag + "'  " 
                + whereCls;

        return query;
    }

    public String getPrnDataGridQuery(Assessment asmt, ViewClientMast clientmast, Util utl, String moduleFlag, String prnFlag, String tanno, int min, int max, String processLevel) {
        String whereCls = getWhereClause(utl, prnFlag, tanno, moduleFlag, processLevel);
        String dataGridQuery
                = "SELECT *\n"
                + "  FROM ( SELECT row_number() over (order by 1) SLNO, t.TANNO,c.bank_branch_code,tds_return_type,       \n"
                + "       t.TDS_CHALLAN_REC,       \n"
                + "       t.TDS_CHALLAN_AMT,       \n"
                + "       t.DEDUCTEE_REC,       \n"
                + "       t.DEDUCTEE_TRAN_AMT,       \n"
                + "       t.DEDUCTEE_TDS_AMT,       \n"
                + "       t.FILE_UPLOAD_ACK_NO,       \n"
                + "      to_char(t.FILE_UPLOAD_ACK_DATE,'dd-MM-rrrr') AS FILE_UPLOAD_ACK_DATE,       \n"
                + "       t.FILE_UPLOAD_ACK_PDF_PATH,       \n"
                + "       t.FILE_UPLOAD_ACK_PDF_NAME,       \n"
                + "       t.FORM16_FILE_STATUS,       \n"
                + "       t.FORM16_FILE_DATE,       \n"
                + "       t.FORM16_DEDUCTEE_REC,t.ROWID_SEQ , T.FVU_FILE_GENERATED_FLAG,   t.tds_challan_return_rec,\n"
                + "       t.tds_challan_return_amt,  \n"
                + "       t.deductee_return_rec,\n"
                + "       t.deductee_tran_return_amt,\n"
                + "       t.deductee_tds_return_amt, \n"
                + "       t.deductee_ret_pnotavbl_rec,\n"
                + "       t.deductee_tran_ret_pnotavbl_amt,\n"
                + "       t.deductee_tds_ret_pnotavbl_amt\n"
                + "  FROM LHSSYS_TDS_RETURN_TRAN t, client_mast c\n"
                + " where "
                + " c.entity_code = t.entity_code"
                + " and c.client_code = t.client_code"
                + "  and "
                + "t.entity_code = '" + clientmast.getEntity_code() + "'\n"
                + "           and"
                + "  (c.client_code = '" + clientmast.getClient_code() + "' or c.parent_code = '" + clientmast.getClient_code() + "' or\n"
                + "               c.g_parent_code = '" + clientmast.getClient_code() + "' or c.sg_parent_code = '" + clientmast.getClient_code() + "' or\n"
                + "               c.ssg_parent_code = '" + clientmast.getClient_code() + "' or c.sssg_parent_code = '" + clientmast.getClient_code() + "')\n"
                //                                            + "   and t.client_code = 'BLRU04306B'\n"
                + "   and t.acc_year = '" + asmt.getAccYear() + "' "
                + "   and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "   and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n "
                //                + "  and t.tds_return_type ='" + moduleFlag + "'"
                + " " + whereCls + "  order by t.TANNO asc)\n"
                + " WHERE SLNO BETWEEN " + min + " AND " + max + "";
System.out.println("datagridQuery--"+dataGridQuery);
        return dataGridQuery;
    }

    public String getWhereClause(Util utl, String prnFlag, String tanno, String moduleFlag, String processLevel) {

        String whereCls = "";
        if (!utl.isnull(prnFlag)) {
            if (prnFlag.equalsIgnoreCase("U")) {
                whereCls += "  AND T.FILE_UPLOAD_ACK_NO IS NOT NULL";
            } else if (prnFlag.equalsIgnoreCase("N")) {
                whereCls += "  AND T.FILE_UPLOAD_ACK_NO IS NULL";
            }
        }
        if (!utl.isnull(tanno)) {
            whereCls += " and t.tanno='" + tanno + "' ";
        }

        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
            whereCls += "  and t.tds_return_type = '" + moduleFlag + "'";
        } else {
            whereCls += "  and t.tds_return_type in ('G','H')";
        }
        return whereCls;
    }

}
