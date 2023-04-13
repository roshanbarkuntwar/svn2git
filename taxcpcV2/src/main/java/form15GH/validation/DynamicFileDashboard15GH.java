/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class DynamicFileDashboard15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    Util utl;

    List<DynamicProcessRecordList15GH> viewTdsProcessRecoListDed;
    List<DynamicProcessRecordList15GH> viewTdsProcessRecoListCh;
    private Long challanDifference;
    private Long deducteeDifference;
    private String value;

    public DynamicFileDashboard15GH() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String returnString = "success";
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");

        String l_client_code = "";
        String l_entity_code = "";

        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                try {
                    int quarter_no = 0;
                    l_client_code = client.getClient_code();
                    l_entity_code = client.getEntity_code();
                    String quarterNo = asmt.getQuarterNo();
                    quarter_no = Integer.parseInt(quarterNo);

                    ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                    DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();

                    String l_deductee_query = proc_err.getDynamicFileDashboardDetails15GHQuery(asmt, l_entity_code, l_client_code);

                    utl.generateSpecialLog("DynamicFileDashboard15GH 53 ----", l_deductee_query);

                    List<DynamicProcessRecordList15GH> tdsProcessRecoListDed = objList.getGenericList(new DynamicProcessRecordList15GH(), l_deductee_query);
                    ArrayList<DynamicProcessRecordList15GH> obj_challan_list_data = new ArrayList<DynamicProcessRecordList15GH>();
                    ArrayList<DynamicProcessRecordList15GH> obj_deductee_list_data = new ArrayList<DynamicProcessRecordList15GH>();
                    try {
                        if (tdsProcessRecoListDed != null && tdsProcessRecoListDed.size() > 0) {

                            int i = 0;
                            for (DynamicProcessRecordList15GH tdsDeducteeList : tdsProcessRecoListDed) {
                                i++;
                                if (i <= 1) {
                                    DynamicProcessRecordList15GH obj_challan_list = new DynamicProcessRecordList15GH();
                                    obj_challan_list.setUser_Seq(tdsDeducteeList.getUser_Seq());
                                    obj_challan_list.setData_type(tdsDeducteeList.getData_type());
                                    obj_challan_list.setData_type_name(tdsDeducteeList.getData_type_name());
                                    obj_challan_list.setRec_count(tdsDeducteeList.getRec_count());
                                    obj_challan_list_data.add(obj_challan_list);
                                } else {
                                    DynamicProcessRecordList15GH obj_deductee_list = new DynamicProcessRecordList15GH();
                                    obj_deductee_list.setUser_Seq(tdsDeducteeList.getUser_Seq());
                                    obj_deductee_list.setData_type(tdsDeducteeList.getData_type());
                                    obj_deductee_list.setData_type_name(tdsDeducteeList.getData_type_name());
                                    obj_deductee_list.setRec_count(tdsDeducteeList.getRec_count());
                                    obj_deductee_list_data.add(obj_deductee_list);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setViewTdsProcessRecoListCh(obj_challan_list_data);
                    setViewTdsProcessRecoListDed(obj_deductee_list_data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else {
        }
        return returnString;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public List<DynamicProcessRecordList15GH> getViewTdsProcessRecoListDed() {
        return viewTdsProcessRecoListDed;
    }

    public void setViewTdsProcessRecoListDed(List<DynamicProcessRecordList15GH> viewTdsProcessRecoListDed) {
        this.viewTdsProcessRecoListDed = viewTdsProcessRecoListDed;
    }

    public List<DynamicProcessRecordList15GH> getViewTdsProcessRecoListCh() {
        return viewTdsProcessRecoListCh;
    }

    public void setViewTdsProcessRecoListCh(List<DynamicProcessRecordList15GH> viewTdsProcessRecoListCh) {
        this.viewTdsProcessRecoListCh = viewTdsProcessRecoListCh;
    }

    public Long getChallanDifference() {
        return challanDifference;
    }

    public void setChallanDifference(Long challanDifference) {
        this.challanDifference = challanDifference;
    }

    public Long getDeducteeDifference() {
        return deducteeDifference;
    }

    public void setDeducteeDifference(Long deducteeDifference) {
        this.deducteeDifference = deducteeDifference;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
