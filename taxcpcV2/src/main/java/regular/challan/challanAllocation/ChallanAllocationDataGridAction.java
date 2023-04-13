/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsSplRateMastDAO;
import dao.ViewTdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewTdsTranLoad;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ChallanAllocationDataGridAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String return_view = "grid_success";
        String return_value;
        StringBuilder sb = new StringBuilder();

        try {

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment ass = (Assessment) session.get("ASSESSMENT");
            
            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch())) {

                    if (getTotalRecords() > 0) {

                        if (getRecordsPerPage() > 0) {
                            if (getPageNumber() > 0) {
                                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                                TdsSplRateMastDAO tdsSplMastDAO = factory.getTdsSplRateMastDAO();
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

                                  ViewTdsTranLoadDAO maptdsChallanlaod = factory.getViewTdsTranLoadDAO();
                                  ChallanAllocationDB dbquery = new ChallanAllocationDB();
                                  String query = dbquery.challanRecordQuery(clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode(), getMapTdsChallanFltrSrch(), getSearch(), minVal,maxVal, utl);
                                  List<ChallanAllocationUnallocationEntity> listtdstran =  new DbGenericFunctionExecutor().getGenericList(new ChallanAllocationUnallocationEntity(), query);
                                   

                                    //  List<ViewTdsTranLoad> listtdstran = maptdsChallanlaod.readTDSDataByCode(clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode(), getMapTdsChallanFltrSrch(), getSearch(), minVal - 1, getRecordsPerPage(), utl);

                                    maptdsChallanlaod = factory.getViewTdsTranLoadDAO();
                                    MapTdsChallanGrossTotalList listtdstranData = maptdsChallanlaod.readTDSDataSumDetail(clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode(), getMapTdsChallanFltrSrch(), utl);

                                    ChallanAllocationSupport db = new ChallanAllocationSupport();
                                    db.getChallanAllocationDataGrid(sb, listtdstran, utl, getStartIndex(), getPageNumber(), getIdentifyFlag(), listtdstranData);
                                } else {
                                    addActionError("some error occured");
                                    return_view = "input";
                                }
                            }
                        }
                    } else {
                        sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                    }
                    sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");

                    inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

                } else if (getSetRecPerPage() > 0) {
                    session.put("CHALLANALLOCATIONRECPERPG", String.valueOf(getSetRecPerPage()));
                    return_value = "success";
                    return_view = "input_success";
                    inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
                }
            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_view;
    }

    public ChallanAllocationDataGridAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    Util utl;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private String identifyFlag;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private ChallanAllocationFilterEntity mapTdsChallanFltrSrch;
    InputStream inputStream;
    private String filterFlag;

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public ChallanAllocationFilterEntity getMapTdsChallanFltrSrch() {
        return mapTdsChallanFltrSrch;
    }

    public void setMapTdsChallanFltrSrch(ChallanAllocationFilterEntity mapTdsChallanFltrSrch) {
        this.mapTdsChallanFltrSrch = mapTdsChallanFltrSrch;
    }

    public String getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(String identifyFlag) {
        this.identifyFlag = identifyFlag;
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
