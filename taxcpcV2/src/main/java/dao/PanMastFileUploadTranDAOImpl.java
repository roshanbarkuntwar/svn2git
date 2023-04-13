/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.PanMastFileUploadTran;
import java.io.Serializable;

/**
 *
 * @author kapil.gupta
 */
public class PanMastFileUploadTranDAOImpl extends GenericHibernateDAO<PanMastFileUploadTran, Serializable> implements PanMastFileUploadTranDAO{
    Util utl;

    public PanMastFileUploadTranDAOImpl() {
        this.utl = new Util();
    }
    
}
