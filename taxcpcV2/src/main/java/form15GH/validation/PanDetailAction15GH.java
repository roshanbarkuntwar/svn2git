/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class PanDetailAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    Util utl;
    private InputStream inputStream;

    private int TotalDeducteePAN;
    private int VerifiedDeducteePAN;
    private int InvalidDeducteePAN;
    private int PANNOTAVBLCount;
    private int UnverifiedPAN;

    public PanDetailAction15GH() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_str = "";
        try {
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            DbFunctionExecutorAsString qur = new DbFunctionExecutorAsString();
            String l_pan_unverified = "";

            if (clientMast != null) {
                try {
                    String client_code = clientMast.getClient_code();
                    String entity_code = clientMast.getEntity_code();

                    try {
                        ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                        String l_update_pan_query = proc_err.getUpdatePANColumnQuery(entity_code, client_code);

                        DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                        boolean updatePanColumnResult = objDbFunctionClass.execute_oracle_update_function_as_string(l_update_pan_query);

                        utl.generateSpecialLog("l_update_pan_query...", l_update_pan_query);
                        utl.generateLog("updatePanColumnResult...", updatePanColumnResult);

                        String l_pan_unverified_query = proc_err.getUnverifiedPANCountQuery(entity_code, client_code);

                        utl.generateSpecialLog("l_pan_unverified_query ON 15 GH---", l_pan_unverified_query);

                        l_pan_unverified = qur.execute_oracle_function_as_string(l_pan_unverified_query);
                        l_pan_unverified = utl.isnull(l_pan_unverified) ? "0" : l_pan_unverified;
                        l_return_str = l_pan_unverified;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_str.getBytes("UTF-8"));
        return l_return_value;
    }//end method

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

    public int getTotalDeducteePAN() {
        return TotalDeducteePAN;
    }

    public void setTotalDeducteePAN(int TotalDeducteePAN) {
        this.TotalDeducteePAN = TotalDeducteePAN;
    }

    public int getVerifiedDeducteePAN() {
        return VerifiedDeducteePAN;
    }

    public void setVerifiedDeducteePAN(int VerifiedDeducteePAN) {
        this.VerifiedDeducteePAN = VerifiedDeducteePAN;
    }

    public int getInvalidDeducteePAN() {
        return InvalidDeducteePAN;
    }

    public void setInvalidDeducteePAN(int InvalidDeducteePAN) {
        this.InvalidDeducteePAN = InvalidDeducteePAN;
    }

    public int getPANNOTAVBLCount() {
        return PANNOTAVBLCount;
    }

    public void setPANNOTAVBLCount(int PANNOTAVBLCount) {
        this.PANNOTAVBLCount = PANNOTAVBLCount;
    }

    public int getUnverifiedPAN() {
        return UnverifiedPAN;
    }

    public void setUnverifiedPAN(int UnverifiedPAN) {
        this.UnverifiedPAN = UnverifiedPAN;
    }

}//end class
