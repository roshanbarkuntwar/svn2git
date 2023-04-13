/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysCorrFileUploadTran;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class CorrImportFileOtherAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_mas = "error";
        try {
            if (!utl.isnull(getAction())) {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");

                Long l_client_loginid_seq;

                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }

                if (getAction().equalsIgnoreCase("extractAndSave")) {
//                    
//                            // code for UNIX to Windows
//                                EolConverter converter = new EolConverter();
//                                converter.convertUnixToWindows(textFilePath + File.separator + file[i].getName(), textFilePathEOL + File.separator + file[i].getName());
//                    

                    try {

                        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

//                        String destPath = javaDriveName + "/CORR_BULK_FILE/" + client_code + "/" + l_acc_year + "/Q" + l_period_no.longValue() + "/" + tds_type_code + "/" + getFolderName() + "/";
//                        String destFolder = javaDriveName + "/CORR_BULK_FILE/" + client_code + "/" + l_acc_year + "/Q" + l_period_no.longValue() + "/" + tds_type_code + "/" + getFolderName();
                        String destPath = javaDriveName + "/CORR_BULK_FILE/" + client.getClient_code() + "/" + asmt.getAccYear() + "/Q" + asmt.getQuarterNo() + "/" + asmt.getTdsTypeCode() + "/";
                        String destFolder = javaDriveName + "/CORR_BULK_FILE/" + client.getClient_code() + "/" + asmt.getAccYear() + "/Q" + asmt.getQuarterNo() + "/" + asmt.getTdsTypeCode();

                        String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
                        String storageLoction = oracleDriveName + File.separator + "CORR_TEXT_IMPORT" + File.separator;
                        utl.generateLog("\ndestPath 1 ", destPath);
                        new UploadZipFileAjaxSupport().unZipAndCopyFile(destPath, destFolder, oracleDriveName, storageLoction);
                        try {

                            saveFilesDetails(factory, destPath, l_client_loginid_seq, client.getClient_code(), asmt.getAccYear(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), Double.parseDouble(asmt.getQuarterNo()), asmt.getTdsTypeCode(), getSelectCorrectionType());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return_mas = "success";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("cleanDataAndDirectory")) {

                    try {
                        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                        String directoryLocation = javaDriveName + "/CORR_BULK_FILE/" + client.getClient_code() + "/" + asmt.getAccYear() + "/Q" + asmt.getQuarterNo() + "/" + asmt.getTdsTypeCode();
                        boolean status = deleteOldData(factory, client.getClient_code(), asmt.getAccYear(), Double.parseDouble(asmt.getQuarterNo()), asmt.getTdsTypeCode(), l_client_loginid_seq);
                        new UploadZipFileAjaxSupport().deleteFilesFromLocation(directoryLocation);

                        return_mas = "success";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }//End action
            }//End Action != null
        } catch (Exception e) {

        }
        inputStream = new ByteArrayInputStream(return_mas.getBytes("UTF-8"));
        return return_view;
    }

    public void saveFilesDetails(DAOFactory factory, String destPath, Long l_client_loginid_seq, String client_code, String l_acc_year, Date quarterFromDate, Date quarterToDate, Double l_period_no, String tds_type_code, String selectCorrection_Type) {
        if (!utl.isnull(destPath)) {

            String destPath_Just = destPath + "Justification/UNIX/";
            String destPath_PAN = destPath + "PANNO_Correction/UNIX/";

            ArrayList<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList = new ArrayList<LhssysCorrFileUploadTran>();
            try {
                deleteOldData(factory, client_code, l_acc_year, l_period_no, tds_type_code, l_client_loginid_seq);

//                System.out.println("saveFilesDetails --------- 309 ");
                String destPathUNIX = destPath + "UNIX/";
                File destFile = new File(destPathUNIX);
                if (destFile != null) {
                    File[] files = destFile.listFiles();
                    if (files != null && files.length > 0) {
//                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                        System.out.println("saveFilesDetails --------- 315 ");

                        for (File file : files) {

                            if (!file.isDirectory()) {
                                String fileName = file.getName();
                                int index = fileName.indexOf(".");
                                String extenssion = fileName.substring(index + 1, fileName.length());
                                int i = 1;
                                if (!extenssion.equalsIgnoreCase("ZIP")) {
                                    try {

                                        LhssysCorrFileUploadTran lhssysCorrFileUploadTran = new LhssysCorrFileUploadTran();
                                        lhssysCorrFileUploadTran.setClient_login_session_seqno(l_client_loginid_seq);
                                        lhssysCorrFileUploadTran.setClient_code(client_code);
                                        lhssysCorrFileUploadTran.setTds_type_code(tds_type_code);
                                        lhssysCorrFileUploadTran.setAcc_year(l_acc_year);
                                        lhssysCorrFileUploadTran.setQuarter_no(l_period_no);
                                        lhssysCorrFileUploadTran.setMonth(new DateTimeUtil().get_sysdate("MMM").toUpperCase());
                                        lhssysCorrFileUploadTran.setFrom_date(quarterFromDate);
                                        lhssysCorrFileUploadTran.setTo_date(quarterToDate);
                                        lhssysCorrFileUploadTran.setFile_type("ZIP");
                                        lhssysCorrFileUploadTran.setFile_name(file.getName());
                                        lhssysCorrFileUploadTran.setStorage_file_path(destPathUNIX);
                                        lhssysCorrFileUploadTran.setLoad_start_timestamp(new Date());
                                        lhssysCorrFileUploadTran.setFile_size(new FileOptUtil().GetFileSizeByFilePath(destPathUNIX + "/" + file.getName()));

                                        lhssysCorrFileUploadTranList.add(lhssysCorrFileUploadTran);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }//
                            }// Not Dir
                        }//End Foe
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Start Justification Folder
            try {
//                System.out.println("destPath_Just _____" + destPath_Just);
//                System.out.println("saveFilesDetails --------- 359 ");
                File destFile = new File(destPath_Just);
                if (destFile != null) {
                    File[] files = destFile.listFiles();
                    if (files != null && files.length > 0) {
//                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                        System.out.println("saveFilesDetails --------- 365 ");

                        for (File file : files) {
                            if (!file.isDirectory()) {

                                String fileName = file.getName();

                                int index = fileName.indexOf(".");
                                String extenssion = fileName.substring(index + 1, fileName.length());
                                int i = 1;
                                if (!extenssion.equalsIgnoreCase("ZIP")) {
                                    try {

                                        if (lhssysCorrFileUploadTranList != null) {
                                            for (LhssysCorrFileUploadTran lhssysCorrFileUploadTran : lhssysCorrFileUploadTranList) {
                                                lhssysCorrFileUploadTran.setJustification_file_name(fileName);
                                            }//End For
                                        }//End If

//                                        String saveAndReturnFileSeqno = factory.getLhssysCorrFileUploadTranDAO().saveAndReturnFileSeqno(lhssysCorrFileUploadTran);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }// Not Dir
                        }// End For
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // End Justification Folder

            // Start PANNO_Correction Folder
            try {
//                System.out.println("destPath_PAN _____" + destPath_PAN);
//                System.out.println("saveFilesDetails --------- 410 ");
                File destFile = new File(destPath_PAN);
                if (destFile != null) {
                    File[] files = destFile.listFiles();
                    if (files != null && files.length > 0) {
//                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                        System.out.println("saveFilesDetails --------- 416 ");

                        for (File file : files) {
                            if (!file.isDirectory()) {
                                String fileName = file.getName();

                                int index = fileName.indexOf(".");
                                String extenssion = fileName.substring(index + 1, fileName.length());
                                int i = 1;
                                if (!extenssion.equalsIgnoreCase("ZIP")) {
                                    try {

                                        if (lhssysCorrFileUploadTranList != null) {
                                            for (LhssysCorrFileUploadTran lhssysCorrFileUploadTran : lhssysCorrFileUploadTranList) {
                                                lhssysCorrFileUploadTran.setPanno_corr_file_name(fileName);

                                            }//End For
                                        }//End If

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }// Not Dir
                        }//End For
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // End PANNO_Correction Folder

//            Start save Data in Table
            if (lhssysCorrFileUploadTranList != null) {
                for (LhssysCorrFileUploadTran lhssysCorrFileUploadTran : lhssysCorrFileUploadTranList) {
                    String saveAndReturnFileSeqno = factory.getLhssysCorrFileUploadTranDAO().saveAndReturnFileSeqno(lhssysCorrFileUploadTran);
                }//End For
            }//End If

//            End save Data in Table
        }// End dest Path != null

    }//End Method

    public boolean deleteOldData(DAOFactory factory, String client_code, String acc_year, Double quarter_no, String tds_type_code, Long client_login_session_seqno) {
        boolean status = false;

        try {
            String deleteQuery
                    = "\n"
                    + "delete from lhssys_corr_file_upload_tran a\n"
                    + " where a.client_code = '" + client_code + "'\n"
                    + "   and a.acc_year = '" + acc_year + "'\n"
                    + "   and a.quarter_no = " + quarter_no.longValue() + "\n"
                    + "   and a.tds_type_code = '" + tds_type_code + "'\n"
                    + "   /*and a.client_login_session_seqno = '" + client_login_session_seqno + "'*/ \n";
            utl.generateSpecialLog("deleteOldData Query \n ", deleteQuery);
            status = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(deleteQuery);
            utl.generateSpecialLog("Delete status ", status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }//End Function
    Util utl = new Util();

    private String action;
    private Map<String, Object> session;
    private InputStream inputStream;
    private String selectCorrectionType;

    public String getSelectCorrectionType() {
        return selectCorrectionType;
    }

    public void setSelectCorrectionType(String selectCorrectionType) {
        this.selectCorrectionType = selectCorrectionType;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CorrImportFileOtherAjaxAction() {
        utl = new Util();
    }
}
