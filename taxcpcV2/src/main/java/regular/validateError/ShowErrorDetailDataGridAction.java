/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ShowErrorDetailDataGridAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    Util utl;
    private ArrayList<ShowErrorDetailsBean> showErrorDetailBeanList;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;

    private String search;
    private String filterFlag;
    private String TdsTypeCode;
    private String ReadonlyFlag;
    private String l_error_type_code;
    private String tds_code;
    private String deductee_code;
    private String deductee_panno;
    private String l_error_code;

    private LinkedHashMap<String, String> selectSection;
    private LinkedHashMap<String, String> selectDeductReason;
    private LinkedHashMap<String, String> selectRateType;
    private HashMap<String, String> select_country;
    private LinkedHashMap<String, String> selectRemittanceNature;

    private FilterTdsErrorDataList objErrFilterList;
    private InputStream inputStream;

    public ShowErrorDetailDataGridAction() {
        utl = new Util();

        selectSection = new LinkedHashMap<>();
        selectDeductReason = new LinkedHashMap<>();

        selectRateType = new LinkedHashMap<>();
        selectRateType.put("I", "Income Tax Rate");
        selectRateType.put("D", "DTAA Rate");
        select_country = new LinkedHashMap<>();
        selectRemittanceNature = new LinkedHashMap<>();
    }

    @Override
    public String execute() throws Exception {
        String l_return_view = "grid_success";
        String return_value = "";

        StringBuilder grid = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            try {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                setSelectSection(factory.getTdsMastDAO().getSectionAsHashMap(asmt.getTdsTypeCode(), asmt.getQuarterToDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                setSelectRemittanceNature(factory.getViewRemittanceNatureDAO().getRemittanceAsLinkedHashMap());
                setSelect_country(factory.getCountryMastDAO().getCountryCodeAsHashMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
//            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
            if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
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

                            String l_entity_code = "";
                            String l_client_code = "";

                            String l_tds_type_code = "";
                            Assessment asmt = null;
                            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                            if (viewClientMast != null) {
                                l_entity_code = viewClientMast.getEntity_code();
                                l_client_code = viewClientMast.getClient_code();
                                asmt = (Assessment) session.get("ASSESSMENT");

                                l_tds_type_code = asmt.getTdsTypeCode();
                                setTdsTypeCode(l_tds_type_code);
                            }

                            String h_client_code = (String) session.get("summary_client_code");
                            String screenCallFrom = (String) session.get("screenCallFrom");

                            FilterTdsErrorDataList objErrorFilterParam = getObjErrFilterList() == null ? null : getObjErrFilterList();
                            String additionalWhereClause = this.getWhereClauseOfFilters(objErrorFilterParam);

                            GetDataListThroughWorkbook1 objGetDataListThroughWorkbook = new GetDataListThroughWorkbook1();
                            DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();

                            ProcessErrorsDB proc_err = new ProcessErrorsDB();
                            ValidateErrorSupport validateSupport = new ValidateErrorSupport();

                            String l_query = proc_err.getDeducteeTransactionErrorDetailQuery(asmt, l_entity_code, l_client_code, getObjErrFilterList(),
                                    l_error_type_code, l_error_code, l_tds_type_code, h_client_code, screenCallFrom, getTds_code(),
                                    getDeductee_panno(), additionalWhereClause, l_record_MNL, l_record_MXL);

                            utl.generateSpecialLog("RPE-0007 (Deductee transaction error detail screen 2/3 data grid query)----", l_query);

                            ArrayList<ShowErrorDetailsBean> errorDetailList = dbQueryList.getGenericList(new ShowErrorDetailsBean(), l_query);
                            if (errorDetailList.size() > 0) {
                                setTotalRecordsCount(errorDetailList.size());
                            }
                            String l_screen3_gross_sum_query = proc_err.getThirdScreenGrossSumQuery(asmt, l_entity_code, l_client_code, l_error_type_code,
                                    l_error_code, l_tds_type_code, h_client_code, getTds_code(), getDeductee_panno(), additionalWhereClause);

                            utl.generateSpecialLog("RPE-0007 (Gross Sum screen 3 data grid query)----", l_screen3_gross_sum_query);

                            ArrayList<String> arrData = objGetDataListThroughWorkbook.execute_oracle_query_as_arrlist(l_screen3_gross_sum_query);

                            grid = validateSupport.errorDetailsGrid(errorDetailList, asmt, getSelectSection(), getSelectRateType(), getSelectRemittanceNature(),
                                    getSelect_country(), l_record_MNL, l_record_MXL, additionalWhereClause, arrData);
                        }
                    }
                } else {
                    grid.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                grid.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");
                grid.append("<input type=\"hidden\" id=\"hidden_tds_type_code\" name=\"tdsTypeCode\" value=\"").append(getTdsTypeCode()).append("\" />");

                return_value = grid.toString();

            } else if (getSetRecPerPage() > 0) {
                session.put("SHOWERRORDETAILSRCHPG", String.valueOf(getSetRecPerPage()));// records per page
                //return_value = "successs";
                return_value = grid.append(GlobalMessage.PAGINATION_NO_RECORDS).toString();
                l_return_view = "input_success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        utl.generateLog("Error detail view name: ", l_return_view);
        inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
        return l_return_view;
    }// end method

    public String getAdditionalWhereClause(String condition, String fieldName, String fieldValue1, String fieldValue2, boolean isDate) {
        String additionalWhereClause = "";

        if (!utl.isnull(condition)) {
            if (condition.equalsIgnoreCase("EQ")) {
                if (isDate) {
                    additionalWhereClause = "and " + fieldName + " = to_date('" + fieldValue1 + "','dd-MM-yyyy')\n";
                } else {
                    additionalWhereClause = "and " + fieldName + " = '" + fieldValue1 + "'\n";
                }
            } else if (condition.equalsIgnoreCase("NEQ")) {
                if (isDate) {
                    additionalWhereClause = "and " + fieldName + " != to_date('" + fieldValue1 + "','dd-MM-yyyy')\n";
                } else {
                    additionalWhereClause = "and " + fieldName + " != '" + fieldValue1 + "'\n";
                }
            } else if (condition.equalsIgnoreCase("BW")) {
                additionalWhereClause = "and upper(" + fieldName + ")  like upper('" + fieldValue1 + "%')\n";

            } else if (condition.equalsIgnoreCase("EW")) {
                additionalWhereClause = "and upper(" + fieldName + ")  like upper('%" + fieldValue1 + "')\n";

            } else if (condition.equalsIgnoreCase("LIKE")) {
                additionalWhereClause = "and upper(" + fieldName + ")  like upper('%" + fieldValue1 + "%')\n";

            } else if (condition.equalsIgnoreCase("Not LIKE")) {
                additionalWhereClause = "and upper(" + fieldName + ") not like upper('%" + fieldValue1 + "%')\n";

            } else if (condition.equalsIgnoreCase("BTWN")) {
                if (!utl.isnull(fieldValue2)) {
                    if (!isDate) {
                        additionalWhereClause = "and upper(" + fieldName + ") between upper('" + fieldValue1 + "') and upper('" + fieldValue2.trim().toUpperCase() + "')\n";
                    } else {
                        additionalWhereClause = "and to_date(upper(" + fieldName + ")) between upper('" + fieldValue1 + "') and upper('" + fieldValue2.trim().toUpperCase() + "')\n";
                    }
                }
            }
        }
        return additionalWhereClause;

    }// end method

    public String getWhereClauseOfFilters(FilterTdsErrorDataList objErrorFilterParam) {
        String additionalWhereClause = "";

        if (objErrorFilterParam != null) {
//            if (!utl.isnull(objErrorFilterParam.getDeductee_name_condition()) && !utl.isnull(objErrorFilterParam.getDeductee_name())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getDeductee_name_condition(), "a.deductee_name", objErrorFilterParam.getDeductee_name().trim(), null, false);
//            }
            if (!utl.isnull(objErrorFilterParam.getDeductee_name())) {
                additionalWhereClause += " and   upper(a.deductee_name) like upper('%" + objErrorFilterParam.getDeductee_name().trim() + "%') \n";
            }
//            if (!utl.isnull(objErrorFilterParam.getDeductee_panno_condition()) && !utl.isnull(objErrorFilterParam.getDeductee_panno())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getDeductee_panno_condition(), "a.deductee_panno", objErrorFilterParam.getDeductee_panno().trim().toUpperCase(), null, false);
//            }
            if (!utl.isnull(objErrorFilterParam.getDeductee_panno())) {
                additionalWhereClause += " and a.deductee_panno = '" + objErrorFilterParam.getDeductee_panno().trim().toUpperCase() + "' \n";
            }
//            if (!utl.isnull(objErrorFilterParam.getTran_ref_date_condition()) && !utl.isnull(objErrorFilterParam.getFrom_tran_ref_date())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getTran_ref_date_condition(), "a.tran_ref_date", objErrorFilterParam.getFrom_tran_ref_date().trim().toUpperCase(), objErrorFilterParam.getTo_tran_ref_date(), true);
//            }
            if (!utl.isnull(objErrorFilterParam.getFrom_tran_ref_date())) {
                additionalWhereClause += " and a.TRAN_REF_DATE  >= to_date('" + objErrorFilterParam.getFrom_tran_ref_date() + "','dd-mm-rrrr') \n";
            }
//            if (!utl.isnull(objErrorFilterParam.getTds_deduct_date_condition()) && !utl.isnull(objErrorFilterParam.getFrom_tds_deduct_date())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getTds_deduct_date_condition(), "a.tds_deduct_date", objErrorFilterParam.getFrom_tds_deduct_date().trim().toUpperCase(), objErrorFilterParam.getTo_tds_deduct_date(), true);
//            }
            if (!utl.isnull(objErrorFilterParam.getFrom_tds_deduct_date())) {
                additionalWhereClause += " and a.tds_deduct_date  >= to_date('" + objErrorFilterParam.getFrom_tds_deduct_date() + "','dd-mm-rrrr') \n";
            }
            if (!utl.isnull(objErrorFilterParam.getTds_amt_condition()) && !utl.isnull(objErrorFilterParam.getFrom_tds_amt())) {
                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getTds_amt_condition(), "a.tds_amt", objErrorFilterParam.getFrom_tds_amt().trim(), objErrorFilterParam.getTo_tds_amt(), false);
            }
//            if (!utl.isnull(objErrorFilterParam.getTran_amt_condition()) && !utl.isnull(objErrorFilterParam.getFrom_tran_amt())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getTran_amt_condition(), "a.tran_amt", objErrorFilterParam.getFrom_tran_amt().trim(), objErrorFilterParam.getTo_tran_amt(), false);
//            }
            if (!utl.isnull(objErrorFilterParam.getFrom_tran_amt())) {
                additionalWhereClause += " and a.tran_amt = '" + objErrorFilterParam.getFrom_tran_amt().trim() + "' \n";
            }
//            if (!utl.isnull(objErrorFilterParam.getTds_code_condition()) && !utl.isnull(objErrorFilterParam.getTds_code())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getTds_code_condition(), "a.tds_code", objErrorFilterParam.getTds_code().trim().toUpperCase(), null, false);
//            }
            if (!utl.isnull(objErrorFilterParam.getTds_code())) {
                additionalWhereClause += " and a.tds_code = '" + objErrorFilterParam.getTds_code().trim().toUpperCase() + "' \n";
            }
//            if (!utl.isnull(objErrorFilterParam.getCertificate_no_condition()) && !utl.isnull(objErrorFilterParam.getCertificate_no())) {
//                additionalWhereClause += getAdditionalWhereClause(objErrorFilterParam.getCertificate_no_condition(), "a.certificate_no", objErrorFilterParam.getCertificate_no().trim(), null, false);
//            }
            if (!utl.isnull(getObjErrFilterList().getCertificate_no())) {
                additionalWhereClause += " and a.certificate_no = '" + objErrorFilterParam.getCertificate_no().trim() + "' \n";
            }
        }
        return additionalWhereClause;
    }//end method
    private long totalRecordsCount;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public ArrayList<ShowErrorDetailsBean> getShowErrorDetailBeanList() {
        return showErrorDetailBeanList;
    }

    public void setShowErrorDetailBeanList(ArrayList<ShowErrorDetailsBean> showErrorDetailBeanList) {
        this.showErrorDetailBeanList = showErrorDetailBeanList;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getTdsTypeCode() {
        return TdsTypeCode;
    }

    public void setTdsTypeCode(String TdsTypeCode) {
        this.TdsTypeCode = TdsTypeCode;
    }

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    public String getL_error_type_code() {
        return l_error_type_code;
    }

    public void setL_error_type_code(String l_error_type_code) {
        this.l_error_type_code = l_error_type_code;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getL_error_code() {
        return l_error_code;
    }

    public void setL_error_code(String l_error_code) {
        this.l_error_code = l_error_code;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public LinkedHashMap<String, String> getSelectDeductReason() {
        return selectDeductReason;
    }

    public void setSelectDeductReason(LinkedHashMap<String, String> selectDeductReason) {
        this.selectDeductReason = selectDeductReason;
    }

    public LinkedHashMap<String, String> getSelectRateType() {
        return selectRateType;
    }

    public void setSelectRateType(LinkedHashMap<String, String> selectRateType) {
        this.selectRateType = selectRateType;
    }

    public HashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(HashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public LinkedHashMap<String, String> getSelectRemittanceNature() {
        return selectRemittanceNature;
    }

    public void setSelectRemittanceNature(LinkedHashMap<String, String> selectRemittanceNature) {
        this.selectRemittanceNature = selectRemittanceNature;
    }

    public FilterTdsErrorDataList getObjErrFilterList() {
        return objErrFilterList;
    }

    public void setObjErrFilterList(FilterTdsErrorDataList objErrFilterList) {
        this.objErrFilterList = objErrFilterList;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }
}
