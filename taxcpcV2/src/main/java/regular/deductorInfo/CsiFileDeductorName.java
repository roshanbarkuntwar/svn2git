package regular.deductorInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import globalUtilities.HttpConnUtil;
import globalUtilities.OltasCSIUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.generateFVU.GenerateFVUFileSupport;
import regular.generateFVU.ReadFromConsolidatedTDSFile;

/**
 *
 * @author aniket.naik
 */
public class CsiFileDeductorName extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnString = "success";
        String client_name = "";

        try {

            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            if (viewClientMast != null) {
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                client_name = this.getClientNameFromCsiFile(viewClientMast, (Assessment) session.get("ASSESSMENT"), javaDriveName);

            }

        } catch (Exception e) {
            client_name = "";
            e.printStackTrace();
        }
        //utl.generateLog("ClientName-----"+client_name);
        inputStream = new ByteArrayInputStream(client_name.getBytes("UTF-8"));

        return returnString;
    }

    public String getClientNameFromCsiFile(ViewClientMast viewClientMast, Assessment asmt, String javaDriveName) {
        String deductor_data = "";
        GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();

//        String tds_quarter = asmt.getQuarterNo();
        try {
            String csiFilePath = javaDriveName + File.separator + "CSI_FILES"
                    + File.separator + viewClientMast.getClient_code()
                    + File.separator + asmt.getAccYear()
                    + File.separator + asmt.getTdsTypeCode();
            fVUFileSupport.createDirectory(csiFilePath);
//            File csiFileLoc = new File(csiFilePath);

            // CHECK IF FILE IS PRESENT AT CSI LOCATION
            //get csi file name
            boolean isCsiFilePresent = false;
            String csiName = fVUFileSupport.getCsiFileName(viewClientMast.getTanno());
            csiName = !utl.isnull(csiName) ? csiName : "";

            utl.generateLog("csiFilePath= ", csiFilePath);
            utl.generateLog("csiName= ", csiName);

            if (Files.exists(Paths.get(csiFilePath + File.separator + csiName))) {
//            File[] listFiles = csiFileLoc.listFiles();
//            for (File listFile : listFiles) {
//                String oldCsiName = listFile.getName();
//                if (oldCsiName.equalsIgnoreCase(csiName)) {
                isCsiFilePresent = true;
                utl.generateLog(null, "CSI FOUND IN FOLDER-----");
//                    break;
//                }
//            }
            }
            if (!isCsiFilePresent) {
                boolean isCsiFileDownloaded = false;

                OltasCSIUtil oltasCsiUtil = new OltasCSIUtil();

                // delete all files in csi file location
                fVUFileSupport.deleteFilesInDirectory(csiFilePath);
                //end
                String downloadFileLocation = oltasCsiUtil.getCsiDownloadUrl(viewClientMast.getTanno(), viewClientMast.getClient_code(), asmt);
                utl.generateLog("Deductor Name url----", viewClientMast.getClient_code() + "----" + downloadFileLocation);

                String downloadFile = HttpConnUtil.downloadFile(downloadFileLocation, csiFilePath);
                if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                    isCsiFileDownloaded = true;
                    isCsiFilePresent = true;
                }
                if (!isCsiFileDownloaded) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                    LhssysParameters csiFileTime = lhssysParametersDAO.readDataAsParameterAndEntity("CSI_DWNLD_WAIT_TIME", viewClientMast.getEntity_code());
                    long csiDwndwait = 3000; // csiDwndwait is used to wait thread for few minutes to get proper response . if parameter is empty than default 3 sec is used
                    if (csiFileTime != null) {
                        try {
                            csiDwndwait = Long.parseLong(csiFileTime.getParameter_value());
                        } catch (Exception e) {
                            csiDwndwait = 3000;
                        }
                    }
                    downloadFile = HttpConnUtil.downloadFile(downloadFileLocation, csiFilePath, csiDwndwait);
                    if (!utl.isnull(downloadFile) && downloadFile.equalsIgnoreCase("T")) {
                        isCsiFileDownloaded = true;
                        isCsiFilePresent = true;
                        utl.generateLog(null, "CSI FILE DOWNLOADED-----");
                    }
                }
            }

            if (isCsiFilePresent) {
                ReadFromConsolidatedTDSFile dSFile = new ReadFromConsolidatedTDSFile();
                String clientNameFromCsiFile = dSFile.getClientNameFromCsiFile(csiFilePath + File.separator + csiName);
                if (!utl.isnull(clientNameFromCsiFile)) {
                    deductor_data = clientNameFromCsiFile;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deductor_data;
    }
    private Map<String, Object> session;

    private Util utl;

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CsiFileDeductorName() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
