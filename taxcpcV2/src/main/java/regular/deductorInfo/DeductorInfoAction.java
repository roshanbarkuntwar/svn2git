/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductorInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.ViewClientMast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class DeductorInfoAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() {
        String returnType = "success";
        session.put("ACTIVE_TAB", "tdsDeductorInfo");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ClientMastDAO clientDao = null;
            ViewClientMastDAO viewClientMastDAO = null;
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            String client_login_level = (String) session.get("CLIENT_LOGIN_LEVEL");
            String client_level_type = viewClientMast.getClient_level_type();
            String login_entity_code = viewClientMast.getEntity_code();
            String login_client_code = viewClientMast.getClient_code();
            setSelect_state(factory.getStateMastDAO().getStateCodeAsLinkedHashMap());
            setSelect_country(factory.getCountryMastDAO().getCountryCodeAsHashMap());
            setSelect_city(factory.getCityMastDAO().getCityCodeAsHashMap());
            setSelectMinistryCode(factory.getViewMinistryMastDAO().getMinistryCodeAsList());
         //   setMinstatecode(viewClientMast.getMinistry_state_code());
            setSelectSubMinistryCode(factory.getViewSubMinistryMastDAO().getSubMinistryCodeAsList());
            Assessment assessment = (Assessment) session.get("ASSESSMENT");

            if (utl.isnull(getAction())) {
                if (viewClientMast != null && assessment != null) {
                    clientDao = factory.getClientMastDAO();
                    System.out.println("get client mast");
                    //ClientMast clientmast = clientDao.readById(viewClientMast.getClient_code(), true);
                     ClientMast clientmast = clientDao.getClientByClientCode(viewClientMast.getClient_code());
                    setClientMastObj(clientmast);
                    setMinstatecode(clientmast.getMinistry_state_code());
                }

                if (Integer.parseInt((String) session.get("CLIENT_LOGIN_LEVEL")) == (Integer.parseInt(viewClientMast.getClient_level_type()))) {
                    setGetChkBxFlag("T");
                }

                DeductorInfoDB deductordb = new DeductorInfoDB();
                String clientGroupQuery = deductordb.clientGroupListQuery(login_entity_code, client_level_type, client_login_level, login_client_code);
                utl.generateSpecialLog("clientGroupQuery--->", clientGroupQuery);
                DbFunctionExecutorAsString objDb = new DbFunctionExecutorAsString();
                ArrayList<ArrayList<String>> ClientGroupList = objDb.execute_oracle_query_as_list(clientGroupQuery);
                if (ClientGroupList != null && ClientGroupList.size() > 0) {
                    LinkedHashMap<String, String> linkedObj = new LinkedHashMap<String, String>();
                    linkedObj.put("", "--Select--");
                    for (ArrayList<String> arrayList : ClientGroupList) {
                        String client_code = arrayList.get(0);
                        String entity_code = arrayList.get(1);
                        linkedObj.put(client_code, entity_code.toUpperCase());
                    }
                    setSelectClient(linkedObj);
                }

                clientDao = factory.getClientMastDAO();
                ClientMast clientmast = clientDao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                if (!utl.isnull(clientmast.getDeductor_add_change())) {
                    if (clientmast.getDeductor_add_change().equalsIgnoreCase("T")) {
                        setH_deductor_add_change("true");
                    } else {
                        setH_deductor_add_change("false");
                    }
                }
                setClientMastObj(clientmast);
                try {
                    viewClientMastDAO = factory.getViewClientMastDAO();
                    ViewClientMast obj_data = viewClientMastDAO.readClientByClientCode(clientmast.getClient_code());
                    if (clientmast.getDeductor_add_change_on() != null) {
                        setH_deductor_add_change_on(sdf.format(clientmast.getDeductor_add_change_on()));
                    }
                    setL_deductor_name(obj_data.getClient_name());
                    setL_client_type_code(obj_data.getClient_type_code() + "~" + obj_data.getClient_type_code());
                    setL_client_catg_code(obj_data.getClient_catg_code());
                    setClient_category_type(obj_data.getClient_type_code());

                    GenlClientCodeAction objgenlevel = new GenlClientCodeAction();
                    String conClientCode = objgenlevel.getgenLevelClientCodeValue(obj_data, assessment, "getConsolidateLevelClient");
                    //System.ou.println("conClientCode..." + conClientCode);

                    if (!utl.isnull(conClientCode)) {
                        setConsolidate_chkbx_value("1");
                    } else {
                        setConsolidate_chkbx_value("0");
                    }
                    setGenLevelClientCode(conClientCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setL_closed_date(sdf.format(new Date()));
                returnType = "edit";
            } else {
                if (getAction().equalsIgnoreCase("editNilDeductor")) {
                    setEnableNilReturnBtn("true");
                    try {
                        ClientMastDAO clientMastDAO = factory.getClientMastDAO();

                        if (!utl.isnull(getNilReturnClientCode())) {
                            ClientMast clientmast = clientMastDAO.readClientByClientCode(getNilReturnClientCode());

                            if (!utl.isnull(clientmast.getDeductor_add_change())) {
                                if (clientmast.getDeductor_add_change().equalsIgnoreCase("T")) {
                                    setH_deductor_add_change("true");
                                } else {
                                    setH_deductor_add_change("false");
                                }
                            }

                            if (clientmast.getDeductor_add_change_on() != null) {
                                setH_deductor_add_change_on(sdf.format(clientmast.getDeductor_add_change_on()));
                            }
                            setL_closed_date(sdf.format(new Date()));
                            setClientMastObj(clientmast);

                            try {
                                viewClientMastDAO = factory.getViewClientMastDAO();
                                ViewClientMast obj_data = viewClientMastDAO.readClientByClientCode(getNilReturnClientCode());
                                setL_deductor_name(obj_data.getClient_name());
                                setL_client_type_code(obj_data.getClient_type_code() + "~" + obj_data.getClient_type_code());
                                setL_client_catg_code(obj_data.getClient_catg_code());
                                setClient_category_type(obj_data.getClient_type_code());
                                setEditIsClientLoginLevel((String) session.get("CLIENT_LOGIN_LEVEL"));

                                String conClientCode = this.getgenLevelClientCodeValue(obj_data, assessment, "getConsolidateLevelClient", utl);

                                if (!utl.isnull(conClientCode)) {
                                    setConsolidate_chkbx_value("1");
                                } else {
                                    setConsolidate_chkbx_value("0");
                                }
                                setGenLevelClientCode(conClientCode);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            ClientMast clientmast = clientDao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                            if (!utl.isnull(clientmast.getDeductor_add_change())) {
                                if (clientmast.getDeductor_add_change().equalsIgnoreCase("T")) {
                                    setH_deductor_add_change("true");
                                } else {
                                    setH_deductor_add_change("false");
                                }
                            }
                            if (clientmast.getDeductor_add_change_on() != null) {
                                setH_deductor_add_change_on(sdf.format(clientmast.getDeductor_add_change_on()));
                            }

                            setL_closed_date(sdf.format(new Date()));
                            setClientMastObj(clientmast);

                            try {
                                viewClientMastDAO = factory.getViewClientMastDAO();
                                ViewClientMast obj_data = viewClientMastDAO.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                                setL_deductor_name(obj_data.getClient_name());
                                setL_client_type_code(obj_data.getClient_type_code() + "~" + obj_data.getClient_type_code());
                                setL_client_catg_code(obj_data.getClient_catg_code());
                                setClient_category_type(obj_data.getClient_type_code());
                                setEditIsClientLoginLevel((String) session.get("CLIENT_LOGIN_LEVEL"));

                                String conClientCode = this.getgenLevelClientCodeValue(obj_data, assessment, "getConsolidateLevelClient", utl);
                                //System.out.println("conClientCode..." + conClientCode);

                                if (!utl.isnull(conClientCode)) {
                                    setConsolidate_chkbx_value("1");
                                } else {
                                    setConsolidate_chkbx_value("0");
                                }
                                setGenLevelClientCode(conClientCode);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                returnType = "edit";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnType;

    }

    public DeductorInfoAction() {
        utl = new Util();
        selectClientCatg = new LinkedHashMap<>();
        selectClientCatg.put("", "--Select--");

        select_department = new HashMap<>();
        select_department.put("", "select");
        select_state = new HashMap<>();
        select_state.put("", "-----select------");


        select_city = new HashMap<>();

        select_country = new HashMap<>();

        selectMinistryCode = new LinkedHashMap<>();

        selectSubMinistryCode = new LinkedHashMap<>();
        selectSubMinistryCode.put("", "-----select------");

        selectClient = new LinkedHashMap<>();

    }

    Util utl;
    Map<String, Object> session;
    ClientMast clientMastObj;

    private LinkedHashMap<String, String> selectClientCatg;
    private LinkedHashMap<String, String> selectMinistryCode;
    private LinkedHashMap<String, String> selectSubMinistryCode;
    private HashMap<String, String> select_state;
    private HashMap<String, String> select_city;
    private HashMap<String, String> select_department;
    private HashMap<String, String> select_country;
    private LinkedHashMap<String, String> selectClient;
    private String h_deductor_add_change_on;
    private String h_deductor_add_change;
    private String l_deductor_name;
    private String L_client_type_code;
    private String getChkBxFlag;
    private String client_category_type;
    private String l_closed_date;
    private String l_client_catg_code;
    private String genLevelClientCode;
    private String consolidate_chkbx_value;
    private String nilReturnClientCode;
    private String action;
    private String editIsClientLoginLevel;
    private String enableNilReturnBtn;
   private String minstatecode;
    public String getEditIsClientLoginLevel() {
        return editIsClientLoginLevel;
    }

    public String getEnableNilReturnBtn() {
        return enableNilReturnBtn;
    }

    public void setEnableNilReturnBtn(String enableNilReturnBtn) {
        this.enableNilReturnBtn = enableNilReturnBtn;
    }

    public void setEditIsClientLoginLevel(String editIsClientLoginLevel) {
        this.editIsClientLoginLevel = editIsClientLoginLevel;
    }

    public String getNilReturnClientCode() {
        return nilReturnClientCode;
    }

    public void setNilReturnClientCode(String nilReturnClientCode) {
        this.nilReturnClientCode = nilReturnClientCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ClientMast getClientMastObj() {
        return clientMastObj;
    }

    public void setClientMastObj(ClientMast clientMastObj) {
        this.clientMastObj = clientMastObj;
    }

    public LinkedHashMap<String, String> getSelectClientCatg() {
        return selectClientCatg;
    }

    public void setSelectClientCatg(LinkedHashMap<String, String> selectClientCatg) {
        this.selectClientCatg = selectClientCatg;
    }

    public LinkedHashMap<String, String> getSelectMinistryCode() {
        return selectMinistryCode;
    }

    public void setSelectMinistryCode(LinkedHashMap<String, String> selectMinistryCode) {
        this.selectMinistryCode = selectMinistryCode;
    }

    public LinkedHashMap<String, String> getSelectSubMinistryCode() {
        return selectSubMinistryCode;
    }

    public void setSelectSubMinistryCode(LinkedHashMap<String, String> selectSubMinistryCode) {
        this.selectSubMinistryCode = selectSubMinistryCode;
    }

    public HashMap<String, String> getSelect_state() {
        return select_state;
    }

    public void setSelect_state(HashMap<String, String> select_state) {
        this.select_state = select_state;
    }

    public HashMap<String, String> getSelect_city() {
        return select_city;
    }

    public void setSelect_city(HashMap<String, String> select_city) {
        this.select_city = select_city;
    }

    public HashMap<String, String> getSelect_department() {
        return select_department;
    }

    public void setSelect_department(HashMap<String, String> select_department) {
        this.select_department = select_department;
    }

    public HashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(HashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public LinkedHashMap<String, String> getSelectClient() {
        return selectClient;
    }

    public void setSelectClient(LinkedHashMap<String, String> selectClient) {
        this.selectClient = selectClient;
    }

    public String getH_deductor_add_change() {
        return h_deductor_add_change;
    }

    public void setH_deductor_add_change(String h_deductor_add_change) {
        this.h_deductor_add_change = h_deductor_add_change;
    }

    public String getH_deductor_add_change_on() {
        return h_deductor_add_change_on;
    }

    public void setH_deductor_add_change_on(String h_deductor_add_change_on) {
        this.h_deductor_add_change_on = h_deductor_add_change_on;
    }

    public String getL_deductor_name() {
        return l_deductor_name;
    }

    public void setL_deductor_name(String l_deductor_name) {
        this.l_deductor_name = l_deductor_name;
    }

    public String getL_client_type_code() {
        return L_client_type_code;
    }

    public void setL_client_type_code(String L_client_type_code) {
        this.L_client_type_code = L_client_type_code;
    }

    public String getL_client_catg_code() {
        return l_client_catg_code;
    }

    public void setL_client_catg_code(String l_client_catg_code) {
        this.l_client_catg_code = l_client_catg_code;
    }

    public String getClient_category_type() {
        return client_category_type;
    }

    public void setClient_category_type(String client_category_type) {
        this.client_category_type = client_category_type;
    }

    public String getConsolidate_chkbx_value() {
        return consolidate_chkbx_value;
    }

    public void setConsolidate_chkbx_value(String consolidate_chkbx_value) {
        this.consolidate_chkbx_value = consolidate_chkbx_value;
    }

    public String getGenLevelClientCode() {
        return genLevelClientCode;
    }

    public void setGenLevelClientCode(String genLevelClientCode) {
        this.genLevelClientCode = genLevelClientCode;
    }

    public String getL_closed_date() {
        return l_closed_date;
    }

    public void setL_closed_date(String l_closed_date) {
        this.l_closed_date = l_closed_date;
    }

    public String getGetChkBxFlag() {
        return getChkBxFlag;
    }

    public void setGetChkBxFlag(String getChkBxFlag) {
        this.getChkBxFlag = getChkBxFlag;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getMinstatecode() {
        return minstatecode;
    }

    public void setMinstatecode(String minstatecode) {
        this.minstatecode = minstatecode;
    }
    
    

    public String getgenLevelClientCodeValue(ViewClientMast viewClientMast, Assessment asmt, String action, Util utl) {
        String l_return_message = "";
        try {
            String l_acc_year = asmt.getAccYear();
            String l_entity_code = viewClientMast.getEntity_code();
            //String l_client_code = viewClientMast.getClient_code();
            if (!utl.isnull(action)) {
                int currentCodeLevel = 1;
                //int nextCodeLevel = 1;
                try {
                    currentCodeLevel = Integer.parseInt((String) session.get("CLIENT_LOGIN_LEVEL"));
                    //nextCodeLevel = currentCodeLevel + 1;
                } catch (Exception e) {
                }
                String l_client_code_level_1 = "";
                String l_client_code_level_2 = "";
                String l_client_code_level_3 = "";
                String l_client_code_level_4 = "";
                String l_client_code_level_5 = "";
                String l_client_code_level_6 = "";
                String gen_level_client_where_clause = "";
                if (currentCodeLevel == 1) {
                    l_client_code_level_1 = viewClientMast.getClient_code();//1
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "') \n";
                } else if (currentCodeLevel == 2) {
                    l_client_code_level_1 = viewClientMast.getParent_code();//1
                    l_client_code_level_2 = viewClientMast.getClient_code();//2
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "', '" + l_client_code_level_2 + "') \n";
                } else if (currentCodeLevel == 3) {
                    l_client_code_level_1 = viewClientMast.getG_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getParent_code();//2
                    l_client_code_level_3 = viewClientMast.getClient_code();//3
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "', '" + l_client_code_level_3 + "') \n";
                } else if (currentCodeLevel == 4) {
                    l_client_code_level_1 = viewClientMast.getSg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getG_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getParent_code();//3
                    l_client_code_level_4 = viewClientMast.getClient_code();//4
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "', '" + l_client_code_level_4 + "') \n";
                } else if (currentCodeLevel == 5) {
                    l_client_code_level_1 = viewClientMast.getSsg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getSg_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getG_parent_code();//3
                    l_client_code_level_4 = viewClientMast.getParent_code();//4
                    l_client_code_level_5 = viewClientMast.getClient_code();//5
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "','" + l_client_code_level_4 + "', '" + l_client_code_level_5 + "') \n";
                } else if (currentCodeLevel == 6) {
                    l_client_code_level_1 = viewClientMast.getSssg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getSsg_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getSg_parent_code();//3
                    l_client_code_level_4 = viewClientMast.getG_parent_code();//4
                    l_client_code_level_5 = viewClientMast.getParent_code();//5
                    l_client_code_level_6 = viewClientMast.getClient_code();//6
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "','" + l_client_code_level_4 + "','" + l_client_code_level_5 + "', '" + l_client_code_level_6 + "') \n";
                }
                if (action.equalsIgnoreCase("getConsolidateLevelCount")) {
                    String conClientCodeCount = "0";
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String client_count_query
                                = "select nvl(count(t.gen_level_client_code), '0')gen_level_client_code\n"
                                + "  from deductee_bflag_gen_level t\n"
                                + " where t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + l_acc_year + "'\n"
                                + gen_level_client_where_clause
                                + "   and (t.client_code_level1 = '" + l_client_code_level_1 + "'\n"
                                + "   or t.client_code_level2 = '" + l_client_code_level_2 + "'\n"
                                + "   or t.client_code_level3 = '" + l_client_code_level_3 + "'\n"
                                + "   or t.client_code_level4 = '" + l_client_code_level_4 + "'\n"
                                + "   or t.client_code_level5 = '" + l_client_code_level_5 + "'\n"
                                + "   or t.client_code_level6 = '" + l_client_code_level_6 + "')";
                        utl.generateSpecialLog("client_count_query..", client_count_query);
                        conClientCodeCount = objFn.execute_oracle_function_as_string(client_count_query);
                    } catch (Exception e) {
                    }
                    conClientCodeCount = utl.isnull(conClientCodeCount) ? "0" : conClientCodeCount;
                    l_return_message = conClientCodeCount;
                } else if (action.equalsIgnoreCase("getConsolidateLevelClient")) {
                    String conClientCode = "";
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String cons_client_query
                                = "select distinct t.gen_level_client_code\n"
                                + "  from deductee_bflag_gen_level t\n"
                                + " where t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + l_acc_year + "'\n"
                                + gen_level_client_where_clause
                                + "   and (t.client_code_level1 = '" + l_client_code_level_1 + "'\n"
                                + "   or t.client_code_level2 = '" + l_client_code_level_2 + "'\n"
                                + "   or t.client_code_level3 = '" + l_client_code_level_3 + "'\n"
                                + "   or t.client_code_level4 = '" + l_client_code_level_4 + "'\n"
                                + "   or t.client_code_level5 = '" + l_client_code_level_5 + "'\n"
                                + "   or t.client_code_level6 = '" + l_client_code_level_6 + "')";
                        utl.generateSpecialLog("cons_client_query..", cons_client_query);
                        conClientCode = objFn.execute_oracle_function_as_string(cons_client_query);
                        conClientCode = utl.isnull(conClientCode) ? "" : conClientCode;
                    } catch (Exception e) {
                    }
                    l_return_message = conClientCode;
                } else if (action.equalsIgnoreCase("getDeductorLevelBflagCount")) {
                    String leavelBflagRec = "0#0";
                    String get_client_where_clause = "";
                    if (currentCodeLevel == 1) {
                        get_client_where_clause = "and t.client_code_level1 = '" + l_client_code_level_1 + "' \n";
                    } else if (currentCodeLevel == 2) {
                        get_client_where_clause = "and t.client_code_level2 = '" + l_client_code_level_2 + "' \n";
                    } else if (currentCodeLevel == 3) {
                        get_client_where_clause = "and t.client_code_level3 = '" + l_client_code_level_3 + "' \n";
                    } else if (currentCodeLevel == 4) {
                        get_client_where_clause = "and t.client_code_level4 = '" + l_client_code_level_4 + "' \n";
                    } else if (currentCodeLevel == 5) {
                        get_client_where_clause = "and t.client_code_level5 = '" + l_client_code_level_5 + "' \n";
                    } else if (currentCodeLevel == 6) {
                        get_client_where_clause = "and t.client_code_level6 = '" + l_client_code_level_6 + "' \n";
                    }
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String leavel_bflag_query
                                = "select NVL(sum(d.gen_record_count), '0') || '#' || NVL(COUNT(*), '0') rec_count\n"
                                + "  from deductee_bflag_refinfo_seq_no d\n"
                                + " where exists (select 1\n"
                                + "          from deductee_bflag_gen_level t\n"
                                + "         where t.acc_year = '" + l_acc_year + "'\n"
                                + get_client_where_clause
                                + "           and t.entity_code = '" + l_entity_code + "'\n"
                                + "           and d.client_code = t.gen_level_client_code)";
                        utl.generateSpecialLog("leavel_bflag_query..", leavel_bflag_query);
                        leavelBflagRec = objFn.execute_oracle_function_as_string(leavel_bflag_query);
                        leavelBflagRec = utl.isnull(leavelBflagRec) ? "0#0" : leavelBflagRec;
                    } catch (Exception e) {
                    }
                    l_return_message = leavelBflagRec;
                } else if (action.equalsIgnoreCase("getGeneratedBflagRecord")) {
                    ArrayList<GeneratedBflagPojo> entitylist = null;
                    String tableDetail = "No Record Found";
                    String get_client_where_clause = "";
                    if (currentCodeLevel == 1) {
                        get_client_where_clause = "and t.client_code_level1 = '" + l_client_code_level_1 + "' \n";
                    } else if (currentCodeLevel == 2) {
                        get_client_where_clause = "and t.client_code_level2 = '" + l_client_code_level_2 + "' \n";
                    } else if (currentCodeLevel == 3) {
                        get_client_where_clause = "and t.client_code_level3 = '" + l_client_code_level_3 + "' \n";
                    } else if (currentCodeLevel == 4) {
                        get_client_where_clause = "and t.client_code_level4 = '" + l_client_code_level_4 + "' \n";
                    } else if (currentCodeLevel == 5) {
                        get_client_where_clause = "and t.client_code_level5 = '" + l_client_code_level_5 + "' \n";
                    } else if (currentCodeLevel == 6) {
                        get_client_where_clause = "and t.client_code_level6 = '" + l_client_code_level_6 + "' \n";
                    }
                    try {
                        String getGeneratedBflagRecord_query
                                = "select lhs_utility.get_name('client_code', d.client_code)client_code, d.bflag, d.reference_no, d.gen_record_count\n"
                                + "  from deductee_bflag_refinfo_seq_no d\n"
                                + " where exists (select 1\n"
                                + "          from deductee_bflag_gen_level t\n"
                                + "         where t.acc_year = '" + l_acc_year + "'\n"
                                + get_client_where_clause
                                + "           and t.entity_code = '" + l_entity_code + "'\n"
                                + "           and d.client_code = t.gen_level_client_code)";
                        utl.generateSpecialLog("getGeneratedBflagRecord_query..", getGeneratedBflagRecord_query);
                        DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
                        entitylist = db.getGenericList(new GeneratedBflagPojo(), getGeneratedBflagRecord_query);

                        tableDetail = getTableGenDetail(entitylist, utl);
                    } catch (Exception e) {
                    }
                    l_return_message = tableDetail;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_message;
    }//end method

    private String getTableGenDetail(ArrayList<GeneratedBflagPojo> entitylist, Util utl) {
        String returnData = "";
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("<table class=\"table-responsive table-striped\" id=\"table\">");
            sb.append("<thead>");
            sb.append("<tr class=\"text-center\">");
            sb.append("<th>Slno</th>");
            sb.append("<th>Client Name</th>");
            sb.append("<th>File Type(G/H)</th>");
            sb.append("<th>Refrence No</th>");
            sb.append("<th>Refrence No Count</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody>");

            if (entitylist != null) {
                int slcount = 0;
                for (GeneratedBflagPojo entity : entitylist) {
                    slcount++;
                    sb.append("<tr class=\"text-center\">");
                    sb.append("<td>").append(slcount).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getClient_code()) ? "" : entity.getClient_code().toUpperCase()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getBflag()) ? "" : entity.getBflag()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getReference_no()) ? "" : entity.getReference_no()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getGen_record_count()) ? "0" : entity.getGen_record_count()).append("</td>");
                    sb.append("</tr>");

                }
                if (slcount <= 20) {
                    for (int i = slcount; i <= 20; i++) {
                        sb.append("<tr class=\"text-center\">");
                        sb.append("<td >").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("</tr>");
                    }
                }
            } else {
                sb.append("<div class=\"well well-lg\" >");
                sb.append("No records found");
                sb.append("</div>");

            }
            sb.append("</tbody>");
            sb.append("</table>");

            returnData = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData;
    }//end method
}
