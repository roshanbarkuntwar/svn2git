/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 * @author ayushi.jain
 */
public class GetBuildVersion extends ActionSupport {

    @Override
    public String execute() throws UnsupportedEncodingException {
        String retVal = "success";
        String return_mas = "";

        try {

            InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.txt");

            if (resourceAsStream != null) {
                Properties prop = new Properties();
                prop.load(resourceAsStream);

                String lastBuildDate = prop.getProperty("build.date");

                if (!utl.isnull(lastBuildDate) && !lastBuildDate.equalsIgnoreCase("${timestamp}")) {
                    return_mas = " Application Last Updated on " + lastBuildDate;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        inputStream = new ByteArrayInputStream(return_mas.getBytes("UTF-8"));
        return retVal;
    }

    public GetBuildVersion() {
        utl = new Util();
    }
    private final globalUtilities.Util utl;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
