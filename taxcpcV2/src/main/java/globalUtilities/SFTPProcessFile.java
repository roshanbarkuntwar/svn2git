/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;

public class SFTPProcessFile {

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

//    public static void main(String[] args) throws FileNotFoundException {
//        String src = args[0];
//        String dest = args[1];
//        int days = !isNull(args[2]) ? Integer.parseInt(args[2]) : 0;
//        String operation = args[3];
//        String host = args[4];
//        int port = !isNull(args[5]) ? Integer.parseInt(args[5]) : 0;
//        String username = args[6];
//        String password = args[7];
//////        System.out.println("Upload started...");
////        String host = "192.168.100.21";
////        int port = 22;
////        String username = "administrator";
////        String password = "Lhs@1234";
//////        String fileName = "E:\\csv\\NEWA2_23287_191CLIENT_MAST_11111.csv";
//////        String sftpFileName = "59554644.txt";
//////
////////        new SFTPProcessFile().uploadFileToSFTP(host, port, username, password, fileName);
//////        new SFTPProcessFile().downloadFileFromSFTP(host, port, username, password, sftpFileName, "H:\\FVU_RELATED_FILES\\23293\\19-20\\Q1\\26Q\\59554644.txt");
//        new SFTPProcessFile().transferLogFiles(src, dest, days, operation, host, port, username, password);
//    }//end method
    public void uploadFileToSFTP(final String host, final int port, final String username, final String password, final String filePath) {

        if (!isNull(host) && !isNull(username) && !isNull(password) && !isNull(filePath)) {
            String SFTPWORKINGDIR = "/G:/TEXT_FILES/";

            File f = new File(filePath);
            if (f.exists()) {
                long interval = 1000l;
                Runnable runnable = () -> {
                    try {
                        channelSftp = this.connectAndReturnSFTPChannel(host, port, username, password);
                        if (channelSftp != null) {
//                            channelSftp.cd(SFTPWORKINGDIR);
                            channelSftp.put(new FileInputStream(f), f.getName());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        this.disconnectSFTPChannel();
                    }
                };

                Thread t = new Thread(runnable);
                t.start();

                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                }
            } else {
            }
        } else {
        }
    }//end method

    public void downloadFileFromSFTP(final String host, final int port, final String username, final String password, final String fileName, final String localFilePath) {

        if (!isNull(host) && !isNull(username) && !isNull(password) && !isNull(fileName) && !isNull(localFilePath)) {
            final String SFTPWORKINGDIR = "/G:/TEXT_FILES/";

            try {
                long interval = 1000l;
                Runnable runnable = () -> {
                    try {
                        System.out.println("SFTP Download started........\n");
                        channelSftp = this.connectAndReturnSFTPChannel(host, port, username, password);
                        if (channelSftp != null) {
                            channelSftp.cd(SFTPWORKINGDIR);
                            channelSftp.get(fileName, new FileOutputStream(localFilePath));
                        }
                    } catch (FileNotFoundException | SftpException e) {
                        e.printStackTrace();
                    }
                };

                Thread t = new Thread(runnable);
                t.start();

                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                }
            } catch (Exception ex) {
            } finally {
                this.disconnectSFTPChannel();
            }
        } else {
        }
    }//end method

    public void transferLogFiles(String src, String dest, int days, String operation, final String host, final int port, final String username, final String password) throws FileNotFoundException {

        try {
            if (!isNull(src) && !isNull(dest) && !isNull(host) && !isNull(username) && !isNull(password)) {
                File dir = new File(src);
                Calendar cal = Calendar.getInstance();
                Date currentDate = cal.getTime();
//            System.out.println("currentDate-- " + sdf.format(new Date(cal.getTimeInMillis())));

                cal.add(Calendar.DATE, (days > 0 ? -days : -7));
                Date prevDate = cal.getTime();
//            System.out.println("beforeDays-- " + sdf.format(beforeDays));

                channelSftp = this.connectAndReturnSFTPChannel(host, port, username, password);
                if (channelSftp != null) {
                    channelSftp.cd(!isNull(dest) ? dest : "/G:/TEXT_FILES/");

                    if (dir.isDirectory()) {
                        try {
                            FileTime fileTime;
                            int fileCount = 0;

                            File[] fileArray = dir.listFiles();
                            if (fileArray != null) {
//                                channelSftp.cd(!isNull(dest) ? dest : "/G:/TEXT_FILES/");
                                System.out.println("\nTotal files count: " + fileArray.length);

                                for (File file : fileArray) {
                                    fileTime = Files.getLastModifiedTime(file.toPath());
                                    Date fileCreationDate = new Date(fileTime.toMillis());

                                    try {
                                        if ((fileCreationDate.before(currentDate) && fileCreationDate.after(prevDate))) {
//                                            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get("C:\\Users\\gaurav.khanzode\\Desktop\\temp\\" + file.getName()));
                                            channelSftp.put(new FileInputStream(new File(file.getAbsolutePath())), file.getName());

                                            if (!isNull(operation) && "move".equalsIgnoreCase(operation) && !file.isDirectory()) {
                                                Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
                                            }
                                            ++fileCount;
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else if (dir.isFile()) {
//                        channelSftp.cd(!isNull(dest) ? dest : "/G:/TEXT_FILES");
                        channelSftp.put(new FileInputStream(new File(dir.getAbsolutePath())), dir.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "No such file or directory found, please provide valid source directory.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No sufficient parameters found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.disconnectSFTPChannel();
        }
    }//end method

    public String readFileData(String file_src, String host, int port, String username, String password, final String workingdir) {
        StringBuilder file_data = new StringBuilder();
        //File sftp_log_file = null;
        try {
            if (!isNull(file_src)) {
                channelSftp = this.connectAndReturnSFTPChannel(host, port, username, password);
                if (channelSftp != null) {
//                    final String SFTP_WORKING_DIR = "/G:/TEXT_FILES/";
                    channelSftp.cd(workingdir);

                    try (InputStream downloaded_file = channelSftp.get(file_src)) {
                        if (downloaded_file != null) {
                            SftpATTRS file_metadata = channelSftp.stat(file_src);
                            Date file_date = new Date(file_metadata.getMTime() * 1000l);
                            String file_size = (file_metadata.getSize() / 1024) + " KB";
                            String file_contents = IOUtils.toString(downloaded_file, StandardCharsets.UTF_8);
//                            int data;
//                            while ((data = downloaded_file.read()) != -1) {
//                                output_file.write(data);
//                            }
//                            sftp_log_file = new File(file_dst);
                            file_data.append(file_date).append("#").append(file_size).append("#").append(file_contents);
                        }
                    } catch (Exception e) {
                        file_data.setLength(0);
                        file_data.append("File not found: ").append(file_src);
                        e.printStackTrace();
                    }
                }
            }
        } catch (SftpException e) {
            file_data.setLength(0);
            file_data.append("Error connecting to the server.");
            e.printStackTrace();
        } finally {
            this.disconnectSFTPChannel();
        }
        return file_data.toString();
    }//end method

    public ChannelSftp connectAndReturnSFTPChannel(final String sftpHost, final int sftpPort, final String sftpUsername, final String sftpPassword) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
            session.setPassword(sftpPassword);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            System.out.println("Host connected.");

            channel = session.openChannel("sftp");
            channel.connect();


        } catch (Exception e) {
        }
        return channel != null ? (ChannelSftp) channel : null;
    }//end method

    public void disconnectSFTPChannel() {
        try {
            if (this.channelSftp.isConnected()) {
                this.channelSftp.exit();
            }
            if (this.channel.isConnected()) {
                this.channel.disconnect();
            }
            if (this.session.isConnected()) {
                this.session.disconnect();
            }
        } catch (Exception e) {
        }
    }//end method

    public static boolean isNull(String comparionValue) {
        boolean nullValue = true;
        try {
            if (comparionValue != null && !"".equals(comparionValue) && !"null".equalsIgnoreCase(comparionValue) && comparionValue.length() > 0) {
                nullValue = false;
            }
        } catch (NullPointerException nullPointerException) {
        } catch (Exception exception) {
        }
        return nullValue;
    }//end method
}//end class
