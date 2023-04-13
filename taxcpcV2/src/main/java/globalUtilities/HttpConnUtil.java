/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author akash.dev
 */
public class HttpConnUtil {

    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     *
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @return
     * @throws IOException
     */
    public static String downloadFile(String fileURL, String saveDir) {
        String download_flag = "F";
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();//get file type
                int contentLength = httpConn.getContentLength();//get content length
                //getting file name
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 9,
                                disposition.length());
                    }
                } else {
                    // extracts file name from URL if fail to get name from disposition
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                            fileURL.length());
                }

                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("fileName = " + fileName);
                //System.out.println("contentLength = " + contentLength);
                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                download_flag = "T";
                outputStream.close();
                inputStream.close();
                //System.out.println("File downloaded Successfully");
            } else {
                download_flag = "F";
                //System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (IOException e) {
            download_flag = "F";
        }
        return download_flag;
    }

    public static String downloadFile(String fileURL, String saveDir, long threadInterval) {
        String download_flag = "F";
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            try {
                Thread.sleep(threadInterval);
            } catch (Exception e) {
            }
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                try {
                    Thread.sleep(threadInterval);
                } catch (Exception e) {
                }
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();//get file type
                int contentLength = httpConn.getContentLength();//get content length
                //getting file name
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 9,
                                disposition.length());
                    }
                } else {
                    // extracts file name from URL if fail to get name from disposition
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                            fileURL.length());
                }

                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("fileName = " + fileName);
                //System.out.println("contentLength = " + contentLength);
                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                download_flag = "T";
                outputStream.close();
                inputStream.close();
                //System.out.println("File downloaded Successfully");
            } else {
                download_flag = "F";
                //System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (IOException e) {
            download_flag = "F";
        }
        return download_flag;
    }

    public static String downloadFileAndReturnFileName(String fileURL, String saveDir) {
        String download_flag = "F";
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();//get file type
                int contentLength = httpConn.getContentLength();//get content length
                //getting file name
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 9,
                                disposition.length());
                    }
                } else {
                    // extracts file name from URL if fail to get name from disposition
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                            fileURL.length());
                }

                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("fileName = " + fileName);
                //System.out.println("contentLength = " + contentLength);
                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                download_flag = "T#" + fileName;
                outputStream.close();
                inputStream.close();
                //System.out.println("File downloaded Successfully");
            } else {
                download_flag = "F";
                //System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (IOException e) {
            download_flag = "F";
        }
        return download_flag;
    }

    public void getCookiesFromHTTPConn() throws Exception {

        URL url = new URL("http://localhost/taxcpc/default");
        URLConnection conn = url.openConnection();

        Map<String, List<String>> headerFields = conn.getHeaderFields();

        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

        while (hearerFieldsIter.hasNext()) {

            String headerFieldKey = hearerFieldsIter.next();

            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {

                List<String> headerFieldValue = headerFields.get(headerFieldKey);

                for (String headerValue : headerFieldValue) {

                    //System.out.println("Cookie Found...");
                    String[] fields = headerValue.split(";\\s*");

                    String cookieValue = fields[0];
                    String expires = null;
                    String path = null;
                    String domain = null;
                    boolean secure = false;

                    // Parse each field
                    for (int j = 1; j < fields.length; j++) {
                        if ("secure".equalsIgnoreCase(fields[j])) {
                            secure = true;
                        } else if (fields[j].indexOf('=') > 0) {
                            String[] f = fields[j].split("=");
                            if ("expires".equalsIgnoreCase(f[0])) {
                                expires = f[1];
                            } else if ("domain".equalsIgnoreCase(f[0])) {
                                domain = f[1];
                            } else if ("path".equalsIgnoreCase(f[0])) {
                                path = f[1];
                            }
                        }

                    }

                    System.out.println("cookieValue:" + cookieValue);
                    System.out.println("expires:" + expires);
                    System.out.println("path:" + path);
                    System.out.println("domain:" + domain);
                    System.out.println("secure:" + secure);

                }

            }

        }

    }

}
