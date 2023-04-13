/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.ErrorType;
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
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ForgotPassword extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private String submit;
    private String email;

    public ForgotPassword() {
        utl = new Util();
    }//end const

    @Override
    public String execute() throws Exception {
        String return_view = "input";

        if (!utl.isnull(getSubmit()) && getSubmit().equals("true")) {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            UserMastDAO userDAO = factory.getUserMastDAO();
            UserMast umast = userDAO.readUserByLoginId(getEmail());

            if (umast != null) {
                int newPwd = utl.getRandomNumber(100000, 999999);
                String pwd = getEmail().substring(0, 1) + newPwd;
                umast.setLogin_pwd(pwd);
                userDAO = factory.getUserMastDAO();
//                clientDAO = factory.getClientMastDAO();

                boolean updateResult = userDAO.update(umast);
                if (updateResult) {
                    //send mail
                    String message = "<h3>Hello User,</h3>\n"
                            + "<h4>We received a request for password change for Login Id '" + umast.getLogin_id() + "'.</h4>\n"
                            + "<br/>\n"
                            + "<h4>Your new password is as follows : </h4>\n"
                            + "<span><strong>New Password : <span></span>" + pwd + "</strong></span><br/>\n"
                            + "<p>&nbsp;</p>\n"
                            + "<p><strong>Thanks!</strong>&nbsp;</p>\n"
                            + "<p>&nbsp;</p>\n"
                            + "<p>&nbsp;</p>\n"
                            + "<p><small><span style=\"color: gray;\"><strong><span style=\"\">Note:&nbsp;<strong>*** This is an auto generated email, please do not reply ***</strong></span><br /></strong></span></p><p>&nbsp;</small></p>";
                    try {
                        String sender = "";
                        String senderPassword = "";
                        String host = "";
                        String port = "";

                        LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                        LhssysParameters readOutgoingMailSender = lhssysParametersDAO.readDataAsParameterAndEntity("OUTGOING_MAIL_SENDER", "");
                        if (readOutgoingMailSender != null) {
                            sender = readOutgoingMailSender.getParameter_value();
                        }
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

                        if (!utl.isnull(sender) && !utl.isnull(senderPassword) && !utl.isnull(host) && !utl.isnull(port)) {
                            boolean result = new SendMailUtil().mailsend(sender, senderPassword, getEmail(), "", "akash.dev@lighthouseindia.com", "Password Reset Notification", message, "", host, port, "");
                            if (result) {
                                return_view = "success";
                                session.put("GLOBALMSGTYPE", MessageType.successFlag);
                                session.put("GLOBALMSGVALUE", "New Password Has Been Sent To Your Registered Email Id.");
                            } else {
                                utl.generateSpecialLog("Some Error Occured, Could Not Send Mail. ", result);
                                session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                                session.put("GLOBALMSGVALUE", "Some Error Occured, Could Not Send Mail.");
                            }
                        } else {
                            session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                            session.put("GLOBALMSGVALUE", "Some Error Occured, Could Not Send Mail.");
                        }
                    } catch (Exception e) {
                        session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                        utl.generateLog("Some Error Occured, Could Not Send Mail.", e.getMessage());
                        session.put("GLOBALMSGVALUE", "Some Error Occured, Could Not Send Mail.");
                        e.printStackTrace();
                    }
                } else {
                    session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                    session.put("GLOBALMSGVALUE", "Some Error Occured, Try Again Later.");

                }
            } else {
                session.put("GLOBALMSGTYPE", MessageType.errorFlag);
                addActionError("Email id is not registered, please contact to TAXCPC support team.");
                session.put("GLOBALMSGVALUE", "Email id is not registered, please contact to TAXCPC support team.");
            }
        }
        return return_view;
    }//end method

    @Override
    public void validate() {
        if (!utl.isnull(getSubmit()) && getSubmit().equalsIgnoreCase("true")) {
            if (utl.isnull(getEmail())) {
                session.put("ERRORCLASS", ErrorType.errorMessage);
                addActionError("Email Address Must Not Be Empty");
            }
        }
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

}//end class
