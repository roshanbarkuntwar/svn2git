/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsTranLoad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import regular.challan.challanAllocation.ChallanAllocationFilterEntity;
import regular.challan.challanAllocation.MapTdsChallanGrossTotalList;

/**
 *
 * @author ayushi.jain
 */
public class ViewTdsTranLoadDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewTdsTranLoad, Serializable> implements ViewTdsTranLoadDAO {

    @Override
    public long getMapTdsChallanLoadCount(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, String paraRowIdSeq, 
            ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, Util utl) {
        Long rowcount;

        try {
            Criteria crit = getSession().createCriteria(ViewTdsTranLoad.class);

            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                    if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                        Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                        crit.add(Restrictions.or(rowid_seq, rowid_seq_null));
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {System.out.println("mapTdsChallanFilterSrchData.getParaRowidSeq()"+mapTdsChallanFilterSrchData.getParaRowidSeq());
                        crit.add(Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq()));
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                        crit.add(Restrictions.isNull("tds_challan_rowid_seq"));
                    }
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
                        crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
                        crit.add(Restrictions.eq("deductee_name", mapTdsChallanFilterSrchData.getDeductee_name()).ignoreCase());//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
                        crit.add(Restrictions.eq("deductee_panno", mapTdsChallanFilterSrchData.getPan_no()));//deductee_panno
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020 
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020 
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020 
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                        if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from() + " "));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from() + ""));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                        }
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                        crit.add(Restrictions.eq("pan_4_char_c_nc", mapTdsChallanFilterSrchData.getPan_4th_char()).ignoreCase());//on dropdown value is tds_code
                    }

                    // New added filters--from transaction browse page
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeRefNo())) {
                        crit.add(Restrictions.eq("deductee_ref_code1", mapTdsChallanFilterSrchData.getDeducteeRefNo()).ignoreCase());// Deductee Ref No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getAccountNo())) {
                        crit.add(Restrictions.eq("account_no", mapTdsChallanFilterSrchData.getAccountNo()).ignoreCase());// Account No
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getBankBranchCode())) {
                        crit.add(Restrictions.eq("bank_branch_code", mapTdsChallanFilterSrchData.getBankBranchCode()).ignoreCase());// Bank Branch Code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTdsDeductReason())) {
                        crit.add(Restrictions.eq("tds_deduct_reason", mapTdsChallanFilterSrchData.getTdsDeductReason()).ignoreCase());// Tds Deduct Reason
                    }
                } else {
                    Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                    Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                    crit.add(Restrictions.or(rowid_seq, rowid_seq_null));

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
                        crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));
                    }
                }
            }
            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("validation_client_code", l_workinguser));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
            }

            crit.add(Restrictions.eq("entity_code", entity_code));
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
    public List<ViewTdsTranLoad> readTDSDataByCode(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code,
            ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, int minVal, int maxVal, Util utl) {
        List<ViewTdsTranLoad> tdsTranList;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsTranLoad.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (mapTdsChallanFilterSrchData != null) {
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                        if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                            Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                            Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                            crit.add(Restrictions.or(rowid_seq, rowid_seq_null));
                        } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {
                            crit.add(Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq()));
                        } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                            crit.add(Restrictions.isNull("tds_challan_rowid_seq"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                            crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getDeductee_name()..." + mapTdsChallanFilterSrchData.getDeductee_name());
                            crit.add(Restrictions.eq("deductee_name", mapTdsChallanFilterSrchData.getDeductee_name()).ignoreCase());//on dropdown value is tds_code
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getPan_no()..." + mapTdsChallanFilterSrchData.getPan_no());
                            crit.add(Restrictions.eq("deductee_panno", mapTdsChallanFilterSrchData.getPan_no()));//deductee_panno
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        System.out.println("to_date");
//                        crit.add(Restrictions.sqlRestriction("tds_deduct_date between '" + mapTdsChallanFilterSrchData.getFrom_date() + "' and '" + mapTdsChallanFilterSrchData.getTo_date() + "'"));
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                            // Changed filter to tran_ref_date - 16-01-2020
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                            // Changed filter to tran_ref_date - 16-01-2020
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                            // Changed filter to tran_ref_date - 16-01-2020
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                            if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from() + " "));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from() + ""));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                            }
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                            crit.add(Restrictions.eq("pan_4_char_c_nc", mapTdsChallanFilterSrchData.getPan_4th_char()).ignoreCase());//on dropdown value is tds_code
                        }

                        // New added filters--from transaction browse page
                        if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeRefNo())) {
                            crit.add(Restrictions.eq("deductee_ref_code1", mapTdsChallanFilterSrchData.getDeducteeRefNo()).ignoreCase());// Deductee Ref No
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getAccountNo())) {
                            crit.add(Restrictions.eq("account_no", mapTdsChallanFilterSrchData.getAccountNo()).ignoreCase());// Account No
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getBankBranchCode())) {
                            crit.add(Restrictions.eq("bank_branch_code", mapTdsChallanFilterSrchData.getBankBranchCode()).ignoreCase());// Bank Branch Code
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTdsDeductReason())) {
                            crit.add(Restrictions.eq("tds_deduct_reason", mapTdsChallanFilterSrchData.getTdsDeductReason()).ignoreCase());// Tds Deduct Reason
                        }

                    } else {
//                        System.out.println("mapTdsChallanFilterSrchData.getParaRowidSeq()..." + mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                        crit.add(Restrictions.or(rowid_seq, rowid_seq_null));

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                            crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                        }
                    }
                }
            }//end if

            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("validation_client_code", l_workinguser));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
            }

            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("tds_amt"));
            tdsTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (tdsTranList != null && tdsTranList.size() > 0 ? tdsTranList : null);
    }//end method

    @Override
    public Long readTDSDataByCodeCount(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, int minVal, int maxVal, Util utl) {
        Long rowCount = 0l;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsTranLoad.class);
            if (!utl.isnull(search) && search.equalsIgnoreCase("true")) {
                if (mapTdsChallanFilterSrchData != null) {
                    if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                        if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                            Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                            Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                            crit.add(Restrictions.or(rowid_seq, rowid_seq_null));
                        } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {
                            crit.add(Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq()));
                        } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                            crit.add(Restrictions.isNull("tds_challan_rowid_seq"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                            crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getDeductee_name()..." + mapTdsChallanFilterSrchData.getDeductee_name());
                            crit.add(Restrictions.eq("deductee_name", mapTdsChallanFilterSrchData.getDeductee_name()).ignoreCase());//on dropdown value is tds_code
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getPan_no()..." + mapTdsChallanFilterSrchData.getPan_no());
                            crit.add(Restrictions.eq("deductee_panno", mapTdsChallanFilterSrchData.getPan_no()));//deductee_panno
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        System.out.println("to_date");
//                        crit.add(Restrictions.sqlRestriction("tds_deduct_date between '" + mapTdsChallanFilterSrchData.getFrom_date() + "' and '" + mapTdsChallanFilterSrchData.getTo_date() + "'"));
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
                            crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                            if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from() + " "));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from() + ""));
                            } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                                crit.add(Restrictions.sqlRestriction("to_number(tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                            }
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                            crit.add(Restrictions.eq("pan_4_char_c_nc", mapTdsChallanFilterSrchData.getPan_4th_char()).ignoreCase());//on dropdown value is tds_code
                        }

                    } else {
//                        System.out.println("mapTdsChallanFilterSrchData.getParaRowidSeq()..." + mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                        crit.add(Restrictions.or(rowid_seq, rowid_seq_null));

                        if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
//                            System.out.println("mapTdsChallanFilterSrchData.getTds_section()..." + mapTdsChallanFilterSrchData.getTds_section());
                            crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                        }
                    }
                }
            }//end if
//            crit.add(Restrictions.sqlRestriction("EXISTS (SELECT 1 FROM (select Z1.CLIENT_CODE, Z1.CODE_LEVEL from client_mast Z1 START WITH Z1.CLIENT_CODE = '" + l_workinguser + "' CONNECT BY PRIOR Z1.CLIENT_CODE = Z1.PARENT_CODE) W1 WHERE W1.CLIENT_CODE = this_.CLIENT_CODE)"));
            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", l_workinguser));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
            }

            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.setFirstResult(minVal);
            crit.setMaxResults(maxVal);
            crit.addOrder(Order.asc("tds_amt"));
            rowCount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowCount = 0l;
            getSession().getTransaction().rollback();
        }
        return rowCount;
    }//end method

    @Override
    public MapTdsChallanGrossTotalList readTDSDataSumDetail(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, 
            ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, Util utl) {
        List<MapTdsChallanGrossTotalList> tdsTranresult;
        try {
            Criteria crit = getSession().createCriteria(ViewTdsTranLoad.class);

            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.sum("tds_amt"), "tds_amt");
            projList.add(Projections.sum("tran_amt"), "tran_amt");
            projList.add(Projections.sum("tds_rate"), "tds_rate");
            crit.setProjection(projList);

            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getAllocationFilter())) {//only use for challan allocation
                    if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALL")) {
                        Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                        Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                        crit.add(Restrictions.or(rowid_seq, rowid_seq_null));
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("ALC")) {
                        crit.add(Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq()));
                    } else if (mapTdsChallanFilterSrchData.getAllocationFilter().equalsIgnoreCase("UNA")) {
                        crit.add(Restrictions.isNull("tds_challan_rowid_seq"));
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
                        crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getDeductee_name())) {
                        crit.add(Restrictions.eq("deductee_name", mapTdsChallanFilterSrchData.getDeductee_name()).ignoreCase());//on dropdown value is tds_code
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_no())) {
                        crit.add(Restrictions.eq("deductee_panno", mapTdsChallanFilterSrchData.getPan_no()));//deductee_panno
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        System.out.println("to_date");
//                        crit.add(Restrictions.sqlRestriction("tds_deduct_date between '" + mapTdsChallanFilterSrchData.getFrom_date() + "' and '" + mapTdsChallanFilterSrchData.getTo_date() + "'"));
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrchData.getFrom_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTo_date()) && utl.isnull(mapTdsChallanFilterSrchData.getFrom_date())) {
//                        crit.add(Restrictions.sqlRestriction("TO_DATE(tds_deduct_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                        // Changed filter to tran_ref_date - 16-01-2020
                        crit.add(Restrictions.sqlRestriction("TO_DATE(tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrchData.getTo_date() + "', 'DD-MM-RRRR')"));
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_amt_betwn())) {
                        if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) >= " + mapTdsChallanFilterSrchData.getTds_amt_from() + " "));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) <= " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("=")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) = " + mapTdsChallanFilterSrchData.getTds_amt_from() + ""));
                        } else if (mapTdsChallanFilterSrchData.getTds_amt_betwn().equalsIgnoreCase("between")) {
                            crit.add(Restrictions.sqlRestriction("to_number(tds_amt) between " + mapTdsChallanFilterSrchData.getTds_amt_from() + " and " + mapTdsChallanFilterSrchData.getTds_amt_to() + " "));
                        }
                    }

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getPan_4th_char())) {
                        crit.add(Restrictions.eq("pan_4_char_c_nc", mapTdsChallanFilterSrchData.getPan_4th_char()).ignoreCase());//on dropdown value is tds_code
                    }
                } else {
                    Criterion rowid_seq = Restrictions.eq("tds_challan_rowid_seq", mapTdsChallanFilterSrchData.getParaRowidSeq());
                    Criterion rowid_seq_null = Restrictions.isNull("tds_challan_rowid_seq");
                    crit.add(Restrictions.or(rowid_seq, rowid_seq_null));

                    if (!utl.isnull(mapTdsChallanFilterSrchData.getTds_section().trim())) {
                        crit.add(Restrictions.eq("tds_code", mapTdsChallanFilterSrchData.getTds_section().trim()));//on dropdown value is tds_code
                    }
                }
            }
//            crit.add(Restrictions.sqlRestriction("EXISTS (SELECT 1 FROM (select Z1.CLIENT_CODE, Z1.CODE_LEVEL from client_mast Z1 START WITH Z1.CLIENT_CODE = '" + l_workinguser + "' CONNECT BY PRIOR Z1.CLIENT_CODE = Z1.PARENT_CODE) W1 WHERE W1.CLIENT_CODE = this_.CLIENT_CODE)"));
            if (mapTdsChallanFilterSrchData != null) {
                if (!utl.isnull(mapTdsChallanFilterSrchData.getDeducteeLevel()) && mapTdsChallanFilterSrchData.getDeducteeLevel().equalsIgnoreCase("L")) {
                    crit.add(Restrictions.eq("client_code", l_workinguser));
                } else {
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                            + "                   where w1.client_code = this_.client_code\n"
                            + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                            + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
                }
            } else {
                crit.add(Restrictions.sqlRestriction("exists (select 1 from client_mast w1\n"
                        + "                   where w1.client_code = this_.client_code\n"
                        + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                        + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "'))"));
            }
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));

            crit.setResultTransformer(Transformers.aliasToBean(MapTdsChallanGrossTotalList.class));
            tdsTranresult = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            tdsTranresult = null;
            getSession().getTransaction().rollback();
        }
        return tdsTranresult != null && tdsTranresult.size() > 0 ? tdsTranresult.get(0) : null;
    }//end method

}
