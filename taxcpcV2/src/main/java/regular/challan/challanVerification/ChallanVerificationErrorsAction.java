/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanVerification;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author user
 */
public class ChallanVerificationErrorsAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;

    private String action;
    private String errorMessage;
    private String flag;
    private String filePath;
    private String fileName;

    public ChallanVerificationErrorsAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        setErrorMessage("Some Error Occurred, Could Not Verify Challans");
        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("NOCHALLANS")) {
                setErrorMessage("No Challans Found");
            } else if (getAction().equalsIgnoreCase("ERROR")) {
                setErrorMessage("Some Error Occurred, Could Not Verify Challans");
            } else if (getAction().equalsIgnoreCase("errorFileGenerated")) {
                setFlag("true");
                try {
                    String fileLocation = (String) session.get("genertedFileLoc");
                    String fName = (String) session.get("genertedFileName");
                    utl.generateLog("fname..", fName);
                    if (!utl.isnull(fileLocation) && !utl.isnull(fName)) {
                        utl.generateLog("fname..", fName);
                        setFileName(fName);
                        setFilePath(fileLocation);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                setErrorMessage("Error File Generated, Could Not Verify Challans");
            } else if (getAction().equalsIgnoreCase("csiFileNotGenerated")) {
                setErrorMessage("Some Error Occured, Could Not Generate CSI File");
            } else if (getAction().equalsIgnoreCase("deducteeNameNotMatch")) {
                setErrorMessage("Client Name is Different From ITD, Could Not Generate Challan File");
            } else if (getAction().equalsIgnoreCase("fvuLimit")) {
                setErrorMessage("This is a free version. You cannot file more than 3 returns. For further details contact support person.");
            } else if (getAction().equalsIgnoreCase("driveError")) {
                setErrorMessage("External Drive Not Available/Not Mapped, Please Contact Support Person.");
            }
        }
        return returnView;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}//end class
