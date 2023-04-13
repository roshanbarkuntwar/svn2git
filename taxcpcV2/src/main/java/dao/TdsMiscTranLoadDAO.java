/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.GenericDAO;
import hibernateObjects.TdsMiscTranLoad;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.Serializable;
import regular.dashboard.miscTran.CinMiscFilterEntity;

/**
 *
 * @author gaurav.khanzode
 */
public interface TdsMiscTranLoadDAO extends GenericDAO<TdsMiscTranLoad, Serializable> {

    public TdsMiscTranLoad getByRowId(String rowid_seq);

    public String getPanStatusName(String pan4thChar);

    public int saveMiscCinDetails(CinMiscFilterEntity miscData, UserMast user, Assessment asmt, ViewClientMast client);

    public int deleteTdsTranLoad(String rowid_seq);
}
