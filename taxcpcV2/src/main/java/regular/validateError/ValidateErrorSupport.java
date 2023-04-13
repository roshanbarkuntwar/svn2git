/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.TdsMastDAO;
import dao.ViewTdsDeductReasonDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewTdsDeductReason;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author gaurav.khanzode
 */
public class ValidateErrorSupport {

    Util utl = null;
    StringBuilder grid = null;

    public ValidateErrorSupport() {
        utl = new Util();
        grid = new StringBuilder();
    }//end constructor

    public StringBuilder sectionWiseErrorDetailsAndCorrectionGrid(ArrayList<ViewTranLoadErrorBean> beanList, ArrayList<ArrayList<ViewTranLoadErrorDetailsBean>> errDetailList) {

        if (beanList != null && beanList.size() > 0) {
            int errorTypeCount = 1;
            for (ViewTranLoadErrorBean errorBean : beanList) {
                ArrayList<ViewTranLoadErrorDetailsBean> errDetailBeanList = errDetailList.get(beanList.indexOf(errorBean));
                grid.append("<div class=\"card border-secondary error-details-section mb-3\">");
                grid.append("<div class=\"card-header\">");
                grid.append("<h5 class=\"d-inline-block align-top \">");
                grid.append("<input type=\"hidden\" value=\"" + errorBean.getError_type_name() + "\" id=\"" + errorBean.getError_type_code() + "\">");
//                grid.append("<input type=\"hidden\" name=\"\" value=\"\" id=\"" + errorBean.getError_type_code() + "_bulkReviewAllSpan\" onclick=\"openBulkReviewModal(this.id);\">");
                grid.append((errorTypeCount++) + ". " + errorBean.getError_type_name());
                grid.append("</h5><button type=\"button\" id=\"" + errorBean.getError_type_code() + "_bulkReviewAllSpan\" "
                        + "class=\"btn btn-sm bulk-review position-absolute rounded-0\" onclick=\"openBulkReviewAllModal(this.id);\">Bulk Review</button>");
                grid.append("</div>");
                grid.append("<div class=\"card-body p-3\">");
                grid.append("<div class=\"table-responsive\">");
                grid.append("<table class=\"table table-sm table-striped mb-0\">");
                grid.append("<thead><tr class=\"text-center\">");
                grid.append("<th colspan=\"4\">Action</th>");
                grid.append("<th>Sr.No.</th>");
                grid.append("<th>Error Name</th>");
                grid.append("<th>Financial Potential Error</th>");
                grid.append("<th>Record Count</th>");
                grid.append("</tr></thead>");
                grid.append("<tbody>");

                String error_type_code = null;
                int rowSeqNum = 0;
                String l_review_id = "";
                if (errDetailBeanList != null && errDetailBeanList.size() > 0) {
                    for (ViewTranLoadErrorDetailsBean errDetailBean : errDetailBeanList) {
                        error_type_code = errorBean.getError_type_code();
                        if (errDetailBean.getError_type_code().equals(errorBean.getError_type_code())) {
                            rowSeqNum++;

                            grid.append("<tr>");
                            grid.append("<td class=\"text-center\">");
                            if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("TDS_CHALLAN_TRAN_LOAD")) {
                                grid.append("<a href=\"#\"><i class=\"fa details text-primary cursor-pointer mr-1\" id=\"challanErrDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Details\" onclick=\"getshow_error_details_model(this.id);\"></i></a> \n");
                            } else if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("CLIENT_MAST")) {
                                grid.append("<a href=\"#\"><i class=\"fa details text-primary cursor-pointer mr-1\" id=\"deductorErrDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Details\" onclick=\"getshow_error_details_model(this.id);\"></i></a> \n");
                            } else if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("DEDUCTEE_MAST")) {
                                grid.append("<a href=\"#\"><i class=\"fa details text-primary cursor-pointer mr-1\" id=\"deducteeErrDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Details\" onclick=\"getshow_error_details_model(this.id);\"></i></a> \n");
                            } else if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("TDS_TRAN_LOAD")) {
                                grid.append(" <a href=\"#\"><i class=\"fa details text-primary cursor-pointer mr-1\" id=\"errSummaryDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Details\" onclick=\"getShow_error_details(this.id);\"></i></a> \n");
                            } else if (!utl.isnull(errDetailBean.getTable_name()) && errDetailBean.getTable_name().equalsIgnoreCase("SALARY_TRAN_LOAD")) {
                                grid.append(" <a href=\"#\"><i class=\"fa details text-primary cursor-pointer mr-1\" id=\"errSalarySummaryDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Details\" onclick=\"getshow_salary_error_details_summary(this.id);\"></i></a> \n");
                            }
                            grid.append("</td>");
                            grid.append("<td class=\"text-center\">");
//                            grid.append("<i class=\"fa fa-pencil-square text-success cursor-pointer mr-1\" title=\"Bulk Update\" data-toggle=\"modal\" data-target=\"#bulkUpdation\"></i>");
                            if (!utl.isnull(errDetailBean.getUpdation_allow_flag()) && errDetailBean.getUpdation_allow_flag().equalsIgnoreCase("T")) {
                                if (!utl.isnull(errDetailBean.getBulk_pan_verification_flag()) && errDetailBean.getBulk_pan_verification_flag().equalsIgnoreCase("T")) {
                                    grid.append("<a href=\"#\"><span class=\"fa edit text-success cursor-pointer mr-1\" id=\"updateBlkPanDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Bulk PAN Update\" onclick=\"fileUploadErrorPanVerification('").append(errDetailBean.getError_code()).append("');\"></span></a> \n");
                                } else if (!utl.isnull(errDetailBean.getBulk_pan_verification_flag()) && errDetailBean.getBulk_pan_verification_flag().equalsIgnoreCase("X")) {
                                    grid.append("<a href=\"bulkXMLPanVerification\"><span class=\"fa edit text-success cursor-pointer mr-1\" id=\"updateBlkXMLPanDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Bulk PAN Update\"></span></a> \n");
                                } else {
                                    grid.append("<a href=\"#\"><span class=\"fa edit text-success cursor-pointer mr-1\" id=\"updateBlkDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Bulk Update\" onclick=\"openBulkUploadModal(this.id);\"></span></a> \n");
                                }
                            } else {
                                grid.append("<span class=\"fa edit cursor-pointer mr-1\" data-toggle=\"tooltip\" title=\"Bulk Update\"></span> \n");
                            }
                            grid.append("</td>");
                            grid.append("<td class=\"text-center\">");
                            if (!utl.isnull(errDetailBean.getError_reviewed()) && "1".equals(errDetailBean.getError_reviewed())) {

                                grid.append("<i class=\"fa fa-eye text-success cursor-pointer mr-1\" title=\"Review\" id=\"reviewDetails~" + errDetailBean.getError_code() + "_" + (rowSeqNum) + "\" ");
                            } else {

                                grid.append("<i class=\"fa fa-eye text-warning cursor-pointer mr-1\" title=\"Review\" id=\"reviewDetails~" + errDetailBean.getError_code() + "_" + (rowSeqNum) + "\" ");
                            }
                            grid.append(" onclick=\"openBulkReviewModal(this.id);\"></i>");
//                            if (!utl.isnull(errDetailBean.getReview_required()) && errDetailBean.getReview_required().equalsIgnoreCase("T")) {
//                                if (!utl.isnull(errDetailBean.getError_reviewed()) && errDetailBean.getError_reviewed().equalsIgnoreCase("-1")) {
//                                    grid.append("<a href=\"#\"><span class=\"fa view cursor-pointer mr-1\" id=\"reviewDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" style=\"color:#D27D5C;\" title=\"Review\" onclick=\"openBulkReviewModal(this.id);\"></span></a> \n");
//                                    l_review_id += "reviewDetails~" + errDetailBean.getError_code() + "_" + rowSeqNum + "#";
//                                } else {
//                                    grid.append("<a href=\"#\"><span class=\"fa view text-success cursor-pointer mr-1\" id=\"reviewDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\" title=\"Review\" onclick=\"openBulkReviewModal(this.id);\"></span></a> \n");
//                                }
//                            } else {
//                                grid.append(" <span class=\"fa view cursor-pointer mr-1\" id=\"reviewDetails~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\"  title=\"Review\" ></span> \n");
//                            }
                            grid.append("</td>");
                            grid.append("<td class=\"text-center\">");
//                            grid.append("<i class=\"fa fa-cog text-info cursor-pointer mr-1\" id=\"callProcedure~" + errDetailBean.getError_code() + "_" + (rowSeqNum) + "\" "
//                                    + "title=\"Call For Bulk Updation\" onclick=\"openProcedureModel('" + errDetailBean.getError_code() + "','" + errDetailBean.getBulk_updt_proc_flag() + "');\"></i>");
                            if (!utl.isnull(errDetailBean.getBulk_updt_required()) && errDetailBean.getBulk_updt_required().equalsIgnoreCase("T") && utl.isnull(errDetailBean.getBulk_pan_verification_flag())) {
                                grid.append(" <span class=\"fa bulk-updation text-success cursor-pointer mr-1\" id=\"callProcedure~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\"  title=\"Call For Bulk Updation\" onclick =\"callProcedure('" + errDetailBean.getError_code().trim() + "')\" ></span> \n");
                            } else if (!utl.isnull(errDetailBean.getBulk_updt_required()) && errDetailBean.getBulk_updt_required().equalsIgnoreCase("T") && !utl.isnull(errDetailBean.getBulk_pan_verification_flag())) {

                                grid.append(" <span class=\"fa bulk-updation text-success cursor-pointer mr-1\" id=\"callProcedure~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\"  title=\"Call For Bulk Updation\" onclick =\"openProcedureModel('" + errDetailBean.getError_code() + "','" + errDetailBean.getBulk_updt_proc_flag().trim() + "')\"></span> \n");
                            } else {
                                grid.append(" <span class=\"fa bulk-updation cursor-pointer mr-1\" id=\"callProcedure~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" data-toggle=\"tooltip\"  title=\"Call For Bulk Updation\" ></span> \n");
                            }
                            grid.append("</td>");

                            grid.append("<td class=\"text-center\">" + (rowSeqNum) + "</td>");
                            grid.append("<td>" + errDetailBean.getError_name() + "</td>");
                            grid.append("<td class=\"text-right data-height font-weight-bold\">" + (utl.getAmountIndianFormat(Double.parseDouble(errDetailBean.getDiff_tds_amt()))) + "</td>");
                            grid.append("<td class=\"text-center data-height font-weight-bold\">" + errDetailBean.getRecord_count() + "</td>");
                            grid.append("<input type = \"hidden\" id=\"error_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_name()).append("\"/>\n");
                            grid.append("<input type=\"hidden\" id=\"error_type_code~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_type_code()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"error_code~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_code()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"table_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getTable_name()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"column_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getColumn_name()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"error_type_name~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_type_name()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"error_screen2_required~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_screen2_required()).append("\"/> \n");
                            grid.append("<input type=\"hidden\" id=\"error_screen3_required~").append(errDetailBean.getError_code()).append("_").append(rowSeqNum).append("\" value=\"").append(errDetailBean.getError_screen3_required()).append("\"/> \n");
                            grid.append("</tr>");
                        }
                    }
                    grid.append("<input type=\"hidden\" id=\"hiddenReviewImageID~").append(error_type_code).append("\" value=\"").append(l_review_id).append("\"/> \n");
                } else {
                    grid.append("<tr>");
                    grid.append("<td>");
                    grid.append("<i class=\"fa fa-file-text text-primary cursor-pointer mr-1\" title=\"Details\"></i>");
                    grid.append("<i class=\"fa fa-pencil-square text-success cursor-pointer mr-1\" title=\"Bulk Update\" data-target=\"#bulkUpdation\"></i>");
                    grid.append("<i class=\"fa fa-eye text-warning cursor-pointer mr-1\" title=\"Review\"></i>");
                    grid.append("<i class=\"fa fa-cog text-info cursor-pointer mr-1\" title=\"Review\"></i>");
                    grid.append("</td>");
                    grid.append("<td></td>");
                    grid.append("<td></td>");
                    grid.append("<td class=\"text-right\"></td>");
                    grid.append("<td></td>");
                    grid.append("</tr>");
                }
                grid.append("</tbody>");
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

    public StringBuilder errorDetailsGrid(ArrayList<ShowErrorDetailsBean> listtranLoadError, Assessment asmt,
            LinkedHashMap<String, String> selectSection, LinkedHashMap<String, String> selectRateType, LinkedHashMap<String, String> selectRemittanceNature,
            HashMap<String, String> select_country, int startIndex, int minVal, String additionalWhereClause, ArrayList<String> arrData) {

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

        Double tran_amt_sum = 0d;
        Double tds_amt_sum = 0d;
        Double calc_tds_amt_sum = 0d;
        Double diff_tds_amt_sum = 0d;

        Double g_tran_amt_sum = 0d;
        Double g_tds_amt_sum = 0d;
        Double g_surcharge_amt_sum = 0d;
        Double g_cess_amt_sum = 0d;

        if (listtranLoadError != null && listtranLoadError.size() > 0) {
            grid.append("<div class=\"table-responsive mt-2\"><table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            grid.append("<thead><tr class=\"text-center\">");
            grid.append("<th class=\"th-action\">Action</th>");
            grid.append("<th>Sr No.</th>");
            grid.append("<th>Deductee Name</th>");
            grid.append("<th>Deductee Pan No.</th>");
            grid.append("<th>Section</th>");
            grid.append("<th>Transaction Date</th>");
            grid.append("<th>Transaction Amount</th>");
            grid.append("<th>TDS Deduct Date</th>");
            grid.append("<th>Reason</th>");
            grid.append("<th>Certificate No.</th>");

            if (asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                grid.append("<th>Rate Type</th>");
                grid.append("<th>Nature Of Remittance</th>");
                grid.append("<th>Country</th>");
                grid.append("<th>Email</th>");
                grid.append("<th>Address</th>");
                grid.append("<th>Tin Uin NO</th>");
            }
            grid.append("<th>Tax Rate</th>");
            grid.append("<th>TDS Amount</th>");
            grid.append("<th>Calculated  TDS Amount</th>");
            grid.append("<th>Difference  TDS Amount</th></tr></thead>");

            grid.append("<tbody>");
            int count = 0;
            count = startIndex + count;

            for (ShowErrorDetailsBean errorDetailBean : listtranLoadError) {
                if (!utl.isnull(errorDetailBean.getTran_amt().trim())) {
                    try {
                        tran_amt_sum += Double.parseDouble(errorDetailBean.getTran_amt());
                    } catch (Exception e) {
                    }
                }
                if (!utl.isnull(errorDetailBean.getTds_amt().trim())) {
                    try {
                        tds_amt_sum += Double.parseDouble(errorDetailBean.getTds_amt());
                    } catch (Exception e) {
                    }
                }
                if (!utl.isnull(errorDetailBean.getCalc_tds_amt().trim())) {
                    try {
                        calc_tds_amt_sum += Double.parseDouble(errorDetailBean.getCalc_tds_amt());
                    } catch (Exception e) {
                    }
                }
                if (!utl.isnull(errorDetailBean.getDiff_tds_amt().trim())) {
                    try {
                        diff_tds_amt_sum += Double.parseDouble(errorDetailBean.getDiff_tds_amt());
                    } catch (Exception e) {
                    }
                }
                grid.append("<tr id=\"row~").append(count).append("\">");
                grid.append("<td class=\"td-action text-center\">");
                grid.append("<div class=\"custom-control custom-checkbox action-check\">");
                grid.append("<input type=\"checkbox\" class=\"custom-control-input toggle-action-section\" id=\"check~").append(count).append("\" ");
                grid.append("onclick=\"onClickCheckbox(this.id);\"/>");
                grid.append("<label class=\"custom-control-label\" for=\"check~").append(count).append("\"></label>");
                grid.append("<input type=\"hidden\" id=\"editCheckBox~").append(count).append("\" name=\"editCheckBox\" value=\"false\"> </div>");
                grid.append("</td>");
                grid.append("<td class=\"text-center\">").append(count).append("</td>");
                grid.append("<td title=\"").append(errorDetailBean.getDeductee_name()).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"deductee_name~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].deductee_name\" value=\"").append(errorDetailBean.getDeductee_name()).append("\" onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td class=\"text-center\" title=\"").append(errorDetailBean.getDeductee_panno()).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"deductee_panno~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].deductee_panno\" value=\"").append(errorDetailBean.getDeductee_panno()).append("\" onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");

                grid.append("<td class=\"text-center\">");
                grid.append("<select style=\"width: 350px; border: none;background: inherit; text-align:center;white-space: nowrap;\"").append("onchange=\"changeDeducteeReason(this.id); getCheckedCheckbox(this.id);\" id=\"tds_code~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tds_code\" value=\"").append(errorDetailBean.getTds_code()).append("\" onclick=\"getTextValue(this.id);\"/>");
//                grid.append("<option value=\"\">----SELECT----</option> \n");
                if (selectSection != null) {
                    for (Map.Entry<String, String> entry : selectSection.entrySet()) {
                        if (errorDetailBean.getTds_code().equalsIgnoreCase(entry.getKey())) {
                            grid.append("<option value=\"").append(entry.getKey()).append("\" selected=\"selected\">").append(entry.getValue()).append("</option>");
                        } else {
                            grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");
                        }
                    }
                }
                grid.append("</select>");
                grid.append("</td>");

                String l_tran_ref_date = "";
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (!utl.isnull(errorDetailBean.getTran_ref_date())) {
                    try {
                        Date l_ref_date = new Date(errorDetailBean.getTran_ref_date());
                        if (l_ref_date != null) {
                            l_tran_ref_date = sdf.format(l_ref_date);
                        }
                    } catch (Exception e) {
                        utl.generateLog("tran_ref_date_error...", e.getMessage());
                    }
                }
                grid.append("<td class=\"text-center\" title=\"").append(l_tran_ref_date).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"tran_ref_date~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1)
                        .append("].tran_ref_date\" value=\"").append(l_tran_ref_date).append("\" ")
                        .append(" onmouseover=\"openCalendar(this.id, this.id); doOnLoad(this.id, this.id)\"/></td>");
                String l_tran_amt = "0.00";
                try {
                    if (!utl.isnull(errorDetailBean.getTran_amt())) {
                        l_tran_amt = utl.getAmountIndianFormat(Double.parseDouble(errorDetailBean.getTran_amt()));
                    }
                } catch (Exception e) {
                }
                grid.append("<td title=\"").append(l_tran_amt).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" class=\"text-right\" id=\"tran_amt~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tran_amt\" value=\"").append(l_tran_amt).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                String l_tds_deduct_date = "";
                if (!utl.isnull(errorDetailBean.getTds_deduct_date())) {
                    try {
                        Date l_deduct_date = new Date(errorDetailBean.getTds_deduct_date());
                        if (l_deduct_date != null) {
                            l_tds_deduct_date = sdf.format(l_deduct_date);
                        }
                    } catch (Exception e) {
                        utl.generateLog("deduct_date_error...", e.getMessage());
                    }
                }
                grid.append("<td class=\"text-center\" title=\"").append(l_tds_deduct_date).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"tds_deduct_date~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1);
                grid.append("].tds_deduct_date\" value=\"").append(l_tds_deduct_date);
                grid.append("\" onmouseover=\"openCalendar(this.id, this.id); doOnLoad(this.id, this.id)\"/>");
                grid.append("</td>");
                grid.append("<td>");
                grid.append("<select style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" name=\"tdsTranLoad[").append(count).append("].tds_deduct_reason\" id=\"tds_deduct_reason~").append(count - 1).append("\" onchange=\"onchangeDeducteReasonValue(this.id); getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\">");

                String l_deduct_reason_flag = "";
                try {
                    TdsMastDAO tdsMastDAO = factory.getTdsMastDAO();
                    ArrayList<String> listdata = tdsMastDAO.getAsDeductReasonAsString(errorDetailBean.getTds_code(), utl);

                    if (listdata != null) {
                        l_deduct_reason_flag = listdata.get(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<ViewTdsDeductReason> viewtdsdeduct = null;
                try {
                    ViewTdsDeductReasonDAO tdsDeductDAO = factory.getViewTdsDeductReasonDAO();
                    viewtdsdeduct = tdsDeductDAO.getDeductReasonList(l_deduct_reason_flag, utl);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                grid.append("<option value=\"\">----SELECT----</option> \n");
                if (viewtdsdeduct != null) {
                    for (ViewTdsDeductReason tdslist : viewtdsdeduct) {
                        if (!utl.isnull(errorDetailBean.getTds_deduct_reason()) && (errorDetailBean.getTds_deduct_reason().equals(tdslist.getTds_deduct_reason()))) {
                            grid.append("<option value=\"").append(tdslist.getTds_deduct_reason()).append("\" selected >").append(tdslist.getTds_deduct_reason()).append("</option>");

                        } else {
                            grid.append("<option value=\"").append(tdslist.getTds_deduct_reason()).append("\" >").append(tdslist.getTds_deduct_reason()).append("</option>");
                        }
                    }
                }
                grid.append("</select> ");
                grid.append("</td>");
                grid.append("<td title=\"").append(errorDetailBean.getCertificate_no()).append("\">");
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"certificate_no~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].certificate_no\" value=\"").append(errorDetailBean.getCertificate_no()).append("\" onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                if (asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                    grid.append("<td title=\"").append(errorDetailBean.getRate_type()).append("\">");
                    grid.append("<select style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" id=\"rate_type~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].rate_type\" value=\"").append(errorDetailBean.getRate_type()).append("\" onchange=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    for (Map.Entry<String, String> entry : selectRateType.entrySet()) {
                        grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");//                                    
                    }
                    grid.append("</td>");
                    grid.append("<td title=\"").append(errorDetailBean.getNature_of_remittance()).append("\">");
                    grid.append("<select style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" id=\"nature_of_remittance~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].nature_of_remittance\" value=\"").append(errorDetailBean.getNature_of_remittance()).append("\" onchange=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    for (Map.Entry<String, String> entry : selectRemittanceNature.entrySet()) {
                        grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");//                                    
                    }
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\" title=\"").append(errorDetailBean.getCountry_code()).append("\">");
                    grid.append("<select ").append(" style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" id=\"country_code~").append(count).append("\" name=\"tdsTranLoad[").append(count).append("].country_code\" value=\"").append(errorDetailBean.getCountry_code()).append("\" onchange=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    for (Map.Entry<String, String> entry : select_country.entrySet()) {
                        grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");//                                    
                    }
                    grid.append("</td>");
                    grid.append("<td title=\"").append(errorDetailBean.getEmail_id()).append("\">");
                    grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"email~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].email\" value=\"").append(errorDetailBean.getEmail_id()).append("\" onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    grid.append("</td>");
                    grid.append("<td title=\"").append(errorDetailBean.getAdd1()).append("\">");
                    grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"address~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].add1\" value=\"").append(errorDetailBean.getAdd1()).append("\" onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    grid.append("</td>");
                    grid.append("<td title=\"").append(errorDetailBean.getTin_uin_no()).append("\">");
                    grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" id=\"tin_uno_no~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tin_uin_no\" value=\"").append(errorDetailBean.getTin_uin_no()).append("\"  onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    grid.append("</td>");
                }
                grid.append("<td title=\"").append(errorDetailBean.getTds_rate()).append("\">");
                String l_itax_rate = "0.00";
                try {
                    if (!utl.isnull(errorDetailBean.getTds_rate())) {
                        l_itax_rate = utl.getAmountIndianFormat(Double.parseDouble(errorDetailBean.getTds_rate()));
                    }
                } catch (Exception e) {
                }
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" class=\"text-right\" id=\"itax_rate~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].itax_rate\" value=\"").append(l_itax_rate).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td title=\"").append(errorDetailBean.getTds_amt()).append("\">");
                String l_tds_amt = "0.00";
                try {
                    if (!utl.isnull(errorDetailBean.getTds_amt())) {
                        l_tds_amt = utl.getAmountIndianFormat(Double.parseDouble(errorDetailBean.getTds_amt()));
                    }
                } catch (Exception e) {
                }
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" class=\"text-right\" id=\"tds_amt~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tds_amt\" value=\"").append(l_tds_amt).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td title=\"").append(errorDetailBean.getCalc_tds_amt()).append("\">");
                String l_surcharge_amt = "0.00";
                try {
                    if (!utl.isnull(errorDetailBean.getCalc_tds_amt())) {
                        l_surcharge_amt = utl.getAmountIndianFormat(Double.parseDouble(errorDetailBean.getCalc_tds_amt()));
                    }
                } catch (Exception e) {
                }
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" type=\"text\" class=\"text-right\" id=\"surcharge_amt~").append(count).append("\" value=\"").append(l_surcharge_amt).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\" readonly=\"true\"/>");

                grid.append("</td>");
                grid.append("<td title=\"").append(errorDetailBean.getDiff_tds_amt()).append("\">");
                String l_cess_amt = "0.00";
                try {
                    if (!utl.isnull(errorDetailBean.getDiff_tds_amt())) {
                        l_cess_amt = utl.getAmountIndianFormat(Double.parseDouble(errorDetailBean.getDiff_tds_amt()));
                    }
                } catch (Exception e) {
                }
                grid.append("<input style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" class=\"text-right\" type=\"text\" id=\"cess_amt~").append(count).append("\"  value=\"").append(l_cess_amt).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckbox(this.id);\" onclick=\"getTextValue(this.id);\" readonly=\"true\"/>");
                grid.append("<input type=\"hidden\" id=\"rowid_seq~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].rowid_seq\" value=\"").append(errorDetailBean.getRowid_seq()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"tds_challan_rowid_seq~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tds_challan_rowid_seq\" value=\"").append(errorDetailBean.getTds_challan_rowid_seq()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"tds_challan_tds_amt~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].tds_challan_tds_amt\" value=\"").append(errorDetailBean.getTds_challan_tds_amt()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"hTdsDeductReason~").append(count).append("\" name=\"tdsTranLoad[").append(count - 1).append("].hTdsDeductReason\" value=\"").append(errorDetailBean.getTds_code()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"popup_message~").append(count).append("\" name=\"popup_message\" value=\"").append(errorDetailBean.getPopup_message()).append("\"/>");
                grid.append("</td>");
                grid.append("</tr>");
                count++;
            }
            try {
                if (arrData != null) {
                    String arr1 = arrData.get(0);
                    arr1 = utl.isnull(arr1) ? "0" : arr1;
                    g_tran_amt_sum = Double.parseDouble(arr1);
                    String arr4 = arrData.get(3);
                    arr4 = utl.isnull(arr4) ? "0" : arr4;
                    g_tds_amt_sum = Double.parseDouble(arr4);
                    String arr5 = arrData.get(4);
                    arr5 = utl.isnull(arr5) ? "0" : arr5;
                    g_surcharge_amt_sum = Double.parseDouble(arr5);
                    String arr6 = arrData.get(5);
                    arr6 = utl.isnull(arr6) ? "0" : arr6;
                    g_cess_amt_sum = Double.parseDouble(arr6);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            grid.append("</tbody>");

            grid.append("<tfoot>");
            grid.append("<tr class=\"highlight header1 expand\" onclick=\"collapseOn(this);\">");
            grid.append("<td class=\"text-center\" title=\"Show Grand Total\"> <img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"></td>");
            grid.append("<td colspan=\"5\" class=\"text-right data-height font-weight-bold\">Total</td>");
            grid.append("<td class=\"text-right\">");
            grid.append("<div class=\"data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tran_amt_sum)).append("</div>");
            grid.append("</td>");
            if (asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                grid.append("<td colspan=\"6\"></td>");
            }
            grid.append("<td colspan=\"3\"></td>");
            grid.append("<td></td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tds_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(calc_tds_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(diff_tds_amt_sum)).append("</td></tr>");
            grid.append("<tr class=\"highlight\" style=\"display: none;\">");
            grid.append("<td colspan=\"6\" class=\"text-right data-height font-weight-bold\">Grand Total</td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_tran_amt_sum)).append("</div> </td>");

            if (asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                grid.append("<td colspan=\"6\"></td>");
            }
            grid.append("<td colspan=\"3\"></td>");
            grid.append("<td></td>");
            grid.append("<td class=\"text-right font-weight-bold\">").append(utl.getAmountIndianFormat(g_tds_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right font-weight-bold\">").append(utl.getAmountIndianFormat(g_surcharge_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right font-weight-bold\">").append(utl.getAmountIndianFormat(g_cess_amt_sum)).append("</td>");
            grid.append("</tr>");
            grid.append("</tfoot></table></div>");
//            grid.append("<div class=\"form-group\">\n"
//                    + "                    <div class=\"row\">\n"
//                    + "                        <div class=\"button-section col-md-12 my-1 text-center\"> \n"
//                    + "                            <a href=\"tdsProcessError?validate=true\"><button type=\"button\" id=\"\" class=\"form-btn\">\n"
//                    + "                                <i class=\"fa fa-chevron-left\" aria-hidden=\"true\"></i>Back\n"
//                    + "                            </button></a>\n"
//                    + "                            <button type=\"button\" id=\"saveExitBtn\" class=\"form-btn\" onclick=\"updateErrorDetailsData();\">\n"
//                    + "                                <i class=\"fa fa-pencil-square\" aria-hidden=\"true\"></i>Update\n"
//                    + "                            </button>\n"
//                    + "                        </div>\n"
//                    + "                    </div>\n"
//                    + "                </div>");
        } else {
            grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
        }
        return grid;
    }//end method

    public StringBuilder getChallanErrorDetailGrid(ArrayList<ShowChallanErrorDetailsBean> listchallantranLoadError, String l_error_code,
            LinkedHashMap<String, String> selectSection, String entity_code, String client_code, Assessment asmt, int l_record_MNL, int l_record_MXL,
            TreeMap<String, String> minorHead) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (listchallantranLoadError != null && listchallantranLoadError.size() > 0) {
            Double challan_amt_sum = 0d;
            Double int_latpayment_sum = 0d;
            Double late_payment_fee_sum = 0d;
            Double calc_late_payment_amt_sum = 0d;

            Double g_challan_amt_sum = 0d;
            Double g_int_latpayment_sum = 0d;
            Double g_late_payment_fee_sum = 0d;
            Double g_calc_late_payment_amt_sum = 0d;

            grid.append("<div class=\"table-responsive mt-2\"><table class=\"table table-striped\" id=\"table\" style=\"margin-bottom: 0px; width: 100%;\">");
            grid.append("<thead>");
            grid.append("<tr class=\"text-center\">");
            grid.append("<th>Action</th> ");
            grid.append("<th>Sr No.</th> ");
            grid.append("<th>BSR Code</th>");
            grid.append("<th>Challan Date</th>");
            grid.append("<th>DDO/Challan Serial No.</th>");
            grid.append("<th>Challan Amount</th>");
            grid.append("<th>Section</th>");
            grid.append("<th>Interest On Late Payment </th>");
            grid.append("<th>Late Payment Fee</th>");
            if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                grid.append("<th style=\"width: 150px; text-align:center;\">Late Payment Calculated Amount</th>");
            }
            grid.append("<th style=\"width: 150px; text-align:center;\">Minor Head</th>");
            grid.append("</tr>");
            grid.append("</thead>");
            grid.append("<tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\">");
            int count = 0;
            for (ShowChallanErrorDetailsBean showChallanErrorDetailBen : listchallantranLoadError) {
                count++;

                if (!utl.isnull(showChallanErrorDetailBen.getTds_challan_tds_amt().trim())) {
                    challan_amt_sum += Double.parseDouble(showChallanErrorDetailBen.getTds_challan_tds_amt());
                }
                if (!utl.isnull(showChallanErrorDetailBen.getTds_challan_int_amt().trim())) {
                    int_latpayment_sum += Double.parseDouble(showChallanErrorDetailBen.getTds_challan_int_amt());
                }
                if (!utl.isnull(showChallanErrorDetailBen.getTds_challan_other_amt())) {
                    late_payment_fee_sum += Double.parseDouble(showChallanErrorDetailBen.getTds_challan_other_amt());
                }

                if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                    if (!utl.isnull(showChallanErrorDetailBen.getCalc_late_payment_amt())) {
                        calc_late_payment_amt_sum += Double.parseDouble(showChallanErrorDetailBen.getCalc_late_payment_amt());
                    }
                }

                grid.append("<tr id=\"row~").append(count).append("\">");

                grid.append("<td class=\"text-center\">");
                grid.append("<div class=\"custom-control custom-checkbox action-check\">");
                grid.append("<input type=\"checkbox\" class=\"custom-control-input toggle-action-section\" id=\"chk~").append(count).append("\" ");
                grid.append("onclick=\"onClickCheckboxChallan(this.id);\"/>");
                grid.append("<label class=\"custom-control-label\" for=\"chk~").append(count).append("\"></label>");
                grid.append("<input type=\"hidden\" id=\"editCheckBox~").append(count).append("\" name=\"editCheckBox\" value=\"false\"> </div>");
                grid.append("</td>");

                grid.append("<td style=\"width: 50px; text-align:center;\">").append(count).append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getBank_bsr_code()).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"bank_bsr_code~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].bank_bsr_code\" value=\"").append(showChallanErrorDetailBen.getBank_bsr_code()).append("\" onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                String l_tds_challan_date = "";
                if (!utl.isnull(showChallanErrorDetailBen.getTds_challan_date())) {
                    try {
                        Date l_challan_date = new Date(showChallanErrorDetailBen.getTds_challan_date());
                        if (l_challan_date != null) {
                            l_tds_challan_date = sdf.format(l_challan_date);
                        }
                    } catch (Exception e) {
                        utl.generateLog("tds_challan_date_error...", e.getMessage());
                    }
                }
                grid.append("<td style=\"width: 150px; text-align:left;\" title=\"").append(l_tds_challan_date).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:left;width: 100%;\" readonly id=\"tds_challan_date~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_date\" value=\"").append(l_tds_challan_date).append("\" onkeypress=\"validateDateOnKeyDown(this, event);\" onmouseover=\"openCalendar(this.id, this.id); doOnLoad(this.id, this.id);\"/>");
                grid.append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_challan_no()).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"tds_challan_no~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_no\" value=\"").append(showChallanErrorDetailBen.getTds_challan_no()).append("\" onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_challan_tds_amt()).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tds_challan_tds_amt~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_tds_amt\" value=\"").append(utl.isnull(showChallanErrorDetailBen.getTds_challan_tds_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(showChallanErrorDetailBen.getTds_challan_tds_amt()))).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_code()).append("\">");
                grid.append("<select ").append(" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"tds_code~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_code\" value=\"").append(showChallanErrorDetailBen.getTds_code()).append("\" onchange=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("<option value=\"\">----SELECT----</option> \n");
                if (selectSection != null) {
                    for (Map.Entry<String, String> entry : selectSection.entrySet()) {
                        if (showChallanErrorDetailBen.getTds_code().equalsIgnoreCase(entry.getKey())) {
                            grid.append("<option value=\"").append(entry.getKey()).append("\" selected=\"selected\">").append(entry.getValue()).append("</option>");
                        } else {
                            grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");

                        }
                    }
                }
                grid.append("</select>  ");
                grid.append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_challan_int_amt()).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tds_challan_int_amt~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_int_amt\" value=\"").append(utl.isnull(showChallanErrorDetailBen.getTds_challan_int_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(showChallanErrorDetailBen.getTds_challan_int_amt()))).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                grid.append("</td>");
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_challan_other_amt()).append("\">");
                grid.append("<input type=\"text\" ").append(" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tds_challan_other_amt~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_other_amt\" value=\"").append(utl.isnull(showChallanErrorDetailBen.getTds_challan_other_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(showChallanErrorDetailBen.getTds_challan_other_amt()))).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                    grid.append("</td>                                     ");
                    grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getCalc_late_payment_amt() == null ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(showChallanErrorDetailBen.getCalc_late_payment_amt()))).append("\">");
                    grid.append("<input type=\"text\" ").append("readonly=\"true\"").append(" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"calc_late_payment_amt~").append(count).append("\" value=\"").append(utl.isnull(showChallanErrorDetailBen.getCalc_late_payment_amt().trim()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(showChallanErrorDetailBen.getCalc_late_payment_amt()))).append("\" onkeypress='return validateNumber(event);' onblur=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                    grid.append("</td>");
                }
                grid.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showChallanErrorDetailBen.getTds_challan_minor_head()).append("\">");
                grid.append("<select ").append(" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"tds_challan_minor_head~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].tds_challan_minor_head\" value=\"").append(showChallanErrorDetailBen.getTds_challan_minor_head()).append("\" onchange=\"getCheckedCheckboxChallan(this.id);\" onclick=\"getTextValue(this.id);\"/>");
                for (Map.Entry<String, String> entry : minorHead.entrySet()) {
                    if (showChallanErrorDetailBen.getTds_challan_minor_head().equalsIgnoreCase(entry.getKey())) {
                        grid.append("<option value=\"").append(entry.getKey()).append("\" selected=\"selected\">").append(entry.getValue()).append("</option>");

                    } else {
                        grid.append("<option value=\"").append(entry.getKey()).append("\">").append(entry.getValue()).append("</option>");
                    }
                }
                grid.append("</select>");
                grid.append("</td>");
                grid.append("<input type=\"hidden\" id=\"popup_message~").append(count).append("\" name=\"popup_message\" value=\"").append(showChallanErrorDetailBen.getPopup_message()).append("\"/>");
                grid.append("<input type=\"hidden\" id=\"rowid_seq~").append(count).append("\" name=\"tdsChallanTranLoad[").append(count - 1).append("].rowid_seq\" value=\"").append(showChallanErrorDetailBen.getRowid_seq()).append("\"/>");
                grid.append("</tr>");
            }

            try {
                ProcessErrorsDB proc_err = new ProcessErrorsDB();
                String l_challan_gross_sum_query = proc_err.challanGrossSumQuery(client_code, entity_code, l_error_code, asmt,
                        l_record_MNL, l_record_MXL);
                // System.out.println("l_challan_gross_sum_query.." + l_challan_gross_sum_query);
                DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                ArrayList<String> arrData = db.getResultAsList(l_challan_gross_sum_query);
                if (arrData != null) {
                    String arr1 = arrData.get(0);
                    arr1 = utl.isnull(arr1) ? "0" : arr1;
                    g_challan_amt_sum = Double.parseDouble(arr1);
                    String arr2 = arrData.get(1);
                    arr2 = utl.isnull(arr2) ? "0" : arr2;
                    g_int_latpayment_sum = Double.parseDouble(arr2);
                    String arr3 = arrData.get(2);
                    arr3 = utl.isnull(arr3) ? "0" : arr3;
                    g_late_payment_fee_sum = Double.parseDouble(arr3);
                    if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                        String arr4 = arrData.get(3);
                        arr4 = utl.isnull(arr4) ? "0" : arr4;
                        g_calc_late_payment_amt_sum = Double.parseDouble(arr4);
                    }
                }
            } catch (Exception e) {
            }
            grid.append("</tbody>");
            grid.append("<tfoot>");
            grid.append("<tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
            grid.append("<td class=\"text-center\" title=\"Show Grand Total\"> <img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"></td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"4\">Page Wise Total :</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(challan_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\"></td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(int_latpayment_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(late_payment_fee_sum)).append("</td>");
            if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                grid.append("<td class=\"text-right\">").append(utl.getAmountIndianFormat(calc_late_payment_amt_sum)).append("</td>");
            }
            grid.append("<td class=\"text-right\" colspan=\"3\"></td>");
            grid.append("</tr>");
            grid.append("<tr class=\"highlight\" style=\"display: none;\">");
            grid.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"5\">Grand Total :</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_challan_amt_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\"></td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_int_latpayment_sum)).append("</td>");
            grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_late_payment_fee_sum)).append("</td>");
            if (!utl.isnull(l_error_code) && l_error_code.equalsIgnoreCase("L-CVE-1001")) {
                grid.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_calc_late_payment_amt_sum)).append("</td>");
            }
            grid.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"3\"></td>");
            grid.append("</tr>");
            grid.append("</tfoot>");
            grid.append("</table></div>");

            grid.append("<div class=\"form-group\">\n"
                    + "                    <div class=\"row\">\n"
                    + "                        <div class=\"button-section col-md-12 my-1 text-center\"> \n"
                    + "                            <a href=\"tdsProcessError?validate=true\"><button type=\"button\" id=\"\" class=\"form-btn\">\n"
                    + "                                <i class=\"fa fa-chevron-left\" aria-hidden=\"true\"></i>Back\n"
                    + "                            </button></a>\n"
                    + "                            <button type=\"button\" id=\"\" class=\"form-btn\" onclick=\"updateChallanErrorDetailData();\">\n"
                    + "                                <i class=\"fa fa-pencil-square\" aria-hidden=\"true\"></i>Update\n"
                    + "                            </button>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>");
        }
        return grid;
    }//end method
}//end class
