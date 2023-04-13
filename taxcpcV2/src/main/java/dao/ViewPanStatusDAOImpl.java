/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.ViewPanStatus;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class ViewPanStatusDAOImpl extends GenericHibernateDAO<ViewPanStatus, Serializable> implements ViewPanStatusDAO {

    @Override
    public LinkedHashMap<String, String> getViewPanStatusASList() {
        LinkedHashMap<String, String> list = null;
        Util utl = new Util();
        try {
            Criterion crit = Restrictions.isNotNull("status_code");
            List<ViewPanStatus> readAll = readByCriteria(crit);
            if (readAll != null && readAll.size() > 0) {
                list = new LinkedHashMap<String, String>();
                for (ViewPanStatus viewPanStatus : readAll) {
                    String code = viewPanStatus.getStatus_code_15g();
                    code = utl.isnull(code) ? "" : code;
                    String status = viewPanStatus.getStatus_name();
                    status = utl.isnull(status) ? "" : status;
                    if (!utl.isnull(code) && !utl.isnull(status)) {
                        list.put(code, status);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

}
