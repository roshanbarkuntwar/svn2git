/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchGeneration;

import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class BranchDetailsGridAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    private InputStream inputStream;
    private final Util utl;
    private final DateTimeUtil dateUtl;

    private String search;
    private String clientCode;
    private String branchCode;
    private String tokenNo;
    private String downloadType;
    private String downloadStatus;
    private BranchDetailsBean branchDetailsFilters;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;

    public BranchDetailsGridAction() {
        this.utl = new Util();
        this.dateUtl = new DateTimeUtil();
    }

    @Override
    public String execute() throws Exception {
        String return_value = "";
        String return_view = "success";
        StringBuilder sb = new StringBuilder();
        try {
            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));

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

                    if (clientMast != null) {

                        BranchDetailsDB branchDb = new BranchDetailsDB();
                        String query = branchDb.getBranchDetailsQuery(clientMast, getBranchDetailsFilters(), l_record_MNL, l_record_MXL);
//                        utl.generateLog("Branch details query...", query);

                        DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                        List<BranchDetailsBean> detailsList = db.getGenericList(new BranchDetailsBean(), query);

                        sb = this.getDetailsGrid(detailsList);
                    } else {
                        addActionError("Some error occured!");
                        return_view = "input";
                    }
                } else {
                    sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

            } else if (getSetRecPerPage() > 0) {
                session.put("BRDETAILSRECPERPG", String.valueOf(getSetRecPerPage()));
                return_value = "success";
                return_view = "success";
                inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return return_view; //To change body of generated methods, choose Tools | Templates.
    }//end method

    private StringBuilder getDetailsGrid(List<BranchDetailsBean> detailsBeanList) {
        StringBuilder detailsBuilder = new StringBuilder("");
        if (detailsBeanList == null) {
            detailsBuilder.append(GlobalMessage.PAGINATION_NO_RECORDS);
            return detailsBuilder;
        } else {
            detailsBuilder.append("<table class=\"table table-striped\" id=\"table\">");
            detailsBuilder.append("<thead>")
                    .append("<tr class=\"text-center\">")
                    .append("<th>Action</th>")
                    .append("<th>Sr. No.</th>")
                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">Bank Branch Code</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">Branch/Division Name</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">Parent Code(Bank Branch Code)</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">PAN Number</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">TAN Number</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    //                    .append("<th><div class=\"table-head-inner\"><div class=\"table-heading\">Last Update</div><div class=\"sort-icon\"><i class=\"fa fa-sort-desc\"></i><i class=\"fa fa-sort-asc\"></i></div></div></th>")
                    .append("</tr>")
                    .append("</thead><tbody>");

            for (BranchDetailsBean statusData : detailsBeanList) {
                detailsBuilder.append("<tr class=\"text-center\">")
                        .append("<td>").append("<i title=\"Edit\" class=\"fa fa-pencil text-primary cursor-pointer\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" ")
                        .append(" id=\"edit_").append(statusData.getClient_code()).append("\" ")
                        .append("onclick=\"editBranchDetails(this.id)\"")
                        .append(" ></i></td>")
                        
                        /*Delete button commented based on requirement of client 14-09-2021*/
//                        .append("<td>")
//                        .append("<i title=\"Delete\" class=\"fa delete text-danger cursor-pointer\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\"id=\"del_")
//                        .append(statusData.getClient_code()).append("\" onclick=\"deleteBranchDetails(this.id);\"></i>").append("</td>")
                        
                        .append("<td>").append(utl.isnull(statusData.getSr_no()) ? "" : statusData.getSr_no()).append("</td>")
                        .append("<td>").append(utl.isnull(statusData.getBank_branch_code()) ? "" : statusData.getBank_branch_code()).append("</td>")
                        .append("<td>").append(utl.isnull(statusData.getClient_name()) ? "" : statusData.getClient_name()).append("</td>")
                        .append("<td>").append(utl.isnull(statusData.getParent_branch_code()) ? "" : statusData.getParent_branch_code()).append("</td>")
                        .append("<td>").append(utl.isnull(statusData.getPanno()) ? "" : statusData.getPanno()).append("</td>")
                        .append("<td>").append(utl.isnull(statusData.getTanno()) ? "" : statusData.getTanno()).append("</td>");
//                        .append("<td>").append((statusData.getLastupdate() == null) ? "" : new SimpleDateFormat("dd-MM-yyyy").format(statusData.getLastupdate())).append("</td>");
                detailsBuilder.append("</tr>");
            }
            detailsBuilder.append("</tbody></table>");
        }
        return detailsBuilder;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
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

    public BranchDetailsBean getBranchDetailsFilters() {
        return branchDetailsFilters;
    }

    public void setBranchDetailsFilters(BranchDetailsBean branchDetailsFilters) {
        this.branchDetailsFilters = branchDetailsFilters;
    }

}
