/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcPanMastUnverifyRebuild;
import dao.DbProc.ProcPanUnverifiedTxt;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.PanMastFileUploadTran;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class PanVerificationForTexFileAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        String l_return_message = "";
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user = (UserMast) session.get("LOGINUSER");
            String entity_code = client.getEntity_code();
            String client_code = client.getClient_code();
            int quarter_no = Integer.valueOf(asmt.getQuarterNo());
            String acc_year = asmt.getAccYear();
            String asmt_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
            String tds_type_code = asmt.getTdsTypeCode();
            Date from_date = asmt.getQuarterFromDate();
            Date to_date = asmt.getQuarterToDate();
            String user_code = user.getUser_code();

            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");

            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }

            if (!utl.isnull(getFlag()) && getFlag().equalsIgnoreCase("true")) {
                if (client != null) {

                    if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("textfile")) {

                        String proc_token = "";

                        try {
                            PanMastFileUploadTran panobj = new PanMastFileUploadTran();
                            proc_token = new GetTokenTransactions().generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "",
                                    "PA", "", "PAN_UNVERIFIED_DOWNLOAD", "PROCESS_INSERT", "PAN UNVERIFIED DOWNLOAD", "");

                            if (!utl.isnull(proc_token)) {
                                try {
                                    l_return_message = "success" + '#' + Long.parseLong(proc_token) + '#' + getUnverifiedPanCount();
//                                    ProcPanMastVerifiedBulk panVerified = new ProcPanMastVerifiedBulk();
//                                    panVerified.executeProcedure(entity_code, client_code, acc_year, ass_acc_year,quarter_no , from_date, to_date, tds_type_code, l_client_loginid_seq, "PAN_UNVERIFIED_DOWNLOAD", Long.parseLong(proc_token));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                returnView = "ajax_success";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            l_return_message = "Could not Provide Download, Some Error Occured";
                        }
                    }
                }
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("unverifiedPanCountRefresh")) {
                try {
                    ProcPanMastUnverifyRebuild procPanMastUnverifyRebuild = new ProcPanMastUnverifyRebuild();
                    // wait for out paramters
                    int procResult = procPanMastUnverifyRebuild.executeProcedure(entity_code, client_code, acc_year,
                            asmt_acc_year, quarter_no, from_date, to_date, tds_type_code, l_client_loginid_seq, "", user_code, null);
                    l_return_message = "success" + '#' + procResult;
                    returnView = "ajax_success";
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));
        return returnView;
    }

    public PanVerificationForTexFileAction() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    private final Util utl;
    private Map<String, Object> session;
    private String flag;
    private String action;
    private Long unverifiedPanCount;
    private InputStream inputStream;

    public String getAction() {
        return action;
    }

    public Long getUnverifiedPanCount() {
        return unverifiedPanCount;
    }

    public void setUnverifiedPanCount(Long unverifiedPanCount) {
        this.unverifiedPanCount = unverifiedPanCount;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
