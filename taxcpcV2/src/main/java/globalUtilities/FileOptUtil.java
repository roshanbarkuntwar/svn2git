/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import com.lhs.taxcpcv2.bean.Assessment;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author akash.dev
 */
public class FileOptUtil {

    public class FileDeletePermanent {

        /**
         * This method Search file in given location and return its absolute
         * path
         *
         * @param name
         * @param file
         * @return
         * @throws java.lang.Exception
         */
        public String findFile(String name, File file) throws Exception {
            String returnAbspath = "";

            try {
                File[] list = file.listFiles();
                if (list != null) {
                    for (File chkin : list) {
                        if (chkin.isDirectory()) {
                            findFile(name, chkin);

                        } else if (name.equalsIgnoreCase(chkin.getName())) {
                            returnAbspath = chkin.getAbsolutePath();

                        }
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return returnAbspath;
        }

        /**
         * This method Copy Source file content in Destination file
         *
         * @param srcFile
         * @param destFile
         * @throws java.io.IOException
         */
        public void copyFile(File srcFile, File destFile) throws IOException {
            long point = 0, destpoint, size;
            FileChannel in = null, out = null;
            try {
                if (!srcFile.exists()) {
                    srcFile.createNewFile();
                }

                in = new FileInputStream(srcFile).getChannel();
                out = new FileOutputStream(destFile).getChannel();
                size = in.size();

                while ((destpoint = out.transferFrom(in, point, size)) > 0) {
                    point += destpoint;
                }
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }

        }

        public String checkStatus(String path, String name, String flag) throws IOException {

            FileDeletePermanent fdp = new FileDeletePermanent();
            String returnString = "F";

            try {

                String filePath = fdp.findFile(name, new File(path));
                File fileOrg = new File(filePath);
                if (fileOrg.exists()) {
                    Path fi = Paths.get(fdp.findFile(name, new File(path)));
                    if ("D".equals(flag)) {
                        try {
                            Files.deleteIfExists(fi);
                            if (fileOrg.exists()) {
                                returnString = "F";
                            } else {
                                returnString = "T";
                            }
                        } catch (IOException e) {
                            returnString = "F";
                            e.printStackTrace();
                        }
                    } else if ("CD".equals(flag)) {
                        try {
                            int x = filePath.lastIndexOf(".");
                            String fileName = filePath.substring(0, x) + " - Copy" + filePath.substring(x);
                            File newCopyfile = new File(fileName);
                            this.copyFile(fileOrg, newCopyfile);
                            Files.deleteIfExists(fi);
                            if (fileOrg.exists()) {
                                returnString = "F";
                            } else {
                                returnString = "T";
                            }
                        } catch (IOException e) {
                            returnString = "F";
                            e.printStackTrace();
                        }

                    } else {
                        returnString = "F";
                    }
                } else {
                    try {
                        /*This section for Java8 only*/
                        //    Arrays.stream(new File(path).listFiles()).forEach(File::delete);
                        /*This section for Java6 onwards*/
                        File filefordel = new File(path);
                        if (filefordel.isDirectory()) {
                            if (filefordel.list().length > 0) {
                                File[] files = filefordel.listFiles();
                                for (File f : files) {
                                    if (f.isFile() && f.exists()) {
                                        f.delete();
                                    } else {
                                    }
                                }
                                returnString = "F";
                            } else {
                                returnString = "F";
                            }
                        } else {
                            returnString = "F";
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        returnString = "F";
                    }
                }
                return returnString;
            } catch (Exception e) {
                e.printStackTrace();
                returnString = "F";
            }
            return returnString;
        }
    }

    public double GetFileSizeByFilePath(String FilePath) {
        double SizeInMb;
        try {
            SizeInMb = ((new File(FilePath).length()) / (1024.0 * 1024.0));
        } catch (Exception e) {
            SizeInMb = 0.0;
        }
        return SizeInMb;
    }

    public String GetFileSizeByFilePathAsString(String FilePath) {
        double SizeInMb;
        NumberFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(2);
        try {
            SizeInMb = ((new File(FilePath).length()) / (1024.0 * 1024.0));
        } catch (Exception e) {
            SizeInMb = 0.0;
        }
        return nf.format(SizeInMb) + " mb";
    }

    public boolean createFileLocation(String path) {
        boolean flag = true;
        try {

//            if (!isnull(path)) {//check null
            File file = new File(path);
            if (!file.exists()) {
                flag = file.mkdirs();
            }
//            } else {
//                flag = false;
//            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }// end method

    public Boolean remaneFileOrDirectory(String oldFileName, String newFileName) throws IOException {
        boolean fileRenamed = false;
        try {
            File file1 = new File(oldFileName);
            File file2 = new File(newFileName);
            if (file1.exists()) {
                fileRenamed = file1.renameTo(file2);
            } else {
                System.out.println("*****************************************");
                System.out.println(file1 + " : This file does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileRenamed;
    }// end method

    /**
     * This method is used to copy contents of one file into another
     *
     */
    public void copyFileContent(String srcFile, String destFile) {

        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File sourceFile = new File(srcFile);
            File destinationFile = new File(destFile);

            if (sourceFile.exists() && destinationFile.exists()) {
                inStream = new FileInputStream(sourceFile);
                outStream = new FileOutputStream(destinationFile);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }

                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            } else {
                System.out.println("*****************************************");
                System.out.println("Source file :" + srcFile);
                System.out.println("Destination  file :" + destFile);
                System.out.println("Source file or Destination file does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end method

    /**
     * This method returns new file name with timestamp
     *
     * @param fileName Input file name
     * @return fileNew Return file name with timestamp
     */
    public String getFileNameWithTimestamp(String fileName) {
        String returnFileName = null;
        try {
            String[] split = new String[2];
            if (fileName.contains(".")) {
                split = fileName.split("\\.");
            } else {
                split[0] = fileName;
                split[1] = "";
            }
            String timeStamp = new DateTimeUtil().getCutrrentTimeStamp();
            returnFileName = split[0] + "_" + timeStamp + "." + split[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnFileName;
    }// end method

    /**
     * This method is used to move file from one location to another
     *
     * @param srcFile File to be moved with complete path
     * @param destFileLocation Path where to move file
     * @return <code>true</code> if file moved else <code>false</code>
     */
    public boolean moveFile(String srcFile, String destFileLocation) {
        boolean fileMoved = false;
        try {
            File fileSrc = new File(srcFile);
            File fileDest = new File(destFileLocation + fileSrc.getName());
            if (fileSrc.exists()) {
                if (fileSrc.renameTo(fileDest)) {
                    fileMoved = true;
                }
            } else {
                System.out.println("*****************************************");
                System.out.println("Source file :" + srcFile);
                System.out.println("Source file does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileMoved;
    }// end method

    /**
     * This method is used to copy file
     *
     * @param srcFile File to be copied with complete path
     * @param destination Destination where file is to be copied
     * @return <code>true</code> if file copied else <code>false</code>
     */
    public Boolean copyFile(String srcFile, String destination) {
        boolean fileCopied = false;
        try {
            File file1 = new File(srcFile);
            File file2 = new File(destination + File.separator + file1.getName());
            if (file1.exists()) {
                FileUtils.copyFile(file1, file2);
                fileCopied = true;
            } else {
                System.out.println("*********************");
                System.out.println("Source File : " + file1);
                System.out.println("File does not exist..");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileCopied;
    }// end method

    /**
     * This method is used to copy file
     *
     * @param srcFile File to be copied
     * @param destination Destination where file is to be copied
     * @return <code>true</code> if file copied else <code>false</code>
     */
    public Boolean copyFile(File srcFile, String destination) {
        boolean fileCopied = false;
        try {
            File file2 = new File(destination + File.separator + srcFile.getName());
            if (srcFile.exists()) {
                FileUtils.copyFile(srcFile, file2);
                fileCopied = true;
            } else {
                System.out.println("*********************");
                System.out.println("Source File : " + srcFile);
                System.out.println("File does not exist..");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileCopied;
    }// end method

    /**
     * This method is used to upload files
     *
     * @param srcFile File to be copied with complete path
     * @param destination Destination where file is to be copied
     * @param destFileName Name of file at destination
     * @return <code>true</code> if file copied else <code>false</code>
     */
    public Boolean copyFile(String srcFile, String destination, String destFileName) {
        boolean fileCopied = false;
        try {
            File file1 = new File(srcFile);
            File file2 = new File(destination + File.separator + destFileName);
            if (file1.exists()) {
                FileUtils.copyFile(file1, file2);
                fileCopied = true;
            } else {
                System.out.println("*********************");
                System.out.println("Source File : " + file1);
                System.out.println("File does not exist..");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileCopied;
    }//ens method

    /**
     * This method is used to upload files
     *
     * @param srcFile File to be copied
     * @param destination Destination where file is to be copied
     * @param destFileName
     * @return <code>true</code> if file copied else <code>false</code>
     */
    public Boolean copyFile(File srcFile, String destination, String destFileName) {
        boolean fileCopied = false;
        try {
            File file2 = new File(destination + File.separator + destFileName);
            if (srcFile.exists()) {
                FileUtils.copyFile(srcFile, file2);
                fileCopied = true;
            } else {
                System.out.println("*********************");
                System.out.println("Source File : " + srcFile);
                System.out.println("File does not exist..");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileCopied;
    }

    /**
     * This method is used to create directory(s) if not exists
     *
     * @param dirName Directory structure to be created
     */
    public void createDirectory(String dirName) {
        try {
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// end method

    /**
     * This method is used to delete file or directory
     *
     * @param fileOrDirName File or directory to be deleted
     * @return <code>true</code> if file copied else <code>false</code>
     */
    public boolean deleteFileOrDir(String fileOrDirName) {
        boolean dirDeleted = false;
        try {
            File dir = new File(fileOrDirName);
            if (dir.exists()) {
                try {
                    deleteDir(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirDeleted;
    }// end method

    private void deleteDir(File file) throws IOException {

        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    deleteDir(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            //if file, then delete it
            file.delete();
        }
    }// end method

    public String getTaxcpcDownloadFilename(ViewClientMast client, Assessment assment) {
        String dwnlfilename = "";
        dwnlfilename = "TAXCPC_" + client.getTanno() + "_" + assment.getAccYear().replace("-", "") + "_" + assment.getQuarterNo() + "_" + assment.getTdsTypeCode();
        return dwnlfilename;
    }

    public String setTaxcpcUploadFilename(ViewClientMast client, Assessment assment) {
        String upldfilename = "";
        int rnd = (int) (Math.random() * 100000000);
        upldfilename = client.getEntity_code() + client.getClient_code() + assment.getAccYear().replace("-", "") + assment.getQuarterNo() + assment.getTdsTypeCode() + "_" + rnd;
//        System.out.println("upldfilename----" + upldfilename);
        return upldfilename;
    }
    public String setTaxcpcUploadFilename(ViewClientMast client, Assessment assment,String misTemplateCode) {
        String upldfilename = "";
        int rnd = (int) (Math.random() * 100000000);
        upldfilename = client.getEntity_code() + client.getClient_code() + assment.getAccYear().replace("-", "") + assment.getQuarterNo() + assment.getTdsTypeCode() +  "_" + misTemplateCode + "_" + rnd;
//        System.out.println("upldfilename----" + upldfilename);
        return upldfilename;
    }

    public boolean writeDataInExistingExcel(int no_of_column, String savedFilePath, ArrayList<ArrayList<String>> tempDataRecordList) {
        boolean l_write_data = true;
        Util utl = new Util();
        try {
            final String REGEXP = "^[0-9]\\d*(\\.[0-9]\\d*)?$";// Regular expression for allowing digits.
            Pattern pattern = Pattern.compile(REGEXP);
            Matcher matcher = null;

            FileInputStream inputStream = new FileInputStream(new File(savedFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            CellStyle cellStyle = workbook.createCellStyle();

            if (tempDataRecordList.size() > 0) {// used to generate cell data value
                try {

                    for (int i = 0; i < tempDataRecordList.size(); i++) {//rows
                        Row row = sheet.createRow(i + 2);
                        int columnCount = 0;
                        Cell cell = row.createCell(columnCount);

                        for (int j = 0; j < no_of_column; j++) {//cols
                            String contentString = utl.isnull(tempDataRecordList.get(i).get(j)) ? "" : tempDataRecordList.get(i).get(j);

                            if (!utl.isnull(contentString)) {
                                try {
                                    cell = row.createCell(j + 1);
                                    cell.setCellValue(contentString);

                                    matcher = pattern.matcher(contentString);
                                    if (matcher.find() && matcher.group().equals(contentString)) {// formatting text alignment to right for numeric values.                                        
                                        cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
                                        cell.setCellStyle(cellStyle);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//end 

            if (workbook != null) {
                inputStream.close();
                try (FileOutputStream outputStream = new FileOutputStream(savedFilePath)) {
                    workbook.write(outputStream);
//                    workbook.close();
                }
                l_write_data = true;
            } else {
                l_write_data = false;
            }
        } catch (NullPointerException npe) {
            l_write_data = true;
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_write_data;
    }//end method
    
    public Properties readPropertiesFile(String fileName) throws IOException {
        InputStream resourceAsStream = null;
        Properties prop = null;
        try {
            resourceAsStream = this.getClass().getResourceAsStream(fileName);
            if (resourceAsStream != null) {
                prop = new Properties();
                prop.load(resourceAsStream);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            resourceAsStream.close();
        }
        return prop;
    }//end method
     
    public String readPropertiesKeyName(String keyName) throws IOException {
        String key = "";
        try {
            FileOptUtil fileOptUtil = new FileOptUtil();
            Properties prop = fileOptUtil.readPropertiesFile("/projectInfo.properties");
            key = prop.getProperty(keyName);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return key;
    }//end method
}
