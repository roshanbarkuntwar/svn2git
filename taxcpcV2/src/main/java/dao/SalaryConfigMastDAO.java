/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.SalaryConfigMast;
import hibernateObjects.SalaryConfigMastId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface SalaryConfigMastDAO extends GenericDAO<SalaryConfigMast, SalaryConfigMastId> {

    List<SalaryConfigMast> readSalaryConfigMastForDynamicCols(SalaryConfigMast salaryConfigMast, Date yearBeginDate, Date yearEndDate);
    
    List<SalaryConfigMast> getConfigColsForRefreshingAmount(String itaxCatg);
}
