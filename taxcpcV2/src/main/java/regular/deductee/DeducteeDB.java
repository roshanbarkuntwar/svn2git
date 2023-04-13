/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author ayushi.jain
 */
public class DeducteeDB {

    Util utl;

    public DeducteeDB() {
        utl = new Util();
    }

    public String deducteeCountQuery(ViewClientMast clientMast, Assessment assmt, DeducteeFilterEntity deducteeFltr) {

        String whereCls = deducteeWhereClause(deducteeFltr);
        String query = "select rec\n"
                + " from (select count(*) rec      \n"
                + " from (select a.deductee_ref_code1,\n"
             //   + "a.account_no, \n"
                + "a.deductee_panno, \n"
                + " a.deductee_name,\n"
                + " a.name_as_panno name_as_panno,\n"
                + " a.deductee_panno_verified\n"
                + "from tds_tran_load a, client_mast b \n"
                + "where b.entity_code = a.entity_code\n"
                + "and b.client_code = a.client_code\n"
                + " and (b.client_code = '" + clientMast.getClient_code() + "' Or\n"
                + " b.parent_code = '" + clientMast.getClient_code() + "' OR \n"
                + " b.g_parent_code = '" + clientMast.getClient_code() + "' or\n"
                + " b.sg_parent_code = '" + clientMast.getClient_code() + "' OR\n"
                + " b.ssg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                + " b.sssg_parent_code = '" + clientMast.getClient_code() + "'\n"
                + " )\n"
                + "and a.entity_code = '" + clientMast.getEntity_code() + "'                     \n"
                + "and a.acc_year = '" + assmt.getAccYear() + "'\n"
                + "and a.quarter_no = " + assmt.getQuarterNo() + "\n"
                + "and a.tds_type_code = '" + assmt.getTdsTypeCode() + "'\n"
                + whereCls
                + "group by a.deductee_ref_code1,\n"
        //        + "a.account_no,"
                + "a.deductee_panno, \n"
                + "a.deductee_name,\n"
                + "a.deductee_panno_verified, \n"
                + "  a.name_as_panno \n"
                + "         ))";

        return query;
    }

    public String deducteeDataGridQuery(ViewClientMast clientMast, Assessment ass, int min, int max, DeducteeFilterEntity deducteeFltr) {

        String whereCls = deducteeWhereClause(deducteeFltr);
        String query = "select *\n"
                + "  from (select rownum slno,               \n"
                + "               deductee_ref_code1,               \n"
                + "               deductee_panno,              \n"
                + "               deductee_name,               \n"
                + "               name_as_panno,               \n"
                + "               verifiedby_flag_name,               \n"
                + "               deductee_panno_verified \n"
//                + "               account_no        \n"
                + "          from (select a.deductee_ref_code1,\n"
             //   + "                       a.account_no,                       \n"
                + "                       a.deductee_panno,                       \n"
                + "                       a.deductee_name,                       \n"
                + "                       a.name_as_panno name_as_panno,                       \n"
                + "                       a.deductee_panno_verified,                       \n"
                + "                       case\n"
                + "                         when a.deductee_panno_verified = 'Y' then                         \n"
                + "                          'Verified'                       \n"
                + "                         when a.deductee_panno_verified = 'X' then                         \n"
                + "                          'Invalid'                       \n"
                + "                         when a.deductee_panno_verified = 'N' then                         \n"
                + "                          'Not Verified'                       \n"
                + "                         else                        \n"
                + "                          'Not Verified'                       \n"
                + "                       end verifiedby_flag_name,                       \n"
                + "                       count(*) rec                \n"
                + "                  from tds_tran_load a, client_mast b                \n"
                + "                 where b.entity_code = a.entity_code                      \n"
                + "                   and b.client_code = a.client_code                     \n"
                + "                   and (b.client_code = '" + clientMast.getClient_code() + "' Or                       \n"
                + "                       b.parent_code = '" + clientMast.getClient_code() + "' OR                       \n"
                + "                       b.g_parent_code = '" + clientMast.getClient_code() + "' or                       \n"
                + "                       b.sg_parent_code = '" + clientMast.getClient_code() + "' OR                       \n"
                + "                       b.ssg_parent_code = '" + clientMast.getClient_code() + "' or                       \n"
                + "                       b.sssg_parent_code = '" + clientMast.getClient_code() + "'                       \n"
                + "                       )                      \n"
                + "                   and a.entity_code = '" + clientMast.getEntity_code() + "'                      \n"
                + "                   and a.acc_year = '" + ass.getAccYear() + "'                      \n"
                + "                   and a.quarter_no = " + ass.getQuarterNo() + "                     \n"
                + "                   and a.tds_type_code = '" + ass.getTdsTypeCode() + "'                      \n"
                + whereCls
                + "                 group by a.deductee_ref_code1,                          \n"
          //      + "                          a.account_no,                          \n"
                + "                          a.deductee_panno,                          \n"
                + "                          a.deductee_name,                          \n"
                + "                          a.deductee_panno_verified,                          \n"
                + "                          a.name_as_panno                          \n"
                + "                )\n"
                + "        )where slno between " + min + " and " + max + "";


        return query;

    }

    public String deducteeWhereClause(DeducteeFilterEntity deducteeFltr) {
        String whereClause = "";

        if (deducteeFltr != null) {

            if (!utl.isnull(deducteeFltr.getPanno())) {
                whereClause += " and a.deductee_panno= '" + deducteeFltr.getPanno() + "' \n";
            }
            if (!utl.isnull(deducteeFltr.getDeducteeName())) {
                whereClause += " and a.deductee_name LIKE '%" + deducteeFltr.getDeducteeName() + "%' \n";
            }

            if (!utl.isnull(deducteeFltr.getVerifiedFlag())) {
                if (deducteeFltr.getVerifiedFlag().equalsIgnoreCase("N")) {
                    whereClause += " and a.deductee_panno_verified ='N' and a.deductee_panno <> 'PANAPPLIED' and a.deductee_panno <> 'PANINVALID' and a.deductee_panno <> 'PANNOTAVBL' and a.deductee_panno <> 'PANNOTREQD'\n";
                } else if (deducteeFltr.getVerifiedFlag().equalsIgnoreCase("X")) {
                    whereClause += " and a.deductee_panno_verified ='X'\n";
                } else if (deducteeFltr.getVerifiedFlag().equalsIgnoreCase("Y")) {
                    whereClause += " and a.deductee_panno_verified ='Y' and a.deductee_panno <> 'PANAPPLIED' and a.deductee_panno <> 'PANINVALID' and a.deductee_panno <> 'PANNOTAVBL' and a.deductee_panno <> 'PANNOTREQD'";
                }
            }

            if (!utl.isnull(deducteeFltr.getAccountNo())) {
                whereClause += " and a.account_no ='" + deducteeFltr.getAccountNo() + "'";
            }
            if (!utl.isnull(deducteeFltr.getDeducteeRefNo())) {
                whereClause += " and a.deductee_ref_code1 = '" + deducteeFltr.getDeducteeRefNo() + "'";
            }

        }

        return whereClause;

    }

}
