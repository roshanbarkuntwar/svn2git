/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author gaurav.khanzode
 */
public class ViewLhssysLatestAssYearDAOImpl extends GenericHibernateDAO<ViewLhssysLatestAssYear, Serializable> implements ViewLhssysLatestAssYearDAO {

    @Override
    public LinkedHashMap<String, String> getLatestAssessList() {
        List<ViewLhssysLatestAssYear> assessList = null;
        LinkedHashMap<String, String> assessMap = new LinkedHashMap<>();
        try {
            assessList = readAll();
        } catch (Exception e) {
        }
        assessList = assessList != null && assessList.size() > 0 ? assessList : null;
        if (assessList != null) {
            for (ViewLhssysLatestAssYear deducteecatglist : assessList) {
                assessMap.put(deducteecatglist.getLatest_assessment_year(), deducteecatglist.getLatest_assessment_year());
            }
        }
        return assessMap;
    }

}
