package prnInfo;

import globalUtilities.Util;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ayushi.jain
 */
public class PrnInfoSupport {

    public StringBuilder getBrowseGrid(List<LhssysTdsReturnTranEntity> entity, StringBuilder sb, int startIndex, int pageNo, String moduleFlag, Util utl,String BankBranchCode) {

        sb.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">             ");
        sb.append("                <thead>                ");
        sb.append("                    <tr>");
        sb.append("                        <th rowspan=\"2\" colspan=\"3\" class=\"text-center\">Action</th>           ");
        sb.append("                        <th rowspan=\"2\">"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Sr No.</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>           ");
        sb.append("                        <th rowspan=\"2\">"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">TAN No.</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>    ");
                sb.append("                        <th rowspan=\"2\">"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Branch Code</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>    ");

        // Columns only displays in 15GH or Correction module
        if (moduleFlag.equals("G")) {

            sb.append("                        <th rowspan=\"2\">"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">TDS Return Type</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>    ");
        }

        sb.append("                        <th colspan=\"3\" class=\"text-center\">File Upload Details</th>");

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
            sb.append("                        <th colspan=\"2\" class=\"text-center\">Challan Detail</th>");
            sb.append("                        <th colspan=\"2\" class=\"text-center\">Challan Details (Filed)</th>");

            sb.append("                        <th colspan=\"3\" class=\"text-center\">Deductee Details</th>");
            sb.append("                        <th colspan=\"3\" class=\"text-center\">Deductee Details (Filed)</th>");
        } else {
            sb.append("                        <th colspan=\"1\" class=\"text-center\">Deductee Details</th>");
            sb.append("                        <th colspan=\"1\" class=\"text-center\">Deductee Details (Filed)</th>");
        }

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
            sb.append("                        <th colspan=\"3\" class=\"text-center\">Deductee Details (Filed)  Pannotavbl/Paninvalid/Panapplied</th>");

            sb.append("                        <th colspan=\"3\" class=\"text-center\">Deductee Details (Error)</th>");
        } else {
            sb.append("                        <th colspan=\"1\" class=\"text-center\">Deductee Details (Error)</th>");
        }
        sb.append("                    </tr>");
        sb.append("                    <tr>");
        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Acknowledge No.</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");
        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Acknowledge Date</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");
        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Acknowledge PDF File</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Records</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Records (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
        }

        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Total Records</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {

            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Transaction Amount</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount Deducted</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
        }
        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Total Records (Filed)</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {

            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Transaction Amount (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount Deducted (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
        }

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Records (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Transaction Amount (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount Deducted (Filed)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
        }

        sb.append("                        <th>"
                + "<div class=\"table-head-inner\">"
                + "<div class=\"table-heading\">Total Records</div>"
                + "<div class=\"sort-icon\">"
                + "<i class=\"fa fa-sort-desc\"></i>"
                + "<i class=\"fa fa-sort-asc\"></i>"
                + "</div></div>"
                + "</th>");

        // Columns only displays in Regular or Correction module
        if (moduleFlag.equals("C") || moduleFlag.equals("R")) {

            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Transaction Amount</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
            sb.append("                        <th>"
                    + "<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS Amount Deducted</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>"
                    + "</th>");
        }
        sb.append("                    </tr>");
        sb.append("                </thead>  ");

        if (entity != null && entity.size() > 0) {

            sb.append("<tbody>");
            String tdsChallanAmt = "";
            String tranAmt = "";
            String tdsAmt = "";
            String tdsChallanRecAmt = "";
            String tranRecAmt = "";
            String tdsRecAmt = "";
            String tran_ret_pnotavbl_amt = "";
            String tds_ret_pnotavbl_amt = "";
            int count = 0;
            for (LhssysTdsReturnTranEntity data : entity) {
                count++;
                tdsChallanAmt = utl.isnull(data.getTds_challan_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getTds_challan_amt()));
                tranAmt = utl.isnull(data.getDeductee_tran_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tran_amt()));
                tdsAmt = utl.isnull(data.getDeductee_tds_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tds_amt()));
                tdsChallanRecAmt = utl.isnull(data.getTds_challan_return_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getTds_challan_return_amt()));
                tranRecAmt = utl.isnull(data.getDeductee_tran_return_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tran_return_amt()));
                tdsRecAmt = utl.isnull(data.getDeductee_tds_return_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tds_return_amt()));
                tran_ret_pnotavbl_amt = utl.isnull(data.getDeductee_tran_ret_pnotavbl_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tran_ret_pnotavbl_amt()));
                tds_ret_pnotavbl_amt = utl.isnull(data.getDeductee_tds_ret_pnotavbl_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(data.getDeductee_tds_ret_pnotavbl_amt()));

                Integer deducteeRecords = Integer.parseInt(!utl.isnull(data.getDeductee_rec()) ? data.getDeductee_rec() : "0");
                Integer deducteeReturnRecords = Integer.parseInt(!utl.isnull(data.getDeductee_return_rec()) ? data.getDeductee_return_rec() : "0");
                Integer deducteePnaRecords = Integer.parseInt(!utl.isnull(data.getDeductee_ret_pnotavbl_rec()) ? data.getDeductee_ret_pnotavbl_rec() : "0");
                Integer deducteeErrorRecords = deducteeRecords - (deducteeReturnRecords + deducteePnaRecords);
                sb.append("                    <tr>");
                sb.append("                        <td>                               ");
                sb.append("                            <i class=\"fa edit text-primary cursor-pointer mr-1\" tile=\"Update Record\" data-toggle=\"collapse\" data-target=\"#O" + (count) + "\"></i>");
                sb.append("                        </td>                               ");
                sb.append("                        <td>                               ");
                sb.append("                            <i class=\"fa download cursor-pointer mr-1 " + (!utl.isnull(data.getFile_upload_ack_pdf_name()) ? "text-primary" : "") + "\" "
                        + " title=\"" + (!utl.isnull(data.getFile_upload_ack_pdf_name()) ? "Download Pdf" : "File not available") + "\" onclick=\"downloadPrn('updatePrnForm~" + (count) + "');\">");
                sb.append("                            <form id=\"updatePrnForm~" + (count) + "\" action=\"updatePrnAction\" method=\"post\" enctype=\"multipart/form-data\">");
                sb.append("                            <input type=\"hidden\" name=\"action\" value=\"download\"> "
                        + "                            <input type=\"hidden\" name=\"rowid\" value=\"" + data.getRowid_seq() + "\"></form></i>");
                sb.append("                        </td>                               ");
                sb.append("                        <td>                               ");

                sb.append("                            <i class=\"fa revert revert-correction cursor-pointer mr-1\" title=\"Revert Record\" onclick=\"deletePrn('deletePrnForm~" + (count) + "');\">"
                        + "                            <form id=\"deletePrnForm~" + (count) + "\" action=\"updatePrnAction\" method=\"post\"> "
                        + "                    <input type=\"hidden\" name=\"action\" value=\"delete\"> "
                        + "                    <input type=\"hidden\" name=\"rowid\" value=\"" + data.getRowid_seq() + "\"></form></i>");
                sb.append("                        </td>");
                sb.append("                        <td>" + data.getSlno() + "</td>");
                sb.append("                        <td>" + data.getTanno() + "</td>");
                sb.append("                        <td>" + data.getBank_branch_code() + "</td>");

                // Columns only displays in 15GH or Correction module
                if (moduleFlag.equals("G")) {

                    sb.append("                        <td>" + data.getTds_return_type() + "</td>");
                }

                sb.append("                        <td>" + data.getFile_upload_ack_no() + "</td>");
                sb.append("                        <td>" + data.getFile_upload_ack_date() + "</td>");
                sb.append("                        <td title=\"" + data.getFile_upload_ack_pdf_name() + "\">" + data.getFile_upload_ack_pdf_name() + "</td>");

                // Columns only displays in Regular or Correction module
                if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
                    sb.append("                        <td>" + data.getTds_challan_rec() + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tdsChallanAmt + "</td>");
                    sb.append("                        <td>" + (utl.isnull(data.getTds_challan_return_rec()) ? "0" : data.getTds_challan_return_rec()) + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tdsChallanRecAmt + "</td>");
                }

                sb.append("                        <td>" + (utl.isnull(data.getDeductee_rec()) ? "0" : data.getDeductee_rec()) + "</td>");
                // Columns only displays in Regular or Correction module
                if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
                    sb.append("                        <td class=\"text-right\" >" + tranAmt + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tdsAmt + "</td>");
                }
                sb.append("                        <td >" + (utl.isnull(data.getDeductee_return_rec()) ? "0" : data.getDeductee_return_rec()) + "</td>");

                // Columns only displays in Regular or Correction module
                if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
                    sb.append("                        <td class=\"text-right\" >" + tranRecAmt + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tdsRecAmt + "</td>");
                }

                // Columns only displays in Regular or Correction module
                if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
                    sb.append("                        <td>" + (utl.isnull(data.getDeductee_ret_pnotavbl_rec()) ? "0" : data.getDeductee_ret_pnotavbl_rec()) + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tran_ret_pnotavbl_amt + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + tds_ret_pnotavbl_amt + "</td>");
                }

                sb.append("                        <td>" + deducteeErrorRecords + "</td>");

                // Columns only displays in Regular or Correction module
                if (moduleFlag.equals("C") || moduleFlag.equals("R")) {
                    sb.append("                        <td class=\"text-right\" >" + getErrorAmount(data.getDeductee_tran_amt(), data.getDeductee_tran_return_amt(), data.getDeductee_tran_ret_pnotavbl_amt(), utl) + "</td>");
                    sb.append("                        <td class=\"text-right\" >" + getErrorAmount(data.getDeductee_tds_amt(), data.getDeductee_tds_return_amt(), data.getDeductee_tds_ret_pnotavbl_amt(), utl) + "</td>");
                }

                sb.append("                    </tr>");
                sb.append("                    <tr id=\"O" + (count) + "\" class=\"collapse\">");
                sb.append("                    <input type=\"hidden\" id=\"fvuFileGeneratedFlag~" + count + "\" value= \"" + data.getFvu_file_generated_flag() + "\"/>");
                sb.append("                        <td colspan=\"14\">");
                sb.append("                        <form class=\"w-75\" id=\"updatePrnFormupdate~" + (count) + "\" action=\"updatePrnAction\" method=\"post\" enctype=\"multipart/form-data\">");
                sb.append("                        <input type=\"hidden\" name=\"action\" value=\"update\">");
                sb.append("                        <input type=\"hidden\" name=\"rowid\" value=\"" + data.getRowid_seq() + "\">");
                sb.append("                            <div class=\"form-group\">");
                sb.append("                                <div class=\"row\">");
                sb.append("                                    <div class=\"col-md-2\">");
                sb.append("                                        <input type=\"text\" name=\"file_upload_ack_no\" value=\"\" id=\"file_upload_ack_no~" + (count) + "\" class=\"form-control\"  placeholder=\"File Upload Ack. No.\">");
                sb.append("                                    </div>");
                sb.append("                                    <div class=\"col-md-2\"> <input type=\"text\" name=\"file_upload_ack_date\" value=\"\" id=\"file_upload_ack_date~" + (count) + "\" class=\"form-control\" onmouseover=\"openCalendar(this.id,this.id);\" readonly=\"true\" placeholder=\"DD-MM-YYYY\"></div>");
                sb.append("                                    <div class=\"col-md-4\">");
                sb.append("                                        <div class=\"custom-file\">");
                sb.append("                                            <input type=\"file\" class=\"custom-file-input\" name=\"prnFile\" id=\"prnFile~" + (count) + "\" accept=\"application/pdf\""
                        + "                                             onchange=\"$(this).next('.custom-file-label').html(this.files[0].name);\">");
                sb.append("                                            <label class=\"custom-file-label\" for=\"customFile\">Choose file</label>");
                sb.append("                                        </div>");
                sb.append("                                    </div>");
                sb.append("                                    <div class=\"col-md-2\"><button type=\"button\"  class=\"btn btn-primary addon-btn filter-inner-btn\" title=\"Update and Upload PRN File\" onclick=\"updatePrnNo('updatePrnFormupdate~" + (count) + "');\"><i class=\"fa fa-upload\"></i></button></div>");
                sb.append("                                </div>");
                sb.append("                            </div></form>");
                sb.append("                        </td>");
                sb.append("                    </tr>");
            }
            sb.append("                </tbody>");
        }
        sb.append("                </table>  ");
        return sb;
    }

    public String getErrorAmount(String total, String record1, String record2, Util utl) {
        Double deducteeRecords = Double.parseDouble(!utl.isnull(total) ? total : "0");
        Double deducteeReturnRecords = Double.parseDouble(!utl.isnull(record1) ? record1 : "0");
        Double deducteePnaRecords = Double.parseDouble(!utl.isnull(record2) ? record2 : "0");
        Double finalErrorRecords = deducteeRecords - (deducteeReturnRecords + deducteePnaRecords);
        String finalErrorRecordsAmount = utl.getAmountIndianFormat(finalErrorRecords);

        return finalErrorRecordsAmount;

    }
}
