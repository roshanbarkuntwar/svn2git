/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.TdsChallanTranLoadDAO;
import dao.TdsTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class ChallanCRUDAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;

    private String action;
    private String challanflag;
    private InputStream inputStream;
    private TdsChallanTranLoad objChallan;
    private LinkedHashMap<String, String> selectSection;
    private TreeMap<String, String> selectMinorHead;
    private String saveAndContinue;
    private Double mapAllocatedAmount;
    private Long rowidSeq;
    private String readonly;
    private String procrssFlag;
 
    public ChallanCRUDAction() {

        utl = new Util();
        selectSection = new LinkedHashMap<>();
        selectSection.put("", "Select Section");

        selectMinorHead = new TreeMap<>();
        selectMinorHead.put("200", "Normal Tax");
        selectMinorHead.put("400", "Assessment Tax");
    }

    @Override
    public String execute() throws Exception {//System.out.println("inside my class");

        String l_return_value = "success";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy");

        String l_return_message = "Some Error Occured";
        setAction(getAction());

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            if (!utl.isnull(getAction())) {
                ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
                
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                UserMast user = (UserMast) session.get("LOGINUSER");

                String tds_type_code = asmt.getTdsTypeCode();
                String quarterno = asmt.getQuarterNo();
                String accyr = asmt.getAccYear();
                Date quarterFromDate = asmt.getQuarterFromDate();
                Date quarterToDate = asmt.getQuarterToDate();
                String l_to_date = (sdfnew.format(quarterToDate)).toUpperCase();

                if (getAction().equalsIgnoreCase("save")) {
                    try {
                        DbFunctionExecutorAsIntegar objDBFunction = new DbFunctionExecutorAsIntegar();
                        String rowid_seqQuery = "select tds_challan_rowid.Nextval from dual";
                        Long challan_id = objDBFunction.execute_oracle_function_as_integar(rowid_seqQuery);
                        getObjChallan().setRowid_seq(challan_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String Nil_challan_flag = "";
                    String Book_entry_flag = "";
                    if ("on".equals(getObjChallan().getNil_challan_flag())) {
                        Nil_challan_flag = "Y";
                    } else {
                        Nil_challan_flag = "N";
                    }

                    if ("on".equals(getObjChallan().getBook_entry_flag())) {
                        Book_entry_flag = "Y";
                    } else {
                        Book_entry_flag = "N";
                    }

                    if (!utl.isnull(Nil_challan_flag) && Nil_challan_flag.equalsIgnoreCase("Y")) {
                        try {
                            if (!utl.isnull(getObjChallan().getTds_code())) {
                                try {
                                    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                    String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + getObjChallan().getTds_code() + "'";
                                    String resultValue = ef.execute_oracle_function_as_string(functionString);
                                    getObjChallan().setTds_section(resultValue);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            String l_month = "";
                            if (!utl.isnull(getObjChallan().getTds_challan_date())) {
                                try {
                                    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                    String functionString = "select TO_CHAR(TO_DATE('" + getObjChallan().getTds_challan_date() + "','DD-MM-RRRR'), 'MON') from DUAL t";
                                    l_month = ef.execute_oracle_function_as_string(functionString);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            Date challanDate = sdf.parse(getObjChallan().getTds_challan_date());
                            String l_challan_date_val = (sdfnew.format(challanDate)).toUpperCase();

                            DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                            getObjChallan().setMonth(null);
                            getObjChallan().setTds_type_code(tds_type_code);
                            getObjChallan().setEntity_code(client.getEntity_code());
                            getObjChallan().setClient_code(client.getClient_code());
                            getObjChallan().setAcc_year(accyr);
                            getObjChallan().setTds_challan_date(l_challan_date_val);
                            getObjChallan().setQuarter_no(quarterno);
                            getObjChallan().setFrom_date(quarterFromDate);
                            getObjChallan().setTo_date(quarterToDate);
//                            getObjChallan().setTds_challan_date(null);
                            getObjChallan().setLastupdate(new Date());
                            getObjChallan().setUser_code(user.getUser_code());
                            getObjChallan().setNil_challan_flag(Nil_challan_flag);
                            getObjChallan().setBook_entry_flag(Book_entry_flag);
                            getObjChallan().setInsert_flag("F");
                            getObjChallan().setMonth(l_month);
                            getObjChallan().setData_entry_mode("M");
                            getObjChallan().setCsi_verify_flag("M");
                            getObjChallan().setCsi_verify_timestamp(new Date());
                            TdsChallanTranLoadDAO challandao = factory.getTdsChallanTranLoadDAO();
                            Long save_result = challandao.saveAndReturnSeqnoNO(getObjChallan());

                            if (save_result > 0) {
                                if (!utl.isnull(getSaveAndContinue()) && getSaveAndContinue().equalsIgnoreCase("true")) {
//                                    session.put("session_result", "Record Saved Successfully");
                                    l_return_message = "success";
                                } else {
//                                    session.put("session_result", "Record Saved Successfully");
                                    l_return_message = "success1";
                                }
                            } else {
                                l_return_message = "Could not save, Some Error Occured";
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        if (!utl.isnull(getObjChallan().getTds_code())) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + getObjChallan().getTds_code() + "'";
                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                getObjChallan().setTds_section(resultValue);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        String l_month = "";
                        if (!utl.isnull(getObjChallan().getTds_challan_date())) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select TO_CHAR(TO_DATE('" + getObjChallan().getTds_challan_date() + "','DD-MM-RRRR'), 'MON') from DUAL t";
                                l_month = ef.execute_oracle_function_as_string(functionString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        Date challanDate = sdf.parse(getObjChallan().getTds_challan_date());
                        String l_challan_date_val = (sdfnew.format(challanDate)).toUpperCase();

                        getObjChallan().setTds_type_code(tds_type_code);
                        getObjChallan().setEntity_code(client.getEntity_code());
                        getObjChallan().setClient_code(client.getClient_code());
                        getObjChallan().setAcc_year(accyr);
                        getObjChallan().setQuarter_no(quarterno);
                        getObjChallan().setFrom_date(quarterFromDate);
                        getObjChallan().setTo_date(quarterToDate);
                        getObjChallan().setData_entry_mode("M");
                        getObjChallan().setTds_challan_date(l_challan_date_val);
                        getObjChallan().setUser_code(user.getUser_code());
                        getObjChallan().setMonth(l_month);
                        getObjChallan().setLastupdate(new Date());
                        getObjChallan().setNil_challan_flag(Nil_challan_flag);
                        getObjChallan().setBook_entry_flag(Book_entry_flag);
                        getObjChallan().setInsert_flag("F");
                        getObjChallan().setCsi_verify_flag("P");

                        TdsChallanTranLoadDAO challandao = factory.getTdsChallanTranLoadDAO();
                        Long save_result = challandao.saveAndReturnSeqnoNO(getObjChallan());
                        if (save_result > 0) {

                            if (!utl.isnull(getSaveAndContinue()) && getSaveAndContinue().equalsIgnoreCase("true")) {
//                                session.put("session_result", "Record Saved Successfully");
                                l_return_message = "success";

                            } else {
//                                session.put("session_result", "Record Saved Successfully");
                                l_return_message = "success1";
                            }
                        } else {
                            l_return_message = "Could not save, Some Error Occured";
                        }
                    }
                } else if (getAction().equalsIgnoreCase("DeleteChallan")) {
                    TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                    List<TdsTranLoad> readDeducteesForChallan = tdsTranLoadDAO.readDeducteesForChallan(String.valueOf(getRowidSeq()));
                    if (readDeducteesForChallan != null && readDeducteesForChallan.size() > 0) {
                        if (!utl.isnull(getChallanflag()) && getChallanflag().equalsIgnoreCase("true")) {
                            for (int i = 0; i < readDeducteesForChallan.size(); i++) {
                                TdsTranLoad get = readDeducteesForChallan.get(i);
                                tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                                tdsTranLoadDAO.delete(get);
                            }
                        } else {
                            for (int i = 0; i < readDeducteesForChallan.size(); i++) {
                                TdsTranLoad get = readDeducteesForChallan.get(i);
                                get.setTds_challan_rowid_seq("");
                                tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                                tdsTranLoadDAO.update(get);
                            }
                        }

                    }

                    TdsChallanTranLoadDAO challandao = factory.getTdsChallanTranLoadDAO();
                    TdsChallanTranLoad challan = challandao.readChallanBySequenceID(getRowidSeq());
                    if (challan != null) {
                        challan.setRowid_seq(getRowidSeq());
                    }
                    challandao = factory.getTdsChallanTranLoadDAO();
                    boolean delete_result = challandao.delete(challan);
                    if (delete_result) {
                        // session.put("ERRORCLASS", ErrorType.successMessage);
                        // session.put("session_result", "Record Deleted Successfully");
                    } else {
                        // session.put("session_result", "Could not delete, some error occured");
                    }
                    l_return_value = "deleteSuccess";
                } else if (getAction().equalsIgnoreCase("Edit")) {
                    //System.out.println("inside edit");
                    setReadonly("false");
                    try {
                        setSelectSection(factory.getTdsMastDAO().getSectionAsHashMap(asmt.getTdsTypeCode(), null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   TdsChallanTranLoadDAO chalantran = factory.getTdsChallanTranLoadDAO();
                   TdsChallanTranLoad objchallan = chalantran.readChallanBySequenceID(getRowidSeq());
                   System.out.println("objchallan--->"+objchallan.getTds_challan_tds_amt());
                    // /    objchallan.setTds_challan_date(objChallan.getTds_challan_date());System.out.println("update1");
                   // objchallan.setTds_challan_tds_amt("10021.00");
                    if(objchallan.getTds_challan_tds_amt()!=null){
                    if(objchallan.getTds_challan_tds_amt().matches("^\\d+\\.\\d+")){
                       String amount[]= objchallan.getTds_challan_tds_amt().split("\\.");
                       objchallan.setTds_challan_tds_amt(amount[0]);
                    }
                    }
                   setObjChallan(objchallan);
//                   SimpleDateFormat sdfx = new SimpleDateFormat("dd-MMM-yyyy");
//                   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                  // Date tranRefDate = sdfx.parse(objchallan.getTds_challan_date());
                   //String l_tran_ref_date = (sdf1.format(tranRefDate));
                   //String l_tran_ref_date = (sdf1.format( objchallan.getTds_challan_date()));
                  //  objchallan.setTds_challan_date(l_tran_ref_date);
                   //getObjChallan().setTds_challan_date(l_tran_ref_date);
                  /// objChallan.setTds_challan_date(l_tran_ref_date);System.out.println("update2");
                  
                    try {
                     //Due to slow data processing,Comment old Query 
                     /*   TdsTranLoadDAO tdstrandao = factory.getTdsTranLoadDAO();
                        List<TdsTranLoad> objtdstrandao = tdstrandao.readTdsChallanAmount(String.valueOf(getRowidSeq()), client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), tds_type_code);
                      // System.out.println("list size is ***-->"+objtdstrandao.size());
                         Double allocatedAmount = 0d;
                        if (objtdstrandao != null) {
                            for (TdsTranLoad tdsTranLoaddata : objtdstrandao) {
                                String l_tds_amount = tdsTranLoaddata.getTds_base_amt();
                                allocatedAmount += Double.parseDouble(utl.isnull(l_tds_amount) ? "0" : l_tds_amount);
                            }
                        }*/
                     //new Query for get allocatedAmount  
                     long allocatedAmount= getAllocatedAmountQuery(String.valueOf(getRowidSeq()), client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), tds_type_code);
                     setMapAllocatedAmount((double)allocatedAmount); 
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    l_return_value = "edit";
                } else if (getAction().equalsIgnoreCase("View")) {
                    setReadonly("true");
                    TdsChallanTranLoadDAO chalantran = factory.getTdsChallanTranLoadDAO();
                    TdsChallanTranLoad objchallan = chalantran.readChallanBySequenceID(getRowidSeq());
//                   SimpleDateFormat sdfx = new SimpleDateFormat("dd-MMM-yyyy");
//                   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//                   Date tranRefDate = sdfx.parse(objchallan.getTds_challan_date());
//                   String l_tran_ref_date = (sdf1.format(tranRefDate));
//                   objchallan.setTds_challan_date(l_tran_ref_date);
                   setObjChallan(objchallan);

                    try {
                        setSelectSection(factory.getTdsMastDAO().getSectionAsHashMap(asmt.getTdsTypeCode(), null));
                       //Due to slow data processing,Comment old Query 
                       /* TdsTranLoadDAO tdstrandao = factory.getTdsTranLoadDAO();
                        List<TdsTranLoad> objtdstrandao = tdstrandao.readTdsChallanAmount(String.valueOf(getRowidSeq()), client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), tds_type_code);
                        Double allocatedAmount = 0d;
                        if (objtdstrandao != null) {
                            for (TdsTranLoad tdsTranLoaddata : objtdstrandao) {System.out.println("11111");
                                String l_tds_amount = tdsTranLoaddata.getTds_amt();
                                allocatedAmount += Double.parseDouble(utl.isnull(l_tds_amount) ? "0" : l_tds_amount);
                            }
                        }
                        System.out.println("allocatedAmount--"+allocatedAmount);*/
                    //new Query for get allocatedAmount  
                      long allocatedAmount= getAllocatedAmountQuery(String.valueOf(getRowidSeq()), client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), asmt.getQuarterNo(), tds_type_code);
                      setMapAllocatedAmount((double)allocatedAmount); 
                      System.out.println("allocatedAmount--"+(double)allocatedAmount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    l_return_value = "view";
                } else if (getAction().equalsIgnoreCase("update")) {
                    //System.out.println("inside update flag");
                    TdsChallanTranLoadDAO challandao = factory.getTdsChallanTranLoadDAO();
                    TdsChallanTranLoad challan = challandao.readChallanBySequenceID(getObjChallan().getRowid_seq());
                    if (challan != null) {
                        String Nil_challan_flag = "";
                        String Book_entry_flag = "";
                        if ("on".equals(getObjChallan().getNil_challan_flag())) {
                            Nil_challan_flag = "Y";
                        } else {
                            Nil_challan_flag = "N";
                        }
                        if ("on".equals(getObjChallan().getBook_entry_flag())) {
                            Book_entry_flag = "Y";
                        } else {
                            Book_entry_flag = "N";
                        }

                        Date l_challanDate = null;
                        if (getObjChallan().getTds_challan_date() != null) {
                            try {
                                l_challanDate = sdf.parse(getObjChallan().getTds_challan_date());
                            } catch (Exception e) {
                                try {
                                    l_challanDate = sdfnew.parse(getObjChallan().getTds_challan_date());
                                } catch (Exception ex) {
                                    l_challanDate = null;
                                }
                            }
                        }

                        String l_challan_date_value = "";
                        if (l_challanDate != null) {
                            l_challan_date_value = (sdfnew.format(l_challanDate)).toUpperCase();
                        }

                        String l_month = "";
                        if (!utl.isnull(Nil_challan_flag) && Nil_challan_flag.equalsIgnoreCase("Y")) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select TO_CHAR(TO_DATE('" + l_to_date + "','DD-MM-RRRR'), 'MON') from DUAL t";
                                l_month = ef.execute_oracle_function_as_string(functionString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (!utl.isnull(getObjChallan().getTds_challan_date())) {
                                try {
                                    DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                    String functionString = "select TO_CHAR(TO_DATE('" + getObjChallan().getTds_challan_date() + "','DD-MM-RRRR'), 'MON') from DUAL t";
                                    l_month = ef.execute_oracle_function_as_string(functionString);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                      // System.out.println("l_challan_date_valuel_//////challan_date_value-->"+l_challan_date_value);
                        if (!utl.isnull(Nil_challan_flag) && Nil_challan_flag.equalsIgnoreCase("Y")) {
                            challan.setTds_challan_date(sdfnew.format(quarterToDate).toUpperCase());
                            challan.setMonth(l_month);
                            challan.setCsi_verify_flag("M");
                            challan.setCsi_verify_timestamp(new Date());
                        } else {
                            challan.setTds_challan_date(l_challan_date_value);
                            challan.setMonth(l_month);
                        }

                        challan.setTds_code(getObjChallan().getTds_code());
                        if (!utl.isnull(getObjChallan().getTds_code())) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select t.tds_name from tds_mast t where t.tds_code = '" + getObjChallan().getTds_code() + "'";
                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                challan.setTds_section(resultValue);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            challan.setTds_section("");
                        }
                        //System.out.println("get bsr code---"+getObjChallan().getBank_bsr_code());
                        challan.setBank_bsr_code(getObjChallan().getBank_bsr_code());
                        challan.setBank_name(getObjChallan().getBank_name());
                        challan.setTds_challan_no(getObjChallan().getTds_challan_no());
                        challan.setTds_challan_tds_amt(getObjChallan().getTds_challan_tds_amt());
                        challan.setTds_challan_int_amt(getObjChallan().getTds_challan_int_amt());
                        challan.setTds_challan_other_amt(getObjChallan().getTds_challan_other_amt());
                        challan.setTds_challan_minor_head(getObjChallan().getTds_challan_minor_head());
                        challan.setLastupdate(new Date());
                        challan.setNil_challan_flag(Nil_challan_flag);
                        challan.setBook_entry_flag(Book_entry_flag);
                        challan.setCsi_verify_flag("P");
                        //System.out.println("updated data get-->"+challan.toString());
                        challandao = factory.getTdsChallanTranLoadDAO();
                        boolean update_result = challandao.update(challan);

                        if (update_result) {
//                            session.put("session_result", "Record Updated Successfully");
                            l_return_message = "success";
                            //delete CSI file
                            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                            String generateCsiFileLoc = javaDriveName + File.separator + "CSI_FILES"
                                    + File.separator + client.getClient_code()
                                    + File.separator + asmt.getAccYear()
                                    + File.separator + asmt.getTdsTypeCode();

                            utl.generateLog("Generated CSI file location: ", generateCsiFileLoc);
                            try {
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            l_return_message = "Could not update, some error occured";
                        }
                    } else {
                        l_return_message = "Could not update, some error occured";
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));

        return l_return_value;
    }
    
    Long getAllocatedAmountQuery(String mChallanRowSeq, String l_entity_code, String l_client_code, String acc_year, String quarterNo, String tds_type_code){
        
                 String query = "select sum(this_.tds_amt)        \n" +
                 "  from tds_tran_load this_\n" +
                 " where this_.tds_challan_rowid_seq = '"+mChallanRowSeq+"'\n" +
                 "   and this_.entity_code = '"+l_entity_code+"'\n" +
                 "   and this_.client_code = '"+l_client_code+"'\n" +
                 "   and this_.acc_year = '"+acc_year+"'\n" +
                 "   and this_.quarter_no = '"+quarterNo+"'\n" +
                 "   and this_.tds_type_code = '"+tds_type_code+"'";
                 DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                 long total = objDBListCount.execute_oracle_function_as_integar(query);
        
        return total;
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

    public LinkedHashMap<String, String> getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(LinkedHashMap<String, String> selectSection) {
        this.selectSection = selectSection;
    }

    public TdsChallanTranLoad getObjChallan() {
        return objChallan;
    }

    public void setObjChallan(TdsChallanTranLoad objChallan) {
        this.objChallan = objChallan;
    }

    public TreeMap<String, String> getSelectMinorHead() {
        return selectMinorHead;
    }

    public void setSelectMinorHead(TreeMap<String, String> selectMinorHead) {
        this.selectMinorHead = selectMinorHead;
    }

    public String getSaveAndContinue() {
        return saveAndContinue;
    }

    public void setSaveAndContinue(String saveAndContinue) {
        this.saveAndContinue = saveAndContinue;
    }

    public Long getRowidSeq() {
        return rowidSeq;
    }

    public void setRowidSeq(Long rowidSeq) {
        this.rowidSeq = rowidSeq;
    }

    public String getChallanflag() {
        return challanflag;
    }

    public void setChallanflag(String challanflag) {
        this.challanflag = challanflag;
    }

    public Double getMapAllocatedAmount() {
        return mapAllocatedAmount;
    }

    public void setMapAllocatedAmount(Double mapAllocatedAmount) {
        this.mapAllocatedAmount = mapAllocatedAmount;
    }

    public String getProcrssFlag() {
        return procrssFlag;
    }

    public void setProcrssFlag(String procrssFlag) {
        this.procrssFlag = procrssFlag;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

}
