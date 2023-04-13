/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewTdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class ChallanBrowseAction extends ActionSupport implements SessionAware {

    Util utl;
    private LinkedHashMap<String, String> selectAllocationStatus;
    private LinkedHashMap<String, String> selectChallanStatus;
    private LinkedHashMap<String, String> selectSection;
    private LinkedHashMap<String, String> selectChallanLevel;
    private LinkedHashMap<String, String> selectMinorHead;
    private String dataGridAction;
    private String search;
    private String action;
    private String recordsPerPage;
    private int totalPages;
    private String pageNumber;
    private String readonly;
    private String filterFlag;
    private String showFilter;
    private long totalRecords;
    private long totalRecordsCount;
    private ChallanFilterEntity tranChallanFltrSrch;
    private boolean fromAllocatedChallan;
    private String bulkDownloadFor;
    private String client_catg_code="";

    public ChallanBrowseAction() {

        utl = new Util();

        selectAllocationStatus = new LinkedHashMap<>();
        selectAllocationStatus.put("", "Select Allocation Status");
        selectAllocationStatus.put("ALL", "All Challans");
        selectAllocationStatus.put("ALC", "Fully Allocated Challans");
        selectAllocationStatus.put("PAC", "Partially Allocated Challans");
        selectAllocationStatus.put("UAC", "Unallocated Challans");
        selectAllocationStatus.put("OBC", "Overbooked Challans");

        selectChallanStatus = new LinkedHashMap<>();
        selectChallanStatus.put("", "Select Challan Status");
        selectChallanStatus.put("P", "All Challans");
        selectChallanStatus.put("M", "Match Challans");
        selectChallanStatus.put("U", "Unmatch Challans");

        selectSection = new LinkedHashMap<>();
        selectSection.put("", "Select Section");

        selectChallanLevel = new LinkedHashMap<>();
        selectChallanLevel.put("A", "All Level");
        selectChallanLevel.put("L", "Login Level");

        selectMinorHead = new LinkedHashMap<>();
        selectMinorHead.put("200", "Normal Tax");
        selectMinorHead.put("400", "Assessment Tax");

    }

    @Override
    public String execute() throws Exception {

        setDataGridAction("challanDataGridAction");
        setBulkDownloadFor("TDS_CHALLANS");

        session.put("ACTIVE_TAB", "tdsChallan");
        String result = "success";
        StringBuilder sb = new StringBuilder();

        Assessment assessment = (Assessment) session.get("ASSESSMENT");
        try {
           

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
          //  String client_catg= clientMast.getClient_catg_code()== null ? "":clientMast.getClient_catg_code();
           if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("add")) {
                    setReadonly("false");
                    setSelectSection(this.getListOfSection(assessment));
                    if(clientMast.getClient_type_code() != null){
            setClient_catg_code(clientMast.getClient_type_code());
          }System.out.println("hhhh66-->"+clientMast.getClient_type_code());
                    return "challanentry";
                }
            }
          
            ViewTdsChallanTranLoadDAO chalantran = factory.getViewTdsChallanTranLoadDAO();

            long total = 0L;
            setTotalRecords(total);
            setPageNumber("0");
            setTotalRecordsCount(total);
            int pages = 0;
            if (total > 0) {
                String recNumber = (String) session.get("MANAGECHALLANRECPERPG");// records per page
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
                setPageNumber("1");
            } else {
                setPageNumber("0");
            }

            setTotalPages(pages);

            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {

                ChallanFilterEntity tdsChallanTranFilterSrch = getTranChallanFltrSrch();
               // total = chalantran.getTdsChallanTranLoadCount(clientMast.getEntity_code(), clientMast.getClient_code(), assessment, tdsChallanTranFilterSrch,
               //         isFromAllocatedChallan(), utl);getTdsChallanTranLoadCount
                ChallanDashboardQuery query =new ChallanDashboardQuery();
                String countquery = query.challanCountRecordQuery(clientMast.getEntity_code(), clientMast.getClient_code(), assessment, tdsChallanTranFilterSrch,isFromAllocatedChallan(), utl);
                System.out.println("clallan queryyy-- countquery--"+countquery);
                total = new DbFunctionExecutorAsIntegar().execute_oracle_function_as_integar(countquery);
                utl.generateLog("Challan details count", total);

                //paginator logic start..
                setTotalRecordsCount(total);
                pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("MANAGECHALLANRECPERPG");
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
                result = "filter_success";
            } else {
                setTotalPages(pages);
                result = "success";
            }
            if (!utl.isnull(getShowFilter()) && getShowFilter().equalsIgnoreCase("true")) {
                try {
                    LinkedHashMap<String, String> sectionList = this.getListOfSection(assessment);
                    sb.append("challan").append("#");
                    sectionList.forEach((key, value) -> {
                        sb.append("<option value=\"").append(key).append("\">").append(value).append("</option>");
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result = "grid_success";
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return result;
    }
    private Map<String, Object> session;
    private InputStream inputStream;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public LinkedHashMap<String, String> getSelectAllocationStatus() {
        return selectAllocationStatus;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public void setSelectAllocationStatus(LinkedHashMap<String, String> selectAllocationStatus) {
        this.selectAllocationStatus = selectAllocationStatus;
    }

    public LinkedHashMap<String, String> getSelectChallanStatus() {
        return selectChallanStatus;
    }

    public void setSelectChallanStatus(LinkedHashMap<String, String> selectChallanStatus) {
        this.selectChallanStatus = selectChallanStatus;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public LinkedHashMap<String, String> getSelectChallanLevel() {
        return selectChallanLevel;
    }

    public void setSelectChallanLevel(LinkedHashMap<String, String> selectChallanLevel) {
        this.selectChallanLevel = selectChallanLevel;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public ChallanFilterEntity getTranChallanFltrSrch() {
        return tranChallanFltrSrch;
    }

    public void setTranChallanFltrSrch(ChallanFilterEntity tranChallanFltrSrch) {
        this.tranChallanFltrSrch = tranChallanFltrSrch;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LinkedHashMap<String, String> getSelectMinorHead() {
        return selectMinorHead;
    }

    public void setSelectMinorHead(LinkedHashMap<String, String> selectMinorHead) {
        this.selectMinorHead = selectMinorHead;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean isFromAllocatedChallan() {
        return fromAllocatedChallan;
    }

    public void setFromAllocatedChallan(boolean fromAllocatedChallan) {
        this.fromAllocatedChallan = fromAllocatedChallan;
    }

    public String getShowFilter() {
        return showFilter;
    }

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    public LinkedHashMap<String, String> getListOfSection(Assessment assessment) {
        GetGlobalList sectionlist = new GetGlobalList();
        return sectionlist.getSectionList(assessment.getTdsTypeCode(), assessment.getQuarterToDate());

    }

    public String getClient_catg_code() {
        return client_catg_code;
    }

    public void setClient_catg_code(String client_catg_code) {
        this.client_catg_code = client_catg_code;
    }

}
