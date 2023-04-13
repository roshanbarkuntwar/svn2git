/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kapil.gupta
 */
public class SalarySevaarthProjectionAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private String action;
    private String dataGridAction;
    private Long rowid_seq;
    private String deducteePanno;
    private String deductee_pan;

    public SalarySevaarthProjectionAction() {
        utl = new Util();

    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "success";

        session.put("ACTIVE_TAB", "salaryEntryNew");

        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            if (getAction() != null) {
                if (getRowid_seq() != null) {
                    setDeducteePanno(getDeductee_pan());
                    setDataGridAction("salarySevaarthProjGrid");
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getDeducteePanno() {
        return deducteePanno;
    }

    public void setDeducteePanno(String deducteePanno) {
        this.deducteePanno = deducteePanno;
    }

    public String getDeductee_pan() {
        return deductee_pan;
    }

    public void setDeductee_pan(String deductee_pan) {
        this.deductee_pan = deductee_pan;
    }

}
