/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranCorrection;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class TdsChallanTranCorrectionDAOImpl extends GenericHibernateDAO<TdsChallanTranCorrection, Serializable>
        implements TdsChallanTranCorrectionDAO {

    @Override
    public TdsChallanTranCorrection readChallanBySequenceID(Long rowid_seq) {
        Criterion cltcode = Restrictions.eq("rowid_seq", rowid_seq);
        List<TdsChallanTranCorrection> uselist = readByCriteria(cltcode);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }

    @Override
    public LinkedHashMap<String, String> getChallanCorrAsListFilter(Assessment asmt, ViewClientMast clientmast, String justification_flag, Util utl) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        try {
            if (asmt != null) {

                String quarterNumber = asmt.getQuarterNo();
                Criteria crit = getSession().createCriteria(TdsChallanTranCorrection.class);
                crit.add(Restrictions.eq("entity_code", clientmast.getEntity_code()));
                crit.add(Restrictions.eq("client_code", clientmast.getClient_code()));
                crit.add(Restrictions.eq("acc_year", asmt.getAccYear()));
                crit.add(Restrictions.eq("quarter_no", Long.parseLong(quarterNumber)));
//                crit.add(Restrictions.eq("quarter_no", Long.parseLong(quarterNumber)));
                crit.add(Restrictions.eq("tds_type_code", asmt.getTdsTypeCode()));
                if (!utl.isnull(justification_flag)) {
                    crit.add(Restrictions.sqlRestriction(" exists (select 1  \n"
                            + "               from VIEW_TDS_TRAN_CORR_DETAIL b\n"
                            + "               where b.entity_code=this_.entity_code\n"
                            + "               and   b.client_code=this_.client_code\n"
                            + "               and   b.acc_year=this_.acc_year\n"
                            + "               and   b.quarter_no=this_.quarter_no\n"
                            + "               and   b.tds_type_code=b.tds_type_code\n"
                            + "               and   b.tds_challan_slno=this_.tds_challan_slno\n"
                            + "               and   b.justification_code='" + justification_flag + "')"));
                }
                crit.addOrder(Order.asc("tds_challan_no"));
                List<TdsChallanTranCorrection> list = crit.list();
                if (list != null) {
                    try {
                        for (int i = 0; i < list.size(); i++) {
                            TdsChallanTranCorrection tdsChallanTranCorrection = list.get(i);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String challanListValue = "";
                            challanListValue += sdf.format(tdsChallanTranCorrection.getTds_challan_date()) + "-";
                            if (!utl.isnull(tdsChallanTranCorrection.getTds_challan_no())) {
                                challanListValue += tdsChallanTranCorrection.getTds_challan_no() + "-";
                            } else {
                                challanListValue += "NA" + "-";
                            }
                            if (!utl.isnull(tdsChallanTranCorrection.getBank_bsr_code())) {
                                challanListValue += tdsChallanTranCorrection.getBank_bsr_code() + "-";
                            } else {
                                challanListValue += "NA" + "-";
                            }
                            DecimalFormat df = new DecimalFormat("0.00");
                            if (tdsChallanTranCorrection.getTds_challan_tds_amt() != null && tdsChallanTranCorrection.getTds_challan_tds_amt() > 0l) {
                                challanListValue += df.format(tdsChallanTranCorrection.getTds_challan_tds_amt());
                            } else {
                                challanListValue += "0.0";
                            }
                            map.put(String.valueOf(tdsChallanTranCorrection.getTds_challan_slno()), challanListValue);//challan slno as per girish sir 22.9.16
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
        }
        return map != null && map.size() > 0 ? map : null;
    }

}
