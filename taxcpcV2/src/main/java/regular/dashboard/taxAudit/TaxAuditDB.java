/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.taxAudit;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.ArrayList;

/**
 *
 * @author ayushi.jain
 */
public class TaxAuditDB {

    public String firstReportQuery(ViewClientMast client, Assessment asmt) {
        String query = "select "
                //                + " a.entity_code,\n"
                + "        a.acc_year,\n"
                + "        a.quarter_no,\n"
                + "        a.tds_type_code,\n"
                //                + "        a.tds_challan_rowid_seq,\n"
                + "        a.client_code,\n"
                + "        a.tran_month,\n"
                + "        a.tds_name,\n"
                + "        a.tds_amt,\n"
                + "        b.client_code challan_client_code ,\n"
                + "        d.tanno ,\n"
                + "        b.nil_challan_flag  ,\n"
                + "        b.bank_bsr_code ,\n"
                + "        b.tds_challan_no  ,\n"
                + "        b.tds_challan_date   ,\n"
                + "        b.tds_challan_tds_amt ,\n"
                + "        to_char(D.FILE_UPLOAD_ACK_DATE,'dd-MON-rrrr') , d.file_upload_ack_no,  (d.file_upload_ack_pdf_path || d.file_upload_ack_pdf_name) as filePath\n"
                + "from\n"
                + "(select a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.tds_challan_rowid_seq,\n"
                + "       a.client_code,\n"
                + "       to_char(to_date(a.tran_ref_date,'DD-MON-RRRR'),'MON-RRRR') tran_month,\n"
                + "       LHS_UTILITY.GET_NAME('TDS_CODE',A.TDS_CODE) TDS_NAME,\n"
                + "       sum(nvl(to_number(tds_amt),0)) tds_amt\n"
                + "from   tds_tran_load a, client_mast b\n"
                + " where  b.entity_code=a.entity_code\n"
                + " and    b.client_code=a.client_code\n"
                //                + " and    a.entity_code='" + client.getEntity_code() + "'\n"
                //                + " and    (b.client_code='" + client.getClient_code() + "' or b.parent_code='" + client.getClient_code() + "' or b.g_parent_code='" + client.getClient_code() + "' or b.sg_parent_code='" + client.getClient_code() + "' or b.ssg_parent_code='" + client.getClient_code() + "' or b.sssg_parent_code='" + client.getClient_code() + "')\n"
                //                + " and    a.acc_year='" + asmt.getAccYear() + "' "
                + ""
                //                + "from   tds_tran_load a\n"
                //                + "where  entity_code='" + client.getEntity_code() + "'\n"
                //                + "and    acc_year='" + asmt.getAccYear() + "'\n"
                //                + "and    client_code='" + client.getClient_code() + "'\n"

                + "group by  a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.tds_challan_rowid_seq,\n"
                + "       a.client_code,\n"
                + "       to_char(to_date(a.tran_ref_date,'DD-MON-RRRR'),'MON-RRRR'),\n"
                + "       A.TDS_CODE\n"
                + ") a, tds_challan_tran_load b, lhssys_tds_return_tran d\n"
                + "where b.rowid_Seq=a.tds_challan_rowid_seq\n"
                + "and   b.entity_code=d.entity_code\n"
                + "and   b.client_code=d.client_code\n"
                + "and   b.acc_year=d.acc_year\n"
                + "and   b.quarter_no = d.quarter_no \n"
                + "and   b.tds_type_code=d.tds_type_code";

        return query;
    }

    public String secondReportQuery(ViewClientMast client, Assessment asmt) {
        String query = "select        a.acc_year,\n"
                + "        a.quarter_no,\n"
                + "        a.tds_type_code,\n"
                + "        a.client_code, \n"
                + "        a.tds_deduct_reason,\n"
                + "        a.tds_deduct_reason_name,\n"
                + "        a.tran_amt,       \n"
                + "      a.tds_amt\n"
                + "from\n"
                + "(select a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.client_code,      \n"
                + "       a.tds_deduct_reason,\n"
                + "       b.tds_deduct_reason_name,\n"
                + "       sum(nvl(a.tran_amt,0)) tran_amt,\n"
                + "       sum(nvl(to_number(tds_amt),0)) tds_amt\n"
                + "from   tds_bank_tran a, view_tds_deduct_reason b, client_mast c\n"
                + "where  a.entity_code='" + client.getEntity_code() + "'\n"
                + "and    a.acc_year='" + asmt.getAccYear() + "'\n"
                + "and    c.entity_code=a.entity_code\n"
                + "and    c.client_code=a.client_code\n"
                + "and    (c.client_code='" + client.getClient_code() + "' or \n"
                + "        c.parent_code='" + client.getClient_code() + "' or \n"
                + "        c.g_parent_code='" + client.getClient_code() + "' or \n"
                + "        c.sg_parent_code='" + client.getClient_code() + "' or \n"
                + "        c.ssg_parent_code='" + client.getClient_code() + "' or \n"
                + "        c.sssg_parent_code='" + client.getClient_code() + "' \n"
                + "       )\n"
                + "and    a.error_code_str is null\n"
                + "and    a.tds_deduct_reason=b.tds_deduct_reason(+)\n"
                + "group by  a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.client_code, \n"
                + "       a.tds_deduct_reason,\n"
                + "       b.tds_deduct_reason_name\n"
                + ") a";
//        String query = "select"
//                //                + "  a.entity_code,\n"
//                + "        a.acc_year,\n"
//                + "        a.quarter_no,\n"
//                + "        a.tds_type_code,\n"
//                + "        a.client_code, \n"
//                + "        a.tds_deduct_reason,\n"
//                + "        a.tds_deduct_reason_name,\n"
//                + "        a.tran_amt,       \n"
//                + "      a.tds_amt\n"
//                + "from\n"
//                + "(select a.entity_code,\n"
//                + "       a.acc_year,\n"
//                + "       a.quarter_no,\n"
//                + "       a.tds_type_code,\n"
//                + "       a.client_code,      \n"
//                + "       a.tds_deduct_reason,\n"
//                + "       b.tds_deduct_reason_name,\n"
//                + "       sum(nvl(a.tran_amt,0)) tran_amt,\n"
//                + "       sum(nvl(to_number(tds_amt),0)) tds_amt\n"
//                + "from   tds_bank_tran a, view_tds_deduct_reason b\n"
//                + "where  entity_code='" + client.getEntity_code() + "'\n"
//                + "and    acc_year='" + asmt.getAccYear() + "'\n"
//                + "and    a.error_code_str is null\n"
//                + "and    a.client_code='" + client.getClient_code() + "'\n"
//
//
//                + "and    a.tds_deduct_reason=b.tds_deduct_reason(+)\n"
//                + "group by  a.entity_code,\n"
//                + "       a.acc_year,\n"
//                + "       a.quarter_no,\n"
//                + "       a.tds_type_code,\n"
//                + "       a.client_code, \n"
//                + "       a.tds_deduct_reason,\n"
//                + "       b.tds_deduct_reason_name\n"
//                + ") a";
        return query;
    }

    public String thirdReportQuery(ViewClientMast client, Assessment asmt) {
        String query = "select          a.acc_year,\n"
                + "        a.quarter_no,\n"
                + "        a.tds_type_code,\n"
                + "        a.client_code,\n"
                + "       a.tds_table_code,\n"
                + "        a.tran_amt,\n"
                + "        a.tds_amt\n"
                + "from\n"
                + "(select a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.client_code,\n"
                + "       a.tds_table_code,\n"
                + "       sum(nvl(a.tran_amt,0)) tran_amt,\n"
                + "       sum(nvl(to_number(tds_amt),0)) tds_amt\n"
                + "from   tds_bank_tran a, client_mast b\n"
                + "where  a.entity_code='" + client.getEntity_code() + "'\n"
                + "and    a.acc_year='" + asmt.getAccYear() + "'\n"
                + "and    b.entity_code=a.entity_code\n"
                + "and    b.client_code=a.client_code\n"
                + "and    (b.client_code='" + client.getClient_code() + "' or \n"
                + "        b.parent_code='" + client.getClient_code() + "' or\n"
                + "        b.g_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.sg_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.ssg_parent_code='" + client.getClient_code() + "' or\n"
                + "        b.sssg_parent_code='" + client.getClient_code() + "'\n"
                + "       )\n"
                + "and    a.error_code_str is not null\n"
                + "group by  a.entity_code,\n"
                + "       a.acc_year,\n"
                + "       a.quarter_no,\n"
                + "       a.tds_type_code,\n"
                + "       a.client_code,\n"
                + "       a.tds_table_code\n"
                + ") a";
//        String query = "select  "
//                //                + "a.entity_code,\n"
//                + "        a.acc_year,\n"
//                + "        a.quarter_no,\n"
//                + "        a.tds_type_code,\n"
//                + "        a.client_code,\n"
//                + "       a.tds_table_code,\n"
//                + "        a.tran_amt,\n"
//                + "        a.tds_amt\n"
//                + "from\n"
//                + "(select a.entity_code,\n"
//                + "       a.acc_year,\n"
//                + "       a.quarter_no,\n"
//                + "       a.tds_type_code,\n"
//                + "       a.client_code,\n"
//                + "       a.tds_table_code,\n"
//                + "       sum(nvl(a.tran_amt,0)) tran_amt,\n"
//                + "       sum(nvl(to_number(tds_amt),0)) tds_amt\n"
//                + "from   tds_bank_tran a\n"
//                + "where  entity_code='" + client.getEntity_code() + "'\n"
//                + "and    acc_year='" + asmt.getAccYear() + "'\n"
//                + "and    a.error_code_str is not null\n"
//                + "and    a.client_code='" + client.getClient_code() + "'\n"
//                + "group by  a.entity_code,\n"
//                + "       a.acc_year,\n"
//                + "       a.quarter_no,\n"
//                + "       a.tds_type_code,\n"
//                + "       a.client_code,\n"
//                + "       a.tds_table_code\n"
//                + ") a";
        return query;
    }

    public String getFilePathQuery(ViewClientMast client, Assessment asmt) {
        String query = "select filePath from ( " + firstReportQuery(client, asmt) + " )";
        return query;
    }

    public ArrayList<String> getSheetsHeading(String reportNo) {
        ArrayList<String> list = new ArrayList<String>();
        if (!utl.isnull(reportNo)) {
            if (reportNo.equalsIgnoreCase("1")) {
                list = new ArrayList<String>();
//                list.add("Entity");
                list.add("ACC_YEAR");
                list.add("QUARTER_NO");
                list.add("FORM_TYPE");
//                list.add("Tds Challan Rowid Seq");
                list.add("SOL_ID");
                list.add("TRAN_MONTH");
                list.add("SECTION_CODE");
                list.add("TDS_AMOUNT");
                list.add("CENTRALIZED_SOL_ID");
                list.add("CENTRALIZED_TAN_NO");
                list.add("NIL_CHALLAN_FLAG");
                list.add("CHALLAN_BSR_CODE");
                list.add("CHALLAN_NO");
                list.add("CHALLAN_DATE");
                list.add("CHALLAN_AMOUNT");
                list.add("PRN_DATE");
                list.add("PRN_NUMBER");
            } else if (reportNo.equalsIgnoreCase("2")) {
                list = new ArrayList<String>();
//                list.add("Entity");
                list.add("ACC_YEAR");
                list.add("QUARTER_NO");
                list.add("FORM_TYPE");
                list.add("SOL_ID");
//                list.add("Tran Month");
                list.add("REASON_FLAG");
                list.add("REASON_FLAG_NAME");
                list.add("TRANSACTION_AMOUNT");
                list.add("TDS_AMOUNT");

            } else if (reportNo.equalsIgnoreCase("3")) {
                list = new ArrayList<String>();
//                list.add("Entity");
                list.add("ACC_YEAR");
                list.add("QUARTER_NO");
                list.add("FORM_TYPE");
                list.add("SOL_ID");
//                list.add("Tran Month");
                list.add("TDS_TABLE_CODE");
                list.add("TRANSACTION_AMOUNT");
                list.add("TDS_AMOUNT");

            }
        }

        return list;
    }

    Util utl;

    public TaxAuditDB() {
        utl = new Util();
    }
}
