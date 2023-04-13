/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.Map;

/**
 *
 * @author ayushi.jain
 */
public class TransactionDB {

    public String getTransactionCountQuery(ViewClientMast client, Assessment assment, TransactionFilterEntity filterEntity, String flag) {
        String whereclause = filterWhereClause(filterEntity, client);
        Assessment filterAsmt = filterEntity == null ? null : filterEntity.getAsmt();
        System.out.println("filterEntity--" + filterEntity.getTdsDeductReason());

        String query
                = "SELECT COUNT(*)\n"
                + "FROM (SELECT *\n"
                + "FROM TDS_TRAN_LOAD A,CLIENT_MAST B\n"
                + " WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                + "AND B.CLIENT_CODE = A.CLIENT_CODE\n"
                + " AND (B.CLIENT_CODE = '" + client.getClient_code() + "' OR B.PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "B.G_PARENT_CODE = '" + client.getClient_code() + "' OR B.SG_PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "B.SSG_PARENT_CODE = '" + client.getClient_code() + "' OR B.SSSG_PARENT_CODE = '" + client.getClient_code() + "')\n"
                + "AND B.ENTITY_CODE = '" + client.getEntity_code() + "'\n";

        if (!utl.isnull(flag)) {
            if (filterAsmt != null) {
                if (!utl.isnull(filterAsmt.getAccYear()) && !filterAsmt.getAccYear().equalsIgnoreCase("all")) {
                    query += "AND A.ACC_YEAR = '" + filterAsmt.getAccYear() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getQuarterNo()) && !filterAsmt.getQuarterNo().equalsIgnoreCase("all")) {
                    query += "AND A.QUARTER_NO = '" + filterAsmt.getQuarterNo() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getTdsTypeCode()) && !filterAsmt.getTdsTypeCode().equalsIgnoreCase("all")) {
                    query += "AND A.TDS_TYPE_CODE = '" + filterAsmt.getTdsTypeCode() + "'\n";
                }

            }

        } else {
            query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
            query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
            query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
        }

//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getAccYear()) && !filterAsmt.getAccYear().equalsIgnoreCase("all"))) {
//
//            query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
//        } else {
//
//        }
//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getQuarterNo()) && !filterAsmt.getQuarterNo().equalsIgnoreCase("all"))) {
//
//            query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
//        } else {
//
//        }
//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getTdsTypeCode()) && !filterAsmt.getTdsTypeCode().equalsIgnoreCase("all"))) {
//
//            query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
//        } else {
//
//        }
//        query += " and instr(d.tds_type_code_str,a.tds_type_code) <> 0 /* add new condition */ \n";
        query += whereclause
                //                + l_erroCodeWhereQuery
                + ")";
        return query;

    }

    public String getTransactionDataGridQuery(ViewClientMast client, Assessment assment, TransactionFilterEntity filterEntity, int min, int max, String flag) {
        String whereclause = filterWhereClause(filterEntity, client);

        Assessment filterAsmt = filterEntity == null ? null : filterEntity.getAsmt();
        String query
                = "SELECT *\n"
                + "  FROM (SELECT row_number() over (order by 1) SLNO,\n"
                + "               A.ROWID_SEQ,\n"
                + "               A.ENTITY_CODE,\n"
                + "               A.CLIENT_CODE,\n"
                + "               '' CLIENT_NAME,\n"
                + "               '' CLIENT_TYPE_CODE,\n"
                + "               A.ACC_YEAR,\n"
                + "               A.ASSESMENT_ACC_YEAR,\n"
                + "               A.QUARTER_NO,\n"
                + "               A.MONTH,\n"
                + "               A.FROM_DATE,\n"
                + "               A.TO_DATE,\n"
                + "               A.DATA_ENTRY_MODE,\n"
                + "               A.FILE_DOC_CODE,\n"
                + "               A.UPLOAD_TEMPLATE_CODE,\n"
                + "               A.CLIENT_PANNO,\n"
                + "               A.CLIENT_TANNO,\n"
                + "               A.DEDUCTEE_CODE,\n"
                + "               A.DEDUCTEE_NAME,\n"
                + "               A.DEDUCTEE_PANNO,\n"
                + "               '' PAN_4_CHAR_C_NC,\n"
                + "               A. DOB,\n"
                + "               A.TDS_TYPE_CODE,\n"
                + "               A.TDS_CODE,\n"
                + "               C.TDS_NAME TDS_SECTION,\n"
                + "               '' GOVT_TDS_CODE,\n"
                + "               A.TRAN_REF_NO,\n"
                + "               A.TRAN_REF_DATE,\n"
                + "               A.TRAN_AMT,\n"
                + "               A.TDS_DEDUCT_DATE,\n"
                + "               A.TDS_DEDUCT_REASON,\n"
                + "               D.TDS_DEDUCT_REASON_NAME,\n"
                + "               A.TDS_RATE,\n"
                + "               A.CESS_RATE,\n"
                + "               A.SURCHARGE_RATE,\n"
                + "               A.TDS_BASE_AMT,\n"
                + "               A.TDS_AMT,\n"
                + "               A.CESS_AMT,\n"
                + "               A.HECESS_AMT,\n"
                + "               A.SURCHARGE_AMT,\n"
                + "               '' TOTAL_TDS_AMT,\n"
                + "               A. TDS_ERROR_STATUS1,\n"
                + "               A.TDS_ERROR_STATUS2,\n"
                + "               A.TDS_ERROR_STATUS3,\n"
                + "               A.TDS_ERROR_STATUS4,\n"
                + "               A.TDS_ERROR_STATUS5,\n"
                + "               A.TDS_ERROR_STATUS6,\n"
                + "               A.TDS_ERROR_STATUS7,\n"
                + "               A.DEDUCTEE_REF_CODE1,\n"
                + "               A.DEDUCTEE_REF_CODE2,\n"
                + "               A.FILE_SEQNO,\n"
                + "               A.USER_CODE,\n"
                + "               A.LASTUPDATE,\n"
                + "               A.CERTIFICATE_NO,\n"
                + "               A.FLAG,\n"
                + "               A.ITAX_RATE,\n"
                + "               A.TDS_CHALLAN_ROWID_SEQ,\n"
                + "               '' TDS_CHALLAN_TDS_AMT,\n"
                + "               '' DEDUCTEE_CATG,\n"
                + "               A.PARENT_CODE,\n"
                + "               A.G_PARENT_CODE,\n"
                + "               A.SG_PARENT_CODE,\n"
                + "               A.SSG_PARENT_CODE,\n"
                + "               A.SSSG_PARENT_CODE,\n"
                + "               B.BANK_BRANCH_CODE,\n"
                + "(select bank_branch_code from client_mast cm where cm.entity_code=a.entity_code and cm.client_code=a.validation_client_code)  VALIDATION_BANK_BRANCH_CODE,"
                + "               A.PARTYBILLAMT,\n"
                + "               A.ACCOUNT_NO,\n"
                + "               '' IDENTIFICATION_NO ,A.Tds_Tran_Corr_Rowid_Seq, a.tds_tran_corr_approvedby, a.tds_tran_corr_approveddate\n"
                + "          FROM TDS_TRAN_LOAD          A,\n"
                + "               CLIENT_MAST            B,\n"
                + "               TDS_MAST               C,\n"
                + "               VIEW_TDS_DEDUCT_REASON D\n"
                + "         WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                + "           AND B.CLIENT_CODE = A.CLIENT_CODE\n"
                + "           AND A.TDS_CODE = C.TDS_CODE\n"
                + "           AND A.TDS_DEDUCT_REASON = D.TDS_DEDUCT_REASON(+)\n"
                + "           AND (B.CLIENT_CODE = '" + client.getClient_code() + "' OR B.PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "           B.G_PARENT_CODE = '" + client.getClient_code() + "' OR B.SG_PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "           B.SSG_PARENT_CODE = '" + client.getClient_code() + "' OR B.SSSG_PARENT_CODE = '" + client.getClient_code() + "')\n"
                + "           AND A.ENTITY_CODE = '" + client.getEntity_code() + "'\n";

        if (!utl.isnull(flag)) {
            if (filterAsmt != null) {
                if (!utl.isnull(filterAsmt.getAccYear()) && !filterAsmt.getAccYear().equalsIgnoreCase("all")) {
                    query += "AND A.ACC_YEAR = '" + filterAsmt.getAccYear() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getQuarterNo()) && !filterAsmt.getQuarterNo().equalsIgnoreCase("all")) {
                    query += "AND A.QUARTER_NO = '" + filterAsmt.getQuarterNo() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getTdsTypeCode()) && !filterAsmt.getTdsTypeCode().equalsIgnoreCase("all")) {
                    query += "AND A.TDS_TYPE_CODE = '" + filterAsmt.getTdsTypeCode() + "'\n";
                }
            }

        } else {
            query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
            query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
            query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
        }

        query += " and instr(d.tds_type_code_str(+), a.tds_type_code) <> 0 /* add new condition */ \n";
        query += whereclause
                //                + l_erroCodeWhereQuery
                + ")\n"
                + " WHERE SLNO BETWEEN " + min + " AND " + max + "";

        return query;
    }

    public String getTdsTranGrossSumQuery(ViewClientMast client, Assessment assment, TransactionFilterEntity filterEntity, String flag) {
        String whereclause = filterWhereClause(filterEntity, client);
        Assessment filterAsmt = filterEntity == null ? null : filterEntity.getAsmt();
        String query = " select tran_amt ||'#'||tds_amt from ( SELECT SUM(NVL(A.TRAN_AMT, '0')) TRAN_AMT , SUM(NVL(A.TDS_AMT, 0)) TDS_AMT\n"
                + "\n"
                + "  FROM TDS_TRAN_LOAD A, CLIENT_MAST B\n"
                + "\n"
                + "         WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                + "\n"
                + "           AND B.CLIENT_CODE = A.CLIENT_CODE " + whereclause + " \n"
                + "\n"
                + "           AND (B.CLIENT_CODE = '" + client.getClient_code() + "' OR B.PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "\n"
                + "               B.G_PARENT_CODE = '" + client.getClient_code() + "' OR B.SG_PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "\n"
                + "               B.SSG_PARENT_CODE = '" + client.getClient_code() + "' OR B.SSSG_PARENT_CODE = '" + client.getClient_code() + "')\n"
                + "\n"
                + "           AND A.ENTITY_CODE = '" + client.getEntity_code() + "'\n"
                + "\n";

        if (!utl.isnull(flag)) {
            if (filterAsmt != null) {
                if (!utl.isnull(filterAsmt.getAccYear()) && !filterAsmt.getAccYear().equalsIgnoreCase("all")) {
                    query += "AND A.ACC_YEAR = '" + filterAsmt.getAccYear() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getQuarterNo()) && !filterAsmt.getQuarterNo().equalsIgnoreCase("all")) {
                    query += "AND A.QUARTER_NO = '" + filterAsmt.getQuarterNo() + "'\n";
                }
                if (!utl.isnull(filterAsmt.getTdsTypeCode()) && !filterAsmt.getTdsTypeCode().equalsIgnoreCase("all")) {
                    query += "AND A.TDS_TYPE_CODE = '" + filterAsmt.getTdsTypeCode() + "'\n";
                }
            }

        } else {
            query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
            query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
            query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
        }
//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getAccYear()) && !filterAsmt.getAccYear().equalsIgnoreCase("all"))) {
//
//            query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
//        } else {
//
//        }
//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getQuarterNo()) && !filterAsmt.getQuarterNo().equalsIgnoreCase("all"))) {
//
//            query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
//        } else {
//
//        }
//        if (filterAsmt == null || (filterAsmt != null && !utl.isnull(filterAsmt.getTdsTypeCode()) && !filterAsmt.getTdsTypeCode().equalsIgnoreCase("all"))) {
//
//            query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
//        } else {
//
//        }
        //                + "           AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n"
        //                + "\n"
        //                + "           AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n"
        //                + "\n"
        //                + "              AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'"
        query += " )";
//                                                + l_tds_code + l_deductee_name + l_deductee_panno + l_tds_deduct_date + l_tds_amt + refCode + deducteeRefNo + accountNo+corrFilter
//                                                + l_erroCodeWhereQuery;
        return query;
    }

    public String filterWhereClause(TransactionFilterEntity tdsTranFltrSearch, ViewClientMast client) {
        DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
        String whereClause = "";
        String data_validation_client_code = "";
        String deductee_refcode_lpad = utl.padLeftZeros(tdsTranFltrSearch.getDeducteeRefNo(), 10);
        if (!utl.isnull(tdsTranFltrSearch.getChallan_bank_branch_code())) {
            data_validation_client_code = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                    "select client_code from client_mast where entity_code='" + client.getEntity_code() + "' and bank_branch_code='" + tdsTranFltrSearch.getChallan_bank_branch_code() + "'");
        }
        if (tdsTranFltrSearch != null) {
            if (!utl.isnull(tdsTranFltrSearch.getBankBranchCode())) {
                whereClause += " and b.bank_branch_code = '" + tdsTranFltrSearch.getBankBranchCode().trim() + "' \n";
            }
            if (!utl.isnull(tdsTranFltrSearch.getChallan_bank_branch_code())) {
                whereClause += "and a.validation_client_code = '" + data_validation_client_code + "'\n";
            }
            if (!utl.isnull(tdsTranFltrSearch.getTdsDeductReason())) {
                whereClause += " and a.TDS_DEDUCT_REASON = '" + tdsTranFltrSearch.getTdsDeductReason().trim() + "' \n";
            }

            if (!utl.isnull(tdsTranFltrSearch.getSection())) {
                whereClause += " and a.tds_code = '" + tdsTranFltrSearch.getSection().trim() + "' \n";
            }

            if (!utl.isnull(tdsTranFltrSearch.getDeducteeName())) {
                whereClause += " and a.deductee_name like '%" + tdsTranFltrSearch.getDeducteeName().toUpperCase() + "%' \n";
            }

            if (!utl.isnull(tdsTranFltrSearch.getPanno())) {
                whereClause += " and a.deductee_panno = '" + tdsTranFltrSearch.getPanno().toUpperCase() + "'\n";
            }

            if (!utl.isnull(tdsTranFltrSearch.getAccountNo())) {
                whereClause += " and a.account_no = '" + tdsTranFltrSearch.getAccountNo() + "' ";
            }

            if (!utl.isnull(tdsTranFltrSearch.getDeducteeRefNo())) {
                whereClause += " and ( a.deductee_ref_code1 = '" + tdsTranFltrSearch.getDeducteeRefNo() + "' OR a.deductee_ref_code1 = '" + utl.padLeftZeros(tdsTranFltrSearch.getDeducteeRefNo(), 10) + "')";
            }

            if (!utl.isnull(tdsTranFltrSearch.getRowIdSeq())) {
                whereClause += " and ( a.rowid_seq = '" + tdsTranFltrSearch.getRowIdSeq() + "')";
            }

            if (!utl.isnull(tdsTranFltrSearch.getFromDate()) && !utl.isnull(tdsTranFltrSearch.getToDate())) {
                whereClause += " and a.TRAN_REF_DATE between to_date('" + tdsTranFltrSearch.getFromDate() + "','dd-mm-rrrr') and to_date('" + tdsTranFltrSearch.getToDate() + "','dd-mm-rrrr')\n";
            } else if (!utl.isnull(tdsTranFltrSearch.getFromDate())) {
                whereClause += " and a.TRAN_REF_DATE  >= to_date('" + tdsTranFltrSearch.getFromDate() + "','dd-mm-rrrr') \n";
            } else if (!utl.isnull(tdsTranFltrSearch.getToDate())) {
                whereClause += " and a.TRAN_REF_DATE  <= to_date('" + tdsTranFltrSearch.getToDate() + "','dd-mm-rrrr')\n";

            }

            // TDS Amount Between
            if (!utl.isnull(tdsTranFltrSearch.getTdsAmountOperat())) {
                System.out.println("tdsTranFltrSearch.getTdsAmountOperat()-->" + tdsTranFltrSearch.getTdsAmountOperat());
                if (tdsTranFltrSearch.getTdsAmountOperat().equalsIgnoreCase(">=")) {
                    whereClause += " and a.tds_amt >= " + tdsTranFltrSearch.getTdsAmountFrom() + " \n";
                } else if (tdsTranFltrSearch.getTdsAmountOperat().equalsIgnoreCase("<=")) {
                    whereClause += " and a.tds_amt <= " + tdsTranFltrSearch.getTdsAmountFrom() + " \n";
                } else if (tdsTranFltrSearch.getTdsAmountOperat().equalsIgnoreCase("=")) {
                    whereClause += " and a.tds_amt = " + tdsTranFltrSearch.getTdsAmountFrom() + " \n";
                } else if (tdsTranFltrSearch.getTdsAmountOperat().equalsIgnoreCase("between")) {
                    whereClause += " and a.tds_amt between " + tdsTranFltrSearch.getTdsAmountFrom() + " and " + tdsTranFltrSearch.getTdsAmountTo() + " \n";
                }
            }

        }

        return whereClause;
    }

    public String getTransactionDownloadCountQuery(String client_code, String acc_year, String quarter_no, String tds_type_code, String NewwhrCls) {
        String l_return_query = "";
        try {

            l_return_query = "select   a.bank_bsr_code,\n"
                    + "         a.tds_challan_date,\n"
                    + "         a.tds_challan_no,\n"
                    + "         a.tds_challan_tds_amt,\n"
                    + "         a.tds_challan_other_amt,\n"
                    + "         a.tds_challan_int_amt,\n"
                    + "         a.deductee_ref_code1,\n"
                    + "         a.deductee_panno,\n"
                    + "         a.deductee_name,\n"
                    + "         a.account_no,\n"
                    + "         a.tds_name tds_section,\n"
                    + "         a.tran_amt,\n"
                    + "         a.tran_ref_date,\n"
                    + "         a.tds_deduct_date,\n"
                    + "         a.tds_amt,\n"
                    + "         a.tds_deduct_reason,\n"
                    + "         a.certificate_no,\n"
                    + "         a.country_code,\n"
                    + "         a.rate_type,\n"
                    + "         a.nature_of_remittance,\n"
                    + "         a.tin_uin_no,\n"
                    + "         a.deductee_address,\n"
                    + "         a.deductee_email_id,\n"
                    + "         a.deductee_phoneno,\n"
                    + "         a.bank_branch_code,\n"
                    + "         a.challan_bank_branch_code,\n"
                    + "         a.tds_challan_rowid_seq,\n"
                    + "         null filler2,\n"
                    + "         null filler3,\n"
                    + "         null filler4,\n"
                    + "         null filler5,\n"
                    + "         null filler6\n"
                    + " from    view_tran_data_excel_all a ";
            String query = " where (a.client_code = '" + client_code + "' OR a.parent_code = '" + client_code + "' OR\n"
                    + "               a.g_parent_code = '" + client_code + "' OR a.sg_parent_code = '" + client_code + "' OR\n"
                    + "               a.ssg_parent_code = '" + client_code + "' OR a.sssg_parent_code = '" + client_code + "')\n"
                    + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                    + "           and a.acc_year = '" + acc_year + "'\n"
                    + "           and a.quarter_no = '" + quarter_no + "'";
            l_return_query = l_return_query.concat(query);
            if (!utl.isnull(NewwhrCls)) {
                l_return_query = l_return_query.concat(NewwhrCls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_query;
    }//end method

    public String getTransactionDownloadDataGridQuery(String client_code, String acc_year, String quarter_no, String tds_type_code, int startInd, int endInd, String NewwhrCls) {
        String l_return_query = "";
        try {

            l_return_query = "select bank_bsr_code,\n"
                    + "        tds_challan_date,\n"
                    + "         tds_challan_no,\n"
                    + "         tds_challan_tds_amt,\n"
                    + "         tds_challan_other_amt,\n"
                    + "         tds_challan_int_amt,\n"
                    + "         deductee_ref_code1,\n"
                    + "         deductee_panno,\n"
                    + "         deductee_name,\n"
                    + "         account_no,\n"
                    + "         tds_section,\n"
                    + "         tran_amt,\n"
                    + "         tran_ref_date,\n"
                    + "         tds_deduct_date,\n"
                    + "         tds_amt,\n"
                    + "         tds_deduct_reason,\n"
                    + "         certificate_no,\n"
                    + "         country_code,\n"
                    + "         rate_type,\n"
                    + "         nature_of_remittance,\n"
                    + "         tin_uin_no,\n"
                    + "         deductee_address,\n"
                    + "         deductee_email_id,\n"
                    + "         deductee_phoneno,\n"
                    + "         bank_branch_code,\n"
                    + "         challan_bank_branch_code,\n"
                    + "         tds_challan_rowid_seq,\n"
                    + "          filler2,\n"
                    + "          filler3,\n"
                    + "          filler4,\n"
                    + "          filler5,\n"
                    + "          filler6 from ( select  rownum slno, a.bank_bsr_code,\n"
                    + "         a.tds_challan_date,\n"
                    + "         a.tds_challan_no,\n"
                    + "         a.tds_challan_tds_amt,\n"
                    + "         a.tds_challan_other_amt,\n"
                    + "         a.tds_challan_int_amt,\n"
                    + "         a.deductee_ref_code1,\n"
                    + "         a.deductee_panno,\n"
                    + "         a.deductee_name,\n"
                    + "         a.account_no,\n"
                    + "         a.tds_name tds_section,\n"
                    + "         a.tran_amt,\n"
                    + "         a.tran_ref_date,\n"
                    + "         a.tds_deduct_date,\n"
                    + "         a.tds_amt,\n"
                    + "         a.tds_deduct_reason,\n"
                    + "         a.certificate_no,\n"
                    + "         a.country_code,\n"
                    + "         a.rate_type,\n"
                    + "         a.nature_of_remittance,\n"
                    + "         a.tin_uin_no,\n"
                    + "         a.deductee_address,\n"
                    + "         a.deductee_email_id,\n"
                    + "         a.deductee_phoneno,\n"
                    + "         a.bank_branch_code,\n"
                    + "         a.challan_bank_branch_code,\n"
                    + "         a.tds_challan_rowid_seq,\n"
                    + "        null filler2,\n"
                    + "         lhs_utility.is_valid_panno('deductee_panno') filler3,\n"
                    + "         null filler4,\n"
                    + "         null filler5,\n"
                    + "         null filler6\n"
                    + " from    view_tran_data_excel_all a ";

            String query = " where (a.client_code = '" + client_code + "' OR a.parent_code = '" + client_code + "' OR\n"
                    + "               a.g_parent_code = '" + client_code + "' OR a.sg_parent_code = '" + client_code + "' OR\n"
                    + "               a.ssg_parent_code = '" + client_code + "' OR a.sssg_parent_code = '" + client_code + "')\n"
                    + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                    + "           and a.acc_year = '" + acc_year + "'\n"
                    + "           and a.quarter_no = '" + quarter_no + "'";
            l_return_query = l_return_query.concat(query);
            if (!utl.isnull(NewwhrCls)) {
                l_return_query = l_return_query.concat(NewwhrCls);
            }
            l_return_query = l_return_query.concat(") where slno between " + startInd + " and " + endInd + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l_return_query;
    }//end method
    Util utl;

    public TransactionDB() {
        utl = new Util();
    }

}
