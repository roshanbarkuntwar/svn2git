/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import globalUtilities.Util;
import hibernateObjects.ViewTdsTranLoad;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class ChallanAllocationSupport {

    public StringBuilder getChallanAllocationDataGrid(StringBuilder sb, List<ChallanAllocationUnallocationEntity> list, Util utl, int startIndex, int pageNumber, String identifyflag, MapTdsChallanGrossTotalList listtdstranData) {
        
        try {

            if (list != null && list.size() > 0) {

                sb.append("<table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
                sb.append("<thead>");
                sb.append("<tr class=\"text-center\">");
                if (identifyflag.equalsIgnoreCase("map_transaction")) {
                    sb.append("<th class=\"th-action check-action text-center\">");
                    sb.append("<div class=\"custom-control custom-checkbox action-check\"><input type=\"checkbox\" name=\"allocateAllRecordID\" class=\"custom-control-label custom-control-input toggle-action-section\" class=\"\"  id=\"allocateAllRecordID\" onclick=\"chkAllocateAllTdsRecord()\" title=\"Allocate All TDS Record\">");
                    sb.append("<label  class=\"custom-control-label\" for=\"chkAll\" ></label>");
                    sb.append("</div></th>");
                    sb.append("<th>Sr.No</th> ");
                }

                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Branch</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Deductee PAN</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Deductee Ref. No.</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Account No.</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">TDS Section</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");

                
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Transaction Amount</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Total TDS</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">TDS Rate</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Deductee Name</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Transaction Date</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">TDS Deduct Date</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");

                sb.append("<th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Certificate No.</div>"
//                        + "<div class=\"sort-icon\">"
//                        + "<i class=\"fa fa-sort-desc\"></i>"
//                        + "<i class=\"fa fa-sort-asc\"></i>"
//                        + "</div>"
                        + "</div>"
                        + "</th>");
//                sb.append("<th>"
//                        + "<div class=\"table-head-inner\">"
//                        + "<div class=\"table-heading\">Reason Flag</div>"
////                        + "<div class=\"sort-icon\">"
////                        + "<i class=\"fa fa-sort-desc\"></i>"
////                        + "<i class=\"fa fa-sort-asc\"></i>"
////                        + "</div>"
//                        + "</div>"
//                        + "</th>");
                sb.append("</tr>");
                sb.append("</thead>");
                sb.append("<tbody>");

                int count = startIndex;
                String cssClass = "";
                Double totalTranAmt = 0.0;
                Double totalAmt = 0.0;
                Double totalTdsRate = 0.0;
              try {
                for (ChallanAllocationUnallocationEntity data : list) {

                    if (utl.isnull(data.getTds_challan_rowid_seq())) {
                        cssClass = "unallocated";
                    } else {
                        cssClass = "allocated";

                    }
                    totalTranAmt += !utl.isnull(data.getTran_amt()) ?  Double.parseDouble(data.getTran_amt()) : 0.0;
                    totalAmt += !utl.isnull(data.getTds_amt()) ?  Double.parseDouble(data.getTds_amt()) : 0.0;
                    totalTdsRate +=  !utl.isnull(data.getTds_rate()) ?  Double.parseDouble(data.getTds_rate()) : 0.0;

                    count++;
                    sb.append("<tr class=\"" + cssClass + "\">");
                    sb.append("<td class=\"d-none\">").append(data.getRowid_seq()).append("</td>");
                    sb.append("<td class=\"d-none\" id=\"tdsChallenRowId#").append(count).append("\">").append(data.getTds_challan_rowid_seq()).append("</td>");

                    if (identifyflag.equalsIgnoreCase("map_transaction")) {
                        sb.append("<td class=\"td-action\"><div class=\"custom-control custom-checkbox action-check\">");
                        if (utl.isnull(data.getTds_challan_rowid_seq())) {
                            sb.append("<input type=\"checkbox\" class=\"mapTransaction custom-control-input toggle-action-section\" name=\"chkBox[]\" id=\"chk#" + count + "\" onclick=\"getMapTransaction(this.id);\">");
                            sb.append("<label class=\"custom-control-label\" for=\"chk#" + count + "\" ></label>");
                        } else {
                            sb.append("<input type=\"checkbox\" class=\"mapTransaction custom-control-input toggle-action-section\" name=\"chkBox[]\" id=\"chk#" + count + "\" onclick=\"getMapTransaction(this.id);\" checked=\"true\">");
                            sb.append("<label class=\"custom-control-label\" for=\"chk#" + count + "\" ></label>");
                        }
                        sb.append("</div></td>");
                    }
//                    sb.append("<td class=\"text-center\"><div class=\"custom-control custom-checkbox action-check\">\n"
//                            + "<input type=\"checkbox\" class=\"mapTransaction custom-control-input toggle-action-section\" name=\"chkBox[]\" id=\"chk#" + count + "\" onclick=\"getMapTransaction(this.id);\">\n"
//                            + "<label class=\"custom-control-label\" for=\"chk#" + count + "\" ></label>\n"
//                            + "</div></td>");
//                    sb.append("<td class=\"text-center\"><input id=\"chk#" + count + "\"  name=\"checkAll\" id=\"chkAll\" onclick=\"getCheckedAll(this.id);\" type=\"checkbox\"></td>");
                    sb.append("<td class=\"text-center\">" + count + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getBank_branch_code()) ? "" : data.getBank_branch_code()) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getDeductee_panno()) ? "" : data.getDeductee_panno()) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getDeductee_ref_code1()) ? "" : data.getDeductee_ref_code1()) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getAccount_no()) ? "" : data.getAccount_no()) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getTds_section())? "" : data.getTds_section()) + "</td>");
                    sb.append("<td class=\"text-right\">" + (utl.isnull(data.getTran_amt()) ? "0.00" : utl.getAmountIndianFormat( Double.parseDouble(data.getTran_amt()))) + "</td>");
                    sb.append("<td class=\"text-right\" id=\"tdsAmtId#" + count + "\">" + (utl.isnull(data.getTds_amt()) ? "0.00" : utl.getAmountIndianFormat( Double.parseDouble(data.getTds_amt()))) + "</td>");
                    sb.append("<td class=\"text-right\">" + (utl.isnull(data.getTds_rate()) ? "0.00" : utl.getAmountIndianFormat( Double.parseDouble(data.getTds_rate()))) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getDeductee_name()) ? "" : data.getDeductee_name()) + "</td>");
                    sb.append("<td class=\"text-center\">" + (utl.isnull(data.getTran_ref_date()) ? "" : data.getTran_ref_date()) + "</td>");
                    sb.append("<td class=\"text-center\">" + (utl.isnull(data.getTds_deduct_date()) ? "" : data.getTds_deduct_date()) + "</td>");
                    sb.append("<td>" + (utl.isnull(data.getCertificate_no()) ? "" : data.getCertificate_no()) + "</td>");
//                    sb.append("<td>" + (data.getTds_deduct_reason_name() == null ? "" : data.getTds_deduct_reason_name()) + "</td>");
                    sb.append("</tr>");
                    
                }
                 } catch (Exception e) {
                     e.printStackTrace();
                    }

                double G_sum_tds = 0d;
                double G_sum_tran = 0d;
                double G_sum_rate = 0d;
                if (listtdstranData != null) {
                    try {
                        G_sum_tds = listtdstranData.getTds_amt();
                        G_sum_tran = listtdstranData.getTran_amt();
                        G_sum_rate = listtdstranData.getTds_rate();
                    } catch (Exception e) {
                    }
                }
                sb.append("</tbody>");
                sb.append("<tfoot>");
                sb.append("<tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
                sb.append("<td class=\"text-center\" title=\"Show Grand Total\">");
                sb.append("<img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\">");
                sb.append("</td>");
                sb.append("<td colspan=\"6\" class=\"text-right data-height font-weight-bold\"> Page Wise Total :</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">  " + utl.getAmountIndianFormat(totalTranAmt) + " </td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">" + utl.getAmountIndianFormat(totalAmt) + "</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\"> </td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
//                sb.append("<td></td>");
                sb.append("</tr>");
                sb.append("<tr class=\"highlight \" style=\"display:none;\">");
                sb.append("<td colspan=\"7\" class=\"text-right data-height font-weight-bold\">Grand Total :</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">" + utl.getAmountIndianFormat(G_sum_tran) + "</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">" + utl.getAmountIndianFormat(G_sum_tds) + "</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\"></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
//                sb.append("<td></td>");
                sb.append("</tr>");
                sb.append("</tfoot>");
                sb.append("</table>");
            }
        } catch (Exception e) {

        }

        return sb;
    }

}
