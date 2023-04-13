/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.LhssysParametersDAO;
import dao.UserMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.SendMailUtil;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class SignupUser extends ActionSupport implements SessionAware {

    public SignupUser() {
        utl = new Util();

    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        String return_msg = "";
        if (!utl.isnull(getAction())) {
            try {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                ClientMastDAO clientDAO;
                LhssysParametersDAO lhssysParametersDAO;
                if (getAction().equalsIgnoreCase("registerNewUser")) {
                    String emailExistsStatus = this.checkIfUserNotExistsWithEmail(factory, getClientMast().getLogin_id());
                    if (emailExistsStatus.equals("valid")) {
                        try {
                            ClientMast cm = getClientMast();
                            String l_def_entity_code = "B0";
                            boolean client_saved = false;
                            lhssysParametersDAO = factory.getLhssysParametersDAO();
                            LhssysParameters readDefaultClientEntityCode = lhssysParametersDAO.readDataAsParameterAndEntity("DEFAULT_ENTITY_CODE", "");
                            if (readDefaultClientEntityCode != null) {
                                l_def_entity_code = readDefaultClientEntityCode.getParameter_value();
                            }

                            Integer randomNumber = null;
                            try {
                                randomNumber = utl.getRandomNumber(100000, 999999);//OTP

                                cm.setEntity_code(l_def_entity_code);
                                cm.setClient_name(cm.getClient_name());
                                cm.setEmail_id(cm.getLogin_id().toLowerCase());
                                cm.setAppr_verify_code(String.valueOf(randomNumber));
                                cm.setTanno(cm.getTanno().toUpperCase());
                                cm.setCode_level("6");
                                cm.setClient_level_type("1");
                                cm.setFlag("O"); // Online registration
                                cm.setInitiate_date(new Date());
                                cm.setLastupdate(new Date());

                                clientDAO = factory.getClientMastDAO();
                                //Client Master Save
                                client_saved = clientDAO.save(cm);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (client_saved) {
                                if (randomNumber == null) {
                                    return_msg = "otpError";
                                } else {
                                    return_msg = "registeredSuccessfully#" + randomNumber;
                                }
                                String sender = "";
                                String senderPassword = "";
                                String cc = "";
                                String host = "";
                                String port = "";

                                lhssysParametersDAO = factory.getLhssysParametersDAO();
                                LhssysParameters readOutgoingMailSender = lhssysParametersDAO.readDataAsParameterAndEntity("OUTGOING_MAIL_SENDER", "");
                                if (readOutgoingMailSender != null) {
                                    sender = readOutgoingMailSender.getParameter_value();
                                }
//                        System.out.println("2.....");
                                lhssysParametersDAO = factory.getLhssysParametersDAO();
                                LhssysParameters readOutgoingMailSenderPassword = lhssysParametersDAO.readDataAsParameterAndEntity("OUTGOING_MAIL_SENDER_PASSWORD", "");
                                if (readOutgoingMailSenderPassword != null) {
                                    senderPassword = readOutgoingMailSenderPassword.getParameter_value();
                                }
                                lhssysParametersDAO = factory.getLhssysParametersDAO();
                                LhssysParameters readIncomingMailHost = lhssysParametersDAO.readDataAsParameterAndEntity("INCOMING_MAIL_HOST", "");
                                if (readIncomingMailHost != null) {
                                    host = readIncomingMailHost.getParameter_value();
                                }
                                lhssysParametersDAO = factory.getLhssysParametersDAO();
                                LhssysParameters readIncomingMailPort = lhssysParametersDAO.readDataAsParameterAndEntity("INCOMING_MAIL_PORT", "");
                                if (readIncomingMailPort != null) {
                                    port = readIncomingMailPort.getParameter_value();
                                }
                                String imagePath = getServletContext().getContextPath() + "/resources/images/global/TAXCPC-logo.png";
                                SendMailUtil sm = new SendMailUtil();
                                StringBuilder sb = new StringBuilder();

                                sb.append("<div style=\"background-color: #F7F5F5; width: 600px; padding : 15px;\">\n"
                                        + "         <img src=\"cid:image\">\n"
                                        + "            <div>\n"
                                        + "                <b> Dear User,</b>\n"
                                        + "            </div>\n"
                                        + "            <div style=\"margin-top: 10px;\">\n"
                                        + "                <span style=\"font-weight: 500;\">Welcome to TaxCPC</span><br> <br>\n"
                                        + "                Login Id : " + cm.getLogin_id() + "<br>\n"
                                        + "            </div>\n"
                                        + "            <div style=\"text-align: center;margin-top: 15px;\">                \n"
                                        + "                <b style=\"font-size: 16px;\">Your registration verification code is<br>\n"
                                        + "                    <h1 style=\"color: green;\">" + cm.getAppr_verify_code() + "</h1>"
                                        + "                    <span style=\"color: #760050; font-size: 15px;\">You are just one step away from verifying the email \n"
                                        + "                        address associated with your TaxCPC account </span></b>\n"
                                        + "                <br>\n"
                                        + "            </div>\n"
                                        + "            <div style=\"margin-top: 10%;\">\n"
                                        + "                <span style=\"color: #760050; font-size: 15px;\"> Regards, </span>\n"
                                        + "            </div>\n"
                                        + "            <div>\n"
                                        + "                <span style=\"color: #760050; font-size: 15px;\">Team TaxCPC </span>\n"
                                        + "            </div>\n"
                                        + "            <div style=\"margin-top: 10%; color: #524C4C;\">\n"
                                        + "                Note: *** This is an automatically generated email, please do not reply ***\n"
                                        + "            </div>            \n"
                                        + "        </div> ");

                                String message = sb.toString();

                                sm.mailsend(sender, senderPassword, cm.getLogin_id(), cc, "akash.dev@lighthouseindia.com", "TaxCPC: New Account Registration OTP", message, "", host, port, imagePath);
                                utl.generateLog("OTP mail is sent to user email.......", "");

                            } else {
                                return_msg = "couldNotRegister";
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            return_msg = "error";
                        }
                    } else if (emailExistsStatus.equals("exists")) {
                        return_msg = "emailAlreadyExists";
                    } else {
                        return_msg = "error";
                    }
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                    return_view = "ajax_result";

                } else if (getRegister() != null && getRegister().equalsIgnoreCase("false")) {// check unique login id
                    if (!utl.isnull(getChkEmailId())) {
                        clientDAO = factory.getClientMastDAO();
                        Boolean isUnique = clientDAO.checkUniqueEmailId(getChkEmailId().trim());
                        if (isUnique) {
                            return_msg = "success";
                        } else {
                            return_msg = "error";
                        }
                    }
                    return_view = "ajax_result";
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                } else if (getAction().equals("verifyOtp")) {
                    return_msg = "error";
                    try {

                        ClientMastDAO clientMastDAO = factory.getClientMastDAO();
                        ClientMast clientForVerifyOtp = clientMastDAO.readClientForVerifyOtp(getClientMast().getLogin_id().trim(),
                                getClientMast().getAppr_verify_code().trim());

                        if (clientForVerifyOtp == null) {
                            return_msg = "invalidOtp";
                        } else {
                            return_msg = "validOtp";
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return_view = "ajax_result";
                    inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
                } else if (getAction().equals("submitUserDetails")) {
                    ClientMast cm = getClientMast();

                    ClientMast clientForVerifyOtp;
                    try {
                        ClientMastDAO clientMastDAO = factory.getClientMastDAO();

                        clientForVerifyOtp = clientMastDAO.readClientForVerifyOtp(getClientMast().getLogin_id().trim(),
                                getClientMast().getAppr_verify_code().trim());

                        clientMastDAO = factory.getClientMastDAO();
                        clientForVerifyOtp.setApprovedby("ADMIN");
                        clientForVerifyOtp.setApproveddate(new Date());
                        clientForVerifyOtp.setLastupdate(new Date());
                        clientMastDAO.update(clientForVerifyOtp);
                        utl.generateLog("client updated..", "");

                        UserMastDAO regUserMastDAO = factory.getUserMastDAO();
                        UserMast signupUser = new UserMast();

                        signupUser.setUser_code(null);
                        signupUser.setUser_name(cm.getClient_name());
                        signupUser.setShort_name(cm.getClient_name());
                        signupUser.setLogin_id(cm.getLogin_id());
                        signupUser.setLogin_pwd(cm.getLogin_pwd());
                        signupUser.setClient_code(clientForVerifyOtp.getClient_code());
                        signupUser.setEntity_code("B0");
                        signupUser.setUser_level(6l);
                        signupUser.setAdd_right(1l);
                        signupUser.setDelete_right(1l);
                        signupUser.setEdit_right(1l);
                        signupUser.setApprove_right(1l);
                        signupUser.setFlag("O"); // Online registration
                        signupUser.setLastupdate(new java.util.Date());

                        boolean saved = false;

                        saved = regUserMastDAO.save(signupUser);

                        if (saved) {
                            return_view = "signedup";
                            session.put("GLOBALMSGVALUE", "You have been successfully registered. You can now login with your email id.");
                            session.put("GLOBALMSGTYPE", MessageType.successFlag);
                        } else {
                            return_view = "input";
                            utl.generateSpecialLog("NEW USER Failed To REGISTERED", saved);
                            session.put("GLOBALMSGVALUE", "OTP is invalid. Please try again.");
                            session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return return_view;
    }//end method

    private String checkIfUserNotExistsWithEmail(DAOFactory factory, String registerEmailString) {
        String emailValid = "";
        try {
            ClientMastDAO clientDAO = factory.getClientMastDAO();
            boolean isUnique = clientDAO.checkUniqueEmailId(registerEmailString.trim());

            emailValid = isUnique ? "valid" : "exists";

        } catch (Exception e) {
            emailValid = "error";
            e.printStackTrace();
        }
        return emailValid;
    }

    @Override
    public void validate() {
        if (!utl.isnull(getRegister()) && getRegister().equalsIgnoreCase("true")) {
            if (utl.isnull(getClientMast().getLogin_id())) {
                session.put("ERRORCLASS", MessageType.errorMessage);
                addActionError("Email-Id Must Not be Empty");
            } else if (utl.isnull(getClientMast().getAppr_verify_code())) {
                session.put("ERRORCLASS", MessageType.errorMessage);
                addActionError("OTP Must Not be Empty");
            } else if (utl.isnull(getClientMast().getLogin_pwd())) {
                session.put("ERRORCLASS", MessageType.errorMessage);
                addActionError("Password Must Not be Empty");
            } else if (getClientMast().getLogin_pwd() == null ? getConfrm_pwd() != null : !getClientMast().getLogin_pwd().equals(getConfrm_pwd())) {
                session.put("ERRORCLASS", MessageType.errorMessage);
                addActionError("Password & Confirm Password Not Match");
            }
        }
    }

    private Map<String, Object> session;
    private final Util utl;
    private String register;
    private String confrm_pwd;
    private String chkEmailId;
    private ClientMast clientMast;
    InputStream inputStream;

    private String single_check;
    private String multiple_check;
    private String action;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getChkEmailId() {
        return chkEmailId;
    }

    public void setChkEmailId(String chkEmailId) {
        this.chkEmailId = chkEmailId;
    }

    public String getConfrm_pwd() {
        return confrm_pwd;
    }

    public void setConfrm_pwd(String confrm_pwd) {
        this.confrm_pwd = confrm_pwd;
    }

    public ClientMast getClientMast() {
        return clientMast;
    }

    public void setClientMast(ClientMast clientMast) {
        this.clientMast = clientMast;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getSingle_check() {
        return single_check;
    }

    public void setSingle_check(String single_check) {
        this.single_check = single_check;
    }

    public String getMultiple_check() {
        return multiple_check;
    }

    public void setMultiple_check(String multiple_check) {
        this.multiple_check = multiple_check;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }//end

}//end class

