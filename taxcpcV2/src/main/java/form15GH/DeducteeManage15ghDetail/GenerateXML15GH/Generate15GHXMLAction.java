/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.DeducteeManage15ghDetail.GenerateXML15GH;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.DbQueryExecutorAsList;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author seema.mourya
 */
public class Generate15GHXMLAction extends ActionSupport implements SessionAware
{
    
    public String execute() throws Exception{
        
       
        String returnView = "success";
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
           // ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String l_client_code = "";
            String l_entity_code = "";
            String acc_year = "";
            //String branch_code = "";
            //String quarterNo = "";
            int quarterNoInt = 0;
            if (client != null) {
              
                if (asmt != null) {
                 
                    l_client_code = client.getClient_code();
                    l_entity_code = client.getEntity_code();
                    //branch_code = clientMast.getBank_branch_code();
                    acc_year = asmt.getAccYear();
                    String quarterNo = asmt.getQuarterNo();
                    String tds_type_code = asmt.getTdsTypeCode();
                   // String[] quarterNameSplit = tds_quarter.split("-");
                   // quarterNo = quarterNameSplit[2];
                    quarterNoInt = Integer.parseInt(quarterNo);

                    String gflag = "false";
                    try {
                        
                        
                        Object flag = session.get("generateFlag");
                        if (flag != null) {
                            gflag = flag.toString();
                        }
                    } catch (Exception e) {

                    }

//                    String isClientTeanLevel = "1";
//                    String clientLevelType = "1";
//                    try {
//                        isClientTeanLevel = clientMast.getIs_client_login_level();
//                        clientLevelType = clientMast.getClient_level_type();
//                    } catch (Exception e) {
//
//                    }
//                    String loginLevel = "";
//                    if (Integer.valueOf(isClientTeanLevel) == Integer.valueOf(clientLevelType)) {
//                        loginLevel = "LastLevel";
//                    } else {
//                        loginLevel = "upperLevel";
//                    }
//
//                    String tannoVal = "";
//
//                    System.out.println("loginLevel..." + loginLevel);
//                    setClientLoginLevel(loginLevel);
                    if (!utl.isnull(getAction())) {
                      
                        if (!utl.isnull(gflag) && !gflag.equalsIgnoreCase("true")) {
                            if (getAction().equalsIgnoreCase("generate")) {

                                session.put("generateFlag", "true");
                                Generate15GHXmlSupport support = new Generate15GHXmlSupport();
                                ArrayList<ArrayList<String>> arrData = null;
//                            if (!utl.isnull(getClientLoginLevel()) && getClientLoginLevel().equalsIgnoreCase("LastLevel")) {
//                                tannoVal = "c.tanno";
//                                ArrayList<String> arr = new ArrayList<String>();
//                                arr.add(l_client_code);
//                                arrData = new ArrayList<ArrayList<String>>();
//                                arrData.add(arr);
                                // } else if (!utl.isnull(getClientLoginLevel()) && getClientLoginLevel().equalsIgnoreCase("upperLevel")) {
//                            if (!utl.isnull(getGenType()) && getGenType().equalsIgnoreCase("L")) {
//                                String l_tanno = utl.isnull(clientMast.getTanno()) ? "" : clientMast.getTanno();
//                                tannoVal = "'" + l_tanno + "'";
//                                ArrayList<String> arr = new ArrayList<String>();
//                                arr.add(l_client_code);
//                                arrData = new ArrayList<ArrayList<String>>();
//                                arrData.add(arr);
//                            } else {
//                                tannoVal = "c.tanno";
                                String l_query
                                        //                                        = "select client_code\n"
                                        //                                        + "  from client_mast t\n"
                                        //                                        + " where entity_code = '" + l_entity_code + "'\n"
                                        //                                        + "   and code_level = 6\n"
                                        //                                        + "   and (client_code = '" + l_client_code + "' or parent_code = '" + l_client_code + "' or\n"
                                        //                                        + "       g_parent_code = '" + l_client_code + "' or sg_parent_code = '" + l_client_code + "' or\n"
                                        //                                        + "       ssg_parent_code = '" + l_client_code + "' or sssg_parent_code = '" + l_client_code + "')\n"
                                        //                                        + "   and exists\n"
                                        //                                        + " (select 1\n"
                                        //                                        + "          from deductee_mast_15gh a\n"
                                        //                                        + "         where a.entity_code = t.entity_code\n"
                                        //                                        + "           and a.client_code = t.client_code\n"
                                        //                                        + "           and a.acc_year = '" + acc_year + "'\n"
                                        //                                        + "           and a.quarter_no = " + quarterNoInt + "\n"
                                        //                                        + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                                        //                                        + "           and not exists (select 1\n"
                                        //                                        + "                  from tran_load_error_15gh b\n"
                                        //                                        + "                 where b.entity_code = a.entity_code\n"
                                        //                                        + "                   and b.client_code = a.client_code\n"
                                        //                                        + "                   and b.acc_year = a.acc_year\n"
                                        //                                        + "                   and b.quarter_no = a.quarter_no\n"
                                        //                                        + "                   and b.tds_type_code = a.tds_type_code\n"
                                        //                                        + "                   and b.rowid_seq = a.rowid_seq))\n"
                                        //                                        + " order by client_code";
                                        = "SELECT B.CLIENT_CODE\n"
                                        + "  FROM client_mast B\n"
                                        + " where B.entity_code = '" + l_entity_code + "'"
                                        + "   and code_level = '" + selectedLevel + "'\n"
                                        + "   and (client_code = '" + l_client_code + "' or parent_code = '" + l_client_code + "' or\n"
                                        + "       g_parent_code = '" + l_client_code + "' or sg_parent_code = '" + l_client_code + "' or\n"
                                        + "       ssg_parent_code = '" + l_client_code + "' or sssg_parent_code = '" + l_client_code + "')\n";

                                utl.generateSpecialLog("15GH-GX-0001(Branch Level Total Client Query)----", l_query);
                                DbQueryExecutorAsList objData = new DbQueryExecutorAsList();
                                arrData = objData.execute_oracle_query_as_list(l_query);
//                            }
                                //}
                                try {
                                     
                                    if (arrData != null && arrData.size() > 0) {
                                        ClientMastDAO clientDAO = factory.getClientMastDAO();
                                        String l_systemDate = utl.get_sysdate("dd_MM_yyyy_hh_mm_ss_SSS");
//                                      System.out.println("l_systemDate---"+l_systemDate);
                                        String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                                        String rootLoc = javaDriveName + File.separator + "BULK_15G_H_XML";
                                        String clientCodeLoc = rootLoc + File.separator + l_client_code;
                                        String accYearLoc = clientCodeLoc + File.separator + acc_year;
                                        String tdsTypeCodeLoc = accYearLoc + File.separator + asmt.getTdsTypeCode();
                                        String finalPath = tdsTypeCodeLoc + File.separator + l_systemDate + File.separator;

                                        support.createDirectory(rootLoc);
                                        support.createDirectory(clientCodeLoc);
                                        support.createDirectory(accYearLoc);
                                        support.createDirectory(tdsTypeCodeLoc);
                                        support.createDirectory(finalPath);
                                        setFilePath(finalPath);
                                        setFileName(l_client_code + "_" + acc_year + "_" + asmt.getTdsTypeCode() + "_" + l_systemDate);
                                        List tempList = new ArrayList<String>();
                                        tempList.add("G");
                                        tempList.add("H");
                                        int l_rec_gen_count = 0;
                                        for (Object ghChar : tempList) {
                                            for (ArrayList<String> arrayList : arrData) {
                                                String g_client_code = arrayList.get(0);
                                                g_client_code = utl.isnull(g_client_code) ? "" : g_client_code;
                                                clientDAO = factory.getClientMastDAO();
                                                ClientMast g_client = clientDAO.readClientByClientCode(g_client_code);
                                                ArrayList<Generate15GHXmlClientDetails> clientDetails = new Generate15GHXmlSupport().execute_oracle_query_as_bean_object(g_client.getEntity_code(), g_client.getClient_code(), asmt.getAccYear(), quarterNo, tds_type_code, (String) ghChar, g_client.getTanno());
                                                if (clientDetails != null && clientDetails.size() > 0) {

                                                    String bankBranch = "";
                                                    if (!utl.isnull(g_client.getBank_branch_code())) {
                                                        bankBranch = "_" + g_client.getBank_branch_code();
                                                    }
                                                    String fileLoc15G = finalPath + File.separator + g_client.getClient_code() + bankBranch + "_15" + ghChar + "_xml.xml";
                                                    String fileNameG = g_client.getClient_code() + bankBranch + "_15" + ghChar + "_xml.xml";
                                                    String sourceG = finalPath + fileNameG;
                                                    String destnG = finalPath + g_client.getClient_code() + bankBranch + "_15" + ghChar + "_xml" + ".zip";
                                                    int createXML = support.createXML(fileLoc15G, g_client.getEntity_code(), g_client.getClient_code(), asmt.getAccYear(), clientDetails, g_client.getTanno(), quarterNoInt, (String) ghChar, g_client, asmt, fileNameG, sourceG, destnG);
                                                    l_rec_gen_count++;
//                                                System.out.println("l_rec_gen_count" + l_rec_gen_count++);
                                                }
                                            }
                                        }
                                        if (l_rec_gen_count > 0) {
                                            setFileGenerated(true);
                                            addActionError("(" + l_rec_gen_count + ")- 15G/15H files generated successfully");
                                            session.put("ERRORCLASS", ErrorType.successMessage);
                                        } else {
                                           
                                            addActionError("No Data Found, Could Not Generate");
                                            session.put("ERRORCLASS", ErrorType.errorMessage);
                                        }

                                    } else {
                                        
                                        addActionError("Some Error Occured, Could Not Generate");
                                        session.put("ERRORCLASS", ErrorType.errorMessage);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                         
                            setErrorMessage("Your previous process is already in use , We request you to try again after some time !");
                            returnView = "error";
                           
                        }
                        // returnView = "process_success";
                    } else {
                      
                        session.put("generateFlag", "false");
                    }

                } else {
                   
                    returnView = "setassessment";
                }

                try {
                
                    utl.generateLog(null, "Garbage Collection Start");
                    System.gc();
               
                    utl.generateLog(null, "Garbage Collection End");
                     
                } catch (Exception e) {
                    // e.printStackTrace();
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
             
        }
       
        return returnView;
    }

     public Generate15GHXMLAction() {
        utl = new Util();
    }//end constructor

    private final Util utl;
    private Map<String, Object> session;
    private String action;
    private Boolean fileGenerated;
    private String fileName;
    private String filePath;
    private String clientLoginLevel;
    private String cgl;
    private String genType;
    private String selectedLevel;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
    
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getFileGenerated() {
        return fileGenerated;
    }

    public void setFileGenerated(Boolean fileGenerated) {
        this.fileGenerated = fileGenerated;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getClientLoginLevel() {
        return clientLoginLevel;
    }

    public void setClientLoginLevel(String clientLoginLevel) {
        this.clientLoginLevel = clientLoginLevel;
    }

    public String getCgl() {
        return cgl;
    }

    public void setCgl(String cgl) {
        this.cgl = cgl;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(String selectedLevel) {
        this.selectedLevel = selectedLevel;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}
