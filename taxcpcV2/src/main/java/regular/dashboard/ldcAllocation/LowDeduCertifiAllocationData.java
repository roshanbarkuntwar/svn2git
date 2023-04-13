/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.ldcAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class LowDeduCertifiAllocationData extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String returnView = "success";
        String returnMsg = "error";
        try {

            if (utl.isnull(getAllocatedFlag())) {
                setAllocatedFlag("");
            }

            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                    if (getTotalRecords() > 0 && getRecordsPerPage() > 0 && getPageNumber() > 0) {

                        int maxVal = getTotalRecords();
                        int minVal = 1;

                        if (getTotalRecords() > getRecordsPerPage()) {
                            maxVal = getPageNumber() * getRecordsPerPage();
                            minVal = maxVal - getRecordsPerPage() + 1;
                            if (maxVal > getTotalRecords()) {

                                maxVal = getTotalRecords();
                            }

                        }
                        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

                        ViewClientMast clientmast = (ViewClientMast) session.get("WORKINGUSER");
                        Assessment asmt = (Assessment) session.get("ASSESSMENT");

                        String acc_year = asmt.getAccYear();
                        String quarterNo = asmt.getQuarterNo();
                        String tds_type_code = asmt.getTdsTypeCode();

                        if (clientmast != null) {
                            String filter_whrClause = "";
                            if (!utl.isnull(getAllocatedFlag())) {
                                if (getAllocatedFlag().equalsIgnoreCase("notAllocated")) {
                                    filter_whrClause += "--Not allocated\n ";
                                    filter_whrClause += "AND NVL(T.AMOUNT_CONSUMED, 0) = 0\n ";

                                } else if (getAllocatedFlag().equalsIgnoreCase("allocated")) {
                                    filter_whrClause += "--Allocated\n ";
                                    filter_whrClause += "AND NVL(T.AMOUNT_CONSUMED, 0) > 0\n ";
                                } else if (getAllocatedFlag().equalsIgnoreCase("notAllocatedAFlag")){
                            
                                    //System.out.println("getAllocatedFlag()--"+getAllocatedFlag());
                               }
                            }
                            if (!utl.isnull(getCertificateNo())) {
                                filter_whrClause += "AND T.CERTIFICATE_NO = '" + getCertificateNo() + "'\n ";
                            }
                            if (!utl.isnull(getDeducteePanNo())) {
                                filter_whrClause += "AND T.DEDUCTEE_PANNO = '" + getDeducteePanNo() + "'\n ";
                            }

                            entityList = new LowDeduCertifiAllocationSuppo().getLowDeduCertifiAllocaBean_List(clientmast.getEntity_code(), clientmast.getClient_code(), acc_year, quarterNo, tds_type_code, filter_whrClause, utl, getRecordsPerPage(), getPageNumber(),getAllocatedFlag());
                            returnMsg = getDataGridNew(entityList, factory, asmt,getAllocatedFlag());
                            returnView = "stringBuilder";
                        }
                    } else {
                        returnMsg = GlobalMessage.PAGINATION_NO_RECORDS;
                    }
                } else if (getSetRecPerPage() > 0) {
                    session.put("LOWDEDUCTEERECPERPG", String.valueOf(getSetRecPerPage()));// records per page
                    returnMsg = "success";
                    returnView = "input_success";
                }
            } else {
                returnMsg = GlobalMessage.PAGINATION_SHOW_MORE;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnView;

    }

    public String getDataGridNew(ArrayList<LowDeduCertifiAllocaBean> entityList, DAOFactory factory, Assessment asmt,String allocatedflag ) {
        StringBuilder sb = null;
        int head = 0;
        int indexCount = 0;

        SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat display_date_format = new SimpleDateFormat("dd-MMM-yyyy");

        try {

            if (entityList != null && entityList.size() > 0) {

                sb = new StringBuilder();

                if(allocatedflag.equalsIgnoreCase("notAllocatedAFlag")){
                    
                   
                    sb.append("<table class=\"table table-striped\" id=\"\" style=\"margin-bottom: 0px; width: 100%; font-size: 14px;\"> \n");
                sb.append("        <thead>           \n");
                sb.append("            <tr class=\"text-center\">           \n");
                sb.append("                 <th class=\"text-center\" colspan=\"\">\n");
//                sb.append("                 <input type=\"checkbox\" id=\"checkAll\" value=\"All\" onclick=\"selectAllRow();\" style=\"opacity:1; left:50px;\"> \n");

                sb.append("<div class=\"custom-control custom-checkbox action-check text-center\">")
                        .append(" <input type=\"checkbox\" id=\"checkAll\"")
                        .append("class=\"custom-control-input toggle-action-section\"")
                        .append("onclick=\"selectAllRow(); openActionDiv();\">\n")
                        .append(" <label class=\"custom-control-label\" for=\"checkAll\"></label>\n")
                        .append("</div>");
//                sb.append("                 </th> \n");
//                sb.append("                 <th>Action<br>\n");
//                sb.append("                 </th> \n");
                sb.append("                 <th>Sr.No.<br>\n");
                sb.append("                 </th> \n");
                 sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + " Bank Branch Code"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Challan Bank Branch Code"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Deductee PAN NO."
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Deductee Name"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Deductee Ref Code1"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Tran Ref No."
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Tran Ref Date"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Tran Amt."
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Amt"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Section"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Deduct Reason"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Certificate No."
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                
//                sb.append("<th class=\"text-center\">Action</th> \n");
                sb.append("            </tr> \n");
                sb.append("        </thead> \n");
                sb.append("        <tbody> \n");
//                TdsSplRateMast tdsSplRateMast = null;
                for (LowDeduCertifiAllocaBean entity : entityList) {
                    head++;

                    sb.append("                 \n");
                    sb.append("                <tr class=\"text-center\"> \n");
                    sb.append("                    <td style=\"\"> \n");
//                    sb.append("                       <input type=\"checkbox\" id=\"checkBox~" + head + "\" name=\"beanList[" + (indexCount) + "].checkBoxSelect\" class=\"forSelectAll\" value=\"A\" style=\"opacity:1; left:50px;\"> \n");
                    sb.append("<div class=\"custom-control custom-checkbox action-check text-center\">")
                            .append(" <input type=\"checkbox\" id=\"checkBox~" + head + "\" name=\"beanList[" + (indexCount) + "].checkBoxSelect\" ")
                            .append("class=\"custom-control-input toggle-action-section forSelectAll\"")
                            .append("onclick=\"openActionDiv();\">\n")
                            .append(" <label class=\"custom-control-label\" for=\"checkBox~" + head + "\"></label>\n")
                            .append("</div>");
                    sb.append("                    </td> \n");
//                    sb.append("                    <td> \n");
//                    sb.append("<i class=\"fa view text-success cursor-pointer mr-1\" title=\"View Certificate Details\" onclick=\"showCertificateList('").append(head).append("');\"></i>");
//                    sb.append("                    </td> \n");
                    sb.append("<td> \n");
                    sb.append(indexCount + 1);
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getBank_branch_code()) ? entity.getBank_branch_code() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getChallan_bank_branch_code()) ? entity.getChallan_bank_branch_code() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                     sb.append(!utl.isnull(entity.getDeductee_panno()) ? entity.getDeductee_panno() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                     sb.append(!utl.isnull(entity.getDeductee_name()) ? entity.getDeductee_name() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getDeductee_ref_code1()) ? entity.getDeductee_ref_code1() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTran_ref_no()) ? entity.getTran_ref_no() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTran_ref_date()) ? entity.getTran_ref_date() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTran_amt()) ? entity.getTran_amt() : "");
                    sb.append("</td> \n");
                    sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTds_amt()) ? entity.getTds_amt() : "");
                    sb.append("</td> \n");
                     sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTds_section()) ? entity.getTds_section() : "");
                    sb.append("</td> \n");
                     sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getTds_deduct_reason()) ? entity.getTds_deduct_reason() : "");
                    sb.append("</td> \n");
                     sb.append("<td> \n");
                    sb.append(!utl.isnull(entity.getCertificate_no()) ? entity.getCertificate_no() : "");
                    sb.append("</td> \n");
                    sb.append(" <input type=\"hidden\" id=\"rowidseqid~" + head + "\" name=\"beanList[" + indexCount + "].rowid_seq\" value=\"" + entity.getRowid_seq() + "\" > \n");
                   
                    indexCount++;

                }//End For
                sb.append("        </tbody> \n");
                sb.append("    </table> \n");

                
                    
                    
                }else{
                
                sb.append("<table class=\"table table-striped\" id=\"\" style=\"margin-bottom: 0px; width: 100%; font-size: 14px;\"> \n");
                sb.append("        <thead>           \n");
                sb.append("            <tr class=\"text-center\">           \n");
                sb.append("                 <th class=\"text-center\" colspan=\"\">\n");
//                sb.append("                 <input type=\"checkbox\" id=\"checkAll\" value=\"All\" onclick=\"selectAllRow();\" style=\"opacity:1; left:50px;\"> \n");

                sb.append("<div class=\"custom-control custom-checkbox action-check text-center\">")
                        .append(" <input type=\"checkbox\" id=\"checkAll\"")
                        .append("class=\"custom-control-input toggle-action-section\"")
                        .append("onclick=\"selectAllRow(); openActionDiv();\">\n")
                        .append(" <label class=\"custom-control-label\" for=\"checkAll\"></label>\n")
                        .append("</div>");
//                sb.append("                 </th> \n");
//                sb.append("                 <th>Action<br>\n");
//                sb.append("                 </th> \n");
                sb.append("                 <th>Sr.No.<br>\n");
                sb.append("                 </th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Certificate No"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Valid From"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Valid Upto"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Deductee PAN No"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Section"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Rate"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "TDS Limit Amt."
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Amount Consumed"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
                sb.append("                <th>"
                        + "<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">"
                        + "Balance Limit"
                        + "</div>"
                        + "<div class=\"sort-icon\">"
                        + " <i class=\"fa fa-sort-desc\"></i>"
                        + " <i class=\"fa fa-sort-asc\"></i>"
                        + "</div>"
                        + "</div>"
                        + "</th> \n");
//                sb.append("<th class=\"text-center\">Action</th> \n");
                sb.append("            </tr> \n");
                sb.append("        </thead> \n");
                sb.append("        <tbody> \n");
//                TdsSplRateMast tdsSplRateMast = null;
                for (LowDeduCertifiAllocaBean entity : entityList) {
                    head++;

                    sb.append("                 \n");
                    sb.append("                <tr class=\"text-center\"> \n");
                    sb.append("                    <td style=\"\"> \n");
//                    sb.append("                       <input type=\"checkbox\" id=\"checkBox~" + head + "\" name=\"beanList[" + (indexCount) + "].checkBoxSelect\" class=\"forSelectAll\" value=\"A\" style=\"opacity:1; left:50px;\"> \n");
                    sb.append("<div class=\"custom-control custom-checkbox action-check text-center\">")
                            .append(" <input type=\"checkbox\" id=\"checkBox~" + head + "\" name=\"beanList[" + (indexCount) + "].checkBoxSelect\" ")
                            .append("class=\"custom-control-input toggle-action-section forSelectAll\"")
                            .append("onclick=\"openActionDiv();\">\n")
                            .append(" <label class=\"custom-control-label\" for=\"checkBox~" + head + "\"></label>\n")
                            .append("</div>");
                    sb.append("                    </td> \n");
//                    sb.append("                    <td> \n");
//                    sb.append("<i class=\"fa view text-success cursor-pointer mr-1\" title=\"View Certificate Details\" onclick=\"showCertificateList('").append(head).append("');\"></i>");
//                    sb.append("                    </td> \n");
                    sb.append("                    <td> \n");
                    sb.append(indexCount + 1);
                    sb.append("                    </td> \n");

                    sb.append("                    <td> \n");
                    sb.append(entity.getCertificate_no());
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].certificate_no\" id=\"certificate_no~").append(head)
                            .append("\" value=\"").append(entity.getCertificate_no()).append("\">");
                    sb.append("                    </td> \n");
//                    sb.append("                    <td> \n");
//                    sb.append("<span class=\"badge badge-secondary ml-1\" style=\"cursor: pointer;\" title=\"Find Certificate\" onclick=\"showCertificateList('").append(head).append("');\">F</span>&nbsp;")
//                            .append("<span class=\"badge badge-secondary\" style=\"cursor: pointer;\" title=\"Clear Certificate\" onclick=\"clearThisCertificate('").append(head).append("');\">C</span>");
//                    sb.append("                    </td> \n");

                    Date l_from_date = entity.getFrom_date() != null && !entity.getFrom_date().isEmpty() ? db_date_format.parse(entity.getFrom_date()) : null;
                    sb.append("                    <td> \n");
                    sb.append(l_from_date != null ? display_date_format.format(l_from_date) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].from_date\" id=\"validFrom~").append(head)
                            .append("\" value=\"").append(entity.getFrom_date()).append("\">");
                    sb.append("                    </td> \n");

                    Date l_to_date = entity.getTo_date() != null && !entity.getTo_date().isEmpty() ? db_date_format.parse(entity.getTo_date()) : null;
                    sb.append("                    <td> \n");
                    sb.append(l_to_date != null ? display_date_format.format(l_to_date) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].to_date\" value=\"").append(entity.getTo_date()).append("\">");
                    sb.append("                    </td> \n");

                    sb.append("                    <td> \n");
                    sb.append(entity.getDeductee_panno());
                    sb.append("<input hidden id=\"deductee_panno~").append(head)
                            .append("\" name=\"beanList[").append(indexCount)
                            .append("].deductee_panno\" value=\"").append(entity.getDeductee_panno()).append("\">");
                    sb.append("                    </td> \n");

                    sb.append("                    <td> \n");
                    sb.append(entity.getTds_section());
//                    sb.append("<input hidden name=\"beanList[").append(indexCount);
//                            .append("].tds_code\" value=\"").append(entity.getTds_code()).append("\">");
                    sb.append("                    </td> \n");

                    sb.append("                    <td class=\"text-right\"> \n");
                    sb.append(!utl.isnull(entity.getTds_rate()) ? utl.getAmountIndianFormat(Double.parseDouble(entity.getTds_rate())) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].tds_rate\" value=\"").append(entity.getTds_rate()).append("\">");
                    sb.append("                    </td> \n");

                    sb.append("                    <td class=\"text-right\"> \n");
                    sb.append(!utl.isnull(entity.getTds_limit_amt()) ? utl.getAmountIndianFormat(Double.parseDouble(entity.getTds_limit_amt())) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].tds_limit_amt\" id=\"crt_amt~").append(head)
                            .append("\" value=\"").append(entity.getTds_limit_amt()).append("\">");
                    sb.append("                    </td> \n");

                    sb.append("                    <td class=\"text-right\"> \n");
                    sb.append(!utl.isnull(entity.getAmount_consumed()) ? utl.getAmountIndianFormat(Double.parseDouble(entity.getAmount_consumed())) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].amount_consumed\" value=\"").append(entity.getAmount_consumed()).append("\">");

                    sb.append("                    </td> \n");

                    sb.append("                    <td class=\"text-right\"> \n");
                    sb.append(!utl.isnull(entity.getBal_limit()) ? utl.getAmountIndianFormat(Double.parseDouble(entity.getBal_limit())) : "");
                    sb.append("<input hidden name=\"beanList[").append(indexCount)
                            .append("].bal_limit\" value=\"").append(entity.getBal_limit()).append("\">");
                    sb.append("                    </td> \n");

//                    sb.append("                    <td class=\"text-center\"> \n")
//                            .append("<span class=\"badge badge-secondary\" style=\"cursor: pointer;\" onclick=\"allocatedCertificate('").append(head).append("');\">A</span>");
//                    sb.append("                    </td> \n");
                    sb.append("                        <input type=\"hidden\" id=\"entity_code~" + head + "\" name=\"beanList[" + indexCount + "].entity_code\" value=\"" + entity.getEntity_code() + "\" > \n");
                    sb.append("                        <input type=\"hidden\" id=\"acc_year~" + head + "\" name=\"beanList[" + indexCount + "].acc_year\" value=\"" + entity.getAcc_year() + "\" > \n");
                    sb.append("                        <input type=\"hidden\" id=\"quarter_no~" + head + "\" name=\"beanList[" + indexCount + "].quarter_no\" value=\"" + asmt.getQuarterNo() + "\" > \n");
                    sb.append("                        <input type=\"hidden\" id=\"tds_type_code~" + head + "\" name=\"beanList[" + indexCount + "].tds_type_code\" value=\"" + entity.getTds_type_code() + "\" > \n");
                    sb.append("                        <input type=\"hidden\" id=\"tds_code~" + head + "\" name=\"beanList[" + indexCount + "].tds_code\" value=\"" + entity.getTds_code() + "\" > \n");
                    sb.append("                        <input type=\"hidden\" id=\"client_code~" + head + "\" name=\"beanList[" + indexCount + "].client_code\" value=\"" + entity.getClient_code() + "\" > \n");
//                    sb.append("                        <input type=\"hidden\" id=\"certificate_no~" + head + "\" name=\"beanList[" + indexCount + "].certificate_no\" value=\"" + entity.getCertificate_no() + "\" > \n");
//                    sb.append("                        <input type=\"hidden\" id=\"quarter_no~" + head + "\" name=\"beanList[" + indexCount + "].quarter_no\" value=\"" + entity.getQuarter_no() + "\" > \n");
                    sb.append("                </tr> \n");
                    indexCount++;

                }//End For
                sb.append("        </tbody> \n");
                sb.append("    </table> \n");

                
            }
            }
        } catch (Exception e) {
            e.printStackTrace();

            sb = null;
        }
        if (sb == null) {

            sb = new StringBuilder();
            sb.append("<div class=\"well well-lg\"> \n");
            sb.append("     No records found \n");
            sb.append("</div> \n");
        }
        return sb.toString();
    }//end 

    public LowDeduCertifiAllocationData() {
        utl = new Util();
    }

    private Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private String allocatedFlag;

    private String parentFlag;
    private String filterFlag;
    private String childFlag;
    private String certificateNo;
    private String deducteePanNo;
    private ArrayList<LowDeduCertifiAllocaBean> entityList;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getDeducteePanNo() {
        return deducteePanNo;
    }

    public void setDeducteePanNo(String deducteePanNo) {
        this.deducteePanNo = deducteePanNo;
    }

    public String getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(String parentFlag) {
        this.parentFlag = parentFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public ArrayList<LowDeduCertifiAllocaBean> getEntityList() {
        return entityList;
    }

    public void setEntityList(ArrayList<LowDeduCertifiAllocaBean> entityList) {
        this.entityList = entityList;
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

    public String getAllocatedFlag() {
        return allocatedFlag;
    }

    public void setAllocatedFlag(String allocatedFlag) {
        this.allocatedFlag = allocatedFlag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

}
