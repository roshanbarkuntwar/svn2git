/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.SalaryAmtTran;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ayushi.jain
 */
public interface SalaryAmtTranDAO extends GenericDAO<SalaryAmtTran, Serializable> {

    Session getHibernateSession();

    ArrayList<SalaryAmtTran> readSalaryAmtTranByDeducteeCode(Date fromDate, Date toDate, String flag, String deductee_code, String month, String entity_code, String client_code, Util utl);

    ArrayList<SalaryAmtTran> readSalaryAmtTranByRowIdSeq(Date fromDate, Date toDate, String flag, String deductee_code, String month, ViewClientMast clientMast, Long rowidSeq, Util utl);

    ArrayList<SalaryAmtTran> readSalaryAmtTranByDeducteeCode(String deductee_code, String itax_catg, String acc_year, ViewClientMast clientMast);

    Long getSalaryCount(Date fromDate, Date toDate, String flag, String deductee_code, String month, String entity_code, String client_code, Util utl);

    ArrayList<SalaryAmtTran> readSalaryAmtTranForRefreshingAmount(String deductee_code, String acc_year, String clientCode);

    ArrayList<String> readDistinctDeducteeCodesForRefreshingAmount(String itax_catg, String acc_year, String clientCode);

    Long getPositiveShortDeductionCount(String clientCode, String entityCode, String accYear);

    List<SalaryAmtTran> getPositiveShortDeduction(String clientCode, String entityCode, String accYear);

    SalaryAmtTran getPreviousEmployerRecord(SalaryAmtTran tran);
}
