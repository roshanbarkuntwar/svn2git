/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadTdsCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DownloadFileUsingTanOrPanno extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnType = "success";

        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String finyear = asmt.getAccYear();
            String qrtNo = asmt.getQuarterNo();

            if (!utl.isnull(getFlag())) {
                if (getFlag().equalsIgnoreCase("P")) {
                    utl.generateLog("SEARCHED PANNO", getPanno());
                    List<String> list = new ArrayList<String>();
                    if (!utl.isnull(getDownloadType()) && getDownloadType().equalsIgnoreCase("F")) {
                        utl.generateLog("TDS CErtificate using File System Method", "");
                        String query = "select t.TDS_CERTIFICATE_DIR from view_acc_year t where t.acc_year='" + finyear + "'";

                        DbFunctionExecutorAsString exe = new DbFunctionExecutorAsString();
                        String directory = exe.execute_oracle_function_as_string(query);

                        //sbi trs logic start****************
                        String tannonoFileList = directory + File.separator + "TDS_CERTIFICATE" + File.separator + "TANNO_LIST_FILE" + File.separator + client.getEntity_code() + File.separator + "TAN_LIST.txt";
                        List<String> tannoList = readTannoList(tannonoFileList);
                        //System.out.println("directory"+directory);
                        utl.generateLog("tannno list filepath.............", tannoList.size());
                        File t = null;
                        if (tannoList != null) {
                            for (String tanno : tannoList) {
                                // utl.generateLog(directory + "\\TDS_CERTIFICATE\\" + finyear + "\\Q" + qrtNo + "\\" + tanno + "\\" + getPanno()+"_Q"+qrtNo+ "_" + finyear +".pdf");
                                t = new File(directory + "\\TDS_CERTIFICATE\\" + finyear + "\\Q" + qrtNo + "\\" + tanno + "\\" + getPanno() + "_Q" + qrtNo + "_20" + utl.ChangeAccYearToAssessmentAccYear(finyear) + ".pdf"
                                );
                               // System.out.println("pathhh->"+t.getAbsolutePath());
                                if (t.exists() && t.isFile()) {
                                    utl.generateLog("File Found path : ", t.getAbsolutePath());
//					log.info("File path : " + t.getAbsolutePath());
                                    list.add(t.getAbsolutePath());
                                }
                            }

                        }

                        //sbi trs logic end***************** 
                    } else {
                        utl.generateLog("TDS CErtificate using Query Method", "");
                        String query = new DownloadTdsCertificateDB().getForm16PanPdfQuery(client, asmt, getPanno());
                        utl.generateSpecialLog("Pan Number Query is--->", query);
                        DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                        list = db.getResultAsList(query);
                    }
//                    List<String> list = new ArrayList<String>();
//                    list.add("H:\\FVU_RELATED_FILES\\LHS\\18-19\\Q1\\26Q\\23765204.err");
////                    list.add("H:\\FVU_RELATED_FILES\\LHS\\18-19\\Q1\\26Q\\23765204err.html");

                    if (list != null && list.size() > 0) {

                        if (list.size() == 1) {

                            fileNamePath = list.get(0);
                            if (!utl.isnull(getDownloadType()) && getDownloadType().equalsIgnoreCase("F")) {

                                File f = new File(fileNamePath);
                                fileInputStream = new FileInputStream(f);
                                fileNameToDownload = f.getName();
                                returnType = "download";

                            }

                        } else {
                            try {
                                utl.generateLog(list.get(0), "");
                                File checkFile = new File(list.get(0));
                                if (checkFile.exists()) {
                                    byte[] zip = zipFiles(list);
                                    fileInputStream = new ByteArrayInputStream(zip);
                                    fileNameToDownload = getPanno() + ".zip";
                                    returnType = "download";
                                } else {
                                    returnType = "samePage";
                                    setErrorMessage("File Not Found Please Try again.");
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                returnType = "samePage";
                                setErrorMessage("File Not Found Please Try again.");

                            }
                        }

                    }

                } else if (getFlag().equalsIgnoreCase("T")) {
                    if (!utl.isnull(getDownloadType()) && getDownloadType().equalsIgnoreCase("F")) {
                        utl.generateLog("TDS CErtificate(Tanno) using File System Method", "");
                        String query = "select t.TDS_CERTIFICATE_DIR from view_acc_year t where t.acc_year='" + finyear + "'";

                        DbFunctionExecutorAsString exe = new DbFunctionExecutorAsString();
                        String directory = exe.execute_oracle_function_as_string(query);
                        fileNamePath = directory + "\\TDS_CERTIFICATE\\CLIENT_DATA\\" + finyear + "\\Q" + qrtNo + "\\" + client.getBank_branch_code() + ".ZIP";
                        utl.generateLog("filenamePath---------", fileNamePath);
                    } else {
                        utl.generateLog("TDS CErtificate(Tanno) using Query Method", "");
                        String query = new DownloadTdsCertificateDB().getSingleForm16Query(client, asmt, getTanno());
                        DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                        fileNamePath = db.executeAsList(query);
//                  fileNamePath = "H:\\FVU_RELATED_FILES\\LHS\\18-19\\Q1\\26Q\\Q126QC.rtf";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnType;
    }

    private byte[] zipFiles(List<String> files) {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = null;
        try {
            int count = 0;
            baos = new ByteArrayOutputStream();

            ZipOutputStream zos = new ZipOutputStream(baos);
            for (String file : files) {
                if (!utl.isnull(file)) {
                    File f1 = new File(file);
                    if (f1.exists() && f1.isFile()) {
                        count++;
                        ZipEntry ze = new ZipEntry(count + "_" + f1.getName());
                        zos.putNextEntry(ze);
                        FileInputStream in = new FileInputStream(file);
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        in.close();
                    }
                }
            }
            zos.closeEntry();
            zos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return baos.toByteArray();
    }

    private List<String> readTannoList(String path) {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(path);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String st;
                while ((st = br.readLine()) != null) {
                    //  utl.generateLog(st);
                    list.add(st);
                }
            } else {
                utl.generateLog("Tanno List File not found!", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private Map<String, Object> session;
    final Util utl;
    private String fileNamePath;
    private String tanno;
    private String panno;
    private String flag;
    private String fileNameToDownload;
    private String errorMessage;
    InputStream fileInputStream;
    private String downloadType;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public DownloadFileUsingTanOrPanno() {
        utl = new Util();
    }

    public String getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getPanno() {
        return panno.toUpperCase();
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

}//end class
