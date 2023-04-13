package com.lhs.pdfFileConverter.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganisePdfServiceImpl implements OrganisePdfService {

	@Override
	public List<String> mergePdf(MultipartFile[] file, String[] indexArrays, String uploadValue, String pathDrive,
			HttpServletResponse response) {
		System.out.println("merge Pdf Service Implementation");
		//System.out.println(indexArrays[1]);
		System.out.println(file.length);

		
		if (file[0].getOriginalFilename().contains(".pdf")) {

			Long datetime = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(datetime);
			System.out.println("merge pdf service implementation: ");
			
			String finalfileName = "";
                try {  
              
                //////////////////////////////////merge pdf///////////////////////////////////
                PDFMergerUtility PDFmerger = new PDFMergerUtility();
               
                PDFmerger.setDestinationFileName(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//" +file[0].getOriginalFilename());
                
                
                
                for(int i=0;i<indexArrays.length;i++) {
                        //PDFmerger.addSource(file[i].getInputStream());
                        File tempFile = convertMultiPartToFile(file[Integer.valueOf(indexArrays[i])]);
					PDFmerger.addSource(tempFile);
					
				}
                PDFmerger.mergeDocuments(null);
		        System.out.println("PDF Documents merged to a single file successfully");
                
                } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
		} else {
			throw new RuntimeException("Please enter pdf file.");
	}
		return null;
	}

    public  File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
      }

	@Override
	public List<String> splitPdf(MultipartFile[] file, String[] indexArrays, String uploadValue, String pathDrive,
			HttpServletResponse response) {
	System.out.println("split pdf service implementation");
	
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("splitPdf");
		List<PDDocument> Pages = null;
		String finalfileName=""; 
		
		
		try {
			file[0].transferTo(
					new File(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\" + file[0].getOriginalFilename()));
			
			String timestampString = String.valueOf(timestamp);
			File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\" + file[0].getOriginalFilename());

			 finalfileName = file[0].getOriginalFilename().replace(".pdf", "") + "_"
					+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
			System.out.println(finalfileName);
			File rename = new File(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\" + finalfileName);
			fileOriginalName.renameTo(rename);
			System.out.println("File created");
			
			
			  InputStream is = null;
			    OutputStream os = null;
			    try {
			        is = new FileInputStream(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\"+finalfileName);
			        os = new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\OriginalFile\\Original_"+finalfileName);
			        byte[] buffer = new byte[1024];
			        int length;
			        while ((length = is.read(buffer)) > 0) {
			            os.write(buffer, 0, length);
			        }
			    } finally {
			        is.close();
			        os.close();
			    }
			
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong");
		}

		try (PDDocument document = PDDocument.load(
				new File(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\" + finalfileName))) {
			Splitter splitter = new Splitter();
			Pages = splitter.split(document);

			Iterator<PDDocument> iterator = Pages.listIterator();
			int i = 1;
			while (iterator.hasNext()) {
				PDDocument pd = iterator.next();
				pd.save(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\split_" + i + "_"+finalfileName);
				i++;
			}
			document.close();
		} catch (IOException e) {
			System.err.println("Exception while trying to read pdf document - " + e);
			throw new RuntimeException("Something went wrong");
		}

		List<String> listOfFileNames = new ArrayList<>();
		for (int i = 1; i <= Pages.size(); i++) {
			listOfFileNames.add(pathDrive+"\\PdfGenereatedFiles\\splitPdf\\split_" + i + "_"+finalfileName);
		}
		return listOfFileNames;
	}

	@Override
	public List<String> organizePdf(MultipartFile[] file, String[] indexArrays, String uploadValue, String pathDrive,
			HttpServletResponse response) {
		System.out.println("organize Pdf Service Implementation");
		//System.out.println(indexArrays[1]);
		List<String> listOfFileNames = new ArrayList<>();
		System.out.println(file.length);
		PDDocument document = new PDDocument();
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getOriginalFilename());
		}
                try {  
                //////////////////////////////////merge pdf///////////////////////////////////
                PDFMergerUtility PDFmerger = new PDFMergerUtility();
                PDFmerger.setDestinationFileName(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename() );
                listOfFileNames.add(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
                for(int i=0;i<file.length;i++) {
                        //PDFmerger.addSource(file[i].getInputStream());
                        File tempFile = convertMultiPartToFile(file[Integer.valueOf(i)]);
					PDFmerger.addSource(tempFile);
				}
                PDFmerger.mergeDocuments(null);
		        System.out.println("PDF Documents merged to a single file successfully");
                } catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("index array : "+indexArrays[0]+" index arrray length :"+indexArrays.length);
		        
		       // document1.close();
				  
		        
                
                try {
					PDDocument document1 = PDDocument.load(
							new File(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename()));
					System.out.println("document1 "+document1.getNumberOfPages());
					for(int i=0;i<indexArrays.length;i++) {
						document.addPage(document1.getPage(Integer.valueOf(indexArrays[i])));
						
					}
					document.save(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
					//document.save(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//tejas135.pdf");
					document.close();
					document1.close();
				} catch (InvalidPasswordException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		return listOfFileNames;
	}

	@Override
	public List<String> organizesortedPdf(MultipartFile[] file, String[] indexArrays, String uploadValue,
			String pathDrive, HttpServletResponse response) {
		System.out.println("organize Pdf Service sorted Implementation");
		//System.out.println(indexArrays[1]);
		List<String> listOfFileNames = new ArrayList<>();
		System.out.println(file.length);
		 if(file.length>1) {
				PDDocument document = new PDDocument();
				for(int i=0;i<file.length;i++) {
					System.out.println(file[i].getOriginalFilename());
				}
				try {  
					 File indexFile = convertMultiPartToFile(file[0]);
						PDDocument indexdocument = PDDocument.load(indexFile);
						System.out.println("index document pages : "+indexdocument);
						
						for(int i=0;i<indexArrays.length;i++) {
							document.addPage(indexdocument.getPage(Integer.valueOf(indexArrays[i])));
							
						}
						document.save(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
					
						document.close();
						indexdocument.close();
						
						File indexfilesorted=(new File(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename()));
						
						//File filemulitiart = new File("src/test/resources/input.txt");
						//FileInputStream input = new FileInputStream(filemulitiart);
//						MultipartFile multipartFile = new MockMultipartFile("filemulitiart",
//								filemulitiart.getName(), "text/plain", IOUtils.toByteArray(input));
					
		                //////////////////////////////////merge pdf///////////////////////////////////
		                PDFMergerUtility PDFmerger = new PDFMergerUtility();
		                PDFmerger.setDestinationFileName(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename() );
		                listOfFileNames.add(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
		                PDFmerger.addSource(indexfilesorted);
		                
		                if(file.length>1) {
		                	
		                	System.out.println("only above one file is merged");
		                	   for(int i=1;i<file.length;i++) {
		                           //PDFmerger.addSource(file[i].getInputStream());
		                           File tempFile = convertMultiPartToFile(file[Integer.valueOf(i)]);
		   					PDFmerger.addSource(tempFile);
		   				}
		                }
		             
		                PDFmerger.mergeDocuments(null);
				        System.out.println("PDF Documents merged to a single file successfully");
		                } catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
				        System.out.println("index array : "+indexArrays[0]+" index arrray length :"+indexArrays.length);

				       // System.out.println(listOfFileNames);
		 }
		 else {
				
				try {
					System.out.println("index array length: "+indexArrays.length);
					PDDocument docFinal = new PDDocument();
					PDDocument indexFinaldocument;
					indexFinaldocument = PDDocument.load(new File(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename() ));
					System.out.println("index document pages : "+indexFinaldocument.getNumberOfPages());
					for(int i=0;i<indexArrays.length;i++) {
						docFinal.addPage(indexFinaldocument.getPage(Integer.valueOf(indexArrays[i])));
					}
					docFinal.save(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
					
					docFinal.close();
					indexFinaldocument.close();
					 listOfFileNames.add(pathDrive+"//PdfGenereatedFiles//merge//multiplefile//"+file[0].getOriginalFilename());
					
				} catch (InvalidPasswordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		 }
		
		

		return listOfFileNames;
	}
}
