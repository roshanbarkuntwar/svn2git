/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsTranLoad;
import java.io.Serializable;
import java.util.List;
import regular.challan.challanAllocation.ChallanAllocationFilterEntity;
import regular.challan.challanAllocation.MapTdsChallanGrossTotalList;

/**
 *
 * @author ayushi.jain
 */
public interface ViewTdsTranLoadDAO extends GenericDAO<hibernateObjects.ViewTdsTranLoad, Serializable> {

    public long getMapTdsChallanLoadCount(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, String paraRowIdSeq, ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, String search, Util utl);

    public List<ViewTdsTranLoad> readTDSDataByCode(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanAllocationFilterEntity mapTdsChallanFilterSrch, String search, int i, int recordsPerPage, Util utl);

    public Long readTDSDataByCodeCount(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanAllocationFilterEntity mapTdsChallanFilterSrch, String search, int i, int recordsPerPage, Util utl);

    public MapTdsChallanGrossTotalList readTDSDataSumDetail(String entity_code, String l_workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanAllocationFilterEntity mapTdsChallanFilterSrchData, Util utl);

}
