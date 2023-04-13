/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2.bean;

import java.io.File;
import java.lang.reflect.Field;

/**
 *
 * @author harshad.umredkar
 */
public class ImportFileAttribs {

    private File template;
    private String templateContentType;
    private String templateFileName;
    private String readLineNo;
    private String fileName;

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    public String getTemplateContentType() {
        return templateContentType;
    }

    public void setTemplateContentType(String templateContentType) {
        this.templateContentType = templateContentType;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getReadLineNo() {
        return readLineNo;
    }

    public void setReadLineNo(String readLineNo) {
        this.readLineNo = readLineNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void to_String() {
        Field[] allFields = this.getClass().getFields();
        for (Field fld : allFields) {
            try {
                //System.out.println(fld.getName() + " : " + fld.get(this));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }//end method
}
