/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.DbProc.SetDatabaseValues;
import dao.LhssysFileTranDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.ReadFromConsolidatedFile;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.LhssysFileTran;
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
 * @author bhawna.agrawal
 */
public class GenerateFVUFileAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        String returnMsg = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
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
                    if (getAction().equalsIgnoreCase("generate")) {
//                        GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
//                        boolean textFileGenerated = fVUFileSupport.generateTextFile(client, acc_year, quarter_no, from_date, to_date, tds_type_code, l_client_loginid_seq);
//                        if (textFileGenerated) {
//                            returnMsg = "success";
//                        } else {
//                            returnMsg = "error";
//                        }
                    } else if (getAction().equalsIgnoreCase("textFileDetails")) {
                        try {
                            SetDatabaseValues databaseValues = new SetDatabaseValues();
                            databaseValues.setClientCode(client.getClient_code());
                            databaseValues.setEntityCode(client.getEntity_code());
                            databaseValues.setAccYear(acc_year);
                            databaseValues.setQuarterNo(quarter_no);
                            databaseValues.setTdsTypeCode(tds_type_code);

                            String sqlFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'TXT_FILE_GEN') from dual";
                            String functionResult = new DbFunctionExecutorAsString().execute_oracle_function_as_string(sqlFunction);
                            if (!utl.isnull(functionResult) && functionResult.equalsIgnoreCase("yes")) {
                                LhssysFileTran fileTran1 = new LhssysFileTran();
                                fileTran1.setClient_code(client.getClient_code());
                                fileTran1.setTds_type_code(tds_type_code);
                                fileTran1.setUpload_purpose("R");// regular
                                fileTran1.setImport_flag("TX");
                                fileTran1.setAcc_year(acc_year);
                                fileTran1.setFile_type("E");
                                fileTran1.setUpload_method(3l);
                                fileTran1.setQuarter_no(Double.parseDouble(quarterNo));
                                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                                LhssysFileTranDAO fileTranDAO = factory.getLhssysFileTranDAO();
                                LhssysFileTran defaultFVULocation = fileTranDAO.getDefaultFVULocation(fileTran1);
                                setTran(defaultFVULocation);
//                                String generateFileLoc = "Z:" + File.separator + "FVU_RELATED_FILES" + File.separator + client.getClient_code() + File.separator + asmt.getAccYear().getAcc_year() + File.separator + "Q" + quarterNo + File.separator + asmt.getForm().getTds_type_code() + File.separator + defaultFVULocation.getFile_name();//remove zero
                                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

                                String generateFileLoc = javaDriveName + File.separator + "TEXT_FILES" + File.separator + defaultFVULocation.getFile_name();
                                setHiddenFileLoc(generateFileLoc);

                                ReadFromConsolidatedFile consolidatedFile = new ReadFromConsolidatedFile();
                                Long lineCount = consolidatedFile.getLineCount(generateFileLoc, "DD");

                                setDeducteeCount(lineCount);
                            }
                            returnView = "page_success";

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (getAction().equalsIgnoreCase("changeAddress")) {
                        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                        ClientMastDAO clientDAO = factory.getClientMastDAO();
                        ClientMast readClientByClientCode = clientDAO.readClientByClientCode(client.getClient_code());
                        if (!utl.isnull(getDeductorAddress())) {
                            readClientByClientCode.setAdd_change(getDeductorAddress());
                        } else if (!utl.isnull(getResponsiblePersonAddress())) {
                            readClientByClientCode.setDeductor_add_change(getResponsiblePersonAddress());
                        }
                        clientDAO = factory.getClientMastDAO();
                        boolean update = clientDAO.update(readClientByClientCode);
                        if (update) {
                            returnMsg = "success";

                            ViewClientMast newWorkingUser = factory.getViewClientMastDAO().readClientByClientCode(readClientByClientCode.getClient_code());
                            session.put("WORKINGUSER", newWorkingUser);
                        } else {
                            returnMsg = "error";
                        }
                    } else if (getAction().equalsIgnoreCase("checkFvuPresent")) {
                        String fileSeqNoFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'FILE_SEQNO') from dual";
                        String fileSeqNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileSeqNoFunction);
                        if (!utl.isnull(fileSeqNo)) {
                            boolean fvuCreated = false;
                            returnMsg = "error";
                            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                            LhssysFileTranDAO lhssysFileTranDAO = factory.getLhssysFileTranDAO();
                            LhssysFileTran readDataByFileSeqno = lhssysFileTranDAO.readDataByFileSeqno(fileSeqNo);
                            if (readDataByFileSeqno != null) {
                                String fvu_file_name_str = readDataByFileSeqno.getFvu_file_name_str();
                                if (!utl.isnull(fvu_file_name_str)) {
                                    String[] split = fvu_file_name_str.split("#");
                                    for (String stringVal : split) {
                                        if (stringVal.endsWith(".fvu")
                                                || stringVal.endsWith(".pdf")
                                                || stringVal.endsWith("err.html")
                                                || stringVal.endsWith("_Electronic_Statement_Warning_File.html")
                                                || stringVal.endsWith(".fvu.zip")
                                                || stringVal.endsWith(".txt")) {
                                            fvuCreated = true;
                                            returnMsg = "success";
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (getAction().equalsIgnoreCase("responsiblePersonDetails")) {
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
//                                returnMsg = "success";
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
                    }
                }

            } else {
            }
        } else {
        }

        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnView;
    }

    public GenerateFVUFileAjaxAction() {
        utl = new Util();
    }
    private Util utl;
    private String action;
    private Map<String, Object> session;
    private LhssysFileTran tran;
    private String hiddenFileLoc;
    private String actionForFile;
    InputStream inputStream;
    private Long deducteeCount;
    private String fileSeqNo;
    private String deductorAddress;
    private String responsiblePersonAddress;
    private String deductorName;
    private String deductorPan;
    private String deductorDesig;
    private String deductorMobile;
    private Long prn_no;
    private String hiddenPrnCheck;
    private String hiddenAddCheck;

    public String getHiddenPrnCheck() {
        return hiddenPrnCheck;
    }

    public void setHiddenPrnCheck(String hiddenPrnCheck) {
        this.hiddenPrnCheck = hiddenPrnCheck;
    }

    public String getHiddenAddCheck() {
        return hiddenAddCheck;
    }

    public void setHiddenAddCheck(String hiddenAddCheck) {
        this.hiddenAddCheck = hiddenAddCheck;
    }

    public Long getPrn_no() {
        return prn_no;
    }

    public void setPrn_no(Long prn_no) {
        this.prn_no = prn_no;
    }

    public String getDeductorMobile() {
        return deductorMobile;
    }

    public void setDeductorMobile(String deductorMobile) {
        this.deductorMobile = deductorMobile;
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

    public String getDeductorAddress() {
        return deductorAddress;
    }

    public void setDeductorAddress(String deductorAddress) {
        this.deductorAddress = deductorAddress;
    }

    public String getResponsiblePersonAddress() {
        return responsiblePersonAddress;
    }

    public void setResponsiblePersonAddress(String responsiblePersonAddress) {
        this.responsiblePersonAddress = responsiblePersonAddress;
    }

    public String getFileSeqNo() {
        return fileSeqNo;
    }

    public void setFileSeqNo(String fileSeqNo) {
        this.fileSeqNo = fileSeqNo;
    }

    public Long getDeducteeCount() {
        return deducteeCount;
    }

    public void setDeducteeCount(Long deducteeCount) {
        this.deducteeCount = deducteeCount;
    }

    public String getActionForFile() {
        return actionForFile;
    }

    public void setActionForFile(String actionForFile) {
        this.actionForFile = actionForFile;
    }

    public String getHiddenFileLoc() {
        return hiddenFileLoc;
    }

    public void setHiddenFileLoc(String hiddenFileLoc) {
        this.hiddenFileLoc = hiddenFileLoc;
    }

    public LhssysFileTran getTran() {
        return tran;
    }

    public void setTran(LhssysFileTran tran) {
        this.tran = tran;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
