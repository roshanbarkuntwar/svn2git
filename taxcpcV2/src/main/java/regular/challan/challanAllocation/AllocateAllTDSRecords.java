/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcChallanAutoAllocate;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanFilterEntity;

/**
 *
 * @author akash.dev
 */
public class AllocateAllTDSRecords extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String search;
    InputStream inputStream;
    private String identifyFlag;
    private String ParaRowidSeq;
    private String ParaTdsAmount;
    private String ParaAllocatedTdsAmount;
    private String allocationStatus;

    public AllocateAllTDSRecords() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
            ViewClientMast clientmast = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            String l_workinguser = clientmast.getClient_code();
            String l_entity_code = clientmast.getEntity_code();
            ChallanAllocationFilterEntity mapTdsChallanFilterSrch = (ChallanAllocationFilterEntity) session.get("MAPTDSCHALLANLOADSRCH"); //get filter from session
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            if (asmt != null) {

                String acc_year = asmt.getAccYear();
                String quarterNo = asmt.getQuarterNo();

                String tds_type_code = asmt.getTdsTypeCode();

                Double ParamTdsAmountValue = 0d;
                if (!utl.isnull(getParaTdsAmount())) {
                    ParamTdsAmountValue = Double.parseDouble(getParaTdsAmount());
                }
//                utl.generateLog("ParamTdsAmountValue..." + ParamTdsAmountValue);

                Double ParamallocatedTdsAmountValue = 0d;
                if (!utl.isnull(getParaAllocatedTdsAmount())) {
                    try {
                        ParamallocatedTdsAmountValue = Double.parseDouble(getParaAllocatedTdsAmount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                utl.generateLog("ParamallocatedTdsAmountValue..." + ParamallocatedTdsAmountValue);

                Double amtToBeallocate = 0d;
                try {
                    amtToBeallocate = (ParamTdsAmountValue - ParamallocatedTdsAmountValue);
                } catch (Exception e) {
                }
//                utl.generateLog("amtToBeallocate..." + amtToBeallocate);

                String l_where_clause = "";
                String rowIdnuulOrNotnull = "";
                if (allocationStatus.equalsIgnoreCase("UNA")) {
                    utl.generateLog("alocation", "");
                    allocationStatus = "ALLOC";//is 
                    rowIdnuulOrNotnull = "and t.tds_challan_rowid_seq is null";
                } else if (allocationStatus.equalsIgnoreCase("ALC")) {
                    utl.generateLog("un-alocation", "");
                    allocationStatus = "UN-ALLOC";
                    rowIdnuulOrNotnull = "and t.tds_challan_rowid_seq ='" + ParaRowidSeq + "'";
                }
                try {

                    if (mapTdsChallanFilterSrch != null) {
                        if (!utl.isnull(mapTdsChallanFilterSrch.getDeducteeLevel()) && mapTdsChallanFilterSrch.getDeducteeLevel().equalsIgnoreCase("L")) {
                            l_where_clause
                                    = " t.client_code = '" + l_workinguser + "'\n"
                                    + "   and t.entity_code = '" + l_entity_code + "'\n"
                                    + "   and t.acc_year = '" + acc_year + "'\n"
                                    + "   and t.quarter_no = '" + quarterNo + "'\n"
                                    + "   and t.tds_type_code = '" + tds_type_code + "' \n"
                                    + rowIdnuulOrNotnull + " \n";
                        } else {
                            l_where_clause
                                    = "exists (select 1 from client_mast w1\n"
                                    + "                   where w1.client_code = t.client_code\n"
                                    + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                                    + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                                    + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "')) \n"
                                    + "   and t.entity_code = '" + l_entity_code + "'\n"
                                    + "   and t.acc_year = '" + acc_year + "'\n"
                                    + "   and t.quarter_no = '" + quarterNo + "'\n"
                                    + "   and t.tds_type_code = '" + tds_type_code + "' \n"
                                    + rowIdnuulOrNotnull + " \n";
                        }
                    } else {
                        l_where_clause
                                = "exists (select 1 from client_mast w1\n"
                                + "                   where w1.client_code = t.client_code\n"
                                + "                   and (w1.client_code = '" + l_workinguser + "' or w1.parent_code = '" + l_workinguser + "' or\n"
                                + "                        w1.g_parent_code = '" + l_workinguser + "' or w1.sg_parent_code = '" + l_workinguser + "' or\n"
                                + "                        w1.ssg_parent_code = '" + l_workinguser + "' or w1.sssg_parent_code = '" + l_workinguser + "')) \n"
                                + "   and t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + acc_year + "'\n"
                                + "   and t.quarter_no = '" + quarterNo + "'\n"
                                + "   and t.tds_type_code = '" + tds_type_code + "' \n"
                                + rowIdnuulOrNotnull + " \n";
                    }
                    if (mapTdsChallanFilterSrch != null) {

                        if (!utl.isnull(mapTdsChallanFilterSrch.getTds_section().trim())) {
                            l_where_clause += "and t.tds_code = '" + mapTdsChallanFilterSrch.getTds_section().trim() + "'\n";
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getDeductee_name())) {
                            l_where_clause += "and t.deductee_name = '" + mapTdsChallanFilterSrch.getDeductee_name() + "'\n";
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getPan_no())) {
                            l_where_clause += "and t.deductee_panno = '" + mapTdsChallanFilterSrch.getPan_no() + "' \n";
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrch.getFrom_date())) {
                            l_where_clause += "and TO_DATE(t.tran_ref_date, 'DD-MON-RRRR') between TO_DATE('" + mapTdsChallanFilterSrch.getFrom_date() + "', 'DD-MM-RRRR') and TO_DATE('" + mapTdsChallanFilterSrch.getTo_date() + "', 'DD-MM-RRRR') \n";
                        }

                        if (utl.isnull(mapTdsChallanFilterSrch.getTo_date()) && !utl.isnull(mapTdsChallanFilterSrch.getFrom_date())) {
                            l_where_clause += "and TO_DATE(t.tran_ref_date, 'DD-MON-RRRR')  >= TO_DATE('" + mapTdsChallanFilterSrch.getFrom_date() + "', 'DD-MM-RRRR') \n";
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getTo_date()) && utl.isnull(mapTdsChallanFilterSrch.getFrom_date())) {
                            l_where_clause += "and TO_DATE(t.tran_ref_date, 'DD-MON-RRRR') <= TO_DATE('" + mapTdsChallanFilterSrch.getTo_date() + "', 'DD-MM-RRRR') \n";
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getTds_amt_betwn())) {
                            if (mapTdsChallanFilterSrch.getTds_amt_betwn().equalsIgnoreCase(">=")) {
                                l_where_clause += "and to_number(t.tds_amt) >= '" + mapTdsChallanFilterSrch.getTds_amt_from() + "' \n";
                            } else if (mapTdsChallanFilterSrch.getTds_amt_betwn().equalsIgnoreCase("<=")) {
                                l_where_clause += "and to_number(t.tds_amt) <= '" + mapTdsChallanFilterSrch.getTds_amt_to() + "' \n";
                            } else if (mapTdsChallanFilterSrch.getTds_amt_betwn().equalsIgnoreCase("=")) {
                                l_where_clause = "and to_number(t.tds_amt) = '" + mapTdsChallanFilterSrch.getTds_amt_from() + "' \n";
                            } else if (mapTdsChallanFilterSrch.getTds_amt_betwn().equalsIgnoreCase("between")) {
                                l_where_clause += "and to_number(t.tds_amt) between '" + mapTdsChallanFilterSrch.getTds_amt_from() + "' and '" + mapTdsChallanFilterSrch.getTds_amt_to() + "' \n";
                            }
                        }

                        if (!utl.isnull(mapTdsChallanFilterSrch.getPan_4th_char())) {
                            l_where_clause += "and t.pan_4_char_c_nc = '" + mapTdsChallanFilterSrch.getPan_4th_char() + "' \n";
                        }
                        //New added filters
                        if (!utl.isnull(mapTdsChallanFilterSrch.getTdsDeductReason())) {
                            l_where_clause += "and t.tds_deduct_reason = '" + mapTdsChallanFilterSrch.getTdsDeductReason() + "' \n";
                        }
                        if (!utl.isnull(mapTdsChallanFilterSrch.getDeducteeRefNo())) {
                            l_where_clause += "and t.deductee_ref_code1 = '" + mapTdsChallanFilterSrch.getDeducteeRefNo() + "' \n";
                        }
                        if (!utl.isnull(mapTdsChallanFilterSrch.getAccountNo())) {
                            l_where_clause += "and t.account_no = '" + mapTdsChallanFilterSrch.getAccountNo() + "' \n";
                        }
                    }
                } catch (Exception e) {
                }

//                utl.generateLog("l_where_clause.." + l_where_clause);
                ProcChallanAutoAllocate objfun = new ProcChallanAutoAllocate();

                String returnResult = objfun.executeSetChallanAllocationAmtFunction(l_where_clause, amtToBeallocate, Integer.parseInt(getParaRowidSeq()), allocationStatus);
                utl.generateLog("returnResult..", returnResult);
//                String string = "004-034556";
                String[] parts = returnResult.split("#");
                String code = parts[0]; // 004
                String message = parts[1]; // 034556
                if (!utl.isnull(code) && code.equalsIgnoreCase("0")) {
                    session.put("ERRORCLASS", MessageType.successMessage);
//                    session.put("session_result", returnResult);
                    l_return_message = "success" + "#" + message;
                } else {
                    l_return_message = "error";
                }
            }//end if
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(String identifyFlag) {
        this.identifyFlag = identifyFlag;
    }

    public String getParaRowidSeq() {
        return ParaRowidSeq;
    }

    public void setParaRowidSeq(String ParaRowidSeq) {
        this.ParaRowidSeq = ParaRowidSeq;
    }

    public String getParaTdsAmount() {
        return ParaTdsAmount;
    }

    public void setParaTdsAmount(String ParaTdsAmount) {
        this.ParaTdsAmount = ParaTdsAmount;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public String getParaAllocatedTdsAmount() {
        return ParaAllocatedTdsAmount;
    }

    public void setParaAllocatedTdsAmount(String ParaAllocatedTdsAmount) {
        this.ParaAllocatedTdsAmount = ParaAllocatedTdsAmount;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
