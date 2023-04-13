/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prnInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsCorrTranLoadUpdt;
import dao.DbProc.ProcTdsReturnStatusInsert;
import dao.LhssysTdsReturnTranDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysTdsReturnTran;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class FileTranDashboardAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {

        String return_value = "";
        String returnType = "success";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            if (client != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {
                    String acc_year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();
                    String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                    String Tds_type = asmt.getTdsTypeCode();
                    String assement_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
                    Date quarterToDate = asmt.getQuarterToDate();//use for procedure
                    Date quarterFromDate = asmt.getQuarterFromDate();//use for procedure
                    Long l_client_loginid_seq;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }
                    if (!utl.isnull(getAction())) {
                        LhssysTdsReturnTranDAO lhssysTdsReturnTranDAO = factory.getLhssysTdsReturnTranDAO();

                        if (getAction().equalsIgnoreCase("update")) {
                            if (!utl.isnull(getRowid())) {
                                try {
                                    if (getPrnFile() != null && !utl.isnull(getPrnFileFileName())) {
                                        boolean fileUploadFlag = false;
                                        String uploadFilePath = javaDriveName + File.separator + "CLIENT_PRN" + File.separator + client.getClient_code() + File.separator + acc_year + File.separator + quarterNo + File.separator + Tds_type + File.separator + "PDF" + File.separator;
                                        try {
                                            File uploadFile = new File(uploadFilePath);
                                            if (!uploadFile.exists()) {
                                                uploadFile.mkdirs();
                                            }
                                            File destFile = new File(uploadFilePath, getPrnFileFileName());
                                            FileUtils.copyFile(getPrnFile(), destFile);
                                            fileUploadFlag = true;
                                        } catch (Exception e) {
                                            fileUploadFlag = false;
                                            e.printStackTrace();
                                        }
                                        if (fileUploadFlag) {
                                            //LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readById(getRowid(), true);
                                            LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readByRowIdSeq(Long.parseLong(getRowid()));
                                            if (entity != null) {
                                                entity.setFile_upload_ack_no(getFile_upload_ack_no());
                                                if (!utl.isnull(getFile_upload_ack_date())) {
                                                    entity.setFile_upload_ack_date(new SimpleDateFormat("dd-MM-yyyy").parse(getFile_upload_ack_date()));
                                                }
                                                entity.setFile_upload_ack_pdf_name(getPrnFileFileName());
                                                entity.setFile_upload_ack_pdf_path(uploadFilePath);

                                                LhssysTdsReturnTranDAO lhssysTdsReturnTranDAO1 = factory.getLhssysTdsReturnTranDAO();
                                                boolean update = lhssysTdsReturnTranDAO1.update(entity);

                                                return_value = "Record Updated Successfully!";
                                                setMsg("S");

                                                String module = session.get("MODULE").toString();
                                                if (update && !utl.isnull(module) && module.equalsIgnoreCase("Correction")) {
                                                    ProcTdsCorrTranLoadUpdt proc = new ProcTdsCorrTranLoadUpdt();
                                                    String execute_procedure = proc.execute_procedure(client.getEntity_code(), client.getClient_code(), acc_year, assement_acc_year, Integer.parseInt(asmt.getQuarterNo()), quarterFromDate, quarterToDate, Tds_type, l_client_loginid_seq);
                                                    if (execute_procedure.equalsIgnoreCase("0")) {
                                                        setMsg("S");
                                                    } else {
                                                        setMsg("E");
                                                    }
                                                }
                                            }else{
                                                utl.generateLog("Record Not Updated ", "");
                                            }
                                        } else {
                                            return_value = "Some Error Occurred File Not Uploaded!";
                                            setMsg("E");
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    setMsg("E");
                                }

                            }
                            returnType = "success";
                        } else if (getAction().equalsIgnoreCase("download")) {
                            if (!utl.isnull(getRowid())) {
                                try {
                                   // LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readById(getRowid(), true);
                                   LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readByRowIdSeq(Long.parseLong(getRowid()));
                                    if (entity != null) {
                                        if (!utl.isnull(entity.getFile_upload_ack_pdf_path()) && !utl.isnull(entity.getFile_upload_ack_pdf_name())) {
                                            String filePath = entity.getFile_upload_ack_pdf_path() + File.separator + entity.getFile_upload_ack_pdf_name();
                                            try {
                                                File fileName = new File(filePath);
                                                if (fileName.isFile()) {
                                                    fileInputStream = new FileInputStream(fileName);
                                                    fileNameToDownload = fileName.getName();
                                                    returnType = "download";
                                                } else {
                                                    returnType = "samePage";
                                                    setErrorMessage("Some Error Occurred !");
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                returnType = "samePage";
                                                setErrorMessage("File Not Found Please Try again.");
                                            }
                                        }
                                    } else {
                                        returnType = "samePage";
                                        setErrorMessage("File Not Found Please Try again.");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (getAction().equalsIgnoreCase("refreshRecords")) {
                            if (!utl.isnull(getProcessType())) {
                                String module = (String) session.get("MODULE") != null ? (String) session.get("MODULE") : "";
                                String moduleFlag = "R";
                                if (!utl.isnull(module) && module.equalsIgnoreCase("Correction")) {
                                    moduleFlag = "C";
                                } else if (!utl.isnull(module) && module.equalsIgnoreCase("G")) {
                                    moduleFlag = "15GH";
                                }
                                ProcTdsReturnStatusInsert proc = new ProcTdsReturnStatusInsert();
                                String execute_procedure = proc.execute_procedure(client.getEntity_code(), client.getClient_code(), acc_year, Integer.parseInt(asmt.getQuarterNo()), 
                                        Tds_type, moduleFlag, getProcessType(), getProcessLevel());
                                
                                return_value = execute_procedure;
                            }
                            returnType = "ajaxSuccess";
                        } else if (getAction().equalsIgnoreCase("delete")) {
                            if (!utl.isnull(getRowid())) {
                                try {
                                    LhssysTdsReturnTranDAO lhssysTds = factory.getLhssysTdsReturnTranDAO();
                                    //LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readById(getRowid(), true);
                                     LhssysTdsReturnTran entity = lhssysTdsReturnTranDAO.readByRowIdSeq(Long.parseLong(getRowid()));
                                    if (entity != null) {
                                        File f = new File(entity.getFile_upload_ack_pdf_path() + File.separator + entity.getFile_upload_ack_pdf_name());
                                        try {
                                            if (f.exists()) {
                                                f.deleteOnExit();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        entity.setFile_upload_ack_no("");
                                        entity.setFile_upload_ack_date(null);
                                        entity.setFile_upload_ack_pdf_path("");
                                        entity.setFile_upload_ack_pdf_name("");
                                        LhssysTdsReturnTranDAO lhssysTds1 = factory.getLhssysTdsReturnTranDAO();

                                        boolean delete = lhssysTds1.update(entity);
                                        if (delete) {
                                            setMsg("DS");
                                        }
                                    } else {
                                        setMsg("DE");
                                    }
                                } catch (Exception e) {
                                    setMsg("DE");
                                    e.printStackTrace();
                                }
                            } else {
                                setMsg("DE");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMsg("E");
        }
        inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
        return returnType;
    }

    public FileTranDashboardAjaxAction() {
        utl = new Util();
    }
    Util utl;
    InputStream inputStream;
    Map<String, Object> session;
    private String action;
    private String rowid;
    private String msg;
    private LhssysTdsReturnTran lhssysTdsReturnTran;
    private File prnFile;
    private String prnFileContentType;
    private String prnFileFileName;
    private String file_upload_ack_no;
    private String file_upload_ack_date;
    private String errorMessage;
    private String processType;
    private String processLevel;
    InputStream fileInputStream;
    private String fileNameToDownload;

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public void setFileNameToDownload(String fileNameToDownload) {
        this.fileNameToDownload = fileNameToDownload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFile_upload_ack_no() {
        return file_upload_ack_no;
    }

    public void setFile_upload_ack_no(String file_upload_ack_no) {
        this.file_upload_ack_no = file_upload_ack_no;
    }

    public String getFile_upload_ack_date() {
        return file_upload_ack_date;
    }

    public void setFile_upload_ack_date(String file_upload_ack_date) {
        this.file_upload_ack_date = file_upload_ack_date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public LhssysTdsReturnTran getLhssysTdsReturnTran() {
        return lhssysTdsReturnTran;
    }

    public void setLhssysTdsReturnTran(LhssysTdsReturnTran lhssysTdsReturnTran) {
        this.lhssysTdsReturnTran = lhssysTdsReturnTran;
    }

    public File getPrnFile() {
        return prnFile;
    }

    public void setPrnFile(File prnFile) {
        this.prnFile = prnFile;
    }

    public String getPrnFileContentType() {
        return prnFileContentType;
    }

    public void setPrnFileContentType(String prnFileContentType) {
        this.prnFileContentType = prnFileContentType;
    }

    public String getPrnFileFileName() {
        return prnFileFileName;
    }

    public void setPrnFileFileName(String prnFileFileName) {
        this.prnFileFileName = prnFileFileName;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @Override
    public void setSession(Map<String, Object> map
    ) {
        this.session = map;
    }//end

}
