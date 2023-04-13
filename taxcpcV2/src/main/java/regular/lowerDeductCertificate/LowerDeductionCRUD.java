/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsSplRateMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsSplRateMastId;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class LowerDeductionCRUD extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnView = "success";
        String returnMsg = "error";
        try {
            if (!utl.isnull(getAction())) {

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                TdsSplRateMastDAO tdsSplRateMastDAO;
                if (getAction().equalsIgnoreCase("save")) {
                    ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                    Assessment asmt = (Assessment) session.get("ASSESSMENT");
                    try {
                        boolean save = false;
                        if (getTdsSplRateMast() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            getTdsSplRateMast().setLastupdate(new Date());
                            getTdsSplRateMast().setCertificate_no(getTdsSplRateMast().getCertificate_no().toUpperCase());
                            getTdsSplRateMast().setDeductee_panno(getTdsSplRateMast().getDeductee_panno().toUpperCase());

                            getTdsSplRateMast().setClient_code(viewClientMast.getClient_code());

                            getTdsSplRateMast().setEntity_code(viewClientMast.getEntity_code());
                            getTdsSplRateMast().setAcc_year(asmt.getAccYear());
                            getTdsSplRateMast().setTds_type_code(asmt.getTdsTypeCode());

                            tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                            save = tdsSplRateMastDAO.save(getTdsSplRateMast());

                            if (save) {
                                session.put("ERRORCLASS", MessageType.successMessage);
                                session.put("TDSSPLRATERSLTMSG", "Record Saved Successfully");
                                returnMsg = "success";
                            }
                            inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
                            returnView = "input_success";
                        }
                    } catch (Exception e) {
                        addActionError("Some Error Occured");
                        returnView = "input_return";
                    }
                } else if (getAction().equalsIgnoreCase("delete")) {
                    if (getTdsSplRateMastId() != null) {
                        factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                        tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                        TdsSplRateMast tm = tdsSplRateMastDAO.readTdsSplRateById(getTdsSplRateMastId());
                        if (tm != null) {
                            tdsSplRateMastDAO = factory.getTdsSplRateMastDAO();
                            boolean delete = tdsSplRateMastDAO.delete(tm);
                            if (delete) {
                                session.put("ERRORCLASS", MessageType.successMessage);
                                session.put("TDSSPLRATERSLTMSG", "Record Deleted Successfully");
                                returnMsg = "success";
                            }
                        }
                        returnView = "input_success";
                        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
                    }
                } else if (getAction().equalsIgnoreCase("update")) {
                    if (getTdsSplRateMast() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String whereClauseCertificateNo = getTdsSplRateMast().getCertificate_no();
                        if (!utl.isnull(getHiddenCertificateNo())) {
                            whereClauseCertificateNo = getHiddenCertificateNo();
                        }

                        String updateQuery
                                = "update tds_spl_rate_mast m\n"
                                + "   set m.certificate_valid_upto  = to_date('" + sdf.format(getTdsSplRateMast().getCertificate_valid_upto()) + "','dd-mm-rrrr'),\n"
                                + "       m.tds_rate        = '" + (utl.isnull(getTdsSplRateMast().getTds_rate()) ? "" : getTdsSplRateMast().getTds_rate()) + "',\n"
                                + "       m.surcharge_rate  = '" + (utl.isnull(getTdsSplRateMast().getSurcharge_rate()) ? "" : getTdsSplRateMast().getSurcharge_rate()) + "',\n"
                                + "       m.cess_rate       = '" + (utl.isnull(getTdsSplRateMast().getCess_rate()) ? "" : getTdsSplRateMast().getCess_rate()) + "',\n"
                                + "       m.tds_limit_amt   = '" + (utl.isnull(getTdsSplRateMast().getTds_limit_amt()) ? "" : getTdsSplRateMast().getTds_limit_amt()) + "',\n"
                                + "       m.certificate_no  = '" + (utl.isnull(getTdsSplRateMast().getCertificate_no()) ? "" : getTdsSplRateMast().getCertificate_no().toUpperCase()) + "',\n"
                                + "       m.lastupdate      = sysdate,\n"
                                + "       m.hecess_rate     = '" + (utl.isnull(getTdsSplRateMast().getHecess_rate()) ? "" : getTdsSplRateMast().getHecess_rate()) + "',\n"
                                + "       m.amount_consumed = '" + (utl.isnull(getTdsSplRateMast().getAmount_consumed()) ? "" : getTdsSplRateMast().getAmount_consumed()) + "'\n"
                                + " where m.tds_code = '" + getTdsSplRateMast().getTds_code() + "'\n"
                                + "   and m.certificate_no = '" + whereClauseCertificateNo.toUpperCase() + "'";
                        DbFunctionExecutorAsString executorAsString = new DbFunctionExecutorAsString();
                        boolean updateRes = executorAsString.execute_oracle_update_function_as_string(updateQuery);

                        if (updateRes) {
                            session.put("ERRORCLASS", MessageType.successMessage);
                            session.put("TDSSPLRATERSLTMSG", "Record Updated Successfully");
                            returnMsg = "success";
                        }
                    }
                    inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
                    returnView = "input_success";

                }

            }
        } catch (Exception e) {
            e.printStackTrace();

            returnView = "input_return";
        }
        return returnView;
    }

    public LowerDeductionCRUD() {
        utl = new Util();
    }
    Util utl;
    private String action;
    private TdsSplRateMast tdsSplRateMast;
    private Map<String, Object> session;
    private InputStream inputStream;
    private TdsSplRateMastId tdsSplRateMastId;
    private String hiddenCertificateNo;

    public TdsSplRateMastId getTdsSplRateMastId() {
        return tdsSplRateMastId;
    }

    public void setTdsSplRateMastId(TdsSplRateMastId tdsSplRateMastId) {
        this.tdsSplRateMastId = tdsSplRateMastId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TdsSplRateMast getTdsSplRateMast() {
        return tdsSplRateMast;
    }

    public void setTdsSplRateMast(TdsSplRateMast tdsSplRateMast) {
        this.tdsSplRateMast = tdsSplRateMast;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getHiddenCertificateNo() {
        return hiddenCertificateNo;
    }

    public void setHiddenCertificateNo(String hiddenCertificateNo) {
        this.hiddenCertificateNo = hiddenCertificateNo;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
