/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.taxAudit;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.DateTimeUtil;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ayushi.jain
 */
public class TaxAuditSupport {

    public String copyFiles(final String destPath, ViewClientMast client, Assessment asmt) {

        boolean result = false;
        String path = "";
        try {
            DbFunctionExecutorAsString dbFunction = new DbFunctionExecutorAsString();
            ArrayList<String> list = dbFunction.getResultAsList(new TaxAuditDB().getFilePathQuery(client, asmt));
            String outputFilePath = destPath + "ZIP_FOLDER" + File.separator;

            File outFile = new File(outputFilePath);
            if (!outFile.exists()) {
                outFile.mkdirs();
            }
            if (list != null && list.size() > 0) {
                list.forEach(data -> {
                    if (!utl.isnull(data)) {
                        File f = new File(data);
                        File destFile = new File(destPath + f.getName());
                        if (f.exists()) {
                            try {
                                FileUtils.copyFile(f, destFile);
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }

                    }

                });

            }
            path = zipFile(destPath);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return path;
    }

    public String zipFile(String sourcePath) {
        boolean result = false;
        String path = "";

        try {
            String outputFilePath = sourcePath + "ZIP_FOLDER" + File.separator;
            String outPutFileName = "TAX_AUDIT_" + new DateTimeUtil().get_sysdate("ddMMyyyy_HHmmss") + ".zip";
            File f = new File(sourcePath);
            File outFile = new File(outputFilePath);
            if (!outFile.exists()) {
                outFile.mkdirs();
            }
            if (f.exists()) {
                ZipFileUtil zip = new ZipFileUtil();
                File list[] = f.listFiles();
                result = createZip(list, outputFilePath + outPutFileName);
                if (result) {
                    path = outputFilePath + outPutFileName;
                }

            }

        } catch (Exception e) {

        }

        return path;

    }

    public boolean createZip(File[] srcFiles, String destFiles) {
        boolean result = false;
        try {

            // create byte buffer
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(destFiles);
            ZipOutputStream zos = new ZipOutputStream(fos);
            if (srcFiles != null && srcFiles.length > 0) {
                for (int i = 0; i < srcFiles.length; i++) {
                    if (srcFiles[i].isFile()) {

                        FileInputStream fis = new FileInputStream(srcFiles[i]);
                        zos.putNextEntry(new ZipEntry(srcFiles[i].getName()));
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                        // close the InputStream
                        fis.close();
                    }

                }
            }

            // close the ZipOutputStream
            zos.close();
            result = true;

        } catch (IOException ioe) {
        }
        return result;
    }
//
//    public static void main(String[] args) {
//
//        String zipFile = "C:/archive.zip";
//
//        String[] srcFiles = {"C:/srcfile1.txt", "C:/srcfile2.txt", "C:/srcfile3.txt"};
//
//        try {
//
//            // create byte buffer
//            byte[] buffer = new byte[1024];
//            FileOutputStream fos = new FileOutputStream(zipFile);
//            ZipOutputStream zos = new ZipOutputStream(fos);
//            for (int i = 0; i < srcFiles.length; i++) {
//                File srcFile = new File(srcFiles[i]);
//                FileInputStream fis = new FileInputStream(srcFile);
//                zos.putNextEntry(new ZipEntry(srcFile.getName()));
//                int length;
//                while ((length = fis.read(buffer)) > 0) {
//                    zos.write(buffer, 0, length);
//                }
//                zos.closeEntry();
//                // close the InputStream
//                fis.close();
//            }
//
//            // close the ZipOutputStream
//            zos.close();
//
//        } catch (IOException ioe) {
//        }
//
//    }

    public Util utl;

    public TaxAuditSupport() {
        utl = new Util();
    }
}
