/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.processDetails;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessDetailGridAction extends ActionSupport implements SessionAware {

    Util utl;
    private Map<String, Object> session;

    private InputStream inputStream;

    private ProcessDetailBean detailsBeanObj;

    private String action;
    private String filterFlag;
    private String search;
    private String dataGridAction;
    private String templateCode;
    private Long processSeqNo;

    private int recordsPerPage;
    private int pageNumber;
    private int startIndex;
    private int endIndex;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;

    public ProcessDetailGridAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "ajax_success";
        String return_msg = "";

        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            if (client != null && asmt != null) {
                String entityCode = client.getEntity_code();
                String clientCode = client.getClient_code();

                if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                    if (!utl.isnull(getSearch())) {
                        ProcessDetailsSupport processDetailsSupport = new ProcessDetailsSupport();

                        if (getTotalRecords() > 0 && getRecordsPerPage() > 0 && getPageNumber() > 0) {
                            int l_record_MNL = 1;
                            int l_record_MXL = 10;
                            
                            int maxVal = (int) getTotalRecords();
                            int minVal = 1;

                            if (getTotalRecords() > getRecordsPerPage()) {
                                maxVal = getPageNumber() * getRecordsPerPage();
                                minVal = maxVal - getRecordsPerPage() + 1;
                                if (maxVal > getTotalRecords()) {
                                    maxVal = (int) getTotalRecords();
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
                            l_record_MXL = (l_start_page * l_records_to_add);
                            l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;
                            System.out.println("l_record_MNL.."+l_record_MNL);
                            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                            String data_sql = processDetailsSupport.getProcessDetailsQuery(getDetailsBeanObj(), client,asmt,
                                    l_record_MNL, l_record_MXL);
                          //  String data_sql = processDetailsSupport.getProcessDetailsQuery(entityCode, clientCode, asmt, getDetailsBeanObj(), 
                             //       l_record_MNL, l_record_MXL);
                            //ArrayList<ProcessDetailBean> processDetailsList = db.getGenericList(ProcessDetailBean ,data_sql);
                              ArrayList<ProcessDetailBean> processDetailsList = db.getGenericList(new ProcessDetailBean(), data_sql);
                            utl.generateSpecialLog("delete process details query", data_sql);
                            System.out.println("processDetailsList.."+data_sql);
                           
                            if (processDetailsList != null && !processDetailsList.isEmpty()) {
                                return_msg = processDetailsSupport.getProcessDetailsGrid(processDetailsList,utl);
                              
                            } else {
                                return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                            }
                        } else {
                            return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        
        utl.generateLog("Process dashboard grid return view", return_view);
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
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

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
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

    public int getRecordsPerPage() {
        return this.recordsPerPage;
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

    public ProcessDetailBean getDetailsBeanObj() {
        return detailsBeanObj;
    }

    public void setDetailsBeanObj(ProcessDetailBean detailsBeanObj) {
        this.detailsBeanObj = detailsBeanObj;
    }

    public Long getProcessSeqNo() {
        return processSeqNo;
    }

    public void setProcessSeqNo(Long processSeqNo) {
        this.processSeqNo = processSeqNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

}
