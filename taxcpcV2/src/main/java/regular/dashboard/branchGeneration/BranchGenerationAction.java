/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchGeneration;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.HibernateUtil;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.PinCodeMast;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gaurav.khanzode
 */
public class BranchGenerationAction extends ActionSupport implements SessionAware {

    private final Util utl;

    private Map<String, Object> session;
    private HashMap<String, String> select_state;
    private LinkedHashMap<String, String> loginLevelList;
    private LinkedHashMap<String, String> entityList;

    private ClientMast clientMastObj;
    private UserMast userMastObj;
    private InputStream inputStream;

    private String action;

    private String clientCode;
    private String sessionResult;

    public BranchGenerationAction() {
        loginLevelList = new LinkedHashMap<>();
        entityList = new LinkedHashMap<>();
        clientMastObj = new ClientMast();
        userMastObj = new UserMast();
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String return_view = "input";
        String return_msg = "";
        session.put("ACTIVE_TAB", "dashboard");

        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            setSelect_state(factory.getStateMastDAO().getStateCodeAsLinkedHashMap());
            setEntityList(factory.getEntityMastDAO().readAllEntitiesAsHashMapFromClientMast());

            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            try {
                if (!utl.isnull(clientMast.getCode_level())) {
                    for (int i = Integer.valueOf(clientMast.getCode_level()) + 1; i <= 6; i++) {
                        loginLevelList.put(i + "", "Level-" + i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (utl.isnull(getAction())) {
                //setAction("save");
            } else {
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
                String accYear = "";
                String quarterNo = "";
                String tdsTypeCode = "";

                if (asmt != null) {
                    accYear = asmt.getAccYear();
                    quarterNo = asmt.getQuarterNo();
                    tdsTypeCode = asmt.getTdsTypeCode();
                }

                if (getAction().equalsIgnoreCase("getBranches")) {

                    StringBuilder sbb = new StringBuilder();
                    sbb.append("<option value=\"\">").append("Select Immediate Bank Branch Code").append("</option>");

                    int branchNos = 0;
                    if (!utl.isnull(clientMast.getEntity_code()) && !utl.isnull(clientMastObj.getCode_level())) {
                        List<ClientMast> branchesList = factory.getClientMastDAO().readClientByEntityAndCodeLevel(clientMast.getEntity_code(), clientMastObj.getCode_level(), clientMast.getClient_code());

                        if (branchesList != null && !branchesList.isEmpty()) {
                            branchNos = branchesList.size();
                            try {
                                String branchCode = "";
                                String branchName = "";
                                String clientCode = "";
                                for (ClientMast branchDetail : branchesList) {
                                    clientCode = !utl.isnull(branchDetail.getClient_code()) ? branchDetail.getClient_code() : "";
                                    branchCode = !utl.isnull(branchDetail.getBank_branch_code()) ? branchDetail.getBank_branch_code() : "";
                                    branchName = !utl.isnull(branchDetail.getClient_name()) ? branchDetail.getClient_name() : "";

                                    if (!utl.isnull(branchCode)) {
                                        sbb.append("<option value=\"").append(clientCode).append("\">").append(branchCode).append(" - ")
                                                .append(branchName).append("</option>");
                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            branchNos = 0;
                        }
                    }
                    sbb.append("#");
                    sbb.append(branchNos);

                    return_msg = sbb.toString();
                    return_view = "ajax_success";

                } else if (getAction().equalsIgnoreCase("getLocality")) {

                    PinCodeMast readPinCodeById = factory.getPinCodeMastDAO().readPinCodeById(getClientMastObj().getPin());

                    if (readPinCodeById != null) {
                        return_msg = readPinCodeById.getCity() + "#" + readPinCodeById.getState_code();
                    }

                    return_view = "ajax_success";

                } else if (getAction().equalsIgnoreCase("save")) {
                    String parentCodes = this.getParentCodesByCodeLevel(0, clientMast.getEntity_code(), getClientMastObj().getParent_code());

                    String[] parentCodesAry = !utl.isnull(parentCodes) ? parentCodes.split("#") : null;

                    String g_parent_code = "";
                    String sg_parent_code = "";
                    String ssg_parent_code = "";
                    String sssg_parent_code = "";

                    if (parentCodesAry != null) {

                        try {
                            g_parent_code = parentCodesAry[0];
                            sg_parent_code = parentCodesAry[1];
                            ssg_parent_code = parentCodesAry[2];
                            sssg_parent_code = parentCodesAry[3];

                        } catch (Exception e) {
                            utl.generateLog("error-1", e.getMessage());
                            e.getMessage();
                        }
                    }

                    Session openSession = HibernateUtil.currentSessionOnly();
                    Transaction tx = openSession.beginTransaction();

                    boolean saved = false;
                    boolean userSaveFlag = false;
                    String clientCode = "";
                    try {
                        ClientMast clientMastSave = getClientMastObj();
                        UserMast userMastSave = getUserMastObj();
                        try {
                            userSaveFlag = utl.isnull(clientMastSave.getClient_code());

                            clientMastSave.setEntity_code(clientMast.getEntity_code());
                            clientMastSave.setLastupdate(new Date());
                            clientMastSave.setClient_level_type(6 + "");
                            clientMastSave.setG_parent_code(!utl.isnull(g_parent_code) ? g_parent_code.toUpperCase() : "");
                            clientMastSave.setSg_parent_code(!utl.isnull(sg_parent_code) ? sg_parent_code.toUpperCase() : "");
                            clientMastSave.setSsg_parent_code(!utl.isnull(ssg_parent_code) ? ssg_parent_code.toUpperCase() : "");
                            clientMastSave.setSssg_parent_code(!utl.isnull(sssg_parent_code) ? sssg_parent_code.toUpperCase() : "");
                            clientMastSave.setApprovedby("admin");
                            clientMastSave.setApproveddate(new Date());
//                            clientMastSave.setParent_code(clientMast.getClient_code());

                            openSession.saveOrUpdate(clientMastSave);
                            openSession.flush();
                            clientCode = (String) openSession.getIdentifier(clientMastSave);
                            saved = true;

                            utl.printObjectAsString(clientMastSave);
//                            System.out.println("saved/updated clientCode-" + clientCode);
                        } catch (Exception e) {
                            utl.generateLog("error-2", e.getMessage());
                            e.printStackTrace();
                        }

                        try {
                            if (saved && userSaveFlag && !utl.isnull(clientCode)) {
                                saved = false;
                                userMastSave.setUser_name(clientMastSave.getBank_branch_code());
                                userMastSave.setShort_name(clientMastSave.getBank_branch_code());
                                userMastSave.setLogin_id(clientMastSave.getLogin_id());
                                userMastSave.setLogin_pwd(clientMastSave.getLogin_pwd());
                                userMastSave.setEntity_code(clientMast.getEntity_code());
                                userMastSave.setClient_code(clientCode);
                                userMastSave.setDefault_acc_year(accYear);
                                userMastSave.setDefault_quarter_no(quarterNo);
                                userMastSave.setDefault_tds_type_code(tdsTypeCode);
                                userMastSave.setAdd_right(1l);
                                userMastSave.setEdit_right(1l);
                                userMastSave.setDelete_right(1l);
                                userMastSave.setLastupdate(new Date());

                                openSession.save(userMastSave);
                                saved = true;
//                                System.out.println("saved userCode-" + savedUserCode.toString());
                            }
                        } catch (Exception e) {
                            utl.generateLog("error-3", e.getMessage());
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        utl.generateLog("error-4", e.getMessage());
                        saved = false;
                        e.printStackTrace();
                    }

                    if (saved) {
                        try {
                            tx.commit();
                            openSession.close();
                            return_msg = "success~" + clientCode;
                        } catch (Exception e) {
                            return_msg = "error";
                        }

                    } else {
                        return_msg = "error";
                        tx.rollback();
                        openSession.close();
                    }
                    return_view = "save_ajax";
                } else if (getAction().equalsIgnoreCase("edit")) {
                    //setAction("save");

                    //return_view = "inputBrowse";
                    if (!utl.isnull(getClientCode())) {
                        ClientMast editClientData = factory.getClientMastDAO().readClientByClientCode(getClientCode());
                        if (editClientData != null) {
                            setClientMastObj(editClientData);

                            //return_view = "edit_entry";
                        } else {
                            session.put("BRANCHRESULT", "Some error occurred, Please try again.");
                            session.put("ERRORCLASS", ErrorType.errorMessage);
                        }
                    }
                } else if (getAction().equalsIgnoreCase("delete")) {
                    return_view = "inputBrowse";

                    if (!utl.isnull(getClientCode())) {
                        UserMast user = factory.getUserMastDAO().readUserByClientCode(getClientCode());
                        ClientMast client = factory.getClientMastDAO().readClientByClientCode(getClientCode());

                        Session openSession = HibernateUtil.currentSessionOnly();
                        Transaction tx = openSession.beginTransaction();

                        boolean deleteFlag = false;
                        try {
                            try {
                                if (user != null) {
                                    openSession.delete(user);
                                }
                                deleteFlag = true;
                            } catch (Exception e) {
                            }

                            try {
                                if (deleteFlag && client != null) {
                                    deleteFlag = false;
                                    openSession.delete(client);
                                    deleteFlag = true;
                                }
                            } catch (Exception e) {
                            }

                            if (deleteFlag) {
                                tx.commit();
                                return_view = "success";
                                session.put("BRANCHRESULT", "Record(s) deleted successfully.");
                                session.put("ERRORCLASS", MessageType.successMessage);
                            } else {
                                tx.rollback();
                                session.put("BRANCHRESULT", "Some error occurred!");
                                session.put("ERRORCLASS", MessageType.errorMessage);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            openSession.close();
                        }
                    }
                }
            }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return return_view;
    }//end method

    public String getParentCodesByCodeLevel(int codeLevel, String entityCode, String parentCode) {
        String parentCodes = "";
        DbFunctionExecutorAsString executer = new DbFunctionExecutorAsString();
        String parent_code_sql = "SELECT A.PARENT_CODE || '#' || A.G_PARENT_CODE || '#' ||\n"
                + "       A.SG_PARENT_CODE || '#' || A.SSG_PARENT_CODE\n"
                + "  FROM CLIENT_MAST A\n"
                + " WHERE A.ENTITY_CODE = '" + entityCode + "'\n"
                + "   AND A.CLIENT_CODE = '" + parentCode + "'";

        try {
            parentCodes = executer.execute_oracle_function_as_string(parent_code_sql);
            //utl.generateLog("get parent codes query", parent_code_sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentCodes;
    }//end

    public LinkedHashMap<String, String> getEntityList() {
        return entityList;
    }

    public void setEntityList(LinkedHashMap<String, String> entityList) {
        this.entityList = entityList;
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

    public UserMast getUserMastObj() {
        return userMastObj;
    }

    public void setUserMastObj(UserMast userMastObj) {
        this.userMastObj = userMastObj;
    }

    public HashMap<String, String> getSelect_state() {
        return select_state;
    }

    public void setSelect_state(HashMap<String, String> select_state) {
        this.select_state = select_state;
    }

    public LinkedHashMap<String, String> getLoginLevelList() {
        return loginLevelList;
    }

    public void setLoginLevelList(LinkedHashMap<String, String> loginLevelList) {
        this.loginLevelList = loginLevelList;
    }

    public ClientMast getClientMastObj() {
        return clientMastObj;
    }

    public void setClientMastObj(ClientMast clientMastObj) {
        this.clientMastObj = clientMastObj;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class
