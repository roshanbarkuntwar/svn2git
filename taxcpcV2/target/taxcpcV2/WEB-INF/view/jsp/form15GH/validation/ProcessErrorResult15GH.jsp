<%-- 
    Document   : DynamicFileUploadResult15GH
    Created on : May 7, 2019, 11:45:17 AM
    Author     : gaurav.khanzode
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processErrorResult/processErrorResult.css" type="text/css">
        <script type="text/javascript" src="resources/js/form15GH/validateError/validation15GH.js?r=<%=Math.random()%>"></script>        
        <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>      
        <title>JSP Page</title>
    </head>
    <!--<body onload="getDynamicFileUplodErrorDetailsGrid15GH();">-->
    <body onload="callUserLevelPopup();">
        <s:if test="%{sessionResult != null && sessionResult != ''}">
            <div id="session_notification" style="position: relative; top: -7px;text-align: center;">
                <s:div cssClass="%{#session.ERRORCLASS}">
                    <s:property value="sessionResult"/>
                </s:div>
            </div>
        </s:if>

        <s:if test="hasActionErrors()">            
            <s:div cssClass="%{#session.ERRORCLASS}">
                <s:actionerror/> 
            </s:div>
        </s:if>

        <div class="tab-section col-md-12 my-1">
            <ul class="nav nav-pills">
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Status</p></a></li>
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Details</p> </a></li>
                <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>15GH Validation & Error Process Result</p></a></li>
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Error Details</p></a></li>
            </ul>
            <div class="clearfix"></div>
        </div>

        <form id="dynamicFileUploadResultForm" name="dynamicFileUploadResultForm" action="ProcessErrorResultAction15GH?validate=true" method="POST">

            <s:hidden name="parentFlag"/>
            <s:hidden name="childFlag"/>
            <s:hidden id="clientLoginLevel" name="clientLoginLevel"/>
            <s:hidden id="selectedValue" name="selectedValue"/>
            <s:hidden id="upload_purpose" name="upload_purpose"/>
            <s:hidden id="l_error_process_type" name="l_error_process_type"/>
            <s:hidden id="processCnfChkBx" name="processCnfChkBx"/>
            <s:hidden id="loginLevel" name="loginLevel"/>
            <s:hidden id="tokenNo" name="tokenNo"/>

            <div class="tab-content px-4 py-4">
                <div id="" class="tab-pane show in active">
                    <div class="border error-list-wrap mb-2">
                        <div class="row mx-0">

                        </div>
                    </div>
                </div>
                <div id="ProcessErrorResult" class="tab-pane show in active">
                    <div class="row">
                        <div class="col-md-4">
                            <h4 class="page-title mb-3">Processed Error Summary :</h4>
                        </div>
                        <div class="col-md-4 mt-2 text-left">
                            <h5>Selected User Level: &nbsp;&nbsp; <strong><span id="selectedUserLevelText"></span></strong></h5>
                        </div>
                        <div class="col-md-4 text-left">
                            <button type="button" class="form-btn" onclick="callUserLevelPopup(true);" title="Change User Level"><i class="fa refresh" aria-hidden="true"></i>Change</button>
                            <s:hidden id="selectedUserLevelCode" name="selectedUserLevel" value="%{selectedUserLevel}"/>

                            <!--                            <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientTypeDefault" checked onclick="refreshProcessErrorSummary15GH();
                                                                    getDynamicFileUplodErrorDetailsGrid15GH();">
                                                            <label class="custom-control-label" for="clientTypeDefault" title="Login Level" style="font-size: 15px;">Login Level
                                                            </label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientType" onclick="refreshProcessErrorSummary15GH();
                                                                    getDynamicFileUplodErrorDetailsGrid15GH();">
                                                            <label class="custom-control-label" for="clientType" title="All (Login &amp; Branch Level)" style="font-size: 15px;">All (Login &amp; Branch Level)</label>
                                                        </div>-->
                        </div>
                    </div>
                    <div class="border error-list-wrap mb-2" id="processErrorSummaryDiv15GH">
                        <%--                        <div class="row mx-0">

                            <s:if test="%{viewTranLoadErrorsSummery == null && !viewTranLoadErrorsSummery.size()>0}">              
                                <s:iterator value="viewTranLoadErrorsSummery" status="head" >
                                    <div class="col-lg-4">
                                        <div class="media error-list position-relative">
                                            <i class="fa fa-info-circle mr-3 position-absolute" title="<s:property value="error_type_name"/>"></i>
                                            <div class="media-body">
                                                <s:hidden id="error_type_code~%{#head.count}" name="error_type_code"/>
                                                <h5 class="d-inline-block"><s:property value="error_type_name"/></h5>
                                                <span class="text-primary float-right font-weight-bold"><s:property value="record_count"/></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-lg-4">
                                        <div class="media error-list position-relative">
                                            <i class="fa fa-info-circle mr-3 position-absolute" title="Reviewed Error Records"></i>
                                            <div class="media-body">
                                                <h5 class="d-inline-block">Reviewed Error Records</h5>
                                                <span class="text-primary float-right font-weight-bold"><s:property value="review_error_record"/></span>
                                            </div>
                                        </div>
                                    </div> 

                                    <div class="col-lg-4">
                                        <div class="media error-list position-relative">
                                            <i class="fa fa-info-circle mr-3 position-absolute" title="Pending for review"></i>
                                            <div class="media-body">
                                                <h5 class="d-inline-block">Pending for review</h5>
                                                <span class="text-primary float-right font-weight-bold"><s:property value="pending_for_review"/></span>
                                            </div>
                                        </div>
                                    </div>                                    
                                </s:iterator>
                            </s:if> 
                            <s:else>
                                <%=GlobalMessage.PAGINATION_NO_RECORDS%>
                            </s:else>
                        </div>--%>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <h4 class="page-title mb-3">Section Wise Error Detail(s) and Correction :</h4>
                    </div>                    
                </div>

                <div class="border error-list-wrap mb-2 p-4" style="display: none;" id="dynamicFileUplodErrorDetailsGrid15GH">
                    <!--Ajax Data Here-->
                </div>  

                <div class="height-gap w-100"></div>

                <div class="btn-action-section  bg-white text-center fixed-bottom">
                    <button type="button"  class="form-btn" onclick="callUrl('/errorStatus15GH');"> 
                        <i class="fa back" aria-hidden="true"></i>Back
                    </button> 

                    <!--                    <button type="button"  class="form-btn" onclick="getDynamicFileUplodErrorDetailsGrid15GH();"> 
                                            <i class="fa back" aria-hidden="true"></i>Show Error Details
                                        </button> -->

                    <button type="button"  class="form-btn" onclick="downloadErrorDetails15GH(this);"> 
                        <i class="fa fa-download" aria-hidden="true"></i>Download
                    </button>
                </div>
            </div>
        </form>
        <!--Review Modal-->
        <div class="modal" tabindex="-1" id="myModalBulkReview" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel">Review</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <s:hidden id="R_ErrorCodeID"/>
                        <s:hidden id="R_ID"/>
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
                            <button type="button"  class="form-btn" onclick="updateBulkReview15GH();">
                                <i class="fa save" aria-hidden="true"></i>Save Review
                            </button>
                            <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Review Modal-->
        <!--User Levl Modal-->
        <div class="modal" tabindex="-1" id="userLevelModal" role="dialog" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="userLevelModalTitle">Select User Level</h5>
                        <!--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>-->
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
                            <div class="col-md-4 text-right">
                                <label class="control-label">User Level<span class="text-danger font-weight-bold ml-1">*</span>:</label>
                            </div>
                            <div class="col-md-8">
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="r_login_level" name="r_user_level" value="0">
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
                        <button type="button" class="form-btn" onclick="callUrl('/errorStatus15GH');" title="Cancel"><i class="fa save" aria-hidden="true"></i>Cancel</button>

                    </div>
                </div>
            </div>
        </div>
        <!--User Levl Modal-->
    </body>
</html>
