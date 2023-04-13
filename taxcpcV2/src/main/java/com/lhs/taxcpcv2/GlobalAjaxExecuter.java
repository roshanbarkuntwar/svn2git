/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysClientLoginTranDAO;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysClientLoginTran;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class GlobalAjaxExecuter extends ActionSupport implements SessionAware, ServletRequestAware {

    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;
    private String action;
    private HttpServletRequest request;

    public GlobalAjaxExecuter() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String returnValue = "success";
        String returnMessage = "error";
        try {
            if (!utl.isnull(getAction())) {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                
                
                UserMast user = (UserMast) session.get("LOGINUSER");
                if (getAction().equalsIgnoreCase("globalSessionValue")) {

                    // GET LHSSYS PARAMETERS BASED ON SESSION FLAG
                    try {
                        //LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                        //List<LhssysParameters> sessionFlagParam = lhssysParametersDAO.readParametersForSession(client.getEntity_code());
                        String query="select * from lhssys_parameters this_ where this_.session_flag = 'T'";
                        DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                        List<LhssysParameters> sessionFlagParam = dbQueryList.getGenericList(new LhssysParameters(), query);
//                        utl.generateLog("--------------------------------------", "");
//                        utl.generateLog("Lhssys_Parameters name", "");
//                        sessionFlagParam.forEach(p -> System.out.println(p.getParameter_name()));
//                        utl.generateLog("--------------------------------------", "");
                        //Map<String, String> map = new LinkedHashMap<String, String>();
//                        utl.generateLog("SESSION LHSSYS-PARAMETERS VALUES", "");
                        if (sessionFlagParam != null) {
                            String paramName = "";
                            for (LhssysParameters sessionParam : sessionFlagParam) {
                               if(sessionParam != null){
                                    paramName = sessionParam.getParameter_name();
                                    if (paramName.equalsIgnoreCase("JAVA_DRIVE_NAME")) {
                                        session.put("JAVADRIVE", sessionParam.getParameter_value());
                                        utl.generateLog("set JAVADRIVE in session", sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("ORACLE_DRIVE_NAME")) {
                                        session.put("ORACLEDRIVE", sessionParam.getParameter_value());
                                        utl.generateLog("set ORACLEDRIVE in session", sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("EXCEL_FORMAT")) {
                                        session.put("EXCELFORMAT", sessionParam.getParameter_value());
//                                        utl.generateLog("set EXCELFORMAT in session", sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("ENABLE_FUNCTIONAL_BUTTON_VFG")) {
                                        session.put("ENABLEBUTTONVFG", sessionParam.getParameter_value());
//                                    utl.generateLog("set ENABLEBUTTONVFG in session",  sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("CLOB_IMPORT_FLAG")) {
                                        session.put("CLOB_IMPORT_FLAG", sessionParam.getParameter_value());
//                                        utl.generateLog("set CLOB_IMPORT_FLAG in session",  sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("CLOB_LOG_FLAG")) {
                                        session.put("CLOB_LOG_FLAG", sessionParam.getParameter_value());
//                                        utl.generateLog("set CLOB_LOG_FLAG in session",  sessionParam.getParameter_value());
                                    } else if (paramName.equalsIgnoreCase("CLOB_FVU_GEN_FLAG")) {
                                        session.put("CLOB_FVU_GEN_FLAG", sessionParam.getParameter_value());
//                                        utl.generateLog("set CLOB_FVU_GEN_FLAG in session",  sessionParam.getParameter_value());
                                    }else if (paramName.equalsIgnoreCase("BLOB_FILE_DOWNLOAD_FLAG")) {
                                      session.put("BLOB_FILE_DOWNLOAD_FLAG", sessionParam.getParameter_value());
//                                      utl.generateLog("set BLOB_FILE_DOWNLOAD_FLAG in session",  sessionParam.getParameter_value());
                                    }else if (paramName.equalsIgnoreCase("NEW_FVU_GEN_FLAG")) {
                                      session.put("NEW_FVU_GEN_FLAG", sessionParam.getParameter_value());
//                                      utl.generateLog("set NEW_FVU_GEN_FLAG in session",  sessionParam.getParameter_value());
                                    }else if (paramName.equalsIgnoreCase("FVU_FILE_VERSION")) {
                                      session.put("FVU_FILE_VERSION", sessionParam.getParameter_value());
//                                      utl.generateLog("set FVU_FILE_VERSION in session",  sessionParam.getParameter_value());
                                    }

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //set clientlevel and tran level in session
//                    utl.generateLog("client.getEntity_code()",  client.getEntity_code());
//                    utl.generateLog("client.getClient_code()",  client.getClient_code());
                    String clientTranLevel = new DbFunctionExecutorAsString().execute_oracle_function_as_string("SELECT lhs_utility.is_client_tran_level('"+client.getEntity_code()+"','"+client.getClient_code()+"',null,null,null,null,null,null,null,'R') is_client_tran_level FROM dual");
//                    utl.generateLog("CLIENT_TRAN_LEVEL",  clientTranLevel);
                    session.put("CLIENT_TRAN_LEVEL", clientTranLevel);
                    String clientLoginLevel = new DbFunctionExecutorAsString().execute_oracle_function_as_string("SELECT lhs_utility.is_client_login_level('"+client.getEntity_code()+"','"+client.getClient_code()+"',null,null,null,null,null,null,null,'R') is_client_login_level FROM dual");
                    utl.generateLog("CLIENT_LOGIN_LEVEL", clientLoginLevel);
                    session.put("CLIENT_LOGIN_LEVEL", clientLoginLevel);
                    //enter session details in database
//                    String sesionId = "";
                    try {
//                        sesionId = request.getSession().getId();
                        String ipAddress = request.getRemoteAddr();
                        InetAddress addr = InetAddress.getByName(ipAddress);
                        String host = addr.getHostName();
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                        Browser browser = userAgent.getBrowser();
                        String browserNameString = browser.getGroup().getName() + " " + userAgent.getBrowserVersion();
                        String osName = userAgent.getOperatingSystem().getName();
                        LhssysClientLoginTranDAO clientLoginTranDAO = factory.getLhssysClientLoginTranDAO();
                        LhssysClientLoginTran clientLoginTran = new LhssysClientLoginTran();
                        clientLoginTran.setEntity_code(client.getEntity_code());
                        clientLoginTran.setClient_code(client.getClient_code());
                        clientLoginTran.setLogin_time(timestamp);
                        clientLoginTran.setMachine_name(host);
                        clientLoginTran.setMachine_ip(ipAddress);
                        clientLoginTran.setMachine_browser(browserNameString);
                        clientLoginTran.setMachine_os_name(osName);
                        clientLoginTran.setUser_code(user.getUser_code());
                        clientLoginTran.setFlag("T");
                        Object sessionId = clientLoginTranDAO.saveAndReturnIdentifier(clientLoginTran);
                        Long loginSessionId;
                        if (sessionId != null) {
                            loginSessionId = (Long) sessionId;
                            utl.generateLog("login session id", loginSessionId);
                            session.put("LOGINSESSIONID", loginSessionId);
                        } else {
                            session.put("LOGINSESSIONID", null);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        session.put("LOGINSESSIONID", null);
                    }

                    try {
                        Assessment asmt = (Assessment) session.get("ASSESSMENT");
                        if (asmt != null) {
                            FileOptUtil optUtil = new FileOptUtil();
                            Object templateName = optUtil.getTaxcpcDownloadFilename(client, asmt);
                            session.put("EXCELTEMPLATENAME", templateName);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    session.put("SESSIONINSERT", "T");
                    returnMessage = "save";
                }
            }
        } catch (Exception e) {
        }
        inputStream = new ByteArrayInputStream(returnMessage.getBytes("UTF-8"));
        return returnValue;
    }//end method

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}//end class
