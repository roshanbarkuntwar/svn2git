/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author akash.dev
 */
public interface LhssysFileTranDAO extends GenericDAO<hibernateObjects.LhssysFileTran, Serializable> {

    LhssysFileTran readDataByFileSeqno(String file_seqno);

    LhssysFileTran readDataByFileSeqnoAndFlag(String file_seqno);

    String saveAndReturnFileSeqno(LhssysFileTran lhsfiletran);

    Session getHibernateSession();

    Long getAvailDataCount(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String importFlag, String templeteCode);

    LhssysFileTran readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String importFlag, String TempleteCode);

    List<LhssysFileTran> readFileContentDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String file_error_type);

    int updateData(String fileSeqno, String filename, String filepath, String loadStatus, String fvuFileName);

    int updateData1(String fileSeqno, String fileSeqno1);

    int updateFileTranData(String fileSeqno, String l_getSession_code, String appendRecord);

    int DeleteData(List<LhssysFileTran> readDataForDelRecord);

    List<LhssysFileTran> readDataByParameterForGenerateFile(String clientCode, String tdsTypeCode, String acc_year, int quarterNo, String importFlag);

    List<LhssysFileTran> readDataByParameterForValidation(String clientCode, String tdsTypeCode, String acc_year, int quarterNo, String importFlag);

    List<LhssysFileTran> readDataByParameterForFVU(LhssysFileTran fileTran);

    LhssysFileTran getFileSeqnoByParameter(String client_code, String entity_code, String acc_year, Double quarterNumber, String tds_type_code, String ch);

    List<LhssysFileTran> readDynamicFileContentDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String file_error_type, String month, Util utl);

    LhssysFileTran getDefaultFVULocation(String clientCode, String quarterNo, String tdsTypeCode, String importFlag);

    LhssysFileTran getDefaultFVULocation(LhssysFileTran fileTran);

    List<LhssysFileTran> readFileForCorrection(String clientCode, String entity_code, String acc_year, String quarterNo);

    Long getAvailDataCount(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String importFlag, String templeteCode);

    LhssysFileTran readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String importFlag, String TempleteCode);

    List<LhssysFileTran> readAllUplaodedFilesByQuarter(LhssysFileTran fileTran);

    LhssysFileTran readDataByFileName(String fileName);

    LhssysFileTran getFVUFileDetails(LhssysFileTran fileTran);

    Long getFvuFileCount(LhssysFileTran fileTran);

    LhssysFileTran getFVUFileDetailsByFileSeqNo(String seqNo);

    int updateDataAfterDelete(String fileSeqno, String filename, String filepath);

    boolean deleteFvuRecord(String fileSeqNo);

}//end class
