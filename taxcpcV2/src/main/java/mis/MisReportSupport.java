/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.MisReportConfigMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaurav.khanzode
 */
public class MisReportSupport {

    private Util utl;

    public MisReportSupport() {
    }

    public MisReportSupport(Util utl) {
        this.utl = utl;
    }

    public void runProcedureBatchFile(String batchFileName, String oracleDriveName) throws IOException {

        if (utl.isnull(batchFileName)) {
            throw new IOException("Procedure batch file name not found!!");
        }

        File drive = new File(oracleDriveName);
        if (drive.exists()) {
            String batchFilePath = drive.getPath() + File.separator + "TEXT_FILES" + File.separator + batchFileName;

            File batchFile = new File(batchFilePath);
            if (batchFile.exists()) {
                String pathToExecute = "cmd /c start " + batchFile.getPath();

                utl.generateLog("path To Execute", pathToExecute);

                Runtime runtime = Runtime.getRuntime();
                runtime.exec(pathToExecute);

                utl.generateLog("Batch file executed successfully...", "");
            }

        }
    }

    public String getMisDashboard(ArrayList<ReportGroupListData> reportGroupList, Util utl, String entity_code,String tds_type_code) {
        StringBuilder mis_grid = new StringBuilder();

        if (reportGroupList != null && !reportGroupList.isEmpty()) {
            int group_index = 0;

            mis_grid.append("<div class=\"panel panel-default\">");

            for (ReportGroupListData group_data : reportGroupList) {
                mis_grid.append("<div class=\"panel-heading\">");
                mis_grid.append("<div class=\"main-heading\"><div class=\"main-count\"><span class=\"heading\">").append(group_index).append(1).append(".</span></div> <div class=\"main-label\"><h5 class=\"float-left\">").append(group_data.getL_report_group()).append("</h5><div class=\"main-heading-count\"> ");

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                MisReportConfigMastDAO repConfigMastDao = factory.getMisReportConfigMastDAO();
                if (!utl.isnull(group_data.getL_report_group())) {
                    List<MisReportConfigMast> objListalertDirectMail = repConfigMastDao.getReportList(group_data.getL_report_group(), utl, entity_code,tds_type_code);

                    if (objListalertDirectMail != null && !objListalertDirectMail.isEmpty()) {

                        mis_grid.append(" <a class=\"badge-pill badge-danger position-absolute pulsate\"> ").append(objListalertDirectMail.size()).append("</a>");
                        mis_grid.append("</div></div></div></div>");

                        String report_group_data = this.getGroupDetails(objListalertDirectMail, group_data.getL_report_group(), utl);
                        mis_grid.append(report_group_data);
                    } else {
                        mis_grid.append("</div></div></div></div>");
                    }
                } else {
                    mis_grid.append("</div></div></div></div>");
                }
                group_index++;
            }

            mis_grid.append("</div>");
        }
        return mis_grid.toString();
    }//end method

    public String getGroupDetails(List<MisReportConfigMast> objListalertDirectMail, String report_group, Util utl) {
        StringBuilder report_grid = new StringBuilder("");

        if (objListalertDirectMail != null && !objListalertDirectMail.isEmpty()) {
            report_grid.append("<div class=\"panel-body\"><div class=\"row\">");

            int count = 0;
            try {
                for (MisReportConfigMast directMail : objListalertDirectMail) {
                    if (!utl.isnull(directMail.getRep_status()) && directMail.getRep_status().equals("T") && directMail.getRep_app_type().equals("V2") ) {
                        String rep_req_dwnld_flag = directMail.getRep_req_dwnl_flag();
                        String rep_assesm_fltr_flag = directMail.getRep_assesm_fltr_flag();
                        String rep_upload_flag = directMail.getRep_upload_flag();
                        String rep_upload_template_code = directMail.getRep_upload_template_code();
                        String rep_upload_dir = directMail.getRep_upload_dir();

                        report_grid.append("<input type=\"hidden\" name=\"rep_pagination_flag\" value=\"").append(directMail.getRep_pagination_flag()).append("\" id=\"pagin_flag_").append(report_group).append("~").append(count + 1).append("\">");
                        report_grid.append("<input type=\"hidden\" name=\"rep_upload_dir\" value=\"").append(directMail.getRep_upload_dir()).append("\" id=\"rep_upload_dir_").append(report_group).append("~").append(count + 1).append("\">");
                        report_grid.append("<input type=\"hidden\" name=\"seq_id\" value=\"").append(directMail.getRep_seq_id()).append("\" id=\"l_seqid_").append(report_group).append("~").append(count + 1).append("\">");
                        report_grid.append("<input type=\"hidden\" name=\"report_group\" value=\"").append(report_group).append("\" id=\"l_report_group_").append(report_group).append("~").append(count + 1).append("\">");
                        report_grid.append("<input type=\"hidden\" name=\"rep_upload_template_code\" value=\"").append(rep_upload_template_code).append("\" id=\"rep_upload_template_code_").append(report_group).append("~").append(count + 1).append("\">");

                        report_grid.append("<div class=\"col-md-6\">");
                        report_grid.append("<div class=\"subheading\">");
                        report_grid.append("<div class=\"label-heading \">");

                        report_grid.append("&nbsp;&nbsp;<label><p class=\"count\"><span>").append(count + 1)
                                .append("</span></p>&nbsp;&nbsp;<span id=\"").append(report_group).append("~").append(count + 1).append("\" class=\"cursor-pointer\" ");

                        if (utl.isnull(directMail.getRep_dashboard_req_flag()) || !directMail.getRep_dashboard_req_flag().equalsIgnoreCase("F")) {
                            report_grid.append("onclick=\"showSelectedReport(this.id);\"");
                        } else {
                            report_grid.append("onclick=\"addActionError('message', 'Sorry, Dashboard is not available for this report, you can download report file!');\"");
                        }

                        report_grid.append(" title=\"Click here to show detail\">").append(directMail.getRep_desc().trim()).append("</span></label>");

                        report_grid.append("</div>");

                        //******** Configuration applied settings status
                        report_grid.append("<div class=\"\">"); // class = edit-sec
                        if (!utl.isnull(rep_assesm_fltr_flag) && rep_assesm_fltr_flag.trim().equalsIgnoreCase("T")) {
//                        report_grid.append("<input type=\"checkbox\" class=\"cursor-pointer\" checked=\"checked\" title=\"Assessment filters applied.\">");
                        } else {
//                        report_grid.append("<input type=\"checkbox\" class=\"cursor-pointer\" title=\"No configurations applied.\">");
                        }
                        report_grid.append("</div></div>");
                        // *****Upload Report button
                        report_grid.append("<div class=\"edit-sec-2\" style=\"right: 53px;\">");
                        if (!utl.isnull(rep_upload_flag) && rep_upload_flag.trim().equalsIgnoreCase("T")) {
                            report_grid.append("<i class=\"fa upload cursor-pointer\" title=\"Upload ").append(directMail.getRep_desc()).append("\"").append("id=\"").append(report_group).append("~").append(count + 1).append("\"").append("onclick=\"showImportTab(this.id);\"></i>");
                        } else {
                            report_grid.append("<span><i class=\"fa upload cursor-pointer\" title=\"Upload Not Available\" aria-hidden=\"true\" style=\"opacity: .3;cursor: no-drop;\"></i></span>");
                        }
                        report_grid.append("</div>");
                        //******** Request download button
                        report_grid.append("<div class=\"edit-sec-2\">");
                        if (!utl.isnull(rep_req_dwnld_flag) && rep_req_dwnld_flag.trim().equalsIgnoreCase("T")) {
                            report_grid.append("<i class=\"fa download cursor-pointer\" title=\"Download ").append(directMail.getRep_desc()).append(" Data\" aria-hidden=\"true\" onclick=\"misBulkDownloadRequest(").append(directMail.getRep_seq_id()).append(", '").append(directMail.getRep_desc().trim()).append("');\"></i>");
                        } else {
                            report_grid.append("<span><i class=\"fa download cursor-pointer\" title=\"Download Not Available\" aria-hidden=\"true\" style=\"opacity: .3;cursor: no-drop;\"></i></span>");
                        }
                        report_grid.append("</div>");

                        report_grid.append("</div>");

                        count++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            report_grid.append("</div></div>");
        }
        return report_grid.toString();
    }//end method

    public String getReportData(ArrayList<ReportHeadAttributes> rep_attributes, ArrayList<ArrayList<String>> rep_list_data,
            ArrayList<String> rep_data_total) {

        StringBuilder rep_grid = new StringBuilder();
        try {
            if (rep_attributes != null && rep_list_data != null) {
                if (!rep_attributes.isEmpty() && !rep_list_data.isEmpty()) {
                    StringBuilder sb_hdr = new StringBuilder();

                    rep_grid.append("<div class=\"table-header\" id=\"table-head\">                    \n"
                            + "                    <table class=\"table table-striped mb-0\" id=\"\">\n");

                    /**
                     * Building report header table
                     */
                    rep_grid.append("<thead>  \n");

                    String hidden_header = "<thead class=\"d-none\">";

                    sb_hdr.append("<tr class=\"text-center\"> \n");
                    try {
                        rep_attributes.forEach(rep_attribute -> {
                            sb_hdr.append("<th style=\"min-width: ").append(rep_attribute.getReport_header_width()).append("px;\n                                        max-width: ").append(rep_attribute.getReport_header_width()).append("px;\n                                        width: ").append(rep_attribute.getReport_header_width()).append("px; \n                                        font-size: 12px;\">").append(rep_attribute.getReport_header_captions()).append("</th>\n");
                        });
                    } catch (Exception e) {
//                        System.out.println("Error in loop : reason - " + e.getMessage());
                    }
                    sb_hdr.append("</tr>\n"
                            + "</thead>\n");

                    rep_grid.append(sb_hdr.toString());
                    rep_grid.append("</table>\n"
                            + "</div>");

                    /**
                     * Building report body table
                     */
                    rep_grid.append("<div class=\"table-body\" id=\"table-body\" onscroll=\"global_HorizontalTableScroller('table-body', 'table-head');\">\n"
                            + "                    <table class=\"table table-striped mb-0\" id=\"table\">\n");

                    rep_grid.append(hidden_header).append(sb_hdr.toString());

                    rep_grid.append("                        <tbody>\n");
                    for (ArrayList<String> report_list : rep_list_data) {
                        rep_grid.append("<tr class=\"mis-table-td\">\n");
                        try {
                            for (String report_data : report_list) {
                                int data_col_index = report_list.indexOf(report_data);

                                if (rep_attributes.get(data_col_index).getReport_header_data_type().contains("~")) {
                                    rep_grid.append("<td  style=\"min-width: ").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px;\nmax-width: ").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px; \nwidth:").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px; padding-left:3px; \ntext-align:").append(rep_attributes.get(data_col_index).getReport_header_data_type().equalsIgnoreCase("NUMBER") ? "right"
                                            : rep_attributes.get(data_col_index).getReport_header_data_type().equalsIgnoreCase("VARCHAR2") ? "left" : "center").append(";\">");
                                    rep_grid.append(report_data).append("</td>");

                                } else {
                                    rep_grid.append("<td style=\"min-width: ").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px;\nmax-width: ").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px; \nwidth:").append(rep_attributes.get(data_col_index).getReport_header_width()).append("px;\npadding-left:3px; \ntext-align:").append(rep_attributes.get(data_col_index).getReport_header_data_type().equalsIgnoreCase("NUMBER") ? "right"
                                            : rep_attributes.get(data_col_index).getReport_header_data_type().equalsIgnoreCase("VARCHAR2") ? "left" : "center").append(";\">").append(report_data).append("</td>\n");
                                }
                            }
                        } catch (Exception e) {
                        }
                        rep_grid.append("</tr>\n");
                    }
                    rep_grid.append("</tbody></table></div>");
                } else {
                    rep_grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
            } else {
                rep_grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep_grid.toString();
    }//end method
}//end class
