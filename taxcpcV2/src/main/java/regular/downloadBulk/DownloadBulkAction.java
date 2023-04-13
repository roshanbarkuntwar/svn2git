/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadBulk;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhssysProcessLogIud;
import dao.DbProc.ProcTdsDownload;
import dao.DbProc.ProcTranLoadErrorDwnld;
import dao.DbProc.ProcUpdtLhssysProcessLog;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadBulkAction extends ActionSupport implements SessionAware {

    private final Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private FileInputStream fileInputStream;
    private TokenStatusAttribs tokenUploadObj;
    private String token_no;
    private String filter_column_string;
    private String fileNamePath;
    private String fileName;
    private String action;
    private String bulkDownloadFor;
    private String bulk_error_download_flag;
    private Long processLevel;

    public DownloadBulkAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "error";

        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("bulkFileGenerate")) {
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                UserMast user_mast = (UserMast) session.get("LOGINUSER");
                Object sessionId = session.get("LOGINSESSIONID");
                String sessionModule = (String) session.get("MODULE");

                Long l_client_loginid_seq;

                if (sessionId != null) {
                    l_client_loginid_seq = (Long) sessionId;
                } else {
                    l_client_loginid_seq = 0l;
                }

                if (client != null && asmt != null) {
                    String entity_code = client.getEntity_code();
                    String client_code = client.getClient_code();
                    String acc_year = asmt.getAccYear();
                    String asmt_acc_year = utl.ChangeAccYearToAssessmentAccYear(acc_year);
                    String quarter_no = asmt.getQuarterNo();
                    String tds_type_code = asmt.getTdsTypeCode();
                    Date from_date = asmt.getQuarterFromDate();
                    Date to_date = asmt.getQuarterToDate();

                    try {
                        String tokenProcessType = "PROCESS_DOWNLOAD";
                        if ("G".equals(sessionModule) && "15GH_TRANSACTION".equals(getBulkDownloadFor())) {
                            tokenProcessType = "15GH_PROCESS_DOWNLOAD";
                        }
                        //Calling token generation procedure for bulk download.
                        String tokenResult = this.generateTokenForBulkDownload(entity_code, client_code, acc_year, quarter_no, asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                                tds_type_code, l_client_loginid_seq, user_mast.getUser_code(), tokenProcessType);

                        utl.generateLog("bulk download token generate status", tokenResult);

                        //Calling token status procedure for bulk download.
                        GetTokenTransactions gtt = new GetTokenTransactions();
                        tokenUploadObj = gtt.getTokenSatus(entity_code, client_code, asmt, user_mast.getUser_code(), tokenProcessType, l_client_loginid_seq);
                        utl.generateLog("bulk download token status ", tokenUploadObj);

                        if (tokenUploadObj != null) {
                            int proc_type = !utl.isnull(tokenUploadObj.getProcessType()) ? Integer.parseInt(tokenUploadObj.getProcessType()) : 0;
                            if (proc_type == 0) {
                                tokenUploadObj.setProcessTypeName("Login Level");
                            } else {
                                tokenUploadObj.setProcessTypeName("All (Login & Branch Level)");
                            }
                            token_no = tokenUploadObj.getTokenNo();
                            utl.generateLog("Token no for bulk download generated", token_no);
                        }

                        if (!utl.isnull(token_no)) {
                            return_msg = "success#" + token_no;
                            String column_value = "";
                            try {
                                column_value = !utl.isnull(filter_column_string) ? filter_column_string.replaceAll("~~", "#") : "";
                            } catch (Exception e) {
                                column_value = "";
                            }


                            if (!utl.isnull(getBulk_error_download_flag()) && getBulk_error_download_flag().equalsIgnoreCase("error_download")) {
                                try {
                                    String a_download_type = "DA";

                                    if ("4".equals(asmt.getQuarterNo()) && "24Q".equals(asmt.getTdsTypeCode())) {
                                        a_download_type = "SALARY_TRAN_ERROR";
                                    }

                                    ProcTranLoadErrorDwnld proc_err_dwnld = new ProcTranLoadErrorDwnld();
                                    String proc_err_dwnld_status = proc_err_dwnld.executeProcedure(entity_code, client_code, acc_year, asmt_acc_year, quarter_no, from_date, to_date, tds_type_code,
                                            l_client_loginid_seq, getProcessLevel(),
                                            a_download_type, // Process type
                                            getBulkDownloadFor(), user_mast.getUser_code(), Long.parseLong(token_no));

                                    utl.generateLog("Error download proc status", proc_err_dwnld_status);
                                } catch (Exception e) {
                                    return_msg = "error";
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    //Calling download initialization procedure for bulk download.
                                    ProcUpdtLhssysProcessLog proc_updt = new ProcUpdtLhssysProcessLog();
                                    proc_updt.executeProcedure(Long.parseLong(token_no), "FILTER_VALUES", column_value);

                                    //Calling procedure for bulk download.
                                    ProcTdsDownload proc_tds_download = new ProcTdsDownload();
                                    proc_tds_download.executeProcedure(entity_code, client_code, acc_year, asmt_acc_year, quarter_no, asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                                            tds_type_code, l_client_loginid_seq, Long.valueOf(client.getCode_level()), getBulkDownloadFor(), user_mast.getUser_code(), Long.parseLong(token_no));
                                } catch (Exception e) {
                                    return_msg = "error";
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e) {
                        return_msg = "error";
                        e.printStackTrace();
                    }
                }
                inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));

            } else if (getAction().equalsIgnoreCase("bulkFileDownload")) {
                return_view = "input";

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                LhssysParameters lhssysParameters = factory.getLhssysParametersDAO().readParametersBy("ORACLE_DRIVE_NAME");
                String oracle_drive_name = "";
                if (lhssysParameters != null) {
                    oracle_drive_name = lhssysParameters.getParameter_value();
                }

                if (!utl.isnull(oracle_drive_name)) {
                    if (!utl.isnull(getFileName())) {
                        String bulk_file_name = getFileName();

                        //Oracle path for text_files
                        String bulk_file_path = oracle_drive_name + File.separator + "TEXT_FILES" + File.separator;

                        if (bulk_file_name.contains("#")) {// For multiple file download
                            try {

                                bulk_file_name = this.validateFileName(bulk_file_name);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //Oracle path for bat file
                            String bulk_file_path_multiple = oracle_drive_name + File.separator + "BLK_DWNLD_MULTIPLE" + File.separator;
                            Path bulkDownloadPath = Paths.get(bulk_file_path_multiple);
                            if (!Files.exists(bulkDownloadPath)) {
                                try {
                                    Files.createDirectory(bulkDownloadPath);
                                } catch (Exception e) {
                                }
                            }

                            // Creating bat file for copying file via windows cmd ---
                            StringBuilder proc_exec_text = new StringBuilder("cd\\ \n").append(oracle_drive_name);
                            proc_exec_text.append("\ncd ").append(bulk_file_path).append("\n");
                            proc_exec_text.append("copy ");
                            proc_exec_text.append(bulk_file_name.replaceAll("#", "+")).append(" bulk_dwnld_").append(getToken_no()).append(".txt");

                            File bulk_dwnld_bat = new File(bulk_file_path_multiple + "bulk_dwnld_bat.bat");
                            try (FileOutputStream fout = new FileOutputStream(bulk_dwnld_bat);) {
                                fout.write(proc_exec_text.toString().getBytes());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //----------

                            // Executing bat file for copying file via windows cmd ---
                            try {
                                Runtime.getRuntime().exec("cmd /c \"" + bulk_file_path_multiple + "bulk_dwnld_bat.bat\"");
                                Thread.sleep(1000);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //----------

                            // Setting file name & path for final download ---
                            try {
                                bulk_file_name = "bulk_dwnld_" + getToken_no() + ".txt";
                                bulk_file_path = bulk_file_path + bulk_file_name;

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //----------
                        } else {// For single file download
                            bulk_file_path = bulk_file_path + bulk_file_name;
                        }

                        try {
                            setFileName(bulk_file_name);
                            fileInputStream = new FileInputStream(bulk_file_path);
                            return_view = "bulkExcel";
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        session.put("DWNLDSESSIONRESULT", "File not found!");
                        session.put("ERRORCLASS", ErrorType.errorMessage);
                    }
                } else {
                    session.put("DWNLDSESSIONRESULT", "Oracle Drive not found!");
                    session.put("ERRORCLASS", ErrorType.errorMessage);
                }
            }
        }
        return return_view;
    }//end method

    public String generateTokenForBulkDownload(String entity_code, String client_code, String acc_year, String quarter_no, Date from_date,
            Date to_date, String tds_type_code, Long l_client_loginid_seq, String user_code, String processType) {

        String tokenResult = "";
        try {
            ProcLhssysProcessLogIud proc_log = new ProcLhssysProcessLogIud();
            tokenResult = proc_log.executeProcedure(
                    null,// Process seq no
                    entity_code, //entity code
                    client_code, //client code
                    acc_year, //acc year
                    quarter_no, //quarter no
                    from_date, to_date, // from date, to date
                    tds_type_code, //tds type code
                    l_client_loginid_seq, // client login seq
                    null,
                    null, null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    processType, // Process type
                    user_code, //User code
                    "PROCESS_INSERT", //IUD type
                    null, // Template code
                    null, "", "",""); // Process type
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenResult;
    }//end method

    private String validateFileName(String bulk_file_name) {

        String return_bulk_file_name = "";

        return_bulk_file_name = (bulk_file_name.charAt(0) == '#') ? bulk_file_name.substring(1, bulk_file_name.length()) : bulk_file_name;
        return_bulk_file_name = (return_bulk_file_name.charAt(return_bulk_file_name.length() - 1) == '#') ? return_bulk_file_name.substring(0, return_bulk_file_name.length() - 2) : return_bulk_file_name;

        return return_bulk_file_name;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getBulk_error_download_flag() {
        return bulk_error_download_flag;
    }

    public void setBulk_error_download_flag(String bulk_error_download_flag) {
        this.bulk_error_download_flag = bulk_error_download_flag;
    }

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilter_column_string() {
        return filter_column_string;
    }

    public void setFilter_column_string(String filter_column_string) {
        this.filter_column_string = filter_column_string;
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Long getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(Long processLevel) {
        this.processLevel = processLevel;
    }

}//end class
