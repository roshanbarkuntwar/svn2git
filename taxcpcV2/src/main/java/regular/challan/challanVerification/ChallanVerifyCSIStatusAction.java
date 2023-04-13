/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanVerification;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.HttpDownloadUtility;
import globalUtilities.OltasCSIUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.generateFVU.GenerateFVUFileSupport;

/**
 *
 * @author gaurav
 */
public class ChallanVerifyCSIStatusAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnString = "success";
        String result = "false";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

        ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
        ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
        String code_level_val = client.getCode_level();
        setCode_level(code_level_val);
        setIsParentRecord((String) session.get("CLIENT_TRAN_LEVEL"));
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        String acc_year = asmt.getAccYear();
        String quarterNo = asmt.getQuarterNo();
        String tds_type_code = asmt.getTdsTypeCode();

        boolean isCsiFilePresent = false;
        try {
            // get JAVA and oracle drive names
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            //
            // CHECK IF FILE IS PRESENT AT CSI LOCATION
            //get csi file name
            String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                    + File.separator + client.getClient_code()
                    + File.separator + asmt.getAccYear()
                    + File.separator + asmt.getTdsTypeCode();
            GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
            fVUFileSupport.createDirectory(generateCsiFileLoc);
            File csiFileLoc = new File(generateCsiFileLoc);
            String csiName = fVUFileSupport.getCsiFileName(client.getTanno());
            csiName = !utl.isnull(csiName) ? csiName : "";
            File[] listFiles = csiFileLoc.listFiles();
            for (File listFile : listFiles) {
                String oldCsiName = listFile.getName();
                utl.generateLog("oldCsiName===", oldCsiName);
                if (oldCsiName.equalsIgnoreCase(csiName)) {
                    isCsiFilePresent = true;
                    break;
                }
            }
            if (!isCsiFilePresent) {
                boolean isCsiFileDownloaded = false;

                // commented as per harsh mehadia 18/01/2017 bcz CSI not validating
                // delete all files in csi file location
                fVUFileSupport.deleteFilesInDirectory(generateCsiFileLoc);

                OltasCSIUtil oltasCsiUtil = new OltasCSIUtil();
                // end
                String downloadFileLocation = oltasCsiUtil.getCsiDownloadUrl(client.getTanno(), client.getClient_code(),
                        acc_year, tds_type_code, quarterNo);
                utl.generateLog(null, "Regular Verify challan Name url----" + client.getClient_code() + "----" + downloadFileLocation);

                String downloadFile = HttpDownloadUtility.downloadFile(downloadFileLocation, generateCsiFileLoc);
                if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                    isCsiFileDownloaded = true;
                    isCsiFilePresent = true;
                }
                if (!isCsiFileDownloaded) {
                    LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                    LhssysParameters csiFileTime = lhssysParametersDAO.readCsiWaitTime(client.getEntity_code());
                    long csiDwndwait = 3000; // csiDwndwait is used to wait thread for few minutes to get proper response . if parameter is empty than default 3 sec is used
                    if (csiFileTime != null) {
                        try {
                            csiDwndwait = Long.parseLong(csiFileTime.getParameter_value());
                        } catch (Exception e) {
                            csiDwndwait = 3000;
                        }
                    }
                    downloadFile = HttpDownloadUtility.downloadFile(downloadFileLocation, generateCsiFileLoc, csiDwndwait);
                    if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                        isCsiFileDownloaded = true;
                        isCsiFilePresent = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isCsiFilePresent) {
            result = "true";
        }
        inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
        return returnString;
    }//end method

    public ChallanVerifyCSIStatusAction() {
        utl = new Util();
    }//end constructor

    private final Util utl;
    InputStream inputStream;
    Map<String, Object> session;
    private String code_level;
    private String IsParentRecord;
    private String allowCsiUpload;

    public String getAllowCsiUpload() {
        return allowCsiUpload;
    }

    public void setAllowCsiUpload(String allowCsiUpload) {
        this.allowCsiUpload = allowCsiUpload;
    }

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getIsParentRecord() {
        return IsParentRecord;
    }

    public void setIsParentRecord(String IsParentRecord) {
        this.IsParentRecord = IsParentRecord;
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
}//end class
