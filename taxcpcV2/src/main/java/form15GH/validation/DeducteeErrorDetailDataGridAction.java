/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

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
public class DeducteeErrorDetailDataGridAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private ArrayList<ShowDeducteeErrorDetailBean> deducteeErrorList;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private InputStream inputStream;

    public DeducteeErrorDetailDataGridAction() {
        utl = new Util();
    }//end constructor 

    @Override
    public String execute() throws Exception {

        String l_return_view = "input_success";
        String return_value = "";
        StringBuilder grid = new StringBuilder();

        try {
            if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                if (getTotalRecords() > 0) {
                    if (getRecordsPerPage() > 0) {
                        if (getPageNumber() > 0) {
                            int maxVal = getTotalRecords();
                            int minVal = 1;
//
                            if (getTotalRecords() > getRecordsPerPage()) {
                                maxVal = getPageNumber() * getRecordsPerPage();
                                minVal = (int) maxVal - getRecordsPerPage() + 1;
                                if (maxVal > getTotalRecords()) {
                                    maxVal = getTotalRecords();
                                }
                            }
                            setStartIndex(minVal - 1);
                            setEndIndex((int) (maxVal - 1));

                            int l_start_page = 0;
                            if (getPageNumber() == 0) {
                                l_start_page = 1;
                            } else {
                                l_start_page = getPageNumber();
                            }

                            int l_records_to_add = getRecordsPerPage();
                            int l_record_MXL = (l_start_page * l_records_to_add);
                            int l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;

                            String l_entity_code = "";
                            String l_client_code = "";
                            String l_acc_year = "";

                            Assessment asmt = null;
                            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");

                            if (viewClientMast != null) {
                                l_entity_code = viewClientMast.getEntity_code();
                                l_client_code = viewClientMast.getClient_code();
                                asmt = (Assessment) session.get("ASSESSMENT");
                                l_acc_year = asmt.getAccYear();
                            }
//
                        ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                        ValidateError15GHSupport support_grid = new ValidateError15GHSupport();
//
                        String l_error_code = (String) session.get("deducteeErrorCode");
                        String l_query = proc_err.get15GHDeducteeDetailsQuery(asmt, l_entity_code, l_client_code, l_error_code, l_record_MNL, l_record_MXL);
                        utl.generateSpecialLog("15GH-PE-0005 (Deductee Error 2nd Screen record data grid Query)----", l_query);
//
                        DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();
                        ArrayList<ShowDeducteeErrorDetailBean> deducteeErrorListData = objList.getGenericList(new ShowDeducteeErrorDetailBean(), l_query);
//
                        grid = support_grid.deducteeDetailsGrid15GH(deducteeErrorListData);
                        }
                    }
                } else {
                    grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
//         
//            grid.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");
//            l_return_view = "input_success";
            inputStream = new ByteArrayInputStream(grid.toString().getBytes("UTF-8"));
            } else if (getSetRecPerPage() > 0) {
//                
                session.put("SHOWDEDUCTEEERRORDETAILSRCHPG", String.valueOf(getSetRecPerPage()));// records per page
                return_value = "success";
                l_return_view = "input_success";
                inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_view;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public ArrayList<ShowDeducteeErrorDetailBean> getDeducteeErrorList() {
        return deducteeErrorList;
    }

    public void setDeducteeErrorList(ArrayList<ShowDeducteeErrorDetailBean> deducteeErrorList) {
        this.deducteeErrorList = deducteeErrorList;
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

}
