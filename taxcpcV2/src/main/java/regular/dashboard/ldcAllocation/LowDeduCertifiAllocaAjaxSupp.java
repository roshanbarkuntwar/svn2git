/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.ldcAllocation;

import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public class LowDeduCertifiAllocaAjaxSupp {

    public String getCertificateListData1(List<TdsTranLoad> tdsTranLoadList, Util utl) {
        StringBuilder sb = new StringBuilder();
        try {
//            if (tdsTranLoadList != null) {
            SimpleDateFormat sdf_2 = new SimpleDateFormat("dd-MMM-yyyy");

            sb.append("<table class=\"table table-striped table-responsive\" id=\"table\" style=\"margin-bottom: 0px; width: 100%; font-size: 14px;\"> \n");
            sb.append("        <thead>           \n");
            sb.append("            <tr>           \n");
            sb.append("                <th class=\"text-center\">Sr.No.</th> \n");
//            sb.append("                <th class=\"text-center\">Acc Year</th> \n");
//            sb.append("                <th class=\"text-center\">Quarter No</th> \n");
//            sb.append("                <th class=\"text-center\">Deductee Code</th> \n");
            sb.append("                <th class=\"text-center\">Deductee Name</th> \n");
            sb.append("                <th class=\"text-center\">TDS Section</th> \n");
            sb.append("                <th class=\"text-center\">Tran Ref No</th> \n");
            sb.append("                <th class=\"text-center\">Tran Ref Date</th> \n");
            sb.append("                <th class=\"text-center\">Tran Date</th> \n");
            sb.append("                <th class=\"text-center\">TDS Amt</th> \n");
            sb.append("                <th class=\"text-center\">TDS Rate</th> \n");
            sb.append("                <th class=\"text-center\">TDS Deduct Reason</th> \n");
            sb.append("            </tr> \n");
            sb.append("        </thead> \n");
            sb.append("        <tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\"> \n");

            if (tdsTranLoadList.isEmpty()) {
                sb.append("<tr class=\"text-center\"><td colspan=\"12\">No records found.</td></tr>");
            } else {
                int head = 0;
                for (TdsTranLoad obj : tdsTranLoadList) {
                    head++;
                    sb.append("                <tr onclick=\"setCertificat('").append(head).append("');\"> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n").append(head).append("</td> \n");

//                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
//                            .append(utl.isnull(obj.getAcc_year()) ? "" : obj.getAcc_year());
////                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
//                    sb.append("                    </td> \n");
//                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
//                            .append(utl.isnull(obj.getQuarter_no()) ? "" : obj.getQuarter_no());
////                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
//                    sb.append("                    </td> \n");
//                    sb.append("                    <td> \n")
//                            .append(utl.isnull(obj.getDeductee_code()) ? "" : obj.getDeductee_code());
////                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
//                    sb.append("                    </td> \n");
                    sb.append("                    <td> \n")
                            .append(utl.isnull(obj.getDeductee_name()) ? "" : obj.getDeductee_name());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTds_section()) ? "" : obj.getTds_section());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTran_ref_no()) ? "" : obj.getTran_ref_no());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTran_ref_date()) ? "" : obj.getTran_ref_date());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTds_deduct_date()) ? "" : obj.getTds_deduct_date());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTds_amt()) ? "" : obj.getTds_amt());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTds_rate()) ? "" : obj.getTds_rate());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                    <td style=\"text-align:center;width: 100px;\"> \n")
                            .append(utl.isnull(obj.getTds_deduct_reason()) ? "" : obj.getTds_deduct_reason());
//                    sb.append("                        <input type=\"text\" id=\"certificate_noModal~" + head + "\" value=\"" + obj.getCertificate_no() + "\" class=\"display_txt charTxt\" readonly=\"true\"> \n");
                    sb.append("                    </td> \n");
                    sb.append("                </tr> \n");
                }//End For
            }
            sb.append("        </tbody> \n");
            sb.append("    </table> \n");
        } catch (Exception e) {
            //sb = null;
            e.printStackTrace();
        }
        return sb.toString();
    }//End Method

}
