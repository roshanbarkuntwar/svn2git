/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadStatusGridAction extends ActionSupport implements SessionAware {

    Util utl;

    private Map<String, Object> session;

    private String search;
    private String clientCode;
    private String branchCode;
    private String tokenNo;
    private String downloadType;
    private String downloadStatus;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private String processTypeForData;
    InputStream inputStream;
    private String processType;

    public DownloadStatusGridAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "status_grid";
        String return_value;
        StringBuilder sb = new StringBuilder();

        try {
            String moduleFlag = (String) session.get("MODULE");
           String BLOB_FILE_DOWNLOAD_FLAG = (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") != null ? (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") : "";
      
            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment ass = (Assessment) session.get("ASSESSMENT");

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
                        String additional_whereclause = "";
                        if (!utl.isnull(getTokenNo())) {
                            additional_whereclause += " AND A.PROCESS_SEQNO = '" + getTokenNo().trim() + "' \n";
                        }
                        if (!utl.isnull(getDownloadStatus())) {
                            additional_whereclause += " AND A.PROCESS_STATUS = '" + getDownloadStatus() + "' \n";
                        }
                        if (!utl.isnull(getProcessTypeForData())) {
                            additional_whereclause += " AND A.PROCESS_TYPE = '" + getProcessTypeForData().trim() + "' \n";
                        }

                        String pagination_clause = " WHERE SLNO BETWEEN " + l_record_MNL + " AND " + l_record_MXL;

                        DownloadActionDB status_obj = new DownloadActionDB();
                        String query = status_obj.getDownloadStatusData(ass, clientMast, additional_whereclause, pagination_clause, moduleFlag, "D"); // provide hardcode D only download section and its refer view_lhssys_process_type
                        utl.generateSpecialLog("Download section browse Query", query);

                        DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                        ArrayList<DownloadStatusBean> status_list = db.getGenericList(new DownloadStatusBean(), query);

                        DownloadStatusSupport status_details = new DownloadStatusSupport();
                        sb = status_details.getDownloadStatusGrid(status_list,BLOB_FILE_DOWNLOAD_FLAG);
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
                session.put("PROCESSSTATUSRECPERPG", String.valueOf(getSetRecPerPage()));
                return_value = "success";
                return_view = "input_success";
                inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessTypeForData() {
        return processTypeForData;
    }

    public void setProcessTypeForData(String processTypeForData) {
        this.processTypeForData = processTypeForData;
    }

}
