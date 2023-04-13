/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import hibernateObjects.LhssysPortalNews;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ankush.jangle
 */
public interface LhssysNewsPortalDAO extends GenericDAO<hibernateObjects.LhssysPortalNews, Serializable> {

//    List<LhssysPortalNews> getNewsBrowseData();
    int getNewsCount();

    List<LhssysPortalNews> getNewsBrowseData(int startIndex);

    LhssysPortalNews getNewsBrowseDataById(long newsId);
}
