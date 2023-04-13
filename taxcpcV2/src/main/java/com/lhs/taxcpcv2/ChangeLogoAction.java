/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.EntityLogoMast;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class ChangeLogoAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";
        session.put("ACTIVE_TAB", "dashboard");
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            UserMast user = (UserMast) session.get("LOGINUSER");
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            if (!utl.isnull(getUpload()) && getUpload().equalsIgnoreCase("true")) {
                EntityLogoMast logoMast = new EntityLogoMast();
                if (!utl.isnull(client.getEntity_code())) {
                    logoMast.setEntity_code(client.getEntity_code());
                    logoMast.setUser_code("ADMIN");
                    logoMast.setLastupdate(new Date());
                    logoMast.setLogo(FileUtils.readFileToByteArray(getLogoUploadFile()));
                    try {
                        boolean saveOrUpdateLogo = factory.getEntityLogoMastDAO().saveOrUpdate(logoMast);
                        if (saveOrUpdateLogo) {
                            return_msg = "success";
                            return_view = "filter_success";
                        } else {
                            return_msg = "error";
                            return_view = "filter_success";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return_msg = "error";
                    return_view = "filter_success";
                }
            } else if (!utl.isnull(getResetLogo()) && getResetLogo().equalsIgnoreCase("true")) {
                if (!utl.isnull(client.getEntity_code())) {
                    try {
                        EntityLogoMast logoMast = new EntityLogoMast();
                        logoMast.setEntity_code(client.getEntity_code());
                        logoMast.setUser_code("ADMIN");
                        logoMast.setLastupdate(new Date());
                        boolean resetLogo = factory.getEntityLogoMastDAO().delete(logoMast);
                        if (resetLogo) {
                            return_msg = "success";
                            return_view = "filter_success";
                        } else {
                            return_msg = "error";
                            return_view = "filter_success";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return_msg = "error";
                    return_view = "filter_success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
    }//end method

    private Map<String, Object> session;
    private final Util utl;
    private String upload;
    private String resetLogo;
    private File logoUploadFile;
    private String logoUploadFileContentType;
    private String logoUploadFileFileName;
    private InputStream inputStream;

    public ChangeLogoAction() {
        utl = new Util();
    }//end constructor

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getResetLogo() {
        return resetLogo;
    }

    public void setResetLogo(String resetLogo) {
        this.resetLogo = resetLogo;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public File getLogoUploadFile() {
        return logoUploadFile;
    }

    public void setLogoUploadFile(File logoUploadFile) {
        this.logoUploadFile = logoUploadFile;
    }

    public String getLogoUploadFileContentType() {
        return logoUploadFileContentType;
    }

    public void setLogoUploadFileContentType(String logoUploadFileContentType) {
        this.logoUploadFileContentType = logoUploadFileContentType;
    }

    public String getLogoUploadFileFileName() {
        return logoUploadFileFileName;
    }

    public void setLogoUploadFileFileName(String logoUploadFileFileName) {
        this.logoUploadFileFileName = logoUploadFileFileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
