<%-- 
    Document   : processFileResult
    Created on : Mar 16, 2019, 6:43:48 PM
    Author     : ayushi.jain
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>
<link href="resources/css/excelImport/excelImport.css" rel="stylesheet">  


<div class="page-section">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Import Master Excel Files</h4>
    </div>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li   class="nav-item mr-5"><a class="nav-link"  href="tdsImportStatus"><p> Import Status </p></a></li>
            <li   class="nav-item mr-5"><a class="nav-link "  href="tdsImportAction"><p>Import File </p> </a></li>
            <li   class="nav-item"><a class="nav-link active" href="#processFileResult"><p> Processed File Error Result </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">


        <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>

        <div id="processFileResult" class="tab-pane show in active"> 
            <div class="container-fluid pt-2">


                <!--                             <div class="col-lg-2 col-xl-2 text-right">
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
                                <div class="col-lg-12 col-xl-7 mb-lg-2 mb-xl-0 mt-2">    
                                    <!--                                    <label class="control-label mr-1">
                                                                            Total Records <span class="font-weight-bold text-danger ml-1">*</span>
                                    
                                                                        </label>
                                                                        <label class="badge badge-primary mr-3">     22         </label> -->
                                    <label class="control-label mr-1">
                                        Template Records <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-success mr-3"><div id="NoOfRecordImportID" delay="10" class="label label-success" loadingText="Loading..." showLoadingText="true"></div></label>
                                    <label class="control-label mr-1">
                                        No. of Errors <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-danger mr-3"><div id="NoOfErrorImportID" delay="10" class="label label-danger" loadingText="Loading..." showLoadingText="true"></div> </label> 

                                    <label class="control-label mr-1">
                                        Token No. <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                    <label class="badge badge-info mr-3"><div  class="label label-danger" >
                                            <s:property value="%{importSeqNo}"/>
                                        </div> </label> 
                                </div>
                                <div class="col-lg-8 col-xl-5">
                                    <div class="row form-group mb-0">
                                        <div class="col-lg-3 col-xl-3 text-right px-0 d-flex justify-content-end">
                                            <label class="control-label align-self-center">
                                                Filter Error(s)<span class="font-weight-bold text-danger ml-1">*</span>
                                            </label>
                                        </div>
                                        <div class="col-lg-9 col-xl-9">
                                            <div class="input-group">

                                                <s:select list="selectErrorFilterList" name="ErrorTypeCode" id="ErrorTypeListID" cssClass="form-control" cssStyle="background-color:inherit;"></s:select>
                                                    <!--                                                
                                                                                                    <select name="processType" id="processType" class="form-control">
                                                                                                        <option value="A">All</option>
                                                                                                    </select>-->
                                                    <div class="input-group-addon">
                                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn mx-2" title="Show Error Records" onclick="FilterMasterExcelContentData();"><i class="fa search"></i></button>
                                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Download Error Records" onclick="DownloadUploadedDataList();"><i class="fa download"></i></button>
                                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Refresh Error" id="refBtn" onclick="refreshError(this);" disabled><i class="fa refresh pr-2"></i>Refresh Error</button>
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
                            <div class="update-page"> <button type="button" class="form-btn" id="updateBtn"  disabled="true" onclick="updateCheckedRecords();"><i class="fa fa-pencil-square" aria-hidden="true"></i>Update Page No.: <span id="updateBtnSpan">1</span></button></div>
                        </div>
                    </div>

                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

                <!-----------------settings------------>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>
                <s:form id="ExcelImportDtlID" name="ExcelImportDtlID">
                    <div class="table-responsive mt-2" id="dataSpan">
                        <table class="table table-bordered table-striped mb-1" id="table">     
                            <thead>                    
                                <tr>                   
                                    <th style="text-align:left;width: 50px;" colspan="2">Action</th>                         
                                        <s:property value="%{EngineHeadingData}" escapeHtml="false"/>   
                                </tr>
                            </thead>
                            <!--                        <thead>            
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
                                                    </thead>-->
                            <s:hidden id="NoOfColumns" name="NoOfColumns"/>
                            <tbody id="ImportResultDataID">
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
                </s:form>


                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 

                            <button type="button" class="form-btn" id="saveBtn"  onclick="getSaveTempData(this);"><i class="fa save" aria-hidden="true"></i>Save And Process</button>
                            <button type="button"   class="form-btn" onclick="callUrl('/tdsImportStatus')" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>





            </div>
        </div>



    </div>



</div>

<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{update_data_row}" name="update_data_row" id="update_data_row"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<s:hidden value="%{importSeqNo}" id="importSeqNo"/>
<s:hidden value="%{processType}" id="processType"/>
<s:hidden value="%{templateCode}" id="templateCode"/>
<div class="fileupload_light_box_content" id="browse_process_indicator_css1" style="display:none">            
    <div class="fileuploadprogress">
        <div class="progress-bar progress-bar-striped active progress-bar-primary" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%;background-color: blue;font-weight: bold;font-size: 15px;padding-top: 2px;">Please Wait It Will Take Some Time...</div>
    </div>
</div>
<script src="resources/js/regular/excelImport/processFileResult.js?<%=Math.random()%>" type="text/javascript"></script>
<script type="text/javascript">
//      document.getElementById("global-black-process-indicator").style.display = "flex";
                                showBlackProcessIndicator();
                                getNoOFRecordProcess();
                                
                                //  geProcessErrorRecord();
//    hideBlackProcessIndicator();
</script>
