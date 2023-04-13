/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysClientLoginTranDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.HibernateUtil;
import globalUtilities.Util;
import hibernateObjects.LhssysClientLoginTran;
import hibernateObjects.UserMast;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ayushi.jain
 */
public class Logout extends ActionSupport implements SessionAware, ServletRequestAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        try {
            if (request.getSession() != null) {
                try {
                    Assessment assessment = (Assessment) session.get("ASSESSMENT");
                    if (assessment != null) {
                        UserMast userMast = (UserMast) session.get("LOGINUSER");
                        if (userMast != null) {
                            StringBuilder queryBuilder = new StringBuilder();
                            queryBuilder.append("update user_mast t\n")
                                    .append("   set t.default_acc_year      = '").append(assessment.getAccYear()).append("',\n")
                                    .append("       t.default_quarter_no    = '").append(assessment.getQuarterNo()).append("',\n")
                                    .append("       t.default_tds_type_code = '").append(assessment.getTdsTypeCode()).append("'\n")
                                    .append(" where t.user_code = '").append(userMast.getUser_code()).append("'");

                            DbFunctionExecutorAsString dbFunction = new DbFunctionExecutorAsString();
                            boolean result = dbFunction.execute_oracle_update_function_as_string(queryBuilder.toString());
                            if (result) {
                                utl.generateLog("Updating the Default assessment Parameters before Logging out the user=" + userMast.getUser_code(), "Default Value are:" + assessment.getAccYear() + ">" + assessment.getQuarterNo() + ">" + assessment.getTdsTypeCode());
                            } else {
                                utl.generateLog("unable to update the Default values when Logging out", "");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Long loginSessionId;
                Object sessionId = session.get("LOGINSESSIONID");

                if (sessionId != null) {
                    loginSessionId = (Long) sessionId;
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    LhssysClientLoginTranDAO clientLoginTranDAO = factory.getLhssysClientLoginTranDAO();
                    LhssysClientLoginTran clientLoginTran = clientLoginTranDAO.readClientLoginTranBySessionId(loginSessionId);
                    clientLoginTran.setLogout_time(new Timestamp(new Date().getTime()));
                    clientLoginTran.setLogout_method("P");
                    clientLoginTranDAO = factory.getLhssysClientLoginTranDAO();
                    clientLoginTranDAO.update(clientLoginTran);
                }
                request.getSession().invalidate();
            }//end if
        } catch (Exception e) {
            if (request.getSession() != null) {
                request.getSession().invalidate();
            }
        } finally {
            try {
                Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
                if (currentSession != null) {
                    currentSession.close();
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return return_view;
    }//end method

    public Logout() {
        utl = new Util();
    }//end constructor

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }//end method

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }//end method
    private Map<String, Object> session;
    private HttpServletRequest request;
    private final globalUtilities.Util utl;
    private String mx_upd_res;

    public String getMx_upd_res() {
        return mx_upd_res;
    }

    public void setMx_upd_res(String mx_upd_res) {
        this.mx_upd_res = mx_upd_res;
    }

}//end class

