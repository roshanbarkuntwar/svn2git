/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.lhs.taxcpcv2.bean.MessageType;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhssysProcessLogIud;
import dao.LhssysParametersDAO;
import dao.LhssysProcessLogDAO;
import dao.ViewDeducteeTemplateDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetGlobalList;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class ImportExcelAction extends ActionSupport implements SessionAware {

    public ImportExcelAction() {
        utl = new Util();
        templateTypes = new LinkedHashMap<>();
        processTypes = new LinkedHashMap<>();
       // processTypes.put("A", "Appendddd");
    }

    @Override
    public String execute() {
        String returnView = "input";

        utl.generateLog("import process start", "");

        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
        Assessment assesment = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");
        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
        l_client_loginid_seq = (Long) sessionId;
        try {
            String sessionTdsLink = (String) session.get("SESSIONDWNLINK");
            sessionTdsLink = utl.isnull(sessionTdsLink) ? "" : sessionTdsLink;
            if (!utl.isnull(sessionTdsLink)) {
                setSessionDwnLink(sessionTdsLink);
                session.remove("SESSIONDWNLINK");
            }
        } catch (Exception e) {
        }

        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
        String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
        String clobImportFlag = (String) session.get("CLOB_IMPORT_FLAG") != null ? (String) session.get("CLOB_IMPORT_FLAG") : "";
        utl.generateLog("CLOB_IMPORT_FLAG", clobImportFlag);

        excelFormat = (String) session.get("EXCELFORMAT") != null ? (String) session.get("EXCELFORMAT") : "";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            session.put("ACTIVE_TAB", "tdsImport");
            try {
                String resultValue = (String) session.get("SESSIONRESULT");
                resultValue = utl.isnull(resultValue) ? "" : resultValue;
                if (!utl.isnull(resultValue)) {
                    setSessionResult(resultValue);
                    session.remove("SESSIONRESULT");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Long resultValue = (Long) session.get("PROCESSSEQNO");
                resultValue = resultValue == null ? 0l : resultValue;
                setSeqNo(resultValue.toString());
                session.remove("PROCESSSEQNO");

            } catch (Exception e) {
                e.printStackTrace();
            }

            processTypes = new GetGlobalList().getTemplateFlagList();
            System.out.println("processTypes list size - "+processTypes.size());
           
            if (!utl.isnull(action) && action.equals("upload")) {

                utl.generateLog("File upload controller start", "");
                int uploadCount = 0;
                try {
                    uploadCount = (Integer) session.get("SESSIONUPLOADCOUNT");
                } catch (Exception e) {
                    uploadCount = 1;
                }
                if (uploadCount < 5) {
                    uploadCount = uploadCount + 1;
                    session.put("SESSIONUPLOADCOUNT", uploadCount);
                    String[] templateTypeFlag;
                    try {
                        templateTypeFlag = getTemplateType().split("#");
                    } catch (Exception e) {
                        templateTypeFlag = null;
                    }

                    String templeteCode = templateTypeFlag[0];
                    utl.generateLog("templeteCode :", templeteCode);

                    ViewDeducteeTemplateDAO viewDeducteeTemplateDAO = factory.getViewDeducteeTemplateDAO();
                    ViewClientTemplate viewClientTemplate = viewDeducteeTemplateDAO.getDataAsTempleteCode(templeteCode);
                    String engineType = "";
                    if (viewClientTemplate != null) {
                        engineType = viewClientTemplate.getEngine_type();
                        utl.generateLog("Engine type:", engineType);
                    }
                    ImportExcelSupport support = new ImportExcelSupport();
                    utl.generateLog("file copying start...", "");
                    String savedFilePath = support.RenameAndSaveUploadedFile(engineType, getUploadFileObj(), viewClientMast, assesment, javaDriveName, oracleDriveName);
                    utl.generateLog("saved File Path:", savedFilePath);
                    //
                    
                    if(!utl.isnull(savedFilePath)){
                       
                    }
                    
                    if (!utl.isnull(savedFilePath) && templateTypeFlag != null) {
                        ProcLhssysProcessLogIud proc = new ProcLhssysProcessLogIud();
                        String procResult = proc.executeProcedure(null, viewClientMast.getEntity_code(), viewClientMast.getClient_code(), assesment.getAccYear(), assesment.getQuarterNo(), assesment.getQuarterFromDate(), assesment.getQuarterToDate(), assesment.getTdsTypeCode(), l_client_loginid_seq, null, null, null, null, null, null, null, null, null, null, "TEMPLATE_INSERT", user.getUser_code(), "PROCESS_INSERT", templeteCode, getProcessType(), "", "",getModuleType());
                        if (!utl.isnull(procResult) && !procResult.equalsIgnoreCase("-1")) {
                            LhssysParametersDAO lhssysParameters = factory.getLhssysParametersDAO();

                            GetTokenTransactions gt = new GetTokenTransactions();
                            TokenStatusAttribs tokenSatus = gt.getTokenSatus(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), assesment, user.getUser_code(), "TEMPLATE_INSERT", l_client_loginid_seq);

                            setProcessSeqNo(Long.parseLong(tokenSatus.getTokenNo()));

                            int savedResult = new ProcessUploadFile().saveImportMastUploadedTemplate(engineType, getProcessType(), viewClientMast, assesment, savedFilePath, templeteCode,
                                    javaDriveName, oracleDriveName, Long.parseLong(tokenSatus.getTokenNo()), user.getUser_code(), Long.parseLong(tokenSatus.getTokenNo()), getProcessSeqNo(), lhssysParameters,clobImportFlag);

                            if (savedResult > 0) {
                                
                                DbFunctionExecutorAsString db= new DbFunctionExecutorAsString();
                                boolean flag = false;
                                String query = "UPDATE lhssys_process_log SET import_filename = '"+savedFilePath+"' WHERE process_seqno="+getProcessSeqNo();
                                System.out.println("queryquery->"+query);
                                flag = db.execute_oracle_update_function_as_string(query);
                               
                                session.put("importTempleteCode", templeteCode);
                                session.put("importProcessType", getProcessType());

                                session.put("PROCESSSEQNO", Long.parseLong(tokenSatus.getTokenNo()));
                                session.put("ERRORCLASS", MessageType.successMessage);
                                session.put("SESSIONRESULT", "File Uploaded SuccessFully ! Your token no. is generated check import status !#" + tokenSatus.getTokenNo());
                                returnView = "success";
                            } else {
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                session.put("SESSIONRESULT", "Some error occured, could not save");
                                returnView = "errorfileresult";
                            }
                        } else {
                            session.put("ERRORCLASS", MessageType.errorMessage);
                            session.put("SESSIONRESULT", "Some error occured, could not save");
                            returnView = "errorfileresult";
                        }
                    }
                } else {
                    try {
                        session.remove("SESSIONUPLOADCOUNT");
                    } catch (Exception e) {
                    }
                    setMxUpdRes("TBC");
                    returnView = "uploadMaxResult";
                }
            } else {//set view page
//                setExcelFormat(excelFormat);
                ViewDeducteeTemplateDAO viewDeducteeTemplateDAO = factory.getViewDeducteeTemplateDAO();
                if (!utl.isnull(assesment.getTdsTypeCode())) {System.out.println("queryyy--");
                    String sessionModule = (String) session.get("MODULE");
                    sessionModule = utl.isnull(sessionModule) ? "R" : sessionModule;
                    List<ViewClientTemplate> templateList = viewDeducteeTemplateDAO.getAllTemplatesAsKeyCode(viewClientMast, assesment.getTdsTypeCode(), sessionModule);
                    if (templateList != null) {
                        templateList.forEach(template -> {
                            templateTypes.put(template.getTemplate_code() + "#" + template.getTepmlate_flag(), template.getTemplate_name());
                        });
                    }
                } else {
                    templateTypes.put("", "---Select---");
                }
            }
        } catch (IOException | NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
        return returnView;
    }//End Method

    private Map<String, Object> session;
    private final Util utl;
    private Map<String, String> templateTypes;
    private Map<String, String> processTypes;

    private String templateType;
    private String processType;
    private ImportFileAttribs uploadFileObj;
    private String excelFormat;
    private String action;
    private String mxUpdRes;
    private String sessionResult;
    private String sessionDwnLink;
    private Long processSeqNo;
    private String seqNo;
    private String moduleType;

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, String> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(Map<String, String> templateTypes) {
        this.templateTypes = templateTypes;
    }

    public Map<String, String> getProcessTypes() {
        return processTypes;
    }

    public void setProcessTypes(Map<String, String> processTypes) {
        this.processTypes = processTypes;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public ImportFileAttribs getUploadFileObj() {
        return uploadFileObj;
    }

    public void setUploadFileObj(ImportFileAttribs uploadFileObj) {
        this.uploadFileObj = uploadFileObj;
    }

    public String getExcelFormat() {
        return excelFormat;
    }

    public void setExcelFormat(String excelFormat) {
        this.excelFormat = excelFormat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMxUpdRes() {
        return mxUpdRes;
    }

    public void setMxUpdRes(String mxUpdRes) {
        this.mxUpdRes = mxUpdRes;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getSessionDwnLink() {
        return sessionDwnLink;
    }

    public void setSessionDwnLink(String sessionDwnLink) {
        this.sessionDwnLink = sessionDwnLink;
    }

    public Long getProcessSeqNo() {
        return processSeqNo;
    }

    public void setProcessSeqNo(Long processSeqNo) {
        this.processSeqNo = processSeqNo;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }
    
    

    // Get Templates Type 
    public Map<String, String> getTemplatesType(Assessment assesment, DAOFactory factory, ViewClientMast viewClientMast, String module) {
        try {
            ViewDeducteeTemplateDAO viewDeducteeTemplateDAO = factory.getViewDeducteeTemplateDAO();
            if (!utl.isnull(assesment.getTdsTypeCode())) {
                module = utl.isnull(module) ? "R" : module;
                List<ViewClientTemplate> templateList = viewDeducteeTemplateDAO.getAllTemplatesAsKeyCode(viewClientMast, assesment.getTdsTypeCode(), module);
                if (templateList != null) {
                    templateList.forEach(template -> {
                        templateTypes.put(template.getTemplate_code(), template.getTemplate_name());
                    });
                }
            } else {
                templateTypes.put("", "---Select---");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return templateTypes;
    }

}//End Class
