/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DemoUITest extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        session.put("ACTIVE_TAB", "tdsDeductorInfo");
        String returnType = "success";

        if (!utl.isnull(getFlag())) {
            if (getFlag().equalsIgnoreCase("temp1")) {

                returnType = "temp1";

            } else if (getFlag().equalsIgnoreCase("temp2")) {
                returnType = "temp2";

            } else if (getFlag().equalsIgnoreCase("temp3")) {
                returnType = "temp3";

            } else if (getFlag().equalsIgnoreCase("temp4")) {
                returnType = "temp4";

            }
        }

        return returnType;
    }

    public DemoUITest() {
        utl = new Util();
    }
    Util utl;
    private Map<String, Object> session;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
