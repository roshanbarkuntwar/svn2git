/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewSalaryDataValidation;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author ayushi.jain
 */
public interface ViewSalaryDataValidationDAO extends GenericDAO<ViewSalaryDataValidation, Serializable> {

    Long getSalaryDataValidationCount(ViewSalaryDataValidation salaryDataFilterSearch, String search, ViewClientMast clientMast, Assessment asmt, Util utl);

    List<ViewSalaryDataValidation> getSalaryDataByType(ViewSalaryDataValidation salaryDataFilterSearch, ViewClientMast clientMast, Assessment asmt, String search, int minVal, int maxVal, Util utl);

    Long getSalaryDataValidationDuplicateCount(ViewSalaryDataValidation salaryDataFilterSearch, String search, ViewClientMast clientMast, Assessment asmt, Util utl);

    List<ViewSalaryDataValidation> getSalaryDuplicateDataByType(ViewClientMast clientMast, Assessment asmt, Util utl);

    List<ViewSalaryDataValidation> readDeducteeNameAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String panno, String deducteeCatg);

    List<ViewSalaryDataValidation> readDeducteePanAutocompleteForSalary(String term, String entity_code, String client_code, Util utl, String deducteeName, String deducteeCatg);

    ViewSalaryDataValidation readDeducteeByDeducteeNameOrPanno(Util utl, String entity_code, String client_code, String deducteeName, String panno);

    


   

    
}
