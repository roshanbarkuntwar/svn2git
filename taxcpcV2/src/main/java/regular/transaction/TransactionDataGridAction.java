/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class TransactionDataGridAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "grid_success";
        String return_value;
        session.put("ERRORCLASS", MessageType.errorMessage);
        StringBuilder sb = new StringBuilder();
        String flag = "";
        try {

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment ass = (Assessment) session.get("ASSESSMENT");

            try {
                flag = (String) session.get("ALLASSESSMENT");
            } catch (Exception e) {

            }

            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch())) {

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

                                if (clientMast != null) {

                                    TransactionDB db = new TransactionDB();
                                    String l_query = db.getTransactionDataGridQuery(clientMast, ass, getTranFilter(), l_record_MNL, l_record_MXL, flag);
                                    String grossQuery = db.getTdsTranGrossSumQuery(clientMast, ass, getTranFilter(), flag);
                                    utl.generateSpecialLog("Regular tran data query: ", l_query);
                                    DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();

                                    List<TransactionBrowseEntity> transactionBrowseEntityList = dbQueryList.getGenericList(new TransactionBrowseEntity(), l_query);

                                    DbFunctionExecutorAsString objGetDataListThroughWorkbook1 = new DbFunctionExecutorAsString();
                                    String arrData = objGetDataListThroughWorkbook1.execute_oracle_function_as_string(grossQuery);

                                    double G_sum_tranamt = 0d;
                                    double G_sum_tds = 0d;
                                    if (arrData != null) {
                                        try {
                                            String arr1 = arrData.split("#")[0];
                                            arr1 = utl.isnull(arr1) ? "0" : arr1;
                                            G_sum_tranamt = Double.parseDouble(arr1);
                                            String arr2 = arrData.split("#")[1];
                                            arr2 = utl.isnull(arr2) ? "0" : arr2;
                                            G_sum_tds = Double.parseDouble(arr2);
                                        } catch (Exception e) {
//                                        e.printStackTrace();
                                        }

                                    }

                                    TransactionSupport ds = new TransactionSupport();
                                    sb = ds.getBrowseGrid(transactionBrowseEntityList, clientMast,ass, sb, getStartIndex(), getPageNumber(), G_sum_tranamt, G_sum_tds, flag,l_query);
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
                    session.put("TRANSACTIONRECPERPG", String.valueOf(getSetRecPerPage()));
                    return_value = "success";
                    return_view = "input_success";
                    inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
                }

            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);
//                sb.append("<div class=\"col-md-12 text-center p-1 see-record\">").append("Click The Above Button  ").append("<button type=\"button\" class=\"btn mx-1 btn-primary addon-btn filter-inner-btn\"><i class=\"fa fa-search\"></i></button>").append("To See The Records").append("</div>");
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }//end method

    public TransactionDataGridAction() {
        utl = new Util();
    }
    Util utl;
    private Map<String, Object> session;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private String filterFlag;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private TransactionFilterEntity tranFilter;

    public TransactionFilterEntity getTranFilter() {
        return tranFilter;
    }

    public void setTranFilter(TransactionFilterEntity tranFilter) {
        this.tranFilter = tranFilter;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
