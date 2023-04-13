/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.ViewTdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.ViewClientMast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class GenerateFVUAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        session.put("ACTIVE_TAB", "errorStatus");
        String returnView = "success";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast viewClientMast = null;
            try {
                viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            } catch (Exception e) {
            }
            if (!utl.isnull(getSessionResult())) {
                addActionError(getSessionResult());
                session.put("ERRORCLASS", MessageType.errorMessage);
            }
            // END

            String result_value = (String) session.get("SESSIONRESULT");
            result_value = utl.isnull(result_value) ? "" : result_value;
            if (!utl.isnull(result_value)) {
                setSessionResult(result_value);
                session.remove("SESSIONRESULT");
            }

            GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
            if (viewClientMast != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {

                    int quarter_no = 0;
                    String acc_year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();

                    quarter_no = Integer.parseInt(quarterNo);

                    // calculate challan records : START
                    String tds_type_code = asmt.getTdsTypeCode();
                    ViewTdsChallanTranLoadDAO chalantran = factory.getViewTdsChallanTranLoadDAO();
                    Long tdsChallanTranCount = chalantran.getTdsChallanTranCount(viewClientMast.getClient_code(), acc_year, quarterNo, tds_type_code, null, "false", utl);
                    if (tdsChallanTranCount > 0l) {// if challans found
                        setTotal(tdsChallanTranCount);

                        // setting client mast object for responsible person details
                        ClientMastDAO clientDAO = factory.getClientMastDAO();
                        ClientMast readClientByClientCode = clientDAO.readClientByClientCode(viewClientMast.getClient_code());

                        setClientMast(readClientByClientCode);
                        // END
                        // previous qtr details
                        int previousQtr = 0;
                        String previousAccYear = "";
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
                        utl.generateLog("previousQtr--", previousQtr);
                        utl.generateLog("previousAccYear--", previousAccYear);
                        Date previousFrom = fVUFileSupport.getPreviousQuarterDates(previousAccYear, previousQtr, "from");
                        Date previousTo = fVUFileSupport.getPreviousQuarterDates(previousAccYear, previousQtr, "to");

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String tokenNoQuery = "select lhs_tds.get_tds_reco_details('" + viewClientMast.getEntity_code() + "', '" + viewClientMast.getClient_code() + "', '" + previousAccYear + "', '" + previousQtr + "',  to_date('" + sdf.format(previousFrom) + "','dd-mm-rrrr'),  to_date('" + sdf.format(previousTo) + "','dd-mm-rrrr'), '" + asmt.getTdsTypeCode() + "', '', 'LHSSYS_FILE_TRAN', 'TOKEN_NO') from dual";
                        utl.generateSpecialLog("tokenNoQuery--", tokenNoQuery);
                        String prnNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(tokenNoQuery);
                        if (!utl.isnull(prnNo)) {
                            setPreviousQtrPrno(prnNo);
                        }
                        //END
                    } else {
                        returnView = "error";
                        setSessionResult("No challans found");
                        session.put("ERRORCLASS", MessageType.errorMessage);
                        session.put("SESSIONRESULT", "No challans found");
                    }
                } else {
                    returnView = "setassessment";
                    addActionError("Some Error Occured, Please Select Default Acc Year And Quarter Type");
                    session.put("ERRORCLASS", MessageType.errorMessage);
                }
            } else {
                returnView = "getlogin";
                addActionError("Some Error Occured, Please Login Again");
                session.put("ERRORCLASS", MessageType.errorMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Some Error Occured, Could Not Generate FVU Text File");
            session.put("ERRORCLASS", MessageType.errorMessage);
        }

        return returnView;
    }//END METHOD
    private Map<String, Object> session;
    private String sessionResult;
    private String jsessionid;
    private long total;
    private ClientMast clientMast;// object to set details of responsible person
    private String previousQtrPrno;
    private String dwnldFileLoc;// file loc to dwnld files
    private String allowClick;// flag to one time click generate fvu btn
    private boolean validateFlag;
    private String allowCsiUpload;// flag to allow/ not allow upload of csi file when readonline flag is true--08-12-16
    private String fileTypeFound;
    private String tokenNo;
    Util utl;
    private String generatedFileLink; //
    private String generatedFileName;//
    private String genFileName;// file absolute path of FVU or error zip files

    private String validate;
    private String upload_purpose;
    private String l_error_process_type;
    private String processCnfChkBxID;
    private String reprocessFlag;
    private String errorTypeProc;
    private String loginLevelFlag;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public GenerateFVUAction() {
        utl = new Util();
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public ClientMast getClientMast() {
        return clientMast;
    }

    public void setClientMast(ClientMast clientMast) {
        this.clientMast = clientMast;
    }

    public String getPreviousQtrPrno() {
        return previousQtrPrno;
    }

    public void setPreviousQtrPrno(String previousQtrPrno) {
        this.previousQtrPrno = previousQtrPrno;
    }

    public String getDwnldFileLoc() {
        return dwnldFileLoc;
    }

    public void setDwnldFileLoc(String dwnldFileLoc) {
        this.dwnldFileLoc = dwnldFileLoc;
    }

    public String getAllowClick() {
        return allowClick;
    }

    public void setAllowClick(String allowClick) {
        this.allowClick = allowClick;
    }

    public boolean isValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean validateFlag) {
        this.validateFlag = validateFlag;
    }

    public String getAllowCsiUpload() {
        return allowCsiUpload;
    }

    public void setAllowCsiUpload(String allowCsiUpload) {
        this.allowCsiUpload = allowCsiUpload;
    }

    public String getFileTypeFound() {
        return fileTypeFound;
    }

    public void setFileTypeFound(String fileTypeFound) {
        this.fileTypeFound = fileTypeFound;
    }

    public String getGeneratedFileLink() {
        return generatedFileLink;
    }

    public void setGeneratedFileLink(String generatedFileLink) {
        this.generatedFileLink = generatedFileLink;
    }

    public String getGeneratedFileName() {
        return generatedFileName;
    }

    public void setGeneratedFileName(String generatedFileName) {
        this.generatedFileName = generatedFileName;
    }

    public String getGenFileName() {
        return genFileName;
    }

    public void setGenFileName(String genFileName) {
        this.genFileName = genFileName;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public String getL_error_process_type() {
        return l_error_process_type;
    }

    public void setL_error_process_type(String l_error_process_type) {
        this.l_error_process_type = l_error_process_type;
    }

    public String getProcessCnfChkBxID() {
        return processCnfChkBxID;
    }

    public void setProcessCnfChkBxID(String processCnfChkBxID) {
        this.processCnfChkBxID = processCnfChkBxID;
    }

    public String getReprocessFlag() {
        return reprocessFlag;
    }

    public void setReprocessFlag(String reprocessFlag) {
        this.reprocessFlag = reprocessFlag;
    }

    public String getErrorTypeProc() {
        return errorTypeProc;
    }

    public void setErrorTypeProc(String errorTypeProc) {
        this.errorTypeProc = errorTypeProc;
    }

    public String getLoginLevelFlag() {
        return loginLevelFlag;
    }

    public void setLoginLevelFlag(String loginLevelFlag) {
        this.loginLevelFlag = loginLevelFlag;
    }

}
