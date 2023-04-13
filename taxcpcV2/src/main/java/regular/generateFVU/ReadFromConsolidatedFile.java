/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

/**
 *
 * @author akash.dev
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromConsolidatedFile {

//    public static void main(String[] args) {
//        String readDataFromConsolidatedFile = readDataFromConsolidatedFile("D:/BLRI00382E_200910_24Q_Q2.tds", "AAACI1607G");
//    }
    public String readDataFromConsolidatedFile(String path, String Validate_string) {
        String deductor_data = "";
        int lineNo = 0;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            for (lineNo = 1; lineNo < 3; lineNo++) {//stop loop for unneccesarry traversing
                if (lineNo == 2) {
                    deductor_data = br.readLine();
                    String[] values = deductor_data.split("\\^");
//                    //System.out.println("----" + values);
                    deductor_data = (values[12].equalsIgnoreCase(Validate_string)) ? deductor_data : "false";
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

    public Long getLineCount(String path, String lineType) {
        Long return_Long = null;
        int lineNo = 0;

        String lineType_Count = "";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            int totalLines = 0;

            for (lineNo = 1; lineNo < 3; lineNo++) {//stop loop for unneccesarry traversing and to get CD count
                if (lineNo == 2) {
                    lineType_Count = br.readLine();
                    String[] values = lineType_Count.split("\\^");
                    lineType_Count = values[3];
                } else {
                    br.readLine();
                }
            }

            try {
                while (br.readLine() != null) {
                    totalLines++;
                }
            } catch (Exception e) {
                br.close();
            }

            totalLines = totalLines + 2;//As the two lines are read in above for loop

            if (lineType.equalsIgnoreCase("CD")) {
                return_Long = Long.parseLong(lineType_Count);
            } else if (lineType.equalsIgnoreCase("DD")) {
                int cd_count = Integer.parseInt(lineType_Count) + 2; // 2 is for BH(1)+FH(1)
                String dd_count = Integer.toString((totalLines - cd_count));
                return_Long = Long.parseLong(dd_count);
            } else if (lineType.equalsIgnoreCase("BH")) {
                return_Long = 1l;
            } else if (lineType.equalsIgnoreCase("FH")) {
                return_Long = 1l;
            } else if (lineType.equalsIgnoreCase("ALL")) {
                return_Long = (long) totalLines;
            }

        } catch (Exception e) {
            return_Long = 0l;
            e.printStackTrace();
        }

        return return_Long;
    }
}
