/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.opensymphony.xwork2.ActionSupport;
import dao.CityMastDAO;
import dao.PanMastDAO;
import dao.StateMastDAO;
import dao.ViewClientCatgDAO;
import dao.ViewClientTypeDAO;
import dao.ViewDeducteeTypeDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.CityMast;
import hibernateObjects.StateMast;
import hibernateObjects.ViewClientCatg;
import hibernateObjects.ViewClientType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author seema.mourya
 */
public class SelectListAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String selectedCountryCode;
    private String selectedStateCode;
    private String selectedCityCode;
    private String deductor_type_code;
    private String deductor_catg_code;
    private String PANNO;
    private String deductor_type_catg;
    private String hiddenClientCatgCode;

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
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

    public String getSelectedCountryCode() {
        return selectedCountryCode;
    }

    public void setSelectedCountryCode(String selectedCountryCode) {
        this.selectedCountryCode = selectedCountryCode;
    }

    public String getSelectedStateCode() {
        return selectedStateCode;
    }

    public void setSelectedStateCode(String selectedStateCode) {
        this.selectedStateCode = selectedStateCode;
    }

    public String getSelectedCityCode() {
        return selectedCityCode;
    }

    public void setSelectedCityCode(String selectedCityCode) {
        this.selectedCityCode = selectedCityCode;
    }

    public String getDeductor_type_code() {
        return deductor_type_code;
    }

    public void setDeductor_type_code(String deductor_type_code) {
        this.deductor_type_code = deductor_type_code;
    }

    public String getDeductor_catg_code() {
        return deductor_catg_code;
    }

    public void setDeductor_catg_code(String deductor_catg_code) {
        this.deductor_catg_code = deductor_catg_code;
    }

    public String getPANNO() {
        return PANNO;
    }

    public void setPANNO(String PANNO) {
        this.PANNO = PANNO;
    }

    public String getDeductor_type_catg() {
        return deductor_type_catg;
    }

    public void setDeductor_type_catg(String deductor_type_catg) {
        this.deductor_type_catg = deductor_type_catg;
    }

    public String getHiddenClientCatgCode() {
        return hiddenClientCatgCode;
    }

    public void setHiddenClientCatgCode(String hiddenClientCatgCode) {
        this.hiddenClientCatgCode = hiddenClientCatgCode;
    }

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("selectCity")) {
                CityMastDAO citydao = factory.getCityMastDAO();
                List<CityMast> clientmast = citydao.getCityCodeAsStateCode(getSelectedStateCode());
                for (CityMast cityMast : clientmast) {
                    sb.append("<option value=\"").append(cityMast.getCity_code()).append("\">").append(cityMast.getCity_name()).append("</option>");
                }
            } else if (getAction().equalsIgnoreCase("selectState")) {
                StateMastDAO statedao = factory.getStateMastDAO();
                List<StateMast> statetmast = statedao.getStateAsCountryCode(getSelectedCountryCode());
                for (StateMast stateMast : statetmast) {
                    sb.append("<option value=\"").append(stateMast.getState_code()).append("\">").append(stateMast.getState_name()).append("</option>");
                }
            } else if (getAction().equalsIgnoreCase("selectDeducteeCity")) {
                CityMastDAO citydao = factory.getCityMastDAO();
                List<CityMast> clientmast = citydao.getCityCodeAsStateCode(getSelectedStateCode());
                for (CityMast cityMast : clientmast) {
                    sb.append("<option value=\"").append(cityMast.getCity_code()).append("\">").append(cityMast.getCity_name()).append("</option>");
                }
            } else if (getAction().equalsIgnoreCase("select_client_catg")) {
                ViewClientCatgDAO clientcatgdao = factory.getViewClientCatgDAO();
                List<ViewClientCatg> data = clientcatgdao.getClientCatgAsList(getDeductor_type_code());
                if (data != null) {
                    for (ViewClientCatg viewClientCatg : data) {
                        sb.append("<option value=\"").append(viewClientCatg.getClient_catg_code()).append("\">").append(viewClientCatg.getClient_catg_name()).append("</option>");
                    }
                }
            } else if (getAction().equalsIgnoreCase("select_client_catg_list")) {
                ViewClientCatgDAO clientcatgdao = factory.getViewClientCatgDAO();
                List<ViewClientCatg> data = clientcatgdao.getClientCatgList(getDeductor_type_code(), utl);
                if (data != null) {
                    for (ViewClientCatg viewClientCatg : data) {
                        if (!utl.isnull(getHiddenClientCatgCode()) && getHiddenClientCatgCode().equalsIgnoreCase(viewClientCatg.getClient_catg_code())) {
                            sb.append("<option value=\"").append(viewClientCatg.getClient_catg_code()).append("\" selected=\"true\">").append(viewClientCatg.getClient_catg_name()).append("</option>");
                        } else {
                            sb.append("<option value=\"").append(viewClientCatg.getClient_catg_code()).append("\">").append(viewClientCatg.getClient_catg_name()).append("</option>");
                        }
                    }
                }
            } else if (getAction().equalsIgnoreCase("select_client_type_list_data")) {
                ViewClientCatgDAO clientcatgdao = factory.getViewClientCatgDAO();
                ViewClientCatg data = clientcatgdao.getClientTypeAsCatg(getDeductor_type_catg(), utl);
                if (data != null) {
                    sb.append(data.getClient_type_str());
                }
            } else if (getAction().equalsIgnoreCase("validatePANNO")) {
                PanMastDAO panmastdao = factory.getPanMastDAO();
                long pancount = panmastdao.getPanCountAsPanCode(getPANNO());
                if (pancount > 0) {
                    sb.append("T");
                } else {
                    sb.append("F");
                }
            } else if (getAction().equalsIgnoreCase("setDeducteeType")) {
                String panno_letter = "";
                if (!utl.isnull(getPANNO()) && getPANNO().length() > 3) {
                    panno_letter = getPANNO().substring(3, 4);
                }
                ViewDeducteeTypeDAO viewDeducteeTypeDAO = factory.getViewDeducteeTypeDAO();
                ArrayList<ArrayList<String>> deducteeCatg = viewDeducteeTypeDAO.getDeducteeCatgAsLinkedPanno(panno_letter.toUpperCase());
                if (deducteeCatg != null && !deducteeCatg.isEmpty() && !utl.isnull(panno_letter)) {
                    for (int i = 0; i < deducteeCatg.size(); i++) {
                        ArrayList<String> arr = deducteeCatg.get(i);
                        sb.append(arr.get(0)).append("~").append(arr.get(2));
                    }
                } else {
                    sb.append("");
                }

            } else if (getAction().equalsIgnoreCase("setDeducteeTypeList")) {
                String panno_letter = "";
                if (!utl.isnull(getPANNO()) && getPANNO().length() > 3) {
                    if (getPANNO().equals("PANNOTRQED")) {
                        panno_letter = getPANNO().substring(3, 10);
                    } else {
                        panno_letter = getPANNO().substring(3, 4);
                    }
                }
                ViewClientTypeDAO viewclientTypeDAO = factory.getviewClientTypeDAO();
                List<ViewClientType> viewclienttype = viewclientTypeDAO.ClientTypeListAsPanNO(panno_letter.toUpperCase(), utl);
                if (viewclienttype != null && !viewclienttype.isEmpty() && !utl.isnull(panno_letter)) {
                    for (ViewClientType viewclientType : viewclienttype) {
                        sb.append(viewclientType.getClient_type_code());
                    }
                } else {
                    sb.append("N");
                }

            } else if (getAction().equalsIgnoreCase("setDeductorCatg")) {
                ViewClientCatgDAO clientcatgdao = factory.getViewClientCatgDAO();
                ViewClientCatg clientCatgFromCode = clientcatgdao.getClientCatgFromCode(getDeductor_catg_code());
                if (clientCatgFromCode != null) {
                    ViewClientTypeDAO clientTypeDAO = factory.getviewClientTypeDAO();
                    ViewClientType clientTypeFromCatg = clientTypeDAO.getClientTypeFromCatg(clientCatgFromCode.getClient_type_str());
                    if (clientTypeFromCatg != null) {
                        sb.append("<option value=").append(clientTypeFromCatg.getClient_type_code()).append("~").append(clientTypeFromCatg.getPan_card_type()).append(">").append(clientTypeFromCatg.getClient_type_name()).append("</option>");
                    }
                }
            }
//        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
}
