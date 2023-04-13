/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductorInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ClientMastDAO;
import dao.DeducteeBflagRefinfoSeqNoDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.DeducteeBflagRefinfoSeqNo;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class DeductorManipulationAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {utl.generateLog("DeductorManipulationAction", "");


        String l_return = "input";
        DAOFactory factory = null;
        ClientMastDAO clientDao = null;
        String str_value = "";
        factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        if (clientMast != null) {
            if (asmt != null) {
                String l_entity_code = clientMast.getEntity_code();
                String l_acc_year = asmt.getAccYear();
                try {
                    setClient_type(factory.getViewClientTypeDAO().getDeductorTypeAsHashMap());
                    setSelect_state(factory.getStateMastDAO().getStateCodeAsHashMap());
                    setSelect_country(factory.getCountryMastDAO().getCountryCodeAsHashMap());
                     
                    // set ClientCode from Session
                    if (utl.isnull(getClientCode())) {
                        setClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
                    }
                } catch (Exception e) {
                }

                try {

                    setSelectMinistryCode(factory.getViewMinistryMastDAO().getMinistryCodeAsList());
                    setSelectSubMinistryCode(factory.getViewSubMinistryMastDAO().getSubMinistryCodeAsList());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!utl.isnull(getAction())) {

                    if (getAction().equals("workOnDeductor")) {
                        ViewClientMastDAO viewclientDao = factory.getViewClientMastDAO();
                        ViewClientMast client = viewclientDao.readClientByClientCode(getWorking_clientCode());
                        if (client != null) {
//                            session.put("session_result", "Deductor Set Successfully");
//                            session.put("ERRORCLASS", ErrorType.successMessage);
                            session.put("WORKINGUSER", client);
                            try {
                                setParentFlag((String) session.get("PARENTFLAG"));
                                setChildFlag((String) session.get("CHILDFLAG"));
                            } catch (Exception e) {
                            }
                            l_return = "deductorSuccess";
                        } else {
                            // session.put("ERRORCLASS", ErrorType.errorMessage);
                            addActionError("Could Not Set Deductor, Some Error Occured");
                            l_return = "deductorError";
                        }
                    } else if (getAction().equals("DeleteDeductor")) {
                        clientDao = factory.getClientMastDAO();
                        ClientMast login_user = clientDao.readClientByClientCode(((ViewClientMast) session.get("LOGINUSER")).getClient_code());
                        if (!utl.isnull(login_user.getClient_code()) && (!login_user.getClient_code().equalsIgnoreCase(getClientCode()))) {
                            clientDao = factory.getClientMastDAO();
                            ClientMast client = clientDao.readClientByClientCode(getClientCode());
                            client.setClient_code(getClientCode());
                            clientDao = factory.getClientMastDAO();
                            boolean delete_result = clientDao.delete(client);
                            if (delete_result) {
                                session.put("session_result", "Record Delete Successfully");
                            } else {
                                addActionError("Could not delete, some error occured");
                                //session.put("ERRORCLASS", ErrorType.errorMessage);
                            }
                        } else {
                            addActionError("Could not delete Login deductor");
                            // session.put("ERRORCLASS", ErrorType.errorMessage);
                        }
                        l_return = "deleteSuccess";
                    } else if (getAction().equalsIgnoreCase("updateDeductor")) {

                        boolean resultReturnFlag = true;
                        clientDao = factory.getClientMastDAO();
                        //set Client Code in objectList object

                        ClientMast tempObjlist = getClientMastObj();
                        tempObjlist.setClient_code(getClientCode());
                        setClientMastObj(tempObjlist);
                        
                      ClientMast client = clientDao.readClientByClientCode(getClientCode());
                     
                        try {

                            if (!utl.isnull(getDeductorInfoLevel())) {

                                if (getDeductorInfoLevel().equalsIgnoreCase("PermanentInfo")) {
//
                                    client.setTraces_id(getClientMastObj().getTraces_id());
                                    client.setTraces_pwd(getClientMastObj().getTraces_pwd());
                                    client.setTanno(getClientMastObj().getTanno());
                                    client.setWeb1_login_id(getClientMastObj().getTanno());
                                    client.setWeb1_login_pwd(getClientMastObj().getWeb1_login_pwd());
                                    client.setPanno(getClientMastObj().getPanno());
                                    client.setClient_type_code(getClientMastObj().getClient_type_code());
                                    client.setClient_catg_code(getClientMastObj().getClient_catg_code());
                                    client.setClient_name(getClientMastObj().getClient_name());
                                    client.setBranch_division(getClientMastObj().getBranch_division());
                                    client.setGstn_no(getClientMastObj().getGstn_no());
                                    client.setBank_branch_code(getClientMastObj().getBank_branch_code());

                                    client.setMinistry_code(getClientMastObj().getMinistry_code());
                                    client.setSub_ministry_code(getClientMastObj().getSub_ministry_code());

                                    client.setAin_no(getClientMastObj().getAin_no());
                                    client.setPao_code(getClientMastObj().getPao_code());
                                    client.setPao_registration_no(getClientMastObj().getPao_registration_no());
                                    client.setDdo_code(getClientMastObj().getDdo_code());
                                    client.setDdo_registration_no(getClientMastObj().getDdo_registration_no());
                                    client.setReference_remark(getClientMastObj().getReference_remark());
                                    //client.setMinistry_state_code(getClientMastObj().getMinistry_state_code());

                                    if (!utl.isnull(getGetChkBxFlag()) && getGetChkBxFlag().equalsIgnoreCase("T")) {

                                        try {//auto ge nerate bflag
                                            String conClientCode = "";
                                            try {
                                                GenlClientCodeAction objgenlevel = new GenlClientCodeAction();
                                                conClientCode = objgenlevel.getgenLevelClientCodeValue(clientMast, asmt, "getConsolidateLevelClient");
                                            } catch (Exception e) {
                                            }
                                            if (utl.isnull(conClientCode)) {
                                                conClientCode = getClientCode();
                                            }

                                            DeducteeBflagRefinfoSeqNoDAO deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                            if (getConsolidate_chkbx_value().equals("1")) {
                                                if (getBflag_value().equals("1")) {
                                                    DeducteeBflagRefinfoSeqNo bflagSeqNoG = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "G");
                                                    if (bflagSeqNoG == null) {
                                                        DeducteeBflagRefinfoSeqNo deducteeBflagObjG = new DeducteeBflagRefinfoSeqNo();
                                                        try {
                                                            deducteeBflagObjG.setEntity_code(l_entity_code);
                                                            deducteeBflagObjG.setClient_code(conClientCode);
                                                            deducteeBflagObjG.setAcc_year(l_acc_year);
                                                            deducteeBflagObjG.setBflag("G");
                                                            deducteeBflagObjG.setLastupdate(new Date());
                                                            deducteeBflagObjG.setAuto_gen_ref_no("1");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjG);
                                                            //System.out.println("update bflag result.a1.." + result);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        try {
                                                            bflagSeqNoG.setAuto_gen_ref_no("1");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.update(bflagSeqNoG);
                                                            //System.out.println("update bflag result.a2.." + result);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                    try {
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        DeducteeBflagRefinfoSeqNo bflagSeqNoH = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "H");
                                                        if (bflagSeqNoH == null) {
                                                            DeducteeBflagRefinfoSeqNo deducteeBflagObjH = new DeducteeBflagRefinfoSeqNo();
                                                            try {
                                                                deducteeBflagObjH.setEntity_code(l_entity_code);
                                                                deducteeBflagObjH.setClient_code(conClientCode);
                                                                deducteeBflagObjH.setAcc_year(l_acc_year);
                                                                deducteeBflagObjH.setBflag("H");
                                                                deducteeBflagObjH.setLastupdate(new Date());
                                                                deducteeBflagObjH.setAuto_gen_ref_no("1");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjH);
                                                                //System.out.println("update bflag result.a3.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        } else {
                                                            try {
                                                                bflagSeqNoH.setAuto_gen_ref_no("1");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.update(bflagSeqNoH);
                                                                //System.out.println("update bflag result.a4.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else if (getBflag_value().equals("0")) {
                                                    DeducteeBflagRefinfoSeqNo bflagSeqNoG = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "G");
                                                    if (bflagSeqNoG == null) {
                                                        DeducteeBflagRefinfoSeqNo deducteeBflagObjG = new DeducteeBflagRefinfoSeqNo();
                                                        try {
                                                            deducteeBflagObjG.setEntity_code(l_entity_code);
                                                            deducteeBflagObjG.setClient_code(conClientCode);
                                                            deducteeBflagObjG.setAcc_year(l_acc_year);
                                                            deducteeBflagObjG.setBflag("G");
                                                            deducteeBflagObjG.setLastupdate(new Date());
                                                            deducteeBflagObjG.setAuto_gen_ref_no("0");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjG);
                                                            //System.out.println("update bflag result.a5.." + result);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        bflagSeqNoG.setAuto_gen_ref_no("0");
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        boolean result = deducteeBflagSeqNodao.update(bflagSeqNoG);
                                                        //System.out.println("update bflag result.a6.." + result);
                                                    }

                                                    deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                    DeducteeBflagRefinfoSeqNo bflagSeqNoH = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "H");
                                                    if (bflagSeqNoH == null) {
                                                        DeducteeBflagRefinfoSeqNo deducteeBflagObjH = new DeducteeBflagRefinfoSeqNo();
                                                        try {
                                                            deducteeBflagObjH.setEntity_code(l_entity_code);
                                                            deducteeBflagObjH.setClient_code(conClientCode);
                                                            deducteeBflagObjH.setAcc_year(l_acc_year);
                                                            deducteeBflagObjH.setBflag("H");
                                                            deducteeBflagObjH.setLastupdate(new Date());
                                                            deducteeBflagObjH.setAuto_gen_ref_no("0");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjH);
                                                            //System.out.println("update bflag result.a7.." + result);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        bflagSeqNoH.setAuto_gen_ref_no("0");
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        boolean result = deducteeBflagSeqNodao.update(bflagSeqNoH);
                                                        //System.out.println("update bflag result.a8.." + result);
                                                    }
                                                }
                                            } else if (getConsolidate_chkbx_value().equals("0")) {

                                                if (getBflag_value().equals("1")) {
                                                    try {
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        DeducteeBflagRefinfoSeqNo bflagSeqNoG = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "G");
                                                        if (bflagSeqNoG != null) {
                                                            bflagSeqNoG.setAuto_gen_ref_no("1");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.update(bflagSeqNoG);
                                                            //System.out.println("update bflag result.a9.." + result);
                                                        } else if (getEditIsClientLoginLevel() == 6) {
                                                            DeducteeBflagRefinfoSeqNo deducteeBflagObjG = new DeducteeBflagRefinfoSeqNo();
                                                            try {
                                                                deducteeBflagObjG.setEntity_code(l_entity_code);
                                                                deducteeBflagObjG.setClient_code(conClientCode);
                                                                deducteeBflagObjG.setAcc_year(l_acc_year);
                                                                deducteeBflagObjG.setBflag("G");
                                                                deducteeBflagObjG.setLastupdate(new Date());
                                                                deducteeBflagObjG.setAuto_gen_ref_no("1");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjG);
                                                                //System.out.println("update bflag result.a10.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                    try {
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        DeducteeBflagRefinfoSeqNo bflagSeqNoH = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "H");
                                                        if (bflagSeqNoH != null) {
                                                            bflagSeqNoH.setAuto_gen_ref_no("1");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.update(bflagSeqNoH);
                                                            //System.out.println("update bflag result.a11.." + result);
                                                        } else if (getEditIsClientLoginLevel() == 6) {
                                                            DeducteeBflagRefinfoSeqNo deducteeBflagObjH = new DeducteeBflagRefinfoSeqNo();
                                                            try {
                                                                deducteeBflagObjH.setEntity_code(l_entity_code);
                                                                deducteeBflagObjH.setClient_code(conClientCode);
                                                                deducteeBflagObjH.setAcc_year(l_acc_year);
                                                                deducteeBflagObjH.setBflag("H");
                                                                deducteeBflagObjH.setLastupdate(new Date());
                                                                deducteeBflagObjH.setAuto_gen_ref_no("1");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjH);
                                                                //System.out.println("update bflag result.a12.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                } else if (getBflag_value().equals("0")) {

                                                    try {
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        DeducteeBflagRefinfoSeqNo bflagSeqNoG = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "G");
                                                        if (bflagSeqNoG != null) {
                                                            bflagSeqNoG.setAuto_gen_ref_no("0");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.update(bflagSeqNoG);
                                                            //System.out.println("update bflag result.a13.." + result);
                                                        } else if (getEditIsClientLoginLevel() == 6) {

                                                            DeducteeBflagRefinfoSeqNo deducteeBflagObjG = new DeducteeBflagRefinfoSeqNo();
                                                            try {
                                                                deducteeBflagObjG.setEntity_code(l_entity_code);
                                                                deducteeBflagObjG.setClient_code(conClientCode);
                                                                deducteeBflagObjG.setAcc_year(l_acc_year);
                                                                deducteeBflagObjG.setBflag("G");
                                                                deducteeBflagObjG.setLastupdate(new Date());
                                                                deducteeBflagObjG.setAuto_gen_ref_no("0");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjG);
                                                                //System.out.println("update bflag result.a14.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                    try {
                                                        deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                        DeducteeBflagRefinfoSeqNo bflagSeqNoH = deducteeBflagSeqNodao.readDeducteeBflagRefinfoByBFlag(l_entity_code, conClientCode, l_acc_year, "H");
                                                        if (bflagSeqNoH != null) {
                                                            bflagSeqNoH.setAuto_gen_ref_no("0");
                                                            deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                            boolean result = deducteeBflagSeqNodao.update(bflagSeqNoH);
                                                            //System.out.println("update bflag result.a15.." + result);
                                                        } else if (getEditIsClientLoginLevel() == 6) {
                                                            DeducteeBflagRefinfoSeqNo deducteeBflagObjH = new DeducteeBflagRefinfoSeqNo();
                                                            try {
                                                                deducteeBflagObjH.setEntity_code(l_entity_code);
                                                                deducteeBflagObjH.setClient_code(conClientCode);
                                                                deducteeBflagObjH.setAcc_year(l_acc_year);
                                                                deducteeBflagObjH.setBflag("H");
                                                                deducteeBflagObjH.setLastupdate(new Date());
                                                                deducteeBflagObjH.setAuto_gen_ref_no("0");
                                                                deducteeBflagSeqNodao = factory.getDeducteeBflagRefinfoSeqNoDAO();
                                                                boolean result = deducteeBflagSeqNodao.saveOrUpdate(deducteeBflagObjH);
                                                                //System.out.println("update bflag result.a16.." + result);
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }

                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }

                                    }

                                    //=====================update deductee bflag end======================================
                                }

                                if (getDeductorInfoLevel().equalsIgnoreCase("AddressInfo")) {

                                    client.setAdd1(getClientMastObj().getAdd1());
                                    client.setAdd2(getClientMastObj().getAdd2());
                                    client.setAdd3(getClientMastObj().getAdd3());
                                    client.setAdd4(getClientMastObj().getAdd4());
                                    client.setCity_code(getClientMastObj().getCity_code());
                                    client.setPin(getClientMastObj().getPin());
                                    client.setStdcode(getClientMastObj().getStdcode());
                                    client.setState_code(getClientMastObj().getState_code());
                                    client.setPhoneno(getClientMastObj().getPhoneno());
                                    client.setMobileno(getClientMastObj().getMobileno());
                                    client.setEmail_id(getClientMastObj().getEmail_id());

                                    client.setAlternate_stdcode(getClientMastObj().getAlternate_stdcode());
                                    client.setAlternate_phoneno(getClientMastObj().getAlternate_phoneno());
                                    client.setAlternate_mobileno(getClientMastObj().getAlternate_mobileno());
                                    client.setAlternate_email_id(getClientMastObj().getAlternate_email_id());
                                    client.setDeductor_add_change("N");
                                }

                                if (getDeductorInfoLevel().equalsIgnoreCase("ContactInfo")) {

                                    client.setDeductor_name(getClientMastObj().getDeductor_name());
                                    client.setDeductor_desig(getClientMastObj().getDeductor_desig());
                                    client.setDeductor_phoneno(getClientMastObj().getDeductor_phoneno());
                                    client.setDeductor_mobileno(getClientMastObj().getDeductor_mobileno());
                                    client.setDeductor_email_id(getClientMastObj().getDeductor_email_id());

                                    client.setDeductor_panno(getClientMastObj().getDeductor_panno());
                                    client.setDeductor_add1(getClientMastObj().getDeductor_add1());
                                    client.setDeductor_add2(getClientMastObj().getDeductor_add2());
                                    client.setDeductor_add3(getClientMastObj().getDeductor_add3());
                                    client.setDeductor_add4(getClientMastObj().getDeductor_add4());
                                    client.setDeductor_city_code(getClientMastObj().getDeductor_city_code());
                                    client.setDeductor_pin(getClientMastObj().getDeductor_pin());
                                    client.setDeductor_stdcode(getClientMastObj().getDeductor_stdcode());
                                    client.setDeductor_state_code(getClientMastObj().getDeductor_state_code());
                                    String geo_org_code = getClientMastObj().getGeo_org_code();
                                    if (utl.isnull(geo_org_code)) {
                                        client.setGeo_org_code("F");
                                    } else {
                                        client.setGeo_org_code(getClientMastObj().getGeo_org_code());
                                    }
                                    
                                    client.setDeductor_add_change("N");
                                }

                                if (getDeductorInfoLevel().equalsIgnoreCase("MinistryInfo")) {
                                    System.out.println("bbbb-->"+getClientMastObj().toString());
                                    client.setMinistry_code(getClientMastObj().getMinistry_code());
                                    client.setSub_ministry_code(getClientMastObj().getSub_ministry_code());
                                    client.setAin_no(getClientMastObj().getAin_no());
                                    client.setPao_code(getClientMastObj().getPao_code());
                                    client.setPao_registration_no(getClientMastObj().getPao_registration_no());
                                    client.setDdo_code(getClientMastObj().getDdo_code());
                                    client.setDdo_registration_no(getClientMastObj().getDdo_registration_no());
                                    client.setReference_remark(getClientMastObj().getReference_remark());
                                      if (!utl.isnull(getClientMastObj().getMinistry_state_code())) {
                                          client.setMinistry_state_code(getClientMastObj().getMinistry_state_code());   
                                          System.out.println("ministry state code-->"+getClientMastObj().getMinistry_state_code());
                                         
                                    }
                                 //  System.out.println("getClientMastObj().getMinistry_state_code()-->"+getClientMastObj().getMinistry_state_code());
                                  
                                }

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (!utl.isnull(getClientMastObj().getEntity_code())) {
                            try {
                                DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
                                String functionString = "select t.country from entity_mast t where t.entity_code = '" + getClientMastObj().getEntity_code() + "'";
                                String resultValue = ef.execute_oracle_function_as_string(functionString);
                                client.setCountry_code(resultValue);
                                client.setDeductor_country_code(resultValue);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        boolean result = false;
                        if (resultReturnFlag) {
                            //client.setMinistry_state_code(getClientMastObj().getMinistry_state_code());
                            clientDao = factory.getClientMastDAO();
                            result = clientDao.update(client);
                        } else {
                            result = false;
                        }

                        if (result) {
                            str_value = "success";
                            l_return = "loadEditDeductor";

                            ViewClientMast newWorkingUser = factory.getViewClientMastDAO().readClientByClientCode(client.getClient_code());
                            session.put("WORKINGUSER", newWorkingUser);
                        } else {
                            str_value = "error";
                            l_return = "loadEditDeductor";
                        }
                    }

                } else {
                    l_return = "login";
                }

            } else {
                str_value = "setassessment";
                l_return = "loadEditDeductor";
            }

        }
//        if(!utl.isnull(getMinstatecode())){
//        System.out.println("min ssss code"+getMinstatecode());
//        }
        inputStream = new ByteArrayInputStream(str_value.getBytes("UTF-8"));
        return l_return;
    }

    public DeductorManipulationAction() {

        utl = new Util();

        select_type = new HashMap<String, String>();
        select_type.put("", "select");

        select_department = new HashMap<String, String>();
        select_department.put("", "select");

        select_state = new HashMap<String, String>();
        select_state.put("", "select");
        select_country = new HashMap<String, String>();
        client_type = new HashMap<String, String>();

        selectMinistryCode = new LinkedHashMap<String, String>();
        selectMinistryCode.put("", "select");

        selectSubMinistryCode = new LinkedHashMap<String, String>();
        selectSubMinistryCode.put("", "select");

        selectClientStatus = new LinkedHashMap<String, String>();
        selectClientStatus.put("", "-----select------");
        selectClientStatus.put("R", "Running");
        selectClientStatus.put("C", "Closed");

    }

    Map<String, Object> session;
    private HashMap<String, String> select_country;
    private HashMap<String, String> client_type;
    private HashMap<String, String> select_state;
    Util utl;
    private String bflag_value;
    private ClientMast clientMastObj;
    private String deductorInfoLevel;
    private HashMap<String, String> select_type;
    private HashMap<String, String> select_department;
    private String parentFlag;
    private int editIsClientLoginLevel;
    private String childFlag;
    private String consolidate_chkbx_value;
    private String clientCode;
    private int levelGenCount;
    private InputStream inputStream;
    private LinkedHashMap<String, String> selectClientStatus;
    private int BFlagGenCount;
    private String getChkBxFlag;
    private String action;
    private LinkedHashMap<String, String> selectMinistryCode;
    private String working_clientCode;
    private LinkedHashMap<String, String> selectSubMinistryCode;
  

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public HashMap<String, String> getClient_type() {
        return client_type;
    }

    public void setClient_type(HashMap<String, String> client_type) {
        this.client_type = client_type;
    }

    public HashMap<String, String> getSelect_country() {
        return select_country;
    }

    public void setSelect_country(HashMap<String, String> select_country) {
        this.select_country = select_country;
    }

    public HashMap<String, String> getSelect_state() {
        return select_state;
    }

    public void setSelect_state(HashMap<String, String> select_state) {
        this.select_state = select_state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
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

    public String getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(String parentFlag) {
        this.parentFlag = parentFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getWorking_clientCode() {
        return working_clientCode;
    }

    public void setWorking_clientCode(String working_clientCode) {
        this.working_clientCode = working_clientCode;
    }

    public ClientMast getClientMastObj() {
        return clientMastObj;
    }

    public void setClientMastObj(ClientMast clientMastObj) {
        this.clientMastObj = clientMastObj;
    }

    public String getDeductorInfoLevel() {
        return deductorInfoLevel;
    }

    public void setDeductorInfoLevel(String deductorInfoLevel) {
        this.deductorInfoLevel = deductorInfoLevel;
    }

    public int getLevelGenCount() {
        return levelGenCount;
    }

    public void setLevelGenCount(int levelGenCount) {
        this.levelGenCount = levelGenCount;
    }

    public int getBFlagGenCount() {
        return BFlagGenCount;
    }

    public void setBFlagGenCount(int BFlagGenCount) {
        this.BFlagGenCount = BFlagGenCount;
    }

    public String getGetChkBxFlag() {
        return getChkBxFlag;
    }

    public void setGetChkBxFlag(String getChkBxFlag) {
        this.getChkBxFlag = getChkBxFlag;
    }

    public String getConsolidate_chkbx_value() {
        return consolidate_chkbx_value;
    }

    public void setConsolidate_chkbx_value(String consolidate_chkbx_value) {
        this.consolidate_chkbx_value = consolidate_chkbx_value;
    }

    public String getBflag_value() {
        return bflag_value;
    }

    public void setBflag_value(String bflag_value) {
        this.bflag_value = bflag_value;
    }

    public int getEditIsClientLoginLevel() {
        return editIsClientLoginLevel;
    }

    public void setEditIsClientLoginLevel(int editIsClientLoginLevel) {
        this.editIsClientLoginLevel = editIsClientLoginLevel;
    }

    public HashMap<String, String> getSelect_type() {
        return select_type;
    }

    public void setSelect_type(HashMap<String, String> select_type) {
        this.select_type = select_type;
    }

    public HashMap<String, String> getSelect_department() {
        return select_department;
    }

    public void setSelect_department(HashMap<String, String> select_department) {
        this.select_department = select_department;
    }

    public LinkedHashMap<String, String> getSelectClientStatus() {
        return selectClientStatus;
    }

    public void setSelectClientStatus(LinkedHashMap<String, String> selectClientStatus) {
        this.selectClientStatus = selectClientStatus;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

   

}
