/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

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
public class ProcessDataAction extends ActionSupport implements SessionAware {

    private Util utl;
    private Map<String, Object> session;
    List<ProcessDataRecordList> viewTdsProcessRecoListDed;
    List<ProcessDataRecordList> viewTdsProcessRecoListCh;
    private Long challanDifference;
    private Long deducteeDifference;
    private String value;

    public ProcessDataAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String returnString = "success";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                try {
                    DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();

                    ProcessErrorsDB process_db = new ProcessErrorsDB();
                    String l_deductee_query = process_db.getDeducteeDetailsQuery(asmt, client);


                    List<ProcessDataRecordList> tdsProcessRecoListDed = objList.getGenericList(new ProcessDataRecordList(), l_deductee_query);
                    ArrayList<ProcessDataRecordList> obj_challan_list_data = new ArrayList<ProcessDataRecordList>();
                    ArrayList<ProcessDataRecordList> obj_deductee_list_data = new ArrayList<ProcessDataRecordList>();
                    try {
                        if (tdsProcessRecoListDed != null && tdsProcessRecoListDed.size() > 0) {
                            int i = 0;
                            for (ProcessDataRecordList tdsDeducteeList : tdsProcessRecoListDed) {
                                i++;
                                if (i <= 1) {
                                    ProcessDataRecordList obj_challan_list = new ProcessDataRecordList();
                                    obj_challan_list.setUser_Seq(tdsDeducteeList.getUser_Seq());
                                    obj_challan_list.setData_type(tdsDeducteeList.getData_type());
                                    obj_challan_list.setData_type_name(tdsDeducteeList.getData_type_name());
                                    obj_challan_list.setRec_count(tdsDeducteeList.getRec_count());
                                    obj_challan_list_data.add(obj_challan_list);
                                } else {
                                    ProcessDataRecordList obj_deductee_list = new ProcessDataRecordList();
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
//                    System.out.println("obj_challan_list_data--"+obj_challan_list_data.get(0).getRec_count());
//                    System.out.println("obj_deductee_list_data--"+obj_deductee_list_data.get(0).getRec_count());
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

    public List<ProcessDataRecordList> getViewTdsProcessRecoListDed() {
        return viewTdsProcessRecoListDed;
    }

    public void setViewTdsProcessRecoListDed(List<ProcessDataRecordList> viewTdsProcessRecoListDed) {
        this.viewTdsProcessRecoListDed = viewTdsProcessRecoListDed;
    }

    public List<ProcessDataRecordList> getViewTdsProcessRecoListCh() {
        return viewTdsProcessRecoListCh;
    }

    public void setViewTdsProcessRecoListCh(List<ProcessDataRecordList> viewTdsProcessRecoListCh) {
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
