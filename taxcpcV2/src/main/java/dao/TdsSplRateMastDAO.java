/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
import hibernateObjects.TdsSplRateMastId;
import hibernateObjects.ViewClientMast;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface TdsSplRateMastDAO extends GenericDAO<TdsSplRateMast, TdsSplRateMastId> {

    Long getTdsSplRateCount(TdsSplRateMast tdsSplRateFilterSearch, String search, Util utl);

    List<TdsSplRateMast> getTdsSplRateByType(TdsSplRateMast tdsSplRateFilterSearch, String search, int minVal, int maxVal, Util utl);

    TdsSplRateMast readTdsSplRateById(TdsSplRateMastId tdsSplRateMastId);

    TdsSplRateMast getDeducteeCertificateNo(TdsSplRateMast tdsSplRateMastSearch);

    TdsSplRateMast getCertificateDetails(ViewClientMast client, Assessment asmt, TdsSplRateMast tdsSplRateMastSearch);

    Boolean checkUniquePrimaryKey(TdsSplRateMast tdsSplRateMastSearch);

    public List<TdsSplRateMast> readTdsSplRateForLowDeduCertifiAlloca(TdsSplRateMast tdsSplRateMast);

    public List<TdsSplRateMast> readTdsSplRateForDeducteeList(ViewClientMast client, Assessment asmt, String tdsCode,
            String deducteePanno);

}
