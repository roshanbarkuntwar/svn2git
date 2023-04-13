/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.CountryMastDAO;
import dao.TdsMastDAO;
import dao.ViewDeducteeTypeDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.CountryMast;
import hibernateObjects.TdsMast;
import hibernateObjects.ViewDeducteeType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class SelectAJAXDataAction15GH extends ActionSupport implements SessionAware {

    public SelectAJAXDataAction15GH() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
   System.out.println("SelectAJAXDataAction15GH");
        String return_value = "success";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

        if (getAction().equalsIgnoreCase("setDeducteeType")) {
            String panno_letter = "";
            if (!utl.isnull(getPanno()) && getPanno().length() > 3) {
                panno_letter = (getPanno().substring(3, 4)).toUpperCase();
            }
            ViewDeducteeTypeDAO viewDeducteeTypeDAO = factory.getViewDeducteeTypeDAO();
            List<ViewDeducteeType> viewdeducteetype = viewDeducteeTypeDAO.getDeducteeTypeList(panno_letter, utl);
            if (viewdeducteetype != null) {
                for (ViewDeducteeType viewDeducteeType : viewdeducteetype) {
                    if (!utl.isnull(getHdeducteeType()) && (getHdeducteeType().equals(viewDeducteeType.getDeductee_type_code()))) {
                        sb.append("<option value=\"").append(viewDeducteeType.getDeductee_type_code()).append("~").append(viewDeducteeType.getPan_card_type()).append("\" selected=\"true\">").append(viewDeducteeType.getDeductee_type_name()).append("</option>");
                    } else {
                        sb.append("<option value=").append(viewDeducteeType.getDeductee_type_code()).append("~").append(viewDeducteeType.getPan_card_type()).append(">").append(viewDeducteeType.getDeductee_type_name()).append("</option>");
                    }
                }
            } else {
                viewDeducteeTypeDAO = factory.getViewDeducteeTypeDAO();
                List<ViewDeducteeType> allviewdeducteetype = viewDeducteeTypeDAO.getAllDeducteeTypeList();
                if (allviewdeducteetype != null) {
                    for (ViewDeducteeType allviewDeducteeType : allviewdeducteetype) {
                        if (!utl.isnull(getHdeducteeType()) && (getHdeducteeType().equals(allviewDeducteeType.getDeductee_type_code()))) {
                            sb.append("<option value=\"").append(allviewDeducteeType.getDeductee_type_code()).append("~").append(allviewDeducteeType.getPan_card_type()).append("\" selected=\"true\">").append(allviewDeducteeType.getDeductee_type_name()).append("</option>");
                        } else {
                            sb.append("<option value=").append(allviewDeducteeType.getDeductee_type_code()).append("~").append(allviewDeducteeType.getPan_card_type()).append(">").append(allviewDeducteeType.getDeductee_type_name()).append("</option>");
                        }
                    }
                }
            }
        } else if (getAction().equalsIgnoreCase("selectCountry")) {
            CountryMastDAO countrydao = factory.getCountryMastDAO();
            List<CountryMast> countrymast = countrydao.getCountryListAsResident(getResident_status(), utl);
            if (countrymast != null) {
                sb.append("<option value=\"\">Select Country</option>");
                for (CountryMast countryMast : countrymast) {
                    if (!utl.isnull(getHcountry_code()) && (getHcountry_code().equals(countryMast.getCountry_code()))) {
                        sb.append("<option value=\"").append(countryMast.getCountry_code()).append("\" selected=\"true\">").append(countryMast.getCountry_name()).append("</option>");
                    } else {
                        sb.append("<option value=\"").append(countryMast.getCountry_code()).append("\">").append(countryMast.getCountry_name()).append("</option>");
                    }
                }
            }

        } else if (getAction().equalsIgnoreCase("selectTDSCode")) {
            String flag = getDeducteeCatg();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String tds_type_code = asmt.getTdsTypeCode();
            TdsMastDAO tdsMastDAO = factory.getTdsMastDAO();
            List<TdsMast> tdsmast = tdsMastDAO.getTdsCodeList(getTdsSection(), tds_type_code, flag, utl);
            if (tdsmast != null) {
                for (TdsMast tdsMast : tdsmast) {
                    if (!utl.isnull(getHtdsCode()) && (getHtdsCode().equals(tdsMast.getTds_code()))) {
                        sb.append("<option value=\"").append(tdsMast.getTds_code()).append("\" selected=\"true\">").append(tdsMast.getTds_name()).append("</option>");
                    } else {
                        sb.append("<option value=\"").append(tdsMast.getTds_code()).append("\">").append(tdsMast.getTds_name()).append("</option>");
                    }
                }
            }
        }

        inputStream = new ByteArrayInputStream(sb.toString().getBytes());
        return return_value;
    }

    Map<String, Object> session;
    private String action;
    private String panno;
    private InputStream inputStream;
    private String hdeducteeType;
    private String resident_status;
    private String hcountry_code;
    Util utl;
    private String panChar;
    private String deducteeCatg;
    private String htdsCode;
    private String tdsSection;

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

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getHdeducteeType() {
        return hdeducteeType;
    }

    public void setHdeducteeType(String hdeducteeType) {
        this.hdeducteeType = hdeducteeType;
    }

    public String getResident_status() {
        return resident_status;
    }

    public void setResident_status(String resident_status) {
        this.resident_status = resident_status;
    }

    public String getHcountry_code() {
        return hcountry_code;
    }

    public void setHcountry_code(String hcountry_code) {
        this.hcountry_code = hcountry_code;
    }

    public String getPanChar() {
        return panChar;
    }

    public void setPanChar(String panChar) {
        this.panChar = panChar;
    }

    public String getDeducteeCatg() {
        return deducteeCatg;
    }

    public void setDeducteeCatg(String deducteeCatg) {
        this.deducteeCatg = deducteeCatg;
    }

    public String getHtdsCode() {
        return htdsCode;
    }

    public void setHtdsCode(String htdsCode) {
        this.htdsCode = htdsCode;
    }

    public String getTdsSection() {
        return tdsSection;
    }

    public void setTdsSection(String tdsSection) {
        this.tdsSection = tdsSection;
    }

}
