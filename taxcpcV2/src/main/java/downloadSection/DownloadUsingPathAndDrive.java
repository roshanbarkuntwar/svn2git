/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadSection;

import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysProcessLogDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class DownloadUsingPathAndDrive extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnView = "download";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        //ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        //String clientCode = client.getClient_code();
        UserMast user_mast = (UserMast) session.get("LOGINUSER");
        String username = user_mast.getUser_name();
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
                            System.out.println("call file download");
                            fileInputStream = new FileInputStream(file);
                            fileNameToDownload = fname;

                            try {
                                Long Process_seqno = Long.parseLong(getProcess_seqno());
                                Long Parent_process_seqno = Long.parseLong(getParent_process_seqno());
                                System.out.println("Process_seqno  = " + Process_seqno);
                                System.out.println("Parent_process_seqno = " + Parent_process_seqno);
                                //DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                                LhssysProcessLogDAO lhssysProcessLogDAO1 = factory.getLhssysProcessLogDAO();
                                LhssysProcessLog lhssys_log = lhssysProcessLogDAO1.readPRocessLogByProcessSeqNoAndParent(Process_seqno, Parent_process_seqno);
                                //LhssysProcessLog lhssys_log = lhssysProcessLogDAO1.readProcessbySeqNo(Process_seqno);
                                if (lhssys_log != null) {
                                    Timestamp timestamp = new Timestamp(new Date().getTime());
                                    lhssys_log.setFvu_pid_flag("T");
                                    lhssys_log.setFvu_pid("File has been download by " + username + " - " + timestamp);
                                    LhssysProcessLogDAO fileTranDAO1 = factory.getLhssysProcessLogDAO();

                                    boolean updateData = fileTranDAO1.update(lhssys_log);
                                    if (updateData) {
                                        utl.generateLog("report flag updated in Fvu_pid and fvu_pid_flag coloum", "");
                                    }
                                } else {
                                    utl.generateLog("getting  null object LhssysProcessLog ", "");
                                    utl.generateLog("flag value not updeted in Fvu_pid and fvu_pid_flag coloum", "");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                returnView = "download";
                            }

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
                        // session.put("session_result", "File Not Found !");
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
    private String parent_process_seqno;
    private String process_seqno;

    public String getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    public DownloadUsingPathAndDrive() {
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

    public String getParent_process_seqno() {
        return parent_process_seqno;
    }

    public void setParent_process_seqno(String parent_process_seqno) {
        this.parent_process_seqno = parent_process_seqno;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

}
