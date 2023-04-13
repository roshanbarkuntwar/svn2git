/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.generateRefNo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewClientMastDAO;
import dao.ViewClientTypeDAO;
import dao.ViewDeducteeCatgDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import form15GH.transaction.DeducteeSearchDetail15GH;
import form15GH.transaction.Form15GHDB;
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
 * @author ayushi.jain
 */
public class GenerateRefNoAction extends ActionSupport implements SessionAware {

    public GenerateRefNoAction() {
        select_type = new HashMap<String, String>();
        select_type.put("", "select");

        VrifiedFlg = new HashMap<String, String>();
        VrifiedFlg.put("", "--Select Verified Flag--");
        VrifiedFlg.put("Verified", "Verified");
        VrifiedFlg.put("Invalid", "Invalid");
        VrifiedFlg.put("Not Verified", "Not Verified");

        IncompleteGHFlag = new LinkedHashMap<String, String>();
        IncompleteGHFlag.put("", "All");
        IncompleteGHFlag.put("INV", "Incomplete");
        IncompleteGHFlag.put("V", "Complete");

        IncompleteGHAmountFlag = new LinkedHashMap<String, String>();
        IncompleteGHAmountFlag.put("", "--Select 15G/H Amount Details--");
        IncompleteGHAmountFlag.put("I", "Incomplete");
        IncompleteGHAmountFlag.put("C", "Complete");

        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        session.put("ACTIVE_TAB", "15GHGenerateRefNo");
        //return "success";
        // session.put("ACTIVE_TAB", "15GHTransaction");
        String return_msg = "success";
        // setBrowseAction("getGenReferenceNoAction");
        String result_value = (String) session.get("session_result");
        result_value = utl.isnull(result_value) ? "" : result_value;
        StringBuilder sb = new StringBuilder();

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        String tdsTypeCode = asmt.getTdsTypeCode();
        if (!utl.isnull(tdsTypeCode) && tdsTypeCode.equalsIgnoreCase("26Q")) {
            try {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
                ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                ViewDeducteeCatgDAO viewDeducteeCatgDAO = factory.getViewDeducteeCatgDAO();
                setDeductee_catg_list(viewDeducteeCatgDAO.getDeducteeCatgAsTdsType(tdsTypeCode));
                String quarterNo = asmt.getQuarterNo();
                String code_level_val = client.getCode_level();
                setCode_level(code_level_val);
                setIsClientTranLevel((String) session.get("CLIENT_TRAN_LEVEL"));
                String client_code = "";
                String entity_code = "";
                ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");

                if (clientMast != null) {
                    client_code = clientMast.getClient_code();
                    entity_code = clientMast.getEntity_code();
                    setResultMessage((String) session.get("DEDCUTERSLTMSG") == null ? "" : (String) session.get("DEDCUTERSLTMSG"));
                    session.put("DEDCUTERSLTMSG", "");

                    if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                        session.put("DEDUCTESRCH15GH", getDeducteeSearch());
                    } else {
                        DeducteeSearchDetail15GH dataSearch = new DeducteeSearchDetail15GH();
                        dataSearch.setInvalidGHFlag("INV");
                        session.put("DEDUCTESRCH15GH", dataSearch);
                    }

                    DeducteeSearchDetail15GH ds = getDeducteeSearch();
                    String pan_filter = "";
                    String dname_filter = "";
                    String vffilter = "";
                    String invGF_filter = "";
                    String incompAmt_filter = "";
                    String unino_filter = "";
                    String custid_filter = "";
                    if (!utl.isnull(getSearch()) && search.equalsIgnoreCase("true")) {
                        if (!utl.isnull(ds.getPanno())) {
                            pan_filter = "and t.panno= upper('" + ds.getPanno() + "') \n";
                        }
                        if (!utl.isnull(ds.getDeductee_name())) {
                            dname_filter = " and t.deductee_name LIKE upper('%" + ds.getDeductee_name() + "%') \n";
                        }

                        if (!utl.isnull(ds.getVerifiedby_flag_name())) {
                            if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Not Verified")) {
                                vffilter = "and nvl(t.deductee_panno_verified,'N') ='N'\n";
                            } else if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Invalid")) {
                                vffilter = "and nvl(t.deductee_panno_verified,'X') ='X' \n";
                            } else if (ds.getVerifiedby_flag_name().equalsIgnoreCase("Verified")) {
                                vffilter = "and nvl(t.deductee_panno_verified,'Y') ='Y'";
                            }
                        }

                        if (!utl.isnull(ds.getInvalidGHFlag())) {
                            if (ds.getInvalidGHFlag().equalsIgnoreCase("V")) {
                                invGF_filter = "and t.reference_no is not null and invalid_rec_flag = 0 \n";
                            } else if (ds.getInvalidGHFlag().equalsIgnoreCase("INV")) {
                                invGF_filter = "and t.reference_no is not null and invalid_rec_flag != 0 \n";
                            }
                        }

                        if (!utl.isnull(ds.getUinNo())) {
                            unino_filter = "and t.reference_no = '" + ds.getUinNo() + "' \n";
                        }

                        if (!utl.isnull(ds.getCustId())) {
                            custid_filter = "and t.deduction_ref_no = '" + ds.getCustId() + "' \n";
                        }

                        if (!utl.isnull(ds.getIncompleteGhAmountFlag())) {
                            if (ds.getIncompleteGhAmountFlag().equalsIgnoreCase("I")) {
                                incompAmt_filter
                                        = "and not exists (select 1 from deductee_bflag_amt_tran dat\n"
                                        + "                              where dat.entity_code = t.entity_code and dat.client_code = t.client_code and dat.acc_year = t.acc_year \n"
                                        + "                              and dat.quarter_no = t.quarter_no and dat.tds_type_code = t.tds_type_code and dat.deductee_code = t.deductee_code and dat.Panno = t.panno )";

                            } else if (ds.getIncompleteGhAmountFlag().equalsIgnoreCase("C")) {
                                incompAmt_filter
                                        = "and exists (select 1 from deductee_bflag_amt_tran dat\n"
                                        + "                              where dat.entity_code = t.entity_code and dat.client_code = t.client_code and dat.acc_year = t.acc_year \n"
                                        + "                              and dat.quarter_no = t.quarter_no and dat.tds_type_code = t.tds_type_code and dat.deductee_code = t.deductee_code and dat.Panno = t.panno )";
                            }
                        }
                    }

                    Form15GHDB form15GHquery = new Form15GHDB();
                    String deductee15ghQuery = form15GHquery.getDeducteeMast15GHCountQuery(client_code, entity_code, asmt, pan_filter, dname_filter, vffilter, invGF_filter, incompAmt_filter, unino_filter, custid_filter);
                    DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                    //System.out.println("objDBList------->"+deductee15ghQuery);
                    long total = objDBListCount.execute_oracle_function_as_integar(deductee15ghQuery);

                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {
                        String recNumber = getRecordsPerPage();
                        if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                            recNumber = (String) session.get("DEDUCTERECPERPG");
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

                    ViewClientTypeDAO clientTypeDAO = factory.getViewClientTypeDAO();
                    select_type = clientTypeDAO.getDeductorTypeAsHashMap();
                    if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                        String return_data = "";
                        if (getTotalRecordsCount() != 0) {
                            return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                        } else {
                            return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                        }
                        sb.append(return_data);

                        return_msg = "filter_success";

                    } else {
                        setTotalPages(pages);
                        return_msg = "success";
                    }

                } else {
                    //go to error page
                    //  session.put("ERRORCLASS", ErrorType.infoMessage);
                    addActionError("Client code not found");
                    return_msg = "error";
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            return_msg = "selectAssessment";
        }
        // System.out.println("Reutrn_MSg---->"+return_msg);
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return return_msg;

    }

    Map<String, Object> session;
    private HashMap<String, String> select_type;
    private HashMap<String, String> VrifiedFlg;
    private LinkedHashMap<String, String> IncompleteGHFlag;
    private LinkedHashMap<String, String> IncompleteGHAmountFlag;
    private InputStream inputStream;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String code_level;
    private String IsClientTranLevel;
    private String sessionResult;
    private String browseAction;
    private LinkedHashMap<String, String> deductee_catg_list;
    private DeducteeSearchDetail15GH deducteeSearch;
    private String update_data_row;
    Util utl;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public HashMap<String, String> getSelect_type() {
        return select_type;
    }

    public void setSelect_type(HashMap<String, String> select_type) {
        this.select_type = select_type;
    }

    public HashMap<String, String> getVrifiedFlg() {
        return VrifiedFlg;
    }

    public void setVrifiedFlg(HashMap<String, String> VrifiedFlg) {
        this.VrifiedFlg = VrifiedFlg;
    }

    public LinkedHashMap<String, String> getIncompleteGHFlag() {
        return IncompleteGHFlag;
    }

    public void setIncompleteGHFlag(LinkedHashMap<String, String> IncompleteGHFlag) {
        this.IncompleteGHFlag = IncompleteGHFlag;
    }

    public LinkedHashMap<String, String> getIncompleteGHAmountFlag() {
        return IncompleteGHAmountFlag;
    }

    public void setIncompleteGHAmountFlag(LinkedHashMap<String, String> IncompleteGHAmountFlag) {
        this.IncompleteGHAmountFlag = IncompleteGHAmountFlag;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public String getUpdate_data_row() {
        return update_data_row;
    }

    public void setUpdate_data_row(String update_data_row) {
        this.update_data_row = update_data_row;
    }

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public LinkedHashMap<String, String> getDeductee_catg_list() {
        return deductee_catg_list;
    }

    public void setDeductee_catg_list(LinkedHashMap<String, String> deductee_catg_list) {
        this.deductee_catg_list = deductee_catg_list;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public DeducteeSearchDetail15GH getDeducteeSearch() {
        return deducteeSearch;
    }

    public void setDeducteeSearch(DeducteeSearchDetail15GH deducteeSearch) {
        this.deducteeSearch = deducteeSearch;
    }

}
