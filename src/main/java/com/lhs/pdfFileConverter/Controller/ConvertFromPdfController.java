package com.lhs.pdfFileConverter.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.lhs.pdfFileConverter.service.ConvertFromPdfService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ConvertFromPdf/")
public class ConvertFromPdfController {
	@Autowired
	ConvertFromPdfService convertFromPdfService;

	List<String> finalfilename;
	@Value("${pdfConverterDrive}")
    String pathDrive;
	
//	@Autowired
//	RestTemplate restTemplat;
	
	@PostMapping("pdfToJpg")
	public String pdfToJpg(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			HttpServletResponse response) {
		String extension;
		String filepathmain;
		
		System.out.println(uploadValue);
		
		System.out.println("pdf to jpg");
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		
		//System.out.println(file.getOriginalFilename());
		List<String> pdfcreated = convertFromPdfService.convertPdfTojpg(file, response,pathDrive,uploadValue);
		finalfilename = pdfcreated;
		return null;
	}

	@GetMapping("pdfTpJpgdownload")
	public ResponseEntity<Resource> downloadFile(HttpServletResponse response) throws IOException {
		
		List<String> listOfFileNames = new ArrayList<>();
		//String[] filenameAndPageNo = finalfilename.split("#");
		System.out.println(finalfilename);
		//System.out.println(filenameAndPageNo[1]);
		
		//String[] filenamelist=filenameAndPageNo[0].split(",");
		//System.out.println(filenamelist.length+"       :finamelist length");
		
//		for (int i = 1; i <= (Integer.valueOf(filenameAndPageNo[1])); i++) {
//			
//			for(int j=1;j<=filenamelist.length;j++) {
//				listOfFileNames.add(pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\" + filenamelist[j] + "_" + i + ".jpg");
//			}
			
		System.out.println(finalfilename.size());
			for (int i = 0; i < finalfilename.size(); i++) {
				
				//for(int j=1;j<=filenamelist.length;j++) {
					listOfFileNames.add(pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\" + finalfilename.get(i));
				//}
			
		}

		
		
		contentDispositonzip(listOfFileNames, response);
		for (int i = 0; i < listOfFileNames.size(); i++) {
			File fileDeletejpg = new File(
					pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\" + finalfilename.get(i));
			if (fileDeletejpg.exists()) {
				fileDeletejpg.delete();
				System.out.println("file deleted successfully");
			}
			
		}
//		for (int i = 0; i < listOfFileNames.size(); i++) {
//			File fileDeletejpg = new File(
//					pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\splitjpg_" + i+1 +"_"+finalfilename.get(i).substring(0, finalfilename.get(i).length()-6));
//			System.out.println(pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\splitjpg_" + (i+1) +"_"+finalfilename.get(i).substring(0, finalfilename.get(i).length()-6));
//			if (fileDeletejpg.exists()) {
//				fileDeletejpg.delete();
//				System.out.println("file deleted successfully");
//			}
//			
//		}

		return null;
	}
	
	public String contentDispositonzip(List<String> listOfFileNames,HttpServletResponse response) {
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "inline; filename=download.zip");
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
