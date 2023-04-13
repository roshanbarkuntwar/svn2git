/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysTemplate;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akash.dev
 */
public class LhssysTemplateDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysTemplate, Serializable> implements LhssysTemplateDAO {

    @Override
    public Session getHibernateSession() {
        return getSession();
    }//end method

//    @Override
//    public Long getTempDataCount() {
//        Long rowcount;
//        try {
//            Criteria crit = getSession().createCriteria(LhssysTemplate.class);
//            crit.setProjection(Projections.rowCount());
//            rowcount = (Long) crit.uniqueResult();
//            getSession().getTransaction().commit();
//        } catch (Exception e) {
//            rowcount = 0L;
//            getSession().getTransaction().rollback();
//        }
//        return rowcount;
//    }//end method

//    @Override
//    public List<LhssysTemplate> getReadAllData() {
//        List<LhssysTemplate> tempDataList;
//        try {
//            Criteria crit = getSession().createCriteria(LhssysTemplate.class);
//            tempDataList = crit.list();
//            getSession().getTransaction().commit();
//        } catch (Exception e) {
//            tempDataList = null;
//        }
//        return (tempDataList != null && tempDataList.size() > 0 ? tempDataList : null);
//    }//end method

    @Override
    public boolean getTempDetailDelete(String l_query, Util utl) {
        boolean Result = true;
        Session ssn = getSession();
        ssn.beginTransaction();
        try {
            PreparedStatement pstmt = ssn.connection().prepareStatement(l_query);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                //System.out.println("record deleted");
            } else {
                Result = false;
                //System.out.println("some error occured");
            }
            pstmt.close();
            getSession().connection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ssn.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }//end method

    @Override
    public ArrayList<ArrayList<String>> getTempDataErrorDetail(String l_query, Util utl) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        Session ssn = getSession();
        //System.out.println("l_query..." + l_query);
        try {
            PreparedStatement pstmt = ssn.connection().prepareStatement(l_query);
            ResultSet rs = pstmt.executeQuery(l_query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            //System.out.println("column_count..." + column_count);
            while (rs.next()) {
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < column_count; i++) {
                    //System.out.println("i value..." + i);
                    String col_value = rs.getString(i + 1);
                    col_value = utl.isnull(col_value) ? "" : col_value;
                    arr.add(col_value);
                }
                dataList.add(arr);
            }
            pstmt.close();
            getSession().connection().close();
        } catch (SQLException ex) {
            dataList = null;
        }
        try {
            ssn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }//end method

//    @Override
//    public int getTotalRecordCount(String l_query, Util utl) {
//        int Result = 0;
//        Session ssn = getSession();
//        try {
//            PreparedStatement pstmt = ssn.connection().prepareStatement(l_query);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String l_totalCount = rs.getString(1);
//                l_totalCount = utl.isnull(l_totalCount) ? "0" : l_totalCount;
//                Result = Integer.parseInt(l_totalCount);
//            }
//            pstmt.close();
//            getSession().connection().close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            ssn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Result;
//    }//end method

    @Override
    public Long getTempDataCount(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String processSeqno, String template_code) {
        Long rowcount = 0L;
        try {
            
            Criteria crit = getSession().createCriteria(LhssysTemplate.class);
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", quarter_no));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.add(Restrictions.eq("process_seqno", processSeqno));
            crit.setProjection(Projections.rowCount());
            Number dataCount = (Number) crit.list().get(0);
            rowcount = Long.valueOf(dataCount.longValue());
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method 

    @Override
    public Long getTempDataCountAsErrorOrAllData(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String template_code, String errorType, String client_loginid_seq, Util utl) {
        Long rowcount;
        try { 
            Criteria crit = getSession().createCriteria(LhssysTemplate.class);
            crit.add(Restrictions.eq("entity_code", entity_code));
            crit.add(Restrictions.eq("client_code", client_code));
            crit.add(Restrictions.eq("acc_year", acc_year));
            crit.add(Restrictions.eq("quarter_no", "0" + quarter_no));
            crit.add(Restrictions.eq("tds_type_code", tds_type_code));
            crit.add(Restrictions.eq("process_seqno", client_loginid_seq));

            if (!utl.isnull(errorType)) {
                if (errorType.equalsIgnoreCase("AE")) {//ALL ERROR
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from view_lhssys_template_error b where b.process_Seqno = '" + client_loginid_seq + "' and b.lhssys_template_rowid_seq = this_.rowid_seq)"));
                } else {//ERROR BY FILTER 
                    crit.add(Restrictions.sqlRestriction("exists (select 1 from view_lhssys_template_error b where b.lhssys_template_rowid_seq = this_.rowid_seq and b.error_code_str ='" + errorType + "')"));
                }
            }

            crit.setProjection(Projections.rowCount());
            rowcount = (Long) crit.uniqueResult();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            rowcount = 0L;
            getSession().getTransaction().rollback();
        }
        return rowcount;
    }//end method

}//end class
