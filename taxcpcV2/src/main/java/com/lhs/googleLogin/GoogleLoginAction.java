/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.googleLogin;

import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.HibernateDAOFactory;
import dao.generic.HibernateUtil;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.UserMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.json.JSONObject;

/**
 *
 * @author gaurav.khanzode
 */
public class GoogleLoginAction extends ActionSupport implements SessionAware {

    private final Util utl;

    private Map<String, Object> session;
    private String loginAction;
    private String profileId;
    private String profileEmail;
    private String signupTanno;
    private String signupAccountName;
    private String signupMobileNo;
    private String signupPassword;
    private InputStream inputStream;

    public GoogleLoginAction() {
        this.utl = new Util();
    }//End Constructor

    @Override
    public String execute() throws Exception {
        String returnResult = "ajax_result";
        String authResult = "invalidRequest";

        utl.generateLog("Inside Google Login Action: email=", getProfileEmail());

        DAOFactory factory = DAOFactory.instance(HibernateDAOFactory.HIBERNATE);
        if (!utl.isnull(getLoginAction())) {
            ClientMastDAO clientMastDAO = factory.getClientMastDAO();
            ClientMast clientMastAccount = clientMastDAO.verifyGoogleAccountLogin(getProfileId(), getProfileEmail());
            if (getLoginAction().equals("authenticateUser") && !utl.isnull(getProfileId()) && !utl.isnull(getProfileEmail())) {
                try {
                    GoogleAuthenticationBean googleAuthenticationBean = new GoogleAuthenticationBean();
                    if (clientMastAccount != null) {
                        googleAuthenticationBean.setAuthenticationStatus("authenticated");
                        googleAuthenticationBean.setProfileId(getProfileId());
                        googleAuthenticationBean.setProfileEmail(getProfileEmail());
                        googleAuthenticationBean.setLoginId(clientMastAccount.getLogin_id());
                        googleAuthenticationBean.setLoginPassword(clientMastAccount.getLogin_pwd());
                    } else {
                        googleAuthenticationBean.setAuthenticationStatus("notRegistered");
                    }
                    JSONObject userObject = new JSONObject();
                    userObject.put("authenticationResponse", googleAuthenticationBean);
                    authResult = userObject.toString();
//                    utl.generateLog("Google Account", authResult);
                } catch (Exception e) {
                    authResult = "";
                    utl.generateLog("Google account is not authorized...", "");
                    e.printStackTrace();
                }
            } else if (getLoginAction().equals("registerUser")) {
                if (clientMastAccount != null) {
                    authResult = "userAlreadyRegistered";

                } else {
                    Session openSession = HibernateUtil.getSessionFactory().openSession();
                    Transaction tranxn = openSession.beginTransaction();
                    boolean commitChanges = false;
                    try {
                        ClientMast signupClient = new ClientMast();

                        signupClient.setClient_code(null);
                        signupClient.setEmail_id(getProfileEmail().toLowerCase());
                        signupClient.setAppr_verify_code(getProfileId().trim());
                        signupClient.setLogin_id(getProfileEmail());
                        signupClient.setLogin_pwd(getProfileId());
                        signupClient.setClient_name(getSignupAccountName());
                        signupClient.setLastupdate(new java.util.Date());
                        signupClient.setEntity_code("B0");
                        signupClient.setRemark("Registered with Google Account");
                        signupClient.setCode_level("6");
                        signupClient.setClient_level_type("6");
                        signupClient.setApprovedby("ADMIN");
                        signupClient.setApproveddate(new java.util.Date());

                        String clientCode = null;
                        try {
                            clientCode = (String) openSession.save(signupClient);
                            commitChanges = true;
                        } catch (Exception e) {
                            clientCode = null;
                            authResult = "userUnableToRegistered";
                            tranxn.rollback();
                            commitChanges = false;
                            e.printStackTrace();
                        }

                        String userCode = null;
                        if (commitChanges && !utl.isnull(clientCode)) {
                            commitChanges = false;

                            UserMast signupUser = new UserMast();

                            signupUser.setUser_code(null);
                            signupUser.setUser_name(getSignupAccountName().toUpperCase());
                            signupUser.setShort_name(getSignupAccountName().toUpperCase());
                            signupUser.setLogin_id(getProfileEmail());
                            signupUser.setLogin_pwd(getProfileId());
                            signupUser.setClient_code(clientCode);
                            signupUser.setEntity_code("B0");
                            signupUser.setUser_level(6l);
                            signupUser.setAdd_right(1l);
                            signupUser.setDelete_right(1l);
                            signupUser.setEdit_right(1l);
                            signupUser.setApprove_right(1l);
                            signupUser.setLastupdate(new java.util.Date());

                            try {
                                userCode = (String) openSession.save(signupUser);
                                commitChanges = true;
                            } catch (Exception e) {
                                userCode = null;
                                authResult = "userUnableToRegistered";
                                tranxn.rollback();
                                commitChanges = false;
                                e.printStackTrace();
                            }
                        }

                        if (commitChanges && !utl.isnull(clientCode) && !utl.isnull(userCode)) {
                            tranxn.commit();
                            authResult = "userRegisteredSuccessfully";
                        }
                    } catch (Exception e) {
                        tranxn.rollback();
                    } finally {
                        if (openSession.isOpen()) {
                            openSession.close();
                        }
                    }
                }
            }
        }
        this.inputStream = new ByteArrayInputStream(authResult.getBytes(StandardCharsets.UTF_8));
        return returnResult;
    }// End Method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getLoginAction() {
        return loginAction;
    }

    public void setLoginAction(String loginAction) {
        this.loginAction = loginAction;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public String getSignupTanno() {
        return signupTanno;
    }

    public void setSignupTanno(String signupTanno) {
        this.signupTanno = signupTanno;
    }

    public String getSignupAccountName() {
        return signupAccountName;
    }

    public void setSignupAccountName(String signupAccountName) {
        this.signupAccountName = signupAccountName;
    }

    public String getSignupMobileNo() {
        return signupMobileNo;
    }

    public void setSignupMobileNo(String signupMobileNo) {
        this.signupMobileNo = signupMobileNo;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}//End Class
