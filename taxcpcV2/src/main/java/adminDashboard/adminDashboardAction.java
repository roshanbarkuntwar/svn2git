/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class adminDashboardAction extends ActionSupport implements SessionAware {
    
    @Override
    public String execute() throws Exception {
        
        session.put("ACTIVE_TAB", "adminDashboard");
        return "success";
    }
     private Map<String, Object> session;
     
      @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
