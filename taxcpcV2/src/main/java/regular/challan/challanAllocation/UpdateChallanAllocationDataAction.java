/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan.challanAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsChallanRebuild;
import dao.TdsChallanTranLoadDAO;
import dao.TdsTranLoadDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author gaurav.khanzode
 */
public class UpdateChallanAllocationDataAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    InputStream inputStream;
    private String ArrRowIdSeq;
    private String ParaRowidSeq;
    private String identifyFlag;
    private String validationClientCode;
    Util utl;

    public UpdateChallanAllocationDataAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String responseValue = "error";

        String[] arrRowidSeq = ArrRowIdSeq.split(",");
        ArrayList<String> arLstRowid = new ArrayList<String>();
        arLstRowid.addAll(Arrays.asList(arrRowidSeq));
        utl.generateLog("Rowid:", arLstRowid);
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
        ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());

        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        String a_entity_code = client.getEntity_code();
        String a_client_code = client.getClient_code();
        String a_acc_year = asmt.getAccYear();
        String a_quarter_no = asmt.getQuarterNo();
        Date a_from_date = asmt.getQuarterFromDate();
        Date a_to_date = asmt.getQuarterToDate();
        String a_tds_type_code = asmt.getTdsTypeCode();
        int a_tds_challan_rowid_seq = 0;
        TdsTranLoadDAO tdstranloaddao;
        TdsChallanTranLoadDAO tdsChallanTranLoaddao;
        //Long challanRowId =0L;

        ArrayList<TdsTranLoad> arr = new ArrayList<TdsTranLoad>();
        Boolean allRecordsUpdated = false;
        long parseLong = 0;
        int count = 0;
        int totalrowseqid = 0;
        for (String l_str : arLstRowid) {
            String[] l_split_data = l_str.split("#");
            String rowidSeq = l_split_data[0];
            String tdsChallenRowidSeq = l_split_data[1];
            String flag = l_split_data[2];
            System.out.println("flag---" + flag);
            rowidSeq = utl.isnull(rowidSeq) ? "" : rowidSeq.trim();
            tdsChallenRowidSeq = utl.isnull(tdsChallenRowidSeq) ? "" : tdsChallenRowidSeq.trim();

            flag = utl.isnull(flag) ? "" : flag.trim();
            parseLong = Long.parseLong(rowidSeq);

//            if (flag.equals("false") && !"".equals(tdsChallenRowidSeq)) {
//                System.out.println("unchecked....");
////                tdstranloaddao = factory.getTdsTranLoadDAO();
////                TdsTranLoad tdstranloaddata = tdstranloaddao.readTDSBySequenceID(parseLong);
////                if (tdstranloaddata != null) {
////                    tdstranloaddata.setTds_challan_rowid_seq("");
////                    //tdstran.setValidation_client_code("");
////                    arr.add(tdstranloaddata);
////                    totalrowseqid++;
////                }
//            } else 
            if (flag.equals("true") && tdsChallenRowidSeq.equals("")) {

                Long rowIdparseLong = Long.parseLong(getParaRowidSeq());
                System.out.println("for get tdschallantranload rowIdparseLong" + rowIdparseLong);
                tdsChallanTranLoaddao = factory.getTdsChallanTranLoadDAO();
                TdsChallanTranLoad tdsChallanTranLoad = tdsChallanTranLoaddao.readChallanBySequenceID(rowIdparseLong);
                if (tdsChallanTranLoad != null) {
                    System.out.println("get client code from tdsChallanTranLoad--" + tdsChallanTranLoad.getClient_code());
                    setValidationClientCode(tdsChallanTranLoad.getClient_code());
                }

                tdstranloaddao = factory.getTdsTranLoadDAO();
                TdsTranLoad tdstran = tdstranloaddao.readTDSBySequenceID(parseLong);

                if (tdstran != null) {
                    System.out.println("getParaRowidSeq1--" + getParaRowidSeq());
                    System.out.println("TdsTranLoad--->" + tdstran.toString());
                    tdstran.setTds_challan_rowid_seq(getParaRowidSeq());
                    tdstran.setValidation_client_code(getValidationClientCode());
                    arr.add(tdstran);
                    totalrowseqid++;
                }
            } else if (flag.equals("false") && !"".equals(tdsChallenRowidSeq)) {
                System.out.println("getParaRowidSeq2--" + getParaRowidSeq());
                tdstranloaddao = factory.getTdsTranLoadDAO();
                TdsTranLoad tdstran = tdstranloaddao.readTDSBySequenceID(parseLong);
                if (tdstran != null) {
                    //tdstran.setTds_challan_rowid_seq(getParaRowidSeq());
                    tdstran.setTds_challan_rowid_seq("");
                    tdstran.setValidation_client_code("");
                    arr.add(tdstran);
                    totalrowseqid++;
                }
            }
        }//end for

        Session dbssn = getSessionFactory().getCurrentSession();
        try {
            System.out.println("Session status: " + dbssn.isOpen());
            dbssn.beginTransaction();
            for (int i = 0; i < arr.size(); i++) {
                dbssn.update(arr.get(i));
                count += 1;
            }
            System.out.println("count...." + count);
            if (count == totalrowseqid) {
                dbssn.flush();
                dbssn.clear();
                dbssn.getTransaction().commit();
                allRecordsUpdated = true;
            } else {
                dbssn.clear();
                dbssn.getTransaction().rollback();
                allRecordsUpdated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (dbssn != null) {
                dbssn.clear();
                dbssn.getTransaction().rollback();
                allRecordsUpdated = false;
            }
        }

        //System.out.println("allRecordsUpdated...." + allRecordsUpdated);
        if (allRecordsUpdated) {
            try {
                utl.generateLog("", "procedure called START after Allocation:Lhs_tds.proc_tds_challan_rebuild");
                a_tds_challan_rowid_seq = Integer.parseInt(utl.isnull(getParaRowidSeq()) ? "0" : getParaRowidSeq());
                ProcTdsChallanRebuild callProc = new ProcTdsChallanRebuild();
                String a_process_error = callProc.executeProcedure(a_entity_code, a_client_code, a_acc_year, a_quarter_no, a_from_date, a_to_date, a_tds_type_code, a_tds_challan_rowid_seq, "");
                session.put("ERRORCLASS", ErrorType.successMessage);
                utl.generateLog("", "procedure called END after Allocation:Lhs_tds.proc_tds_challan_rebuild");
//                session.put("session_result", "Record Update Successfully");
                responseValue = "success";
            } catch (Exception e) {
                e.printStackTrace();
                responseValue = "error";
            }
        } else {
            responseValue = "error";
        }

        inputStream = new ByteArrayInputStream(responseValue.getBytes("UTF-8"));
        return responseValue;
    }//end method

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getArrRowIdSeq() {
        return ArrRowIdSeq;
    }

    public void setArrRowIdSeq(String ArrRowIdSeq) {
        this.ArrRowIdSeq = ArrRowIdSeq;
    }

    public String getParaRowidSeq() {
        return ParaRowidSeq;
    }

    public void setParaRowidSeq(String ParaRowidSeq) {
        this.ParaRowidSeq = ParaRowidSeq;
    }

    public String getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(String identifyFlag) {
        this.identifyFlag = identifyFlag;
    }

    public String getValidationClientCode() {
        return validationClientCode;
    }

    public void setValidationClientCode(String validationClientCode) {
        this.validationClientCode = validationClientCode;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
