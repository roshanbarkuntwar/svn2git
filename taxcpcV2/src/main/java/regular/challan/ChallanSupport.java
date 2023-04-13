package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewTdsChallanTranLoad;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aniket.naik
 */
public class ChallanSupport {

    Util utl;
    Double tds_challan_tds_amt_sum = 0d;
    Double tds_challan_int_amt_sum = 0d;
    Double tds_challan_other_amt_sum = 0d;
    Double allocated_amount_sum = 0d;
    Double balance_to_allocate_sum = 0d;
    private Map<String, Object> session;

    public ChallanSupport() {
        utl = new Util();
    }

    public StringBuilder getChallanBrowseGrid(List<ChallanBrowseEntity> viewtdschallantranload, int startIndex, int pageNumber, ChallanFilterEntity tranChallanFltrSrch, String search, Assessment assessment,String client_code, ViewClientMast clientMast, boolean fromAllocatedChallan,String sumAmmount) {

        StringBuilder sb = new StringBuilder();
//        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//        ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
        String l_workinguser = clientMast.getClient_code();
        String prnNo = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tokenNoQuery = "select lhs_tds.get_tds_reco_details('" + clientMast.getEntity_code() + "', '" + l_workinguser + "', '" + assessment.getAccYear() + "', '" + Integer.parseInt(assessment.getQuarterNo()) + "',  to_date('" + sdf.format(assessment.getQuarterFromDate()) + "','dd-mm-rrrr'),  to_date('" + sdf.format(assessment.getQuarterToDate()) + "','dd-mm-rrrr'), '" + assessment.getTdsTypeCode() + "', '', 'LHSSYS_TDS_RETURN_TRAN', 'TOKEN_NO') from dual";
            prnNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(tokenNoQuery);
        } catch (Exception e) {

        }

        sb.append("  <table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">");
        sb.append("                    <thead>");
        sb.append("                    <th class=\"th-action text-center\" colspan=\"5\">Action</th>");
        sb.append("                    <th>  <div class=\"table-head-inner\">");
        sb.append("                            <div class=\"table-heading\">");
        sb.append("                                Sr.No.");
        sb.append("                                <hr>");
        sb.append("                                 Branch Code");
        sb.append("                            </div>");
        sb.append("                            <div class=\"sort-icon\">");
        sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
        sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
        sb.append("                            </div>");
        sb.append("                        </div></th>");
        sb.append("                    <th>");
        sb.append("                        <div class=\"table-head-inner\">");
        sb.append("                            <div class=\"table-heading\">");
        sb.append("                                 Tds Section");
        sb.append("                                <hr>");
        sb.append("                                BSR Code");
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
        sb.append("                                Challan Date");
        sb.append("                                <hr>");
        sb.append("                                Challan Number");
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
        sb.append("                                Challan Amount");
        sb.append("                                <hr>");
        sb.append("                                Interest Payment");
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
        sb.append("                                Others Payment");
        sb.append("                                <hr>");
        sb.append("                                Allocated To Deductee");
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
        sb.append("                                Pending for Allocation");
        sb.append("                                <hr>");
        sb.append("                                ");
        sb.append("                            </div>");
        sb.append("                     </div>");
        sb.append("               </th>");
        sb.append("      </thead>");
        
 
                                    

        if (viewtdschallantranload != null && viewtdschallantranload.size() > 0) {
            sb.append("<tbody>");
            int count = 0;
            for (ChallanBrowseEntity viewtdschlntrnload : viewtdschallantranload) {

                if (viewtdschlntrnload.getTds_challan_tds_amt() != null) {
                    tds_challan_tds_amt_sum += Double.parseDouble(viewtdschlntrnload.getTotal_tds_challan_amt());
                }
                if (viewtdschlntrnload.getTds_challan_int_amt() != null) {
                    tds_challan_int_amt_sum += Double.parseDouble(viewtdschlntrnload.getTds_challan_int_amt());
                }
                if (viewtdschlntrnload.getTds_challan_other_amt() != null) {
                    tds_challan_other_amt_sum += Double.parseDouble(viewtdschlntrnload.getTds_challan_other_amt());
                }
                if (viewtdschlntrnload.getAllocated_amount() != null) {
                    allocated_amount_sum += Double.parseDouble(viewtdschlntrnload.getAllocated_amount());
                }
                if (viewtdschlntrnload.getBalance_to_allocate() != null) {
                    balance_to_allocate_sum += Double.parseDouble(viewtdschlntrnload.getBalance_to_allocate());
                }
                String tds_section = utl.isnull(viewtdschlntrnload.getTds_section()) ? "" : viewtdschlntrnload.getTds_section();
                String tds_code = utl.isnull(viewtdschlntrnload.getTds_code()) ? "" : viewtdschlntrnload.getTds_code();
                String bankbsrCode = utl.isnull(viewtdschlntrnload.getBank_bsr_code()) ? "" : viewtdschlntrnload.getBank_bsr_code();
                String tdsChallanDate = utl.isnull(viewtdschlntrnload.getTds_challan_date()) ? "" : viewtdschlntrnload.getTds_challan_date();//System.out.println("tdsChallanDatetdsChallanDate-->"+tdsChallanDate);
                String tdschallanNumber = utl.isnull(viewtdschlntrnload.getTds_challan_no()) ? "" : viewtdschlntrnload.getTds_challan_no();
                String bankBranchCode = utl.isnull(viewtdschlntrnload.getBank_branch_code()) ? "" : viewtdschlntrnload.getBank_branch_code();
                try {
                    count++;
                    sb.append("<tr>");
                    sb.append("<td class=\"td-action text-center px-0\">");
                    if (!utl.isnull(clientMast.getEdit_right()) && clientMast.getEdit_right().equalsIgnoreCase("1") && utl.isnull(prnNo) && !fromAllocatedChallan) {
                        sb.append("<form action=\"getChallanEditPage\" method=\"post\" class=\"d-inline-block\">");
                        sb.append("<input type=\"hidden\" name=\"action\" value=\"Edit\"/>");
                        sb.append("<input type=\"hidden\" name=\"rowidSeq\" value=\"").append(viewtdschlntrnload.getRowid_seq()).append("\"/>");
                        sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\"><i class=\"fa edit text-primary cursor-pointer mr-1\" style=\"cursor: pointer;\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Edit\"></i></s:a>");
                        sb.append("</form>");
                    } else {
                        sb.append("<i class=\"fa fa-pencil\" aria-hidden=\"true\" title=\"Sorry ! You have not required privileges.\" style=\"color: black;\" data-toggle=\"tooltip\" data-placement=\"top\" ></i>");
                    }
                    sb.append("</td>");
                    sb.append("<td class=\"td-action text-center px-0\">");
                    if (!utl.isnull(clientMast.getQuery_right()) && clientMast.getQuery_right().equalsIgnoreCase("1") && utl.isnull(prnNo)) {
                        sb.append("<form action=\"getChallanEditPage\" method=\"post\" class=\"d-inline-block\">");
                        sb.append("<input type=\"hidden\" name=\"action\" value=\"View\"/>");
                        sb.append("<input type=\"hidden\" name=\"rowidSeq\" value=\"").append(viewtdschlntrnload.getRowid_seq()).append("\"/>");
                        sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\"><i class=\"fa view text-success cursor-pointer mr-1\" style=\"cursor: pointer;color: green;\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"View\"></i></s:a>");
                        sb.append("</form>");
                    } else {
                        sb.append("<i class=\"fa view\" aria-hidden=\"true\" title=\"Sorry ! You have not required privileges.\"  data-toggle=\"tooltip\" data-placement=\"top\" ></i>");
                    }
                    sb.append("</td>");
                    sb.append("<td class=\"td-action text-center px-0\">");
                    if (!utl.isnull(clientMast.getDelete_right()) && clientMast.getDelete_right().equalsIgnoreCase("1") && utl.isnull(prnNo) && !fromAllocatedChallan) {
                       // sb.append("<a onclick=\"deleteTDS('").append(viewtdschlntrnload.getRowid_seq()).append("');\" href=\"#\">");
                        
               // Double balance_to_allocate = viewtdschlntrnload.getTds_challan_tds_amt();
               // Double tds_challan_tds_amt = viewtdschlntrnload.getBalance_to_allocate();
                       boolean ammountFlag = false;// balance_to_allocate.equals(tds_challan_tds_amt);
                       if(ammountFlag){
                          sb.append("<a onclick=\"deleteTDS('").append(viewtdschlntrnload.getRowid_seq()).append("');\" href=\"#\">"); 
                       }else{
                           sb.append("<a onclick=\"unallocateAllocated()\" href=\"#\">");
                       }
                       
                        //sb.append("<a onclick=\"deleteTDS(").append(viewtdschlntrnload.getRowid_seq()+","+viewtdschlntrnload.getTds_challan_tds_amt()+","+viewtdschlntrnload.getBalance_to_allocate()).append(");\" href=\"#\">");
                        sb.append("<i class=\"fa delete text-danger cursor-pointer mr-1\" style=\"font-size:17px;\" title=\"Delete\"></i>");
                        sb.append("</a>");
                    } else {
                        sb.append("<i  class=\"fa fa-times\" aria-hidden=\"true\" title=\"Sorry ! You have not required privileges.\" style=\"color: black;\" data-toggle=\"tooltip\" data-placement=\"top\"></i> ");
                    }
                    sb.append("</td>");
                    sb.append("<td class=\"td-action text-center px-0\">");
                    if (!utl.isnull(clientMast.getAdd_right()) && clientMast.getAdd_right().equalsIgnoreCase("1") && utl.isnull(prnNo) && !fromAllocatedChallan) {
                        sb.append("<input type=\"hidden\" id=\"l_tds_code~").append(count).append("\" name=\"tds_code\" value=\"").append(tds_code).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_tds_challan_tds_amt~").append(count).append("\" name=\"tds_challan_tds_amt\" value=\"").append(viewtdschlntrnload.getTds_challan_tds_amt()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_rowid_seq~").append(count).append("\" name=\"rowid_seq\" value=\"").append(viewtdschlntrnload.getRowid_seq()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_allocated_amount~").append(count).append("\" name=\"allocated_amount\" value=\"").append(viewtdschlntrnload.getAllocated_amount()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_month~").append(count).append("\" name=\"month\" value=\"").append(viewtdschlntrnload.getMonth()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_tds_challan_date~").append(count).append("\" name=\"tds_challan_date\" value=\"").append(tdsChallanDate).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_tds_challan_no~").append(count).append("\" name=\"tds_challan_no\" value=\"").append(tdschallanNumber).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_pending_amt~").append(count).append("\" name=\"balance_to_allocate\" value=\"").append(viewtdschlntrnload.getBalance_to_allocate()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_int_pmt_amt~").append(count).append("\" name=\"tds_challan_int_amt\" value=\"").append(viewtdschlntrnload.getTds_challan_int_amt()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"l_other_payment_amt~").append(count).append("\" name=\"tds_challan_other_amt\" value=\"").append(viewtdschlntrnload.getTds_challan_other_amt()).append("\"/>");

                        sb.append("<a href=\"#\" id=\"mapDtn~").append(count).append("\" onclick=\"mapChallanAllocation(this.id);\"><div class=\"btn btn-info taxcpc-general-btn\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Map Deductee Transaction\" style=\"    \n"
                                + "                                 font-size: 10px;\n"
                                + "                                 padding-left: 3px;\n"
                                + "                                 padding-right: 2px;\n"
                                + "                                 padding-top: 1px;\n"
                                + "                                 padding-bottom: 0PX;\n"
                                + "                                 font-weight: bold;cursor: pointer;\">M</div></a>");
                    } else {

                        sb.append("<a href=\"#\" id=\"mapDtn~").append(count).append("\" ><div class=\"btn btn-info taxcpc-general-btn\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"You don't have rights to map deductee transaction !\" style=\"    \n"
                                + "                                 font-size: 10px;\n"
                                + "                                 padding-left: 3px;\n"
                                + "                                 padding-right: 2px;\n"
                                + "                                 padding-top: 1px;\n"
                                + "                                 padding-bottom: 0PX;\n"
                                + "                                 font-weight: bold;cursor: pointer;\">M</div></a>");

                    }
                    sb.append("</td>");
                    sb.append("<td class=\"td-action text-center px-0\">");
                    if (!utl.isnull(clientMast.getAdd_right()) && clientMast.getAdd_right().equalsIgnoreCase("1") && utl.isnull(prnNo) && !fromAllocatedChallan) {
                        sb.append("<form action=\"tdsTransactionEntry\" method=\"post\" class=\"d-inline-block\">");
                        sb.append("<input type=\"hidden\" name=\"action\" value=\"add\"/>");
                        sb.append("<input type=\"hidden\" name=\"mapFlag\" value=\"MAPTDSAMT\"/>");
                        sb.append("<input type=\"hidden\" name=\"mapChallanRowSeq\" value=\"").append(viewtdschlntrnload.getRowid_seq()).append("\"/>");
                        sb.append("<input type=\"hidden\" name=\"mapTDSChallanAmt\" value=\"").append(viewtdschlntrnload.getTds_challan_tds_amt()).append("\"/>");
                        sb.append("<input type=\"hidden\" name=\"tdsChallanDate\" value=\"").append(utl.isnull(viewtdschlntrnload.getTds_challan_date()) ? "" : viewtdschlntrnload.getTds_challan_date()).append("\"/>");
                        sb.append("<input type=\"hidden\" name=\"tdsChallanNo\" value=\"").append(utl.isnull(viewtdschlntrnload.getTds_challan_no()) ? "" : viewtdschlntrnload.getTds_challan_no()).append("\"/>");
                        sb.append("<s:a href=\"\" onclick=\"$(this).closest('form').submit();\"><i style=\"color:blue;font-size:17px;\" class=\"fa fa-plus-circle table-add-icon table-add-icon\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Add Deductee Transaction\"></i></s:a>");
                        sb.append("</form>");
                    } else {
                        sb.append("<i  class=\"fa fa-plus-circle table-add-icon\" title=\"Sorry ! You have not required privileges.\" style=\"color: black;\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\"></i> ");

                    }
                    sb.append("</td>");

                    sb.append("<td><div class=\"data-height font-weight-bold\">").append(count + startIndex).append("</div><hr>")
                            .append(utl.isnull(viewtdschlntrnload.getBank_branch_code()) ? "" : viewtdschlntrnload.getBank_branch_code()).append("</td>");
                    sb.append("<td><div class=\"data-height font-weight-bold\">").append(utl.isnull(viewtdschlntrnload.getTds_section()) ? "" : viewtdschlntrnload.getTds_section())
                            .append("</div><hr>").append(utl.isnull(viewtdschlntrnload.getBank_bsr_code()) ? "" : viewtdschlntrnload.getBank_bsr_code())
                            .append("</td>");
                    sb.append("<td><div class=\"data-height font-weight-bold\">").append(utl.isnull(viewtdschlntrnload.getTds_challan_date()) ? "" : viewtdschlntrnload.getTds_challan_date())
                            .append("</div><hr>").append(utl.isnull(viewtdschlntrnload.getTds_challan_no()) ? "" : viewtdschlntrnload.getTds_challan_no())
                            .append("</td>");
                    sb.append("<td class=\"text-right\"><div class=\"data-height font-weight-bold\">").append(utl.getAmountIndianFormat(Double.parseDouble(viewtdschlntrnload.getTds_challan_tds_amt())))
                            .append("</div><hr>").append(utl.getAmountIndianFormat(Double.parseDouble( viewtdschlntrnload.getTds_challan_int_amt())))
                            .append("</td>");
                    sb.append("<td class=\"text-right\"><div class=\"data-height font-weight-bold\">").append(utl.getAmountIndianFormat(Double.parseDouble( viewtdschlntrnload.getTds_challan_other_amt())))
                            .append("</div><hr>").append(utl.getIndianAmountFormat(Double.parseDouble( viewtdschlntrnload.getAllocated_amount())))
                            .append("</td>");
                    sb.append("<td class=\"text-right\"><div class=\"data-height font-weight-bold\">").append(utl.getAmountIndianFormat(Double.parseDouble(viewtdschlntrnload.getBalance_to_allocate())))
                            .append("</div></td>");
                    sb.append("</tr>");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            sb.append("<tfoot>");
            sb.append("<tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
            sb.append("<td class=\"text-center\" title=\"Show Grand Total\"><img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"></td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"7\">Page Wise Total :").append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tds_challan_tds_amt_sum)).append("<hr>").append(utl.getAmountIndianFormat(tds_challan_int_amt_sum)).append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(tds_challan_other_amt_sum)).append("<hr>").append(utl.getAmountIndianFormat(allocated_amount_sum)).append("</td>");
            sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(balance_to_allocate_sum)).append("</td>");
            sb.append("</tr>");

            Double G_tds_challan_tds_amt_sum = 0d;
            Double G_tds_challan_int_amt_sum = 0d;
            Double G_tds_challan_other_amt_sum = 0d;
            Double G_allocated_amount_sum = 0d;
            Double G_balance_to_allocate_sum = 0d;   // change long data type to double bcz its creates exception

            try {

     
                
                 if (sumAmmount != null) {
                                        try {
                                            String arr1 = sumAmmount.split("#")[1];
                                            arr1 = utl.isnull(arr1) ? "0" : arr1;
                                            G_tds_challan_tds_amt_sum = Double.parseDouble(arr1);
                                            String arr2 = sumAmmount.split("#")[2];
                                            arr2 = utl.isnull(arr2) ? "0" : arr2;
                                            G_tds_challan_int_amt_sum = Double.parseDouble(arr2);
                                            String arr3 = sumAmmount.split("#")[3];
                                            arr3 = utl.isnull(arr3) ? "0" : arr3;
                                            G_tds_challan_other_amt_sum = Double.parseDouble(arr3);
                                            String arr4 = sumAmmount.split("#")[4];
                                            arr4 = utl.isnull(arr4) ? "0" : arr4;
                                            G_allocated_amount_sum = Double.parseDouble(arr4);
                                            String arr5 = sumAmmount.split("#")[5];
                                            arr5 = utl.isnull(arr5) ? "0" : arr5;
                                            G_balance_to_allocate_sum = Double.parseDouble(arr5);
                                        } catch (Exception e) {
//                                        e.printStackTrace();
                                        }

                                    }
                sb.append("<tr class=\"highlight\" style=\"display:none;\">");
                sb.append("<td class=\"text-right data-height font-weight-bold\" colspan=\"8\">Grand Total :</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(G_tds_challan_tds_amt_sum)).append("<hr>").append(utl.getAmountIndianFormat(G_tds_challan_int_amt_sum)).append("</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(G_tds_challan_other_amt_sum)).append("<hr>").append(utl.getAmountIndianFormat(G_allocated_amount_sum)).append("</td>");
                sb.append("<td class=\"text-right data-height font-weight-bold\">").append(utl.getAmountIndianFormat(G_balance_to_allocate_sum)).append("</td>");
                sb.append("</tr>");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            sb.append("</tfoot>");
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb;
    }

}
