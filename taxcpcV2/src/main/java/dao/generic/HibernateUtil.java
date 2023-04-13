/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import java.sql.Connection;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author akash.dev
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session currentSessionOnly() throws HibernateException {
        Session session = null;
        String DEFAULT_TENANT_ID = "";
        try {
            HttpSession httpSession = ServletActionContext.getRequest().getSession();
            DEFAULT_TENANT_ID = (String) httpSession.getAttribute("TENANTID");
        } catch (Exception e) {
        }

        try {
            session = sessionFactory.openSession();
//            Transaction opentransaction = session.beginTransaction();
//            HibernateUtil.threadTransaction.set(opentransaction);
        } catch (HibernateException ex) {
            if (session != null) {
                session.close();
            }
        }
        return session;
    }//end method

    public static Connection getConnection() {
        Connection conn = null;
        org.hibernate.Session dbsession = null;
        try {//used to create database connection
            dbsession = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction tx = dbsession.beginTransaction();
            conn = dbsession.connection();
        } catch (HibernateException e) {
        }
        return conn;
    }//end method

    public static void closeSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
