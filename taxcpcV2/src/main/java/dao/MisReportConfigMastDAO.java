/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.MisReportConfigMast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaurav.khanzode
 */
public interface MisReportConfigMastDAO extends GenericDAO<MisReportConfigMast, Serializable> {

    public abstract ArrayList<String> getDistinctReportGroup(Util utl);

    public abstract List<MisReportConfigMast> getReportList(String report_group, Util utl, String entity_code,String tds_type_code);

    public abstract Long getReportGroupCount(String reportGroup, Util utl, String entity_code);

    public abstract List<MisReportConfigMast> getReportListAsSeqId(Long seq_id, Util utl);

    public abstract Long getReportListAsSeqIdCount(Long seq_id, Util utl);

    public abstract MisReportConfigMast getReportAsSeqId(Long seq_id, Util utl);

    public abstract MisReportConfigMast getDeducteeSalaryParams(Long seq_id);

    public abstract String getValueByParamNameAndSeqId(Long seq_id, String param_name);
    
    public abstract List<MisReportConfigMast> getReportListByAppType(String rep_app_type);

}//end interface
