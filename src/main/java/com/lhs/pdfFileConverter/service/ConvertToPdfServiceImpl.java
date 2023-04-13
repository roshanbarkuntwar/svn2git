package com.lhs.pdfFileConverter.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
//import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.parser.Path;
import com.itextpdf.text.pdf.parser.clipper.Paths;

//import com.itextpdf.text.log.SysoCounter;

@Service
public class ConvertToPdfServiceImpl implements ConvertToPdfService{
	
	LocalDate now = LocalDate.now();
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
		  String date = formatter.format(now);
	       

	@Override
//	public List<String> convertjpgTopdf(MultipartFile[] file, HttpServletResponse response, String pathDrive,
//			String uploadValue) {
	public List<String> convertjpgTopdf(MultipartFile[] file, HttpServletResponse response, String pathDrive,
			String uploadValue,String downloadType) {
		
		////////////////////////create folder for original file///////////////////
		 System.out.println(formatter.format(now));
			File f = new File( pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\OriginalFile\\" +date);
			f.mkdir();

		//////////////////////////////////////////////////////////////////////////
		
		
		System.out.println("convert to jpg impl");
		List<String> listOfFileNames = new ArrayList<>();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("jpg to pdf service impl class");
		System.out.println(file[0].getOriginalFilename());
		String jpgTopdfReplace = "";
		String timestampString = String.valueOf(timestamp);
		String finalfileName = "";

		
		
		for(int i=0;i<file.length;i++) {
			
			
			
			if(uploadValue.equalsIgnoreCase("folder")) {
				
//////////////////////////////////////Split by directory /////////////////////////////
	String fileName = file[i].getOriginalFilename().replace("\\", "/");
	String[] splittedFileName = fileName.split("/");
	String filenamefolder = splittedFileName[splittedFileName.length-1];
 //////////////////////////////////////////////////////////////////////////////////
				///////////////////remove folder directory////////////////////////////////////////////
				if (filenamefolder.contains(".jpg")) {
					jpgTopdfReplace = filenamefolder.replace(".jpg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					// jpgTopdfReplace = file.getOriginalFilename().replace(".jpg", ".pdf");
					finalfileName = filenamefolder.replace(".jpg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".jpg";
				} else if (file[i].getOriginalFilename().contains(".jpeg")) {
					jpgTopdfReplace = filenamefolder.replace(".jpeg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					// jpgTopdfReplace = file.getOriginalFilename().replace(".jpeg", ".pdf");
					finalfileName = filenamefolder.replace(".jpeg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".jpeg";

				} else {
					throw new RuntimeException("Please input correct file format");
				}
				System.out.println("pdf name: " + jpgTopdfReplace);
				System.out.println("jpgTopdf " + file[i].getOriginalFilename());
				///////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
			}else {
				if (file[i].getOriginalFilename().contains(".jpg")) {
					jpgTopdfReplace = file[i].getOriginalFilename().replace(".jpg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					// jpgTopdfReplace = file.getOriginalFilename().replace(".jpg", ".pdf");
					finalfileName = file[i].getOriginalFilename().replace(".jpg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".jpg";
				} else if (file[i].getOriginalFilename().contains(".jpeg")) {
					jpgTopdfReplace = file[i].getOriginalFilename().replace(".jpeg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					// jpgTopdfReplace = file.getOriginalFilename().replace(".jpeg", ".pdf");
					finalfileName = file[i].getOriginalFilename().replace(".jpeg", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".jpeg";

				} else {
					throw new RuntimeException("Please input correct file format");
				}
				System.out.println("pdf name: " + jpgTopdfReplace);
				System.out.println("jpgTopdf " + file[i].getOriginalFilename());
				
			}
			
			////////////////////////////////////////////////////////////////
			
			
			try {
				if (uploadValue.equalsIgnoreCase("folder")) {
                    //////////////////////////////////////Split by directory /////////////////////////////
					String fileName = file[i].getOriginalFilename().replace("\\", "/");
					String[] splittedFileName = fileName.split("/");
					String filenamefolder = splittedFileName[splittedFileName.length - 1];
					//////////////////////////////////////////////////////////////////////////////////
					file[i].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\JpgToPdf\\" + filenamefolder));

					File fileOriginalName = new File(pathDrive + "\\PdfGenereatedFiles\\JpgToPdf\\" + filenamefolder);
					System.out.println(finalfileName);
					File rename = new File(pathDrive + "\\PdfGenereatedFiles\\JpgToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
					
				
				}
				else {
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + file[i].getOriginalFilename()));

					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + file[i].getOriginalFilename());
					System.out.println(finalfileName);
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
					System.out.println(date   +"-----------------");
				}
				
				
				
				
				
				
				  InputStream is = null;
				    OutputStream os = null;
				    try {
				        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + finalfileName);
				        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\OriginalFile\\"+date+"\\" + finalfileName);
				        byte[] buffer = new byte[1024];
				        int length;
				        while ((length = is.read(buffer)) > 0) {
				            os.write(buffer, 0, length);
				        }
				    } finally {
				        is.close();
				        os.close();
				    }
				
				
				System.out.println("jpgTopdf");
				// File file1 = new File("J:\\PdfGenereatedFiles\\JpgToPdf\\" + "new.pdf");
				PDDocument doc = new PDDocument();
				try {
					PDPage page = new PDPage();
					doc.addPage(page);
					PDImageXObject pdImage = PDImageXObject
							.createFromFile(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + finalfileName, doc);
					
					int pdfwidth=((int) page.getMediaBox().getWidth());
					int pdfheight=((int) page.getMediaBox().getHeight());
					int imageWidth = pdImage.getWidth();
					int imageHeight =  pdImage.getWidth();
					
					if(imageWidth<=pdfwidth || imageHeight<=pdfheight) {
						PDPageContentStream contentStream = new PDPageContentStream(doc, page,
								PDPageContentStream.AppendMode.APPEND, true);
						float x_pos = page.getCropBox().getWidth();
						float y_pos = page.getCropBox().getHeight();
						float x_adjusted = (x_pos - imageWidth) / 2;
						float y_adjusted = (y_pos - imageHeight) / 2;
						contentStream.drawImage(pdImage, x_adjusted, y_adjusted, imageWidth, imageHeight);
						contentStream.close();
						doc.save(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + jpgTopdfReplace);
						doc.close();
					}
					else { 
						PDPageContentStream content1 = new PDPageContentStream(doc, page);
//						content1.drawImage(pdImage, 25, 100);
						content1.drawImage(pdImage, 5, 250, 600,400);
						content1.close();
						// String[] pdfname = file.getOriginalFilename().split(".");
						doc.save(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + jpgTopdfReplace);
					}
					System.out.println("image inserted");
					listOfFileNames.add(jpgTopdfReplace);
					//File file2 = new File(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + jpgTopdfReplace);
					//contentDispositonSinglePdf(file2, response);
				} finally {
					doc.close();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			}
			//return "success";	
		}
		System.out.println(listOfFileNames);
		if(downloadType.equalsIgnoreCase("pdfJointPage")) {
			
		}else {
			
		}
       String createdFile="merged_"+timestampString.replace(".", "").replace("-", "").replace(":", "").trim()+".pdf";
		
		try {
			PDFMergerUtility obj = new PDFMergerUtility();
			obj.setDestinationFileName(pathDrive+"//PdfGenereatedFiles/JpgToPdf/" + createdFile);
			for (int k = 0; k < listOfFileNames.size(); k++) {
				System.out.println( listOfFileNames.get(k));
				if (listOfFileNames.get(k).contains(".pdf")) {
					obj.addSource(new File(pathDrive+"//PdfGenereatedFiles/JpgToPdf/" + listOfFileNames.get(k)));
					System.out.println((pathDrive+"//PdfGenereatedFiles/JpgToPdf/" + listOfFileNames.get(k)));
				}
			}
			System.out.println(obj);
			obj.mergeDocuments();
			System.out.println("PDF Documents merged to a single file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(createdFile);
          if(downloadType.equalsIgnoreCase("pdfJointPage")) {
        	  System.out.println(downloadType);
      		listOfFileNames.removeAll(listOfFileNames);
			listOfFileNames.add(createdFile);
        	     System.out.println(listOfFileNames);
			
		}else {
			System.out.println(downloadType);
			System.out.println(listOfFileNames);
		}	
	
		return listOfFileNames;
	}

	
	
	
	@Override
	public List<String> convertpngTopdf(MultipartFile[] file, HttpServletResponse response, String pathDrive,
			String uploadValue,String downloadType) {
		
////////////////////////create folder for original file///////////////////
System.out.println(formatter.format(now));
File f = new File( pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\OriginalFile\\" +date);
f.mkdir();
//////////////////////////////////////////////////////////////////////////
		
		List<String> listOfFileNames = new ArrayList<>();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		String pngTopdfReplace = "";
		String timestampString = String.valueOf(timestamp);
		String finalfileName = "";

		
		
		for(int i=0;i<file.length;i++) {
			
			
			
			if(uploadValue.equalsIgnoreCase("folder")) {
				
//////////////////////////////////////Split by directory /////////////////////////////
	String fileName = file[i].getOriginalFilename().replace("\\", "/");
	String[] splittedFileName = fileName.split("/");
	String filenamefolder = splittedFileName[splittedFileName.length-1];
 //////////////////////////////////////////////////////////////////////////////////
				///////////////////remove folder directory////////////////////////////////////////////
				if (filenamefolder.contains(".png")) {
					pngTopdfReplace = filenamefolder.replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					finalfileName = filenamefolder.replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".png";
				} else if (file[i].getOriginalFilename().contains(".png")) {
					pngTopdfReplace = filenamefolder.replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					finalfileName = filenamefolder.replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".png";

				} else {
					throw new RuntimeException("Please input correct file format");
				}
				System.out.println("pdf name: " + pngTopdfReplace);
				System.out.println("jpgTopdf " + file[i].getOriginalFilename());
				///////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
			}else {
				System.out.println(file[i].getOriginalFilename());
				if (file[i].getOriginalFilename().contains(".png")) {
					pngTopdfReplace = file[i].getOriginalFilename().replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					finalfileName = file[i].getOriginalFilename().replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".png";
				} else if (file[i].getOriginalFilename().contains(".png")) {
					pngTopdfReplace = file[i].getOriginalFilename().replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
					finalfileName = file[i].getOriginalFilename().replace(".png", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".png";

				} else {
					throw new RuntimeException("Please input correct file format");
				}
				System.out.println("pdf name: " + pngTopdfReplace);
				System.out.println("PngToPdf " + file[i].getOriginalFilename());
				
			}
			
			////////////////////////////////////////////////////////////////
			
			
			try {
				if (uploadValue.equalsIgnoreCase("folder")) {
                    //////////////////////////////////////Split by directory /////////////////////////////
					String fileName = file[i].getOriginalFilename().replace("\\", "/");
					String[] splittedFileName = fileName.split("/");
					String filenamefolder = splittedFileName[splittedFileName.length - 1];
					//////////////////////////////////////////////////////////////////////////////////
					file[i].transferTo(new File(pathDrive + "\\PdfGenereatedFiles\\PngToPdf\\" + filenamefolder));

					File fileOriginalName = new File(pathDrive + "\\PdfGenereatedFiles\\PngToPdf\\" + filenamefolder);
					System.out.println(finalfileName);
					File rename = new File(pathDrive + "\\PdfGenereatedFiles\\PngToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
					
					  InputStream is = null;
					    OutputStream os = null;
					    try {
					        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + finalfileName);
					        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\OriginalFile\\"+date+"\\" + finalfileName);
					        byte[] buffer = new byte[1024];
					        int length;
					        while ((length = is.read(buffer)) > 0) {
					            os.write(buffer, 0, length);
					        }
					    } finally {
					        is.close();
					        os.close();
					    }
				}
				else {
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + file[i].getOriginalFilename()));

					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + file[i].getOriginalFilename());
					System.out.println(finalfileName);
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
					
					  InputStream is = null;
					    OutputStream os = null;
					    try {
					        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + finalfileName);
					        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\OriginalFile\\"+date+"\\" + finalfileName);
					        byte[] buffer = new byte[1024];
					        int length;
					        while ((length = is.read(buffer)) > 0) {
					            os.write(buffer, 0, length);
					        }
					    } finally {
					        is.close();
					        os.close();
					    }
				}
				
				System.out.println("PngToPdf");
				// File file1 = new File("J:\\PdfGenereatedFiles\\JpgToPdf\\" + "new.pdf");
				PDDocument doc = new PDDocument();
				try {
					PDPage page = new PDPage();
					doc.addPage(page);
					PDImageXObject pdImage = PDImageXObject
							.createFromFile(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + finalfileName, doc);
					
					int pdfwidth=((int) page.getMediaBox().getWidth());
					int pdfheight=((int) page.getMediaBox().getHeight());
					int imageWidth = pdImage.getWidth();
					int imageHeight =  pdImage.getWidth();
					
					if(imageWidth<=pdfwidth || imageHeight<=pdfheight) {
						PDPageContentStream contentStream = new PDPageContentStream(doc, page,
								PDPageContentStream.AppendMode.APPEND, true);
						float x_pos = page.getCropBox().getWidth();
						float y_pos = page.getCropBox().getHeight();
						float x_adjusted = (x_pos - imageWidth) / 2;
						float y_adjusted = (y_pos - imageHeight) / 2;
						contentStream.drawImage(pdImage, x_adjusted, y_adjusted, imageWidth, imageHeight);
						contentStream.close();
						doc.save(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + pngTopdfReplace);
						doc.close();
					}
					else { 
						PDPageContentStream content1 = new PDPageContentStream(doc, page);
//						content1.drawImage(pdImage, 25, 100);
						content1.drawImage(pdImage, 5, 250, 600,400);
						content1.close();
						// String[] pdfname = file.getOriginalFilename().split(".");
						doc.save(pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\" + pngTopdfReplace);
					}
					System.out.println("image inserted");
					listOfFileNames.add(pngTopdfReplace);
					//File file2 = new File(pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\" + jpgTopdfReplace);
					//contentDispositonSinglePdf(file2, response);
				} finally {
					doc.close();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong");
			}
			//return "success";	
		}
		System.out.println(listOfFileNames);
       String createdFile="merged_"+timestampString.replace(".", "").replace("-", "").replace(":", "").trim()+".pdf";
		
		try {
			PDFMergerUtility obj = new PDFMergerUtility();
			obj.setDestinationFileName(pathDrive+"//PdfGenereatedFiles/PngToPdf/" + createdFile);
			for (int k = 0; k < listOfFileNames.size(); k++) {
				System.out.println( listOfFileNames.get(k));
				if (listOfFileNames.get(k).contains(".pdf")) {
					obj.addSource(new File(pathDrive+"//PdfGenereatedFiles/PngToPdf/" + listOfFileNames.get(k)));
					System.out.println((pathDrive+"//PdfGenereatedFiles/PngToPdf/" + listOfFileNames.get(k)));
				}
			}
			System.out.println(obj);
			obj.mergeDocuments();
			System.out.println("PDF Documents merged to a single file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(createdFile);
		
		
		
        if(downloadType.equalsIgnoreCase("pdfJointPage")) {
      	  System.out.println(downloadType);
    		listOfFileNames.removeAll(listOfFileNames);
			listOfFileNames.add(createdFile);
      	     System.out.println(listOfFileNames);
			
		}else {
			System.out.println(downloadType);
			System.out.println(listOfFileNames);
		}	
	
	
		return listOfFileNames;
	}




	@Override
	public List<String> converttiffTopdf(MultipartFile[] file, HttpServletResponse response, String pathDrive,
			String uploadValue,String downloadType) {
		System.out.println("convert to tiff to pdf service impl");
////////////////////////create folder for original file///////////////////
System.out.println(formatter.format(now));
File f = new File( pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\OriginalFile\\" +date);
f.mkdir();
//////////////////////////////////////////////////////////////////////////
		
		
		String finalfileName="";
		Long datetime = System.currentTimeMillis();
		List<String> listOfFileNames = new ArrayList<>();
		Timestamp timestamp = new Timestamp(datetime);
		String timestampString="";
		System.out.println("tiff to pdf service impl class");
		//System.out.println(file[i].getOriginalFilename());
		String tiffTopdfReplace = "";
		for(int i=0;i<file.length;i++) {
			
			
			
			
			System.out.println(i);
			System.out.println(file[i].getOriginalFilename());
			if (file[i].getOriginalFilename().contains(".tiff")) {
				tiffTopdfReplace = file[i].getOriginalFilename().replace(".tiff", ".pdf");
			} else if (file[i].getOriginalFilename().contains(".TIFF")) {
				tiffTopdfReplace = file[i].getOriginalFilename().replace(".TIFF", ".pdf");
			} else {
				throw new RuntimeException("Please input correct file format");
			}
			tiffTopdfReplace = file[i].getOriginalFilename() + ".pdf";
			try {
				
				if (uploadValue.equalsIgnoreCase("folder")) {
					
                    //////////////////////////////////////Split by directory /////////////////////////////
					String fileName = file[i].getOriginalFilename().replace("\\", "/");
					String[] splittedFileName = fileName.split("/");
					String filenamefolder = splittedFileName[splittedFileName.length - 1];
					//////////////////////////////////////////////////////////////////////////////////
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + filenamefolder));
					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + filenamefolder);
					timestampString = String.valueOf(timestamp);
				    finalfileName = filenamefolder.replace(".tiff", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "") + ".tiff";
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					System.out.println(finalfileName+" folder");
				}
				else {
					file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + file[i].getOriginalFilename()));
					File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + file[i].getOriginalFilename());
					timestampString = String.valueOf(timestamp);
				    finalfileName = file[i].getOriginalFilename().replace(".tiff", "") + "_"
							+ timestampString.replace(".", "").replace("-", "").replace(":", "") + ".tiff";
					File rename = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName);
					fileOriginalName.renameTo(rename);
					
				}
				
				
				  InputStream is = null;
				    OutputStream os = null;
				    try {
				        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName);
				        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\OriginalFile\\"+date+"\\" + finalfileName);
				        byte[] buffer = new byte[1024];
				        int length;
				        while ((length = is.read(buffer)) > 0) {
				            os.write(buffer, 0, length);
				        }
				    } finally {
				        is.close();
				        os.close();
				    }
				
//				file[i].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + file[i].getOriginalFilename()));
//				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + file[i].getOriginalFilename());
//				timestampString = String.valueOf(timestamp);
//			    finalfileName = file[i].getOriginalFilename().replace(".tiff", "") + "_"
//						+ timestampString.replace(".", "").replace("-", "").replace(":", "") + ".tiff";
//				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName);
//				fileOriginalName.renameTo(rename);
				PDDocument document = new PDDocument();
				ImageInputStream isb;
				try {
					File file1 = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName);
					//File file1 = new File("J:\\PdfGenereatedFiles\\TiffToPdf\\" + "file_example_TIFF_5MB_20230330 160915825.tiff");
					System.out.println(file1.getAbsolutePath());
					isb = ImageIO.createImageInputStream(file1);
					Iterator<ImageReader> iterator = ImageIO.getImageReaders(isb);
					if (iterator == null || !iterator.hasNext()) {
						throw new IOException("Image format file not supported by image io");
					}
					ImageReader reader = (ImageReader) iterator.next();
					iterator = null;
					reader.setInput(isb);
					int nbPages = reader.getNumImages(true);
					System.out.println(nbPages);

					for (int p = 0; p < nbPages; p++) {
						BufferedImage bufferdImage = reader.read(0);
						PDPage page = new PDPage();
						document.addPage(page);
						PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferdImage);
						
						
						int pdfwidth=((int) page.getMediaBox().getWidth());
						int pdfheight=((int) page.getMediaBox().getHeight());
						int imageWidth = pdImage.getWidth();
						int imageHeight =  pdImage.getWidth();

						
						if(imageWidth<=pdfwidth || imageHeight<=pdfheight) {
							PDPageContentStream contentStream = new PDPageContentStream(document, page,
									PDPageContentStream.AppendMode.APPEND, true);
							float x_pos = page.getCropBox().getWidth();
							float y_pos = page.getCropBox().getHeight();
							float x_adjusted = (x_pos - imageWidth) / 2;
							float y_adjusted = (y_pos - imageHeight) / 2;
							contentStream.drawImage(pdImage, x_adjusted+50, y_adjusted+75, imageWidth-100, imageHeight-100);
							contentStream.close();
//							document.save("J:\\PdfGenereatedFiles\\PngToPdf\\" +  finalfileName + ".pdf");
//							document.close();
						}
						else { 
							PDPageContentStream content1 = new PDPageContentStream(document, page);
//							content1.drawImage(pdImage, 25, 100);
							content1.drawImage(pdImage, 5, 250, 600,400);
							content1.close();
							// String[] pdfname = file.getOriginalFilename().split(".");
							// System.out.println(pdfname[pdfname.length-1]+".pdf");
//							document.save("J:\\PdfGenereatedFiles\\PngToPdf\\" +  finalfileName + ".pdf");
						}
						

						reader.abort();
						//content.close();
						reader.dispose();
						reader.abort();
						document.save(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName + ".pdf");
						listOfFileNames.add(finalfileName+".pdf");
						System.out.println("image inserted");
						File file2 = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + finalfileName + ".pdf");
						//contentDispositonSinglePdf(file2, response);
						isb.close();
					}
				} finally {
					document.close();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new RuntimeException("Some error occured");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Some error occured");
			}
			//String originalfilename = file[0].getOriginalFilename();
			System.out.println(listOfFileNames);
			File filedeletetiff = new File(pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\" + timestamp + file[i].getOriginalFilename());
//			 if (filedeletetiff.delete()) 
//			    {
//	                System.out.println(filedeletetiff.getName() + " is deleted!");
//	            } 
//			 else 
//	            {
//	                System.out.println("Sorry, unable to delete the file.");
//	            }
//			 
//			 File filedeletepdf = new File("D:\\gen_pdf\\"+tiffTopdfReplace);
//				
//				if (filedeletepdf.exists()) 
//				{
//					filedeletepdf.delete();
//			    }
			//return finalfileName+".pdf";
			
		}

		   String createdFile="merged_tiff_"+timestampString.replace(".", "").replace("-", "").replace(":", "").trim()+".pdf";
			
			try {
				PDFMergerUtility obj = new PDFMergerUtility();
				obj.setDestinationFileName(pathDrive+"//PdfGenereatedFiles/TiffToPdf/" + createdFile);
				for (int k = 0; k < listOfFileNames.size(); k++) {
					System.out.println( listOfFileNames.get(k));
					if (listOfFileNames.get(k).contains(".pdf")) {
						obj.addSource(new File(pathDrive+"//PdfGenereatedFiles/TiffToPdf/" + listOfFileNames.get(k)));
						System.out.println((pathDrive+"//PdfGenereatedFiles/TiffToPdf/" + listOfFileNames.get(k)));
					}
				}
				System.out.println(obj);
				obj.mergeDocuments();
				System.out.println("PDF Documents merged to a single file");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(createdFile);
		    if(downloadType.equalsIgnoreCase("pdfJointPage")) {
		      	  System.out.println(downloadType);
		    		listOfFileNames.removeAll(listOfFileNames);
					listOfFileNames.add(createdFile);
		      	     System.out.println(listOfFileNames);
					
				}else {
					System.out.println(downloadType);
					System.out.println(listOfFileNames);
				}
		return listOfFileNames;
	
		//return null;
	}

}
