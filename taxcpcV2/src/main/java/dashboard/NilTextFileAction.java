/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author sakshi.bandhate
 */
public class NilTextFileAction extends ActionSupport implements SessionAware{

    @Override
    public String execute(){
//        System.out.println("regular.dashboard.taxAudit.TaxAuditDataAction.execute()");
        String returnView = "success";
        formName = "NIL Text File";
        actionUrl = "javascript:nilReturn()";
        return returnView;
    } 

    Map<String, Object> session;
    private String formName;
    private String actionUrl;
    
    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
    
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
}
