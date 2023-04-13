/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DownloadUsingPath extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnView = "download";
        try {

            if (!utl.isnull(getFileNamePath())) {
                try {

                    File file = new File(getFileNamePath());
                    if (file.exists()) {
                        String fname = file.getName();
                        if (!utl.isnull(getNewFileName())) {
                            fname = getNewFileName();
                        }

                        if (file.isFile()) {
                            fileInputStream = new FileInputStream(file);
                            fileNameToDownload = fname;
                            returnView = "download";

                        } else {
                            returnView = "samePage";
                            session.put("ERRORCLASS", MessageType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File");
                            setErrorMessage("Some Error Occured, Could Not Download File");

                        }
                    } else {
                        returnView = "samePage";
                        session.put("ERRORCLASS", MessageType.errorMessage);
                        utl.generateLog("File Not Found in server Try again",getFileNamePath());
                        setErrorMessage("File Not Found Please Try again.");
                    }
                } catch (Exception e) {
                    session.put("ERRORCLASS", MessageType.errorMessage);
                    session.put("session_result", "Some Error Occured, Could Not Download File");
                    returnView = "samePage";
                    setErrorMessage("File Not Found Please Try again.");
                }

            } else {
                returnView = "samePage";
                session.put("ERRORCLASS", MessageType.errorMessage);
//                session.put("session_result", "File Not Found !");
                setErrorMessage("File Not Found Please Try again.");
            }

        } catch (Exception e) {
            session.put("ERRORCLASS", MessageType.errorMessage);
            session.put("session_result", "Some Error Occured, Could Not Download File");
            setErrorMessage("Some Error Occured, Could Not Download File");
            returnView = "samePage";
        }

        return returnView;
    }

    private Map<String, Object> session;
    final Util utl;
    private String action;
    ArrayList<String> panNo;
    ArrayList<String> termList;
    private InputStream inputStream;
    InputStream fileInputStream;
    private String fileNamePath;
    private String newFileName;
    private String filePath;
    private String fileNameToDownload;
    private String fileTypeFound;
    private String errorMessage;

    public String getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    public DownloadUsingPath() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getPanNo() {
        return panNo;
    }

    public void setPanNo(ArrayList<String> panNo) {
        this.panNo = panNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public void setFileNameToDownload(String fileNameToDownload) {
        this.fileNameToDownload = fileNameToDownload;
    }

    public String getFileTypeFound() {
        return fileTypeFound;
    }

    public void setFileTypeFound(String fileTypeFound) {
        this.fileTypeFound = fileTypeFound;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
    
    
}
