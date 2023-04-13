/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcInsertSalaryAmtTran;
import dao.DbProc.ProcPanMastInsert;
import dao.DbProc.ProcRefreshSalaryTdsAmt;
import dao.DbProc.ProcSalaryTranTotalRefresh;
import dao.DbProc.ProcStdDedUpdt;
import dao.SalaryTranDAO;
import dao.ViewEmpCatgDAO;
import dao.ViewSalaryDataValidationDAO;
import dao.ViewSalaryEmployeeDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.SalaryTran;
import hibernateObjects.SalaryTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewSalaryDataValidation;
import hibernateObjects.ViewSalaryEmployee;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class SalaryTranLoadSupport extends ActionSupport implements SessionAware {

    public String execute() throws Exception {
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
//        String rowId = request.getParameter("rowid_seq");
//        String deductee_code = request.getParameter("deductee_code");
//        utl.generateLog("rowId..............." + rowId);
        String returnView = "success";
        String returnString = "0";
        String l_return_value;
        try {
            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user = (UserMast) session.get("LOGINUSER");

            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("FUNCTION")) {
                    if (!utl.isnull(getFunctionWithParam())) {
                        DbFunctionExecutorAsString functionExecutorAsString = new DbFunctionExecutorAsString();
                        String execute_oracle_function_as_string = functionExecutorAsString.execute_oracle_function_as_string(getFunctionWithParam());
                        execute_oracle_function_as_string = utl.isnull(execute_oracle_function_as_string) ? "0" : execute_oracle_function_as_string;
                        Double doubleVal = Double.parseDouble(execute_oracle_function_as_string);
                        long round = Math.round(doubleVal);
                        returnString = String.valueOf(round);
                    }
                } else if (getAction().equalsIgnoreCase("deleteSalary")) {

                    if (!utl.isnull(deductee_code)) {

                        if (clientMast != null) {
                            if (asmt != null) {

                                String deleteQuery
                                        = "delete from salary_tran t where\n"
                                        + "t.entity_code='" + clientMast.getEntity_code() + "'\n"
                                        + "and   exists (select 1\n"
                                        + "        from   client_mast b1\n"
                                        + "        where  b1.entity_code=t.entity_code\n"
                                        + "        and    b1.client_code=t.client_code\n"
                                        + "        and    (b1.client_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                                        + "               )\n"
                                        + "       )"
                                        + "and t.acc_year='" + asmt.getAccYear() + "'\n"
                                        + "and t.tds_type_code='" + asmt.getTdsTypeCode() + "'\n"
                                        + "and t.quarter_no='" + Integer.parseInt(asmt.getQuarterNo()) + "'\n"
                                        + "and t.deductee_code='" + getDeductee_code() + "'\n"
                                        + "and t.rowid_seq='" + getRowidSeq() + "'";
                                boolean deleteSalary = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(deleteQuery);
//                                ArrayList<SalaryAmtTran> readSalaryAmtTranByDeducteeCode = salaryAmtTranDAO.readSalaryAmtTranByDeducteeCode(fromDate, toDate, "S", getDeductee_code(), getMonth(), clientMast, utl);
//                                boolean deleteSalaryAmtTran = new SalaryTranLoadSupport().deleteSalaryAmtTran(readSalaryAmtTranByDeducteeCode);
                                if (deleteSalary) {
                                    try {

                                        // call procedure to insert into salary amt tran
                                        String execureProcedure = new ProcInsertSalaryAmtTran().execureProcedure(
                                                getEntity_code(),
                                                getClient_code(),
                                                asmt.getAccYear(),
                                                asmt.getTdsTypeCode(),
                                                Long.parseLong(asmt.getQuarterNo()),
                                                getSalaryTran().getDeductee_code(),
                                                getRowidSeq(),
                                                "D");
                                    } catch (Exception e) {
                                    }
                                    returnString = "true";
                                    session.put("SALRYTRANRSLTMSG", "Records Deleted Successfully");
                                    session.put("ERRORCLASS", MessageType.successMessage);
                                } else {
                                    returnString = "false";
                                }
                            } else {
                                returnString = "false";
                            }
                        } else {
                            returnString = "false";
                        }
                    }
                } else if (getAction().equalsIgnoreCase("deleteSalaryFromBrowse")) {
                    if (null != getRowidSeq()) {

                        if (clientMast != null) {
                            if (asmt != null) {

                                String deleteQuery
                                        = "delete from salary_tran_load t where\n"
                                        + "t.entity_code='" + clientMast.getEntity_code() + "'\n"
                                        + "and   exists (select 1\n"
                                        + "        from   client_mast b1\n"
                                        + "        where  b1.entity_code=t.entity_code\n"
                                        + "        and    b1.client_code=t.client_code\n"
                                        + "        and    (b1.client_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                        + "                b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                                        + "               )\n"
                                        + "       )"
                                        + "and t.acc_year='" + asmt.getAccYear() + "'\n"
                                        + "and t.tds_type_code='" + asmt.getTdsTypeCode() + "'\n"
                                        + "and t.quarter_no='" + Integer.parseInt(asmt.getQuarterNo()) + "'\n"
                                        //                                        + "and t.deductee_code='" + getDeductee_code() + "'\n"
                                        + "and t.rowid_seq='" + getRowidSeq() + "'";

                                utl.generateSpecialLog("RSA-0005 (Delete Salary From Browse Page Query)----", deleteQuery);

                                boolean deleteSalary = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(deleteQuery);
                                if (deleteSalary) {
                                    returnString = "true";
                                } else {
                                    returnString = "false";
                                }
                            } else {
                                returnString = "false";
                            }
                        } else {
                            returnString = "false";
                        }
                    }
                } else if (getAction().equalsIgnoreCase("saveNewDeductee")) {
                    if (getSalaryTran() != null) {

                        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");

                        Long rowid_seq = 0L;
                        int l_quarter_no = Integer.parseInt(asmt.getQuarterNo());
                        if (viewClientMast != null) {

                            String rowid_seqQuery = "select TDS_TRAN_LOAD_ROWID_SEQ.Nextval from dual";
                            DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                            rowid_seq = objDBFunction.execute_oracle_function_as_integar(rowid_seqQuery);
                            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                            SalaryTranDAO salaryDAO = factory.getSalaryTranDAO();
                            SalaryTran st = new SalaryTran();
                            utl.generateLog("getSalaryTran().getDeductee_panno()---", getSalaryTran().getDeductee_panno());
                            utl.generateLog("getSalaryTran().getIdentification_no()---", getSalaryTran().getIdentification_no());
                            String tds_type_code = asmt.getTdsTypeCode();
                            utl.generateLog("fromdate---", getFrom_date());
                            Date fromDate = null;
                            Date toDate = null;
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            if (!utl.isnull(getFrom_date())) {
                                fromDate = sdf.parse(getFrom_date());
                            }
                            if (!utl.isnull(getTo_date())) {
                                toDate = sdf.parse(getTo_date());
                            }

                            st.setDeductee_code(rowid_seq.toString());
                            st.setRowid_seq(rowid_seq.toString());
                            st.setDeductee_name(getSalaryTran().getDeductee_name().toUpperCase());
                            st.setDeductee_panno(getSalaryTran().getDeductee_panno().toUpperCase());
                            st.setEntity_code(viewClientMast.getEntity_code());
                            st.setClient_code(viewClientMast.getClient_code());
                            st.setFrom_date(fromDate);
                            st.setTo_date(toDate);
                            st.setAcc_year(asmt.getAccYear());
                            st.setTds_type_code(tds_type_code);
                            st.setQuarter_no(asmt.getQuarterNo());
                            st.setIdentification_no(getSalaryTran().getIdentification_no());
                            st.setDeductee_panno_valid(getSalaryTran().getDeductee_panno_valid());
                            st.setDeductee_panno_verified(getSalaryTran().getDeductee_panno_verified());

                            Boolean insertResult = salaryDAO.save(st);
//                            DeducteeMast dm = new DeducteeMast();
//                            dm.setDeductee_name(getDeducteeMast().getDeductee_name().toUpperCase());
//                            dm.setPanno(getDeducteeMast().getPanno().toUpperCase());
//                            dm.setEntity_code(viewClientMast.getEntity_code());
//                            dm.setClient_code(viewClientMast.getClient_code());
//                            dm.setDeductee_type_code(dedcuteeTypeCodeString);
//                            dm.setDeductee_catg("E");
//                            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                            DeducteeDAO deducteeDAO = factory.getDeducteeDAO();
//                            Boolean insertResult = deducteeDAO.save(dm);

//                            Boolean insertResult = new ProcDeducteeMastInsert().execute_procedure(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), getDeducteeMast().getDeductee_name(), getDeducteeMast().getPanno().toUpperCase(), sequence_nextval, dedcuteeTypeCodeString);
                            if (insertResult) {
                                ProcPanMastInsert panmastMastInsert = new ProcPanMastInsert();
                                try {
                                    Boolean execute_procedure = panmastMastInsert.execute_procedure(viewClientMast.getEntity_code(), viewClientMast.getClient_code(), asmt.getAccYear(), l_quarter_no, asmt.getTdsTypeCode(), getSalaryTran().getDeductee_panno(), getSalaryTran().getDeductee_name());
                                } catch (Exception e) {
                                    //utl.generateLog("Exception on inserting proc mast procedure");
                                }
//                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
//                                String functionString = "select LHS_TDS.get_deductee_code('" + viewClientMast.getEntity_code() + "','" + viewClientMast.getClient_code() + "','" + getSalaryTran().getDeductee_panno().toUpperCase() + "','" + getSalaryTran().getDeductee_name().toUpperCase() + "','" + tds_type_code + "') from dual";
//
//                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                String resultValue = rowid_seq.toString();

                                if (!utl.isnull(resultValue)) {
                                    returnString = resultValue;
                                }
                            }
                        }
                    }
                } else if (getAction().equalsIgnoreCase("getDataFromDojo")) {
                    try {
                        if (!utl.isnull(getFunctionWithParam())) {
                            if (asmt != null && clientMast != null) {
                                String acc_year = asmt.getAccYear();
                                String sqlText = getFunctionWithParam();
                                if (sqlText.length() > 0) {
                                    if (getFunctionWithParam().contains("tcDEDUCTEE_CODE")) {
                                        sqlText = sqlText.replaceAll("tcDEDUCTEE_CODE", getDeductee_code());
                                    }
                                    if (sqlText.contains("tcASSESMENT_ACC_YEAR")) {
                                        sqlText = sqlText.replaceAll("tcASSESMENT_ACC_YEAR", utl.ChangeAccYearToAssessmentAccYear(acc_year));
                                    }
                                    if (sqlText.contains("tcACC_YEAR")) {
                                        sqlText = sqlText.replaceAll("tcACC_YEAR", acc_year);
                                    }
                                    if (sqlText.contains("tcCLIENT_CODE")) {
                                        sqlText = sqlText.replaceAll("tcCLIENT_CODE", clientMast.getClient_code());
                                    }
                                    if (sqlText.contains("tcENTITY_CODE")) {
                                        sqlText = sqlText.replaceAll("tcENTITY_CODE", clientMast.getEntity_code());
                                    }
                                    String execute_oracle_function_as_string = new DbFunctionExecutorAsString().execute_oracle_function_as_string(sqlText);
                                    execute_oracle_function_as_string = utl.isnull(execute_oracle_function_as_string) ? "0" : execute_oracle_function_as_string;
                                    String readonlystr = "";
                                    if (!utl.isnull(getReadOnlyField()) && getReadOnlyField().equalsIgnoreCase("true")) {
                                        readonlystr = "readonly=true";
                                    }

                                    returnString = "<input type=\"text\" id=\"" + getTextFieldId() + "\" name=\"" + getTextFieldName() + "\"  class=\"form-control\" placeholder=\"\" maxLength=\"\" " + readonlystr + " onkeypress=\"" + getOnKeyPressEvent() + "\" onblur=\"checkForDependentColumns(this.id);\" onchange=\"resetInnerFields(this.id); setValueOnChange(this.id);\" value=\"" + execute_oracle_function_as_string + "\">";
                                }
                            } else {
                                returnString = "<input type=\"text\" id=\"" + getTextFieldId() + "\" name=\"" + getTextFieldName() + "\"  class=\"form-control\" placeholder=\"\" maxLength=\"\" readonly=\"" + getReadOnlyField() + "\" onkeypress=\"" + getOnKeyPressEvent() + "\" onblur=\"checkForDependentColumns(this.id);\" onchange=\"resetInnerFields(this.id); setValueOnChange(this.id);\">";
                            }
                        }

                    } catch (Exception e) {
                        returnString = "<input type=\"text\" id=\"" + getTextFieldId() + "\" name=\"" + getTextFieldName() + "\"  class=\"form-control\" placeholder=\"\" maxLength=\"\" readonly=\"" + getReadOnlyField() + "\" onkeypress=\"" + getOnKeyPressEvent() + "\" onblur=\"checkForDependentColumns(this.id);\" onchange=\"resetInnerFields(this.id); setValueOnChange(this.id);\">";
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("callFunction")) {
                    try {
                        if (!utl.isnull(getFunctionWithParam())) {
                            if (asmt != null && clientMast != null) {
                                String acc_year = asmt.getAccYear();
                                String sqlText = getFunctionWithParam();
                                if (sqlText.length() > 0) {
                                    if (getFunctionWithParam().contains("tcDEDUCTEE_CODE")) {
                                        sqlText = sqlText.replaceAll("tcDEDUCTEE_CODE", getDeductee_code());
                                    }
                                    if (sqlText.contains("tcASSESMENT_ACC_YEAR")) {
                                        sqlText = sqlText.replaceAll("tcASSESMENT_ACC_YEAR", utl.ChangeAccYearToAssessmentAccYear(acc_year));
                                    }
                                    if (sqlText.contains("tcACC_YEAR")) {
                                        sqlText = sqlText.replaceAll("tcACC_YEAR", acc_year);
                                    }
                                    if (sqlText.contains("tcCLIENT_CODE")) {
                                        sqlText = sqlText.replaceAll("tcCLIENT_CODE", clientMast.getClient_code());
                                    }
                                    if (sqlText.contains("tcENTITY_CODE")) {
                                        sqlText = sqlText.replaceAll("tcENTITY_CODE", clientMast.getEntity_code());
                                    }
                                    String execute_oracle_function_as_string = new DbFunctionExecutorAsString().execute_oracle_function_as_string(sqlText);
                                    execute_oracle_function_as_string = utl.isnull(execute_oracle_function_as_string) ? "0" : execute_oracle_function_as_string;

                                    returnString = execute_oracle_function_as_string;
                                }
                            } else {
                                returnString = "0";
                            }
                        }

                    } catch (Exception e) {
                        returnString = "0";
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("getDeducteeCategory")) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    HashMap<String, String> genders = new HashMap<String, String>();
                    if (clientMast != null) {
                        ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
                        genders = viewEmpCatgDAO.getEmpCatgAsLinkedHashMap();
                    }
                    if (!genders.isEmpty()) {
                        Iterator it = genders.entrySet().iterator();
                        returnString = "";
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            returnString = returnString + pair.getKey() + "=" + pair.getValue() + "#";
                        }
                    }
                } else if (getAction().equalsIgnoreCase("getDeducteeCategoryByDeducteeName")) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    ViewSalaryEmployeeDAO viewSalaryEmployeeDAO = factory.getViewSalaryEmployeeDAO();
                    ViewSalaryEmployee viewSalaryEmployee = viewSalaryEmployeeDAO.readDeducteeByDeducteeName(utl, clientMast.getEntity_code(), clientMast.getClient_code(), getDeducteeName());
                    returnString = (utl.isnull(viewSalaryEmployee.getItax_catg()) ? "" : viewSalaryEmployee.getItax_catg()) + "=" + (utl.isnull(viewSalaryEmployee.getItax_catg_name()) ? "" : viewSalaryEmployee.getItax_catg_name());
                } else if (getAction().equalsIgnoreCase("getDeducteeCategoryByPanno")) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    ViewSalaryEmployeeDAO viewSalaryEmployeeDAO = factory.getViewSalaryEmployeeDAO();
                    ViewSalaryEmployee viewSalaryEmployee = viewSalaryEmployeeDAO.readDeducteeByPanno(utl, clientMast.getEntity_code(), clientMast.getClient_code(), getPanno());
                    returnString = viewSalaryEmployee.getItax_catg() + "=" + viewSalaryEmployee.getItax_catg_name();
                } else if (getAction().equalsIgnoreCase("getDeducteeCategoryByDeducteeNameOrPanno")) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                    ViewSalaryEmployeeDAO viewSalaryEmployeeDAO = factory.getViewSalaryEmployeeDAO();
                    ViewSalaryDataValidationDAO viewSalaryDataValidationDAO = factory.getViewSalaryDataValidationDAO();
                    ViewSalaryDataValidation viewSalaryEmployee = viewSalaryDataValidationDAO.readDeducteeByDeducteeNameOrPanno(utl, clientMast.getEntity_code(), clientMast.getClient_code(), getDeducteeName(), getPanno());
                    if (viewSalaryEmployee != null) {
                        returnString = viewSalaryEmployee.getItax_catg() + "=" + viewSalaryEmployee.getItax_catg_name();
                    } else {
                        returnString = "";
                    }
                } else if (getAction().equalsIgnoreCase("refreshSalary")) {
                    returnView = "redirectSuccess";

                    //Current employer TDS details exist , Deductee Pan-No not verified
                    String pannoNotVerified
                            = "select count(*)\n"
                            + "from   salary_tran_load a, client_mast b\n"
                            + "where  b.entity_code=a.entity_code\n"
                            + "and    b.client_code=a.client_code\n"
                            + "and    a.entity_code='" + clientMast.getEntity_code() + "'\n"
                            + "and    (b.client_code='" + clientMast.getClient_code() + "' or\n"
                            + "  b.parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "  b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "  b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "  b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "  b.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                            + ")\n"
                            + "and    a.acc_year='" + asmt.getAccYear() + "'\n"
                            + "and    a.tds_type_code='24Q'\n"
                            + "and nvl(a.deductee_panno_verified,'N') = ('N')";

//
                    String pannoNotVerifiedCount = new DbFunctionExecutorAsString().execute_oracle_function_as_string(pannoNotVerified);
                    Long pannoVerifiedCountLong = !utl.isnull(pannoNotVerifiedCount) ? Long.parseLong(pannoNotVerifiedCount) : 0l;
                    if (pannoVerifiedCountLong <= 0l) {
                        //Deductee Pan-No verified
                        String pannoVerified
                                = "select count(*)\n"
                                + "from   salary_tran_load a, client_mast b\n"
                                + "where  b.entity_code=a.entity_code\n"
                                + "and    b.client_code=a.client_code\n"
                                + "and    a.entity_code='" + clientMast.getEntity_code() + "'\n"
                                + "and    (b.client_code='" + clientMast.getClient_code() + "' or\n"
                                + "        b.parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "        b.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "        b.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "        b.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                                + "        b.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                                + "    )\n"
                                + "and    a.acc_year='" + asmt.getAccYear() + "'\n"
                                + "and    a.tds_type_code='24Q'\n"
                                + "and nvl(a.deductee_panno_verified,'N') = ('Y')";

//
                        String pannoVerifiedCount = new DbFunctionExecutorAsString().execute_oracle_function_as_string(pannoVerified);
                        Long pannoVerifiedLong = !utl.isnull(pannoVerifiedCount) ? Long.parseLong(pannoVerifiedCount) : 0l;

                        int quarterNo = Integer.parseInt(asmt.getQuarterNo());
                        if (pannoVerifiedLong > 0l) {
//
                            ProcRefreshSalaryTdsAmt proc = new ProcRefreshSalaryTdsAmt();
                            String result = proc.execureProcedure(clientMast.getEntity_code(), clientMast.getClient_code(), asmt.getAccYear(), quarterNo, asmt.getTdsTypeCode());

                            if (!utl.isnull(result) && result.equalsIgnoreCase("0")) {
                                session.put("SALRYTRANRSLTMSG", "Records Refreshed Successfully");
                                session.put("ERRORCLASS", MessageType.successMessage);
                                returnString = "success";
                            } else {
                                returnString = "Some Error Occured, Could Not Refresh Records";
                                session.put("SALRYTRANRSLTMSG", "Some Error Occured, Could Not Refresh Records");
                                session.put("ERRORCLASS", MessageType.errorMessage);
                            }

                        } else {
                            returnString = "No Verified Deductees Present";
                            session.put("SALRYTRANRSLTMSG", "No Verified Deductees Present");
                            session.put("ERRORCLASS", MessageType.errorMessage);
                        }
                    } else {
                        returnString = "Some PAN No's are not verified, Please Verify";
                        session.put("SALRYTRANRSLTMSG", "Some PAN No's are not verified, Please Verify");
                        session.put("ERRORCLASS", MessageType.errorMessage);
                    }
                } else if (getAction().equalsIgnoreCase("getDeducteeCode")) {
                    if (!utl.isnull(getSalaryTran().getDeductee_panno())) {

                        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                        SalaryTranDAO salaryDAO = factory.getSalaryTranDAO();
                        SalaryTran salaryTran = salaryDAO.findByDeducteePannoAndClientCodeAndEntityCode(getSalaryTran().getDeductee_panno(), viewClientMast.getClient_code(), viewClientMast.getEntity_code());
                        if (salaryTran != null) {
                            if (!utl.isnull(salaryTran.getDeductee_code())) {
                                String name = salaryTran.getDeductee_name();
                                returnString = salaryTran.getDeductee_code() + "#" + name + "#" + (!utl.isnull(salaryTran.getIdentification_no()) ? salaryTran.getIdentification_no() : "");
                            } else {
                                returnString = "";
                            }
                        }
                    }
                } else if (getAction().equalsIgnoreCase("getPannoStatus")) {
                    if (!utl.isnull(getPanno())) {
                        returnString = "";
                        String query = "select lhs_utility.is_valid_panno('" + getPanno() + "') || '#' ||\n"
                                + "       lhs_utility.is_panno_verified('" + getPanno() + "')\n"
                                + "  from dual";
                        DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                        String data = db.execute_oracle_function_as_string(query);
                        if (!utl.isnull(data) && data.contains("#")) {
                            returnString = data;
                        }
                    }

                } else if (getAction().equalsIgnoreCase("callTaxableAmtFunc")) {
                    char amtTypeArray[] = {'I', 'S', 'C', 'R'};
                    returnString = "";
                    String functionSql = "";
                    utl.generateLog("Tax_115bac_flag", getTax_115bac_flag());
                    String emp_catg = getEmp_catg();
                    utl.generateLog("Employee Category Before Tax_115bac_flag value", emp_catg);
                    String tax115flag = getTax_115bac_flag();
                    if (tax115flag.equalsIgnoreCase("Y")) {
                        emp_catg = "N";
                    }
                    utl.generateLog("Employee Category After (YES(Y) OR NO(N)) Tax_115bac_flag value", emp_catg);
//                    Double l_reb_and_rel_total_amt = getReb_and_rel_total_amt();
//                    Double income_tax_total_income = 0.0;
                    Double proc_amt_param = getAfgr4_total_amt();

                    DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
                    for (char type : amtTypeArray) {
//                        utl.generateLog("proc_amt_param for " + type + " =" + proc_amt_param);
                        functionSql = "select nvl(lhs_hrd.get_taxable_amount(nvl(" + proc_amt_param + ", 0),\n"
                                + "                                  '0',\n"
                                + "                                  '" + emp_catg + "',\n"
                                + "                                  'TXINC',\n"
                                + "                                  '" + type + "',\n"
                                + "                                  'Y',\n"
                                + "                                  to_date('" + getSalary_to_date() + "', 'dd-MM-rrrr'),\n"
                                + "                                  '" + getDeductee_panno_verified() + "'), 0) from dual";

                        try {

                            String data = db.execute_oracle_function_as_string(functionSql);

//                            if (!utl.isnull(data) && type == 'I' && income_tax_total_income == 0.0) {
//                                try {
//                                    income_tax_total_income = Double.valueOf(data);
//                                    proc_amt_param = (income_tax_total_income != null ? income_tax_total_income : 0) - (l_reb_and_rel_total_amt != null ? l_reb_and_rel_total_amt : 0);
//                                } catch (Exception e) {
//                                }
//                            }
                            returnString += (!utl.isnull(data) ? data : "0.0") + "#";

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else if (getAction().equalsIgnoreCase("call_proc_stdded_updt")) {
                    returnView = "error";
                    String processSeqNo = "";
//                    try {
                    Long l_client_loginid_seq;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }

//                        GetTokenTransactions tokenTransactions = new GetTokenTransactions();
//                        processSeqNo = tokenTransactions.generateTokenGlobalMethod(asmt, clientMast, user, l_client_loginid_seq,
//                                clientMast.getCode_level(), "ZA", "Standard Deduction Update Process Started", "STD_DED_UPDT", "PROCESS_INSERT", "");
//
//                        utl.generateLog("processSeqNo==" + processSeqNo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    if (!utl.isnull(processSeqNo)) {
                    try {
                        ProcStdDedUpdt procStdDedUpdt = new ProcStdDedUpdt();
                        String std_ded_updt_result = procStdDedUpdt.execureProcedure(clientMast.getEntity_code(), clientMast.getClient_code(), asmt.getAccYear(),
                                Integer.valueOf(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                                asmt.getTdsTypeCode(), null, user.getUser_code(), l_client_loginid_seq);

                        returnView = "success";
                    } catch (Exception e) {
                        returnView = "error";
                        e.printStackTrace();
                    }
                    inputStream = new ByteArrayInputStream(returnView.getBytes("UTF-8"));
                    return "success";
                } else if (getAction().equalsIgnoreCase("reCalculateTds")) {
                    returnView = "error";

                    Long l_client_loginid_seq;
                    Object sessionId = session.get("LOGINSESSIONID");
                    try {
                        l_client_loginid_seq = (Long) sessionId;
                    } catch (Exception e) {
                        l_client_loginid_seq = 0l;
                    }

                    try {
                        ProcSalaryTranTotalRefresh totalRefreshProc = new ProcSalaryTranTotalRefresh();

                        String execureProcedure = totalRefreshProc.execureProcedure(clientMast.getEntity_code(), clientMast.getClient_code(),
                                asmt.getAccYear(), Integer.valueOf(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(),
                                asmt.getTdsTypeCode(), Long.valueOf(0), user.getUser_code(), null);
                        utl.generateLog("call lhs_tds.proc_salary_tran_total_refresh", "");
                        utl.generateLog("out parameters value", execureProcedure);
                        if (execureProcedure != null) {
                            returnView = "success";
                        } else {
                            returnView = "error";
                        }
                    } catch (Exception e) {
                        returnView = "error";
                        e.printStackTrace();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(returnString.getBytes("UTF-8"));
        return returnView;
    }

    public SalaryTranLoad prepareUpdateObject(SalaryTranLoad formObject, SalaryTranLoad dbObject) {
        try {

            Field[] dbObjMethods = dbObject.getClass().getDeclaredFields();
            for (Field field : dbObjMethods) {
                String fieldName = field.getName();
                if (fieldName.endsWith("_amt")) {
                    Object value = PropertyUtils.getProperty(formObject, fieldName);
                    try {
                        PropertyUtils.setProperty(dbObject, fieldName, value);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbObject;
    }

    public SalaryTranLoadSupport() {
        utl = new Util();
    }

    private Double afgr4_total_amt;
    private Double reb_and_rel_total_amt;
    private String emp_catg;
    private String salary_to_date;
    private String deductee_panno;
    private String deductee_panno_verified;

    private final Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private String action;
    private String to_date;
    private String from_date;
    private String functionWithParam;
    private String deductee_code;
    private String yrbegdate;
    private String yrenddate;
    private String month;
    private SalaryTran salaryTran;
    private String textFieldId;
    private String readOnlyField;
    private String textFieldName;
    private String acc_year;
    private String deducteeName;
    private String panno;
    private String deducteeCatg;
    private String onKeyPressEvent;
    private Long rowidSeq;
    private String entity_code;
    private String client_code;
    private String tax_115bac_flag;

    public String getTax_115bac_flag() {
        return tax_115bac_flag;
    }

    public void setTax_115bac_flag(String tax_115bac_flag) {
        this.tax_115bac_flag = tax_115bac_flag;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public String getDeductee_panno_verified() {
        return deductee_panno_verified;
    }

    public Double getAfgr4_total_amt() {
        return afgr4_total_amt;
    }

    public void setAfgr4_total_amt(Double afgr4_total_amt) {
        this.afgr4_total_amt = afgr4_total_amt;
    }

    public Double getReb_and_rel_total_amt() {
        return reb_and_rel_total_amt;
    }

    public void setReb_and_rel_total_amt(Double reb_and_rel_total_amt) {
        this.reb_and_rel_total_amt = reb_and_rel_total_amt;
    }

    public String getEmp_catg() {
        return emp_catg;
    }

    public void setEmp_catg(String emp_catg) {
        this.emp_catg = emp_catg;
    }

    public String getSalary_to_date() {
        return salary_to_date;
    }

    public void setSalary_to_date(String salary_to_date) {
        this.salary_to_date = salary_to_date;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public Long getRowidSeq() {
        return rowidSeq;
    }

    public void setRowidSeq(Long rowidSeq) {
        this.rowidSeq = rowidSeq;
    }

    public String getOnKeyPressEvent() {
        return onKeyPressEvent;
    }

    public void setOnKeyPressEvent(String onKeyPressEvent) {
        this.onKeyPressEvent = onKeyPressEvent;
    }

    public String getDeducteeCatg() {
        return deducteeCatg;
    }

    public void setDeducteeCatg(String deducteeCatg) {
        this.deducteeCatg = deducteeCatg;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getDeducteeName() {
        return deducteeName;
    }

    public SalaryTran getSalaryTran() {
        return salaryTran;
    }

    public void setSalaryTran(SalaryTran salaryTran) {
        this.salaryTran = salaryTran;
    }

    public void setDeducteeName(String deducteeName) {
        this.deducteeName = deducteeName;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTextFieldName() {
        return textFieldName;
    }

    public void setTextFieldName(String textFieldName) {
        this.textFieldName = textFieldName;
    }

    public String getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(String textFieldId) {
        this.textFieldId = textFieldId;
    }

    public String getReadOnlyField() {
        return readOnlyField;
    }

    public void setReadOnlyField(String readOnlyField) {
        this.readOnlyField = readOnlyField;
    }

    public String getYrbegdate() {
        return yrbegdate;
    }

    public void setYrbegdate(String yrbegdate) {
        this.yrbegdate = yrbegdate;
    }

    public String getYrenddate() {
        return yrenddate;
    }

    public void setYrenddate(String yrenddate) {
        this.yrenddate = yrenddate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getFunctionWithParam() {
        return functionWithParam;
    }

    public void setFunctionWithParam(String functionWithParam) {
        this.functionWithParam = functionWithParam;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
