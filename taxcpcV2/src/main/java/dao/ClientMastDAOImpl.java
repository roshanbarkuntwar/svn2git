/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ClientMastDAOImpl extends GenericHibernateDAO<hibernateObjects.ClientMast, Serializable> implements ClientMastDAO {

    @Override
    public ClientMast verifyGoogleAccountLogin(String profileId, String profileEmail) throws Exception {
        ClientMast client = null;
        try {
            List<ClientMast> data = readByCriteria(Restrictions.eq("appr_verify_code", profileId),
                    Restrictions.eq("email_id", profileEmail).ignoreCase());
            client = data != null && !data.isEmpty() ? data.get(0) : null;
        } catch (Exception ex) {
            client = null;
            throw ex;
        }
        return client;
    }

    @Override
    public String getDefaultDeductor(String entityCode, String clientCode, Util utl) {
        StringBuilder details = new StringBuilder();

        try {

            Criterion crtrn = Restrictions.eq("entity_code", entityCode);
            Criterion crtrp = Restrictions.eq("client_code", clientCode);
            List<ClientMast> data = readByCriteria(crtrn, crtrp);
            ClientMast client = data != null && data.size() > 0 ? data.get(0) : null;
            //System.out.println("client..." + client);
            if (client != null) {
//                details.append((utl.isnull(client.getClient_name()) ? "-" : client.getClient_name()) + " > " + (utl.isnull(client.getTanno()) ? "-" : client.getTanno()) + " > " + (utl.isnull(client.getBank_branch_code()) ? "-" : client.getBank_branch_code()));
                details.append("<span title=\"Client Name\">")
                        .append((utl.isnull(client.getClient_name()) ? "-" : client.getClient_name()))
                        .append("</span>")
                        .append(" > ")
                        .append("<span title=\"Client TANNO\">")
                        .append((utl.isnull(client.getTanno()) ? "-" : client.getTanno()))
                        .append("</span>")
                        .append(" > ")
                        .append("<span title=\"Bank Branch Code\">")
                        .append((utl.isnull(client.getBank_branch_code()) ? "-" : client.getBank_branch_code()))
                        .append("</span>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return details.toString();

    }

    @Override
    public Boolean checkUniqueEmailId(String loginId) {
        List<ClientMast> uselist = null;
        try {
            Criterion usrcrtry = Restrictions.eq("login_id", loginId).ignoreCase();
//            Criterion usrApproved = Restrictions.isNull("approvedby");
//            Criterion usrApprovedDate = Restrictions.isNull("approveddate");
            uselist = readByCriteria(usrcrtry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (uselist != null && uselist.size() > 0) ? false : true;
    }

    @Override
    public ClientMast readClientByClientCode(String clientCode) {
        Criterion usrcrtry = Restrictions.eq("client_code", clientCode);
        List<ClientMast> uselist = readByCriteria(usrcrtry);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }

    @Override
    public List<ClientMast> getClientMastByTanno(String tanno) {
        List<ClientMast> objList = null;
        try {
            Criteria criteria = getSession().createCriteria(ClientMast.class);
            criteria.add(Restrictions.eq("tanno", tanno).ignoreCase());
            objList = criteria.list();
            getSession().beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().beginTransaction().rollback();
        }
        return objList != null && objList.size() > 0 ? objList : null;
    }//End Method

    @Override
    public List<ClientMast> readClientByName(String name) {
        Criterion namecrt = Restrictions.like("client_name", name, MatchMode.ANYWHERE).ignoreCase();
        return readByCriteria(namecrt);
    }

    @Override
    public List<ClientMast> readClientByParent(String parent) {
        Criterion parntcrt = Restrictions.eq("parent_code", parent);
        return readByCriteria(parntcrt);
    }

    @Override
    public List<ClientMast> readClientByGParent(String parent) {
        Criterion parntcrt = Restrictions.eq("g_parent_code", parent);
        return readByCriteria(parntcrt);
    }

    @Override
    public ClientMast readClientByEmailId(String emailId) {
        Criterion usrcrtry = Restrictions.eq("login_id", emailId).ignoreCase();
        List<ClientMast> uselist = readByCriteria(usrcrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    @Override
    public ClientMast readClientByLoginIdAndPassword(String loginid, String password) {
        Criterion usrcrtry = Restrictions.eq("login_id", loginid).ignoreCase();
        Criterion pwdcrtry = Restrictions.eq("login_pwd", password);
        List<ClientMast> uselist = readByCriteria(usrcrtry, pwdcrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    @Override
    public String saveAndReturnIdentifier(ClientMast clientMast) {
        String id;
        try {
            getSession().persist(clientMast);
            getSession().flush();
            id = (String) getSession().getIdentifier(clientMast);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            id = null;
            getSession().getTransaction().rollback();
        }
        return id;
    }

    @Override
    public List<ClientMast> readUnApprovedClients() {
        List<ClientMast> readByCriteria;
        try {
            Criteria crit = getSession().createCriteria(ClientMast.class);
            crit.add(Restrictions.isNull("approvedby"));
            crit.addOrder(Order.asc("lastupdate"));
            readByCriteria = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            readByCriteria = null;
            getSession().getTransaction().rollback();
        }
        return (readByCriteria != null && readByCriteria.size() > 0) ? readByCriteria : null;
    }

    @Override
    public HashMap<String, String> getClientNameAsHashMap() {
        List<ClientMast> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (ClientMast type : data) {
                forms.put(type.getClient_code(), type.getClient_name());
            }
        }
        return forms;
    }

    @Override
    public ClientMast readClientByBranchCode(String branchCode) {
        Criterion crit = Restrictions.eq("bank_branch_code", branchCode.trim());
        List<ClientMast> readByCriteria = readByCriteria(crit);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<ClientMast> readMobileNoAutocomplete(String term) {
        List<ClientMast> mobileNoList;
        try {
            Criteria criteria = getSession().createCriteria(ClientMast.class);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("mobileno", term, MatchMode.START).ignoreCase());
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("mobileno"));
            mobileNoList = criteria.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
            mobileNoList = null;
        }
        return mobileNoList;
    }

    @Override
    public ClientMast getClientByClientCode(String clientCode) {
        List<ClientMast> data = null;
        Criteria criteria = getSession().createCriteria(ClientMast.class);
        criteria.add(Restrictions.eq("client_code", clientCode).ignoreCase());
        data = criteria.list();
        return data != null && data.size() > 0 ? data.get(0) : null;
    }

    @Override
    public ClientMast getClientByClientCodeAndEntityCode(String clientCode, String entityCode) {
        Criterion usrcrtry = Restrictions.eq("client_code", clientCode);
        Criterion entity = Restrictions.eq("entity_code", entityCode);
        List<ClientMast> uselist = readByCriteria(usrcrtry, entity);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }

    @Override
    public ClientMast readClientByClientName(String client_name) {
        Criterion client = Restrictions.eq("client_name", client_name.trim().toLowerCase()).ignoreCase();
        List<ClientMast> clientList = readByCriteria(client);
        return (clientList != null && clientList.size() > 0) ? clientList.get(0) : null;
    }

    @Override
    public List<ClientMast> readClientByEntityAndCodeLevel(String entityCode, String codeLevel, String clientCode) {
        Criterion entity_code = Restrictions.eq("entity_code", entityCode);
        Criterion code_level = Restrictions.eq("code_level", codeLevel);
        Criterion client_code_or = Restrictions.sqlRestriction("(client_code = '" + clientCode + "' or parent_code = '" + clientCode + "' or\n"
                + "       g_parent_code = '" + clientCode + "' or sg_parent_code = '" + clientCode + "' or\n"
                + "       ssg_parent_code = '" + clientCode + "' or sssg_parent_code = '" + clientCode + "')");

        List<ClientMast> clientList = readByCriteria(entity_code, code_level, client_code_or);
        return (clientList != null && clientList.size() > 0) ? clientList : null;
    }

    @Override
    public List<ClientMast> getXmlClients(String entityCode, String clientCode, String codeLevel) {
        List<ClientMast> clientList = null;
        try {
            Criterion entity_code = Restrictions.eq("entity_code", entityCode);
            Criterion code_level = Restrictions.eq("code_level", codeLevel);
            Criterion client_code = Restrictions.sqlRestriction("(client_code = '" + clientCode + "' or parent_code = '" + clientCode + "' or\n"
                    + "       g_parent_code = '" + clientCode + "' or sg_parent_code = '" + clientCode + "' or\n"
                    + "       ssg_parent_code = '" + clientCode + "' or sssg_parent_code = '" + clientCode + "')");

            clientList = readByCriteria(entity_code, code_level, client_code);
//            clientList.stream().forEach(System.out::println);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (clientList != null && clientList.size() > 0) ? clientList : null;
    }

    @Override
    public ClientMast readClientForVerifyOtp(String loginid, String userotp) {
        try {
            Criterion verifyotp = Restrictions.eq("appr_verify_code", userotp);
            Criterion usrcrtry = Restrictions.eq("login_id", loginid).ignoreCase();
            Criterion usrApproved = Restrictions.isNull("approvedby");
            Criterion usrApprovedDate = Restrictions.isNull("approveddate");
            List<ClientMast> readByCriteria = readByCriteria(usrcrtry, verifyotp, usrApproved, usrApprovedDate);

            return readByCriteria == null ? null : readByCriteria.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
