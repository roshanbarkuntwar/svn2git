/*
 * To change this license header= 0L; long  choose License Headers in Project Properties.
 * To change this template file= 0L; long  choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author kapil.gupta
 */
public class SalSevaarthProjSupport {

    StringBuilder grid = null;
    Util utl;

    public SalSevaarthProjSupport() {
        utl = new Util();
        grid = new StringBuilder();
    }

    public StringBuilder getBrowseGrid(ArrayList<SalSevaarthProjBrowseEntity> entity) {
        try {
            grid.append("<div >");
            grid.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            grid.append("                    <thead>");
            grid.append("                    <tr class=\"text-center\">");
            grid.append("                    <th>Sr. No.</th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Month</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append(" <div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Sevarth ID</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">PAN Number</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Employee Name</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Category</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Salary Type</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Gross Amount</div>"
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
                    + "<div class=\"table-heading\">Basic Salary</div>"
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
                    + "<div class=\"table-heading\">NPS Employer Contribution Adj</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">P.L.I.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">I.T.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Excess Payment Recovery</div>"
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
                    + "<div class=\"table-heading\">Partially/Fully Tax Free</div>"
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
                    + "<div class=\"table-heading\">G.P.F</div>"
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
                    + "<div class=\"table-heading\">G.I.S</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">P.T.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">H.B.A Principal</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">H.B.A Interest</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
//            
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">L.I.C</div>"
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
                    + "<div class=\"table-heading\">P.G. Allowance</div>"
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

            grid.append("                    </tr>");
            grid.append("                    </thead>");
            grid.append("                    <tbody>");
            // int count=0;
            //Calculation 
            long bill_gross_amt = 0L;
            long netamt = 0L;
            long basic_pay = 0L;
            long nps_employer_contr = 0L;
            long total_gross = 0L;
            long nps_employer_cont = 0L;
            long pli = 0L;
            long it = 0L;
            long exe_pay_rec = 0L;
            long total_deduction = 0L;
            long net_pay = 0L;
            long da = 0L;
            long hra = 0L;
            long ta = 0L;
            long partially_fully_tax_free = 0L;
            long other_taxable = 0L;
            long gpf = 0L;
            long nps_employee_contr = 0L;
            long gis = 0L;
            long pt = 0L;
            long hba_principal = 0L;
            long hba_interest = 0L;
            long lic = 0L;
            long other_deduction = 0L;
            long washing_allowance = 0L;
            long uniform_allowance = 0L;
            long donations = 0L;
            long naxal_area_allowance = 0L;
            long pg_allowance = 0L;
            long medical_education_allowance = 0L;
            long medical_insurance_premium = 0L;
//            double sum_tds = 0d;
            if (entity != null && !entity.isEmpty()) {
                try {
                    SalSevaarthProjBrowseEntity salCal = null;
                    for (int i = 0; i < entity.size(); i++) {
                        salCal = entity.get(i);
                        {
                            if (!utl.isnull(salCal.getBill_gross_amt())) {
                                bill_gross_amt += Long.parseLong(salCal.getBill_gross_amt());
                            }
                            if (!utl.isnull(salCal.getNetamt())) {
                                netamt += Long.parseLong(salCal.getNetamt());
                            }
                            if (!utl.isnull(salCal.getBasic_pay())) {
                                basic_pay += Long.parseLong(salCal.getBasic_pay());
                            }
                            if (!utl.isnull(salCal.getNps_employer_contr())) {
                                nps_employer_contr += Long.parseLong(salCal.getNps_employer_contr());
                            }
                            if (!utl.isnull(salCal.getTotal_gross())) {
                                total_gross += Long.parseLong(salCal.getTotal_gross());
                            }
                            if (!utl.isnull(salCal.getNps_employer_cont())) {
                                nps_employer_cont += Long.parseLong(salCal.getNps_employer_cont());
                            }
                            if (!utl.isnull(salCal.getPli())) {
                                pli += Long.parseLong(salCal.getPli());
                            }
                            if (!utl.isnull(salCal.getIt())) {
                                it += Long.parseLong(salCal.getIt());
                            }
                            if (!utl.isnull(salCal.getExe_pay_rec())) {
                                exe_pay_rec += Long.parseLong(salCal.getExe_pay_rec());
                            }
                            if (!utl.isnull(salCal.getTotal_deduction())) {
                                total_deduction += Long.parseLong(salCal.getTotal_deduction());
                            }
                            if (!utl.isnull(salCal.getNet_pay())) {
                                net_pay += Long.parseLong(salCal.getNet_pay());
                            }
                            if (!utl.isnull(salCal.getDa())) {
                                da += Long.parseLong(salCal.getDa());
                            }
                            if (!utl.isnull(salCal.getHra())) {
                                hra += Long.parseLong(salCal.getHra());
                            }
                            if (!utl.isnull(salCal.getTa())) {
                                ta += Long.parseLong(salCal.getTa());
                            }
                            if (!utl.isnull(salCal.getPartially_fully_tax_free())) {
                                partially_fully_tax_free += Long.parseLong(salCal.getPartially_fully_tax_free());
                            }
                            if (!utl.isnull(salCal.getOther_taxable())) {
                                other_taxable += Long.parseLong(salCal.getOther_taxable());
                            }
                            if (!utl.isnull(salCal.getGpf())) {
                                gpf += Long.parseLong(salCal.getGpf());
                            }
                            if (!utl.isnull(salCal.getNps_employee_contr())) {
                                nps_employee_contr += Long.parseLong(salCal.getNps_employee_contr());
                            }
                            if (!utl.isnull(salCal.getGis())) {
                                gis += Long.parseLong(salCal.getGis());
                            }
                            if (!utl.isnull(salCal.getPt())) {
                                pt += Long.parseLong(salCal.getPt());
                            }
                            if (!utl.isnull(salCal.getHba_principal())) {
                                hba_principal += Long.parseLong(salCal.getHba_principal());
                            }
                            if (!utl.isnull(salCal.getHba_interest())) {
                                hba_interest += Long.parseLong(salCal.getHba_interest());
                            }
                            if (!utl.isnull(salCal.getLic())) {
                                lic += Long.parseLong(salCal.getLic());
                            }
                            if (!utl.isnull(salCal.getOther_deduction())) {
                                other_deduction += Long.parseLong(salCal.getOther_deduction());
                            }
                            if (!utl.isnull(salCal.getWashing_allowance())) {
                                washing_allowance += Long.parseLong(salCal.getWashing_allowance());
                            }

                            if (!utl.isnull(salCal.getUniform_allowance())) {
                                uniform_allowance += Long.parseLong(salCal.getUniform_allowance());
                            }
                            if (!utl.isnull(salCal.getDonations())) {
                                donations += Long.parseLong(salCal.getDonations());
                            }
                            if (!utl.isnull(salCal.getNaxal_area_allowance())) {
                                naxal_area_allowance += Long.parseLong(salCal.getNaxal_area_allowance());
                            }
                            if (!utl.isnull(salCal.getPg_allowance())) {
                                pg_allowance += Long.parseLong(salCal.getPg_allowance());
                            }
                            if (!utl.isnull(salCal.getMedical_education_allowance())) {
                                medical_education_allowance += Long.parseLong(salCal.getMedical_education_allowance());
                            }
                            if (!utl.isnull(salCal.getMedical_insurance_premium())) {
                                medical_insurance_premium += Long.parseLong(salCal.getMedical_insurance_premium());
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (entity != null && !entity.isEmpty()) {
                SalSevaarthProjBrowseEntity sal_tran = null;
                for (int i = 0; i < entity.size(); i++) {
                    sal_tran = entity.get(i);

                    grid.append("<tr>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(i + 1);// sl Number
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(sal_tran.getMonth());// Month
                    grid.append("</td>");

                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getSevarth_id()) ? sal_tran.getSevarth_id() : "");// Sevarth_id
                    grid.append("</td>");
                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_panno()) ? sal_tran.getDeductee_panno() : "");// Deductee_panno
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_name()) ? sal_tran.getDeductee_name() : "");// Deductee_name
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_catg_name()) ? sal_tran.getDeductee_catg_name() : "");//  getDeductee_catg_name
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getData_type()) ? sal_tran.getData_type() : "");// Data_type
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

                    grid.append("</tr>");
                }
            }
            grid.append("</tbody>");
            grid.append("<tfoot>");
            grid.append("<tr class=\"highlight header1\" >");
            grid.append("<td  class=\"text-center\"  title=\"Show Grand Total\"> <img src=\"resources/images/global/sum-icon.png\" class=\"cursor-pointer\"> </td>");
            grid.append("<td colspan=\"6\" class=\"text-right data-height font-weight-bold\">Grand Wise Total :</td>");

            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
            grid.append(bill_gross_amt);

            grid.append("</div> </td>");

            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(netamt);
            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
            grid.append(basic_pay);
            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(nps_employer_contr);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(total_gross);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(nps_employer_cont);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(pli);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(it);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(exe_pay_rec);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(total_deduction);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(net_pay);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(da);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(hra);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(ta);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(partially_fully_tax_free);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(other_taxable);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(gpf);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(nps_employee_contr);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(gis);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(pt);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(hba_principal);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(hba_interest);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(lic);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(other_deduction);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(washing_allowance);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(uniform_allowance);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(donations);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(naxal_area_allowance);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(pg_allowance);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(medical_education_allowance);

            grid.append("</div> </td>");
            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");

            grid.append(medical_insurance_premium);

            grid.append("</div> </td>");
//            grid.append("<td class=\"text-right\"> <div class=\"data-height font-weight-bold\">");
//
//            grid.append(sevarth_id);
//
//            grid.append("</div> </td>");

            grid.append("</tr>");
            grid.append("</tfoot>");
            grid.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;

    }
}
