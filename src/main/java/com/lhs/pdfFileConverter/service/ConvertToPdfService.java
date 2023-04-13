package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface ConvertToPdfService {
	//List<String> convertjpgTopdf(MultipartFile[] file,HttpServletResponse response,String pathDrive,String uploadValue);
	List<String> convertjpgTopdf(MultipartFile[] file,HttpServletResponse response,String pathDrive,String uploadValue,String downloadType);
	List<String> convertpngTopdf(MultipartFile[] file,HttpServletResponse response,String pathDrive,String uploadValue,String downloadType);
	List<String> converttiffTopdf(MultipartFile[] file,HttpServletResponse response,String pathDrive,String uploadValue,String downloadType);

}


