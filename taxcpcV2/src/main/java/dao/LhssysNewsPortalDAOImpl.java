/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.LhssysPortalNews;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ankush.jangle
 */
public class LhssysNewsPortalDAOImpl extends GenericHibernateDAO<hibernateObjects.LhssysPortalNews, Serializable> implements LhssysNewsPortalDAO {

    @Override
    public List<LhssysPortalNews> getNewsBrowseData(int startIndex) {
        List<LhssysPortalNews> newsList = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysPortalNews.class);
            crit.addOrder(Order.asc("news_id"));
            crit.setFirstResult(startIndex);
            crit.setMaxResults(10);
            newsList = crit.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            newsList = null;
        }
        return newsList;
    }

    @Override
    public LhssysPortalNews getNewsBrowseDataById(long newsId) {
        List<LhssysPortalNews> data = null;
        try {
            Criteria crit = getSession().createCriteria(LhssysPortalNews.class);
            crit.add(Restrictions.eq("news_id", newsId));
            data = crit.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return data != null && data.size() > 0 ? data.get(0) : null;
    }

    @Override
    public int getNewsCount() {
        Long newscount = 0l;
        try {
            Criteria crit = getSession().createCriteria(LhssysPortalNews.class);
            crit.setProjection(Projections.rowCount());
            newscount = (Long) crit.uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return newscount.intValue();
    }
}
