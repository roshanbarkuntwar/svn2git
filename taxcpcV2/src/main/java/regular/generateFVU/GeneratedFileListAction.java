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
import globalUtilities.ReadFromConsolidatedFile;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class GeneratedFileListAction extends ActionSupport implements SessionAware, ServletRequestAware {

    @Override
    public String execute() {
        viewFileList = new ArrayList<ViewFileListEntity>();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client = null;
        try {
            client = (ViewClientMast) session.get("WORKINGUSER");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        String generatedFileLoc = "";

        String seqno = "";

        if (client != null) {
            if (asmt != null) {
                int quarter_no = 0;
                String acc_year = asmt.getAccYear();

                String quarterNo = asmt.getQuarterNo();

                String tds_type_code = asmt.getTdsTypeCode();
                Date from_date = asmt.getQuarterFromDate();
                Date to_date = asmt.getQuarterToDate();
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";

                generatedFileLoc = javaDriveName + File.separator + "FVU_RELATED_FILES" + File.separator + client.getClient_code() + File.separator + acc_year + File.separator + "Q" + quarterNo + File.separator + tds_type_code;//remove zero

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String fileDateFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'TXT_FILE_NAME_DATE') from dual";
                String execute_oracle_function_for_date = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileDateFunction);

                String fileSeqNoFunction = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '" + acc_year + "', '" + quarter_no + "',  to_date('" + sdf.format(from_date) + "','dd-mm-rrrr'),  to_date('" + sdf.format(to_date) + "','dd-mm-rrrr'), '" + tds_type_code + "', '', 'LHSSYS_FILE_TRAN', 'FILE_SEQNO') from dual";
                String execute_oracle_function_as_string = new DbFunctionExecutorAsString().execute_oracle_function_as_string(fileSeqNoFunction);
                if (!utl.isnull(execute_oracle_function_as_string)) {

                    LhssysFileTranDAO lhssys_file_tran_list = factory.getLhssysFileTranDAO();
                    LhssysFileTran readDataByFileSeqno = lhssys_file_tran_list.readDataByFileSeqno(execute_oracle_function_as_string);
                    if (readDataByFileSeqno != null) {
                        LhssysFileTran tran = readDataByFileSeqno;
                        if (!utl.isnull(tran.getFvu_file_name_str())) {
                            String fvu_file_name_str = tran.getFvu_file_name_str();
                            String[] split = fvu_file_name_str.split("#");
                            for (String stringVal : split) {
                                ViewFileListEntity entityLst = new ViewFileListEntity();
                                if (stringVal.endsWith(".fvu")
                                        || stringVal.endsWith(".pdf")
                                        || stringVal.endsWith(".html")
                                        || stringVal.endsWith(".fvu.zip")
                                        || stringVal.endsWith("_Electronic_Statement_Warning_File.html")
                                        || (stringVal.endsWith(".txt") && !stringVal.startsWith("error"))) {
                                    entityLst.setFileName(stringVal);
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//                        String format = sdf.format(file.lastModified());
//                        entityLst.setDate(format);
                                    entityLst.setDate(execute_oracle_function_for_date);
                                    entityLst.setHiddenFileLoc(generatedFileLoc);
                                    viewFileList.add(entityLst);
                                }
                                if (stringVal.endsWith(".txt") && !stringVal.startsWith("error")) {

                                    String textFileLoc = generatedFileLoc + File.separator + stringVal;
                                    ReadFromConsolidatedFile consolidatedFile = new ReadFromConsolidatedFile();
                                    Long lineCount = consolidatedFile.getLineCount(textFileLoc, "DD");
                                    setDeducteeCount(lineCount);

                                    Long lineCount1 = consolidatedFile.getLineCount(textFileLoc, "CD");
                                    setChallanCount(lineCount1);
                                }
                            }
                        }
                    }
                }
            }
        }

        utl.generateLog("challan count..", getChallanCount());
        return ActionSupport.SUCCESS;
    }// END EXECUTE METHOD

    public GeneratedFileListAction() {
        utl = new Util();
    }
    public long lastModifiedDate;
    private String fileName;
    private String date;
    private ArrayList<ViewFileListEntity> viewFileList;
    Map<String, Object> session;
    HttpServletRequest servletRequest;
    Util utl;
    private Long deducteeCount;
    private Long challanCount;

    public Long getDeducteeCount() {
        return deducteeCount;
    }

    public void setDeducteeCount(Long deducteeCount) {
        this.deducteeCount = deducteeCount;
    }

    public Long getChallanCount() {
        return challanCount;
    }

    public void setChallanCount(Long challanCount) {
        this.challanCount = challanCount;
    }

    public ArrayList<ViewFileListEntity> getViewFileList() {
        return viewFileList;
    }

    public void setViewFileList(ArrayList<ViewFileListEntity> viewFileList) {
        this.viewFileList = viewFileList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
