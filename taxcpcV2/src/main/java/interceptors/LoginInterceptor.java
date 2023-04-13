/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author akash.dev
 */
public class LoginInterceptor implements Interceptor {

    private final static Logger log = Logger.getLogger(LoginInterceptor.class.getName());

    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
    }//end method

    @Override
    public void init() {
    }//end method

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

        String formLogin = sessionAttributes.get("FORMLOGIN") == null ? "" : (String) sessionAttributes.get("FORMLOGIN");
        UserMast userMast = sessionAttributes.get("LOGINUSER") == null ? null : (UserMast) sessionAttributes.get("LOGINUSER");
        ViewClientMast client = sessionAttributes.get("WORKINGUSER") == null ? null : (ViewClientMast) sessionAttributes.get("WORKINGUSER");
        if ("".equals(formLogin) || (userMast == null && client == null)) {
            /**
             * This condition will track for direct url access.
             */

            log.info("Interceptor status - login denied!");
            return "loginFailed";
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Expires", "-1");

            return actionInvocation.invoke();
        }
    }//end method
}//end class
