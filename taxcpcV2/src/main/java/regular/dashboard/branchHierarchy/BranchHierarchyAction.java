/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchHierarchy;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class BranchHierarchyAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        StringBuilder sb = new StringBuilder();
        session.put("ACTIVE_TAB", "dashboard");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            BranchHierarchyDB branchHierarchyDB = new BranchHierarchyDB();
            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getBranchHierarchyDetails")) {
                ArrayList<TreeMenuBean> treeMenuBeanList = branchHierarchyDB.getAllBranchHierarchy(clientMast.getEntity_code());

                if (treeMenuBeanList != null && treeMenuBeanList.size() > 0) {
                    sb.append(new Gson().toJson(treeMenuBeanList));
                    returnView = "filter_success";
                } else {
                    sb.append(GlobalMessage.DASHBOARD_NO_RECORDS);
                    returnView = "filter_success";
                }
            }else if(!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getLoggedInBranchHierarchy")){
                ArrayList<TreeMenuBean> treeMenuBeanList = branchHierarchyDB.getLoggedInBranchHierarchy(clientMast.getEntity_code(), clientMast.getClient_code());

                if (treeMenuBeanList != null && treeMenuBeanList.size() > 0) {
                    sb.append(new Gson().toJson(treeMenuBeanList));
                    returnView = "filter_success";
                } else {
                    sb.append(GlobalMessage.DASHBOARD_NO_RECORDS);
                    returnView = "filter_success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnView;
    }

    Map<String, Object> session;
    private InputStream inputStream;
    private Util utl;
    private String action;

    public BranchHierarchyAction() {
        utl = new Util();
    }//end constructor

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
