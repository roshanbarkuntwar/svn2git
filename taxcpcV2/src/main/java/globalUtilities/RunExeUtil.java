/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

/**
 *
 * @author akash.dev
 */
public class RunExeUtil {
    //This is Example that display how to run exe using java

    public void exe_main(String fvu_file, String tanno, String dsc_panno, String dsc_pin, String folder_path) {
        String result = "";
        WriteFileUtil wfu = new WriteFileUtil();
        result = wfu.write_file(fvu_file, tanno, dsc_panno, dsc_pin, folder_path);
        if ("T".equals(result)) {
            String filePath = "E:/DSC_MAN/DSC_UTILITY.exe";
            try {
                Process p = Runtime.getRuntime().exec(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
