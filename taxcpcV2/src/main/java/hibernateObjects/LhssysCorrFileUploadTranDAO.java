/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author akash.dev
 */
public interface LhssysCorrFileUploadTranDAO extends GenericDAO<hibernateObjects.LhssysCorrFileUploadTran, Serializable> {

    public String saveAndReturnFileSeqno(LhssysCorrFileUploadTran lhssysCorrFileUploadTran);

    public List<LhssysCorrFileUploadTran> readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo);

    public LhssysCorrFileUploadTran updateData(String fileSeqno, String flag);

    public List<LhssysCorrFileUploadTran> getDataForDelete(String client_code, String acc_year, Double quarter_no, String tds_type_code, Long client_login_session_seqno);

    public List<LhssysCorrFileUploadTran> readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, LhssysCorrFileUploadTran filterObj, Util utl);
}
