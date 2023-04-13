/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.LoginValidatorSupport;
import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcGenerateTdsBulkTxtFile;
import dao.DbProc.ProcInsertSalaryAmtTran;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.SFTPProcessFile;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class SelectDynamicContentDataAsAJAX extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    final Util utl;

    private String action;
    private String month;
    private String upload_purpose;
    private String file_error_type;
    private String deductee_code;
    private String process_level;
    private Long tokenNo;
    private String processStatus;
    private Long rowid_seq;

    private InputStream inputStream;

    public SelectDynamicContentDataAsAJAX() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String returnMsg = "error";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String clobLogFlag = null;
        clobLogFlag = (String) session.get("CLOB_LOG_FLAG") != null ? (String) session.get("CLOB_LOG_FLAG") : "";
        utl.generateLog("get Clob  flag Value from session",clobLogFlag);
        if(utl.isnull(clobLogFlag)){
         try{   
         String queryToFullcsiFileName = "select t.parameter_value from lhssys_parameters t where t.parameter_name LIKE 'CLOB_LOG_FLAG'";
         clobLogFlag = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFullcsiFileName);
         utl.generateLog("get Clob value by using Query",clobLogFlag);     
         }catch(Exception e){
             
         }
        }
        Date approveddate = null;
        ViewClientMast client = null;

        try {
            client = (ViewClientMast) session.get("WORKINGUSER");
          } catch (Exception e) {
            e.printStackTrace();
        }
        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
        try {
            l_client_loginid_seq = (Long) sessionId;
        } catch (Exception e) {
            l_client_loginid_seq = 0l;
        }
        if (client != null) {
            UserMast user = (UserMast) session.get("LOGINUSER");

            String l_client_code = client.getClient_code();
            String l_entity_code = client.getEntity_code();
            String l_bank_branch_code = client.getBank_branch_code();
            approveddate = client.getApproveddate();

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
                        String l_review_check_query
                                = "select count(*) from (select quarter_no,\n"
                                + "       tds_type_code,\n"
                                + "       table_name,\n"
                                + "       error_type_code,\n"
                                + "       error_type_name,\n"
                                + "       lhs_utility.is_tran_load_error_reviewed(entity_code,client_code,acc_year,quarter_no,tds_type_code,error_code) error_reviewed, \n"
                                + "       sum(nvl(record_count,0)) record_count\n"
                                + "  from (select t.entity_code,\n"
                                + "               t.client_code,\n"
                                + "               t.acc_year,\n"
                                + "               t.quarter_no,\n"
                                + "               t.tds_type_code,\n"
                                + "               t.error_type_code,\n"
                                + "               t.error_type_name,\n"
                                + "               null table_name,\n"
                                + "               null column_name,\n"
                                + "               t.error_code,\n"
                                + "               vlm.error_description error_name,\n"
                                + "               vlm.popup_shown,\n"
                                + "               vlm.updation_allow_flag,\n"
                                + "               vlm.show_detail_required,\n"
                                + "               vlm.bulk_pan_verification_flag,\n"
                                + "               vlm.Review_required,\n"
                                + "               nvl(t.record_count, 0) record_count,\n"
                                + "               nvl(t.tds_amt, 0) tds_amt\n"
                                + "          from tran_load_error_part2_temp t, view_lhssys_error_mast_table vlm, client_mast w1\n"
                                + "          where w1.client_code = t.client_code\n"
                                + "          and   (w1.client_code='" + l_client_code + "' or\n"
                                + "                 w1.parent_code='" + l_client_code + "' or\n"
                                + "                 w1.g_parent_code='" + l_client_code + "' or\n"
                                + "                 w1.sg_parent_code='" + l_client_code + "' or\n"
                                + "                 w1.ssg_parent_code='" + l_client_code + "' or\n"
                                + "                 w1.sssg_parent_code='" + l_client_code + "')\n"
                                + "   and vlm.error_code=t.error_code\n"
                                + "   and t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + acc_year + "'\n"
                                + "   and t.quarter_no = " + l_quarterNo + "\n"
                                + "   and t.tds_type_code = '" + tds_type_code + "')\n"
                                + "group by entity_code,client_code,acc_year,error_code,quarter_no,\n"
                                + "       tds_type_code,\n"
                                + "       table_name,\n"
                                + "       error_type_code,\n"
                                + "       error_type_name) where error_reviewed = -1";
                        //utl.generateLog("review_check_query",l_review_check_query); 
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
                } else if (getAction().equalsIgnoreCase("getBulkTextFileToken")) {
                    utl.generateLog(" call for  getBulk TextFile Token",""); 
                    String proc_token = new GetTokenTransactions().generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "",
                            "EG", "", "BULK_FVU_TEXT_GEN", "PROCESS_INSERT", "BULK_FVU_TEXT_GEN", "");
                    sb.append(proc_token);
                    utl.generateLog(" after get BulkTextFile Token",proc_token); 

                } else if (getAction().equalsIgnoreCase("getValidateGenerateFUV")) {
                    LoginValidatorSupport objloginval = new LoginValidatorSupport();
                    Long fuvExpiryDays = objloginval.getValidateGenerateFUV(l_entity_code);
                    boolean checkValidFUVGen = utl.checkApprovedDate(approveddate, fuvExpiryDays);
                    if (checkValidFUVGen) {
                        sb.append("T");
                    } else {
                        sb.append("F");
                    }
                } else if (getAction().equalsIgnoreCase("getSalaryProcedureResult")) {
                    try {
                        String execureProcedure = new ProcInsertSalaryAmtTran().execureProcedure(
                                l_entity_code,
                                l_client_code,
                                asmt.getAccYear(),
                                asmt.getTdsTypeCode(),
                                Long.parseLong(quarterNo),
                                getDeductee_code(),
                                getRowid_seq(),
                                "I");
                        sb.append(execureProcedure);
                    } catch (Exception e) {
                        sb.append("error");
                    }
                } else if (getAction().equalsIgnoreCase("callBulkProc")) {
                    try {

                        Date from_date = asmt.getQuarterFromDate();
                        Date to_date = asmt.getQuarterToDate();

//                        Long l_client_loginid_seq;
//                        Object sessionId = session.get("LOGINSESSIONID");
//                        try {
//                            l_client_loginid_seq = (Long) sessionId;
//                        } catch (NumberFormatException e) {
//                            l_client_loginid_seq = 0l;
//                        }
                        Long proc_client_loginid_seq = l_client_loginid_seq;
                        Integer processLevel = Integer.valueOf(getProcess_level());

                        ProcGenerateTdsBulkTxtFile proc = new ProcGenerateTdsBulkTxtFile();
                        proc.execute_procedure(l_entity_code, l_client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year), l_quarterNo, "", from_date, to_date,
                                tds_type_code, "R", 0, proc_client_loginid_seq, "", "", "TX", "", processLevel, user.getUser_code(), getTokenNo(), utl);

                        sb.append("success");
                        l_return_value = "bulk_gen_init";
                    } catch (Exception e) {
                        sb.append("error");
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("readProcessStatus")) {
                    returnMsg = "Some Error Occurred ! Could Not fetch information";
                     if (!utl.isnull(clobLogFlag) && clobLogFlag.trim().equalsIgnoreCase("T")) {
                        try{
                        utl.generateLog("GET LOG DETAILS FROM CLOB", "");     
                        utl.generateLog("ERROR PROCESS LOG", "");
                        String logProcessType = utl.getLogProcess("ERROR_PROCESS_LOG");
                        returnMsg = new DbFunctionExecutorAsString().get_clob_data(getTokenNo(), logProcessType);
                        if(!utl.isnull(l_return_value)){
                        sb.append(returnMsg);
                        }else{
                        utl.generateLog("LOG not found", "");        
                        returnMsg="Log Not Found In Database";    
                        sb.append(returnMsg);    
                        }
                        }catch(Exception e){
                            
                        }
                    } else {
                        utl.generateLog("GET LOG FROM DIRECTORY", ""); 
                        String oracleDrive = (String) session.get("ORACLEDRIVE");
                        if (!utl.isnull(oracleDrive)) {
                            try {
                                File ora_dir = new File(oracleDrive);
                                if (ora_dir.exists()) {
                                    String fileDir = oracleDrive + File.separator + "TEXT_FILES" + File.separator;
                                    String queryOfLogFileName = "select t.log_file_name1 from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                                    String logFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryOfLogFileName);
                                    String path_to_file = fileDir + logFileName;
                                    utl.generateLog("Log filename", path_to_file);
                                    File log_file = new File(path_to_file.toLowerCase());
                                    if (log_file.exists()) {
                                        try {
                                            String logFileStatus = utl.getLogFile(log_file);
                                            sb.append(logFileStatus);
                                        } catch (Exception e) {
                                            sb.append("Some error occurred, could not read file.");
                                            e.printStackTrace();
                                        }
                                    } else {
                                        sb.append("Log not found..");
                                    }
                                } else {
                                    sb.append("Log not found.");
                                }
                            } catch (Exception e) {
                                sb.append("Some error occurred, could not read file.");
                                e.printStackTrace();
                            }
                        } else {
                            sb.append("Drive not configured, could not read file.");
                        }
                    }
                    l_return_value = "log_status_result";
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
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;

    }//end method

    public String getLogFileTypeFlag() {
        String returnValue = "";
        try {
            if (getProcessStatus().equals("EA") || getProcessStatus().equals("EB") || getProcessStatus().equals("EC")) {
                returnValue = "ERR";

            } else if (getProcessStatus().equals("ED") || getProcessStatus().equals("EE") || getProcessStatus().equals("EF") || getProcessStatus().equals("EG")
                    || getProcessStatus().equals("EH") || getProcessStatus().equals("EI") || getProcessStatus().equals("FA") || getProcessStatus().equals("FB")
                    || getProcessStatus().equals("FC") || getProcessStatus().equals("FD")) {

                returnValue = "FVU";
            }
        } catch (Exception e) {
            utl.generateLog("error while getting log file type", e.getMessage());
        }
        return returnValue;
    }//end method

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
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

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
}//end class
