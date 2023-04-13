/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import hibernateObjects.ViewClientMast;
import java.util.Map;

/**
 *
 * @author akash.dev
 */
public class ModuleAccessInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
    }//end method

    @Override
    public void init() {
    }//end method

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
//        String module = actionInvocation.getStack().findString("module");
//        String accessKeyValue = "0";
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
        ViewClientMast client = (ViewClientMast) sessionAttributes.get("WORKINGUSER");

        String addRight = client.getAdd_right();
        String editRight = client.getEdit_right();
        String deleteRight = client.getDelete_right();
        String queryRight = client.getQuery_right();

        if (addRight.equalsIgnoreCase("1") || editRight.equalsIgnoreCase("1") || deleteRight.equalsIgnoreCase("1") || queryRight.equalsIgnoreCase("1")) {
            return actionInvocation.invoke();
        } else {
            return "accessDenied";
        }
    }//end method

}//end class
