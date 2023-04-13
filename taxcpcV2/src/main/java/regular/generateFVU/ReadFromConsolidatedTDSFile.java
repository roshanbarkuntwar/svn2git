/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import globalUtilities.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author bhawna.agrawal
 */
public class ReadFromConsolidatedTDSFile {

    private globalUtilities.Util utl;

    public ReadFromConsolidatedTDSFile() {
        utl = new Util();
    }

    public boolean readDataFromConsolidatedTdsFile(String path, String validateString1, String validateString2, String validateString3, String tdsTypeCode) {
        String deductor_data = "";
        boolean returnValue = false;
        int lineNo = 0;
        int count = 0;
        utl.generateLog("validateString1...", validateString1);
        utl.generateLog("validateString2...", validateString2);
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            for (lineNo = 1; lineNo < 3; lineNo++) {//stop loop for unneccesarry traversing
                if (lineNo == 2) {
                    deductor_data = br.readLine();
                    String[] values = deductor_data.split("\\^");
                    if (values[12].equals(validateString1)) {
                        count++;
                    }
                    if (values[15].equals(validateString2)) {
                        count++;
                    }
                    if (values[17].equals(validateString3)) {
                        count++;
                    }
//                    if (values[4].equals(tdsTypeCode)) {
//                        count++;
//                    }
                } else {
                    br.readLine();
                }
            }
            if (count == 3) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }// end method

    public String readDateFromConsolidatedFile(String path) {
        String deductor_data = "";
        int lineNo = 0;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            for (lineNo = 1; lineNo < 2; lineNo++) {//stop loop for unneccesarry traversing
                if (lineNo == 1) {
                    deductor_data = br.readLine();
                    String[] values = deductor_data.split("\\^");
                    deductor_data = values[4];
                } else {
                    br.readLine();
                }
            }
        } catch (IOException e) {
            deductor_data = "";
            e.printStackTrace();
        }
        //System.out.println("Deductor_data: " + deductor_data);
        return deductor_data;
    }

    public String getClientNameFromCsiFile(String path) {
        String clientName = "";
        int lineNo = 0;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            for (lineNo = 1; lineNo < 2; lineNo++) {//stop loop for unneccesarry traversing
                if (lineNo == 1) {
                    clientName = br.readLine();
                    String[] values = clientName.split("\\^");
                    clientName = values[2];

                } else {
                    br.readLine();
                }
            }
        } catch (IOException e) {
            clientName = "";
            e.printStackTrace();
        }
        return clientName;
    }
}
