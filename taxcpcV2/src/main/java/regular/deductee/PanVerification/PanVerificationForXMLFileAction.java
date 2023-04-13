/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee.PanVerification;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author kapil.gupta
 */
public class PanVerificationForXMLFileAction extends ActionSupport implements SessionAware {

    private final Util utl;
    private Map<String, Object> session;
    private String flag;
    private String downloadedFileName;
    private InputStream inputStream;

    private String action;

    public PanVerificationForXMLFileAction() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {

        String returnView = "success_download";
        String l_return_message = "";

        String javaDriveName = "";
        String oracleDriveName = "";

        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        UserMast user = (UserMast) session.get("LOGINUSER");
        if (!utl.isnull(getFlag()) && getFlag().equalsIgnoreCase("true")) {
            if (client != null) {

                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");

                try {
                    l_client_loginid_seq = (Long) sessionId;
                    javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                    oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }

                if (!utl.isnull(oracleDriveName)) {
                    if (!utl.isnull(javaDriveName)) {
                        String process_token = "";
                        try {

                            String xmlfileLocation = javaDriveName + File.separator + "PAN_VERIFICATION_OFFLINE" + File.separator + client.getClient_code() + File.separator + l_client_loginid_seq + File.separator;
                            File f = new File(xmlfileLocation);
                            if (!f.exists()) {
                                f.mkdirs();
                            }
                            try {
                                FileUtils.cleanDirectory(f);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("xmlfile")) {

                                //generate the xml file--
                                this.getPanNumberXMLFile(xmlfileLocation);
                                // ---

                                File list1[] = f.listFiles();
                                if (list1 != null && list1.length > 0) {
                                    process_token = new GetTokenTransactions().generateTokenGlobalMethod(asmt, client, user, l_client_loginid_seq, user.getUser_level() + "",
                                            "DA", "PAN XML Generation Started", "PAN_UNVERIFIED_DOWNLOAD", "PROCESS_INSERT", "PAN Unverified XML Download", "");

                                    String zipFileName = xmlfileLocation + client.getClient_code() + "_" + l_client_loginid_seq + ".zip";
                                    FileOutputStream fos = new FileOutputStream(zipFileName);
                                    ZipOutputStream zos = new ZipOutputStream(fos);

                                    for (File file : list1) {
                                        if (file.getName().endsWith(".xml")) {

                                            addToZipFile(file, zos);
                                        }
                                    }
                                    zos.close();
                                    fos.close();
                                    File fileToDownload = new File(zipFileName);
                                    inputStream = new FileInputStream(fileToDownload);
                                    if (fileToDownload != null && fileToDownload.exists()) {
                                        downloadedFileName = fileToDownload.getAbsolutePath();
                                        this.updateStatus(process_token, "DC", "PAN XML Generation Completed", downloadedFileName);
                                        l_return_message = "success" + '#' + process_token;
                                    } else {
                                        this.updateStatus(process_token, "DB", "PAN XML Generation Failed", "");
                                        l_return_message = "Could not Provide Download, Some Error Occured";
                                    }
//                                        contentLength = fileToDownload.length();
                                    returnView = "success_download";
                                } else {
                                    utl.generateLog("Xml file is not generated", "");
                                    l_return_message = "Could not Provide Download, Some Error Occured";
                                    returnView = "success_download";

                                }
                            }

                        } catch (Exception e) {
                            returnView = "success_download";
                            l_return_message = "Could not Provide Download, Some Error Occured";
                        }
                    } else {
                        l_return_message = "Could not Provide Download, Some Error Occured";
                        utl.generateLog("Java Drive Not Found!!", "");
                        returnView = "success_download";
                    }
                } else {
                    l_return_message = "Could not Provide Download, Some Error Occured";
                    utl.generateLog("Oracle Drive Not Found!!", "");
                    returnView = "success_download";
                }
            }
        }
        inputStream = new ByteArrayInputStream(l_return_message.getBytes(StandardCharsets.UTF_8));
        return returnView;
    }//end method

    public void addToZipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    public void updateStatus(String token, String processStatus, String procesRemark, String fvuFilesPath) {
        int executeUpdate = 0;
        String query
                = "UPDATE LHSSYS_PROCESS_LOG set\n"
                + "process_remark ='" + procesRemark + "',\n"
                + "process_status ='" + processStatus + "',\n"
                + "fvu_files_path ='" + fvuFilesPath + "',\n"
                + "process_end_timestamp =sysdate,\n"
                + "flag ='j',\n"
                + "lastupdate=sysdate\n"
                + "where process_seqno ='" + token + "'";

        try {
            DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
            dbFunctionExecutorAsString.execute_oracle_update_function_as_string(query);
            utl.generateSpecialLog("LHSSYS_PROCESS_LOG update query-->\n", query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPanNumberXMLFile(String filePath) {
        Object sessionId = session.get("LOGINSESSIONID");
        Long l_client_loginid_seq;
        ArrayList<String> list = null;

        String fileName = null;
        try {

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

            ViewClientMastDAO clientdao = factory.getViewClientMastDAO();
            ViewClientMast client = clientdao.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
            String client_code = client.getClient_code();
            String entity_code = client.getEntity_code();

            String pan_query = "";

            pan_query = "    select panno\n"
                    + "  from (select A.panno\n"
                    + "          from PAN_MAST_UNVERIFIED a, client_mast b\n"
                    + "         where a.entity_code = '" + entity_code + "'\n"
                    + "           and b.client_code = a.client_code\n"
                    + "           and (b.client_code = '" + client_code + "' or b.parent_code = '" + client_code + "' or\n"
                    + "               b.g_parent_code = '" + client_code + "' or b.sg_parent_code = '" + client_code + "' or\n"
                    + "               b.ssg_parent_code = '" + client_code + "' or b.sssg_parent_code = '" + client_code + "')\n"
                    + "           and nvl(lhs_UTILITY.is_valid_panno(upper(A.panno)), '#') = 'Y'\n"
                    + "           and a.panno <> 'PANAPPLIED'\n"
                    + "           and a.panno <> 'PANINVALID'\n"
                    + "           and a.panno <> 'PANNOTAVBL'\n"
                    + "           and a.panno <> 'PANNOTREQD')\n"
                    + " where rownum <= '5000'";

            DbFunctionExecutorAsString support = new DbFunctionExecutorAsString();

            List<String> panList = support.getResultAsList(pan_query);

            if (panList != null && !panList.isEmpty()) {
                Integer panSize = panList.size();

                session.put("PANSIZE", panSize);

                ArrayList<ArrayList<String>> panArrayList = new ArrayList<>();
                Long count = null;
                l_client_loginid_seq = (Long) sessionId;

                count = Long.parseLong(String.valueOf(panList.size()));

                Iterator it = panList.iterator();
                int i = 1;
                int j = 1;
                boolean flag = false;

                while (it.hasNext()) {
                    String panmast = (String) it.next();

                    if (!utl.isnull(panmast)) {
                        if (count <= 999) { // for less than the 999 record to generate xml file
                            if (list == null) {
                                list = new ArrayList<>();
                                flag = true;
                            }
                            if (flag) {
//                                    list.add(panmast.getPanno().toUpperCase());
                                list.add(panmast.toUpperCase());
                                if (j == count) {
                                    panArrayList.add(list);
                                }
                                j++;
                            }
                        } else { //for more than 999 records and to create more than one xml file and create List of arrylist(999 records) 
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            if (i < 999) {
//                                    list.add(panmast.getPanno().toUpperCase());
                                list.add(panmast.toUpperCase());
                                if (j == count) {
                                    panArrayList.add(list);
                                }
                            } else {
//                                    list.add(panmast.getPanno().toUpperCase());
                                list.add(panmast.toUpperCase());
                                panArrayList.add(list);
                                list = null;
                                i = 0;
                            }
                            i++;
                            j++;
                        }//end else loop

                    }//end null loop
                }// end while loop

                if (!panArrayList.isEmpty()) {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();

                    File panDirectory = new File(filePath);
                    if (!panDirectory.exists()) {
                        panDirectory.mkdir();
                    }
                    for (ArrayList<String> nextList : panArrayList) { // fetch List of AraayList

                        ArrayList<String> XMLlist = nextList;
                        try {// this block is used to crate a XML file
                            Transformer transformer = transformerFactory.newTransformer();
                            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                            Document doc = docBuilder.newDocument();
                            doc.setXmlStandalone(true);
                            Element rootElement = doc.createElementNS("http://bulkpanws.tcs.com/", "ns1:getPANInfo");
                            doc.appendChild(rootElement);
                            Element PAN = null;
                            int ss = 0;
                            for (String panNo : XMLlist) {// fetch the data from the arry list
                                if (ss < 1000) {
                                    PAN = doc.createElement("pan");
                                    PAN.appendChild(doc.createTextNode(panNo));
                                    rootElement.appendChild(PAN);
                                }
                                ss++;
                            }
                            DOMSource source = new DOMSource(doc);
                            fileName = "PAN(" + Math.random() + ")" + "." + l_client_loginid_seq + ".xml";
                            String newGeneratedFilePath = filePath + fileName;
                            StreamResult result = new StreamResult(new File(newGeneratedFilePath));
                            transformer.transform(source, result);

                        } catch (Exception e) {
                            fileName = null;
                            e.printStackTrace();
                        }//end catch
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        try {
            System.gc();
        } catch (Exception e) {
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

}
