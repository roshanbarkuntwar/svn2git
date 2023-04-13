/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

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
public class ProcessErrorDetailAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;

    private String ertp;
    private String processLevel;
    private InputStream inputStream;

    public ProcessErrorDetailAction15GH() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        StringBuilder grid = new StringBuilder();

        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            try {
                ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
                ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());

                if (client != null) {
                    String client_code = client.getClient_code();
                    String entity_code = client.getEntity_code();
                    Assessment asmt = (Assessment) session.get("ASSESSMENT");
                    String acc_year = asmt.getAccYear();

                    String quarterNo = asmt.getQuarterNo();
                    int quarter_no = Integer.parseInt(quarterNo);
                    String tds_type_code = asmt.getTdsTypeCode();

                    ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                    DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();

                    String l_summary_query = proc_err.getFirstScreen15GHSummaryQuery(asmt, entity_code, client_code, getProcessLevel());
                    ArrayList<ViewTranLoadErrorSummaryBean15GH> viewloaderrorsummery = objList.getGenericList(new ViewTranLoadErrorSummaryBean15GH(), l_summary_query);

                    ArrayList<ArrayList<ViewTranLoadErrorDetailBean15GH>> errorDetailBeanList = new ArrayList<>();
                    //System.out.println("1...."+viewloaderrorsummery);

                    if (viewloaderrorsummery != null && viewloaderrorsummery.size() > 0) {
                        //System.out.println("2...."+viewloaderrorsummery.size());

                        for (ViewTranLoadErrorSummaryBean15GH errorSummaryBean : viewloaderrorsummery) {
                            //System.out.println("3.....");

                            String l_detail_query = proc_err.getFirstScreenErrorDetails15GHQuery(asmt, entity_code, client_code, errorSummaryBean.getError_type_code(), getProcessLevel());
                            ArrayList<ViewTranLoadErrorDetailBean15GH> listData = objList.getGenericList(new ViewTranLoadErrorDetailBean15GH(), l_detail_query);
                            errorDetailBeanList.add(listData);
                            utl.generateSpecialLog("15GH Error detail query\t", l_detail_query);
                        }
                    }
                    ValidateError15GHSupport validateObj = new ValidateError15GHSupport();
                    grid = validateObj.sectionWiseErrorDetailsAndCorrectionGrid15GH(viewloaderrorsummery, errorDetailBeanList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(grid.toString().getBytes("UTF-8"));
        return return_view;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

}
