/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadTdsCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DownloadTdsCertificateAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws UnsupportedEncodingException {
        String returnType = "success";
        session.put("ACTIVE_TAB", "downloadTdsCertificate");
        String returnMsg = "";
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysParametersDAO lhssysParametersDAO;
        String acc_year = asmt.getAccYear();
        String quarter_no = asmt.getQuarterNo();
        String tds_type_code = asmt.getTdsTypeCode();
        String Url = "";

        try {
            String resultValue = (String) session.get("FORM16RESULT");
            resultValue = utl.isnull(resultValue) ? "" : resultValue;
            if (!utl.isnull(resultValue)) {
                setSessionResult(resultValue);
                session.remove("FORM16RESULT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lhssysParametersDAO = factory.getLhssysParametersDAO();
            String tdsCerDFlag = "Q";
            try {
                tdsCerDFlag = lhssysParametersDAO.readParametersBy("TDS_CERT_DOWNLOAD_FLAG").getParameter_value();
            } catch (Exception e) {
                e.printStackTrace();
            }
           // System.out.println("tdsCerDFlag--->" + tdsCerDFlag);
            setDownloadType(tdsCerDFlag);

            if (tdsCerDFlag.equalsIgnoreCase("F")) {
                tannoList = new ArrayList<ArrayList<String>>();
                ArrayList<String> a = new ArrayList<String>();
              //  String src = "F:\\TDS_CERTIFICATE\\CLIENT_DATA\\2020-21\\Q4\\bankbranchcode.ZIP";
                a.add(client.getBank_branch_code());
                a.add("");
                a.add("");
                tannoList.add(a);
            } else {
                String query = new DownloadTdsCertificateDB().getSingleForm16Query(client, asmt, getTanno());
                utl.generateSpecialLog("Tan Number Query is--->", query);
                DbFunctionExecutorAsString dbQuery = new DbFunctionExecutorAsString();
                tannoList = dbQuery.execute_oracle_query_as_list(query);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnType;
    }

    public DownloadTdsCertificateAction() {

        utl = new Util();
    }
    private String urlPath;
    private String panno;
    private String tanno;
    private String action;
    private String uploadFileFlag;
    private String sessionResult;
    private String downloadType;
    InputStream inputStream;
    private ArrayList<ArrayList<String>> tannoList;

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public ArrayList<ArrayList<String>> getTannoList() {
        return tannoList;
    }

    public void setTannoList(ArrayList<ArrayList<String>> tannoList) {
        this.tannoList = tannoList;
    }

    private Map<String, Object> session;
    Util utl;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getUploadFileFlag() {
        return uploadFileFlag;
    }

    public void setUploadFileFlag(String uploadFileFlag) {
        this.uploadFileFlag = uploadFileFlag;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
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

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

}
