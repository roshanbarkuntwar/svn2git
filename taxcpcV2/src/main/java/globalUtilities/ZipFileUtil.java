/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author akash.dev
 */
public class ZipFileUtil {

    public void ZipIt(String UnzipFile, String UnzipFileDir, String outputFolder) {

        byte[] buffer = new byte[1024];

        try {
            FileOutputStream fos = new FileOutputStream(outputFolder);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(UnzipFile);
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(UnzipFileDir);
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            in.close();
            zos.closeEntry();//close zip single entry        
            zos.close();//close zip o/p stream
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//end method
    
    public byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            byte bytes[] = new byte[4096];
            for (String fileName : files) {
                FileInputStream fis = new FileInputStream(directory.getAbsolutePath() + File.separator + fileName);
                BufferedInputStream bis = new BufferedInputStream(fis);
                zos.putNextEntry(new ZipEntry(fileName));

                int bytesRead;
                while ((bytesRead = bis.read(bytes)) > 0) {
                    zos.write(bytes, 0, bytesRead);
                }
                zos.closeEntry();
                bis.close();
                fis.close();
            }
            zos.flush();
            baos.flush();
            zos.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }//end method

    public class MultipleFileZip {

        List<String> fileList;

        public MultipleFileZip() {
            fileList = new ArrayList<String>();
        }

        public void zipMultiplefiles(String sourcePath, String destinationZipFile) {
            this.generateFileList(sourcePath, new File(sourcePath));
            this.zipIt(sourcePath, destinationZipFile);
        }// end method

        private void zipIt(String SOURCE_FOLDER, String zipFile) {
            byte[] buffer = new byte[1024];
            try {
                FileOutputStream fos = new FileOutputStream(zipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);
                for (String file : this.fileList) {
                    ZipEntry ze = new ZipEntry(file);
                    zos.putNextEntry(ze);
                    FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    in.close();
                }
                zos.closeEntry();
                zos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private void generateFileList(String SOURCE_FOLDER, File node) {
            //add file only
            if (node.isFile()) {
                fileList.add(generateZipEntry(SOURCE_FOLDER, node.getAbsoluteFile().toString()));
            }
            if (node.isDirectory()) {
                String[] subNote = node.list();
                for (String filename : subNote) {
                    generateFileList(SOURCE_FOLDER, new File(node, filename));
                }
            }

        }

        private String generateZipEntry(String SOURCE_FOLDER, String file) {
            return file.substring(SOURCE_FOLDER.length() + 1, file.length());
        }// end method

        public void zipListOfFiles(List<String> files, String zipFileLocation) {

            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream fis = null;
            try {
//            fos = new FileOutputStream("C:/testing.zip");
                fos = new FileOutputStream(zipFileLocation);
                zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
                for (String filePath : files) {
                    File input = new File(filePath);
                    fis = new FileInputStream(input);
                    ZipEntry ze = new ZipEntry(input.getName());
                    zipOut.putNextEntry(ze);
                    byte[] tmp = new byte[4 * 1024];
                    int size = 0;
                    while ((size = fis.read(tmp)) != -1) {
                        zipOut.write(tmp, 0, size);
                    }
                    zipOut.flush();
                    fis.close();
                }
                zipOut.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception ex) {

                }
            }
        }
//    private static final String INPUT_ZIP_FILE = "C:\\99026QT.zip";
//    private static final String OUTPUT_FOLDER = "C:\\outputzip";

//    public static void main(String[] args) {
//        UnZipFile unZip = new UnZipFile();
//        unZip.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
//    }
        /**
         * Unzip it
         *
         * @param zipFile input zip file
         * @param output zip file output folder
         */
        public void unZipIt(String zipFile, String outputFolder) {
            byte[] buffer = new byte[1024];
            try {
                //create output directory is not exists
                File folder = new File(outputFolder);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                //get the zip file content
                ZipInputStream zis
                        = new ZipInputStream(new FileInputStream(zipFile));
                //get the zipped file list entry
                ZipEntry ze = zis.getNextEntry();
                while (ze != null) {
                    String fileName = ze.getName();
                    File newFile = new File(outputFolder + File.separator + fileName);
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    ze = zis.getNextEntry();
                }
                zis.closeEntry();
                zis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void unZipFile(String fileName) {
            try {
                ZipFile zipFile = new ZipFile(fileName);
                Enumeration<?> enu = zipFile.entries();
                while (enu.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                    String name = zipEntry.getName();
                    long size = zipEntry.getSize();
                    long compressedSize = zipEntry.getCompressedSize();

                    File file = new File(name);
                    if (name.endsWith("/")) {
                        file.mkdirs();
                        continue;
                    }

                    File parent = file.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }

                    InputStream is = zipFile.getInputStream(zipEntry);
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = is.read(bytes)) >= 0) {
                        fos.write(bytes, 0, length);
                    }
                    is.close();
                    fos.close();

                }
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//    public static void main(String a[]) {
//
//        MultipleFileZip mfe = new MultipleFileZip();
//        List<String> files = new ArrayList<String>();
//        files.add("C:/a.txt");
//        files.add("C:/b.txt");
//        files.add("C:/c.txt");
//        mfe.zipListOfFiles(files);
//    }

//    public static void main(String[] args) {
//        String OUTPUT_ZIP_FILE = "Z:\\BULK_15G_H_XML\\PAR006\\16-17\\26Q\\MyFile.zip";
//        String SOURCE_FOLDER = "Z:\\BULK_15G_H_XML\\PAR006\\16-17\\26Q";
//        MultipleFileZip appZip = new MultipleFileZip();
//        appZip.zipMultiplefiles(SOURCE_FOLDER, OUTPUT_ZIP_FILE);
//    }
    }
//    public static void main(String[] args) {
////        ZipIt("Q126QC.fvu.log","C:\\Q126QC.fvu.log","C:\\MyFile.zip");
////        ZipIt("VJB-ZO3_15H_xml.xml","Z:\\BULK_15G_H_XML\\VJB-HO\\16-17\\26Q\\VJB-ZO3_15H_xml.xml","Z:\\BULK_15G_H_XML\\VJB-HO\\16-17\\26Q.zip");
//    }

    public byte[] getZipFileData(String[] files) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            byte bytes[] = new byte[4096];
            for (String fileName : files) {
                FileInputStream fis = new FileInputStream(fileName);
                BufferedInputStream bis = new BufferedInputStream(fis);
                zos.putNextEntry(new ZipEntry(fileName));

                int bytesRead;
                while ((bytesRead = bis.read(bytes)) > 0) {
                    zos.write(bytes, 0, bytesRead);
                }
                zos.closeEntry();
                bis.close();
                fis.close();
            }
            zos.flush();
            baos.flush();
            zos.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }//end method

    public void unZipIt(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];
        try {
            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
            //get the zip file content
            ZipInputStream zis
                    = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
