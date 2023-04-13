/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.validation;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.HibernateDBCredentials;
import dao.generic.DAOFactory;
import hibernateObjects.ClientMast;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class Form15GHXMLGenerationThread implements Runnable, SessionAware {

    private final String batchFilePath;
    private final ViewClientMast clientMast;
    private final Assessment asmt;
    private final String token;
    private final String loginLevel;
    private final String javaDriveName;
    private final String db_url;
    private final String db_userId;
    private final String db_userPassword;

    public Form15GHXMLGenerationThread(String batchFilePath, ViewClientMast clientMast, Assessment asmt, String token, String destPath,
            String fileName, String loginLevel, String javaDriveName, HibernateDBCredentials loginParams, Long l_client_loginid_seq) {
        this.batchFilePath = batchFilePath;
        this.clientMast = clientMast;
        this.asmt = asmt;
        this.token = token;
        this.loginLevel = loginLevel;
        this.javaDriveName = javaDriveName;
        this.db_url = loginParams.getL_db_url();
        this.db_userId = loginParams.getL_db_userid();
        this.db_userPassword = loginParams.getL_db_userpwd();
    }

    public boolean generateBatchFileAndExecute() {
        boolean batchFileCreated = false;

        File batchFile = new File(batchFilePath + File.separator + "CONFIG");
        if (!batchFile.exists()) {
            batchFile.mkdirs();
        }

        String jarFileName = "GenerateXMLJar.jar";

        String batchFileName = "15G_XML_BATCH.bat";

        String[] l_split = batchFilePath.split(":");
        File oldfile = new File(batchFile + File.separator + batchFileName);
        String jarFilesrc = javaDriveName + File.separator + "TAXCPC" + File.separator + "15GHJAR" + File.separator + jarFileName;

        /**
         * Changed logic to execute multiple statement in one batch file -
         * 10-01-2020
         */
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        List<ClientMast> xmlClientCodes = factory.getClientMastDAO().getXmlClients(clientMast.getEntity_code(), clientMast.getClient_code(), loginLevel);
        String text = "";
        String h_text = "";

        /**
         * Batch file text for multiple client code separated by G/H type. -
         * 13-01-2020
         */
        if (xmlClientCodes != null && !xmlClientCodes.isEmpty()) {
            text += "cd\\ \n"
                    + l_split[0] + ":\n"
                    + "cd " + batchFilePath + "\\ \n";

            h_text = text;
            for (ClientMast xmlClientMast : xmlClientCodes) {
                text += " java -jar " + jarFilesrc + " \"" + db_url + "\" \"" + db_userId + "\" \"" + db_userPassword + "\" "
                        + "\"" + token + "\" \"" + asmt.getAccYear() + "\" \"" + asmt.getQuarterNo() + "\" "
                        + "\"" + asmt.getTdsTypeCode() + "\" \"" + xmlClientMast.getClient_code() + "\" \"" + batchFilePath + "\" \"G\" \n";

                h_text += " java -jar " + jarFilesrc + " \"" + db_url + "\" \"" + db_userId + "\" \"" + db_userPassword + "\" "
                        + "\"" + token + "\" \"" + asmt.getAccYear() + "\" \"" + asmt.getQuarterNo() + "\" "
                        + "\"" + asmt.getTdsTypeCode() + "\" \"" + xmlClientMast.getClient_code() + "\" \"" + batchFilePath + "\" \"H\" \n";
            }

            text += " exit\n";
            h_text += " exit\n";
        }

        String batchFileName_h = "15H_XML_BATCH.bat";
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(oldfile)) {
                fileOutputStream.write(text.getBytes());
            }

            File h_type_file = new File(batchFile + File.separator + batchFileName_h);
            try (FileOutputStream fileOutputStream = new FileOutputStream(h_type_file)) {
                fileOutputStream.write(h_text.getBytes());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File generatedfile = new File(batchFile + File.separator + batchFileName);
        File generatedfile_h = new File(batchFile + File.separator + batchFileName_h);
        try {
            if (generatedfile.exists()) {

                String pathToExecute = "cmd /c start " + batchFile + File.separator + batchFileName;
                Runtime runtime = Runtime.getRuntime();
                Process p1 = runtime.exec(pathToExecute);

                batchFileCreated = true;
            } else {
            }

            if (generatedfile_h.exists()) {
                String pathToExecute = "cmd /c start " + batchFile + File.separator + batchFileName_h;
                Runtime runtime = Runtime.getRuntime();
                Process p1 = runtime.exec(pathToExecute);

                batchFileCreated = true;
            } else {
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return batchFileCreated;
    }

    @Override
    public void run() {
        generateBatchFileAndExecute();
    }

    Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
