/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsChallanTranLoad;
import hibernateObjects.tdsChallanTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import regular.challan.ChallanFilterEntity;
import regular.challan.ChallanGrossTotalSumList;

/**
 *
 * @author aniket.naik
 */
public class ViewTdsChallanTranLoadDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewTdsChallanTranLoad, Serializable> implements ViewTdsChallanTranLoadDAO {

    @Override
    public Long getTdsChallanTranLoadCount(String loginEntityCode, String workinguser, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, boolean fromAllocatedChallan, Util utl) {
        Long rowcount = null;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsChallanTranLoad.class);
            crit.add(Restrictions.eq("entity_code", loginEntityCode));
            if (tdsChallanTranFilterSrch != null) {
                if (!utl.isnull(tdsChallanTranFilterSrch.getTds_section())) {
                    crit.add(Restrictions.eq("tds_code", tdsChallanTranFilterSrch.getTds_section()));//on dropdown value is tds_code
                }
                // From-Date / To-Date
                if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date()) && !utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                    crit.add(Restrictions.sqlRestriction("tds_challan_date between to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr') and to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));

                } else if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date())) {
                    crit.add(Restrictions.sqlRestriction("tds_challan_date  >= to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr')"));

                } else if (!utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                    crit.add(Restrictions.sqlRestriction("tds_challan_date  <= to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));
                }
                // Challan Status
                String flag = "";
                if (!utl.isnull(tdsChallanTranFilterSrch.getChallanStatus())) {
                    flag = tdsChallanTranFilterSrch.getChallanStatus();
                    if (!flag.equalsIgnoreCase("P")) {
//                            //System.out.println("csi_verify_flag IS :: " + flag);
                        crit.add(Restrictions.eq("csi_verify_flag", flag));
                    }

                }
                // Allocation Status
                if (!utl.isnull(tdsChallanTranFilterSrch.getAllocationStatus())) {
                    Double l_value = 0d;
                    if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                        crit.add(Restrictions.eq("allocated_amount", l_value));
                    }

                    if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                        crit.add(Restrictions.ne("allocated_amount", l_value));
                    }

                    if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt = allocated_amount"));
                    }

                    if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("OBC")) {
                        crit.add(Restrictions.sqlRestriction("nvl(balance_to_allocate, 0) < 0"));
                    }
                }

                if (!utl.isnull(tdsChallanTranFilterSrch.getBankBranchCode())) {
                    crit.add(Restrictions.eq("bank_branch_code", tdsChallanTranFilterSrch.getBankBranchCode()));
                }
                
                 if (!utl.isnull(tdsChallanTranFilterSrch.getTds_challan_no())) {
                        crit.add(Restrictions.eq("tds_challan_no", tdsChallanTranFilterSrch.getTds_challan_no()));
                    }
            }

            if (tdsChallanTranFilterSrch != null) {
                if (!utl.isnull(tdsChallanTranFilterSrch.getChallanLevel()) && tdsChallanTranFilterSrch.getChallanLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", workinguser));

                } else if (!fromAllocatedChallan) {
                    crit.add(Restrictions.sqlRestriction(normal_WhereClause(workinguser)));

                } else if (fromAllocatedChallan) {
                    crit.add(Restrictions.sqlRestriction(fromAllocatedChallan_WhereClause(workinguser)));
                }
            } else if (!fromAllocatedChallan) {
                crit.add(Restrictions.sqlRestriction(normal_WhereClause(workinguser)));

            } else if (fromAllocatedChallan) {
                crit.add(Restrictions.sqlRestriction(fromAllocatedChallan_WhereClause(workinguser)));
            }

            crit.add(Restrictions.eq("acc_year", assessment.getAccYear()));
            crit.add(Restrictions.eq("quarter_no", assessment.getQuarterNo()));
            crit.add(Restrictions.eq("tds_type_code", assessment.getTdsTypeCode()));
            crit.setProjection(Projections.rowCount());

            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();

        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }

    public String fromAllocatedChallan_WhereClause(String clientcode) {
        String returnStr = "";
        try {
            returnStr = " exists (\n"
                    + "SELECT 1 FROM TDS_TRAN_LOAD W1\n"
                    + " WHERE W1.ENTITY_CODE = THIS_.ENTITY_CODE\n"
                    + "   AND W1.CLIENT_CODE = '" + clientcode + "' \n"
                    + "   AND W1.ACC_YEAR = THIS_.ACC_YEAR \n"
                    + "   AND W1. QUARTER_NO = THIS_. QUARTER_NO \n"
                    + "   AND W1. TDS_TYPE_CODE = THIS_. TDS_TYPE_CODE \n"
                    + "   AND W1. TDS_CHALLAN_ROWID_SEQ = THIS_. ROWID_SEQ) \n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }//End Method

    public String normal_WhereClause(String clientcode) {
        String returnStr = "";
        try {
            returnStr = "exists (select 1 from client_mast w1\n"
                    + "where w1.client_code = this_.client_code\n"
                    + "and (w1.client_code = '" + clientcode + "' or w1.parent_code = '" + clientcode + "' or\n"
                    + "w1.g_parent_code = '" + clientcode + "' or w1.sg_parent_code = '" + clientcode + "' or\n"
                    + "w1.ssg_parent_code = '" + clientcode + "' or w1.sssg_parent_code = '" + clientcode + "'))";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }//End Method 

    @Override
    public List<ViewTdsChallanTranLoad> getTdsChallanTranLoadData(String loginEntityCode, String clientcode, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, String search, int minVal, int maxVal, Util utl, boolean procedureFlag, boolean fromAllocatedChallan) {
        List<ViewTdsChallanTranLoad> viewtdsChallanTranLoad = null;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsChallanTranLoad.class);
         
            crit.add(Restrictions.eq("entity_code", loginEntityCode));

            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (tdsChallanTranFilterSrch != null) {
                    if (!utl.isnull(tdsChallanTranFilterSrch.getTds_section())) {
                        crit.add(Restrictions.eq("tds_code", tdsChallanTranFilterSrch.getTds_section()));
                    }
                    if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date()) && !utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date between to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr') and to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));
                    } else if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  >= to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr')"));
                    } else if (!utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  <= to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));
                    }
                    if (!utl.isnull(tdsChallanTranFilterSrch.getChallanStatus())) {
                        String flag = "";
                        flag = tdsChallanTranFilterSrch.getChallanStatus();
                        if (!flag.equalsIgnoreCase("P")) {
                            crit.add(Restrictions.eq("csi_verify_flag", flag));
                        }
                    }
                    if (!utl.isnull(tdsChallanTranFilterSrch.getAllocationStatus())) {
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.eq("allocated_amount", l_value));
                        }
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt != allocated_amount"));
                            crit.add(Restrictions.ne("allocated_amount", l_value));
                        }
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt = allocated_amount"));
                        }

                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("OBC")) {
                            crit.add(Restrictions.sqlRestriction("nvl(balance_to_allocate, 0) < 0"));
                        }
                    }

                    if (!utl.isnull(tdsChallanTranFilterSrch.getBankBranchCode())) {
                        crit.add(Restrictions.eq("bank_branch_code", tdsChallanTranFilterSrch.getBankBranchCode()));
                    }
                    
                    if (!utl.isnull(tdsChallanTranFilterSrch.getTds_challan_no())) {
                        crit.add(Restrictions.eq("tds_challan_no", tdsChallanTranFilterSrch.getTds_challan_no()));
                    }
                    
                }
            }
            if (tdsChallanTranFilterSrch != null) {
                if (!utl.isnull(tdsChallanTranFilterSrch.getChallanLevel()) && tdsChallanTranFilterSrch.getChallanLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", clientcode));

                } else if (!fromAllocatedChallan) {
                    crit.add(Restrictions.sqlRestriction(normal_WhereClause(clientcode)));

                } else if (fromAllocatedChallan) {
                    crit.add(Restrictions.sqlRestriction(fromAllocatedChallan_WhereClause(clientcode)));
                }
            } else if (!fromAllocatedChallan) {
                crit.add(Restrictions.sqlRestriction(normal_WhereClause(clientcode)));

            } else if (fromAllocatedChallan) {
                crit.add(Restrictions.sqlRestriction(fromAllocatedChallan_WhereClause(clientcode)));
            }
            crit.add(Restrictions.eq("acc_year", assessment.getAccYear()));
            crit.add(Restrictions.eq("quarter_no", assessment.getQuarterNo()));
            crit.add(Restrictions.eq("tds_type_code", assessment.getTdsTypeCode()));
           // crit.add(Restrictions.sqlRestriction("SLNO BETWEEN " + minVal + " AND " + maxVal + ""));
           // System.out.println("fygfgfyufgfy------>"+procedureFlag+"kklk"+fromAllocatedChallan);
            if (!procedureFlag) { 
                crit.setFirstResult(minVal);
                if(minVal== 0){
                crit.setMaxResults(maxVal);
                }else{
                    crit.setMaxResults(10);
                }
               
                }
            System.out.println("min and max-->"+minVal+"-----"+maxVal);
             
           
            crit.addOrder(Order.asc("rowid_seq")); System.out.println("min 33333");
           // crit.setFirstResult(minVal);   System.out.println("min 111111");
           // crit.setMaxResults(maxVal);  System.out.println("min 222222");
            System.out.println("crit.list() size-->"+crit.list().size());
            viewtdsChallanTranLoad = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            viewtdsChallanTranLoad = null;
            getSession().getTransaction().rollback();
        }

        return viewtdsChallanTranLoad;
    }

    @Override
    public ChallanGrossTotalSumList readAllChallanDataSum(String client_code, Assessment assessment, ChallanFilterEntity tranChallanFltrSrch, String search, Util utl) {
        List<ChallanGrossTotalSumList> result = null;
        try {System.out.println("get challan list-**");
            Criteria crit = getSession().createCriteria(ViewTdsChallanTranLoad.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.sum("total_tds_challan_amt"), "total_tds_challan_amt");
            projList.add(Projections.sum("tds_challan_tds_amt"), "tds_challan_tds_amt");
            projList.add(Projections.sum("tds_challan_int_amt"), "tds_challan_int_amt");
            projList.add(Projections.sum("tds_challan_other_amt"), "tds_challan_other_amt");
            projList.add(Projections.sum("allocated_amount"), "allocated_amount");
            projList.add(Projections.sum("balance_to_allocate"), "balance_to_allocate");
            crit.setProjection(projList);

            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (tranChallanFltrSrch != null) {    //change as per by GKK sir and 28-10-2015
                    if (!utl.isnull(tranChallanFltrSrch.getTds_section())) {
                        crit.add(Restrictions.eq("tds_code", tranChallanFltrSrch.getTds_section()));//on dropdown value is tds_code
                    }
                    if (!utl.isnull(tranChallanFltrSrch.getF_month())) {
                        crit.add(Restrictions.eq("month", tranChallanFltrSrch.getF_month()));
                    }

                    // From-Date / To-Date
                    if (!utl.isnull(tranChallanFltrSrch.getFrom_date()) && !utl.isnull(tranChallanFltrSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date between to_date('" + tranChallanFltrSrch.getFrom_date() + "','dd-mm-rrrr') and to_date('" + tranChallanFltrSrch.getTo_date() + "','dd-mm-rrrr')"));
                    }
                    if (!utl.isnull(tranChallanFltrSrch.getFrom_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  >= to_date('" + tranChallanFltrSrch.getFrom_date() + "','dd-mm-rrrr') "));
                    }

                    if (!utl.isnull(tranChallanFltrSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  <= to_date('" + tranChallanFltrSrch.getTo_date() + "','dd-mm-rrrr') "));
                    }
                    // Challan Status
                    if (!utl.isnull(tranChallanFltrSrch.getChallanStatus())) {
                        String flag = "";
                        flag = tranChallanFltrSrch.getChallanStatus();
                        if (!flag.equalsIgnoreCase("P")) {
                            crit.add(Restrictions.eq("csi_verify_flag", flag));
                        }
                    }
                    // Allocation Status
                    if (!utl.isnull(tranChallanFltrSrch.getAllocationStatus())) {
                        if (tranChallanFltrSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.eq("allocated_amount", l_value));
                        }
                        if (tranChallanFltrSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt != allocated_amount"));
                            crit.add(Restrictions.ne("allocated_amount", l_value));
                        }
                        if (tranChallanFltrSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt = allocated_amount"));
                        }
                    }

                }
            }

            if (tranChallanFltrSrch != null) {
                if (!utl.isnull(tranChallanFltrSrch.getChallanLevel()) && tranChallanFltrSrch.getChallanLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", client_code));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + client_code + "' or w1.parent_code = '" + client_code + "' or\n"
                            + "                        w1.g_parent_code = '" + client_code + "' or w1.sg_parent_code = '" + client_code + "' or\n"
                            + "                        w1.ssg_parent_code = '" + client_code + "' or w1.sssg_parent_code = '" + client_code + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + client_code + "' or w1.parent_code = '" + client_code + "' or\n"
                        + "                        w1.g_parent_code = '" + client_code + "' or w1.sg_parent_code = '" + client_code + "' or\n"
                        + "                        w1.ssg_parent_code = '" + client_code + "' or w1.sssg_parent_code = '" + client_code + "'))"));
            }
            crit.add(Restrictions.eq("acc_year", assessment.getAccYear()));
            crit.add(Restrictions.eq("quarter_no", assessment.getQuarterNo()));
            crit.add(Restrictions.eq("tds_type_code", assessment.getTdsTypeCode()));
            crit.addOrder(Order.asc("rowid_seq"));

            crit.setResultTransformer(Transformers.aliasToBean(ChallanGrossTotalSumList.class));
            result = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result != null && result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public Long getTdsChallanTranCount(String workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanFilterEntity tdsChallanTranFilterSrch, String search, Util utl) {
        Long rowcount;
        try {

            Criteria crit = getSession().createCriteria(ViewTdsChallanTranLoad.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (tdsChallanTranFilterSrch != null) {
                    if (!utl.isnull(tdsChallanTranFilterSrch.getTds_section())) {
                        crit.add(Restrictions.eq("tds_code", tdsChallanTranFilterSrch.getTds_section()));//on dropdown value is tds_code
                    }
                    // From-Date / To-Date
                    if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date()) && !utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date between to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr') and to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));
//                        //System.out.println("tds_challan_date between '" + tdsChallanTranFilterSrch.getFrom_date() + "' and '" + tdsChallanTranFilterSrch.getTo_date() + "'");
                    } else if (!utl.isnull(tdsChallanTranFilterSrch.getFrom_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  >= to_date('" + tdsChallanTranFilterSrch.getFrom_date() + "','dd-mm-rrrr')"));
//                        //System.out.println("tds_challan_date  >= '" + tdsChallanTranFilterSrch.getFrom_date() + "'");
                    } else if (!utl.isnull(tdsChallanTranFilterSrch.getTo_date())) {
                        crit.add(Restrictions.sqlRestriction("tds_challan_date  <= to_date('" + tdsChallanTranFilterSrch.getTo_date() + "','dd-mm-rrrr')"));
//                        //System.out.println("tds_challan_date  <= '" + tdsChallanTranFilterSrch.getTo_date() + "'");
                    }
                    // Challan Status
                    String flag = "";
                    if (!utl.isnull(tdsChallanTranFilterSrch.getChallanStatus())) {
                        flag = tdsChallanTranFilterSrch.getChallanStatus();
                        if (!flag.equalsIgnoreCase("P")) {
                            crit.add(Restrictions.eq("csi_verify_flag", flag));
                        }
                    }
                    // Allocation Status
                    if (!utl.isnull(tdsChallanTranFilterSrch.getAllocationStatus())) {
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("UAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.eq("allocated_amount", l_value));
                        }
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("PAC")) {
                            Double l_value = 0d;
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt != allocated_amount"));
                            crit.add(Restrictions.ne("allocated_amount", l_value));

                        }
                        if (tdsChallanTranFilterSrch.getAllocationStatus().equalsIgnoreCase("ALC")) {
                            crit.add(Restrictions.sqlRestriction("tds_challan_tds_amt = allocated_amount"));
                        }
                    }

                }
            }
            if (tdsChallanTranFilterSrch != null) {
                if (!utl.isnull(tdsChallanTranFilterSrch.getChallanLevel()) && tdsChallanTranFilterSrch.getChallanLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", workinguser));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + workinguser + "' or w1.parent_code = '" + workinguser + "' or\n"
                            + "                        w1.g_parent_code = '" + workinguser + "' or w1.sg_parent_code = '" + workinguser + "' or\n"
                            + "                        w1.ssg_parent_code = '" + workinguser + "' or w1.sssg_parent_code = '" + workinguser + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + workinguser + "' or w1.parent_code = '" + workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + workinguser + "' or w1.sg_parent_code = '" + workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + workinguser + "' or w1.sssg_parent_code = '" + workinguser + "'))"));
            }
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public Long getChallanCount(ViewTdsChallanTranLoad challanTran, Util utl) {
        Long rowcount;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsChallanTranLoad.class);
            crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + challanTran.getClient_code() + "' or w1.parent_code = '" + challanTran.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + challanTran.getClient_code() + "' or w1.sg_parent_code = '" + challanTran.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + challanTran.getClient_code() + "' or w1.sssg_parent_code = '" + challanTran.getClient_code() + "'))"));
            crit.add(Restrictions.eq("acc_year", challanTran.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", challanTran.getQuarter_no()));
            crit.add(Restrictions.eq("tds_type_code", challanTran.getTds_type_code()));
            if (!utl.isnull(challanTran.getCsi_verify_flag())) {
                crit.add(Restrictions.eq("csi_verify_flag", challanTran.getCsi_verify_flag()));
            }
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

    @Override
    public List<ViewTdsChallanTranLoad> readChallansByCSIFlag(ViewTdsChallanTranLoad challanTran, Util utl) {
        List<ViewTdsChallanTranLoad> challanTranList = null;
        try {
            Criteria crit = getSession().createCriteria(tdsChallanTran.class);
            crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                    + "                   where w1.client_code = this_.client_code\n"
                    + "                   and (w1.client_code = '" + challanTran.getClient_code() + "' or w1.parent_code = '" + challanTran.getClient_code() + "' or\n"
                    + "                        w1.g_parent_code = '" + challanTran.getClient_code() + "' or w1.sg_parent_code = '" + challanTran.getClient_code() + "' or\n"
                    + "                        w1.ssg_parent_code = '" + challanTran.getClient_code() + "' or w1.sssg_parent_code = '" + challanTran.getClient_code() + "'))"));
            crit.add(Restrictions.eq("acc_year", challanTran.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", challanTran.getQuarter_no()));
            crit.add(Restrictions.eq("tds_type_code", challanTran.getTds_type_code()));
            if (!utl.isnull(challanTran.getCsi_verify_flag())) {
                crit.add(Restrictions.eq("csi_verify_flag", challanTran.getCsi_verify_flag()));
            }
            challanTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            challanTran = null;
            getSession().getTransaction().rollback();
        }
        return challanTranList;
    }//End Method
}//end class
