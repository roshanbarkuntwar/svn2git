/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class DeducteeSupport {

    public StringBuilder dataGrid(List<DeducteeBrowseEntity> deducteesByTypeList, StringBuilder sb, int startIndex, int pageNo) {
        if (deducteesByTypeList != null) {
            sb.append("<table class=\"table table-striped\" id=\"table\">");
            sb.append("<thead>");
            sb.append("<tr>");
         sb.append("<th class=\"th-action\"  style=\"width: 100px; text-align:center;\">Action</th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("Sr.No.");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("Deductee Ref.");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("Name");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("PAN");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("Name As Pan No.");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("<th>");
            sb.append("<div class=\"table-head-inner\">");
            sb.append(" <div class=\"table-heading\">");
            sb.append("Verified Status");
            sb.append("</div>");
            sb.append("<div class=\"sort-icon\" title=\"Sorting\">");
            sb.append(" <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("<i class=\"fa fa-sort-asc\"></i>");
            sb.append("</div>");
            sb.append("</div></th>");

            sb.append("</tr>");
            sb.append("</thead>");
            sb.append(" <tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\">");
            int i = 0;
            for (DeducteeBrowseEntity viewDeducteeMast : deducteesByTypeList) {
                try {
                    String cssClass = "";
                    i++;

                    sb.append(" <tr>");

//                    sb.append("<td class=\"td-action ").append(cssClass).append("\"  style=\"width: 40px; text-align:center;\">");
//
//                    sb.append("<form method=\"post\" action=\"manage_tds_action\">");
//                    sb.append("<input type=\"hidden\" name=\"action\" value=\"\"/>");
//
//                    sb.append("<input type=\"hidden\" name=\"tranFltrSrch.deductee_panno\" value=\"").append(viewDeducteeMast.getDeductee_panno() == null ? "" : viewDeducteeMast.getDeductee_panno().toUpperCase()).append("\"/>");
//                    sb.append("<input type=\"hidden\" name=\"tranFltrSrch.other_filter\" value=\"true").append("\"/>");
//                    sb.append("<input type=\"hidden\" name=\"tranFltrSrch.identifyFlag\" value=\"").append(viewDeducteeMast.getDeductee_ref_code1() == null ? "" : viewDeducteeMast.getDeductee_ref_code1().toUpperCase().trim()).append("\"/>");
//                    sb.append("<input type=\"hidden\" name=\"pageNumber\" value=\"").append(pageNo).append("\"/>");
//
////                  sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\" style=\"color:#29AECC;\" ><i class=\"fa fa-eye text-success cursor-pointer mr-1\"  data-toggle=\"tooltip\" title=\"View\"></i></s:a>");
//                    sb.append("</form>");
//                    sb.append("</td>");
                   
//                    sb.append("<td class=\"td-action\">");
//                    sb.append("<s:a href=\"\" onclick=\"openPanUpdateModel('" + viewDeducteeMast.getDeductee_panno() + "','" + viewDeducteeMast.getDeductee_name() + "','" + viewDeducteeMast.getDeductee_ref_code1() + "','" + viewDeducteeMast.getAccount_no() + "');\" ><i class=\"fa edit text-primary cursor-pointer mr-1\"  data-toggle=\"tooltip\" title=\"Edit/Update PAN\"></i></s:a>");
//                    sb.append("</td>");
//                    
//                    sb.append("<td class=\"td-action\">");
//                    sb.append("<i onclick=\"callRevertProcedure('" + viewDeducteeMast.getDeductee_panno() + "','" + viewDeducteeMast.getDeductee_name() + "','" + viewDeducteeMast.getDeductee_ref_code1() + "','" + viewDeducteeMast.getAccount_no() + "');\" class=\"fa revert revert-correction cursor-pointer mr-1\"  data-toggle=\"tooltip\" title=\"Revert PAN Updation\"></i>");
//                    sb.append("</td>");
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"font-weight:bold; width:40px; text-align: center;\" title=\"Edit\" >");
                    sb.append("<i class=\"fa edit text-primary cursor-pointer mr-1\" tile=\"Update Record\" data-toggle=\"collapse\" data-target=\"#d").append(i).append("\"").append("onclick=\"editPanForUpdate("+i+");\"").append("ondbclick=\"editPanForUpdateclose("+i+");\"").append("aria-expanded=\"true\"></i>");
                    sb.append("</td>");
//                    sb.append("<td></td>");
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"font-weight:bold; width:40px; text-align: center;\" >");
                    sb.append(startIndex + i);
                    sb.append("</td>");

                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"width:150px; text-align: center;\"  id=\"deductee_ref"+i+"\">").append(viewDeducteeMast.getDeductee_ref_code1() == null ? "" : viewDeducteeMast.getDeductee_ref_code1().toUpperCase()).append("</td>");
                   
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"width:490px;\"  id=\"deductee_name"+i+"\">").append(viewDeducteeMast.getDeductee_name() == null ? "" : viewDeducteeMast.getDeductee_name().toUpperCase()).append("</td>");
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"width:196px;\" id=\"deductee_panno"+i+"\">").append(viewDeducteeMast.getDeductee_panno() == null ? "" : viewDeducteeMast.getDeductee_panno().toUpperCase()).append("</td>");
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"width:490px;\">").append(viewDeducteeMast.getName_as_panno() == null ? "" : viewDeducteeMast.getName_as_panno().toUpperCase()).append("</td>");
                    sb.append("<td class=\"").append(cssClass).append("\"  style=\"width:200px; color: green; font-weight: bold\">").append(viewDeducteeMast.getDeductee_panno_verified() == null ? "" : viewDeducteeMast.getVerifiedby_flag_name()).append("</td>");
                    sb.append("</tr>");
                    sb.append("<tr id=\"d").append(i).append("\" class=\"collapse\">");
                    sb.append("<td colspan=\"7\">");
                    sb.append("<div class=\"py-1\"> \n" +
"<div class=\"row\">\n" +
"<div class=\"col-md-12\">\n" +
"<input type=\"text\" name=\"\" style=\"width: 135px;display:inline-block;margin-right:10px;text-transform: uppercase\" id=\"dd"+i+"\" class=\"form-control\"   onblur=\"validatePAN(this);check_deductor_type(this.id);PanNoIsBlank()\"  placeholder=\"Update PAN\">"+

" <button type=\"button\" id=\"btn-"+i+"\" title=\"Update\" onclick=\"PanIsBlankchedk('dd" + (i) + "','"+i+"');\" class=\"form-btn\" ><i class=\"fa update\" aria-hidden=\"true\"></i>Update</button>\n" +
"<span style=\"width:200px; color: green; font-weight: bold;display:inline-block;margin-left:10px\"  id=\"updatepancount"+i+"\" ></span> "+ 
        " </div>                                \n" +
       
" </div>                            \n" +
" </div>");
                     sb.append("<td");
                    sb.append("</tr>");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }//end of for each loop

            sb.append("</tbody>");
            sb.append(" </table>");
        }

        return sb;
    }

}
