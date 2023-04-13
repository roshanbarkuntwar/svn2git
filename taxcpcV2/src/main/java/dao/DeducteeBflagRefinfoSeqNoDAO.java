/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.DeducteeBflagRefinfoSeqNo;
import java.io.Serializable;

/**
 *
 * @author aniket.naik
 */
public interface DeducteeBflagRefinfoSeqNoDAO extends GenericDAO<DeducteeBflagRefinfoSeqNo, Serializable> {

    DeducteeBflagRefinfoSeqNo readDeducteeBflagRefinfoByBFlag(String entityCode, String clientCode, String accYear, String Bflag);

}
