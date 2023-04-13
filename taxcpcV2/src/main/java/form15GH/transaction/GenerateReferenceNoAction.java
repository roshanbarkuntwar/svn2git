package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class GenerateReferenceNoAction extends ActionSupport implements SessionAware {

    public GenerateReferenceNoAction() {
        utl = new Util();
        generationLevel = new LinkedHashMap<>();
    }

    @Override
    public String execute() throws Exception {
        String return_msg = "success";
        try {
            ViewClientMast Client = (ViewClientMast) session.get("WORKINGUSER");
            String entity_code = Client.getEntity_code();
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            UserMast userMast = (UserMast) session.get("LOGINUSER");
            session.put("ACTIVE_TAB", "15GHGenerateRefNo");
            String tdsTypeCode = asmt.getTdsTypeCode();
            String accYear = asmt.getAccYear();
            String quarterNo = asmt.getQuarterNo();

            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("RefNoPage")) {
                    try {
                        //      ViewClientMast loginLevelClient = (ViewClientMast) session.get("LOGINUSER");
                        String login_entity_code = Client.getEntity_code();
                        String login_client_code = "";
                        if (!utl.isnull((String) session.get("CLIENT_LOGIN_LEVEL"))) {
                            setClientLoginLevel((String) session.get("CLIENT_LOGIN_LEVEL"));
                            setClientLevelType(Client.getClient_level_type());
                            login_client_code = Client.getClient_code();
                        } else {
                            setClientLoginLevel((String) session.get("CLIENT_LOGIN_LEVEL"));
                            setClientLevelType(Client.getClient_level_type());
                            login_client_code = Client.getClient_code();
                        }

                        Form15GHDB form15GH = new Form15GHDB();
//                        String client_group_list_query = form15GH.getClientGroupListQuery(login_entity_code, login_client_code);
                        String client_group_list_query = form15GH.getClientGroupListQuery(Client, asmt, userMast, 1);
                        DbFunctionExecutorAsString objDb = new DbFunctionExecutorAsString();
                        ArrayList<ArrayList<String>> ClientGroupList = objDb.execute_oracle_query_as_list(client_group_list_query);
                        if (ClientGroupList != null && ClientGroupList.size() > 0) {
                            LinkedHashMap<String, String> linkedObj = new LinkedHashMap<String, String>();
                            linkedObj.put("", "--Select Branch Code ---");
                            for (ArrayList<String> arrayList : ClientGroupList) {
                                String client_code = arrayList.get(0);
                                String entitycode = arrayList.get(1);
                                String login_level = arrayList.get(2);
                                String client_level_type = arrayList.get(3);
                                String bank_branch_code = arrayList.get(4);

                                String finalString = client_code + "~" + login_level + "~" + client_level_type;
                                linkedObj.put(finalString, entitycode.toUpperCase() + " (" + bank_branch_code + ")");
                            }
                            setGenerationLevel(linkedObj);
//                            setGenerationLevelClient("");
                        }
                        setGenerationLevelClient(login_client_code + "~" + getClientLoginLevel() + "~" + getClientLevelType());

                        return_msg = "refnoPage";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (getAction().equalsIgnoreCase("getRefNoType1Dtl")) {
                    String returnRefNo = "";
                    try {
//                        String reference_no_h = getReturnGeneratedRefranceNo(entity_code, getGenClientCode(), accYear, quarterNo, tdsTypeCode, "D");
//                        setLastGen15HNo(reference_no_h);
//                        String refrance_no_g = getReturnGeneratedRefranceNo(entity_code, getGenClientCode(), accYear, quarterNo, tdsTypeCode, "B");
//                        setLastGen15GNo(refrance_no_g);
//                        String next_gen_ref_no_h = "";
//                        next_gen_ref_no_h = getNextGeneratedRefranceNo(getLastGen15HNo());
//                        setNextGen15HNo(next_gen_ref_no_h);
//                        String l_next_gen_ref_no_g = "";
//                        l_next_gen_ref_no_g = getNextGeneratedRefranceNo(getLastGen15GNo());
//                        setNextGen15GNo(l_next_gen_ref_no_g);
//
//                       
                        Form15GHDB dtl_query = new Form15GHDB();
                        String query = dtl_query.show15GHReferanceNoDetail(entity_code, accYear, quarterNo, tdsTypeCode, getGenClientCode(), getFilterFlag(), getLoginFlag(), Client.getCode_level(), Client.getClient_code());
                        utl.generateSpecialLog("Ref no query:", query);
                        GetDeductee15GHDataList deducteeObj = new GetDeductee15GHDataList();
                        ArrayList<ReferenceNoDetailPOJO> refNoList = deducteeObj.getDeducteeRefranceNoDetailsList(query, utl);
                        if (refNoList != null && refNoList.size() > 0) {
                            ReferenceNoDetailPOJO pojo = refNoList.get(0);
                            setLastGen15HNo(pojo.getLast_gen_15h());
                            setLastGen15GNo(pojo.getLast_gen_15g());
                            setNextGen15HNo(pojo.getNext_gen_15h());
                            setNextGen15GNo(pojo.getNext_gen_15g());
                        }

                        returnRefNo = getLastGen15GNo() + "#" + getLastGen15HNo() + "#" + getNextGen15GNo() + "#" + getNextGen15HNo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    inputStream = new ByteArrayInputStream(returnRefNo.getBytes("UTF-8"));
                    return_msg = "inputString";
                } else if (getAction().equalsIgnoreCase("getRefNoType2Dtl")) {
                    String returnList = "";
                    String query = "";
                    try {
                        String refno_dtl_query = "";
                        if (!utl.isnull(getDispTempFlag()) && getDispTempFlag().equalsIgnoreCase("T")) {
                            Long l_client_loginid_seq;
                            Object sessionId = session.get("LOGINSESSIONID");
                            try {
                                l_client_loginid_seq = (Long) sessionId;
                            } catch (Exception e) {
                                l_client_loginid_seq = 0l;
                            }

                            Form15GHDB dtl_query = new Form15GHDB();
                            query = dtl_query.getRefNoDtlQuery(entity_code, accYear, quarterNo, tdsTypeCode, l_client_loginid_seq, getGenClientCode());
                        } else {
                            Form15GHDB dtl_query = new Form15GHDB();
                            query = dtl_query.show15GHReferanceNoDetail(entity_code, accYear, quarterNo, tdsTypeCode, getGenClientCode(), getFilterFlag(), getLoginFlag(), Client.getCode_level(), Client.getClient_code());
                        }
                        utl.generateSpecialLog("**15GH reference no. detail query", query);

                        GetDeductee15GHDataList deducteeObj = new GetDeductee15GHDataList();
                        ArrayList<ReferenceNoDetailPOJO> refNoList = deducteeObj.getDeducteeRefranceNoDetailsList(query, utl);
                        Deductee15GHSupport deductee15GHData = new Deductee15GHSupport();
                        returnList = deductee15GHData.getDeducteeVerifiyDtl(refNoList).toString();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    inputStream = new ByteArrayInputStream(returnList.getBytes("UTF-8"));
                    return_msg = "inputString";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_msg;
    }

    public String getNextGeneratedRefranceNo(String lastGenRefNo) {
        String l_next_gen_ref_no = "0";
        try {
            if (!utl.isnull(lastGenRefNo) && lastGenRefNo.equals("0")) {
                l_next_gen_ref_no = "000000001";
            } else {
                if (!utl.isnull(getLastGen15HNo())) {
                    int nextno = Integer.parseInt(lastGenRefNo) + 1;
                    if (String.valueOf(nextno).length() < 9) {
                        String generatedNo = String.valueOf(nextno);
                        for (int i = String.valueOf(nextno).length(); i < 10; i++) {
                            if (generatedNo.length() != 9) {
                                generatedNo = "0" + generatedNo;
                            }
                        }
                        l_next_gen_ref_no = generatedNo;
                    } else {
                        l_next_gen_ref_no = String.valueOf(nextno);
                    }
                }
            }
        } catch (Exception e) {
        }
        return l_next_gen_ref_no;
    }

    Map<String, Object> session;
    Util utl;
    private String action;
    private String clientLoginLevel;
    private String client_level_type;
    private String clientLevelType;
    private String generationLevelClient;
    private String GenClientCode;
    private String lastGen15HNo;
    private String NextGen15HNo;
    private String lastGen15GN;
    private String NextGen15GNo;
    private String lastGen15GNo;
    private InputStream inputStream;
    private String dispTempFlag;
    private String filterFlag;
    private int loginFlag;

    public int getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(int loginFlag) {
        this.loginFlag = loginFlag;
    }

    private LinkedHashMap<String, String> generationLevel;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClientLoginLevel() {
        return clientLoginLevel;
    }

    public void setClientLoginLevel(String clientLoginLevel) {
        this.clientLoginLevel = clientLoginLevel;
    }

    public String getClient_level_type() {
        return client_level_type;
    }

    public void setClient_level_type(String client_level_type) {
        this.client_level_type = client_level_type;
    }

    public String getClientLevelType() {
        return clientLevelType;
    }

    public void setClientLevelType(String clientLevelType) {
        this.clientLevelType = clientLevelType;
    }

    public LinkedHashMap<String, String> getGenerationLevel() {
        return generationLevel;
    }

    public void setGenerationLevel(LinkedHashMap<String, String> generationLevel) {
        this.generationLevel = generationLevel;
    }

    public String getGenerationLevelClient() {
        return generationLevelClient;
    }

    public void setGenerationLevelClient(String generationLevelClient) {
        this.generationLevelClient = generationLevelClient;
    }

    public String getGenClientCode() {
        return GenClientCode;
    }

    public void setGenClientCode(String GenClientCode) {
        this.GenClientCode = GenClientCode;
    }

    public String getLastGen15HNo() {
        return lastGen15HNo;
    }

    public void setLastGen15HNo(String lastGen15HNo) {
        this.lastGen15HNo = lastGen15HNo;
    }

    public String getLastGen15GNo() {
        return lastGen15GNo;
    }

    public void setLastGen15GNo(String lastGen15GNo) {
        this.lastGen15GNo = lastGen15GNo;
    }

    public String getNextGen15HNo() {
        return NextGen15HNo;
    }

    public void setNextGen15HNo(String NextGen15HNo) {
        this.NextGen15HNo = NextGen15HNo;
    }

    public String getLastGen15GN() {
        return lastGen15GN;
    }

    public void setLastGen15GN(String lastGen15GN) {
        this.lastGen15GN = lastGen15GN;
    }

    public String getNextGen15GNo() {
        return NextGen15GNo;
    }

    public void setNextGen15GNo(String NextGen15GNo) {
        this.NextGen15GNo = NextGen15GNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDispTempFlag() {
        return dispTempFlag;
    }

    public void setDispTempFlag(String dispTempFlag) {
        this.dispTempFlag = dispTempFlag;
    }

}
