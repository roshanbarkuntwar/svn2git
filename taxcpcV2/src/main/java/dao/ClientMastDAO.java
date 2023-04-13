/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ClientMastDAO extends GenericDAO<hibernateObjects.ClientMast, Serializable> {

    ClientMast verifyGoogleAccountLogin(String profileId, String profileEmail) throws Exception;

    String getDefaultDeductor(String entityCode, String clientCode, Util utl);

    Boolean checkUniqueEmailId(String loginId);

    List<ClientMast> getClientMastByTanno(String tanno);

    ClientMast readClientByClientCode(String loginid);

    List<ClientMast> readClientByName(String name);

    List<ClientMast> readClientByParent(String parent);

    List<ClientMast> readClientByGParent(String parent);

    ClientMast readClientByEmailId(String emailId);

    ClientMast readClientByLoginIdAndPassword(String loginid, String password);

    @Override
    String saveAndReturnIdentifier(ClientMast clientMast);

    List<ClientMast> readUnApprovedClients();

    HashMap<String, String> getClientNameAsHashMap();

    ClientMast readClientByBranchCode(String branchCode);

    public List<ClientMast> readMobileNoAutocomplete(String term);

    ClientMast getClientByClientCode(String clientCode);

    ClientMast getClientByClientCodeAndEntityCode(String clientCode, String entityCode);

    ClientMast readClientByClientName(String loginid);

    List<ClientMast> readClientByEntityAndCodeLevel(String entityCode, String codeLevel, String clientCode);

    List<ClientMast> getXmlClients(String entityCode, String clientCode, String userLevel);

    ClientMast readClientForVerifyOtp(String loginid, String userotp);

}
