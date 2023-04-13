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
import hibernateObjects.SalaryTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import regular.salaryNew.SalaryTranLoadBean;
import regular.salaryNew.SalaryTranLoadDB;
import regular.salaryNew.SalaryTranLoadFilter;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryTranLoadDAOImpl extends GenericHibernateDAO<SalaryTranLoad, Serializable> implements SalaryTranLoadDAO {

//    @Override
    public ArrayList<SalaryTranLoad> getSalaryTranLoadList1(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {
        ArrayList<SalaryTranLoad> tranLoadList = null;
        try {
            Criteria crit = getSession().createCriteria(SalaryTranLoad.class);

            if (filters != null) {
                if (!utl.isnull(filters.getDeductee_paano())) {
                    crit.add(Restrictions.eq("deductee_panno", filters.getDeductee_paano()));
                }
                if (!utl.isnull(filters.getDeductee_name())) {
                    crit.add(Restrictions.like("deductee_name", filters.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                }
                if (!utl.isnull(filters.getDeductee_catg())) {
                    crit.add(Restrictions.eq("deductee_catg", filters.getDeductee_catg()));
                }
                if (!utl.isnull(filters.getDeduction_catg()) && filters.getDeduction_catg().equalsIgnoreCase("S")) {
                    crit.add(Restrictions.sqlRestriction("shortfall_excess_deduction  >=1 "));
                }
                if (!utl.isnull(filters.getDuplicate_record_flag()) && filters.getDuplicate_record_flag().equalsIgnoreCase("T")) {
                    crit.add(Restrictions.eq("deductee_panno_verified", "Y"));
                    crit.add(Restrictions.sqlRestriction(" exists (select 1        \n"
                            + "          from (                \n"
                            + "                select entity_code,\n"
                            + "                        client_code,\n"
                            + "                        acc_year,\n"
                            + "                        quarter_no,\n"
                            + "                        tds_type_code,\n"
                            + "                        deductee_panno,\n"
                            + "                        rowid_Seq                \n"
                            + "                  from (select b.entity_code,\n"
                            + "                                b.client_code,\n"
                            + "                                b.acc_year,\n"
                            + "                                b.quarter_no,\n"
                            + "                                b.tds_type_code,\n"
                            + "                                b.deductee_panno,\n"
                            + "                                b.rowid_Seq,                                \n"
                            + "                                count(deductee_panno) over(partition by b.entity_code,"
                            + " b.client_code, b.acc_year, b.quarter_no, b.tds_type_code, b.deductee_panno) dupl_deductee_panno\n"
                            + "                           from salary_tran_load b,client_mast b1                         \n"
                            + "                         where b1.entity_code=b.entity_code\n"
                            + "                          and   b1.client_code=b.client_code\n"
                            + "                          and   b.entity_code = '" + clientMast.getEntity_code() + "'\n"
                            + "                            and (b1.client_code = '" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                            + "                                )\n"
                            + "                            and b.acc_year = '" + asmt.getAccYear() + "') b             \n"
                            + "                 where nvl(dupl_deductee_panno, 0) > 1                \n"
                            + "                ) b        \n"
                            + "         where b.entity_code = this_.entity_code              \n"
                            + "           and b.client_code = this_.client_code             \n"
                            + "           and b.acc_year = this_.acc_year              \n"
                            + "         and b.deductee_panno = this_.deductee_panno "
                            + "        )    and this_.entity_code = '" + clientMast.getEntity_code() + "'"));
                }
            }
//            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
//                    + "                   where w1.client_code = this_.client_code\n"
//                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
//                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
//                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
//
//            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
//            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));

            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("rowid_seq"));

            tranLoadList = (ArrayList<SalaryTranLoad>) crit.list();
        } catch (Exception e) {
            tranLoadList = null;
            e.printStackTrace();
        }
        return (tranLoadList != null) ? tranLoadList : null;
    }//end method

    @Override
    public ArrayList<SalaryTranLoadBean> getSalaryTranLoadList(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl) {
        SalaryTranLoadDB salaryTranLoadDb = new SalaryTranLoadDB();
        String salaryTranLoadQuery = salaryTranLoadDb.getSalaryTranLoadQuery(filters, clientMast, asmt, minVal, maxVal, utl);
        ArrayList<SalaryTranLoadBean> list = null;
        try {

            list = (ArrayList<SalaryTranLoadBean>) getSession().createSQLQuery(salaryTranLoadQuery).addEntity(SalaryTranLoadBean.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SalaryTranLoad getByRowId(Long rowid) {
        SalaryTranLoad object = null;
        try {
            Criteria crit = getSession().createCriteria(SalaryTranLoad.class);
            crit.add(Restrictions.eq("rowid_seq", rowid));

            object = (SalaryTranLoad) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }//end method

    @Override
    public Long getSalaryTranLoadDataCount(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, Util utl) {
        Long object = null;
        try {
            Criteria crit = getSession().createCriteria(SalaryTranLoad.class);
            DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
            String data_client_code = "";
            String data_validation_client_code = "";
            if (!utl.isnull(filters.getClient_bank_branch_code())) {
                data_client_code = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                        "select client_code from client_mast where entity_code='" + clientMast.getEntity_code() + "' and bank_branch_code='" + filters.getClient_bank_branch_code() + "'");
            }
            if (!utl.isnull(filters.getChallan_bank_branch_code())) {
                data_validation_client_code = dbFunctionExecutorAsString.execute_oracle_function_as_string(
                        "select client_code from client_mast where entity_code='" + clientMast.getEntity_code() + "' and bank_branch_code='" + filters.getChallan_bank_branch_code() + "'");
            }
            if (filters != null) {

                if (!utl.isnull(filters.getDeductee_paano())) {
                    crit.add(Restrictions.eq("deductee_panno", filters.getDeductee_paano()));
                }
                if (!utl.isnull(filters.getClient_bank_branch_code())) {
                    crit.add(Restrictions.eq("client_code", data_client_code));
                }
                if (!utl.isnull(filters.getChallan_bank_branch_code())) {
                    crit.add(Restrictions.eq("validation_client_code", data_validation_client_code));
                }
                if (!utl.isnull(filters.getDeductee_name())) {
                    crit.add(Restrictions.like("deductee_name", filters.getDeductee_name().trim(), MatchMode.ANYWHERE).ignoreCase());
                }
                if (!utl.isnull(filters.getDeductee_catg())) {
                    crit.add(Restrictions.eq("deductee_catg", filters.getDeductee_catg()));
                }
                if (!utl.isnull(filters.getTax_115bac_flag())) {
                    crit.add(Restrictions.eq("tax_115bac_flag", filters.getTax_115bac_flag()));
                }
                if (!utl.isnull(filters.getDeduction_catg()) && filters.getDeduction_catg().equalsIgnoreCase("S")) {
                    crit.add(Restrictions.sqlRestriction("nvl(AFG46_SFTD_AMT,0)>=1 "));
                }
                if (!utl.isnull(filters.getDuplicate_record_flag()) && filters.getDuplicate_record_flag().equalsIgnoreCase("T")) {
                    crit.add(Restrictions.eq("deductee_panno_verified", "Y"));
                    crit.add(Restrictions.sqlRestriction(" exists (select 1        \n"
                            + "          from (                \n"
                            + "                select entity_code,\n"
                            + "                        client_code,\n"
                            + "                        acc_year,\n"
                            + "                        quarter_no,\n"
                            + "                        tds_type_code,\n"
                            + "                        deductee_panno,\n"
                            + "                        rowid_Seq                \n"
                            + "                  from (select b.entity_code,\n"
                            + "                                b.client_code,\n"
                            + "                                b.acc_year,\n"
                            + "                                b.quarter_no,\n"
                            + "                                b.tds_type_code,\n"
                            + "                                b.deductee_panno,\n"
                            + "                                b.rowid_Seq,                                \n"
                            + "                                count(deductee_panno) over(partition by b.entity_code,"
                            + " b.client_code, b.acc_year, b.quarter_no, b.tds_type_code, b.deductee_panno) dupl_deductee_panno\n"
                            + "                           from salary_tran_load b,client_mast b1                         \n"
                            + "                         where b1.entity_code=b.entity_code\n"
                            + "                          and   b1.client_code=b.client_code\n"
                            + "                          and   b.entity_code = '" + clientMast.getEntity_code() + "'\n"
                            + "                            and (b1.client_code = '" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.g_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.sg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.ssg_parent_code='" + clientMast.getClient_code() + "' or\n"
                            + "                                 b1.sssg_parent_code='" + clientMast.getClient_code() + "'\n"
                            + "                                )\n"
                            + "                            and b.acc_year = '" + asmt.getAccYear() + "') b             \n"
                            + "                 where nvl(dupl_deductee_panno, 0) > 1                \n"
                            + "                ) b        \n"
                            + "         where b.entity_code = this_.entity_code              \n"
                            + "           and b.client_code = this_.client_code             \n"
                            + "           and b.acc_year = this_.acc_year              \n"
                            + "         and b.deductee_panno = this_.deductee_panno "
                            + "        )    and this_.entity_code = '" + clientMast.getEntity_code() + "'"));
                }
            }
            crit.add(Restrictions.sqlRestriction(" exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + clientMast.getClient_code() + "' or w1.parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + clientMast.getClient_code() + "' or w1.sg_parent_code = '" + clientMast.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + clientMast.getClient_code() + "' or w1.sssg_parent_code = '" + clientMast.getClient_code() + "'))"));
            crit.add(Restrictions.eq("entity_code", clientMast.getEntity_code()));
            crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
            crit.add(Restrictions.eq("quarter_no", Long.valueOf(asmt.getQuarterNo())));
            crit.add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()));

            crit.setProjection(Projections.rowCount());
            object = (Long) crit.uniqueResult();
        } catch (Exception e) {
            object = 0l;
            e.printStackTrace();
        }
        return object;
    }

}//end class
