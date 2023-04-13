/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.bulkTxtFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import regular.dashboard.processDetails.ProcessDetailBean;

/**
 *
 * @author akash.meshram
 */
public class BulkTextFileSupport {

    public String getBulkTextFileCountQuery(ViewClientMast clientMast, Assessment ass, BulkTextFileBean filterObj,String tokenNo) {

        String return_sql = " SELECT COUNT(*)\n"
                + "  FROM ( "
                + getBulkTextFileQuery(filterObj, clientMast, ass, 0, 0,tokenNo)
                + ") ";
        System.out.println("count query --" + return_sql);
        return return_sql;
    }//end

    public String getBulkTextFileQuery(BulkTextFileBean filterObj, ViewClientMast clientMast, Assessment ass, int minVal, int maxVal,String tokenNo) {

        String return_sql = "SELECT *  FROM (SELECT row_number() over(order by 1) SLNO ,\n"
                + "        a.process_seqno,\n"
                + "        b.client_code,\n"
                + "        b.bank_branch_code,\n"
                + "        a.process_start_timestamp,\n"
                + "        a.process_end_timestamp,\n"
                + "        a.fvu_txt_file_name \n"
                + "       from lhssys_process_log a, client_mast b\n"
                + "       where b.entity_code=a.entity_code\n"
                + "       and b.client_code=a.client_code\n"
                + "       and a.entity_code='" + clientMast.getEntity_code() + "'\n"
                + "       and (\n"
                + "       b.client_code='" + clientMast.getClient_code() + "' or\n"
                + "       b.parent_code='" + clientMast.getClient_code() + "' or\n"
                + "       b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                + "       b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                + "       b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                + "       b.sssg_parent_code='" + clientMast.getClient_code() + "' \n"
                + "       )\n"
                + "       and   a.acc_year='" + ass.getAccYear() + "'\n"
                + "       and   a.quarter_no='" + ass.getQuarterNo() + "'\n"
                + "       and   a.tds_type_code='" + ass.getTdsTypeCode() + "'      \n"
                + "       and (a.Process_type='BULK_FVU_TEXT_GEN')\n"
                + "       and   (a.process_seqno='"+tokenNo+"' or a.parent_process_seqno='"+tokenNo+"')";

        if (!filterObj.getProcess_seqno().isEmpty()) {
            return_sql = return_sql + "and a.process_seqno='" + filterObj.getProcess_seqno() + "'";
        }
        if (!filterObj.getBank_branch_code().isEmpty()) {
            return_sql = return_sql + "and b.bank_branch_code='" + filterObj.getBank_branch_code() + "'";
        }

        return_sql += ")";
        System.out.println("minVal-" + minVal);
        System.out.println("maxVal-" + maxVal);
        if (minVal != 0 && maxVal != 0) {
            return_sql += " WHERE SLNO BETWEEN " + minVal + " AND " + maxVal + "";
        }

        return return_sql;
    }

    public String getProcessDetailsGrid(ArrayList<BulkTextFileBean> details, Util utl, int minVal,String BLOB_FILE_DOWNLOAD_FLAG,String oracalDrive) {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        System.out.println("inside string builder");
        sb.append("  <table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">");
        sb.append("                    <thead>");
        sb.append("                    <tr>");
        sb.append("                    <th class=\"text-center\">Sr No.</th>");
        sb.append("                    <th class=\"text-center\">Proc Seq No.</th>");
        //     sb.append("                    <th class=\"text-center\">Client Code</th>");
        sb.append("                    <th class=\"text-center\">Bank Branch Code</th>");
        sb.append("                    <th class=\"text-center\">Process Start Timestamp</th>");
        sb.append("                    <th class=\"text-center\">Process End Timestamp</th>");
        sb.append("                    <th class=\"text-center\">Fvu Text File Name</th>");
        sb.append("                    <th class=\"text-center\">Download</th>");
        sb.append("                    </thead>");
        
        sb.append("<tbody>");

        if (details != null && details.size() > 0) {
            int count = 0;

            for (BulkTextFileBean detail : details) {
//                count = details.indexOf(detail) + 1;

                sb.append("<tr>");

                sb.append("<td class=\"td-action text-center px-0\">");
                sb.append(minVal);
                sb.append("</td>");

                sb.append("<td class=\"text-center px-0\">");
                sb.append(detail.getProcess_seqno());
                sb.append("</td>");
               
                sb.append("<td class=\"text-center px-0\">");
                sb.append(detail.getBank_branch_code());
                sb.append("</td>");

                if(detail.getProcess_start_timestamp()!=null){
                sb.append("<td class=\"text-center px-0\">");
                sb.append(dateformate.format(detail.getProcess_start_timestamp()));
                sb.append("</td>");
                }else{
                sb.append("<td></td>");    
                }
              
                if(detail.getProcess_end_timestamp()!=null){
                sb.append("<td class=\"text-center px-0\">");
                sb.append(dateformate.format(detail.getProcess_end_timestamp()));
                sb.append("</td>");
                }else{
                sb.append("<td></td>");    
                }
                
                sb.append("<td class=\"text-center px-0\">");
                sb.append(detail.getFvu_txt_file_name());
                sb.append("</td>");
                
                sb.append("<td class=\"text-center px-0\">");
                sb.append("<i class=\"fa download cursor-pointer text-primary mr-1\" onclick=\"downloadBlobFile('"+BLOB_FILE_DOWNLOAD_FLAG+"','"+count+"')\"></i>");
                sb.append("</td>");
                sb.append("<input type=\"hidden\"  id=\"process_seq_no~"+count +"\" value=\"" + detail.getProcess_seqno()+ "\"/>");
                sb.append("<input type=\"hidden\"  id=\"fvu_txt_file_name~"+count +"\" value=\"" + detail.getFvu_txt_file_name()+ "\"/>");
                sb.append("<input type=\"hidden\"  id=\"file_download_from_directory~"+count +"\" value=\"" + oracalDrive +File.separator+"TEXT_FILES"+File.separator + detail.getFvu_txt_file_name() + "\"/>");
                sb.append("</tr>");
                minVal++;

            }
        } else {
            sb.append("<tr>");
            sb.append("<td class=\"td-action text-center px-0\" colspan=\"8\">");
            sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
            sb.append("</td>");
            sb.append("</tr>");

        }
        sb.append("</tbody>");
        sb.append("<input type=\"hidden\"  id=\"oracledrivename\" value=\"" + oracalDrive + "\"/>");
        sb.append("</table>");

        return sb.toString();
    }//end

}
