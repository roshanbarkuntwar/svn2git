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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class BaseLayoutAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnValue = "success";
        String returnMsg = "";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        try {
            UserMast userMast = (UserMast) session.get("LOGINUSER");
            String query = "select client_code||'~'||nvl(client_name,'-')||'> '||nvl(tanno,'-')||'> '||nvl(bank_branch_code,'-')\n"
                    + "from   client_mast\n"
                    + "where  entity_code='" + userMast.getEntity_code() + "'\n"
                    + "and    (client_code='" + userMast.getClient_code() + "' or parent_code='" + userMast.getClient_code() + "' or\n"
                    + "        g_parent_code='" + userMast.getClient_code() + "' or sg_parent_code='" + userMast.getClient_code() + "' or\n"
                    + "        ssg_parent_code='" + userMast.getClient_code() + "' or sssg_parent_code='" + userMast.getClient_code() + "'\n"
                    + "        )\n"
                    + "and upper(bank_branch_code||client_name||tanno||client_code) LIKE UPPER('%" + (utl.isnull(getTerm()) ? "" : getTerm()) + "%')"
                    + "order by bank_branch_code ";
            utl.generateSpecialLog("Client-group-Query", query);
            DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
            ArrayList<String> list = db.getResultAsList(query);
            if (list != null && list.size() > 0) {
                for (String string : list) {
                    if (string.contains("~")) {
                        count++;
                        String arr[] = string.split("~");
                        sb.append("<input type=\"hidden\"  id=\"hiddenVal~" + count + "\" value=\"" + arr[0] + "\"/>");
                        sb.append("<li class=\"list-group-item\" id=\"list~" + count + "\" onclick=\"changeDeductor(this.id);\">" + arr[1] + "</li>");
                         
                    }
                }

            } else {
                sb.append("<li class=\"list-group-item\"> No Records Found</li>");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnValue;
    }

    public BaseLayoutAjaxAction() {
        utl = new Util();
    }
    InputStream inputStream;
    private final globalUtilities.Util utl;
    private Map<String, Object> session;
    private String term;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
