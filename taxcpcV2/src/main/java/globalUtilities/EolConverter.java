/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author akash.dev
 */
public class EolConverter {

    Util utl;

    public void convertUnixToWindows(String inFilePara, String outFilePara) {
        try {
            utl.generateLog("-----------**************-------------------------------", "");
            utl.generateLog("-----------File Conversion Initialised------------------", "");
//            String inFilePara = "";
//            String outFilePara = "";
//            inFilePara = args[0];
//            outFilePara = args[1];
//            String inFilePara = "";
//            String outFilePara = "";
//            inFilePara = args[0];
//            outFilePara = args[1];
            utl.generateLog("Input File--->>", inFilePara);
            utl.generateLog("Output File--->>", outFilePara);
            File inFile = new File(inFilePara);
            File outFile = new File(outFilePara);
            utl.generateLog("Processing...", "");
            BufferedWriter bufferedwriter = null;
            try {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(inFile));
                bufferedwriter = new BufferedWriter(new FileWriter(outFile));
                String line;
                while ((line = bufferedreader.readLine()) != null) {
                    bufferedwriter.write(line);
                    bufferedwriter.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedwriter != null) {
                        bufferedwriter.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            utl.generateLog("-----------**************-------------------------------", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//End Class
