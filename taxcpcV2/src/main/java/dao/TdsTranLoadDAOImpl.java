/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import regular.dashboard.miscTran.CinMiscFilterEntity;
import regular.dashboard.miscTran.TDSRateBean;

/**
 *
 * @author ayushi.jain
 */
public class TdsTranLoadDAOImpl extends GenericHibernateDAO<TdsTranLoad, Serializable> implements TdsTranLoadDAO {

    Util utl;

    public TdsTranLoadDAOImpl() {
        this.utl = new Util();
    }

    @Override
    public List<TdsTranLoad> readDeducteesForChallan(String challanRowSeq) {
        Criterion crit = Restrictions.eq("tds_challan_rowid_seq", challanRowSeq);
        List<TdsTranLoad> readByCriteria = readByCriteria(crit);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null;
    }//end method

    @Override
    public List<TdsTranLoad> readTdsChallanAmount(String mChallanRowSeq, String l_entity_code, String l_client_code, String acc_year, String quarterNo, String tds_type_code) {
        List<TdsTranLoad> tdsTranList;System.out.println("mChallanRowSeq--"+mChallanRowSeq);
        try {
            Criteria crit = getSession().createCriteria(TdsTranLoad.class);
            crit.add(Restrictions.eq("tds_challan_rowid_seq", mChallanRowSeq));
            crit.add(Restrictions.eq("entity_code", l_entity_code));
            crit.add(Restrictions.eq("client_code", l_client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            tdsTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsTranList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsTranList != null && tdsTranList.size() > 0 ? tdsTranList : null);
    }//end method

    @Override
    public TdsTranLoad getObjForLowDeduCertifiAlloca(TdsTranLoad entity) {
        List<TdsTranLoad> tdsTranList = null;
        try {
            Criteria crit = getSession().createCriteria(TdsTranLoad.class);

            crit.add(Restrictions.eq("entity_code", entity.getEntity_code()));
            crit.add(Restrictions.eq("client_code", entity.getClient_code()));
            crit.add(Restrictions.eq("acc_year", entity.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", entity.getQuarter_no()));
            crit.add(Restrictions.eq("tds_type_code", entity.getTds_type_code()));
            crit.add(Restrictions.eq("deductee_panno", entity.getDeductee_panno()));
            crit.add(Restrictions.eq("tds_code", entity.getTds_code()));
            crit.add(Restrictions.eq("tds_deduct_reason", "A"));
            crit.add(Restrictions.eq("certificate_no", entity.getCertificate_no()));
            tdsTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tdsTranList = null;
            getSession().getTransaction().rollback();
        }
        return (tdsTranList != null && tdsTranList.size() > 0 ? tdsTranList.get(0) : null);
    }//end method

    @Override
    public Long getDeducteesCountForChallan(String challanRowSeq) {
        Long rowcount = 0l;
        try {
            Criteria crit = getSession().createCriteria(TdsTranLoad.class);
            crit.add(Restrictions.eq("tds_challan_rowid_seq", challanRowSeq));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public TdsTranLoad readTDSBySequenceID(Long rowid_seq) {
        Criterion cltcode = Restrictions.eq("rowid_seq", rowid_seq);
        List<TdsTranLoad> uselist = readByCriteria(cltcode);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }//end method

    @Override
    public List<TdsTranLoad> readProcessDetails(String entityCode, String clientCode, Assessment asmt) {
        Criteria crit = getSession().createCriteria(TdsTranLoad.class);

        crit.add(Restrictions.eq("entity_code", entityCode));
        crit.add(Restrictions.eq("client_code", clientCode));
        crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
        crit.add(Restrictions.eq("quarter_no", asmt.getQuarterNo()));
        crit.add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()));

        List<TdsTranLoad> uselist = crit.list();
        return (uselist != null && uselist.size() > 0) ? uselist : null;
    }//end method

    @Override
    public List<String> getMonthFormYear(String accYear, String clientCode) {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT month FROM lhssys_misc_config where year = '" + accYear + "' and flag='A'");
        List<String> rows = query.list();
        return rows;
    }

    @Override
    public LinkedHashMap<String, String> getTdsSectionValues(String tdsTypeCode) {

        LinkedHashMap<String, String> tdsMap = new LinkedHashMap<String, String>();
        Session session = getSession();
        String qry = "";
        if (!utl.isnull(tdsTypeCode)) {
            qry = "select t.tds_code, t.tds_name, t.remark from tds_mast t where tds_type_code='" + tdsTypeCode + "' and closed_date is null";
        } else {
            qry = "select t.tds_code, t.tds_name, t.remark from tds_mast t";
        }
        SQLQuery query = session.createSQLQuery(qry);
        List<Object[]> resultList = query.list();
        tdsMap.put("", "Select Section");
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                tdsMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]) + " (" + String.valueOf(obj[2]) + ")");
            }
        }
        return tdsMap;
    }

    @Override
    public LinkedHashMap<String, String> getTdsDeductReason() {
        LinkedHashMap<String, String> tdsDeductReason = new LinkedHashMap<String, String>();
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("select * from view_tds_deduct_reason_misc");
        List<Object[]> resultList = query.list();
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                tdsDeductReason.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
            }
        }
        return tdsDeductReason;
    }

    @Override
    public LinkedHashMap<String, String> getRemittanceNature() {
        LinkedHashMap<String, String> remittanceNature = new LinkedHashMap<String, String>();
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("select * from view_remittance_nature");
        List<Object[]> resultList = query.list();

        remittanceNature.put("", "Please Select");
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                remittanceNature.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
            }
        }
        return remittanceNature;
    }

    @Override
    public LinkedHashMap<String, String> getTdsRateType() {
        LinkedHashMap<String, String> rateType = new LinkedHashMap<String, String>();
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("select * from view_tds_rate_type");
        List<Object[]> resultList = query.list();

        rateType.put("", "Please Select");
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                rateType.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
            }
        }
        return rateType;
    }

    @Override
    public LinkedHashMap<String, String> getCountryCode() {
        LinkedHashMap<String, String> countryCode = new LinkedHashMap<String, String>();
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("select * from country_mast ORDER BY COUNTRY_NAME ASC");
        List<Object[]> resultList = query.list();

        countryCode.put("", "Please Select");
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                countryCode.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
            }
        }
        return countryCode;
    }

    @Override
    public Long getMiscCinDataCount(CinMiscFilterEntity filters, ViewClientMast clientMast, Assessment asmt, Util utl) {
        Long count = 0L;
        Session session = getSession();

        try {
            String sql
                    = "select count(*)\n"
                    + " from Tds_misc_Tran_Load t , client_mast c\n"
                    + "where t.entity_code =  c.entity_code\n"
                    + " and  t.client_code = c.client_code\n"
                    + " and t.entity_code = '" + clientMast.getEntity_code() + "'\n"
                    + " and (c.client_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "      c.g_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.sg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.ssg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.sssg_parent_code= '" + clientMast.getClient_code() + "' )\n"
                    + "and  t.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "and t.data_entry_mode ='M'\n"
                    + getWhereClause(filters);

            utl.generateSpecialLog("Misc browse page Count Query", sql);

            SQLQuery query = session.createSQLQuery(sql);
            Object obj = query.uniqueResult();
            count = Long.parseLong(obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public String getWhereClause(CinMiscFilterEntity filters) {
        String whrClause = "";
        if (filters != null) {
            if (!utl.isnull(filters.getDeductee_panno())) {
                whrClause += "and t.deductee_panno = '" + filters.getDeductee_panno() + "'\n";
            }
            if (!utl.isnull(filters.getDeductee_name())) {
                whrClause += "and lower(t.deductee_name) like lower('%" + filters.getDeductee_name().trim() + "%')\n";
            }
            if (!utl.isnull(filters.getBgl_code())) {
                whrClause += "and t.flag = '" + filters.getBgl_code() + "'\n";
            }
            if (!utl.isnull(filters.getTds_code())) {
                whrClause += "and t.tds_code = '" + filters.getTds_code() + "'\n";
            }
            if (!utl.isnull(filters.getMonth())) {
                whrClause += "and t.month = '" + filters.getMonth() + "'\n";
            }
            if (!utl.isnull(filters.getAuthStatusFlag())) {
                if ("A".equals(filters.getAuthStatusFlag())) {
                    whrClause += "and t.approveddate is not null\n";
                } else if ("R".equals(filters.getAuthStatusFlag())) {
                    whrClause += "and t.approveddate is null\n";
                }
            }
            if (!utl.isnull(filters.getFrom_date()) && !utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate between to_date('" + filters.getFrom_date() + "','dd-mm-rrrr') "
                        + "and to_date('" + filters.getTo_date() + "','dd-mm-rrrr')\n";
            }
            if (!utl.isnull(filters.getFrom_date()) && utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate >= to_date('" + filters.getFrom_date() + "','dd-mm-rrrr')\n";
            }
            if (utl.isnull(filters.getFrom_date()) && !utl.isnull(filters.getTo_date())) {
                whrClause += "and t.approveddate <= to_date('" + filters.getTo_date() + "','dd-mm-rrrr')\n";
            }
            if (!utl.isnull(filters.getTds_amt_from()) && !utl.isnull(filters.getTds_amt_to())) {
                if (!utl.isnull(filters.getTdsAmtFlag()) && filters.getTdsAmtFlag().equals("between")) {
                    whrClause += "and t.tds_amt between " + filters.getTds_amt_from() + " and " + filters.getTds_amt_to() + "\n";
                } else if (!utl.isnull(filters.getTdsAmtFlag())) {
                    whrClause += "and (t.tds_amt " + filters.getTdsAmtFlag() + " " + filters.getTds_amt_from() + "";
                    whrClause += " or t.tds_amt " + filters.getTdsAmtFlag() + " " + filters.getTds_amt_to() + ")\n";

                }
            }
            if (!utl.isnull(filters.getTdsAmtFlag()) && utl.isnull(filters.getTds_amt_from()) && !utl.isnull(filters.getTds_amt_to())) {
                whrClause += "and t.tds_amt " + filters.getTdsAmtFlag() + " '" + filters.getTds_amt_to() + "'\n";
            }
            if (!utl.isnull(filters.getTdsAmtFlag()) && !utl.isnull(filters.getTds_amt_from()) && utl.isnull(filters.getTds_amt_to())) {
                whrClause += "and t.tds_amt  " + filters.getTdsAmtFlag() + " '" + filters.getTds_amt_to() + "'\n";
            }
            if (!utl.isnull(filters.getAccount_number())) {
                whrClause += "and t.account_number  = '" + filters.getAccount_number() + "'\n";
            }
        }
        return whrClause;
    }

    @Override
    public TdsTranLoad getByRowId(String rowid) {
        TdsTranLoad object = null;
        try {
            Criteria crit = getSession().createCriteria(TdsTranLoad.class);
            crit.add(Restrictions.eq("rowid_seq", Long.parseLong(rowid)));
            object = (TdsTranLoad) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public LinkedHashMap<String, String> getTdsSectionValuesFromTdsCode(String tds_code) {
        LinkedHashMap<String, String> tdsMap = new LinkedHashMap<String, String>();
        Session session = getSession();
        String qry = "";
        qry = "select * from tds_mast where tds_code='" + tds_code + "' and closed_date is null";
        SQLQuery query = session.createSQLQuery(qry);
        List<Object[]> resultList = query.list();
        tdsMap.put("", "--Select Section---");
        resultList = resultList != null && resultList.size() > 0 ? resultList : null;
        if (resultList != null) {
            for (Object[] obj : resultList) {
                tdsMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]) + " (" + String.valueOf(obj[5]) + ")");
            }
        }

        return tdsMap;
    }

    public String getWhereClauseAuth(TdsTranLoad filters, String authFlag) {
        String whrClause = "";
        if (filters != null) {
            if (!utl.isnull(filters.getDeductee_panno())) {
                whrClause += "and t.deductee_panno = '" + filters.getDeductee_panno() + "'\n";
            }
            if (!utl.isnull(filters.getDeductee_name())) {
                whrClause += "and lower(t.deductee_name) like lower('%" + filters.getDeductee_name().trim() + "%')\n";
            }
            if (!utl.isnull(authFlag)) {
                if (authFlag.equals("R")) {
                    whrClause += "and ('" + authFlag + "' = 'R' and t.approvedby is null) \n";
                } else {
                    whrClause += "and ('" + authFlag + "' = 'A' and t.approvedby is not null)  \n";
                }
            }
        }
        return whrClause;
    }

    @Override
    public String updateSingleTranLoad(String rowidSeq, String flag, String client_code) {
        String return_message = "";
        try {
            if (rowidSeq != null && !rowidSeq.isEmpty()) {
                DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                Date dateobj = new Date();
                String query = "";
                if (flag.equals("A")) {
                    query = "update tds_misc_tran_load set TRAN_REF_DATE=to_char(sysdate, 'dd-MON-rrrr'), approvedby = '" + client_code + "', approveddate='" + df.format(dateobj) + "' where rowid_seq = '" + rowidSeq + "'";
                } else {
                    query = "update tds_misc_tran_load set TRAN_REF_DATE='', approvedby = '', approveddate='' where rowid_seq = '" + rowidSeq + "'";
                }

                DbFunctionExecutorAsString executor = new DbFunctionExecutorAsString();
                boolean update_function = executor.execute_oracle_update_function_as_string(query);
                if (update_function) {
                    return_message = "updateRecord";
                } else {
                    return_message = "updateError";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_message;
    }

    @Override
    public Long getCheckerMakerDataCount(TdsTranLoad miscData, String authFlag, ViewClientMast clientMast, Assessment asmt, Util utl) {

        Long count = 0L;
        Session session = getSession();
        try {
            String sql
                    = "select count(*)\n"
                    + " from Tds_misc_Tran_Load t , client_mast c\n"
                    + "where t.entity_code =  c.entity_code\n"
                    + " and  t.client_code = c.client_code\n"
                    + " and t.entity_code = '" + clientMast.getEntity_code() + "'\n"
                    + " and (c.client_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "      c.g_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.sg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.ssg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                    + "      c.sssg_parent_code= '" + clientMast.getClient_code() + "' )\n"
                    + "and  t.acc_year = '" + asmt.getAccYear() + "'\n"
                    + "and t.quarter_no = '" + asmt.getQuarterNo() + "'\n"
                    + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                    + "and t.data_entry_mode ='M'\n"
                    + getWhereClauseAuth(miscData, authFlag);

            SQLQuery query = session.createSQLQuery(sql);
            Object obj = query.uniqueResult();
            count = Long.parseLong(obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    }

    @Override
    public String updateBulkTranLoad(String flag, String authStatus, ViewClientMast clientMast, Assessment asmt, String panno, String name, String usercode) {
        String return_message = "";
        try {
            if (flag != null && !flag.isEmpty()) {
                DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                Date dateobj = new Date();

                String usercodeAppUn = "";
                String dateFormat = "";
                String tranRefDate = "''";

                if (flag.equals("A")) {
                    dateFormat = df.format(dateobj);
                    usercodeAppUn = usercode;
                    tranRefDate = "to_char(sysdate, 'dd-MON-rrrr')";
                }
                String query
                        = " update tds_misc_tran_load t SET TRAN_REF_DATE=" + tranRefDate + ", t.approvedby ='" + usercodeAppUn + "', approveddate='" + dateFormat + "'\n"
                        + " where\n"
                        + " t.entity_code = '" + clientMast.getEntity_code() + "'\n"
                        + " and exists(select 1 from client_mast c\n"
                        + " where t.client_code = c.client_code\n"
                        + " and(\n"
                        + " c.client_code= '" + clientMast.getClient_code() + "' or\n"
                        + "  c.parent_code = '" + clientMast.getClient_code() + "' or\n"
                        + "  c.g_parent_code= '" + clientMast.getClient_code() + "' or\n"
                        + "  c.sg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                        + "  c.ssg_parent_code= '" + clientMast.getClient_code() + "' or\n"
                        + "  c.sssg_parent_code= '" + clientMast.getClient_code() + "' ))\n"
                        + "\n"
                        + "--and  t.client_code=' '\n"
                        + "and  t.acc_year = '" + asmt.getAccYear() + "'\n"
                        + "--and t.quarter_no = '4'\n"
                        + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "'\n"
                        + "and t.data_entry_mode ='M'\n"
                        + getWhereClauseAll(panno, name, authStatus);

                utl.generateLog("MISC Bulk approve/unapprove Query", query);

                DbFunctionExecutorAsString executor = new DbFunctionExecutorAsString();
                boolean update_function_bulk = executor.execute_oracle_update_function_as_string(query);

                if (update_function_bulk) {
                    return_message = "updateRecord";
                } else {
                    return_message = "updateError";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_message;
    }

    public String getWhereClauseAll(String panno, String name, String authFlag) {
        String whrClause = "";

        if (!utl.isnull(panno)) {
            whrClause += "and t.deductee_panno = '" + panno + "'\n";
        }
        if (!utl.isnull(name)) {
            whrClause += "and lower(t.deductee_name) like lower('%" + name.trim() + "%')\n";
        }
        if (!utl.isnull(authFlag)) {
            if (authFlag.equals("A")) {
                whrClause += "and ('" + authFlag + "' = 'A' and t.approvedby is not null)  \n";
            } else if (authFlag.equals("R")) {
                whrClause += "and ('" + authFlag + "' = 'R' and t.approvedby is null) \n";
            } else {
                whrClause += "and ('" + authFlag + "' = 'AL' and 1=1)\n";
            }
        }
        return whrClause;
    }

    @Override
    public LinkedHashMap<String, String> getBGLSectionValues(String bglCode, String tdsTypeCode) {
        LinkedHashMap<String, String> bglMap = new LinkedHashMap<String, String>();
        Session session = getSession();
        String qry = "select *"
                + "  from tds_mast t\n"
                + " where t.tds_code = (SELECT TDS_CODE\n"
                + "                       FROM VIEW_BGL_MAST A\n"
                + "                      WHERE A.BGL_CODE = '" + bglCode + "')";

        SQLQuery query = session.createSQLQuery(qry);
        List<Object[]> resultList = query.list();

        if (resultList != null && !resultList.isEmpty()) {
            for (Object[] obj : resultList) {
                bglMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]) + " (" + String.valueOf(obj[5]) + ")");
            }
        }

        return bglMap;
    }

    @Override
    public LinkedHashMap<String, String> getBGLCodeNameList(String tdsType) {
        LinkedHashMap<String, String> bglCodeName = new LinkedHashMap<String, String>();
        Session session = getSession();
        String qry = "";
        try {
            qry = "select bgl_code, bgl_name, substr(bgl_code, 1, 5) bgl from VIEW_BGL_MAST t where t.tds_type_code='" + tdsType + "'"
                    + " order by bgl_code";

            SQLQuery query = session.createSQLQuery(qry);
            List<Object[]> resultList = query.list();

            if (resultList != null && !resultList.isEmpty()) {
                for (Object[] obj : resultList) {
                    bglCodeName.put(String.valueOf(obj[0]), "(" + String.valueOf(obj[2]) + ") " + String.valueOf(obj[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bglCodeName;
    }//end

    @Override
    public String getRateAndTotalAmount(Assessment asmt, ViewClientMast client, TDSRateBean rateBean) {

        Object uniqueResult = null;
        String pan_valid_verified_flag = "";
        DbFunctionExecutorAsString funcExecutor = new DbFunctionExecutorAsString();
        try {
            pan_valid_verified_flag = funcExecutor.execute_oracle_function_as_string(
                    "select lhs_utility.is_valid_panno('" + rateBean.getDeductee_panno() + "') || '#' ||\n"
                    + "       lhs_utility.is_panno_verified('" + rateBean.getDeductee_panno() + "')\n"
                    + "  from dual");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            uniqueResult = getTdsRateAndThresholdRate("CUM_TDS_RATE", client, asmt, pan_valid_verified_flag, rateBean);
            uniqueResult += "#";
            uniqueResult += getTdsRateAndThresholdRate("TDS_AMT", client, asmt, pan_valid_verified_flag, rateBean);
            uniqueResult += "#";
            uniqueResult += getTranAmtAndTdsAmt("TRAN_AMT", client, asmt, rateBean);
            uniqueResult += "#";
            uniqueResult += getTranAmtAndTdsAmt("TDS_AMT", client, asmt, rateBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uniqueResult != null ? uniqueResult.toString() : "";
    }//end method

    private String getTdsRateAndThresholdRate(String cum_base_tds_rate, ViewClientMast client, Assessment asmt, String pan_valid_verified_flag, TDSRateBean rateBean) {
        String returnRate = "";
        DbFunctionExecutorAsString funcExecutor = new DbFunctionExecutorAsString();
        String rate_type = rateBean.getRate_type();
        if (rateBean.getRate_type().equals("A")) {
            rate_type = "I";
        } else if (rateBean.getRate_type().equals("B")) {
            rate_type = "D";

        }
        try {
            String rateQuery = "select LHS_TDS.GET_TDS_RATE('" + client.getEntity_code() + "',\n" //entity code
                    + "                            '" + client.getClient_code() + "',\n"//client code
                    + "                            '" + asmt.getAccYear() + "',\n"//acc year
                    + "                            '" + asmt.getTdsTypeCode() + "',\n"//tds type code
                    + "                            '" + rateBean.getDeductee_panno().toUpperCase() + "',\n"//deductee pan
                    + "                            '" + pan_valid_verified_flag.split("#")[0] + "',\n"//pan valid
                    + "                            '" + pan_valid_verified_flag.split("#")[1] + "',\n"//pan verified
                    + "                            '" + rateBean.getCertificate_no() + "',\n"//cert no
                    + "                            '" + rate_type + "',\n"
                    + "                            '" + rateBean.getNature_of_rem() + "',\n"
                    + "                            '" + rateBean.getCountry_code() + "',\n"//country code
                    + "                            '" + rateBean.getTds_section() + "',\n"//tds section
                    + "                            to_date(to_char(sysdate, 'dd-MM-rrrr'), 'dd-MM-rrrr'),\n"//tds deduct date
                    + "                            '" + (rateBean.getTotal_paid_amt() + rateBean.getPayment_amt()) + "',\n"
                    + "                            '" + cum_base_tds_rate + "') tds_rate\n"
                    + "  from dual";
            returnRate = funcExecutor.execute_oracle_function_as_string(rateQuery);
//            System.out.println("Rate function==" + rateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !utl.isnull(returnRate) ? returnRate : "0";
    }//end method

    private String getTranAmtAndTdsAmt(String data_type, ViewClientMast client, Assessment asmt, TDSRateBean rateBean) {
        String returnRate = "";
        DbFunctionExecutorAsString funcExecutor = new DbFunctionExecutorAsString();

        try {
            String rateQuery = "    select LHS_TDS.get_tds_amt_misc('" + client.getEntity_code() + "',\n" //entity code
                    + "                            '" + client.getClient_code() + "',\n"//client code
                    + "                            '" + asmt.getAccYear() + "',\n"//acc year
                    + "                            '" + asmt.getQuarterNo() + "',\n"//acc year
                    + "                            '" + asmt.getTdsTypeCode() + "',\n"//tds type code
                    + "                            '" + rateBean.getTds_section() + "',\n"//tds section
                    + "                            '" + rateBean.getDeductee_panno().toUpperCase() + "',\n"//deductee pan
                    + "                            '" + (utl.isnull(rateBean.getRowid_seq()) ? "" : rateBean.getRowid_seq()) + "',\n"//deductee pan
                    + "                            '" + data_type + "') tds_amt_and_tds_amt\n"
                    + "  from dual";
            returnRate = funcExecutor.execute_oracle_function_as_string(rateQuery);
//            System.out.println("Tds and Tran amt function==" + rateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !utl.isnull(returnRate) ? returnRate : "0";
    }//end method

    @Override
    public String getTransactionID() {

        Session session = getSession();
        Object uniqueResult = null;

        String qry = "select lhs_tds.get_tran_id_misc from dual";

        SQLQuery query = session.createSQLQuery(qry);

        uniqueResult = query.uniqueResult();

        return uniqueResult != null ? uniqueResult.toString() : "";
    }

    @Override
    public String getPanStatusName(String pan4thChar) {

        Session session = getSession();
        Object uniqueResult = null;
        try {
            String qry = "select status_name from view_pan_status s where s.pan_card_type like upper('" + pan4thChar.trim() + "')";

            SQLQuery query = session.createSQLQuery(qry);

            uniqueResult = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uniqueResult != null ? uniqueResult.toString() : "";
    }//end

//    @Override
//    public List<TdsTranLoad> getCertificateDetails(ViewClientMast clientMast, Assessment asmt, String deductee_panno, String tds_code,
//            String certificate_no) {
    @Override
    public List<TdsTranLoad> getCertificateDetails(TdsTranLoad tds) {
        Session session = getSession();
        List<TdsTranLoad> list = null;

        try {
            Criteria crit = session.createCriteria(TdsTranLoad.class);
            crit.add(Restrictions.eq("entity_code", tds.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", tds.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", tds.getQuarter_no()));
            crit.add(Restrictions.eq("tds_type_code", tds.getTds_type_code()));
            crit.add(Restrictions.eq("deductee_panno", tds.getDeductee_panno()));
            crit.add(Restrictions.eq("tds_code", tds.getTds_code()));
            crit.add(Restrictions.eq("tds_deduct_reason", "A"));
            crit.add(Restrictions.eq("certificate_no", tds.getCertificate_no()));
            crit.add(Restrictions.sqlRestriction("exists\n"
                    + " (select 1\n"
                    + "          from client_mast b\n"
                    + "         where b.entity_code = this_.entity_code\n"
                    + "           and b.client_code = this_.client_code\n"
                    + "           and (b.client_code = '" + tds.getClient_code() + "' or b.parent_code = '" + tds.getClient_code() + "' or\n"
                    + "               b.g_parent_code = '" + tds.getClient_code() + "' or b.sg_parent_code = '" + tds.getClient_code() + "' or\n"
                    + "               b.ssg_parent_code = '" + tds.getClient_code() + "' or b.sssg_parent_code = '" + tds.getClient_code() + "'))"));
            list = crit.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            list = null;
            session.getTransaction().rollback();
        }

        return list;
    }//end

}//end class

