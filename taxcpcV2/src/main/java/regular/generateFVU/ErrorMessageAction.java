/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ErrorMessageAction extends ActionSupport implements SessionAware {
    
    Map<String, Object> session;
    private String errorMessage;
    
    @Override
    public String execute() throws Exception {
        setErrorMessage("You Cannot Make FVU File Because Your Trial Period Validity Expires");
        return "success";
    }//end method

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}//end class
