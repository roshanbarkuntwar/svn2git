/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.GlobalMessage;
import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadStatusSupport {

    StringBuilder sb = null;
    Util utl;

    public DownloadStatusSupport() {
        utl = new Util();
        sb = new StringBuilder();
    }

    public StringBuilder getDownloadStatusGrid(ArrayList<DownloadStatusBean> downloadStatusList,String BLOB_FILE_DOWNLOAD_FLAG) {

        if (downloadStatusList != null && downloadStatusList.size() > 0) {
            sb.append("<table class=\"table table-striped\" id=\"table\">");
            sb.append("<thead>\n"
                    + "<tr class=\"text-center\">\n"
                    + "<th>Sr. No.</th>\n"
                    + "<th><div class=\"table-head-inner\"><div class=\"table-heading\">Token No.</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>\n"
                    + "<th><div class=\"table-head-inner\"><div class=\"table-heading\">Report Name</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>\n"
                    + "<th><div class=\"table-head-inner\"><div class=\"table-heading\">Start Time</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>\n"
                    + "<th><div class=\"table-head-inner\"><div class=\"table-heading\">End Time</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>\n"
                    + "<th><div class=\"table-head-inner\"><div class=\"table-heading\">Download Status</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>\n"
                    + "<th colspan=\"2\">Download/Kill Process</th>\n"
                    + "</tr>\n"
                    + "</thead><tbody>");

            for (DownloadStatusBean statusData : downloadStatusList) {
                int index = downloadStatusList.indexOf(statusData) + 1;

                sb.append("<tr class=\"text-center\">");
                sb.append("<td>").append(statusData.getSlno()).append("</td>"); // Sr No
                sb.append("<td>").append(statusData.getProcess_seqno()).append("</td>"); // Process seq
                sb.append("<td>").append(statusData.getReport_name()).append("</td>"); // Download Type Name
                sb.append("<td>").append(statusData.getProcess_start_timestamp()).append("</td>"); // Process Start Time
                sb.append("<td>").append(statusData.getProcess_end_timestamp()).append("</td>");// Process End Time

                //download status column
                String process_status_class = "";
                switch (statusData.getProcess_status()) {
                    case "DB":
                        process_status_class = "text-danger";
                        break;
                    case "ZK":
                        process_status_class = "text-danger";
                        break;
                    case "DC":
                        process_status_class = "text-success";
                        break;
                    default:
                        process_status_class = "text-primary";
                        break;
                }
                sb.append("<td class=\"").append(process_status_class).append("\">").append(statusData.getProcess_status_name()).append("</td>");

                //download/kill process action column
                switch (statusData.getProcess_status()) {
                    case "DC":
                        // Download column
                        sb.append("<td style=\"width: 55px !important;\">");
                        if (!utl.isnull(statusData.getFvu_txt_file_name()) || !utl.isnull(statusData.getFvu_files_path()) ) {
                            sb.append("<i class=\"fa download text-primary\" title=\"Download\" aria-hidden=\"true\" style=\"cursor: pointer;\" onclick=\"downloadBulkFile(" + (index) + ", '" + (statusData.getProcess_seqno()) + "','"+ (BLOB_FILE_DOWNLOAD_FLAG)+"');\"></i>");
                            sb.append("<input type=\"hidden\" id=\"fileNamePath_" + (index) + "\" value=\"" + statusData.getFvu_txt_file_name() + "\">");
                            sb.append("<input type=\"hidden\" id=\"zipFileNamePath_" + (index) + "\" value=\"" + statusData.getFvu_files_path() + "\">");
                            sb.append("<input type=\"hidden\" id=\"flag_" + (index) + "\" value=\"" + statusData.getFlag() + "\">");
                        } else {
                            sb.append("<i class=\"fa download\" title=\"Not Available\" style=\"cursor: pointer;\" aria-hidden=\"true\"></i>");
                        }
                        sb.append("</td>");
                        // Kill process column
                        sb.append("<td style=\"width: 55px !important;\">");
                        sb.append("</td>");
                        break;
                    case "DA":
                        // Download column
                        sb.append("<td style=\"width: 55px !important;\">");
                        sb.append("<i class=\"fa download \" style=\"cursor: pointer;\" aria-hidden=\"true\" \"></i>");
                        sb.append("</td>");
                        // Kill process column
                        sb.append("<td style=\"width: 55px !important;\">");
                        sb.append("<i title=\"Kill Process\" class=\"fa fa-remove text-primary\" aria-hidden=\"true\" style=\"margin-left:10px; cursor: pointer;\" "
                                + "onclick=\"killProcess('" + statusData.getProcess_seqno() + "');\"></i>");
                        sb.append("</td>");
                        break;
                    default:
                        sb.append("<td style=\"width: 55px !important;\"></td>");
                        sb.append("<td style=\"width: 55px !important;\"></td>");
                        break;
                }
                sb.append("</tr>");
            }
            sb.append("</tbody></table>");
        } else {
            sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
        }
        return sb;
    }//end method
}//end class
