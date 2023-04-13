/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcDeducteeMastInsert;
import dao.DbProc.ProcPanMastInsert;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ErrorDataManipulationAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private ArrayList<Boolean> editCheckBox;
    private ArrayList<TdsTranLoad> tdsTranLoad;
    private ArrayList<String> tdsTypeCode;

    public ErrorDataManipulationAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

        if (!utl.isnull(getAction())) {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            if (getAction().equalsIgnoreCase("BulkUpdate")) {
                if (getEditCheckBox() != null && getEditCheckBox().size() > 0) {
                    if (getTdsTranLoad() != null && getTdsTranLoad().size() > 0) {
                        ArrayList<TdsTranLoad> tdsTranLoads = new ArrayList<TdsTranLoad>();
                        ArrayList<String> hiddenTdsTypeCode = new ArrayList<String>();
                        for (int i = 0; i < getEditCheckBox().size(); i++) {
                            Boolean checkBox = getEditCheckBox().get(i);
                            if (checkBox) {
                                TdsTranLoad get = getTdsTranLoad().get(i);
                                tdsTranLoads.add(get);
                                hiddenTdsTypeCode.add(getTdsTypeCode().get(i));
                            }// end if
                        }// end for loop (iterate checkbox)
                        int updateCount = 0;
                        TdsTranLoadDAO tdsTranLoadDAO = null;
                        int count = 0;
                        for (TdsTranLoad tdsTranLoad1 : tdsTranLoads) {
                            if (!utl.isnull(tdsTranLoad1.getDeductee_panno()) && !utl.isnull(tdsTranLoad1.getDeductee_name()) && !utl.isnull(hiddenTdsTypeCode.get(count))) {
                                try {
                                    ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
                                    Assessment asmt = (Assessment) session.get("ASSESSMENT");
                                    if (clientMast != null) {
                                        String l_client_code = clientMast.getClient_code();
                                        String l_entity_code = clientMast.getEntity_code();
                                        String a_acc_year = asmt.getAccYear();
                                        String tds_type_code = asmt.getTdsTypeCode();
                                        String quarterNumber = asmt.getQuarterNo();
                                        int l_quarter_no = Integer.parseInt(quarterNumber);
                                        String dbdeducteeCode = "";
                                        try {
                                            DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                                            String dbdeducteeCodequery = "select lhs_tds.get_deductee_code('" + l_entity_code + "','" + l_client_code + "','" + tdsTranLoad1.getDeductee_panno() + "','" + tdsTranLoad1.getDeductee_name() + "','" + hiddenTdsTypeCode.get(count) + "')from dual";
//                                System.out.println("dbdeducteeCodequery..." + dbdeducteeCodequery);
                                            dbdeducteeCode = objDbFunctionClass.execute_oracle_function_as_string(dbdeducteeCodequery);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        if (utl.isnull(dbdeducteeCode)) {
                                            try {
                                                String panno_letter = "";
                                                if (!utl.isnull(tdsTranLoad1.getDeductee_panno()) && tdsTranLoad1.getDeductee_panno().length() > 3) {
                                                    panno_letter = (tdsTranLoad1.getDeductee_panno().substring(3, 4)).toUpperCase();
                                                }
                                                ProcDeducteeMastInsert objinsertProc = new ProcDeducteeMastInsert();
                                                objinsertProc.execute_procedure(l_entity_code, l_client_code, tdsTranLoad1.getDeductee_name(), tdsTranLoad1.getDeductee_panno(), tdsTranLoad1.getRowid_seq(), panno_letter, hiddenTdsTypeCode.get(count));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                ProcPanMastInsert objpaninsert = new ProcPanMastInsert();
                                                objpaninsert.execute_procedure(l_entity_code, l_client_code, a_acc_year, l_quarter_no, tds_type_code, tdsTranLoad1.getDeductee_panno(), tdsTranLoad1.getDeductee_name());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }// end if
                            // update tds_tran_load record
                            tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                            TdsTranLoad objtdstranload = tdsTranLoadDAO.readTDSBySequenceID(tdsTranLoad1.getRowid_seq());
                            if (objtdstranload != null) {
                                try {
                                    objtdstranload.setTds_code(tdsTranLoad1.getTds_code());
                                    objtdstranload.setDeductee_name(tdsTranLoad1.getDeductee_name());
                                    objtdstranload.setDeductee_panno(tdsTranLoad1.getDeductee_panno());
                                    try {
                                        if (tdsTranLoad1.getTran_ref_date() != null) {
                                            Date tranRefDate = sdf1.parse(tdsTranLoad1.getTran_ref_date());
                                            String l_tran_ref_date = (sdf.format(tranRefDate)).toUpperCase();
                                            objtdstranload.setTran_ref_date(l_tran_ref_date);
                                        }
                                    } catch (Exception e) {
                                    }
                                    objtdstranload.setTran_amt(tdsTranLoad1.getTran_amt());
                                    try {
                                        if (tdsTranLoad1.getTds_deduct_date() != null) {
                                            Date tdsDeductDate = sdf1.parse(tdsTranLoad1.getTds_deduct_date());
                                            String l_tds_deduct_date = (sdf.format(tdsDeductDate)).toUpperCase();
                                            objtdstranload.setTds_deduct_date(l_tds_deduct_date);
                                        }
                                    } catch (Exception e) {
                                    }
                                    objtdstranload.setTds_deduct_reason(tdsTranLoad1.getTds_deduct_reason());
                                    objtdstranload.setCertificate_no(tdsTranLoad1.getCertificate_no());
                                    objtdstranload.setTran_amt(getAmountAsNormalFormat(tdsTranLoad1.getTran_amt()));
                                    objtdstranload.setItax_rate(getAmountAsNormalFormat(tdsTranLoad1.getItax_rate()));
                                    objtdstranload.setTds_amt(getAmountAsNormalFormat(tdsTranLoad1.getTds_amt()));
                                    objtdstranload.setSurcharge_amt(getAmountAsNormalFormat(tdsTranLoad1.getSurcharge_amt()));
                                    objtdstranload.setCess_amt(getAmountAsNormalFormat(tdsTranLoad1.getCess_amt()));
                                    
                                    try {
                                        String l_tds_amt = utl.isnull(tdsTranLoad1.getTds_amt()) ? "0" : getAmountAsNormalFormat(tdsTranLoad1.getTds_amt());
                                        String l_surcharge_amt = utl.isnull(tdsTranLoad1.getSurcharge_amt()) ? "0" : getAmountAsNormalFormat(tdsTranLoad1.getSurcharge_amt());
                                        String l_cess_amt = utl.isnull(tdsTranLoad1.getCess_amt()) ? "0" : getAmountAsNormalFormat(tdsTranLoad1.getCess_amt());
                                        int l_tds_base_amt = Integer.parseInt(l_tds_amt) + Integer.parseInt(l_surcharge_amt) + Integer.parseInt(l_cess_amt);
                                        //System.out.println("l_tds_base_amt..." + l_tds_base_amt);
                                        objtdstranload.setTds_base_amt(String.valueOf(l_tds_base_amt));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (!utl.isnull(hiddenTdsTypeCode.get(count)) && hiddenTdsTypeCode.get(count).equalsIgnoreCase("27Q")) {
                                        objtdstranload.setNature_of_remittance(tdsTranLoad1.getNature_of_remittance());
                                        objtdstranload.setCountry_code(tdsTranLoad1.getCountry_code());
                                        objtdstranload.setRate_type(tdsTranLoad1.getRate_type());
                                    }
                                    try {
                                        DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                        String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + tdsTranLoad1.getTds_code() + "'";
                                        String resultValue = ef.execute_oracle_function_as_string(functionString);
                                        objtdstranload.setTds_section(resultValue);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                                    boolean update_result = tdsTranLoadDAO.update(objtdstranload);
                                    if (update_result) {
                                        updateCount += 1;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            // update tds_tran_load record
                        }
                        if (updateCount > 0) {
                            if (updateCount == tdsTranLoads.size()) {
                                l_return_message = "updateData";
                            }
                        }
                    }
                }
            }// end if
        }// end if action
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getAmountAsNormalFormat(String amount) {
        String l_return_value = amount;
        try {
            if (!utl.isnull(amount)) {
                if (amount.contains(".")) {
                    String splitAmt[] = amount.split("\\.");
                    String l_amt1 = "0";
                    try {
                        l_amt1 = splitAmt[0];
                    } catch (Exception e) {
                        l_amt1 = "0";
                    }
                    String l_amt2 = "0";
                    try {
                        l_amt2 = splitAmt[1];
                    } catch (Exception e) {
                        l_amt2 = "0";
                    }
                    if (!utl.isnull(l_amt2) && l_amt2.equals("0")) {
                        if (!utl.isnull(l_amt1)) {
                            if (amount.contains(",")) {
                                l_amt1 = l_amt1.replaceAll("\\,", "");
                            }
                            l_return_value = l_amt1;
                        }
                    } else if (!utl.isnull(l_amt2) && l_amt2.equals("00")) {
                        if (!utl.isnull(l_amt1)) {
                            if (amount.contains(",")) {
                                l_amt1 = l_amt1.replaceAll("\\,", "");
                            }
                            l_return_value = l_amt1;
                        }
                    } else if (!utl.isnull(l_amt2) && l_amt2.equals("000")) {
                        if (!utl.isnull(l_amt1)) {
                            if (amount.contains(",")) {
                                l_amt1 = l_amt1.replaceAll("\\,", "");
                            }
                            l_return_value = l_amt1;
                        }
                    } else {
                        if (amount.contains(",")) {
                            amount = amount.replaceAll("\\,", "");
                        }
                        l_return_value = amount;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //utl.generateLog("l_return_value...", l_return_value);
        if (utl.isnull(l_return_value)) {
            l_return_value = "0";
        }
        return l_return_value;
    }//end method

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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

    public ArrayList<Boolean> getEditCheckBox() {
        return editCheckBox;
    }

    public void setEditCheckBox(ArrayList<Boolean> editCheckBox) {
        this.editCheckBox = editCheckBox;
    }

    public ArrayList<TdsTranLoad> getTdsTranLoad() {
        return tdsTranLoad;
    }

    public void setTdsTranLoad(ArrayList<TdsTranLoad> tdsTranLoad) {
        this.tdsTranLoad = tdsTranLoad;
    }

    public ArrayList<String> getTdsTypeCode() {
        return tdsTypeCode;
    }

    public void setTdsTypeCode(ArrayList<String> tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
    }

}//end class
