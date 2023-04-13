package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface PdfSecurityService {

	//String digitalSign();
	List<String> digitalSign(MultipartFile[] file,String signValue,String signReason,MultipartFile pfxfile, HttpServletResponse response,String pathDrive,String uploadValue);
	List<String> protectpassPdf(MultipartFile[] file,String password,String uploadValue,String pathDrive,HttpServletResponse response);
	List<String> removepassPdf(MultipartFile[] file,String password,String uploadValue,String pathDrive,HttpServletResponse response);
}

