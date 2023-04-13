/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsHigerRateUpdt;
import dao.DbProc.ProcTdsShortDedWotdsUpdt;
import dao.DbProc.ProcTdsShortDedWtdsUpdt;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ShowErrorDetailsAction extends ActionSupport implements SessionAware {

    /* forward name="success" path="" */
    private final Util utl;

    private Map<String, Object> session;

    private InputStream inputStream;

    private String action;
    private String l_error_type_code;
    private String l_error_type_name;
    private String l_error_name;
    private String l_error_code;
    private String table_name;
    private String tds_code;
    private String deductee_code;
    private String deductee_panno;
    private String h_tran_amount;
    private String h_tds_amt;
    private String h_calc_tda_amt;
    private String h_diff_tds_amt;
    private String ReadonlyFlag;
    private int recordCount;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String validate;
    private LinkedHashMap<String, String> selectSection;
    private LinkedHashMap<String, String> selectTdsDeductReason;
    private LinkedHashMap<String, String> selectCondition;
    private FilterTdsErrorDataList objErrFilterList;
    private LinkedHashMap<String, String> selectConditionOther;
    private String dataGridAction;
    private String h_client_code;
    private String sessionResult;
    private String screenCallFrom;
    private String procedureType;
    private String processLevel;
    private String backBtn;
    private String updateBtn;
    private String backAction;
    private String e2;
    private String bulkDownloadFor;
    

    public ShowErrorDetailsAction() {
        utl = new Util();
        selectSection = new LinkedHashMap<String, String>();
        selectTdsDeductReason = new LinkedHashMap<String, String>();

        selectCondition = new LinkedHashMap<String, String>();
//        selectCondition.put("", "---Select---");
        selectCondition.put("EQ", "Equal");
        selectCondition.put("NEQ", "Not Equal");
        selectCondition.put("BW", "Begin With");
        selectCondition.put("EW", "End With");
        selectCondition.put("LIKE", "Contain");
        selectCondition.put("Not LIKE", "Not Contain");
        selectCondition.put("BTWN", "Between");

        selectConditionOther = new LinkedHashMap<String, String>();
//        selectConditionOther.put("", "---Select---");
        selectConditionOther.put("EQ", "Equal");
        selectConditionOther.put("NEQ", "Not Equal");
        selectConditionOther.put("BW", "Begin With");
        selectConditionOther.put("EW", "End With");
        selectConditionOther.put("LIKE", "Contain");
        selectConditionOther.put("Not LIKE", "Not Contain");
    }

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "";
        try {
            String result_value = (String) session.get("session_result");
            result_value = utl.isnull(result_value) ? "" : result_value;
            if (!utl.isnull(result_value)) {
                setSessionResult(result_value);
                session.remove("session_result");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            try {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                setSelectSection(factory.getTdsMastDAO().getSectionAsHashMap(asmt.getTdsTypeCode(), asmt.getQuarterToDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!utl.isnull(getAction())) {
            Assessment asmt = null;
            String l_entity_code = "";
            String l_client_code = "";
            String l_acc_year = "";
            String l_quarter_no = "";
            int l_quarter_No = 0;
            String l_tds_type_code = "";
            Date quarterFromDate = null;
            Date quarterToDate = null;
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            if (viewClientMast != null) {
                l_entity_code = viewClientMast.getEntity_code();
                l_client_code = viewClientMast.getClient_code();
                asmt = (Assessment) session.get("ASSESSMENT");
                l_acc_year = asmt.getAccYear();

                l_quarter_no = asmt.getQuarterNo();
                l_quarter_No = Integer.parseInt(asmt.getQuarterNo());

                l_tds_type_code = asmt.getTdsTypeCode();
                if (utl.isnull(getH_client_code())) {
                    setH_client_code(l_client_code);
                }
                quarterFromDate = asmt.getQuarterFromDate();
                quarterToDate = asmt.getQuarterToDate();
            }
            if (getAction().equalsIgnoreCase("showErrorDetails") || getAction().equalsIgnoreCase("showErrDetailsAjax")) {
                setDataGridAction("showErrorDetailsDataGridAction");
                setBulkDownloadFor("error_" + getL_error_code());

                String additionalWhereClause = "";
                if (getObjErrFilterList() != null) {
//                    if (!utl.isnull(getObjErrFilterList().getDeductee_name_condition()) && !utl.isnull(getObjErrFilterList().getDeductee_name())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getDeductee_name_condition(), "a.deductee_name", getObjErrFilterList().getDeductee_name().trim(), null, false);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getDeductee_name())) {
                        additionalWhereClause += " and a.deductee_name LIKE upper('%" + getObjErrFilterList().getDeductee_name().trim() + "%') \n";
                    }
//                    if (!utl.isnull(getObjErrFilterList().getDeductee_panno_condition()) && !utl.isnull(getObjErrFilterList().getDeductee_panno())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getDeductee_panno_condition(), "a.deductee_panno", getObjErrFilterList().getDeductee_panno().trim().toUpperCase(), null, false);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getDeductee_panno())) {
                        additionalWhereClause += " and a.deductee_panno = '" + getObjErrFilterList().getDeductee_panno().trim().toUpperCase() + "' \n";
                    }
//                    if (!utl.isnull(getObjErrFilterList().getTran_ref_date_condition()) && !utl.isnull(getObjErrFilterList().getFrom_tran_ref_date())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getTran_ref_date_condition(), "a.tran_ref_date", getObjErrFilterList().getFrom_tran_ref_date().trim().toUpperCase(), getObjErrFilterList().getTo_tran_ref_date(), true);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getFrom_tran_ref_date())) {
                        additionalWhereClause += " and a.TRAN_REF_DATE  >= to_date('" + getObjErrFilterList().getFrom_tran_ref_date() + "','dd-mm-rrrr') \n";
                    }
//                    if (!utl.isnull(getObjErrFilterList().getTds_deduct_date_condition()) && !utl.isnull(getObjErrFilterList().getFrom_tds_deduct_date())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getTds_deduct_date_condition(), "a.tds_deduct_date", getObjErrFilterList().getFrom_tds_deduct_date().trim().toUpperCase(), getObjErrFilterList().getTo_tds_deduct_date(), true);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getFrom_tds_deduct_date())) {
                        additionalWhereClause += " and a.tds_deduct_date  >= to_date('" + getObjErrFilterList().getFrom_tds_deduct_date() + "','dd-mm-rrrr') \n";

                    }
                    if (!utl.isnull(getObjErrFilterList().getTds_amt_condition()) && !utl.isnull(getObjErrFilterList().getFrom_tds_amt())) {
                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getTds_amt_condition(), "a.tds_amt", getObjErrFilterList().getFrom_tds_amt().trim(), getObjErrFilterList().getTo_tds_amt(), false);
                    }
//                    if (!utl.isnull(getObjErrFilterList().getTran_amt_condition()) && !utl.isnull(getObjErrFilterList().getFrom_tran_amt())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getTran_amt_condition(), "a.tran_amt", getObjErrFilterList().getFrom_tran_amt().trim(), getObjErrFilterList().getTo_tran_amt(), false);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getFrom_tran_amt())) {
                        additionalWhereClause += " and a.tran_amt = '" + getObjErrFilterList().getFrom_tran_amt().trim() + "' \n";
                    }
//                    if (!utl.isnull(getObjErrFilterList().getTds_code_condition()) && !utl.isnull(getObjErrFilterList().getTds_code())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getTds_code_condition(), "a.tds_code", getObjErrFilterList().getTds_code().trim().toUpperCase(), null, false);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getTds_code())) {
                        additionalWhereClause += " and a.tds_code = '" + getObjErrFilterList().getTds_code().trim().toUpperCase() + "' \n";
                    }
//                    if (!utl.isnull(getObjErrFilterList().getCertificate_no_condition()) && !utl.isnull(getObjErrFilterList().getCertificate_no())) {
//                        additionalWhereClause += getAdditionalWhereClause(getObjErrFilterList().getCertificate_no_condition(), "a.certificate_no", getObjErrFilterList().getCertificate_no().trim(), null, false);
//                    }
                    if (!utl.isnull(getObjErrFilterList().getCertificate_no())) {
                        additionalWhereClause += " and a.certificate_no = '" + getObjErrFilterList().getCertificate_no().trim() + "' \n";
                    }
                }
                ProcessErrorsDB proc_err = new ProcessErrorsDB();
                DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                Long total = 0l;
                try {
                    String l_query = "";
                    l_query = proc_err.getDeducteeTransactionErrorDetailCountQuery(asmt, l_entity_code, l_client_code, getObjErrFilterList(), getL_error_type_code(), getL_error_code(), l_tds_type_code,
                            getH_client_code(), getScreenCallFrom(), getTds_code(), getDeductee_panno(), additionalWhereClause);

                    utl.generateSpecialLog("RPE-0006 (Deductee transaction error detail screen 2/3 count query)--514--", l_query);
                    total = objDBFunction.execute_oracle_function_as_integar(l_query);
                    session.put("summary_client_code", getH_client_code());
                    session.put("screenCallFrom", getScreenCallFrom());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setPageNumber("0");
                setTotalRecordsCount(total);
                int pages = 0;
                if (total > 0) {
                    String recNumber = (String) session.get("SHOWERRORDETAILSRCHPG");
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
                    setPageNumber("1");
                    setBackBtn("d-inline-block");
                    setUpdateBtn("d-inline-block");
                } else {
                    setBackBtn("d-inline-block");
                    setUpdateBtn("d-none");
                }
                setTotalPages(pages);
                l_return_value = "success";
            }
            if (getAction().equalsIgnoreCase("showErrDetailsAjax")) {
                if (getTotalRecordsCount() != 0) {
                    l_return_message = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                } else {
                    l_return_message = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                }
                l_return_value = "input_success";
                inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));

            } else if (getAction().equalsIgnoreCase("CallProcedure")) {
                String exceuteProcedure = "-1";

                String module = (String) session.get("MODULE") != null ? (String) session.get("MODULE") : "";
                String moduleFlag = "R";
                if (!utl.isnull(module) && module.equalsIgnoreCase("Correction")) {
                    moduleFlag = "C";
                }
                try {
                    if (!utl.isnull(getProcedureType())) {
                        if (getProcedureType().equalsIgnoreCase("higerRate")) {

                            ProcTdsHigerRateUpdt proc = new ProcTdsHigerRateUpdt();
                            exceuteProcedure = proc.exceuteProcedure(l_entity_code, l_client_code, l_acc_year, l_quarter_no, quarterFromDate, quarterToDate, l_tds_type_code, 0);

                        } else if (getProcedureType().equalsIgnoreCase("tdsWithError") || getProcedureType().equalsIgnoreCase("tdsWithOutError")) {
                            ProcTdsShortDedWtdsUpdt proc = new ProcTdsShortDedWtdsUpdt();
                            exceuteProcedure = proc.exceuteProcedure(l_entity_code, l_client_code, l_acc_year, l_quarter_no, quarterFromDate, quarterToDate, l_tds_type_code, 0);

//                            ProcTdsReturnStatusInsert proc1 = new ProcTdsReturnStatusInsert();
//                            proc1.execute_procedure(l_entity_code, l_client_code, l_acc_year, l_quarter_No, l_tds_type_code, moduleFlag, null);
                        } else if (getProcedureType().equalsIgnoreCase("tdsWithOutError1")) {
                            ProcTdsShortDedWotdsUpdt proc = new ProcTdsShortDedWotdsUpdt();
                            exceuteProcedure = proc.exceuteProcedure(l_entity_code, l_client_code, l_acc_year, l_quarter_no, quarterFromDate, quarterToDate, l_tds_type_code, 0);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                l_return_value = "input_success";
                inputStream = new ByteArrayInputStream(exceuteProcedure.getBytes("UTF-8"));
            }
        }
        return l_return_value;
    }//end method

    public String getAdditionalWhereClause(String condition, String fieldName, String fieldValue1, String fieldValue2, boolean isDate) {
        String additionalWhereClause = "";
        if (!utl.isnull(condition)) {
            if (condition.equalsIgnoreCase("EQ")) {
                additionalWhereClause = "and " + fieldName + " = '" + fieldValue1 + "'\n";

            } else if (condition.equalsIgnoreCase("NEQ")) {
                additionalWhereClause = "and " + fieldName + " != '" + fieldValue1 + "'\n";

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
    }//end method

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public String getBackAction() {
        return backAction;
    }

    public void setBackAction(String backAction) {
        this.backAction = backAction;
    }

    public String getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(String backBtn) {
        this.backBtn = backBtn;
    }

    public String getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(String updateBtn) {
        this.updateBtn = updateBtn;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getL_error_type_code() {
        return l_error_type_code;
    }

    public void setL_error_type_code(String l_error_type_code) {
        this.l_error_type_code = l_error_type_code;
    }

    public String getL_error_type_name() {
        return l_error_type_name;
    }

    public void setL_error_type_name(String l_error_type_name) {
        this.l_error_type_name = l_error_type_name;
    }

    public String getL_error_name() {
        return l_error_name;
    }

    public void setL_error_name(String l_error_name) {
        this.l_error_name = l_error_name;
    }

    public String getL_error_code() {
        return l_error_code;
    }

    public void setL_error_code(String l_error_code) {
        this.l_error_code = l_error_code;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
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

    public String getH_tran_amount() {
        return h_tran_amount;
    }

    public void setH_tran_amount(String h_tran_amount) {
        this.h_tran_amount = h_tran_amount;
    }

    public String getH_tds_amt() {
        return h_tds_amt;
    }

    public void setH_tds_amt(String h_tds_amt) {
        this.h_tds_amt = h_tds_amt;
    }

    public String getH_calc_tda_amt() {
        return h_calc_tda_amt;
    }

    public void setH_calc_tda_amt(String h_calc_tda_amt) {
        this.h_calc_tda_amt = h_calc_tda_amt;
    }

    public String getH_diff_tds_amt() {
        return h_diff_tds_amt;
    }

    public void setH_diff_tds_amt(String h_diff_tds_amt) {
        this.h_diff_tds_amt = h_diff_tds_amt;
    }

    public String getReadonlyFlag() {
        return ReadonlyFlag;
    }

    public void setReadonlyFlag(String ReadonlyFlag) {
        this.ReadonlyFlag = ReadonlyFlag;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public LinkedHashMap<String, String> getSelectTdsDeductReason() {
        return selectTdsDeductReason;
    }

    public void setSelectTdsDeductReason(LinkedHashMap<String, String> selectTdsDeductReason) {
        this.selectTdsDeductReason = selectTdsDeductReason;
    }

    public LinkedHashMap<String, String> getSelectCondition() {
        return selectCondition;
    }

    public void setSelectCondition(LinkedHashMap<String, String> selectCondition) {
        this.selectCondition = selectCondition;
    }

    public FilterTdsErrorDataList getObjErrFilterList() {
        return objErrFilterList;
    }

    public void setObjErrFilterList(FilterTdsErrorDataList objErrFilterList) {
        this.objErrFilterList = objErrFilterList;
    }

    public LinkedHashMap<String, String> getSelectConditionOther() {
        return selectConditionOther;
    }

    public void setSelectConditionOther(LinkedHashMap<String, String> selectConditionOther) {
        this.selectConditionOther = selectConditionOther;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getH_client_code() {
        return h_client_code;
    }

    public void setH_client_code(String h_client_code) {
        this.h_client_code = h_client_code;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getScreenCallFrom() {
        return screenCallFrom;
    }

    public void setScreenCallFrom(String screenCallFrom) {
        this.screenCallFrom = screenCallFrom;
    }

    public String getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

}
