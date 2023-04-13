/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.uploadCSI;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.LhssysProcessLogDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import regular.generateFVU.GenerateFVUFileSupport;

/**
 *
 * @author ayushi.jain
 */
public class UploadCSIAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String validate;
    private File template;
    private String templateContentType;
    private String templateFileName;
    private String fileName;
    private String destPath;
    private String jsessionid;
    private String readOnlineFlag;
    private InputStream inputStream;
    private Long tokenNo;
    private String csiFileName;
    private String csiFilePath;
    public UploadCSIAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String return_message = "success";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client_mast = (ViewClientMast) session.get("WORKINGUSER");
        if (client_mast != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                ;
                try {
                    if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {

                        try {
                           String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                          
//                            destPath = externalDriveName + "/FVU_RELATED_FILES/" + client_code + "/" + l_acc_year + "/" + "Q" + quarterNo + "/" + "CSI" + "/";
                            destPath =  javaDriveName + File.separator + "CSI_FILES"
                                            + File.separator + client_mast.getClient_code()
                                            + File.separator + asmt.getAccYear()
                                            + File.separator + asmt.getTdsTypeCode();
                            System.out.println("destPath..." + destPath);
                        } catch (Exception e) {
                            //System.out.println("Exception On Creating Directory..." + e.getMessage());
                            destPath = "";
                        }


                        try {
                            File file = new File(destPath);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                            fVUFileSupport.deleteFilesInDirectory(destPath);
                            setCsiFileName(getTemplateFileName());
                            setCsiFilePath(destPath);
                            File destFile = new File(destPath, getTemplateFileName());
                            FileUtils.copyFile(getTemplate(), destFile);
                             DbFunctionExecutorAsString db= new DbFunctionExecutorAsString();
                                boolean flag = false;
                                String query = "UPDATE lhssys_process_log SET fvu_csi_filename = '"+destPath+File.separator+getTemplateFileName()+"' WHERE process_seqno="+getTokenNo();
                                System.out.println("queryquery->"+query);
                                flag = db.execute_oracle_update_function_as_string(query);
                            String CSIFileFlag="";    
                            File file2 = new File(destPath+File.separator+getTemplateFileName());
                            if(file2.exists()) {
                                System.out.println("uploaded CSI File present in system");
                                CSIFileFlag="T";
                            }else{
                                System.out.println("uploaded CSI File not present in system");
                                CSIFileFlag="F";
                            }    
                            setCsiFileName(getTemplateFileName());
                            setCsiFilePath(destPath);
                            session.put("ERRORCLASS", MessageType.successMessage);
                            addActionError("Upload File Successfully");
                            return_message = "success#"+getTemplateFileName()+"#"+destPath+File.separator+getTemplateFileName()+"#"+CSIFileFlag;
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                            session.put("ERRORCLASS", MessageType.errorMessage);
                            addActionError("Could Not Upload File");
                            return_message = "fail";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // flag to check internet connection 
                String readonlineflag = "";

                LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                LhssysParameters readOnlineFlag = lhssysParametersDAO.readDataAsParameterAndEntity("ONLINE_FLAG",client_mast.getEntity_code());
                if (readOnlineFlag != null) {
                    readonlineflag = readOnlineFlag.getParameter_value();
                    setReadOnlineFlag(readonlineflag);
                }
                //

            }
        }
        setJsessionid("ij123kih7896kirtwc5638Nsdsuybn3456");
        inputStream = new ByteArrayInputStream(return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getReadOnlineFlag() {
        return readOnlineFlag;
    }

    public void setReadOnlineFlag(String readOnlineFlag) {
        this.readOnlineFlag = readOnlineFlag;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getTemplateContentType() {
        return templateContentType;
    }

    public void setTemplateContentType(String templateContentType) {
        this.templateContentType = templateContentType;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getCsiFileName() {
        return csiFileName;
    }

    public void setCsiFileName(String csiFileName) {
        this.csiFileName = csiFileName;
    }

    public String getCsiFilePath() {
        return csiFilePath;
    }

    public void setCsiFilePath(String csiFilePath) {
        this.csiFilePath = csiFilePath;
    }
    
    
}//end class