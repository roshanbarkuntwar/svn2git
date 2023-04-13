/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.DeducteeBflagAmtTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author aniket.naik
 */
public interface DeducteeBflagAmtTranDAO extends GenericDAO<DeducteeBflagAmtTran, Serializable> {

    List<DeducteeBflagAmtTran> readListForDeductee15gh(String entityCode, String clientCode, String accYear, String quarterNo, String tdsTypeCode, Long rowid_seq, String panno);

    public Session getHibernateSession();

    public List<DeducteeBflagAmtTran> getAmountTranDataList(String entity_code, String client_code, String accYear, String quarterNo, String tdsTypeCode, Long rowid_seq, String panno);

    public DeducteeBflagAmtTran readAmtTranById(Long deducteeBflagSeqNo);

}
