/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.DateTimeUtil;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class SetAssessmentAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
                       
        String returnValue = "success";
        String returnMsg = "";
        String defaultLogo = "resources/images/global/taxcpcLogoWhite.jpg";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast workingUser = (ViewClientMast) session.get("WORKINGUSER");
            UserMast userMastt = (UserMast) session.get("LOGINUSER");
            String module = (String) session.get("MODULE");
            String login_id = userMastt.getLogin_id();
            String login_psw = userMastt.getLogin_pwd();
            boolean adminuser=false;
            if(!utl.isnull(login_id) && login_id.equalsIgnoreCase("ADMIN")){
              if(!utl.isnull(login_psw) && login_psw.equalsIgnoreCase("ADMIN")){
                adminuser=true;  
              }
                 
            }
            
            if(adminuser){
                utl.generateLog("admin user dashboard", "");
                session.put("ADMINUSER", "ADMINUSER");
                returnValue = "adminDashboard";
                Assessment asst = new Assessment();
                UserMast userMast = (UserMast) session.get("LOGINUSER");
                asst.setAccYear(utl.isnull(userMast.getDefault_acc_year()) ? "20-21" : userMast.getDefault_acc_year());

                asst.setQuarterNo(utl.isnull(userMast.getDefault_quarter_no()) ? "1" : userMast.getDefault_quarter_no());
                asst.setTdsTypeCode(utl.isnull(userMast.getDefault_tds_type_code()) ? "26Q" : userMast.getDefault_tds_type_code());
                try {
                    asst.setQuarterFromDate(dateTime.getQuarterFromDate(asst.getAccYear(), Integer.parseInt(asst.getQuarterNo())));
                    asst.setQuarterToDate(dateTime.getQuarterToDate(asst.getAccYear(), Integer.parseInt(asst.getQuarterNo())));
                    asst.setYearBegDate(dateTime.getYearBegDate(asst.getAccYear()));
                    asst.setYearEndDate(dateTime.getYearEndDate(asst.getAccYear()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }
                session.put("ASSESSMENT", asst);
                session.put("ACTIVE_TAB", "dashboard");

                ClientMastDAO clientMastDAO = factory.getClientMastDAO();
                String defaultDeductor = clientMastDAO.getDefaultDeductor(workingUser.getEntity_code(), workingUser.getClient_code(), utl);

                if (!utl.isnull(defaultDeductor)) {
                    session.put("DEDUCTOR", defaultDeductor);
                }
                try {
                    AssessmentBandValue asmtListValues = new AssessmentBandValue();
                    List<String> dynAccYearOptions = (List<String>) session.get("DYNACCYEAR");

                    if (dynAccYearOptions == null || dynAccYearOptions.isEmpty()) {
                        setSelectAssessmentyear(asmtListValues.getSelectAssessmentyear());
                    } else {
                        setSelectAssessmentyear(new LinkedList<>(dynAccYearOptions));
                    }

                    String startYr = (String) session.get("startAccYr");
                    if (!utl.isnull(startYr) && getSelectAssessmentyear() != null && getSelectAssessmentyear().size() > 1) {
                        List<String> selectAssessmentyear1 = getSelectAssessmentyear().stream()
                                .filter((entry) -> (entry.startsWith(startYr)))
                                .collect(Collectors.toList());

                        setSelectAssessmentyear(new LinkedList<>(selectAssessmentyear1));
                    }
                    session.put("sessionAccYearList", getSelectAssessmentyear());
                    session.put("sessionQtrList", asmtListValues.getSelectQuarter());
                    session.put("sessionTdsTypeCodeList", asmtListValues.getSelectTdsType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**
                 * Setting up login_entity_logo and put in session variable
                 */
                try {
                    LoginValidatorSupport lvs = new LoginValidatorSupport();
                    String entity_logo = lvs.getSessionEntityLogo(workingUser.getEntity_code());
                    if (!utl.isnull(entity_logo) && !utl.isnull(workingUser.getEntity_code())) {
                        session.put("SESSION_ENTITY_LOGO", entity_logo);
                    } else {
                        session.put("SESSION_ENTITY_LOGO", defaultLogo);
                    }
                } catch (Exception e) {
                    session.put("SESSION_ENTITY_LOGO", defaultLogo);
                }
            }else{

            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("changeAssessment")) {
                    FileOptUtil optUtil = new FileOptUtil();

                    if (getAssessment() != null) {
                        Assessment asst = new Assessment();
                        if (!utl.isnull(getAssessment().getAccYear())) {
                            asst.setAccYear(getAssessment().getAccYear());

                        }
                        if (!utl.isnull(getAssessment().getQuarterNo())) {
                            asst.setQuarterNo(getAssessment().getQuarterNo());

                        } else if (module.equals("M")) {
                            asst.setQuarterNo(""); // For 'all' option in dropdown

                        }
                        if (!utl.isnull(getAssessment().getTdsTypeCode())) {
                            asst.setTdsTypeCode(getAssessment().getTdsTypeCode());

                        } else if (module.equals("M")) {
                            asst.setTdsTypeCode(""); // For 'all' option in dropdown

                        }
                        try {
                            asst.setQuarterFromDate(dateTime.getQuarterFromDate(getAssessment().getAccYear(), Integer.parseInt(getAssessment().getQuarterNo())));
                            asst.setQuarterToDate(dateTime.getQuarterToDate(getAssessment().getAccYear(), Integer.parseInt(getAssessment().getQuarterNo())));
                            asst.setYearBegDate(dateTime.getYearBegDate(asst.getAccYear()));
                            asst.setYearEndDate(dateTime.getYearEndDate(asst.getAccYear()));
                        } catch (Exception e) {
                        }

                        Object templateName = optUtil.getTaxcpcDownloadFilename(workingUser, asst);
                        session.put("EXCELTEMPLATENAME", templateName);
                        session.put("ASSESSMENT", asst);
                        returnMsg = "success";
                    } else {
                        returnMsg = "error";

                    }
                    returnValue = "ajaxSuccess";

                } else if (getAction().equalsIgnoreCase("changeDeductor")) {
                    if (!utl.isnull(getClientCode())) {

                        ViewClientMastDAO viewClientMastDAO = factory.getViewClientMastDAO();
                        // ViewClientMast clientMast = viewClientMastDAO.readById(getClientCode(), true);
                        ViewClientMast clientMast = viewClientMastDAO.readClientByClientCode(getClientCode());
                        if (clientMast != null) {
                            session.put("WORKINGUSER", clientMast);
                            ClientMastDAO clientMastDAO = factory.getClientMastDAO();
                            String defaultDeductor = clientMastDAO.getDefaultDeductor(clientMast.getEntity_code(), clientMast.getClient_code(), utl);
                            if (!utl.isnull(defaultDeductor)) {

                                session.put("DEDUCTOR", defaultDeductor);
                                returnMsg = "success";
                            }
                        }

                    }
                    returnValue = "ajaxSuccess";
                }
            } else { 
                if (utl.isnull(workingUser.getTanno())) {
                    returnValue = "newlyRegisteredClient";
                }
                Assessment asst = new Assessment();
                UserMast userMast = (UserMast) session.get("LOGINUSER");
                asst.setAccYear(utl.isnull(userMast.getDefault_acc_year()) ? "20-21" : userMast.getDefault_acc_year());

                asst.setQuarterNo(utl.isnull(userMast.getDefault_quarter_no()) ? "1" : userMast.getDefault_quarter_no());
                asst.setTdsTypeCode(utl.isnull(userMast.getDefault_tds_type_code()) ? "26Q" : userMast.getDefault_tds_type_code());
                try {
                    asst.setQuarterFromDate(dateTime.getQuarterFromDate(asst.getAccYear(), Integer.parseInt(asst.getQuarterNo())));
                    asst.setQuarterToDate(dateTime.getQuarterToDate(asst.getAccYear(), Integer.parseInt(asst.getQuarterNo())));
                    asst.setYearBegDate(dateTime.getYearBegDate(asst.getAccYear()));
                    asst.setYearEndDate(dateTime.getYearEndDate(asst.getAccYear()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }
                session.put("ASSESSMENT", asst);
                session.put("ACTIVE_TAB", "dashboard");

                ClientMastDAO clientMastDAO = factory.getClientMastDAO();
                String defaultDeductor = clientMastDAO.getDefaultDeductor(workingUser.getEntity_code(), workingUser.getClient_code(), utl);

                if (!utl.isnull(defaultDeductor)) {
                    session.put("DEDUCTOR", defaultDeductor);
                }
                try {
                    AssessmentBandValue asmtListValues = new AssessmentBandValue();
                    List<String> dynAccYearOptions = (List<String>) session.get("DYNACCYEAR");

                    if (dynAccYearOptions == null || dynAccYearOptions.isEmpty()) {
                        setSelectAssessmentyear(asmtListValues.getSelectAssessmentyear());
                    } else {
                        setSelectAssessmentyear(new LinkedList<>(dynAccYearOptions));
                    }

                    String startYr = (String) session.get("startAccYr");
                    if (!utl.isnull(startYr) && getSelectAssessmentyear() != null && getSelectAssessmentyear().size() > 1) {
                        List<String> selectAssessmentyear1 = getSelectAssessmentyear().stream()
                                .filter((entry) -> (entry.startsWith(startYr)))
                                .collect(Collectors.toList());

                        setSelectAssessmentyear(new LinkedList<>(selectAssessmentyear1));
                    }
                    session.put("sessionAccYearList", getSelectAssessmentyear());
                    session.put("sessionQtrList", asmtListValues.getSelectQuarter());
                    session.put("sessionTdsTypeCodeList", asmtListValues.getSelectTdsType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**
                 * Setting up login_entity_logo and put in session variable
                 */
                try {
                    LoginValidatorSupport lvs = new LoginValidatorSupport();
                    String entity_logo = lvs.getSessionEntityLogo(workingUser.getEntity_code());
                    if (!utl.isnull(entity_logo) && !utl.isnull(workingUser.getEntity_code())) {
                        session.put("SESSION_ENTITY_LOGO", entity_logo);
                    } else {
                        session.put("SESSION_ENTITY_LOGO", defaultLogo);
                    }
                } catch (Exception e) {
                    session.put("SESSION_ENTITY_LOGO", defaultLogo);
                }
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnValue;
    }

    public SetAssessmentAction() {
        utl = new Util();
        dateTime = new DateTimeUtil();
    }//end constructor

    private final Util utl;
    private final DateTimeUtil dateTime;
    private Map<String, Object> session;
    private String action;
    private String clientCode;
    InputStream inputStream;
    private Assessment assessment;
    private LinkedList<String> selectAssessmentyear;

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public LinkedList<String> getSelectAssessmentyear() {
        return selectAssessmentyear;
    }

    public void setSelectAssessmentyear(LinkedList<String> selectAssessmentyear) {
        this.selectAssessmentyear = selectAssessmentyear;
    }

}
