/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class DeducteePanDetailsAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        String l_return_message = "";
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user = (UserMast) session.get("LOGINUSER");
            if (!utl.isnull(getPanno())) {
                PANVerificationDB pan_db = new PANVerificationDB();
                String panVerifiedAndUnVerifiedDetailsQuery = null;

                DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                panVerifiedAndUnVerifiedDetailsQuery = pan_db.panVerifiedAndUnVerifiedDetailsQuery(panno, "pan_mast", "");

                ArrayList<PanVerifiedUnverifiedBean> checkPanVerifiedDetails = dbQueryList.getGenericList(new PanVerifiedUnverifiedBean(), panVerifiedAndUnVerifiedDetailsQuery);
                if (checkPanVerifiedDetails != null && checkPanVerifiedDetails.size() > 0) {
                    l_return_message = "success#Verified#" + new Gson().toJson(checkPanVerifiedDetails);
                } else {
                    panVerifiedAndUnVerifiedDetailsQuery = pan_db.panVerifiedAndUnVerifiedDetailsQuery(panno, "pan_mast_unverified", client.getEntity_code());
                    ArrayList<PanVerifiedUnverifiedBean> checkPanUnverifiedDetails = dbQueryList.getGenericList(new PanVerifiedUnverifiedBean(), panVerifiedAndUnVerifiedDetailsQuery);
                    if (checkPanUnverifiedDetails != null && checkPanUnverifiedDetails.size() > 0) {
                        l_return_message = "success#unverified#" + new Gson().toJson(checkPanUnverifiedDetails);
                    } else {
                        l_return_message = "error# No Data Found";
                    }
                }
                utl.generateSpecialLog("Pan Details Query is ", panVerifiedAndUnVerifiedDetailsQuery);

                returnView = "ajax_success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));
        return returnView;
    }

    public DeducteePanDetailsAction() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    private final Util utl;
    private Map<String, Object> session;
    private String action;
    private String panno;
    private InputStream inputStream;

    public String getAction() {
        return action;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
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
}
