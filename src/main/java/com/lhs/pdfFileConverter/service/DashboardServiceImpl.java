package com.lhs.pdfFileConverter.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Value("${pdfConverterDrive}")
    String pathDrive;
	
	
	
	
	
	
	@Override
	public void createDirectory() {
		System.out.println("PdfConverterService impl");
		System.out.println("stringValue    :"+pathDrive);

		   //System.out.println("Enter the path where you want to create a folder: ");  
		     // Scanner sc = new Scanner(System.in);  
		      String path ="";  
		      //Using Scanner class to get the folder name from the user  
		      //System.out.println("Enter the name of the desired a directory: ");  
		      path = pathDrive+"\\"+"PdfGenereatedFiles";  
		     // path = "F:\\PdfGenereatedFiles\\DigitalSignature\\OriginalFile";  
		      System.out.println(path);
		      //Instantiate the File class   
		      File f1 = new File(path); 
		      boolean bool = f1.mkdir(); 
		      
		      ///////////////////////////////////////////////////
		      path = pathDrive+"\\PdfGenereatedFiles\\DigitalSignature";  
		      f1 = new File(path); 
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\DigitalSignature\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir(); 
		       
		       
		       ////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\CompressPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       
		      //////////////////////////////////////////////////////////////////////// 
		      path = pathDrive+"\\PdfGenereatedFiles\\JpgToPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\JpgToPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      
		      ////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\merge";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		       
		      path = pathDrive+"\\PdfGenereatedFiles\\merge\\multiplefile";  
		      f1 = new File(path);
		      bool = f1.mkdir();  
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\merge\\multiplefile\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\merge\\zip";  
		      f1 = new File(path); 
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\merge\\zip\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       /////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\PasswordPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\PasswordPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      /////////////////////////////////////////////////////////////////////////////////
		      path = pathDrive+"\\PdfGenereatedFiles\\PdfToJpg";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\PdfToJpg\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       
		       /////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfToPng";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\PdfToPng\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       
		       //////////////////////////////////////////////////////////////////////////////////////////
		       
		      path = pathDrive+"\\PdfGenereatedFiles\\PngToPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\PngToPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       ///////////////////////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\RemovePassowrdPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       ////////////////////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\RotatePdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\RotatePdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       
		       //////////////////////////////////////////////////////////////////////////////////////
		      path = pathDrive+"\\PdfGenereatedFiles\\splitPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\splitPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		       ///////////////////////////////////////////////////////////////////////////////////
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\TiffToPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      path = pathDrive+"\\PdfGenereatedFiles\\TiffToPdf\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      //////////////////////////////////////////////////////////////////////////////////////////
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf\\image";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf\\image\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf\\text";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      
		      cleanDirectory(f1);
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf\\text\\OriginalFile";  
		      f1 = new File(path); 
		       bool = f1.mkdir();
		      
		      path = pathDrive+"\\PdfGenereatedFiles\\watermarkPdf\\image\\OrigninalPdffile";  
		      f1 = new File(path);
		      bool = f1.mkdir(); 
		      //////////////////////////////////////////////////////////////////////////////////////////////////////
		      //Creating a folder using mkdir() method  
		     
		      if(bool){  
		         System.out.println("Folder is created successfully");  
		      }else{  
		         System.out.println("Error Found!");  
		      } 
		
	}

	void cleanDirectory(File dir) {
		for (File file : dir.listFiles()) {
			if (file.getName().equals("OriginalFile")) {
				// do nothing
			} else {
				// delete file
				//file.delete();
			}
		}
	}
}
