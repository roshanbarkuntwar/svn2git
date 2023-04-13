<%-- 
    Document   : filesToDwnld
    Created on : Sep 16, 2016, 6:11:20 PM
    Author     : bhawna.agrawal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<s:if test="%{zipFileName!=null}">
    <s:url id="fileDownload" namespace="/" action="downloadGeneratedFileCorrection">
        <s:param name="filePath"><s:property value="zipFileLoc"/></s:param>
        <s:param name="fname"><s:property value="zipFileName"/></s:param>
    </s:url>

    <s:a href="%{fileDownload}" class="btn taxcpc-general-btn">
        <button type="button" class="form-btn" ><i class="fa fa-download" aria-hidden="true"></i><s:property value="genFileDwnldBtnText"/></button>       
        </s:a> 




</s:if>
<s:if test="%{textFileName!=null}">
    <s:url id="fileDownloadTxt" namespace="/" action="downloadGeneratedFileCorrection">
        <s:param name="filePath"><s:property value="textFileLoc"/></s:param>
        <s:param name="fname"><s:property value="textFileName"/></s:param>
    </s:url>
    <s:a href="%{fileDownloadTxt}" class="btn taxcpc-general-btn">   <button type="button" class="form-btn" ><i class="fa fa-download" aria-hidden="true"></i>Download Text File</button>    
    </s:a>
</s:if>
