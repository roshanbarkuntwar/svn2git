/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcPanMastInsert;
import dao.DeducteeBflagAmtTranDAO;
import dao.DeducteeMast15ghDAO;
import dao.PanMastDAO;
import dao.ViewClientMastDAO;
import dao.ViewEmpCatgDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import hibernateObjects.DeducteeBflagAmtTran;
import hibernateObjects.DeducteeMast15gh;
import hibernateObjects.PanMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author aniket.naik
 */
public class DeducteeCRUDAction15GH extends ActionSupport implements SessionAware {

    public DeducteeCRUDAction15GH() {
        utl = new Util();
        selectGender = new HashMap<String, String>();
        dateTimeUtil = new DateTimeUtil();
    }

    @Override
    public String execute() throws Exception {
        System.out.println("inside DeducteeCRUDAction15GH");
        String return_value = "input";
        String month = "";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String return_message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ProcGen15GhRefNo ghRefNo = null;

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        ViewClientMast Client = (ViewClientMast) session.get("WORKINGUSER");

        String assessmentYear = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
        Long l_client_loginid_seq;
        Object sessionId = session.get("LOGINSESSIONID");
        try {
            l_client_loginid_seq = (Long) sessionId;
        } catch (Exception e) {
            l_client_loginid_seq = 0l;
        }
        setAction(getAction());
        if (!utl.isnull(getAction())) {
            ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
            setSelectGender(viewEmpCatgDAO.getEmpCatgAsLinkedHashMap());
            if (getAction().equalsIgnoreCase("verifyPAN")) {
                PanMastDAO panMastDAO = factory.getPanMastDAO();
                if (!utl.isnull(getPanNoVal())) {
                    PanMast recordByPanCode = panMastDAO.getRecordByPanCode(getPanNoVal());
                    if (recordByPanCode != null) {
                        return_message = recordByPanCode.getVerifiedby_flag();
                    } else {
                        return_message = "null";
                    }
                }
                return_value = "success";
            } else if (getAction().equalsIgnoreCase("save15GHTranEntry")) {
                String tdsTypeCode = asmt.getTdsTypeCode();
                String quarterNo = asmt.getQuarterNo();
                String acc_year = asmt.getAccYear();
                String entityCode = Client.getEntity_code();
                String clientCode = Client.getClient_code();

                DeducteeMast15ghDAO deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                DeducteeMast15gh pancount = deductee15ghDAO.validateDeducteePanAvailbility(entityCode, clientCode, acc_year, quarterNo, tdsTypeCode,
                        getDeducteeMast15gh().getDeductee_name(), getDeducteeMast15gh().getPanno());
                DeducteeMast15gh pan_not_avble_count = null;

                Date quarterToDate = asmt.getQuarterToDate();
                Date quarterFronDate = asmt.getQuarterFromDate();
                Date yearBegDate = asmt.getYearBegDate();
                Date yearEndDate = asmt.getYearEndDate();

                Date declaration_date = getDeducteeMast15gh().getDeclaration_date();
                boolean check_declaration_date = false;
                if (declaration_date != null) {
                    check_declaration_date = utl.check_date_less_then_selected_dates(quarterFronDate, quarterToDate, declaration_date);

                } else {
                    check_declaration_date = true;
                }

                if (check_declaration_date) {
                    Date income_paid_date = getDeducteeMast15gh().getIncome_paid_date();
                    boolean check_income_paid_date = false;

                    if (income_paid_date != null) {
                        check_income_paid_date = utl.check_date_less_then_selected_dates(yearBegDate, yearEndDate, income_paid_date);
                    } else {
                        check_income_paid_date = true;
                    }

                    if (check_income_paid_date) {
                        if (pancount != null) {
                            String l_pan_val = pancount.getPanno();
                            String L_Deductee_code = pancount.getDeductee_code();
                            return_message = "PAN No Already Available In Database  : <s:a href=\"#\"><span onclick=\"callEditData('" + l_pan_val + "', '" + L_Deductee_code + "');\"  style=\"cursor:pointer;color:blue\">Edit Deductee</span></s:a>";
                        } else if (pancount == null && getDeducteeMast15gh().getPanno() != null && getDeducteeMast15gh().getPanno().equalsIgnoreCase("PANNOTAVBL")) {
                            deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                            pan_not_avble_count = deductee15ghDAO.validateDeducteeForPANNOTAVBL(entityCode, clientCode, acc_year, quarterNo, tdsTypeCode,
                                    getDeducteeMast15gh().getDeductee_name(), getDeducteeMast15gh().getPanno());

                            if (pan_not_avble_count != null) {
                                String l_pan_val = pan_not_avble_count.getPanno();
                                String L_Deductee_code = pan_not_avble_count.getDeductee_code();
                                return_message = "Deductee already added with PANNOTAVBL In Database  : <s:a href=\"#\"><span onclick=\"callEditData('" + l_pan_val + "', '" + L_Deductee_code + "');\"  style=\"cursor:pointer;color:blue\">Edit Deductee</span></s:a>";
                            }

                        }
                        if (pancount == null && pan_not_avble_count == null) {
                            if (!utl.isnull(getDeducteeMast15gh().getDeductee_name())) {
                                try {
                                    if (!utl.isnull(getDeducteeMast15gh().getDeductee_type_code())) {
                                        String[] Deductee_type_code_catg = getDeducteeMast15gh().getDeductee_type_code().split("~");
                                        getDeducteeMast15gh().setDeductee_type_code(Deductee_type_code_catg[0]);
                                    } else {
                                        getDeducteeMast15gh().setDeductee_type_code("");
                                    }
                                    getDeducteeMast15gh().setAssesment_acc_year(utl.ChangeAccYearToAssessmentAccYear(acc_year));
                                    getDeducteeMast15gh().setFrom_date(quarterFronDate == null ? "" : sdf.format(quarterFronDate));
                                    getDeducteeMast15gh().setTo_date(quarterToDate == null ? "" : sdf.format(quarterToDate));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    getDeducteeMast15gh().setEntity_code(entityCode);
                                    getDeducteeMast15gh().setClient_code(clientCode);
                                    getDeducteeMast15gh().setAcc_year(acc_year);
                                    getDeducteeMast15gh().setQuarter_no(quarterNo);
                                    getDeducteeMast15gh().setTds_type_code(tdsTypeCode);
                                    getDeducteeMast15gh().setInsert_flag("F");
                                    getDeducteeMast15gh().setCreated_date(new Date());
                                    getDeducteeMast15gh().setLastupdate(new Date());
                                    if (getDeducteeMast15gh().getDob() != null) {
                                        getDeducteeMast15gh().setDob(sdf.format(getDeducteeMast15gh().getDob()));
                                    }
                                    getDeducteeMast15gh().setSex(getDeducteeMast15gh().getSex());
                                    getDeducteeMast15gh().setBflag(getDeducteeMast15gh().getBflag());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    if (!utl.isnull(tdsTypeCode) && tdsTypeCode.equalsIgnoreCase("26Q")) {
                                        if (!utl.isnull(getDeducteeMast15gh().getLatest_assessment_year()) || !utl.isnull(getDeducteeMast15gh().getEst_declaration_income()) || !utl.isnull(getDeducteeMast15gh().getEst_total_income()) || !utl.isnull(getDeducteeMast15gh().getTotal_no_form()) || !utl.isnull(getDeducteeMast15gh().getAggregate_income_amt()) || getDeducteeMast15gh().getDeclaration_date() != null || !utl.isnull(getDeducteeMast15gh().getIncome_amount_paid()) || getDeducteeMast15gh().getIncome_paid_date() != null) {
                                            String l_declared_date = "";
                                            if (getDeducteeMast15gh().getDeclaration_date() != null) {
                                                l_declared_date = sdf.format(getDeducteeMast15gh().getDeclaration_date());
                                            }

                                            String l_income_pad_date = "";
                                            if (getDeducteeMast15gh().getIncome_paid_date() != null) {
                                                l_income_pad_date = sdf.format(getDeducteeMast15gh().getIncome_paid_date());
                                            }
                                            getDeducteeMast15gh().setDeclaration_date(l_declared_date);
                                            getDeducteeMast15gh().setIncome_paid_date(l_income_pad_date);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                Long result = deductee15ghDAO.saveAndReturnDeducteeCode(getDeducteeMast15gh());
                                if (result > 0) {
                                    try {

                                        int savedCounter = 0;
                                        int tranCounter = 0;
                                        DeducteeBflagAmtTranDAO deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                                        Session hibernateSession = deducteeBflagAmtTranDAO.getHibernateSession();
                                        if (getDeducteeBflagAmtTranList() != null && getDeducteeBflagAmtTranList().size() > 0) {
                                            for (int i = 0; i < getDeducteeBflagAmtTranList().size(); i++) {
                                                DeducteeBflagAmtTran tran = getDeducteeBflagAmtTranList().get(i);
                                                if (tran != null) {
                                                    if (!utl.isnull(tran.getAccount_no()) && !utl.isnull(tran.getSection())) {

                                                        tranCounter++;
                                                        tran.setEntity_code(entityCode);
                                                        tran.setClient_code(clientCode);
                                                        tran.setAcc_year(acc_year);
                                                        tran.setDeductee_mast_15gh_rowid_seq(result);
                                                        tran.setReference_no(getDeducteeMast15gh().getReference_no());
                                                        tran.setPanno(getDeducteeMast15gh().getPanno());
                                                        tran.setQuarter_no(quarterNo);
                                                        tran.setTds_type_code(tdsTypeCode);
                                                        Serializable save = hibernateSession.save(tran);
                                                        if (save != null) {
                                                            savedCounter++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (tranCounter == savedCounter) {
                                            hibernateSession.getTransaction().commit();
                                        } else {
                                            hibernateSession.getTransaction().rollback();
                                        }
                                    } catch (Exception e) {
                                    }

                                    ProcPanMastInsert obj_ProcPanMastInsert = new ProcPanMastInsert();
                                    boolean savePanMastResult = obj_ProcPanMastInsert.execute_procedure(entityCode, clientCode, acc_year, Integer.parseInt(quarterNo),
                                            tdsTypeCode, getDeducteeMast15gh().getPanno(), getDeducteeMast15gh().getDeductee_name());

                                    try {
                                        String l_update_pan_query
                                                = "update deductee_mast_15gh a set a.deductee_panno_verified=(select lhs_utility.is_panno_verified(a.panno) from dual),\n"
                                                + "                           a.deductee_panno_valid=(select lhs_utility.is_valid_panno(a.panno) from dual),\n"
                                                + "                           a.name_as_panno=(select lhs_utility.get_name('panmast_name',panno) from dual)\n"
                                                + "where entity_code='" + entityCode + "'\n"
                                                + "and nvl(deductee_panno_verified,'N')<>'Y'\n"
                                                + "and rowid_seq = '" + result + "'\n"
                                                + "and   exists (select 1\n"
                                                + "              from   client_mast b\n"
                                                + "              where  b.client_code=a.client_code\n"
                                                + "              and    (b.client_code = '" + clientCode + "' or b.parent_code = '" + clientCode + "' or\n"
                                                + "                      b.g_parent_code = '" + clientCode + "' or b.sg_parent_code = '" + clientCode + "' or\n"
                                                + "                      b.ssg_parent_code = '" + clientCode + "' or b.sssg_parent_code = '" + clientCode + "')\n"
                                                + "              )";

                                        DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                                        boolean updatePanColumnResult = objDbFunctionClass.execute_oracle_update_function_as_string(l_update_pan_query);
                                        utl.generateSpecialLog("updatePanColumnResult...", updatePanColumnResult);

                                    } catch (Exception e) {
                                    }
                                    return_message = "saveSuccess";
                                } else {
                                    return_message = "Could not save, some error occured";
                                }
                            }
                        }
                    } else {
                        return_message = "Date of income paid should be of this Acc Year";
                    }
                } else {
                    return_message = "Date of Declaration should be of this Quarter";
                }
                return_value = "success";
            } else if (getAction().equalsIgnoreCase("update")) {
                   System.out.println("Update Action");
                //   Assessment asmt = (Assessment) session.get("ASSESSMENT");
               try {
                    ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
                    ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                    String quarterNo = asmt.getQuarterNo();
                    String acc_year = asmt.getAccYear();
                    String tdsTypeCode = asmt.getTdsTypeCode();
                    DeducteeMast15ghDAO deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                    DeducteeMast15gh dm = deductee15ghDAO.readById(client.getEntity_code(), client.getClient_code(), acc_year, quarterNo, tdsTypeCode, getRowid_seq());
                    if (dm != null) {

                        DeducteeMast15gh pancount = null;
                        DeducteeMast15gh pan_not_avble_count = null;
                        Date quarterToDate = asmt.getQuarterToDate();
                        Date quarterFromDate = asmt.getQuarterFromDate();
                        // Date yearBegDate = YearBeginingDate is Skipped for now, Akash Sir will Configure this in assessment Obj later.
                        //Date yearEndDate = YearEndDate is Skipped for now, Akash Sir will Configure this in assessment Obj later.

                        Date declaration_date = getDeducteeMast15gh().getDeclaration_date();
                        boolean check_declaration_date = false;
                        if (declaration_date != null) {
                            check_declaration_date = dateTimeUtil.check_date_less_then_selected_dates(quarterFromDate, quarterToDate, declaration_date);
                        } else {
                            check_declaration_date = true;
                        }

                        if (check_declaration_date) {

                            Date income_paid_date = getDeducteeMast15gh().getIncome_paid_date();
                            boolean check_income_paid_date = false;
                            if (income_paid_date != null) {

                                // check_income_paid_date = utl.check_date_less_then_selected_dates(yearBegDate, yearEndDate, income_paid_date); // Please Uncomment this if Assessment Obj is set with begDate and EndDate
                            } else {
                                check_income_paid_date = true;
                            }

                            if (!check_income_paid_date) { // Please Reverse the condition if Negation is used in if statement when  begDate and EndDate is set.

                                if (!dm.getPanno().equalsIgnoreCase(getDeducteeMast15gh().getPanno())) {
                                    if (!dm.getDeductee_name().equalsIgnoreCase(getDeducteeMast15gh().getDeductee_name())) {
                                        deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                        pancount = deductee15ghDAO.validateDeducteePanAvailbility(client.getEntity_code(), client.getClient_code(), acc_year, quarterNo, tdsTypeCode, getDeducteeMast15gh().getDeductee_name(), getDeducteeMast15gh().getPanno());
                                        if (pancount != null) {
                                            String l_pan_val = pancount.getPanno();
                                            String L_Deductee_code = pancount.getDeductee_code();
                                            return_message = "PAN No Already Available In Database  : <s:a href=\"#\"><span onclick=\"callEditData('" + l_pan_val + "', '" + L_Deductee_code + "');\"  style=\"cursor:pointer;color:blue\">Edit Deductee</span></s:a>";
                                        } else if (pancount == null && getDeducteeMast15gh().getPanno() != null && getDeducteeMast15gh().getPanno().equalsIgnoreCase("PANNOTAVBL")) {
                                            deductee15ghDAO = factory.getDeducteeMast15ghDAO();
                                            pan_not_avble_count = deductee15ghDAO.validateDeducteeForPANNOTAVBL(client.getEntity_code(), client.getClient_code(), acc_year, quarterNo, tdsTypeCode, getDeducteeMast15gh().getDeductee_name(), getDeducteeMast15gh().getPanno());
                                            if (pan_not_avble_count != null) {
                                                String l_pan_val = pan_not_avble_count.getPanno();
                                                String L_Deductee_code = pan_not_avble_count.getDeductee_code();
                                                return_message = "Deductee already added with PANNOTAVBL In Database  : <s:a href=\"#\"><span onclick=\"callEditData('" + l_pan_val + "', '" + L_Deductee_code + "');\"  style=\"cursor:pointer;color:blue\">Edit Deductee</span></s:a>";
                                            }
                                        }

                                    }

                                }
                                if (pancount == null && pan_not_avble_count == null) {
                                    dm.setDeductee_catg(getDeducteeMast15gh().getDeductee_catg());
                                    dm.setEmp_code(getDeducteeMast15gh().getEmp_code());
                                    dm.setPanno(getDeducteeMast15gh().getPanno());

                                    dm.setDeductee_name(getDeducteeMast15gh().getDeductee_name());
                                    if (getDeducteeMast15gh().getDob() != null) {
                                        String dobString = sdf.format(getDeducteeMast15gh().getDob());
                                        dm.setDob(dobString);
                                    }
                                    dm.setTds_code(getDeducteeMast15gh().getTds_code());
                                    try {
                                        if (!utl.isnull(getDeducteeMast15gh().getDeductee_type_code())) {
                                            String[] Deductee_type_code_catg = getDeducteeMast15gh().getDeductee_type_code().split("~");
                                            getDeducteeMast15gh().setDeductee_type_code(Deductee_type_code_catg[0]);
                                            dm.setDeductee_type_code(Deductee_type_code_catg[0]);
                                        } else {
                                            dm.setDeductee_type_code("");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    dm.setResident_status(getDeducteeMast15gh().getResident_status());
                                    dm.setTds_code(getDeducteeMast15gh().getTds_code());
                                    dm.setCountry_code(getDeducteeMast15gh().getCountry_code());
                                    dm.setDeduction_ref_no(getDeducteeMast15gh().getDeduction_ref_no());
                                    dm.setCertficate_no(getDeducteeMast15gh().getCertficate_no());
                                    dm.setSex(getDeducteeMast15gh().getSex());

                                    if (getDeducteeMast15gh().getJoin_date() != null) {
                                        String joinString = sdf.format(getDeducteeMast15gh().getJoin_date());
                                        dm.setJoin_date(joinString);
                                    }
                                    if (getDeducteeMast15gh().getLeft_date() != null) {
                                        String leftString = sdf.format(getDeducteeMast15gh().getLeft_date());
                                        dm.setLeft_date(leftString);
                                    }

                                    dm.setAdd1(getDeducteeMast15gh().getAdd1());
                                    dm.setAdd2(getDeducteeMast15gh().getAdd2());
                                    dm.setAdd3(getDeducteeMast15gh().getAdd3());
                                    dm.setAdd4(getDeducteeMast15gh().getAdd4());
                                    dm.setStdcode(getDeducteeMast15gh().getStdcode());
                                    dm.setPhoneno(getDeducteeMast15gh().getPhoneno());
                                    dm.setMobileno(getDeducteeMast15gh().getMobileno());
                                    dm.setPin(getDeducteeMast15gh().getPin());
                                    dm.setEmail_id(getDeducteeMast15gh().getEmail_id());
                                    dm.setState_code(getDeducteeMast15gh().getState_code());
                                    dm.setCity_code(getDeducteeMast15gh().getCity_code());

                                    dm.setLastupdate(new Date());

                                    try {
                                        if (!utl.isnull(getDeducteeMast15gh().getLatest_assessment_year()) || !utl.isnull(getDeducteeMast15gh().getEst_declaration_income()) || !utl.isnull(getDeducteeMast15gh().getEst_total_income()) || !utl.isnull(getDeducteeMast15gh().getTotal_no_form()) || !utl.isnull(getDeducteeMast15gh().getAggregate_income_amt()) || getDeducteeMast15gh().getDeclaration_date() != null || !utl.isnull(getDeducteeMast15gh().getIncome_amount_paid()) || getDeducteeMast15gh().getIncome_paid_date() != null) {
                                            String l_declared_date = "";
                                            if (getDeducteeMast15gh().getDeclaration_date() != null) {
                                                l_declared_date = sdf.format(getDeducteeMast15gh().getDeclaration_date());
                                            }

                                            String l_income_pad_date = "";
                                            if (getDeducteeMast15gh().getIncome_paid_date() != null) {
                                                l_income_pad_date = sdf.format(getDeducteeMast15gh().getIncome_paid_date());
                                            }
                                            dm.setDeclaration_date(l_declared_date);
                                            dm.setIncome_paid_date(l_income_pad_date);
                                            dm.setBflag(getDeducteeMast15gh().getBflag());
                                            dm.setReference_no(getDeducteeMast15gh().getReference_no());
                                            dm.setLatest_assessment_year(getDeducteeMast15gh().getLatest_assessment_year());
                                            dm.setEst_declaration_income(getDeducteeMast15gh().getEst_declaration_income());
                                            dm.setEst_total_income(getDeducteeMast15gh().getEst_total_income());
                                            dm.setTotal_no_form(getDeducteeMast15gh().getTotal_no_form());
                                            dm.setAggregate_income_amt(getDeducteeMast15gh().getAggregate_income_amt());
                                            dm.setIncome_amount_paid(getDeducteeMast15gh().getIncome_amount_paid());
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    deductee15ghDAO = factory.getDeducteeMast15ghDAO();//this new instance required because transaction is commited after every call
                                    boolean update = deductee15ghDAO.update(dm);

                                    if (update) {
                                        try {
                                            int savedCounter = 0;
                                            int tranCounter = 0;
                                            DeducteeBflagAmtTranDAO deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                                            Session hibernateSession = deducteeBflagAmtTranDAO.getHibernateSession();
                                            if (getDeducteeBflagAmtTranList() != null && getDeducteeBflagAmtTranList().size() > 0) {
                                                for (int i = 0; i < getDeducteeBflagAmtTranList().size(); i++) {
                                                    DeducteeBflagAmtTran tran = getDeducteeBflagAmtTranList().get(i);
                                                    if (tran != null) {
                                                        if (!utl.isnull(tran.getAccount_no()) && !utl.isnull(tran.getSection())) {
                                                            tranCounter++;
                                                            if (tran.getBflag_tran_seq_id() != null && tran.getBflag_tran_seq_id() > 0l) {
                                                                hibernateSession.update(tran);
                                                                savedCounter++;
                                                            } else {
                                                                tran.setEntity_code(client.getEntity_code());
                                                                tran.setClient_code(client.getClient_code());
                                                                tran.setAcc_year(asmt.getAccYear());
                                                                tran.setDeductee_mast_15gh_rowid_seq(dm.getRowid_seq());
                                                                tran.setReference_no(dm.getReference_no());
                                                                tran.setPanno(getDeducteeMast15gh().getPanno());
                                                                tran.setQuarter_no(quarterNo);
                                                                tran.setTds_type_code(tdsTypeCode);
                                                                Serializable save = hibernateSession.save(tran);
                                                                if (save != null) {
                                                                    savedCounter++;
                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                            if (tranCounter == savedCounter) {
                                                hibernateSession.getTransaction().commit();
                                            } else {
                                                hibernateSession.getTransaction().rollback();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            PanMastDAO objPanmastdao = factory.getPanMastDAO();
                                            PanMast panMastList = objPanmastdao.getRecordByPanCode(getDeducteeMast15gh().getPanno());
                                            if (panMastList == null) {
                                                ProcPanMastInsert obj_ProcPanMastInsert = new ProcPanMastInsert();
                                                boolean savePanMastResult = obj_ProcPanMastInsert.execute_procedure(client.getEntity_code(), client.getClient_code(), acc_year, Integer.parseInt(quarterNo), tdsTypeCode, getDeducteeMast15gh().getPanno(), getDeducteeMast15gh().getDeductee_name());
                                            }
                                            try {
                                                Form15GHDB query = new Form15GHDB();
                                                String updateQuery = query.getUpdateQuery(client, getRowid_seq());
                                                //System.out.println("Update Query---->"+updateQuery);
                                                DbFunctionExecutorAsString objDb = new DbFunctionExecutorAsString();
                                                boolean updatePanColumnResult = objDb.execute_oracle_update_function_as_string(updateQuery);

                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        return_message = "updateSuccess-" + getPageNumber() + "-" + getUpdate_data_row();
                                    } else {
                                        return_message = "Some Error Occured, Could Not Update";
                                    }

                                }

                            } else {
                                return_message = "Date of income paid should be of this Acc Year";
                            }

                        } else {
                            return_message = "Date of Declaration should be of this Quarter";
                        }

                    } else {
                        return_message = "Some Error Occured, Could Not Update";
                    }

                } catch (Exception e) {
                    return_message = "Some Error Occured, Could Not Update";
                    e.printStackTrace();
                }
                return_value = "success";
            } else if (getAction().equalsIgnoreCase("deleteBflagAmt")) {
                if (getDeducteeBflagSeqNo() != null) {
                    DeducteeBflagAmtTranDAO deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                    DeducteeBflagAmtTran readAmtTranById = deducteeBflagAmtTranDAO.readAmtTranById(getDeducteeBflagSeqNo());
                    if (readAmtTranById != null) {
                        deducteeBflagAmtTranDAO = factory.getDeducteeBflagAmtTranDAO();
                        boolean delete = deducteeBflagAmtTranDAO.delete(readAmtTranById);
                        if (delete) {
                            return_value = "success";
                        } else {
                            return_value = "error";
                        }
                    }
                }
            } else if (getAction().equalsIgnoreCase("checkExistingRefNo")) {
                //    Assessment asmt = (Assessment) session.get("ASSESSMENT");
                //    ViewClientMast Client = (ViewClientMast) session.get("WORKINGUSER");
                //  String assessmentYear = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());
                if (!utl.isnull(getNextGen15GNo())) {
                    setNextGen15GNo("G" + getNextGen15GNo());
                }
                if (!utl.isnull(getNextGen15HNo())) {
                    setNextGen15HNo("H" + getNextGen15HNo());
                }
                String processTypeG = "";
                if (!utl.isnull(getEditRadioBtnG())) {
                    if (getEditRadioBtnG().equalsIgnoreCase("R")) {
                        processTypeG = "R";
                    } else if (getEditRadioBtnG().equalsIgnoreCase("B")) {
                        processTypeG = "B";
                    }
                }
                String processTypeH = "";
                if (!utl.isnull(getEditRadioBtnH())) {
                    if (getEditRadioBtnH().equalsIgnoreCase("R")) {
                        processTypeH = "R";
                    } else if (getEditRadioBtnH().equalsIgnoreCase("B")) {
                        processTypeH = "B";
                    }
                }

                int gTypeValue = 0;
                if (!utl.isnull(processTypeG)) {
                    if (processTypeG.equalsIgnoreCase("B")) {
                        gTypeValue = checkExistingReferenceNo(Client.getEntity_code(), Client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getNextGen15GNo(), "B");

                    } else if (processTypeG.equalsIgnoreCase("R")) {
                        gTypeValue = checkExistingReferenceNo(Client.getEntity_code(), Client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getNextGen15GNo(), "B");
                    }
                }

                int hTypeValue = 0;
//                    if (!utl.isnull(processTypeG) && !processTypeG.equalsIgnoreCase("R")) {
                if (!utl.isnull(processTypeG)) {

                    if (processTypeH.equalsIgnoreCase("B")) {
                        hTypeValue = checkExistingReferenceNo(Client.getEntity_code(), Client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getNextGen15HNo(), "D");

                    } else if (processTypeH.equalsIgnoreCase("R")) {
                        hTypeValue = checkExistingReferenceNo(Client.getEntity_code(), Client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), getNextGen15HNo(), "D");

                    }
                }
                if (gTypeValue > 0 && hTypeValue > 0) {
                    return_message = "" + getNextGen15GNo() + " & " + getNextGen15HNo() + " Reference No Already Available In Database";
                } else if (gTypeValue > 0 && hTypeValue <= 0) {
                    return_message = "" + getNextGen15GNo() + " Reference No Already Available In Database";
                } else if (gTypeValue <= 0 && hTypeValue > 0) {
                    return_message = "" + getNextGen15HNo() + " Reference No Already Available In Database";
                } else if (gTypeValue <= 0 && hTypeValue <= 0) {
                    return_message = "T";
                }

                if (!utl.isnull(return_message) && return_message.equalsIgnoreCase("T")) {
                    try {
                        ghRefNo = new ProcGen15GhRefNo();
                        String process_result = ghRefNo.executepProcGen15ghRefno(Client.getEntity_code(), Client.getClient_code(), asmt.getAccYear(), assessmentYear, Integer.parseInt(asmt.getQuarterNo()), month, asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), getNextGen15GNo(), getNextGen15HNo(), processTypeG, l_client_loginid_seq);
                        //  System.out.println("process_result--->"+process_result);
                        if (!utl.isnull(process_result) && process_result.equalsIgnoreCase("0")) {
                            return_message = "save";
                        } else {
                            return_message = "error";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return_message = "error";
                    }
                }
                return_value = "success";
            } else if (getAction().equalsIgnoreCase("checkRefNoList")) {
                //   Assessment asmt = (Assessment) session.get("ASSESSMENT");
                //  ViewClientMast Client = (ViewClientMast) session.get("WORKINGUSER");
                return_message = "T";
                try {
                    String clientList[] = new String[0];
                    if (!utl.isnull(getClient_code_list())) {
                        clientList = getClient_code_list().split("#");
                    }
                    String next15GList[] = new String[0];
                    if (!utl.isnull(getNext_gen_15g_list())) {
                        next15GList = getNext_gen_15g_list().split("#");
                    }

                    String next15HList[] = new String[0];
                    if (!utl.isnull(getNext_gen_15h_list())) {
                        next15HList = getNext_gen_15h_list().split("#");
                    }

                    if (clientList != null && clientList.length > 0) {
                        for (int i = 0; i < clientList.length; i++) {
                            try {
                                if (!utl.isnull(next15GList[i])) {
                                    next15GList[i] = "G" + next15GList[i];
                                }
                                if (!utl.isnull(next15HList[i])) {
                                    next15HList[i] = "H" + next15HList[i];
                                }
                                int gTypeValue = checkRefNo(Client.getEntity_code(), clientList[i], asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), next15GList[i], "B");
                                int hTypeValue = checkRefNo(Client.getEntity_code(), clientList[i], asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), next15HList[i], "D");
                                if (gTypeValue > 0 && hTypeValue > 0) {
                                    return_message = "" + next15GList[i] + " & " + next15HList[i] + " Refrance No Already Available In Database";
                                    break;
                                } else if (gTypeValue > 0 && hTypeValue <= 0) {
                                    return_message = "" + next15GList[i] + " Refrance No Already Available In Database";
                                    break;
                                } else if (gTypeValue <= 0 && hTypeValue > 0) {
                                    return_message = "" + next15HList[i] + " Refrance No Already Available In Database";
                                    break;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        return_message = "T";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return_message = "T";
                }
                return_value = "success";
            } else if (getAction().equalsIgnoreCase("saveRefNoDetails")) {

                if (getEditCheckBox() != null && getEditCheckBox().size() > 0) {
                    if (getRefNoList() != null && getRefNoList().size() > 0) {
                        ArrayList<ReferenceNoDetailPOJO> refNoDetails = new ArrayList<ReferenceNoDetailPOJO>();
                        ArrayList<String> arrData = new ArrayList<String>();
                        for (int i = 0; i < getEditCheckBox().size(); i++) {
                            Boolean checkBox = getEditCheckBox().get(i);
                            if (checkBox) {
                                ReferenceNoDetailPOJO get = getRefNoList().get(i);
                                refNoDetails.add(get);
                                arrData.add(getEditRadioBtn().get(i));
                            }// end if
                        }// end for loop (iterate checkbox)
                        int saveCount = 0;

                        for (int j = 0; j < refNoDetails.size(); j++) {
                            String next15G = refNoDetails.get(j).getNext_gen_15g();
                            String next15H = refNoDetails.get(j).getNext_gen_15h();
                            String radioBtnValue = arrData.get(j);
                            String clientCode = "";

                            if (!utl.isnull(getUser_level()) && getUser_level().equalsIgnoreCase("next")) {
                                clientCode = refNoDetails.get(j).getClient_code();
                            } else {
                                clientCode = Client.getClient_code();
                            }
//                            System.out.println("next15G..." + next15G);
//                            System.out.println("next15H..." + next15H);
//                            System.out.println("clientCode.." + clientCode);

                            if (!utl.isnull(next15G)) {
                                next15G = "G" + next15G;
                            }
                            if (!utl.isnull(next15H)) {
                                next15H = "H" + next15H;
                            }

                            String processType = "";
                            if (!utl.isnull(radioBtnValue)) {
                                if (radioBtnValue.equalsIgnoreCase("R")) {
                                    processType = "R";
                                } else if (radioBtnValue.equalsIgnoreCase("B")) {
                                    processType = "B";
                                }
                            }

                            ProcGen15GhRefNo ghRefNo1 = new ProcGen15GhRefNo();
//                                String process_result = ExceutepProcGen15ghRefno(entity_code, clientCode, accYear, assessmentYear, l_quarter_no, month, l_from_date, l_to_date, tdsTypeCode, next15G, next15H, processType, l_client_loginid_seq);
                            String process_result = ghRefNo1.executepProcGen15ghRefno(Client.getEntity_code(), clientCode, asmt.getAccYear(), assessmentYear, Integer.parseInt(asmt.getQuarterNo()), month, asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), next15G, next15H, processType, l_client_loginid_seq);
                            if (!utl.isnull(process_result) && process_result.equalsIgnoreCase("0")) {
                                saveCount++;
                            }
                        }
                        if (saveCount > 0) {
                            return_message = "saveData";
                        } else {
                            return_message = "error";
                        }
                    }
                }

                return_value = "success";
            }

        }

        inputStream = new ByteArrayInputStream(return_message.getBytes("UTF-8"));
        return return_value;
    }

    private int checkExistingReferenceNo(String entity_code, String genClientCode, String accYear, String quarterNo, String tdsTypeCode, String nextGen15GNo, String Bflag) {
        int result = 0;
        try {
            DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
            Form15GHDB form15DB = new Form15GHDB();
            String functionString = form15DB.getExistingReferenceNoQuery(genClientCode, accYear, entity_code, quarterNo, tdsTypeCode, nextGen15GNo, Bflag);
            utl.generateSpecialLog("functionString query...", functionString);
            String l_return_value = ef.execute_oracle_function_as_string(functionString);
            l_return_value = utl.isnull(l_return_value) ? "0" : l_return_value;
            result = Integer.parseInt(l_return_value);
        } catch (Exception e) {
        }
        return result;
    }//end method

    private int checkRefNo(String entity_code, String genClientCode, String accYear, String quarterNo, String tdsTypeCode, String nextGen15HNo, String Bflag) {

        int result = 0;
        try {
            DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
            Form15GHDB form15Db = new Form15GHDB();
            String query = form15Db.getFunctionString(entity_code, accYear, quarterNo, tdsTypeCode, Bflag, genClientCode, nextGen15HNo);
            utl.generateSpecialLog("functionString query..564..", query);
            String l_return_value = ef.execute_oracle_function_as_string(query);
            l_return_value = utl.isnull(l_return_value) ? "0" : l_return_value;
            result = Integer.parseInt(l_return_value);
        } catch (Exception e) {
        }
        return result;
    }

    Map<String, Object> session;
    private String action;
    private HashMap<String, String> selectGender;
    Util utl;
    private Long rowid_seq;
    private int pageNumber;
    private InputStream inputStream;
    private String PanNoVal;
    private DeducteeMast15gh deducteeMast15gh;
    private final DateTimeUtil dateTimeUtil;
    private String update_data_row;
    private ArrayList<DeducteeBflagAmtTran> deducteeBflagAmtTranList;
    private Long deducteeBflagSeqNo;
    private String NextGen15GNo;
    private String NextGen15HNo;
    private String editRadioBtnG;
    private String editRadioBtnH;
    private String GenClientCode;
    private String generationLevelClient;
    private String client_code_list;
    private String next_gen_15g_list;
    private String next_gen_15h_list;
    private String user_level;
    private ArrayList<Boolean> editCheckBox;
    private ArrayList<ReferenceNoDetailPOJO> refNoList;
    private ArrayList<String> editRadioBtn;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HashMap<String, String> getSelectGender() {
        return selectGender;
    }

    public void setSelectGender(HashMap<String, String> selectGender) {
        this.selectGender = selectGender;
    }

    public String getPanNoVal() {
        return PanNoVal;
    }

    public void setPanNoVal(String PanNoVal) {
        this.PanNoVal = PanNoVal;
    }

    public InputStream getInputStream() {
        return inputStream;
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

    public DeducteeMast15gh getDeducteeMast15gh() {
        return deducteeMast15gh;
    }

    public void setDeducteeMast15gh(DeducteeMast15gh deducteeMast15gh) {
        this.deducteeMast15gh = deducteeMast15gh;
    }

    public ArrayList<DeducteeBflagAmtTran> getDeducteeBflagAmtTranList() {
        return deducteeBflagAmtTranList;
    }

    public void setDeducteeBflagAmtTranList(ArrayList<DeducteeBflagAmtTran> deducteeBflagAmtTranList) {
        this.deducteeBflagAmtTranList = deducteeBflagAmtTranList;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getUpdate_data_row() {
        return update_data_row;
    }

    public void setUpdate_data_row(String update_data_row) {
        this.update_data_row = update_data_row;
    }

    public Long getDeducteeBflagSeqNo() {
        return deducteeBflagSeqNo;
    }

    public void setDeducteeBflagSeqNo(Long deducteeBflagSeqNo) {
        this.deducteeBflagSeqNo = deducteeBflagSeqNo;
    }

    public String getNextGen15GNo() {
        return NextGen15GNo;
    }

    public void setNextGen15GNo(String NextGen15GNo) {
        this.NextGen15GNo = NextGen15GNo;
    }

    public String getNextGen15HNo() {
        return NextGen15HNo;
    }

    public void setNextGen15HNo(String NextGen15HNo) {
        this.NextGen15HNo = NextGen15HNo;
    }

    public String getEditRadioBtnG() {
        return editRadioBtnG;
    }

    public void setEditRadioBtnG(String editRadioBtnG) {
        this.editRadioBtnG = editRadioBtnG;
    }

    public String getEditRadioBtnH() {
        return editRadioBtnH;
    }

    public void setEditRadioBtnH(String editRadioBtnH) {
        this.editRadioBtnH = editRadioBtnH;
    }

    public String getGenClientCode() {
        return GenClientCode;
    }

    public void setGenClientCode(String GenClientCode) {
        this.GenClientCode = GenClientCode;
    }

    public String getGenerationLevelClient() {
        return generationLevelClient;
    }

    public void setGenerationLevelClient(String generationLevelClient) {
        this.generationLevelClient = generationLevelClient;
    }

    public String getClient_code_list() {
        return client_code_list;
    }

    public void setClient_code_list(String client_code_list) {
        this.client_code_list = client_code_list;
    }

    public String getNext_gen_15g_list() {
        return next_gen_15g_list;
    }

    public void setNext_gen_15g_list(String next_gen_15g_list) {
        this.next_gen_15g_list = next_gen_15g_list;
    }

    public String getNext_gen_15h_list() {
        return next_gen_15h_list;
    }

    public void setNext_gen_15h_list(String next_gen_15h_list) {
        this.next_gen_15h_list = next_gen_15h_list;
    }

    public ArrayList<Boolean> getEditCheckBox() {
        return editCheckBox;
    }

    public void setEditCheckBox(ArrayList<Boolean> editCheckBox) {
        this.editCheckBox = editCheckBox;
    }

    public ArrayList<ReferenceNoDetailPOJO> getRefNoList() {
        return refNoList;
    }

    public void setRefNoList(ArrayList<ReferenceNoDetailPOJO> refNoList) {
        this.refNoList = refNoList;
    }

    public ArrayList<String> getEditRadioBtn() {
        return editRadioBtn;
    }

    public void setEditRadioBtn(ArrayList<String> editRadioBtn) {
        this.editRadioBtn = editRadioBtn;
    }

}
