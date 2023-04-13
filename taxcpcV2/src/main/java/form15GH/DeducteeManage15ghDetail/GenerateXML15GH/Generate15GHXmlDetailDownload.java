/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.DeducteeManage15ghDetail.GenerateXML15GH;

import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author seema.mourya
 */
public class Generate15GHXmlDetailDownload extends ActionSupport implements SessionAware, ServletRequestAware{
    
    @Override
    public  String execute() throws Exception
    {
          String l_returnFlag = "samePage";
        try {
            File directory = new File(getFilePath());
            if (directory.exists()) {
                String[] files = directory.list();
                byte[] zip = zipFiles(directory, files);
                fileInputStream =  new ByteArrayInputStream(zip);
                fileNameToDownload = getFname() + ".zip";
                l_returnFlag = "success";
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
    
     public Generate15GHXmlDetailDownload() {
    }

     
    Map<String, Object> session;
    InputStream fileInputStream;
    private HttpServletRequest servletRequest;
    private String fname;
    private String filePath;
    private String fileNameToDownload;
    private String fileTypeFound;
    private String message = "File Not Found"; 
     
     
     
  private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            byte bytes[] = new byte[4096];
            for (String fileName : files) {
                FileInputStream fis = new FileInputStream(directory.getAbsolutePath() + File.separator + fileName);
                BufferedInputStream bis = new BufferedInputStream(fis);
                zos.putNextEntry(new ZipEntry(fileName));

                int bytesRead;
                while ((bytesRead = bis.read(bytes)) > 0) {
                    zos.write(bytes, 0, bytesRead);
                }
                zos.closeEntry();
                bis.close();
                fis.close();
            }
            zos.flush();
            baos.flush();
            zos.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }//end method

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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = hsr;
    }//end

    
}
