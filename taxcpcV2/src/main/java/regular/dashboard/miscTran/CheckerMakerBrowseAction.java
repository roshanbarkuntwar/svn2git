/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author pratik.barahate
 */
public class CheckerMakerBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";
        try {
            session.put("ACTIVE_TAB", "dashboard");
            setDataGridAction("checkerMakerDataGridNew");

            try {
                String rsltMsg = (String) session.get("SALRYTRANRSLTMSG");

                if (!utl.isnull(rsltMsg)) {
                    setSessionResult(rsltMsg);
                    session.remove("SALRYTRANRSLTMSG");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

            if (getSearch() != null && getSearch().equalsIgnoreCase("true")) {
                ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                Long total = tdsTranLoadDAO.getCheckerMakerDataCount(getCheckerMakerData(), getAuthStatusFlag(), clientMast, asmt, utl);

                int pages = 0;

                if (total > 0) {
                    setTotalRecordsCount(total);
                    pages = 0;
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("SALNEWRECPERPG");
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

                    setTotalPages(pages);

                    if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                        String return_data = "";
                        if (getTotalRecordsCount() != 0) {
                            return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                        } else {
                            return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                        }

                        return_msg = return_data;
                    }
                }
                return_view = "filter_success";
            } else {
                setTotalRecords(0l);
                setTotalPages(0);
                setPageNumber("0");
                return_view = "success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
    }

    Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private TdsTranLoad checkerMakerData;
    private String search;
    private String fetchRecords;
    private String dataGridAction;
    private String browseAction;
    private String errorMessage;
    private int setRecPerPage;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;
    private long startIndex;
    private long endIndex;

    private String action;
    private String readonly;
    private String recordsPerPage;
    private String pageNumber;
    private String sessionResult;
    private String authStatusFlag;

    public CheckerMakerBrowseAction() {
        utl = new Util();
        checkerMakerData = new TdsTranLoad();
    }

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

    public TdsTranLoad getCheckerMakerData() {
        return checkerMakerData;
    }

    public void setCheckerMakerData(TdsTranLoad checkerMakerData) {
        this.checkerMakerData = checkerMakerData;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(String fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
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

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getAuthStatusFlag() {
        return authStatusFlag;
    }

    public void setAuthStatusFlag(String authStatusFlag) {
        this.authStatusFlag = authStatusFlag;
    }

}
