/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.*;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author gaurav.khanzode
 */
public interface ViewLhssysLatestAssYearDAO extends GenericDAO<ViewLhssysLatestAssYear, Serializable> {

    LinkedHashMap<String, String> getLatestAssessList();
}
