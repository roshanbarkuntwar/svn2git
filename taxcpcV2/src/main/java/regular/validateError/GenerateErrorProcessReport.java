/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.LoginValidatorSupport;
import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcGenErrorReport;
import dao.DbProc.ProcGenerateTdsTextFile;
import dao.LhssysProcessLogDAO;
import dao.ViewClientMastDAO;
import dao.ViewDisplayReportListDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.LhssysProcessLog;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewErrorProcessReports;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class GenerateErrorProcessReport extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
       String BLOB_FILE_DOWNLOAD_FLAG = (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") != null ? (String) session.get("BLOB_FILE_DOWNLOAD_FLAG") : "";
        String l_return_value = "success";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        Date approveddate = null;
        if (getAction().equalsIgnoreCase("getGeneratedErrorProcessReport")) {
            String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
            
            //System.out.println("oracleDriveName-->"+oracleDriveName);
            //String driveName =oracleDriveName;
            //oracleDriveName +File.separator+"TEXT_FILES";
            ViewDisplayReportListDAO viewDisplayReportListDAO = factory.getViewDisplayReportListDAO();
            Long process_seqnoo = Long.parseLong(process_seqno);
            System.out.println("process_seqnoo-->"+process_seqnoo);
            List<ViewErrorProcessReports> ViewDisplayReportList = viewDisplayReportListDAO.getViewDisplayReportList(process_seqnoo);

            if (ViewDisplayReportList != null) {
                sb.append("<div class=\"card border-secondary error-details-section mb-3 mt-2\">\n"
                        + "<div class=\"card-header collapse-header\" >\n"
                        + "<h5 class=\"d-inline-block align-top\" >\n"
                        + "Generated Error Process Report\n"
                        + " <span class=\"fa fa-angle-down collapse-icon\" data-toggle=\"collapse\" href=\"#collapseOne\" aria-expanded=\"false\"></span>\n"
                        + "<!--<button type=\"button\" class=\"btn btn-sm bulk-review position-absolute rounded-0 fa fa-angle-down\"></button>-->\n"
                        + "<!--<span class=\"fa fa-angle-down collapse-icon\"></span>-->\n"
                        + "</h5>\n"
                        + "</div>\n"
                        + "<div id=\"collapseOne\" class=\"collapse\">\n"
                        + "<div class=\"card-body\">\n"
                        + "<div class=\"table-responsive mt-2\">");
                sb.append("<table class=\"table table-bordered table-striped mb-1\">");
                sb.append("<thead>\n"
                        + "<tr>\n"
                        + "<th class=\"seq-no\">Sr No.</th>\n"
                        + "<th>Name</th>\n"
                        + "<th>Action</th>\n"
                        + "</tr>\n"
                        + "</thead>");
                sb.append("<tbody>");
                int count = 1;
                for (ViewErrorProcessReports obj : ViewDisplayReportList) {

                    String filename = oracleDriveName + File.separator + "TEXT_FILES" + File.separator + obj.getFvu_txt_file_name();
                    sb.append("<tr>");
                    sb.append("<td>").append(count).append("</td>");
                    sb.append("<td>").append(obj.getFvu_txt_file_name() != null ? obj.getFvu_txt_file_name() : "").append("</td>");
                    sb.append("<td>").append("<a><i class=\"fa download text-primary cursor-pointer mr-1\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" onclick=\"downloadReportSetting(" + count + ",'"+BLOB_FILE_DOWNLOAD_FLAG+"');\" title=\"Download\"></i></a>").append("</td>");
                    sb.append("</tr>");
                    sb.append("<input type=\"hidden\"  id=\"reportfilename~" + count + "\" value=\"" + obj.getFvu_txt_file_name() + "\"/>");
                    sb.append("<input type=\"hidden\"  id=\"reportfilelocation~" + count + "\" value=\"" + filename + "\"/>");
                    sb.append("<input type=\"hidden\"  id=\"process_seqno~" + count + "\" value=\"" + obj.getProcess_seqno() + "\"/>");
                    sb.append("<input type=\"hidden\"  id=\"parent_process_seqno~" + count + "\" value=\"" + obj.getParent_process_seqno() + "\"/>");

                    count++;
                }
                sb.append("</tbody>");
                sb.append("</table>");
                sb.append("</div>");
                sb.append("</div>");
                sb.append("</div>");
                sb.append("</div>");

            } else {
                System.out.println("NO RECORD FOUND IN View_Error_Process_Reports");
                sb.append("<h5>NO RECORD FOUND</h5>");
            }

        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;

    }//end method

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String month;
    private String upload_purpose;
    private String file_error_type;
    private String deductee_code;
    private String process_level;
    private Long rowid_seq;
    private String process_seqno;

    public GenerateErrorProcessReport() {
        utl = new Util();
    }//end constructor

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUpload_purpose() {
        return upload_purpose;
    }

    public void setUpload_purpose(String upload_purpose) {
        this.upload_purpose = upload_purpose;
    }

    public String getFile_error_type() {
        return file_error_type;
    }

    public void setFile_error_type(String file_error_type) {
        this.file_error_type = file_error_type;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getProcess_level() {
        return process_level;
    }

    public void setProcess_level(String process_level) {
        this.process_level = process_level;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

}
