<%-- 
    Document   : corrImport
    Created on : Mar 1, 2019, 10:25:20 AM
    Author     : ayushi.jain
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>


<script src="resources/js/filestylelib/bootstrap-filestyle.min.js"></script>
<div class="page-section">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Import Master Excel Files</h4>
    </div>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li   class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#importFile"><p>Import File </p> </a></li>
            <li   class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="#importStatus"> <p> Import Status </p></a></li>
            <li   class="nav-item"><a class="nav-link" data-toggle="pill" href="#processFileResult"> <p> Processed File Result </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>



    <s:if test="hasActionErrors()">
        <div class="text-center col-md-12" id="session_notification">
            <div class="d-inline-block">
                <s:div class="%{#session.ERRORCLASS}" >
                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                    <h5 class="d-inline-block" > <s:actionerror/> </h5>
                </s:div>
            </div>
        </div>
    </s:if>




    <div class="text-center"><b><span id="approximatevalue" style="color: red "></span></b></div>
    <div class="progress" style="display:  none;" id="prgogress_bar">
        <div class="progress-bar progress-bar-striped active" role="progressbar"
             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">
            <span style="font-weight: bold; color:#7D1C1C; font-size: 15px;">Uploading Zip File... Don't Refresh the Page and Don't Press Any Button </span>
        </div>
    </div>
    <div class="progress" style="display: none;" id="next_prgogress_bar">
        <div class="progress-bar progress-bar-striped active" role="progressbar"
             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">
            <span style="font-weight: bold; color:#7D1C1C; font-size: 15px;">Processing.... </span>
        </div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="importFile" class="tab-pane show in active">

            <div class="container-fluid pt-2">
                <input type="hidden" id="displayCleanData" value='<s:property value="displayCleanData"/>'/>    
                <form id="uploadZipFileForm" name="uploadZipFileForm" action="corrImportAction?action=uploadzip" method="post" enctype="multipart/form-data">
                    <div class="row form-group">
                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4 text-right">

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Upload Type <span class="font-weight-bold text-danger ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select id="selectUploadTypeId" name="selectedUploadType" list="selectedUploadTypeList" class="form-control"></s:select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Correction Type <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                            <s:select id="selectCorrectionTypeId" name="selectCorrectionType" list="viewCorrTypeList" class="form-control" onchange="changeFileUploadType();"></s:select>
                            </div>
                        </div>
                        <div class="row form-group" id="fileTagDivId">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    PAN Correction File Name <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input form-control" name="panNOCorrectionFile" id="panNOCorrectionFile" title="Upload only Excel File" required="" onchange="">
                                    <label class="custom-file-label" for="panNOCorrectionFile">Choose file...</label>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Correction File <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input form-control" name="correctionFile" id="correctionFile" title="Upload only Excel File" required="" onchange="">
                                    <label class="custom-file-label" for="correctionFile">Choose file...</label>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Justification File 
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input form-control" name="justificationFile" id="justificationFile" title="Upload only Excel File" required="" onchange="">
                                    <label class="custom-file-label" for="justificationFile">Choose file...</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="button-section col-md-12 my-1 text-center"> 
                                    <button type="button" class="form-btn" id="cleanDataAndDirBtn" onclick="cleanDataAndDirectory('T');"><i class="fa clean" aria-hidden="true"></i>Clean Data And Directory</button>
                                    <button type="button" class="form-btn" id="uploadBtnId" onclick="uploadFile();" disabled><i class="fa upload" aria-hidden="true"></i>Upload</button>
                                    <button type="button" class="form-btn" id="nextProcessBtn"  onclick="nextProcessForSave();"><i class="fa file-extraction" aria-hidden="true"></i>Click Here For File Extraction</button>
                                </div>
                            </div>
                        </div>
                    </form>
              


                <form id="filterForm" action="corrImportAction" method="post"> 
                    <div class="row mb-1">
                        <div class="col-lg-4 col-xl-4 text-right"> 
                            <label class="control-label">File Name <span style="color: red;">*</span> </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">         
                            <div class="input-group">
                            <s:select list="fileNameList" id="filterFileName" name="filterData.file_name" cssClass="form-control"></s:select>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary addon-btn" title="Search" onclick="showRecords();">
                                        <i class="fa search" id="fromDateIcon"></i>
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>

                </form>




                <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>


            <span id="dataSpan">
                <!--data from ajax-->
            </span>  

          </div>
            <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
            <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
            <s:hidden value="%{browseAction}" id="browseAction"/>
            <s:hidden value="%{browseAction}" id="dataGridAction"/>
            <s:hidden value="%{pageNumber}" id="pageNumber"/>
            <input type="hidden" id="countCall" value="0">
            <input type="hidden" id="fltrformId" value="filterForm">
            <script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
            <script type="text/javascript">
                                    try {
                                        defaultValues();
                                    } catch (err) {

                                    }
                                    try {
                                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);

                                    } catch (e) {

                                    }
            </script>

            <script type="text/javascript">
                $(document).ready(function () {
                    $("form").bind("keypress", function (e) {
                        if (e.keyCode === 13) {
                            return false;
                        }
                    });
                });

                $(document).ready(
                        function () {
                            $('input:file').change(
                                    function () {
                                        if ($(this).val()) {
                                            var correctionFile = document.getElementById('correctionFile').value;
                                            if (lhsIsNull(correctionFile)) {
                                                document.getElementById('uploadBtnId').disabled = true;
                                            } else {
                                                document.getElementById('uploadBtnId').disabled = false;

                                            }
                                        }
                                    }
                            );
                        });



            </script>



        </div>

        <div id="importStatus" class="tab-pane"> 
            <div class="container-fluid pt-2">

                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Token No <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <input type="text" value=""  class="form-control" placeholder="Token No.">
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Process Type <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <input type="text" value=""  class="form-control" placeholder="Process Type">
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Process Start Timestamp <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <input type="text" value=""  class="form-control" placeholder="  Process Start Timestamp">
                    </div>
                </div>


                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Process End Timestamp <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <input type="text" value=""  class="form-control" placeholder="Process End Timestamp">
                    </div>
                </div>


                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Process Remark<span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <input type="text" value=""  class="form-control" placeholder="Process Remark">
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <button type="button" class="form-btn"><i class="fa process" aria-hidden="true"></i>Process File</button>
                        </div>
                    </div>
                </div>



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
                                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn mx-2" title="Show Error Records"><i class="fa fa-search"></i></button>
                                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Download Error Records"><i class="fa fa-download"></i></button>
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

<script src="resources/js/correction/importFile/CorrImport.js?r=<%= Math.random()%>"></script>
<script>


                $('.custom-file-input').on('change', function () {
                    let file = $(this)[0].files[0];
                    $(this).next('.custom-file-label').html(file.name);
                })




</script>