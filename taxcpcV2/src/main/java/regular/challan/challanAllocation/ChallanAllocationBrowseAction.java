/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewTdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ChallanAllocationBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnType = "success";
        StringBuilder sb = new StringBuilder();

        setBulkDownloadFor("TDS_CHALLAN_ALLOC");
        setDataGridAction("tdsChallanAllocationDataGrid");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment ass = (Assessment) session.get("ASSESSMENT");
            GetGlobalList getGlobalList = new GetGlobalList();
            tdsSectionList = getGlobalList.getSectionList(ass.getTdsTypeCode(), ass.getQuarterToDate());
            operatorList = getGlobalList.getOperatorList("--Select Tds Amount--", utl);
            allocationStatusList = getGlobalList.getAllocationStatus("", utl);
            tdsDeductReasonList = factory.getViewTdsDeductReasonDAO().getDeductReasonData(ass.getTdsTypeCode());
            ViewTdsTranLoadDAO viewTdsTranLoadDAO = factory.getViewTdsTranLoadDAO();

            long total = 0l;
            setTotalRecordsCount(total);
            int pages = 0;
            if (total > 0) {
                String recNumber = getRecordsPerPage();
                if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                    recNumber = (String) session.get("CHALLANALLOCATIONRECPERPG");
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
                try {
                    setTotalAllocatedAmount(getAllocatedAmount());
                } catch (Exception ex) {
                    //System.out.println("Amount format Exception: " + ex.getMessage());
                }
            } else {
                setPageNumber("0");
            }
            setTotalPages(pages);

            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                session.put("MAPTDSCHALLANLOADSRCH", getMapTdsChallanFltrSrch());
                ChallanAllocationDB db = new ChallanAllocationDB();
                String query = db.challanCountRecordQuery(clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode(), "", getMapTdsChallanFltrSrch(), getSearch(), utl);
                total =  new DbFunctionExecutorAsIntegar().execute_oracle_function_as_integar(query);
 //            total = viewTdsTranLoadDAO.getMapTdsChallanLoadCount(clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode(), "", getMapTdsChallanFltrSrch(), getSearch(), utl);
                setTotalRecordsCount(total);

                pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("CHALLANALLOCATIONRECPERPG");
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
                setTotalPages(pages);
                returnType = "success";
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnType;
    }
    Map<String, Object> session;
    Util utl;
    private String action;
    private String identifyFlag;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String pageNumber;
    private String dataGridAction;
    private String search;
    private String ChallanNo;
    private String tdsAmount;
    private String ChallanDate;
    private String totalAllocatedAmount;
    private String pendingForalloc;

    public String getPendingForalloc() {
        return pendingForalloc;
    }

    public void setPendingForalloc(String pendingForalloc) {
        this.pendingForalloc = pendingForalloc;
    }

  
    private String allocatedAmount;
    private String ChallanMonth;
    private String ParaRowidSeq;
    private String bulkDownloadFor;
    private ChallanAllocationFilterEntity mapTdsChallanFltrSrch;
    LinkedHashMap<String, String> tdsSectionList;
    LinkedHashMap<String, String> allocationStatusList;
    LinkedHashMap<String, String> operatorList;
    LinkedHashMap<String, String> deducteeLevelList;
    LinkedHashMap<String, String> categoryList;
    private LinkedHashMap<String, String> tdsDeductReasonList;
    private InputStream inputStream;

    public String getParaRowidSeq() {
        return ParaRowidSeq;
    }

    public void setParaRowidSeq(String ParaRowidSeq) {
        this.ParaRowidSeq = ParaRowidSeq;
    }

    public String getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(String identifyFlag) {
        this.identifyFlag = identifyFlag;
    }

    public LinkedHashMap<String, String> getTdsSectionList() {
        return tdsSectionList;
    }

    public void setTdsSectionList(LinkedHashMap<String, String> tdsSectionList) {
        this.tdsSectionList = tdsSectionList;
    }

    public LinkedHashMap<String, String> getAllocationStatusList() {
        return allocationStatusList;
    }

    public void setAllocationStatusList(LinkedHashMap<String, String> allocationStatusList) {
        this.allocationStatusList = allocationStatusList;
    }

    public LinkedHashMap<String, String> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(LinkedHashMap<String, String> operatorList) {
        this.operatorList = operatorList;
    }

    public LinkedHashMap<String, String> getDeducteeLevelList() {
        return deducteeLevelList;
    }

    public void setDeducteeLevelList(LinkedHashMap<String, String> deducteeLevelList) {
        this.deducteeLevelList = deducteeLevelList;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getChallanNo() {
        return ChallanNo;
    }

    public void setChallanNo(String ChallanNo) {
        this.ChallanNo = ChallanNo;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getChallanDate() {
        return ChallanDate;
    }

    public void setChallanDate(String ChallanDate) {
        this.ChallanDate = ChallanDate;
    }

    public String getTotalAllocatedAmount() {
        return totalAllocatedAmount;
    }

    public void setTotalAllocatedAmount(String totalAllocatedAmount) {
        this.totalAllocatedAmount = totalAllocatedAmount;
    }

    public String getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(String allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    public String getChallanMonth() {
        return ChallanMonth;
    }

    public void setChallanMonth(String ChallanMonth) {
        this.ChallanMonth = ChallanMonth;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ChallanAllocationBrowseAction() {
        utl = new Util();

        deducteeLevelList = new LinkedHashMap<>();
        deducteeLevelList.put("", "--Select Deducteee Level--");
        deducteeLevelList.put("A", "All Level");
        deducteeLevelList.put("L", "Login Level");

        categoryList = new LinkedHashMap<>();
        categoryList.put("", "--Select Category--");
        categoryList.put("C", "Company");
        categoryList.put("O", "Non-Company");

//        corrStatusList = new LinkedHashMap<>();
//        corrStatusList.put("", "--Select Correction Status--");
//        corrStatusList.put("all", "All");
//        corrStatusList.put("CNA", "Correction Not Approved");
//        corrStatusList.put("CA", "Correction Approved");
    }

    public LinkedHashMap<String, String> getTdsDeductReasonList() {
        return tdsDeductReasonList;
    }

    public void setTdsDeductReasonList(LinkedHashMap<String, String> tdsDeductReasonList) {
        this.tdsDeductReasonList = tdsDeductReasonList;
    }

    public LinkedHashMap<String, String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(LinkedHashMap<String, String> categoryList) {
        this.categoryList = categoryList;
    }

    public ChallanAllocationFilterEntity getMapTdsChallanFltrSrch() {
        return mapTdsChallanFltrSrch;
    }

    public void setMapTdsChallanFltrSrch(ChallanAllocationFilterEntity mapTdsChallanFltrSrch) {
        this.mapTdsChallanFltrSrch = mapTdsChallanFltrSrch;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
