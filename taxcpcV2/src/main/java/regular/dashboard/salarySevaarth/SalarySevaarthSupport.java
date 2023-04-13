/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.salarySevaarth;

import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.List;

/**
 *
 * @author kapil.gupta
 */
public class SalarySevaarthSupport {

    Util utl = new Util();

    public StringBuilder getBrowseGrid(List<SalarySevaarthBrowseEntity> entity, ViewClientMast clientmast, StringBuilder grid, int startindex, int pageNo) {
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            utl.generateLog("inside getBrowseGrid method..........", "");
            utl.generateLog("Grid creation started..........", "");
            grid.append("<div >");
            grid.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            grid.append("                    <thead>");
            grid.append("                    <tr class=\"text-center\">");
            grid.append("                    <th>Sr. No.</th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">DDO Number</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append(" <div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Bill ID</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">BILL Description</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Bill Type</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Voucher Number</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Voucher Date</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Bill Gross Amount</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net Amount</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Basic Pay</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">NPS Employer Contribution</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Gross</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">NPS Employer Contribution</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">PLI</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">IT</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Exe. Pay Rec.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Deduction</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net Pay</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">D.A</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">H.R.A</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">T.A</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Partially Fully Tax Free</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Other Taxable</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">GPF</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">NPS Employee Contribution</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">GIS</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">PT</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">HBA Principal</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">HBA Interest</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">LIC</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Other Deduction</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Washing allowance</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Uniform allowance</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Donations</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Naxal Area Allowance</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">PG Allowance</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Medical Education Allowance</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Medical Insurance Premium</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Sevarth Id</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("                    </tr>");
            grid.append("                    </thead>");
            grid.append("                    <tbody>");

            if (entity != null && !entity.isEmpty()) {
                SalarySevaarthBrowseEntity sal_tran = null;
                for (int i = 0; i < entity.size(); i++) {
                    sal_tran = entity.get(i);

                    grid.append("<tr>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(sal_tran.getSlno());// sl Number
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(sal_tran.getDdo_no());// DDO Number
                    grid.append("</td>");

                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getBill_id()) ? sal_tran.getBill_id() : "");// BillId
                    grid.append("</td>");
                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getBill_desc()) ? sal_tran.getBill_desc() : "");// BillDesc
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getBill_type()) ? sal_tran.getBill_type() : "");// BillType
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getVrno()) ? sal_tran.getVrno() : "");//  Vrno
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getVrdate()) ? sal_tran.getVrdate() : "");// Vrdate
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getBill_gross_amt()) ? sal_tran.getBill_gross_amt() : "");// BillGrossAmt
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getNetamt()) ? sal_tran.getNetamt() : "");// Netamt
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getBasic_pay()) ? sal_tran.getBasic_pay() : "");// BasicPay
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getNps_employer_contr()) ? sal_tran.getNps_employer_contr() : "");// NpsEmployerContr
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getTotal_gross()) ? sal_tran.getTotal_gross() : "");// TotalGross
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getNps_employer_cont()) ? sal_tran.getNps_employer_cont() : "");// NpsEmployerCont
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getPli()) ? sal_tran.getPli() : "");// Pli
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getIt()) ? sal_tran.getIt() : "");// It
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getExe_pay_rec()) ? sal_tran.getExe_pay_rec() : "");// ExePayRec
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getTotal_deduction()) ? sal_tran.getTotal_deduction() : "");// TotalDeduction
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getNet_pay()) ? sal_tran.getNet_pay() : "");// NetPay
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getDa()) ? sal_tran.getDa() : "");// Da
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getHra()) ? sal_tran.getHra() : "");// Hra
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getTa()) ? sal_tran.getTa() : "");// Ta
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getPartially_fully_tax_free()) ? sal_tran.getPartially_fully_tax_free() : "");// PartiallyFullyTaxFree
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getOther_taxable()) ? sal_tran.getOther_taxable() : "");// OtherTaxable
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getGpf()) ? sal_tran.getGpf() : "");// Gpf
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getNps_employee_contr()) ? sal_tran.getNps_employee_contr() : "");// NpsEmployeeContr
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getGis()) ? sal_tran.getGis() : "");// Gis
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getPt()) ? sal_tran.getPt() : "");// Pt
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getHba_principal()) ? sal_tran.getHba_principal() : "");// HbaPrincipal
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getHba_interest()) ? sal_tran.getHba_interest() : "");// HbaInterest
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getLic()) ? sal_tran.getLic() : "");// Lic
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getOther_deduction()) ? sal_tran.getOther_deduction() : "");// OtherDeduction
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getWashing_allowance()) ? sal_tran.getWashing_allowance() : "");// WashingAllowance
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getUniform_allowance()) ? sal_tran.getUniform_allowance() : "");// UniformAllowance
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getDonations()) ? sal_tran.getDonations() : "");// Donations
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getNaxal_area_allowance()) ? sal_tran.getNaxal_area_allowance() : "");// NaxalAreaAllowance
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getPg_allowance()) ? sal_tran.getPg_allowance() : "");// PgAllowance
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getMedical_education_allowance()) ? sal_tran.getMedical_education_allowance() : "");// MedicalEducationAllowance
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getMedical_insurance_premium()) ? sal_tran.getMedical_insurance_premium() : "");// MedicalInsurancePremium
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(sal_tran.getSevarth_id()) ? sal_tran.getSevarth_id() : "");// SevarthId
                    grid.append("</td>");

                    grid.append("</tr>");
                }
            }
            grid.append("</tbody>");
            grid.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;

    }

}
