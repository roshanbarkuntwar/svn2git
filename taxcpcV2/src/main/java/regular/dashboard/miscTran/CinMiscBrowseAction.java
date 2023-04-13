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
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author pratik.barahate
 */
public class CinMiscBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "success";
        String return_msg = "";
        try {
            session.put("ACTIVE_TAB", "dashboard");
            setDataGridAction("miscDataGridNew");
            setBulkDownloadFor("TDS_TRANSACTIONS_MISC");

            try {
                String rsltMsg = (String) session.get("SALRYTRANRSLTMSG");

                if (!utl.isnull(rsltMsg)) {
                    setSessionResult(rsltMsg);
                    session.remove("SALRYTRANRSLTMSG");
                }
            } catch (Exception e) {

            }

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            TdsTranLoadDAO tdsTranLoadObj = factory.getTdsTranLoadDAO();
            try {
                setBglCodeNameList(tdsTranLoadObj.getBGLCodeNameList(asmt.getTdsTypeCode()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            LinkedHashMap<String, String> tdsSectionValuesList = tdsTranLoadObj.getTdsSectionValues(asmt.getTdsTypeCode());
            setSelectSectionList(tdsSectionValuesList);

            if (asmt != null && asmt.getTdsTypeCode().equalsIgnoreCase("26Q") || asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                if (getSearch() != null && getSearch().equalsIgnoreCase("true")) {
                    TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                    Long total = tdsTranLoadDAO.getMiscCinDataCount(getMiscData(), client, asmt, utl);
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
            } else {
                setErrorMessage("Misc Details is only applicable in 26Q & 27Q(Misc)");
                return_view = "error";
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
    private CinMiscFilterEntity miscData;
    private LinkedHashMap<String, String> selectSectionList;
    private LinkedHashMap<String, String> bglCodeNameList;

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
    private String filterFlag;
    private String recordsPerPage;
    private String pageNumber;
    private String sessionResult;
    private String bulkDownloadFor;

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public LinkedHashMap<String, String> getBglCodeNameList() {
        return bglCodeNameList;
    }

    public void setBglCodeNameList(LinkedHashMap<String, String> bglCodeNameList) {
        this.bglCodeNameList = bglCodeNameList;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
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

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
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

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(int setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
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

    public CinMiscFilterEntity getMiscData() {
        return miscData;
    }

    public void setMiscData(CinMiscFilterEntity miscData) {
        this.miscData = miscData;
    }

    public LinkedHashMap<String, String> getSelectSectionList() {
        return selectSectionList;
    }

    public void setSelectSectionList(LinkedHashMap<String, String> selectSectionList) {
        this.selectSectionList = selectSectionList;
    }

    public CinMiscBrowseAction() {
        utl = new Util();
        miscData = new CinMiscFilterEntity();

        bglCodeNameList = new LinkedHashMap<>();

        selectSectionList = new LinkedHashMap<>();
        selectSectionList.put("", "Select Section");
    }

}
