/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.opensymphony.xwork2.ActionSupport;
import dao.BankMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.BankMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public class FetchDetailsAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        String return_value = "";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            if (getAction().equals("fetchDetails")) {
                BankMastDAO bankmastDAO = factory.getBankMastDAO();
                List<BankMast> bankdetails = bankmastDAO.getBankDetails(getValue());
                if (bankdetails != null && !bankdetails.isEmpty()) {
                    BankMast bankdata = bankdetails.get(0);
                    String bankName = bankdata.getBank_name();
                    String branchName = bankdata.getBranch();
                    bankName = utl.isnull(bankName) ? "" : bankName;
                    branchName = utl.isnull(branchName) ? "" : branchName;
                    return_value += bankdata.getBank_name() + "#" + bankdata.getBranch();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setInputStream(new ByteArrayInputStream(return_value.toString().getBytes("UTF-8")));
        return return_view;
    }

    Util utl;
    private String action;
    InputStream inputStream;
    private String value;

    public FetchDetailsAction() {
        utl = new Util();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
