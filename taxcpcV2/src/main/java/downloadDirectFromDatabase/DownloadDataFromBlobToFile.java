/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadDirectFromDatabase;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.meshram
 */
public class DownloadDataFromBlobToFile extends HttpServlet implements SessionAware {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        utl.generateLog("Get BlobData FromDB to File And Download ToBrowser", "");

        String contextPath = req.getContextPath();
        try {
            String seqno = req.getParameter("process_seqno");
            utl.generateLog("process_seqno ", seqno);
            String javaDriveName = req.getParameter("javadrive_name");
            utl.generateLog("javaDriveName ", javaDriveName);
            boolean flag = false;
            flag = getBlobDataIntoFileSystem(seqno, javaDriveName);
            if (flag) {
                res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                String filename = getFileName();
                String filepath = getFullFilePath();
                res.setContentType("APPLICATION/OCTET-STREAM");
                res.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                FileInputStream fileInputStream = new FileInputStream(getFullFilePath());
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    out.write(i);
                }
                fileInputStream.close();
                out.close();
            } else {
                utl.generateLog("Data not found token no ", "");
                req.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
                res.sendRedirect(contextPath + "/downloadCustomError");

            }

        } catch (Exception e) {
            e.printStackTrace();
            utl.generateLog("Data not found token no ", "");
            req.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
            res.sendRedirect(contextPath + "/downloadCustomError");
        }
    }

    public boolean getBlobDataIntoFileSystem(String seq_no, String javaDriveName) {
        System.out.println("get Blob data into file and dowbnload into browser");
        FileDownloadFlag = true;
        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    Long processSeqNo = Long.parseLong(seq_no);
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select * from LHSSYS_PROCESS_LOG t WHERE t.process_seqno ='1624'";
                    try {

                        pstmt = cnctn.prepareStatement(query);
                        //pstmt.setLong(1, processSeqNo);//47894
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            utl.generateLog("get data from clob", "");
                            Blob blob = rs.getBlob("REQ_DWNLD_FILE1");
                            String file_name = rs.getString("FVU_TXT_FILE_NAME");

                            String full_file_path = javaDriveName + File.separator + "BLOB_FILES" + File.separator + file_name;
                            File directory = new File(javaDriveName + File.separator + "BLOB_FILES");
                            if (!directory.exists()) {
                                directory.mkdirs();
                            }
                            setFileName(file_name);
                            setFullFilePath(full_file_path);
                            InputStream inputStream = blob.getBinaryStream();
                            File file = new File(full_file_path);
                            boolean flag = false;
                            try {
                                flag = file.createNewFile();
                            } catch (IOException ioe) {
                                utl.generateLog("Error while Creating File in Java", ioe);
                            }
                            try (FileWriter writer = new FileWriter(file)) {
                                int i;
                                while ((i = inputStream.read()) != -1) {
                                    writer.write(i);
                                }
                                inputStream.close();
                                writer.close();
                            }

                        }

                    } catch (NullPointerException ex) {
                        FileDownloadFlag = false;
                        ex.printStackTrace();

                    } catch (SQLException ex) {
                        FileDownloadFlag = false;
                        ex.printStackTrace();

                    } catch (IOException ex) {
                        FileDownloadFlag = false;
                        ex.printStackTrace();
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (rs != null) {
                            rs.close();
                        }
                    }
                }
            };
            ssn.beginTransaction();
            ssn.doWork(work);
            ssn.beginTransaction().commit();
        } catch (Exception e) {
            FileDownloadFlag = false;
            e.printStackTrace();

            ssn.beginTransaction().rollback();
        } finally {
            ssn.close();
        }

        return FileDownloadFlag;

    }//end method

    private String FileName;
    private String FullFilePath;
    InputStream fileInputStream;
    private Map<String, Object> session;
    private final Util utl;
    private boolean FileDownloadFlag;
    private String errorMessage;

    public DownloadDataFromBlobToFile() {
        utl = new Util();
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFullFilePath() {
        return FullFilePath;
    }

    public void setFullFilePath(String FullFilePath) {
        this.FullFilePath = FullFilePath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
