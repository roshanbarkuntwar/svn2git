/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class FVUfileDashboardAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnString = "success";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        if (client != null) {
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {
                try {
                    int quarter_no = Integer.parseInt(asmt.getQuarterNo());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

                    DbGenericFunctionExecutor objList = new DbGenericFunctionExecutor();
                    String salaryDtlQry = "";
                    if (!utl.isnull(asmt.getTdsTypeCode()) && asmt.getTdsTypeCode().equalsIgnoreCase("24Q") && quarter_no == 4) {
                        salaryDtlQry = "union all \n"
                                + "select '07' user_seq, 'SALARY_TRAN' data_type,' SALARY SUMMARY TRANSACTION(S)' data_type_name,\n"
                                + "LHS_TDS.GET_TDS_RECO_DETAILS('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",'" + sdf.format(asmt.getQuarterFromDate()).toUpperCase() + "','" + sdf.format(asmt.getQuarterToDate()).toUpperCase() + "','" + asmt.getTdsTypeCode() + "',NULL,'SALARY_TRAN','NO_OF_DEDUCTEE') \n"
                                + "from dual \n"
                                + "union all \n"
                                + "select '08' user_seq, 'SALARY_TRAN' data_type,'SALARY SUMMARY TRANSACTION ERROR(S)' data_type_name,\n"
                                + "LHS_TDS.GET_TDS_RECO_DETAILS('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",'" + sdf.format(asmt.getQuarterFromDate()).toUpperCase() + "','" + sdf.format(asmt.getQuarterToDate()).toUpperCase() + "','" + asmt.getTdsTypeCode() + "',NULL,'SALARY_TRAN','NO_OF_DEDUCTEE_ERROR') \n"
                                + "from dual \n"
                                + "union all \n"
                                + "select '09' user_seq, 'SALARY_TRAN' data_type,'PROCESSED SALARY SUMMARY TRANSACTION(S)' data_type_name,\n"
                                + "LHS_TDS.GET_TDS_RECO_DETAILS('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",'" + sdf.format(asmt.getQuarterFromDate()).toUpperCase() + "','" + sdf.format(asmt.getQuarterToDate()).toUpperCase() + "','" + asmt.getTdsTypeCode() + "',NULL,'SALARY_TRAN','NO_OF_DEDUCTEE_PROCESS') \n"
                                + "from dual \n";
                    }
                    String l_deductee_query
                            = "select user_seq, data_type,data_type_name ,rec_count\n"
                            + "from(\n"
                            + "select '01' user_seq, 'TDS-CHALLAN-TRAN-LOAD' data_type,'Challan Transaction(s)' data_type_name ,\n"
                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_CHALLAN_TRAN_LOAD','NO_OF_CHALLAN') rec_count\n"
                            + "       from dual\n"
                            + "union all\n"
                            + "select '02' user_seq, 'TDS-CHALLAN-TRAN-LOAD-ERROR','Challan Transaction Error(s)' data_type_name,\n"
                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_CHALLAN_TRAN_LOAD','NO_OF_CHALLAN_ERROR') rec_count\n"
                            + "       from dual\n"
                            + "union all\n"
                            + "select '03' user_seq, 'TDS-CHALLAN-TRAN' data_type,'Processed Challan Transaction(s)' data_type_name,\n"
                            //                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_CHALLAN_TRAN','NO_OF_CHALLAN') rec_count\n"
                            + "       null rec_count\n"
                            + "       from dual\n"
                            + "union all\n"
                            + "select '04' user_seq, 'TDS-TRAN-LOAD' data_type,'Deductee Transaction(s)' data_type_name,\n"
                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_TRAN_LOAD','NO_OF_DEDUCTEE') rec_count\n"
                            + "       from dual\n"
                            + "union all\n"
                            + "select '05' user_seq, 'TDS-TRAN-LOAD-ERROR','Deductee Transaction Error(s)' data_type_name,\n"
                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_TRAN_LOAD','NO_OF_DEDUCTEE_ERROR') rec_count\n"
                            + "       from dual\n"
                            + "union all\n"
                            + "select '06' user_seq, 'TDS-TRAN' data_type,'Processed Deductee Transaction(s)' data_type_name,\n"
                            //                            + "       lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "','" + client.getClient_code() + "','" + asmt.getAccYear() + "'," + quarter_no + ",null,null,'" + asmt.getTdsTypeCode() + "',null,'TDS_TRAN','NO_OF_DEDUCTEE') rec_count\n"
                            + "       null rec_count\n"
                            + "       from dual\n"
                            + salaryDtlQry
                            + ")";
                    utl.generateLog(SUCCESS, salaryDtlQry);
                    List<RecordListFVU> tdsProcessRecoListDed = objList.getGenericList(new RecordListFVU(), l_deductee_query);
                    ArrayList<RecordListFVU> obj_challan_list_data = new ArrayList<RecordListFVU>();
                    ArrayList<RecordListFVU> obj_deductee_list_data = new ArrayList<RecordListFVU>();
                    try {
                        if (tdsProcessRecoListDed != null && tdsProcessRecoListDed.size() > 0) {

                            int i = 0;
                            for (RecordListFVU tdsDeducteeList : tdsProcessRecoListDed) {
                                i++;
                                if (i <= 3) {
                                    RecordListFVU obj_challan_list = new RecordListFVU();
                                    obj_challan_list.setUser_Seq(tdsDeducteeList.getUser_Seq());
                                    obj_challan_list.setData_type(tdsDeducteeList.getData_type());
                                    obj_challan_list.setData_type_name(tdsDeducteeList.getData_type_name());
                                    obj_challan_list.setRec_count(tdsDeducteeList.getRec_count());
                                    obj_challan_list_data.add(obj_challan_list);
                                } else {
                                    RecordListFVU obj_deductee_list = new RecordListFVU();
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
//                    System.out.println("obj_challan_list_data--"+obj_challan_list_data.size());
//                    System.out.println("obj_deductee_list_data--"+obj_deductee_list_data.size());
                    setViewTdsProcessRecoListCh(obj_challan_list_data);
                    setViewTdsProcessRecoListDed(obj_deductee_list_data);
//                   System.out.println("obj_deductee_list_data--"+getViewTdsProcessRecoListCh()); 

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else {
        }
        return returnString;
    }//end execute

    public FVUfileDashboardAction() {
        utl = new Util();
    }
    private Util utl;
    private Map<String, Object> session;
//    List<ViewTdsProcessRecoModel> viewTdsProcessRecoListDed;
//    List<ViewTdsProcessRecoModel> viewTdsProcessRecoListCh;
    List<RecordListFVU> viewTdsProcessRecoListDed;
    List<RecordListFVU> viewTdsProcessRecoListCh;
    private Long challanDifference;
    private Long deducteeDifference;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<RecordListFVU> getViewTdsProcessRecoListDed() {
        return viewTdsProcessRecoListDed;
    }

    public void setViewTdsProcessRecoListDed(List<RecordListFVU> viewTdsProcessRecoListDed) {
        this.viewTdsProcessRecoListDed = viewTdsProcessRecoListDed;
    }

    public List<RecordListFVU> getViewTdsProcessRecoListCh() {
        return viewTdsProcessRecoListCh;
    }

    public void setViewTdsProcessRecoListCh(List<RecordListFVU> viewTdsProcessRecoListCh) {
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
