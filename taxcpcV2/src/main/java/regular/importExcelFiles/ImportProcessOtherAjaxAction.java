/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhsTemplateError;
import dao.DbProc.ProcLhssysProcessLogIud;
import dao.LhssysTemplateDAO;
import dao.ViewLhssysTemplateErrorDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.SFTPProcessFile;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ImportProcessOtherAjaxAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String errorCode;
    private String importSeqNo;
    private String templateCode;
    private String processType;
    private Long tokenNo;

    public ImportProcessOtherAjaxAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnValue = "success";
        String returnMsg = "error";
        try {
            if (!utl.isnull(getAction())) {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
                UserMast user = (UserMast) session.get("LOGINUSER");//use for procedure
                String oracleDrive = (String) session.get("ORACLEDRIVE");
                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }

                if (getAction().equalsIgnoreCase("totalRecordImport")) {
                    Long total = 0l;
                    try {
                        LhssysTemplateDAO objtempdatadao = factory.getLhssysTemplateDAO();
                        total = objtempdatadao.getTempDataCount(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getImportSeqNo(), getTemplateCode());
                        utl.generateLog("getTemplateCode()----", getTemplateCode());
                    } catch (Exception e) {
                        total = 0l;
                        e.printStackTrace();
                    }
                    returnMsg = total.toString();

                } else if (getAction().equalsIgnoreCase("totalErrorImport")) {
                    int resultCount = 0;
                    ViewLhssysTemplateErrorDAO objerrordao = factory.getViewLhssysTemplateErrorDAO();
                    try {// used to count no of error
                        resultCount = objerrordao.getDestinctErrorRow(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getTemplateCode(), getImportSeqNo());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    returnMsg = (String.valueOf(resultCount));
                } else if (getAction().equalsIgnoreCase("ErrorFilter")) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        ViewLhssysTemplateErrorDAO objerrordao = factory.getViewLhssysTemplateErrorDAO();
                        List<Object[]> result = objerrordao.getErrorData(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getTemplateCode(), getImportSeqNo());
                        if (result != null && result.size() > 0) {
                            if (!utl.isnull(getErrorCode()) && getErrorCode().equals("AE")) {
                                sb.append("<option value=\"AE\" selected=\"true\">ALL Error</option>");
                            } else {
                                sb.append("<option value=\"AE\">ALL Error</option>");
                            }
                            for (Object[] objects : result) {
                                if (!utl.isnull(getErrorCode()) && getErrorCode().equals(objects[0].toString())) {
                                    sb.append("<option value=\"").append(objects[0].toString()).append("\" selected=\"true\">").append(objects[1].toString()).append("(").append(objects[2]).append(")" + "</option>");
                                } else {
                                    sb.append("<option value=\"").append(objects[0].toString()).append("\">").append(objects[1].toString()).append("(").append(objects[2]).append(")" + "</option>");
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                    returnMsg = sb.toString();
                } else if (getAction().equalsIgnoreCase("killProcess")) {System.out.println("killProcesskillProcess-->");
                    String result = "error";
                    try {
                        ProcLhssysProcessLogIud process = new ProcLhssysProcessLogIud();
                        String executeProcedure = process.executeProcedure(getTokenNo(), client.getEntity_code(), client.getClient_code(),
                                asmt.getAccYear(), asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(),
                                l_client_loginid_seq, null, null, null, null, null, null, null, null, null, null, "TEMPLATE_INSERT", user.getUser_code(),
                                "PROCESS_CANCEL", getTemplateCode(), getProcessType(), "", "","");
                        if (!utl.isnull(executeProcedure) && executeProcedure.equalsIgnoreCase("0")) {
                            result = "success";
                        }
                        returnMsg = result;
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                } else if (getAction().equalsIgnoreCase("refresh")) {
                    returnMsg = "error";
                    try {
                        GetTokenTransactions gb = new GetTokenTransactions();
                        TokenStatusAttribs tokenSatus = gb.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, user.getUser_code(), "TEMPLATE_INSERT", getTokenNo());
                        if (tokenSatus != null) {
                            returnMsg = new Gson().toJson(tokenSatus);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                } else if (getAction().equalsIgnoreCase("readStatus")) {
                    returnMsg = "Some Error Occurred ! Could Not fetch information";
                    try {
                        String clobLogFlag = (String) session.get("CLOB_LOG_FLAG") != null ? (String) session.get("CLOB_LOG_FLAG") : "";
                        utl.generateLog("CLOB_LOG_FLAG", clobLogFlag);
                        if(utl.isnull(clobLogFlag)){
                        try{   
                        String queryToFullcsiFileName = "select t.parameter_value from lhssys_parameters t where t.parameter_name LIKE 'CLOB_LOG_FLAG'";
                        clobLogFlag = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryToFullcsiFileName);
                        utl.generateLog("Clob value by using Query",clobLogFlag);     
                        }catch(Exception e){

                        }
                       }
        
                        if (!utl.isnull(getProcessType()) && !utl.isnull(String.valueOf(getTokenNo()))) {
                            System.out.println("clob para->"+clobLogFlag);
                            if (!utl.isnull(clobLogFlag) && clobLogFlag.trim().equalsIgnoreCase("T")) {
                                utl.generateLog("Get Log Method From CLOB", "IMPORT_PROCESS_LOG");
                                String logProcessType = utl.getLogProcess("IMPORT_PROCESS_LOG");
                                returnMsg = new DbFunctionExecutorAsString().get_clob_data(getTokenNo(), logProcessType);
                            } else {
                                utl.generateLog("Get Log Method From Oracle Directory", "");
                                String fileName = utl.getLogFileName(asmt, client.getClient_code(), client.getEntity_code(), "IMP", getTokenNo() + "", getTemplateCode());
                                String queryOfLogFileName = "select t.log_file_name1 from lhssys_process_log t where t.process_seqno =" + getTokenNo() + "";
                                String logFileName = new DbFunctionExecutorAsString().execute_oracle_function_as_string(queryOfLogFileName);

                                String filePath = oracleDrive + File.separator + "TEXT_FILES" + File.separator;
                                utl.generateLog("Log filename is", filePath + logFileName);
                                File logFile = new File(filePath + logFileName);
                                if (logFile.exists()) {
                                    try {
                                        returnMsg = utl.getLogFile(logFile);System.out.println("msg-"+returnMsg);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    returnMsg = "File Does Not Exists :" + fileName;
                                }
                            }
//                        
                        }
                    } catch (Exception e) {
                        returnMsg = "Some Error Occurred ! Could Not fetch information : " + e.getMessage();
                        e.printStackTrace();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream((returnMsg).getBytes("UTF-8"));
        return returnValue;

    }

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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(String importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end
}
