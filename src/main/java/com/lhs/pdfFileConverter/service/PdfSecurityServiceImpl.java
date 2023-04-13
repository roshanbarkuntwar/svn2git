package com.lhs.pdfFileConverter.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

@Service
public class PdfSecurityServiceImpl implements PdfSecurityService {
	public void signPdfUsingPfx(String pfxFilePath, String password, String sourcePdfPath, String desPdfPath, int lx,
			int ly, int ux, int uy, HttpServletResponse response, String finalfileName,
			MultipartFile pfx, String signReason) {
		try {
			System.out.println("signpdf using pfx");
			KeyStore ks = KeyStore.getInstance("pkcs12");

			ks.load((pfx.getInputStream()), password.toCharArray());

			String alias = (String) ks.aliases().nextElement();
			PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
			// System.out.println(key.isDestroyed()
			java.security.cert.Certificate[] chain = ks.getCertificateChain(alias);
			PdfReader reader = new PdfReader(sourcePdfPath);
			FileOutputStream fout = new FileOutputStream(desPdfPath);
			//File sd = new File(desPdfPath);
			// System.out.println("read : " + sd.canRead());
			PdfStamper stp1 = PdfStamper.createSignature(reader, fout, '\0');
			PdfSignatureAppearance sap1 = stp1.getSignatureAppearance();
			sap1.setCrypto(key, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
			sap1.setReason(signReason);
			sap1.setVisibleSignature(new Rectangle(lx, ly, ux, uy), 1, null);
			// System.out.println(sap1.getPrivKey());
			stp1.close();

			// File fileprotected = new File("J://PdfGenereatedFiles/DigitalSignature/new_"
			// + finalfileName);
			// contentDispositonSinglePdf(fileprotected, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong");
		}
		System.out.println("sign pdf last line");
	}

	@Override
	public List<String> digitalSign(MultipartFile[] file, String passValue, String signReason, MultipartFile pfxfile,
			HttpServletResponse response,String pathDrive,String uploadValue) {
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		List<String> listOfFileNames=new ArrayList<>();
		String finalfileName;
		
		for(int i=0;i<file.length;i++) {
			
			System.out.println("sign service implementation class");
			try {
				// file.transferTo(new File("J:\\PdfGenereatedFiles\\DigitalSignature\\" +
				// pfxfile.getOriginalFilename()));
				
				if(uploadValue.equalsIgnoreCase("folder")) {
					////////////////////////////////////// Split by directory /////////////////////////////
					System.out.println("folder");
					String fileName = file[i].getOriginalFilename().replace("\\", "/");
					String[] splittedFileName = fileName.split("/");
					String filenamefolder = splittedFileName[splittedFileName.length-1];
			        //////////////////////////////////////////////////////////////////////////////////
					System.out.println(filenamefolder);
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + filenamefolder));
					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + filenamefolder);
					String timestampString = String.valueOf(timestamp);
					 finalfileName = filenamefolder.replace(".pdf", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					System.out.println(finalfileName);
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
					
				}else {
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + file[i].getOriginalFilename()));
					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + file[i].getOriginalFilename());
					String timestampString = String.valueOf(timestamp);
					 finalfileName = file[i].getOriginalFilename().replace(".pdf", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					System.out.println(finalfileName);
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
				}
				
				

				PDDocument document = PDDocument
						.load(new File(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + finalfileName));
				document.save(pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\OriginalFile\\Original_" + finalfileName);
				document.close();

				System.out.println("file specified");
				String pfxFilePath = "";
				// String pfxFilePath = "J:\\PdfGenereatedFiles\\DigitalSignature\\pfxfile\\"+
				// "checkpass.pfx";

				String password = passValue;
				String sourcePdfPath = pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\" + finalfileName;
				String desPdfPath = pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\new_" + finalfileName;
				
				listOfFileNames.add("new_"+finalfileName);
				
				
				Integer lx = 300;
				Integer ly = 80;
				Integer ux = 790;
				Integer uy = 140;

				signPdfUsingPfx(pfxFilePath, password, sourcePdfPath, desPdfPath, lx, ly, ux, uy, response,
						finalfileName, pfxfile, signReason);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			}
			
		}
		
		System.out.println(listOfFileNames);
		return listOfFileNames;
	}

	@Override
	public List<String> protectpassPdf(MultipartFile[] file, String password,String uploadValue,String pathDrive,HttpServletResponse response) {
		System.out.println("protect pass Pdf service impl");	
		List<String> listOfFileNames=new ArrayList<>();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("pointer comes in password protected pdf file");
		String finalfileName = "";
		String timestampString="";
		
		for(int i=0;i<file.length;i++) {
		if(file[i].getOriginalFilename().contains(".pdf")) {
	
		System.out.println(file[i].getOriginalFilename());
		PDDocument pdd;
		try {
			
			
			if(uploadValue.equalsIgnoreCase("folder")) {
				////////////////////////////////////// Split by directory /////////////////////////////
				System.out.println("folder");
				String fileName = file[i].getOriginalFilename().replace("\\", "/");
				String[] splittedFileName = fileName.split("/");
				String filenamefolder = splittedFileName[splittedFileName.length-1];
		        //////////////////////////////////////////////////////////////////////////////////
				file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + filenamefolder));
				 timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + filenamefolder);
				finalfileName = filenamefolder.replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);
			}else {
				file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + file[i].getOriginalFilename()));
				 timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + file[i].getOriginalFilename());
				finalfileName = file[i].getOriginalFilename().replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);
			}
			PDDocument document = PDDocument
					.load(new File(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + finalfileName));
			document.save(pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\OriginalFile\\Original_" + finalfileName);
			document.close();
			
			File f = new File(pathDrive+"//PdfGenereatedFiles/PasswordPdf/" + finalfileName);
			pdd = PDDocument.load(f);

			AccessPermission ap = new AccessPermission();

			StandardProtectionPolicy stpp = new StandardProtectionPolicy(password, password, ap);
			stpp.setEncryptionKeyLength(128);
			stpp.setPermissions(ap);
			pdd.protect(stpp);

			pdd.save(
					pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\" + "pass_" + finalfileName);
			pdd.close();
			
			listOfFileNames.add("pass_"+finalfileName);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong.");
		}
	}else {
		throw new RuntimeException("Please enter pdf file.");
	}
		}
		return listOfFileNames;
	}

	@Override
	public List<String> removepassPdf(MultipartFile[] file, String password, String uploadValue, String pathDrive,
			HttpServletResponse response) {
		
		System.out.println("remove protect pass Pdf service impl : "+ password);	
		List<String> listOfFileNames=new ArrayList<>();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("pointer comes in removepass protected pdf file");
		String finalfileName = "";
		String timestampString="";
		
		for(int i=0;i<file.length;i++) {
		if(file[i].getOriginalFilename().contains(".pdf")) {
	
		System.out.println(file[i].getOriginalFilename());
		PDDocument pdd;
		try {
			if(uploadValue.equalsIgnoreCase("folder")) {
				////////////////////////////////////// Split by directory /////////////////////////////
				System.out.println("folder");
				String fileName = file[i].getOriginalFilename().replace("\\", "/");
				String[] splittedFileName = fileName.split("/");
				String filenamefolder = splittedFileName[splittedFileName.length-1];
		        //////////////////////////////////////////////////////////////////////////////////
				file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + filenamefolder));
				 timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + filenamefolder);
				finalfileName = filenamefolder.replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);
			}else {
				System.out.println("upload Value : "+uploadValue);
				file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + file[i].getOriginalFilename()));
				 timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + file[i].getOriginalFilename());
				finalfileName = file[i].getOriginalFilename().replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);
			}
			
			
			
			
			  InputStream is = null;
			    OutputStream os = null;
			    try {
			        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\"+finalfileName);
			        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\OriginalFile\\Original_"+finalfileName);
			        byte[] buffer = new byte[1024];
			        int length;
			        while ((length = is.read(buffer)) > 0) {
			            os.write(buffer, 0, length);
			        }
			    } finally {
			        is.close();
			        os.close();
			    }
			
			System.out.println("hi");
//			File f = new File(pathDrive+"//PdfGenereatedFiles/RemovePassowrdPdf/" + finalfileName);
			//pdd = PDDocument.load(f);
			File filenew = new File(
					pathDrive+"//PdfGenereatedFiles/RemovePassowrdPdf/" + finalfileName);

			pdd = PDDocument.load(filenew, password);
			pdd.setAllSecurityToBeRemoved(true);
			
			
			
			
			pdd.save(
					pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + "decrypted_" + finalfileName);
			pdd.close();
			System.out.println("check");
			listOfFileNames.add("decrypted_"+finalfileName);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong.");
		}
	}else {
		throw new RuntimeException("Please enter pdf file.");
	}
		}
//		System.out.println(listOfFileNames);
		return listOfFileNames;
	}
		

}
