package dao;

import dao.generic.GenericDAO;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aniket.naik
 */
public interface ViewDeducteeTemplateDAO extends GenericDAO<ViewClientTemplate, Serializable> {

    public List<ViewClientTemplate> getAllTemplatesAsKeyCode(ViewClientMast viewClientMast, String key_code, String moduleType);

    public ViewClientTemplate getDataAsTempleteCode(String templete_code);
    
     public List<ViewClientTemplate> getAllTemplatesAsKeyCodeForDeletePros(ViewClientMast viewClientMast, String key_code, String moduleType);
    

}//End Interface
