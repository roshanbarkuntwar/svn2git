<%-- 
    Document   : generateFvuClob
    Created on : Apr 1, 2022, 4:24:01 PM
    Author     : akash.meshram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <style>
            .taxcpc-process-indicator-outer {background: rgba(0, 0, 0 , 0.6);} 
        </style>
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#generateFvuBtnAction"><p>Generate FVU File</p> </a></li>
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
                            
                            
                      <div class="mb-4" style="background: #ffffce;width: 100%;padding: 18px;border: 1px solid #cbdeff;">
                        <div class="row">
                            <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6" id="unverifiedPanBody">
                                <div class="error-list pl-2 py-3 mb-0">
                                    <div class="d-flex justify-content-center">           
                                        <h5><strong>Token No: &nbsp;&nbsp; <span><s:property value="%{tokenNo}"/></span></strong></h5>
                                    </div>

                                </div>
                            </div>
                            <div class="col-xl  px-xl-2 mb-lg-3  mb-xl-0 col-lg-6" id="unverifiedChallanBody">
                                <div class="error-list pl-2 py-3 mb-0">
                                    <div class="d-flex justify-content-center">           
                                        <h5><strong>FVU Version: &nbsp;&nbsp; <span>${FVU_FILE_VERSION}</span></strong></h5>
                                    </div>

                                </div>
                            </div>
                         </div>                    
                     </div>               
               

                            <div class="mb-4" style="background: #f6f9ff;width: 100%;padding: 18px;border: 1px solid #cbdeff;">
                                <div class="row">
                                    <div class="col-md-4">
                                        <h4 class="page-title">FVU Text File </h4>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="d-inline-block">
                                            <div id="notificationMsg" class="form-validation form-validation--info p-1 mt-1 mb-3" style="display:none">
                                                <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                                <h5 class="d-inline-block" id="Msgpara">Text File Not Generated.Please Generate Text File </h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>




                                <div class="row form-group align-items-center justify-content-center">
                                    <div class="text-center col-md-12" id="" style="display: block;">

                                    </div>
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">Generated Text File &nbsp;:</label>
                                    </div>
                                    <div class="col-lg-3 col-xl-3">
                                        <s:textfield type="text" class="form-control" name="textFileName" id="textFileName" readonly="true"/>
                                        <s:hidden name="textFilepath"  id="textFilePath" value="%{textFilepath}" />
                                        <s:hidden name="textFileName" id="textFileName" value="%{textFileName}" />
                                        <s:hidden name="textFileFlag" id="textFileFlag" value="%{textFileFlag}" />
                                        <s:hidden name="oraTextFilePath" id="oraTextFilePath" value="%{oraTextFilePath}" />
                                        <s:hidden name="tokenNo" id="token_No_" value="%{tokenNo}" />

                                    </div>
                                    <%-- <s:if test="%{CLOB_FVU_GEN_FLAG.equalsIgnoreCase('T')}"> 
                                      <s:if test="%{CLOB_FVU_GEN_FLAG.equalsIgnoreCase('T')}">
                                            <div  class="col-lg-1 col-xl-4 pl-0">

                                                    <button type="button"  class="download-btn"  id="textdwnload"  onclick="downloadFVUclobTextFile();" disabled = true;><i  id="txtdn" title="Download Text File" class="fa download" aria-hidden="true" <s:if test="%{textFileName == null}">disabled</s:if>></i></button>         

                                            </div>
                                       </s:if>--%>  
                                    <%-- <s:if test="%{CLOB_FVU_GEN_FLAG.equalsIgnoreCase('F')}">--%>
                                    <div  class="col-lg-1 col-xl-4 pl-0">

                                        <button type="button"  class="download-btn"  id="textdwnload"  onclick="textFileDownloadForFvu('<s:property value="%{BLOB_FILE_DOWNLOAD_FLAG}"/>');"><i  id="txtdn" title="Download Text File" class="fa download" aria-hidden="true" <s:if test="%{textFileName == null}">disabled</s:if>></i></button>         

                                        </div>
                                    <%-- </s:if> --%>


                                </div>
                                <%-- <s:if test="%{CLOB_FVU_GEN_FLAG.equalsIgnoreCase('T')}"> 
                                <div  id="textRecreation"  class="row form-group align-items-center justify-content-center"  style="display:none">
                                   <s:form id="getTextForm" name="getTextForm"  action="generateFvuBtnClobAction?action=getTextFile"  method="post"   autocomplete="off">
                                     <s:hidden name="tokenNo" id="tokenNo" />
                                    <div class="col-lg-4 col-xl-4 text-right">
                                    </div>
                                    <div id="textRecreation" class="col-lg-12 text-center">
                                    <button type="button" title="Get Text File From Clob To FVU Directory"  data-toggle="collapse" data-target="#textFile" class="btn form-btn mt-0" onclick="getTextFileConformation();"> <i class="fa fa-angle-double-down" ></i> Get Text File From DBS</button>                             
                                    </div>
                                    <div class="col-lg-4 col-xl-4"></div>
                                    </s:form> 

                                 </div>   
                                
                                 </s:if>--%> 

                            </div>


                            <div id="csidivsection" class="mb-4" style="background: #fffaee;width: 100%;padding: 18px;border: 1px solid #fae9b1;"  style="display:">
                                <h4 class="page-title"> CSI File </h4>
                                <div class="row form-group align-items-center justify-content-center">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">Generated CSI File &nbsp;:</label>
                                    </div>
                                    <div class="col-lg-3 col-xl-3">
                                        <s:textfield type="text" class="form-control" name="csiFileName" id="csiFileName" readonly="true"/>
                                        <s:hidden name="csiFilePath"  id="csiFilePath" value="%{csiFilePath}" />
                                        <s:hidden name="csiFileName" id="csiFileName" value="%{csiFileName}" />
                                        <s:hidden name="csiFileFlag" id="csiFileFlag" value="%{csiFileFlag}" />
                                    </div>
                                    <div class="col-lg-1 col-xl-4 pl-0">
                                        <button type="button" id="csidwnloadbtn" class="download-btn" onclick="downloadFVUcsiFile();"><i id="csidn" title="Download CSI File" class="fa download" aria-hidden="true" ></i></button>         
                                    </div>
                                </div>


                                <div class="form-group">             
                                    <s:form id="getCSIForm" name="getCSIForm"  action="generateFvuBtnClobAction?action=getCSIfromOltas"  method="post" theme="css_xhtml" enctype="multipart/form-data" autocomplete="off">
                                        <s:hidden name="tokenNo" id="tokenNo" />
                                        <div class="row form-group">

                                            <div class="col-lg-12 text-center">
                                                <button type="button" class="form-btn mt-0" onclick="getCSIFileConformation();"><i class="fa fa-angle-double-down" aria-hidden="true" disabled = true;></i>Get CSI File from OLTAS </button>
                                            </div>
                                            <div class="col-lg-12 text-center mt-3">
                                                <label class="control-label">
                                                    OR 
                                                </label>
                                            </div>
                                        </div>
                                    </s:form>  
                                    <s:form id="uploadCSIFiles" name="uploadCSIFiles" action="uploadCSI?validate=true" method="post" theme="css_xhtml"   enctype="multipart/form-data" autocomplete="off">
                                        <s:hidden id="token_No" name="tokenNo" value="%{tokenNo}"/>
                                        <div class="row form-group align-items-center justify-content-center" >
                                            <div class="col-lg-4 col-xl-4 text-right">
                                                <label class="control-label">
                                                    Select CSI File  <span class="font-weight-bold text-danger ml-1">*</span>
                                                </label>
                                            </div>
                                            <div class="col-lg-3 col-xl-3">
                                                <div class="custom-file">
                                                    <input type="file" class="custom-file-input form-control" name="template" id="template" title="Upload only CSI File" required="" onchange="" accept=".csi">
                                                    <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-xl-4 pl-0">
                                                <button type="button" class="form-btn mt-0" onclick="validateUpdateBtnCSI();"><i class="fa fa-upload " aria-hidden="true"></i>Upload</button>
                                            </div>
                                        </div>                                    
                                    </s:form>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center">
                                        <s:a href="errorStatus">   <button type="submit" class="form-btn"><i class="fa back" aria-hidden="true"></i>Back</button></s:a>  
                                            <form id="generateFvuForm" action="generateFvuBtnClobAction?action=generateFvuFileCall" method="post" class="d-inline-block">
                                            <s:hidden name="tokenNo" id="tokenNo" />
                                            <button type="button" id="FvuFileCall" class="form-btn" onclick="GenerateFvuFileCall();" disabled = true;>
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
    </body>
    <script type="text/javascript">
        $('.custom-file-input').on('change', function () {
            let file = $(this)[0].files[0];
            $(this).next('.custom-file-label').html(file.name);
        });



        $(document).ready(function () {
            try {
                divBlockCheck();
            } catch (e) {

            }

            try {
                setTimeout(() => {
                    removeError();
                }, 6000);
            } catch (e) {

            }
        });
    </script>

    <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
    <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
</html>
