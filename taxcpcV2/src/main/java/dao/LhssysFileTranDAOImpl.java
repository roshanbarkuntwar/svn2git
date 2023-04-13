/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.DbFunctionExecutorAsString;
import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysFileTranDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysFileTran, Serializable> implements LhssysFileTranDAO {

    @Override
    public LhssysFileTran readDataByFileSeqno(String file_seqno) {
        Criterion usrcrtry = Restrictions.eq("file_seqno", file_seqno).ignoreCase();
        List<LhssysFileTran> uselist = readByCriteria(usrcrtry);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }//end method

    @Override
    public LhssysFileTran readDataByFileSeqnoAndFlag(String file_seqno) {
        Criterion usrcrtry = Restrictions.eq("file_seqno", file_seqno).ignoreCase();
        Criterion usrcrtry2 = Restrictions.isNull("remove_record_flag");
        List<LhssysFileTran> uselist = readByCriteria(usrcrtry, usrcrtry2);
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }//end method

    @Override
    public String saveAndReturnFileSeqno(LhssysFileTran lhsfiletran) {
        String id;
        try {
            getSession().persist(lhsfiletran);
            getSession().flush();
            id = (String) getSession().getIdentifier(lhsfiletran);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            id = null;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return id;
    }//end method

    @Override
    public Session getHibernateSession() {
        return getSession();
    }

    @Override
    public Long getAvailDataCount(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String importFlag, String templeteCode) {
        Long rowcount;
        Long uploadMethod = 2L;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("upload_purpose", uploadPurpose));
            crit.add(Restrictions.eq("import_flag", importFlag));
            crit.add(Restrictions.eq("upload_method", uploadMethod));
            crit.add(Restrictions.eq("template_code", templeteCode));
            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;

    }//end method

    @Override
    public LhssysFileTran readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String importFlag, String TempleteCode) {
        Long uploadMethod = 2L;
        Criterion usrclient = Restrictions.eq("client_code", clientCode);
        Criterion usrtds = Restrictions.eq("tds_type_code", tdsTypeCode);
        Criterion usraccyr = Restrictions.eq("acc_year", acc_year);
        Criterion usrqtrno = Restrictions.eq("quarter_no", quarterNo);
        Criterion usruploadp = Restrictions.eq("upload_purpose", uploadPurpose);
        Criterion usrimpflag = Restrictions.eq("import_flag", importFlag);
        Criterion usruploadmthd = Restrictions.eq("upload_method", uploadMethod);
        Criterion usrtempcode = Restrictions.eq("template_code", TempleteCode);
        Criterion usrimpremoveflag = Restrictions.isNull("remove_record_flag");
        List<LhssysFileTran> uselist = readByCriteria(usrclient, usrtds, usraccyr, usrqtrno, usruploadp, usrimpflag, usrimpremoveflag, usruploadmthd, usrtempcode);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }//end method

    @Override
    public List<LhssysFileTran> readFileContentDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String file_error_type) {
        List<LhssysFileTran> LhssysFileTranList;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.sqlRestriction("EXISTS (SELECT 1 FROM (select Z1.CLIENT_CODE, Z1.CODE_LEVEL from client_mast Z1 START WITH Z1.CLIENT_CODE = '" + clientCode + "' CONNECT BY PRIOR Z1.CLIENT_CODE = Z1.PARENT_CODE) W1 WHERE W1.CLIENT_CODE = this_.CLIENT_CODE)"));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
//            crit.add(Restrictions.eq("month", month));
            crit.add(Restrictions.eq("upload_purpose", uploadPurpose));
            crit.add(Restrictions.eq("import_flag", file_error_type));
            crit.add(Restrictions.isNull("remove_record_flag"));
            LhssysFileTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            LhssysFileTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (LhssysFileTranList != null && LhssysFileTranList.size() > 0 ? LhssysFileTranList : null);
    }//end method

    @Override
    public int updateData(String fileSeqno, String filename, String filepath, String loadStatus, String fvuFileName) {
        int updateStatus = 0;
        try {
            LhssysFileTran filetran = (LhssysFileTran) getSession().get(LhssysFileTran.class, fileSeqno);
            filetran.setFvu_file_name_str(filename);
            filetran.setFvu_storage_file_path(filepath);
//            filetran.setFvu_file_name(fvuFileName);
            filetran.setFvu_file_status("C");

            getSession().update(filetran);
            getSession().getTransaction().commit();
            updateStatus = 1;
        } catch (HibernateException ex) {
            updateStatus = 0;
            ex.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return updateStatus;
    }

    @Override
    public int updateDataAfterDelete(String fileSeqno, String filename, String filepath) {
        int updateStatus = 0;
        try {
            LhssysFileTran filetran = (LhssysFileTran) getSession().get(LhssysFileTran.class, fileSeqno);
            filetran.setFvu_file_name_str(filename);
            filetran.setFvu_storage_file_path(filepath);
//            filetran.setFvu_file_name(fvuFileName);
            filetran.setFvu_file_status("");

            getSession().update(filetran);
            getSession().getTransaction().commit();
            updateStatus = 1;
        } catch (HibernateException ex) {
            updateStatus = 0;
            ex.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return updateStatus;
    }

    @Override
    public int updateData1(String fileSeqno, String fileSeqno1) {
        //System.out.println("file seqno:::" + fileSeqno);
        int updateStatus = 0;
        try {
            LhssysFileTran filetran = (LhssysFileTran) getSession().get(LhssysFileTran.class, fileSeqno1);
            filetran.setLoad_approx_time(fileSeqno);
            getSession().getTransaction().commit();
            updateStatus = 1;
        } catch (HibernateException ex) {
            updateStatus = 0;
            ex.printStackTrace();
        }
        return updateStatus;
    }

    @Override
    public int updateFileTranData(String fileSeqno, String l_getSession_code, String appendRecord) {
        int updateStatus = 0;
        Long session_code = Long.parseLong(fileSeqno);

        try {
            LhssysFileTran filetran = (LhssysFileTran) getSession().get(LhssysFileTran.class, fileSeqno);
            filetran.setRemove_record_flag(appendRecord);
            filetran.setRemove_login_session_seqno(session_code);
            getSession().getTransaction().commit();
            updateStatus = 1;
        } catch (HibernateException ex) {
            updateStatus = 0;
            ex.printStackTrace();
        }
        return updateStatus;
    }

    @Override
    public List<LhssysFileTran> readDataByParameterForGenerateFile(String clientCode, String tdsTypeCode, String acc_year, int quarterNo, String importFlag) {
        List<LhssysFileTran> LhssysFileTranList;

        Double quarterNo1 = Double.parseDouble(quarterNo + "");

        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo1));
            crit.add(Restrictions.eq("import_flag", importFlag));

            LhssysFileTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            LhssysFileTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return LhssysFileTranList;
    }

    @Override
    public List<LhssysFileTran> readDataByParameterForValidation(String clientCode, String tdsTypeCode, String acc_year, int quarterNo, String importFlag) {
        List<LhssysFileTran> LhssysFileTranList;

        Double quarterNo1 = Double.parseDouble(quarterNo + "");

        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo1));
            crit.add(Restrictions.eq("import_flag", importFlag));

            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.add(Restrictions.isNull("remove_login_session_seqno"));
            LhssysFileTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            LhssysFileTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return LhssysFileTranList;
    }

    @Override
    public int DeleteData(List<LhssysFileTran> readDataForDelRecord) {
        int delete_status = 0;
        if (!readDataForDelRecord.isEmpty()) {
            try {
                //System.out.println("read:::" + readDataForDelRecord);
                for (LhssysFileTran lhssysFileTran : readDataForDelRecord) {
                    getSession().delete(lhssysFileTran);
                }
                getSession().getTransaction().commit();
                delete_status = 1;
            } catch (HibernateException ex) {
                getSession().getTransaction().rollback();
                ex.printStackTrace();
            }
        } else {
            delete_status = 2;
        }
        return delete_status;
    }

    @Override
    public LhssysFileTran getFileSeqnoByParameter(String client_code, String entity_code, String acc_year, Double quarterNumber, String tds_type_code, String importFlag) {
        Criterion usrclient = Restrictions.eq("client_code", client_code);
        Criterion usraccyr = Restrictions.eq("acc_year", acc_year);
        Criterion usrqtrno = Restrictions.eq("quarter_no", quarterNumber);
        Criterion usrtds = Restrictions.eq("tds_type_code", tds_type_code);
        Criterion usrimpflag = Restrictions.eq("import_flag", importFlag);
//        Criterion usrmonth = Restrictions.eq("month", month);//AS PER FILE MAKE ONLY QUARTER WISE
        Criterion usrimpremoveflag = Restrictions.isNull("remove_record_flag");
        List<LhssysFileTran> uselist = readByCriteria(usrclient, usraccyr, usrqtrno, usrtds, usrimpflag, usrimpremoveflag);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }//end method

    @Override
    public List<LhssysFileTran> readDynamicFileContentDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String uploadPurpose, String file_error_type, String month, Util utl) {
        List<LhssysFileTran> LhssysFileTranList;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
//            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.sqlRestriction("EXISTS (SELECT 1 FROM (select Z1.CLIENT_CODE, Z1.CODE_LEVEL from client_mast Z1 START WITH Z1.CLIENT_CODE = '" + clientCode + "' CONNECT BY PRIOR Z1.CLIENT_CODE = Z1.PARENT_CODE) W1 WHERE W1.CLIENT_CODE = this_.CLIENT_CODE)"));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            if (!utl.isnull(month) && (!month.equalsIgnoreCase("All"))) {
                crit.add(Restrictions.eq("month", month));
            }
            crit.add(Restrictions.eq("upload_purpose", uploadPurpose));
            crit.add(Restrictions.eq("import_flag", file_error_type));
            crit.add(Restrictions.isNull("remove_record_flag"));
            LhssysFileTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            LhssysFileTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return (LhssysFileTranList != null && LhssysFileTranList.size() > 0 ? LhssysFileTranList : null);
    }//end method

    @Override
    public LhssysFileTran getDefaultFVULocation(String clientCode, String quarterNo, String tdsTypeCode, String importFlag) {
//        getSession().beginTransaction();
        List<LhssysFileTran> uselist = null;
        try {
            Double quarterNoInt = Double.parseDouble(quarterNo);
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("import_flag", importFlag));
            crit.add(Restrictions.eq("upload_purpose", "S"));
            crit.add(Restrictions.eq("quarter_no", quarterNoInt));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.addOrder(Order.desc("load_end_timestamp"));
            uselist = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            uselist = null;
            getSession().getTransaction().rollback();
        }
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    public List<LhssysFileTran> readFileForCorrection(String clientCode, String entity_code, String acc_year, String quarterNo) {
        List<LhssysFileTran> readByCriteria = new ArrayList<LhssysFileTran>();
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode.trim()));
            crit.add(Restrictions.eq("acc_year", acc_year.trim()));
            crit.add(Restrictions.eq("quarter_no", Double.parseDouble(quarterNo)));
            crit.add(Restrictions.eq("import_flag", "CR").ignoreCase());
            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.addOrder(Order.desc("upload_start_timestamp"));
            readByCriteria = crit.list();
        } catch (Exception e) {
            readByCriteria = null;
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null;
    }

    //For Salary excel file
    @Override
    public Long getAvailDataCount(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String importFlag, String templeteCode) {
        Long rowcount;
        Long uploadMethod = 2L;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", clientCode));
            crit.add(Restrictions.eq("tds_type_code", tdsTypeCode));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarterNo));
            crit.add(Restrictions.eq("import_flag", importFlag));
            crit.add(Restrictions.eq("upload_method", uploadMethod));
            crit.add(Restrictions.eq("template_code", templeteCode));
            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;

    }//end method

    //For Salary Excel file
    @Override
    public LhssysFileTran readDataByParameter(String clientCode, String tdsTypeCode, String acc_year, Double quarterNo, String importFlag, String TempleteCode) {
        Long uploadMethod = 2L;
        Criterion usrclient = Restrictions.eq("client_code", clientCode);
        Criterion usrtds = Restrictions.eq("tds_type_code", tdsTypeCode);
        Criterion usraccyr = Restrictions.eq("acc_year", acc_year);
        Criterion usrqtrno = Restrictions.eq("quarter_no", quarterNo);
        Criterion usrimpflag = Restrictions.eq("import_flag", importFlag);
        Criterion usruploadmthd = Restrictions.eq("upload_method", uploadMethod);
        Criterion usrtempcode = Restrictions.eq("template_code", TempleteCode);
        Criterion usrimpremoveflag = Restrictions.isNull("remove_record_flag");
        List<LhssysFileTran> uselist = readByCriteria(usrclient, usrtds, usraccyr, usrqtrno, usrimpflag, usrimpremoveflag, usruploadmthd);
        return (uselist != null && uselist.size() > 0) ? uselist.get(0) : null;
    }//end method

    @Override
    public List<LhssysFileTran> readAllUplaodedFilesByQuarter(LhssysFileTran fileTran) {
        List<LhssysFileTran> readByCriteria = new ArrayList<LhssysFileTran>();
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", fileTran.getClient_code()));
            crit.add(Restrictions.eq("acc_year", fileTran.getAcc_year()));
//            crit.add(Restrictions.eq("tds_type_code", fileTran.getTds_type_code()));
            crit.add(Restrictions.eq("quarter_no", fileTran.getQuarter_no()));
            crit.add(Restrictions.eq("upload_purpose", fileTran.getUpload_purpose()));
            crit.add(Restrictions.eq("import_flag", fileTran.getImport_flag()));
            crit.add(Restrictions.isNull("remove_record_flag"));

//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.isNull("remove_record_flag"));
//            disjunction.add(Restrictions.eq("remove_record_flag", fileTran.getRemove_record_flag()));
//            crit.add(disjunction);
            crit.addOrder(Order.desc("upload_start_timestamp"));

            readByCriteria = crit.list();
        } catch (Exception e) {
            readByCriteria = null;
        }

        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria : null;
    }

    @Override
    public List<LhssysFileTran> readDataByParameterForFVU(LhssysFileTran fileTran) {
        List<LhssysFileTran> LhssysFileTranList;

//        Double quarterNo1 = Double.parseDouble(quarterNo + "");
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", fileTran.getClient_code()));
            crit.add(Restrictions.eq("tds_type_code", fileTran.getTds_type_code()));
            crit.add(Restrictions.eq("acc_year", fileTran.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", fileTran.getQuarter_no()));
            crit.add(Restrictions.eq("import_flag", fileTran.getImport_flag()));
            crit.add(Restrictions.eq("upload_purpose", fileTran.getUpload_purpose()));
            crit.add(Restrictions.eq("upload_method", fileTran.getUpload_method()));
            crit.add(Restrictions.eq("file_type", fileTran.getFile_type()));

            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.add(Restrictions.isNull("remove_login_session_seqno"));
            crit.addOrder(Order.desc("file_seqno"));
            LhssysFileTranList = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            LhssysFileTranList = null;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return LhssysFileTranList;
    }

    @Override
    public LhssysFileTran getDefaultFVULocation(LhssysFileTran fileTran) {
        List<LhssysFileTran> uselist = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", fileTran.getClient_code()));
            crit.add(Restrictions.eq("tds_type_code", fileTran.getTds_type_code()));
            crit.add(Restrictions.eq("acc_year", fileTran.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", fileTran.getQuarter_no()));
            crit.add(Restrictions.eq("import_flag", fileTran.getImport_flag()));
            crit.add(Restrictions.eq("upload_purpose", fileTran.getUpload_purpose()));
            crit.add(Restrictions.eq("upload_method", fileTran.getUpload_method()));
            crit.add(Restrictions.eq("file_type", fileTran.getFile_type()));
            crit.addOrder(Order.desc("file_seqno"));
            uselist = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            uselist = null;
            getSession().getTransaction().rollback();
        }
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    @Override
    public LhssysFileTran readDataByFileName(String fileName) {
        List<LhssysFileTran> readByCriteria = null;

        try {
            Criterion criterion = Restrictions.eq("file_name", fileName);
            readByCriteria = readByCriteria(criterion);
        } catch (Exception e) {
            readByCriteria = null;
            e.printStackTrace();
        }
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;

    }

    @Override
    public LhssysFileTran getFVUFileDetails(LhssysFileTran fileTran) {
        List<LhssysFileTran> uselist = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", fileTran.getClient_code()));
            crit.add(Restrictions.eq("tds_type_code", fileTran.getTds_type_code()));
            crit.add(Restrictions.eq("acc_year", fileTran.getAcc_year()));
            crit.add(Restrictions.eq("quarter_no", fileTran.getQuarter_no()));
            crit.add(Restrictions.eq("import_flag", fileTran.getImport_flag()));
            crit.add(Restrictions.eq("upload_purpose", fileTran.getUpload_purpose()));
            crit.add(Restrictions.eq("upload_method", fileTran.getUpload_method()));
            crit.add(Restrictions.eq("file_type", fileTran.getFile_type()));
            crit.add(Restrictions.isNull("remove_record_flag"));
            crit.add(Restrictions.eq("load_status", fileTran.getFile_type()));
            crit.addOrder(Order.desc("file_seqno"));
            uselist = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            uselist = null;
            getSession().getTransaction().rollback();
        }
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    @Override
    public LhssysFileTran getFVUFileDetailsByFileSeqNo(String seqNo) {
        List<LhssysFileTran> uselist = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("file_seqno", seqNo));

            uselist = crit.list();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            uselist = null;
            getSession().getTransaction().rollback();
        }
        return uselist != null && uselist.size() > 0 ? uselist.get(0) : null;
    }

    @Override
    public Long getFvuFileCount(LhssysFileTran fileTran) {
        Long fvuCount = 0l;
        try {
            Criteria crit = getSession().createCriteria(LhssysFileTran.class);
            crit.add(Restrictions.eq("client_code", fileTran.getClient_code()));
            crit.add(Restrictions.eq("import_flag", fileTran.getImport_flag()));
            crit.add(Restrictions.isNotNull("fvu_file_name"));
            crit.setProjection(Projections.rowCount());
            fvuCount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            fvuCount = null;
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
        return fvuCount;
    }

    public boolean deleteFvuRecord(String seqno) {
        boolean status = false;

        try {
            String deleteQuery
                    = "\n"
                    + "delete from lhssys_file_tran a\n"
                    + " where a.file_seqno = '" + seqno + "'\n";

            status = new DbFunctionExecutorAsString().execute_oracle_update_function_as_string(deleteQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}//end class
