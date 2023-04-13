/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.LhssysFileTranDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class GenerateFVUProcedureAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    InputStream inputStream;
    private String tokenNo;
    private String action;
    private String deductorName;
    private String deductorPan;
    private String deductorDesig;
    private long prn_no;
    private String hiddenAddCheck;
    private String deductorMobile;
    private String hiddenPrnCheck;
    private String sessionResult;
    private TokenStatusAttribs tokenUploadObj;

    public GenerateFVUProcedureAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        String returnMsg = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user_mast = (UserMast) session.get("LOGINUSER");
            if (asmt != null) {
                int quarter_no = 0;
                String acc_year = asmt.getAccYear();
                String quarterNo = asmt.getQuarterNo();
                quarter_no = Integer.parseInt(quarterNo);
                Date from_date = asmt.getQuarterFromDate();
                Date to_date = asmt.getQuarterToDate();
                String tds_type_code = asmt.getTdsTypeCode();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");

                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (NumberFormatException e) {
                    l_client_loginid_seq = 0l;
                }

                if (!utl.isnull(getAction())) {
                    if (getAction().equalsIgnoreCase("responsiblePersonDetails")) {
                        try {
                            returnMsg = "success";
                            String returnMessageText = "";
                            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

                            ClientMastDAO clientDAO = factory.getClientMastDAO();
                            ClientMast readClientByClientCode = clientDAO.readClientByClientCode(client.getClient_code());

                            utl.generateLog("getDeductorName--", getDeductorName());
                            utl.generateLog("getDeductorPan--", getDeductorPan());
                            utl.generateLog("getDeductorDesig--", getDeductorDesig());
                            utl.generateLog("getPrn_no--", getPrn_no());

                            if ((utl.isnull(getHiddenAddCheck()) || getHiddenAddCheck().equalsIgnoreCase("F")) && !utl.isnull(getDeductorPan())) {
                                readClientByClientCode.setDeductor_name(getDeductorName());
                                readClientByClientCode.setDeductor_panno(getDeductorPan());
                                readClientByClientCode.setDeductor_desig(getDeductorDesig());
                                readClientByClientCode.setDeductor_mobileno(getDeductorMobile());
                                readClientByClientCode.setAdd_change("Y");
                                readClientByClientCode.setAdd_change_on(new Date());
                            } else {
                                readClientByClientCode.setAdd_change("N");
                            }
                            clientDAO = factory.getClientMastDAO();
                            boolean update = clientDAO.update(readClientByClientCode);
                            utl.generateLog("update--", update);
                            if (update) {
                                ViewClientMastDAO viewClientMastDAO = factory.getViewClientMastDAO();
                                ViewClientMast clientCodeAfterUpdate = viewClientMastDAO.readClientByClientCode(readClientByClientCode.getClient_code());
                                session.put("WORKINGUSER", clientCodeAfterUpdate);
                            } else {
                                returnMsg = "fail";
                                returnMessageText = "Some Error Occured, Could Not Update Responsible person Details";
                            }
                            ////////////////////////
                            if (!utl.isnull(getHiddenPrnCheck()) && getHiddenPrnCheck().equalsIgnoreCase("T")) {

                                int previousQtr = 0;
                                String previousAccYear = null;
                                if (quarter_no <= 1) {
                                    previousQtr = 4;
                                    String[] split = asmt.getAccYear().split("\\-");
                                    int split1 = Integer.parseInt(split[0]);
                                    split1 = split1 - 1;
                                    int split2 = Integer.parseInt(split[1]);
                                    split2 = split2 - 1;

                                    if (split1 < 10) {
                                        previousAccYear += "0" + split1;
                                    } else {
                                        previousAccYear += split1;
                                    }
                                    previousAccYear += "-";

                                    if (split2 < 10) {
                                        previousAccYear += "0" + split2;
                                    } else {
                                        previousAccYear += split2;
                                    }
                                } else {
                                    previousQtr = quarter_no - 1;
                                    previousAccYear = asmt.getAccYear();
                                }
                                GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                                utl.generateLog("previousQtr--", previousQtr);
                                utl.generateLog("previousAccYear--", previousAccYear);
                                Date previousFrom = fVUFileSupport.getPreviousQuarterDates(previousAccYear, previousQtr, "from");
                                Date previousTo = fVUFileSupport.getPreviousQuarterDates(previousAccYear, previousQtr, "to");
                                String fileSeqNoFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + previousAccYear + "', '" + previousQtr + "',  to_date('" + sdf.format(previousFrom) + "','dd-mm-rrrr'),  to_date('" + sdf.format(previousTo) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'FILE_SEQNO') from dual";
                                String fileSeqNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileSeqNoFunction);
                                utl.generateLog("fileSeqNoFunction--", fileSeqNoFunction);
                                utl.generateLog("fileSeqNo--", fileSeqNo);
                                LhssysFileTranDAO lhssysFileTranDAO = factory.getLhssysFileTranDAO();
                                if (!utl.isnull(fileSeqNo)) {
                                    // update
                                    LhssysFileTran readDataByFileSeqno = lhssysFileTranDAO.readDataByFileSeqno(fileSeqNo);
                                    readDataByFileSeqno.setRef_no(getPrn_no());
                                    readDataByFileSeqno.setFvu_file_status("C");
                                    // setting - if null as token no cannot be fetched if fvu_file_name_str is null
                                    if (utl.isnull(readDataByFileSeqno.getFvu_file_name_str())) {
                                        readDataByFileSeqno.setFvu_file_name_str("-");
                                    }
                                    lhssysFileTranDAO = factory.getLhssysFileTranDAO();
                                    boolean updateTran = lhssysFileTranDAO.update(readDataByFileSeqno);
                                    if (!updateTran) {
                                        returnMsg = "fail";
                                        if (!utl.isnull(returnMessageText)) {
                                            returnMessageText = "Some Error Occured, Could Not Update These Details";
                                        } else {
                                            returnMessageText = "Some Error Occured, Could Not Update PRN No";
                                        }
                                    }
                                } else {
                                    // insert
                                    LhssysFileTran fileTran = new LhssysFileTran();
                                    fileTran.setClient_code(client.getClient_code());
                                    fileTran.setClient_login_session_seqno(9999l);
                                    fileTran.setAcc_year(previousAccYear);
                                    fileTran.setQuarter_no(Double.parseDouble(quarterNo));
                                    fileTran.setFrom_date(previousFrom);
                                    fileTran.setTo_date(previousTo);
                                    fileTran.setRef_no(getPrn_no());
                                    fileTran.setImport_flag("TX");
                                    fileTran.setFile_type("E");
                                    fileTran.setUpload_method(3l);
                                    fileTran.setUpload_purpose("R");
                                    fileTran.setFvu_file_status("C");
                                    // setting - if null as token no cannot be fetched if fvu_file_name_str is null
                                    fileTran.setFvu_file_name_str("-");
                                    fileTran.setRef_no(getPrn_no());
                                    lhssysFileTranDAO = factory.getLhssysFileTranDAO();
                                    boolean save = lhssysFileTranDAO.save(fileTran);
                                    if (!save) {
                                        returnMsg = "fail";
                                        if (!utl.isnull(returnMessageText)) {
                                            returnMessageText = "Some Error Occured, Could Not Update These DEtails";
                                        } else {
                                            returnMessageText = "Some Error Occured, Could Not Update PRN no";
                                        }
                                    }

                                }
                            }
                            if (returnMsg.equalsIgnoreCase("fail")) {
                                returnMsg = returnMessageText;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        returnView = "ajax_success";
                    } else if (getAction().equalsIgnoreCase("callFVUTextProcedure")) {
                        returnMsg = "success";
                        try {
                            int result = this.generateFVUTextFile(client.getEntity_code(), client.getClient_code(), acc_year, quarter_no, from_date, to_date, tds_type_code, user_mast.getUser_code(), getTokenNo(), l_client_loginid_seq);
                        } catch (Exception e) {
                            returnMsg = "error";
                            e.printStackTrace();
                        }
                        returnView = "ajax_success";
//                        if (proc_result) {
//                            session.put("SESSIONRESULT", "Generate Text File Successfully Completed");
//                            session.put("ERRORCLASS", MessageType.successMessage);
//                            utl.generateLog("", "FVU text file generated");

//                        } else {
//                            addActionError("Some Error Occured, Could Not Generate Text File");
//                            session.put("ERRORCLASS", MessageType.errorMessage);
//                            utl.generateLog("", "Could not generate FVU file");
//                            returnView = "input";
//                        }
                    }
                }
            }
        }
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnView;
    }

    public int generateFVUTextFile(String entity_code, String client_code, String acc_year, int l_quarter_no, Date dateFromDate, Date dateToDate,
            String tds_type, String user_code, String process_seq, long l_client_loginid_seq) {

//        Thread task = new Thread() {
//            @Override
//            public void run() {
//        ProcGenerateTdsTextFile proc1 = new ProcGenerateTdsTextFile();
//        proc1.execute_procedure(
//                entity_code,
//                client_code,
//                acc_year,
//                utl.ChangeAccYearToAssessmentAccYear(acc_year),
//                l_quarter_no, "",
//                dateFromDate,
//                dateToDate,
//                tds_type, "R", 0,
//                l_client_loginid_seq,
//                "", "", "TX", "",
//                user_code,
//                process_seq);
//            }
//        };
//        task.start();
        return 1;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public long getPrn_no() {
        return prn_no;
    }

    public void setPrn_no(long prn_no) {
        this.prn_no = prn_no;
    }

    public String getHiddenPrnCheck() {
        return hiddenPrnCheck;
    }

    public void setHiddenPrnCheck(String hiddenPrnCheck) {
        this.hiddenPrnCheck = hiddenPrnCheck;
    }

    public String getDeductorName() {
        return deductorName;
    }

    public void setDeductorName(String deductorName) {
        this.deductorName = deductorName;
    }

    public String getDeductorPan() {
        return deductorPan;
    }

    public void setDeductorPan(String deductorPan) {
        this.deductorPan = deductorPan;
    }

    public String getDeductorDesig() {
        return deductorDesig;
    }

    public void setDeductorDesig(String deductorDesig) {
        this.deductorDesig = deductorDesig;
    }

    public String getHiddenAddCheck() {
        return hiddenAddCheck;
    }

    public void setHiddenAddCheck(String hiddenAddCheck) {
        this.hiddenAddCheck = hiddenAddCheck;
    }

    public String getDeductorMobile() {
        return deductorMobile;
    }

    public void setDeductorMobile(String deductorMobile) {
        this.deductorMobile = deductorMobile;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
