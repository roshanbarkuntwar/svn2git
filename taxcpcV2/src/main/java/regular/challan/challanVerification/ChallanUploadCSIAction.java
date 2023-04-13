/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanVerification;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author user
 */
public class ChallanUploadCSIAction extends ActionSupport implements SessionAware {

    public ChallanUploadCSIAction() {
        utl = new Util();
    }

    Map<String, Object> session;
    Util utl;
    private String validate;
    private File template;
    private String templateContentType;
    private String templateFileName;
    private String fileName;
    private String destPath;
    private String readOnlineFlag;
    private InputStream inputStream;

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String return_message = "success";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client_mast = (ViewClientMast) session.get("WORKINGUSER");
        if (client_mast != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                String client_code = client_mast.getClient_code();
                String l_acc_year = asmt.getAccYear();
                String quarterNo = asmt.getQuarterNo();
                String tds_type_code = asmt.getTdsTypeCode();
                try {
                    if (!utl.isnull(getValidate()) && getValidate().equalsIgnoreCase("true")) {

                        try {
                            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

//                            destPath = javaDriveName + File.separator + "CHALLAN_VERIFICATION_CSI" + File.separator + client_code + File.separator + l_acc_year + File.separator + "Q" + quarterNo + File.separator + "CSI" + File.separator;
                            destPath = javaDriveName + File.separator + "CSI_FILES"
                                    + File.separator + client_mast.getClient_code()
                                    + File.separator + asmt.getAccYear()
                                    + File.separator + asmt.getTdsTypeCode();
                            utl.generateLog("javaDriveName--", destPath);
                        } catch (Exception e) {
                            //System.out.println("Exception On Creating Directory..." + e.getMessage());
                            destPath = "";
                        }
//                System.out.println("destPath..." + destPath);
//                System.out.println("file name..." + getTemplateFileName());

                        try {
                            File file = new File(destPath);
                            if (!file.exists()) {
                                file.mkdir();
                            }
                            File destFile = new File(destPath, getTemplateFileName());
                            FileUtils.copyFile(getTemplate(), destFile);
                            session.put("ERRORCLASS", ErrorType.successMessage);
//                            addActionError("Upload File Successfully");
                            return_message = "success";
                        } catch (Exception e) {
                            e.printStackTrace();
                            session.put("ERRORCLASS", ErrorType.errorMessage);
//                            addActionError("Could Not Upload File");
                            return_message = "fail";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // flag to check internet connection 
                String readonlineflag = "";

                LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                LhssysParameters readOnlineFlag1 = lhssysParametersDAO.readOnlineFlag(client_mast.getEntity_code());
                if (readOnlineFlag1 != null) {
                    readonlineflag = readOnlineFlag1.getParameter_value();
                    setReadOnlineFlag(readonlineflag);
                }
                //

            }
        }
        inputStream = new ByteArrayInputStream(return_message.getBytes("UTF-8"));
        return l_return_value;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
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

    public String getReadOnlineFlag() {
        return readOnlineFlag;
    }

    public void setReadOnlineFlag(String readOnlineFlag) {
        this.readOnlineFlag = readOnlineFlag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}//end class
