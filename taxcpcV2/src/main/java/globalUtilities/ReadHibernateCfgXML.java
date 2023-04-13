package globalUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lhs.taxcpcv2.bean.HibernateDBCredentials;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.util.DTDEntityResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gaurav.khanzode
 */
public class ReadHibernateCfgXML implements ServletRequestAware {

    Util utl;
    public HttpServletRequest request;

    public ReadHibernateCfgXML() {
        utl = new Util();
    }//end constructor

    public HibernateDBCredentials readXml() {
        HibernateDBCredentials paramObj = new HibernateDBCredentials();
        try {
//            //System.out.println("start");
            InputStream inps = getClass().getResourceAsStream("/hibernate.cfg.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setEntityResolver(new DTDEntityResolver());
                Document doc = builder.parse(inps);
                NodeList e = doc.getElementsByTagName("session-factory");
                String DB_server = "";
                String DB_url = "";
                String DB_sid = "";
                String userid = "";
                String userpwd = "";
                for (int temp = 0; temp < e.getLength(); temp++) {
                    Node nNode = e.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        String connection_url = eElement.getElementsByTagName("property").item(2).getTextContent();
                        DB_url = connection_url;
                        if (!utl.isnull(connection_url)) {
                            String split_connection_url[] = connection_url.split("@");
                            if (!utl.isnull(split_connection_url[1])) {
                                String split_data[] = split_connection_url[1].split(":");
//                                //System.out.println("1.." + split_data[0] + "2..." + split_data[1] + "3..." + split_data[2]);
                                DB_server = split_data[0];
                                DB_sid = split_data[2];
                            }
                        }
                        userid = eElement.getElementsByTagName("property").item(3).getTextContent();
                        userpwd = eElement.getElementsByTagName("property").item(4).getTextContent();
                    }
                }//end for
                paramObj.setL_db_server(DB_server);
                paramObj.setL_db_server_id(DB_sid);
                paramObj.setL_db_userid(userid);
                paramObj.setL_db_userpwd(userpwd);
                paramObj.setL_db_url(DB_url);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramObj;

    }//end method

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }//end method
}//end class
