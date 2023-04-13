/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ImportFileAttribs;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author abhijeet.joshi
 */
public class ImportExcelSupport {

    private Util utl;
    private FileOptUtil fileoptUtl;

    public ImportExcelSupport() {
        utl = new Util();
        fileoptUtl = new FileOptUtil();

    }

    public String RenameAndSaveUploadedFile(String engineType, ImportFileAttribs uploadFileObj, ViewClientMast viewClientMast, Assessment assessment, String javaDriveName, String oracleDriveName) {
        String filePath = null;
        String fileName = null;
        String destPath = null;
        if (!utl.isnull(engineType.trim()) && engineType.equalsIgnoreCase("EXCEL")) {
            destPath = javaDriveName + "/UPLOADED_EXCEL/";// used when excel files are to be read from local and insert record in database
        } else if (!utl.isnull(engineType.trim()) && engineType.equalsIgnoreCase("FUVTXT")) {
            destPath = oracleDriveName + "/FVU_TEXT_IMPORT/";// use only when fvu text file is uploaded for oracle shared drive access
        }
        try {
            fileName = uploadFileObj.getTemplateFileName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = fileoptUtl.setTaxcpcUploadFilename(viewClientMast, assessment) + "." + extension;
            File destFile = new File(destPath, newFileName);
            FileUtils.copyFile(uploadFileObj.getTemplate(), destFile);
            filePath = destFile.getAbsolutePath();
            utl.generateLog("File Successfully Copied To JAVA Drive", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }//End Method
}//End Class
