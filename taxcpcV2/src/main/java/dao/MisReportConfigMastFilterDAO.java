/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import globalUtilities.Util;
import hibernateObjects.MisReportConfigMastFilter;
import java.util.List;

/**
 *
 * @author gaurav.khanzode
 */
public interface MisReportConfigMastFilterDAO {

    public abstract List<MisReportConfigMastFilter> getListDataAsSeqId(Long seq_id, Util utl);

    public abstract MisReportConfigMastFilter getListDataAsSeqIdAndSlno(Long seq_id, Util utl, String slno);
    
    public abstract MisReportConfigMastFilter getReportAsSeqIdAndFilterColumn(Long seq_id, String filter_column, Util utl);
}
