/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userProfile;

import com.lhs.taxcpcv2.AssessmentBandValue;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class UserProfileAction extends ActionSupport implements SessionAware {

    public UserProfileAction() {
        assessmentBandValue= new AssessmentBandValue();
        utl = new Util();
    }

    @Override
    public String execute() {
        String retVal = "";
  
        if (utl.isnull(getAction())) {
            UserMast userMast = (UserMast) session.get("LOGINUSER");
            setLoginUser(userMast);

            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            setTanNo(viewClientMast.getTanno());
            setBankCode(viewClientMast.getBank_branch_code());
            setBankName(viewClientMast.getClient_name());

            retVal = "success";
        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("update")) {
            String retMsg = "ERROR";
            try {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                UserMastDAO userMastDAO = factory.getUserMastDAO();
                // UserMast userMast = userMastDAO.readById(getLoginUser().getUser_code(), true);
                UserMast userMast = userMastDAO.readUserByUserCode(getLoginUser().getUser_code());
                
                String login_id = getLoginUser().getLogin_id();
                String user_name = getLoginUser().getUser_name();
                String short_name = getLoginUser().getShort_name();
                String login_pwd = getLoginUser().getLogin_pwd();
                
                if(!utl.isnull(login_id)){
                    userMast.setLogin_id(login_id);
                }
                if(!utl.isnull(user_name)){
                    userMast.setUser_name(user_name);
                }
                if(!utl.isnull(short_name)){
                    userMast.setShort_name(short_name);
                }
                if(!utl.isnull(login_pwd)){
                    userMast.setLogin_pwd(login_pwd);
                }
                userMastDAO = factory.getUserMastDAO();
                boolean status = userMastDAO.update(userMast);
                if (status) {
                    session.replace("LOGINUSER", userMast);
                    utl.generateSpecialLog("User Profile is Updated",status);
                    retMsg = "SUCCESS";
                }

                inputStream = new ByteArrayInputStream(retMsg.getBytes("UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            retVal = "updateSuccess";
        }

        return retVal;
    }

    private String action;
    private UserMast loginUser;
    private String tanNo;
    private String bankCode;
    private String bankName;
    private Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private AssessmentBandValue assessmentBandValue;

    public AssessmentBandValue getAssessmentBandValue() {
        return assessmentBandValue;
    }

    public void setAssessmentBandValue(AssessmentBandValue assessmentBandValue) {
        this.assessmentBandValue = assessmentBandValue;
    }
    
    
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public UserMast getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserMast loginUser) {
        this.loginUser = loginUser;
    }

    public String getTanNo() {
        return tanNo;
    }

    public void setTanNo(String tanNo) {
        this.tanNo = tanNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}//End Class
