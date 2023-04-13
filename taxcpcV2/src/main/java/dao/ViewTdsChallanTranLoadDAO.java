/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.ViewTdsChallanTranLoad;
import java.io.Serializable;
import java.util.List;
import regular.challan.ChallanFilterEntity;
import regular.challan.ChallanGrossTotalSumList;

/**
 *
 * @author aniket.naik
 */
public interface ViewTdsChallanTranLoadDAO extends GenericDAO<hibernateObjects.ViewTdsChallanTranLoad, Serializable> {

    public List<ViewTdsChallanTranLoad> getTdsChallanTranLoadData(String loginEntityCode, String clientcode, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, String search, int minVal, int maxVal, Util utl, boolean procedureFlag, boolean isFromAllocatedChallan);

    public Long getTdsChallanTranLoadCount(String loginEntityCode, String workinguser, Assessment assessment, ChallanFilterEntity tdsChallanTranFilterSrch, boolean isFromAllocatedChallan, Util utl);

    public ChallanGrossTotalSumList readAllChallanDataSum(String client_code, Assessment assessment, ChallanFilterEntity tranChallanFltrSrch, String search, Util utl);

    Long getTdsChallanTranCount(String workinguser, String acc_year, String quarterNo, String tds_type_code, ChallanFilterEntity tdsChallanTranFilterSrch, String search, Util utl);

    Long getChallanCount(ViewTdsChallanTranLoad challanTran, Util utl);

    List<ViewTdsChallanTranLoad> readChallansByCSIFlag(ViewTdsChallanTranLoad challanTran, Util utl);
}
