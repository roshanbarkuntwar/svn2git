/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcLhsTds15GHDefaultError;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class ErrorManipulationAction15GH extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;

    private String action;
    private String errorCode;
    private String reviewValue;
    private String processCnfChkBx;
    private String ColumnCnfData;
    private String TableName;
    private String ColumnName;
    private String errType;
    private String defaultColumnName;
    private String defaultColumnValue;

    private InputStream inputStream;

    public ErrorManipulationAction15GH() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";

        if (!utl.isnull(getAction())) {
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String l_acc_year = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();
            Date a_from_date = asmt.getQuarterFromDate();
            Date a_to_date = asmt.getQuarterToDate();
            int l_quarter_no = Integer.parseInt(quarterNo);
            String l_tds_type_code = asmt.getTdsTypeCode();

            if (getAction().equalsIgnoreCase("updateDefaultValue")) {
                try {
                    ProcLhsTds15GHDefaultError updateValuesProc = new ProcLhsTds15GHDefaultError();
                    int proc_result = updateValuesProc.executeProcedure(l_entity_code, l_client_code, l_acc_year, quarterNo, a_from_date, a_to_date, l_tds_type_code, "", getErrorCode());

//                    ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
//                    String updateDefaultValueQuery = proc_err.updateDefaultValueQuery15GH(asmt, l_entity_code, l_client_code, getErrorCode(), getDefaultColumnName(),
//                            getDefaultColumnValue(), utl);
//                    utl.generateSpecialLog("15GH-PE-0006 (Update Deductee With Default Value Query)----", updateDefaultValueQuery);
//
//                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
//                    boolean result = objDbFunctionClass.execute_oracle_update_function_as_string(updateDefaultValueQuery);
                    boolean result = (proc_result == 0);
                    //System.out.println("result---" + result);
                    if (result) {
//                        try {
//                            String delete_15gh_error_query = proc_err.delete15GHErrorQuery(asmt, l_entity_code, l_client_code, getErrorCode());
//                            System.out.println("updateDefaultValueQuery...." + updateDefaultValueQuery);
//
//                            DbFunctionExecutorAsString objDbFunctionClass1 = new DbFunctionExecutorAsString();
//                            boolean delresult1 = objDbFunctionClass1.execute_oracle_update_function_as_string(delete_15gh_error_query);
//
//                            if (delresult1) {
//                                String delete_tran_load_error_15gh_query = proc_err.deleteTranLoadError15GHQuery(asmt, l_entity_code, l_client_code, getErrorCode());
//                                System.out.println("delete_tran_load_error_15gh_query...." + delete_tran_load_error_15gh_query);
//
//                                DbFunctionExecutorAsString objDbFunctionClass2 = new DbFunctionExecutorAsString();
//                                boolean delresult2 = objDbFunctionClass2.execute_oracle_update_function_as_string(delete_tran_load_error_15gh_query);
//                                System.out.println("Delete tran_load_error_15gh result..." + delresult2);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                        l_return_message = "update";
//                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                        session.put("ERRORCLASS", ErrorType.successMessage);
                        session.put("session_result", "Record Update Successfully");
//                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("BulkReview")) {
                ProcessErrorDB15GH proc_err = new ProcessErrorDB15GH();
                try {
                    String l_review_query = proc_err.getReviewUpdate15GHQuery(asmt, l_entity_code, l_client_code, getReviewValue(), getErrorCode());

                    DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                    boolean result = objDbFunctionClass.execute_oracle_update_function_as_string(l_review_query);
                    if (result) {
                        l_return_message = "updateReview";
                        if (!utl.isnull(getProcessCnfChkBx()) && getProcessCnfChkBx().equalsIgnoreCase("false")) {
                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("session_result", "Record Update Successfully");
                        }
                    } else {
                        l_return_message = "error";
                    }
                } catch (Exception e) {
                    l_return_message = "error";
                    e.printStackTrace();
                }
            } else if (getAction().equalsIgnoreCase("checkReprocessError")) {

            }
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getDefaultColumnValue(String defaultColumnName, ViewClientMast viewClientMast, Assessment asmt) {
        String finalColumnName = "";
        String finalDefaultValue = "";
        try {
            if (!utl.isnull(defaultColumnName)) {
                if (defaultColumnName.equalsIgnoreCase("identification_no")) {
                    finalColumnName = "UNIQUEIDNUMBER";
                } else if (defaultColumnName.equalsIgnoreCase("deductee_name")) {
                    finalColumnName = "ASSESSEENAME";
                } else if (defaultColumnName.equalsIgnoreCase("panno")) {
                    finalColumnName = "ASSESSEEPAN";
                } else if (defaultColumnName.equalsIgnoreCase("dob")) {
                    finalColumnName = "DOB";
                } //                else if (defaultColumnName.equalsIgnoreCase("")) {
                //                    finalColumnName = "STATUS";
                //                } 
                else if (defaultColumnName.equalsIgnoreCase("resident_status")) {
                    finalColumnName = "RESIDENTIALSTATUS";
                } else if (defaultColumnName.equalsIgnoreCase("ADD1")) {
                    finalColumnName = "FLATDOORNO";
                } else if (defaultColumnName.equalsIgnoreCase("ADD2")) {
                    finalColumnName = "PREMISESNAME";
                } else if (defaultColumnName.equalsIgnoreCase("ADD3")) {
                    finalColumnName = "ROADORSTREET";
                } else if (defaultColumnName.equalsIgnoreCase("ADD4")) {
                    finalColumnName = "LOCALITYORAREA";
                } else if (defaultColumnName.equalsIgnoreCase("city_code")) {
                    finalColumnName = "CITYORTOWNORDISTRICT";
                } else if (defaultColumnName.equalsIgnoreCase("state_code")) {
                    finalColumnName = "STATECODE";
                } else if (defaultColumnName.equalsIgnoreCase("pin")) {
                    finalColumnName = "PINCODE";
                } else if (defaultColumnName.equalsIgnoreCase("email_id")) {
                    finalColumnName = "EMAILADDRESS";
                } else if (defaultColumnName.equalsIgnoreCase("stdcode")) {
                    finalColumnName = "STDCODE";
                } else if (defaultColumnName.equalsIgnoreCase("phoneno")) {
                    finalColumnName = "PHONENO";
                } else if (defaultColumnName.equalsIgnoreCase("mobileno")) {
                    finalColumnName = "MOBILENO";
                } //                else if (defaultColumnName.equalsIgnoreCase("")) {
                //                    finalColumnName = "TAXASSESSEDFLAG";
                //                } 
                else if (defaultColumnName.equalsIgnoreCase("latest_assessment_year")) {
                    finalColumnName = "LATESTASSTYR";
                } else if (defaultColumnName.equalsIgnoreCase("est_declaration_income")) {
                    finalColumnName = "ESTIMATEDINC";
                } else if (defaultColumnName.equalsIgnoreCase("est_total_income")) {
                    finalColumnName = "ESTIMATEDTOTALINCPRVYR";
                } else if (defaultColumnName.equalsIgnoreCase("total_no_form")) {
                    finalColumnName = "TOTALNOOFFORM15G";
                } else if (defaultColumnName.equalsIgnoreCase("aggregate_income_amt")) {
                    finalColumnName = "AGGREGATEAMTFORM15G";
                } else if (defaultColumnName.equalsIgnoreCase("declaration_date")) {
                    finalColumnName = "DECLARATIONDATE";
                } else if (defaultColumnName.equalsIgnoreCase("income_amount_paid")) {
                    finalColumnName = "AMTOFINCPAID";
                } else if (defaultColumnName.equalsIgnoreCase("income_paid_date")) {
                    finalColumnName = "DATEINCPAID";
                } else if (defaultColumnName.equalsIgnoreCase("account_no")) {
                    finalColumnName = "IDENFICATIONNUM";
                } else if (defaultColumnName.equalsIgnoreCase("nature_of_income")) {
                    finalColumnName = "NATUREOFINC";
                } else if (defaultColumnName.equalsIgnoreCase("section")) {
                    finalColumnName = "DEDUCTSECTION";
                } else if (defaultColumnName.equalsIgnoreCase("amount")) {
                    finalColumnName = "AMTOFINC";
                }

                String defaultValue = "";
                try {
                    DbFunctionExecutorAsString efforrefno = new DbFunctionExecutorAsString();
                    String defaultValueQuery = "select " + finalColumnName + " from view_bf15gh_default_set_value";
                    defaultValue = efforrefno.execute_oracle_function_as_string(defaultValueQuery);
                } catch (Exception e) {
                    defaultValue = "";
                }

                try {
                    if (!utl.isnull(defaultValue)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        if (defaultValue.equalsIgnoreCase("Deductor_State_code")) {
                            finalDefaultValue = viewClientMast.getDeductor_state_code();
                        } else if (defaultValue.equalsIgnoreCase("Deductor_City_code")) {
                            finalDefaultValue = viewClientMast.getDeductor_state_code();
                        } else if (defaultValue.equalsIgnoreCase("Deductor_Pin")) {
                            finalDefaultValue = viewClientMast.getDeductor_pin();
                        } else if (defaultValue.equalsIgnoreCase("Qtr_First_date")) {
                            finalDefaultValue = sdf.format(asmt.getQuarterFromDate());
                        } else if (defaultValue.equalsIgnoreCase("DEDUCTEE_ESTIMATEDINC")) {
                            finalDefaultValue = "";
                        } else if (defaultValue.equalsIgnoreCase("Qtr_Last_date")) {
                            finalDefaultValue = sdf.format(asmt.getQuarterToDate());
                        } else if (defaultValue.equalsIgnoreCase("cur_Year")) {
                            finalDefaultValue = "";
                        } else {
                            finalDefaultValue = defaultValue;
                        }
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalDefaultValue;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReviewValue() {
        return reviewValue;
    }

    public void setReviewValue(String reviewValue) {
        this.reviewValue = reviewValue;
    }

    public String getProcessCnfChkBx() {
        return processCnfChkBx;
    }

    public void setProcessCnfChkBx(String processCnfChkBx) {
        this.processCnfChkBx = processCnfChkBx;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getColumnCnfData() {
        return ColumnCnfData;
    }

    public void setColumnCnfData(String ColumnCnfData) {
        this.ColumnCnfData = ColumnCnfData;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getDefaultColumnName() {
        return defaultColumnName;
    }

    public void setDefaultColumnName(String defaultColumnName) {
        this.defaultColumnName = defaultColumnName;
    }

    public String getDefaultColumnValue() {
        return defaultColumnValue;
    }

    public void setDefaultColumnValue(String defaultColumnValue) {
        this.defaultColumnValue = defaultColumnValue;
    }

}
