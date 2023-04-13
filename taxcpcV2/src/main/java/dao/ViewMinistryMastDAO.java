package dao;

import dao.generic.GenericDAO;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author aniket.naik
 */
public interface ViewMinistryMastDAO extends GenericDAO<hibernateObjects.ViewMinistryMast, Serializable> {

    LinkedHashMap<String, String> getMinistryCodeAsList();

}
