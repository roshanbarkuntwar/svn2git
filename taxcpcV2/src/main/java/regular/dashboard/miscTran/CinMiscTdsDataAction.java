/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsChallanTranLoadDAO;
import dao.TdsMiscTranLoadDAO;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.TdsMiscTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author pratik.barahate
 */
public class CinMiscTdsDataAction extends ActionSupport implements SessionAware {

    Util utl;
    private Map<String, Object> session;
    private InputStream inputStream;
    private LinkedHashMap<String, String> selectSection;
    private LinkedHashMap<String, String> selectNatureOfRemList;
    private LinkedHashMap<String, String> selectRateList;
    private LinkedHashMap<String, String> selectCountryList;
    private LinkedHashMap<String, String> selectSectionList;
    private LinkedHashMap<String, String> selectTdsDeductReasonList;
    private LinkedHashMap<String, String> bglCodeNameList;

    private String natureRem;
    private String rateType;
    private String country;
    private String section;
    private String tdsDeductReason;
    private String cert_no;
    private String nature_of_rem;
    private String country_code;
    private Double total_paid_amt;
    private Double payment_amt;
    private String rate_type;

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getNature_of_rem() {
        return nature_of_rem;
    }

    public void setNature_of_rem(String nature_of_rem) {
        this.nature_of_rem = nature_of_rem;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Double getTotal_paid_amt() {
        return total_paid_amt;
    }

    public void setTotal_paid_amt(Double total_paid_amt) {
        this.total_paid_amt = total_paid_amt;
    }

    public Double getPayment_amt() {
        return payment_amt;
    }

    public void setPayment_amt(Double payment_amt) {
        this.payment_amt = payment_amt;
    }

    public LinkedHashMap<String, String> getSelectNatureOfRemList() {
        return selectNatureOfRemList;
    }

    public LinkedHashMap<String, String> getBglCodeNameList() {
        return bglCodeNameList;
    }

    public void setBglCodeNameList(LinkedHashMap<String, String> bglCodeNameList) {
        this.bglCodeNameList = bglCodeNameList;
    }

    public void setSelectNatureOfRemList(LinkedHashMap<String, String> selectNatureOfRemList) {
        this.selectNatureOfRemList = selectNatureOfRemList;
    }

    public LinkedHashMap<String, String> getSelectTdsDeductReasonList() {
        return selectTdsDeductReasonList;
    }

    public void setSelectTdsDeductReasonList(LinkedHashMap<String, String> selectTdsDeductReasonList) {
        this.selectTdsDeductReasonList = selectTdsDeductReasonList;
    }

    public String getTdsDeductReason() {
        return tdsDeductReason;
    }

    public void setTdsDeductReason(String tdsDeductReason) {
        this.tdsDeductReason = tdsDeductReason;
    }

    public LinkedHashMap<String, String> getSelectRateList() {
        return selectRateList;
    }

    public void setSelectRateList(LinkedHashMap<String, String> selectRateList) {
        this.selectRateList = selectRateList;
    }

    public LinkedHashMap<String, String> getSelectCountryList() {
        return selectCountryList;
    }

    public void setSelectCountryList(LinkedHashMap<String, String> selectCountryList) {
        this.selectCountryList = selectCountryList;
    }

    public LinkedHashMap<String, String> getSelectSectionList() {
        return selectSectionList;
    }

    public void setSelectSectionList(LinkedHashMap<String, String> selectSectionList) {
        this.selectSectionList = selectSectionList;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNatureRem() {
        return natureRem;
    }

    public void setNatureRem(String natureRem) {
        this.natureRem = natureRem;
    }

    private CinMiscFilterEntity miscData;
    private TdsMiscTranLoad tdsMiscTranLoad;
    private String accYear;
    private String month;
    private String pan_status;
    private String bglCode;
    private String action;
    private String tdsTypeCode;
    private String rowid_seq;
    private String branchCode;
    private String errorMessage;
    private String actionFlag;
    private String panno;
    private String tran_ref_date;
    private String edit_bgl_code;
    private String edit_section_code;
    private String edit_tds_trantype;
    private String month_number;

    public String getMonth_number() {
        return month_number;
    }

    public void setMonth_number(String month_number) {
        this.month_number = month_number;
    }

    private TdsChallanTranLoad challanTranLoad;

    public TdsChallanTranLoad getChallanTranLoad() {
        return challanTranLoad;
    }

    public void setChallanTranLoad(TdsChallanTranLoad challanTranLoad) {
        this.challanTranLoad = challanTranLoad;
    }

    public String getEdit_tds_trantype() {
        return edit_tds_trantype;
    }

    public void setEdit_tds_trantype(String edit_tds_trantype) {
        this.edit_tds_trantype = edit_tds_trantype;
    }

    public String getEdit_section_code() {
        return edit_section_code;
    }

    public void setEdit_section_code(String edit_section_code) {
        this.edit_section_code = edit_section_code;
    }

    public String getEdit_bgl_code() {
        return edit_bgl_code;
    }

    public void setEdit_bgl_code(String edit_bgl_code) {
        this.edit_bgl_code = edit_bgl_code;
    }

    public String getPan_status() {
        return pan_status;
    }

    public void setPan_status(String pan_status) {
        this.pan_status = pan_status;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBglCode() {
        return bglCode;
    }

    public void setBglCode(String bglCode) {
        this.bglCode = bglCode;
    }

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public String getAccYear() {
        return accYear;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CinMiscFilterEntity getMiscData() {
        return miscData;
    }

    public void setMiscData(CinMiscFilterEntity miscData) {
        this.miscData = miscData;
    }

    public ViewClientMast getClientMast() {
        return clientMast;
    }

    public void setClientMast(ViewClientMast clientMast) {
        this.clientMast = clientMast;
    }

    private ViewClientMast clientMast;

    @Override
    public String execute() throws Exception {
        String return_view = "success";
        session.put("ACTIVE_TAB", "dashboard");
        setActionFlag("addFlag");

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

        clientMast = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");

        setTdsTypeCode(asmt.getTdsTypeCode());

        TdsTranLoadDAO tdsTranLoadObj = factory.getTdsTranLoadDAO();
        LinkedHashMap<String, String> remittanceNatureList = tdsTranLoadObj.getRemittanceNature();
        setSelectNatureOfRemList(remittanceNatureList);

        LinkedHashMap<String, String> tdsRateTypeList = tdsTranLoadObj.getTdsRateType();
        setSelectRateList(tdsRateTypeList);

        LinkedHashMap<String, String> countryCodeList = tdsTranLoadObj.getCountryCode();
        setSelectCountryList(countryCodeList);

        LinkedHashMap<String, String> tdsDeductReasonList = tdsTranLoadObj.getTdsDeductReason();
        setSelectTdsDeductReasonList(tdsDeductReasonList);

        try {
            setBglCodeNameList(tdsTranLoadObj.getBGLCodeNameList(asmt.getTdsTypeCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> tdsSectionValuesList = tdsTranLoadObj.getTdsSectionValues(asmt.getTdsTypeCode());
        setSelectSectionList(tdsSectionValuesList);

        if (asmt.getTdsTypeCode().equalsIgnoreCase("26Q") || asmt.getTdsTypeCode().equalsIgnoreCase("27Q")) {
            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("isValidDeductDate")) {
                /**
                 * Not in use since validation is removed from invoice date
                 * field from misc form. Code can be deleted.
                 */
//                String returnResult = "monthError";
//
//                SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
//                Date temp;
//                try {
//                    //temp = parser.parse(getTran_ref_date());
//                    String[] tran_dates = getTran_ref_date().split("-");
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.set(Integer.valueOf(tran_dates[2]), Integer.valueOf(tran_dates[1]) - 1, Integer.valueOf(tran_dates[0]));
//                    temp = calendar.getTime();
//
//                    parser = new SimpleDateFormat("MMM");
//                    String monthName = parser.format(temp);
//
//                    if (monthName.toUpperCase().equals(getMonth())) {
//                        returnResult = "refDateError";
//
//                        String accYear1 = asmt.getAccYear();
//                        String fromYear = "20" + (asmt.getQuarterNo().equals("4") ? accYear1.split("-")[1] : accYear1.split("-")[0]);
//                        //String toYear = "20" + accYear1.split("-")[1];
//                        Calendar instance = Calendar.getInstance();
//                        instance.set(Integer.valueOf(fromYear), Integer.valueOf(getMonth_number()) - 1, 1);
//
//                        Date fromDate = instance.getTime();
//                        int actualMaximum = instance.getActualMaximum(Calendar.DATE);
//                        instance.set(Integer.valueOf(fromYear), Integer.valueOf(getMonth_number()) - 1, actualMaximum);
//                        Date toDate = instance.getTime();
//
//                        if (temp.equals(fromDate) || temp.equals(toDate)) {
//                            returnResult = "refDateSuccess";
//                            System.out.println("The date " + temp + " is within the month " + month);
//                        } else if (temp.after(fromDate) && temp.before(toDate)) {
//                            returnResult = "refDateSuccess";
//
//                            System.out.println("The date " + temp + " is within the month " + month);
//                        } else {
//                            System.out.println("The date " + temp + " is not within the month " + month);
//
//                        }
//
//                    }
//                } catch (Exception e) {
//                    returnResult = "monthError";
//                }
//                System.out.println("returnResult=" + returnResult);
//                inputStream = new ByteArrayInputStream(returnResult.getBytes("UTF-8"));

            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getMonth")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                String client_code = clientMast.getClient_code();
                List<String> monthList = tdsTranLoadDAO.getMonthFormYear(getAccYear(), client_code);
                StringBuilder sb = new StringBuilder();
                sb.append("<option value=\"\">Select Month</option>");
                for (String string : monthList) {
                    sb.append("<option value=\"" + string + "\">" + string + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getSection")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> tdsSectionValues = tdsTranLoadDAO.getTdsSectionValues(getTdsTypeCode());
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : tdsSectionValues.entrySet()) {
                    sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } //new
            else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getBGLSectionRate")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                StringBuilder sb = new StringBuilder();

                if (!utl.isnull(getBglCode())) {
                    //new section

                    LinkedHashMap<String, String> bglSectionValues = tdsTranLoadDAO.getBGLSectionValues(getBglCode(), asmt.getTdsTypeCode());
                    for (Map.Entry<String, String> entry : bglSectionValues.entrySet()) {
                        sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                    }

                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getRateAndTotalAmt")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                String rateAmt = "error";
                if (!utl.isnull(getPanno())) {
                    TDSRateBean tdsRateBean = new TDSRateBean();
                    tdsRateBean.setCertificate_no(getCert_no());
                    tdsRateBean.setNature_of_rem(getNature_of_rem());
                    tdsRateBean.setCountry_code(getCountry_code());
                    tdsRateBean.setTotal_paid_amt(getTotal_paid_amt() != null ? getTotal_paid_amt() : 0);
                    tdsRateBean.setPayment_amt(getPayment_amt() != null ? getPayment_amt() : 0);
                    tdsRateBean.setTds_section(getSection());
                    tdsRateBean.setTran_date(getTran_ref_date());
                    tdsRateBean.setDeductee_panno(getPanno());
                    tdsRateBean.setBgl_code(getBglCode());
                    tdsRateBean.setRowid_seq(getRowid_seq());
                    tdsRateBean.setRate_type(getRate_type());
                    try {
                        rateAmt = tdsTranLoadDAO.getRateAndTotalAmount(asmt, clientMast, tdsRateBean);
                    } catch (Exception e) {
                        rateAmt = "error";
                    }
                }
                inputStream = new ByteArrayInputStream(rateAmt.getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getPanStatusName")) {
                String resturnStatus = "";
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                if (!utl.isnull(getPanno())) {
                    String pan4thChar = getPanno().substring(3, 4);
                    resturnStatus = tdsTranLoadDAO.getPanStatusName(pan4thChar);
                }
                inputStream = new ByteArrayInputStream(resturnStatus.getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getTdsDeductReason")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> tdsDeductReason = tdsTranLoadDAO.getTdsDeductReason();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : tdsDeductReason.entrySet()) {
                    sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getRemittanceNature")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> remittanceNature = tdsTranLoadDAO.getRemittanceNature();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : remittanceNature.entrySet()) {
                    sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getTdsRateType")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> tdsRateType = tdsTranLoadDAO.getTdsRateType();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : tdsRateType.entrySet()) {
                    sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getCountryCode")) {
                TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                LinkedHashMap<String, String> countryCode = tdsTranLoadDAO.getCountryCode();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : countryCode.entrySet()) {
                    sb.append("<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>");
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("saveMiscDetails")) {
                int count = 0;
                String result = "failure";

                try {
                    TdsMiscTranLoadDAO tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();

                    count = tdsTranLoadDAO.saveMiscCinDetails(miscData, user, asmt, clientMast); //check
                    if (count > 0) {
                        result = "success";
                    }
                } catch (Exception ex) {
                    result = "failure";
                    ex.printStackTrace();
                }
                inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("edit")) {
                setActionFlag("editFlag");

                SimpleDateFormat dbDateParse = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat formDateformat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    if (getRowid_seq() != null) {
                        TdsMiscTranLoadDAO tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();
                        TdsChallanTranLoadDAO tdsChallan = factory.getTdsChallanTranLoadDAO();

                        TdsMiscTranLoad tdsTranEdit = tdsTranLoadDAO.getByRowId(getRowid_seq());
                        Date tempDate = null;
                        try {
                            tempDate = dbDateParse.parse(tdsTranEdit.getInvoice_date());
                        } catch (Exception e) {
                            try {
                                tempDate = formDateformat.parse(tdsTranEdit.getInvoice_date());
                            } catch (Exception ex) {
                            }
                        }
                        tdsTranEdit.setInvoice_date(formDateformat.format(tempDate));

                        setMonth(tdsTranEdit.getMonth());
                        setNatureRem(tdsTranEdit.getNature_of_remittance());
                        setRateType(tdsTranEdit.getRate_type());
                        setCountry(tdsTranEdit.getCountry_code());
                        setTdsDeductReason(tdsTranEdit.getTds_deduct_reason());
                        setSection(tdsTranEdit.getTds_code());

                        String pan4thChar = tdsTranEdit.getDeductee_panno().substring(3, 4);
                        String panStatus = tdsTranLoadDAO.getPanStatusName(pan4thChar);
                        setPan_status(panStatus);
                        setEdit_bgl_code(tdsTranEdit.getFlag());
                        setEdit_section_code(tdsTranEdit.getTds_code());
                        setEdit_tds_trantype(tdsTranEdit.getTds_trantype());

                        try {
                            if (!utl.isnull(tdsTranEdit.getTds_challan_rowid_seq())) {
                                TdsChallanTranLoad challan = tdsChallan.readChallanBySequenceID(Long.valueOf(tdsTranEdit.getTds_challan_rowid_seq()));
                                setChallanTranLoad(challan);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        setTdsMiscTranLoad(tdsTranEdit);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return_view = "edit_entry";
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("delete")) {

                try {
                    if (getRowid_seq() != null) {
                        TdsMiscTranLoadDAO tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();
                        TdsMiscTranLoad tdsTranEdit = tdsTranLoadDAO.getByRowId(getRowid_seq());
                        setAccYear(tdsTranEdit.getAcc_year());
                        setMonth(tdsTranEdit.getMonth());
                        setNatureRem(tdsTranEdit.getNature_of_remittance());
                        setRateType(tdsTranEdit.getRate_type());
                        setCountry(tdsTranEdit.getCountry_code());
                        setTdsDeductReason(tdsTranEdit.getTds_deduct_reason());
                        setSection(tdsTranEdit.getTds_code());

                        setTdsMiscTranLoad(tdsTranEdit);
                        setActionFlag("deleteFlag");

                    }
                } catch (Exception e) {
                }
                return_view = "edit_entry";
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("deleteMiscellaneousCIN")) {
                String result = "failure";
                int count = 0;
                try {
                    if (getRowid_seq() != null) {
                        TdsMiscTranLoadDAO tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();
                       // TdsMiscTranLoad tdsObj = tdsTranLoadDAO.readById(Long.parseLong(getRowid_seq()), true);
                        TdsMiscTranLoad tdsObj = tdsTranLoadDAO.getByRowId(getRowid_seq());
                        if (tdsObj != null) {
                            tdsObj.setRowid_seq(Long.parseLong(getRowid_seq()));
                        }
                        tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();
                        boolean deleteTdsObj = tdsTranLoadDAO.delete(tdsObj);
//                         count = tdsTranLoadDAO.deleteTdsTranLoad(getRowid_seq());   // this old delete logic is commented new one is used
//                        if (count > 0) {
//                            result = "success";
//                        }
                        if (deleteTdsObj) {
                            result = "success";
                        }
                        inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
                    }
                } catch (Exception e) {
                }
            } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("updateMiscDetails")) {
                String result = "failure";
                int count = 0;
                try {
                    TdsMiscTranLoadDAO tdsTranLoadDAO = factory.getTdsMiscTranLoadDAO();
                    miscData.setRowid_seq(Long.valueOf(getRowid_seq()));
                    count = tdsTranLoadDAO.saveMiscCinDetails(miscData, user, asmt, clientMast);
                    if (count > 0) {
                        result = "success";
                    }
                    inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));

                } catch (Exception e) {
                }
            } else {
            }
        } else {
            setErrorMessage("Misc Details is only applicable in 26Q & 27Q(Misc)");
            return_view = "error";
        }

        return return_view;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTdsTypeCode() {
        return tdsTypeCode;
    }

    public void setTdsTypeCode(String tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
    }

    public String getTran_ref_date() {
        return tran_ref_date;
    }

    public void setTran_ref_date(String tran_ref_date) {
        this.tran_ref_date = tran_ref_date;
    }

    public TdsMiscTranLoad getTdsMiscTranLoad() {
        return tdsMiscTranLoad;
    }

    public void setTdsMiscTranLoad(TdsMiscTranLoad tdsMiscTranLoad) {
        this.tdsMiscTranLoad = tdsMiscTranLoad;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public CinMiscTdsDataAction() {
        utl = new Util();

        tdsMiscTranLoad = new TdsMiscTranLoad();
        bglCodeNameList = new LinkedHashMap<>();
        selectNatureOfRemList = new LinkedHashMap<>();

        selectSectionList = new LinkedHashMap<>();
        selectSectionList.put("", "--Select Section--");

        challanTranLoad = new TdsChallanTranLoad();

    }
}
