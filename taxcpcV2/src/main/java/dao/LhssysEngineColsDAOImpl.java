/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import globalUtilities.Util;
import hibernateObjects.LhssysEngineCols;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ayushi.jain
 */
public class LhssysEngineColsDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysEngineCols, Serializable> implements LhssysEngineColsDAO {

    @Override
    public Session getHibernateSession() {
        return getSession();
    }//end method

    @Override
    public List<LhssysEngineCols> getDataAsEngineCode(String engineCode) {
        List<LhssysEngineCols> LhssysEngineColsList;
        try {
            Criteria crit = getSession().createCriteria(LhssysEngineCols.class);
            crit.add(Restrictions.eq("engine_name", engineCode));
            if (engineCode.equalsIgnoreCase("24QBV1") || engineCode.equalsIgnoreCase("24QBV1A")) {
                crit.addOrder(Order.asc("grp2_seq"));
            } else {
                crit.addOrder(Order.asc("grp1_seq"));
            }
            LhssysEngineColsList = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            LhssysEngineColsList = null;
            getSession().getTransaction().rollback();
        }
        return (LhssysEngineColsList != null && LhssysEngineColsList.size() > 0 ? LhssysEngineColsList : null);
    }//end method

    @Override
    public ArrayList<ArrayList<String>> getEngineColDataAsArrayList(String l_query, Util utl) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        Session ssn = getSession();

        try {
            PreparedStatement pstmt = ssn.connection().prepareStatement(l_query);
            ResultSet rs = pstmt.executeQuery(l_query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
//            //System.out.println("column_count..." + column_count);
            while (rs.next()) {
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < column_count; i++) {
//                    //System.out.println("i value..." + i);
                    String col_value = rs.getString(i + 1);
                    col_value = utl.isnull(col_value) ? "" : col_value;
                    arr.add(col_value);
                }
                dataList.add(arr);
            }
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

    @Override
    public ArrayList<String> getEngineColDataAsList(String l_query, Util utl) {
        ArrayList<String> dataList = new ArrayList<String>();
        Session ssn = getSession();

        try {
            PreparedStatement pstmt = ssn.connection().prepareStatement(l_query);
            ResultSet rs = pstmt.executeQuery(l_query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
//            //System.out.println("column_count..." + column_count);
            while (rs.next()) {
                for (int i = 0; i < column_count; i++) {
//                    //System.out.println("i value..." + i);
                    String col_value = rs.getString(i + 1);
                    col_value = utl.isnull(col_value) ? "" : col_value;
                    dataList.add(col_value);
                }
            }
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
}//end class

