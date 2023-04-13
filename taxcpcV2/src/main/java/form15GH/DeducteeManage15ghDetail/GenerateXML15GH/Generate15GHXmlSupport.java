/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.DeducteeManage15ghDetail.GenerateXML15GH;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import hibernateObjects.ClientMast;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author seema.mourya
 */
public class Generate15GHXmlSupport {
    
     private final Util utl;

    Generate15GHXmlDefaultDtlBean xmlDefaultDetails = null;
    ArrayList<Generate15GHXmlUnMergedPanDtlBean> unMergedPanList = null;
//    private static final int MegaBytes = 1024 * 1024;

    public Generate15GHXmlSupport() {
        utl = new Util();
    }//end constructor

    public int createXML(String path, String entity_code, String client_code, String acc_yr, ArrayList<Generate15GHXmlClientDetails> xmlDetailList, String tanNO, int quarter, String fileChar, ClientMast client, Assessment asmt, String fileName, String source, String destn) {
        int success = 0;

        //long totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
        //long maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
//        try {
//            long freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;
//            System.out.println("**** Heap utilization Analysis [MB] ****");
//            //System.out.println("JVM totalMemory also equals to initial heap size of JVM :" + totalMemory);
//            //System.out.println("JVM maxMemory also equals to maximum heap size of JVM: " + maxMemory);
//            System.out.println("JVM freeMemory: " + freeMemory);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("ns2:FORM15" + fileChar);
            doc.appendChild(rootElement);

            // setting attribute to element
            Attr xmlnsAttr = doc.createAttribute("xmlns");
            xmlnsAttr.setValue("http://incometaxindiaefiling.gov.in/common");
            rootElement.setAttributeNode(xmlnsAttr);

            Attr xmlnsNs2Attr = doc.createAttribute("xmlns:ns2");
            xmlnsNs2Attr.setValue("http://incometaxindiaefiling.gov.in/FORM15" + fileChar);
            rootElement.setAttributeNode(xmlnsNs2Attr);

            //  ns2:FormCreationInfo element
            Element formCreationInfo = doc.createElement("ns2:FormCreationInfo");
            rootElement.appendChild(formCreationInfo);

            //  CreationInfo element
            Element creationInfo = doc.createElement("CreationInfo");
            formCreationInfo.appendChild(creationInfo);

            // SUBTAG for creationInfo : START
            Element sWVersionNo = doc.createElement("SWVersionNo");
            sWVersionNo.appendChild(doc.createTextNode("1.0"));
            creationInfo.appendChild(sWVersionNo);

            Element sWCreatedBy = doc.createElement("SWCreatedBy");
            sWCreatedBy.appendChild(doc.createTextNode("ITD_JAVA_UTILITY"));
            creationInfo.appendChild(sWCreatedBy);

            Element xMLCreatedBy = doc.createElement("XMLCreatedBy");
            xMLCreatedBy.appendChild(doc.createTextNode("ITD_JAVA_UTILITY"));
            creationInfo.appendChild(xMLCreatedBy);

            String sysDate = get_sysdate();
            if (!utl.isnull(sysDate)) {
                Element xMLCreationDate = doc.createElement("XMLCreationDate");
                xMLCreationDate.appendChild(doc.createTextNode(sysDate));
                creationInfo.appendChild(xMLCreationDate);
            }

            //  Form_Details element
            Element form_Details = doc.createElement("Form_Details");
            formCreationInfo.appendChild(form_Details);

            // SUBTAG for Form_Details : START
            Element formName = doc.createElement("FormName");
            formName.appendChild(doc.createTextNode("FORM15" + fileChar));
            form_Details.appendChild(formName);

            if (!utl.isnull(acc_yr)) {
                String account_year[] = acc_yr.split("-");
                String assessmentYrSTR = "20" + account_year[1];
                Element assessmentYear = doc.createElement("AssessmentYear");
                assessmentYear.appendChild(doc.createTextNode(assessmentYrSTR));
                form_Details.appendChild(assessmentYear);
            }

            Element schemaVer = doc.createElement("SchemaVer");
            schemaVer.appendChild(doc.createTextNode("V1.0"));
            form_Details.appendChild(schemaVer);

            Element formVer = doc.createElement("FormVer");
            formVer.appendChild(doc.createTextNode("1.0"));
            form_Details.appendChild(formVer);

            // SUBTAG for Form_Details : END
            //  ns2:Form15GDtls element
            Element form15GDtls = doc.createElement("ns2:Form15" + fileChar + "Dtls");
            rootElement.appendChild(form15GDtls);

            // SUBTAG for ns2:Form15GDtls : START
            if (!utl.isnull(tanNO)) {
                Element tan = doc.createElement("ns2:TAN");
                tan.appendChild(doc.createTextNode(tanNO));
                form15GDtls.appendChild(tan);
            }
            if (quarter > 0) {
                Element quarterElm = doc.createElement("ns2:Quarter");
                quarterElm.appendChild(doc.createTextNode("Q" + quarter));
                form15GDtls.appendChild(quarterElm);
            }
            if (!utl.isnull(acc_yr)) {
                String account_year[] = acc_yr.split("-");
                String financialYrSTR = "20" + account_year[0];
                Element financialYr = doc.createElement("ns2:FinancialYr");
                financialYr.appendChild(doc.createTextNode(financialYrSTR));
                form15GDtls.appendChild(financialYr);
            }
            Element filingType = doc.createElement("ns2:FilingType");
            filingType.appendChild(doc.createTextNode("O"));
            form15GDtls.appendChild(filingType);

            Element part1Dtls = doc.createElement("ns2:Part1Dtls");
            form15GDtls.appendChild(part1Dtls);

            // SUBTAG for ns2:Part1Dtls : START
            // SUBTAG for ns2:Part1 : START
            Element incomeDtls = null;
            if (xmlDetailList != null && !xmlDetailList.isEmpty()) {
                utl.generateLog(null, "iterate list and start making format and put data in that format");
                String l_uniqueIdNumber = "";
                for (Generate15GHXmlClientDetails xmlBasicDetailHeader : xmlDetailList) {
                    //System.out.println("l_uniqueIdNumber..."+l_uniqueIdNumber);
                    if (!utl.isnull(l_uniqueIdNumber) && l_uniqueIdNumber.equalsIgnoreCase(xmlBasicDetailHeader.getUniqueIdNumber())) {
                        //System.out.println("if start");
                        Element incomeDtl = doc.createElement("ns2:IncomeDtl");
                        incomeDtls.appendChild(incomeDtl);
                        if (!utl.isnull(xmlBasicDetailHeader.getSrlNo())) {
                            Element srlNo = doc.createElement("ns2:SrlNo");
                            srlNo.appendChild(doc.createTextNode(xmlBasicDetailHeader.getSrlNo()));
                            incomeDtl.appendChild(srlNo);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getIdenficationNum())) {
                            Element idenficationNum = doc.createElement("ns2:IdenficationNum");
                            idenficationNum.appendChild(doc.createTextNode(xmlBasicDetailHeader.getIdenficationNum()));
                            incomeDtl.appendChild(idenficationNum);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getNatureOfInc())) {
                            Element natureOfInc = doc.createElement("ns2:NatureOfInc");
                            natureOfInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getNatureOfInc()));
                            incomeDtl.appendChild(natureOfInc);
                        }
                        if (!utl.isnull(xmlBasicDetailHeader.getDeductSection())) {
                            Element deductSection = doc.createElement("ns2:DeductSection");
                            deductSection.appendChild(doc.createTextNode(xmlBasicDetailHeader.getDeductSection()));
                            incomeDtl.appendChild(deductSection);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getAmtOfInc())) {
                            Element amtOfInc = doc.createElement("ns2:AmtOfInc");
                            amtOfInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAmtOfInc()));
                            incomeDtl.appendChild(amtOfInc);
                        }

                    } else {
                        // System.out.println("else start");
                        l_uniqueIdNumber = xmlBasicDetailHeader.getUniqueIdNumber();

                        Element part1 = doc.createElement("ns2:Part1");
                        part1Dtls.appendChild(part1);

                        Element basicdtls = doc.createElement("ns2:Basicdtls");
                        part1.appendChild(basicdtls);

                        if (!utl.isnull(xmlBasicDetailHeader.getAssesseeName())) {
                            Element assesseeName = doc.createElement("ns2:AssesseeName");
                            assesseeName.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAssesseeName()));
                            basicdtls.appendChild(assesseeName);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getAssesseePAN())) {
                            Element assesseePAN = doc.createElement("ns2:AssesseePAN");
                            assesseePAN.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAssesseePAN()));
                            basicdtls.appendChild(assesseePAN);
                        }

                        if (fileChar.equalsIgnoreCase("G")) {
                            if (!utl.isnull(xmlBasicDetailHeader.getStatus())) {
                                Element status = doc.createElement("ns2:Status");
                                status.appendChild(doc.createTextNode(xmlBasicDetailHeader.getStatus()));
                                basicdtls.appendChild(status);
                            }
                            if (!utl.isnull(xmlBasicDetailHeader.getResidentialStatus())) {
                                Element residentialStatus = doc.createElement("ns2:ResidentialStatus");
                                residentialStatus.appendChild(doc.createTextNode(xmlBasicDetailHeader.getResidentialStatus()));
                                basicdtls.appendChild(residentialStatus);
                            }
                        } else if (!utl.isnull(xmlBasicDetailHeader.getDOB())) {
                            Element dob = doc.createElement("ns2:AssesseeDOB");
                            dob.appendChild(doc.createTextNode(getDateInYYMMDD(xmlBasicDetailHeader.getDOB())));
                            basicdtls.appendChild(dob);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getPreviousYr())) {
                            Element previousYr = doc.createElement("ns2:PreviousYr");
                            previousYr.appendChild(doc.createTextNode(xmlBasicDetailHeader.getPreviousYr()));
                            basicdtls.appendChild(previousYr);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getFlatDoorNo())) {
                            Element flatDoorNo = doc.createElement("ns2:FlatDoorNo");
                            flatDoorNo.appendChild(doc.createTextNode(xmlBasicDetailHeader.getFlatDoorNo()));
                            basicdtls.appendChild(flatDoorNo);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getPremisesName())) {
                            Element premisesname = doc.createElement("ns2:PremisesName");
                            premisesname.appendChild(doc.createTextNode(xmlBasicDetailHeader.getPremisesName()));
                            basicdtls.appendChild(premisesname);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getRoadOrStreet())) {
                            Element roadorstreet = doc.createElement("ns2:RoadOrStreet");
                            roadorstreet.appendChild(doc.createTextNode(xmlBasicDetailHeader.getRoadOrStreet()));
                            basicdtls.appendChild(roadorstreet);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getLocalityOrArea())) {
                            Element localityOrArea = doc.createElement("ns2:LocalityOrArea");
                            localityOrArea.appendChild(doc.createTextNode(xmlBasicDetailHeader.getLocalityOrArea()));
                            basicdtls.appendChild(localityOrArea);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getCityOrTownOrDistrict())) {
                            Element cityOrTownOrDistrict = doc.createElement("ns2:CityOrTownOrDistrict");
                            cityOrTownOrDistrict.appendChild(doc.createTextNode(xmlBasicDetailHeader.getCityOrTownOrDistrict()));
                            basicdtls.appendChild(cityOrTownOrDistrict);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getStateCode())) {
                            Element stateCode = doc.createElement("ns2:StateCode");
                            if (xmlBasicDetailHeader.getStateCode().length() == 1) {
                                stateCode.appendChild(doc.createTextNode("0" + xmlBasicDetailHeader.getStateCode()));
                            } else {
                                stateCode.appendChild(doc.createTextNode(xmlBasicDetailHeader.getStateCode()));
                            }
                            basicdtls.appendChild(stateCode);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getPinCode())) {
                            Element pinCode = doc.createElement("ns2:PinCode");
                            pinCode.appendChild(doc.createTextNode(xmlBasicDetailHeader.getPinCode()));
                            basicdtls.appendChild(pinCode);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getEmailAddress())) {
                            Element emailAddress = doc.createElement("ns2:EmailAddress");
                            emailAddress.appendChild(doc.createTextNode(xmlBasicDetailHeader.getEmailAddress()));
                            basicdtls.appendChild(emailAddress);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getSTDcode()) && !utl.isnull(xmlBasicDetailHeader.getPhoneNo())) {
                            Element sTDcode = doc.createElement("ns2:STDcode");
                            sTDcode.appendChild(doc.createTextNode(xmlBasicDetailHeader.getSTDcode()));
                            basicdtls.appendChild(sTDcode);

                            Element phoneNo = doc.createElement("ns2:PhoneNo");
                            phoneNo.appendChild(doc.createTextNode(xmlBasicDetailHeader.getPhoneNo()));
                            basicdtls.appendChild(phoneNo);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getMobileNo())) {
                            Element mobileNo = doc.createElement("ns2:MobileNo");
                            mobileNo.appendChild(doc.createTextNode(xmlBasicDetailHeader.getMobileNo()));
                            basicdtls.appendChild(mobileNo);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getTaxAssessedFlag())) {
                            Element taxAssessedFlag = doc.createElement("ns2:TaxAssessedFlag");
                            taxAssessedFlag.appendChild(doc.createTextNode(xmlBasicDetailHeader.getTaxAssessedFlag()));
                            basicdtls.appendChild(taxAssessedFlag);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getTaxAssessedFlag()) && xmlBasicDetailHeader.getTaxAssessedFlag().equalsIgnoreCase("Y")) {
                            Element latestAsstYr = doc.createElement("ns2:LatestAsstYr");
                            latestAsstYr.appendChild(doc.createTextNode(xmlBasicDetailHeader.getLatestAsstYr()));
                            basicdtls.appendChild(latestAsstYr);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getEstimatedInc())) {
                            Element estimatedInc = doc.createElement("ns2:EstimatedInc");
                            estimatedInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getEstimatedInc()));
                            basicdtls.appendChild(estimatedInc);
                        } else if (!utl.isnull(xmlBasicDetailHeader.getAmtOfIncPaid())) {
                            Element estimatedInc = doc.createElement("ns2:EstimatedInc");
                            estimatedInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAmtOfIncPaid()));
                            basicdtls.appendChild(estimatedInc);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getEstimatedTotalIncPrvYr())) {
                            Element estimatedTotalIncPrvYr = doc.createElement("ns2:EstimatedTotalIncPrvYr");
                            estimatedTotalIncPrvYr.appendChild(doc.createTextNode(xmlBasicDetailHeader.getEstimatedTotalIncPrvYr()));
                            basicdtls.appendChild(estimatedTotalIncPrvYr);
                        }

                        String l_fileChar = "";
                        if (!utl.isnull(fileChar) && fileChar.equalsIgnoreCase("H")) {
                            l_fileChar = fileChar.toUpperCase();
                        } else {
                            l_fileChar = fileChar.toLowerCase();
                        }
                        if (!utl.isnull(xmlBasicDetailHeader.getTotalNoOfForm15g())) {
                            Element totalNoOfForm15g = doc.createElement("ns2:TotalNoOfForm15" + (l_fileChar));
                            totalNoOfForm15g.appendChild(doc.createTextNode(xmlBasicDetailHeader.getTotalNoOfForm15g()));
                            basicdtls.appendChild(totalNoOfForm15g);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getAggregateAmtForm15g())) {
                            Element aggregateAmtForm15g = doc.createElement("ns2:AggregateAmtForm15" + (l_fileChar));
                            aggregateAmtForm15g.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAggregateAmtForm15g()));
                            basicdtls.appendChild(aggregateAmtForm15g);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getUniqueIdNumber())) {
                            Element uniqueIdNumber = doc.createElement("ns2:UniqueIdNumber");
                            uniqueIdNumber.appendChild(doc.createTextNode(xmlBasicDetailHeader.getUniqueIdNumber()));
                            basicdtls.appendChild(uniqueIdNumber);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getDeclarationDate())) {
                            Element declarationDate = doc.createElement("ns2:DeclarationDate");
                            declarationDate.appendChild(doc.createTextNode(getDateInYYMMDD(xmlBasicDetailHeader.getDeclarationDate())));
                            basicdtls.appendChild(declarationDate);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getAmtOfIncPaid())) {
                            Element amtOfIncPaid = doc.createElement("ns2:AmtOfIncPaid");
                            amtOfIncPaid.appendChild(doc.createTextNode(utl.isnull(xmlBasicDetailHeader.getAmtOfIncPaid()) ? "0" : xmlBasicDetailHeader.getAmtOfIncPaid()));
                            basicdtls.appendChild(amtOfIncPaid);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getDateIncPaid())) {
                            Element dateIncPaid = doc.createElement("ns2:DateIncPaid");
                            dateIncPaid.appendChild(doc.createTextNode(getDateInYYMMDD(xmlBasicDetailHeader.getDateIncPaid())));
                            basicdtls.appendChild(dateIncPaid);
                        }

//                        --------------new inner body start------------------
                        incomeDtls = doc.createElement("ns2:IncomeDtls");
                        part1.appendChild(incomeDtls);
                        Element incomeDtl = doc.createElement("ns2:IncomeDtl");
                        incomeDtls.appendChild(incomeDtl);
                        if (!utl.isnull(xmlBasicDetailHeader.getSrlNo())) {
                            Element srlNo = doc.createElement("ns2:SrlNo");
                            srlNo.appendChild(doc.createTextNode(xmlBasicDetailHeader.getSrlNo()));
                            incomeDtl.appendChild(srlNo);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getIdenficationNum())) {
                            Element idenficationNum = doc.createElement("ns2:IdenficationNum");
                            idenficationNum.appendChild(doc.createTextNode(xmlBasicDetailHeader.getIdenficationNum()));
                            incomeDtl.appendChild(idenficationNum);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getNatureOfInc())) {
                            Element natureOfInc = doc.createElement("ns2:NatureOfInc");
                            natureOfInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getNatureOfInc()));
                            incomeDtl.appendChild(natureOfInc);
                        }
                        if (!utl.isnull(xmlBasicDetailHeader.getDeductSection())) {
                            Element deductSection = doc.createElement("ns2:DeductSection");
                            deductSection.appendChild(doc.createTextNode(xmlBasicDetailHeader.getDeductSection()));
                            incomeDtl.appendChild(deductSection);
                        }

                        if (!utl.isnull(xmlBasicDetailHeader.getAmtOfInc())) {
                            Element amtOfInc = doc.createElement("ns2:AmtOfInc");
                            amtOfInc.appendChild(doc.createTextNode(xmlBasicDetailHeader.getAmtOfInc()));
                            incomeDtl.appendChild(amtOfInc);
                        }

//                        --------------new inner body end------------------
                    }

                    //put code here
                }//end main for loop
            }//end main if condition

            //long totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
            //long maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
//            try {
//                long freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;
//                System.out.println("**** Heap utilization Analysis [MB] ****");
//        //System.out.println("JVM totalMemory also equals to initial heap size of JVM :" + totalMemory);
//                //System.out.println("JVM maxMemory also equals to maximum heap size of JVM: " + maxMemory);
//                System.out.println("JVM freeMemory: " + freeMemory);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                System.out.println("Garbage Collection Start");
//                System.gc();
//                System.out.println("Garbage Collection End");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            // SUBTAG for ns2:Part1 : END
            // SUBTAG for ns2:Part1Dtls : END
            // SUBTAG for ns2:Form15GDtls : END
            // write the content into xml file
            utl.generateLog(null, "start making tramsformerfactory object");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();// Create transformer factory
            Transformer transformer = transformerFactory.newTransformer();// Use the transformer factory to create a transformer
            DOMSource dmsource = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");//property to set standard of xml 
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");//property to set standard of xml 
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");//property to set standard of xml 
            // example <?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
            utl.generateLog(null, "make xml file & writr data");
            transformer.transform(dmsource, result);//create xml and write data 

            // Output to console for testing
//            StreamResult consoleResult = new StreamResult(System.out);
//            transformer.transform(source1, consoleResult);
            try {
                ZipFileUtil zp = new ZipFileUtil();
                zp.ZipIt(fileName, source, destn);
                File dir = new File(path);
                dir.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            utl.generateLog(null, "Garbage Collection Start");
//            System.gc();
//            utl.generateLog(null, "Garbage Collection End");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        success = 1;
        return success;
    }// end method

    public String getDateInYYMMDD(String dateStr) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(dateStr);
            dateStr = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public void createDirectory(String filePath) {
        File fileLoc = null;
        if (!utl.isnull(filePath)) {
            fileLoc = new File(filePath);
            if (!fileLoc.exists()) {
                fileLoc.mkdir();
            }
        }
    }// end method

    public boolean deleteBulkZip(String filePath, String fileName) {
//        System.out.println("filePath--" + filePath);
//        System.out.println("fileName--" + fileName);
        boolean fileDeleted = true;
        File fileLoc = null;
        try {
            if (!utl.isnull(filePath)) {
                fileLoc = new File(filePath);
                if (!fileLoc.exists()) {
                    File[] listFiles = fileLoc.listFiles();
                    for (File file : listFiles) {
                        if (file.getName().equalsIgnoreCase(fileName)) {
                            file.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileDeleted = false;
        }
        return fileDeleted;
    }// end method

    ArrayList<Generate15GHXmlClientDetails> dataList1 = new ArrayList<Generate15GHXmlClientDetails>();

    public ArrayList<Generate15GHXmlClientDetails> execute_oracle_query_as_bean_object(final String entity_code, final String client_code, final String acc_year, final String quarterNo, final String tds_type_code, final String ghchartype, final String tannoVal) {
        Session ssn = getSessionFactory().openSession();
        try {

            final String charType = getCharType(ghchartype);

            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        String query
                                = "select *\n"
                                + "  from (select distinct\n"
                                + "               a.AssesseeName,\n"
                                + "               a.AssesseePAN,\n"
                                + "               a.Status,\n"
                                + "               a.ResidentialStatus,\n"
                                + "               a.PreviousYr,\n"
                                + "               a.FlatDoorNo,\n"
                                + "               a.PremisesName,\n"
                                + "               a.RoadOrStreet,\n"
                                + "               a.LocalityOrArea,\n"
                                + "               a.CityOrTownOrDistrict,\n"
                                + "               a.StateCode,\n"
                                + "               a.PinCode,\n"
                                + "               a.EmailAddress,\n"
                                + "               a.STDcode,\n"
                                + "               a.PhoneNo,\n"
                                + "               a.MobileNo,\n"
                                + "               a.TaxAssessedFlag,\n"
                                + "               a.LatestAsstYr,\n"
                                + "               nvl(round(a.EstimatedInc), '') EstimatedInc,\n"
                                + "               nvl(round(a.EstimatedTotalIncPrvYr), '') EstimatedTotalIncPrvYr,\n"
                                + "               a.TotalNoOfForm15g,\n"
                                + "               nvl(round(a.AggregateAmtForm15g), '') AggregateAmtForm15g,\n"
                                + "               a.UniqueIdNumber,\n"
                                + "               a.DeclarationDate,\n"
                                + "               nvl(round(a.AmtOfIncPaid), '') AmtOfIncPaid,\n"
                                + "               a.DateIncPaid,\n"
                                + "               a.SrlNo,\n"
                                + "               a.IdenficationNum,\n"
                                + "               a.NatureOfInc,\n"
                                + "               a.DeductSection,\n"
                                + "               nvl(floor(a.AmtOfInc), '') AmtOfInc,\n"
                                + "               a.ENTITY_CODE,\n"
                                + "               a.CLIENT_CODE,\n"
                                + "               a.DEDUCTEE_CODE,\n"
                                + "               a.ACC_YEAR,\n"
                                + "               a.PANNO,\n"
                                + "               a.DOB\n"
                                + "        from   (select rownum srlno,\n"
                                + "                       a.deductee_name assesseename,\n"
                                + "                       a.panno assesseepan,\n"
                                + "                       a.pan_status status,\n"
                                + "                       (select vr1.bflag_code\n"
                                + "                          from view_resident_type vr1\n"
                                + "                         where vr1.resdent_type_code = a.resident_status) residentialstatus,\n"
                                + "                       '20' || substr(b.acc_year, 1, 2) previousyr,\n"
                                + "                       a.add1 flatdoorno,\n"
                                + "                       a.add2 premisesname,\n"
                                + "                       a.add3 roadorstreet,\n"
                                + "                       a.add4 localityorarea,\n"
                                + "                       a.city_code cityortownordistrict,\n"
                                + "                       a.state_code statecode,\n"
                                + "                       a.pin pincode,\n"
                                + "                       a.email_id emailaddress,\n"
                                + "                       a.stdcode stdcode,\n"
                                + "                       a.phoneno phoneno,\n"
                                + "                       a.mobileno mobileno,\n"
                                + "                       a.assessed_tax_flag taxassessedflag,\n"
                                + "                       decode(a.latest_assessment_year,\n"
                                + "                              null,\n"
                                + "                              null,\n"
                                + "                              '20' || substr(a.latest_assessment_year, 1, 2)) latestasstyr,\n"
                                + "                       a.est_declaration_income estimatedinc,\n"
                                + "                       a.est_total_income estimatedtotalincprvyr,\n"
                                + "                       a.total_no_form totalnoofform15g,\n"
                                + "                       a.aggregate_income_amt aggregateamtform15g,\n"
                                + "                       a.reference_no || '20' || replace(a.acc_year, '-', '') || '" + tannoVal + "'  uniqueidnumber,\n"
                                + "                       a.declaration_date declarationdate,\n"
                                + "                       a.income_amount_paid amtofincpaid,\n"
                                + "                       a.income_paid_date dateincpaid,\n"
                                + "                       b.account_no idenficationnum,\n"
                                + "                       b.nature_of_income natureofinc,\n"
                                + "                       b.section deductsection,\n"
                                + "                       b.amount amtofinc,\n"
                                + "                       a.entity_code,\n"
                                + "                       a.client_code,\n"
                                + "                       a.deductee_code,\n"
                                + "                       a.acc_year,\n"
                                + "                       a.panno,\n"
                                + "                       a.dob\n"
                                + "                  from deductee_mast_15gh a, deductee_bflag_amt_tran b,\n"
                                + "                       client_mast c\n"
                                + "                  where a.entity_code=b.entity_code\n"
                                + "                  and   a.client_code=b.client_code\n"
                                + "                  and   a.acc_year=b.acc_year\n"
                                + "                  and   a.quarter_no=b.quarter_no\n"
                                + "                  and   a.tds_type_code=b.tds_type_code\n"
                                + "                  and   a.rowid_seq=b.deductee_mast_15gh_rowid_seq\n"
                                + "                  and   a.entity_code=c.entity_code\n"
                                + "                  and   a.client_code=c.client_code\n"
                                + "                  and   a.entity_code='" + entity_code + "'\n"
                                + "                  and   a.acc_year='" + acc_year + "'\n"
                                + "                  and   a.quarter_no='" + quarterNo + "'\n"
                                + "                  and   a.tds_type_code='" + tds_type_code + "'\n"
                                + "                  and   a.bflag='" + charType + "'\n"
                                + "                  and   (c.client_code='" + client_code + "' or\n"
                                + "                         c.parent_code='" + client_code + "' or\n"
                                + "                         c.g_parent_code='" + client_code + "' or\n"
                                + "                         c.sg_parent_code='" + client_code + "' or\n"
                                + "                         c.ssg_parent_code='" + client_code + "' or\n"
                                + "                         c.sssg_parent_code='" + client_code + "')\n"
                                + "                  and    not exists (select rowid_seq\n"
                                + "                                     from   tran_load_error_15gh x\n"
//                                + "                                     where  x.entity_code=a.entity_code\n"
//                                + "                                     and    x.client_code=a.client_code\n"
//                                + "                                     and    x.acc_year=a.acc_year\n"
//                                + "                                     and    x.quarter_no=a.quarter_no\n"
//                                + "                                     and    x.tds_type_code=a.tds_type_code\n"
//                                + "                                     and    x.deductee_code=a.deductee_code\n"
                                + "                                     where    x.rowid_seq=a.rowid_seq\n"
                                + "                                     and     nvl(x.error_review_flag,'N')='N')\n"
                                + "               ) a\n"
                                + "        ) a\n"
                                + " ORDER BY UniqueIdNumber";
                        utl.generateSpecialLog("15GH-GX-0002 (Generate 15GH XML  Query)----", query);
                        pstmt = cnctn.prepareStatement(query);
                        pstmt.setFetchSize(5000);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            boolean record_fetched = true;
                            Generate15GHXmlClientDetails grid_data = new Generate15GHXmlClientDetails();
                            try {
                                Field[] tim_tbl_flds = grid_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    String fld_name = fld.getName();
                                    String fld_value = rs.getString(fld_name);
                                    fld_value = utl.isnull(fld_value) ? "" : fld_value;
                                    fld.set(grid_data, fld_value);
                                }//end for
                            } catch (SecurityException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (SQLException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            }
                            if (record_fetched) {
                                dataList1.add(grid_data);
                            }
                        }
                    } catch (SQLException ex) {
                        dataList1 = null;
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
            ssn.doWork(work);
        } catch (Exception e) {
            dataList1 = null;
            e.printStackTrace();
            //System.out.println("Error in Getting Query Data");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        try {
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList1;
    }//end method

    public String getParentClientCode(String entity_code, String client_code, String acc_year) {
        String l_return_value = "";
        try {
            DbFunctionExecutorAsString ef = new DbFunctionExecutorAsString();
            String l_Query = "select lhs_utility.is_client_bflag_gen_level_code('" + entity_code + "','" + client_code + "','" + acc_year + "') from dual";
            String l_client_code = ef.execute_oracle_function_as_string(l_Query);
            utl.generateSpecialLog("parent code ", l_client_code);
            l_return_value = l_client_code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_value;
    }//end method

    public String stringMaker(String entity_code, String client_code, String acc_year, String from_date, String to_date, String isBranchLevel) {
        String l_return_query = "";

        String l_parent_client_code = "";
        try {
            l_parent_client_code = getParentClientCode(entity_code, client_code, acc_year);
        } catch (Exception e) {
        }

        try {
            if (!utl.isnull(isBranchLevel) && isBranchLevel.equalsIgnoreCase("T")) {
                l_return_query
                        = "SELECT A.PANNO, COUNT(*) + 1 COUNT\n"
                        + "  FROM (select T.PANNO,\n"
                        + "               T.DEDUCTEE_CODE,\n"
                        + "               (select RANK() OVER(PARTITION BY PANNO ORDER BY DEDUCTEE_CODE)\n"
                        + "                  from dual) SLNO\n"
                        + "          from (select D.DEDUCTEE_NAME AssesseeName,\n"
                        + "                       B.PANNO AssesseePAN,\n"
                        + "                       B.PAN_STATUS Status,\n"
                        + "                       (SELECT VR1.bflag_code\n"
                        + "                          FROM VIEW_RESIDENT_TYPE VR1\n"
                        + "                         WHERE VR1.resdent_type_code = D.RESIDENT_STATUS) ResidentialStatus,\n"
                        + "                       '20' || SUBSTR(T.ACC_YEAR, 1, 2) PreviousYr,\n"
                        + "                       D.ADD1 FlatDoorNo,\n"
                        + "                       '' PremisesName,\n"
                        + "                       '' RoadOrStreet,\n"
                        + "                       D.ADD2 LocalityOrArea,\n"
                        + "                       D.CITY_CODE CityOrTownOrDistrict,\n"
                        + "                       D.STATE_CODE StateCode,\n"
                        + "                       D.PIN PinCode,\n"
                        + "                       D.EMAIL_ID EmailAddress,\n"
                        + "                       D.STDCODE STDcode,\n"
                        + "                       D.PHONENO PhoneNo,\n"
                        + "                       D.MOBILENO MobileNo,\n"
                        + "                       B.ASSESSED_TAX_FLAG TaxAssessedFlag,\n"
                        + "                       DECODE(LATEST_ASSESSMENT_YEAR,\n"
                        + "                              NULL,\n"
                        + "                              NULL,\n"
                        + "                              '20' || SUBSTR(B.LATEST_ASSESSMENT_YEAR, 1, 2)) LatestAsstYr,\n"
                        + "                       B.est_declaration_income EstimatedInc,\n"
                        + "                       B.EST_TOTAL_INCOME EstimatedTotalIncPrvYr,\n"
                        + "                       B.TOTAL_NO_FORM TotalNoOfForm15g,\n"
                        + "                       B.AGGREGATE_INCOME_AMT AggregateAmtForm15g,\n"
                        + "                       B.REFERENCE_NO || '20' || REPLACE(B.ACC_YEAR, '-', '') ||\n"
                        + "                       (SELECT C1.TANNO\n"
                        + "                          FROM CLIENT_MAST C1\n"
                        + "                         WHERE C1.CLIENT_CODE = D.CLIENT_CODE) UniqueIdNumber,\n"
                        + "                       B.DECLARATION_DATE DeclarationDate,\n"
                        + "                       B.INCOME_AMOUNT_PAID AmtOfIncPaid,\n"
                        + "                       B.INCOME_PAID_DATE DateIncPaid,\n"
                        + "                       ROWNUM SrlNo,\n"
                        + "                       T.ACCOUNT_NO IdenficationNum,\n"
                        + "                       T.NATURE_OF_INCOME NatureOfInc,\n"
                        + "                       T.SECTION DeductSection,\n"
                        + "                       T.AMOUNT AmtOfInc,\n"
                        + "                       B.ENTITY_CODE,\n"
                        + "                       B.CLIENT_CODE,\n"
                        + "                       D.DEDUCTEE_CODE,\n"
                        + "                       B.ACC_YEAR,\n"
                        + "                       B.SLNO,\n"
                        + "                       T.PANNO,\n"
                        + "                       D.DOB\n"
                        + "                  FROM (SELECT B2.*, ROWNUM SLNO\n"
                        + "                          FROM (SELECT *\n"
                        + "                                  FROM DEDUCTEE_BFLAG_REFINFO_TRAN B1\n"
                        + "                                 WHERE B1.ENTITY_CODE = '" + entity_code + "'\n"
                        + "                                   AND B1.ACC_YEAR = '" + acc_year + "'\n"
                        + "                                   AND B1.CLIENT_CODE = '" + client_code + "'\n"
                        + "                                   AND (declaration_date IS NULL OR\n"
                        + "                                       declaration_date BETWEEN\n"
                        + "                                       to_date('" + from_date + "', 'dd-mm-rrrr') AND\n"
                        + "                                       to_date('" + to_date + "', 'dd-mm-rrrr'))) B2) B,\n"
                        + "                       DEDUCTEE_BFLAG_AMT_TRAN T,\n"
                        + "                       DEDUCTEE_MAST D\n"
                        + "                 WHERE B.REFERENCE_NO = T.REFERENCE_NO\n"
                        + "                   AND B.ACC_YEAR = T.ACC_YEAR\n"
                        + "                   AND D.PARENT_CODE IS NULL\n"
                        + "\n"
                        + "                   and B.ENTITY_CODE = T.ENTITY_CODE\n"
                        + "                   AND D.ENTITY_CODE = B.ENTITY_CODE\n"
                        + "                   and t.entity_code = d.entity_code\n"
                        + "\n"
                        + "                   and t.client_code = d.client_code\n"
                        + "                   and B.client_code = d.client_code\n"
                        + "                   and t.client_code = b.client_code\n"
                        + "\n"
                        + "                   and d.PANNO = T.PANNO\n"
                        + "                   AND D.PANNO = B.PANNO\n"
                        + "                   AND B.PANNO = T.PANNO\n"
                        + "\n"
                        + "                   AND d.client_code = '" + client_code + "'\n"
                        + "\n"
                        + "                 AND D.ENTITY_CODE = '" + entity_code + "'\n"
                        + "                ) t\n"
                        + "         GROUP BY T.PANNO, T.DEDUCTEE_CODE) A\n"
                        + " WHERE A.SLNO > 1\n"
                        + " GROUP BY A.PANNO";

            } else {
                l_return_query = "SELECT PANNO, COUNT(*) + 1 COUNT\n"
                        + "                  FROM (select T.PANNO,\n"
                        + "                               T.DEDUCTEE_CODE,\n"
                        + "                               RANK() OVER(PARTITION BY PANNO ORDER BY DEDUCTEE_CODE) SLNO\n"
                        + "                          from (\n"
                        + "                select D.DEDUCTEE_NAME AssesseeName,\n"
                        + "                               B.PANNO AssesseePAN,\n"
                        + "                               B.PAN_STATUS Status,\n"
                        + "                               (SELECT VR1.bflag_code\n"
                        + "                                  FROM VIEW_RESIDENT_TYPE VR1\n"
                        + "                                 WHERE VR1.resdent_type_code = D.RESIDENT_STATUS) ResidentialStatus,\n"
                        + "                               '20' || SUBSTR(T.ACC_YEAR, 1, 2) PreviousYr,\n"
                        + "                               D.ADD1 FlatDoorNo,\n"
                        + "                               '' PremisesName,\n"
                        + "                               '' RoadOrStreet,\n"
                        + "                               D.ADD2 LocalityOrArea,\n"
                        + "                               D.CITY_CODE CityOrTownOrDistrict,\n"
                        + "                               D.STATE_CODE StateCode,\n"
                        + "                               D.PIN PinCode,\n"
                        + "                               D.EMAIL_ID EmailAddress,\n"
                        + "                               D.STDCODE STDcode,\n"
                        + "                               D.PHONENO PhoneNo,\n"
                        + "                               D.MOBILENO MobileNo,\n"
                        + "                               B.ASSESSED_TAX_FLAG TaxAssessedFlag,\n"
                        + "                               DECODE(LATEST_ASSESSMENT_YEAR,\n"
                        + "                                      NULL,\n"
                        + "                                      NULL,\n"
                        + "                                      '20' || SUBSTR(B.LATEST_ASSESSMENT_YEAR, 1, 2)) LatestAsstYr,\n"
                        + "                               B.est_declaration_income EstimatedInc,\n"
                        + "                               B.EST_TOTAL_INCOME EstimatedTotalIncPrvYr,\n"
                        + "                               B.TOTAL_NO_FORM TotalNoOfForm15g,\n"
                        + "                               B.AGGREGATE_INCOME_AMT AggregateAmtForm15g,\n"
                        + "                               B.REFERENCE_NO || '20' || REPLACE(B.ACC_YEAR, '-', '') ||\n"
                        + "                               (SELECT C1.TANNO\n"
                        + "                                  FROM CLIENT_MAST C1\n"
                        + "                                 WHERE C1.CLIENT_CODE = D.CLIENT_CODE) UniqueIdNumber,\n"
                        + "                               B.DECLARATION_DATE DeclarationDate,\n"
                        + "                               B.INCOME_AMOUNT_PAID AmtOfIncPaid,\n"
                        + "                               B.INCOME_PAID_DATE DateIncPaid,\n"
                        + "                               ROWNUM SrlNo,\n"
                        + "                               T.ACCOUNT_NO IdenficationNum,\n"
                        + "                               T.NATURE_OF_INCOME NatureOfInc,\n"
                        + "                               T.SECTION DeductSection,\n"
                        + "                               T.AMOUNT AmtOfInc,\n"
                        + "                               B.ENTITY_CODE,\n"
                        + "                               B.CLIENT_CODE,\n"
                        + "                               D.DEDUCTEE_CODE,\n"
                        + "                               B.ACC_YEAR,\n"
                        + "                               B.SLNO,\n"
                        + "                               T.PANNO,\n"
                        + "                               D.DOB\n"
                        + "                              FROM\n"
                        + "(SELECT *\n"
                        + "                                  FROM ((SELECT D1.*,\n"
                        //                        + "              ( select   RANK() OVER(PARTITION BY D1.PANNO ORDER BY D1.DEDUCTEE_CODE) from dual ) RANK_LEVEL\n"
                        + "               RANK() OVER(PARTITION BY D1.PANNO ORDER BY D1.DEDUCTEE_CODE) RANK_LEVEL\n"
                        + "                                           FROM DEDUCTEE_MAST D1\n"
                        + "                                          WHERE D1.CLIENT_CODE IN\n"
                        + "                                                (SELECT CLIENT_CODE\n"
                        + "                                                   FROM CLIENT_MAST A\n"
                        + "                                                  START WITH A.CLIENT_CODE = '" + client_code + "'\n"
                        + "                                                 CONNECT BY PRIOR CLIENT_CODE =\n"
                        + "                                                             parent_COdE)))\n"
                        + "                                 WHERE RANK_LEVEL = 1) D,\n"
                        + "                               (SELECT B2.*, ROWNUM SLNO\n"
                        + "                                  FROM (SELECT *\n"
                        + "                                          FROM DEDUCTEE_BFLAG_REFINFO_TRAN B1\n"
                        + "                                         WHERE B1.ENTITY_CODE = '" + entity_code + "'\n"
                        + "                                           AND B1.ACC_YEAR = '" + acc_year + "'\n"
                        + "                                           AND B1.CLIENT_CODE = '" + l_parent_client_code + "'\n"
                        //                        + "                                         (select 1\n"
                        //                        + "                                                  from dual\n"
                        //                        + "                                                 where lhs_utility.is_client_bflag_gen_level_code('" + entity_code + "',\n"
                        //                        + "                                                                                                  '" + client_code + "',\n"
                        //                        + "                                                                                                  '" + acc_year + "') =\n"
                        //                        + "                                                       B1.CLIENT_CODE)\n"
                        + "                                           AND (declaration_date IS NULL OR\n"
                        + "                                               declaration_date BETWEEN\n"
                        + "                                               to_date('" + from_date + "',\n"
                        + "                                                        'dd-mm-rrrr') AND\n"
                        + "                                               to_date('" + to_date + "',\n"
                        + "                                                        'dd-mm-rrrr'))\n"
                        + "                                        ) B2) B   , DEDUCTEE_BFLAG_AMT_TRAN T\n"
                        + "                         WHERE B.ENTITY_CODE = T.ENTITY_CODE\n"
                        + "                           AND B.CLIENT_CODE = T.CLIENT_CODE\n"
                        + "                           AND B.ACC_YEAR = T.ACC_YEAR\n"
                        + "                           AND B.PANNO = T.PANNO\n"
                        + "                           AND B.REFERENCE_NO = T.REFERENCE_NO\n"
                        + "                           AND D.ENTITY_CODE = B.ENTITY_CODE\n"
                        + "                           and d.PANNO = T.PANNO\n"
                        + "\n"
                        // + "                           and t.client_code = d.client_code\n"
                        + "                           and t.client_code = b.client_code\n"
                        + "\n"
                        + "                           AND exists\n"
                        + "                         (select 1\n"
                        + "                                  from (SELECT CM.CLIENT_CODE\n"
                        + "                                          FROM CLIENT_MAST CM\n"
                        + "                                         WHERE CM.CLIENT_CODE = '" + client_code + "'\n"
                        + "                                            OR CM.PARENT_CODE = '" + client_code + "'\n"
                        + "                                            OR CM.G_PARENT_CODE = '" + client_code + "'\n"
                        + "                                            OR CM.SG_PARENT_CODE = '" + client_code + "'\n"
                        + "                                            OR CM.SSG_PARENT_CODE = '" + client_code + "'\n"
                        + "                                            OR CM.SSSG_PARENT_CODE = '" + client_code + "') c1\n"
                        + "                                 where c1.client_code = D.CLIENT_CODE)\n"
                        + "                                AND D.PANNO = B.PANNO\n"
                        + "                           AND D.PARENT_CODE IS NULL\n"
                        //                        + " and EXISTS(SELECT 1 FROM DUAL WHERE t.client_code = lhs_utility.is_client_bflag_gen_level_code('" + entity_code + "','" + client_code + "','" + acc_year + "'))\n"
                        + " and t.client_code = '" + l_parent_client_code + "'\n"
                        + "                           AND EXISTS (SELECT 1 FROM DUAL WHERE D.ENTITY_CODE = '" + entity_code + "'\n"
                        + "\n"
                        + "                      ) ) t\n"
                        + "                         GROUP BY T.PANNO, T.DEDUCTEE_CODE)\n"
                        + "                 WHERE SLNO > 1\n"
                        + "                 GROUP BY PANNO";
            }
        } catch (Exception e) {
        }
        return l_return_query;
    }//end method

    public ArrayList<Generate15GHXmlUnMergedPanDtlBean> get_unMergedPanList(String entity_code, String client_code, String acc_year, String from_date, String to_date, String isBranchLevel) throws SQLException {

        final String function_name_para_query = stringMaker(entity_code, client_code, acc_year, from_date, to_date, isBranchLevel);

        //System.out.println("function_name_para_query..." + function_name_para_query);
        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet resultSet = null;
                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        //                        pstmt.setFetchSize(500);
                        resultSet = pstmt.executeQuery();
                        if (resultSet != null && resultSet.next()) {
                            unMergedPanList = new ArrayList<Generate15GHXmlUnMergedPanDtlBean>();
                            do {
                                Generate15GHXmlUnMergedPanDtlBean unMergedPanDetails = new Generate15GHXmlUnMergedPanDtlBean();

                                unMergedPanDetails.setPanno(resultSet.getString("PANNO"));
                                unMergedPanDetails.setTotalRepeated(resultSet.getString("COUNT"));

                                unMergedPanList.add(unMergedPanDetails);

                            } while (resultSet.next());
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (resultSet != null) {
                            resultSet.close();
                        }
                    }
                }
            };
            ssn.doWork(work);
        } catch (Exception e) {
            //System.out.println("Error in Executing Sequence");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return unMergedPanList;
    }//end method

    public static String get_sysdate() {
        String today = "";
        try {
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            today = formatter.format(currentDate.getTime());
        } catch (Exception ex) {
            today = "";
        }
        return today;
    }// end get_sysdate

    public Generate15GHXmlDefaultDtlBean get_default_Xml_details() throws SQLException {

        final String function_name_para_query = "select UNIQUEIDNUMBER,\n"
                + "ASSESSEENAME,ASSESSEEPAN,STATUS,RESIDENTIALSTATUS,PREVIOUSYR,FLATDOORNO,PREMISESNAME,\n"
                + "ROADORSTREET,LOCALITYORAREA,CITYORTOWNORDISTRICT,STATECODE,PINCODE,EMAILADDRESS,\n"
                + "STDCODE,PHONENO,MOBILENO,TAXASSESSEDFLAG,LATESTASSTYR,ESTIMATEDINC,ESTIMATEDTOTALINCPRVYR,\n"
                + "TOTALNOOFFORM15G,AGGREGATEAMTFORM15G,DECLARATIONDATE,AMTOFINCPAID,DATEINCPAID,IDENFICATIONNUM,\n"
                + "NATUREOFINC,DEDUCTSECTION,AMTOFINC, DOB \n"
                + "from view_bf15gh_default_set_value";

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet default_resultSet = null;
                    try {
                        pstmt = cnctn.prepareStatement(function_name_para_query);
                        default_resultSet = pstmt.executeQuery();
                        if (default_resultSet != null && default_resultSet.next()) {
                            do {
                                xmlDefaultDetails = new Generate15GHXmlDefaultDtlBean();

                                xmlDefaultDetails.setUniqueIdNumber(default_resultSet.getString("UNIQUEIDNUMBER"));
                                xmlDefaultDetails.setAssessee_name(default_resultSet.getString("ASSESSEENAME"));
                                xmlDefaultDetails.setAssessee_pan(default_resultSet.getString("ASSESSEEPAN"));
                                xmlDefaultDetails.setStatus(default_resultSet.getString("STATUS"));
                                xmlDefaultDetails.setResidential_status(default_resultSet.getString("RESIDENTIALSTATUS"));
                                xmlDefaultDetails.setPrevious_yr(default_resultSet.getString("PREVIOUSYR"));
                                xmlDefaultDetails.setFlatdoor_no(default_resultSet.getString("FLATDOORNO"));
                                xmlDefaultDetails.setPremissess_name(default_resultSet.getString("PREMISESNAME"));
                                xmlDefaultDetails.setRoadorstreet(default_resultSet.getString("ROADORSTREET"));
                                xmlDefaultDetails.setLocalityorarea(default_resultSet.getString("LOCALITYORAREA"));
                                xmlDefaultDetails.setCityortownordistict(default_resultSet.getString("CITYORTOWNORDISTRICT"));
                                xmlDefaultDetails.setStatecode(default_resultSet.getString("STATECODE"));
                                xmlDefaultDetails.setPincode(default_resultSet.getString("PINCODE"));
                                xmlDefaultDetails.setEmailaddress(default_resultSet.getString("EMAILADDRESS"));
                                xmlDefaultDetails.setStdcode(default_resultSet.getString("STDCODE"));
                                xmlDefaultDetails.setPhoneno(default_resultSet.getString("PHONENO"));
                                xmlDefaultDetails.setMobileno(default_resultSet.getString("MOBILENO"));
                                xmlDefaultDetails.setTaxassessedflag(default_resultSet.getString("TAXASSESSEDFLAG"));
                                xmlDefaultDetails.setLatestasstyr(default_resultSet.getString("LATESTASSTYR"));
                                xmlDefaultDetails.setEstimatedinc(default_resultSet.getString("ESTIMATEDINC"));
                                xmlDefaultDetails.setEstimatedtotalincprvyr(default_resultSet.getString("ESTIMATEDTOTALINCPRVYR"));
                                xmlDefaultDetails.setTatalnoofform15g(default_resultSet.getString("TOTALNOOFFORM15G"));
                                xmlDefaultDetails.setAggregateamtform15g(default_resultSet.getString("AGGREGATEAMTFORM15G"));
                                xmlDefaultDetails.setDeclarationdate(default_resultSet.getString("DECLARATIONDATE"));
                                xmlDefaultDetails.setAmtofincpaid(default_resultSet.getString("AMTOFINCPAID"));
                                xmlDefaultDetails.setDateincpaid(default_resultSet.getString("DATEINCPAID"));
                                xmlDefaultDetails.setIdentificationnum(default_resultSet.getString("IDENFICATIONNUM"));
                                xmlDefaultDetails.setNatureofinc(default_resultSet.getString("NATUREOFINC"));
                                xmlDefaultDetails.setDeductsection(default_resultSet.getString("DEDUCTSECTION"));
                                xmlDefaultDetails.setAmtofinc(default_resultSet.getString("AMTOFINC"));
                                xmlDefaultDetails.setDateOfBirth(default_resultSet.getString("DOB"));

                            } while (default_resultSet.next());
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (default_resultSet != null) {
                            default_resultSet.close();
                        }
                    }
                }
            };
            ssn.doWork(work);
        } catch (Exception e) {
            //System.out.println("Error in Executing Sequence");
            //System.out.println(e.getMessage());
        } finally {
            ssn.close();
        }
        return xmlDefaultDetails;
    }//end method

    public Generate15GHXmlDefaultDtlBean getXmlDefaultDetails() {
        return xmlDefaultDetails;
    }

    public void setXmlDefaultDetails(Generate15GHXmlDefaultDtlBean xmlDefaultDetails) {
        this.xmlDefaultDetails = xmlDefaultDetails;
    }

    public ArrayList<Generate15GHXmlUnMergedPanDtlBean> getUnMergedPanList() {
        return unMergedPanList;
    }

    public void setUnMergedPanList(ArrayList<Generate15GHXmlUnMergedPanDtlBean> unMergedPanList) {
        this.unMergedPanList = unMergedPanList;
    }

    public ArrayList<Generate15GHXmlClientDetails> getDataList1() {
        return dataList1;
    }

    public void setDataList1(ArrayList<Generate15GHXmlClientDetails> dataList1) {
        this.dataList1 = dataList1;
    }

    private String getCharType(String ghchartype) {
        String charType = "B";
        try {
            if (!utl.isnull(ghchartype)) {
                if (ghchartype.equalsIgnoreCase("G")) {
                    charType = "B";
                } else {
                    charType = "D";

                }
            }
        } catch (Exception e) {
        }
        return charType;
    }//end method
    
}
