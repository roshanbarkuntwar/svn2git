/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.SalaryTran;
import java.io.Serializable;

/**
 *
 * @author ayushi.jain
 */
public interface SalaryTranDAO extends GenericDAO<SalaryTran, Serializable> {

    SalaryTran findByRowidAndDeducteeCode(String rowId);

    SalaryTran findByDeducteePannoAndClientCodeAndEntityCode(String panno,String clientCode,String entityCode);

}
