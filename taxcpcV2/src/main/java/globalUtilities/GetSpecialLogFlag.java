/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import hibernateObjects.LhssysParameters;

/**
 *
 * @author akash.dev
 */
public class GetSpecialLogFlag {

    private static String specialLogFlag;

    private GetSpecialLogFlag() {
    }

    public static String getLogFlagValue() {
        try {
            if (specialLogFlag != null && !"".equals(specialLogFlag) && !"null".equalsIgnoreCase(specialLogFlag) && specialLogFlag.length() > 0) {
            } else {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                LhssysParameters readLogFlag = lhssysParametersDAO.readDataAsParameterAndEntity("PRINT_SPECIAL_LOG_FLAG", "");
                if (readLogFlag != null) {
                    specialLogFlag = readLogFlag.getParameter_value();
                    if (specialLogFlag == null) {
                        specialLogFlag = "F";
                    }
                }
            }
        } catch (Exception e) {
            specialLogFlag = "F";
        }
        return specialLogFlag;
    }//end method
}//end class
