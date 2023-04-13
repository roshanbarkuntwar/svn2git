/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class LowerDeductCertificateSupport {

    public StringBuilder getLowerCertificateGrid(StringBuilder sb, List<TdsSplRateMastDTO> list, Util utl, int startIndex, int pageNumber) {
        if (list != null && list.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            sb.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            sb.append("                    <thead>");
            sb.append("                    <tr class=\"text-center\">");
            sb.append("                    <th class=\"th-action\" colspan=\"2\">Action</th>");
            sb.append("                    <th>Sr. No.</th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                PAN NO.");
//        sb.append("                                <hr>");
//        sb.append("                                Account No.");
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
            sb.append("                                Bank Branch Code");
//        sb.append("                                <hr>");
//        sb.append("                                Account No.");
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
            sb.append("                               TDS Type");
//        sb.append("                                <hr>");
//        sb.append("                                Deductee Name");
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
            sb.append("                               Section");
//        sb.append("                                <hr>");
//        sb.append("                                Deductee Name");
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
            sb.append("                                Certificate No.");
//        sb.append("                                <hr>");
//        sb.append("                                Deduction Name");
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
            sb.append("                                Certificate From Date");
//        sb.append("                                <hr>");
//        sb.append("                                Rate");
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
            sb.append("                              Certificate Upto Date");
//        sb.append("                                <hr>");
//        sb.append("                                TDS Amt.");
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
            sb.append("                                TDS Rate");
//        sb.append("                                <hr>");
//        sb.append("                                Certificate No.");
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
            sb.append("                                TDS Limit Amount");
//        sb.append("                                <hr>");
//        sb.append("                                Certificate No.");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    </tr>");
            sb.append("                    </thead>");

            sb.append("                    <tbody>");
            int count = 0;
            for (TdsSplRateMastDTO splRate : list) {
                count++;
                sb.append("                        <tr>");
                sb.append("                            <td class=\"text-center td-action\">");

                sb.append("<form class=\"d-inline-block\" id=\"tdsSpclRateEntryFormId~").append(startIndex + count).append("\" method=\"post\" action=\"tdsLowerDeductionEntry\">");
                sb.append("<input type=\"hidden\" name=\"action\" value=\"edit\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.tds_code\" value=\"").append(splRate.getTds_code()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.deductee_panno\" value=\"").append(splRate.getDeductee_panno()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.acc_year\" value=\"").append(splRate.getAcc_year()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.entity_code\" value=\"").append(splRate.getEntity_code()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.client_code\" value=\"").append(splRate.getClient_code()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.certificate_no\" value=\"").append(splRate.getCertificate_no()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"tdsSplRateMastId.tds_type_code\" value=\"").append(splRate.getTds_type_code()).append("\"/>");
                sb.append("<input type=\"hidden\" name=\"pageNumber\" value=\"").append(pageNumber).append("\"/>");
                sb.append("<a id=\"anchorId~").append(startIndex + count).append("\" onclick=\"onEditForm(this.id)\">");
                sb.append("<i class=\"fa edit text-primary cursor-pointer mr-1\" title=\"Edit\" ></i></a>");
                sb.append("</form>");
                sb.append("                            </td>");
                sb.append("                            <td class=\"text-center td-action\">");
                sb.append("                                <i title=\"Delete Record\" onclick=\"deleteTdsSplRateMastRecord('" + splRate.getTds_code() + "', '" + splRate.getCertificate_no() + "', '" + splRate.getDeductee_panno() + "', '" + splRate.getEntity_code() + "', '" + splRate.getAcc_year() + "', '" + splRate.getTds_type_code() + "', '" + splRate.getClient_code() + "');\" class=\"fa delete text-danger cursor-pointer mr-1\"></i>");
                sb.append("                            </td>");
                sb.append("                            <td class=\"text-center data-height font-weight-bold\">" + (startIndex + count) + "</td>");
                sb.append("                            <td>");
                sb.append("                                " + splRate.getDeductee_panno() + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getAccount_no() + "");
                sb.append("                            </td>");
                sb.append("                            <td>");
                sb.append("                                " + splRate.getBank_branch_code() + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getAccount_no() + "");
                sb.append("                            </td>");
                sb.append("                            <td>");
                sb.append("                                " + splRate.getTds_type_code() + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getDeductee_name() + "");
                sb.append("                            </td>");

                sb.append("                            <td>");
                String tdsCodeValue = "";
                try {
                    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                    String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + splRate.getTds_code() + "'";
                    tdsCodeValue = ef.execute_oracle_function_as_string(functionString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sb.append("                                " + !utl.isnull(tdsCodeValue) !=null ? tdsCodeValue : "" + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getDeductee_name() + "");
                sb.append("                            </td>");

                sb.append("                            <td>");
                sb.append("                                " + splRate.getCertificate_no() + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getDeductee_name() + "");
                sb.append("                            </td>");
                sb.append("                            <td>");
                try {
                    sb.append("                                " + splRate.getFrom_date() != null ? sdf.format(splRate.getFrom_date()) : "");
                } catch (Exception e) {
                }
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getTds_rate() + "");
                sb.append("                            </td>");
                sb.append("                            <td>");
                try {
                    sb.append("                                " + splRate.getCertificate_valid_upto() != null ? sdf.format(splRate.getCertificate_valid_upto()) : "");
                } catch (Exception e) {
                }
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getTds_rate() + "");
                sb.append("                            </td>");
                sb.append("                            <td class=\"text-right data-height font-weight-bold\">");
                sb.append("                                " + utl.getAmountIndianFormat(Double.parseDouble(splRate.getTds_rate())) + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getTds_rate() + "");
                sb.append("                            </td>");
                sb.append("                            <td class=\"text-right data-height font-weight-bold\">");
                sb.append("                                " + utl.getAmountIndianFormat(Double.parseDouble(splRate.getTds_limit_amt())) + "");
//                sb.append("                                <hr>");
//                sb.append("                                " + transactionBrowseEntity.getTds_rate() + "");
                sb.append("                            </td>");

                sb.append("                        </tr>");
            }
            sb.append("                       ");
            sb.append("                    </tbody>");

            sb.append(
                    "                </table>");

        } else {
            sb.append("                    hello");
        }

        return sb;
    }

}
