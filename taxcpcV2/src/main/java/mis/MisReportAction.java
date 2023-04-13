/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.MisReportConfigMastDAO;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
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
public class MisReportAction extends ActionSupport implements SessionAware {

    private final Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;

    private String action;
    private String alertDesc;
    private String reportGroup;
    private String paraColumn;
    private String headCount;
    private String columnSelectListValue;
    private String req_pagination_flag;
    private String dataGridAction;
    private String module;
    private Long seqId;
    private ArrayList<ReportGroupListData> reportGroupList;
    MisReportSupport misSupport;

    public MisReportAction() {
        utl = new Util();
        reportGroupList = new ArrayList<>();
        misSupport = new MisReportSupport(utl);
    }

    @Override
    public String execute() throws Exception {
        String l_return_value = SUCCESS;
        String l_return_msg = "";

        try {
            session.put("ACTIVE_TAB", "reportAction");
            if (!utl.isnull(getModule())) {
                session.put("MODULE", getModule());
            }

            if (!utl.isnull(getAction())) {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                UserMast user = (UserMast) session.get("LOGINUSER");

                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }

                MisReportConfigMastDAO repConfigDao = factory.getMisReportConfigMastDAO();
                if (getAction().equals("dashboard")) {
                    ArrayList<String> listReportGroup = null;
                    try {
                        if (repConfigDao != null) {
                            listReportGroup = repConfigDao.getDistinctReportGroup(utl);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (listReportGroup != null) {
                        System.out.println("mis report listReportGroup size -->"+listReportGroup);
                        for (String report_group : listReportGroup) {
                            String Header_type = "Other Report";

                            ReportGroupListData obj_data = new ReportGroupListData();
                            report_group = utl.isnull(report_group) ? "" : report_group;

                            if (!report_group.equalsIgnoreCase("")) {
                                Header_type = report_group;
                            } else {
                                Header_type = "Other Report";
                            }
                            obj_data.setL_report_group(Header_type);
                            
                              System.out.println(obj_data.toString());
                            reportGroupList.add(obj_data);
                        }
                        if (reportGroupList != null && !reportGroupList.isEmpty()) {
                            MisReportSupport misSupport = new MisReportSupport();
                            l_return_msg = misSupport.getMisDashboard(reportGroupList, utl, client.getEntity_code(),asmt.getTdsTypeCode());
                        } else {
                            l_return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                        }
                    } else {
                        l_return_msg = GlobalMessage.PAGINATION_NO_RECORDS;
                    }
                    l_return_value = "ajaxResult";
                } else if (getAction().equalsIgnoreCase("paraDescSelect")) {

                    StringBuilder sbb = new StringBuilder();
                    sbb.append("<select id=\"field_").append(getHeadCount()).append("\" name=\"").append(getParaColumn()).append("\" class=\"form-control\" style=\"width:100%;\">");
                    try {
                        if (!utl.isnull(getColumnSelectListValue())) {
                            String NewColumnSelectList = getColumnSelectListValue().replace("'", "");
                            String SplitList[] = NewColumnSelectList.split("#");
                            for (String string : SplitList) {
                                if (!utl.isnull(string)) {
                                    String splitString[] = string.split("~");
                                    sbb.append("<option value=\"").append(splitString[1]).append("\">").append(splitString[0]).append("</option>");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sbb.append("</select>");
                    l_return_msg = sbb.toString();
                    l_return_value = "ajaxResult";

                } else if (getAction().equalsIgnoreCase("misBulkDownload")) {
                    GetTokenTransactions tokenObj = new GetTokenTransactions();
                    String tokenNo = tokenObj.generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "", "", "Process Initiated by user !",
                            "MIS_REPORT_DOWNLOAD", "PROCESS_INSERT", getReportGroup(), "");
                    try {
                        l_return_msg = tokenNo;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    l_return_value = "ajaxResult";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getParaColumn() {
        return paraColumn;
    }

    public void setParaColumn(String paraColumn) {
        this.paraColumn = paraColumn;
    }

    public String getHeadCount() {
        return headCount;
    }

    public void setHeadCount(String headCount) {
        this.headCount = headCount;
    }

    public String getColumnSelectListValue() {
        return columnSelectListValue;
    }

    public void setColumnSelectListValue(String columnSelectListValue) {
        this.columnSelectListValue = columnSelectListValue;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getAlertDesc() {
        return alertDesc;
    }

    public void setAlertDesc(String alertDesc) {
        this.alertDesc = alertDesc;
    }

    public String getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
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

    public ArrayList<ReportGroupListData> getReportGroupList() {
        return reportGroupList;
    }

    public void setReportGroupList(ArrayList<ReportGroupListData> reportGroupList) {
        this.reportGroupList = reportGroupList;
    }

    public String getReq_pagination_flag() {
        return req_pagination_flag;
    }

    public void setReq_pagination_flag(String req_pagination_flag) {
        this.req_pagination_flag = req_pagination_flag;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
