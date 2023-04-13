/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewClientMastDAO;
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
 * @author gaurav.khanzode
 */
public class DynamicErrorDetailsAction extends ActionSupport implements SessionAware {

    Util utl;
    Map<String, Object> session;
    private String ertp;
    private String errorTypeProc;
    private String processLevel;
    private InputStream inputStream;

    public DynamicErrorDetailsAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        StringBuilder grid = new StringBuilder();
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
            ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            Assessment asmt = null;

            String l_client_code = "";
            String l_entity_code = "";

            if (client != null) {
                l_client_code = client.getClient_code();
                l_entity_code = client.getEntity_code();
                asmt = (Assessment) session.get("ASSESSMENT");
            }
            ProcessErrorsDB procErr = new ProcessErrorsDB();
            String l_query = procErr.getProcessErrorSummaryQuery(l_entity_code, l_client_code, getErrorTypeProc(), asmt, getProcessLevel());

            utl.generateSpecialLog("RPE-0002 (1st Screen Summary Query)--219--", l_query);

            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
            ArrayList<ViewTranLoadErrorBean> viewloaderrorsummery = db.getGenericList(new ViewTranLoadErrorBean(), l_query);

            ArrayList<ArrayList<ViewTranLoadErrorDetailsBean>> errDetailsList = null;
            if (viewloaderrorsummery != null && viewloaderrorsummery.size() > 0) {
                errDetailsList = new ArrayList<>();
                for (ViewTranLoadErrorBean viewTranLoadErrorBean : viewloaderrorsummery) {

                    String err_detail_qry = procErr.getFScreenErrorDetailsQuery(l_entity_code, l_client_code, viewTranLoadErrorBean.getError_type_code(), asmt, getProcessLevel());

                    utl.generateSpecialLog("RPE-0002 (err_detail_qry)--", err_detail_qry);

                    ArrayList<ViewTranLoadErrorDetailsBean> listData = db.getGenericList(new ViewTranLoadErrorDetailsBean(), err_detail_qry);
                    errDetailsList.add(listData);
                }
            }
            ValidateErrorSupport validateObj = new ValidateErrorSupport();
            grid = validateObj.sectionWiseErrorDetailsAndCorrectionGrid(viewloaderrorsummery, errDetailsList);

            inputStream = new ByteArrayInputStream(grid.toString().getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getErtp() {
        return ertp;
    }

    public void setErtp(String ertp) {
        this.ertp = ertp;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getErrorTypeProc() {
        return errorTypeProc;
    }

    public void setErrorTypeProc(String errorTypeProc) {
        this.errorTypeProc = errorTypeProc;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

}
