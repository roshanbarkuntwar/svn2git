package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class DownloadReferenceNoDetailTemplate extends ActionSupport implements SessionAware {

    public DownloadReferenceNoDetailTemplate() {
        utl = new Util();
        dateTimeUtl = new DateTimeUtil();
    }

    @Override
    public String execute() throws Exception {

        String return_value = "input";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

        try {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String tds_type_code = asmt.getTdsTypeCode();
            String acc_year = asmt.getAccYear();
            String fileLocation = javaDriveName + "\\TAXCPC\\DOWNLOAD_FORMATS\\Deductee_15GH_ReferenceNo.xls";
//            String fileLocation = null;
            String file_ext = (String) session.get("EXCELFORMAT");

//            System.out.println("Template code:\t" + getTempleteCode());
//            System.out.println("Gen Client code:\t" + getGenClientCode());
//            ViewDeducteeTemplateDAO viewDeducteeTemplate = factory.getViewDeducteeTemplateDAO();
//            ViewClientTemplate deducteetemp = viewDeducteeTemplate.getDataAsTempleteCode(getTempleteCode());
//
//            if (deducteetemp != null) {
//                fileLocation = deducteetemp.getFile_location();
//            }
            if (!utl.isnull(fileLocation)) {

                ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//
                String entity_code = viewClientMast.getEntity_code();
                String quarterNo = asmt.getQuarterNo();
                Long client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    client_loginid_seq = 0l;
                }

                String loginSeqno = "0";
                try {
                    loginSeqno = String.valueOf(client_loginid_seq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Form15GHDB form15Gh = new Form15GHDB();
                String gen_client_code = getGenClientCode();

                if (utl.isnull(gen_client_code)) {
                    gen_client_code = viewClientMast.getClient_code();
                }

                String refrenceNoDetails = form15Gh.getCountReferenceNoDetailQuery(entity_code, quarterNo, acc_year, tds_type_code, gen_client_code, getProcessLevel(), getCodeLevel()); // Generate client code
                utl.generateSpecialLog("Reference No details count query", refrenceNoDetails);
                
                long total = 0l;
                DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                total = objDBListCount.execute_oracle_function_as_integar(refrenceNoDetails);
                
                int record_per_sheet = 65500;
                double tot = (float) total / record_per_sheet;
                double sheetNumberDbl = Math.ceil(tot);
                
                int total_page = (int) sheetNumberDbl;
                
                setNo_of_column(8);
                String gen_files = "";

                for (int i = 0; i < total_page; i++) {
                    String savedFilePath = "";
                    savedFilePath = RenameAndSaveUploadedFile(fileLocation, getTempleteCode(), file_ext);
                    if (!utl.isnull(savedFilePath)) {
                        int start_page_no = 0;
                        if (i == 0) {
                            start_page_no = 1;
                        } else {
                            start_page_no = (i + 1);
                        }
                        int maxVal = (start_page_no * record_per_sheet);
                        int minVal = ((start_page_no * record_per_sheet) - record_per_sheet) + 1;

                        Form15GHDB form15GH = new Form15GHDB();
                        String referenceNoDetails = form15GH.getReferenceNoDetails(entity_code, quarterNo, acc_year, tds_type_code, gen_client_code, minVal, maxVal,
                                getProcessLevel(), getCodeLevel());
                        utl.generateSpecialLog("15GH-DE-0005 (Download Referance No Detail in Template)--480--", referenceNoDetails);

                        DbFunctionExecutorAsString objData = new DbFunctionExecutorAsString();
                        ArrayList<ArrayList<String>> tempDataRecordList = objData.execute_oracle_query_as_list(referenceNoDetails);

                        String filename = ("Deductee_RefranceNo_Detail_").concat(tds_type_code).concat(asmt.getQuarterNo()).concat(loginSeqno);
                        String file_dwl_time = dateTimeUtl.get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");
                        String real_filename = filename + "_" + i + "_" + file_dwl_time + "." + file_ext;
                        setFileName(real_filename);//User File Name .

                        boolean savedFile = new FileOptUtil().writeDataInExistingExcel(getNo_of_column(), savedFilePath, tempDataRecordList);
                        if (savedFile) {
                            File obj_dwl_file = new File(savedFilePath);
                            if (obj_dwl_file.exists()) {
                                gen_files += savedFilePath + "#";
                            }
                        } else {
                            return_value = "input";
                            //session.put("ERRORCLASS", ErrorType.errorMessage);
                            //  session.put("session_result", "Some Error Occured, Could Not Download File2");
                        }
                    } else {
                        return_value = "input";
                        //session.put("ERRORCLASS", ErrorType.errorMessage);
                        // session.put("session_result", "Some Error Occured, Could Not Download File1");
                    }

                }

                try {
                    if (!utl.isnull(gen_files)) {
                        String[] l_files_list = new String[0];
                        if (gen_files.contains("#")) {
                            l_files_list = gen_files.split("#");
                        }

                        if (l_files_list.length > 1) {
                            //  zipFileConvertAction zipfileobj = new zipFileConvertAction();
                            //   byte[] zip = zipfileobj.getZipFileData(l_files_list);
                            // fileInputStream = new ByteArrayInputStream(zip);
                            String filename = ("Deductee_RefranceNo_Detail_").concat(tds_type_code).concat(asmt.getQuarterNo()).concat(loginSeqno);
                            // String file_dwl_time = utl.get_sysdate("DD_dd_MM_yyyy_hh_mm_ss_SSS");

                            //  String real_filename = filename + "_" + file_dwl_time + ".zip";
                            // setFileName(real_filename);//User File Name 
                            return_value = "downloadtempsuccess";
                        } else if (!utl.isnull(l_files_list[0])) {
                            File obj_dwl_file = new File(l_files_list[0]);
                            if (obj_dwl_file.exists()) {
                                fileInputStream = new FileInputStream(obj_dwl_file);
                                return_value = "singledownloadtemp";
                            } else {
                                return_value = "input";
                                //    session.put("ERRORCLASS", ErrorType.errorMessage);
                                session.put("session_result", "Some Error Occured, Could Not Download File3");
                            }
                        } else {
                            return_value = "input";
                            // session.put("ERRORCLASS", ErrorType.errorMessage);
                            session.put("session_result", "Some Error Occured, Could Not Download File4");
                        }
                    } else {
                        return_value = "input";
                        //session.put("ERRORCLASS", ErrorType.errorMessage);
                        session.put("session_result", "Some Error Occured, Could Not Download File5");
                    }
                } catch (Exception e) {

                }

            } else {
                return_value = "input";
                //session.put("ERRORCLASS", ErrorType.errorMessage);
                // session.put("session_result", "Some Error Occured, Could Not Download File6");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_value; //To change body of generated methods, choose Tools | Templates.
    }

    private String RenameAndSaveUploadedFile(String fileLocation, String templeteCode, String file_ext) {
        String filePath;
        try {
            String file_name = templeteCode + file_ext;
            file_name = ((ViewClientMast) session.get("WORKINGUSER")).getClient_code() + dateTimeUtl.get_sysdate("dd_MM_yyyy_hh_mm_ss_S") + file_name;
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

            String destPath = javaDriveName + "TAXCPC/UPLOADED_EXCEL/";
            utl.generateLog("destPath--", destPath);
            File source = new File(fileLocation);
            File destFile = new File(destPath, file_name);
            FileUtils.copyFile(source, destFile);
            filePath = destFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            filePath = null;
        }
        return filePath;
    }

    Map<String, Object> session;
    private Util utl;
    private String GenClientCode;
    private int no_of_column;
    private String templeteCode;
    private DateTimeUtil dateTimeUtl;
    private String fileName;
    private int processLevel;
    private int codeLevel;
    private InputStream fileInputStream;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public int getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(int processLevel) {
        this.processLevel = processLevel;
    }

    public int getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(int codeLevel) {
        this.codeLevel = codeLevel;
    }

    

    public String getGenClientCode() {
        return GenClientCode;
    }

    public void setGenClientCode(String GenClientCode) {
        this.GenClientCode = GenClientCode;
    }

    public int getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(int no_of_column) {
        this.no_of_column = no_of_column;
    }

    public String getTempleteCode() {
        return templeteCode;
    }

    public void setTempleteCode(String templeteCode) {
        this.templeteCode = templeteCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

}
