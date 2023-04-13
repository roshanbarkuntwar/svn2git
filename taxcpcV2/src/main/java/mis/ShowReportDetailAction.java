/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import com.opensymphony.xwork2.ActionSupport;
import dao.MisReportConfigMastFilterDAO;
import dao.generic.DAOFactory;
import dao.generic.HibernateUtil;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import hibernateObjects.MisReportConfigMastFilter;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

/**
 *
 * @author gaurav.khanzode
 */
public class ShowReportDetailAction extends ActionSupport implements SessionAware, ParameterAware {

    Map<String, Object> session;
    Util utl;
    public HttpServletRequest request;

    private String action;

//    Pagination params
    private String search;
    private String recordsPerPage;
    private String pageNumber;
    private long totalRecords;
    private long setRecPerPage;
    private long totalRecordsCount;
    private int totalPages;
    private int startIndex;
    private int endIndex;
    private String filterFlag;
    private String dataGridAction;
    private String req_pagination_flag;

    private HashMap<String, String> report_paras_processed;
    private Long seqId;
    private String reportGroup;
    private String fetchRecords;
    private String resultMessage;
    private Map parameters;
    private String report_paras;
    private String column_headings;
    private String report_header_text;
    private String ajax_calling_url;
    private String xls_dwl_rights;
    private int totalNoColumn;
    private String db_server;
    private String db_user;
    private String db_pass;
    private String l_report_query;
    private String replace_value_for_pdf;
    private String report_url;
    private String db_sid;
    private String entityCode;
    private ArrayList<String> report_data_total;
    private String total_table_flag;
    private String strAccYear;
    private String strQtr;
    private String strFormType;

    private ArrayList<ReportHeadAttributes> report_attributes;
    private ArrayList<ArrayList<String>> report_data;
    private List<MisReportConfigMastFilter> reportParaList;
    private MisReportConfigMast reportConfig;

    private InputStream inputStream;

    public ShowReportDetailAction() {
        utl = new Util();
        report_data_total = new ArrayList<>();
        report_paras_processed = new HashMap<>();
    }

    @Override
    public String execute() throws Exception {
        String l_return_value = "input";
        String l_return_msg = "";

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ReportDB obj_DB = new ReportDB(session);

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
//        Assessment asmt = (Assessment) session.get("ASSESSMENT");

//        if (client != null) {
//            try {
//                setEntityCode(client.getEntity_code());
//                LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
//                LhssysParameters readExternalDriveName = lhssysParametersDAO.readReportParameter(client.getEntity_code());
//
//                if (readExternalDriveName != null) {
//                    System.out.println("Report Url Path---" + readExternalDriveName.getParameter_value().trim());
//                    setReport_url(readExternalDriveName.getParameter_value().trim());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        Connection conn = null;
        org.hibernate.Session dbsession = null;
        try {//used to create database connection
            dbsession = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction tx = dbsession.beginTransaction();
            conn = dbsession.connection();
        } catch (HibernateException e) {
        }

        MisReportConfigMast reportConfigMast = null;
        try {
            reportConfigMast = factory.getMisReportConfigMastDAO().getReportAsSeqId(getSeqId(), utl);
            setReportConfig(reportConfigMast);
        } catch (Exception e) {
            utl.generateLog("error in getting mis configuration", e.getMessage());
        }

        MisReportConfigMastFilterDAO misRepConfigFilterDao = factory.getMisReportConfigMastFilterDAO();
        List<MisReportConfigMastFilter> misFilterDetails = misRepConfigFilterDao.getListDataAsSeqId(getSeqId(), utl);
        HashMap<String, String> all_inputs = new HashMap<>();
        try {
            LinkedHashMap<String, String> all_report_inputs = new LinkedHashMap<>();
            if (reportConfigMast != null) {
                if (!utl.isnull(reportConfigMast.getRep_assesm_fltr_flag()) && reportConfigMast.getRep_assesm_fltr_flag().equals("T")) {
                    all_report_inputs.put("accYear", "");
                    all_report_inputs.put("quarterNo", "");
                    all_report_inputs.put("tdsTypeCode", "");
                }
            }
            if (misFilterDetails != null && !misFilterDetails.isEmpty()) {
                misFilterDetails.stream()
                        .map((rpt_para) -> rpt_para.getFilter_column())
                        .forEachOrdered((parameter_name) -> {
                            all_report_inputs.put(parameter_name, "");
                        });
            }
            if (!all_report_inputs.isEmpty()) {
                all_inputs = process_report_URL_paras(all_report_inputs);
                setReport_paras_processed(all_report_inputs);
            }
        } catch (Exception e) {
        }

        if (!utl.isnull(getAction())) {
            if (getAction().equals("reportContent")) {
                setDataGridAction("showReportDetailAction");
                setPageNumber("0");
                try {
                    MisReportConfigMastFilterDAO reportConfigMastFilerDao = factory.getMisReportConfigMastFilterDAO();
                    setReportParaList(reportConfigMastFilerDao.getListDataAsSeqId(getSeqId(), utl));
                } catch (Exception e) {
                    setReportParaList(null);
                }
                //setReport_header_text(obj_DB.getReport_header() + strAccYear + strQtr + strFormType);
                l_return_value = "group_content";

            } else if (getAction().equalsIgnoreCase("misRecordsCount")) {

                long total = obj_DB.get_report_data_count(conn, all_inputs, getSeqId(), true);

                setTotalRecords(total);
                setPageNumber("0");
                setTotalRecordsCount(total);

                int pages = 0;
                StringBuilder sb = new StringBuilder();
                try {
                    if (total > 0) {
                        String recNumber = (String) session.get("MISRECORDSCOUNT");// records per page
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
                    } else {
                        setPageNumber("0");
                    }
                    setTotalPages(pages);
                    sb.append("success").append("~");
                    sb.append(getTotalRecords()).append("~");
                    sb.append(getTotalPages()).append("~");
                    sb.append(getPageNumber());
                } catch (Exception e) {
                    sb.setLength(0);

                    sb.append("error").append("~");
                    sb.append("0").append("~");
                    sb.append("0").append("~");
                    sb.append("0");
                    e.printStackTrace();
                }

                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
                l_return_value = "success";

            } else if (getAction().equalsIgnoreCase("showReportDetail")) {
                int l_record_MNL = 1;
                int l_record_MXL = 10;

                if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true") && getReq_pagination_flag().equals("T")) {

                    if (!utl.isnull(getSearch())) {
                        try {
                            if (getTotalRecords() > 0) {
                                int recVal = Integer.parseInt(getRecordsPerPage());
                                if (recVal > 0) {
                                    int recPerPage = Integer.parseInt(getRecordsPerPage());
                                    if (recPerPage > 0) {
                                        int maxVal = Integer.parseInt(String.valueOf(getTotalRecords()));
                                        int minVal = 1;

                                        if (getTotalRecords() > recVal) {
                                            maxVal = recPerPage * recVal;
                                            minVal = maxVal - recVal + 1;
                                            if (maxVal > getTotalRecords()) {
                                                maxVal = Integer.parseInt(String.valueOf(getTotalRecords()));
                                            }
                                        }
                                        setStartIndex(minVal - 1);
                                        setEndIndex((maxVal - 1));

                                        int l_start_page = 0;
                                        int page_number = !utl.isnull(getPageNumber()) ? Integer.parseInt(getPageNumber()) : 0;
                                        if (page_number == 0) {
                                            l_start_page = 1;
                                        } else {
                                            l_start_page = page_number;
                                        }

                                        int l_records_to_add = recPerPage;
                                        l_record_MXL = (l_start_page * l_records_to_add);
                                        l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }// setting for required pagination flag equal T

//                if (misFilterDetails != null) {
                try {
                    ArrayList<ArrayList<String>> report_list_data = obj_DB.get_report_data(conn, all_inputs, getSeqId(), true, l_record_MNL, l_record_MXL, req_pagination_flag);
                    setReport_data(report_list_data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setReport_attributes(obj_DB.getReport_attributes());
                ArrayList<ReportHeadAttributes> obj_arrList = obj_DB.getReport_attributes();

                String column_heading = process_column_headings(obj_arrList);
                setColumn_headings(column_heading);
                setReport_header_text(obj_DB.getReport_header() + strAccYear + strQtr + strFormType);

                int[] tot = new int[25];
                for (int i = 0; i < getReport_data().size(); i++) {
                    ArrayList<String> get_rep_list = getReport_data().get(i);
                    for (int j = 0; j < get_rep_list.size(); j++) {
                        try {
                            if (getReport_attributes().get(j).report_header_data_type.contains("#T") && utl.isNumber(get_rep_list.get(j))) {
                                tot[j] = tot[j] + Integer.parseInt(get_rep_list.get(j).replaceAll(",", ""));
                            } else if (getReport_attributes().get(j).report_header_data_type.contains("#A")) {
                                if (utl.isNumber(get_rep_list.get(j))) {
                                    tot[j] = tot[j] + Integer.parseInt(get_rep_list.get(j).replaceAll(",", ""));
                                }
                                tot[j] = (i + 1);
                            }
                        } catch (Exception e) {
                        }
                    }
                }

                try {
                    for (int j = 0; j < getReport_attributes().size(); j++) {

                        if (getReport_attributes().get(j).report_header_data_type.contains("#T")) {
                            setTotal_table_flag("T");
                            report_data_total.add("Total: " + utl.getAmountIndianFormat((double) tot[j]));
                        } else if (getReport_attributes().get(j).report_header_data_type.contains("#A")) {
                            setTotal_table_flag("T");
                            if (tot[j] > 0) {
                                double avgD = (double) tot[j] / (getReport_data().size());
                                report_data_total.add("Average: " + utl.getAmountIndianFormat(avgD));
                            } else {
                                report_data_total.add("Average: " + String.valueOf(tot[j]));
                            }
                        } else if (getReport_attributes().get(j).report_header_data_type.contains("#N")) {
                            setTotal_table_flag("T");
                            report_data_total.add("Count: " + String.valueOf(tot[j]));
                        } else {
                            report_data_total.add(utl.getAmountIndianFormat((double) tot[j]));
                        }
                    }
                } catch (Exception e) {
                }
                try {//used to get sql_test query & no of column
                    String report_query = obj_DB.l_query;
                    report_query = utl.isnull(report_query) ? "" : report_query;
                    setL_report_query(report_query);
                    int l_column_count = obj_DB.l_column_count_value;
                    setTotalNoColumn(l_column_count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                }//end if               
               try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    dbsession.getTransaction().commit();
                    dbsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    MisReportSupport mis_support = new MisReportSupport();
                    l_return_msg = mis_support.getReportData(report_attributes, report_data, report_data_total);
                    inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
                } catch (Exception e) {
                }
                l_return_value = "success";
            } else if (getAction().equalsIgnoreCase("recordsPerPage")) {
                try {
                    session.put("MISRECORDSCOUNT", String.valueOf(getSetRecPerPage()));

                    l_return_value = "success";
                    l_return_msg = "success";
                } catch (Exception e) {
                    l_return_msg = "error";
                }
                inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
            } else if (getAction().equalsIgnoreCase("autocomplete")) {

                inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
            }
        }
        return l_return_value;
    }

    private HashMap<String, String> process_report_URL_paras(LinkedHashMap<String, String> a_report_paras) {
        try {
            String l_reoprt_paras = "";
            Set mapkeys = a_report_paras.keySet();
            Object[] all_keys = mapkeys.toArray();
            for (Object key : all_keys) {
                try {
                    String para_name = key.toString();
                    String[] para_value = (String[]) getParameters().get(para_name);
                    String parameter_value = para_value[0];
                    parameter_value = utl.isnull(parameter_value) ? "" : parameter_value;
                    a_report_paras.put(para_name, parameter_value);
                    l_reoprt_paras += parameter_value + "~";
                } catch (Exception e) {
                }
            }//end for

            setReport_paras(l_reoprt_paras);

            // Commented due to replacing default assessment and login params
            // while preparing query for count & details purpose.. 20.12.2019
//            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
//            Assessment asmt = (Assessment) session.get("ASSESSMENT");
//
//            String clientCode = viewClientMast.getClient_code();
//            String entity_code = viewClientMast.getEntity_code();
//            String quarterNumber = asmt.getQuarterNo();
//            String tdsTypeCode = asmt.getTdsTypeCode();
//            String accYear = asmt.getAccYear();
//
//            a_report_paras.put("clientCode", clientCode);
//            a_report_paras.put("entityCode", entity_code);
//            a_report_paras.put("quarterNo", quarterNumber);
//            a_report_paras.put("tdsTypeCode", tdsTypeCode);
//            a_report_paras.put("accYear", accYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a_report_paras;
    }//end method

    private String process_column_headings(ArrayList<ReportHeadAttributes> obj_arrList) {
        String return_val = "";
        String column_headings = "";
        try {
            if (obj_arrList != null && obj_arrList.size() > 0) {
                for (int i = 0; i < obj_arrList.size(); i++) {
                    column_headings += obj_arrList.get(i).getReport_header_captions() + "~";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return_val = column_headings;
        return return_val;
    }//end method

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public long getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(long setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
    }

    public String getReq_pagination_flag() {
        return req_pagination_flag;
    }

    public void setReq_pagination_flag(String req_pagination_flag) {
        this.req_pagination_flag = req_pagination_flag;
    }

    public List<MisReportConfigMastFilter> getReportParaList() {
        return reportParaList;
    }

    public void setReportParaList(List<MisReportConfigMastFilter> reportParaList) {
        this.reportParaList = reportParaList;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
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

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
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

    public HashMap<String, String> getReport_paras_processed() {
        return report_paras_processed;
    }

    public void setReport_paras_processed(HashMap<String, String> report_paras_processed) {
        this.report_paras_processed = report_paras_processed;
    }

    public Map getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public String getReport_paras() {
        return report_paras;
    }

    public void setReport_paras(String report_paras) {
        this.report_paras = report_paras;
    }

    public ArrayList<ReportHeadAttributes> getReport_attributes() {
        return report_attributes;
    }

    public void setReport_attributes(ArrayList<ReportHeadAttributes> report_attributes) {
        this.report_attributes = report_attributes;
    }

    public String getColumn_headings() {
        return column_headings;
    }

    public void setColumn_headings(String column_headings) {
        this.column_headings = column_headings;
    }

    public String getReport_header_text() {
        return report_header_text;
    }

    public void setReport_header_text(String report_header_text) {
        this.report_header_text = report_header_text;
    }

    public String getAjax_calling_url() {
        return ajax_calling_url;
    }

    public void setAjax_calling_url(String ajax_calling_url) {
        this.ajax_calling_url = ajax_calling_url;
    }

    public ArrayList<ArrayList<String>> getReport_data() {
        return report_data;
    }

    public void setReport_data(ArrayList<ArrayList<String>> report_data) {
        this.report_data = report_data;
    }

    public String getXls_dwl_rights() {
        return xls_dwl_rights;
    }

    public void setXls_dwl_rights(String xls_dwl_rights) {
        this.xls_dwl_rights = xls_dwl_rights;
    }

    public int getTotalNoColumn() {
        return totalNoColumn;
    }

    public void setTotalNoColumn(int totalNoColumn) {
        this.totalNoColumn = totalNoColumn;
    }

    public String getDb_server() {
        return db_server;
    }

    public void setDb_server(String db_server) {
        this.db_server = db_server;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_pass() {
        return db_pass;
    }

    public void setDb_pass(String db_pass) {
        this.db_pass = db_pass;
    }

    public String getL_report_query() {
        return l_report_query;
    }

    public void setL_report_query(String l_report_query) {
        this.l_report_query = l_report_query;
    }

    public String getReplace_value_for_pdf() {
        return replace_value_for_pdf;
    }

    public void setReplace_value_for_pdf(String replace_value_for_pdf) {
        this.replace_value_for_pdf = replace_value_for_pdf;
    }

    public String getReport_url() {
        return report_url;
    }

    public void setReport_url(String report_url) {
        this.report_url = report_url;
    }

    public String getDb_sid() {
        return db_sid;
    }

    public void setDb_sid(String db_sid) {
        this.db_sid = db_sid;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public ArrayList<String> getReport_data_total() {
        return report_data_total;
    }

    public void setReport_data_total(ArrayList<String> report_data_total) {
        this.report_data_total = report_data_total;
    }

    public String getTotal_table_flag() {
        return total_table_flag;
    }

    public void setTotal_table_flag(String total_table_flag) {
        this.total_table_flag = total_table_flag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getStrAccYear() {
        return strAccYear;
    }

    public void setStrAccYear(String strAccYear) {
        this.strAccYear = strAccYear;
    }

    public String getStrQtr() {
        return strQtr;
    }

    public void setStrQtr(String strQtr) {
        this.strQtr = strQtr;
    }

    public String getStrFormType() {
        return strFormType;
    }

    public void setStrFormType(String strFormType) {
        this.strFormType = strFormType;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public String getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }

    public MisReportConfigMast getReportConfig() {
        return reportConfig;
    }

    public void setReportConfig(MisReportConfigMast reportConfig) {
        this.reportConfig = reportConfig;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
