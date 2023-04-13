package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface ConvertFromPdfService {
	List<String> convertPdfTojpg(MultipartFile[] file,HttpServletResponse response,String pathDrive,String uploadValue);
}
