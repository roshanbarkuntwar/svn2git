package com.lhs.pdfFileConverter.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ConvertFromPdfServiceImpl implements ConvertFromPdfService {

	@Override
	public List<String> convertPdfTojpg(MultipartFile[] file, HttpServletResponse response, String pathDrive,String uploadValue) {
		System.out.println("convertpdf to jpg impl");
		
		
		
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		String finalfileName = "";
		String finalAllPdflength="";
		 int AllPageSize=0;
		String finalAllPdfName = null; 
		List<String> listOfFileNames = new ArrayList<>();
		
		for(int i=0;i<file.length;i++) {
			//System.out.println(file[i].getOriginalFilename());
		}
		 
		 
		for(int j=0;j<file.length;j++) {
			//System.out.println(j+"___"+file[j].getOriginalFilename());
			if (file[j].getOriginalFilename().contains(".pdf")) {
				
				System.out.println("pdfTojpg");
				List<PDDocument> Pages = null;
				 finalfileName = "";
				try {
					System.out.println("pdfTojpg : "+file[0].getName());
					
					if(uploadValue.equalsIgnoreCase("folder")) {
						System.out.println("folder value");
						////////////////////////////////////// Split by directory /////////////////////////////
						String fileName = file[j].getOriginalFilename().replace("\\", "/");
						String[] splittedFileName = fileName.split("/");
						String filenamefolder = splittedFileName[splittedFileName.length-1];
				        //////////////////////////////////////////////////////////////////////////////////
						file[j].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + filenamefolder));

						String timestampString = String.valueOf(timestamp);
						File fileOriginalName = new File(
								pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + filenamefolder);
						finalfileName = filenamefolder.replace(".pdf", "") + "_"
								+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
						System.out.println(finalfileName);
						File rename = new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName);
						fileOriginalName.renameTo(rename);
						
					}
					else {
						file[j].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + file[j].getOriginalFilename()));

						String timestampString = String.valueOf(timestamp);
						File fileOriginalName = new File(
								pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + file[j].getOriginalFilename());
						finalfileName = file[j].getOriginalFilename().replace(".pdf", "") + "_"
								+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
						System.out.println(finalfileName);
						File rename = new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName);
						fileOriginalName.renameTo(rename);
					}

				} catch (Exception e) {
					 e.printStackTrace();
					throw new RuntimeException("Something went wrong.");
				}
				try  {
					PDDocument document = PDDocument
							.load(new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName));

					Splitter splitter = new Splitter();
					Pages = splitter.split(document);
					System.out.println(Pages.size());
					Iterator<PDDocument> iterator = Pages.listIterator();
					int i = 1;
					while (iterator.hasNext()) {
						PDDocument pd = iterator.next();
						pd.save(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\splitjpg_" + i + "_" + finalfileName);

						File file1 = new File(
								pathDrive + "//PdfGenereatedFiles/PdfToJpg/splitjpg_" + i + "_" + finalfileName);
						PDDocument document1;
						try {
							document1 = PDDocument.load(file1);
							PDFRenderer renderer = new PDFRenderer(document1);
							BufferedImage image = renderer.renderImage(0);
							ImageIO.write(image, "JPEG", new File(
									pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName + "_" + i + ".jpg"));
							// System.out.println("Image created " + i);
							listOfFileNames.add(finalfileName + "_" + i + ".jpg");
							
							
							
							document1.close();
						} catch (IOException e) {
							e.printStackTrace();
							throw new RuntimeException("Something went wrong.");
						}
						
						
					    ////////////////////////delete split pdf//////////////////
						File fileDeletejpg = new File(
								pathDrive + "//PdfGenereatedFiles/PdfToJpg/splitjpg_" + i + "_" + finalfileName);
						System.out.println(fileDeletejpg);
						if (fileDeletejpg.exists()) {
							fileDeletejpg.delete();
							System.out.println("file deleted successfully");
						}
						///////////////////////////////////////////////
						i++;
						
                        
					
					}
					document.close();
				} catch (IOException e) {
					System.err.println("Exception while trying to read pdf document - " + e);
					throw new RuntimeException("Something went wrong.");
				}
				
                 AllPageSize=AllPageSize+Pages.size();
				//String pagesSize = String.valueOf(Pages.size());
                 System.out.println("final file nameljlklk : "+ finalfileName);
                 //////////////////////////////
                 System.out.println("file page size : ");
                 finalAllPdfName=finalAllPdfName+","+finalfileName+"##"+Pages.size();
				//return finalfileName + "#" + pagesSize;
			} else {
				throw new RuntimeException("Please enter pdf file.");
			}
			
		}
		
		System.out.println("listOfFileNames : "+listOfFileNames);
		finalAllPdflength=String.valueOf(AllPageSize);
		//listOfFileNames.add(finalAllPdflength);
		
		System.out.println("impl");
		System.out.println(finalAllPdfName);
		for(int k=0;k<file.length;k++) {
			//finalname=finalname+","+finalAllPdfName[k];
		}
		
		//System.out.println("final name : "+finalname);
		
		
		//finalAllPdflength=String.valueOf(AllPageSize);
		System.out.println("final file name : "+ finalAllPdfName);
		System.out.println("final all pdf length: "+finalAllPdflength);
		//return finalAllPdfName + "#" + finalAllPdflength;
		return listOfFileNames;
		
		
//		if (file[0].getOriginalFilename().contains(".pdf")) {
//			Long datetime = System.currentTimeMillis();
//			Timestamp timestamp = new Timestamp(datetime);
//			System.out.println("pdfTojpg");
//			List<PDDocument> Pages = null;
//			String finalfileName = "";
//			try {
//				file[].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + file.getOriginalFilename()));
//
//				String timestampString = String.valueOf(timestamp);
//				File fileOriginalName = new File(
//						pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + file.getOriginalFilename());
//				finalfileName = file.getOriginalFilename().replace(".pdf", "") + "_"
//						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
//				System.out.println(finalfileName);
//				File rename = new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName);
//				fileOriginalName.renameTo(rename);
//
//			} catch (Exception e) {
//				throw new RuntimeException("Something went wrong.");
//			}
//			try (PDDocument document = PDDocument
//					.load(new File(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName))) {
//				Splitter splitter = new Splitter();
//				Pages = splitter.split(document);
//				System.out.println(Pages.size());
//				Iterator<PDDocument> iterator = Pages.listIterator();
//				int i = 1;
//				while (iterator.hasNext()) {
//					PDDocument pd = iterator.next();
//					pd.save(pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\splitjpg_" + i + "_" + finalfileName);
//
//					File file1 = new File(
//							pathDrive + "//PdfGenereatedFiles/PdfToJpg/splitjpg_" + i + "_" + finalfileName);
//					PDDocument document1;
//					try {
//						document1 = PDDocument.load(file1);
//						PDFRenderer renderer = new PDFRenderer(document1);
//						BufferedImage image = renderer.renderImage(0);
//						ImageIO.write(image, "JPEG", new File(
//								pathDrive + "\\PdfGenereatedFiles\\PdfToJpg\\" + finalfileName + "_" + i + ".jpg"));
//						// System.out.println("Image created " + i);
//						document1.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//						throw new RuntimeException("Something went wrong.");
//					}
//					i++;
//				}
//				document.close();
//			} catch (IOException e) {
//				System.err.println("Exception while trying to read pdf document - " + e);
//				throw new RuntimeException("Something went wrong.");
//			}
//
//			String pagesSize = String.valueOf(Pages.size());
//			return finalfileName + "#" + pagesSize;
//		} else {
//			throw new RuntimeException("Please enter pdf file.");
//		}

	}

}
