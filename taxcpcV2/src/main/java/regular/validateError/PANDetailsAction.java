/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsTranPannoUpdt;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class PANDetailsAction extends ActionSupport implements SessionAware {

    /* forward name="success" path="" */
    Map<String, Object> session;
    Util utl;
    private InputStream inputStream;
    private int TotalDeducteePAN;
    private int VerifiedDeducteePAN;
    private int InvalidDeducteePAN;
    private int PANNOTAVBLCount;
    private int UnverifiedPAN;

    private String action;
    private String pan_updt_rowid_str;

    public PANDetailsAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_str = "";
        try {
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            DbFunctionExecutorAsString qur = new DbFunctionExecutorAsString();
            String l_pan_unverified = "";
            String l_challan_not_verified = "";
            String l_lower_deduction_not_verified = "";
            String l_deductee_not_allocated = "";
            String l_refno_not_allocated = "";

            if (clientMast != null) {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                UserMast user_mast = (UserMast) session.get("LOGINUSER");
                if (asmt != null) {
                    try {
                        String client_code = clientMast.getClient_code();
                        String entity_code = clientMast.getEntity_code();
                        String acc_year = asmt.getAccYear();
                        String tds_type_code = asmt.getTdsTypeCode();
                        String quarterNo = asmt.getQuarterNo();
                        Date fromDate = asmt.getQuarterFromDate();
                        Date toDate = asmt.getQuarterToDate();

                        if (utl.isnull(getAction())) {
                            ProcessErrorsDB process_db = new ProcessErrorsDB();

                            String l_pan_unverified_query = process_db.getUnverifiedPANCountQuery(entity_code, client_code);
                            utl.generateSpecialLog("Pan_unverified_query_count_query------", l_pan_unverified_query);
                            l_pan_unverified = qur.execute_oracle_function_as_string(l_pan_unverified_query);

                            String l_challan_not_verified_Query = process_db.getUnverifiedChallanCountQuery(entity_code, client_code, acc_year, tds_type_code, quarterNo);
                            utl.generateSpecialLog("Challan_not_verified_count_query-----", l_challan_not_verified_Query);
                            l_challan_not_verified = qur.execute_oracle_function_as_string(l_challan_not_verified_Query);

                            String l_lower_deduction_not_verified_Query = process_db.getUnverifiedLowerDeductionCountQuery(client_code);
                            utl.generateSpecialLog("Lower_deduction_not_verified_count_query-----", l_lower_deduction_not_verified_Query);
                            l_lower_deduction_not_verified = qur.execute_oracle_function_as_string(l_lower_deduction_not_verified_Query);

                            String l_deductee_not_allocated_Query = process_db.getUnallocatedDeducteeCountQuery(entity_code, client_code, acc_year, quarterNo, tds_type_code);
                            l_deductee_not_allocated = qur.execute_oracle_function_as_string(l_deductee_not_allocated_Query);
                            utl.generateSpecialLog("Deductee_not_allocated_count_query----", l_deductee_not_allocated_Query);

                            String l_refno_not_allocated_Query = process_db.getUnallocatedRefNoCountQuery(entity_code, client_code, acc_year, quarterNo, tds_type_code);
                            //  l_refno_not_allocated = qur.execute_oracle_function_as_string(l_refno_not_allocated_Query);
//                         utl.generateLog("Refno_not_allocated_count_query----", l_refno_not_allocated_Query);

                            l_pan_unverified = utl.isnull(l_pan_unverified) ? "0" : l_pan_unverified;
                            l_challan_not_verified = utl.isnull(l_challan_not_verified) ? "0" : l_challan_not_verified;
                            l_lower_deduction_not_verified = utl.isnull(l_lower_deduction_not_verified) ? "0" : l_lower_deduction_not_verified;
                            l_deductee_not_allocated = utl.isnull(l_deductee_not_allocated) ? "0" : l_deductee_not_allocated;
                            l_refno_not_allocated = utl.isnull(l_refno_not_allocated) ? "0" : l_refno_not_allocated;
                            l_return_str = l_pan_unverified + "#" + l_challan_not_verified + "#" + l_lower_deduction_not_verified + "#" + l_deductee_not_allocated + "#" + l_refno_not_allocated;

                        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("panUpdation")) {
                            Long l_client_loginid_seq;
                            Object sessionId = session.get("LOGINSESSIONID");
                            try {
                                l_client_loginid_seq = (Long) sessionId;
                            } catch (Exception e) {
                                l_client_loginid_seq = 0l;
                            }

                            try {
                                ProcTdsTranPannoUpdt proc_panupdt = new ProcTdsTranPannoUpdt();

                                long count = proc_panupdt.executeProcedure(entity_code, client_code, acc_year, utl.ChangeAccYearToAssessmentAccYear(acc_year),
                                        Integer.valueOf(quarterNo),
                                        fromDate, toDate, tds_type_code, l_client_loginid_seq,
                                        getPan_updt_rowid_str(), // PAN Updation whereclause string
                                        user_mast.getUser_code(), null);

                                l_return_str = count > 0 ? String.valueOf(count) : "0";
                            } catch (Exception e) {
                                l_return_str = "0";
                            }

                            utl.generateLog("PAN Updation count", l_return_str);

                            l_return_str = "success#" + l_return_str;
                            l_return_value = "success";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_str.getBytes("UTF-8"));
        return l_return_value;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session; //To change body of generated methods, choose Tools | Templates.
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int getTotalDeducteePAN() {
        return TotalDeducteePAN;
    }

    public void setTotalDeducteePAN(int TotalDeducteePAN) {
        this.TotalDeducteePAN = TotalDeducteePAN;
    }

    public int getVerifiedDeducteePAN() {
        return VerifiedDeducteePAN;
    }

    public void setVerifiedDeducteePAN(int VerifiedDeducteePAN) {
        this.VerifiedDeducteePAN = VerifiedDeducteePAN;
    }

    public int getInvalidDeducteePAN() {
        return InvalidDeducteePAN;
    }

    public void setInvalidDeducteePAN(int InvalidDeducteePAN) {
        this.InvalidDeducteePAN = InvalidDeducteePAN;
    }

    public int getPANNOTAVBLCount() {
        return PANNOTAVBLCount;
    }

    public void setPANNOTAVBLCount(int PANNOTAVBLCount) {
        this.PANNOTAVBLCount = PANNOTAVBLCount;
    }

    public int getUnverifiedPAN() {
        return UnverifiedPAN;
    }

    public void setUnverifiedPAN(int UnverifiedPAN) {
        this.UnverifiedPAN = UnverifiedPAN;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPan_updt_rowid_str() {
        return pan_updt_rowid_str;
    }

    public void setPan_updt_rowid_str(String pan_updt_rowid_str) {
        this.pan_updt_rowid_str = pan_updt_rowid_str;
    }

}
