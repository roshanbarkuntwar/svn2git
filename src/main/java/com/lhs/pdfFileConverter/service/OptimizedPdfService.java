package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface OptimizedPdfService {
	List<String> compressPdf(MultipartFile[] file,String indexArray,String uploadValue,String pathDrive,HttpServletResponse response);

}
