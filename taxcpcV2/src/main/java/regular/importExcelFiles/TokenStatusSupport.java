/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.GlobalMessage;
import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author kapil.gupta
 */
public class TokenStatusSupport {

    StringBuilder sb = null;
    Util utl;

    public TokenStatusSupport() {
        utl = new Util();
        sb = new StringBuilder();
    }

    public StringBuilder getTokenStatusGrid(ArrayList<TokenStatusBean> tokenStatusList, String messageFlag, String tokenFlag) {

        if (tokenStatusList != null && tokenStatusList.size() > 0) {
            sb.append("<table class=\"table table-striped\" id=\"table\">");
            sb.append("<thead>\n"
                    + "<tr class=\"text-center\">\n"
                    + "<th class=\"token-srno\">Sr. No.</th>\n"
                    + "<th class=\"token-tokenNo\"><div class=\"table-head-inner\"><div class=\"table-heading\">Token No.</div></div></th>\n");
            if (!utl.isnull(tokenFlag) && tokenFlag.equals("I")) {
                sb.append("<th class=\"token-templateName\"><div class=\"table-head-inner\"><div class=\"table-heading\">Template Name</div></div></th>\n"
                        + "<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Insert Mode</div></div></th>\n");
            } else if (!utl.isnull(tokenFlag) && tokenFlag.equals("P")) {
                sb.append("<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">User Level</div></div></th>\n");
            }
            if (!utl.isnull(messageFlag) && messageFlag.equals("importStatus")) {
                sb.append("<th class=\"token-templateName\"><div class=\"table-head-inner\"><div class=\"table-heading\">Template Name</div></div></th>\n"
                        + "<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Insert Mode</div></div></th>\n");
            } else if (!utl.isnull(messageFlag) && messageFlag.equals("fvuStatus")) {
                sb.append("<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">User Level</div></div></th>\n");
            }
            sb.append("<th class=\"token-time\"><div class=\"table-head-inner\"><div class=\"table-heading\">Start Time</div></div></th>\n"
                    + "<th class=\"token-time\"><div class=\"table-head-inner\"><div class=\"table-heading\">End Time</div></div></th>\n"
                    + "<th class=\"token-processRemark\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Remark</div></div></th>\n");
//            if (!utl.isnull(messageFlag) && messageFlag.equals("tokenStatus")) {
//                sb.append("<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">Info</div></div></th>\n");
//            }
            sb.append("<th class=\"token-process\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Log</div></div></th>\n"
                    + "<th class=\"token-kill\"><div class=\"table-head-inner\"><div class=\"table-heading\">Kill Process</div></div></th>\n"
                    + "</tr>\n"
                    + "</thead><tbody>");

            for (TokenStatusBean statusData : tokenStatusList) {
                int index = tokenStatusList.indexOf(statusData) + 1;

                sb.append("<tr class=\"text-center\">");
                sb.append("<td>").append(statusData.getSlno()).append("</td>"); // Sr No
                sb.append("<td>").append(statusData.getProcess_seqno()).append("</td>"); // Process seq
                if (!utl.isnull(tokenFlag) && tokenFlag.equals("I")) {
                    sb.append("<td>").append(statusData.getTemplate_name()).append("</td>"); // Template name
                    sb.append("<td>").append(statusData.getProcess_insert_mode()).append("</td>"); // Process insert mode
                } else if (!utl.isnull(tokenFlag) && tokenFlag.equals("P")) {
                    String checkRecordInsFlag = statusData.getRecord_insert_flag().equals("1") ? "All (Login & Branch Level)" : "Login Level";
                    sb.append("<td>").append(checkRecordInsFlag).append("</td>"); // Process insert mode
                }

                if (!utl.isnull(messageFlag) && messageFlag.equals("importStatus")) {
                    sb.append("<td>").append(statusData.getTemplate_name()).append("</td>"); // Template name
                    sb.append("<td>").append(statusData.getProcess_insert_mode()).append("</td>"); // Process insert mode
                } else if (!utl.isnull(messageFlag) && messageFlag.equals("fvuStatus")) {
                    String checkRecordInsFlag = statusData.getRecord_insert_flag().equals("1") ? "All (Login & Branch Level)" : "Login Level";
                    sb.append("<td>").append(checkRecordInsFlag).append("</td>"); // Process insert mode
                }
                sb.append("<td>").append(statusData.getProcess_start_timestamp()).append("</td>"); // Process Start Time
                sb.append("<td>").append(statusData.getProcess_end_timestamp()).append("</td>");// Process End Time
                sb.append("<td>").append(statusData.getProcess_remark()).append("</td>");// Process remark

//                if (!utl.isnull(messageFlag) && messageFlag.equals("tokenStatus")) {
//                    sb.append("<td>");
//                    sb.append("<button type=\"button\" class=\"btn log-file-btn\" data-toggle=\"tooltip\" data-placement=\"top\"  title=\"Template name :" + statusData.getTemplate_name() + "Process insert mode :" + statusData.getProcess_insert_mode() + "\">"
//                            + "<i class=\"fa fa-info\"></i> </button>");
//                    sb.append("</td>");
//                }
                sb.append("<input type=\"hidden\" id=\"process_status_" + (index) + "\" value=\"" + statusData.getProcess_status() + "\">");
                sb.append("<input type=\"hidden\" id=\"template_code_" + (index) + "\" value=\"" + statusData.getTemplate_code() + "\">");

                if (!utl.isnull(messageFlag) && messageFlag.equals("importStatus")) {
                    sb.append("<td>");
                    sb.append("<button type=\"button\" class=\"btn log-file-btn\" title=\"\" data-toggle=\"modal\" "
                            + " onclick=\"getTextFileDataForIndiv('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "', '" + (statusData.getTemplate_code()) + "');\">"
                            + "<i class=\"fa fa-file-text\"></i></button>");
                    sb.append("</td>");
                } else if (!utl.isnull(messageFlag) && messageFlag.equals("fvuStatus")) {
                    sb.append("<td>");
                    sb.append("<button type=\"button\" class=\"btn log-file-btn\" title=\"\" data-toggle=\"modal\" "
                          //  + " onclick=\"getValidationProcessLog('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "');\">"
                         + " onclick=\"getTextFileDataForIndiv('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "', '" + (statusData.getTemplate_code()) + "');\">"
          
                            + "<i class=\"fa fa-file-text\"></i></button>");
                    sb.append("</td>");
                }

                if (!utl.isnull(tokenFlag) && tokenFlag.equals("I")) {
                    sb.append("<td>");
                    sb.append("<button type=\"button\" class=\"btn log-file-btn\" title=\"\" data-toggle=\"modal\" "
                            + " onclick=\"getTextFileDataForIndiv('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "', '" + (statusData.getTemplate_code()) + "');\">"
                            + "<i class=\"fa fa-file-text\"></i></button>");
                    sb.append("</td>");
                } else if (!utl.isnull(tokenFlag) && tokenFlag.equals("P")) {
                    sb.append("<td>");
                    sb.append("<button type=\"button\" class=\"btn log-file-btn\" title=\"\" data-toggle=\"modal\" "
                           // + " onclick=\"getValidationProcessLog('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "');\">"
                         + " onclick=\"getTextFileDataForIndiv('" + (statusData.getProcess_seqno()) + "', '" + (statusData.getProcess_status()) + "', '" + (statusData.getTemplate_code()) + "');\">"
          
                            + "<i class=\"fa fa-file-text\"></i></button>");
                    sb.append("</td>");
                }

                sb.append("<td>");
                sb.append("<button type=\"button\" class=\"btn kill-process-btn\" title=\"\" data-toggle=\"modal\" "
                        + " onclick=\"killIndivProcess( '" + (statusData.getProcess_seqno()) + "', '" + (statusData.getRecord_insert_flag()) + "');\">"
                        + "<i class=\"fa fa-window-close\"></i></button>");
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("</table>");
        } else {
            sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
        }
        return sb;
    }//end method
}
