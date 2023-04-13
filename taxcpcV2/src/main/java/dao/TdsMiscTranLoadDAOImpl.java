/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.TdsMiscTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import regular.dashboard.miscTran.CinMiscFilterEntity;

/**
 *
 * @author gaurav.khanzode
 */
public class TdsMiscTranLoadDAOImpl extends GenericHibernateDAO<TdsMiscTranLoad, Serializable> implements TdsMiscTranLoadDAO {

    Util utl;

    public TdsMiscTranLoadDAOImpl() {
        this.utl = new Util();
    }

    @Override
    public TdsMiscTranLoad getByRowId(String rowid_seq) {
        TdsMiscTranLoad object = null;
        try {
            Criteria crit = getSession().createCriteria(TdsMiscTranLoad.class);
            crit.add(Restrictions.eq("rowid_seq", Long.parseLong(rowid_seq)));
            object = (TdsMiscTranLoad) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String getPanStatusName(String pan4thChar) {
        if (pan4thChar.equals("A") || pan4thChar.equals("B")) {
            pan4thChar = "A#B";
        } else if (pan4thChar.equals("C") || pan4thChar.equals("F")) {
            pan4thChar = "C#F";
        }

        Session session = getSession();
        Object uniqueResult = null;

        String qry = "select status_name from view_pan_status s where s.pan_card_type like '" + pan4thChar + "'";

        SQLQuery query = session.createSQLQuery(qry);

        uniqueResult = query.uniqueResult();

        return uniqueResult != null ? uniqueResult.toString() : "";
    }

    @Override
    public int saveMiscCinDetails(CinMiscFilterEntity tdsTranLoadDetails, UserMast user, Assessment asmt, ViewClientMast client) {
        int count = 0;
        SimpleDateFormat dbSaveDate = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat formParseDate = new SimpleDateFormat("dd-MM-yyyy");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        TdsMiscTranLoad tdsTranLoad = new TdsMiscTranLoad();

        Long challan_rowid = null;
        DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
        DbFunctionExecutorAsString func_string = new DbFunctionExecutorAsString();

        String tds_section = "";
        try {
            tds_section = getValueFromOracleFunction(func_string, "select lhs_utility.get_name('GOVT_TDS_CODE','" + tdsTranLoadDetails.getTds_code() + "') from dual");
            tdsTranLoad.setTds_section(tds_section);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean tranLoadSaveFlag = true;
        /**
         * Adding or modifying challan details for tds tran load.
         */
        if ((!utl.isnull(tdsTranLoadDetails.getFormMode()) && tdsTranLoadDetails.getFormMode().equals("addFlag")) && utl.isnull(tdsTranLoadDetails.getBgl_code())) {
            /**
             * Adding new challan details in add mode
             */
            try {
                tdsTranLoadDetails.setSection(tds_section);
                challan_rowid = saveNewChallanDetails(client, asmt, user, session, tdsTranLoadDetails, objDBFunction);
                tranLoadSaveFlag = true;
            } catch (Exception e) {
                tranLoadSaveFlag = false;
                session.getTransaction().rollback();
                e.printStackTrace();
            }
            tdsTranLoad.setTds_challan_rowid_seq(challan_rowid != null ? challan_rowid.toString() : "");

        } else if ((!utl.isnull(tdsTranLoadDetails.getFormMode()) && tdsTranLoadDetails.getFormMode().equals("editFlag")) && !utl.isnull(tdsTranLoadDetails.getBgl_code())) {
            /**
             * Deleting challan details if bgl code changes to blank in edit
             * mode and challan rowid found in tran load
             */
            Long tds_challan_rowid_seq = tdsTranLoadDetails.getTds_challan_rowid_seq();
            if (tds_challan_rowid_seq != null) {
                try {
                    TdsChallanTranLoad challan_entry = (TdsChallanTranLoad) session.get(TdsChallanTranLoad.class, tds_challan_rowid_seq);
                    if (challan_entry != null) {
                        session.delete(challan_entry);
                    }
                    tranLoadSaveFlag = true;
                } catch (Exception e) {
                    tranLoadSaveFlag = false;
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
                tdsTranLoad.setTds_challan_rowid_seq("");
            }
        } else if ((!utl.isnull(tdsTranLoadDetails.getFormMode()) && tdsTranLoadDetails.getFormMode().equals("editFlag")) && utl.isnull(tdsTranLoadDetails.getBgl_code())) {
            /**
             * Updating the details in challan details if bgl not selected and
             * rowid found in tran load
             */
            Long tds_challan_rowid_seq = tdsTranLoadDetails.getTds_challan_rowid_seq();
            if (tds_challan_rowid_seq != null) {
                try {
                    TdsChallanTranLoad challan_entry = (TdsChallanTranLoad) session.get(TdsChallanTranLoad.class, tds_challan_rowid_seq);

                    challan_entry.setBank_bsr_code(tdsTranLoadDetails.getTds_challan_bank_bsr_code());
                    challan_entry.setTds_challan_date(tdsTranLoadDetails.getTds_error_status5());
                    challan_entry.setTds_challan_no(tdsTranLoadDetails.getTds_error_status6());
                    challan_entry.setTds_challan_tds_amt(tdsTranLoadDetails.getTds_error_status7());
                    challan_entry.setInst_no(tdsTranLoadDetails.getInst_no());

                    session.update(challan_entry);
                    tranLoadSaveFlag = true;
                } catch (Exception e) {
                    tranLoadSaveFlag = false;
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
                tdsTranLoad.setTds_challan_rowid_seq(tds_challan_rowid_seq.toString());
            } else {
                try {
                    tdsTranLoadDetails.setSection(tds_section);
                    challan_rowid = saveNewChallanDetails(client, asmt, user, session, tdsTranLoadDetails, objDBFunction);
                    tranLoadSaveFlag = true;
                } catch (Exception e) {
                    tranLoadSaveFlag = false;
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
                tdsTranLoad.setTds_challan_rowid_seq(challan_rowid != null ? challan_rowid.toString() : "");
            }
        } else {
            /**
             * Will save tran load data if no challan required by default.
             */
            tranLoadSaveFlag = true;
        }

        /**
         * Adding or modifying tds tran load details
         */
        if (tranLoadSaveFlag) {
            try {
                if (tdsTranLoadDetails.getRowid_seq() != null) {
                    tdsTranLoad.setRowid_seq(tdsTranLoadDetails.getRowid_seq());
                } else {
                    String rowid_seqQuery = "select TDS_MISC_TRAN_LOAD_ROWID_SEQ.Nextval from dual";
                    Long rowid_seq = objDBFunction.execute_oracle_function_as_integar(rowid_seqQuery);
                    tdsTranLoad.setRowid_seq(rowid_seq);

                }
                String ded_code = "";
                if (tdsTranLoadDetails.getRowid_seq() == null) {
                    try {
                        String ded_code_qry = "select deductee_code_seq.nextval from dual";
                        ded_code = getValueFromOracleFunction(func_string, ded_code_qry);

                        tdsTranLoad.setDeductee_code(ded_code);
                        tdsTranLoad.setDeductee_ref_code1(ded_code);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                tdsTranLoad.setInvoice_date(dbSaveDate.format(formParseDate.parse(tdsTranLoadDetails.getInvoice_date())).toUpperCase());
                tdsTranLoad.setInvoice_no(utl.isnull(tdsTranLoadDetails.getInvoice_no()) ? "" : tdsTranLoadDetails.getInvoice_no().toUpperCase());
                tdsTranLoad.setGst_amt(tdsTranLoadDetails.getGst_amt());
                tdsTranLoad.setTotal_g_b_amt(tdsTranLoadDetails.getTotal_g_b_amt());
                tdsTranLoad.setFlag(tdsTranLoadDetails.getBgl_code());
                tdsTranLoad.setTds_type_code(tdsTranLoadDetails.getTdsTypeCode());
                tdsTranLoad.setMonth(!utl.isnull(tdsTranLoadDetails.getMonth()) ? tdsTranLoadDetails.getMonth().toUpperCase() : "");
                tdsTranLoad.setAcc_year(asmt.getAccYear());
                tdsTranLoad.setAssesment_acc_year(utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear()));
                tdsTranLoad.setQuarter_no(asmt.getQuarterNo());
                tdsTranLoad.setTds_type_code(asmt.getTdsTypeCode());
                tdsTranLoad.setFrom_date(asmt.getQuarterFromDate());
                tdsTranLoad.setTo_date(asmt.getQuarterToDate());
                tdsTranLoad.setClient_code(client.getClient_code());
                tdsTranLoad.setDeductee_name(!utl.isnull(tdsTranLoadDetails.getDeductee_name()) ? tdsTranLoadDetails.getDeductee_name().toUpperCase() : "");

                if (!utl.isnull(tdsTranLoadDetails.getDeductee_panno())) {
                    tdsTranLoad.setDeductee_panno(tdsTranLoadDetails.getDeductee_panno().toUpperCase());
                }

                tdsTranLoad.setTran_amt(getSimpleAmtValue(tdsTranLoadDetails.getTran_amt()));
                tdsTranLoad.setTds_amt(getSimpleAmtValue(tdsTranLoadDetails.getTds_amt()));
                tdsTranLoad.setTds_base_amt(getSimpleAmtValue(tdsTranLoadDetails.getTds_amt()));
                tdsTranLoad.setTds_code(tdsTranLoadDetails.getTds_code());
                tdsTranLoad.setTds_section(tds_section);

                Date temp_date = null;
                try {
                    if (!utl.isnull(tdsTranLoadDetails.getTds_error_status5())) {
                        temp_date = formParseDate.parse(tdsTranLoadDetails.getTds_error_status5());
                        tdsTranLoad.setTds_error_status5(dbSaveDate.format(temp_date).toUpperCase());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                tdsTranLoad.setCertificate_no(!utl.isnull(tdsTranLoadDetails.getCertificate_no()) ? tdsTranLoadDetails.getCertificate_no().toUpperCase() : "");
                tdsTranLoad.setTds_error_status6(tdsTranLoadDetails.getTds_error_status6());
                tdsTranLoad.setTds_error_status7(tdsTranLoadDetails.getTds_error_status7());
                tdsTranLoad.setTin_uin_no(tdsTranLoadDetails.getTin_uin_no());
                tdsTranLoad.setTds_deduct_reason(tdsTranLoadDetails.getTds_deduct_reason());
                tdsTranLoad.setTds_tran_iud_flag("I");
                tdsTranLoad.setTds_challan_bank_bsr_code(tdsTranLoadDetails.getTds_challan_bank_bsr_code());
                tdsTranLoad.setCountry_code(tdsTranLoadDetails.getCountry_code());
                tdsTranLoad.setRate_type(tdsTranLoadDetails.getRate_type());
                tdsTranLoad.setNature_of_remittance(tdsTranLoadDetails.getNature_of_remittance());
                tdsTranLoad.setUser_code(client.getClient_code());
                tdsTranLoad.setData_entry_mode("M");
                tdsTranLoad.setClient_tanno(!utl.isnull(tdsTranLoadDetails.getClient_tanno()) ? tdsTranLoadDetails.getClient_tanno().toUpperCase() : "");
                tdsTranLoad.setApproveddate(null);
                tdsTranLoad.setApprovedby(null);
                tdsTranLoad.setTds_trantype(utl.isnull(tdsTranLoadDetails.getTds_trantype()) ? "" : tdsTranLoadDetails.getTds_trantype().substring(0, 1));
                tdsTranLoad.setCgst_amt(tdsTranLoadDetails.getCgst_amt());
                tdsTranLoad.setSgst_amt(tdsTranLoadDetails.getSgst_amt());
                tdsTranLoad.setIgst_amt(tdsTranLoadDetails.getIgst_amt());

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = formatter.format(date);

                tdsTranLoad.setLastupdate(strDate);
                tdsTranLoad.setEntity_code(client.getEntity_code());
                tdsTranLoad.setFile_doc_code(tdsTranLoadDetails.getBgl_code());

                if (tdsTranLoadDetails.getRowid_seq() == null) {

                    try {
                        String tran_ref_no = getValueFromOracleFunction(func_string, "select TDS_TRAN_LOAD_TRAN_ID_MISC_SEQ.nextval from dual");
                        tdsTranLoad.setTran_ref_no(tran_ref_no);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                tdsTranLoad.setTds_rate(tdsTranLoadDetails.getTds_rate());
                tdsTranLoad.setDeductee_ref_code2(tdsTranLoadDetails.getDeductee_ref_code2());

                try {
                    String verifiqry = "select lhs_utility.is_panno_verified('" + tdsTranLoadDetails.getDeductee_panno() + "') from dual";
                    String pan_verified = getValueFromOracleFunction(func_string, verifiqry);

                    tdsTranLoad.setDeductee_panno_verified(pan_verified);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    String validqry = "select lhs_utility.is_valid_panno('" + tdsTranLoadDetails.getDeductee_panno() + "') from dual";
                    String pan_valid = getValueFromOracleFunction(func_string, validqry);

                    tdsTranLoad.setDeductee_panno_valid(pan_valid);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                session.saveOrUpdate(tdsTranLoad);
                count = 1;
                session.getTransaction().commit();
            } catch (Exception ex) {
                count = 0;
                session.getTransaction().rollback();
                ex.printStackTrace();
            } finally {
                if (session.isOpen()) {
                    session.evict(tdsTranLoad);
                    session.close();
                }
            }
        }
        return count;
    }

    private String getValueFromOracleFunction(DbFunctionExecutorAsString func_string, String function_str) {
        String return_value = "";
        try {
            return_value = func_string.execute_oracle_function_as_string(function_str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_value;
    }//end

    private String getSimpleAmtValue(String amt) {
        String returnVal = "";
        if (!utl.isnull(amt)) {
            try {
                returnVal = amt.replaceAll(",", "");
            } catch (Exception e) {
            }
        }
        return returnVal;
    }//end method

    private Long saveNewChallanDetails(ViewClientMast client, Assessment asmt, UserMast user, Session session, CinMiscFilterEntity tdsTranLoadDetails,
            DbFunctionExecutorAsIntegar objDBFunction) throws Exception {

        Long return_rowid = null;
        try {
            String rowid_seqQuery = "select tds_challan_rowid.Nextval from dual";
            Long rowid_seq = objDBFunction.execute_oracle_function_as_integar(rowid_seqQuery);

            TdsChallanTranLoad tdschallantran = new TdsChallanTranLoad();
            tdschallantran.setRowid_seq(rowid_seq);
            tdschallantran.setUser_code(user.getUser_code());
            tdschallantran.setInst_no(tdsTranLoadDetails.getInst_no());
            tdschallantran.setLastupdate(new Date());
            tdschallantran.setData_entry_mode("M");
            tdschallantran.setBank_bsr_code(tdsTranLoadDetails.getTds_challan_bank_bsr_code());
            tdschallantran.setTds_challan_date(tdsTranLoadDetails.getTds_error_status5());
            tdschallantran.setTds_challan_no(tdsTranLoadDetails.getTds_error_status6());
            tdschallantran.setTds_challan_tds_amt(tdsTranLoadDetails.getTds_error_status7());

            tdschallantran.setEntity_code(client.getEntity_code());
            tdschallantran.setClient_code(client.getClient_code());
            tdschallantran.setAcc_year(asmt.getAccYear());
            tdschallantran.setAssesment_acc_year(utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear()));
            tdschallantran.setQuarter_no(asmt.getQuarterNo());
            tdschallantran.setFrom_date(asmt.getQuarterFromDate());
            tdschallantran.setTo_date(asmt.getQuarterToDate());
            tdschallantran.setMonth(tdsTranLoadDetails.getMonth());
            tdschallantran.setFile_doc_code(tdsTranLoadDetails.getBgl_code());
            tdschallantran.setTds_section(tdsTranLoadDetails.getSection());
            tdschallantran.setTds_type_code(asmt.getTdsTypeCode());
            tdschallantran.setTds_code(tdsTranLoadDetails.getTds_code());

            return_rowid = (Long) session.save(tdschallantran);
        } catch (Exception e) {
            return_rowid = null;
            throw new Exception(e);
        }
        return return_rowid;
    }//end method

    @Override
    public int deleteTdsTranLoad(String rowid_seq) {        // this logic is not working properly               
        int result = 0;
        int challanResult = 0;
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            //Remove a persistent object
            TdsMiscTranLoad tds = (TdsMiscTranLoad) session.get(TdsMiscTranLoad.class, Long.valueOf(rowid_seq));
            String tds_challan_rowid_seq = tds.getTds_challan_rowid_seq();
            if (!utl.isnull(tds_challan_rowid_seq)) {
                TdsChallanTranLoad tdsChallan = (TdsChallanTranLoad) session.get(TdsChallanTranLoad.class, Long.valueOf(tds_challan_rowid_seq));
                if (tdsChallan != null) {
                    session.delete(tdsChallan);
                    challanResult = 1;
                    if (challanResult == 1) {
                        if (tds != null) {
                            session.delete(tds);
                            result = 1;
                            transaction.commit();
                        } else {
                            transaction.rollback();
                        }
                    } else {
                        transaction.rollback();
                    }
                }
            } else {
                if (tds != null) {
                    session.delete(tds);
                    result = 1;
                }
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                result = 0;
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

}
