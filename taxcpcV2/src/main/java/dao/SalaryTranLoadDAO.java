/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.SalaryTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.ArrayList;
import regular.salaryNew.SalaryTranLoadBean;
import regular.salaryNew.SalaryTranLoadFilter;

/**
 *
 * @author gaurav.khanzode
 */
public interface SalaryTranLoadDAO extends GenericDAO<SalaryTranLoad, Serializable> {

    public ArrayList<SalaryTranLoadBean> getSalaryTranLoadList(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, int minVal, int maxVal, Util utl);

    public SalaryTranLoad getByRowId(Long rowid);

    public Long getSalaryTranLoadDataCount(SalaryTranLoadFilter filters, ViewClientMast clientMast, Assessment asmt, Util utl);

}
