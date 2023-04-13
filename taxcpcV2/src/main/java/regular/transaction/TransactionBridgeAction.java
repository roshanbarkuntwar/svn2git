/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class TransactionBridgeAction extends ActionSupport implements SessionAware {

    public String execute() {
        if (!utl.isnull(getAction())) {
            try {
                session.remove("ALLASSESSMENT");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            session.put("ALLASSESSMENT", "T");
        }

        return "success";
    }

    Util utl;

    public TransactionBridgeAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    private String action;

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

}
