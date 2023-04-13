/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.ViewErrorProcessReports;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akash.meshram
 */
public interface ViewDisplayReportListDAO extends GenericDAO<hibernateObjects.ViewErrorProcessReports, Serializable> {
    
    List<ViewErrorProcessReports> getViewDisplayReportList(Long process_seqno);
    
    Long getViewDisplayReportListCount(Long process_seqno);
    
}
