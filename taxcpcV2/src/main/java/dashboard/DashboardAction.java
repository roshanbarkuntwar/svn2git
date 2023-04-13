/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.QuickNavigationMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.QuickNavigationMast;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DashboardAction extends ActionSupport implements SessionAware {

    public DashboardAction() {
        utl = new Util();
    }

    private final Util utl;
    private Map<String, Object> session;
    private List<QuickNavigationMast> navList;
    private String module, action;
    private int startIndex;
    private int totalMenuCount;
    private String activeDashboardAssessmentBandFlag = "ActiveAssessmentBand";
    private InputStream inputStream;
    private String dashboardHeaderMessage;

    @Override
    public String execute() {
        String returnType = "success";
        session.put("ACTIVE_TAB", "dashboard");
       
        try{   
         String message=""; 
         String welcomeMsgQuery = "select t.parameter_value from lhssys_parameters t where t.parameter_name LIKE 'DASHBOARD_HEADER_MESSAGE'";
         message = new DbFunctionExecutorAsString().execute_oracle_function_as_string(welcomeMsgQuery);
//         utl.generateLog("welcome message",message); 
         if(!utl.isnull(message)){
         setDashboardHeaderMessage(message);
         }
         }catch(Exception e){
           e.printStackTrace();   
         } 
        
        

        if (!utl.isnull(getModule())) {
            session.put("MODULE", getModule());
            if ("M".equals(getModule())) {
                session.put("ACTIVE_TAB", "reportAction");
                returnType = "reportAction";
            }
        }
        try {
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast userMast = (UserMast) session.get("LOGINUSER");
            String sessionModule = (String) session.get("MODULE");

            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }
            if (clientMast != null) {
                setStartIndex(getStartIndex());
                module = utl.isnull(sessionModule) ? "R" : sessionModule;

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                QuickNavigationMastDAO quickNavigationMastDAO = factory.getQuickNavigationMastDAO();
                int quickNavigationMenuCount = quickNavigationMastDAO.getQuickNavigationMenuCount(clientMast.getEntity_code(), module);
                setMenuCount(quickNavigationMenuCount);

                quickNavigationMastDAO = factory.getQuickNavigationMastDAO();
                navList = quickNavigationMastDAO.getQuickNavigationListByEntityCode(clientMast.getEntity_code(), module, getStartIndex());

                if (navList != null && navList.size() > 0) {
                    int totalCount = navList.size();
                    int rem = 0;
                    int mod = totalCount % 3;
                    if (mod > 0) {
                        rem = 1;
                    }
                    totalRows = (int) Math.ceil(totalCount / 3) + rem;
                }

                if (!utl.isnull(getAction()) && getAction().equals("nilReturn")) {
                    try {

                        String proc_token = new GetTokenTransactions().generateTokenGlobalMethod(asmt, clientMast, userMast, l_client_loginid_seq, userMast.getUser_level() + "",
                                "EG", "", "BULK_FVU_TEXT_GEN", "PROCESS_INSERT", "BULK_FVU_TEXT_GEN", "");
                        inputStream = new ByteArrayInputStream(proc_token.getBytes("UTF-8"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    returnType = "ajax_success";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnType;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    private Integer totalRows;

    public List<QuickNavigationMast> getNavList() {
        return navList;
    }

    public void setNavList(List<QuickNavigationMast> navList) {
        this.navList = navList;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getMenuCount() {
        return totalMenuCount;
    }

    public void setMenuCount(int menuCount) {
        this.totalMenuCount = menuCount;
    }

    public String getActiveDashboardAssessmentBandFlag() {
        return activeDashboardAssessmentBandFlag;
    }

    public void setActiveDashboardAssessmentBandFlag(String activeDashboardAssessmentBandFlag) {
        this.activeDashboardAssessmentBandFlag = activeDashboardAssessmentBandFlag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDashboardHeaderMessage() {
        return dashboardHeaderMessage;
    }

    public void setDashboardHeaderMessage(String dashboardHeaderMessage) {
        this.dashboardHeaderMessage = dashboardHeaderMessage;
    }

}
