/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysAlertDirectEmail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface LhssysAlertDirectEmailDAO extends GenericDAO<LhssysAlertDirectEmail, Serializable> {

    public ArrayList<String> getDistinctReportGroup(Util utl);

    public List<LhssysAlertDirectEmail> getReportList(String report_group, Util utl,String entity_code);

    public Long getReportGroupCount(String reportGroup, Util utl,String entity_code);

    public List<LhssysAlertDirectEmail> getReportListAsSeqId(Long seq_id, Util utl);

    public Long getReportListAsSeqIdCount(Long seq_id, Util utl);

    public LhssysAlertDirectEmail getReportAsSeqId(Long seq_id, Util utl);
    
     LhssysAlertDirectEmail getDeducteeSalaryParams(Long seq_id);
}//end class
