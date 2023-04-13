/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.opensymphony.xwork2.ActionSupport;
import dao.MisReportConfigMastDAO;
import dao.QuickNavigationMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import hibernateObjects.QuickNavigationMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author akash.meshram
 */
public class misReportSettingAction extends ActionSupport{
    @Override
    public String execute() throws Exception {
        
        
    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String l_return_value = "success";
        String l_return_msg = ""; 
        if (utl.isnull(getAction())){
            utl.generateLog("Dashboard Menu Setting", "");
             try {
             
            MisReportConfigMastDAO reportConfigMastDao = factory.getMisReportConfigMastDAO();
            MisReportConfigMastList = reportConfigMastDao.getReportListByAppType("V2");
           } catch (Exception e) {
              utl.generateLog("Dashboard Menu Setting Exception", "");
            }                   

        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("update")) { 
            utl.generateLog("update mis_report_config_mast", "");
            String updatequery = "update mis_report_config_mast  set REP_STATUS= '" + getReport_status() + "'\n"
                                 + "where rep_seq_id ='" + getReport_seq_id() + "'";
           
            System.out.println("updatequery--"+updatequery);
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
    private String report_seq_id;
    private String report_status;
    private List<MisReportConfigMast> MisReportConfigMastList;   

    public misReportSettingAction() {
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

    public String getReport_seq_id() {
        return report_seq_id;
    }

    public void setReport_seq_id(String report_seq_id) {
        this.report_seq_id = report_seq_id;
    }

    public String getReport_status() {
        return report_status;
    }

    public void setReport_status(String report_status) {
        this.report_status = report_status;
    }

    

    public List<MisReportConfigMast> getMisReportConfigMastList() {
        return MisReportConfigMastList;
    }

    public void setMisReportConfigMastList(List<MisReportConfigMast> MisReportConfigMastList) {
        this.MisReportConfigMastList = MisReportConfigMastList;
    }

    
}

        