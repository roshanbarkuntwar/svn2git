/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.opensymphony.xwork2.ActionSupport;
import dao.QuickNavigationMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.QuickNavigationMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class dashboardMenuSettingAction extends ActionSupport {

    @Override
    public String execute() throws Exception {

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String l_return_value = "success";
        String l_return_msg = ""; 
        if (utl.isnull(getAction())){
            utl.generateLog("Dashboard Menu Setting", "");
             try {
                QuickNavigationMastDAO quickNavigationMastDAO = factory.getQuickNavigationMastDAO();
                 dashboardMenuList = quickNavigationMastDAO.getQuickNavigationList();
           } catch (Exception e) {
              utl.generateLog("Dashboard Menu Setting Exception", "");
            }                   

        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("update")) { 
            utl.generateLog("update LHSSYS_APPV2_DASHBOARD_MENU", "");
            String updatequery = "update LHSSYS_APPV2_DASHBOARD_MENU  set active_flag= '" + getActive_flag() + "'\n"
                                 + "where menu_name ='" + getMenu_name() + "' and module_type ='" + getModule_type() + "'";
           
            DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
            boolean updateResult = objDbFunctionClass.execute_oracle_update_function_as_string(updatequery);
            utl.generateLog("updateResult", updateResult);
            if (updateResult) {
                l_return_msg = "updated";
            } else {
                l_return_msg = "error";
            }
            l_return_value = "ajaxsuccess";
         }

        inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
        return l_return_value;
    }

    List<QuickNavigationMast> dashboardMenuList;
    Util utl;
    private String action;
    private InputStream inputStream;
    private String menu_name;
    private String module_type;
    private String active_flag;

    public dashboardMenuSettingAction() {
        utl = new Util();
    }

    public List<QuickNavigationMast> getDashboardMenuList() {
        return dashboardMenuList;
    }

    public void setDashboardMenuList(List<QuickNavigationMast> dashboardMenuList) {
        this.dashboardMenuList = dashboardMenuList;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String active_flag) {
        this.active_flag = active_flag;
    }

}
