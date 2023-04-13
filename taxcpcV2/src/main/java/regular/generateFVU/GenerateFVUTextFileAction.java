/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysFileTranDAO;
import dao.LhssysParametersDAO;
import dao.ViewTdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.ViewClientMast;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class GenerateFVUTextFileAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    
    Util utl;
    
    private InputStream inputStream;
    
    private String tokenNo;
    private String sessionResult;
    private String parentFlag;
    private String childFlag;
    private String message;
    private String jsessionid;
    
    private boolean validateFlag;

    private long total;
    
    public GenerateFVUTextFileAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysParametersDAO lhssysParametersDAO;
        ViewClientMast viewClientMast = null;
        try {
            try {
                viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // flag for menu :START
            if (!utl.isnull(getParentFlag()) && !utl.isnull(getChildFlag())) {
                session.put("PARENTFLAG", getParentFlag());
                session.put("CHILDFLAG", getChildFlag());
            }
            // END 

            // file not found error msg :START
            if (!utl.isnull(getMessage())) {
                addActionError(getMessage());
                session.put("ERRORCLASS", ErrorType.errorMessage);
            }
            // END

            if (viewClientMast != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                if (asmt != null) {
                    int quarter_no = 0;
                    String acc_year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();
                    if (!utl.isnull(quarterNo) && quarterNo.length() > 1) {
                        quarterNo = quarterNo.substring(1, 2);
                    }
                    quarter_no = Integer.parseInt(quarterNo);
                    Date from_date = asmt.getQuarterFromDate();
                    Date to_date = asmt.getQuarterToDate();
                    String tds_type_code = asmt.getTdsTypeCode();

                    GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();
                    if (!utl.isnull(getJsessionid()) && getJsessionid().equalsIgnoreCase("ij123kih7896kirtwc5638Nsdsuybn3456")) { // jsessionid is a variable to validate fvu page from process error page only
                        LhssysFileTranDAO lhssysFileTranDAO = factory.getLhssysFileTranDAO();

                        LhssysFileTran tran = new LhssysFileTran();//Entity lhssys_file_tran
                        tran.setClient_code(viewClientMast.getClient_code());
                        tran.setImport_flag("TX");

                        Long fvuFileCount = lhssysFileTranDAO.getFvuFileCount(tran);
                        if (fvuFileCount < 3l) {
                            ViewTdsChallanTranLoadDAO chalantran = factory.getViewTdsChallanTranLoadDAO();
//                            ChallanFilterEntity tdsChallanTranFilterSrch = session.get("MANAGETDSCHALLANSRCH") == null ? null : (ChallanFilterEntity) session.get("MANAGETDSCHALLANSRCH");
                            Long tdsChallanTranCount = chalantran.getTdsChallanTranCount(viewClientMast.getClient_code(), acc_year, quarterNo, tds_type_code, null, "false", utl);
                            if (tdsChallanTranCount > 0l) {// if challans found
                                setTotal(tdsChallanTranCount);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean validateFlag) {
        this.validateFlag = validateFlag;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(String parentFlag) {
        this.parentFlag = parentFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}//end class
