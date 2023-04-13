/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadDirectFromDatabase;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.commons.mail.ByteArrayDataSource.BUFFER_SIZE;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author akash.meshram
 */
public class DownloadDataDirectFromClob extends HttpServlet implements SessionAware {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        utl.generateLog("Download File Direct From Clob", "");

        Session ssn = getSessionFactory().openSession();
        try {
            Work work;
            work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    String contextPath = request.getContextPath();
                    utl.generateLog("contextPath", contextPath);
                    utl.generateLog("Download File Direct From Clob", "");

                    String seqno = request.getParameter("process_seqno");
                    utl.generateLog("Process_seqno", seqno);
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select * from LHSSYS_PROCESS_LOG t WHERE t.process_seqno = ?";
                    Long seq_no = Long.parseLong(seqno);
                    try {
                        utl.generateLog("Download Text File From Blob", "");
                        pstmt = cnctn.prepareStatement(query);
                        pstmt.setLong(1, seq_no);//47894
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            utl.generateLog("get data from Clob", "");
                            // gets file name and file blob data
                            Clob clob = rs.getClob("LOG_FILE_NAME3");
                            String file_name = rs.getString("FVU_TXT_FILE_NAME");
                            utl.generateLog("get file_name from db", file_name);
                            if (utl.isnull(file_name)) {
                                file_name = "File.txt";
                            }
                            Reader reader = clob.getCharacterStream();
                            int fileLength = (int) clob.length();
                            utl.generateLog("fileLength ", fileLength);

                            ServletContext context = getServletContext();

                            // sets MIME type for the file download
                            String mimeType = context.getMimeType(file_name);
                            if (mimeType == null) {
                                mimeType = "application/octet-stream";
                            }

                            // set content properties and header attributes for the response
                            response.setContentType(mimeType);
                            response.setContentLength(fileLength);
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", file_name);
                            response.setHeader(headerKey, headerValue);

                            // writes the file to the client
                            OutputStream outStream = response.getOutputStream();
                            byte[] buffer = new byte[BUFFER_SIZE];
                            int bytesRead = -1;

                            while ((bytesRead = reader.read()) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            reader.close();
                            outStream.close();
                        } else {
                            utl.generateLog("Data not found token no ", seq_no);
                            request.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
                            response.sendRedirect(contextPath + "/downloadCustomError");

                        }

                    } catch (SQLException ex) {
                        try {
                            utl.generateLog("Data not found token no ", seq_no);
                            request.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
                            response.sendRedirect(contextPath + "/downloadCustomError");
                        } catch (IOException ex1) {
                            Logger.getLogger(DownloadDataDirectFromClob.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    } catch (IOException ex) {
                        try {
                            utl.generateLog("Data not found token no ", seq_no);
                            request.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
                            response.sendRedirect(contextPath + "/downloadCustomError");
                        } catch (IOException ex1) {
                            Logger.getLogger(DownloadDataDirectFromClob.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    } catch (Exception e) {
                        try {
                            utl.generateLog("Data not found token no ", seq_no);
                            request.getSession().setAttribute("error_Message", "Data not found for Token No: " + "");
                            response.sendRedirect(contextPath + "/downloadCustomError");
                        } catch (IOException ex) {
                            Logger.getLogger(DownloadDataDirectFromClob.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
            e.printStackTrace();

            ssn.beginTransaction().rollback();
        } finally {
            ssn.close();
        }

    }//end method

    private InputStream fileInputStream;
    private HttpServletRequest request;
    private final Util utl;
    private String process_seqno;
    private Map<String, Object> session;
    private String fvuSessionResult;

    public DownloadDataDirectFromClob() {
        utl = new Util();
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getFvuSessionResult() {
        return fvuSessionResult;
    }

    public void setFvuSessionResult(String fvuSessionResult) {
        this.fvuSessionResult = fvuSessionResult;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
