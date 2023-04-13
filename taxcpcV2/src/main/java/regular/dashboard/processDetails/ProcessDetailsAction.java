/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.processDetails;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcDeleteTranLoad;
import dao.ViewDeducteeTemplateDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessDetailsAction extends ActionSupport implements SessionAware {

    private final Util utl;
    private Map<String, Object> session;
    private Map<String, String> templateTypes;
    private Map<String, String> seqNoMap;
    private InputStream inputStream;
    private ProcessDetailBean detailsBeanObj;

    private String action;
    private String filterFlag;
    private String search;
    private String dataGridAction;
    private String templateCode;
    private Long processSeqNo;
    private int recordsPerPage;
    private int pageNumber;
    private int startIndex;
    private int endIndex;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;

    public ProcessDetailsAction() {
        utl = new Util();
        detailsBeanObj = new ProcessDetailBean();
        templateTypes = new LinkedHashMap<>();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";

        setDataGridAction("processDataGrid");
        System.out.println("processDataGrid...");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user_mast = (UserMast) session.get("LOGINUSER");

            if (client != null && asmt != null) {
                ProcessDetailsSupport processDetailsSupport = new ProcessDetailsSupport();
                System.out.println("inside client not null...");
                String entityCode = client.getEntity_code();
                String clientCode = client.getClient_code();
                String accYear = asmt.getAccYear();
                String quarterNo = asmt.getQuarterNo();
                String tdsType = asmt.getTdsTypeCode();
                Date fromDate = asmt.getQuarterFromDate();
                Date toDate = asmt.getQuarterToDate();
                String userCode = user_mast.getUser_code();

                Object sessionId = session.get("LOGINSESSIONID");
                Long l_client_loginid_seq;

                if (sessionId != null) {
                    l_client_loginid_seq = (Long) sessionId;
                } else {
                    l_client_loginid_seq = 0l;
                }

                try {
                    ViewDeducteeTemplateDAO viewDeducteeTemplateDAO = factory.getViewDeducteeTemplateDAO();
                    if (!utl.isnull(asmt.getTdsTypeCode())) {

                        String sessionModule = (String) session.get("MODULE");
                        sessionModule = utl.isnull(sessionModule) ? "R" : sessionModule;
                        List<ViewClientTemplate> templateList = viewDeducteeTemplateDAO.getAllTemplatesAsKeyCodeForDeletePros(client, tdsType, sessionModule);
                        if (templateList != null) {
                            for (ViewClientTemplate template : templateList) {
                                templateTypes.put(template.getTemplate_code(), template.getTemplate_name());
                            }
                        }
                    } else {
                        templateTypes.put("", "---Select Template Type---");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("deleteProcess")) {
                    try {
                        ProcDeleteTranLoad procDeleteTranLoad = new ProcDeleteTranLoad();
                        procDeleteTranLoad.executeProcedure(entityCode, clientCode, accYear, utl.ChangeAccYearToAssessmentAccYear(accYear),
                                quarterNo, fromDate, toDate, tdsType, l_client_loginid_seq,
                                "D", //D - Delete previous data
                               "TEMPLATE_ALL", userCode, getProcessSeqNo());
//                             if(utl.isnull(executeProcedureResult)){
//                              executeProcedureResult="error";   
//                             }
                        utl.generateLog("Delete tran load procedure result", "request initiated");
                        return_msg = "success";
                    } catch (Exception e) {
                        return_msg = "error";
                        e.printStackTrace();
                    }
                    return_view = "ajax_success";
                } else {
                    if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                        System.out.println("inside search");
                        DbFunctionExecutorAsIntegar db = new DbFunctionExecutorAsIntegar();
                        String count_sql = processDetailsSupport.getProcessDetailsCountQuery(entityCode, clientCode, client,asmt, getDetailsBeanObj());
                        utl.generateSpecialLog("Process dashboard count query", count_sql);
                        System.out.println("Process dashboard count query"+count_sql);
                        long total = db.execute_oracle_function_as_integar(count_sql);
                        setTotalRecords(total);
                        setPageNumber(0);
                        setTotalRecordsCount(total);

                        int pages = 0;
                        if (total > 0) {
                            String recNumber = (String) session.get("PROCESSDELETERECPERPAGE");// records per page
                            setRecordsPerPage(utl.isnull(recNumber) ? 10 : Integer.parseInt(recNumber));
                            int recVal = getRecordsPerPage();
                            long mod = total % recVal;
                            int rem = 0;
                            if (mod > 0) {
                                rem = 1;
                            }
                            pages = (int) Math.ceil(total / recVal) + rem;

                            setPageNumber(1);
                        } else {
                            setPageNumber(0);
                        }

                        setTotalPages(pages);

                        String return_data = "";
                        if (getTotalRecordsCount() != 0) {
                            return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                        } else {
                            return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                        }

                        return_msg = return_data;
                        return_view = "ajax_success";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        utl.generateLog("Process dashboard browse return view", return_view);

        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view; //To change body of generated methods, choose Tools | Templates.
    }//end method

    public String getSequenceNumberOptions() throws UnsupportedEncodingException {
        String responseText = "";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            Map<String, String> sequenceMap = factory.getLhssysProcessLogDAO().processSequenceNumbersList(client.getEntity_code(), asmt, getTemplateCode());

            if (Optional.ofNullable(sequenceMap).isPresent()) {
                responseText = sequenceMap.entrySet()
                        .stream()
                        .map(seqEntry
                                -> "<option onclick=\"alert(true)\" value=\"" + seqEntry.getKey() + "\">(" + seqEntry.getValue() + ")</option>"
                        ).collect(Collectors.joining("\n"));
            }
//            System.out.println("Sequence number options text: \n" + responseText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(responseText.getBytes("UTF-8"));
        return SUCCESS;
    }

    public Map<String, String> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(Map<String, String> templateTypes) {
        this.templateTypes = templateTypes;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public ProcessDetailBean getDetailsBeanObj() {
        return detailsBeanObj;
    }

    public void setDetailsBeanObj(ProcessDetailBean detailsBeanObj) {
        this.detailsBeanObj = detailsBeanObj;
    }

    public Long getProcessSeqNo() {
        return processSeqNo;
    }

    public void setProcessSeqNo(Long processSeqNo) {
        this.processSeqNo = processSeqNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public String getSearch() {
        return search;
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

    public void setSearch(String search) {
        this.search = search;
    }

    public String getAction() {
        return action;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Map<String, String> getSeqNoMap() {
        return seqNoMap;
    }

    public void setSeqNoMap(Map<String, String> seqNoMap) {
        this.seqNoMap = seqNoMap;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
