/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.LhssysParameters;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface LhssysParametersDAO extends GenericDAO<hibernateObjects.LhssysParameters, Serializable> {

    public LhssysParameters readDataAsParameterAndEntity(String param_name, String entity_code);

    public List<LhssysParameters> readParametersForSession(String entity_code);
    
    public LhssysParameters readCsiWaitTime(String entityCode);
    
    public LhssysParameters readFvuFileVersion(String entityCode);
    
    public LhssysParameters readOnlineFlag(String entityCode);
    
    public List<LhssysParameters> readServerParameters(String serverType);
    
    public LhssysParameters readParametersBy(String parameterName);
    
    public List<LhssysParameters> readParametersByParameterType(String parameterType);
    
   
    


}//End Interface
