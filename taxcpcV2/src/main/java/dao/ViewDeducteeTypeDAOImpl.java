/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewDeducteeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class ViewDeducteeTypeDAOImpl extends GenericHibernateDAO<hibernateObjects.ViewDeducteeType, Serializable> implements ViewDeducteeTypeDAO {

    @Override
    public ArrayList<ArrayList<String>> getDeducteeCatgAsLinkedPanno(String panno_letter) {
        ArrayList<ArrayList<String>> deducteeCatgMap = new ArrayList<ArrayList<String>>();
        List<ViewDeducteeType> deducteeCatgList;

        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            crit.add(Restrictions.eq("pan_card_type", panno_letter));
            crit.addOrder(Order.asc("deductee_type_name"));
            deducteeCatgList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            deducteeCatgList = null;
            getSession().getTransaction().rollback();
        }

        deducteeCatgList = deducteeCatgList != null && deducteeCatgList.size() > 0 ? deducteeCatgList : null;
        if (deducteeCatgList != null) {
            for (ViewDeducteeType deducteeType : deducteeCatgList) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(deducteeType.getDeductee_type_code());
                arr.add(deducteeType.getDeductee_type_name());
                arr.add(deducteeType.getPan_card_type());
                deducteeCatgMap.add(arr);
            }
        }
        return deducteeCatgMap;
    }

    @Override
    public HashMap<String, String> getDeducteeTypeAsHashMap() {
        List<ViewDeducteeType> data = readAll();
        HashMap<String, String> forms = new HashMap<String, String>();
        if (data != null) {
            for (ViewDeducteeType type : data) {
                forms.put(type.getDeductee_type_code() + "~" + type.getPan_card_type(), type.getDeductee_type_name());
            }
        }
        return forms;
    }

    @Override
    public ArrayList<ArrayList<String>> getDeducteeTypeAsArrayList() {
        List<ViewDeducteeType> data = readAll();
        ArrayList<ArrayList<String>> forms = new ArrayList<ArrayList<String>>();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                ViewDeducteeType type = data.get(i);
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(type.getDeductee_type_code());
                arr.add(type.getDeductee_type_name());
                arr.add(type.getPan_card_type());
                forms.add(arr);
            }
        }
        return forms;
    }

    @Override
    public List<ViewDeducteeType> getDeducteeTypeList(String panno_letter, Util utl) {
        List<ViewDeducteeType> data;
        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            if (!utl.isnull(panno_letter)) {
                crit.add(Restrictions.eq("pan_card_type", panno_letter));
            }
            crit.addOrder(Order.asc("deductee_type_name"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (data != null && data.size() > 0 ? data : null);
    }//end method

    @Override
    public List<ViewDeducteeType> getAllDeducteeTypeList() {
        List<ViewDeducteeType> data;
        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            crit.addOrder(Order.asc("deductee_type_name"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (data != null && data.size() > 0 ? data : null);
    }//end method

    @Override
    public LinkedHashMap<String, String> getDeducteeTypeAsLinkedHashMap() {
        List<ViewDeducteeType> data;
        LinkedHashMap<String, String> forms = new LinkedHashMap<String, String>();
        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            crit.addOrder(Order.asc("deductee_type_name"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        if (data != null) {
            for (ViewDeducteeType type : data) {
                forms.put(type.getDeductee_type_code(), type.getDeductee_type_name());
            }
        }
        return forms;
    }//end method

    @Override
    public List<ViewDeducteeType> DeducteeTypeListAsPanNO(String panno_letter, Util utl) {
        List<ViewDeducteeType> deducteeCatgList;

        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            crit.add(Restrictions.eq("pan_card_type", panno_letter));
            crit.addOrder(Order.asc("deductee_type_name"));
            deducteeCatgList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            deducteeCatgList = null;
            getSession().getTransaction().rollback();
        }
        return deducteeCatgList = deducteeCatgList != null && deducteeCatgList.size() > 0 ? deducteeCatgList : null;
    }//end method

    @Override
    public LinkedHashMap<String, String> getAllDeducteeTypeListForTdsRateMaster() {
        LinkedHashMap<String, String> deducteeTypeList = new LinkedHashMap<String, String>();
        List<ViewDeducteeType> data;
        try {
            Criteria crit = getSession().createCriteria(ViewDeducteeType.class);
            crit.addOrder(Order.asc("deductee_type_name"));
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }

        data = data != null && data.size() > 0 ? data : null;
        if (data != null) {
            for (ViewDeducteeType deducteeTypeMast : data) {
                deducteeTypeList.put(deducteeTypeMast.getDeductee_type_code(), deducteeTypeMast.getDeductee_type_name());
            }
        }

        return deducteeTypeList;
    }//end method

}//end class

