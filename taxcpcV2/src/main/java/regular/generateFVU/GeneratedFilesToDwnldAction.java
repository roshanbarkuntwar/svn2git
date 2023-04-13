/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysFileTranDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author bhawna.agrawal
 */
public class GeneratedFilesToDwnldAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        utl.generateLog("GeneratedFilesToDwnldAction...", "");
        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                int quarter_no = 0;
                String acc_year = asmt.getAccYear();
                String quarterNo = asmt.getQuarterNo();

                String tds_type_code = asmt.getTdsTypeCode();
                Date from_date = asmt.getQuarterFromDate();
                Date to_date = asmt.getQuarterToDate();
                setGenFileDwnldBtnText("Download Error Files");
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

                String generatedFileLoc = javaDriveName + File.separator + "FVU_RELATED_FILES" + File.separator + client.getClient_code() + File.separator + acc_year + File.separator + "Q" + quarterNo + File.separator + tds_type_code;//remove zero
                setZipFileLoc(generatedFileLoc);
                setTextFileLoc(generatedFileLoc);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String fileDateFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'TXT_FILE_NAME_DATE') from dual";
//                String execute_oracle_function_for_date = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileDateFunction);
                String fileSeqNoFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'FILE_SEQNO') from dual";
                String execute_oracle_function_as_string = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileSeqNoFunction);
                if (!utl.isnull(execute_oracle_function_as_string)) {

                    LhssysFileTranDAO lhssys_file_tran_list = factory.getLhssysFileTranDAO();
                    LhssysFileTran readDataByFileSeqno = lhssys_file_tran_list.readDataByFileSeqno(execute_oracle_function_as_string);
                    if (readDataByFileSeqno != null) {
                        LhssysFileTran tran = readDataByFileSeqno;
                        setTextFileName(tran.getFile_name());
                        if (!utl.isnull(tran.getFvu_file_name_str())) {
                            String fvu_file_name_str = tran.getFvu_file_name_str();
                            String[] split = fvu_file_name_str.split("#");
                            for (String stringVal : split) {
                                if (stringVal.startsWith(client.getTanno()) && stringVal.endsWith(".zip")) {
                                    setZipFileName(stringVal);
                                    // set zip file path
                                }
                                if (stringVal.endsWith(".pdf")) {
                                    setGenFileDwnldBtnText("Download All FVU Related Files");
                                }
                                if (stringVal.endsWith(".fvu.zip")) {
                                    setFvuZipFileName(stringVal);
                                }
                            }
                        }
                    }
                }
            }
        }
        utl.generateLog("zipFileName...", getFvuZipFileName());
        return ActionSupport.SUCCESS;
    }

    public GeneratedFilesToDwnldAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    final Util utl;
    String zipFileLoc;
    String textFileLoc;
    String zipFileName;
    String textFileName;
    String genFileDwnldBtnText;
    private String fvuZipFileName;// file absolute path of fvu file

    public String getFvuZipFileName() {
        return fvuZipFileName;
    }

    public void setFvuZipFileName(String fvuZipFileName) {
        this.fvuZipFileName = fvuZipFileName;
    }

    public String getGenFileDwnldBtnText() {
        return genFileDwnldBtnText;
    }

    public void setGenFileDwnldBtnText(String genFileDwnldBtnText) {
        this.genFileDwnldBtnText = genFileDwnldBtnText;
    }

    public String getZipFileLoc() {
        return zipFileLoc;
    }

    public void setZipFileLoc(String zipFileLoc) {
        this.zipFileLoc = zipFileLoc;
    }

    public String getTextFileLoc() {
        return textFileLoc;
    }

    public void setTextFileLoc(String textFileLoc) {
        this.textFileLoc = textFileLoc;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getTextFileName() {
        return textFileName;
    }

    public void setTextFileName(String textFileName) {
        this.textFileName = textFileName;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
