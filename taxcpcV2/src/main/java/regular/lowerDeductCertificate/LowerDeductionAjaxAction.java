/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import com.opensymphony.xwork2.ActionSupport;
import dao.TdsSplRateMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class LowerDeductionAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {

        String returnType = "success";
        String status = new String();
        try {

            if (!utl.isnull(getAction())) {

                if (getAction().equals("getDeducteeCertificateNo")) {

                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    TdsSplRateMastDAO tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                    TdsSplRateMast tdsSplRateMast = new TdsSplRateMast();
                    tdsSplRateMast.setTds_code(getTdsCode());
                    tdsSplRateMast.setDeductee_code(getDeducteeCode());
                    tdsSplRateMast.setCertificate_no(getCertificateNo());

                    TdsSplRateMast deducteeCertificateNo = tdsSplRateMastDAO.getDeducteeCertificateNo(tdsSplRateMast);
                    if (deducteeCertificateNo != null) {
                        status = deducteeCertificateNo.getCertificate_no();
//                        status = "true";
                    } else {
                        status = "NotExist";
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        inputStream = new ByteArrayInputStream(status.getBytes("UTF-8"));
        return returnType;

    }

    public LowerDeductionAjaxAction() {
        utl = new Util();
    }

    Util utl;
    private InputStream inputStream;
    private String tdsCode;
    private String deducteeCode;
    private String fromDate;
    private String action;
    private String panno;
    private String certificateNo;
    Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTdsCode() {
        return tdsCode;
    }

    public void setTdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
    }

    public String getDeducteeCode() {
        return deducteeCode;
    }

    public void setDeducteeCode(String deducteeCode) {
        this.deducteeCode = deducteeCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

}
