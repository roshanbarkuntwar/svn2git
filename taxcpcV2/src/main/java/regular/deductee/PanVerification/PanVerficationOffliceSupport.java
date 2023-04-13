/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import globalUtilities.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.lingala.zip4j.core.ZipFile;

/**
 *
 * @author trainee
 */
public class PanVerficationOffliceSupport {

    public boolean extractZipFile(String sourcePath, String destination) {
        boolean flag = false;
        try {
            File dest = new File(destination);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            ZipFile zipFile = new ZipFile(sourcePath);
            zipFile.extractAll(destination);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean buildTextFileForProcedure(String txtPathtxtPath, String extractedFilePath) throws IOException {
        boolean result = false;
        try {
            if (!utl.isnull(txtPathtxtPath) && !utl.isnull(extractedFilePath)) {
                File f = new File(extractedFilePath);
                File file[] = f.listFiles();

                if (file != null && file.length > 0) {
                    try (FileWriter fw = new FileWriter(txtPathtxtPath, false)) //the true will append the new data
                    {
                        for (int i = 0; i < file.length; i++) {
                            if (file[i].getName().endsWith(".CSV") || file[i].getName().endsWith(".csv")) {
                                fw.write(file[i].getName() + "\r\n");
                            }
                        }
//
                    }
                    result = true;
                }
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    Util utl;

    public PanVerficationOffliceSupport() {
        utl = new Util();
    }
}
