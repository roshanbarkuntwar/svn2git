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
import dao.generic.DAOFactory;
import globalUtilities.SFTPProcessFile;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.generateFVU.GenerateFVUFileSupport;

/**
 *
 * @author gaurav.khanzode
 */
public class GetCSIAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;

    private String tokenNo;

    public GetCSIAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String returnMsg = "";

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysParametersDAO lhssysParametersDAO;

        ViewClientMast viewClientMast = null;
        try {
            viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (viewClientMast != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                File javaDir = new File(javaDriveName);
                if (!utl.isnull(javaDriveName) && javaDir.exists()) {
                    String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                            + File.separator + viewClientMast.getClient_code()
                            + File.separator + asmt.getAccYear()
                            + File.separator + asmt.getTdsTypeCode();

                    try {
                        GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                        String csiName = fVUFileSupport.getCsiFileName(viewClientMast.getTanno());
                        csiName = !utl.isnull(csiName) ? csiName : "";

//                        utl.generateLog("Local CSI file name: " + csiName);
//                        utl.generateLog("Local CSI file location: " + generateCsiFileLoc);
                        lhssysParametersDAO = factory.getLhssysParametersDAO();
                        String ftp_flag = lhssysParametersDAO.readDataAsParameterAndEntity("SFTP_FLAG", "").getParameter_value();

                        if (!utl.isnull(ftp_flag)) {
                            List<LhssysParameters> parameters_list = lhssysParametersDAO.readServerParameters("SFTP");
                            String serverip = "";
                            String username = "";
                            String password = "";
                            int port = 0;

                            if (parameters_list != null && !parameters_list.isEmpty()) {
                                for (LhssysParameters param : parameters_list) {
                                    try {
                                        if (!utl.isnull(param.getParameter_name()) && !utl.isnull(param.getParameter_value())) {
                                            if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_IP")) {
                                                serverip = param.getParameter_value();
                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_PORT")) {
                                                port = Integer.parseInt(param.getParameter_value());
                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_USERNAME")) {
                                                username = param.getParameter_value();
                                            } else if (param.getParameter_name().trim().equalsIgnoreCase("SFTP_SERVER_PASSWORD")) {
                                                password = param.getParameter_value();
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            if (!utl.isnull(serverip) && !utl.isnull(username) && !utl.isnull(password) && !utl.isnull(generateCsiFileLoc) && !utl.isnull(csiName)) {

                                String destination_location = generateCsiFileLoc + File.separator + csiName;
                                try {
                                    /**
                                     * Downloading CSI file from SFTP server. (
                                     * in Java drive)
                                     */
                                    utl.generateLog("SCI Location: ", destination_location);

                                    new SFTPProcessFile().downloadFileFromSFTP(serverip, port, username, password, csiName, destination_location);

//                                    returnMsg = "CSI file downloaded successfully.";
//                                    session.put("ERRORCLASS", MessageType.successMessage);
//                                    session.put("fvuSessionResult", returnMsg);
                                } catch (Exception e) {

                                    returnMsg = "Error in downloading file.";
                                    session.put("ERRORCLASS", MessageType.errorMessage);
                                    session.put("fvuSessionResult", returnMsg);
                                }
                            } else {
                                returnMsg = "Insuficient parameters to download file from SFTP server.";
                                session.put("ERRORCLASS", MessageType.errorMessage);
                                session.put("fvuSessionResult", returnMsg);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return SUCCESS;
    }//end method

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
}//end class
