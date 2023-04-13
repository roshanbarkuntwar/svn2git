package regular.dashboard.ldcAllocation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class LowDeduCertifiAllocaAjax extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnView = "success";
        String returnMsg = "error";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String acc_year = asmt.getAccYear();
            String quarter_no = asmt.getQuarterNo();
            String tds_type_code = asmt.getTdsTypeCode();
            ViewClientMast clientmast = factory.getViewClientMastDAO().readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            if (!utl.isnull(getAction()) && clientmast != null) {
                if (getAction().equalsIgnoreCase("updateCertificate") && getEntity() != null) {
                    try {
                        int status = updateEntityFromBtnOnLowDeduCertifiAlloca(getEntity());
                        returnMsg = "" + status;

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (getAction().equalsIgnoreCase("submitAllAllocate")) {
                    int updateRow = 0;
                    try {
                        if (getBeanList() != null) {
                            for (LowDeduCertifiAllocaBean2 bean : getBeanList()) {
                                if (!utl.isnull(bean.getCheckBoxSelect())) {
                                    TdsTranLoad obj = new TdsTranLoad();
                                    obj.setCertificate_no(bean.getCertificate_no());
                                    obj.setEntity_code(bean.getEntity_code());
                                    obj.setClient_code(bean.getClient_code());
                                    obj.setAcc_year(bean.getAcc_year());
                                    obj.setQuarter_no(bean.getQuarter_no());
                                    obj.setTds_type_code(bean.getTds_type_code());
                                    obj.setDeductee_panno(bean.getDeductee_panno());
                                    obj.setTds_code(bean.getTds_code());

                                    if (!utl.isnull(obj.getCertificate_no())) {
                                        int status = updateEntityFromBtnOnLowDeduCertifiAlloca(obj);
                                        if (status > 0) {
                                            updateRow++;
                                        }
                                    }

                                }

                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    returnMsg = "success#" + updateRow;
                } else if (getAction().equalsIgnoreCase("allocateCertificate")) {
                    if (utl.isnull(getDeductee_panno()) && utl.isnull(getCertificateNo()) && utl.isnull(getTds_code())) {
                        returnMsg = "error#No certificates found to allocate.";
                    } else {
                        try {
                            TdsTranLoad obj = new TdsTranLoad();

                            obj.setCertificate_no(getCertificateNo());
                            obj.setEntity_code(clientmast.getEntity_code());
                            obj.setClient_code(clientmast.getClient_code());
                            obj.setAcc_year(asmt.getAccYear());
                            obj.setQuarter_no(asmt.getQuarterNo());
                            obj.setTds_type_code(asmt.getTdsTypeCode());
                            obj.setDeductee_panno(getDeductee_panno());
                            obj.setTds_code(getTds_code());
                            int updateResult = allocateSingleCertificateTranEntry(obj);

                            if (updateResult > 0) {
                                returnMsg = "success#Certificate allocated successfully.";
                            } else if (updateResult == -1) {
                                returnMsg = "error#Some error occurred.";
                            } else {
                                returnMsg = "error#No certificates found to allocate.";
                            }
                        } catch (Exception e) {
                            returnMsg = "error#Some error occurred.";
                            e.printStackTrace();
                        }
                    }
                } else if (getAction().equalsIgnoreCase("unallocateCertificate")) {
                    if (utl.isnull(getDeductee_panno()) && utl.isnull(getCertificateNo()) && utl.isnull(getTds_code())) {
                        returnMsg = "error#No certificates found to unallocate.";
                    } else {
                        try {
                            TdsTranLoad obj = new TdsTranLoad();

                            obj.setCertificate_no(getCertificateNo());
                            obj.setEntity_code(clientmast.getEntity_code());
                            obj.setClient_code(clientmast.getClient_code());
                            obj.setAcc_year(asmt.getAccYear());
                            obj.setQuarter_no(asmt.getQuarterNo());
                            obj.setTds_type_code(asmt.getTdsTypeCode());
                            obj.setDeductee_panno(getDeductee_panno());
                            obj.setTds_code(getTds_code());
                            int updateResult = updateNullEntityFromBtnOnLowDeduCertifiAlloca(obj);

                            if (updateResult > 0) {
                                returnMsg = "success#Certificate unallocated successfully.";
                            } else if (updateResult == -1) {
                                returnMsg = "error#Some error occurred.";
                            } else {
                                returnMsg = "error#No certificates found to unallocate.";
                            }
                        } catch (Exception e) {
                            returnMsg = "error#Some error occurred.";
                            e.printStackTrace();
                        }
                    }
                } else if (getAction().equalsIgnoreCase("submitAllUnAllocate")) {
                    int updateRow = 0;
                    try {
                        if (getBeanList() != null) {
                            for (LowDeduCertifiAllocaBean2 bean : getBeanList()) {
                                if (!utl.isnull(bean.getCheckBoxSelect())) {

                                    TdsTranLoad obj = new TdsTranLoad();
                                    obj.setCertificate_no(bean.getCertificate_no());
                                    obj.setEntity_code(bean.getEntity_code());
                                    obj.setClient_code(bean.getClient_code());
                                    obj.setAcc_year(bean.getAcc_year());
                                    obj.setQuarter_no(bean.getQuarter_no());
                                    obj.setTds_type_code(bean.getTds_type_code());
                                    obj.setDeductee_panno(bean.getDeductee_panno());
                                    obj.setTds_code(bean.getTds_code());

                                    if (!utl.isnull(obj.getCertificate_no())) {
                                        int status = updateNullEntityFromBtnOnLowDeduCertifiAlloca(obj);
                                        if (status > 0) {
                                            updateRow++;
                                        }
                                    }

                                }// Selected
                            }// End For
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    returnMsg = "success#" + updateRow;
                } else if (getAction().equalsIgnoreCase("showCertificateList") && entity != null) {
                    try {
//                        tdsSplRateMastList = factory.getTdsSplRateMastDAO().readTdsSplRateForLowDeduCertifiAlloca(getTdsSplRateMast());
//                        returnMsg = new LowDeduCertifiAllocaAjaxSupp().getCertificateListData(tdsSplRateMastList, utl);
                        List<TdsTranLoad> certificateDetails = factory.getTdsTranLoadDAO().getCertificateDetails(entity);

                        returnMsg = new LowDeduCertifiAllocaAjaxSupp().getCertificateListData1(certificateDetails, utl);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (getAction().equalsIgnoreCase("unAllocatedCertificate") && getEntity() != null) {
                    try {
                        TdsTranLoad tdsTranLoad = factory.getTdsTranLoadDAO().getObjForLowDeduCertifiAlloca(getEntity());
                        tdsTranLoad.setCertificate_no(null);
                        int status = updateNullEntityFromBtnOnLowDeduCertifiAlloca(getEntity());
//                        boolean status = factory.getTdsTranLoadDAO().update(tdsTranLoad);
                        returnMsg = "" + status;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if(getAction().equalsIgnoreCase("updatenotAllocatedAFlag") ){
                    int updateRow = 0;
                    try {
                        if (getBeanList() != null) {
                            for (LowDeduCertifiAllocaBean2 bean : getBeanList()) {
                                if (!utl.isnull(bean.getCheckBoxSelect())) {

                                   

                                    if (!utl.isnull(bean.getRowid_seq())) {
                                        int status = updateDataFor(bean.getRowid_seq());
                                        if (status > 0) {
                                            updateRow++;
                                        }
                                    }

                                }// Selected
                            }// End For
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    returnMsg = "success#" + updateRow;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(returnMsg.getBytes("UTF-8"));
        return returnView;
    }

    public LowDeduCertifiAllocaAjax() {
        utl = new Util();

    }

    private int updateEntityFromBtnOnLowDeduCertifiAlloca(TdsTranLoad obj) {
        int result = 0;
        try {
            StringBuilder ldAllocationSql = new StringBuilder();
            ldAllocationSql.append("update tds_tran_load a set certificate_no = '").append(obj.getCertificate_no()).append("'")
                    .append(" where entity_code = '").append(obj.getEntity_code()).append("'")
                    //                    .append(" and client_code = '").append(obj.getClient_code()).append("'")
                    .append(" and acc_year = '").append(obj.getAcc_year()).append("'")
                    .append(" and quarter_no = '").append(obj.getQuarter_no()).append("'")
                    .append(" and tds_type_code = '").append(obj.getTds_type_code()).append("'")
                    .append(" and deductee_panno = '").append(obj.getDeductee_panno()).append("'")
                    .append(" and tds_code = '").append(obj.getTds_code()).append("'")
                    .append(" and tds_deduct_reason = 'A' ")
                    .append(" and certificate_no is null \n")
                    .append(" and exists (select 1\n")
                    .append("               from   client_mast b\n")
                    .append("               where  b.entity_code=a.entity_code\n")
                    .append("               and    b.client_code=a.client_code\n")
                    .append("               and    (b.client_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.g_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.ssg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sssg_parent_code='").append(obj.getClient_code()).append("'")
                    .append("                      )")
                    .append("               )");


            result = new DbFunctionExecutorAsIntegar().execute_oracle_update_query_integar(ldAllocationSql.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }//End Method

    private int allocateSingleCertificateTranEntry(TdsTranLoad obj) {
        int result = 0;
        try {
            StringBuilder ldAllocationSql = new StringBuilder();
            ldAllocationSql.append("update tds_tran_load a set certificate_no = '").append(obj.getCertificate_no()).append("',\n")
                    .append(" tds_deduct_reason = 'A'")
                    .append(" where entity_code = '").append(obj.getEntity_code()).append("'")
                    .append(" and acc_year = '").append(obj.getAcc_year()).append("'")
                    .append(" and quarter_no = '").append(obj.getQuarter_no()).append("'")
                    .append(" and tds_type_code = '").append(obj.getTds_type_code()).append("'")
                    .append(" and deductee_panno = '").append(obj.getDeductee_panno()).append("'")
                    .append(" and tds_code = '").append(obj.getTds_code()).append("'")
                    .append(" and certificate_no is null \n")
                    .append(" and exists (select 1\n")
                    .append("               from   client_mast b\n")
                    .append("               where  b.entity_code=a.entity_code\n")
                    .append("               and    b.client_code=a.client_code\n")
                    .append("               and    (b.client_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.g_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.ssg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sssg_parent_code='").append(obj.getClient_code()).append("'\n")
                    .append("                      ))");

         //   System.out.println(" certificate allocation query-->"+ldAllocationSql.toString());
            result = new DbFunctionExecutorAsIntegar().execute_oracle_update_query_integar(ldAllocationSql.toString());

        } catch (Exception e) {
            result = -1;
            e.printStackTrace();
        }
        return result;
    }//End Method

    private int updateNullEntityFromBtnOnLowDeduCertifiAlloca(TdsTranLoad obj) {
        int result = 0;
        try {
            StringBuilder ldUnallocation = new StringBuilder();
            ldUnallocation.append(" update tds_tran_load a set certificate_no = null , tds_deduct_reason = null")
                    .append(" where entity_code = '").append(obj.getEntity_code()).append("'")
                    .append(" and acc_year = '").append(obj.getAcc_year()).append("'")
                    .append(" and quarter_no = '").append(obj.getQuarter_no()).append("'")
                    .append(" and tds_type_code = '").append(obj.getTds_type_code()).append("'")
                    .append(" and deductee_panno = '").append(obj.getDeductee_panno()).append("'")
                    .append(" and tds_code = '").append(obj.getTds_code()).append("'")
                    .append(" and tds_deduct_reason = 'A' ")
                    .append(" and certificate_no ='").append(obj.getCertificate_no()).append("'\n")
                    .append(" and exists (select 1\n")
                    .append("               from   client_mast b\n")
                    .append("               where  b.entity_code=a.entity_code\n")
                    .append("               and    b.client_code=a.client_code\n")
                    .append("               and    (b.client_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.g_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.ssg_parent_code='").append(obj.getClient_code()).append("' or\n")
                    .append("                       b.sssg_parent_code='").append(obj.getClient_code()).append("'")
                    .append("                      ))");


            result = new DbFunctionExecutorAsIntegar().execute_oracle_update_query_integar(ldUnallocation.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
    private int updateDataFor(String rowIdseq) {
        
        int result = 0;
        try {
            StringBuilder ldUnallocation = new StringBuilder();
            ldUnallocation.append(" Update tds_tran_load a set a.tds_deduct_reason='A', a.tds_challan_rowid_seq=null").append(" where rowid_seq="+rowIdseq);
           //   System.out.println("updated queryyyy xyz  "+ldUnallocation.toString());
            result = new DbFunctionExecutorAsIntegar().execute_oracle_update_query_integar(ldUnallocation.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private Map<String, Object> session;
    private String action;
    private String certificateNo;
    private String deductee_panno;
    private String tds_code;
    private InputStream inputStream;
    private Util utl;
    private TdsTranLoad entity;
    private TdsSplRateMast tdsSplRateMast;
    private List<TdsSplRateMast> tdsSplRateMastList;
    private ArrayList<LowDeduCertifiAllocaBean2> beanList;

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public TdsTranLoad getEntity() {
        return entity;
    }

    public void setEntity(TdsTranLoad entity) {
        this.entity = entity;
    }

    public ArrayList<LowDeduCertifiAllocaBean2> getBeanList() {
        return beanList;
    }

    public void setBeanList(ArrayList<LowDeduCertifiAllocaBean2> beanList) {
        this.beanList = beanList;
    }

    public TdsSplRateMast getTdsSplRateMast() {
        return tdsSplRateMast;
    }

    public void setTdsSplRateMast(TdsSplRateMast tdsSplRateMast) {
        this.tdsSplRateMast = tdsSplRateMast;
    }

    public List<TdsSplRateMast> getTdsSplRateMastList() {
        return tdsSplRateMastList;
    }

    public void setTdsSplRateMastList(List<TdsSplRateMast> tdsSplRateMastList) {
        this.tdsSplRateMastList = tdsSplRateMastList;
    }

}
