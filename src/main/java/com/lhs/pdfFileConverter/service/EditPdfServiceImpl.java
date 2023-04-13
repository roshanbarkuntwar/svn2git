package com.lhs.pdfFileConverter.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EditPdfServiceImpl implements EditPdfService{

	@Override
	public List<String> rotatePdf(MultipartFile[] file, String[] indexArrays, String uploadValue, String pathDrive,int rotateValue,
			HttpServletResponse response) {
		int rotate_angle=0;
		List<String> listOfFileNames = new ArrayList<>();
		System.out.println("rotate Pdf impl");
		if (file[0].getOriginalFilename().contains(".pdf")) {

			Long datetime = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(datetime);
			System.out.println("rotate pdf service implementation: "+rotateValue);
			
			String finalfileName = "";
			try {
				file[0].transferTo(
						new File(pathDrive+"\\PdfGenereatedFiles\\RotatePdf\\" + file[0].getOriginalFilename()));

				String timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(
						pathDrive+"\\PdfGenereatedFiles\\RotatePdf\\" + file[0].getOriginalFilename());
				finalfileName = file[0].getOriginalFilename().replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\RotatePdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);

				File filenew = new File(pathDrive+"//PdfGenereatedFiles/RotatePdf/" + finalfileName);
				PDDocument document = PDDocument.load(filenew);
				System.out.println("filenew loaded");
				int pageCount = document.getNumberOfPages();
				System.out.println(pageCount);

				for (int i = 0; i < pageCount; i++) {
					System.out.println("page " + i);
					PDPage page = document.getPage(i);
					System.out.println("before rotation" + page.getRotation());
					page.setRotation(Integer.valueOf(rotateValue));

					if (page.getRotation() != 0) {
						System.out.println("after rotation" + page.getRotation());
					}
				}
				document.save(pathDrive+"\\PdfGenereatedFiles\\RotatePdf\\" + "roate_new_" + finalfileName);
				listOfFileNames.add(pathDrive+"//PdfGenereatedFiles/RotatePdf/roate_new_" + finalfileName);
				document.close();
				//File file2 = new File(pathDrive+"//PdfGenereatedFiles/RotatePdf/roate_new_" + finalfileName);
			
				//contentDispositonSinglePdf(file2, response);

			} catch ( IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			}
			//return null;
		} else {
			throw new RuntimeException("Please enter pdf file.");
		}
		return listOfFileNames;
	}

	@Override
	public List<String> pagenoPdf(MultipartFile[] file, String indexArrays, String xcord, String ycord,
			String uploadValue, String pathDrive, HttpServletResponse response) {
		System.out.println("page number pdf service implementation");
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		//System.out.println("rotate pdf service implementation: "+rotateValue);
		String finalfileName = "";
		List<String> listOfFileNames = new ArrayList<>();
		System.out.println(xcord);
		System.out.println(ycord);
		System.out.println(file[0].getOriginalFilename());
		System.out.println("page number Pdf impl");
		//System.out.println("size : "+ file.);
		
		for(int j=0;j<file.length;j++) {
			if (file[j].getOriginalFilename().contains(".pdf")) {
			try {
				
				
				if(uploadValue.equalsIgnoreCase("folder")) {
					System.out.println("folder value");

					////////////////////////////////////// Split by directory /////////////////////////////
					String fileName = file[j].getOriginalFilename().replace("\\", "/");
					String[] splittedFileName = fileName.split("/");
					String filenamefolder = splittedFileName[splittedFileName.length-1];
			        //////////////////////////////////////////////////////////////////////////////////
					file[j].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\" + filenamefolder));

					String timestampString = String.valueOf(timestamp);
					File fileOriginalName = new File(
							pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\" + filenamefolder);
					finalfileName = filenamefolder.replace(".pdf", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					System.out.println(finalfileName);
					File rename = new File(pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
				}
				else {
					file[j].transferTo(
							new File(pathDrive+"\\PdfGenereatedFiles\\AddPageNoPdf\\" + file[j].getOriginalFilename()));
					String timestampString = String.valueOf(timestamp);
					File fileOriginalName = new File(
							pathDrive+"\\PdfGenereatedFiles\\AddPageNoPdf\\" + file[j].getOriginalFilename());
					finalfileName = file[j].getOriginalFilename().replace(".pdf", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					System.out.println(finalfileName);
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\AddPageNoPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
				}
			

				File filenew = new File(pathDrive+"//PdfGenereatedFiles/AddPageNoPdf/" + finalfileName);
				PDDocument document1 = PDDocument.load(filenew);
				
				//PDDocument doc = PDDocument.load(new File("file.pdf"));
				int count = document1.getNumberOfPages();
				System.out.println("size : "+ count);
				int page_counter = 1;
				String numberingFormat = "Page {0}";
				int offset_X = Integer.valueOf(xcord); 
				System.out.println("offset_X---->"+offset_X);
				int offset_Y = Integer.valueOf(ycord);
				System.out.println("offset_Y---->"+offset_Y);

				PDPage page1;
				for(int i=0;i<count;i++) {
				page1 =document1.getPage(i);
				
				 PDPageContentStream contentStream = new PDPageContentStream(document1, page1, PDPageContentStream.AppendMode.APPEND, true, false);
		            contentStream.beginText();
		            contentStream.setFont(PDType1Font.HELVETICA, 10);
		            
		            PDRectangle pageSize = page1.getMediaBox();
		            float x = pageSize.getLowerLeftX();
		            float y = pageSize.getLowerLeftY();
		            contentStream.newLineAtOffset(x+ pageSize.getWidth()-offset_X, y+offset_Y);
		            String text = MessageFormat.format(numberingFormat,page_counter);
		            contentStream.showText(text);
		            contentStream.endText();
		            contentStream.close();
		            page_counter++;
		            
				}
			 
			 //File finalFileName = new File(pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\"+finalfileName);
			 listOfFileNames.add(pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\"+finalfileName);
			 document1.save(pathDrive + "\\PdfGenereatedFiles\\AddPageNoPdf\\"+finalfileName);
			 document1.close();
			 
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong.");
			}
		}
		}
		System.out.println("list of file names : "+listOfFileNames);
		return listOfFileNames;
	}
}
