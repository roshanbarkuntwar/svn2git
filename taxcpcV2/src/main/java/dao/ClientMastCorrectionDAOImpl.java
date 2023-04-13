package dao;

import dao.generic.GenericHibernateDAO;
import hibernateObjects.ClientMastCorrection;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author aniket.naik
 */
public class ClientMastCorrectionDAOImpl extends GenericHibernateDAO<hibernateObjects.ClientMastCorrection, Serializable>
        implements ClientMastCorrectionDAO {

    @Override
    public ClientMastCorrection readClientByCorrParams(ClientMastCorrection clientMastCorrection) {
        Criterion crit1 = Restrictions.eq("client_code", clientMastCorrection.getClient_code());
        Criterion crit2 = Restrictions.eq("acc_year", clientMastCorrection.getAcc_year());
        Criterion crit3 = Restrictions.eq("entity_code", clientMastCorrection.getEntity_code());
        Criterion crit4 = Restrictions.eq("quarter_no", clientMastCorrection.getQuarter_no());
        Criterion crit5 = Restrictions.eq("tds_type_code", clientMastCorrection.getTds_type_code());
        List<ClientMastCorrection> readByCriteria = readByCriteria(crit1, crit2, crit3, crit4, crit5);
        return readByCriteria != null && readByCriteria.size() > 0 ? readByCriteria.get(0) : null;
    }

}
