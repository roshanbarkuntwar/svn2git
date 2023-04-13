/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsSplRateMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class TransactionOtherAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {
        String l_return_value = "success";
        String l_return_message = "";
        DAOFactory factory;
        TdsSplRateMastDAO tdsSplRateMastDAO;
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("save")) {
                factory = DAOFactory.instance(DAOFactory.HIBERNATE);

                try {
                    tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                    if (getTdsSplRateMast() != null) {

                        tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                        TdsSplRateMast tdsSplRateMast1 = getTdsSplRateMast();

                        tdsSplRateMast1.setCertificate_no(tdsSplRateMast1.getCertificate_no().toUpperCase());
                        tdsSplRateMast1.setDeductee_panno(utl.isnull(getDeducteePan()) ? "" : getDeducteePan());
                        tdsSplRateMast1.setClient_code(utl.isnull(client.getClient_code()) ? "" : client.getClient_code());
                        tdsSplRateMast1.setEntity_code(utl.isnull(client.getEntity_code()) ? "" : client.getEntity_code());
                        tdsSplRateMast1.setAcc_year(utl.isnull(asmt.getAccYear()) ? "" : asmt.getAccYear());
                        tdsSplRateMast1.setTds_type_code(utl.isnull(asmt.getTdsTypeCode()) ? "" : asmt.getTdsTypeCode());

                        setTdsSplRateMast(tdsSplRateMast1);

                        boolean save = tdsSplRateMastDAO.save(getTdsSplRateMast());
                        if (save) {

                            l_return_message = "success#" + getTdsSplRateMast().getCertificate_no() + "#" + getTdsSplRateMast().getTds_rate();
                        } else {
                            l_return_message = "error#Some error occured, certificate details could not be saved.";
                        }
                    } else {
                        session.put("ERRORCLASS", MessageType.errorMessage);
                        l_return_message = "error#Some error occured, certificate details could not be saved.";
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
            } else if (getAction().equalsIgnoreCase("checkCertificate")) {
                factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                Boolean isUnique = false;
                try {

                    isUnique = tdsSplRateMastDAO.checkUniquePrimaryKey(getTdsSplRateMast());
                    if (isUnique) {
                        l_return_message = "success#true";
                    } else {
                        l_return_message = "success#false";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("getGrandTotal")) {

            }

            inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        }
        return l_return_value;
    }

    public TransactionOtherAjaxAction() {
        utl = new Util();
    }
    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private TdsSplRateMast tdsSplRateMast;
    private String deducteeName;
    private String deducteePan;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public TdsSplRateMast getTdsSplRateMast() {
        return tdsSplRateMast;
    }

    public void setTdsSplRateMast(TdsSplRateMast tdsSplRateMast) {
        this.tdsSplRateMast = tdsSplRateMast;
    }

    public String getDeducteeName() {
        return deducteeName;
    }

    public void setDeducteeName(String deducteeName) {
        this.deducteeName = deducteeName;
    }

    public String getDeducteePan() {
        return deducteePan;
    }

    public void setDeducteePan(String deducteePan) {
        this.deducteePan = deducteePan;
    }

}
