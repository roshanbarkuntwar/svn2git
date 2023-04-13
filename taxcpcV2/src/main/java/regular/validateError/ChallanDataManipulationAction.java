/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.opensymphony.xwork2.ActionSupport;
import dao.TdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranLoad;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author trainee
 */
public class ChallanDataManipulationAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private ArrayList<Boolean> editCheckBox;
    private ArrayList<TdsChallanTranLoad> tdsChallanTranLoad;

    public ChallanDataManipulationAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            if (!utl.isnull(getAction())) {

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                if (getAction().equalsIgnoreCase("ChallanBlkupdate")) {
                    if (getEditCheckBox() != null && getEditCheckBox().size() > 0) {
                        if (getTdsChallanTranLoad() != null && getTdsChallanTranLoad().size() > 0) {

                            ArrayList<TdsChallanTranLoad> challanTranLoads = new ArrayList<TdsChallanTranLoad>();
                            for (int i = 0; i < getEditCheckBox().size(); i++) {
                                Boolean checkBox = getEditCheckBox().get(i);
                                if (checkBox) {
                                    TdsChallanTranLoad challanTranLoad = getTdsChallanTranLoad().get(i);
                                    challanTranLoads.add(challanTranLoad);
                                }// end if
                            }// end for loop (iterate checkbox)
                            int updateCount = 0;
                            TdsChallanTranLoadDAO objtdschallandao = null;
                            for (TdsChallanTranLoad tdsChallanTranLoad1 : challanTranLoads) {
                                objtdschallandao = factory.getTdsChallanTranLoadDAO();
                                TdsChallanTranLoad tdschallantranload = objtdschallandao.readChallanBySequenceID(tdsChallanTranLoad1.getRowid_seq());
                                if (tdschallantranload != null) {
                                    try {
                                        ShowErrorDataManipulationAction objdata = new ShowErrorDataManipulationAction();
                                        tdschallantranload.setBank_bsr_code(tdsChallanTranLoad1.getBank_bsr_code());
                                        try {
                                            if (tdsChallanTranLoad1.getTds_challan_date() != null) {
                                                Date challanDate = sdf1.parse(tdsChallanTranLoad1.getTds_challan_date());
                                                String l_tds_challan_date = (sdf.format(challanDate)).toUpperCase();
                                                tdschallantranload.setTds_challan_date(l_tds_challan_date);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        tdschallantranload.setTds_challan_no(tdsChallanTranLoad1.getTds_challan_no());
                                        tdschallantranload.setTds_challan_tds_amt(objdata.getAmountAsNormalFormat(tdsChallanTranLoad1.getTds_challan_tds_amt()));
                                        tdschallantranload.setTds_code(tdsChallanTranLoad1.getTds_code());
                                        try {
                                            DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                            String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + tdsChallanTranLoad1.getTds_code() + "'";
                                            String resultValue = ef.execute_oracle_function_as_string(functionString);
                                            tdschallantranload.setTds_section(resultValue);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        tdschallantranload.setTds_challan_int_amt(objdata.getAmountAsNormalFormat(tdsChallanTranLoad1.getTds_challan_int_amt()));
                                        tdschallantranload.setTds_challan_other_amt(objdata.getAmountAsNormalFormat(tdsChallanTranLoad1.getTds_challan_other_amt()));
                                        tdschallantranload.setTds_challan_minor_head(tdsChallanTranLoad1.getTds_challan_minor_head());
                                        objtdschallandao = factory.getTdsChallanTranLoadDAO();
                                        boolean update_result = objtdschallandao.update(tdschallantranload);
                                        //System.out.println("update_result...." + update_result);
                                        if (update_result) {
                                            updateCount += 1;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }//end main if
                            }
                            //System.out.println("updateCount.." + updateCount);
                            if (updateCount > 0) {
                                if (updateCount == challanTranLoads.size()) {
                                    l_return_message = "updateData";
                                } else {
                                    l_return_message = "error";
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

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

    public ArrayList<Boolean> getEditCheckBox() {
        return editCheckBox;
    }

    public void setEditCheckBox(ArrayList<Boolean> editCheckBox) {
        this.editCheckBox = editCheckBox;
    }

    public ArrayList<TdsChallanTranLoad> getTdsChallanTranLoad() {
        return tdsChallanTranLoad;
    }

    public void setTdsChallanTranLoad(ArrayList<TdsChallanTranLoad> tdsChallanTranLoad) {
        this.tdsChallanTranLoad = tdsChallanTranLoad;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
