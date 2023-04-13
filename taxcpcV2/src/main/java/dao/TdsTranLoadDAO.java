/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.TdsTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import regular.dashboard.miscTran.CinMiscFilterEntity;
import regular.dashboard.miscTran.TDSRateBean;

/**
 *
 * @author ayushi.jain
 */
public interface TdsTranLoadDAO extends GenericDAO<TdsTranLoad, Serializable> {

    List<TdsTranLoad> readDeducteesForChallan(String challanRowSeq);

    public List<TdsTranLoad> readTdsChallanAmount(String mChallanRowSeq, String l_entity_code, String l_client_code, String acc_year, String quarterNo, String tds_type_code);

    public TdsTranLoad getObjForLowDeduCertifiAlloca(TdsTranLoad entity);

    Long getDeducteesCountForChallan(String challanRowSeq);

    TdsTranLoad readTDSBySequenceID(Long rowid_seq);

    public List<TdsTranLoad> readProcessDetails(String entityCode, String clientCode, Assessment asmt);

    //miscellaneous
    public List<String> getMonthFormYear(String accYear, String clientCode);

    public LinkedHashMap<String, String> getTdsSectionValues(String tdsTypeCode);

    public LinkedHashMap<String, String> getTdsDeductReason();

    public LinkedHashMap<String, String> getRemittanceNature();

    public LinkedHashMap<String, String> getTdsRateType();

    public LinkedHashMap<String, String> getCountryCode();

    public Long getMiscCinDataCount(CinMiscFilterEntity miscData, ViewClientMast client, Assessment asmt, Util utl);

    public TdsTranLoad getByRowId(String rowid_seq);

    public LinkedHashMap<String, String> getTdsSectionValuesFromTdsCode(String tds_code);

    public Long getCheckerMakerDataCount(TdsTranLoad miscData, String authFlag, ViewClientMast client, Assessment asmt, Util utl);

    public String updateSingleTranLoad(String rowidSeq, String flag, String client_code);

    public String updateBulkTranLoad(String flag, String authStatus, ViewClientMast client_code, Assessment asmt, String panno, String name, String usercode);

    public LinkedHashMap<String, String> getBGLSectionValues(String bglCode, String tdsTypeCode);

    public String getRateAndTotalAmount(Assessment asmt, ViewClientMast client, TDSRateBean bean);

    public String getTransactionID();

    public String getPanStatusName(String pan4thChar);

    public LinkedHashMap<String, String> getBGLCodeNameList(String tdsType);

//    public List<TdsTranLoad> getCertificateDetails(ViewClientMast clientMast, Assessment asmt, String deductee_panno, String tds_code,
//            String certificate_no);
    public List<TdsTranLoad> getCertificateDetails(TdsTranLoad tds);


}
