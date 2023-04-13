/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.globalDBObjects;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import dao.DbProc.ProcLhssysProcessLogIud;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.Date;

/**
 *
 * @author ayushi.jain
 */
public class GetTokenTransactions {

    public TokenStatusAttribs getTokenSatus(String entityCode, String clientCode, Assessment asmt, String userCode, String processType, Long loginSessionId) {
        TokenStatusAttribs token = new TokenStatusAttribs();

        String query = "select lhs_utility.get_process_seqno('" + entityCode + "' ,\n"
                + "                                     '" + clientCode + "',\n"
                + "                                     '" + asmt.getAccYear() + "' ,\n"
                + "                                     " + asmt.getQuarterNo() + " ,\n"
                + "                                     null ,\n"
                + "                                     null ,\n"
                + "                                     '" + asmt.getTdsTypeCode() + "',\n"
                + "                                     " + loginSessionId + " ,\n"
                + "                                     null ,\n"
                + "                                     '" + processType + "' ,\n" //TEMPLATE_INSERT
                + "                                     '" + userCode + "',\n"
                + "                                     'PROCESS_STATUS_STR' )\n"
                + "  FROM DUAL";

        utl.generateSpecialLog("Token Status Query", query);

        DbFunctionExecutorAsString exe = new DbFunctionExecutorAsString();
        String result = exe.execute_oracle_function_as_string(query);
        if (!utl.isnull(result)) {
            if (result.contains("#")) {
                try {
                    String arr[] = result.split("#");
                    token.setTokenNo(arr[0]);
                    token.setTemplateCode(arr[1]);
                    token.setTemplateName(arr[2]);
                    token.setProcessStartTimestamp(arr[3]);
                    token.setProcessEndTimestamp(arr[4]);
                    token.setProcessStatus(arr[5]);
                    token.setProcessRemark(arr[6]);
                    token.setProcessType(arr[7]);
                    token.setProcessTypeName(arr[8]);
                    token.setProcBatchFileName(arr[9]);

                } catch (Exception e) {

                }
            }
        }
        return token;
    }

    public String generateTokenGlobalMethod(Assessment asmt, ViewClientMast client, UserMast user, Long l_client_loginid_seq, String login_level_flag,
            String processStatus, String processRemark, String processType, String processIudType, String report_name, String proc_string) {
        String token = "";
        try {
            ProcLhssysProcessLogIud proc = new ProcLhssysProcessLogIud();
            String procResult = proc.executeProcedure(null, client.getEntity_code(), client.getClient_code(), asmt.getAccYear(),
                    asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(),
                    l_client_loginid_seq, null, new Date(), null, processStatus, processRemark, null, null, null, null, null, processType, user.getUser_code(),
                    processIudType, //use for proccess insert in lhssys_process_log indicate process is start
                    null, login_level_flag, report_name, proc_string,"");

            if (!utl.isnull(procResult) && !procResult.equalsIgnoreCase("-1")) {
                GetTokenTransactions gt = new GetTokenTransactions();
                TokenStatusAttribs tokenSatus = gt.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, user.getUser_code(),
                        processType, l_client_loginid_seq);

                if (tokenSatus != null) {
                    token = tokenSatus.getTokenNo();
                } else {
                    token = "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
// Add new parameter for provide template code also
        public String generateImportTokenGlobalMethod(Assessment asmt, ViewClientMast client, UserMast user, Long l_client_loginid_seq, String login_level_flag,
            String processStatus, String processRemark, String processType, String processIudType, String report_name, String proc_string, String templateCode) {
        String token = "";
        try {
            ProcLhssysProcessLogIud proc = new ProcLhssysProcessLogIud();
            String procResult = proc.executeProcedure(null, client.getEntity_code(), client.getClient_code(), asmt.getAccYear(),
                    asmt.getQuarterNo(), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(),
                    l_client_loginid_seq, null, new Date(), null, processStatus, processRemark, null, null, null, null, null, processType, user.getUser_code(),
                    processIudType, //use for proccess insert in lhssys_process_log indicate process is start
                    templateCode, login_level_flag, report_name, proc_string,"");

            if (!utl.isnull(procResult) && !procResult.equalsIgnoreCase("-1")) {
                GetTokenTransactions gt = new GetTokenTransactions();
                TokenStatusAttribs tokenSatus = gt.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, user.getUser_code(),
                        processType, l_client_loginid_seq);

                if (tokenSatus != null) {
                    token = tokenSatus.getTokenNo();
                } else {
                    token = "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    Util utl;

    public GetTokenTransactions() {
        utl = new Util();
    }

}
