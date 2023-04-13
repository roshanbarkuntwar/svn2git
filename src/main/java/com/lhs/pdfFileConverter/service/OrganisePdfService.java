package com.lhs.pdfFileConverter.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface OrganisePdfService {
	List<String> mergePdf(MultipartFile[] file,String[] indexArrays,String uploadValue,String pathDrive, HttpServletResponse response);
	List<String> splitPdf(MultipartFile[] file,String[] indexArrays,String uploadValue,String pathDrive, HttpServletResponse response);
	List<String> organizePdf(MultipartFile[] file,String[] indexArrays,String uploadValue,String pathDrive, HttpServletResponse response);
	List<String> organizesortedPdf(MultipartFile[] file,String[] indexArrays,String uploadValue,String pathDrive, HttpServletResponse response);
}
