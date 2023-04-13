/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.globalDBObjects;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcGen15ghBulkCsvFile;
import dao.DbProc.ProcGenErrorReport;
import dao.DbProc.ProcGenerateTdsBulkTxtFile;
import dao.DbProc.ProcGenerateTdsTextFile;
import dao.DbProc.ProcInsertTempData;
import dao.DbProc.ProcLhsTds15ghError;
import dao.DbProc.ProcLhsTemplateError;
import dao.DbProc.ProcLhsTemplateInsert;
import dao.DbProc.ProcMISReportDwnld;
import dao.DbProc.ProcPanUnverifiedTxt;
import dao.DbProc.ProcSalaryMonthTdsCalc;
import dao.DbProc.ProcSalaryTranDedRefresh;
import dao.DbProc.ProcSalaryTranTotalRefresh;
import dao.DbProc.ProcTranLoadErrorDwnld;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.validateError.ProcessTdsTranLoadError;

/**
 *
 * @author gaurav.khanzode
 */
public class CommonProcedureExecutionAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private String callingProcName;
    private String docFileName;
    private String month;
    private Long tokenNo, sequenceId, rowIdSeq, unverifiedPanCount;
    private Integer clientLoginLevel, processLevel;
    private String processType, templateCode, fileHeaderType;
    private String errorType, validate, reprocessFlag;

    public CommonProcedureExecutionAction() {
        this.utl = new Util();;
    }

    @Override
    public String execute() throws Exception {
        ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast userMast = (UserMast) session.get("LOGINUSER");

//        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
//        String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
        String entity_code = clientMast.getEntity_code();
        String client_code = clientMast.getClient_code();
        long client_level = Long.valueOf(clientMast.getCode_level());

        String acc_year = asmt.getAccYear();
        String asmt_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
        int quarter_no = Integer.valueOf(asmt.getQuarterNo());
        Date from_date = asmt.getQuarterFromDate();
        Date to_date = asmt.getQuarterToDate();
        String tds_type_code = asmt.getTdsTypeCode();

        String user_code = userMast.getUser_code();

        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
        try {
            l_client_loginid_seq = (Long) sessionId;
        } catch (Exception e) {
            l_client_loginid_seq = 0l;
        }

        try {
            if (!utl.isnull(getCallingProcName())) {
                utl.generateLog("Calling " + getCallingProcName() + " procedure", "Using Token Number - " + getTokenNo());

                if ("ProcMISReportDwnld".equals(getCallingProcName())) {
                  // Procedure for downloading Bulk MIS Report from MIS section.
                    ProcMISReportDwnld proc_dwnld = new ProcMISReportDwnld();
                    synchronized (proc_dwnld) {
                        proc_dwnld.executeProcedure(entity_code, client_code, acc_year, asmt_acc_year, quarter_no,
                                from_date, to_date, tds_type_code, "", "", null, l_client_loginid_seq,
                                client_level, "", "", user_code, getTokenNo(), getSequenceId());
                    }
                } else if ("ProcInsertTempData".equals(getCallingProcName())) {

                    // Procedure for template import in import section.
                    ProcInsertTempData proc = new ProcInsertTempData();
                    synchronized (proc) {
                        String importTempleteCode = (String) session.get("importTempleteCode");
                        String importProcessType = (String) session.get("importProcessType");

                        proc.execute_procedure(entity_code, client_code, acc_year, quarter_no, from_date, to_date, tds_type_code, importTempleteCode,
                                getTokenNo(), user_code, getTokenNo(), importProcessType);

                        try {
                            session.remove("importTempleteCode");
                            session.remove("importProcessType");
                        } catch (Exception e) {
                        }
                    }
                } else if ("ProcLhsTemplateInsert".equals(getCallingProcName())) {
                    //Exceute_Proc_Lhs_Template_Error_function
                    // Procedure for inserting template data after template import done.
                    ProcLhsTemplateInsert objectprocessInsert = new ProcLhsTemplateInsert();
                    synchronized (objectprocessInsert) {
                        objectprocessInsert.Exceute_Proc_Lhs_Tran_Template_Insert_function(entity_code, client_code, acc_year, asmt_acc_year,
                                quarter_no, from_date, to_date, tds_type_code, getSequenceId(), getProcessType(), getTemplateCode(),
                                user_code, getTokenNo());
                    }
                    

                } else if ("procLhsTemplateError".equals(getCallingProcName())) {
                    //Exceute_Proc_Lhs_Template_Error_function
                    // Procedure for inserting template data after template import done.
                    ProcLhsTemplateError procLhsTemplate = new ProcLhsTemplateError();
                    synchronized (procLhsTemplate) {
                        procLhsTemplate.execute_procedure(entity_code, client_code, acc_year,
                               quarter_no, from_date, to_date, tds_type_code,
                                getTemplateCode(), l_client_loginid_seq, user_code, getTokenNo(), null);
                    }
                    

                } 
                else if ("ProcessTdsTranLoadError".equals(getCallingProcName())) {

                    // Procedure for starting new error process from Validation & Error Process module.
                    ProcessTdsTranLoadError call_db_pr2 = new ProcessTdsTranLoadError();
                    synchronized (call_db_pr2) {
                        call_db_pr2.execute_procedure(getTokenNo().toString(), entity_code, client_code, acc_year, quarter_no, from_date, to_date,
                                tds_type_code, 0, 0, "", "R", 1, getClientLoginLevel(), getErrorType(), user_code);
                    }

                } else if ("ProcGenerateTdsTextFile".equals(getCallingProcName())) {
 
                    // Procedure for generating single FVU text file from error process section.
                    ProcGenerateTdsTextFile proc1 = new ProcGenerateTdsTextFile();
                    synchronized (proc1) {
                        proc1.execute_procedure(entity_code, client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year),
                                quarter_no, "", from_date, to_date, tds_type_code, "R", 0, l_client_loginid_seq, "", "", "TX", "",
                                user_code, getTokenNo().toString());
                    }

                } else if ("ProcGenerateTdsBulkTxtFile".equals(getCallingProcName())) {
                 utl.generateLog("calling ProcGenerateTdsBulkTxtFile","");
                 utl.generateLog("getProcessLevel() ",getProcessLevel());
                 utl.generateLog("getTokenNo()",getTokenNo());
                 utl.generateLog("Calling ProcMISReportDwnld","");
                    // Procedure for generating bulk FVU text file from error process section.
                    ProcGenerateTdsBulkTxtFile proc = new ProcGenerateTdsBulkTxtFile();
                    synchronized (proc) {
                        proc.execute_procedure(entity_code, client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year),
                                quarter_no, "", from_date, to_date, tds_type_code, "R", 0, l_client_loginid_seq, "", "", "TX", !utl.isnull(getFileHeaderType()) ? getFileHeaderType() : "",
                                getProcessLevel(), user_code, getTokenNo(), utl);
                    }

                } else if ("ProcTranLoadErrorDwnld".equals(getCallingProcName())) {

                    // Procedure for downloading bulk error records from error process section.
                    ProcTranLoadErrorDwnld pre = new ProcTranLoadErrorDwnld();
                    synchronized (pre) {
                        String a_download_type = "DA";
                        if ("4".equals(asmt.getQuarterNo()) && "24Q".equals(asmt.getTdsTypeCode())) {
                            a_download_type = "SALARY_TRAN_ERROR";
                        }

                        pre.executeProcedure(entity_code, client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year),
                                String.valueOf(quarter_no), from_date, to_date, tds_type_code, l_client_loginid_seq, getProcessLevel(), a_download_type, // Process type
                                "", user_code, getTokenNo());
                    }
                } else if ("ProcLhsTds15ghError".equals(getCallingProcName())) {

                    // Procedure for new error process in 15G/H error process section.
                    ProcLhsTds15ghError proc_15gh = new ProcLhsTds15ghError();
                    synchronized (proc_15gh) {
                        if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
                            if (!utl.isnull(getReprocessFlag()) && getReprocessFlag().equalsIgnoreCase("T")) {

                                proc_15gh.executeProcedure15GH(entity_code, client_code, acc_year, quarter_no, from_date, to_date,
                                        tds_type_code, getClientLoginLevel(), user_code, getTokenNo().toString());
                            }
                        }
                    }
                } else if ("ProcSalaryTranTotalRefresh".equals(getCallingProcName())) {
                    // Add condition only for G1 entity code(Sevaarth)
                    if (!utl.isnull(entity_code) && entity_code.equalsIgnoreCase("G1") && getValidate().equalsIgnoreCase("validate")) {
                        ProcSalaryTranDedRefresh procSalaryTranDedRefresh = new ProcSalaryTranDedRefresh();
                        synchronized (procSalaryTranDedRefresh) {
                            procSalaryTranDedRefresh.executeProcedure(entity_code, client_code, acc_year, quarter_no,
                                    from_date, to_date, tds_type_code, Long.valueOf(getRowIdSeq()), user_code, getTokenNo(),
                                    "", "BACKUP");
                        }
                        ProcSalaryMonthTdsCalc procSalaryMonthTdsCalc = new ProcSalaryMonthTdsCalc();
                        synchronized (procSalaryMonthTdsCalc) {
                            procSalaryMonthTdsCalc.executeProcedure(entity_code, client_code, acc_year, quarter_no,
                                    from_date, to_date, tds_type_code, Long.valueOf(getRowIdSeq()), user_code, getTokenNo(),
                                    "");
                        }

                    } else if (!utl.isnull(entity_code) && entity_code.equalsIgnoreCase("G1") && getValidate().equalsIgnoreCase("update")) {
                        ProcSalaryTranDedRefresh procSalaryTranDedRefresh = new ProcSalaryTranDedRefresh();
                        synchronized (procSalaryTranDedRefresh) {
                            procSalaryTranDedRefresh.executeProcedure(entity_code, client_code, acc_year, quarter_no,
                                    from_date, to_date, tds_type_code, Long.valueOf(getRowIdSeq()), user_code, getTokenNo(),
                                    "", "BACKUP");
                        }
                        ProcSalaryTranTotalRefresh totalRefreshProc = new ProcSalaryTranTotalRefresh();
                        synchronized (totalRefreshProc) {
                            totalRefreshProc.execureProcedure(entity_code, client_code,
                                    acc_year, quarter_no, from_date, to_date,
                                    tds_type_code, Long.valueOf(getRowIdSeq()), user_code, null);
                        }
                        ProcSalaryMonthTdsCalc procSalaryMonthTdsCalc = new ProcSalaryMonthTdsCalc();
                        synchronized (procSalaryMonthTdsCalc) {
                            procSalaryMonthTdsCalc.executeProcedure(entity_code, client_code, acc_year, quarter_no,
                                    from_date, to_date, tds_type_code, Long.valueOf(getRowIdSeq()), user_code, getTokenNo(),
                                    "");
                        }
                    } else {
                        // Procedure for salary tran total refresh.
                        ProcSalaryTranTotalRefresh totalRefreshProc = new ProcSalaryTranTotalRefresh();
                        synchronized (totalRefreshProc) {
                            totalRefreshProc.execureProcedure(entity_code, client_code,
                                    acc_year, quarter_no, from_date, to_date,
                                    tds_type_code, Long.valueOf(getRowIdSeq()), user_code, null);
                        }
                    }

                } else if ("ProcGen15ghBulkCsvFile".equals(getCallingProcName())) {

                    // Procedure for bulk csv file.
                    ProcGen15ghBulkCsvFile procGen15ghBulkCsvFile = new ProcGen15ghBulkCsvFile();
                    synchronized (procGen15ghBulkCsvFile) {
                        procGen15ghBulkCsvFile.executeProcedure(entity_code, client_code,
                                acc_year, asmt_acc_year, quarter_no, from_date, to_date,
                                tds_type_code, l_client_loginid_seq, user_code, getTokenNo());
                    }
                } else if ("ProcPanUnverifiedTxt".equals(getCallingProcName())) {

                    // Procedure for Pan Unverified Text.
                    ProcPanUnverifiedTxt panVer = new ProcPanUnverifiedTxt();
                    synchronized (panVer) {
                        panVer.execute_procedure(entity_code, client_code, acc_year, asmt_acc_year,
                                Integer.parseInt(asmt.getQuarterNo()), from_date, to_date, tds_type_code,
                                null, l_client_loginid_seq, "PAN_UNVERIFIED_DOWNLOAD", user_code,
                                getTokenNo(), null, getUnverifiedPanCount(), "");
                    }
                }else if ("GenerateErrorProcessReport".equals(getCallingProcName())){
                    utl.generateLog("Calling GenerateErrorProcessReport", "");
                    ProcGenErrorReport procDB = new ProcGenErrorReport();
                    synchronized (procDB) {
                    procDB.executeProcedure(entity_code, client_code, acc_year, asmt_acc_year, quarter_no,
                    from_date, to_date, tds_type_code, null, l_client_loginid_seq, 0, null, null, user_code, getTokenNo());
                  
                  
                    
                    }

                } 
                utl.generateLog("Calling " + getCallingProcName() + " procedure status", "completed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public String getCallingProcName() {
        return callingProcName;
    }

    public void setCallingProcName(String callingProcName) {
        this.callingProcName = callingProcName;
    }

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Integer getClientLoginLevel() {
        return clientLoginLevel;
    }

    public void setClientLoginLevel(Integer clientLoginLevel) {
        this.clientLoginLevel = clientLoginLevel;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorTypePro) {
        this.errorType = errorTypePro;
    }

    public Integer getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(Integer processLevel) {
        this.processLevel = processLevel;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public Long getRowIdSeq() {
        return rowIdSeq;
    }

    public void setRowIdSeq(Long rowIdSeq) {
        this.rowIdSeq = rowIdSeq;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFileHeaderType() {
        return fileHeaderType;
    }

    public void setFileHeaderType(String fileHeaderType) {
        this.fileHeaderType = fileHeaderType;
    }

    public Long getUnverifiedPanCount() {
        return unverifiedPanCount;
    }

    public void setUnverifiedPanCount(Long unverifiedPanCount) {
        this.unverifiedPanCount = unverifiedPanCount;
    }

}
