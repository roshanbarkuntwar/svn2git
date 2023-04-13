/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bhawna.agrawal
 */
public class DownloadChromeSetup extends ActionSupport {

    @Override
    public String execute() throws Exception {
        String return_view = "input";
//        String externalDriveName = "D:";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysParametersDAO lhssysParametersDAO;
        String javaDriveName = "";
        try {
            lhssysParametersDAO = factory.getLhssysParametersDAO();
            LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("JAVA_DRIVE_NAME", "");
            if (readExternalDriveName != null) {
                javaDriveName = readExternalDriveName.getParameter_value();
            }
        } catch (Exception e) {
            javaDriveName = "";
        }
        try {
            String fileLocation = javaDriveName + "/TAXCPC/TAXCPC_CHROME_SETUP/ChromeStandaloneSetup.exe";
            if (!utl.isnull(fileLocation)) {
                String l_file_name = fileLocation;
                File file = new File(l_file_name);
                if (file.exists()) {//move the code to download your file inside here...
                    String real_filename = "ChromeStandaloneSetup.exe";
                    setFileName(real_filename);//User File Name
                    fileInputStream = new FileInputStream(file);
                    return_view = "success";
                } else {//handle a response to do nothing
                    return_view = "success";
                    addActionError("Could Not Download Setup");
                }
            } else {
                return_view = "input";
                addActionError("Could Not Download Setup");
            }
        } catch (Exception e) {
            return_view = "input";
            addActionError("Could Not Download Setup");
        }
        return return_view;
    }

    public DownloadChromeSetup() {
        utl = new Util();
    }
    private final Util utl;
    private InputStream fileInputStream;
    private HttpServletRequest servletRequest;
    private String fileName;
    private String dwn_type;
    private ImportFileAttribs obj_exl_html;
    private String templeteType;
    private File inputFile;
    private String inputFileContentType;
    private String inputFileFileName;
    private String contentDisposition;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDwn_type() {
        return dwn_type;
    }

    public void setDwn_type(String dwn_type) {
        this.dwn_type = dwn_type;
    }

    public ImportFileAttribs getObj_exl_html() {
        return obj_exl_html;
    }

    public void setObj_exl_html(ImportFileAttribs obj_exl_html) {
        this.obj_exl_html = obj_exl_html;
    }

    public String getTempleteType() {
        return templeteType;
    }

    public void setTempleteType(String templeteType) {
        this.templeteType = templeteType;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public String getInputFileContentType() {
        return inputFileContentType;
    }

    public void setInputFileContentType(String inputFileContentType) {
        this.inputFileContentType = inputFileContentType;
    }

    public String getInputFileFileName() {
        return inputFileFileName;
    }

    public void setInputFileFileName(String inputFileFileName) {
        this.inputFileFileName = inputFileFileName;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}
