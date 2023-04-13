package com.lhs.pdfFileConverter.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class OptimizedPdfServiceImpl implements OptimizedPdfService {

	@Override
	public List<String> compressPdf(MultipartFile[] file, String indexArray, String uploadValue, String pathDrive,
			HttpServletResponse response) {
		List<String> listOfFileNames = new ArrayList<>();
		System.out.println("compress pdf service impl");
///////////////////////////Read all files of a folder//////////////////////////////////////////////////////
		File folder = new File("E://PdfGenereatedFiles/CompressPdf/");
		File[] listOfFiles = folder.listFiles();

		for (File fileFolder : listOfFiles) {
			if (fileFolder.isFile()) {
				System.out.println(fileFolder.getName());
			}
		}
//////////////////////////////////////////////////

		if (file[0].getOriginalFilename().contains(".pdf")) {
			Long datetime = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(datetime);
			System.out.println(file[0].getOriginalFilename());
			PdfReader reader;
			String finalfileName = "";
			try {
				System.out.println("pointer comes in compress pdf services");
				file[0].transferTo(new File(pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\" + file[0].getOriginalFilename()));

				String timestampString = String.valueOf(timestamp);
				File fileOriginalName = new File(pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\" + file[0].getOriginalFilename());
				finalfileName = file[0].getOriginalFilename().replace(".pdf", "") + "_"
						+ timestampString.replace(".", "").replace("-", "").replace(":", "").trim() + ".pdf";
				System.out.println(finalfileName);
				File rename = new File(pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\" + finalfileName);
				fileOriginalName.renameTo(rename);

				reader = new PdfReader(pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\" + finalfileName);
				PdfStamper stamper = new PdfStamper(reader,
						new FileOutputStream(pathDrive+"\\PdfGenereatedFiles\\CompressPdf\\compress_" + finalfileName),
						PdfWriter.VERSION_1_5);
				stamper.getWriter().setCompressionLevel(9);
				int total = reader.getNumberOfPages() + 1;
				for (int i = 1; i < total; i++) {
					reader.setPageContent(i, reader.getPageContent(i));
				}
				stamper.setFullCompression();
				stamper.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong.");
			} catch (DocumentException e) {
				e.printStackTrace();
				throw new RuntimeException("Something went wrong.");
			}
			//File fileprotected = new File(pathDrive+"//PdfGenereatedFiles/CompressPdf/compress_" + finalfileName);
			listOfFileNames.add(pathDrive+"//PdfGenereatedFiles/CompressPdf/compress_" + finalfileName);
			//contentDispositonSinglePdf(fileprotected, response);
			//return "success";
		} else {
			throw new RuntimeException("Please enter pdf file.");
		}
		return listOfFileNames;
	}

}
