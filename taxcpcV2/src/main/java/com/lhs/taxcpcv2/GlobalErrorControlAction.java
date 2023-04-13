/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.io.InputStream;

/**
 *
 * @author akash.dev
 */
public class GlobalErrorControlAction extends ActionSupport {

    Util utl;
    InputStream inputStream;
    private String action;

    public GlobalErrorControlAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        return l_return_value;
    }//end method

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}//end class
