/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author trainee
 */
public class SalaryDataGrid {

    StringBuilder sb = null;

    public SalaryDataGrid() {
        sb = new StringBuilder();
    }

    public StringBuilder getSalaryDataGrid(ArrayList<ShowSalaryErrorSummaryDetailsBean> listtranLoadErrorSummary, String l_client_code, String l_entity_code, Assessment asmt, String l_error_code, Util utl) {
        Double tran_amt_sum = 0d;
        Double calc_tds_amt_sum = 0d;
        Double ded_tds_amt_sum = 0d;
        Double diff_tds_amt_sum = 0d;

        Double g_tran_amt_sum = 0d;
        Double g_calc_tds_amt_sum = 0d;
        Double g_ded_tds_amt_sum = 0d;
        Double g_diff_tds_amt_sum = 0d;

        if (listtranLoadErrorSummary != null && listtranLoadErrorSummary.size() > 0) {
            sb.append("<table class=\"table table-striped\" id=\"ErrorDtltable\" style=\"margin-bottom: 0px; width: 100%;\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"width: 50px; text-align:center;\">Sr No</th>");
            sb.append("<th style=\"width: 250px; text-align:center;\">Bank Branch</th>");
            sb.append("<th style=\"width: 150px; text-align:center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Deductee Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     </th>");
            sb.append("<th style=\"width: 200px; text-align:center;\">Deductee PAN NO</th>");
            sb.append(" <th style=\"width: 150px; text-align:center;\">Total Taxable Amt.</th>");
            sb.append("<th style=\"width: 250px; text-align:center;\">Calculated TDS Amt.</th>");
            sb.append("<th style=\"width: 250px; text-align:center;\">TDS Deducted Amt.</th>");
            sb.append(" <th style=\"width: 250px; text-align:center;\">TDS Different Amt.</th>");
            sb.append("</tr>\n");
            sb.append(" </thead>\n");
            sb.append(" <tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\">");
            int count = 0;
            for (ShowSalaryErrorSummaryDetailsBean showErrorSummaryDetailBen : listtranLoadErrorSummary) {
                count++;
                if (!utl.isnull(showErrorSummaryDetailBen.getTotal_taxble_income().trim())) {
                    tran_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getTotal_taxble_income());
                }
                if (!utl.isnull(showErrorSummaryDetailBen.getTax_before_adj())) {
                    calc_tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getTax_before_adj());
                }
                if (!utl.isnull(showErrorSummaryDetailBen.getTotal_tds_amt())) {
                    ded_tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getTotal_tds_amt());
                }
                if (!utl.isnull(showErrorSummaryDetailBen.getShort_fall().trim())) {
                    diff_tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getShort_fall());
                }

                sb.append("<tr>");
//                sb.append("<td style=\"width: 50px; text-align:center;\"><span id=\"showErrorDetailBtnId~").append(count).append("\" onclick=\"showSalaryErrorDetailContentData(this.id);\" class=\"fa fa-pencil table-edit-pencil-icon\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Show Error Detail\" style=\"cursor:pointer;\"></span></td>");
                sb.append("<td style=\"width: 50px; text-align:center;\">").append(showErrorSummaryDetailBen.getSlno()).append("</td>");
                sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showErrorSummaryDetailBen.getBank_branch_code()).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"bank_branch_code~").append(count).append("\" name=\"bank_branch_code\" value=\"").append(showErrorSummaryDetailBen.getBank_branch_code()).append("\"/>");
                sb.append("</td>");
                sb.append("<td style=\"width: 150px; text-align:left;\" title=\"").append(showErrorSummaryDetailBen.getDeductee_name()).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:left;width: 100%;\" id=\"deductee_name~").append(count).append("\" name=\"deductee_name\" value=\"").append(showErrorSummaryDetailBen.getDeductee_name()).append("\"/>");
                sb.append("</td>");
                sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showErrorSummaryDetailBen.getDeductee_panno()).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"deductee_panno~").append(count).append("\" name=\"deductee_panno\" value=\"").append(showErrorSummaryDetailBen.getDeductee_panno()).append("\"/>");
                sb.append("</td>");
                sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getTotal_taxble_income()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTotal_taxble_income()))).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tran_amt~").append(count).append("\" name=\"tran_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getTotal_taxble_income()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTotal_taxble_income()))).append("\"/>");
                sb.append("</td>");
                sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getTax_before_adj()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTax_before_adj()))).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"calc_tds_amt~").append(count).append("\" name=\"calc_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getTax_before_adj()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTax_before_adj()))).append("\" />");
                sb.append("</td>");
                sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getTotal_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTotal_tds_amt()))).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"calc_tds_amt~").append(count).append("\" name=\"calc_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getTotal_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTotal_tds_amt()))).append("\" />");
                sb.append("</td>");
                sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getShort_fall()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getShort_fall()))).append("\">");
                sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"diff_tds_amt~").append(count).append("\" name=\"diff_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getShort_fall()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getShort_fall()))).append("\" />");
                sb.append("</td>");
                sb.append("<input type=\"hidden\" id=\"deductee_panno~").append(count).append("\" name=\"deductee_panno\" value=\"").append(showErrorSummaryDetailBen.getDeductee_panno()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"deductee_code~").append(count).append("\" name=\"deductee_code\" value=\"").append(showErrorSummaryDetailBen.getDeductee_code()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"deductee_name~").append(count).append("\" name=\"deductee_name\" value=\"").append(showErrorSummaryDetailBen.getDeductee_name()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"client_code~").append(count).append("\" name=\"client_code\" value=\"").append(showErrorSummaryDetailBen.getClient_code()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"from_date~").append(count).append("\" name=\"from_date\" value=\"").append(showErrorSummaryDetailBen.getFrom_date()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"to_date~").append(count).append("\" name=\"to_date\" value=\"").append(showErrorSummaryDetailBen.getTo_date()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"sex~").append(count).append("\" name=\"sex\" value=\"").append(showErrorSummaryDetailBen.getSex()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"deduction_ref_no~").append(count).append("\" name=\"deduction_ref_no\" value=\"").append(showErrorSummaryDetailBen.getDeduction_ref_no()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"rowid_seq~").append(count).append("\" name=\"rowid_seq\" value=\"").append(showErrorSummaryDetailBen.getRowid_seq()).append("\"/>");
                sb.append("<input type=\"hidden\" id=\"identification_no~").append(count).append("\" name=\"identification_no\" value=\"").append(showErrorSummaryDetailBen.getIdentification_no()).append("\"/>");
                sb.append("</tr>");
            }
            sb.append("</tbody>");
            sb.append("<tfoot>");
            sb.append("<tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
            sb.append("<td class=\"text-center\"><img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"></td>");
            sb.append("<td style=\"text-align: right; min-width: 100px;font-weight: bold;\" colspan=\"3\">Page Wise Total :</td>");
            sb.append("<td style=\"text-align: right; width: 110px;font-weight: bold;\">").append(utl.getAmountIndianFormat(tran_amt_sum)).append("</td>");
            sb.append("<td style=\"text-align: right; width: 110px;font-weight: bold;\">").append(utl.getAmountIndianFormat(calc_tds_amt_sum)).append("</td>");
            sb.append("<td style=\"text-align: right; width: 110px;font-weight: bold;\">").append(utl.getAmountIndianFormat(ded_tds_amt_sum)).append("</td>");
            sb.append("<td style=\"text-align: right; width: 110px;font-weight: bold;\">").append(utl.getAmountIndianFormat(diff_tds_amt_sum)).append("</td>");
            sb.append("</tr>");

            try {
                String l_screen2_gross_sum_query
                        = "select nvl(sum(v.afgr4_total_amt), 0)tran_amt,\n"
                        + "       nvl(sum(v.afg30_total_amt),0)calc_tds_amt,\n"
                        + "       nvl(sum(v.afg45_total_amt),0)ded_tds_amt,\n"
                        + "       nvl(sum(v.afg46_total_amt),0)diff_tds_amt\n"
                        + "  from tran_load_error_part2_temp a, VIEW_SALARY_TRAN_LOAD v\n"
                        + " where a.entity_code = '" + l_entity_code + "'\n"
                        + "   and v.acc_year = a.acc_year\n"
                        + "   and v.entity_code = a.entity_code\n"
                        + "   and v.client_code = a.client_code\n"
                        + "   and v.DEDUCTEE_PANNO = a.deductee_panno\n"
                        + "   and v.deductee_name = a.deductee_name\n"
                        + "   and v.deductee_code = a.deductee_code"
                        + "   and exists\n"
                        + " (select 1\n"
                        + "          from client_mast w1\n"
                        + "         where w1.client_code = a.client_code\n"
                        + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                        + "               w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                        + "               w1.ssg_parent_code = '" + l_client_code + "' or w1.sssg_parent_code = '" + l_client_code + "'))\n"
                        + "   and a.acc_year = '" + asmt.getAccYear() + "'\n"
                        + "   and a.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                        + "   and a.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                        + "   and a.error_code = '" + l_error_code + "'";

                utl.generateSpecialLog("process error bugs salary query----", l_screen2_gross_sum_query);

                DbFunctionExecutorAsString db_fun = new DbFunctionExecutorAsString();
                ArrayList<ArrayList<String>> arrData = db_fun.execute_oracle_query_as_list(l_screen2_gross_sum_query);
                if (arrData != null) {
                    ArrayList<String> arr_list = arrData.get(0);
                    String arr1 = utl.isnull(arr_list.get(0)) ? "0" : arr_list.get(0);
                    g_tran_amt_sum = Double.parseDouble(arr1);
                    String arr2 = utl.isnull(arr_list.get(1)) ? "0" : arr_list.get(1);
                    arr2 = utl.isnull(arr2) ? "0" : arr2;
                    g_calc_tds_amt_sum = Double.parseDouble(arr2);
                    String arr3 = utl.isnull(arr_list.get(2)) ? "0" : arr_list.get(2);
                    arr3 = utl.isnull(arr3) ? "0" : arr3;
                    g_ded_tds_amt_sum = Double.parseDouble(arr3);
                    String arr4 = utl.isnull(arr_list.get(3)) ? "0" : arr_list.get(3);
                    arr4 = utl.isnull(arr4) ? "0" : arr4;
                    g_diff_tds_amt_sum = Double.parseDouble(arr4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append("<tr class=\"highlight collapse\">");
            sb.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"4\">Grand Total :</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_tran_amt_sum)).append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_calc_tds_amt_sum)).append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_ded_tds_amt_sum)).append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_diff_tds_amt_sum)).append("</td>");
            sb.append("</tr>");
            sb.append("</tfoot>");
            sb.append("</table>");
        } else {
            sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
        }
        return sb;
    }//end method
}//end class
