/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.GlobalMessage;
import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author gaurav.khanzode
 */
public class ValidateError15GHSupport {

    StringBuilder grid = null;
    Util utl = null;

    public ValidateError15GHSupport() {
        grid = new StringBuilder();
        utl = new Util();
    }//end constructor

    public StringBuilder sectionWiseErrorDetailsAndCorrectionGrid15GH(ArrayList<ViewTranLoadErrorSummaryBean15GH> viewloaderrorsummery,
            ArrayList<ArrayList<ViewTranLoadErrorDetailBean15GH>> errDetailBeanList) {

        if (viewloaderrorsummery != null && viewloaderrorsummery.size() > 0) {
            for (ViewTranLoadErrorSummaryBean15GH errorSumBean : viewloaderrorsummery) {
                ArrayList<ViewTranLoadErrorDetailBean15GH> errorDetailBeanList = errDetailBeanList.get(viewloaderrorsummery.indexOf(errorSumBean));
                grid.append("<div class=\"card border-secondary error-details-section mb-3\">");
                grid.append("<div class=\"card-header\">");
                grid.append("<h5 class=\"d-inline-block align-top \">");
                grid.append(errorSumBean.getError_type_name());
                grid.append("</h5>");
                grid.append("</div>");
                grid.append("<div class=\"card-body p-3\">");
                grid.append("<div class=\"table-responsive\">");
                grid.append("<table class=\"table table-sm table-striped mb-0\">");
                grid.append("<thead><tr class=\"text-center\">");
                grid.append("<th colspan=\"2\">Action</th>");
                grid.append("<th>Sr.No.</th>");
                grid.append("<th>Error Name</th>");
                grid.append("<th>Record Count</th>");
                grid.append("</tr></thead>");
                grid.append("<tbody>");

                String l_review_id = "";

                if (errDetailBeanList != null && errDetailBeanList.size() > 0) {
                    int rowSeqNum = 0;
                    for (ViewTranLoadErrorDetailBean15GH errDetailBean : errorDetailBeanList) {
                        grid.append("<tr>");
                        grid.append("<td class=\"text-center\">");
                        if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("DEDUCTEE_MAST_15GH")) {
                            grid.append("<a href=\"#\"><span class=\"fa fa details text-primary cursor-pointer mr-1\" id=\"deductee15ghErrorDetail~")
                                    .append(errDetailBean.getError_code()).append("_").append(rowSeqNum)
                                    .append("\" data-toggle=\"tooltip\" title=\"View Details\" onclick=\"show_error_details15GH(this.id);\"></span></a>");
                        }
                        grid.append("</td>");
                        grid.append("<td class=\"text-center\">");
                        if (!utl.isnull(errDetailBean.getReview_required()) && errDetailBean.getReview_required().equalsIgnoreCase("T")) {
                            if (!utl.isnull(errDetailBean.getError_reviewed()) && errDetailBean.getError_reviewed().equalsIgnoreCase("-1")) {
                                grid.append("<a href=\"#\"><span class=\"fa fa-eye text-warning cursor-pointer mr-1 ml-3\" id=\"reviewDetails~")
                                        .append(errDetailBean.getError_code()).append("_").append(rowSeqNum)
                                        .append("\" data-toggle=\"tooltip\" title=\"Review\" onclick=\"openBulkReviewModal15GH(this.id);\"></span></a>");

                                l_review_id += "reviewDetails~" + errDetailBean.getError_code() + "_" + rowSeqNum + "#";
                            } else {
                                grid.append("<a href=\"#\"><span class=\"fa fa-eye text-success cursor-pointer mr-1 ml-3\" id=\"reviewDetails~")
                                        .append(errDetailBean.getError_code()).append("_").append(rowSeqNum)
                                        .append("\" data-toggle=\"tooltip\" title=\"Review\" onclick=\"openBulkReviewModal15GH(this.id);\"></span></a>");
                            }
                        } else {
                            grid.append(" <span class=\"fa fa-eye text-warning cursor-pointer mr-1 ml-3\" id=\"reviewDetails~")
                                    .append(errDetailBean.getError_code()).append("_").append(rowSeqNum)
                                    .append("\" data-toggle=\"tooltip\"  title=\"Review\"></span>");
                        }
                        grid.append("</td>");
                        grid.append("<td class=\"text-center\" title=\"Sr No.\">").append(rowSeqNum + 1).append("</td>");
                        grid.append("<td title=\"").append(errDetailBean.getError_name()).append("\">").append(errDetailBean.getError_name()).append("</td>");
                        grid.append("<input type=\"hidden\" id=\"error_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" name=\"error_name\" value=\"").append(errDetailBean.getError_name()).append("\"/>");
                        grid.append("<td class=\"data-height font-weight-bold text-right\" title=\"").append(errDetailBean.getRecord_count()).append("\">").append(errDetailBean.getRecord_count()).append("</td>");
                        grid.append("<input type=\"hidden\" id=\"record_count~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" name=\"record_count\" value=\"").append(errDetailBean.getRecord_count()).append("\"/>");
                        grid.append("</td>");
                        grid.append("<input type=\"hidden\" id=\"error_type_code~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_type_code()).append("\"/>");
                        grid.append("<input type=\"hidden\" id=\"error_code~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_code()).append("\"/>");
                        grid.append("<input type=\"hidden\" id=\"table_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getTable_name()).append("\"/>");
                        grid.append("<input type=\"hidden\" id=\"column_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getColumn_name()).append("\"/>");
                        grid.append("<input type=\"hidden\" id=\"error_type_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_type_name()).append("\"/>");
                        grid.append("</tr>");

                        rowSeqNum++;
                    }
                }
                grid.append("<input type=\"hidden\" id=\"hiddenReviewImageID~").append(errorSumBean.getError_type_code()).append("\" value=\"").append(l_review_id).append("\"/>");
                grid.append(" </tbody>");
                grid.append("</table>");
                grid.append("</div>");
                grid.append("</div>");
                grid.append("</div>");
            }
        } else {
            grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
        }
        return grid;
    }//end method

    public StringBuilder deducteeDetailsGrid15GH(ArrayList<ShowDeducteeErrorDetailBean> deducteeErrorListData) {
        if (deducteeErrorListData != null && deducteeErrorListData.size() > 0) {
            grid.append("<table class=\"table table-sm table-striped mb-0\" id=\"table\">");
            grid.append("<thead>");
            grid.append("<tr class=\"text-center\">");
            grid.append("<th>Sr No.</th>");
            grid.append("<th>Deductee Name</th>");
            grid.append("<th>PAN No.</th>");
            grid.append("<th>Reference No.</th>");
            grid.append("<th>Date Of Birth</th>");
            grid.append("<th>Column Value</th>");
            grid.append("<th>Bank Branch</th>");
            grid.append("</tr>");
            grid.append("</thead>");
            grid.append("<tbody>");

            int count = 0;
            for (ShowDeducteeErrorDetailBean showDeducteeErrorDetailBean : deducteeErrorListData) {
                count++;
                grid.append("<tr id=\"row~").append(count).append("\">");
                grid.append("<td class=\"text-center\" title=\"").append(count).append("\">").append(count).append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getDeductee_name()).append("\">");
                grid.append(showDeducteeErrorDetailBean.getDeductee_name());
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"deductee_name~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].deductee_name\" value=\"").append(showDeducteeErrorDetailBean.getDeductee_name()).append("\"/>");
                grid.append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getPanno()).append("\">");
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"panno~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].panno\" value=\"").append(showDeducteeErrorDetailBean.getPanno()).append("\" onblur=\"getCheckedCheckboxDeductee(this.id); \"/>");
                grid.append(showDeducteeErrorDetailBean.getPanno());
                grid.append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getReference_no()).append("\">");

                grid.append(showDeducteeErrorDetailBean.getReference_no());
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"reference_no~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].reference_no\" value=\"").append(showDeducteeErrorDetailBean.getReference_no()).append("\" />");
                grid.append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getDob()).append("\">");
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"dob~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].dob\" value=\"").append(showDeducteeErrorDetailBean.getDob()).append("\" />");

                grid.append(showDeducteeErrorDetailBean.getDob());
                grid.append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getColumn_data()).append("\">");
                grid.append(showDeducteeErrorDetailBean.getColumn_data());
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"error_name~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].error_name\" value=\"").append(showDeducteeErrorDetailBean.getColumn_data()).append("\"/>");
                grid.append("</td>");
                grid.append("<td title=\"").append(showDeducteeErrorDetailBean.getBank_branch_code()).append("\">");
//                grid.append("<input type=\"text\" readonly=\"true\" id=\"error_name~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].error_name\" value=\"").append(showDeducteeErrorDetailBean.getBank_branch_code()).append("\"/>");

                grid.append(showDeducteeErrorDetailBean.getBank_branch_code());
                grid.append("</td>");

                grid.append("<input type=\"hidden\" id=\"deductee_code~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].deductee_code\" value=\"").append(showDeducteeErrorDetailBean.getDeductee_code()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"column_name~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].column_name\" value=\"").append(showDeducteeErrorDetailBean.getColumn_name()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"column_data~").append(count).append("\" name=\"deducteeMast15GH[").append(count - 1).append("].column_data\" value=\"").append(showDeducteeErrorDetailBean.getColumn_data()).append("\"/>");
                grid.append("</tr>");
            }//end for loop

            grid.append("</tbody>");
            grid.append("</table>");
        }
        return grid;
    }//end method
}
