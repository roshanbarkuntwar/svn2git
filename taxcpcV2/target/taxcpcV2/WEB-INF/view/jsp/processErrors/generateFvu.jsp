<%-- 
    Document   : generateFvu
    Created on : Jul 6, 2019, 12:48:18 PM
    Author     : ayushi.jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#generateFvuBtnAction"><p>Generate FVU File</p> </a></li>
                    <!--<li class="nav-item mr-5"><a class="nav-link active" href="#"><p>Validation & Error Process Status</p></a></li>-->
                    <!--                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Details</p> </a></li>
                                        <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Result</p></a></li>
                                        <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Error Details</p></a></li>-->
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="tab-content px-4 py-4">

                <div class="container-fluid pt-2">
                    <s:if test="%{fvuSessionResult != null}">
                        <div class="text-center col-md-12" id="session_notification" >
                            <div class="d-inline-block">
                                <s:div cssClass="%{#session.ERRORCLASS}">                                
                                    <h5 class="d-inline-block" > <s:property value="fvuSessionResult" /> </h5>
                                </s:div>
                            </div>
                        </div>
                    </s:if>
                    <div id="errorStatus" class="tab-pane show in active">
                        <div class="container-fluid pt-2">
                            <s:if test="%{allowCsiUpload!=null && allowCsiUpload.equalsIgnoreCase('T')}">
                                <div  id="uploadDiv">
                                    <s:form id="uploadCSIFiles" name="uploadCSIFiles" action="uploadCSI?validate=true" method="post" theme="css_xhtml"   enctype="multipart/form-data" autocomplete="off">
                                        <div class="row form-group">
                                            <div class="col-lg-4 col-xl-4 text-right">
                                                <label class="control-label">
                                                    Select CSI File  <span class="font-weight-bold text-danger ml-1">*</span>
                                                </label>
                                            </div>
                                            <div class="col-lg-4 col-xl-4">
                                                <div class="custom-file">
                                                    <input type="file" class="custom-file-input form-control" name="template" id="template" title="Upload only CSI File" required="" onchange="" accept=".csi">
                                                    <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-xl-4">
                                                <button type="button" class="form-btn mt-0" id="uploadCSIFileBtn"  onclick="validateUpdateBtnCSI();"><i class="fa fa-upload " aria-hidden="true"></i>Upload</button>
                                            </div>
                                        </div>                                    
                                    </s:form>

                                    <s:form id="getCSIForm" name="getCSIForm" action="getCSISftp?tokenNo=%{#request.tokenNo}" method="POST" theme="css_xhtml" enctype="multipart/form-data" autocomplete="off">
                                        <div class="row form-group">
                                            <div class="col-lg-4 col-xl-4 text-right">
                                                <label class="control-label">
                                                    OR 
                                                </label>
                                            </div>
                                            <div class="col-lg-4 col-xl-4">
                                                <button type="submit" class="form-btn mt-0"><i class="fa download " aria-hidden="true"></i>Get CSI File from SFTP server</button>
                                            </div>
                                        </div>
                                    </s:form>
                                </div>
                            </s:if>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">Generated Text File &nbsp;:</label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" class="form-control" name="textFileName" id="textFileName" readonly="true"/>
                                    <%--<s:hidden name="textFilePath" id="textFilePath"/>--%>                                    
                                </div>
                                <div class="col-lg-4 col-xl-4">                                    
                                    <button type="button" class="btn form-btn mt-0" onclick="downloadFVUTextFile();" <s:if test="%{textFileName == null}">disabled</s:if>> <i class="fa download"></i> Download </button>                                    
                                    </div>
                                </div>
                            <%--<s:if test="%{allowCsiUpload!=null && allowCsiUpload.equalsIgnoreCase('T')}">
                                    <s:div cssClass="row">
                                        <s:form id="uploadCSIFiles" name="uploadCSIFiles" action="uploadCSI?validate=true" method="post" theme="css_xhtml"   enctype="multipart/form-data" autocomplete="off">
                                            <s:div cssClass="col-md-2" cssStyle="font-weight: bold;font-size: 14px; padding-top:7px;">Upload CSI File<span style="color:red;">*</span></s:div>
                                            <s:div cssClass="col-md-4" cssStyle="padding-bottom: 0px;">
                                                <s:div cssClass="form-group">
                                                    <s:file id="template" name="template" TYPE="file"  class="filestyle" cssStyle="width: 100%;" data-buttonName="btn-primary choose-file-btn"></s:file>
                                                </s:div>
                                            </s:div>
                                            <s:div cssClass="col-md-6">
                                                <s:div cssClass="form-group">
                                                    <button type="button" class="btn btn-success " style="width: 100%;" id="uploadCSIFileBtn" onclick="validateUpdateBtnCSI();"><i class="fa fa-download" aria-hidden="true"></i> Upload</button>
                                                </s:div>
                                            </s:div>
                                        </s:form>
                                    </s:div>
                            <%--</s:if>--%>
                            <hr>
                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center">
                                        <s:a href="errorStatus">   <button type="submit" class="form-btn"><i class="fa back" aria-hidden="true"></i>Back</button></s:a>  
                                        <form id="generateFvuForm" action="generateFvuBtnAction?validateFlag=true" method="post" class="d-inline-block">
                                            <%--<s:a href="generateFvuBtnAction?validateFlag=true">--%>  
                                            <s:hidden name="tokenNo" id="tokenNo" />
                                            <button type="button" id="processBtn" onclick="callForGenerateFvuFile(this);" <s:property value="%{processBtn}"/> class="form-btn">
                                                <i class="fa generate" aria-hidden="true"></i>Generate & Process FVU File
                                            </button>
                                            <%--</s:a>--%>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $('.custom-file-input').on('change', function () {
            let file = $(this)[0].files[0];
            $(this).next('.custom-file-label').html(file.name);
        });

    </script>

    <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
    <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
</html>
