/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsMastDAO;
import dao.ViewTdsDeductReasonDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsMast;
import hibernateObjects.ViewTdsDeductReason;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class DataListAJAXAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;
    private String action;
    private String deductReasonFlag;
    private String Htds_code;
    private String tdsCode;
    private String tdsCodeStr;
    private String HTdsDeductReason;
    private String deductee_name;
    private String deductee_panno;
    private String clientCode;
    private String quarterNo;
    private String acc_year;
    private String tdsTypeCode;
    private String deductee_client_code;
    private String flag;
    private String rowid_seq;

    private String deductee_code;

    public DataListAJAXAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        StringBuilder sb = new StringBuilder();
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("getTdsDeductReasonFlag")) {
                TdsMastDAO tdsMastDAO = factory.getTdsMastDAO();
                TdsMast tdsmast = tdsMastDAO.getAsDeductReasonAsCode(getTdsCode(), utl);
                String l_deduct_reason = "";
                if (tdsmast != null) {
                    l_deduct_reason = tdsmast.getTds_deduct_reason_flag_str();
                    //System.out.println("l_deduct_reason..." + l_deduct_reason);
                }
                inputStream = new ByteArrayInputStream(l_deduct_reason.getBytes("UTF-8"));
            } else if (getAction().equalsIgnoreCase("getDeductReason")) {
                String flag = getTdsCodeStr();
                ViewTdsDeductReasonDAO tdsDeductDAO = factory.getViewTdsDeductReasonDAO();
                List<ViewTdsDeductReason> viewtdsdeduct = tdsDeductDAO.getDeductReasonList(asmt.getTdsTypeCode(), utl);
                sb.append("<option value=\"\">Select TDS Deduct Reason</option>");
                if (viewtdsdeduct != null) {
                    for (ViewTdsDeductReason tdslist : viewtdsdeduct) {
                        if (!utl.isnull(getHTdsDeductReason()) && (getHTdsDeductReason().equals(tdslist.getTds_deduct_reason()))) {
                            sb.append("<option value=\"").append(tdslist.getTds_deduct_reason()).append("\" selected=\"true\">").append(tdslist.getTds_deduct_reason_name()).append("</option>");
                        } else {
                            sb.append("<option value=\"").append(tdslist.getTds_deduct_reason()).append("\">").append(tdslist.getTds_deduct_reason_name()).append("</option>");
                        }
                    }
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
//            else if (getAction().equalsIgnoreCase("getRefNo")) {
//                DeducteeBflagRefinfoTranDAO deducteeBflagRef = factory.getDeducteeBflagRefinfoTranDAO();
//                String refno = "";
//                ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
////                Assessment asmt = (Assessment) session.get("ASSESSMENT");
//                String l_acc_year = asmt.getAccYear().getAcc_year();
//                DeducteeBflagRefinfoTran deducteeBflagRefinfo = deducteeBflagRef.readRefrenceNoFromDeducteeCode(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), utl, getDeductee_code(), l_acc_year);
//                if (deducteeBflagRefinfo != null) {
//                    refno = deducteeBflagRefinfo.getReference_no();
//                }
//                inputStream = new ByteArrayInputStream(refno.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("getRefNoDetail")) {
//                String refno = "";
//                try {
//                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
////                    Assessment asmt = (Assessment) session.get("ASSESSMENT");
//                    String l_acc_year = asmt.getAccYear().getAcc_year();
//                    String l_code_level = "";
//                    if (lhssysparam != null) {
//                        l_code_level = lhssysparam.getParameter_value();
//                    }
//
//                    String l_clientCode = "";
//                    if (utl.isnull(getDeductee_client_code())) {
//                        l_clientCode = viewClientMast.getClient_code();
//                    } else {
//                        l_clientCode = getDeductee_client_code();
//                    }
//
//                    DeducteeBflagRefinfoTranDAO deducteeBflagRef = factory.getDeducteeBflagRefinfoTranDAO();
//                    DeducteeBflagRefinfoTran deducteeBflagRefinfo = deducteeBflagRef.readDataAsPanno(viewClientMast.getEntity_code(), l_clientCode, l_acc_year, getDeductee_panno(), l_code_level);
//                    if (deducteeBflagRefinfo != null) {
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                        String l_refrence_no = deducteeBflagRefinfo.getReference_no();
//                        l_refrence_no = utl.isnull(l_refrence_no) ? "" : l_refrence_no;
//                        String l_latest_acc_year = deducteeBflagRefinfo.getLatest_assessment_year();
//                        l_latest_acc_year = utl.isnull(l_latest_acc_year) ? "" : l_latest_acc_year;
//                        String l_est_income = deducteeBflagRefinfo.getEst_declaration_income();
//                        l_est_income = utl.isnull(l_est_income) ? "" : l_est_income;
//                        String l_est_Totalincome = deducteeBflagRefinfo.getEst_total_income();
//                        l_est_Totalincome = utl.isnull(l_est_Totalincome) ? "" : l_est_Totalincome;
//                        String l_TotalForm = deducteeBflagRefinfo.getTotal_no_form();
//                        l_TotalForm = utl.isnull(l_TotalForm) ? "" : l_TotalForm;
//                        String l_aggAmt = deducteeBflagRefinfo.getAggregate_income_amt();
//                        l_aggAmt = utl.isnull(l_aggAmt) ? "" : l_aggAmt;
//                        String l_IncomeAmtPad = deducteeBflagRefinfo.getIncome_amount_paid();
//                        l_IncomeAmtPad = utl.isnull(l_IncomeAmtPad) ? "" : l_IncomeAmtPad;
//                        String l_decleration_date = "";
//                        if (deducteeBflagRefinfo.getDeclaration_date() != null) {
//                            l_decleration_date = sdf.format(deducteeBflagRefinfo.getDeclaration_date());
//                        }
//                        String l_income_pad_date = "";
//                        if (deducteeBflagRefinfo.getIncome_paid_date() != null) {
//                            l_income_pad_date = sdf.format(deducteeBflagRefinfo.getIncome_paid_date());
//                        }
//                        String l_bflag = deducteeBflagRefinfo.getBflag();
//                        l_bflag = utl.isnull(l_bflag) ? "G" : l_bflag;
//                        refno = l_refrence_no + "#" + l_latest_acc_year + "#" + l_est_income + "#" + l_est_Totalincome + "#" + l_TotalForm + "#" + l_aggAmt + "#" + l_decleration_date + "#" + l_IncomeAmtPad + "#" + l_income_pad_date + "#" + l_bflag;
//                    }
//                } catch (Exception e) {
//                }
//                inputStream = new ByteArrayInputStream(refno.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("getRefNoAsPan")) {
//                String refno = "";
//                try {
//                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
////                    Assessment asmt = (Assessment) session.get("ASSESSMENT");
//                    String l_code_level = "";
//                    LhssysParametersDAO objlhssysparam = factory.getLhssysParametersDAO();
//                    LhssysParameters lhssysparam = objlhssysparam.readDataAsParameterName("15_GH_SEQ_ASSIGN_LEVEL", viewClientMast.getEntity_code(), "T");
//                    if (lhssysparam != null) {
//                        l_code_level = lhssysparam.getParameter_value();
//                    }
//
//                    String l_clientCode = "";
//                    if (utl.isnull(getDeductee_client_code())) {
//                        l_clientCode = viewClientMast.getClient_code();
//                    } else {
//                        l_clientCode = getDeductee_client_code();
//                    }
//
//                    DeducteeBflagRefinfoTranDAO deducteeBflagRefinfoTranDAO = factory.getDeducteeBflagRefinfoTranDAO();
//                    DeducteeBflagRefinfoTran objDeducteeBflag = deducteeBflagRefinfoTranDAO.readDataAsPanno(viewClientMast.getEntity_code(), l_clientCode, asmt.getAccYear().getAcc_year(), getDeductee_panno(), l_code_level);
//                    if (objDeducteeBflag != null) {
//                        refno = objDeducteeBflag.getReference_no();
//                    }
//                } catch (Exception e) {
//                }
//                inputStream = new ByteArrayInputStream(refno.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("getGeneratedRefNo")) {
//                String refNo = "";
//                try {
//                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
////                Assessment asmt = (Assessment) session.get("ASSESSMENT");
//                    if (getFlag().equalsIgnoreCase("add")) {
//                        try {
//                            String l_reference_query
//                                    = "select t.reference_no, t.panno\n"
//                                    + "  from deductee_mast_15gh t\n"
//                                    + " where t.entity_code = '" + viewClientMast.getEntity_code() + "'\n"
//                                    + "   and exists\n"
//                                    + " (select 1\n"
//                                    + "          from client_mast w1\n"
//                                    + "         where w1.client_code = t.client_code\n"
//                                    + "           and (w1.client_code = '" + viewClientMast.getClient_code() + "' or w1.parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                    + "               w1.g_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sg_parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                    + "               w1.ssg_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sssg_parent_code = '" + viewClientMast.getClient_code() + "'))\n"
//                                    + "   and t.acc_year = '" + asmt.getAccYear().getAcc_year() + "'\n"
//                                    + "   and t.tds_type_code = '" + asmt.getForm().getTds_type_code() + "'\n"
//                                    + "   and t.quarter_no = '" + asmt.getQuarter().getPeriod_name().split("-")[2] + "'\n"
//                                    + "   and t.panno = '" + getDeductee_panno() + "'";
//                            DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
//                            refNo = objDbFunctionClass.execute_oracle_function_as_string(l_reference_query);
//                        } catch (Exception e) {
//                        }
//                    } else {
//                        String cer_query
//                                = "select t.certificate_no\n"
//                                + "  from tds_tran_load t\n"
//                                + " where t.entity_code = '" + viewClientMast.getEntity_code() + "'\n"
//                                + "   and exists\n"
//                                + " (select 1\n"
//                                + "          from client_mast w1\n"
//                                + "         where w1.client_code = t.client_code\n"
//                                + "           and (w1.client_code = '" + viewClientMast.getClient_code() + "' or w1.parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                + "               w1.g_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sg_parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                + "               w1.ssg_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sssg_parent_code = '" + viewClientMast.getClient_code() + "'))\n"
//                                + "   and t.acc_year = '" + asmt.getAccYear().getAcc_year() + "'\n"
//                                + "   and t.quarter_no = '" + asmt.getQuarter().getPeriod_name().split("-")[2] + "'\n"
//                                + "   and t.tds_type_code = '" + asmt.getForm().getTds_type_code() + "'\n"
//                                + "   and t.rowid_seq = '" + getRowid_seq() + "'";
//                        DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
//                        refNo = objDbFunctionClass.execute_oracle_function_as_string(cer_query);
//                        if (utl.isnull(refNo)) {
//                            String l_reference_query
//                                    = "select t.reference_no, t.panno\n"
//                                    + "  from deductee_mast_15gh t\n"
//                                    + " where t.entity_code = '" + viewClientMast.getEntity_code() + "'\n"
//                                    + "   and exists\n"
//                                    + " (select 1\n"
//                                    + "          from client_mast w1\n"
//                                    + "         where w1.client_code = t.client_code\n"
//                                    + "           and (w1.client_code = '" + viewClientMast.getClient_code() + "' or w1.parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                    + "               w1.g_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sg_parent_code = '" + viewClientMast.getClient_code() + "' or\n"
//                                    + "               w1.ssg_parent_code = '" + viewClientMast.getClient_code() + "' or w1.sssg_parent_code = '" + viewClientMast.getClient_code() + "'))\n"
//                                    + "   and t.acc_year = '" + asmt.getAccYear().getAcc_year() + "'\n"
//                                    + "   and t.tds_type_code = '" + asmt.getForm().getTds_type_code() + "'\n"
//                                    + "   and t.quarter_no = '" + asmt.getQuarter().getPeriod_name().split("-")[2] + "'\n"
//                                    + "   and t.panno = '" + getDeductee_panno() + "'";
//                            DbFunctionExecutorAsString objDbFunctionClass1 = new DbFunctionExecutorAsString();
//                            refNo = objDbFunctionClass1.execute_oracle_function_as_string(l_reference_query);
//                        }
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                inputStream = new ByteArrayInputStream(refNo.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("getErrorListForFilter")) {
//                String refno = "";
//
//                try {
//                    ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
//                    ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
//                    refno = createErrorListForFilter(client, asmt);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    refno = "<option value=''>-------select-------</option>";
//                }
//                inputStream = new ByteArrayInputStream(refno.getBytes("UTF-8"));
//
//            } else if (getAction().equalsIgnoreCase("approveCorrection")) {
//                String msg = "error";
//                if (!utl.isnull(getRowid_seq())) {
//
//                    String qury = "update tds_tran_load t set t.tds_tran_corr_approvedby ='SHASHANK' , T.TDS_TRAN_CORR_APPROVEDDATE = SYSDATE  where t.rowid_seq='" + getRowid_seq() + "'";
//                    System.out.println("qury---" + qury);
//                    DbFunctionExecutorAsString executor = new DbFunctionExecutorAsString();
//                    boolean update_function = executor.execute_oracle_update_function_as_string(qury);
//                    if (update_function) {
//                        msg = "success";
//                    }
//
//                }
//                inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("approveAllCorrection")) {
//                String msg = "error";
//                ProcTdsCorrApproval proc = new ProcTdsCorrApproval();
//                ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
//
//                String tds_type_code = asmt.getForm().getTds_type_code();
//                String acc_year = asmt.getAccYear().getAcc_year();
//                String tds_quarter = asmt.getQuarter().getPeriod_name();
//                String[] quarterNameSplit = tds_quarter.split("-");
//                int quarterNo = Integer.parseInt(quarterNameSplit[2]);
//                Date l_from_date = asmt.getQuarter().getFrom_date();
//                Date l_to_date = asmt.getQuarter().getTo_date();
//                Long l_client_loginid_seq;
//                Object sessionId = session.get("LOGINSESSIONID");
//                String assement_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear().getAcc_year());//change according to DB
//                try {
//                    l_client_loginid_seq = (Long) sessionId;
//                } catch (Exception e) {
//                    l_client_loginid_seq = 0l;
//                }
//
//                String result = proc.execute_procedure(clientMast.getEntity_code(), clientMast.getClient_code(), acc_year, assement_acc_year, quarterNo, l_from_date, l_to_date, tds_type_code, l_client_loginid_seq, "SHASHANK");
//
//                if (!utl.isnull(result) && result.equalsIgnoreCase("0")) {
//                    msg = "success";
//                }
//
//                inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
//            } else if (getAction().equalsIgnoreCase("revertAllCorrection")) {
//                String msg = "error";
//
//                ProcTdsCorrRevert proc = new ProcTdsCorrRevert();
//                ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
//
//                String tds_type_code = asmt.getForm().getTds_type_code();
//                String acc_year = asmt.getAccYear().getAcc_year();
//                String tds_quarter = asmt.getQuarter().getPeriod_name();
//                String[] quarterNameSplit = tds_quarter.split("-");
//                int quarterNo = Integer.parseInt(quarterNameSplit[2]);
//                Date l_from_date = asmt.getQuarter().getFrom_date();
//                Date l_to_date = asmt.getQuarter().getTo_date();
//                Long l_client_loginid_seq;
//                Object sessionId = session.get("LOGINSESSIONID");
//                String assement_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear().getAcc_year());//change according to DB
//                try {
//                    l_client_loginid_seq = (Long) sessionId;
//                } catch (Exception e) {
//                    l_client_loginid_seq = 0l;
//                }
//
//                String result = proc.execute_procedure(clientMast.getEntity_code(), clientMast.getClient_code(), acc_year, assement_acc_year, quarterNo, l_from_date, l_to_date, tds_type_code, l_client_loginid_seq, "SHASHANK");
//
//                if (!utl.isnull(result) && result.equalsIgnoreCase("0")) {
//                    msg = "success";
//                }
//                inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
        }
//

        return l_return_value;
    }//end method

//    public String createErrorListForFilter(ViewClientMast client, Assessment asmt) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<option value=''>-------select-------</option>");
//        try {
//            String client_code = client.getClient_code();
//            String Entity_code = client.getEntity_code();
//            String acc_year = asmt.getAccYear().getAcc_year();
//            String tds_quarter = asmt.getQuarter().getPeriod_name();
//            String[] quarterNameSplit = tds_quarter.split("-");
//            String quarterNo = quarterNameSplit[2];
//
//            int quarter_no = Integer.parseInt(quarterNo);
//            String tds_type_code = asmt.getForm().getTds_type_code();
//
//            String query = "select t.error_code, t.error_name, t.total_records from tran_load_error_summ t\n"
//                    + "         where t.entity_code = '" + Entity_code + "' --Login Entity Code\n"
//                    + "           and t.client_code = '" + client_code + "' --login Client Code\n"
//                    + "           and t.acc_year = '" + acc_year + "' --Login A/c Year\n"
//                    + "           and t.quarter_no = " + quarter_no + " --Login Quarter Number\n"
//                    + "           and t.tds_type_code = '" + tds_type_code + "' --Login TDS Type Code\n"
//                    //                    + "           AND T.ERROR_TYPE_CODE = 'TSE'\n"// Not Required
//                    + "           and t.table_name='TDS_TRAN_LOAD' \n";
//            utl.generateSpecialLog("create Error List For Manage TDS Filter--316--", query);
//            DbQueryExecutorAsList dbQueryList = new DbQueryExecutorAsList();
//            ArrayList<ArrayList<String>> arrayList = dbQueryList.execute_oracle_query_as_list(query);
//
//            if (arrayList != null) {
//                for (ArrayList<String> list : arrayList) {
////                    String error_code = list.get(0);
////                    String error_name = list.get(1);
//                    String total_records = list.get(2);
//                    if (utl.isnull(total_records)) {
//                        total_records = "0";
//                    }
//
//                    sb.append("<option value='" + list.get(0) + "'>" + list.get(1) + "(" + total_records + ")" + "</option>");
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }//End Method
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDeductReasonFlag() {
        return deductReasonFlag;
    }

    public void setDeductReasonFlag(String deductReasonFlag) {
        this.deductReasonFlag = deductReasonFlag;
    }

    public String getHtds_code() {
        return Htds_code;
    }

    public void setHtds_code(String Htds_code) {
        this.Htds_code = Htds_code;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTdsCode() {
        return tdsCode;
    }

    public void setTdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
    }

    public String getTdsCodeStr() {
        return tdsCodeStr;
    }

    public void setTdsCodeStr(String tdsCodeStr) {
        this.tdsCodeStr = tdsCodeStr;
    }

    public String getHTdsDeductReason() {
        return HTdsDeductReason;
    }

    public void setHTdsDeductReason(String HTdsDeductReason) {
        this.HTdsDeductReason = HTdsDeductReason;
    }

//    public String getTdsSection() {
//        return tdsSection;
//    }
//
//    public void setTdsSection(String tdsSection) {
//        this.tdsSection = tdsSection;
//    }
    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getQuarterNo() {
        return quarterNo;
    }

    public void setQuarterNo(String quarterNo) {
        this.quarterNo = quarterNo;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTdsTypeCode() {
        return tdsTypeCode;
    }

    public void setTdsTypeCode(String tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getDeductee_client_code() {
        return deductee_client_code;
    }

    public void setDeductee_client_code(String deductee_client_code) {
        this.deductee_client_code = deductee_client_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
