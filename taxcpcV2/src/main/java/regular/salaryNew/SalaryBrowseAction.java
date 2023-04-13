/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.SalaryTranLoadDAO;
import dao.ViewEmpCatgDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryBrowseAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private Util utl;
    private String dataGridAction;
    private InputStream inputStream;
    private String search;
    private String errorMessage;
    private String showFilter;

    private HashMap<String, String> deducteeCatgList;
    private HashMap<String, String> deductionList;
    private HashMap<String, String> filterTaxFlag;

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

    private SalaryTranLoadFilter salaryTranLoadFilter;

    public SalaryBrowseAction() {
        utl = new Util();
        deductionList = new HashMap<String, String>();
        deductionList.put("A", "ALL");
        deductionList.put("S", "SHORTFALL");

        deducteeCatgList = new HashMap<String, String>();
        deducteeCatgList.put("", "Select Deductee Category");

        filterTaxFlag = new HashMap<>();
        filterTaxFlag.put("Y", "YES");
        filterTaxFlag.put("N", "NO");

        salaryTranLoadFilter = new SalaryTranLoadFilter();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";
        StringBuilder sb = new StringBuilder();
        try {
            session.put("ACTIVE_TAB", "salaryEntryNew");
            setDataGridAction("salaryDataGridNew");
            setBulkDownloadFor("SALARY_BROWSE_DETAILS");

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

            if (asmt != null && asmt.getQuarterNo().equalsIgnoreCase("4") && asmt.getTdsTypeCode().equalsIgnoreCase("24Q")) {
                if (getSearch() != null && getSearch().equalsIgnoreCase("true")) {
                    SalaryTranLoadDAO salaryTranLoadDAO = factory.getSalaryTranLoadDAO();

                    Long total = salaryTranLoadDAO.getSalaryTranLoadDataCount(getSalaryTranLoadFilter(), client, asmt, utl);
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
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                    return_view = "filter_success";
                } else {
                    setTotalRecords(0l);
                    setTotalPages(0);
                    setPageNumber("0");
                    return_view = "success";
                }
            } else {
                setErrorMessage("Salary is only applicable in 24Q(Salary) Q4");
                return_view = "error";
            }
            if (!utl.isnull(getShowFilter()) && getShowFilter().equalsIgnoreCase("true")) {
                try {
                    ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
                    HashMap<String, String> empCatgAsLinkedHashMap = viewEmpCatgDAO.getEmpCatgAsLinkedHashMap();
                    sb.append("salary").append("#");
                    sb.append("<option value=\"\"").append("\">").append("Select Deductee Category").append("</option>");
                    empCatgAsLinkedHashMap.forEach((key, value) -> {
                        sb.append("<option value=\"").append(key).append("\">").append(value).append("</option>");
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
                return_view = "filter_success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
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

    public HashMap<String, String> getDeducteeCatgList() {
        return deducteeCatgList;
    }

    public void setDeducteeCatgList(HashMap<String, String> deducteeCatgList) {
        this.deducteeCatgList = deducteeCatgList;
    }

    public HashMap<String, String> getDeductionList() {
        return deductionList;
    }

    public void setDeductionList(HashMap<String, String> deductionList) {
        this.deductionList = deductionList;
    }

    public SalaryTranLoadFilter getSalaryTranLoadFilter() {
        return salaryTranLoadFilter;
    }

    public void setSalaryTranLoadFilter(SalaryTranLoadFilter salaryTranLoadFilter) {
        this.salaryTranLoadFilter = salaryTranLoadFilter;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public HashMap<String, String> getFilterTaxFlag() {
        return filterTaxFlag;
    }

    public void setFilterTaxFlag(HashMap<String, String> filterTaxFlag) {
        this.filterTaxFlag = filterTaxFlag;
    }

    public String getShowFilter() {
        return showFilter;
    }

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

}
