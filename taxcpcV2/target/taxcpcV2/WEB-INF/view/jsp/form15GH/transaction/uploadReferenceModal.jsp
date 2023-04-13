<%-- 
    Document   : uploadReferenceModal
    Created on : Mar 28, 2019, 10:43:36 AM
    Author     : aniket.naik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">     
        <!--Base Layout CSS-->
        <link rel="stylesheet" href="resources/css/main/layout.css" type="text/css">
        <!--Global form,table,button CSS-->
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">
        <!--jQuery-->
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        <!--Bootstrat JS-->
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script src="resources/js/form15GH/deducteeManage15GHDetail.js?r=<%=Math.random()%>" type="text/javascript"></script>
        <script src="resources/js/form15GH/uploadReferenceModal.js?r=<%=Math.random()%>" type="text/javascript"></script>
    </head>
    <body>
        <s:form id="excelReferenceNoFiles" name="excelReferenceNoFiles" action="importMasterExcelFiles?validate=true" 
                method="post" theme="css_xhtml" enctype="multipart/form-data" autocomplete="off">

            <s:hidden id="GenClientCode" name="GenClientCode" value="%{GenClientCode}"/>
            <div class="row form-group m-0">
                <div class="col-4">
                    <label class="control-label">
                        Attachment <span class="font-weight-bold text-danger ml-1">*</span>
                    </label>
                </div>
                <div class="col-8">
                    <div class="custom-file">
                        <s:file title="Upload only Excel File" id="template" name="obj_exl_html.template" TYPE="file" cssClass="filestyle choose-file-btn" data-buttonText="Choose file" data-buttonName="btn-primary choose-file-btn" cssStyle="width: 100%;" onchange="checkFile(event);">
                        </s:file>
                        <input type="file" class="custom-file-input form-control" title="Upload only Excel File" onchange="checkFile(event);">
                    </div>
                </div>         
            </div>
            <div class="row form-group m-0 text-center">
                <div class="col-10">
                    <button type="button" class="form-btn" onclick="uploadReferenceXls();"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                </div>
            </div>          
        </s:form>
    </body>
</html>
