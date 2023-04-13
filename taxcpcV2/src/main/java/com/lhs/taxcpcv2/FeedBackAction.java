/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class FeedBackAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {
        String return_type = "success";
        ViewClientMast client = null;
        try {
            client = (ViewClientMast) session.get("WORKINGUSER");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (client != null) {
                UserMast user = (UserMast) session.get("LOGINUSER");
                Long clientLoginSessionSeqno;
                Object sessionId = session.get("LOGINSESSIONID");
                clientLoginSessionSeqno = (Long) sessionId;
                DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
                if (!utl.isnull(getRemark())) {
                    String insertQuery = "INSERT INTO  USER_FEEDBACK(ENTITY_CODE,CLIENT_CODE,USER_CODE,CLIENT_LOGIN_SESSION_SEQNO,REMARK_AT,LASTUPDATE,FLAG) "
                            + "VALUES('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + user.getUser_code() + "','"
                            + clientLoginSessionSeqno + "','" + remark + "',sysdate,'')";
                    dbFunctionExecutorAsString.execute_oracle_update_function_as_string(insertQuery);

                }
            }
        } catch (Exception e) {
        }
        inputStream = new ByteArrayInputStream(return_type.getBytes("UTF-8"));
        return return_type;

    }

    public FeedBackAction() {
        utl = new Util();
    }

    private final globalUtilities.Util utl;
    private Map<String, Object> session;
    private String remark;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
