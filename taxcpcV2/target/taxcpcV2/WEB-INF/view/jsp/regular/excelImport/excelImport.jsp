<%--
    Document   : excelImport
    Created on : Feb 2, 2019, 12:08:51 PM
    Author     : sneha.parpelli
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>
<link href="resources/css/excelImport/excelImport.css" rel="stylesheet">  
<style>
    .spinner {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 0, 0, 0, .8 )
    url('https://i.stack.imgur.com/FhHRx.gif')
    50% 50%
    no-repeat;
}
body.loading {
    overflow: hidden;
}
body.loading .spinner {
    display: block;
}#loader {
  position: absolute;
  left: 0;
  top: 0;
  z-index: 1;
   background: 
    url('resources/images/global/taxcpcProcessIndicator.png');
   background-repeat: no-repeat;
   background-position: center;
  height:     100%;
    width:      100%;
 webkit-animation: pulse2 1s linear infinite;
    -moz-animation: pulse2 1s linear infinite;
    -ms-animation: pulse2 1s linear infinite;
    animation: pulse2 1s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}

/*#p_log_status {
  display: none;
  text-align: center;
}*/
</style>
<div class="page-section">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Import Master Excel Files</h4>
    </div>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li   class="nav-item mr-5"><a class="nav-link"  href="tdsImportStatus"><p> Import Status </p></a></li>
            <li   class="nav-item mr-5"><a class="nav-link active" ><p>Import File </p> </a></li>
            <li  style="cursor: not-allowed;"  class="nav-item"><a class="nav-link disabled"  style="pointer-events:none;"><p> Processed File Error Result </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">
        <div id="importFile" class="tab-pane show in active">

            <div class="container-fluid pt-2">

                <s:if test="sessionResult != null">

                    <div class="text-center col-md-12" id="session_notification" >
                        <div class="d-inline-block">
                            <s:div cssClass="%{#session.ERRORCLASS}">
                                <!--<i class="fa fa-check mr-2" aria-hidden="true"></i>-->
                                <h5 class="d-inline-block" > <s:property value="sessionResult" /> </h5>
                            </s:div>
                        </div>
                    </div>

                </s:if> 


                <s:form id="importExcelMasterFiles" name="importExcelMasterFiles" action="tdsImportAction?action=upload" method="post" 
                        theme="css_xhtml" enctype="multipart/form-data" autocomplete="off">
                    <div class="row form-group">
                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4 text-right">
                            <%--<s:select id="excelFormat" name="excelFormat" list="excelFormats" class="form-control"></s:select>--%>
                            <s:hidden id="excelFormat" name="excelFormat" value="%{#session.EXCELFORMAT}"/>
                            <s:hidden id="fileNamePath" name="fileNamePath"/>
                            <s:hidden id="newFileName" name="newFileName"/>
                            <s:hidden id="moduleType" name="moduleType"/>
                            
                            <button type="button" class="form-btn" onclick="downloadTemplateMasterExcel();"><i class="fa download" aria-hidden="true"></i>Download Template</button>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Template <span class="font-weight-bold text-danger ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select id="templateType" name="templateType" list="templateTypes" class="form-control" onchange="setFilenameForDownload();"></s:select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    File <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input form-control" name="uploadFileObj.template" id="validatedCustomFile" title="Upload only Excel File" required="" onchange="">
                                    <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Process Insert Mode <span class="font-weight-bold text-danger ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                         <%--<s:select id="processType" name="processType" list="processTypes" class="form-control"></s:select>--%> 
                            <select name="processType" id="processType" class="form-control">
                                                    <option value="A">Append</option>
                            </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="button-section col-md-12 my-1 text-center"> 
                                    <button type="button" class="form-btn" id="upload_button" onclick="validateImportFileProcess(event);" ><i class="fa upload" aria-hidden="true"></i>Upload</button>

                                    <a href='tdsImportStatus'><button type="button"   class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button></a>

                                </div>
                            </div>
                        </div>
                </s:form>
            </div>
        </div>

        <div id="processFileResult" class="tab-pane"> 
            <div class="container-fluid pt-2">


                <!--                    <div class="col-lg-2 col-xl-2 text-right">
                                        <label class="control-label">
                                            Token No. <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-2 col-xl-2">
                                        <input type="text" value=""  class="form-control" placeholder="Token No.">
                                    </div>-->



                <div class="row form-group my-2">
                    <div class="col-lg-12">
                        <div class="count-container">
                            <div class="row form-group mb-0">
                                <div class="col-lg-12 col-xl-7 mb-lg-2 mb-xl-0">    
                                    <label class="control-label mr-1">
                                        Total Records <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-primary mr-3">123456</label> 
                                    <label class="control-label mr-1">
                                        Process Records <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-success mr-3">123456</label>
                                    <label class="control-label mr-1">
                                        Error Records <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-danger mr-3">123456</label> 


                                </div>
                                <div class="col-lg-8 col-xl-5">
                                    <div class="row form-group mb-0">
                                        <div class="col-lg-3 col-xl-3 text-right px-0">
                                            <label class="control-label">
                                                Filter Error(s)<span class="font-weight-bold text-danger ml-1">*</span>
                                            </label>
                                        </div>
                                        <div class="col-lg-9 col-xl-9">
                                            <div class="input-group">
                                                <select name="processType" id="processType" class="form-control">
                                                    <option value="A">All</option>
                                                </select>
                                                <div class="input-group-addon">
                                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn mx-2" title="Show Error Records"><i class="fa search"></i></button>
                                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Download Error Records"><i class="fa download"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>





                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12">
                        <div class="update-page">Update Page No : 1</div>
                    </div>
                </div>

                <div class="table-responsive mt-2" id="dataSpan">
                    <table class="table table-bordered table-striped mb-1" id="table">             
                        <thead>            
                            <tr>

                                <th> Action      
                                </th> 
                                <th>
                                    Grid2  
                                </th> 


                                <th>
                                    Grid3

                                </th> 


                                <th>         
                                    Grid4      
                                </th> 

                                <th>         

                                    Grid5  


                                </th> 
                                <th>         

                                    Grid6


                                </th>
                                <th>         

                                    Grid7 


                                </th> 
                                <th>         

                                    Grid8  


                                </th> 






                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center">
                                    <input type="checkbox" class="cursor-pointer mr-1 align-middle">
                                    <i class="fa fa-trash text-danger cursor-pointer mr-1" title="Delete"></i></td>


                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>
                                <td>
                                    <input type="text" class="form-control">
                                </td>



                            </tr>
                        </tbody>


                    </table>

                </div>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 

                            <button type="button" class="form-btn"  ><i class="fa save" aria-hidden="true"></i>Save</button>
                            <button type="button"   class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>





            </div>
        </div>



    </div>



</div>
<script src="resources/js/regular/excelImport/excelImport.js?<%=Math.random()%>" type="text/javascript"></script>
<script type="text/javascript">
                                        $('.custom-file-input').on('change', function () {
                                            let file = $(this)[0].files[0];
                                            $(this).next('.custom-file-label').html(file.name);
                                        });
                                        setFilenameForDownload();
</script>
