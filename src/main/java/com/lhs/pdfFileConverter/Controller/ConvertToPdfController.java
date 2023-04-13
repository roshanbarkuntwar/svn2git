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

import com.lhs.pdfFileConverter.service.ConvertToPdfService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ConvertToPdf/")
public class ConvertToPdfController {
	
	List<String> finalfilename1 = new ArrayList<>();
	String finalfilename;
	
	String downloadType;
	@Autowired
	ConvertToPdfService convertToPdfService;
	@Value("${pdfConverterDrive}")
    String pathDrive;
	
	@PostMapping("jpgToPdfUpload")
	public String jpgTopdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			@RequestParam(value = "downloadType", required = false) String downloadType,
			HttpServletResponse response) {
		System.out.println(uploadValue);
		System.out.println(downloadType);
		this.downloadType=downloadType;
		System.out.println("jpg to pdf");
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		List<String> pdfcreated = new ArrayList<>();
		 pdfcreated = convertToPdfService.convertjpgTopdf(file, response,pathDrive,uploadValue,downloadType);
		finalfilename1=pdfcreated;
		return null;
	}
	
	
	@GetMapping("jpgToPdfDownload")
	public ResponseEntity<Resource> jpgTopdfdownloadFile(HttpServletResponse response) throws IOException {
		
		List<String> listOfFileNames = new ArrayList<>();
		if(this.downloadType.equalsIgnoreCase("pdfJointPage")) {
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\JpgToPdf\\" + finalfilename1.get(0));
			contentDispositonSinglePdf(file2, response);
			finalfilename1.removeAll(finalfilename1);
			
		}else {
			for(int i=0;i<finalfilename1.size();i++) {
				System.out.println("pdf single page controller");
				listOfFileNames.add(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + finalfilename1.get(i));
			}
			contentDispositonzip(listOfFileNames, response,"jpgToPdf");
		}
		
		return null;
	}
	
	//////////////////////////////////////////////////png to pdf//////////////////////////////////////////////////////////////////////
	@PostMapping("pngToPdfUpload")
	public String pngToPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			@RequestParam(value = "downloadType", required = false) String downloadType,
			HttpServletResponse response) {
		System.out.println(uploadValue);
		this.downloadType=downloadType;
		System.out.println("png to pdf");
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		List<String> pdfcreated = new ArrayList<>();
		pdfcreated = convertToPdfService.convertpngTopdf(file, response,pathDrive,uploadValue,downloadType);
		finalfilename1=pdfcreated;
		return null;
	}
	
	
	@GetMapping("pngToPdfDownload")
	public ResponseEntity<Resource> pngToPdfDownload(HttpServletResponse response) throws IOException {
		List<String> listOfFileNames = new ArrayList<>();
		if (this.downloadType.equalsIgnoreCase("pdfJointPage")) {
			System.out.println("ljllljlkljlljkljljl");
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\PngToPdf\\" + finalfilename1.get(0));
			contentDispositonSinglePdf(file2, response);

		} else {
			for (int i = 0; i < finalfilename1.size(); i++) {
				System.out.println("pdf single page controller");
				listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\PngToPdf\\" + finalfilename1.get(i));
			}

			contentDispositonzip(listOfFileNames, response, "jpgToPdf");
		}
		finalfilename1.removeAll(finalfilename1);
		return null;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	
//////////////////////////////////////////////////tiff to pdf//////////////////////////////////////////////////////////////////////
	@PostMapping("tiffToPdfUpload")
	public String tiffToPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
			@RequestParam(value = "uploadValue", required = false) String uploadValue,@RequestParam(value = "downloadType", required = false) String downloadType, HttpServletResponse response) {
		System.out.println(uploadValue);
		this.downloadType=downloadType;
		System.out.println("png to pdf");
		for (int i = 0; i < file.length; i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		
		List<String> pdfcreated = new ArrayList<>();
//		pdfcreated = convertToPdfService.convertpngTopdf(file, response,pathDrive,uploadValue,downloadType);
//		finalfilename1=pdfcreated;
		
		 pdfcreated = convertToPdfService.converttiffTopdf(file, response, pathDrive, uploadValue,downloadType);
		System.out.println(pdfcreated);
		finalfilename1 = pdfcreated;
		return null;
	}

	@GetMapping("tiffToPdfDownload")
	public ResponseEntity<Resource> tiffToPdfDownload(HttpServletResponse response) throws IOException {
	    System.out.println("tiff to pdf donwlonlos");
		List<String> listOfFileNames = new ArrayList<>();
		if (this.downloadType.equalsIgnoreCase("pdfJointPage")) {
			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename1.get(0));
			contentDispositonSinglePdf(file2, response);
		} else {
			for (int i = 0; i < finalfilename1.size(); i++) {
				listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename1.get(i));
			}
			contentDispositonzip(listOfFileNames, response, "jpgToPdf");
		}
		finalfilename1.removeAll(finalfilename1);
		return null;
	}
////////////////////////////////////////////////////////////////////////////////////////////////
	
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
