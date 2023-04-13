/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.LoginValidatorSupport;
import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcGenerateTdsTextFile;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class DynamicContentDataAsAJAX extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String month;
    private String upload_purpose;
    private String file_error_type;
    private String deductee_code;
    private String process_level;
    private Long rowid_seq;

    public DynamicContentDataAsAJAX() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        StringBuilder sb = new StringBuilder();
        ProcessErrorsDB procDB = new ProcessErrorsDB();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String l_client_code = "";
        String l_entity_code = "";
        Date approveddate = null;
        try {
            ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
            ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            l_client_code = client.getClient_code();
            l_entity_code = client.getEntity_code();
            approveddate = client.getApproveddate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!utl.isnull(getAction())) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String acc_year = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();
            int l_quarterNo = Integer.parseInt(quarterNo);
            String tds_type_code = asmt.getTdsTypeCode();

            Double l_period_no = 0d;
            try {
                l_period_no = Double.parseDouble(quarterNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getAction().equalsIgnoreCase("dynamicSelectSeqno")) {
                List<LhssysFileTran> lhssysfiletran = factory.getLhssysFileTranDAO().readDynamicFileContentDataByParameter(l_client_code, tds_type_code, acc_year, l_period_no, getUpload_purpose(), getFile_error_type(), getMonth(), utl);
                if (lhssysfiletran != null) {
                    if (getMonth().equalsIgnoreCase("All")) {
                        sb.append("<option value=\"All\">All File Of This Quarter</option>");
                    } else {
                        for (LhssysFileTran lhssysFileTran : lhssysfiletran) {
                            sb.append("<option value=\"").append(lhssysFileTran.getFile_seqno()).append("\">").append(lhssysFileTran.getFile_name()).append("</option>");
                        }
                    }
                }
            } else if (getAction().equalsIgnoreCase("getReviewFlagCount")) {
                try {
                    String l_review_check_query = procDB.getReviewFlagCountQuery(l_client_code, l_entity_code, asmt);
                       utl.generateSpecialLog ("Query",l_review_check_query);
                    Long total = 0l;
                    DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                    total = objDBFunction.execute_oracle_function_as_integar(l_review_check_query);
                    if (total > 0) {
                        sb.append("F");
                    } else {
                        sb.append("T");
                    }
                } catch (Exception e) {
                }
            } else if (getAction().equalsIgnoreCase("getValidateGenerateFUV")) {
                LoginValidatorSupport objloginval = new LoginValidatorSupport();
                Long fuvExpiryDays = objloginval.getValidateGenerateFUV(l_entity_code);
                boolean checkValidFUVGen = objloginval.checkApprovedDate(approveddate, fuvExpiryDays);
                if (checkValidFUVGen) {
                    sb.append("T");
                } else {
                    sb.append("F");
                }
            } else if (getAction().equalsIgnoreCase("getSalaryProcedureResult")) {
//                try {
//                    String execureProcedure = new ProcInsertSalaryAmtTran().execureProcedure(
//                            l_entity_code,
//                            l_client_code,
//                            asmt.getAccYear(),
//                            asmt.getTdsTypeCode(),
//                            Long.parseLong(quarterNo),
//                            getDeductee_code(),
//                            getRowid_seq(),
//                            "I");
//                    sb.append(execureProcedure);
//                } catch (Exception e) {
//                    sb.append("error");
//                }
            } else if (getAction().equalsIgnoreCase("callBulkProc")) {

                try {
                    utl.generateLog("calling bulk procedure....", "");
                    Date from_date = asmt.getQuarterFromDate();
                    Date to_date = asmt.getQuarterToDate();

                    Long l_client_loginid_seq;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (NumberFormatException e) {
                        l_client_loginid_seq = 0l;
                    }

                    Integer processLevel = Integer.parseInt(utl.isnull(getProcess_level()) ? "0" : getProcess_level());

                    ProcGenerateTdsTextFile proc = new ProcGenerateTdsTextFile();
//                    proc.execute_procedure(l_entity_code, l_client_code, acc_year, acc_year, l_quarterNo, "", from_date, to_date, tds_type_code, "R", 0, l_client_loginid_seq, "", "", "TX", processLevel.toString(), null, null, utl);
//                    proc.execute_procedure(l_entity_code, l_client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year), l_quarterNo, "", from_date, to_date, tds_type_code, "R", 0, l_client_loginid_seq, "", "", "TX", "",processLevel, utl);
//proc.execute_procedure(entity_code, client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year), l_quarter_no, "", dateFromDate, dateToDate, tds_type, "R", 0, l_client_loginid_seq, "", "", "TX", "", user_code, process_seq);                    
                    sb.append("success");
                } catch (Exception e) {
                    e.printStackTrace();
                    sb.append("error");

                }

            } else {
                List<LhssysFileTran> lhssysfiletran = factory.getLhssysFileTranDAO().readFileContentDataByParameter(l_client_code, tds_type_code, acc_year, l_period_no, getUpload_purpose(), getFile_error_type());
                if (lhssysfiletran != null) {
                    for (LhssysFileTran lhssysFileTran : lhssysfiletran) {
                        sb.append("<option value=\"").append(lhssysFileTran.getFile_seqno()).append("\">").append(lhssysFileTran.getFile_name()).append("</option>");
                    }
                } else {
                    sb.append("<option value=\"\">-------select-------</option>");
                }
            }
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;

    }//end method

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public String getFile_error_type() {
        return file_error_type;
    }

    public void setFile_error_type(String file_error_type) {
        this.file_error_type = file_error_type;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getProcess_level() {
        return process_level;
    }

    public void setProcess_level(String process_level) {
        this.process_level = process_level;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
