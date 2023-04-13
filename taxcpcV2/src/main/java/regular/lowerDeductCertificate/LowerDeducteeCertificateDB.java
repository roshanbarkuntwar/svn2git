/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author gaurav.khanzode
 */
public class LowerDeducteeCertificateDB {

    public String getTdsSplRateCountQuery(ViewClientMast clientMast, Assessment asmt, TdsSplRateMast tdsSplRateMastFilterSrch, Util utl) {
        String countSql = "SELECT count(*)\n"
                + "          FROM TDS_SPL_RATE_MAST T, CLIENT_MAST CM\n"
                + "         WHERE T.ENTITY_CODE = CM.ENTITY_CODE\n"
                + "           AND T.CLIENT_CODE = CM.CLIENT_CODE\n"
                + "           AND T.ENTITY_CODE = '" + clientMast.getEntity_code() + "'\n"
                + "           AND T.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           AND (CM.CLIENT_CODE = '" + clientMast.getClient_code() + "' OR CM.PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.G_PARENT_CODE = '" + clientMast.getClient_code() + "' OR CM.SG_PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.SSG_PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.SSSG_PARENT_CODE = '" + clientMast.getClient_code() + "')\n"
                //                + "           AND T.CLIENT_CODE =  '" + clientMast.getEntity_code() + "'\n"
                + getWhereClause(tdsSplRateMastFilterSrch, utl)
                + "         ORDER BY T.FROM_DATE DESC\n";

        return countSql;
    }

    public String getTdsSplRateDetailsQuery(ViewClientMast clientMast, Assessment asmt, TdsSplRateMast tdsSplRateMastFilterSrch, Util utl,
            int minVal, int maxVal) {

        String dataSql = "SELECT *\n"
                + "  FROM (SELECT T.TDS_TYPE_CODE,\n"
                + "               T.TDS_CODE,\n"
                + "               T.ENTITY_CODE,\n"
                + "               T.DEDUCTEE_PANNO,\n"
                + "               T.CLIENT_CODE,\n"
                + "               CM.BANK_BRANCH_CODE,\n"
                + "               T.CERTIFICATE_NO,\n"
                + "               T.ACC_YEAR,\n"
                + "               T.AMOUNT_CONSUMED,\n"
                + "               T.CERTIFICATE_DATE,\n"
                + "               T.CERTIFICATE_VALID_UPTO,\n"
                + "               T.CESS_RATE,\n"
                + "               T.DEDUCTEE_CODE,\n"
                + "               T.FLAG,\n"
                + "               T.FROM_DATE,\n"
                + "               T.HECESS_RATE,\n"
                + "               T.LASTUPDATE,\n"
                + "               T.SURCHARGE_RATE,\n"
                + "               T.TDS_LIMIT_AMT,\n"
                + "               T.TDS_RATE,\n"
                + "               T.TDS_TRAN_ROWID_SEQ,\n"
                + "               T.TO_DATE,\n"
                + "               T.USER_CODE, ROWNUM SLNO\n"
                + "          FROM TDS_SPL_RATE_MAST T, CLIENT_MAST CM\n"
                + "         WHERE T.ENTITY_CODE = CM.ENTITY_CODE\n"
                + "           AND T.CLIENT_CODE = CM.CLIENT_CODE\n"
                + "           AND T.ENTITY_CODE = '" + clientMast.getEntity_code() + "'\n"
                + "           AND T.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                + "           AND (CM.CLIENT_CODE = '" + clientMast.getClient_code() + "' OR CM.PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.G_PARENT_CODE = '" + clientMast.getClient_code() + "' OR CM.SG_PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.SSG_PARENT_CODE = '" + clientMast.getClient_code() + "' OR\n"
                + "               CM.SSSG_PARENT_CODE = '" + clientMast.getClient_code() + "')\n"
                //                + "           AND T.CLIENT_CODE =  '" + clientMast.getClient_code() + "'\n"
                + getWhereClause(tdsSplRateMastFilterSrch, utl)
                + "         ORDER BY T.FROM_DATE DESC)\n"
                + " WHERE SLNO BETWEEN " + minVal + " AND " + maxVal + "";

        return dataSql;
    }

    private String getWhereClause(TdsSplRateMast tdsSplRateFilterSearch, Util utl) {
        String whreClause = "";
        if (tdsSplRateFilterSearch != null) {
//            if (!utl.isnull(tdsSplRateFilterSearch.getTds_type_code())) {
//                whreClause += " and tds_type_code = '" + tdsSplRateFilterSearch.getTds_type_code().trim() + "' \n";
//            }
            if (!utl.isnull(tdsSplRateFilterSearch.getDeductee_panno())) {
                whreClause += " and deductee_panno like '%" + tdsSplRateFilterSearch.getDeductee_panno().trim() + "%' \n";
            }
            if (!utl.isnull(tdsSplRateFilterSearch.getCertificate_no())) {
                whreClause += " and certificate_no like '%" + tdsSplRateFilterSearch.getCertificate_no().trim() + "%' \n";
            }
        }
        return whreClause;
    }
}
