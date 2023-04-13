/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadTdsCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author ayushi.jain
 */
public class DownloadTdsCertificateDB {

    Util utl;

    public DownloadTdsCertificateDB() {
        utl = new Util();
    }

    public String getSingleForm16Query(ViewClientMast client, Assessment asmt, String tanno) {
        String query = " select T.TANNO,\n"
                + "       T.FORM16_FILEPATH || T.FORM16_FILENAME AS FILE_PATH,\n"
                + "       T.ROWID_SEQ\n"
                + "  from form16_client_mast T \n"
                + " WHERE T.ENTITY_CODE = '" + client.getEntity_code() + "'\n"
                + "  AND T.CLIENT_CODE = '" + client.getClient_code() + "'\n"
                + "  AND T.ACC_YEAR = '" + asmt.getAccYear() + "'\n"
                + "  AND T.QUARTER_NO = '" + asmt.getQuarterNo() + "'\n"
                + "   AND T.TDS_TYPE_CODE = '" + asmt.getTdsTypeCode() + "' \n";
//                + " AND ROWNUM = 1";
        if (!utl.isnull(tanno)) {
            query += "    and t.tanno = '" + tanno + "'";
        }
        return query;
    }

    public String getForm16PanPdfQuery(ViewClientMast client, Assessment asmt, String panno) {
        String whereCls = "";
        if (!utl.isnull(panno)) {
            whereCls += "    and t.deductee_panno = '" + panno + "' ";
        }
        String query = "select t.form16_filepath || t.form16_filename\n"
                + "from form16_pan_mast t\n"
                + "where t.entity_code = '" + client.getEntity_code() + "'\n"
                + "and t.acc_year = '" + asmt.getAccYear() + "'\n"
                + "and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "' \n"
                + whereCls
                + "and exists (select 1 \n"
                + "            from   client_mast b \n"
                + "            where  b.entity_code=t.entity_code \n"
                + "            and    b.client_code=t.client_code\n"
                + "            and    (b.client_code='" + client.getClient_code() + "' or\n"
                + "                    b.parent_code='" + client.getClient_code() + "' or\n"
                + "                    b.g_parent_code='" + client.getClient_code() + "' or\n"
                + "                    b.sg_parent_code='" + client.getClient_code() + "' or\n"
                + "                    b.ssg_parent_code='" + client.getClient_code() + "' or\n"
                + "                    b.sssg_parent_code='" + client.getClient_code() + "'\n"
                + "                   )\n"
                + "           )";
        return query;
    }

}
