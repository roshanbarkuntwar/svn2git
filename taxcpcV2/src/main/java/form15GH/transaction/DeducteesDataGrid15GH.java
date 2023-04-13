/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbQueryExecutorAsList;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class DeducteesDataGrid15GH extends ActionSupport implements SessionAware {

    public DeducteesDataGrid15GH() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String return_view = "grid_success";

        StringBuilder sb = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
            ViewClientMast clientmast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            String code_level_val = clientmast.getCode_level();
            setCode_level(code_level_val);
            setIsClientTranLevel((String) session.get("CLIENT_TRAN_LEVEL"));

            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {

                    if (getTotalRecords() > 0 && getPageNumber() > 0) {
                        int maxVal = getTotalRecords();
                        int minVal = 1;
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
                        DeducteeSearchDetail15GH ds = getDeducteeSearch();

                        ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");

                        if (clientMast != null) {
                            Assessment asmt = (Assessment) session.get("ASSESSMENT");

                            String pan_filter = "";
                            String dname_filter = "";
                            String vffilter = "";
                            String invGF_filter = "";
                            String incompAmt_filter = "";
                            String unino_filter = "";
                            String custid_filter = "";

                            if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                                if (!utl.isnull(ds.getPanno())) {
                                    pan_filter = " and t.panno= '" + ds.getPanno().toUpperCase() + "' \n";
                                }
                                if (!utl.isnull(ds.getDeductee_name())) {
                                    dname_filter = " and t.deductee_name LIKE '%" + ds.getDeductee_name().toUpperCase() + "%' \n";
                                }
                                if (!utl.isnull(ds.getVerifiedby_flag_name())) {
                                    if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Not Verified")) {
                                        vffilter = " and nvl(t.verifiedby_flag,'N') ='N'\n";
                                    } else if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Invalid")) {
                                        vffilter = " and nvl(t.verifiedby_flag,'X') ='X' \n";
                                    } else if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Verified")) {
                                        vffilter = " and nvl(t.verifiedby_flag,'Y') ='Y' \n";
                                    }
                                }
                                if (!utl.isnull(ds.getInvalidGHFlag())) {
                                    if (ds.getInvalidGHFlag().equalsIgnoreCase("V")) {
                                        invGF_filter = " and (t.reference_no is not null and NVL(invalid_rec_flag, 0) = 0) \n";
                                    } else if (ds.getInvalidGHFlag().equalsIgnoreCase("INV")) {
                                        invGF_filter = " and (t.reference_no is not null and NVL(invalid_rec_flag, 0) != 0) \n";
                                    }
                                }

                                if (!utl.isnull(ds.getUinNo())) {
                                    unino_filter = " and t.reference_no = '" + ds.getUinNo() + "' \n";
                                }

                                if (!utl.isnull(ds.getCustId())) {
                                    custid_filter = " and t.deduction_ref_no = '" + ds.getCustId() + "' \n";
                                }

                                if (!utl.isnull(ds.getIncompleteGhAmountFlag())) {
                                    if (ds.getIncompleteGhAmountFlag().equalsIgnoreCase("I")) {
                                        incompAmt_filter
                                                = " and not exists (select 1 from deductee_bflag_amt_tran dat\n"
                                                + "                              where dat.entity_code = t.entity_code and dat.client_code = t.client_code and dat.acc_year = t.acc_year \n"
                                                + "                              and dat.quarter_no = quarter_no and dat.tds_type_code = tds_type_code and dat.deductee_code = deductee_code and dat.Panno = t.panno )\n";

                                    } else if (ds.getIncompleteGhAmountFlag().equalsIgnoreCase("C")) {
                                        incompAmt_filter
                                                = " and exists (select 1 from deductee_bflag_amt_tran dat\n"
                                                + "                              where dat.entity_code = t.entity_code and dat.client_code = t.client_code and dat.acc_year = t.acc_year \n"
                                                + "                              and dat.quarter_no = quarter_no and dat.tds_type_code = tds_type_code and dat.deductee_code = deductee_code and dat.Panno = t.panno )\n";
                                    }
                                }

                            }

                            Form15GHDB dataQuery = new Form15GHDB();
                            String form15GHDataQuery = dataQuery.get15GHDataGridQuery(asmt, clientmast, pan_filter, dname_filter, vffilter, invGF_filter, unino_filter, custid_filter, incompAmt_filter, l_record_MXL, l_record_MNL);
//                            System.out.println("15query--" + form15GHDataQuery);

                            DbQueryExecutorAsList dbQueryList = new DbQueryExecutorAsList();
                            List<ViewDeducteeMastDetailPOJO15GH> deducteesByTypeList = dbQueryList.getDeductee15GHDetailsList(form15GHDataQuery, utl);

                            Deductee15GHSupport deduteeData = new Deductee15GHSupport();
//                            System.out.println("deducteesByType--size--->" + deducteesByTypeList.size());
                            sb = deduteeData.getDeducteeData(deducteesByTypeList, clientmast, getStartIndex(), getPageNumber());
                        }
                    } else {
                        sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                    }
                } else {
                    session.put("DEDUCTERECPERPG", String.valueOf(getSetRecPerPage()));// records per page
                    sb.append("success");
                    return_view = "grid_success";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return return_view;
    }

    Map<String, Object> session;
    InputStream inputStream;
    private String code_level;
    private String IsClientTranLevel;
    private String update_data_row;
    private String filterFlag;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    //  private List<ViewDeducteeMast> deducteesByType;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private DeducteeSearchDetail15GH deducteeSearch;
    Util utl;

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

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getIsClientTranLevel() {
        return IsClientTranLevel;
    }

    public void setIsClientTranLevel(String IsClientTranLevel) {
        this.IsClientTranLevel = IsClientTranLevel;
    }

    public String getUpdate_data_row() {
        return update_data_row;
    }

    public void setUpdate_data_row(String update_data_row) {
        this.update_data_row = update_data_row;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public DeducteeSearchDetail15GH getDeducteeSearch() {
        return deducteeSearch;
    }

    public void setDeducteeSearch(DeducteeSearchDetail15GH deducteeSearch) {
        this.deducteeSearch = deducteeSearch;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

}
