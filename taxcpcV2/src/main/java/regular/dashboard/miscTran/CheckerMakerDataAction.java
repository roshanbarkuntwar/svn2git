/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author pratik.barahate
 */
public class CheckerMakerDataAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        session.put("ACTIVE_TAB", "dashboard");
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");
        if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("updateSingleTranLoad")) {
            TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
            String client_code = clientMast.getClient_code();
            if (!utl.isnull(getRowidSeq()) && !utl.isnull(getFlag()) && !utl.isnull(client_code)) {
                String updateSingleTranLoad = tdsTranLoadDAO.updateSingleTranLoad(getRowidSeq(), getFlag(), client_code);
                inputStream = new ByteArrayInputStream(updateSingleTranLoad.getBytes("UTF-8"));
            }
        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("updateBulkTranLoad")) {
            TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
            if (!utl.isnull(getFlag())) {
                String updateBulkTranLoad = tdsTranLoadDAO.updateBulkTranLoad(getFlag(), getAuthStatus(), clientMast, asmt, getPanno(), getdName(), user.getUser_code());
                inputStream = new ByteArrayInputStream(updateBulkTranLoad.getBytes("UTF-8"));
            }
        } else {
        }

        return return_view;
    }

    Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private TdsTranLoad checkerMakerData;
    private String search;
    private String action;
    private String sessionResult;
    private String rowidSeq;
    private String flag;
    private String authStatus;
    private String panno;
    private String dName;

    public CheckerMakerDataAction() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public TdsTranLoad getCheckerMakerData() {
        return checkerMakerData;
    }

    public void setCheckerMakerData(TdsTranLoad checkerMakerData) {
        this.checkerMakerData = checkerMakerData;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getRowidSeq() {
        return rowidSeq;
    }

    public void setRowidSeq(String rowidSeq) {
        this.rowidSeq = rowidSeq;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }
}
