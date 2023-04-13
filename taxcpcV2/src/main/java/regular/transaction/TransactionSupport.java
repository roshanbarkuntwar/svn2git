/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class TransactionSupport {

    Util utl = new Util();

    public StringBuilder getBrowseGrid(List<TransactionBrowseEntity> entity, ViewClientMast clientmast, Assessment asst, StringBuilder sb, int startindex, int pageNo, double grossTran, double grossTds, String flag,String l_query) {
        try {
            String l_workinguser = clientmast.getClient_code();
            String prnNo = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tokenNoQuery = "select lhs_tds.get_tds_reco_details('" + clientmast.getEntity_code() + "', '" + l_workinguser + "', '" + asst.getAccYear() + "', '" + Integer.parseInt(asst.getQuarterNo()) + "',  to_date('" + sdf.format(asst.getQuarterFromDate()) + "','dd-mm-rrrr'),  to_date('" + sdf.format(asst.getQuarterToDate()) + "','dd-mm-rrrr'), '" + asst.getTdsTypeCode() + "', '', 'LHSSYS_TDS_RETURN_TRAN', 'TOKEN_NO') from dual";
            prnNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(tokenNoQuery);
        } catch (Exception e) {

        }
        
            utl.generateLog("Grid creation started..........", "");
            int colspan = 6;
            sb.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            sb.append("                    <thead>");
            sb.append("                          <tr>");
            
            sb.append("                     <th class=\"th-action check-action text-center\" >   ");
             if (!utl.isnull(clientmast.getEdit_right()) && clientmast.getEdit_right().equalsIgnoreCase("1") && utl.isnull(prnNo)) {
                  
            sb.append("<input type=\"checkbox\" id=\"checkAll\"  title=\"Select All\" onclick=\"setAllChecked(this)\" />");
            }else{
             sb.append("<input type=\"checkbox\" id=\"checkAll\"  title=\"Sorry ! You have not required privileges.\" disabled=\"true\"  />");   
            }
            sb.append("</th>");
            if (!utl.isnull(flag) && flag.equalsIgnoreCase("T")) {
                colspan = 6;

                sb.append("                    <th class=\"\">  <div class=\"table-head-inner\">");
                sb.append("                            <div class=\"table-heading\">");
                sb.append("                               Acc Year # Quarter No.");
                sb.append("                                <hr>");
                sb.append("                               Tds Type Code");
                sb.append("                            </div>");
                sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
                sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
                sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
                sb.append("                            </div>");
                sb.append("                        </div></th>");
            }

            sb.append("                    <th class=\"seq-no\">  <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Sr. No.");
            sb.append("                                <hr>");
            sb.append("                               Tran. Seq");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div></th>");
            sb.append("                    <th class=\"branch\">  <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Branch Code");
            sb.append("                                <hr>");
            sb.append("                               Challan Branch");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div></th>");
            sb.append("                    <th class=\"deducteeRef\">");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Deductee Ref.");
            sb.append("                                <hr>");
            sb.append("                                Account No.");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th>");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                PAN No.");
            sb.append("                                <hr>");
            sb.append("                                Deductee Name");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th class=\"transactionDate\">");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Transaction Date");
            sb.append("                                <hr>");
            sb.append("                                Deduction Date");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th class=\"section\">");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Section");
            sb.append("                                <hr>");
            sb.append("                                Rate");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th class=\"transactionAmt\">");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                                Transaction Amt.");
            sb.append("                                <hr>");
            sb.append("                                TDS Amt.");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            sb.append("                    <th class=\"certificateNo\">");
            sb.append("                        <div class=\"table-head-inner\">");
            sb.append("                            <div class=\"table-heading\">");
            sb.append("                               Certificate No.");
            sb.append("                                <hr>");
            sb.append("                                TDS Reason ");
            sb.append("                            </div>");
            sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
            sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
            sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
            sb.append("                            </div>");
            sb.append("                        </div>");
            sb.append("                    </th>");
            if (asst.getTdsTypeCode().equalsIgnoreCase("27EQ")) {
                sb.append("                    <th class=\"certificateNo\">");
                sb.append("                        <div class=\"table-head-inner\">");
                sb.append("                               <hr> ");
                sb.append("                                 Party Bill Amount ");
                sb.append("                            <div class=\"sort-icon\" title=\"Sorting\">");
                sb.append("                                <i class=\"fa fa-sort-desc\"></i> ");
                sb.append("                                <i class=\"fa fa-sort-asc\"></i>");
                sb.append("                            </div>");
                sb.append("                        </div>");
                sb.append("                    </th>");
            }
            sb.append("                    </tr>");
            sb.append("                    </thead>");
            utl.generateLog("Sum started...", "");
            double sum_tranamt = 0d;
            double sum_tds = 0d;
            if (entity != null) {
                try {
                    for (TransactionBrowseEntity sumtdsTran : entity) {
                        if (sumtdsTran.getTds_amt() != null) {
                            if (!utl.isnull(sumtdsTran.getTds_amt())) {
                                sum_tds += Double.parseDouble(sumtdsTran.getTds_amt());
                            }
                            if (!utl.isnull(sumtdsTran.getTran_amt())) {
                                sum_tranamt += Double.parseDouble(sumtdsTran.getTran_amt());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            utl.generateLog("Sum end...", "");
            if (entity != null && entity.size() > 0) {
                sb.append("                    <tbody>");
                int count = 0;
                for (TransactionBrowseEntity tranEntity : entity) {
                    count++;
                    sb.append("                        <tr>");
                    if (!utl.isnull(clientmast.getEdit_right()) && clientmast.getEdit_right().equalsIgnoreCase("1") && utl.isnull(prnNo)) {
                    sb.append("                          <td class=\"td-action\">\n"
                            + "                                <div class=\"custom-control custom-checkbox action-check \">\n"
                            + "                                    <input type=\"checkbox\" class=\"custom-control-input toggle-action-section\"   id=\"check~" + count + "\"  onclick=\"openActionDiv(this.id,'" + tranEntity.getRowid_seq() + "');\">\n"
                            + "                                    <label class=\"custom-control-label\" for=\"check~" + count + "\" ></label>\n"
                            + "                                </div>\n"
                            + "                            ");
                    
                   
                        sb.append("<form action=\"tdsTransactionEntry\" method=\"post\" id=\"editForm~" + count + "\" >");
                        sb.append("<input type=\"hidden\" name=\"action\" value=\"edit\"/>");
                        sb.append("<input type=\"hidden\" name=\"rowid_seq\" value=\"").append(this.utl.isnull(tranEntity.getRowid_seq()) ? "" : tranEntity.getRowid_seq()).append("\"/>");
                        sb.append("<input type=\"hidden\" id=\"checkedfordelete~" + count + "\" name=\"checkedfordelete\" value=\"").append(utl.isnull(tranEntity.getRowid_seq()) ? "" : tranEntity.getRowid_seq()).append("\"/>");
                        sb.append("<input type=\"hidden\" name=\"mapChallanRowSeq\" value=\"").append(utl.isnull(tranEntity.getTds_challan_rowid_seq()) ? "" : tranEntity.getTds_challan_rowid_seq()).append("\"/>");
                        sb.append("<input type=\"hidden\" name=\"mapTDSChallanAmt\" value=\"").append(utl.isnull(tranEntity.getTds_challan_tds_amt()) ? "" : tranEntity.getTds_challan_tds_amt()).append("\"/>");
                        sb.append("</form>");

                    }else{
                        sb.append(" <td class=\"td-action\" title=\"Sorry ! You have not required privileges.\">\n"
                            + "                                <div class=\"custom-control custom-checkbox action-check \">\n"
                            + "                                    <input type=\"checkbox\" class=\"custom-control-input toggle-action-section\" >\n"
                            + "                                    <label class=\"custom-control-label\" ></label>\n"
                            + "                                </div>\n"
                            + "                            ");
                    }
                    sb.append("<form action=\"tdsTransactionEntry\" method=\"post\" id=\"viewForm~" + count + "\">");
                    sb.append("<input type=\"hidden\" name=\"action\" value=\"view\"/>");
                    sb.append("<input type=\"hidden\" name=\"rowid_seq\" value=\"").append(utl.isnull(tranEntity.getRowid_seq()) ? "" : tranEntity.getRowid_seq()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"mapChallanRowSeq\"  id=\"mapChallanRowSeq~" + count + "\" value=\"").append(utl.isnull(tranEntity.getTds_challan_rowid_seq()) ? "" : tranEntity.getTds_challan_rowid_seq()).append("\"/>");
                    sb.append("<input type=\"hidden\" name=\"mapTDSChallanAmt\" value=\"").append(utl.isnull(tranEntity.getTds_challan_tds_amt()) ? "" : tranEntity.getTds_challan_tds_amt()).append("\"/>");
                    sb.append("</form>");
                    sb.append("                            </td>");
                    if (!utl.isnull(flag) && flag.equalsIgnoreCase("T")) {
                        sb.append("                            <td> <div class=\"data-height font-weight-bold\"> " + tranEntity.getAcc_year() + "# Q" + tranEntity.getQuarter_no() + "</div> <hr> <div class=\"data-height\">" + tranEntity.getTds_type_code() + "</div> </td>");
                    }
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\"> " + tranEntity.getSlno() + "</div> <hr> <div class=\"data-height\">" + tranEntity.getRowid_seq() + "</div> </td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getBank_branch_code() + "");
                    sb.append("                                </div><hr> <div class=\"data-height\">");
                    sb.append("                                " + tranEntity.getVALIDATION_BANK_BRANCH_CODE()+ "");
                    sb.append("                            </div></td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getDeductee_ref_code1() + "");
                    sb.append("                                </div><hr> <div class=\"data-height\">");
                    sb.append("                                " + tranEntity.getAccount_no() + "");
                    sb.append("                            </div></td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getDeductee_panno() + "");
                    sb.append("                                </div><hr> <div class=\"data-height\">");
                    sb.append("                                " + tranEntity.getDeductee_name() + "");
                    sb.append("                           </div> </td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getTran_ref_date() + "");
                    sb.append("                                </div><hr> <div class=\"data-height\">");
                    sb.append("                                " + tranEntity.getTds_deduct_date() + "");
                    sb.append("                           </div> </td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getTds_section() + "");
                    sb.append("                               </div> <hr> <div class=\"data-height text-right\">");

                    try {
                        sb.append("                                " + (utl.isnull(tranEntity.getTds_rate()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(tranEntity.getTds_rate()))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sb.append("                            </div></td>");
                    sb.append("                            <td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
                    try {
                        sb.append("                                " + (utl.isnull(tranEntity.getTran_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(tranEntity.getTran_amt()))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sb.append("                               </div> <hr> <div class=\"data-height\">");
                    try {
                        sb.append("                                " + (utl.isnull(tranEntity.getTds_amt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(tranEntity.getTds_amt()))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sb.append("                            </div></td>");
                    sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                    sb.append("                                " + tranEntity.getCertificate_no() + "");
                    sb.append("                               </div> <hr> <div class=\"data-height\">");
                    sb.append("                                " + tranEntity.getTds_deduct_reason() + " - " + tranEntity.getTds_deduct_reason_name());
                    sb.append("                           </div> </td>");
                    if (asst.getTdsTypeCode().equalsIgnoreCase("27EQ")) {
                        sb.append("                            <td> <div class=\"data-height font-weight-bold\">");
                        sb.append("                               </div> <hr> <div class=\"data-height text-right \">");
                        sb.append("                                " + (utl.isnull(tranEntity.getPartybillamt()) ? "0.00" : utl.getAmountIndianFormat(Double.parseDouble(tranEntity.getPartybillamt()))));
                    }
                    sb.append("                           </div> </td>");
                    sb.append("                        </tr>");
                }
                  sb.append("<input type=\"hidden\" id=\"no_del_count\" value=\"").append(count).append("\"/>");
                sb.append("                       ");
                sb.append("                    </tbody>");
                
                sb.append("                    <tfoot>");
                sb.append("                        <tr class=\"highlight header1\" onclick=\"collapseOn(this);\">");
                sb.append("                          <td  class=\"text-center\"  title=\"Show Grand Total\"> <img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"> </td>");
                sb.append("                            <td colspan=\"" + colspan + "\" class=\"text-right data-height font-weight-bold\">Page Wise Total :</td>");
                sb.append("                            <td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
                try {
                    sb.append(sum_tranamt == 0.0 ? "0.00" : utl.getAmountIndianFormat(sum_tranamt));
                } catch (Exception e) {
                    e.addSuppressed(e);
                }
                sb.append("</div> <hr> <div class=\"data-height font-weight-bold\"> ");
                try {
                    sb.append(sum_tds == 0.0 ? "0.00" : utl.getAmountIndianFormat(sum_tds));
                } catch (Exception e) {
                    e.addSuppressed(e);
                }
                sb.append(" </div> </td>");
                sb.append("                            <td></td>");
                if (asst.getTdsTypeCode().equalsIgnoreCase("27EQ")) {
                    sb.append("<td></td>");
                }
                sb.append("                        </tr>");
                sb.append("                        <tr class=\"highlight \" style=\"display:none;\">");
                sb.append("                            <td colspan=\"" + (colspan + 1) + "\" class=\"text-right data-height font-weight-bold\">Grand Total :</td>");
                sb.append("                            <td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
                try {
                    sb.append(grossTran == 0.0 ? "0.00" : utl.getAmountIndianFormat(grossTran));
                } catch (Exception e) {
                    e.addSuppressed(e);
                }

                sb.append("</div><hr> <div class=\"data-height font-weight-bold\"> ");
                try {
                    sb.append(grossTds == 0.0 ? "0.00" : utl.getAmountIndianFormat(grossTds));
                } catch (Exception e) {
                    e.addSuppressed(e);
                }
                sb.append("</div></td>");
                sb.append("                            <td></td>");
                sb.append("                            <td></td>");
                sb.append("                        </tr>");
                sb.append("                    </tfoot>");
            }
          
           
        //      System.out.println("l_queryl_query-"+whereClause[1]); 
        // sb.append("<textarea type=\"hidden\" name=\"whereClause\" id=\"whereClause\" value=\"").append(whereClause[1]).append("\"/>").append("</textarea>");////cssStyle=\"visibility:hidden\">
        
        
        sb.append("</table>");
        String whereClause[] =l_query.split("a.tds_tran_corr_approveddate");
        String clause=" WHERE SLNO BETWEEN";
        String whereClause2[] = whereClause[1].split(clause);
        String x=whereClause2[0].substring(0, whereClause2[0].lastIndexOf(")")); 
        sb.append("<div style=\"display:none\">");
        sb.append("<textarea id=\"whereClause\">").append(x).append("</textarea>");
        sb.append("</div>");  
          
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;

    }

}
