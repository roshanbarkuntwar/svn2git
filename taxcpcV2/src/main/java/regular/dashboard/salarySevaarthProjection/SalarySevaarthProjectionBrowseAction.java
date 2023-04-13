/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.salarySevaarthProjection;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class SalarySevaarthProjectionBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        utl.generateLog("In Salary Sevaarth Projection Action", "");
        session.put("ACTIVE_TAB", "dashboard");
        String return_view = "success";
        StringBuilder sb = new StringBuilder();
        
        System.out.println("Svn Configuration......................");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            try {
                String rsltMsg = (String) session.get("session_result");
                if (!utl.isnull(rsltMsg)) {
                    setSessionResult(rsltMsg);
                    session.remove("session_result");
                }
            } catch (Exception e) {

            }
            setDataGridAction("salarySevaarthProjectionDataGrid");
            setBulkDownloadFor("");
            long total = 0l;

            //paginator logic start..
            setTotalRecordsCount(total);
            int pages = 0;
            if (total > 0) {
                String recNumber = getRecordsPerPage();
                if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                    recNumber = (String) session.get("SALSEVAARTHPROJECTIONRECPERPG");
                } else {
                    recNumber = getRecordsPerPage();

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
            } else {
                setPageNumber("0");
            }
            setTotalPages(pages);

            if (getSearch() != null && getSearch().equalsIgnoreCase("true")) {
                SalarySevaarthProjectionDB db = new SalarySevaarthProjectionDB();
                String l_query = db.getSalarySevaarthProjectionCountQuery(client, asmt, getSalSevaarthProjectionFilterData());
                utl.generateSpecialLog("Salary Sevaarth PROJECTION  Query : ", l_query);
                DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                total = objDBListCount.execute_oracle_function_as_integar(l_query);

                //paginator logic start..
                setTotalRecordsCount(total);
                pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("SALSEVAARTHPROJECTIONRECPERPG");
                    } else {
                        recNumber = getRecordsPerPage();

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
                } else {
                    setPageNumber("0");
                }
                setTotalPages(pages);

                String return_data = "";
                if (getTotalRecordsCount() != 0) {
                    return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                } else {
                    return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                }

                sb.append(return_data);
                return_view = "filter_success";
            } else {
                setTotalPages(pages);
                return_view = "success";
            }
        } catch (Exception e) {
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return return_view;
    }

    public SalarySevaarthProjectionBrowseAction() {
        utl = new Util();
        salSevaarthProjectionFilterData = new SalarySevaarthProjectionFilterEntity();
    }

    Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private String sessionResult;
    private String search;
    private String dataGridAction;
    private int setRecPerPage;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;
    private long startIndex;
    private long endIndex;
    private String action;
    private String browseAction;
    private String recordsPerPage;
    private String pageNumber;
    private String bulkDownloadFor;
    private String filterFlag;
    private SalarySevaarthProjectionFilterEntity salSevaarthProjectionFilterData;

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public SalarySevaarthProjectionFilterEntity getSalSevaarthProjectionFilterData() {
        return salSevaarthProjectionFilterData;
    }

    public void setSalSevaarthProjectionFilterData(SalarySevaarthProjectionFilterEntity salSevaarthProjectionFilterData) {
        this.salSevaarthProjectionFilterData = salSevaarthProjectionFilterData;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public int getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(int setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(long endIndex) {
        this.endIndex = endIndex;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

}
