/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchGeneration;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class BranchGenerationBrowse extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    private InputStream inputStream;
    private final Util utl;
    private BranchDetailsBean branchDetailsFilters;
    private String dataGridAction;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String tokenNo;
    private String processType;
    private String sessionResult;
    private String filterFlag;
    private String search;
    private String clientCode;
    private LinkedHashMap<String, String> loginLevelList;
    
    public BranchGenerationBrowse() {
        loginLevelList = new LinkedHashMap<>();
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String return_view = "success";

        session.put("ACTIVE_TAB", "dashboard");
        setDataGridAction("branchDetailsGrid");

        String resultValue = (String) session.get("BRANCHRESULT");
        resultValue = utl.isnull(resultValue) ? "" : resultValue;
        if (!utl.isnull(resultValue)) {
            setSessionResult(resultValue);
            session.remove("BRANCHRESULT");
        }
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        setClientCode(client.getCode_level());
         try {
                if (!utl.isnull(client.getCode_level())) {
                    for (int i = Integer.valueOf(client.getCode_level()) + 1; i <= 6; i++) {
                        loginLevelList.put(i + "", "Level-" + i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                DbFunctionExecutorAsIntegar executer = new DbFunctionExecutorAsIntegar();
                BranchDetailsDB branchDb = new BranchDetailsDB();

                String query = branchDb.getBranchDetailsCountQuery(client, getBranchDetailsFilters(), -1, -1);
                utl.generateSpecialLog("Branch details count query", query);

                long total = executer.execute_oracle_function_as_integar(query);

                //paginator logic start..
                setTotalRecordsCount(total);
                int pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("BRDETAILSRECPERPG");
                    }

                    setRecordsPerPage(utl.isnull(recNumber) ? "10" : recNumber);
                    if (!getRecordsPerPage().equalsIgnoreCase("all")) {
                        int recVal = Integer.parseInt(getRecordsPerPage());

                        long mod = total % recVal;
                        int rem = 0;
                        if (mod > 0) {
                            rem = 1;
                        }
                        pages = (int) Math.ceil(total / recVal) + rem;
                    } else {
                        pages = 0;
                    }
                    int pageNoInt = Integer.parseInt(utl.isnull(getPageNumber()) ? "0" : getPageNumber());
                    if (utl.isnull(getPageNumber()) || pageNoInt > pages || pageNoInt == 0) {
                        setPageNumber("1");
                    }
                    setTotalPages(pages);

                } else {
                    setPageNumber("0");
                    setTotalPages(pages);
                }

                if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                    String return_data = "";
                    if (getTotalRecordsCount() != 0) {
                        return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                    } else {
                        return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                    }
                    return_view = "ajax_success";
                    inputStream = new ByteArrayInputStream(return_data.getBytes("UTF-8"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_view; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
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

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(String fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
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

    public BranchDetailsBean getBranchDetailsFilters() {
        return branchDetailsFilters;
    }

    public void setBranchDetailsFilters(BranchDetailsBean branchDetailsFilters) {
        this.branchDetailsFilters = branchDetailsFilters;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public LinkedHashMap<String, String> getLoginLevelList() {
        return loginLevelList;
    }

    public void setLoginLevelList(LinkedHashMap<String, String> loginLevelList) {
        this.loginLevelList = loginLevelList;
    }

}
