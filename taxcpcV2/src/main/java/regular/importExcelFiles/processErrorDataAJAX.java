/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhsTemplateError;
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
public class processErrorDataAJAX extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private Long importSeqNo;
    private String templateCode;

    public processErrorDataAJAX() {
        utl = new Util();
    }//endconstructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "success";

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");
        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
        String l_entity_code = viewClientMast.getEntity_code();
        String l_client_code = viewClientMast.getClient_code();

        String templeteTypeCode = !utl.isnull(getTemplateCode()) ? getTemplateCode() : "TAXCPC01";

        try {//procedure used to check error

//            System.out.println("getImportSeqNo.." + getImportSeqNo());
//            ProcLhssysProcessLogIud proc = new ProcLhssysProcessLogIud();
//            String procResult = proc.executeProcedure(null, viewClientMast.getEntity_code(), viewClientMast.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), getImportSeqNo(), null, null, null, null, null, null, null, null, null, null, "TEMPLATE_INSERT", user.getUser_code(), "PROCESS_ERROR", templeteTypeCode);
//            System.out.println("procResult--" + procResult);

//            if (!utl.isnull(procResult) && !procResult.equalsIgnoreCase("-1")) {
                GetTokenTransactions gt = new GetTokenTransactions();
                TokenStatusAttribs tokenSatus = gt.getTokenSatus(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), asmt, user.getUser_code(), "TEMPLATE_INSERT", getImportSeqNo());
                ProcLhsTemplateError objProcLhsTemplateError = new ProcLhsTemplateError();

                int temperr_result = objProcLhsTemplateError.execute_procedure(l_entity_code, l_client_code, asmt.getAccYear(), Integer.parseInt(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), templeteTypeCode, getImportSeqNo(), user.getUser_code(),utl.isnull(tokenSatus.getTokenNo()) ? 0 : Long.parseLong(tokenSatus.getTokenNo()),null);
                utl.generateSpecialLog("RIM-0001 (Result of Template Error Procedure)----", temperr_result);

//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
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

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end 

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

}//end class
