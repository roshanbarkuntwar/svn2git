/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import hibernateObjects.LhssysParameters;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author aniket.naik
 */
public class ClosePopupMessagesThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String externalDriveName = "Z:";
            try {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("JAVA_DRIVE_NAME", "");
                if (readExternalDriveName != null) {
                    externalDriveName = readExternalDriveName.getParameter_value().trim();
                }
            } catch (Exception e) {
            }
            try {
                Process process = new ProcessBuilder(externalDriveName + File.separator + "CLOSE FVU WINDOW.exe").start();
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
