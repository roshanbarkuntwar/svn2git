/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.EncryptDecryptUtil;
import globalUtilities.FileOptUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Kapil Gupta
 */
public class GetDBEntityMastData extends ActionSupport {

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    FileOptUtil fileOptUtil = new FileOptUtil();

    @Override
    public String execute() throws Exception {
        String return_type = "success";
        String secret_key = "";
        String listOfData = "";
        try {
            DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
            EncryptDecryptUtil encryptData = new EncryptDecryptUtil();
            String data = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                    "SELECT 'Total Entity are ' || count(*) || ' and Entity Details are as ' ||\n"
                    + "       chr(10) || WM_CONCAT(ENTITY_CODE || '>' || ENTITY_NAME)\n"
                    + "  FROM ENTITY_MAST");
            secret_key = fileOptUtil.readPropertiesKeyName("secret-key");
            listOfData = encryptData.encrypt(data, secret_key);
            if (listOfData != null) {
                inputStream = new ByteArrayInputStream(listOfData.getBytes("UTF-8"));
                return return_type;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failure";
    }

    public String getEntityCodeValidation(String entityCode) {
        System.out.println("Checking Entity Code Validation In DataBase");
        DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
        String execQuery = "select entity_code from ENTITY_MAST WHERE ENTITY_CODE = '" + entityCode + "'";
        String queryResult = ef.execute_oracle_function_as_string(execQuery);
        return queryResult;
    }

    public boolean getEntityCheckConfig(String entityCode) throws IOException {
        System.out.println("Checking Entity Code Validation In Config-File");
        String propEntityCode = "";
        boolean queryResult = false;
        propEntityCode = fileOptUtil.readPropertiesKeyName("entity-code");
        boolean matchValue = propEntityCode.matches("(.*)" + entityCode + "(.*)");
        if (matchValue) {
            queryResult = true;
            System.out.println("Entity Code Validation In Config-File Completed");
        } else {
            queryResult = false;
        }
        return queryResult;
    }

//    public static void main(String[] args) throws IOException {
//        DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
//        System.out.println("Configure Entity Are :" + ef.execute_oracle_function_as_string("SELECT replace(WM_CONCAT(ENTITY_CODE),', ','#') FROM ENTITY_MAST"));
//    }
}
