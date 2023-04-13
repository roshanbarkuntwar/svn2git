
<%-- 
    Document   : processErrorResult
    Created on : Mar 13, 2019, 11:42:22 AM
    Author     : sneha.parpelli
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processErrorResult/processErrorResult.css" type="text/css">
        <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>        
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
        <title></title>
        <style>
            .collapse-header {position: relative}
            .collapse-header[aria-expanded="false"] {border-bottom: 0 !important;}
            .collapse-icon {cursor:pointer;background: linear-gradient(to bottom, #0e4dbd, #293e63);position: absolute;right: 0;top:0;display: inline-block;font-size: 25px;
    font-weight: 600;
    color: #fff;
    padding: 0px 30px;}
        </style>
    </head>
    <!--<body onload="getDynamicErrorDetailsAction();">-->        
    <body onload="callUserLevelPopup();">        
        <form id="dynamicFileUploadResultForm" name="dynamicFileUploadResultForm" action="ProcessErrorResultAction" method="POST">

            <s:hidden id="processCnfChkBxID" name="processCnfChkBx"/>
            <s:hidden id="upload_purpose" name="upload_purpose" value="R"/>
            <s:hidden id="validate" name="validate"/>
            <s:hidden id="l_error_process_type" name="l_error_process_type"/>
            <%--<s:hidden id="reprocessFlag" name="reprocessFlag" value="T"/>--%>            
            <s:hidden name="parentFlag"/>
            <s:hidden name="childFlag"/>
            <s:hidden id="loginLevel" name="loginLevel"/>
            <s:hidden id="loginLevelFlag" name="loginLevelFlag"/>
            <s:hidden id="errorTypeProc" name="errorTypeProc"/>
            <s:hidden name="tokenNo" id="tokenNo" value="%{tokenNo}"/>
          <%--  <s:hidden name="errorProcessReportCount" id="errorProcessReportCount" value="%{errorProcessReportCount}"/>--%>
             <script>
                $(document).ready(function () {
                       var err_Pro_Count =  localStorage.getItem("errorProcessReportCount");
                       document.getElementById("errorProcessReportCount").value=err_Pro_Count;                                     
              
    });  
            </script>
       <s:hidden name="errorProcessReportCount" id="errorProcessReportCount" value="%{errorProcessReportCount}" /> 
       

           
            <div class="page-section">
                <div class="tab-section col-md-12 my-1">
                    <ul class="nav nav-pills">
                        <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & FVU Generation Status</p></a></li>
                        <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Details</p></a></li>
                        <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>Validation & Error Process Result</p></a></li>
                        <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Error Details</p></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <s:hidden id="R_ErrorCodeID"/>
                <s:hidden id="R_ID"/>
                <div class="tab-content px-4 py-4">
                    <div class="container-fluid pt-2">

                        <div id="ProcessErrorResult" class="tab-pane show in active">
                            <div class="row">
                                <div class="col-md-4">
                                    <h4 class="page-title mb-3">Processed Error Summary :</h4>
                                </div>
                                <div class="col-md-6 mt-2 text-left">
                                    <h5>Selected User Level: &nbsp;&nbsp; <strong><span id="selectedUserLevelText"></span></strong></h5>
                                </div>
                                <div class="col-md-2 text-right">
                                    <button type="button" class="form-btn" onclick="callUserLevelPopup(true);" title="Change User Level"><i class="fa refresh" aria-hidden="true"></i>Change</button>
                                    <s:hidden id="selectedUserLevelCode" name="selectedUserLevel" value="%{selectedUserLevel}"/>
                                    <!--                                    <div class="custom-control custom-radio custom-control-inline">
                                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientTypeDefault" checked onclick="refreshProcessErrorSummary();
                                                                                    getDynamicErrorDetailsAction();">
                                                                            <label class="custom-control-label" for="clientTypeDefault" title="Login Level" style="font-size: 15px;">Login Level
                                                                            </label>
                                                                        </div>
                                                                        <div class="custom-control custom-radio custom-control-inline">
                                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientType" onclick="refreshProcessErrorSummary();
                                                                                    getDynamicErrorDetailsAction();">
                                                                            <label class="custom-control-label" for="clientType" title="All (Login & Branch Level)" style="font-size: 15px;">All (Login &amp; Branch Level)</label>
                                                                        </div>-->
                                </div>
                                <!--                                <div class="col-md-4 text-right">
                                                                    <button type="button" class="form-btn" onclick="getDynamicErrorDetailsAction();" title="Show Error Details"> 
                                                                        <i class="fa reprocess" aria-hidden="true"></i>Show Error Details
                                                                    </button>
                                                                </div>-->
                            </div>

                            <div class="border error-list-wrap mb-2" id="processErrorSummaryDiv">
                                <div class="row mx-0">
                                    <s:if test="%{viewTranLoadErrorsSummery!=null && viewTranLoadErrorsSummery.size()>0}">              
                                        <s:iterator value="viewTranLoadErrorsSummery" status="head" >
                                            <div class="col-lg-4 pr-0">
                                                <div class="media error-list position-relative">
                                                    <i class="fa fa-info-circle mr-3 position-absolute" title="<s:property value="error_type_name"/>"></i>
                                                    <div class="media-body">
                                                        <s:hidden id="error_type_code~%{#head.count}" name="error_type_code"/>
                                                        <h5 class="d-inline-block"><s:property value="error_type_name"/></h5>
                                                        <span class="text-primary float-right font-weight-bold"><s:property value="record_count"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                    <s:else>
                                        <!--Global no record message-->
                                        <div class="no-record-found-browse col-lg-6 offset-lg-3 my-3 text-center">
                                            <img class="pr-3" src="resources/images/global/empty-box.png"> No Records Found.
                                        </div>
                                    </s:else>
                                </div>
                            </div>
                        </div>
                                    <div class="row">
                                            <div class="col-md-5">
                                                <h4 class="page-title mb-3">Generate Error Process Report :  </h4>
                                            </div> 
                                                                      
                                     </div>
                                   
                                    <div class="border error-list-wrap mb-2 p-4" >
                                       
                                        <s:if test="%{errorProcessReportCount == null }">
                                            <div id="errorReportbtndiv" class="text-center">
                                                <button type="button" id="err_process_rep_btn"  <s:property value="%{err_process_rep_btn}"/> class="form-btn" disabled=""><i class="fa generate" aria-hidden="true"></i> Generate Error Process Report</button>
                                           </div>
                                        </s:if>
                                        <s:elseif test="%{errorProcessReportCount == 0l }">
                                            <div id="errorReportbtndiv" class="text-center">
                                            <button type="button" id="err_process_rep_btn"  <s:property value="%{err_process_rep_btn}"/> class="form-btn" onclick="getErrorProcessReport()"><i class="fa generate" aria-hidden="true"></i> Generate Error Process Report</button>
                                            <div id="disabledmsg" class="form-validation  p-1 mt-1 mb-2" style="display:none;">
                                            <h5 class="d-inline-block">Please wait... previous request is in process.</h5>
                                            </div>
                                            </div>
                                            <script>
                                               $(document).ready(function () {
                                                   checkprocedureCallOrNot();
                                                });  
                                            </script>
                                        </s:elseif>    
                                        <s:else>
                                            <script>
                                               $(document).ready(function () {
                                                   getGeneratedErrorProcessReport();
                                                });  
                                            </script>
                                        </s:else>
                                        <div id="errorReport"></div>
<!--                                        <div class="card border-secondary error-details-section mb-3 mt-2">
                                            <div class="card-header collapse-header" >
                                                <h5 class="d-inline-block align-top" >
                                                    Generate Error Process Report
                                                    <span class="fa fa-angle-down collapse-icon" data-toggle="collapse" href="#collapseOne" aria-expanded="false"></span>
                                                    <button type="button" class="btn btn-sm bulk-review position-absolute rounded-0 fa fa-angle-down"></button>
                                                    <span class="fa fa-angle-down collapse-icon"></span>
                                                </h5>
                                            </div>
                                            <div id="collapseOne" class="collapse">
                                                <div class="card-body">
                                                    <div class="table-responsive mt-2">
                                                        <table class="table table-bordered table-striped mb-1">
                                                            <thead>
                                                                <tr>
                                                                    <th class="seq-no">Sr No.</th>
                                                                    <th>Name</th>
                                                                    <th>Process</th>
                                                                    <th>Status</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>1</td>
                                                                    <td>Asd</td>
                                                                    <td>Asd</td>
                                                                    <td>Active</td>
                                                                    <td>
                                                                        <a><i class="fa download text-primary cursor-pointer mr-1" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Download"></i></a>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    
                                                    
                                                </div>
                                            </div>
                                        </div>-->
                                    </div>
                        
                        <div class="row">
                            <div class="col-md-5">
                                <h4 class="page-title mb-3">Section Wise Error Detail(s) and Correction :</h4>
                            </div>                            
                        </div>

                        <div class="border error-list-wrap mb-2 p-4" style="display: none;" id="SectionWiseErrorDetailAndCorrectionGrid">
                            <!--Ajax Data Here-->
                        </div>
                    </div>
                </div>
                <s:hidden id="errType"/>
                <s:hidden type="hidden" name ="errortypeBulkProc" id="errortypeBulkProc"/>
                <div class="height-gap w-100"></div>
                <div class="btn-action-section  bg-white text-center fixed-bottom">
                    <button type="button" class="form-btn" onclick="callUrl('/errorStatus');" title="Back"> 
                        <i class="fa back" aria-hidden="true"></i>Back
                    </button>

                    <button type="button" class="form-btn" onclick="downloadErrorDetails(this);" title="Download"> 
                        <i class="fa fa-download" aria-hidden="true"></i>Download
                    </button>
                </div>
            </div>
        </form>        
        <form name="genFilFrm" id="genFilFrm" action="tdsGenerateFVUText?action=callProcedure" method="POST"></form>
        <!--Review Modal-->
        <div class="modal" tabindex="-1" id="review" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel">Review</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">
                                    Review Remark<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-7">
                                <input type="text"  class="form-control" id="review_remark_id" placeholder="Review Remark">
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="button"  class="form-btn" onclick="openAssessment();"><i class="fa save" aria-hidden="true"></i>Save Review</button>
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Review Modal-->
        <!--Procedure Modal-->
        <div class="modal" tabindex="-1" id="modalBulkProcedure" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="">Bulk Update</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">
                                    Bulk Update<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-7">
                                <input type="hidden" name ="errortypeBulkProc" id="errortypeBulkProc"/>
                                <select id="errorFlagProc" name="errorFlagProc" class="form-control" style="width:100%;" ></select>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="button"  class="form-btn" onclick="BulkProcedureConfirm();"><i class="fa save" aria-hidden="true"></i>Bulk Update</button>
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Procedure Modal-->
        <!--Bulk Update Modal-->
        <div class="modal" tabindex="-1" id="bulkUpdation" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Bulk Updation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">
                                    Bulk Updation<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-7">
                                <select class="form-control">
                                    <option>With TDS</option>
                                    <option>Without TDS</option>
                                </select>

                            </div>
                        </div>
                        <div class="text-center">
                            <button type="button"  class="form-btn"><i class="fa fa-pencil" aria-hidden="true"></i> Bulk Updation</button>
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Bulk Update Modal-->
        <!--Bulk Review All-->
        <div class="modal" tabindex="-1" id="modalBulkReviewAll" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabelAll">Bulk Updation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">
                                    Review Remark<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-7">
                                <input type="text"  class="form-control" id="review_remark_id_All" placeholder="Review Remark">
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="button"  class="form-btn" onclick="changeAssessment();"><i class="fa fa-pencil" aria-hidden="true"></i>Save Review</button>
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Bulk Review All-->
        <!--salary error detail modal start-->
        <div class="modal" id="errorSalarySummaryModeModel" role="dialog">                
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Error Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <s:hidden id="hidden_salary_error_type_code"/>                        
                    <div class="modal-body">
                        <div class="row form-group" id="errorSalarySummaryModeIframe">                                
                        </div>
                        <div class="text-center">
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
                <img src="<s:property value="#session.CONTEXTPATH" />/resources/images/global/progress_indicator.gif" id='browse_process_error_salary_summary'/>
            </div> 
        </div>
        <!--salary error detail modal end-->     
        <!--User Levl Modal-->
        <div class="modal" tabindex="-1" id="userLevelModal" role="dialog" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="userLevelModalTitle">Select Client Level For Error Processing </h5>                        
                    </div>
                    <div class="modal-body">  
                        <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--info p-1 my-1">
                                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="notificationMsg"></h5>
                                </div>
                            </div>
                        </div>
                        <div class="row">                            
<!--                            <div class="col-md-4 text-right">
                                <label class="control-label">User Level<span class="text-danger font-weight-bold ml-1">*</span>:</label>
                            </div>-->
                            <div class="col-md-10 text-right">
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="r_login_level" checked="checked"  name="r_user_level" value="0">
                                    <label class="custom-control-label" for="r_login_level" title="Login Level" style="font-size: 13px;">Login Level</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="r_all_level" name="r_user_level" value="1">
                                    <label class="custom-control-label" for="r_all_level" title="All (Login & Branch Level)" style="font-size: 13px;">All (Login &amp; Branch Level)</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer justify-content-center">
                        <button type="button" class="form-btn" onclick="showUserLevelErrorSummaryAndDetails();" title="Show"><i class="fa fa-expand" aria-hidden="true"></i>Show</button>
                        <button type="button" class="form-btn" onclick="callUrl('/errorStatus');" title="Cancel"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                    </div>
                </div>
            </div>
        </div>
        <!--User Levl Modal-->
    </body>
</html>

<script>
//    $(document).ready(function () {
//        try{
//        var btn = document.getElementById("err_process_rep_btn"); 
//        var status = btn.disabled;
//        if (status !== "" && status !== "null" && status !== null){
//        if(status){
//          document.getElementById("disabledmsg").style.display = "block";
//        }else{
//          document.getElementById("disabledmsg").style.display = "none";
//        }
//         console.log(btn.disabled);
//     }
//        }catch(error e){
//            
//        }
//     });  
 </script>