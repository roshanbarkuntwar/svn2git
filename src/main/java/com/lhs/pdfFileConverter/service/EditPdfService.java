package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface EditPdfService {
	List<String> rotatePdf(MultipartFile[] file,String[] indexArrays,String uploadValue,String pathDrive,int rotateValue, HttpServletResponse response);

	List<String> pagenoPdf(MultipartFile[] file, String  indexArrays, String xcord, String ycord, String uploadValue,String pathDrive, HttpServletResponse response);
}
