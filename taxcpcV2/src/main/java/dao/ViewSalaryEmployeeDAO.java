/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewSalaryEmployee;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface ViewSalaryEmployeeDAO extends GenericDAO<hibernateObjects.ViewSalaryEmployee, Serializable> {

    Long getSalaryCount(ViewSalaryEmployee SalaryFilterSearch, String search, ViewClientMast clientMast, Util utl);

    List<ViewSalaryEmployee> getSalaryByType(ViewSalaryEmployee salaryFilterSearch, ViewClientMast clientMast, String search, int minVal, int maxVal, Util utl);

    public List<ViewSalaryEmployee> readDeducteePanAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String deducteeName, String deducteeCatg);

    public List<ViewSalaryEmployee> readDeducteeNameAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String panno, String deducteeCatg);

    public ViewSalaryEmployee readDeducteeByDeducteeName(Util utl, String entity_code, String client_code, String deducteeName);

    public ViewSalaryEmployee readDeducteeByPanno(Util utl, String entity_code, String client_code, String panno);

    public ViewSalaryEmployee readDeducteeByDeducteeNameOrPanno(Util utl, String entity_code, String client_code, String deducteeName, String panno);
}
