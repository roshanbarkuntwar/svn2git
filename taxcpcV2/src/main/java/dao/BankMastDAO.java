/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.BankMast;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public interface BankMastDAO extends GenericDAO<hibernateObjects.BankMast, Serializable> {

    public List<BankMast> readChallanAutocomplete(String term);

    List<BankMast> getBankDetails(String bsrCode);

}
