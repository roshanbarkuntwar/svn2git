/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductorInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.CityMastDAO;
import dao.ClientMastDAO;
import dao.PanMastDAO;
import dao.StateMastDAO;
import dao.ViewClientCatgDAO;
import dao.ViewClientTypeDAO;
import dao.ViewDeducteeTypeDAO;
import dao.generic.DAOFactory;
import globalUtilities.ReadFromConsolidatedFile;
import globalUtilities.Util;
import hibernateObjects.CityMast;
import hibernateObjects.ClientMast;
import hibernateObjects.StateMast;
import hibernateObjects.ViewClientCatg;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DeductorAjaxAction extends ActionSupport implements SessionAware, ServletRequestAware {

    private Map<String, Object> session;
    private final Util utl;
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
    File consolidatedTdsFile;
    private String consolidatedTdsFileContentType;
    private String consolidatedTdsFileFileName;
    private HttpServletRequest servletRequest;
    private boolean fileFlag;
    private boolean tdsFileFlag;
    private boolean tanValidate;
    private String update_status;

    public DeductorAjaxAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_result = "error";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
         System.out.println("Svn Configuration......................");
        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("selectCity")) {
                CityMastDAO citydao = factory.getCityMastDAO();
                List<CityMast> clientmast = citydao.getCityCodeAsStateCode(getSelectedStateCode());
                for (CityMast cityMast : clientmast) {
                    sb.append("<option value=\"").append(cityMast.getCity_code()).append("\">").append(cityMast.getCity_name()).append("</option>");
                }
            } else if (getAction().equalsIgnoreCase("selectState")) {
                utl.generateLog("DeductorAjaxActionSTATE", "");
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
                try {
                    utl.generateLog("1.2....", "");
                    ViewClientTypeDAO viewclientTypeDAO = factory.getViewClientTypeDAO();
                    utl.generateLog("1.2.1...", "");
                    List<ViewClientType> viewclienttype = viewclientTypeDAO.ClientTypeListAsPanNO(panno_letter.toUpperCase(), utl);
                    utl.generateLog("1.2..2..", "");
                    if (viewclienttype != null && !viewclienttype.isEmpty() && !utl.isnull(panno_letter)) {
                        for (ViewClientType viewclientType : viewclienttype) {
                            sb.append(viewclientType.getClient_type_code());
                        }
                    } else {
                        sb.append("N");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (getAction().equalsIgnoreCase("setDeductorCatg")) {
                ViewClientCatgDAO clientcatgdao = factory.getViewClientCatgDAO();
                ViewClientCatg clientCatgFromCode = clientcatgdao.getClientCatgFromCode(getDeductor_catg_code());
                if (clientCatgFromCode != null) {
                    ViewClientTypeDAO clientTypeDAO = factory.getViewClientTypeDAO();
                    ViewClientType clientTypeFromCatg = clientTypeDAO.getClientTypeFromCatg(clientCatgFromCode.getClient_type_str());
                    if (clientTypeFromCatg != null) {
                        sb.append("<option value=").append(clientTypeFromCatg.getClient_type_code()).append("~").append(clientTypeFromCatg.getPan_card_type()).append(">").append(clientTypeFromCatg.getClient_type_name()).append("</option>");
                    }
                }
            } else if (getAction().equalsIgnoreCase("uploadConsolidatedTdsFileAction")) {
                utl.generateLog("file= ", getConsolidatedTdsFile());
                utl.generateLog("filename= ", getConsolidatedTdsFileFileName());

                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");

                if (client != null && asmt != null) {

                    File fileToCreate = null;

                    if (getConsolidatedTdsFile() != null) {
                        fileFlag = true;
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
//                        utl.generateLog("filePath= " + filePath);
//                        utl.generateLog("filePath= " + filePath);
                        fileToCreate = new File(filePath, getConsolidatedTdsFileFileName());
                        FileUtils.copyFile(getConsolidatedTdsFile(), fileToCreate);

                        if (fileToCreate.getName().endsWith(".tds") || fileToCreate.getName().endsWith(".txt")) {
                            tdsFileFlag = true;
                        } else {
                            tdsFileFlag = false;
                        }
//                        utl.generateLog("tdsFileFlag= " + tdsFileFlag);
                        if (tdsFileFlag) {
                            ReadFromConsolidatedFile readFile = new ReadFromConsolidatedFile();
                            String deductorDataFromTdsFile = readFile.readDataFromConsolidatedFile(fileToCreate.getAbsolutePath(), client.getTanno());

//                            utl.generateLog("read Data from con:::" + deductorDataFromTdsFile);
                            if (utl.isnull(deductorDataFromTdsFile)) {
                                tanValidate = false;

                                l_return_result = "error##TDS file not valid";
                            }
                            if (deductorDataFromTdsFile.equals("false")) {
                                tanValidate = false;

                                l_return_result = "error##The TDS TAN number does not match the existing TAN number";
                            } else {
                                tanValidate = true;
                                ArrayList<String> arrTdsData = getTdsDataForUpdate(deductorDataFromTdsFile);

//                                utl.generateLog("arrTDSData...... " + arrTdsData);
                                HashMap<Integer, String> arrFileData = new HashMap<>();

                                for (int i = 0; i < arrTdsData.size(); i++) {
                                    arrFileData.put(i, arrTdsData.get(i));
                                }

                                ClientMast clientMastFromTdsData = setClientMastForUPdate(arrFileData, client.getClient_code());
                                boolean update = false;

                                update_status = "false";

                                if (clientMastFromTdsData != null) {
                                    ClientMastDAO clientMastDAO = factory.getClientMastDAO();
                                    update = clientMastDAO.update(clientMastFromTdsData);
                                    if (update) {
                                        update_status = "true";
                                        l_return_result = "success";

                                        ViewClientMast newWorkingUser = factory.getViewClientMastDAO().readClientByClientCode(clientMastFromTdsData.getClient_code());
                                        session.put("WORKINGUSER", newWorkingUser);
                                    }
                                }
                            }
                        }

                    } else {
                        fileFlag = false;
                    }
                } else {
                    l_return_result = "error";
                }
                sb.append(l_return_result);
            }
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public ArrayList<String> getTdsDataForUpdate(String str) {
        ArrayList<String> arrData = new ArrayList<>();
        String str1 = str + "true";
//        String replaceAll = str1.replaceAll("[-+^]", "#");
        String replaceAll = str1.replace("^", "#");// 26-03-2016 asper sapan jain
        String[] split = replaceAll.split("#");
        int count = 0;
        for (String string : split) {
            count++;
            String l_str_data = "";
            if (string.equals("") && string.equals("null")) {
                l_str_data = "^";
            } else {
                l_str_data = string;
            }
            if (count < split.length) {
                if (utl.isnull(l_str_data)) {
                    arrData.add("NA");
                } else {
                    arrData.add(l_str_data);
                }
            }
        }
        return arrData;
    }// end method

    public ClientMast setClientMastForUPdate(HashMap<Integer, String> arrList, String clientCode) {
        ClientMast filetran = null;
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ClientMastDAO clientMastDAO = factory.getClientMastDAO();

            filetran = clientMastDAO.readClientByClientCode(clientCode);
            if (arrList != null && arrList.size() > 0) {
                Set<Integer> keySet = arrList.keySet();
                for (Iterator<Integer> it = keySet.iterator(); it.hasNext();) {
                    int key = it.next();
                    String value = arrList.get(key);
                    value = value.equals("NA") ? "" : value;
                    switch (key) {
                        case 18:
                            filetran.setClient_name(value);
                            break;
                        case 19:
                            filetran.setBranch_division(value);
                            break;
                        case 20:
                            filetran.setAdd1(value);
                            break;
                        case 21:
                            filetran.setAdd2(value);
                            break;
                        case 22:
                            filetran.setAdd3(value);
                            break;
                        case 23:
                            filetran.setAdd4(value);
                            break;
                        case 24:
                            filetran.setCity_code(value);
                            break;
                        case 25:
                            filetran.setState_code(value);
                            break;
                        case 26:
                            filetran.setPin(value);
                            break;
                        case 27:
                            filetran.setEmail_id(value);
                            break;
                        case 28:
                            filetran.setStdcode(value);
                            filetran.setDeductor_stdcode(value);
                            break;
                        case 29:
                            filetran.setPhoneno(value);
                            filetran.setDeductor_phoneno(value);
                            break;
                        case 31:
                            filetran.setClient_catg_code(value);
                            break;
                        case 32:
                            filetran.setDeductor_name(value);
                            break;
                        case 33:
                            filetran.setDeductor_desig(value);
                            break;
                        case 34:
                            filetran.setDeductor_add1(value);
                            break;
                        case 35:
                            filetran.setDeductor_add2(value);
                            break;
                        case 36:
                            filetran.setDeductor_add3(value);
                            break;
                        case 37:
                            filetran.setDeductor_add4(value);
                            break;
                        case 38:
                            filetran.setDeductor_city_code(value);
                            break;
                        case 39:
                            filetran.setDeductor_state_code(value);
                            break;
                        case 40:
                            filetran.setDeductor_pin(value);
                            break;
                        case 41:
                            filetran.setDeductor_email_id(value);
                            break;
                        case 42:
                            // case for text file
                            utl.generateLog("deductor upload--" + clientCode, "Mobile no found at 42" + value);
                            if (!utl.isnull(value) && value.length() == 10) {
                                filetran.setDeductor_mobileno(value);
                            }
                            break;
                        //                    else if (key == 43) {
                        case 47:
                            //case for tds file
                            utl.generateLog("deductor upload--" + clientCode, "Mobile no found at 47" + value);
                            if (!utl.isnull(value) && value.length() == 10) {
                                filetran.setDeductor_mobileno(value);
                            }
                            break;
                        case 45:
                            filetran.setDeductor_add_change(value);
                            break;
                        case 53:
                            filetran.setMinistry_state_code(value);
                            break;
                        case 54:
                            filetran.setPao_code(value);
                            break;
                        case 55:
                            filetran.setDdo_code(value);
                            break;
                        case 56:
                            filetran.setMinistry_code(value);
                            break;
                        case 57:
                            filetran.setMinistry_name_other(value);
                            break;
                        case 58:
                            filetran.setDeductor_panno(value);
                            break;
                        case 59:
                            filetran.setPao_registration_no(value);
                            break;
                        //                    else if (key == 61) {
                        case 60:
                            filetran.setDdo_registration_no(value);
                            break;
                        case 67:
                            filetran.setAin_no(value);
                            break;
                        case 14:
                            filetran.setPanno(value);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            filetran = null;
        }
        return filetran;
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

    public String getPANNO() {
        return PANNO;
    }

    public void setPANNO(String PANNO) {
        this.PANNO = PANNO;
    }

    public String getDeductor_catg_code() {
        return deductor_catg_code;
    }

    public void setDeductor_catg_code(String deductor_catg_code) {
        this.deductor_catg_code = deductor_catg_code;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public File getConsolidatedTdsFile() {
        return consolidatedTdsFile;
    }

    public void setConsolidatedTdsFile(File consolidatedTdsFile) {
        this.consolidatedTdsFile = consolidatedTdsFile;
    }

    public String getConsolidatedTdsFileContentType() {
        return consolidatedTdsFileContentType;
    }

    public void setConsolidatedTdsFileContentType(String consolidatedTdsFileContentType) {
        this.consolidatedTdsFileContentType = consolidatedTdsFileContentType;
    }

    public String getConsolidatedTdsFileFileName() {
        return consolidatedTdsFileFileName;
    }

    public void setConsolidatedTdsFileFileName(String consolidatedTdsFileFileName) {
        this.consolidatedTdsFileFileName = consolidatedTdsFileFileName;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public boolean isFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(boolean fileFlag) {
        this.fileFlag = fileFlag;
    }

    public boolean isTdsFileFlag() {
        return tdsFileFlag;
    }

    public void setTdsFileFlag(boolean tdsFileFlag) {
        this.tdsFileFlag = tdsFileFlag;
    }

    public boolean isTanValidate() {
        return tanValidate;
    }

    public void setTanValidate(boolean tanValidate) {
        this.tanValidate = tanValidate;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }

}//end class

