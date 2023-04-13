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

import com.lhs.pdfFileConverter.service.OrganisePdfService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/organizedPdf/")
public class OrganizedPdfController {
	List<String> finalfilename;
	List<String> listOfFileNames=new ArrayList<>();
	@Value("${pdfConverterDrive}")
    String pathDrive;
	@Autowired
	OrganisePdfService organisePdfService;
	
	
//////////////////////////////////////////////////merge to pdf//////////////////////////////////////////////////////////////////////
@PostMapping("mergePdfUpload")
public String mergePdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
@RequestParam(value = "uploadValue", required = false) String uploadValue,@RequestParam(value = "indexArray", required = false) String indexArray,
HttpServletResponse response) {
System.out.println(uploadValue);
System.out.println(indexArray);
String[] indexArrays=indexArray.split(",");
//System.out.println(indexArrays[0]);
//this.downloadType=downloadType;
System.out.println("merge pdf");
for (int i = 0; i < file.length; i++) {
System.out.println(file[i].getOriginalFilename());
}
organisePdfService.mergePdf(file, indexArrays, uploadValue, pathDrive, response);

List<String> pdfcreated = new ArrayList<>();
//pdfcreated = convertToPdfService.convertpngTopdf(file, response,pathDrive,uploadValue,downloadType);
//finalfilename1=pdfcreated;

//pdfcreated = convertToPdfService.converttiffTopdf(file, response, pathDrive, uploadValue,downloadType);
//System.out.println(pdfcreated);
//finalfilename1 = pdfcreated;
return null;
}

@GetMapping("mergePdfDownload")
public ResponseEntity<Resource> mergePdfDownload(HttpServletResponse response) throws IOException {
	
	
	
System.out.println("merge pdf donwlonlos");
List<String> listOfFileNames = new ArrayList<>();
//if (this.downloadType.equalsIgnoreCase("pdfJointPage")) {
//File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename1.get(0));
//contentDispositonSinglePdf(file2, response);
//} else {
//for (int i = 0; i < finalfilename1.size(); i++) {
//listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename1.get(i));
//}
//contentDispositonzip(listOfFileNames, response, "jpgToPdf");
//}
//finalfilename1.removeAll(finalfilename1);
return null;
}
////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////split pdf//////////////////////////////////////////////////////////////////////
@PostMapping("splitPdfUpload")
public String splitPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
		@RequestParam(value = "uploadValue", required = false) String uploadValue,@RequestParam(value = "indexArray", required = false) String indexArray,
		HttpServletResponse response) {
	System.out.println("split pdf upload controller");
System.out.println(uploadValue);

for (int i = 0; i < file.length; i++) {
System.out.println(file[i].getOriginalFilename());
}

finalfilename=organisePdfService.splitPdf(file, null, uploadValue,pathDrive , response);
 System.out.println(finalfilename);
return null;
}

@GetMapping("splitPdfDownload")
public ResponseEntity<Resource> splitPdfDownload(HttpServletResponse response) throws IOException {
System.out.println("split pdf donwlonlos");
List<String> listOfFileNames = new ArrayList<>();
//System.out.println(finalfilename);
//
//for (int i = 0; i < finalfilename.size(); i++) {
//listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename.get(i));
//}
System.out.println("finalfilename "+finalfilename);
contentDispositonzip(finalfilename, response, "splitPdf");
//}
//finalfilename1.removeAll(finalfilename1);
return null;
}
////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////organise  pdf//////////////////////////////////////////////////////////////////////
@PostMapping("organizePdfUpload")
public String organizePdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
@RequestParam(value = "uploadValue", required = false) String uploadValue,@RequestParam(value = "indexArray", required = false) String indexArray,
HttpServletResponse response) {
	System.out.println("organize pdf upload controller");
System.out.println(uploadValue);
System.out.println(indexArray);
String[] indexArrays=indexArray.split(",");
//System.out.println(indexArrays[0]);
//this.downloadType=downloadType;
for (int i = 0; i < file.length; i++) {
System.out.println(file[i].getOriginalFilename());
}
listOfFileNames=organisePdfService.organizePdf(file, indexArrays, uploadValue, pathDrive, response);

List<String> pdfcreated = new ArrayList<>();

return null;
}
@PostMapping("organizesortedPdfUpload")
public String organizesortedPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
@RequestParam(value = "uploadValue", required = false) String uploadValue,@RequestParam(value = "indexArray", required = false) String indexArray,
HttpServletResponse response) {
	System.out.println("organize pdf upload sorted controller");
System.out.println(uploadValue);
System.out.println(indexArray);
String[] indexArrays=indexArray.split(",");
System.out.println("file length uploaded : ");
System.out.println("file length uploaded : "+file.length);
//System.out.println(indexArrays[0]);
//this.downloadType=downloadType;
for (int i = 0; i < file.length; i++) {
System.out.println(file[i].getOriginalFilename());
}
listOfFileNames=organisePdfService.organizesortedPdf(file, indexArrays, uploadValue, pathDrive, response);

List<String> pdfcreated = new ArrayList<>();

return null;
}
@GetMapping("organizeMergePdfDownload")
public ResponseEntity<Resource> organizeMergePdfDownload(HttpServletResponse response) throws IOException {
System.out.println("organize merge pdf donwlonlos");
File filecompressSinglePdf = new File(listOfFileNames.get(0));
contentDispositonSinglePdf(filecompressSinglePdf,response);
return null;
}

@GetMapping("organizePdfDownload")
public ResponseEntity<Resource> organizePdfDownload(HttpServletResponse response) throws IOException {
System.out.println("organize pdf donwlonlos");
//List<String> listOfFileNames = new ArrayList<>();
//File filecompressSinglePdf = new File(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//tejas13567.pdf");
File filecompressSinglePdf = new File(listOfFileNames.get(0));
contentDispositonSinglePdf(filecompressSinglePdf,response);
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
