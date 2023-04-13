/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
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
 * @author akash.dev
 */
public class SaveDataAsProcedureAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;
    private String action;
    private String ExcFlag;
    private int no_of_column;
    private Long importSeqNo;
    private String templateCode;
    private String processType;

    public SaveDataAsProcedureAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_message = "error#";
        try {
            final ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            final Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
            final UserMast user = (UserMast) session.get("LOGINUSER");//use for procedure
//            String assesment_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }

            GetTokenTransactions gt = new GetTokenTransactions();
            TokenStatusAttribs tokenSatus = gt.getTokenSatus(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), asmt, user.getUser_code(), "TEMPLATE_INSERT", l_client_loginid_seq);
            final Long tokenNo1 = Long.parseLong(!utl.isnull(tokenSatus.getTokenNo()) ? tokenSatus.getTokenNo() : "0");
//            final String processType1 = getProcessType();
//            final Long importSeqNo1 = getImportSeqNo();
//            final String assesment_acc_year1 = assesment_acc_year;
//            Thread task = new Thread() {
//                @Override
//                public void run() {
//                    ProcLhsTemplateInsert objectprocessInsert = new ProcLhsTemplateInsert();
//                    objectprocessInsert.Exceute_Proc_Lhs_Tran_Template_Insert_function(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), asmt.getAccYear(), assesment_acc_year1, Integer.parseInt(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), importSeqNo1, processType1, templateCode, user.getUser_code(), tokenNo1);
//                }
//            };
//            task.start();
            l_message = "save#" + tokenNo1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

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

    public String getExcFlag() {
        return ExcFlag;
    }

    public void setExcFlag(String ExcFlag) {
        this.ExcFlag = ExcFlag;
    }

    public int getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(int no_of_column) {
        this.no_of_column = no_of_column;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

}//end class
