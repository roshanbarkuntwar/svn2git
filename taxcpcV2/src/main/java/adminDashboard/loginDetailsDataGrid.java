/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanFilterEntity;
import regular.dashboard.bulkTxtFile.BulkTextFileBean;
import regular.dashboard.bulkTxtFile.BulkTextFileSupport;

/**
 *
 * @author akash.meshram
 */
public class loginDetailsDataGrid extends ActionSupport implements SessionAware {

    public loginDetailsDataGrid() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String return_view = "grid_success";
        String return_value = "";
        session.put("ERRORCLASS", ErrorType.errorMessage);
        StringBuilder sb = new StringBuilder();
        System.out.println("datagridaction");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment assessment = (Assessment) session.get("ASSESSMENT");
            loginDashboardSupport querysupp = new loginDashboardSupport();

            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                if (getTotalRecords() > 0) {
                    if (getRecordsPerPage() > 0) {
                        if (getPageNumber() > 0) {

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
                            String dataGridQuery;
                            ArrayList<ArrayList<String>> dataRecordList;
                            if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("client")) {
                                dataGridQuery = querysupp.entityWiseBranchListQuery(l_record_MNL, l_record_MXL, getBankBranchCode(), utl);
                                System.out.println("dataGridQuery-" + dataGridQuery);
                                dataRecordList = new DbFunctionExecutorAsString().execute_oracle_query_as_list(dataGridQuery);
                                return_value = querysupp.branchListDataGrid(dataRecordList, utl,l_record_MNL);
                            } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("user")) {
                                dataGridQuery = querysupp.entityWiseUserListQuery(l_record_MNL, l_record_MXL, getUserCode(), getLoginId(), utl);
                                dataRecordList = new DbFunctionExecutorAsString().execute_oracle_query_as_list(dataGridQuery);
                                return_value = querysupp.userListDataGrid(dataRecordList, utl,l_record_MNL);
                            } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("clientView")) {
                                dataGridQuery = querysupp.clientCodeWiseViewListQuery(getClientCode(), getDate(),utl,l_record_MNL, l_record_MXL);
                                dataRecordList = new DbFunctionExecutorAsString().execute_oracle_query_as_list(dataGridQuery);
                                return_value = querysupp.clientViewListDataGrid(dataRecordList, utl,l_record_MNL);
                            } else if (!utl.isnull(getLoginDetailsType()) && getLoginDetailsType().equalsIgnoreCase("userView")) {
                                dataGridQuery = querysupp.userCodeWiseViewListQuery(getUserCode(),getDate(),utl, l_record_MNL, l_record_MXL);
                                dataRecordList = new DbFunctionExecutorAsString().execute_oracle_query_as_list(dataGridQuery);
                                return_value = querysupp.userViewListDataGrid(dataRecordList, utl,l_record_MNL);
                            }

                        }
                    }
                } else {
                    sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);

                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));

        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    Util utl;
    private Map<String, Object> session;
    private String tokenNo;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private String LoginDetailsType;
    private String filterFlagR;
    private String userCode;
    private String bankBranchCode;
    private String loginId;
    private String clientCode;
    private String filterFlag;
    private String date;

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getLoginDetailsType() {
        return LoginDetailsType;
    }

    public void setLoginDetailsType(String LoginDetailsType) {
        this.LoginDetailsType = LoginDetailsType;
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

    public String getFilterFlagR() {
        return filterFlagR;
    }

    public void setFilterFlagR(String filterFlagR) {
        this.filterFlagR = filterFlagR;
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
