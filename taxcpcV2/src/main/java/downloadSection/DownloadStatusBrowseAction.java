/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadStatusBrowseAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    private InputStream inputStream;

    private final Util utl;
    private String search;
    private String action;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String dataGridAction;
    private String tokenNo;
    private String downloadType;
    private String downloadStatus;
    private String processType;
    private String sessionResult;
    private String filterFlag;
    private String processTypeForData;
    private HashMap<String, String> downloadTypeMap;
    private HashMap<String, String> downloadStatusMap;
    private HashMap<String, String> processTypeMap;

    public DownloadStatusBrowseAction() {
        utl = new Util();
        downloadTypeMap = new HashMap<>();
        downloadStatusMap = new HashMap<>();
        processTypeMap = new HashMap<>();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "success";

        session.put("ACTIVE_TAB", "dashboard");
        String resultValue = (String) session.get("DWNLDSESSIONRESULT");
        resultValue = utl.isnull(resultValue) ? "" : resultValue;
        if (!utl.isnull(resultValue)) {
            addActionError(resultValue);
            session.remove("DWNLDSESSIONRESULT");
        }

        String moduleFlag = (String) session.get("MODULE");
        if (utl.isnull(getProcessType()) && moduleFlag.equalsIgnoreCase("M")) {
            setProcessType("MIS_REPORT_DOWNLOAD");
        }

        try {
            DownloadActionDB db = new DownloadActionDB();
            DbFunctionExecutorAsIntegar executer = new DbFunctionExecutorAsIntegar();

            setDataGridAction("downloadStatusGrid");

            initializeFilterSection(); // Initializing filters on page

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                String additional_whereclause = "";
                if (!utl.isnull(getTokenNo())) {
                    additional_whereclause += " AND A.PROCESS_SEQNO = '" + getTokenNo().trim() + "' \n";
                }
                if (!utl.isnull(getDownloadStatus())) {
                    additional_whereclause += " AND A.PROCESS_STATUS = '" + getDownloadStatus().trim() + "' \n";
                }
                if (!utl.isnull(getProcessTypeForData())) {
                    additional_whereclause += " AND A.PROCESS_TYPE = '" + getProcessTypeForData().trim() + "' \n";
                }
                String query = db.getDownloadStatusDataCount(asmt, client, additional_whereclause, "", moduleFlag, "D");// provide hardcode D only download section and its refer view_lhssys_process_type
                utl.generateSpecialLog("Download section count query", query);

                long total = executer.execute_oracle_function_as_integar(query);

                //paginator logic start..
                setTotalRecordsCount(total);
                int pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("PROCESSSTATUSRECPERPG");
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
            } else {
                return_view = "success";
            }
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            e.printStackTrace();
        }
        return return_view;
    }//end method

    public void initializeFilterSection() {
        DownloadActionDB dadb = new DownloadActionDB();
        DbFunctionExecutorAsString dbexecuter = new DbFunctionExecutorAsString();
        try {
            //Download type list
            String downloadTypeQry = dadb.getDownloadTypeListQry();
            ArrayList<ArrayList<String>> typeList = dbexecuter.execute_oracle_query_as_list(downloadTypeQry);

            HashMap<String, String> typeMap = new HashMap<>();
            if (typeList != null && typeList.size() > 0) {
                typeList.forEach((type) -> {
                    typeMap.put(type.get(0), type.get(1));
                });
                setDownloadTypeMap(typeMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //Download status list
            String downloadStatusQry = dadb.getDownloadStatusQry();
            ArrayList<ArrayList<String>> statusList = dbexecuter.execute_oracle_query_as_list(downloadStatusQry);

            HashMap<String, String> statusMap = new HashMap<>();
            if (statusList != null && statusList.size() > 0) {
                statusList.forEach((status) -> {
                    statusMap.put(status.get(0), status.get(1));
                });
                setDownloadStatusMap(statusMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //Process Type  list
            String processTypeFlag = (String) session.get("MODULE");
            String processTypeQry = dadb.getProcessTypeQry(processTypeFlag, "download", "");
            ArrayList<ArrayList<String>> processList = dbexecuter.execute_oracle_query_as_list(processTypeQry);

            HashMap<String, String> processTypeMap = new HashMap<>();
            if (processList != null && processList.size() > 0) {
                processList.forEach(processType -> {
                    processTypeMap.put(processType.get(0), processType.get(2));
                });
                setProcessTypeMap(processTypeMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end method

    public String getProcessTypeForData() {
        return processTypeForData;
    }

    public void setProcessTypeForData(String processTypeForData) {
        this.processTypeForData = processTypeForData;
    }

    public HashMap<String, String> getProcessTypeMap() {
        return processTypeMap;
    }

    public void setProcessTypeMap(HashMap<String, String> processTypeMap) {
        this.processTypeMap = processTypeMap;
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

    public HashMap<String, String> getDownloadTypeMap() {
        return downloadTypeMap;
    }

    public void setDownloadTypeMap(HashMap<String, String> downloadTypeMap) {
        this.downloadTypeMap = downloadTypeMap;
    }

    public HashMap<String, String> getDownloadStatusMap() {
        return downloadStatusMap;
    }

    public void setDownloadStatusMap(HashMap<String, String> downloadStatusMap) {
        this.downloadStatusMap = downloadStatusMap;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
