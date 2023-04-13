/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import globalUtilities.EolConverter;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author sandeep.bowade
 */
public class UploadZipFileAjaxSupport {

    private final Util utl;

    public UploadZipFileAjaxSupport() {
        utl = new Util();

    }

    public void unZipAndCopyFile(String destPath, String destFolder, String oracleDriveName, String storageLoction) {

//
//                            // code for UNIX to Windows
        EolConverter converter = new EolConverter();
//                                converter.convertUnixToWindows(textFilePath + File.separator + file[i].getName(), textFilePathEOL + File.separator + file[i].getName());
//
        String destPath_Just = destPath + "Justification/";
        String destFolder_Just = destFolder + "/Justification";
        String destPath_PAN = destPath + "PANNO_Correction/";
        String destFolder_PAN = destFolder + "/PANNO_Correction";

        utl.generateLog("destPath 1 --", destPath);
        utl.generateLog("destFolder 2 --", destFolder);
        utl.generateLog("Justification 1 --", destPath_Just);
        utl.generateLog("Justification 2 --", destFolder_Just);
        utl.generateLog("PANNO_Correction 1 --", destPath_PAN);

        // Start Noraml
        try {
            File folder = new File(destFolder);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().endsWith(".zip")) {
                        ZipFileUtil unzip = new ZipFileUtil();
                        unzip.unZipIt(destPath + listOfFiles[i].getName(), destPath);

                    }

//                            if (listOfFiles[i].isFile()) {
//                                System.out.println("File " + listOfFiles[i].getName());
//                            } else if (listOfFiles[i].isDirectory()) {
//                                System.out.println("Directory " + listOfFiles[i].getName());
//                            }
                }//End For

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // End Noraml

        // Start Justification
        try {
            File folder = new File(destFolder_Just);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().endsWith(".zip")) {
                        ZipFileUtil unzip = new ZipFileUtil();
                        unzip.unZipIt(destPath_Just + listOfFiles[i].getName(), destPath_Just);
                    }
                }//End For

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // End Justification

        // Start PANNO_Correction
        try {
            File folder = new File(destFolder_PAN);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().endsWith(".zip")) {
                        ZipFileUtil unzip = new ZipFileUtil();
                        unzip.unZipIt(destPath_PAN + listOfFiles[i].getName(), destPath_PAN);
                    }
                }//End For
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // End PANNO_Correction

//                        Start copy file to Oracle Drive
        File oracleDrive = null;
        try {
            oracleDrive = new File(storageLoction);
        } catch (Exception e) {
        }
//                        System.out.println("oracle Drive --" + storageLoction);
        // Start Normal
        convertUNIXfile(converter, destFolder);
        copyToOracleDrive(destFolder, oracleDrive);
        // End Normal

        // Start Justification
        convertUNIXfile(converter, destFolder_Just);
        copyToOracleDrive(destFolder_Just, oracleDrive);
        // End Justification

        // Start PANNO_Correction
        convertUNIXfile(converter, destFolder_PAN);
        copyToOracleDrive(destFolder_PAN, oracleDrive);
        // End PANNO_Correction

//                        End copy file to Oracle Drive
    }//End Method

    public String unZipAndCopyFileForSeparateProcess(String destPath, String destFolder, String oracleDriveName, String storageLoction, String separator) {

        boolean result = false;
        String fileName = "";

//
//                            // code for UNIX to Windows
        EolConverter converter = new EolConverter();
        utl.generateLog("destPath 1 --", destPath);

        // Start Noraml
        try {
            File folder = new File(destFolder);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().endsWith(".zip")) {
                        ZipFileUtil unzip = new ZipFileUtil();
                        unzip.unZipIt(destFolder + listOfFiles[i].getName(), destPath);

                    }

//                            if (listOfFiles[i].isFile()) {
//                                System.out.println("File " + listOfFiles[i].getName());
//                            } else if (listOfFiles[i].isDirectory()) {
//                                System.out.println("Directory " + listOfFiles[i].getName());
//                            }
                }//End For

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // End Noraml

//                        Start copy file to Oracle Drive
        File oracleDrive = null;
        try {
            oracleDrive = new File(storageLoction);
        } catch (Exception e) {
        }
//                        System.out.println("oracle Drive --" + storageLoction);
        // Start Normal
        String filePath = convertUNIXfile(converter, destFolder);
        if (!utl.isnull(filePath)) {
            File f = new File(filePath);
            fileName = changeSepartorOfFile(f, storageLoction, separator);
        }
//        copyToOracleDrive(destFolder, oracleDrive);
        // End Normal
        return fileName;
    }//End Method

    public String changeSepartorOfFile(File inputFile, String outputFilePath, String seprator) {
        String fileName = "";

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;
        boolean flag = false;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            String newContent = oldContent.replaceAll("\\" + seprator, "^");
            File outputFile = new File(outputFilePath + File.separator + inputFile.getName());

            writer = new FileWriter(outputFile);
            writer.write(newContent);
            flag = true;
            fileName = inputFile.getName();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public boolean deleteFilesFromLocation(String directoryLocation) {
        boolean status = false;
        try {
            File directory = new File(directoryLocation);
            try {
                File[] listFiles = directory.listFiles();
                if (listFiles.length > 0) {
                    for (File file : listFiles) {
//                                  file.delete();
                        FileDeleteStrategy.FORCE.delete(file);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }// End method

    public void copyToOracleDrive(String inFolder, File oracleDrive) {
        try {
            File folder2 = new File(inFolder + "/UNIX");
            if (folder2 != null) {
//                File oracleDrive = new File(storageLoction);
                if (oracleDrive != null) {
                    File[] listOfFiles2 = folder2.listFiles();
                    if (listOfFiles2 != null) {

                        for (int i = 0; i < listOfFiles2.length; i++) {
                            if (listOfFiles2[i].isDirectory()) {

                            } else if (!listOfFiles2[i].getName().endsWith(".zip")) {
                                try {
                                    FileUtils.copyFileToDirectory(listOfFiles2[i], oracleDrive);
                                } catch (Exception e) {
                                }
                            }

                        }//End For                            
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//End Method

    public String convertUNIXfile(EolConverter converter, String inFilePara) {
        String outFilePara = inFilePara + "/UNIX";
        String fileName = "";
        try {
//            String outFilePara = inFilePara + "/UNIX";
            File UNIXLocation = new File(outFilePara);
            if (!UNIXLocation.exists()) {
                if (UNIXLocation.mkdir()) {
//                    System.out.println("Directory is created!");
//                } else {
//                    System.out.println("Failed to create directory!");
                }
            }

            File folder2 = new File(inFilePara);
            if (folder2 != null) {
                if (UNIXLocation != null) {
                    File[] listOfFiles2 = folder2.listFiles();
                    if (listOfFiles2 != null) {

                        for (int i = 0; i < listOfFiles2.length; i++) {
                            if (listOfFiles2[i].isDirectory()) {

                            } else if (!listOfFiles2[i].getName().endsWith(".zip")) {
                                try {
                                    converter.convertUnixToWindows(inFilePara + "/" + listOfFiles2[i].getName(), UNIXLocation + File.separator + listOfFiles2[i].getName());
                                    fileName = UNIXLocation + File.separator + listOfFiles2[i].getName();
                                } catch (Exception e) {
                                }
                            }

                        }//End For                            
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }//End Method

}//End Class
