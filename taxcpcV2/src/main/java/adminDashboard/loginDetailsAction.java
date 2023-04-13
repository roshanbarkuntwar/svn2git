/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class loginDetailsAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        session.put("ACTIVE_TAB", "userLogin");
        String return_msg = "";
        StringBuilder sb = new StringBuilder();
        String returnView = "successDashboard";
        System.out.println("userLoginDetailsAction");
        setDataGridAction("loginDetailsDataGrid");

        try {

            if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("clientview")) {
                returnView = "viewDashboard";

            } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("userview")) {
                returnView = "viewDashboard";

            }

            if (!utl.isnull(search) && getSearch().equalsIgnoreCase("true")) {
                System.out.println("LoginDetailsAction search");
                String count_sql_query = "";
                loginDashboardSupport querysupp = new loginDashboardSupport();

                if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("client")) {
                    count_sql_query = querysupp.entityWiseBranchCountQuery(getBankBranchCode(), utl);

                } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("user")) {
                    count_sql_query = querysupp.entityWiseUserCountQuery(getUserCode(), getLoginId(), utl);

                } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("clientview")) {
                    count_sql_query = querysupp.clientViewCountQuery(getClientCode(),getDate(),utl);

                } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("userview")) {
                    count_sql_query = querysupp.userViewCountQuery(getUserCode(),getDate(),utl);

                }

                System.out.println(count_sql_query);
                DbFunctionExecutorAsIntegar db = new DbFunctionExecutorAsIntegar();
                long total = db.execute_oracle_function_as_integar(count_sql_query);
                System.out.println("totaltotal-" + total);

                setTotalRecords(total);
                setPageNumber(0);
                setTotalRecordsCount(total);

                int pages = 0;
                if (total > 0) {
                    String recNumber = (String) session.get("PROCESSDELETERECPERPAGE");// records per page
                    setRecordsPerPage(utl.isnull(recNumber) ? 10 : Integer.parseInt(recNumber));
                    int recVal = getRecordsPerPage();
                    long mod = total % recVal;
                    int rem = 0;
                    if (mod > 0) {
                        rem = 1;
                    }
                    pages = (int) Math.ceil(total / recVal) + rem;

                    setPageNumber(1);
                } else {
                    setPageNumber(0);
                }

                setTotalPages(pages);

                String return_data = "";
                if (getTotalRecordsCount() != 0) {
                    return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                } else {
                    return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                }

                return_msg = return_data;
                returnView = "filter_success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("returnViewreturnView-" + returnView);
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return returnView;
    }//End Method

    private Map<String, Object> session;
    private Util utl;
    private InputStream inputStream;

    private String action;
    private String search;
    private String browseAction;
    private int recordsPerPage;
    private int pageNumber;
    private int startIndex;
    private int endIndex;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;
    private String loginDetailsType;
    private String dataGridAction;
    private String userCode;
    private String bankBranchCode;
    private String loginId;
    private String clientCode;
    private String filterFlag;
    private String date;

    public loginDetailsAction() {
        utl = new Util();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getLoginDetailsType() {
        return loginDetailsType;
    }

    public void setLoginDetailsType(String loginDetailsType) {
        this.loginDetailsType = loginDetailsType;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
