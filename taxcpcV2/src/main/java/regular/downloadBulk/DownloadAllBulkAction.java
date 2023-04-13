/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadBulk;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsTranDwnld;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DownloadAllBulkAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private InputStream inputStream;

    private TokenStatusAttribs tokenUploadObj;
    private String token_no;
    private String action;

    public DownloadAllBulkAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_msg = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user_mast = (UserMast) session.get("LOGINUSER");
        Object sessionId = session.get("LOGINSESSIONID");

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

            try {
                //Calling token generation procedure for bulk download.
                BulkDownloadSupport bs = new BulkDownloadSupport();
                String tokenResult = bs.generateTokenForBulkDownload(entity_code, client_code, acc_year, quarter_no, asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                        tds_type_code, l_client_loginid_seq, user_mast.getUser_code());

                GetTokenTransactions gtt = new GetTokenTransactions();
                tokenUploadObj = gtt.getTokenSatus(entity_code, client_code, asmt, user_mast.getUser_code(), "PROCESS_DOWNLOAD", l_client_loginid_seq);
                utl.generateLog("bulk download token status ", tokenUploadObj);

                if (tokenUploadObj != null) {
                    token_no = tokenUploadObj.getTokenNo();
                    utl.generateLog("Token no for bulk download generated", token_no);
                }

                if (!utl.isnull(token_no)) {
                    if (getAction().equalsIgnoreCase("tranBulkDownload")) {
                        try {
                            //Calling bulk download procedure for bulk download.
                            ProcTdsTranDwnld proc_tds_download = new ProcTdsTranDwnld();
                            proc_tds_download.executeProcedure(entity_code, client_code, acc_year, asmt_acc_year, quarter_no, asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                                    tds_type_code, l_client_loginid_seq, "TDS_CHALLANS", user_mast.getUser_code(), Long.parseLong(token_no));

                            return_msg = "success";
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
        return return_view;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }
}//end class
