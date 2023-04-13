/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import hibernateObjects.LhssysCorrFileUploadTran;
import hibernateObjects.ViewClientMast;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class CorrImportDataGridAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";

        try {

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            Long l_client_loginid_seq;

            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }

            if (getTotalRecords() > 0) {
                if (getRecordsPerPage() > 0) {
                    if (getPageNumber() > 0) {

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
                        String showAll = "";
                        lhssysCorrFileUploadTranList = new CorrImportDB().getDataGridQuery(client.getClient_code(), asmt.getTdsTypeCode(), asmt.getAccYear(), Double.parseDouble(asmt.getQuarterNo()), l_record_MNL, l_record_MXL, showAll, getFilterData());

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }//End Method

    public CorrImportDataGridAction() {
        utl = new Util();

    }
    private final Util utl;
    private Map<String, Object> session;

    List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList;
    private String selectFileSeqs = "";

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private String code_level;
    private String IsParentRecord;
    private String update_data_row;

    private LhssysCorrFileUploadTran filterData;

    public List<LhssysCorrFileUploadTran> getLhssysCorrFileUploadTranList() {
        return lhssysCorrFileUploadTranList;
    }

    public void setLhssysCorrFileUploadTranList(List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList) {
        this.lhssysCorrFileUploadTranList = lhssysCorrFileUploadTranList;
    }

    public String getSelectFileSeqs() {
        return selectFileSeqs;
    }

    public void setSelectFileSeqs(String selectFileSeqs) {
        this.selectFileSeqs = selectFileSeqs;
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

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getIsParentRecord() {
        return IsParentRecord;
    }

    public void setIsParentRecord(String IsParentRecord) {
        this.IsParentRecord = IsParentRecord;
    }

    public String getUpdate_data_row() {
        return update_data_row;
    }

    public void setUpdate_data_row(String update_data_row) {
        this.update_data_row = update_data_row;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public LhssysCorrFileUploadTran getFilterData() {
        return filterData;
    }

    public void setFilterData(LhssysCorrFileUploadTran filterData) {
        this.filterData = filterData;
    }

}
