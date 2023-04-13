/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ErrorDetailSummaryDataGridAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private ArrayList<ShowErrorSummaryDetailsBean> showErrorDetailSummaryList;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private String processLevel;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private InputStream inputStream;
   

    public ErrorDetailSummaryDataGridAction() {
        utl = new Util();
    }//end constructor 

    @Override
    public String execute() throws Exception {
        String l_return_view = "input_success";

        StringBuilder sb = new StringBuilder();
        try {
            if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                if (getTotalRecords() > 0) {
                    if (getRecordsPerPage() > 0) {
                        if (getPageNumber() > 0) {
                            int maxVal = getTotalRecords();
                            int minVal = 1;

                            if (getTotalRecords() > getRecordsPerPage()) {
                                maxVal = getPageNumber() * getRecordsPerPage();
                                minVal = maxVal - getRecordsPerPage() + 1;
                                if (maxVal > getTotalRecords()) {
                                    maxVal = getTotalRecords();
                                }
                            }
                            setStartIndex(minVal - 1);
                            setEndIndex((maxVal - 1));

                            int l_start_page = 0;
                            if (getPageNumber() == 0) {
                                l_start_page = 1;
                            } else {
                                l_start_page = getPageNumber();
                            }

                            int l_records_to_add = getRecordsPerPage();
                            int l_record_MXL = (l_start_page * l_records_to_add);
                            int l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;

                            String l_entity_code = "";
                            String l_client_code = "";
                            String l_acc_year = "";
                            String l_quarter_no = "";
                            String l_tds_type_code = "";
                            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                            if (viewClientMast != null) {
                                l_entity_code = viewClientMast.getEntity_code();
                                l_client_code = viewClientMast.getClient_code();
                                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                                l_acc_year = asmt.getAccYear();
                                l_quarter_no = asmt.getQuarterNo();
                                l_tds_type_code = asmt.getTdsTypeCode();
                            }

                            String l_error_code = (String) session.get("SummaryErrorCode");

                            String process_level_whr_clause = "";
                            if (!utl.isnull(getProcessLevel()) && getProcessLevel().equalsIgnoreCase("1")) {// for all level
                                process_level_whr_clause += "     and (w1.client_code='" + l_client_code + "' or \n"
                                        + "                         w1.parent_code='" + l_client_code + "' or\n"
                                        + "                         w1.g_parent_code='" + l_client_code + "' or\n"
                                        + "                         w1.sg_parent_code='" + l_client_code + "' or\n"
                                        + "                         w1.ssg_parent_code='" + l_client_code + "' or\n"
                                        + "                         w1.sssg_parent_code='" + l_client_code + "')";
                            } else {// for all level
                                process_level_whr_clause += " and a.validation_client_code = '" + l_client_code + "'\n";
                            }

                            String l_query = "";
                            l_query = "select *\n"
                                    + "  from (select rownum slno,\n"
                                    + "               client_code,\n"
                                    + "               bank_branch_code,\n"
                                    + "               tds_code,\n"
                                    + "               tds_name,\n"
                                    + "               deductee_code,\n"
                                    + "               deductee_name,\n"
                                    + "               deductee_panno,\n"
                                    + "               tran_amt,\n"
                                    + "               tds_amt,\n"
                                    + "               calc_tds_amt,\n"
                                    + "               diff_tds_amt,\n"
                                    + "               uptolq_tds_amt,\n"
                                    + "               uptolq_calc_tds_amt,\n"
                                    + "               uptolq_diff_tds_amt,\n"
                                    + "               net_diff_tds_amt,\n"
                                    + "               late_fee_amt,\n"
                                    + "               late_payment_amt,\n"
                                    + "               late_payment_ramt,\n"
                                    + "               late_payment_bamt,\n"
                                    + "               record_count\n"
                                    + "          from (select a.client_code,\n"
                                    + "                       w1.bank_branch_code,\n"
                                    + "                       tds_code,\n"
                                    + "                       tds_name,\n"
                                    + "                       deductee_code,\n"
                                    + "                       deductee_name,\n"
                                    + "                       deductee_panno,\n"
                                    + "                       tran_amt,\n"
                                    + "                       tds_amt,\n"
                                    + "                       calc_tds_amt,\n"
                                    + "                       diff_tds_amt,\n"
                                    + "                       uptolq_tds_amt,\n"
                                    + "                       uptolq_calc_tds_amt,\n"
                                    + "                       nvl(lhs_tds_error_n.lhssys_is_short_deduction(a.entity_code,\n"
                                    + "                                                                     a.client_code,\n"
                                    + "                                                                     a.acc_year,\n"
                                    + "                                                                     a.quarter_no,\n"
                                    + "                                                                     a.tds_type_code,\n"
                                    + "                                                                     a.tds_code,\n"
                                    + "                                                                     a.deductee_panno,\n"
                                    + "                                                                     'YEAR'),\n"
                                    + "                           0) uptolq_diff_tds_amt,\n"
                                    + "                       net_diff_tds_amt,\n"
                                    + "                       late_fee_amt,\n"
                                    + "                       late_payment_amt,\n"
                                    + "                       late_payment_ramt,\n"
                                    + "                       late_payment_bamt,\n"
                                    + "                       a.record_count\n"
                                    + "                  from tran_load_error_part2_temp a, client_mast w1\n"
                                    + "                 where a.entity_code = '" + l_entity_code + "'\n"
                                    + "                   and w1.entity_code = a.entity_code\n"
                                    + "                   and w1.client_code = a.client_code\n"
                                    + process_level_whr_clause
                                    + "                   and a.acc_year = '" + l_acc_year + "'\n"
                                    + "                   and a.quarter_no = '" + l_quarter_no + "'\n"
                                    + "                   and a.tds_type_code = '" + l_tds_type_code + "'\n"
                                    + "                   and a.error_code = '" + l_error_code + "'\n"
                                    + "                 order by diff_tds_amt desc, deductee_panno))\n"
                                    + "                 where slno between " + l_record_MNL + " and " + l_record_MXL + "";

                            utl.generateSpecialLog("RPE-0005 (deductee transaction second screen summary query)--208--", l_query);
                            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                            ArrayList<ShowErrorSummaryDetailsBean> listtranLoadErrorSummary = db.getGenericList(new ShowErrorSummaryDetailsBean(), l_query);
                            Double tran_amt_sum = 0d;
                            Double tds_amt_sum = 0d;
                            Double calc_tds_amt_sum = 0d;
                            Double diff_tds_amt_sum = 0d;
                            Double ul_qt_diff_sum = 0d;
//                            Double gross_diff_sum = 0d;

                            Double g_tran_amt_sum = 0d;
                            Double g_tds_amt_sum = 0d;
                            Double g_calc_tds_amt_sum = 0d;
                            Double g_diff_tds_amt_sum = 0d;
                            Double g_ul_qt_diff_sum = 0d;
//                            Double g_gross_diff_sum = 0d;

                            if (listtranLoadErrorSummary != null && listtranLoadErrorSummary.size() > 0) {
                                sb.append("<div class=\"table-responsive mt-2\">");
                                sb.append("<table class=\"table table-striped\" id=\"table\">");
                                sb.append("<thead>");
                                sb.append("<tr>");
                                sb.append("<th >Action</th>");
                                sb.append("<th >Sr No.</th>");
                                sb.append("<th >Bank Branch</th>");
                                sb.append("<th >Section</th>");                                
                                sb.append("<th >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Deductee Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     </th>");
                                //L-CVE-1001
                                //sb.append("<th >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Challan No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     </th>");
                                sb.append("<th >Deductee PAN NO</th>");
                                sb.append(" <th >Tran-Amt Q").append(l_quarter_no.substring(1)).append("</th>");
                                sb.append("<th >TDS Amt Q").append(l_quarter_no.substring(1)).append("</th>");
                                sb.append(" <th >Calc TDS Amt Upto Q").append(l_quarter_no.substring(1)).append("</th>");
                                sb.append("<th >Diff TDS Amt Upto Q").append(l_quarter_no.substring(1)).append("</th>");
                                sb.append("<th >Excess TDS Upto Last Qtr</th>");
//                                sb.append("<th style=\"width: 150px; text-align:center;\">Gross Diff TDS Amount</th>");
                                sb.append("</tr>\n");
                                sb.append(" </thead>\n");
                                sb.append(" <tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\">");
                                int count = 0;
                                for (ShowErrorSummaryDetailsBean showErrorSummaryDetailBen : listtranLoadErrorSummary) {
                                    count++;
                                    if (!utl.isnull(showErrorSummaryDetailBen.getTran_amt().trim())) {
                                        tran_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getTran_amt());
                                    }
                                    if (!utl.isnull(showErrorSummaryDetailBen.getTds_amt().trim())) {
                                        tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getTds_amt());
                                    }
                                    if (!utl.isnull(showErrorSummaryDetailBen.getCalc_tds_amt())) {
                                        calc_tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getCalc_tds_amt());
                                    }
                                    if (!utl.isnull(showErrorSummaryDetailBen.getDiff_tds_amt().trim())) {
                                        diff_tds_amt_sum += Double.parseDouble(showErrorSummaryDetailBen.getDiff_tds_amt());
                                    }
                                    if (!utl.isnull(showErrorSummaryDetailBen.getUptolq_diff_tds_amt().trim())) {
                                        ul_qt_diff_sum += Double.parseDouble(showErrorSummaryDetailBen.getUptolq_diff_tds_amt());
                                    }
                                    sb.append("<tr>");
                                    sb.append("<td class=\"text-center\"><span id=\"showErrorDetailBtnId~").append(count).append("\" onclick=\"showErrorDetailContentData(this.id);\" class=\"fa details cursor-pointer mr-1 text-success\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Show Error Detail\" style=\"cursor:pointer;\"></span></td>");
                                    sb.append("<td class=\"text-center\">").append(count).append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showErrorSummaryDetailBen.getBank_branch_code()).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"bank_branch_code~").append(count).append("\" name=\"bank_branch_code\" value=\"").append(showErrorSummaryDetailBen.getBank_branch_code()).append("\"/>");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showErrorSummaryDetailBen.getTds_name()).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:center;width: 100%;\" id=\"tds_name~").append(count).append("\" name=\"tds_name\" value=\"").append(showErrorSummaryDetailBen.getTds_name()).append("\"/>");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:left;\" title=\"").append(showErrorSummaryDetailBen.getDeductee_name()).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:left;width: 100%;\" id=\"deductee_name~").append(count).append("\" name=\"deductee_name\" value=\"").append(showErrorSummaryDetailBen.getDeductee_name()).append("\"/>");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(showErrorSummaryDetailBen.getDeductee_panno()).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:center;width: 80%;\" id=\"deductee_panno~").append(count).append("\" name=\"deductee_panno\" value=\"").append(showErrorSummaryDetailBen.getDeductee_panno()).append("\"/>");
                                    sb.append(" (<b>").append(showErrorSummaryDetailBen.getRecord_count()).append("</b>)");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getTran_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTran_amt()))).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tran_amt~").append(count).append("\" name=\"tran_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getTran_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTran_amt()))).append("\"/>");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 150px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getTds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTds_amt()))).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"tds_amt~").append(count).append("\" name=\"tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getTds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getTds_amt()))).append("\"/>");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getCalc_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getCalc_tds_amt()))).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"calc_tds_amt~").append(count).append("\" name=\"calc_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getCalc_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getCalc_tds_amt()))).append("\" />");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getDiff_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getDiff_tds_amt()))).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"diff_tds_amt~").append(count).append("\" name=\"diff_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getDiff_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getDiff_tds_amt()))).append("\" />");
                                    sb.append("</td>");
                                    sb.append("<td style=\"width: 250px; text-align:center;\" title=\"").append(utl.isnull(showErrorSummaryDetailBen.getUptolq_diff_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getUptolq_diff_tds_amt()))).append("\">");
                                    sb.append("<input type=\"text\" readonly=\"true\" style=\"border: none;background: none; text-align:right;width: 100%;\" id=\"uptolq_diff_tds_amt~").append(count).append("\" name=\"uptolq_diff_tds_amt\" value=\"").append(utl.isnull(showErrorSummaryDetailBen.getUptolq_diff_tds_amt()) ? "" : utl.getAmountIndianFormat(Double.parseDouble(showErrorSummaryDetailBen.getUptolq_diff_tds_amt()))).append("\" />");
                                    sb.append("</td>");
                                    sb.append("<input type=\"hidden\" id=\"tds_code~").append(count).append("\" name=\"tds_code\" value=\"").append(showErrorSummaryDetailBen.getTds_code()).append("\"/>");
                                    sb.append("<input type=\"hidden\" id=\"deductee_code~").append(count).append("\" name=\"deductee_code\" value=\"").append(showErrorSummaryDetailBen.getDeductee_code()).append("\"/>");
                                    sb.append("<input type=\"hidden\" id=\"client_code~").append(count).append("\" name=\"client_code\" value=\"").append(showErrorSummaryDetailBen.getClient_code()).append("\"/>");
                                    sb.append("</tr>");
                                }
                                sb.append("</tbody>");
                                sb.append("<tfoot>");
                                sb.append("<tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
                                sb.append("<td class=\"text-center\" title=\"Show Grand Total\"><img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"><i class=\"text-primary cursor-pointer\" onclick=\"collapseOn();\"></i></td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"5\">Page Wise Total :</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tran_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(calc_tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(diff_tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(ul_qt_diff_sum)).append("</td>");
                                sb.append("</tr>");

                                try {
                                    String l_screen2_gross_sum_query
                                            = "select nvl(sum(tran_amt), 0)tran_amt,\n"
                                            + "       nvl(sum(tds_amt),0)tds_amt,\n"
                                            + "       nvl(sum(calc_tds_amt),0)calc_tds_amt,\n"
                                            + "       nvl(sum(diff_tds_amt),0)diff_tds_amt,\n"
                                            + "       nvl(sum(uptolq_diff_tds_amt),0)uptolq_diff_tds_amt,\n"
                                            + "       nvl(sum(net_diff_tds_amt),0)net_diff_tds_amt\n"
                                            + "  from tran_load_error_part2_temp a\n"
                                            + " where a.entity_code = '" + l_entity_code + "'\n"
                                            + "   and exists\n"
                                            + " (select *\n"
                                            + "          from client_mast w1\n"
                                            + "         where w1.client_code = a.client_code\n"
                                            + "           and (w1.client_code = '" + l_client_code + "' or w1.parent_code = '" + l_client_code + "' or\n"
                                            + "               w1.g_parent_code = '" + l_client_code + "' or w1.sg_parent_code = '" + l_client_code + "' or\n"
                                            + "               w1.ssg_parent_code = '" + l_client_code + "' or w1.sssg_parent_code = '" + l_client_code + "'))\n"
                                            + "   and a.acc_year = '" + l_acc_year + "'\n"
                                            + "   and a.quarter_no = '" + l_quarter_no + "'\n"
                                            + "   and a.tds_type_code = '" + l_tds_type_code + "'\n"
                                            + "   and a.error_code = '" + l_error_code + "'";
                                    utl.generateSpecialLog("gross sum query is", l_screen2_gross_sum_query);
                                    DbFunctionExecutorAsString objGetDataListThroughWorkbook1 = new DbFunctionExecutorAsString();
                                    ArrayList<ArrayList<String>> execute_oracle_query_as_list = objGetDataListThroughWorkbook1.execute_oracle_query_as_list(l_screen2_gross_sum_query);
                                    ArrayList<String> arrData=new ArrayList<>();  
                                    for (ArrayList<String> arrayList : execute_oracle_query_as_list) {
                                        for (String values : arrayList) {
                                            arrData.add(values);
                                        }
                                    }
//                                    ArrayList<String> arrData = objGetDataListThroughWorkbook1.getResultAsList(l_screen2_gross_sum_query);
                                    if (arrData.size()>0 && !arrData.isEmpty()) {
                                        String arr1 = arrData.get(0);
                                        arr1 = utl.isnull(arr1) ? "0" : arr1;
                                        g_tran_amt_sum = Double.parseDouble(arr1);
                                        String arr2 = arrData.get(1);
                                        arr2 = utl.isnull(arr2) ? "0" : arr2;
                                        g_tds_amt_sum = Double.parseDouble(arr2);
                                        String arr3 = arrData.get(2);
                                        arr3 = utl.isnull(arr3) ? "0" : arr3;
                                        g_calc_tds_amt_sum = Double.parseDouble(arr3);
                                        String arr4 = arrData.get(3);
                                        arr4 = utl.isnull(arr4) ? "0" : arr4;
                                        g_diff_tds_amt_sum = Double.parseDouble(arr4);
                                        String arr5 = arrData.get(4);
                                        arr5 = utl.isnull(arr5) ? "0" : arr5;
                                        g_ul_qt_diff_sum = Double.parseDouble(arr5);
                                    }
                                } catch (Exception e) {
                                }
                                sb.append("<tr class=\"highlight collapse\">");
                                sb.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"6\">Grand Total :</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_tran_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_calc_tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_diff_tds_amt_sum)).append("</td>");
                                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(g_ul_qt_diff_sum)).append("</td>");
                                sb.append("</tr>");
                                sb.append("</tfoot>");
                                sb.append("</table></div>");

                                sb.append("<div class=\"form-group\">\n"
                                        + " <div class=\"row\">\n"
                                        + "     <div class=\"button-section col-md-12 my-1 text-center\"> \n"
                                        + "         <a href=\"tdsProcessError?validate=true\"><button type=\"button\" id=\"\" class=\"form-btn\">\n"
                                        + "             <i class=\"fa fa-chevron-left\" aria-hidden=\"true\"></i>Back\n"
                                        + "         </button></a>\n"
                                        + "     </div>\n"
                                        + " </div>\n"
                                        + "</div>");
                            }
                        }
                    }
                } else {
                    sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");
                l_return_view = "input_success";
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (getSetRecPerPage() > 0) {
                session.put("SHOWERRORDETAILSUMMARYSRCHPG", String.valueOf(getSetRecPerPage()));// records per page
//                return_value = "success";
                sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                l_return_view = "input_success";
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_view;
    }//end method

    public ArrayList<ShowErrorSummaryDetailsBean> getShowErrorDetailSummaryList() {
        return showErrorDetailSummaryList;
    }

    public void setShowErrorDetailSummaryList(ArrayList<ShowErrorSummaryDetailsBean> showErrorDetailSummaryList) {
        this.showErrorDetailSummaryList = showErrorDetailSummaryList;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(int setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    

}//end class
