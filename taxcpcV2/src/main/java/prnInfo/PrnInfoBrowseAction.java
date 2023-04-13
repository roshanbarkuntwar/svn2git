/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prnInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.LhssysTdsReturnTran;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class PrnInfoBrowseAction extends ActionSupport implements SessionAware {

    private String bulkDownloadFor;

    @Override
    public String execute() {
        String returnType = "success";
        try {
            session.put("ACTIVE_TAB", "prnInfo");
            setBulkDownloadFor("PRN_DETAILS");
            
            String module = (String) session.get("MODULE") != null ? (String) session.get("MODULE") : "";
            String moduleFlag = "R";
            if (!utl.isnull(module) && module.equalsIgnoreCase("Correction")) {
                moduleFlag = "C";
            }
            if (!utl.isnull(module) && module.equalsIgnoreCase("G")) {
                moduleFlag = "G";
            }

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");

            if (client != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {
                    setDataGridAction("prnInfoDataGrid");
                    if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                        PrnInfoDB db = new PrnInfoDB();
                        String l_query = db.getPrnCountQuery(asmt, client, utl, moduleFlag, getPrnUpdateFlag(), getFilterTan(), getProcessLevel());
                        utl.generateSpecialLog("PRN Query", l_query);
                        DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                        long total = objDBListCount.execute_oracle_function_as_integar(l_query);

                        //paginator logic start..
                        setTotalRecordsCount(total);
                        int pages = 0;
                        if (total > 0) {
                            String recNumber = getRecordsPerPage();
                            if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                                recNumber = (String) session.get("PRNRECPERPG");
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
                        if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                            String return_data = "";
                            if (getTotalRecordsCount() != 0) {
                                return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                            } else {
                                return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                            }
                            returnType = "ajax_success";
                            inputStream = new ByteArrayInputStream(return_data.getBytes("UTF-8"));
                        }
                    }
                    else {
                        returnType = "success";
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnType;
    }

    public PrnInfoBrowseAction() {
        prnUpdateFlagList = new LinkedHashMap<String, String>();
        prnUpdateFlagList.put("", "Select PRN Status");
        prnUpdateFlagList.put("U", "Updated");
        prnUpdateFlagList.put("N", "Not Updated");
        utl = new Util();
    }
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String browseAction;
    private String dataGridAction;
    private String action;
    private String rowid;
    private String search;
    private LhssysTdsReturnTran lhssysTdsReturnTran;
    private File prnFile;
    private String prnFileContentType;
    private String prnFileFileName;
    private String msg;
    private String prnUpdateFlag;
    private String module;
    private String filterFlag;
    private String processLevel;
    private LinkedHashMap<String, String> prnUpdateFlagList;
    private String filterTan;
    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
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

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public LhssysTdsReturnTran getLhssysTdsReturnTran() {
        return lhssysTdsReturnTran;
    }

    public void setLhssysTdsReturnTran(LhssysTdsReturnTran lhssysTdsReturnTran) {
        this.lhssysTdsReturnTran = lhssysTdsReturnTran;
    }

    public File getPrnFile() {
        return prnFile;
    }

    public void setPrnFile(File prnFile) {
        this.prnFile = prnFile;
    }

    public String getPrnFileContentType() {
        return prnFileContentType;
    }

    public void setPrnFileContentType(String prnFileContentType) {
        this.prnFileContentType = prnFileContentType;
    }

    public String getPrnFileFileName() {
        return prnFileFileName;
    }

    public void setPrnFileFileName(String prnFileFileName) {
        this.prnFileFileName = prnFileFileName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPrnUpdateFlag() {
        return prnUpdateFlag;
    }

    public void setPrnUpdateFlag(String prnUpdateFlag) {
        this.prnUpdateFlag = prnUpdateFlag;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public LinkedHashMap<String, String> getPrnUpdateFlagList() {
        return prnUpdateFlagList;
    }

    public void setPrnUpdateFlagList(LinkedHashMap<String, String> prnUpdateFlagList) {
        this.prnUpdateFlagList = prnUpdateFlagList;
    }

    public String getFilterTan() {
        return filterTan;
    }

    public void setFilterTan(String filterTan) {
        this.filterTan = filterTan;
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

    private void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

}
