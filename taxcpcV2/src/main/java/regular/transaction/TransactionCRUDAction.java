/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.transaction;

import com.google.gson.Gson;
import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcMultipleDeleteTranLoad;
import dao.DbProc.ProcPanMastInsert;
import dao.DbProc.ProcTdsChallanRebuild;
import dao.LhssysFileTranDAO;
import dao.TdsSplRateMastDAO;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.DeducteeBflagRefinfoTran;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class TransactionCRUDAction extends ActionSupport implements SessionAware {

    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();

    @Override
    public String execute() throws Exception {//System.out.println("clll class");
        String l_return_value = "success";
        String l_return_message = "";
        int counting=0 ;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");
        ProcPanMastInsert panmastMastInsert = new ProcPanMastInsert();
        if (!utl.isnull(getAction())) {
            if (getAction().equalsIgnoreCase("save")) {
                try {
                    Date Tran_deduct_date = sdf.parse(getTdsTranLoad().getTds_deduct_date());
                    Date Tran_ref_date = sdf.parse(getTdsTranLoad().getTran_ref_date());

                    DateTimeUtil dutil = new DateTimeUtil();
                    boolean check_tran_ref_date = dutil.check_date_less_then_selected_dates("dd-MM-yyyy", asmt.getQuarterToDate(), Tran_ref_date);
                    if (check_tran_ref_date) {
                        /**
                         * Before save the transaction, TDS section is getting
                         * validated here.
                         *
                         * If the TDS section is invalid then the transaction
                         * will not be saved in db and will return tdsError.
                         */
                        boolean isValidTds = checkTdsValidOrNot(getTdsTranLoad().getTds_code(), asmt.getTdsTypeCode(), Tran_ref_date);
                        if (!isValidTds) {
                            l_return_message = "tdsError";
                            inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));
                            return l_return_value;
                        }

                        /**
                         * If the TDS section is valid then the transaction will
                         * be saved in db.
                         */
                        Long rowid_seq = 0l;
                        boolean check_date = dutil.check_date_between_two_dates("dd-MM-yyyy", asmt.getQuarterFromDate(), asmt.getQuarterToDate(), Tran_deduct_date);

                        if (check_date) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + getTdsTranLoad().getTds_code() + "'";
                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                getTdsTranLoad().setTds_section(resultValue);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            getTdsTranLoad().setDeductee_ref_code1(getTdsTranLoad().getDeductee_ref_code1().toUpperCase());
                            Date tranRefDate = sdf.parse(getTdsTranLoad().getTran_ref_date());
                            Date tdsDeductDate = sdf.parse(getTdsTranLoad().getTds_deduct_date());
                            String l_tran_ref_date = (sdf1.format(tranRefDate)).toUpperCase();
                            String l_tds_deduct_date = (sdf1.format(tdsDeductDate)).toUpperCase();

                            getTdsTranLoad().setClient_code(client.getClient_code());
                            getTdsTranLoad().setEntity_code(client.getEntity_code());
                            getTdsTranLoad().setAcc_year(asmt.getAccYear());
                            getTdsTranLoad().setAssesment_acc_year(utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear()));
                            getTdsTranLoad().setQuarter_no(asmt.getQuarterNo());
                            getTdsTranLoad().setDeductee_panno((getTdsTranLoad().getDeductee_panno()).toUpperCase());
                            getTdsTranLoad().setDeductee_code(getTdsTranLoad().getDeductee_code());
                            getTdsTranLoad().setTds_type_code(asmt.getTdsTypeCode());
                            getTdsTranLoad().setFrom_date(asmt.getQuarterFromDate());
                            getTdsTranLoad().setTo_date(asmt.getQuarterToDate());
                            getTdsTranLoad().setTran_ref_date(l_tran_ref_date);
                            getTdsTranLoad().setTds_deduct_date(l_tds_deduct_date);
                            getTdsTranLoad().setInsert_flag("F");
                            getTdsTranLoad().setClient_panno((String) session.get("CLIENTPANNO"));
                            getTdsTranLoad().setClient_tanno((String) session.get("CLIENTTANNO"));
                            getTdsTranLoad().setData_entry_mode("M");
                            getTdsTranLoad().setUser_code(user.getUser_code());

                            if (!utl.isnull(getTdsTranLoad().getTds_deduct_reason())) {
                                if (getTdsTranLoad().getTds_deduct_reason().equalsIgnoreCase("B") || getTdsTranLoad().getTds_deduct_reason().equalsIgnoreCase("D")) {

                                    if (!utl.isnull(getL_reference_no())) {
                                        getTdsTranLoad().setCertificate_no(getL_reference_no());
                                    }
                                }//end check deduct reason
                            }
                            String rowid_seqQuery = "select TDS_TRAN_LOAD_ROWID_SEQ.Nextval from dual";
                            DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                            rowid_seq = objDBFunction.execute_oracle_function_as_integar(rowid_seqQuery);
                            utl.generateLog("rowid_seqQuery.........", rowid_seqQuery);
                            getTdsTranLoad().setRowid_seq(rowid_seq);
                            getTdsTranLoad().setDeductee_code(rowid_seq.toString());
                            TdsTranLoadDAO tdstran = factory.getTdsTranLoadDAO();
                            boolean save_result = tdstran.save(getTdsTranLoad());
                            if (save_result) {
                                try {
                                    panmastMastInsert.execute_procedure(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), Integer.parseInt(asmt.getQuarterNo()), asmt.getTdsTypeCode(), getTdsTranLoad().getDeductee_panno(), getTdsTranLoad().getDeductee_name());
                                } catch (Exception e) {
                                }
//                                utl.generateLog("1...");
                                session.put("ERRORCLASS", MessageType.successMessage);
                                if (!utl.isnull(getSaveAndContinue()) && getSaveAndContinue().equalsIgnoreCase("true")) {
                                    session.put("TDSRSLTONSAVE", "Record Saved Successfully");
                                    l_return_message = "success1";
                                } else {
                                    session.put("TDSRSLTONSAVE", "Record Saved Successfully");
                                    l_return_message = "success";
                                }
                            } else {
                                l_return_message = "Could not save, Some Error Occured";
                            }
                        } else {
                            l_return_message = "refDateError";
                        }
                    } else {
                        l_return_message = "tranrRefDateError";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (getAction().equalsIgnoreCase("update")) {
                TdsTranLoadDAO tdstran = factory.getTdsTranLoadDAO();
                //utl.generateLog("TDS tran load row seq ID: " + getTdsTranLoad().getRowid_seq());

               // TdsTranLoad objtds = tdstran.readById(getTdsTranLoad().getRowid_seq(), true);
               TdsTranLoad objtds = tdstran.readTDSBySequenceID(getTdsTranLoad().getRowid_seq());

                //utl.generateLog("TdsTranLoad---" + objtds);
                if (objtds != null) {
                    Date Tran_deduct_date = null;
                    Date Tran_ref_date = null;
                    try {
                        Tran_deduct_date = sdf.parse(getTdsTranLoad().getTds_deduct_date());
                        Tran_ref_date = sdf.parse(getTdsTranLoad().getTran_ref_date());
                    } catch (Exception e) {
                        l_return_message = "error";
                        e.printStackTrace();
                    }
                    DateTimeUtil dbUtil = new DateTimeUtil();
                    boolean check_tran_ref_date = dbUtil.check_date_less_then_selected_dates("dd-MM-yyyy", asmt.getQuarterToDate(), Tran_ref_date);
                    if (check_tran_ref_date) {
                        boolean check_date = dbUtil.check_date_between_two_dates("dd-MM-yyyy", asmt.getQuarterFromDate(), asmt.getQuarterToDate(), Tran_deduct_date);
                        if (check_date) {

                            objtds.setTds_code(getTdsTranLoad().getTds_code());
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + getTdsTranLoad().getTds_code() + "'";
                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                objtds.setTds_section(resultValue);
                            } catch (Exception e) {
                                l_return_message = "error";
                                e.printStackTrace();
                            }
                            String l_tran_ref_date = "";
                            String l_tds_deduct_date = "";
                            try {
                                Date tranRefDate = sdf.parse(getTdsTranLoad().getTran_ref_date());
                                Date tdsDeductDate = sdf.parse(getTdsTranLoad().getTds_deduct_date());

                                l_tran_ref_date = (sdf1.format(tranRefDate)).toUpperCase();
                                l_tds_deduct_date = (sdf1.format(tdsDeductDate)).toUpperCase();
                            } catch (Exception e) {
                            }
//                            utl.generateLog("l_tran_ref_date.." + l_tran_ref_date + "l_tds_deduct_date.." + l_tds_deduct_date);

                            objtds.setMonth(getTdsTranLoad().getMonth());
                            objtds.setDeductee_code(getTdsTranLoad().getDeductee_code());
                            objtds.setDeductee_name(getTdsTranLoad().getDeductee_name().toUpperCase());
                            objtds.setTds_deduct_date(l_tds_deduct_date);
                            objtds.setTds_deduct_reason(getTdsTranLoad().getTds_deduct_reason());
                            objtds.setDeductee_panno((getTdsTranLoad().getDeductee_panno()).toUpperCase());
                            objtds.setSurcharge_rate(getTdsTranLoad().getSurcharge_rate());
                            objtds.setSurcharge_amt(getTdsTranLoad().getSurcharge_amt());
                            objtds.setTran_ref_no(getTdsTranLoad().getTran_ref_no());
                            objtds.setTran_ref_date(l_tran_ref_date);
                            objtds.setTds_amt(getTdsTranLoad().getTds_amt());
                            objtds.setTds_rate(getTdsTranLoad().getTds_rate());
                            objtds.setTran_amt(getTdsTranLoad().getTran_amt());
                            objtds.setPartybillamt(getTdsTranLoad().getPartybillamt());
                            objtds.setCess_rate(getTdsTranLoad().getCess_rate());
                            objtds.setCess_amt(getTdsTranLoad().getCess_amt());
                            objtds.setCertificate_no(getTdsTranLoad().getCertificate_no());
                            objtds.setTds_base_amt(getTdsTranLoad().getTds_base_amt());
                            objtds.setDeductee_ref_code1(getTdsTranLoad().getDeductee_ref_code1().toUpperCase());
                             if(!utl.isnull(getTdsTranLoad().getAccount_no())){
                            objtds.setAccount_no(getTdsTranLoad().getAccount_no());
                              }
                            objtds.setTds_rate(getTdsTranLoad().getTds_rate());
                            if (!utl.isnull(asmt.getTdsTypeCode()) && asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
                                objtds.setNature_of_remittance(getTdsTranLoad().getNature_of_remittance());
                                objtds.setRate_type(getTdsTranLoad().getRate_type());
                                objtds.setCountry_code(getTdsTranLoad().getCountry_code());
                                objtds.setTin_uin_no(getTdsTranLoad().getTin_uin_no());
                            }

                            objtds.setLastupdate(sdf.format(new Date()));

                            if (!utl.isnull(getTdsTranLoad().getTds_deduct_reason())) {

                                if (getTdsTranLoad().getTds_deduct_reason().equalsIgnoreCase("B") || getTdsTranLoad().getTds_deduct_reason().equalsIgnoreCase("D")) {

                                    if (!utl.isnull(getL_reference_no())) {
                                        objtds.setCertificate_no(getL_reference_no());
                                    }
//                                    
                                }
                            }

                            tdstran = factory.getTdsTranLoadDAO();
                            boolean update_result = false;
                            try {
                                update_result = tdstran.update(objtds);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (update_result) {
                                try {
                                    panmastMastInsert.execute_procedure(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), Integer.parseInt(asmt.getQuarterNo()), asmt.getTdsTypeCode(), getTdsTranLoad().getDeductee_panno(), getTdsTranLoad().getDeductee_name());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (getFlag().equalsIgnoreCase("updateAlloc")) {
                                    session.put("ERRORCLASS", MessageType.successMessage);
                                    session.put("session_result", "Record Updated Successfully");
                                    session.put("manage_TDS_session_pageNo", getPageNumber());

                                    l_return_message = "successAlloc";
                                } else {
                                    session.put("ERRORCLASS", MessageType.successMessage);
                                    session.put("session_result", "Record Updated Successfully");
                                    l_return_message = "success";
                                }
                            } else {
                                l_return_message = "Could not update, some error occured";
                            }
                        } else {
                            l_return_message = "refDateError";
                        }
                    } else {
                        l_return_message = "tranrRefDateError";
                    }
                } else {
                    l_return_message = "Could not update, some error occured";
                }

            } else if (getAction().equalsIgnoreCase("delete")) {
           
                TdsTranLoadDAO tdstran = factory.getTdsTranLoadDAO();
                TdsTranLoad objtds = tdstran.readTDSBySequenceID(getRowid_seq());
                if (objtds != null) {
                    objtds.setRowid_seq(getRowid_seq());
                }
                tdstran = factory.getTdsTranLoadDAO();
                boolean delete_result = tdstran.delete(objtds);
                if (delete_result) {

                    try {
                        ProcTdsChallanRebuild callProc = new ProcTdsChallanRebuild();
                        String a_process_error = callProc.executeProcedure(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), String.valueOf(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), getRowid_seq().intValue(), "D");
                        if (!utl.isnull(a_process_error)) {
                            if (a_process_error.equalsIgnoreCase("0")) {
                            } else {
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    session.put("ERRORCLASS", MessageType.successMessage);
                    session.put("session_result", "Record Deleted Successfully");
                } else {
                    session.put("ERRORCLASS", MessageType.errorMessage);
                    session.put("session_result", "Could not delete, some error occured");
                }
                l_return_value = "deleteSuccess";
            }else if (getAction().equalsIgnoreCase("getTDSRate")) {
                if (!utl.isnull(getL_deductDate()) && !utl.isnull(getL_tdsCode())) {
                    try {

                        String l_tds_type_code = asmt.getTdsTypeCode();
                        DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                        Date date = sdf.parse(getL_deductDate());
                        String dedeuctDateStr = sdf1.format(date);
//                                                                                                                    
                        String rate_type = (!utl.isnull(getL_rate_type()) ? getL_rate_type() : "");
                        String nature_of_remittance = (!utl.isnull(getL_nature_of_remittance()) ? getL_nature_of_remittance() : "");
                        String country_code = (!utl.isnull(getL_country_code()) ? getL_country_code() : "");
                        setL_deductee_panno(getL_deductee_panno().toUpperCase());
                        String deductee_panno_valid = ef.execute_oracle_function_as_string("select lhs_utility.is_valid_panno('" + getL_deductee_panno() + "') valid from dual \n");
                        String deductee_panno_verified = ef.execute_oracle_function_as_string("select lhs_utility.is_panno_verified('" + getL_deductee_panno() + "') verfied from dual \n");
                        //changes as per required to CUM_TDS_RATE---
//                        String functionStringTDS = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','TDS_RATE') from dual";
                        String functionStringTDSPL = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','CUM_SPL_TDS_RATE') from dual";
                        String functionStringTDS = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','CUM_TDS_RATE') from dual";
                        String functionStringCESS = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','CESS_RATE') from dual";
                        String functionStringSUECHRG = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','SURCHARGE_RATE') from dual";
                        String functionCumulativeRate = "Select lhs_tds.get_tds_rate('" + l_tds_type_code + "','" + getL_tdsCode() + "',nvl('" + dedeuctDateStr + "','" + getL_tran_Ref_date() + "'),'" + getL_tran_amt() + "','" + getL_deducteeCode() + "', '" + getL_deductee_panno() + "','" + deductee_panno_valid + "','" + deductee_panno_verified + "', '" + rate_type + "', '" + nature_of_remittance + "', '" + country_code + "','CUM_TDS_RATE') from dual";
//                        utl.generateLog("functionStringTDSPL.........." + functionStringTDSPL);
//                        
//System.out.println("functionStringTDSPL-->"+functionStringTDSPL);
                        String resultValueTDS = ef.execute_oracle_function_as_string(functionStringTDSPL);
                        if (utl.isnull(resultValueTDS)) {
                            //System.out.println("functionStringTDS-->"+functionStringTDS);
                            resultValueTDS = ef.execute_oracle_function_as_string(functionStringTDS);
                            resultValueTDS = utl.isnull(resultValueTDS) ? "" : resultValueTDS;

                        }

                        String resultValueCESS = ef.execute_oracle_function_as_string(functionStringCESS);
                        resultValueCESS = utl.isnull(resultValueCESS) ? "" : resultValueCESS;
                        String resultValueSURCHRG = ef.execute_oracle_function_as_string(functionStringSUECHRG);
                        resultValueSURCHRG = utl.isnull(resultValueSURCHRG) ? "" : resultValueSURCHRG;
                        String resultCumulativeRate = ef.execute_oracle_function_as_string(functionCumulativeRate);
                        resultCumulativeRate = utl.isnull(resultCumulativeRate) ? "" : resultCumulativeRate;
                        l_return_message = resultValueTDS + "#" + resultValueCESS + "#" + resultValueSURCHRG + "#" + resultCumulativeRate;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    l_return_value = "success";
                }
            } else if (getAction().equalsIgnoreCase("verifyPAN")) {
                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                if (!utl.isnull(getL_panno())) {
                    String deductee_panno_verified = ef.execute_oracle_function_as_string("select lhs_utility.is_panno_verified('" + getL_panno() + "') verfied from dual \n");
//                    String deductee_panno_verified_name = ef.execute_oracle_function_as_string("select first_name from pan_mast t where t.panno='" + getL_panno() + "'");
                    String deductee_panno_verified_name = ef.execute_oracle_function_as_string("select lhs_utility.get_verified_panname('" + getL_panno() + "') panname from dual");
                    //utl.generateLog("deductee_panno_verified====" + deductee_panno_verified);
                    if (!utl.isnull(deductee_panno_verified)) {
                        String panno_verified_value = deductee_panno_verified + "#" + deductee_panno_verified_name;
                        System.out.println("-------->" + panno_verified_value);
                        l_return_message = panno_verified_value;

                    } else {
                        l_return_message = null;

                    }
                    l_return_value = "success";
                }
            } else if (getAction().equalsIgnoreCase("getCertificateNo")) {
                if (!utl.isnull(getL_deductee_panno())) {
                    try {
                        TdsSplRateMastDAO tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                        List<TdsSplRateMast> tdsMast = tdsSplRateMastDAO.readTdsSplRateForDeducteeList(client, asmt, getL_tdsCode(),
                                getL_deductee_panno());

                        if (tdsMast != null) {

                            String certi_no = tdsMast.get(0).getCertificate_no() + "#" + tdsMast.get(0).getTds_rate();
                            if (!utl.isnull(certi_no)) {
                                l_return_message = certi_no;

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    l_return_value = "success";
                }
            } else if (getAction().equalsIgnoreCase("getCertificateNoPopup")) {
                if (getTdsSplRateMast() == null) {
                    l_return_message = "error";
                } else if (utl.isnull(getTdsSplRateMast().getDeductee_panno()) && utl.isnull(getTdsSplRateMast().getTds_code())
                        && utl.isnull(getTdsSplRateMast().getCertificate_no())) {

                    l_return_message = "empty";
                } else {
                    try {
                        TdsSplRateMastDAO tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                        TdsSplRateMast tdsMast = tdsSplRateMastDAO.getCertificateDetails(client, asmt, getTdsSplRateMast());

                        if (tdsMast != null) {
                            utl.generateLog("Certificate details: ", "");
                            utl.generateLog("Certificate_no: ", tdsMast.getCertificate_no());
                            utl.generateLog("From_date: ", tdsMast.getFrom_date());
                            utl.generateLog("Certificate_valid_upto: ", tdsMast.getCertificate_valid_upto());
                            utl.generateLog("Tds_limit_amt: ", tdsMast.getTds_limit_amt());
                            utl.generateLog("Tds_rate: ", tdsMast.getTds_rate());

                            String splRateMastToJson = new Gson().toJson(tdsMast);
                            l_return_message = splRateMastToJson;
                        } else {
                            l_return_message = "empty";
                        }
                    } catch (Exception e) {
                        l_return_message = "error";
                        e.printStackTrace();
                    }
                }
                l_return_value = "success";
            } else {
                l_return_value = "input";
            }
        } else {
            l_return_value = "input";
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));

        return l_return_value;
    }//end method

    public TransactionCRUDAction() {
        utl = new Util();
        selectTdsType = new LinkedHashMap<String, String>();
        selectSection = new LinkedHashMap<String, String>();
        selectMonth = new LinkedHashMap<String, String>();
        selectDeductReason = new LinkedHashMap<String, String>();
        selectRateType = new LinkedHashMap<String, String>();
        selectRateType.put("I", "Income Tax Rate");
        selectRateType.put("D", "DTAA Rate");
        select_country = new LinkedHashMap<String, String>();
        selectRemittanceNature = new LinkedHashMap<String, String>();

    }//end constructor
    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private TdsTranLoad tdsTranLoad;
    private DeducteeBflagRefinfoTran objBflagRefinfo;
    private Long rowid_seq;
    private String l_panno;
    private String l_deducteeCode;
    private String l_deductDate;
    private String l_tran_Ref_date;
    private String l_tran_amt;
    private String l_tdsCode;
    private String l_deductee_name;
    private String l_deductee_panno;
    private String l_dedeuctee_type;
    private String l_dedeuctee_email;
    private String l_dedeuctee_phoneno;
    private String l_dedeuctee_address;
    private String l_dedeuctee_tin;
    private String sessionResult;
    private TransactionFilterEntity tranFilter;

    private String l_rate_type;
    private String l_nature_of_remittance;
    private String l_country_code;

    private String tds_type_code;
    private String saveAndContinue;
    private LinkedHashMap<String, String> selectTdsType;
    private LinkedHashMap<String, String> selectSection;
    private LinkedHashMap<String, String> selectMonth;
    private LinkedHashMap<String, String> selectDeductReason;
    private LinkedHashMap<String, String> selectRateType;
    private HashMap<String, String> select_country;
    private LinkedHashMap selectRemittanceNature;

    private String mapFlag;
    private String mapTDSChallanAmt;
    private String mapChallanRowSeq;
    private double mapAllocatedAmount;
    private String flag;
    private String pageNumber;
    private String forBflag;
    private String l_reference_no;
    private String seqidformuldel;
    private String[] seqArray11 = null;

    private TdsSplRateMast tdsSplRateMast;
    private String whereClause;
    public TdsSplRateMast getTdsSplRateMast() {
        return tdsSplRateMast;
    }

    public void setTdsSplRateMast(TdsSplRateMast tdsSplRateMast) {
        this.tdsSplRateMast = tdsSplRateMast;
    }

    private String getCheckLhssysFileSeqNoAvailibility(String client_code, String entity_code, String acc_year, String quarterNumber, String tds_type_code, Date quarterFromDate, Date quarterToDate) {
        String reslut_value = null;
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        Double l_period_no = 0d;
        if (!utl.isnull(quarterNumber)) {
            try {
                l_period_no = Double.parseDouble(quarterNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            LhssysFileTranDAO filetrandao = factory.getLhssysFileTranDAO();
            LhssysFileTran tdsobj = filetrandao.getFileSeqnoByParameter(client_code, entity_code, acc_year, l_period_no, tds_type_code, "TD");
            if (tdsobj != null) {
                reslut_value = tdsobj.getFile_seqno();
            } else {
                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }
                LhssysFileTran lhsfiletranobj = new LhssysFileTran();
                if (!utl.isnull(Long.toString(l_client_loginid_seq))) {
                    lhsfiletranobj.setClient_login_session_seqno(l_client_loginid_seq);
                } else {
                    lhsfiletranobj.setClient_login_session_seqno(0l);//no file tracking
                }
                lhsfiletranobj.setClient_code(client_code);
                lhsfiletranobj.setAcc_year(acc_year);

                lhsfiletranobj.setQuarter_no(l_period_no);
                lhsfiletranobj.setMonth(getTdsTranLoad().getMonth());
                lhsfiletranobj.setFrom_date(quarterFromDate);
                lhsfiletranobj.setTo_date(quarterToDate);
                lhsfiletranobj.setTds_type_code(tds_type_code);//from dropdown
                lhsfiletranobj.setUpload_purpose("N");
                lhsfiletranobj.setUpload_method(1L);//1 - LHS Format / 2 - Customer Format / 3 - Institution File (Bulk)
                lhsfiletranobj.setUpload_start_timestamp(new Date());
                lhsfiletranobj.setUpload_end_timestamp(new Date());
                lhsfiletranobj.setFile_name("TD-" + tds_type_code + "-" + acc_year + "-Q" + l_period_no);
                lhsfiletranobj.setLoad_status("S");//E - File Error / S - Started / C - Completed / V - Validated
                lhsfiletranobj.setLoad_start_timestamp(new Date());
                lhsfiletranobj.setLoad_end_timestamp(new Date());
                lhsfiletranobj.setImport_flag("TD");//TD- TDS,CH- CHALLAN,DR- DEDUCTOR,DE-DEDUCTEE
                LhssysFileTranDAO lhssysfiletrandao1 = factory.getLhssysFileTranDAO();
                String save = lhssysfiletrandao1.saveAndReturnFileSeqno(lhsfiletranobj);
                if (save != null) {
                    reslut_value = save;
                } else {
                    reslut_value = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslut_value;
    }//end method

    public String getL_dedeuctee_tin() {
        return l_dedeuctee_tin;
    }

    public void setL_dedeuctee_tin(String l_dedeuctee_tin) {
        this.l_dedeuctee_tin = l_dedeuctee_tin;
    }

    public String getL_dedeuctee_email() {
        return l_dedeuctee_email;
    }

    public void setL_dedeuctee_email(String l_dedeuctee_email) {
        this.l_dedeuctee_email = l_dedeuctee_email;
    }

    public String getL_dedeuctee_phoneno() {
        return l_dedeuctee_phoneno;
    }

    public void setL_dedeuctee_phoneno(String l_dedeuctee_phoneno) {
        this.l_dedeuctee_phoneno = l_dedeuctee_phoneno;
    }

    public String getL_dedeuctee_address() {
        return l_dedeuctee_address;
    }

    public void setL_dedeuctee_address(String l_dedeuctee_address) {
        this.l_dedeuctee_address = l_dedeuctee_address;
    }

    public String getL_dedeuctee_type() {
        return l_dedeuctee_type;
    }

    public void setL_dedeuctee_type(String l_dedeuctee_type) {
        this.l_dedeuctee_type = l_dedeuctee_type;
    }

    public String getSaveAndContinue() {
        return saveAndContinue;
    }

    public void setSaveAndContinue(String saveAndContinue) {
        this.saveAndContinue = saveAndContinue;
    }

    public String getL_deductee_name() {
        return l_deductee_name;
    }

    public void setL_deductee_name(String l_deductee_name) {
        this.l_deductee_name = l_deductee_name;
    }

    public String getL_deducteeCode() {
        return l_deducteeCode;
    }

    public void setL_deducteeCode(String l_deducteeCode) {
        this.l_deducteeCode = l_deducteeCode;
    }

    public String getL_deductDate() {
        return l_deductDate;
    }

    public void setL_deductDate(String l_deductDate) {
        this.l_deductDate = l_deductDate;
    }

    public String getL_tdsCode() {
        return l_tdsCode;
    }

    public void setL_tdsCode(String l_tdsCode) {
        this.l_tdsCode = l_tdsCode;
    }

    public String getL_panno() {
        return l_panno;
    }

    public void setL_panno(String l_panno) {
        this.l_panno = l_panno;
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

    public TdsTranLoad getTdsTranLoad() {
        return tdsTranLoad;
    }

    public void setTdsTranLoad(TdsTranLoad tdsTranLoad) {
        this.tdsTranLoad = tdsTranLoad;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public LinkedHashMap<String, String> getSelectTdsType() {
        return selectTdsType;
    }

    public void setSelectTdsType(LinkedHashMap<String, String> selectTdsType) {
        this.selectTdsType = selectTdsType;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public LinkedHashMap<String, String> getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(LinkedHashMap<String, String> selectMonth) {
        this.selectMonth = selectMonth;
    }

    public LinkedHashMap<String, String> getSelectDeductReason() {
        return selectDeductReason;
    }

    public void setSelectDeductReason(LinkedHashMap<String, String> selectDeductReason) {
        this.selectDeductReason = selectDeductReason;
    }

    public LinkedHashMap<String, String> getSelectRateType() {
        return selectRateType;
    }

    public void setSelectRateType(LinkedHashMap<String, String> selectRateType) {
        this.selectRateType = selectRateType;
    }

    public HashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(HashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public LinkedHashMap getSelectRemittanceNature() {
        return selectRemittanceNature;
    }

    public void setSelectRemittanceNature(LinkedHashMap selectRemittanceNature) {
        this.selectRemittanceNature = selectRemittanceNature;
    }

    public String getMapFlag() {
        return mapFlag;
    }

    public void setMapFlag(String mapFlag) {
        this.mapFlag = mapFlag;
    }

    public String getMapTDSChallanAmt() {
        return mapTDSChallanAmt;
    }

    public void setMapTDSChallanAmt(String mapTDSChallanAmt) {
        this.mapTDSChallanAmt = mapTDSChallanAmt;
    }

    public String getMapChallanRowSeq() {
        return mapChallanRowSeq;
    }

    public void setMapChallanRowSeq(String mapChallanRowSeq) {
        this.mapChallanRowSeq = mapChallanRowSeq;
    }

    public double getMapAllocatedAmount() {
        return mapAllocatedAmount;
    }

    public void setMapAllocatedAmount(double mapAllocatedAmount) {
        this.mapAllocatedAmount = mapAllocatedAmount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public DeducteeBflagRefinfoTran getObjBflagRefinfo() {
        return objBflagRefinfo;
    }

    public void setObjBflagRefinfo(DeducteeBflagRefinfoTran objBflagRefinfo) {
        this.objBflagRefinfo = objBflagRefinfo;
    }

    public String getForBflag() {
        return forBflag;
    }

    public void setForBflag(String forBflag) {
        this.forBflag = forBflag;
    }

    public String getL_reference_no() {
        return l_reference_no;
    }

    public void setL_reference_no(String l_reference_no) {
        this.l_reference_no = l_reference_no;
    }

    public String getL_tran_Ref_date() {
        return l_tran_Ref_date;
    }

    public void setL_tran_Ref_date(String l_tran_Ref_date) {
        this.l_tran_Ref_date = l_tran_Ref_date;
    }

    public String getL_tran_amt() {
        return l_tran_amt;
    }

    public void setL_tran_amt(String l_tran_amt) {
        this.l_tran_amt = l_tran_amt;
    }

    public String getL_deductee_panno() {
        return l_deductee_panno;
    }

    public void setL_deductee_panno(String l_deductee_panno) {
        this.l_deductee_panno = l_deductee_panno;
    }

    public String getL_rate_type() {
        return l_rate_type;
    }

    public void setL_rate_type(String l_rate_type) {
        this.l_rate_type = l_rate_type;
    }

    public String getL_nature_of_remittance() {
        return l_nature_of_remittance;
    }

    public void setL_nature_of_remittance(String l_nature_of_remittance) {
        this.l_nature_of_remittance = l_nature_of_remittance;
    }

    public String getL_country_code() {
        return l_country_code;
    }

    public void setL_country_code(String l_country_code) {
        this.l_country_code = l_country_code;

    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public String getSeqidformuldel() {
        return seqidformuldel;
    }

    public void setSeqidformuldel(String seqidformuldel) {
        this.seqidformuldel = seqidformuldel;
    }

    public TransactionFilterEntity getTranFilter() {
        return tranFilter;
    }

    public void setTranFilter(TransactionFilterEntity tranFilter) {
        this.tranFilter = tranFilter;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    
    
   

   
  
    
    

    private boolean checkTdsValidOrNot(String tds_code, String tdsTypeCode, Date Tran_ref_date) {
        String validationStr = "select lhs_tds.get_tds_code("
                + "'" + tds_code + "', "
                + "'" + tdsTypeCode + "', "
                + "TO_DATE('" + new java.sql.Date(Tran_ref_date.getTime()) + "','RRRR-MM-DD')) tds_code_valid from dual";

        utl.generateLog("validationStr", validationStr);

        try {
            String resultValue = ef.execute_oracle_function_as_string(validationStr);
            if (!utl.isnull(resultValue)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
}// End Class
