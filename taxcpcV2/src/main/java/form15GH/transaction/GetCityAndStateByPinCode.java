/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.opensymphony.xwork2.ActionSupport;
import dao.PinCodeMastDAO;
import dao.generic.DAOFactory;
import hibernateObjects.PinCodeMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author seema.mourya
 */
public class GetCityAndStateByPinCode extends ActionSupport
{
     @Override
    public String execute() throws Exception {
        String returnValue = "success";
        String returnMessage = "";
        //System.out.println(" getField_value() " + getField_value());
        try {

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            PinCodeMastDAO pinCodeMastDAO = factory.getPinCodeMastDAO();
            PinCodeMast searchStr = pinCodeMastDAO.readCityAndStateByPincode(getField_value());
            if (searchStr != null) {
                returnMessage = searchStr.getCity() + "#" + searchStr.getState_code();
            }
            inputStream = new ByteArrayInputStream(returnMessage.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    private InputStream inputStream;
    private String field_value;

    public String getField_value() {
        return field_value;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
