/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.CityMastDAO;
import dao.DbProc.SetDatabaseValues;
import dao.DeducteeBflagAmtTranDAO;
import dao.DeducteeMast15ghDAO;
import dao.PanMastDAO;
import dao.StateMastDAO;
import dao.ViewClientMastDAO;
import dao.ViewDeducteeCatgDAO;
import dao.ViewEmpCatgDAO;
import dao.ViewLhssysLatestAssYearDAO;
import dao.ViewPanStatusDAO;
import dao.ViewResidentTypeDAO;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.DeducteeBflagAmtTran;
import hibernateObjects.DeducteeMast15gh;
import hibernateObjects.PanMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class DeducteesAction15GH extends ActionSupport implements SessionAware {

    public DeducteesAction15GH() {

        deducteeMast15gh = new DeducteeMast15gh();

        utl = new Util();
        resident_type = new LinkedHashMap<String, String>();
        deductee_catg_list = new LinkedHashMap<String, String>();
        deductee_type = new LinkedHashMap<String, String>();
        select_country = new LinkedHashMap<String, String>();
        tds_code_list = new LinkedHashMap<String, String>();
        select_state = new LinkedHashMap<String, String>();
        select_city = new HashMap<String, String>();
        selectGender = new HashMap<String, String>();

        assessment_year = new LinkedHashMap<String, String>();

        assessed_tax_list = new LinkedHashMap<String, String>();
        assessed_tax_list.put("N", "No");
        assessed_tax_list.put("Y", "Yes");

        select_pan_status = new LinkedHashMap<String, String>();

        selectSection = new LinkedHashMap<String, String>();

        fileTypeList = new LinkedHashMap<String, String>();
        fileTypeList.put("B", "15G");
        fileTypeList.put("D", "15H");
        fileTypeList.put("A", "Lower deduction u/s 197");
    }

    @Override
    public String execute() throws Exception {
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String tdsTypeCode = "";
        String return_view = "input";
        try {

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            tdsTypeCode = asmt.getTdsTypeCode();

            SetDatabaseValues databaseValues = new SetDatabaseValues();
            databaseValues.setClientCode(client.getClient_code());
            databaseValues.setEntityCode(client.getEntity_code());

            setDeductee_type(factory.getViewDeducteeTypeDAO().getDeducteeTypeAsLinkedHashMap());

            ViewDeducteeCatgDAO viewDeducteeCatgDAO = factory.getViewDeducteeCatgDAO();
            setDeductee_catg_list(viewDeducteeCatgDAO.getDeducteeCatgAsTdsType(tdsTypeCode));

            StateMastDAO stateMastDAO = factory.getStateMastDAO();
            setSelect_state(stateMastDAO.getStateCodeAsLinkedHashMap());

            CityMastDAO cityMastDAO = factory.getCityMastDAO();
            setSelect_city(cityMastDAO.getCityCodeAsHashMap());

            setHidden_tdsTypeCode(tdsTypeCode);

            ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
            setSelectGender(viewEmpCatgDAO.getEmpCatgAsLinkedHashMap());

            GetGlobalList sectionlist = new GetGlobalList();
            setSelectSection(sectionlist.getSectionList(asmt.getTdsTypeCode(), asmt.getQuarterToDate()));

            ViewPanStatusDAO viewPanStatusDAO = factory.getViewPanStatusDAO();
            setSelect_pan_status(viewPanStatusDAO.getViewPanStatusASList());

            ViewLhssysLatestAssYearDAO viewLatestAssYearDAO = factory.getViewLhssysLatestAssYearDAO();
            setAssessment_year(viewLatestAssYearDAO.getLatestAssessList());
            // System.out.println("PAN STATUS----->"+getSelect_pan_status());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        if (!utl.isnull(getAction())) {
//           System.out.println("Action------>" + getAction());
            setReadonly("false");
            setPanNoVal(getPanNoVal());
            try {
                DeducteeMast15ghDAO deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                if (getAction().equalsIgnoreCase("add")) {

                    try {

                        ViewResidentTypeDAO objviewresidenttype = factory.getViewResidentTypeDAO();
                        setResident_type(objviewresidenttype.getViewResidentList());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return_view = "add";
                } else if (getAction().equalsIgnoreCase("edit")) {
                    ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
                    ViewClientMast clientMast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                    String entity_code = clientMast.getEntity_code();
                    String client_code = clientMast.getClient_code();
                    String accYear = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();

                    deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                    DeducteeMast15gh objDeducteeMast = deductee15ghDAO.readById(entity_code, client_code, accYear, quarterNo, tdsTypeCode, getRowid_seq());
                    if (objDeducteeMast != null) {
                        setDeducteeMast15gh(objDeducteeMast);
                    } else {
                    }
                    if (getDeducteeMast15gh() != null) {
                        return_view = "edit";
                    } else {
                        return_view = "input";
                    }

                    try {
                        ViewResidentTypeDAO objviewresidenttype = factory.getViewResidentTypeDAO();
                        setResident_type(objviewresidenttype.getViewResidentList());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("getDeducteeRefAmtDetail")) {
//                    System.out.println("getDeducteeRefAmtDetail");
                    ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
                    ViewClientMast clientMast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                    String client_code = "";
                    if (utl.isnull(getDeductee_client_code())) {
                        client_code = clientMast.getClient_code();
                    } else {
                        client_code = getDeductee_client_code();
                    }
                    String accYear = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();
                    DeducteeBflagAmtTranDAO deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                    List<DeducteeBflagAmtTran> readListForDeductee = deducteeBflagAmtTranDAO.readListForDeductee15gh(clientMast.getEntity_code(), client_code, accYear, quarterNo, tdsTypeCode, getRowid_seq(), getDeductee_panno());
                    setDeducteeBflagAmtTranList(readListForDeductee);
                    StringBuilder amountDetails = new Deductee15GHSupport().getAmountDetails(readListForDeductee, asmt, getViewFlag());
                    inputStream = new ByteArrayInputStream(amountDetails.toString().getBytes("UTF-8"));

                    return_view = "deductee_type";
                } else if (getAction().equalsIgnoreCase("view")) {
                    setReadonly("true");
                    try {
                        ViewResidentTypeDAO objviewresidenttype = factory.getViewResidentTypeDAO();
                        setResident_type(objviewresidenttype.getViewResidentList());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
                    ViewClientMast clientMast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                    String entity_code = clientMast.getEntity_code();
                    String client_code = clientMast.getClient_code();
                    String acc_Year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();

                    deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                    DeducteeMast15gh objDeducteeMast = deductee15ghDAO.readById(entity_code, client_code, acc_Year, quarterNo, tdsTypeCode, getRowid_seq());
                    if (objDeducteeMast != null) {
                        setDeducteeMast15gh(objDeducteeMast);
                    } else {

                    }

                    if (getDeducteeMast15gh() != null) {
                        return_view = "view";
                    } else {
                        return_view = "input";
                    }
                } else if (getAction().equalsIgnoreCase("findName")) {
                    String le_return_message = "";
                    PanMastDAO panMastDAO = factory.getPanMastDAO();
                    PanMast dm = panMastDAO.readPanCodeById(getL_panno());
                    String l_deductee_name = "";
                    if (dm != null) {
                        l_deductee_name = ((utl.isnull(dm.getFirst_name()) ? "" : dm.getFirst_name()) + " " + ((utl.isnull(dm.getMiddle_name()) ? "" : dm.getMiddle_name())) + " " + ((utl.isnull(dm.getLast_name()) ? "" : dm.getLast_name()))).trim();
                        if (!utl.isnull(l_deductee_name)) {
                            le_return_message = l_deductee_name;
                        }
                    }

                    inputStream = new ByteArrayInputStream(le_return_message.getBytes("UTF-8"));
                    return_view = "deductee_type";
                } else if (getAction().equalsIgnoreCase("delete")) {
//                   System.out.println("Deductee Name----->" + getPanNoVal());
                    if (getRowid_seq() != null && !utl.isnull(getDeducteeName()) && !utl.isnull(getPanNoVal())) {
                        ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
                        ViewClientMast clientMast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                        String entity_code = clientMast.getEntity_code();
                        String client_code = clientMast.getClient_code();
                        String accYear = asmt.getAccYear();
                        String quarterNo = asmt.getQuarterNo();

                        DeducteeBflagAmtTranDAO deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                        List<DeducteeBflagAmtTran> bflagAmtList = deducteeBflagAmtTranDAO.getAmountTranDataList(entity_code, client_code, accYear, quarterNo, tdsTypeCode, getRowid_seq(), getPanNoVal());
                        if (bflagAmtList != null && bflagAmtList.size() > 0) {
                            int delRecordCount = 0;
                            for (DeducteeBflagAmtTran deducteeBflagAmtTran : bflagAmtList) {
                                deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                                boolean delete_record_result = deducteeBflagAmtTranDAO.delete(deducteeBflagAmtTran);
                                if (delete_record_result) {
                                    delRecordCount++;
                                }
                            }
                            if (delRecordCount > 0) {
                                deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                DeducteeMast15gh dm = deductee15ghDAO.readById(entity_code, client_code, accYear, quarterNo, tdsTypeCode, getRowid_seq());
                                if (dm != null) {
                                    deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                    boolean delete = deductee15ghDAO.delete(dm);
                                    if (delete) {
                                        //session.put("ERRORCLASS", ErrorType.successMessage);
                                        session.put("DEDCUTERSLTMSG", "Record deleted succesfully");
                                    } else {
                                        //  session.put("ERRORCLASS", ErrorType.errorMessage);
                                        session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                                    }
                                } else {
                                    // session.put("ERRORCLASS", ErrorType.errorMessage);
                                    session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                                }

                            } else {
                                //session.put("ERRORCLASS", ErrorType.errorMessage);
                                session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                            }

                        } else {
                            deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                            DeducteeMast15gh dm = deductee15ghDAO.readById(entity_code, client_code, accYear, quarterNo, tdsTypeCode, getRowid_seq());
                            if (dm != null) {
                                deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                boolean delete = deductee15ghDAO.delete(dm);
                                if (delete) {
                                    //   session.put("ERRORCLASS", ErrorType.successMessage);
                                    session.put("DEDCUTERSLTMSG", "Record deleted succesfully");
                                } else {
                                    //    session.put("ERRORCLASS", ErrorType.errorMessage);
                                    session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                                }
                            } else {
                                //session.put("ERRORCLASS", ErrorType.errorMessage);
                                session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                            }
                        }

                    } else {
                        // session.put("ERRORCLASS", ErrorType.errorMessage);
                        session.put("DEDCUTERSLTMSG", "Some Error Occured, Could Not Delete");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return return_view;
    }

    Map<String, Object> session;
    private LinkedHashMap<String, String> deductee_catg_list;
    private List<DeducteeMast15gh> deducteeList;
    private List<DeducteeMast15gh> deductee15ghList;
    private LinkedHashMap<String, String> resident_type;
    private LinkedHashMap<String, String> assessed_tax_list;
    private LinkedHashMap<String, String> deductee_type;
    private LinkedHashMap<String, String> tds_code_list;
    private LinkedHashMap<String, String> select_country;
    private LinkedHashMap<String, String> select_state;
    private LinkedHashMap<String, String> assessment_year;
    private LinkedHashMap<String, String> select_pan_status;
    private LinkedHashMap<String, String> select_tds_deduct_reason;
    private LinkedHashMap<String, String> selectSection;
    private HashMap<String, String> select_city;
    private HashMap<String, String> selectGender;
    private LinkedHashMap<String, String> fileTypeList;
    private LinkedHashMap<String, String> panStatusList;
    private List<DeducteeBflagAmtTran> deducteeBflagAmtTranList;
    private String hidden_tdsTypeCode;
    private String action;
    private String readonly;
    private String PanNoVal;
    private Long rowid_seq;
    private String l_panno;
    private String deductee_panno;
    private String deductee_client_code;
    private String DeducteeName;
    private String viewFlag;
    private InputStream inputStream;
    Util utl;
    private DeducteeMast15gh deducteeMast15gh;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getL_panno() {
        return l_panno;
    }

    public void setL_panno(String l_panno) {
        this.l_panno = l_panno;
    }

    public LinkedHashMap<String, String> getDeductee_catg_list() {
        return deductee_catg_list;
    }

    public void setDeductee_catg_list(LinkedHashMap<String, String> deductee_catg_list) {
        this.deductee_catg_list = deductee_catg_list;
    }

    public List<DeducteeMast15gh> getDeducteeList() {
        return deducteeList;
    }

    public void setDeducteeList(List<DeducteeMast15gh> deducteeList) {
        this.deducteeList = deducteeList;
    }

    public List<DeducteeMast15gh> getDeductee15ghList() {
        return deductee15ghList;
    }

    public void setDeductee15ghList(List<DeducteeMast15gh> deductee15ghList) {
        this.deductee15ghList = deductee15ghList;
    }

    public LinkedHashMap<String, String> getResident_type() {
        return resident_type;
    }

    public void setResident_type(LinkedHashMap<String, String> resident_type) {
        this.resident_type = resident_type;
    }

    public LinkedHashMap<String, String> getAssessed_tax_list() {
        return assessed_tax_list;
    }

    public void setAssessed_tax_list(LinkedHashMap<String, String> assessed_tax_list) {
        this.assessed_tax_list = assessed_tax_list;
    }

    public LinkedHashMap<String, String> getDeductee_type() {
        return deductee_type;
    }

    public void setDeductee_type(LinkedHashMap<String, String> deductee_type) {
        this.deductee_type = deductee_type;
    }

    public LinkedHashMap<String, String> getTds_code_list() {
        return tds_code_list;
    }

    public void setTds_code_list(LinkedHashMap<String, String> tds_code_list) {
        this.tds_code_list = tds_code_list;
    }

    public LinkedHashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(LinkedHashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public LinkedHashMap<String, String> getSelect_state() {
        return select_state;
    }

    public void setSelect_state(LinkedHashMap<String, String> select_state) {
        this.select_state = select_state;
    }

    public LinkedHashMap<String, String> getAssessment_year() {
        return assessment_year;
    }

    public void setAssessment_year(LinkedHashMap<String, String> assessment_year) {
        this.assessment_year = assessment_year;
    }

    public LinkedHashMap<String, String> getSelect_pan_status() {
        return select_pan_status;
    }

    public void setSelect_pan_status(LinkedHashMap<String, String> select_pan_status) {
        this.select_pan_status = select_pan_status;
    }

    public LinkedHashMap<String, String> getSelect_tds_deduct_reason() {
        return select_tds_deduct_reason;
    }

    public void setSelect_tds_deduct_reason(LinkedHashMap<String, String> select_tds_deduct_reason) {
        this.select_tds_deduct_reason = select_tds_deduct_reason;
    }

    public HashMap<String, String> getSelect_city() {
        return select_city;
    }

    public void setSelect_city(HashMap<String, String> select_city) {
        this.select_city = select_city;
    }

    public HashMap<String, String> getSelectGender() {
        return selectGender;
    }

    public void setSelectGender(HashMap<String, String> selectGender) {
        this.selectGender = selectGender;
    }

    public String getHidden_tdsTypeCode() {
        return hidden_tdsTypeCode;
    }

    public void setHidden_tdsTypeCode(String hidden_tdsTypeCode) {
        this.hidden_tdsTypeCode = hidden_tdsTypeCode;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public DeducteeMast15gh getDeducteeMast15gh() {
        return deducteeMast15gh;
    }

    public void setDeducteeMast15gh(DeducteeMast15gh deducteeMast15gh) {
        this.deducteeMast15gh = deducteeMast15gh;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getPanNoVal() {
        return PanNoVal;
    }

    public void setPanNoVal(String PanNoVal) {
        this.PanNoVal = PanNoVal;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public LinkedHashMap<String, String> getFileTypeList() {
        return fileTypeList;
    }

    public void setFileTypeList(LinkedHashMap<String, String> fileTypeList) {
        this.fileTypeList = fileTypeList;
    }

    public LinkedHashMap<String, String> getPanStatusList() {
        return panStatusList;
    }

    public void setPanStatusList(LinkedHashMap<String, String> panStatusList) {
        this.panStatusList = panStatusList;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getDeductee_client_code() {
        return deductee_client_code;
    }

    public void setDeductee_client_code(String deductee_client_code) {
        this.deductee_client_code = deductee_client_code;
    }

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

    public List<DeducteeBflagAmtTran> getDeducteeBflagAmtTranList() {
        return deducteeBflagAmtTranList;
    }

    public void setDeducteeBflagAmtTranList(List<DeducteeBflagAmtTran> deducteeBflagAmtTranList) {
        this.deducteeBflagAmtTranList = deducteeBflagAmtTranList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDeducteeName() {
        return DeducteeName;
    }

    public void setDeducteeName(String DeducteeName) {
        this.DeducteeName = DeducteeName;
    }

}
