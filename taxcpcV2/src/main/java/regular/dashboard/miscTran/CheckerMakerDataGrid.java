/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author pratik.barahate
 */
public class CheckerMakerDataGrid extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "success";
        String return_msg = "";

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

                    //Tds grid data..
                    try {
                        CinMiscDataDB cinMiscDataDB = new CinMiscDataDB();
                        String checkerMakerDataQuery = cinMiscDataDB.getCheckerMakerDataQuery(checkerMakerData, getAuthStatusFlag(), client, asmt, l_record_MNL, l_record_MXL, utl);
                        DbGenericFunctionExecutor executor = new DbGenericFunctionExecutor();

                        ArrayList<TdsTranLoadBean> checkerMakerDataList = executor.getGenericList(new TdsTranLoadBean(), checkerMakerDataQuery);

                        return_msg = this.checkerMakerGridData(checkerMakerDataList, getAuthStatusFlag());
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

    public String checkerMakerGridData(ArrayList<TdsTranLoadBean> list, String authFlag) {
        StringBuilder grid = new StringBuilder();
        try {
            grid.append("<div class=\"table-responsive mt-2\">");
            grid.append("  <table class=\"table table-bordered table-striped mb-1\" id=\"table\">");
            grid.append("                    <thead>");
            grid.append("                    <tr class=\"text-center\">");
            grid.append("                    <th>Sr. No.</th>");
            grid.append("                    <th class=\"text-center\">Action</th>");//th-action header

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Bank Branch Code</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Bank Branch Name</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Deductee PAN</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Deductee Name</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Invoice Date</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Invoice No</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">BGL Code</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Deduction Date</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Section</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">TDS Applicable</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">TDS Rate</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Certificate No.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Basic Payment Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">CGST Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">SGST Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">IGST Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">TDS Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Total Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");
            grid.append("<th>");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Net Amt.</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("</th>");

            grid.append("                    <th> ");
            grid.append("<div class=\"table-head-inner\">"
                    + "<div class=\"table-heading\">Status</div>"
                    + "<div class=\"sort-icon\">"
                    + "<i class=\"fa fa-sort-desc\"></i>"
                    + "<i class=\"fa fa-sort-asc\"></i>"
                    + "</div></div>");
            grid.append("                    </th>");
            grid.append("                    </tr>");
            grid.append("                    </thead>");
            grid.append("                    <tbody>");

            if (list != null && !list.isEmpty()) {
                SimpleDateFormat monFormat = new SimpleDateFormat("MMM");
                String currentMonth = monFormat.format(new Date());
                String tranMonth = "";

//                System.out.println("currentMonth= " + currentMonth);
                TdsTranLoadBean tds_tran = null;
                for (int i = 0; i < list.size(); i++) {
                    tds_tran = list.get(i);

                    grid.append("<tr>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(tds_tran.getSlno());// Sr.No.
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    boolean approveFlag = true;
                    if (authFlag.equals("A")) {
                        approveFlag = false;
                        try {
                            if (!utl.isnull(tds_tran.getTran_ref_date())) {
                                tranMonth = monFormat.format((new SimpleDateFormat("dd-MMM-yyyy")).parse(tds_tran.getTran_ref_date()));
//                                System.out.println("tranMonth= " + tranMonth);
                                if (currentMonth.equals(tranMonth)) {
                                    approveFlag = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    grid.append("<select id=\"flag\" onChange=\"updateTranLoad(this,'" + tds_tran.getRowid_seq() + "', " + approveFlag + ");\">");

                    grid.append("<option value=\"\">Select</option> \n");
                    if (authFlag.equals("R")) {

                        grid.append("<option value=\"A\">Approve</option> \n");
                    } else if (authFlag.equals("A")) {

                        grid.append("<option value=\"R\">Unapprove</option> \n");
                    }

                    grid.append("</select>");
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getBank_branch_code()) ? tds_tran.getBank_branch_code() : "");// 1 Deductee panno
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getBank_branch_name()) ? tds_tran.getBank_branch_name() : "");// 1 Deductee panno
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getDeductee_panno()) ? tds_tran.getDeductee_panno() : "");// 1 Deductee panno
                    grid.append("</td>");

                    grid.append("<td class=\"\">");
                    grid.append(!utl.isnull(tds_tran.getDeductee_name()) ? tds_tran.getDeductee_name() : "");// 2 Deductee name
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getInvoice_date()) ? tds_tran.getInvoice_date() : "");// 4 Salary from date
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getInvoice_no()) ? tds_tran.getInvoice_no() : "");// 4 Salary from date
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getFlag()) ? tds_tran.getFlag() : "");// 4 Salary from date
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getTran_ref_date()) ? tds_tran.getTran_ref_date() : "");// 4 Salary from date
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getTds_section()) ? tds_tran.getTds_section() : "");// 4 Salary to date
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getTds_tran_type()) ? tds_tran.getTds_tran_type() : "");// TDS Applicable flag
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getTds_rate()) ? tds_tran.getTds_rate() : "");// TDS Applicable flag
                    grid.append("</td>");
                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getCertificate_no()) ? tds_tran.getCertificate_no() : "");// TDS Applicable flag
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getTran_amt()) ? tds_tran.getTran_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getCgst_amt()) ? tds_tran.getCgst_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getSgst_amt()) ? tds_tran.getSgst_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getIgst_amt()) ? tds_tran.getIgst_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getTds_amt()) ? tds_tran.getTds_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getTotal_amt()) ? tds_tran.getTotal_amt() : "");// 4 Salary to date
                    grid.append("</td>");
                    grid.append("<td class=\"text-right\">");
                    grid.append(!utl.isnull(tds_tran.getNet_amt()) ? tds_tran.getNet_amt() : "");// 4 Salary to date
                    grid.append("</td>");

                    grid.append("<td class=\"text-center\">");
                    grid.append(!utl.isnull(tds_tran.getStatus()) ? tds_tran.getStatus() : "");// 2 Deductee name
                    grid.append("</td>");

                    grid.append("</tr>");
                }

            }
            grid.append("</tbody>");
            grid.append("</table>");

            grid.append("</div>");
            grid.append("<div class=\"row form-group mt-2 text-center\">");
            grid.append("<div class=\"col-12\">");

//            if (authFlag.equals("R")) {
//                grid.append("<button type=\"button\" class=\"form-btn font-weight-bold\" title=\"Approve All Records\" onclick=\"updateBulkRecord('A','" + getAuthStatusFlag() + "','" + checkerMakerData.getDeductee_panno() + "', '" + checkerMakerData.getDeductee_name() + "');\">").append("<i class=\"fa update\" aria-hidden=\"true\">").append("</i>").append("Approve All").append("</button>");
//            } else if (authFlag.equals("A")) {
//                grid.append("<button type=\"button\" class=\"form-btn font-weight-bold\" title=\"Reject All Records\" onclick=\"updateBulkRecord('R','" + getAuthStatusFlag() + "','" + checkerMakerData.getDeductee_panno() + "', '" + checkerMakerData.getDeductee_name() + "');\">").append("<i class=\"fa update\" aria-hidden=\"true\">").append("</i>").append("Unapprove All").append("</button>");
//            }
            grid.append("</div>");
            grid.append("</div>");

        } catch (Exception e) {
        }

        return grid.toString();
    }

    private final Util utl;
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
    private TdsTranLoad checkerMakerData;
    private String authStatusFlag;

    public String getAuthStatusFlag() {
        return authStatusFlag;
    }

    public void setAuthStatusFlag(String authStatusFlag) {
        this.authStatusFlag = authStatusFlag;
    }

    public Map<String, Object> getSession() {
        return session;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public TdsTranLoad getCheckerMakerData() {
        return checkerMakerData;
    }

    public void setCheckerMakerData(TdsTranLoad checkerMakerData) {
        this.checkerMakerData = checkerMakerData;
    }

    public CheckerMakerDataGrid() {
        this.utl = new Util();
        checkerMakerData = new TdsTranLoad();
    }

}
