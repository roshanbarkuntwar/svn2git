/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.deductorInfo;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DeductorInfoAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        session.put("ACTIVE_TAB", "15GHDeductorInfo");
         System.out.println("Svn Configuration......................");
        return "success";
        
    }

    Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
