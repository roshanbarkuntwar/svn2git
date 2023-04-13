/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import downloadSection.DownloadActionDB;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class TokenSatusAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        StringBuilder sb = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user_mast = (UserMast) session.get("LOGINUSER");
            String sessionModule = (String) session.get("MODULE");
            if (client != null && asmt != null) {

                Object sessionId = session.get("LOGINSESSIONID");
                Long l_client_loginid_seq;
                if (sessionId != null) {
                    l_client_loginid_seq = (Long) sessionId;
                } else {
                    l_client_loginid_seq = 0l;
                }
                if (!utl.isnull(getMsgFlag())) {
                    if (getMsgFlag().equals("importStatus")) {
                        session.put("ACTIVE_TAB", "tdsImport");
                        setHeaderMessage("Token Status (Import File)");
                        setMsgFlag(getMsgFlag());
                    } else if (getMsgFlag().equals("fvuStatus")) {
                        session.put("ACTIVE_TAB", "errorStatus");
                        setMsgFlag(getMsgFlag());
                        setHeaderMessage("Token Status(Validation & FVU genaration)");
                    }
                } else {
                    setMsgFlag("tokenStatus");
                    setHeaderMessage("  Token Status Dashboard ");
                    session.put("ACTIVE_TAB", "dashboard");
                }
                try {
                    templateTypes = importExcelAction.getTemplatesType(asmt, factory, client, sessionModule);
                    try {
                        //Process Type  list
                        if (msgFlag.equals("importStatus") || msgFlag.equals("fvuStatus") || getMainProcessType().equals("I") || getMainProcessType().equals("P")) {
                            DownloadActionDB dadb = new DownloadActionDB();
                            DbFunctionExecutorAsString dbexecuter = new DbFunctionExecutorAsString();
                            String processTypeQry = dadb.getProcessTypeQry(sessionModule, msgFlag, mainProcessType);
                            ArrayList<ArrayList<String>> processList = dbexecuter.execute_oracle_query_as_list(processTypeQry);
                            if (processList != null && processList.size() > 0) {
                                processList.forEach(processType -> {
                                    processTypes.put(processType.get(0), processType.get(2));
                                });
                            }
                        }
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setDataGridAction("tokenStatusGrid");
                if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                    TokenStatusDB db = new TokenStatusDB();
                    DbFunctionExecutorAsIntegar executer = new DbFunctionExecutorAsIntegar();

                    String additional_whereclause = "";
                    if (!utl.isnull(getTokenNo())) {
                        additional_whereclause += " AND V.PROCESS_SEQNO = '" + getTokenNo().trim() + "' \n";
                    }
                    if (!utl.isnull(getTemplateCode())) {
                        additional_whereclause += " AND V.TEMPLATE_CODE = '" + getTemplateCode().trim() + "' \n";
                    }
                    if (!utl.isnull(getProcesType())) {
                        additional_whereclause += " AND V.PROCESS_TYPE = '" + getProcesType().trim() + "' \n";
                    }
                    if (!utl.isnull(getLoginType())) {
                        additional_whereclause += " AND V.record_insert_flag = '" + getLoginType().trim() + "' \n";
                    }
                    if (!utl.isnull(getMainProcessType())) {
                        additional_whereclause += " AND a.process_log_type = '" + getMainProcessType().trim() + "' \n";
                    }
                    String query = db.getTokenStatusDataCount(asmt, client, "", additional_whereclause, sessionModule, msgFlag);
                    utl.generateSpecialLog("Token Status count query", query);

                    long total = executer.execute_oracle_function_as_integar(query);

                    //paginator logic start..
                    setTotalRecordsCount(total);
                    int pages = 0;
                    if (total > 0) {
                        String recNumber = getRecordsPerPage();
                        if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                            recNumber = (String) session.get("TOKENSTATUSRECPERPG");
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

                    } else {
                        setPageNumber("0");
                        setTotalPages(pages);
                    }

                    String return_data = "";
                    if (getTotalRecordsCount() != 0) {
                        return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                    } else {
                        return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                    }
                    sb.append(return_data);
                    return_view = "ajax_success";

                } else {
                    return_view = "success";
                    // added I &  P logic for Import file and Validation Fvu generation 
                    if (!utl.isnull(getMainProcessType()) && (getMainProcessType().equals("I") || getMainProcessType().equals("P"))) {
                        sb.append(processTypes);
                        return_view = "ajax_success";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return return_view;
    }

    public TokenSatusAction() {
        utl = new Util();
        templateTypes = new LinkedHashMap<>();
        processTypes = new LinkedHashMap<>();
        importExcelAction = new ImportExcelAction();
    }
    private Map<String, Object> session;
    private InputStream inputStream;
    private Map<String, String> templateTypes;
    private Map<String, String> processTypes;
    private String search;
    private String action;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String dataGridAction;
    private String tokenNo;
    private String templateCode;
    private String procesType;
    private String sessionResult;
    private String filterFlag;
    private String msgFlag;
    private String headerMessage;
    private String loginType;
    private String mainProcessType;
    Util utl;
    private ImportExcelAction importExcelAction;

    public String getMainProcessType() {
        return mainProcessType;
    }

    public void setMainProcessType(String mainProcessType) {
        this.mainProcessType = mainProcessType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, String> getProcessTypes() {
        return processTypes;
    }

    public String getHeaderMessage() {
        return headerMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public String getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(String msgFlag) {
        this.msgFlag = msgFlag;
    }

    public void setProcessTypes(Map<String, String> processTypes) {
        this.processTypes = processTypes;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Map<String, String> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(Map<String, String> templateTypes) {
        this.templateTypes = templateTypes;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getProcesType() {
        return procesType;
    }

    public void setProcesType(String procesType) {
        this.procesType = procesType;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

}
