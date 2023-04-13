/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewEmpCatgDAO;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryGridNew extends ActionSupport implements SessionAware {

    private Util utl;
    private Map<String, Object> session;
    private String search;
    private String filterFlag;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private InputStream inputStream;
    private SalaryTranLoadFilter salaryTranLoadFilter;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public SalaryGridNew() {
        this.utl = new Util();
        salaryTranLoadFilter = new SalaryTranLoadFilter();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");

        if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
            if (!utl.isnull(getSearch())) {
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

                    //Salary grid data..
                    try {
//                        SalaryTranLoadDAO salaryTranLoadDAO = factory.getSalaryTranLoadDAO();
//                        ArrayList<SalaryTranLoadBean> salaryTranLoadList = salaryTranLoadDAO.getSalaryTranLoadList(getSalaryTranLoadFilter(),client, asmt, l_record_MNL, l_record_MXL, utl);
                        SalaryTranLoadDB salaryTranLoadDB = new SalaryTranLoadDB();
                        String salaryTranLoadQuery = salaryTranLoadDB.getSalaryTranLoadQuery(salaryTranLoadFilter, client, asmt, minVal, maxVal, utl);
                        DbGenericFunctionExecutor executor = new DbGenericFunctionExecutor();
                        ArrayList<SalaryTranLoadBean> salaryTranLoadList = executor.getGenericList(new SalaryTranLoadBean(), salaryTranLoadQuery);

                        ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
                        HashMap<String, String> empCatgMap = viewEmpCatgDAO.getEmpCatgAsLinkedHashMap();

                        return_msg = this.salaryGridData(salaryTranLoadList, empCatgMap, client);
                    } catch (Exception e) {
                        return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                        e.printStackTrace();
                    }
                } else {
                    return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                }

                return_view = "ajax_result";
            } else if (getSetRecPerPage() > 0) {
                session.put("SALNEWRECPERPG", String.valueOf(getSetRecPerPage()));// records per page
                return_msg = "success";
                return_view = "ajax_result";
            }
        } else {
            return_msg = GlobalMessage.PAGINATION_SHOW_MORE;
        }

        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
    }

    public String salaryGridData(ArrayList<SalaryTranLoadBean> list, HashMap<String, String> empCatgMap, ViewClientMast clientMast) {
        StringBuilder grid = new StringBuilder();
        try {
            grid.append("<div >");
            grid.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            grid.append("                    <thead>");
            grid.append("                    <tr class=\"text-center\">");
            if (clientMast.getEntity_code().equalsIgnoreCase("G1")) {
                grid.append("                    <th class=\"th-action header\" colspan=\"3\">Action</th>");
            } else {
                grid.append("                    <th class=\"th-action header\" colspan=\"2\">Action</th>");
            }

            grid.append("                    <th>Sr. No.</th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Employee PAN No.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append(" <div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Employee Name</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Employee Category</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\"> Whether opting for taxation u/s 115BAC [Yes/No]</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Employee Code</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">From Date</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">To Date</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Amount of Previous Employer Salary</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Gross Salary of Current Employer 17(1)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total income of salary</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Eligible Amount for Exemptions under Section - 10</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net taxable income under the head salaries  (After exceptions)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total amount of deductions under Sec-16</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Income chargable under head salaries(System calculated)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net income chargable under the head House properties</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Income from Other Sources offered for TDS 192 (2B)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Gross Total Income</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Gross Total of Chapter VI-A Deductions</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Taxable Income, Subject to Limits under Chapter VI-A</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Tax before Adjustments</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Income Tax Relief u/s 89 when salary etc is paid in arrear or advance 89</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Rebate under section 87A, if applicable 87A</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net Income Tax Payable/(refundable) including Surcharge and H&E Cess</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS for the whole year by current employer</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
//
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total TDS for the whole year by previous employer</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Shortfall in tax deduction (+)/Excess tax deduction(-)</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            if (clientMast.getEntity_code().equalsIgnoreCase("G1")) {
                grid.append("                    <th> ");
                grid.append("<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Current month salary</div>"
                        + "<div class=\"sort-icon\">"
                        + "<i class=\"fa fa-sort-desc\"></i>"
                        + "<i class=\"fa fa-sort-asc\"></i>"
                        + "</div></div>");
                grid.append("</th>");

                grid.append("                    <th> ");
                grid.append("<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Total salary already paid (Upto previous month)</div>"
                        + "<div class=\"sort-icon\">"
                        + "<i class=\"fa fa-sort-desc\"></i>"
                        + "<i class=\"fa fa-sort-asc\"></i>"
                        + "</div></div>");
                grid.append("</th>");
                grid.append("                    <th> ");
                grid.append("<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Salary to be paid inluding current month</div>"
                        + "<div class=\"sort-icon\">"
                        + "<i class=\"fa fa-sort-desc\"></i>"
                        + "<i class=\"fa fa-sort-asc\"></i>"
                        + "</div></div>");
                grid.append("</th>");
                grid.append("                    <th> ");
                grid.append("<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Average tax rate</div>"
                        + "<div class=\"sort-icon\">"
                        + "<i class=\"fa fa-sort-desc\"></i>"
                        + "<i class=\"fa fa-sort-asc\"></i>"
                        + "</div></div>");
                grid.append("</th>");
                grid.append("                    <th> ");
                grid.append("<div class=\"table-head-inner\">"
                        + "<div class=\"table-heading\">Tax to be collected (TDS for the month)</div>"
                        + "<div class=\"sort-icon\">"
                        + "<i class=\"fa fa-sort-desc\"></i>"
                        + "<i class=\"fa fa-sort-asc\"></i>"
                        + "</div></div>");
                grid.append("</th>");

            }

            grid.append("                    </tr>");
            grid.append("                    </thead>");
            grid.append("                    <tbody>");

            if (list != null && !list.isEmpty()) {
                SalaryTranLoadBean sal_tran = null;
                for (int i = 0; i < list.size(); i++) {
                    sal_tran = list.get(i);

                    grid.append("<tr>");
                    // Add for Sevaarth Projection
                    if (clientMast.getEntity_code().equalsIgnoreCase("G1")) {
                        grid.append("<td class=\"text-center\">");
                        grid.append("<a href=\"sevaarthProjectionDetails?action=sevaarth&rowid_seq=" + sal_tran.getRowid_seq() + "&deductee_pan=" + sal_tran.getDeductee_panno() + " \">"
                                + "<i class=\"fa fa-tag text-primary cursor-pointer mr-1\" title=\"Sevaarth Projection Details\"></i></a>");// Action
                        grid.append("</td>");
                    }

                    grid.append("<td class=\"text-center\">");
                    grid.append("<a href=\"salaryEntryNew2?action=edit&rowid_seq=" + sal_tran.getRowid_seq() + "\">"
                            + "<i class=\"fa edit text-primary cursor-pointer mr-1\" title=\"Edit\"></i></a>");// Action
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append("<a href=\"#\" onclick=\"deleteSalaryEntry('" + sal_tran.getRowid_seq() + "','" + sal_tran.getDeductee_code() + "');\">"
                            + "<i class=\"fa delete text-danger cursor-pointer mr-1\" title=\"Delete\"></i></a>");// Action
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(sal_tran.getSlno());// Sr.No.
                    grid.append("</td>");

                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_panno()) ? sal_tran.getDeductee_panno() : "");// 1 Deductee panno
                    grid.append("</td>");
                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_name()) ? sal_tran.getDeductee_name() : "");// 2 Deductee name
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getDeductee_catg()) ? empCatgMap.get(sal_tran.getDeductee_catg().trim()) : "");// 3 Deductee category
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getTax_115bac_flag()) ? sal_tran.getTax_115bac_flag() : "");//  Whether opting for taxation u/s 115BAC [Yes/No]
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getIdentification_no()) ? sal_tran.getIdentification_no() : "");// 4 Employee Code
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getSalary_from_date()) ? sal_tran.getSalary_from_date() : "");// 4 Salary from date
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(sal_tran.getSalary_to_date()) ? sal_tran.getSalary_to_date() : "");// 4 Salary to date
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg01_pes_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg01_pes_amt()) : "");// 5 Total Amount of Previous Employer Salary
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg01_ces_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg01_ces_amt()) : "");// 6  (a) Gross Salary of Current Employer 17(1)
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg01_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg01_total_amt()) : "");// 7 Total income of salary
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg05_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg05_total_amt()) : "");// 7 Eligible Amount for Exemptions under Section - 10
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfgr1_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfgr1_total_amt()) : "");// 8 Net Taxable Income under the head - Salaries(after exemptions)
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg10_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg10_total_amt()) : "");// 10 Total amount of deduction from head salaries
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfgr2_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfgr2_total_amt()) : "");// 11 Income chargeable under the head salaries
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg15_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg15_total_amt()) : "");// 12 Net income Chargeable under the head House Property
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg20_ios_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg20_ios_amt()) : "");// 13 Interest paid on home loans
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfgr3_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfgr3_total_amt()) : "");// 14 Net income chargeable under the head house properties
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg25_total_gross_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg25_total_gross_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfgr4_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfgr4_total_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg30_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg30_total_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg35_rus_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg35_rus_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg40_itr_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg40_itr_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfgr5_total_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfgr5_total_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg45_ttce_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg45_ttce_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg45_ttpe_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg45_ttpe_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(sal_tran.getAfg46_sftd_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg46_sftd_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                    grid.append("</td>");
                    if (clientMast.getEntity_code().equalsIgnoreCase("G1")) {
                        grid.append("<td class=\"text-right\">");
                        grid.append(sal_tran.getAfg60_cms_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg60_cms_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                        grid.append("</td>");
                        grid.append("<td class=\"text-right\">");
                        grid.append(sal_tran.getAfg60_upms_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg60_upms_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                        grid.append("</td>");
                        grid.append("<td class=\"text-right\">");
                        grid.append(sal_tran.getAfg60_stpic_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg60_stpic_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                        grid.append("</td>");
                        grid.append("<td class=\"text-right\">");
                        grid.append(sal_tran.getAfg60_avgtax_rate() != null ? utl.getAmountIndianFormat(sal_tran.getAfg60_avgtax_rate()) : "");// 16 Gross total income before chapter VI-A deductions
                        grid.append("</td>");
                        grid.append("<td class=\"text-right\">");
                        grid.append(sal_tran.getAfg60_pcmtds_amt() != null ? utl.getAmountIndianFormat(sal_tran.getAfg60_pcmtds_amt()) : "");// 16 Gross total income before chapter VI-A deductions
                        grid.append("</td>");
                    }
                    grid.append("</tr>");
                }
            }
            grid.append("</tbody>");
            grid.append("</table>");

        } catch (Exception e) {
        }
        return grid.toString();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
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

    public SalaryTranLoadFilter getSalaryTranLoadFilter() {
        return salaryTranLoadFilter;
    }

    public void setSalaryTranLoadFilter(SalaryTranLoadFilter salaryTranLoadFilter) {
        this.salaryTranLoadFilter = salaryTranLoadFilter;
    }

}
