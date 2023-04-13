package regular.dashboard.ldcAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
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
public class LowDeduCertifiAllocation extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "success";
        String return_msg = "";

        try {
            ViewClientMast clientmast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            String acc_year = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();
            String tds_type_code = asmt.getTdsTypeCode();

            if (clientmast != null) {
                setDataGridAction("lowDeduCertifiAllocaData");
                setBulkDownloadFor("LOWER_DEDUCTION");
                if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                    String filter_whrClause = "";
                    if (!utl.isnull(getAllocatedFlag())) {
                        if (getAllocatedFlag().equalsIgnoreCase("notAllocated")) {
                            filter_whrClause += "--Not allocated\n ";
                            filter_whrClause += "AND NVL(T.AMOUNT_CONSUMED, 0) = 0\n ";

                        } else if (getAllocatedFlag().equalsIgnoreCase("allocated")) {
                            filter_whrClause += "--Allocated\n ";
                            filter_whrClause += "AND NVL(T.AMOUNT_CONSUMED, 0) > 0\n ";
                        } else if (getAllocatedFlag().equalsIgnoreCase("notAllocatedAFlag")){
                            
                             //System.out.println("getAllocatedFlag()--"+getAllocatedFlag());
                        }
                    }
                    if (!utl.isnull(getCertificateNo())) {
                        filter_whrClause += "AND T.CERTIFICATE_NO = '" + getCertificateNo() + "'\n ";
                    }
                    if (!utl.isnull(getDeducteePanNo())) {
                        filter_whrClause += "AND T.DEDUCTEE_PANNO = '" + getDeducteePanNo() + "'\n ";
                    }
                   long total = new LowDeduCertifiAllocationSuppo().getTotalCount(clientmast.getEntity_code(), clientmast.getClient_code(), acc_year, quarterNo, tds_type_code, filter_whrClause,getAllocatedFlag(),utl);
                    setTotalRecords(total);
                    setPageNumber("0");
                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {
                        String recNumber = (String) session.get("LOWDEDUCTEERECPERPG");// records per page
                        setRecordsPerPage(utl.isnull(recNumber) ? "10" : recNumber);
                        setRecordsPerPage(utl.isnull(getRecordsPerPage()) ? "10" : getRecordsPerPage());
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
                        return_view = "ajax_success";

                    }
                } else {
                    setTotalRecords(0l);
                    setTotalPages(0);
                    setPageNumber("0");
                    return_view = "success";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
    }

    public LowDeduCertifiAllocation() {
        utl = new Util();
        allocatedFlagList = new LinkedHashMap<>();
        allocatedFlagList.put("", "Allocated + Un-Allocated ");
        allocatedFlagList.put("allocated", "Allocated");
        allocatedFlagList.put("notAllocated", "Un-Allocated");
        allocatedFlagList.put("notAllocatedAFlag", "Un-allocated (None A-Flag)");
        //Un-allocated (None A-Flag)
    }

    private LinkedHashMap<String, String> allocatedFlagList;
    private final Util utl;
    private Map<String, Object> session;
    private String allocatedFlag;
    private String certificateNo;
    private String deducteePanNo;
    private String dataGridAction;
    private String bulkDownloadFor;
    private long totalRecordsCount;
    private long totalRecords;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String search;
    private InputStream inputStream;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getDeducteePanNo() {
        return deducteePanNo;
    }

    public void setDeducteePanNo(String deducteePanNo) {
        this.deducteePanNo = deducteePanNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public LinkedHashMap<String, String> getAllocatedFlagList() {
        return allocatedFlagList;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setAllocatedFlagList(LinkedHashMap<String, String> allocatedFlagList) {
        this.allocatedFlagList = allocatedFlagList;
    }

    public String getAllocatedFlag() {
        return allocatedFlag;
    }

    public void setAllocatedFlag(String allocatedFlag) {
        this.allocatedFlag = allocatedFlag;
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

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
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

}
