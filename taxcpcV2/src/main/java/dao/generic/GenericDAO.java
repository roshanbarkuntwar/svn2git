/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
/**
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {

   //data processing is slow by using readById(ID id, boolean lock);(for update issue)
   //T readById(ID id, boolean lock);
    
    List<T> readAll();

    List<T> readByExample(T exampleInstance, String[] excludeProperty);

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    Object saveAndReturnIdentifier(T entity);

    boolean saveOrUpdate(T entity);
}//end interface
