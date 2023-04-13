/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.Proc15GHErrorDwnld;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ProcessError15GHSupport extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private String sessionResult;
    private String action;
    private String tokenNo;
    private String l_error_process_type;
    private String reprocessFlag;
    private String validate;
    private long codeLevel;
    private int loginLevel;
    private InputStream inputStream;
    private String fileNameToDownload;

    public ProcessError15GHSupport() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        String return_msg = "";

        String result_value = (String) session.get("session_result");
        result_value = utl.isnull(result_value) ? "" : result_value;
        if (!utl.isnull(result_value)) {
            setSessionResult(result_value);
            session.remove("session_result");
        }

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        UserMast user_mast = (UserMast) session.get("LOGINUSER");
        Assessment assesment = (Assessment) session.get("ASSESSMENT");

        if (client != null && assesment != null) {

            String l_client_code = client.getClient_code();
            String l_entity_code = client.getEntity_code();

            String l_acc_year = assesment.getAccYear();
            String quarterNo = assesment.getQuarterNo();
            int l_quarter_no = Integer.parseInt(quarterNo);
            Date l_from_date = assesment.getQuarterFromDate();
            Date l_to_date = assesment.getQuarterToDate();
            String l_tds_type_code = assesment.getTdsTypeCode();

            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("getToken")) {
                    Long l_client_loginid_seq = null;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }

                    GetTokenTransactions token_obj = new GetTokenTransactions();
                    String token_no = token_obj.generateTokenGlobalMethod(assesment, client, user_mast, l_client_loginid_seq, getLoginLevel() + "", "",
                            "", "PROCESS_ERROR_15GH", "PROCESS_INSERT", "", "");

//                    if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {
//                        if (!utl.isnull(getReprocessFlag()) && getReprocessFlag().equalsIgnoreCase("T")) {
//                            int l_login_level = getLoginLevel();
//                            String l_user_code = user_mast.getUser_code();
//
//                            try {
//                                ProcLhsTds15ghError proc_15gh = new ProcLhsTds15ghError();
//                                String execute_procedure_result = proc_15gh.executeProcedure15GH(l_entity_code, l_client_code, l_acc_year, l_quarter_no, l_from_date, l_to_date,
//                                        l_tds_type_code, l_login_level, l_user_code, token_no);
//                                utl.generateLog("15GH process error procedure execute result: " + execute_procedure_result);
//                            } catch (ParseException ex) {
//                            }
//                        }
//                    }
                    return_msg = "success##" + token_no;
                    return_view = "token_result";
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                } else if (getAction().equalsIgnoreCase("refresh")) {
                    try {
                        GetTokenTransactions gb = new GetTokenTransactions();
                        TokenStatusAttribs tokenSatus = gb.getTokenSatus(client.getEntity_code(), client.getClient_code(), assesment, user_mast.getUser_code(), "PROCESS_ERROR_15GH", Long.parseLong(getTokenNo()));
                        if (tokenSatus != null) {
                            utl.generateLog("15GHPE Token status: ", tokenSatus);
                            return_msg = new Gson().toJson(tokenSatus);
                            return_view = "token_refreshed";
                            inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                        }
                    } catch (Exception e) {
                    }
                } else if (getAction().equalsIgnoreCase("xml_download")) {
                    try {
                        DbFunctionExecutorAsString executer = new DbFunctionExecutorAsString();
                        String xml_path_sql = "select t.xml_file_name from LHSSYS_PROCESS_LOG t WHERE T.PROCESS_SEQNO ='" + getTokenNo() + "'";
                        String xmlFilesDir = executer.execute_oracle_function_as_string(xml_path_sql);

                        utl.generateSpecialLog("Xml files directory sql: ", xml_path_sql);

                        if (!utl.isnull(xmlFilesDir)) {
                            SimpleDateFormat xml_filename_format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
                            String xml_file_timestamp = xml_filename_format.format(new Date());

                            String[] acc_year_string = l_acc_year.split("-");
                            String xmlZipFileName = l_client_code + "_" + acc_year_string[0] + "_" + acc_year_string[1] + "_" + l_tds_type_code + "_"
                                    + l_quarter_no + "_" + xml_file_timestamp;

                            File directory = new File(xmlFilesDir);

                            if (directory.exists()) {
                                ZipFileUtil zipFileUtil = new ZipFileUtil();
                                String[] files = directory.list();
                                byte[] zip = zipFileUtil.zipFiles(directory, files);

                                inputStream = new ByteArrayInputStream(zip);
                                fileNameToDownload = xmlZipFileName + ".zip";

                                return_view = "success";
                            } else {
                                return_view = "input";
                                session.put("SESSIONRESULT", "Directory Not Found");
                            }
                        } else {
                            return_view = "input";
                            session.put("SESSIONRESULT", "Directory Not Found");
                        }
                    } catch (Exception e) {
                        return_view = "input";
                        session.put("SESSIONRESULT", "Some error occurred, could not download xml file.");
                        e.printStackTrace();
                    }
                } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("downloadError")) {
                    Long l_client_loginid_seq = null;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }
                    String assesment_acc_year = utl.ChangeAccYearToAssessmentAccYear(assesment.getAccYear());
                    GetTokenTransactions generateToken = new GetTokenTransactions();
                    String token = generateToken.generateTokenGlobalMethod(assesment, client, user_mast, l_client_loginid_seq, user_mast.getUser_level() + "",
                            "DA", "Process Initiated by user !", "15GH_ERROR_DOWNLOAD", "PROCESS_INSERT", "", "");

                    if (!utl.isnull(token)) {

                        Proc15GHErrorDwnld pre = new Proc15GHErrorDwnld();

                        String proc_err_dwnld_status = pre.executeProcedure(client.getEntity_code(), client.getClient_code(), assesment.getAccYear(), assesment_acc_year,
                                assesment.getQuarterNo(), assesment.getQuarterFromDate(), assesment.getQuarterToDate(), assesment.getTdsTypeCode(),
                                l_client_loginid_seq, getCodeLevel(),
                                "DA", // Process type
                                "", user_mast.getUser_code(), Long.parseLong(token));

                        if (!utl.isnull(proc_err_dwnld_status) && proc_err_dwnld_status.equalsIgnoreCase("0")) {
                            return_msg = "success#" + token;
                        } else {
                            return_msg = "error";
                        }
                    }
                    return_view = "token_refreshed";
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                }
            }
        }
        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getL_error_process_type() {
        return l_error_process_type;
    }

    public void setL_error_process_type(String l_error_process_type) {
        this.l_error_process_type = l_error_process_type;
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

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public int getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(int loginLevel) {
        this.loginLevel = loginLevel;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public void setFileNameToDownload(String fileNameToDownload) {
        this.fileNameToDownload = fileNameToDownload;
    }

    public long getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(long codeLevel) {
        this.codeLevel = codeLevel;
    }

}
