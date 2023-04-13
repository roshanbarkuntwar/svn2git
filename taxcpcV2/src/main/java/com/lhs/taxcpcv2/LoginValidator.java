/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserMastDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.DateTimeUtil;
import globalUtilities.EncryptDecryptUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class LoginValidator extends ActionSupport implements SessionAware, ServletRequestAware {

    @Override
    public String execute() throws Exception {
        String return_view = "input";

        if (!utl.isnull(login_id) && !utl.isnull(login_pwd)) {
            session.remove("LOGINUSER");
            session.remove("WORKINGUSER");
            session.remove("FORMLOGIN");

            session.put("ERRORCLASS", MessageType.errorMessage);
            try {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                UserMastDAO userDao = factory.getUserMastDAO();
                UserMast user = userDao.getUserByLoginAndPasswordOnly(login_id, login_pwd);
                utl.generateLog("New Login Request(DataBase)", "");
                if (user != null) {
                    ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
                    // ViewClientMast client = clientdao.readById(user.getClient_code(), true);
                    ViewClientMast client = clientdao.readClientByClientCode(user.getClient_code());
                    GetDBEntityMastData entMast = new GetDBEntityMastData();
                    String entityCodeValidation = entMast.getEntityCodeValidation(client.getEntity_code());
                    boolean entityMatchDbApp = entMast.getEntityCheckConfig(client.getEntity_code());
                    if(user.getEntity_code().equalsIgnoreCase(client.getEntity_code())){
                        if (entityMatchDbApp) {//check entitycodeavailable in properties file
                            if (client != null && !utl.isnull(client.getApprovedby()) && client.getApproveddate() != null) {
                                if ((entityCodeValidation != null) && !utl.isnull(entityCodeValidation) && (entityCodeValidation != null)) {
                                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                    boolean dateChkVaribale = true;
                                    String closedRemark = "Login Validity Expire ,Contact Taxcpc Support Team";
                                    if (client.getClosed_date() != null) {
                                        if (!utl.isnull(client.getClosed_remark())) {
                                            closedRemark = client.getClosed_remark();
                                        }
                                        try {
                                            Date l_date = new Date();
                                            String sysdate = df.format(l_date);
                                            String closed_date = df.format(client.getClosed_date());
                                            if (df.parse(closed_date).equals(df.parse(sysdate))) {
                                                dateChkVaribale = false;
                                            }
                                            if (df.parse(closed_date).after(df.parse(sysdate))) {
                                                dateChkVaribale = true;
                                            }
                                            if (df.parse(closed_date).before(df.parse(sysdate))) {
                                                dateChkVaribale = false;
                                            }
                                        } catch (Exception e) {
                                            dateChkVaribale = false;
                                            e.printStackTrace();
                                        }
                                    }
                                    if (dateChkVaribale) {
                                        LoginValidatorSupport lvs = new LoginValidatorSupport();
                                        Long l_loginExpDays = lvs.getLoginExpiryDays(client);
                                        Long l_trialValidRunDays = lvs.getValidateGenerateFUV(client.getEntity_code());

                                        Date approvedate = client.getApproveddate();
                                        String checkValidFUVGen = lvs.checkLoginValidity(approvedate, l_loginExpDays, l_trialValidRunDays);// this function is used to check login validity
                                        if (!utl.isnull(checkValidFUVGen)) {
                                            session.put("LOGINEXPMSG", checkValidFUVGen);
                                        } else {
                                            session.put("LOGINEXPMSG", "");
                                        }

                                        session.put("LOGINUSER", user);
                                        session.put("WORKINGUSER", client);
                                        session.put("CLIENTPANNO", client.getPanno());
                                        session.put("CLIENTTANNO", client.getTanno());
                                        session.put("CLIENTLOGINLEVEL", client.getCode_level());
                                        session.put("FORMLOGIN", getFormLogin());

                                        Stack reqSeqStk = new Stack();
                                        session.put("USRREQSEQ", reqSeqStk);

                                        if (String.valueOf(user.getUser_level()).equalsIgnoreCase("0")) {
                                            session.put("MODULE", "A");//ADMIN MODULE
                                        } else {
                                            session.put("MODULE", "R");//REGULAR MODULE
                                        }
                                        utl.generateLog("\nlogged in client details", "");
                                        utl.generateLog("client entity code", client.getEntity_code());
                                        utl.generateLog("client code", client.getClient_code());
                                        utl.generateLog("client tanno", client.getTanno());
                                        utl.generateLog("client bank branch code", client.getBank_branch_code());

                                        String contextPath = request.getContextPath();
                                        session.put("CONTEXTPATH", contextPath);
                                        try {
                                            String l_timeStamp = dtl.get_sysdate("dd MMM yyyy hh:mm:ss a");
                                            session.put("SESSIONTIMESTEMP", l_timeStamp);
                                        } catch (Exception e) {
                                        }

                                        try {
                                            LhssysParameters mdule_para = factory.getLhssysParametersDAO().readDataAsParameterAndEntity("MODULE_TYPE_STR", client.getEntity_code());

                                            if (mdule_para != null && !utl.isnull(mdule_para.getParameter_value())) {
                                                List<String> moduleList = Stream.of(mdule_para.getParameter_value().split("#")).collect(Collectors.toList());
                                                session.put("MODULESLIST", moduleList);
                                                utl.generateLog("moduleList not null ", "");
                                            } else {
                                                utl.generateLog("moduleList  null ", "");

                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            if (!utl.isnull(contextPath)) {
//                                            utl.generateLog("contextPath " ,contextPath);
                                            String startYr = contextPath.substring(contextPath.length(), contextPath.length());
//                                            utl.generateLog("startYr " ,startYr);
                                                try {
                                                    startYr = utl.isNumeric(startYr) ? startYr : "";
                                                } catch (Exception e) {
                                                    startYr = "";
                                                }
                                                List<String> dynAccYearOptions = lvs.getDynAccYearOptions(startYr);
//                                                dynAccYearOptions.forEach(System.out::println);
                                                session.put("DYNACCYEAR", dynAccYearOptions);
                                                session.put("startAccYr", startYr);
                                            } else {
//                                             utl.generateLog("contextPath  null " ,"");
                                                session.put("DYNACCYEAR", null);
                                                session.put("startAccYr", null);
                                            }
                                        } catch (Exception e) {
                                            session.put("DYNACCYEAR", null);
                                            session.put("startAccYr", null);
                                            utl.generateLog("Dynamic acc year not found!\n"
                                                    + "Populating default acc year options.", e.getMessage());
                                        }
                                        return_view = "success";//after login success page return
                                    } else {
                                        session.put("ERRORCLASS", MessageType.errorMessage);
                                        addActionError(closedRemark);
                                    }
                                } else {
                                    session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                                    session.put("GLOBALMSGVALUE", "Bank Code Is Not Configured");
                                    utl.generateLog("'" + client.getEntity_code() + "'" + " Entity Code is not available in Entity_mast: ", "");
                                }
                            } else {
                                session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                                session.put("GLOBALMSGVALUE", "You are not an approved user, So Kindly check your email for verification or contact Admin");
                                utl.generateLog("Error in getting value from database for username : " + login_id
                                        + "\tand password : ", login_pwd);
                            }
                        } else {
                            session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                            session.put("GLOBALMSGVALUE", "Bank Code Is Not Configured !");
                            utl.generateLog("'" + client.getEntity_code() + "'" + " Entity Code is not available in Config-File: ", "");
                        }
                    } else {
                        session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                        session.put("GLOBALMSGVALUE", "Branch  and User Configuration Mismatch");
                        utl.generateLog("User-Entity-Code '" + user.getEntity_code() + "'" + " and Client-Entity Code  " + client.getEntity_code() + "is not match", "");
                    }
                } else {
                    session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                    session.put("GLOBALMSGVALUE", "Login Id Or Password Is Incorrect");
                    utl.generateLog("Login Id Or Password Is Incorrect: username=" + login_id + "\t, password=" + login_pwd, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                session.put("GLOBALMSGVALUE", "Could not provide login, Please try after some time");
                utl.generateLog("Error in getting value from database for username : " + login_id
                        + "\tand password : ", login_pwd + "\nException Log : " + e.getMessage());
            }
        } else {
            this.setupSignupFeatureForLoginPage();

            if (!utl.isnull(getLoginId()) && !utl.isnull(getPassword())) {
                setLoginId(encryptDecryptUtil.decrypt(getLoginId(), "lhswma@1234"));
                setPassword(encryptDecryptUtil.decrypt(getPassword(), "lhswma@1234"));
            } else {
                setLoginId(null);
                setPassword(null);
            }
        }

        return return_view;
    }//end method

    private void setupSignupFeatureForLoginPage() {
        String googleSigunupFtrFlag = "F";
        String taxcpcSigunupFtrFlag = "F";
        String forgetPasswordFtrFlag = "F";
        String changeLogoFtrFlag = "F";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            LhssysParameters google_sigunupFtr_para = factory.getLhssysParametersDAO().readParametersBy("GOOGLE_SIGNUP_FEATURE");
            LhssysParameters taxcpc_sigunupFtr_para = factory.getLhssysParametersDAO().readParametersBy("TAXCPC_SIGNUP_FEATURE");
            LhssysParameters forget_passwordFtr_para = factory.getLhssysParametersDAO().readParametersBy("FORGET_PASSWORD_FEATURE");
            LhssysParameters change_logoFtr_para = factory.getLhssysParametersDAO().readParametersBy("CHANGE_LOGO_FEATURE");
            if (google_sigunupFtr_para != null && taxcpc_sigunupFtr_para != null && forget_passwordFtr_para != null && change_logoFtr_para != null) {
                googleSigunupFtrFlag = google_sigunupFtr_para.getParameter_value();
                taxcpcSigunupFtrFlag = taxcpc_sigunupFtr_para.getParameter_value();
                forgetPasswordFtrFlag = forget_passwordFtr_para.getParameter_value();
                changeLogoFtrFlag = change_logoFtr_para.getParameter_value();
            }
        } catch (Exception e) {
            googleSigunupFtrFlag = "F";
            taxcpcSigunupFtrFlag = "F";
            forgetPasswordFtrFlag = "F";
            changeLogoFtrFlag = "F";
            e.printStackTrace();
        }
        session.put("GSIGNUPFTR", googleSigunupFtrFlag);
        session.put("TSIGNUPFTR", taxcpcSigunupFtrFlag);
        session.put("FORGETPASSFTR", forgetPasswordFtrFlag);
        session.put("CHANGELOGOFTR", changeLogoFtrFlag);
    }//end method

    public LoginValidator() {
        utl = new Util();
        dtl = new DateTimeUtil();
        encryptDecryptUtil = new EncryptDecryptUtil();
    }//end construcotr

    private final globalUtilities.Util utl;
    private final globalUtilities.DateTimeUtil dtl;
    private String login_id;
    private String login_pwd;
    private String validatelogin;
    private Map<String, Object> session;
    private HttpServletRequest request;
    private String mx_upd_res;
    private String assessmentYear;

    private String loginId;
    private String password;
    private String formLogin;

    private final EncryptDecryptUtil encryptDecryptUtil;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getValidatelogin() {
        return validatelogin;
    }

    public void setValidatelogin(String validatelogin) {
        this.validatelogin = validatelogin;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getMx_upd_res() {
        return mx_upd_res;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public void setMx_upd_res(String mx_upd_res) {
        this.mx_upd_res = mx_upd_res;
    }

    public String getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(String formLogin) {
        this.formLogin = formLogin;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }//end method

}//end class
