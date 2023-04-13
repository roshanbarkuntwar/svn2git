/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
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
public class SalarySevaarthProjGridAction extends ActionSupport implements SessionAware {

    Util utl;

    private Map<String, Object> session;

    private InputStream inputStream;
    private String deducteePanno;
    private String Search;

    public SalarySevaarthProjGridAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String return_view = "status_grid";
        StringBuilder sb = new StringBuilder();

        try {
            String moduleFlag = (String) session.get("MODULE");

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            if (clientMast != null && !utl.isnull(getSearch())) {
                SalaryTranLoadDB salDB = new SalaryTranLoadDB();
                String query = salDB.getSalSevaarthProjDataGridQuery(clientMast, asmt, getDeducteePanno());
                utl.generateSpecialLog("Salary Sevaarth Projection Details browse Query", query);
                try {
                    DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                    ArrayList<SalSevaarthProjBrowseEntity> queryData = db.getGenericList(new SalSevaarthProjBrowseEntity(), query);
                    if (queryData != null && queryData.size() > 0) {
                        SalSevaarthProjSupport dataGrid = new SalSevaarthProjSupport();
                        sb = dataGrid.getBrowseGrid(queryData);
                    } else {

                        sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
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

    public String getDeducteePanno() {
        return deducteePanno;
    }

    public void setDeducteePanno(String deducteePanno) {
        this.deducteePanno = deducteePanno;
    }

    public String getSearch() {
        return Search;
    }

    public void setSearch(String Search) {
        this.Search = Search;
    }

}
