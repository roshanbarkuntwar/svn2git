/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsSplRateMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsSplRateMastId;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class LowerDeductCertificateBrowse extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        session.put("ACTIVE_TAB", "tdsLowerDeductionBrowse");
        String returnType = "success";
        StringBuilder sb = new StringBuilder();
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String rsltMsg = (String) session.get("TDSSPLRATERSLTMSG");
            if (!utl.isnull(rsltMsg)) {
                setResultMessage(rsltMsg);
                session.remove("TDSSPLRATERSLTMSG");
            }

            if (!utl.isnull(getAction())) {
                GetGlobalList gb = new GetGlobalList();
                tdsSectionList = gb.getSectionList(asmt.getTdsTypeCode(), asmt.getQuarterToDate());
                DAOFactory factory;
                TdsSplRateMastDAO tdsSplRateMastDAO;
                if (getAction().equalsIgnoreCase("add")) {
                    setPageHeaderTitle("Edit Lower Deduction Certificate");
                    setPageAction("save");
                    returnType = "entry";
                } else if (getAction().equalsIgnoreCase("edit")) {
                    setPageHeaderTitle("Edit Lower Deduction Certificate");
                    setPageAction("update");
                    factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                    setTdsSplRateMast(tdsSplRateMastDAO.readTdsSplRateById(getTdsSplRateMastId()));
                    returnType = "entry";
                }
            } else {
                setDataGridAction("lowerDeductDataGridAction");
                setBulkDownloadFor("LOWER_DEDUCTION");
                if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
//                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                TdsSplRateMastDAO tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                    TdsSplRateMast tdsSplRateMastFilterSrch = getTdsSplRateMastFltrSrch() == null ? null : getTdsSplRateMastFltrSrch();
//                long total = tdsSplRateMastDAO.getTdsSplRateCount(tdsSplRateMastFilterSrch, getSearch(), utl);

                    LowerDeducteeCertificateDB ld_db = new LowerDeducteeCertificateDB();
                    String totalQuery = ld_db.getTdsSplRateCountQuery(client, asmt, tdsSplRateMastFilterSrch,
                            utl);

                    utl.generateSpecialLog("Lower deductee count query", totalQuery);

                    DbFunctionExecutorAsIntegar db_exe = new DbFunctionExecutorAsIntegar();
                    long total = db_exe.execute_oracle_function_as_integar(totalQuery);

                    //paginator logic start..
                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {
                        String recNumber = getRecordsPerPage();
                        if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                            recNumber = (String) session.get("LOWERDEDUCTRECPERPG");
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
                    returnType = "filter_success";
                } else {
                    returnType = "success";
                }
            }

        } catch (Exception e) {

        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnType;
    }

    public LowerDeductCertificateBrowse() {
        utl = new Util();
    }
    Util utl;
    private Map<String, Object> session;
    private String search;
    private String action;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String dataGridAction;
    private String bulkDownloadFor;
    private String resultMessage;
    private TdsSplRateMast tdsSplRateMastFltrSrch;
    private TdsSplRateMast tdsSplRateMast;
    private LinkedHashMap<String, String> tdsSectionList;
    private String pageHeaderTitle;
    private String pageAction;
    private TdsSplRateMastId tdsSplRateMastId;
    private InputStream inputStream;

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public TdsSplRateMastId getTdsSplRateMastId() {
        return tdsSplRateMastId;
    }

    public void setTdsSplRateMastId(TdsSplRateMastId tdsSplRateMastId) {
        this.tdsSplRateMastId = tdsSplRateMastId;
    }

    public String getPageHeaderTitle() {
        return pageHeaderTitle;
    }

    public void setPageHeaderTitle(String pageHeaderTitle) {
        this.pageHeaderTitle = pageHeaderTitle;
    }

    public String getPageAction() {
        return pageAction;
    }

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    public TdsSplRateMast getTdsSplRateMast() {
        return tdsSplRateMast;
    }

    public void setTdsSplRateMast(TdsSplRateMast tdsSplRateMast) {
        this.tdsSplRateMast = tdsSplRateMast;
    }

    public LinkedHashMap<String, String> getTdsSectionList() {
        return tdsSectionList;
    }

    public void setTdsSectionList(LinkedHashMap<String, String> tdsSectionList) {
        this.tdsSectionList = tdsSectionList;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public TdsSplRateMast getTdsSplRateMastFltrSrch() {
        return tdsSplRateMastFltrSrch;
    }

    public void setTdsSplRateMastFltrSrch(TdsSplRateMast tdsSplRateMastFltrSrch) {
        this.tdsSplRateMastFltrSrch = tdsSplRateMastFltrSrch;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}
