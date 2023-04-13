/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ViewClientTypeDAOImpl extends GenericHibernateDAO<ViewClientType, Serializable>
        implements ViewClientTypeDAO {

    @Override
    public HashMap<String, String> getDeductorTypeAsHashMap() {
        List<ViewClientType> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (ViewClientType type : data) {
                forms.put(type.getClient_type_code() + "~" + type.getPan_card_type(), type.getClient_type_name());
            }
        }
        return forms;
    }

    @Override
    public HashMap<String, String> getClientTypeAsHashMap() {
        List<ViewClientType> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (ViewClientType type : data) {
                forms.put(type.getClient_type_code(), type.getClient_type_name());
            }
        }
        return forms;
    }

    @Override
    public ArrayList<ArrayList<String>> getDeductorTypeAsArrayList() {
        List<ViewClientType> data = readAll();
        ArrayList<ArrayList<String>> forms = new ArrayList<ArrayList<String>>();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                ViewClientType type = data.get(i);
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(type.getClient_type_code());
                arr.add(type.getClient_type_name());
                arr.add(type.getPan_card_type());
                forms.add(arr);
            }
        }
        return forms;
    }

    @Override
    public ArrayList<ArrayList<String>> getDeductorCatgAsLinkedPanno(String panno) {
        ArrayList<ArrayList<String>> clientTypeMap = new ArrayList<ArrayList<String>>();
        List<ViewClientType> clientTypeList;

        try {
            Criteria crit = getSession().createCriteria(ViewClientType.class);
            if (panno.equals("JPTBKFAQLN")) {
                crit.add(Restrictions.sqlRestriction("instr('" + panno + "',pan_card_type) <> 0"));
            } else {
                crit.add(Restrictions.eq("pan_card_type", panno).ignoreCase());
            }
            crit.addOrder(Order.asc("client_type_name"));
            clientTypeList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            clientTypeList = null;
            getSession().getTransaction().rollback();
        }

        clientTypeList = clientTypeList != null && clientTypeList.size() > 0 ? clientTypeList : null;
        if (clientTypeList != null) {
            for (ViewClientType viewClientType : clientTypeList) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(viewClientType.getClient_type_code());
                arr.add(viewClientType.getClient_type_name());
                arr.add(viewClientType.getPan_card_type());
                clientTypeMap.add(arr);
            }
        }
        return clientTypeMap;
    }

    @Override
    public LinkedHashMap<String, String> getDeductorTypeAsLinkedHashMap() {
        LinkedHashMap<String, String> forms;
        try {
            Criteria crit = getSession().createCriteria(ViewClientType.class);
            crit.addOrder(Order.asc("client_type_name"));
            List<ViewClientType> data = crit.list();
            forms = new LinkedHashMap<String, String>();
            forms.put("", "-------select-------");
            if (data != null) {
                for (ViewClientType type : data) {
                    forms.put(type.getClient_type_code(), type.getClient_type_name());
                }
            }
            getSession().getTransaction().commit();
        } catch (Exception e) {
            forms = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return forms;
    }//end method

    @Override
    public ViewClientType getClientTypeFromCatg(String clientTypeCode) {
        Criterion criterion = Restrictions.eq("client_type_code", clientTypeCode);
        List<ViewClientType> readByCriteria = readByCriteria(criterion);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

    @Override
    public List<ViewClientType> ClientTypeListAsPanNO(String panletter, Util utl) {
        List<ViewClientType> clientTypeList;

        try {
            Criteria crit = getSession().createCriteria(ViewClientType.class);
            if (panletter.length() == 1) {
                crit.add(Restrictions.eq("pan_card_type", panletter));
            }

            crit.addOrder(Order.asc("client_type_name"));
            clientTypeList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            clientTypeList = null;
            getSession().getTransaction().rollback();
        }
        return clientTypeList = clientTypeList != null && clientTypeList.size() > 0 ? clientTypeList : null;
    }//end method
}//end class
