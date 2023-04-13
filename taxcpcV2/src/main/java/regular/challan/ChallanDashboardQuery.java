/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.ViewClientMast;
import org.hibernate.criterion.Restrictions;
import regular.transaction.TransactionFilterEntity;

/**
 *
 * @author akash.meshram
 */
public class ChallanDashboardQuery {

    public String challanCountRecordQuery(String loginEntityCode, String workinguser, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, boolean fromAllocatedChallan, Util utl) {
        String count_record_query = "SELECT count(*) FROM  \n"
                + "    (select a.rowid_seq,\n"
                + "            a.entity_code,\n"
                + "            a.client_code,\n"
                + "            cm.parent_code,\n"
                + "            cm.g_parent_code,\n"
                + "            cm.sg_parent_code,\n"
                + "            cm.ssg_parent_code,\n"
                + "            cm.sssg_parent_code,\n"
                + "            a.acc_year,\n"
                + "            a.assesment_acc_year,\n"
                + "            a.quarter_no,\n"
                + "            a.month,\n"
                + "            a.from_date,\n"
                + "            a.to_date,\n"
                + "            a.data_entry_mode,\n"
                + "            a.file_doc_code,\n"
                + "            a.upload_template_code,\n"
                + "            a.tds_challan_no,\n"
                + "            a.tds_challan_date,\n"
                + "            a.ddo_slno,\n"
                + "            a.tds_type_code,\n"
                + "            a.tds_code,\n"
                + "            lhs_utility.get_name('TDS_CODE',a.tds_code) tds_section,\n"
                + "            a.tds_challan_minor_head,\n"
                + "            to_number(nvl((a.tds_challan_tds_amt), '0')) tds_challan_tds_amt,\n"
                + "            to_number(nvl((a.tds_challan_cess_amt), '0')) tds_challan_cess_amt,\n"
                + "            to_number(nvl((a.tds_challan_surcharge_amt), '0')) tds_challan_surcharge_amt,\n"
                + "            to_number(nvl((a.tds_challan_int_amt), '0')) tds_challan_int_amt,\n"
                + "            to_number(nvl((a.tds_challan_other_amt), '0')) tds_challan_other_amt,\n"
                + "\n"
                + "\n"
                + "            to_number(nvl((nvl(a.tds_challan_tds_amt,0) +\n"
                + "            nvl(a.tds_challan_cess_amt,0) +\n"
                + "            nvl(a.tds_challan_surcharge_amt,0) +\n"
                + "            nvl(a.tds_challan_int_amt,0) +\n"
                + "            nvl(a.tds_challan_other_amt,0)), 0)) total_tds_challan_amt,\n"
                + "\n"
                + "            a.bank_name,\n"
                + "            a.bank_branch,\n"
                + "            a.bank_swift_code,\n"
                + "            a.bank_address,\n"
                + "            cm.bank_branch_code,\n"
                + "            a.bank_ifsc_code,\n"
                + "            a.bank_bsr_code,\n"
                + "            a.inst_type,\n"
                + "            a.inst_no,\n"
                + "            a.inst_date,\n"
                + "            to_number(a.inst_amt) inst_amt,\n"
                + "            a.challan_error_status1,\n"
                + "            a.challan_error_status2,\n"
                + "            a.challan_error_status3,\n"
                + "            a.challan_error_status4,\n"
                + "            total_allocated_amount,\n"
                + "            to_number(nvl((total_allocated_amount), '0')) allocated_amount,\n"
                + "            to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+ nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) balance_to_allocate, total_allocated_count,\n"
                + "            a.flag,a.lastupdate,\n"
                + "            a.user_code, a.csi_verify_flag,decode(a.nil_challan_flag,'N','No','Y','Yes','') nil_challan_flag,decode(a.book_entry_flag,'N','No','Y','Yes','')book_entry_flag,\n"
                + "            'TAXCPC_DB_0412211015' taxcpc_db_ver\n"
                + "            from tds_challan_tran_load a, tds_mast b, client_mast cm\n"
                + "            where b.tds_code(+)=a.tds_code\n"
                + "            and cm.entity_code=a.entity_code\n"
                + "            and cm.client_code=a.client_code\n"
                + "            and a.entity_code = '" + loginEntityCode + "'";

        if (!utl.isnull(tdsChallanTranFilterSrch.getTds_section())) {
            count_record_query += " and a.tds_code=" + tdsChallanTranFilterSrch.getTds_section();
        }
        if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date()) && !utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {

            count_record_query += " and a.tds_challan_date between to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "', 'dd-mm-rrrr') and  to_date('" + tdsChallanTranFilterSrch.getTo_date() + "', 'dd-mm-rrrr')";

        } else if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date())) {
            count_record_query += " and a.tds_challan_date >= to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "', 'dd-mm-rrrr')";

        } else if (!utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
            count_record_query += " and a.tds_challan_date between <= to_date('" + tdsChallanTranFilterSrch.getTo_date() + "', 'dd-mm-rrrr')";
        }

        
          if (!utl.isnull(tdsChallanTranFilterSrch.getAllocationStatus())) {
            Double l_value = 0d;
            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                count_record_query += " and to_number(nvl((a.total_allocated_amount), '0')) =" + l_value;
            }

            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                 count_record_query += " and to_number(nvl((a.total_allocated_amount), '0')) > " + l_value;
//          count_record_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
//                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) >" + l_value;
           
            }

            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {

               // count_record_query += " and a.tds_challan_tds_amt = to_number(nvl((a.total_allocated_amount), '0')) =" + l_value;
               count_record_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0))= " + l_value;
            }

            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("OBC")) {
                //count_record_query += " and to_number(nvl((a.total_allocated_amount), '0')) <" + l_value;
               count_record_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) < " + l_value;
           
            }
        }

        String flag = "";
        if (!utl.isnull(tdsChallanTranFilterSrch.getChallanStatus())) {
            flag = tdsChallanTranFilterSrch.getChallanStatus();
            if (!flag.equalsIgnoreCase("P")) {
                count_record_query += " and a.csi_verify_flag='" + flag + "'";
            }

        }

        if (!utl.isnull(tdsChallanTranFilterSrch.getBankBranchCode())) {
            count_record_query += " and cm.bank_branch_code ='" + tdsChallanTranFilterSrch.getBankBranchCode() + "'";// Bank Branch Code
        }

        if (!utl.isnull(tdsChallanTranFilterSrch.getTds_challan_no())) {
            count_record_query += " and a.tds_challan_no = '" + tdsChallanTranFilterSrch.getTds_challan_no().trim() + "' \n";
        }

        count_record_query += " and exists (select 1\n"
                + "                          from client_mast w1\n"
                + "                         where w1.client_code = a.client_code\n"
                + "                           and (w1.client_code = '" + workinguser + "' or w1.parent_code = '" + workinguser + "' or\n"
                + "                                w1.g_parent_code = '" + workinguser + "' or w1.sg_parent_code = '" + workinguser + "' or\n"
                + "                                w1.ssg_parent_code = '" + workinguser + "' or w1.sssg_parent_code = '" + workinguser + "'))\n"
                + "                           and a.acc_year = '" + assessment.getAccYear() + "'\n"
                + "                           and a.quarter_no = '" + assessment.getQuarterNo() + "'\n"
                + "                           and a.tds_type_code = '" + assessment.getTdsTypeCode() + "'\n"
                + "                           order by a.rowid_seq asc) a\n"
                + "                            ";
        // System.out.println("count_record_query" + count_record_query);
        return count_record_query;
    }

    
    
    
    public String challanRecordQuery(String loginEntityCode, String clientcode, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, String search, int minVal, int maxVal, Util utl, boolean procedureFlag, boolean fromAllocatedChallan) {
        String record_detail_query = "select *\n"
                + "     from \n"
                + "    (select row_number() over (order by 1) SLNO,\n"
                + "            a.rowid_seq,\n"
                + "            a.entity_code,\n"
                + "            a.client_code,\n"
                + "            cm.parent_code,\n"
                + "            cm.g_parent_code,\n"
                + "            cm.sg_parent_code,\n"
                + "            cm.ssg_parent_code,\n"
                + "            cm.sssg_parent_code,\n"
                + "            a.acc_year,\n"
                + "            a.assesment_acc_year,\n"
                + "            a.quarter_no,\n"
                + "            a.month,\n"
                + "            a.from_date,\n"
                + "            a.to_date,\n"
                + "            a.data_entry_mode,\n"
                + "            a.file_doc_code,\n"
                + "            a.upload_template_code,\n"
                + "            a.tds_challan_no,\n"
                + "            a.tds_challan_date,\n"
                + "            a.ddo_slno,\n"
                + "            a.tds_type_code,\n"
                + "            a.tds_code,\n"
                + "            lhs_utility.get_name('TDS_CODE',a.tds_code) tds_section,\n"
                + "            a.tds_challan_minor_head,\n"
                + "            to_number(nvl((a.tds_challan_tds_amt), '0')) tds_challan_tds_amt,\n"
                + "            to_number(nvl((a.tds_challan_cess_amt), '0')) tds_challan_cess_amt,\n"
                + "            to_number(nvl((a.tds_challan_surcharge_amt), '0')) tds_challan_surcharge_amt,\n"
                + "            to_number(nvl((a.tds_challan_int_amt), '0')) tds_challan_int_amt,\n"
                + "            to_number(nvl((a.tds_challan_other_amt), '0')) tds_challan_other_amt,\n"
                + "\n"
                + "\n"
                + "            to_number(nvl((nvl(a.tds_challan_tds_amt,0) +\n"
                + "            nvl(a.tds_challan_cess_amt,0) +\n"
                + "            nvl(a.tds_challan_surcharge_amt,0) +\n"
                + "            nvl(a.tds_challan_int_amt,0) +\n"
                + "            nvl(a.tds_challan_other_amt,0)), 0)) total_tds_challan_amt,\n"
                + "\n"
                + "            a.bank_name,\n"
                + "            a.bank_branch,\n"
                + "            a.bank_swift_code,\n"
                + "            a.bank_address,\n"
                + "            cm.bank_branch_code,\n"
                + "            a.bank_ifsc_code,\n"
                + "            a.bank_bsr_code,\n"
                + "            a.inst_type,\n"
                + "            a.inst_no,\n"
                + "            a.inst_date,\n"
                + "            to_number(a.inst_amt) inst_amt,\n"
                + "            a.challan_error_status1,\n"
                + "            a.challan_error_status2,\n"
                + "            a.challan_error_status3,\n"
                + "            a.challan_error_status4,\n"
                + "            total_allocated_amount,\n"
                + "            to_number(nvl((total_allocated_amount), '0')) allocated_amount,\n"
                + "            to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+ nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) balance_to_allocate, total_allocated_count,\n"
                + "            a.flag,a.lastupdate,\n"
                + "            a.user_code, a.csi_verify_flag,decode(a.nil_challan_flag,'N','No','Y','Yes','') nil_challan_flag,decode(a.book_entry_flag,'N','No','Y','Yes','')book_entry_flag,\n"
                + "            'TAXCPC_DB_0412211015' taxcpc_db_ver\n"
                + "            from tds_challan_tran_load a, tds_mast b, client_mast cm\n"
                + "            where b.tds_code(+)=a.tds_code\n"
                + "            and cm.entity_code=a.entity_code\n"
                + "            and cm.client_code=a.client_code\n"
                + "            and  a.entity_code = '" + loginEntityCode + "'";

        if (!utl.isnull(tdsChallanTranFilterSrch.getTds_section())) {
            record_detail_query += " and a.tds_code=" + tdsChallanTranFilterSrch.getTds_section();
        }
        if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date()) && !utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {

            record_detail_query += " and a.tds_challan_date between to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "', 'dd-mm-rrrr') and  to_date('" + tdsChallanTranFilterSrch.getTo_date() + "', 'dd-mm-rrrr')";

        } else if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date())) {
            record_detail_query += " and a.tds_challan_date >= to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "', 'dd-mm-rrrr')";

        } else if (!utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
            record_detail_query += " and a.tds_challan_date between <= to_date('" + tdsChallanTranFilterSrch.getTo_date() + "', 'dd-mm-rrrr')";
        }

                if (!utl.isnull(tdsChallanTranFilterSrch.getAllocationStatus())) {
            Double l_value = 0d;
            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                record_detail_query += " and to_number(nvl((a.total_allocated_amount), '0')) =" + l_value;
            }

            if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                 record_detail_query += " and to_number(nvl((a.total_allocated_amount), '0')) > " + l_value;
//             record_detail_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
//                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) >  " + l_value;
//           
            }

          if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {

               // record_detail_query += " and a.tds_challan_tds_amt = to_number(nvl((a.total_allocated_amount), '0')) =" + l_value;
               record_detail_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0))= " + l_value;
            }


             if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("OBC")) {
                //record_detail_query += " and to_number(nvl((a.total_allocated_amount), '0')) <" + l_value;
               record_detail_query += " and to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))), 0)) - (nvl(total_allocated_amount, 0)+"
                                   + " nvl(a.tds_challan_int_amt, 0) + nvl(a.tds_challan_other_amt, 0)) < " + l_value;
           
            }
        }

        String flag = "";
        if (!utl.isnull(tdsChallanTranFilterSrch.getChallanStatus())) {
            flag = tdsChallanTranFilterSrch.getChallanStatus();
            if (!flag.equalsIgnoreCase("P")) {
                record_detail_query += " and a.csi_verify_flag='" + flag + "'";
            }

        }

        if (!utl.isnull(tdsChallanTranFilterSrch.getBankBranchCode())) {
            record_detail_query += " and cm.bank_branch_code ='" + tdsChallanTranFilterSrch.getBankBranchCode() + "'";// Bank Branch Code
        }

        if (!utl.isnull(tdsChallanTranFilterSrch.getTds_challan_no())) {
            record_detail_query += " and a.tds_challan_no = '" + tdsChallanTranFilterSrch.getTds_challan_no().trim() + "' \n";
        }

        record_detail_query += " and exists (select 1\n"
                + "                          from client_mast w1\n"
                + "                         where w1.client_code = a.client_code\n"
                + "                           and (w1.client_code = '" + clientcode + "' or w1.parent_code = '" + clientcode + "' or\n"
                + "                                w1.g_parent_code = '" + clientcode + "' or w1.sg_parent_code = '" + clientcode + "' or\n"
                + "                                w1.ssg_parent_code = '" + clientcode + "' or w1.sssg_parent_code = '" + clientcode + "'))\n"
                + "                           and a.acc_year = '" + assessment.getAccYear() + "'\n"
                + "                           and a.quarter_no = '" + assessment.getQuarterNo() + "'\n"
                + "                           and a.tds_type_code = '" + assessment.getTdsTypeCode() + "'\n"
                + "                           order by a.rowid_seq asc\n"
                + "                           )          \n"
                + "    where slno BETWEEN " + minVal + " AND " + maxVal ;
        System.out.println("record_detail_query--" + record_detail_query);
        return record_detail_query;

    }

    
    
    String getamountQuery(ViewClientMast client, Assessment assessment) {

        String query = "select  total_tds_challan_amt || '#' || tds_challan_tds_amt || '#' || tds_challan_int_amt || '#' || tds_challan_other_amt|| '#' || allocated_amount ||\n"
                + "   '#' || balance_to_allocate\n"
                + "  from ( select  sum(to_number(nvl((nvl(a.tds_challan_tds_amt, 0) +\n"
                + "                             nvl(a.tds_challan_cess_amt, 0) + \n"
                + "                             nvl(a.tds_challan_surcharge_amt, 0) +\n"
                + "                             nvl(a.tds_challan_int_amt, 0) +\n"
                + "                             nvl(a.tds_challan_other_amt, 0)), \n"
                + "                             0))) total_tds_challan_amt,\n"
                + "      -- sum(this_.tds_challan_tds_amt) as y1_,\n"
                + "         sum(to_number(nvl((a.tds_challan_tds_amt), '0'))) tds_challan_tds_amt, \n"
                + "         sum(to_number(nvl((a.tds_challan_int_amt), '0'))) tds_challan_int_amt ,\n"
                + "         sum(to_number(nvl((a.tds_challan_other_amt), '0'))) tds_challan_other_amt , \n"
                + "         sum(to_number(nvl((total_allocated_amount), '0'))) allocated_amount, \n"
                + "        sum(to_number(nvl((to_number(nvl((a.tds_challan_tds_amt), '0'))),\n"
                + "                             0)) -\n"
                + "               (nvl(total_allocated_amount, 0) +\n"
                + "                nvl(a.tds_challan_int_amt, 0) +\n"
                + "                nvl(a.tds_challan_other_amt, 0))) balance_to_allocate\n"
                + "       from tds_challan_tran_load a\n"
                + "        where exists (select 1 from client_mast w1\n"
                + "                   where w1.client_code = a.client_code\n"
                + "                   and (w1.client_code = '" + client.getClient_code() + "' or w1.parent_code = '" + client.getClient_code() + "' or\n"
                + "                        w1.g_parent_code = '" + client.getClient_code() + "' or w1.sg_parent_code = '" + client.getClient_code() + "' or\n"
                + "                        w1.ssg_parent_code = '" + client.getClient_code() + "' or w1.sssg_parent_code = '" + client.getClient_code() + "')) \n"
                + "                        and a.acc_year='" + assessment.getAccYear() + "' \n"
                + "                        and a.quarter_no='" + assessment.getQuarterNo() + "' \n"
                + "                        and a.tds_type_code='" + assessment.getTdsTypeCode() + "')";

        //System.out.println("GRAND TOTAL--"+query);
        return query;
    }

}
