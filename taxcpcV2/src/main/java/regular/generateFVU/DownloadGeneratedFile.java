/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author bhawna.agrawal
 */
public class DownloadGeneratedFile extends ActionSupport implements SessionAware {

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String execute() throws Exception {
        String l_returnFlag = "samePage";
        try {
            File file = new File(getFilePath());
            if (file.exists()) {            // if derectory exist then
                File[] files = file.listFiles();
                for (File fileName : files) { // iterate through all files and folder in the directory
                    // check whether the given file exists in the directory  
                    if (fileName.isFile()) {
                        if (fileName.getName().equalsIgnoreCase(fname)) {
                            fileInputStream = new FileInputStream(fileName);
                            fileNameToDownload = fileName.getName();
                            l_returnFlag = "success";
                            message = "";
                            break;
                        }
                    } else {
                        l_returnFlag = "samePage";
                        message = "File Not found";
                    }
                }
            } else {
                l_returnFlag = "samePage";
                message = "Directory Not Found";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            l_returnFlag = "samePage";
            message = "File Not Found Please Try again !!";

        }
        return l_returnFlag;
    }

    public DownloadGeneratedFile() {
    }
    Map<String, Object> session;
    InputStream fileInputStream;
    private String fname;
    private String filePath;
    private String fileNameToDownload;
    private String fileTypeFound;
    private String message = "File Not Found";

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMessage() {
        return message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileTypeFound() {
        return fileTypeFound;
    }

    public void setFileTypeFound(String fileTypeFound) {
        this.fileTypeFound = fileTypeFound;
    }

}
