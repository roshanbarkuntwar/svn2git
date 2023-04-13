/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.importCertificate;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ImportTDSSpecialRateAction extends ActionSupport implements SessionAware {

    Util utl;
    private Map<String, Object> session;

    public ImportTDSSpecialRateAction() {
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        return return_view; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end method

}//end class
