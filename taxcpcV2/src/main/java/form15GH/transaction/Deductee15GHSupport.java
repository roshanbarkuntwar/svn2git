/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.DeducteeBflagAmtTran;
import hibernateObjects.ViewClientMast;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aniket.naik
 */
public class Deductee15GHSupport {

    Util utl;

    public Deductee15GHSupport() {
        utl = new Util();
    }

    public StringBuilder getDeducteeData(List<ViewDeducteeMastDetailPOJO15GH> deducteesByTypeList, ViewClientMast clientmast, int startindex, int pageNo) {
        // System.out.println("DeducteeDataSupport");

        StringBuilder sb = new StringBuilder();
        if (deducteesByTypeList != null && deducteesByTypeList.size() > 0) {
            sb.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            sb.append("                    <thead>");
            sb.append("                          <tr>");
            sb.append("                     <th class=\"check-action text-center\" >   ");

            sb.append("                                <div class=\"custom-control custom-checkbox action-check \">\n"
                    + "                                    <input type=\"checkbox\" class=\"custom-control-input toggle-action-section\" id=\"checkAll\" onclick=\"setAllChecked(this)\" >\n"
                    + "                                    <label class=\"custom-control-label\" for=\"checkAll\" ></label>\n"
                    + "                                </div>\n");
            sb.append("</th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 Sr. No.");
            sb.append("                                <hr>");
            sb.append("                                Branch Code");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 Name");
            sb.append("                                <hr>");
            sb.append("                                PAN No.");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 Name as on PAN No.");
            sb.append("                                <hr>");
            sb.append("                                 Cust Id");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 15G/H Ref.No.");
            sb.append("                                <hr>");
            sb.append("                                 Verified Status");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("      </thead>");

            int i = 0;
            //  System.out.println("Called in Filter");
            sb.append("<tbody>");
            for (ViewDeducteeMastDetailPOJO15GH viewDeducteeMast : deducteesByTypeList) {
                i++;
                sb.append("<tr>");
                // sb.append("<td>");
                sb.append("<td >\n"
                        + "<div class=\"custom-control custom-checkbox action-check \">\n"
                        + "<input type=\"checkbox\" class=\"custom-control-input toggle-action-section\" id=\"check~" + i + "\"  onclick=\"openActionDiv(this.id,'" + viewDeducteeMast.getRowid_seq() + "');\">\n"
                        + "<label class=\"custom-control-label\" for=\"check~" + i + "\" ></label>\n"
                        + "</div>\n");
                if (!utl.isnull(clientmast.getEdit_right()) && clientmast.getEdit_right().equalsIgnoreCase("1")) {
                    sb.append("<form method=\"post\" action=\"deducteesAction15gh\" id=\"editForm~" + i + "\">");
                    sb.append("<input type=\"hidden\" name=\"action\" value=\"edit\"/>");
                    sb.append("<input type=\"hidden\" name=\"updateVersion\" value=\"").append(viewDeducteeMast.getDeductee_code() == null ? "" : viewDeducteeMast.getDeductee_code()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"PanNoVal\" value=\"").append(viewDeducteeMast.getPanno() == null ? "" : viewDeducteeMast.getPanno().toUpperCase()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"pageNumber\" value=\"").append(pageNo).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"rowid_seq\" value=\"").append(viewDeducteeMast.getRowid_seq()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"update_data_row\" value=\"").append(viewDeducteeMast.getDeductee_code() == null ? "" : viewDeducteeMast.getDeductee_code()).append("\"/>");
                    sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\" style=\"color:#29AECC;\"></s:a>");
                    sb.append("</form>");
                } else {

                }
//                sb.append("</td>");
//                sb.append("<td>");
                if (!utl.isnull(clientmast.getQuery_right()) && clientmast.getQuery_right().equalsIgnoreCase("1")) {
                    sb.append("<form method=\"post\" action=\"deducteesAction15gh\" id=\"viewForm~" + i + "\">");
                    sb.append("<input type=\"hidden\" name=\"action\" value=\"view\"/>");
                    sb.append("<input type=\"hidden\" name=\"updateVersion\" value=\"").append(viewDeducteeMast.getDeductee_code() == null ? "" : viewDeducteeMast.getDeductee_code()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"PanNoVal\" value=\"").append(viewDeducteeMast.getPanno() == null ? "" : viewDeducteeMast.getPanno().toUpperCase()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"pageNumber\" value=\"").append(pageNo).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"rowid_seq\" value=\"").append(viewDeducteeMast.getRowid_seq()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"update_data_row\" value=\"").append(viewDeducteeMast.getDeductee_code() == null ? "" : viewDeducteeMast.getDeductee_code()).append("\"/>");
                    sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\" style=\"color:#29AECC;\"></s:a>");
                    sb.append("</form>");
                } else {

                }
//                sb.append("</td>");
//                sb.append("<td>");
                if (!utl.isnull(clientmast.getDelete_right()) && clientmast.getDelete_right().equalsIgnoreCase("1")) {
                    sb.append("<form method=\"post\" action=\"deducteesAction15gh\" id=\"deleteForm~" + i + "\">");
                    sb.append("<input type=\"hidden\" name=\"action\" value=\"delete\"/>");
                    sb.append("<input type=\"hidden\" name=\"rowid_seq\" value=\"").append(viewDeducteeMast.getRowid_seq()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"PanNoVal\" value=\"").append(viewDeducteeMast.getPanno() == null ? "" : viewDeducteeMast.getPanno().toUpperCase()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"DeducteeName\" value=\"").append(viewDeducteeMast.getDeductee_name() == null ? "" : viewDeducteeMast.getDeductee_name().toUpperCase()).append("\"/>");
                    sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\" style=\"color:#29AECC;\"></s:a>");
                    sb.append("</form>");
                }
                sb.append("</td>");
                //  System.out.println("start index--->"+startindex);
                sb.append("<td><div class=\"data-height font-weight-bold\">").append(i + startindex).append("</div><hr>")
                        .append(utl.isnull(viewDeducteeMast.getBank_branch_code()) ? "" : viewDeducteeMast.getBank_branch_code())
                        .append("</td>");

                sb.append("<td><div class=\"data-height font-weight-bold\">").append(utl.isnull(viewDeducteeMast.getDeductee_name()) ? "" : viewDeducteeMast.getDeductee_name())
                        .append("</div><hr>")
                        .append(utl.isnull(viewDeducteeMast.getPanno()) ? "" : viewDeducteeMast.getPanno())
                        .append("</td>");
                sb.append("<td><div class=\"data-height font-weight-bold\">").append(utl.isnull(viewDeducteeMast.getName_as_panno()) ? "" : viewDeducteeMast.getName_as_panno())
                        .append("</div><hr>").append(utl.isnull(viewDeducteeMast.getDeduction_ref_no()) ? "" : viewDeducteeMast.getDeduction_ref_no())
                        .append("</td>");
                sb.append("<td><div class=\"data-height font-weight-bold\">").append(utl.isnull(viewDeducteeMast.getReference_no()) ? "" : viewDeducteeMast.getReference_no())
                        .append("</div><hr>").append(utl.isnull(viewDeducteeMast.getVerifiedby_flag_name()) ? "" : viewDeducteeMast.getVerifiedby_flag_name())
                        .append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody>");
            sb.append("</table>");
        } else {
            // sb.append("<td>")
        }
        return sb;
    }

    public StringBuilder getAmountDetails(List<DeducteeBflagAmtTran> readListForDeductee, Assessment asmt, String viewFlag) {
        

        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LinkedHashMap<String, String> sectionAsHashMap = factory.getTdsMastDAO().getSectionAsHashMapForDeductee(asmt.getTdsTypeCode(), asmt.getQuarterToDate());
        try {
            sb.append("<table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            sb.append(" <thead>");
            sb.append("  <tr>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 SR.NO");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                 Identification No Of Relevant Investment/Account, Etc.");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Section Under Which Tax is Deductable");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                              Nature Of Income");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                             Amount Of Income");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            if (!utl.isnull(viewFlag) && !viewFlag.equalsIgnoreCase("view")) {
                sb.append("<i class=\"fa fa-plus-circle table-edit-pencil-icon\" aria-hidden=\"true\" onclick=\"addNewRow();\" title=\"Add New Row\"></i>\n");
            } else {
                sb.append("<i class=\"fa fa-plus-circle table-edit-pencil-icon\" aria-hidden=\"true\" style=\"color:black;\" title=\"Sorry ! You have not required privilege.\"></i>\n");
            }

            sb.append("     </div>");
            sb.append("   </div>");
            sb.append("</th>");
            sb.append("</tr>");
            sb.append("</thead>");

            Double sumAmt = 0d;
            if (readListForDeductee != null && readListForDeductee.size() > 0) {
                int count = 0;
                sb.append("<tbody>");
                for (int i = 0; i < readListForDeductee.size(); i++) {
                    count++;
                    try {
                        sumAmt += readListForDeductee.get(i).getAmount();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    sb.append("<tr id=\"row~").append(count).append("\">\n");
                    sb.append("<td>").append(count)
                            .append("</td>");
                    sb.append("<td>")
                            .append("<input type=\"text\" class=\"transparent_input form-control\" id=\"accountNo~")
                            .append(count).append("\" name=\"deducteeBflagAmtTranList[")
                            .append(count - 1).append("].account_no\" title=\"")
                            .append(readListForDeductee.get(i).getAccount_no()).append("\" value=\"")
                            .append(readListForDeductee.get(i).getAccount_no()).append("\" maxLength=\"30\" >\n")
                            .append("</td>");

                    sb.append("<td>");
                    sb.append("<select class=\"form-control\" id=\"section~").append(count).append("\" name=\"deducteeBflagAmtTranList[")
                            .append(count - 1).append("].section\" title=\"")
                            .append(readListForDeductee.get(i).getSection()).append("\" value=\"")
                            .append(readListForDeductee.get(i).getSection()).append("\" onchange=\"setNatureOfIncome(this.id);\">");

                    sb.append("<option value=\"").append("").append("\">Select</option>");

                    if (sectionAsHashMap != null && sectionAsHashMap.size() > 0) {
                        for (Map.Entry<String, String> entry : sectionAsHashMap.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            String selected = "";
                            if (!utl.isnull(key) && key.equalsIgnoreCase(readListForDeductee.get(i).getSection())) {
                                selected = "selected";
                            }
                            sb.append("<option value=\"").append(key).append("\" ").append(selected).append(">").append(value).append("</option>");
                        }
                    }
                    sb.append("</select>\n");
                    sb.append("</td>");

                    sb.append("<td>")
                            .append(" <input type=\"text\" class=\"transparent_input form-control\" id=\"natureOfIncome~").append(count)
                            .append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].nature_of_income\" title=\"")
                            .append(readListForDeductee.get(i).getNature_of_income()).append("\" value=\"")
                            .append(readListForDeductee.get(i).getNature_of_income())
                            .append("\" maxLength=\"30\" readonly=\"true\">\n")
                            .append("</td>");

                    sb.append("<td>")
                            .append("<input type=\"text\" class=\"transparent_input form-control\" style=\"text-align:right\" id=\"amount~")
                            .append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].amount\" title=\"")
                            .append(readListForDeductee.get(i).getAmount()).append("\" value=\"")
                            .append(readListForDeductee.get(i).getAmount())
                            .append("\" onkeypress=\"return validateNumber(event);\" >\n")
                            .append("</td>");

                    sb.append("<td>");
                    if (!utl.isnull(viewFlag) && !viewFlag.equalsIgnoreCase("view")) {
                        sb.append("<i id=\"delete~").append(count).append("\" class=\"fa fa-times table-delete-icon\" aria-hidden=\"true\" onclick=\"deleteRow(this.id);\" title=\"Delete Row\"></i>\n");
                    } else {
                        sb.append("<i id=\"delete~").append(count).append("\" class=\"fa fa-times table-delete-icon\" style=\"color:black;\" aria-hidden=\"true\" title=\"Sorry ! You have not required privileges.\"></i>\n");
                    }
                    sb.append("                    </td>\n");
                    sb.append("                    <input type=\"hidden\"  id=\"bflag_tran_seq_id~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].bflag_tran_seq_id\" value=\"").append(readListForDeductee.get(i).getRowid_seq()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"entity_code~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].entity_code\" value=\"").append(readListForDeductee.get(i).getEntity_code()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"client_code~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].client_code\" value=\"").append(readListForDeductee.get(i).getClient_code()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"acc_year~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].acc_year\" value=\"").append(readListForDeductee.get(i).getAcc_year()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"reference_no~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].reference_no\" value=\"").append(utl.isnull(readListForDeductee.get(i).getReference_no()) ? "" : readListForDeductee.get(i).getReference_no()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"panno~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].panno\" value=\"").append(readListForDeductee.get(i).getPanno()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"quarterNo~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].quarter_no\" value=\"").append(readListForDeductee.get(i).getQuarter_no()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"tdsTypeCOde~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].tds_type_code\" value=\"").append(readListForDeductee.get(i).getTds_type_code()).append("\">\n");
                    sb.append("                    <input type=\"hidden\"  id=\"deductee_mast_15gh_rowid_seq~").append(count).append("\" name=\"deducteeBflagAmtTranList[").append(count - 1).append("].deductee_mast_15gh_rowid_seq\" value=\"").append(readListForDeductee.get(i).getDeductee_mast_15gh_rowid_seq()).append("\">\n");
                    sb.append("                </tr>\n");
                }
                sb.append("<input type=\"hidden\" id=\"rowCount\" value=\"").append(readListForDeductee.size()).append("\">\n");

            } else {

                sb.append("<tr id=\"row~1\">");
                sb.append("<td>").append("1")
                        .append("</td>");
                sb.append("<td>");
                sb.append("<input type=\"text\" class=\"transparent_input\" id=\"accountNo~1\" name=\"deducteeBflagAmtTranList[0].account_no\" maxLength=\"30\">\n");
                sb.append("</td>\n");
                sb.append("<td>");
                sb.append("<select class=\"transparent_input\" id=\"section~1\" name=\"deducteeBflagAmtTranList[0].section\" list=\"selectSection\" headerKey=\"\" headerValue=\"--select--\" onchange=\"setNatureOfIncome(this.id);\">");
                sb.append("<option value=\"").append("").append("\">Select</option>");
                if (sectionAsHashMap != null && sectionAsHashMap.size() > 0) {
                    for (Map.Entry<String, String> entry : sectionAsHashMap.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        sb.append("<option value=\"").append(key).append("\">").append(value).append("</option>");
                    }
                }
                sb.append("</select>\n");
                sb.append("                   </td>\n");
                sb.append("                    <td>");
                sb.append("                    <input type=\"text\" class=\"transparent_input\" id=\"natureOfIncome~1\" name=\"deducteeBflagAmtTranList[0].nature_of_income\" maxLength=\"30\" readonly=\"true\">\n");
                sb.append("                </td>\n");
                sb.append("                <td>");
                sb.append("                    <input type=\"text\" class=\"transparent_input\" style=\"text-align:right\" id=\"amount~1\" name=\"deducteeBflagAmtTranList[0].amount\" onkeypress=\"return validateNumber(event);\">\n");
                sb.append("                </td>\n");
                sb.append("                <td style=\"text-align:center;\">\n");
                sb.append("                    <i id=\"delete~1\" class=\"fa fa-times table-delete-icon\" aria-hidden=\"true\" onclick=\"deleteRow(this.id);\"></i>\n");
                sb.append("                </td>\n");
                sb.append("                <input type=\"hidden\"  id=\"bflag_tran_seq_id~1\" name=\"deducteeBflagAmtTranList[0].bflag_tran_seq_id\">\n");
                sb.append("            </tr>\n");
            }
            sb.append("</tbody>\n");
            sb.append("</table>\n");
            sb.append("<input type=\"hidden\" id=\"rowCount\" value=\"1\">\n");

            if (readListForDeductee != null && readListForDeductee.size() > 0) {
                sb.append("<div style=\"width:100%;box-sizing: border-box;\">\n");
                sb.append("<div style=\"text-align: right;display: inline-block;width: 75%;\"><span style=\"font-weight:bold;\">Total :</span></div> \n");
                sb.append("<div style=\"text-align: right;display: inline-block;width: 20%;font-weight:bold;\">").append(sumAmt).append("</div>\n");
                sb.append("</div>\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb;
    }

    public String getDeducteeVerifiyDtl(ArrayList<ReferenceNoDetailPOJO> refNoList) {
        StringBuilder sb = new StringBuilder();

        try {

            if (refNoList != null && refNoList.size() > 0) {
                sb.append("<form method=\"post\" id=\"deductee15GhRefNoForm\" name=\"deductee15GhRefNoForm\" action=\"getDeducteeCRUDEAction15gh?action=saveRefNoDetails\">");
                sb.append("<input type=\"hidden\" id=\"user_level\" name=\"user_level\" >");
                sb.append("  <table id=\"refranceNoTypeTwoTableId\" class=\"table table-bordered table-striped mb-1 tablesorter\">");
                sb.append(" <thead>");
                sb.append("<th>");
                sb.append("<div>\n"
                        + "<input type=\"checkbox\" id=\"allchk\" onclick=\"onClickAllCheckbox()\" >\n"
                        + "<label class=\"custom-control-label\" for=\"allchk\" ></label>\n"
                        + "</div>\n");
                sb.append("</th>");
                sb.append("    <th>");
                sb.append("    <div class=\"table-head-inner\">");
                sb.append("   <div class=\"table-heading\">");
                sb.append("              Slno");
                sb.append("                <hr>");
                sb.append("              Bank Branch");
                sb.append("        </div>");
                sb.append("    <div class=\"sort-icon\">");
                sb.append("<i class=\"fa fa-sort-desc\"></i> ");
                sb.append("<i class=\"fa fa-sort-asc\"></i>");
                sb.append("  </div>");
                sb.append("</div>");
                sb.append("</th>");
                sb.append("    <th>");
                sb.append("    <div class=\"table-head-inner\">");
                sb.append("   <div class=\"table-heading\">");
                sb.append("             Client Name");
                sb.append("                <hr>");
                sb.append("             TANNO");
                sb.append("        </div>");
                sb.append("    <div class=\"sort-icon\">");
                sb.append("<i class=\"fa fa-sort-desc\"></i> ");
                sb.append("<i class=\"fa fa-sort-asc\"></i>");
                sb.append("  </div>");
                sb.append("</div>");
                sb.append("</th>");
                sb.append("    <th>");
                sb.append("    <div class=\"table-head-inner\">");
                sb.append("   <div class=\"table-heading\">");
                sb.append("           Last Generated No 15G");
                sb.append("                <hr>");
                sb.append("            Last Generated No 15H");
                sb.append("        </div>");
                sb.append("    <div class=\"sort-icon\">");
                sb.append("<i class=\"fa fa-sort-desc\"></i> ");
                sb.append("<i class=\"fa fa-sort-asc\"></i>");
                sb.append("  </div>");
                sb.append("</div>");
                sb.append("</th>");
                sb.append("    <th>");
                sb.append("    <div class=\"table-head-inner\">");
                sb.append("   <div class=\"table-heading\">");
                sb.append("           Next No 15G");
                sb.append("                <hr>");
                sb.append("          Next No 15H");
                sb.append("        </div>");
                sb.append("    <div class=\"sort-icon\">");
                sb.append("<i class=\"fa fa-sort-desc\"></i> ");
                sb.append("<i class=\"fa fa-sort-asc\"></i>");
                sb.append("  </div>");
                sb.append("</div>");
                sb.append("</th>");
//                 sb.append(" <th style=\"width:25%;\"> \n");
                sb.append("    <th>");
                sb.append("    <div class=\"table-head-inner\">");
                sb.append("   <div class=\"table-heading\">");
                sb.append(" <input id=\"RValueAll\" type=\"radio\" onclick=\"editRadioBtnAll(this.id)\">&nbsp;Refresh \n");
                //sb.append("           Referesh");
                sb.append("                <hr>");
//                sb.append("         Blank");
                sb.append("<input id=\"BValueAll\" type=\"radio\" onclick=\"editRadioBtnAll(this.id)\">&nbsp;Blank \n");
                sb.append("        </div>");
                sb.append("    <div class=\"sort-icon\">");
                sb.append("<i class=\"fa fa-sort-desc\"></i> ");
                sb.append("<i class=\"fa fa-sort-asc\"></i>");
                sb.append("  </div>");
                sb.append("</div>");
                sb.append("</th>");
                sb.append(" </thead>");

                sb.append("<tbody>");

                int count = 0;

                for (ReferenceNoDetailPOJO objList : refNoList) {
                    count++;
                    try {
                        sb.append("<tr id=\"row~").append(count).append("\"> \n");
                        sb.append(" <td> \n");
                        sb.append(" <input type=\"hidden\" id=\"editCheckBox~").append(count).append("\" name=\"editCheckBox\" value=\"false\"/> \n");
                        sb.append("<input type=\"checkbox\" name=\"chkBox\" id=\"chk~").append(count).append("\" onclick=\"onClickCheckbox(this.id);\" style=\"opacity:1;left:23px;float: none;\"/> \n");
                        sb.append("</td> \n");
                        String BranchName = "";

                        if (!utl.isnull(objList.getBank_branch_code()) && objList.getBank_branch_code().length() > 9) {
                            BranchName = objList.getBank_branch_code().substring(0, 8);
                        } else {
                            BranchName = objList.getBank_branch_code();
                        }
                        sb.append("<td>").append(count)
                                .append("<hr>").append(BranchName)
                                .append("</td>");

                        String ClientName = "";
                        if (!utl.isnull(objList.getClient_name()) && objList.getClient_name().length() > 10) {
                            ClientName = objList.getClient_name().substring(0, 10);
                        } else {
                            ClientName = objList.getClient_name();
                        }
                        sb.append("<td>").append(ClientName).append("<hr>")
                                .append(utl.isnull(objList.getTanno()) ? "" : objList.getTanno());
                        sb.append("</td>");

                        sb.append("<td>").append("G-").append(objList.getLast_gen_15g())
                                .append("<hr>").append("H-")
                                .append(objList.getLast_gen_15h())
                                .append("</td>");

                        sb.append("<td>")
                                .append("G-").append("<input type=\"text\" id=\"next_gen_15g~")
                                .append(count).append("\" name=\"refNoList[").append(count - 1)
                                .append("].next_gen_15g\" value=\"").append(objList.getNext_gen_15g()).append("\"")
                                .append("onblur=\"validateRefranceNo(this.id)\"/>")
                                .append("<hr>")
                                .append("H-").append("<input type=\"text\" id=\"next_gen_15h~").append(count)
                                .append("\" name=\"refNoList[").append(count - 1).append("].next_gen_15h\" value=\"")
                                .append(objList.getNext_gen_15h()).append("\"").append("onblur=\"validateRefranceNo(this.id)\"/>")
                                .append("</td>");

                        sb.append("<td>")
                                .append("<input type=\"hidden\" id=\"editRadioBtn~").append(count).append("\" name=\"editRadioBtn\" value=\"F\"/> \n")
                                .append("<input id=\"RValue~").append(count).append("\" type=\"radio\" onclick=\"editRadioBtnData(this.id)\">Refresh")
                                .append("<hr>")
                                .append("<input id=\"BValue~").append(count).append("\" type=\"radio\" onclick=\"editRadioBtnData(this.id)\">Blank")
                                .append("<input type=\"hidden\" id=\"client_code~").append(count).append("\" name=\"refNoList[").append(count - 1).append("].client_code\" value=\"").append(objList.getClient_code()).append("\"/>")
                                .append("</td>");
                        sb.append("</tr>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                sb.append("</tbody>");
                sb.append("</table>");
                sb.append("<input type=\"hidden\" id=\"dataAvailFlag\" name=\"dataAvailFlag\" value=\"T\"/> \n");
                sb.append("</form>");

            } else {
                sb.append("<div style=\"font-weight: bold;text-align: center;\">No Record Found</div>");
                sb.append("<input type=\"hidden\" id=\"dataAvailFlag\" name=\"dataAvailFlag\" value=\"F\"/> \n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

}
