/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import globalUtilities.Util;

/**
 *
 * @author trainee
 */
public class PANVerificationDB {

    private Util utl;

    public PANVerificationDB() {
        utl = new Util();
    }

    public String getPanVerificationCountQry(String client_code, String entity_code) {

        String unverifiedQuery = "select count(*) from (\n"
                + "select a.panno,count(*) rec\n"
                + "from   pan_mast_unverified a, client_mast b\n"
                + "where  b.entity_code=a.entity_code\n"
                + "and    b.client_code=a.client_code\n"
                + "and    a.entity_code='" + entity_code + "'\n"
                + "and    (b.client_code='" + client_code + "' or\n"
                + "        b.parent_code='" + client_code + "' or\n"
                + "        b.g_parent_code='" + client_code + "' or\n"
                + "        b.sg_parent_code='" + client_code + "' or\n"
                + "        b.ssg_parent_code='" + client_code + "' or\n"
                + "        b.sssg_parent_code='" + client_code + "'\n"
                + "       )\n"
                + "group by a.panno\n"
                + ")";
        return unverifiedQuery;
    }//end method

    public String panVerifiedAndUnVerifiedDetailsQuery(String panno, String tableName, String entityCode) {

        String panVerifiedQuery = "select p.panno,\n"
                + "       p.first_name,\n"
                + "       p.middle_name,\n"
                + "       p.last_name,\n"
                + "       to_char(p.pan_allot_date,'DD-MM-RRRR')pan_allot_date,\n"
                + "       p.pan_aadhar_status,\n"
                + "       decode(p.ab206_status,'N','No','Y','Yes','NA')ab206_status,\n"
                + "       decode(p.n194_status,'N','No','Y','Yes','NA')n194_status,\n"
                + "       p.remark,\n"
                + "       TO_CHAR(p.verified_date,'DD-MM-RRRR')verified_date\n"
                + " from " + tableName + "  \n"
                + "p where p.panno='" + panno + "'\n";
        if (!utl.isnull(entityCode)) {
            panVerifiedQuery += "and p.entity_code='" + entityCode + "'";
        }
        return panVerifiedQuery;
    }//end method

}//end class
