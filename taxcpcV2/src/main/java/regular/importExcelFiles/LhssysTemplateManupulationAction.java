/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.LhssysTemplate;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class LhssysTemplateManupulationAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    private Long importSeqNo;
    Util utl;
    private InputStream inputStream;
    private ArrayList<Boolean> editCheckBox;
    private ArrayList<LhssysTemplate> objTempData;
    private String action;
    private String templateCode;
////    private String col1;
//    private String col2;
//    private String col3;
//    private String col4;
//    private String col5;
//    private String col6;
//    private String col7;
//    private String col8;
//    private String col9;
    private String rowid_seq;

    public LhssysTemplateManupulationAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_message = "error";
        String TempleteCode = getTemplateCode();
//        String TempleteCode = "TAXCPC01";
//        String TempleteCode = "TAXCPC01";
        utl.generateLog("Inside update action ...", "");

        try {

            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            UserMast user = (UserMast) session.get("LOGINUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

//        String TempleteCode = (String) session.get("IMPORTMASTERTEMPLATEECODE");
//        //utl.generateLog("col1.." + col1 + "col2.." + col2 + "col3.." + col3 + "col4.." + col4 + "col5.." + col5 + "col6.." + col6 + "col7.." + col7 + "col8.." + col8 + "col9.." + col9 + "col10.." + col10 + "col11.." + col11);
            if (!utl.isnull(getAction())) {
                DbFunctionExecutorAsString fun_executer = new DbFunctionExecutorAsString();
//                Connection conn = null;
//                org.hibernate.Session dbsession = null;
//                try {//used to create database connection
//                    dbsession = HibernateUtil.getSessionFactory().openSession();
//                    org.hibernate.Transaction tx = dbsession.beginTransaction();
//                    conn = dbsession.connection();
//                } catch (HibernateException e) {
//                    //utl.generateLog("Could not get database connection\n" + e.getMessage());
//                }
                if (getAction().equalsIgnoreCase("SaveImportDtl")) {

                    int status[] = null;
                    try {
                        if (getEditCheckBox() != null && getEditCheckBox().size() > 0) {

                            if (getObjTempData() != null && getObjTempData().size() > 0) {
                                ArrayList<LhssysTemplate> l_tempData = new ArrayList<LhssysTemplate>();
                                for (int i = 0; i < getEditCheckBox().size(); i++) {
                                    Boolean checkBox = getEditCheckBox().get(i);
                                    if (checkBox) {
                                        LhssysTemplate tempdata = getObjTempData().get(i);
                                        l_tempData.add(tempdata);
                                    }// end if
                                }// end for loop (iterate checkbox)

                                String read_col_detail = " SELECT T.READ_COLUMN_DTL FROM VIEW_CLIENT_TEMPLATE T WHERE UPPER(T.TEMPLATE_CODE) = UPPER('" + getTemplateCode() + "') ";
                                String column_detail = fun_executer.execute_oracle_function_as_string(read_col_detail);

                                int column_range = 0;
                                if (!utl.isnull(column_detail)) {
                                    column_range = Integer.parseInt(column_detail.split("#")[1]);
                                }

//                                String l_update_query = "UPDATE LHSSYS_TEMPLATE \n"
//                                        + "SET    col11 = ?,\n"
//                                        + "col12 = ?,\n"
//                                        + "col13 = ?,\n"
//                                        + "col14 = ?,\n"
//                                        + "col15 = ?,\n"
//                                        + "col16 = ?,\n"
//                                        + "col17 = ?,\n"
//                                        + "col18 = ?,\n"
//                                        + "col19 = ?,\n"
//                                        + "col20 = ?,\n"
//                                        + "col21 = ?,\n"
//                                        + "col22 = ?,\n"
//                                        + "col23 = ?,\n"
//                                        + "col24 = ?,\n"
//                                        + "col25 = ?,\n"
//                                        + "col26 = ?,\n"
//                                        + "col27 = ?,\n"
//                                        + "col28 = ?,\n"
//                                        + "col29 = ?,\n"
//                                        + "col30 = ?,\n"
//                                        + "col31 = ?,\n"
//                                        + "col32 = ?,\n"
//                                        + "col33 = ?,\n"
//                                        + "col34 = ?,\n"
//                                        + "col35 = ?,\n"
//                                        + "col36 = ?,\n"
//                                        + "col37 = ?,\n"
//                                        + "col38 = ?,\n"
//                                        + "col39 = ?,\n"
//                                        + "col40 = ?,\n"
//                                        + "col41 = ?,\n"
//                                        + "col42 = ?,\n"
//                                        + "col43 = ?,\n"
//                                        + "col44 = ?,\n"
//                                        + "col45 = ?,\n"
//                                        + "col46 = ?,\n"
//                                        + "col47 = ?,\n"
//                                        + "col48 = ?,\n"
//                                        + "col49 = ?,\n"
//                                        + "col50 = ?\n"
//                                        + "WHERE  rowid_seq = ? ";
                                String l_update_query1 = " UPDATE LHSSYS_TEMPLATE SET \n";

                                for (int colCount = 11; colCount <= column_range; colCount++) {//update column range starts from 11.
                                    l_update_query1 += " col" + colCount + " = ? , \n";
                                }

                                l_update_query1 = l_update_query1.substring(0, l_update_query1.lastIndexOf(","));
                                l_update_query1 += " WHERE  rowid_seq = ? ";

                                utl.generateSpecialLog("l_update_query...", l_update_query1);

//                                PreparedStatement pstmt = conn.prepareStatement(l_update_query1);
//                                for (LhssysTemplate tempdatalist : l_tempData) {
//                                    utl.generateLog("rowid_seq..." + tempdatalist.getRowid_seq());
//                                    pstmt.setString(1, tempdatalist.getCol11());
//                                    pstmt.setString(2, tempdatalist.getCol12());
//                                    pstmt.setString(3, tempdatalist.getCol13());
//                                    pstmt.setString(4, tempdatalist.getCol14());
//                                    pstmt.setString(5, tempdatalist.getCol15());
//                                    pstmt.setString(6, tempdatalist.getCol16());
//                                    pstmt.setString(7, tempdatalist.getCol17());
//                                    pstmt.setString(8, tempdatalist.getCol18());
//                                    pstmt.setString(9, tempdatalist.getCol19());
//                                    pstmt.setString(10, tempdatalist.getCol20());
//                                    pstmt.setString(11, tempdatalist.getCol21());
//                                    pstmt.setString(12, tempdatalist.getCol22());
//                                    pstmt.setString(13, tempdatalist.getCol23());
//                                    pstmt.setString(14, tempdatalist.getCol24());
//                                    pstmt.setString(15, tempdatalist.getCol25());
//                                    pstmt.setString(16, tempdatalist.getCol26());
//                                    pstmt.setString(17, tempdatalist.getCol27());
//                                    pstmt.setString(18, tempdatalist.getCol28());
//                                    pstmt.setString(19, tempdatalist.getCol29());
//                                    pstmt.setString(20, tempdatalist.getCol30());
//                                    pstmt.setString(21, tempdatalist.getCol31());
//                                    pstmt.setString(22, tempdatalist.getCol32());
//                                    pstmt.setString(23, tempdatalist.getCol33());
//                                    pstmt.setString(24, tempdatalist.getCol34());
//                                    pstmt.setString(25, tempdatalist.getCol35());
//                                    pstmt.setString(26, tempdatalist.getCol36());
//                                    pstmt.setString(27, tempdatalist.getCol37());
//                                    pstmt.setString(28, tempdatalist.getCol38());
//                                    pstmt.setString(29, tempdatalist.getCol39());
//                                    pstmt.setString(30, tempdatalist.getCol40());
//                                    pstmt.setString(31, tempdatalist.getCol41());
//                                    pstmt.setString(32, tempdatalist.getCol42());
//                                    pstmt.setString(33, tempdatalist.getCol43());
//                                    pstmt.setString(34, tempdatalist.getCol44());
//                                    pstmt.setString(35, tempdatalist.getCol45());
//                                    pstmt.setString(36, tempdatalist.getCol46());
//                                    pstmt.setString(37, tempdatalist.getCol47());
//                                    pstmt.setString(38, tempdatalist.getCol48());
//                                    pstmt.setString(39, tempdatalist.getCol49());
//                                    pstmt.setString(40, tempdatalist.getCol50());
//                                    pstmt.setString(41, tempdatalist.getRowid_seq());
//                                    pstmt.addBatch();
//                                }
//                                status = pstmt.executeBatch();
//                                pstmt.close();
//                                DbFunctionExecutorAsString db_fun = new DbFunctionExecutorAsString();
                                boolean insertion_done = fun_executer.execute_oracle_update_lhssysTemplate_function_by_batch(l_tempData, l_update_query1, column_range);
//                                if (status != null && status.length > 0) {
//                                    for (int sts : status) {
//                                        if (sts != -2) {
//                                            insertion_done = false;
//                                            break;
//                                        }
//                                    }
//                                } else {
//                                    insertion_done = false;
//                                }

                                try {
                                    if (insertion_done) {
//                                        GetTokenTransactions gt = new GetTokenTransactions();
//                                        TokenStatusAttribs tokenSatus = gt.getTokenSatus(client.getEntity_code(), client.getClient_code(), asmt, user.getUser_code(), "TEMPLATE_INSERT", getImportSeqNo());
//                                        ProcLhsTemplateError objProcLhsTemplateError = new ProcLhsTemplateError();
//                                        int temperr_result = objProcLhsTemplateError.Exceute_Proc_Lhs_Template_Error_function(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), Integer.parseInt(asmt.getQuarterNo()), asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), TempleteCode, getImportSeqNo(), user.getUser_code(),utl.isnull(tokenSatus.getTokenNo()) ? 0 : Long.parseLong(tokenSatus.getTokenNo()),null);
//                                        utl.generateSpecialLog("RIM-0001 (Result of Template Error Procedure)----", temperr_result);

                                        l_return_message = "save";
                                        //commmit connection
//                                        conn.commit();
                                    } else {
                                        l_return_message = "error";
                                        //connection rollbask
//                                        conn.rollback();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    //no need to chceck exception
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("DeleteImportDtl")) {

                    try {
                        String l_delete_query = "delete from LHSSYS_TEMPLATE \n"
                                + "WHERE rowid_seq = '" + getRowid_seq() + "'";

                        boolean del_result = fun_executer.execute_oracle_update_function_as_string(l_delete_query);
//                        PreparedStatement pstmt = conn.prepareStatement(l_delete_query);
//                        int del_result = pstmt.executeUpdate();
                        if (del_result) {
                            l_return_message = "delete";
                        }
//                        pstmt.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //               ===============closed connection here==================
                try {
//                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
//                    dbsession.getTransaction().commit();
//                    dbsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        utl.generateLog("return Msg...", l_return_message);
        inputStream = new ByteArrayInputStream(l_return_message.getBytes("UTF-8"));
        return l_return_value;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public ArrayList<Boolean> getEditCheckBox() {
        return editCheckBox;
    }

    public void setEditCheckBox(ArrayList<Boolean> editCheckBox) {
        this.editCheckBox = editCheckBox;
    }

    public ArrayList<LhssysTemplate> getObjTempData() {
        return objTempData;
    }

    public void setObjTempData(ArrayList<LhssysTemplate> objTempData) {
        this.objTempData = objTempData;
    }

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

}//end class
