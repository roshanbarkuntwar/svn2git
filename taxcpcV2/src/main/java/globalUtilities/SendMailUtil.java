/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 *
 * @author akash.dev
 */
public class SendMailUtil {

    public boolean sendMail(String username, String password, String toAddress_str, String cc, String bcc, String subject, String message, String attach, String host, String port, String imagePath) {
        boolean result;
        try {
            // Configure your JavaMail.
            //System.out.println("..............................................");
            String fromAddress = username;
            String fileDelim = "\\;";
            String attachFile1 = "";
            String attachFile2 = "";
            String delimiter = "\\,";
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtps");
            props.setProperty("mail.smtps.auth", "true");
            props.setProperty("mail.host", host);
            props.setProperty("mail.port", port);
            props.setProperty("mail.user", username);
            props.setProperty("mail.password", password);

            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.ssl.trust", host);
            props.put("mail.smtp.debug", "false");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.socketFactory.port", port);
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "true");

            //System.out.println("user_name : " + username);
            //System.out.println("password : " + password);
            //System.out.println("host : " + host);
            //System.out.println("port :" + port);
            // Start an email session.
            Session session = Session.getDefaultInstance(props, null);
            Transport transport = session.getTransport("smtp");
            MimeMessage mimeMessage = new MimeMessage(session);
            Multipart multiPart = new MimeMultipart();
            mimeMessage.setSubject(subject);

            if (!toAddress_str.equals("") && toAddress_str != null) {
                String[] toAddress = toAddress_str.split(delimiter);
                for (String toAddres : toAddress) {
                    try {
                        String tmp_to_add = toAddres.trim();
                        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(tmp_to_add));
                    } catch (MessagingException ex) {
                        //System.out.println(ex.getMessage());
                    }
                }
            } else {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("reqtaxcpc@gmail.com"));
            }
            if (!cc.equals("") && cc != null) {
                String[] ccMailid;
                ccMailid = cc.split(delimiter);
                for (String ccMailid1 : ccMailid) {
                    try {
                        String tmp_cc_add = ccMailid1.trim();
                        mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(tmp_cc_add));
                    } catch (MessagingException ex) {
                        //System.out.println(ex.getMessage());
                    }
                }
            } else {
                cc = "";
            }
            if (!bcc.equals("") && bcc != null) {
                String[] bccMailid;
                bccMailid = bcc.split(delimiter);
                for (String bccMailid1 : bccMailid) {
                    try {
                        String tmp_bcc_add = bccMailid1.trim();
                        mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(tmp_bcc_add));
                    } catch (MessagingException ex) {
                        //System.out.println(ex.getMessage());
                    }
                }
            } else {
                bcc = "";

            }

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(message, "text/html");
            multiPart.addBodyPart(textBodyPart);
            if (imagePath != null && imagePath != "" && !imagePath.equalsIgnoreCase("null")) {
                textBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource(imagePath);
                textBodyPart.setDataHandler(new DataHandler(fds));
                textBodyPart.setHeader("Content-ID", "<image>");
            }

            // add image to the multipart
            mimeMessage.setContent(multiPart);
            mimeMessage.setFrom(new InternetAddress(fromAddress));

            //**************Attachment*****************
            if (!(attach.equals("") || attach.equals("###"))) {
                int ind = attach.indexOf(";");
                if (ind > 0) {
                    String[] fileAtt = attach.split(fileDelim);
                    attachFile1 = fileAtt[0];
                    attachFile2 = fileAtt[1];
                    MimeBodyPart attachmentPart = new MimeBodyPart();

                    FileDataSource fileDataSource = new FileDataSource(attachFile1) {
                        @Override
                        public String getContentType() {
                            return "application/octet-stream";
                        }
                    };
                    attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                    MimeBodyPart attachmentPart2 = new MimeBodyPart();

                    FileDataSource fileDataSource2 = new FileDataSource(attachFile2) {
                        @Override
                        public String getContentType() {
                            return "application/octet-stream";
                        }
                    };
                    attachmentPart2.setDataHandler(new DataHandler(fileDataSource2));
                    attachmentPart2.setFileName(attachFile2);
                    attachmentPart.setFileName(attachFile1);
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(textBodyPart);
                    multipart.addBodyPart(attachmentPart2);
                    multipart.addBodyPart(attachmentPart);
                    mimeMessage.setContent(multipart);
                } else {
                    //System.out.println("only one file attached");
                    MimeBodyPart attachmentPart1 = new MimeBodyPart();
                    FileDataSource fileDataSource1 = new FileDataSource(attach) {
                        @Override
                        public String getContentType() {
                            return "application/octet-stream";
                        }
                    };
                    attachmentPart1.setDataHandler(new DataHandler(fileDataSource1));
                    attachmentPart1.setFileName(attach);
                    Multipart multipart1 = new MimeMultipart();
                    multipart1.addBodyPart(textBodyPart);
                    multipart1.addBodyPart(attachmentPart1);
                    mimeMessage.setContent(multipart1);
                }//end else
            } else {
//                Multipart multipart = new MimeMultipart();
//                multipart.addBodyPart(textBodyPart);
//                mimeMessage.setContent(multipart);
            }//end else
            //System.out.println("connecting with mail server...");

            transport.connect(host, username, password);
            //System.out.println("sending mail...");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            //System.out.println("mail sent successfully");
            transport.close();
            //System.out.println("connection closed with mail server...");
            result = true;
        } catch (MessagingException ex) {
            //System.out.println("\n\n\n\n-----------Error Details------------");
            ex.printStackTrace();
            //System.out.println("-----------Error Details end------------");
            result = false;
        }
        return result;
    }// end method

    public boolean mailsend(String username, String password, String toAddress_str, String cc, String bcc, String subject,
            String message, String attachmentLocationString, String host, String port, String imagePath) throws EmailException,
            MessagingException, IOException {

        boolean flag = false;
        String fileDelim = "\\;";
        String delimiter = "\\,";
//        HtmlEmail htmlEmail = new HtmlEmail();
//        htmlEmail.setHtmlMsg(message);
        Util utl = new Util();
//        String htmlEmailTemplate = "";
//        try {
//            if (imagePath != "" && imagePath != null && imagePath != "null") {
//                htmlEmailTemplate = "<img src=\"file:///" + imagePath + "\"><br>" + message;
//            } else {
//                htmlEmailTemplate = message;
//            }
//        } catch (Exception e) {
//            htmlEmailTemplate = message;
//        }
//
//        utl.generateLog("htmlEmailTemplate---" , htmlEmailTemplate);

        // define you base URL to resolve relative resource locations
        URL url = new URL("http://www.taxcpc.com/");
        // create the email message
        ImageHtmlEmail emailImage = new ImageHtmlEmail();
        emailImage.setDataSourceResolver(new DataSourceUrlResolver(url));
        // set the html message
        emailImage.setHtmlMsg(message);
//        Email email = new SimpleEmail();
        MultiPartEmail email = new MultiPartEmail();
        email = emailImage;
        email.setHostName(host);
        email.setSmtpPort(Integer.parseInt(port));
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setTLS(true);
        email.setSSLOnConnect(true);
        email.setFrom(username);
//        utl.generateLog("Subject--------\n", email.getSubject());
//        utl.generateLog("user_name--------\n", email.getFromAddress());
//        utl.generateLog("password--------\n", password);
//        utl.generateLog("host--------\n", email.getHostName());
//        utl.generateLog("port--------\n", email.getSmtpPort());

        email.setSubject(subject);

        if (toAddress_str != null && !toAddress_str.equals("")) {
            String[] toAddress = toAddress_str.split(delimiter);
            email.addTo(toAddress);
//            utl.generateLog("To--------\n", email.getToAddresses());
        }
//        email.addTo("seema.mourya@lighthouseindia.com");

        if (cc != null && !cc.equals("")) {
            String[] ccMailid;
            ccMailid = cc.split(delimiter);
            email.addCc(ccMailid);
//            utl.generateLog("CC--------\n", email.getCcAddresses());
        }

        if (bcc != null && !bcc.equals("")) {
            String[] bccMailid;
            bccMailid = bcc.split(delimiter);
            email.addBcc(bccMailid);
//            utl.generateLog("BCC--------\n", email.getBccAddresses());
        }

        if (attachmentLocationString != null && !attachmentLocationString.equals("") && !attachmentLocationString.equals("###") && attachmentLocationString.length() > 0) {
//            utl.generateLog(null, "Processing attachment(s)...");
            String[] AllFilePaths;
            try {
                AllFilePaths = attachmentLocationString.split(fileDelim);
            } catch (Exception e) {
                AllFilePaths = null;
            }
            if (AllFilePaths != null) {
                for (String filepath : AllFilePaths) {
                    email.attach(new File(filepath));
                }
            }
        }

        try {
            // send the email
//            System.out.println("mail...");
//            utl.generateLog(null, "Sending mail..........");
            email.send();
//            utl.generateLog("Mail Sent To", email.getToAddresses());
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;

    }// end

//    public static void main(String[] args) {
//        SendMailUtil sm = new SendMailUtil();
//        boolean result = sm.sendMail("harsh@taxcpc.com", "S@25101985j", "akash.dev@lighthouseindia.com", "", "", "TEST MAIL",
//                "TEST MAIL BODY", "", "smtp.office365.com", "587", "");
//    }
}
