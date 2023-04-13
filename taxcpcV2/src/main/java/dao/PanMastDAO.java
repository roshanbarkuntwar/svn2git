/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.PanMast;
import java.io.Serializable;

/**
 *
 * @author ayushi.jain
 */
public interface PanMastDAO extends GenericDAO<PanMast, Serializable> {

    PanMast readPanCodeById(String PanCode);

    Long getPanCountAsPanCode(String pan_code);

    PanMast getRecordByPanCode(String pan_code);

}//end class
