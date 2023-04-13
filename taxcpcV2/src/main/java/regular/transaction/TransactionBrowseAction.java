/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcDelTranSingleMulti;
import dao.DbProc.ProcDelTranSingleMultiTran;
import dao.DbProc.ProcMultipleDeleteTranLoad;
import dao.DbProc.ProcTdsChallanRebuild;
import dao.TdsChallanTranLoadDAO;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanBrowseAction;

/**
 *
 * @author ayushi.jain
 */
public class TransactionBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        session.put("ACTIVE_TAB", "tdsTransaction");
        String returnType = "success";
        StringBuilder sb = new StringBuilder();
        String flag = "";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment ass = (Assessment) session.get("ASSESSMENT");
            UserMast user_mast = (UserMast) session.get("LOGINUSER");
            GetGlobalList gb = new GetGlobalList();
            tdsSectionList = gb.getSectionList(ass.getTdsTypeCode(), ass.getQuarterToDate());
            tdsAmtOperatList = gb.getOperatorList("Select Tds Amount", utl);
            tdsDeductReasonList = factory.getViewTdsDeductReasonDAO().getDeductReasonData(ass.getTdsTypeCode());
            System.out.println("tdsDeductReasonList--");
            for (Map.Entry<String, String> ite : tdsDeductReasonList.entrySet()) {
                System.out.print(ite.getKey() + ", ");
            }
            try {
                String rsltMsg = (String) session.get("session_result");
                if (!utl.isnull(rsltMsg)) {
                    setSessionResult(rsltMsg);
                    session.remove("session_result");
                }
            } catch (Exception e) {

            }

            try {
                TdsTranLoadDAO tdsTranLoadObj = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> countryCodeList = tdsTranLoadObj.getCountryCode();
                setSelect_country(countryCodeList);
                setSelectRemittanceNature(factory.getViewRemittanceNatureDAO().getRemittanceAsLinkedHashMap());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                flag = (String) session.get("ALLASSESSMENT");
            } catch (Exception e) {

            }

            if (!utl.isnull(getAction())) {
                try {
                    String rsltMsg = (String) session.get("TDSRSLTONSAVE");
                    if (!utl.isnull(rsltMsg)) {
                        setSessionResult(rsltMsg);
                        session.remove("TDSRSLTONSAVE");
                    }
                } catch (Exception e) {

                }
                if (getAction().equalsIgnoreCase("add")) {
                    try {
                        setTdsSectionList(challanBrowseAction.getListOfSection(ass));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setMapFlag(getMapFlag());
                    setMapChallanRowSeq(getMapChallanRowSeq());
                    setMapTDSChallanAmt(getMapTDSChallanAmt());
                    setTdsChallanDate(getTdsChallanDate());
                    setTdsChallanNo(getTdsChallanNo());
                    if (!utl.isnull(getMapFlag()) && getMapFlag().equalsIgnoreCase("MAPTDSAMT")) {
                        try {

                            TdsTranLoadDAO tdstrandao = factory.getTdsTranLoadDAO();
                            List<TdsTranLoad> objtdstrandao = tdstrandao.readTdsChallanAmount(getMapChallanRowSeq(), clientMast.getEntity_code(), clientMast.getClient_code(), ass.getAccYear(), ass.getQuarterNo(), ass.getTdsTypeCode());
                            Double allocatedAmount = 0d;
                            if (objtdstrandao != null) {
                                for (TdsTranLoad tdsTranLoaddata : objtdstrandao) {
                                    String l_tds_amount = tdsTranLoaddata.getTds_base_amt();
                                    l_tds_amount = utl.isnull(l_tds_amount) ? "0" : l_tds_amount;
                                    allocatedAmount += Double.parseDouble(l_tds_amount);
                                }
                            }
                            setMapAllocatedAmount(allocatedAmount);
                            try {
                                setBalanceAmount(Double.parseDouble(getMapTDSChallanAmt()) - allocatedAmount);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }//end map if
                    returnType = "entry";
                } else if (getAction().equalsIgnoreCase("edit") || getAction().equalsIgnoreCase("view")) {
                    try {
                        setTdsSectionList(challanBrowseAction.getListOfSection(ass));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (getRowid_seq() != null) {
                        TdsTranLoadDAO tdstran = factory.getTdsTranLoadDAO();
                        //TdsTranLoad entity = tdstran.readById(getRowid_seq(), true);
                        TdsTranLoad entity = tdstran.readTDSBySequenceID(getRowid_seq());
                        if (entity != null) {
                            setTdsTranLoad(entity);
                            setMapChallanRowSeq(getMapChallanRowSeq());
                            setMapTDSChallanAmt(getMapTDSChallanAmt());
                            List<TdsSplRateMast> splRateMastList = factory.getTdsSplRateMastDAO().readTdsSplRateForDeducteeList(clientMast, ass, tdsTranLoad.getTds_code(),
                                    tdsTranLoad.getDeductee_panno());

                            if (splRateMastList != null) {

                                setCertificateNoList(splRateMastList);
                            }

                            try {

                                TdsChallanTranLoadDAO challanDAO = factory.getTdsChallanTranLoadDAO();
//                                utl.generateLog("getMapChallanRowSeq()--" + getMapChallanRowSeq());

                                if (!utl.isnull(getMapChallanRowSeq())) {
                                    TdsChallanTranLoad data = challanDAO.readChallanBySequenceID(Long.parseLong(getMapChallanRowSeq()));

//                                    utl.generateLog("data.." + data);
                                    if (data != null) {

                                        setMapTDSChallanAmt(utl.isnull(data.getTds_challan_tds_amt()) ? "0" : data.getTds_challan_tds_amt());
                                        if (!getMapTDSChallanAmt().equalsIgnoreCase("0")) {
                                            System.out.println("Double.parseDouble(getMapTDSChallanAmt())---" + Double.parseDouble(getMapTDSChallanAmt()));
//                                            System.out.println("Double.parseDouble(entity.getTds_amt())()---"+Double.parseDouble(entity.getTds_amt));
                                            System.out.println("total---" + (data.getTotal_allocated_amount() - Double.parseDouble(entity.getTds_amt())));
                                            System.out.println("Double.parseDouble(entity.getTds_amt())---" + Double.parseDouble(entity.getTds_amt()));
                                            setMapAllocatedAmount(data.getTotal_allocated_amount() - Double.parseDouble(entity.getTds_amt()));
//                                            setMapAllocatedAmount(Double.parseDouble(data.getTds_challan_tds_amt()) - data.getTotal_allocated_amount());
                                            setTdsChallanDate(data.getTds_challan_date());
                                            setTdsChallanNo(data.getTds_challan_no());
//                                setMapAllocatedAmount(data.getTotal_allocated_amount() == null ? 0 : data.getTotal_allocated_amount());
                                        }
                                        try {
                                            System.out.println("Double.parseDouble(getMapTDSChallanAmt())---" + Double.parseDouble(getMapTDSChallanAmt()));
                                            System.out.println("getMapAllocatedAmount()---" + getMapAllocatedAmount());
                                            System.out.println("total---" + (Double.parseDouble(getMapTDSChallanAmt()) - getMapAllocatedAmount()));
                                            setBalanceAmount(Double.parseDouble(getMapTDSChallanAmt()) - getMapAllocatedAmount());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    returnType = "entry";
                } else if (getAction().equalsIgnoreCase("MultipleDelete")) {
                    try {

                        System.out.println("where clause" + getWhereClause());
                        System.out.println("Multiple Delete Transaction");
                        String entityCode = clientMast.getEntity_code();
                        String clientCode = clientMast.getClient_code();
                        String accYear = ass.getAccYear();
                        String quarterNo = ass.getQuarterNo();
                        String tdsTypecode = ass.getTdsTypeCode();
                        Date fromDate = ass.getQuarterFromDate();
                        Date toDate = ass.getQuarterToDate();
                        String userCode = user_mast.getUser_code();
                        Object sessionId = session.get("LOGINSESSIONID");
                        Long l_client_loginid_seq;

                        if (sessionId != null) {
                            l_client_loginid_seq = (Long) sessionId;
                        } else {
                            l_client_loginid_seq = 0l;
                        }
                        String procResult = "";
                        /*  String jsonParameter="";
                    String process_type="";
                    String seq_count = getSeqcount() != null ? getSeqcount(): "-1";
                    int count=Integer.parseInt(seq_count); 
                    ObjectMapper mapper = new ObjectMapper();
                                 
                      if(count==0){
                        tranFilter.setTotalRecord(getMuldelTotRec());
                        tranFilter.setEntity_code(entityCode);
                        tranFilter.setClient_code(clientCode);
                        tranFilter.setAcc_year(accYear);
                        tranFilter.setQuarter_no(quarterNo);
                        tranFilter.setTds_type_code(tdsTypecode);
                        jsonParameter = mapper.writeValueAsString(tranFilter);
                        //jsonParameter =getWhereClause();
                        //System.out.println("convert tranfilter into JSON---->"+tranFilterToJson);
                        process_type ="A";
                       }else if(count>0){
                        String str[]=getSeqidformuldel().split(",");
                        jsonParameter = mapper.writeValueAsString(str);
                       // jsonParameter =str[0];
                        //System.out.println("convert SeqId into JSON---->"+seqnoToJson);   
                        process_type ="S";
                       } 
                       
                       utl.generateLog("ProcName-> LHS_TDS_IMP_TEMPLATE.proc_del_tran_single_multi", "");
                       ProcDelTranSingleMulti procedure =new ProcDelTranSingleMulti();
                       procResult = procedure.executeProcedure(entityCode, clientCode, accYear, utl.ChangeAccYearToAssessmentAccYear(accYear), quarterNo, fromDate, toDate, tdsTypecode, l_client_loginid_seq, process_type, "TDS_TRAN_LOAD", jsonParameter, user_mast.getUser_code(), 0l);
                       System.out.println("PROCE RESULT : "+procResult);
                         */

                        String whereClauseQuery = "";
                        String process_type = "";
                        String seq_count = getSeqcount() != null ? getSeqcount() : "-1";
                        int count = Integer.parseInt(seq_count);
                        Long deleteRecordCount = 0l;
                        if(count == 0){
                            //for all records delete
                            process_type = "A";
                            deleteRecordCount = getMuldelTotRec();
                            whereClauseQuery = getWhereClause();
                        }else if (count > 0){
                            //for selected records delete
                            process_type = "S";
                            whereClauseQuery = getSeqidformuldel();
                            deleteRecordCount = getSelectedRecordCount();
                        }

                        ProcDelTranSingleMultiTran procedure = new ProcDelTranSingleMultiTran();
                        procResult = procedure.executeProcedure(entityCode, clientCode, accYear, utl.ChangeAccYearToAssessmentAccYear(accYear), quarterNo, fromDate, toDate, tdsTypecode, l_client_loginid_seq, process_type, "TDS_TRAN_LOAD", whereClauseQuery, user_mast.getUser_code(), 0l, deleteRecordCount);
                        try {
                            if(!utl.isnull(procResult)){
                                String result[] = procResult.split("#");
                                if(result[0].equalsIgnoreCase("0")){
                                    ProcTdsChallanRebuild callProc = new ProcTdsChallanRebuild();
                                    String a_process_error = callProc.executeProcedure(entityCode, clientCode, accYear, quarterNo, fromDate, toDate, tdsTypecode, null, "D");
                                    if(!utl.isnull(a_process_error)){
                                        if (a_process_error.equalsIgnoreCase("0")) {
                                            utl.generateLog("procedure ProcTdsChallanRebuild success", "");
                                        } else {
                                             utl.generateLog("procedure ProcTdsChallanRebuild faild", "");
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        sb.append(procResult);

                    } catch (Exception e) {
                        sb.append("error");
                        e.printStackTrace();
                    }

                    returnType = "filter_success";
                }

            } else {
                setDataGridAction("transactionDatagridAction");
                setBulkDownloadFor("TDS_TRANSACTIONS");
                //setBulkDownloadAction("tranBulkDownload");
//                TransactionDB db = new TransactionDB();
//                String l_query = db.getTransactionCountQuery(clientMast, ass, getTranFilter(), flag);
//
//                DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
//                long total = objDBListCount.execute_oracle_function_as_integar(l_query);
                long total = 0l;

                //paginator logic start..
                setTotalRecordsCount(total);
                int pages = 0;
                if (total > 0) {
                    String recNumber = getRecordsPerPage();
                    if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                        recNumber = (String) session.get("TRANSACTIONRECPERPG");
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

                if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                    TransactionDB db = new TransactionDB();
                    String l_query = db.getTransactionCountQuery(clientMast, ass, getTranFilter(), flag);
                    utl.generateSpecialLog("Regular tran query: ", l_query);
                    DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                    total = objDBListCount.execute_oracle_function_as_integar(l_query);

                    //paginator logic start..
                    setTotalRecordsCount(total);
                    pages = 0;
                    if (total > 0) {
                        String recNumber = getRecordsPerPage();
                        if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                            recNumber = (String) session.get("TRANSACTIONRECPERPG");
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

                    String return_data = "";
                    if (getTotalRecordsCount() != 0) {
                        return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                    } else {
                        return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                    }

                    sb.append(return_data);
                    returnType = "filter_success";
                } else {
                    setTotalPages(pages);
                    returnType = "success";
                }

            }
            if (!utl.isnull(getShowFilter()) && getShowFilter().equalsIgnoreCase("true")) {
                try {
                    LinkedHashMap<String, String> sectionList = challanBrowseAction.getListOfSection(ass);
                    sb.append("transaction").append("#");
                    sectionList.forEach((key, value) -> {
                        sb.append("<option value=\"").append(key).append("\">").append(value).append("</option>");
                    });

                    LinkedHashMap<String, String> listOfDeductReason = this.getListOfDeductReason(factory, ass);
                    sb.append("#");
                    sb.append("<option value=\"\"").append("\">").append("Select TDS Deduct Reason").append("</option>");
                    listOfDeductReason.forEach((key, value)
                            -> {
                        sb.append("<option value=\"").append(key).append("\">").append(value).append("</option>");
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                returnType = "filter_success";
            }
            //paginator logic end
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnType;
    }

    public TransactionBrowseAction() {
        utl = new Util();

        selectRateType = new LinkedHashMap<String, String>();
        selectRateType.put("I", "Income Tax Rate");
        selectRateType.put("D", "DTAA Rate");

        select_country = new LinkedHashMap<>();
        certificateNoList = new ArrayList<>();
        challanBrowseAction = new ChallanBrowseAction();

        tdsDeductReasonList = new LinkedHashMap<>();
        // tdsDeductReasonList.put("", "Select TDS Deduct Reason");

        tdsSectionList = new LinkedHashMap<>();
        tdsSectionList.put("", "Select Section");
    }
    Util utl;
    private ChallanBrowseAction challanBrowseAction;
    private LinkedHashMap<String, String> selectRateType;
    private Map<String, Object> session;
    private String search;
    private String action;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String dataGridAction;
    private String browseAction;
    private String sessionResult;
    private String filterFlag;
    private String bulkDownloadAction;
    private TransactionFilterEntity tranFilter;
    private LinkedHashMap<String, String> tdsSectionList;
    private LinkedHashMap<String, String> tdsAmtOperatList;
    private LinkedHashMap<String, String> tdsDeductReasonList;
    private LinkedHashMap<String, String> assList;
    private TdsTranLoad tdsTranLoad;
    private Long rowid_seq;
    private Double balanceAmount;
    private String bulkDownloadFor;
    private String mapFlag;
    private String mapTDSChallanAmt;
    private String mapChallanRowSeq;
    private double mapAllocatedAmount;
    private InputStream inputStream;
    private LinkedHashMap selectRemittanceNature;
    private LinkedHashMap<String, String> select_country;
    private List<TdsSplRateMast> certificateNoList;
    private String showFilter;
    private String tdsChallanNo;
    private String tdsChallanDate;
    private String tdsAmount;
    private String seqidformuldel;
    private boolean mulDelFlag;
    private String seqcount;
    private Long muldelTotRec;
    private String whereClause;
    private Long selectedRecordCount;

    public List<TdsSplRateMast> getCertificateNoList() {
        return certificateNoList;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getTdsChallanNo() {
        return tdsChallanNo;
    }

    public void setTdsChallanNo(String tdsChallanNo) {
        this.tdsChallanNo = tdsChallanNo;
    }

    public String getTdsChallanDate() {
        return tdsChallanDate;
    }

    public void setTdsChallanDate(String tdsChallanDate) {
        this.tdsChallanDate = tdsChallanDate;
    }

    public void setCertificateNoList(List<TdsSplRateMast> certificateNoList) {
        this.certificateNoList = certificateNoList;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public Util getUtl() {
        return utl;
    }

    public LinkedHashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(LinkedHashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public LinkedHashMap getSelectRemittanceNature() {
        return selectRemittanceNature;
    }

    public void setSelectRemittanceNature(LinkedHashMap selectRemittanceNature) {
        this.selectRemittanceNature = selectRemittanceNature;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public LinkedHashMap<String, String> getSelectRateType() {
        return selectRateType;
    }

    public void setSelectRateType(LinkedHashMap<String, String> selectRateType) {
        this.selectRateType = selectRateType;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public String getBulkDownloadAction() {
        return bulkDownloadAction;
    }

    public void setBulkDownloadAction(String bulkDownloadAction) {
        this.bulkDownloadAction = bulkDownloadAction;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getMapFlag() {
        return mapFlag;
    }

    public void setMapFlag(String mapFlag) {
        this.mapFlag = mapFlag;
    }

    public String getMapTDSChallanAmt() {
        return mapTDSChallanAmt;
    }

    public void setMapTDSChallanAmt(String mapTDSChallanAmt) {
        this.mapTDSChallanAmt = mapTDSChallanAmt;
    }

    public String getMapChallanRowSeq() {
        return mapChallanRowSeq;
    }

    public void setMapChallanRowSeq(String mapChallanRowSeq) {
        this.mapChallanRowSeq = mapChallanRowSeq;
    }

    public double getMapAllocatedAmount() {
        return mapAllocatedAmount;
    }

    public void setMapAllocatedAmount(double mapAllocatedAmount) {
        this.mapAllocatedAmount = mapAllocatedAmount;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public TransactionFilterEntity getTranFilter() {
        return tranFilter;
    }

    public void setTranFilter(TransactionFilterEntity tranFilter) {
        this.tranFilter = tranFilter;
    }

    public LinkedHashMap<String, String> getTdsSectionList() {
        return tdsSectionList;
    }

    public void setTdsSectionList(LinkedHashMap<String, String> tdsSectionList) {
        this.tdsSectionList = tdsSectionList;
    }

    public LinkedHashMap<String, String> getTdsAmtOperatList() {
        return tdsAmtOperatList;
    }

    public void setTdsAmtOperatList(LinkedHashMap<String, String> tdsAmtOperatList) {
        this.tdsAmtOperatList = tdsAmtOperatList;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public TdsTranLoad getTdsTranLoad() {
        return tdsTranLoad;
    }

    public void setTdsTranLoad(TdsTranLoad tdsTranLoad) {
        this.tdsTranLoad = tdsTranLoad;
    }

    public LinkedHashMap<String, String> getTdsDeductReasonList() {
        return tdsDeductReasonList;
    }

    public void setTdsDeductReasonList(LinkedHashMap<String, String> tdsDeductReasonList) {
        this.tdsDeductReasonList = tdsDeductReasonList;
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

    public LinkedHashMap<String, String> getAssList() {
        return assList;
    }

    public void setAssList(LinkedHashMap<String, String> assList) {
        this.assList = assList;
    }

    public String getShowFilter() {
        return showFilter;
    }

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    public String getSeqidformuldel() {
        return seqidformuldel;
    }

    public void setSeqidformuldel(String seqidformuldel) {
        this.seqidformuldel = seqidformuldel;
    }

    public ChallanBrowseAction getChallanBrowseAction() {
        return challanBrowseAction;
    }

    public void setChallanBrowseAction(ChallanBrowseAction challanBrowseAction) {
        this.challanBrowseAction = challanBrowseAction;
    }

    public boolean isMulDelFlag() {
        return mulDelFlag;
    }

    public void setMulDelFlag(boolean mulDelFlag) {
        this.mulDelFlag = mulDelFlag;
    }

    public String getSeqcount() {
        return seqcount;
    }

    public void setSeqcount(String seqcount) {
        this.seqcount = seqcount;
    }

    public Long getMuldelTotRec() {
        return muldelTotRec;
    }

    public void setMuldelTotRec(Long muldelTotRec) {
        this.muldelTotRec = muldelTotRec;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public Long getSelectedRecordCount() {
        return selectedRecordCount;
    }

    public void setSelectedRecordCount(Long selectedRecordCount) {
        this.selectedRecordCount = selectedRecordCount;
    }

    public LinkedHashMap<String, String> getListOfDeductReason(DAOFactory factory, Assessment ass) {
        LinkedHashMap<String, String> deductReasonData = factory.getViewTdsDeductReasonDAO().getDeductReasonData(ass.getTdsTypeCode());
        return deductReasonData;
    }
}
