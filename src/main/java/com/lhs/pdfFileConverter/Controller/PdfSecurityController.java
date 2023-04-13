package com.lhs.pdfFileConverter.Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lhs.pdfFileConverter.service.PdfSecurityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pdfSecurity/")
public class PdfSecurityController {
	
	@Autowired
	PdfSecurityService pdfSecurityService;
	List<String> listOfFileNames=new ArrayList<>();
	
	@Value("${pdfConverterDrive}")
    String pathDrive;
	
	@PostMapping("signPdfUpload")
	public String signpdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "pfxFile", required = false) MultipartFile[] pfxFile,@RequestParam(value = "pfxFilePassword", required = false) String pfxFilePassword,@RequestParam(value = "pfxFileReason", required = false) String pfxFileReason,
			@RequestParam(value = "uploadValue", required = false) String uploadValue,
			HttpServletResponse response) {
	
		System.out.println("sign pdf controller");
		System.out.println(pfxFilePassword);
		System.out.println(pfxFileReason);
		System.out.println(uploadValue);
		System.out.println(pfxFile[0].getOriginalFilename());
		System.out.println(file[0].getOriginalFilename());
		listOfFileNames=pdfSecurityService.digitalSign( file, pfxFilePassword, pfxFileReason, pfxFile[0],
				 response,pathDrive,uploadValue);
		//System.out.println("return :"+listOfFileNames);
		return null;
	}
	
	
	@GetMapping("signPdfDownload")
	public ResponseEntity<Resource> signPdfDownload(HttpServletResponse response) throws IOException {
	    System.out.println("signPdfDownload pdf downloads...............................");
		List<String> listOfFilePath = new ArrayList<>();
	
		if(listOfFileNames.size()==1) {
			
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\DigitalSignature\\" + listOfFileNames.get(0));
			contentDispositonSinglePdf(file2, response);
			
		}else {
		System.out.println("download: "+ listOfFileNames);
			for (int i = 0; i < listOfFileNames.size(); i++) {
				listOfFilePath.add( pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\"+ listOfFileNames.get(i));
			}
			contentDispositonzip(listOfFilePath, response, "DigitalsignPdfDownload");
		}
//		finalfilename1.removeAll(finalfilename1);
		return null;
	}
//////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////Password Protected Pdf///////////////////////////////////
	@PostMapping("protectPdfUpload")
	public String protectpdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "password", required = false) String password,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			HttpServletResponse response) {
		System.out.println("upload value is : " +   uploadValue );
		System.out.println("password protected pdf controller");
		System.out.println("password is : "+password);
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		listOfFileNames= pdfSecurityService.protectpassPdf(file, password,uploadValue, pathDrive,response);
		return null;
	}
	@GetMapping("protectPdfDownload")
	public ResponseEntity<Resource> protectPdfDownload(HttpServletResponse response) throws IOException {
		System.out.println("protectpdf download");
		List<String> listOfFilePath = new ArrayList<>();
		if (listOfFileNames.size() == 1) {
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\PasswordPdf\\" + listOfFileNames.get(0));
			contentDispositonSinglePdf(file2, response);
		} else {
			System.out.println("download: " + listOfFileNames);
			for (int i = 0; i < listOfFileNames.size(); i++) {
				listOfFilePath.add(pathDrive + "\\PdfGenereatedFiles\\PasswordPdf\\" + listOfFileNames.get(i));
			}
			contentDispositonzip(listOfFilePath, response, "passProtectedPdfDownload");
		}
		return null;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////remove Password Protected Pdf///////////////////////////////////
	@PostMapping("removepassPdfUpload")
	public String removepassPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "password", required = false) String password,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			HttpServletResponse response) {
		System.out.println("upload value is : " +   uploadValue );
		System.out.println("password protected pdf controller");
		System.out.println("password is : "+password);
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		this.listOfFileNames= pdfSecurityService.removepassPdf(file, password,uploadValue, pathDrive,response);
		
		return null;
	}
	@GetMapping("removepassPdfDownload")
	public ResponseEntity<Resource> removepassPdfDownload(HttpServletResponse response) throws IOException {
		System.out.println("remove password protectpdf download");
		List<String> listOfFilePath = new ArrayList<>();
		System.out.println(listOfFileNames+"============="+listOfFileNames.size());
		if (listOfFileNames.size() == 1) {
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + listOfFileNames.get(0));
			contentDispositonSinglePdf(file2, response);
		} else {
			System.out.println("download: " + listOfFileNames);
			for (int i = 0; i < listOfFileNames.size(); i++) {
				listOfFilePath.add(pathDrive + "\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + listOfFileNames.get(i));
			}
			contentDispositonzip(listOfFilePath, response, "removepassProtectedPdfDownload");
		}
		return null;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String contentDispositonSinglePdf(File filedispostn, HttpServletResponse response) {
		if (filedispostn.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(filedispostn.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition",
					String.format("attachment; filename=\"" + filedispostn.getName() + "\""));
			response.setContentLength((int) filedispostn.length());
			
			InputStream inputStream;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(filedispostn));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			}
		}
		return null;
	}
	public String contentDispositonzip(List<String> listOfFileNames,HttpServletResponse response,String filedownloadname) {
		System.out.println("conetent dispostion zip");
		System.out.println("contentpositionzip");
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "inline; filename="+filedownloadname+".zip");
		Cookie ck = new Cookie("user", "uservalue");// creating cookie object
		response.addCookie(ck);// adding cookie in the response
		response.addHeader("headername", "headervalue");

		try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {

			for (String fileName : listOfFileNames) {
				FileSystemResource fileSystemResource = new FileSystemResource(fileName);
				ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
				zipEntry.setSize(fileSystemResource.contentLength());
				zipEntry.setTime(System.currentTimeMillis());

				zipOutputStream.putNextEntry(zipEntry);

				StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
				zipOutputStream.closeEntry();
			}
			// response.setHeader("Refresh", "1");
			zipOutputStream.finish();
			zipOutputStream.close();
			response.setHeader("Refresh", "1");

		} catch (IOException e) {
			// logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new RuntimeException("Something went wrong.");
		}
		return null;
	}
}
