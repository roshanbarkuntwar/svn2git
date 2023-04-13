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

import com.lhs.pdfFileConverter.service.EditPdfService;
import com.lhs.pdfFileConverter.service.OrganisePdfService;
import com.lhs.pdfFileConverter.service.PdfSecurityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/editPdf/")
public class EditPdfController {
	List<String> finalfilename;
	List<String> listOfFileNames=new ArrayList<>();
	@Value("${pdfConverterDrive}")
	String pathDrive;
	@Autowired
	EditPdfService editPdfService;

//////////////////////////////////////////////////split pdf//////////////////////////////////////////////////////////////////////
	@PostMapping("rotatePdfUpload")
	public String splitPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,
			@RequestParam(value = "uploadValue", required = false) String uploadValue,
			@RequestParam(value = "indexArray", required = false) String indexArray,
			@RequestParam(value = "rotateValue", required = false) int rotateValue, HttpServletResponse response) {

		System.out.println("rotate pdf upload controller");
		System.out.println(uploadValue);

		for (int i = 0; i < file.length; i++) {
			System.out.println(file[i].getOriginalFilename());
		}

		listOfFileNames = editPdfService.rotatePdf(file, null, uploadValue, pathDrive, rotateValue, response);
		System.out.println("listOfFileNames: "+listOfFileNames);
		System.out.println(finalfilename);
		return null;
	}

	@GetMapping("rotatePdfDownload")
	public ResponseEntity<Resource> rotatePdfDownload(HttpServletResponse response) throws IOException {
		System.out.println("rotatePdfDownload pdf donwlonlos");
		//File filecompressSinglePdf = new File(listOfFileNames.get(0));
		File filerotateSinglePdf = new File(listOfFileNames.get(0));
		contentDispositonSinglePdf(filerotateSinglePdf,response);		
		
		
//System.out.println(finalfilename);
//
//for (int i = 0; i < finalfilename.size(); i++) {
//listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\TiffToPdf\\" + finalfilename.get(i));
//}
		System.out.println("finalfilename " + finalfilename);
		contentDispositonzip(finalfilename, response, "splitPdf");
//}
//finalfilename1.removeAll(finalfilename1);
		return null;
	}
////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////page number Pdf///////////////////////////////////
	@PostMapping("pagenoPdfUpload")
	public String compressPdfUpload(@RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "xcord", required = false) String xcord,@RequestParam(value = "ycord", required = false) String ycord,@RequestParam(value = "password", required = false) String password,@RequestParam(value = "uploadValue", required = false) String uploadValue,
			HttpServletResponse response) {
		System.out.println("upload value is : " +   uploadValue+" x: "+xcord+" Y: "+ycord );
		System.out.println("pagenoPdfUpload pdf controller");
		//System.out.println("password is : "+password);
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
		listOfFileNames = editPdfService.pagenoPdf(file, null,xcord,ycord, uploadValue, pathDrive, response);
		
		
		return null;
	}
	@GetMapping("pagenoPdfDownload")
	public ResponseEntity<Resource> compressPdfDownload(HttpServletResponse response) throws IOException {
		System.out.println("pagenoPdfDownload download");
		List<String> listPageNoDownlowad;
		//contentDispositonzip(listOfFileNames,response,"pageNoPdf");
		
		File filecompressSinglePdf = new File(listOfFileNames.get(0));
		contentDispositonSinglePdf(filecompressSinglePdf,response);

		//		List<String> listOfFilePath = new ArrayList<>();
////		System.out.println(listOfFileNames+"============="+listOfFileNames.size());
//		if (listOfFileNames.size() == 1) {
//			File file2 = new File(pathDrive + "\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + listOfFileNames.get(0));
//			contentDispositonSinglePdf(file2, response);
//		} else {
//			System.out.println("download: " + listOfFileNames);
//			for (int i = 0; i < listOfFileNames.size(); i++) {
//				listOfFilePath.add(pathDrive + "\\PdfGenereatedFiles\\RemovePassowrdPdf\\" + listOfFileNames.get(i));
//			}
			//contentDispositonzip(listOfFilePath, response, "removepassProtectedPdfDownload");
//		}
		return null;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String contentDispositonSinglePdf(File filedispostn, HttpServletResponse response) {
		if (filedispostn.exists()) {
			System.out.println("content disopositon downolad single file");
			String mimeType = URLConnection.guessContentTypeFromName(filedispostn.getName());
			if (mimeType == null) {
				mimeType = "application/pdf";
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

	public String contentDispositonzip(List<String> listOfFileNames, HttpServletResponse response,
			String filedownloadname) {
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "inline; filename=" + filedownloadname + ".zip");
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
