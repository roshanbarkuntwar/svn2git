/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcSalaryTranTotalRefresh;
import dao.SalaryTranLoadDAO;
import dao.ViewEmpCatgDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.SalaryTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryEntryAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private final Util utl;
    private HashMap<String, String> empCatgMap;
    private HashMap<String, String> taxFlag;
    private SalaryTranLoad salaryTranLoad;
    private InputStream inputStream;
    private String action;
    private Long rowid_seq;

    public SalaryEntryAction() {
        salaryTranLoad = new SalaryTranLoad();
        utl = new Util();

        taxFlag = new HashMap<>();
        taxFlag.put("Y", "YES");
        taxFlag.put("N", "NO");
    }

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        try {
            session.put("ACTIVE_TAB", "salaryEntryNew");

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast user = (UserMast) session.get("LOGINUSER");

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

            // Entry CRUD operations
            if (getAction() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

                Object rowid_seq = null;
                SalaryTranLoadDAO salaryTranLoadDAO = factory.getSalaryTranLoadDAO();

                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }
                if (getAction().equalsIgnoreCase("save")) {
                    return_view = "error";

                    try {
                        /**
                         * Save salary entry to the database.
                         *
                         *
                         *
                         */

                        //SAVE entry for new salary data
                        SalaryTranLoad salaryTran = getSalaryTranLoad();
                        if (salaryTran.getRowid_seq() == null) {
                            //utl.generateLog("rowid seq save: " + salaryTran.getRowid_seq());
//                            //Setting default values for login user
//                            salaryTran.setRowid_seq(null);
                            salaryTran.setDeductee_panno(salaryTran.getDeductee_panno().toUpperCase());
                            salaryTran.setDeductee_name(salaryTran.getDeductee_name().toUpperCase());
                            salaryTran.setIdentification_no(!utl.isnull(salaryTran.getIdentification_no()) ? salaryTran.getIdentification_no().toUpperCase() : "");
                            salaryTran.setDeductee_address(!utl.isnull(salaryTran.getDeductee_address()) ? salaryTran.getDeductee_address().toUpperCase() : "");
                            salaryTran.setEntity_code(client.getEntity_code());
                            salaryTran.setClient_code(client.getClient_code());
                            salaryTran.setAcc_year(asmt.getAccYear());
                            salaryTran.setTds_type_code(asmt.getTdsTypeCode());
                            salaryTran.setQuarter_no(Long.valueOf(asmt.getQuarterNo()));
                            salaryTran.setFrom_date(asmt.getQuarterFromDate());
                            salaryTran.setTo_date(asmt.getQuarterToDate());
                            salaryTran.setSession_id(l_client_loginid_seq.toString());
                            salaryTran.setUser_code(user.getUser_code());
                            salaryTran.setLastupdate(new Date());
                            salaryTran.setValidation_client_code(client.getClient_code());
                            salaryTran.setTax_115bac_flag(getSalaryTranLoad().getTax_115bac_flag());

                            utl.generateLog("Salary tran load name: ", salaryTran);
                            try {
                                rowid_seq = salaryTranLoadDAO.saveAndReturnIdentifier(salaryTran);
                                return_view = rowid_seq != null ? "success#" + rowid_seq : "error";
                            } catch (Exception e) {
                                return_view = "error";
                            }
                            if (rowid_seq != null) {
                                try {
                                    ProcSalaryTranTotalRefresh totalRefreshProc = new ProcSalaryTranTotalRefresh();
                                    String execureProcedure = totalRefreshProc.execureProcedure(client.getEntity_code(), client.getClient_code(),
                                            asmt.getAccYear(), Integer.valueOf(asmt.getQuarterNo()), sdf.parse(salaryTran.getSalary_from_date()),
                                            sdf.parse(salaryTran.getSalary_to_date()),
                                            asmt.getTdsTypeCode(), Long.valueOf(rowid_seq.toString()), user.getUser_code(), null);
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    inputStream = new ByteArrayInputStream(return_view.getBytes("UTF-8"));
                    return "ajax_success";
                } else if (getAction().equalsIgnoreCase("edit")) {
                    setAction("update");
                    try {
                        if (getRowid_seq() != null) {
                            SalaryTranLoad salaryTranEdit = salaryTranLoadDAO.getByRowId(getRowid_seq());
                            setSalaryTranLoad(salaryTranEdit);
                        }
                    } catch (Exception e) {
                    }
                    return_view = "edit_entry";
                } else if (getAction().equalsIgnoreCase("sevaarth")) {
                    setAction("update");
                    try {
                        if (getRowid_seq() != null) {
                            SalaryTranLoad salaryTranEdit = salaryTranLoadDAO.getByRowId(getRowid_seq());

                        }
                    } catch (Exception e) {
                    }
                    return_view = "sevaarth";
                } else if (getAction().equalsIgnoreCase("update")) {
                    return_view = "error";
                    try {
                        // UPDATE entry for previous data with rowid seq.
                        SalaryTranLoadSupport salarySupport = new SalaryTranLoadSupport();
                        SalaryTranLoad salaryTranDbObject = salaryTranLoadDAO.getByRowId(getSalaryTranLoad().getRowid_seq());

                        //Setting up all amount fields from form object to the updating object
                        SalaryTranLoad salaryTran = salarySupport.prepareUpdateObject(getSalaryTranLoad(), salaryTranDbObject);
//                        utl.generateLog("salaryTran==" + salaryTran);
                        salaryTran.setEntity_code(client.getEntity_code());
                        salaryTran.setClient_code(client.getClient_code());
                        salaryTran.setAcc_year(asmt.getAccYear());
                        salaryTran.setTds_type_code(asmt.getTdsTypeCode());
                        salaryTran.setQuarter_no(Long.valueOf(asmt.getQuarterNo()));
                        salaryTran.setFrom_date(asmt.getQuarterFromDate());
                        salaryTran.setTo_date(asmt.getQuarterToDate());
                        salaryTran.setSession_id(l_client_loginid_seq.toString());
                        salaryTran.setUser_code(user.getUser_code());
                        salaryTran.setLastupdate(new Date());
                        salaryTran.setValidation_client_code(client.getClient_code());
                        salaryTran.setTax_115bac_flag(getSalaryTranLoad().getTax_115bac_flag());
                        salaryTran.setDeductee_panno(getSalaryTranLoad().getDeductee_panno().toUpperCase());
                        salaryTran.setDeductee_name(getSalaryTranLoad().getDeductee_name().toUpperCase());
                        salaryTran.setDeductee_catg(getSalaryTranLoad().getDeductee_catg());
                        salaryTran.setIdentification_no(!utl.isnull(getSalaryTranLoad().getIdentification_no()) ? getSalaryTranLoad().getIdentification_no().toUpperCase() : "");
                        salaryTran.setDeductee_phoneno(getSalaryTranLoad().getDeductee_phoneno());
                        salaryTran.setDeductee_address(!utl.isnull(getSalaryTranLoad().getDeductee_address()) ? getSalaryTranLoad().getDeductee_address().toUpperCase() : "");
                        salaryTran.setSalary_from_date(getSalaryTranLoad().getSalary_from_date());
                        salaryTran.setSalary_to_date(getSalaryTranLoad().getSalary_to_date());
                        salaryTran.setDeductee_panno_valid(getSalaryTranLoad().getDeductee_panno_valid());
                        salaryTran.setDeductee_panno_verified(getSalaryTranLoad().getDeductee_panno_verified());
                        //utl.generateLog("rowid seq update: " + salaryTran.getRowid_seq());
                        try {
                            boolean updateResult = salaryTranLoadDAO.update(salaryTran);
                            utl.generateLog("Salary entry update status", updateResult);
                            return_view = updateResult ? "success" : "error";
                        } catch (Exception e) {
                            return_view = "error";
                        }
                        inputStream = new ByteArrayInputStream(return_view.getBytes("UTF-8"));
                        return "ajax_success";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                setAction("save");
            }

            // Entry page loading
            ViewEmpCatgDAO viewEmpCatgDAO = factory.getViewEmpCatgDAO();
            empCatgMap = viewEmpCatgDAO.getEmpCatgAsLinkedHashMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
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

    public HashMap<String, String> getEmpCatgMap() {
        return empCatgMap;
    }

    public void setEmpCatgMap(HashMap<String, String> empCatgMap) {
        this.empCatgMap = empCatgMap;
    }

    public SalaryTranLoad getSalaryTranLoad() {
        return salaryTranLoad;
    }

    public void setSalaryTranLoad(SalaryTranLoad salaryTranLoad) {
        this.salaryTranLoad = salaryTranLoad;
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

    public HashMap<String, String> getTaxFlag() {
        return taxFlag;
    }

    public void setTaxFlag(HashMap<String, String> taxFlag) {
        this.taxFlag = taxFlag;
    }

}
