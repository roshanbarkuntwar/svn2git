<%-- 
    Document   : errrorStatus
    Created on : Mar 15, 2019, 4:40:13 PM
    Author     : neha.bisen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <style>
            @media (min-width: 1200px) and (max-width:1470px){
                .form-btn {
                 padding: 4px 4px 4px 34px;
                 font-size: 12px;
            }
        }
        @media (min-width: 1200px) and (max-width:1332px){
                .form-btn {
                 padding: 4px 2px 4px 31px;
                 font-size: 11px;
            }
            .form-btn .fa:after {
                left:3px
            }
        }
        </style>
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>Validation & FVU Generation Status</p></a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Details</p> </a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Result</p></a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Error Details</p></a></li>
                   
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="tab-content px-4 py-4">

                <div class="container-fluid pt-2">
                    <s:if test="%{sessionResult != null}">
                        <div class="text-center col-md-12" id="session_notification" >
                            <div class="d-inline-block">
                                <s:div cssClass="%{#session.ERRORCLASS}">                                
                                    <h5 class="d-inline-block" > <s:property value="sessionResult" /> </h5>
                                </s:div>
                            </div>
                        </div>
                    </s:if>
                    <div id="errorStatus" class="tab-pane show in active">
                        <div class="container-fluid pt-2">
                            <form id="errorStatusForm" name="errorStatusForm" action="tdsProcessError" method="POST">
                                <s:hidden id="h_vauation_right" name="h_vauation_right" value="%{#loginuser.getVauation_right()}"/>
                                <s:hidden name="validate" value="true" />  
                                <s:hidden id="loginLevel" name="loginLevel" value="%{#loginuser.getCode_level()}"/>
                                <s:hidden id="loginLevelFlag" name="loginLevelFlag"/>
                                <s:hidden name="errorTypeProc" id="errorTypeProc" value="ALL"/>
                                <s:hidden name="errorProcessReportCount" id="errorProcessReportCount" value="%{errorProcessReportCount}"/>
                                <s:hidden name="lhssysLog_listsize" id="lhssysLog_listsize" />
                                <s:hidden name="fvuPid_Fflag_count" id="fvuPid_Fflag_count" />
                                <s:hidden name="fvuPid_Tflag_count" id="fvuPid_Tflag_count" />
                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">
                                            Token No <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4 input-group">
                                        <input type="text" id="tokenNo" name="tokenNo" value="<s:property value="%{tokenUploadObj.tokenNo}"/>" class="form-control" placeholder="Token No.">
                                        <div class="input-group-append">
                                            <button   type="button" class="btn btn-primary btn-sm addon-btn" onclick="openTokenStatus('fvuStatus');" title="Tokens of Process Error" ><i class="fa fa-link rotate-90"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">
                                            Process Parameters <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4">
                                        <input type="text" name="t_processTypeName" id="t_processTypeName" value="<s:property value="%{tokenUploadObj.processTypeName}"/>" class="form-control" placeholder="Process Parameters">
                                        <input type="hidden" name="t_processType" id="t_processType" value="<s:property value="%{tokenUploadObj.processType}"/>">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">
                                            Process Start Timestamp <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4">
                                        <input type="text" id="t_processStartTimestamp" name="t_processStartTimestamp" value="<s:property value="%{tokenUploadObj.processStartTimestamp}"/>" class="form-control" placeholder="Process Start Timestamp">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">
                                            Process End Timestamp <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4">
                                        <input type="text" id="t_processEndTimestamp" name="t_processEndTimestamp" value="<s:property value="%{tokenUploadObj.processEndTimestamp}"/>" class="form-control" placeholder="Process End Timestamp">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4 text-right">
                                        <label class="control-label">
                                            Process Remark<span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4" >
                                        <div class="input-group">
                                            <input type="hidden" id="t_processStatus" name="t_processStatus" value="<s:property value="%{tokenUploadObj.processStatus}"/>">
                                            <input type="text" id="t_processRemark" name="t_processRemark" value="<s:property value="%{tokenUploadObj.processRemark}"/>" class="form-control"c style="font-weight: bold; color: green;" placeholder="Process Remark">
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-primary btn-sm addon-btn" data-toggle="modal" 
                                                        onclick="getValidationProcessLog('<s:property value="%{tokenUploadObj.tokenNo}"/>', '<s:property value="%{tokenUploadObj.processStatus}"/>');"><i class="fa fa-file-text"></i></button>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-lg-1 col-xl-4 pl-0">
                                        <s:if test="%{#session.CLIENTLOGINLEVEL != null && (tokenUploadObj.processStatus == 'FC' || tokenUploadObj.processStatus == 'FD') && (clobFlag == 'F')}">   
                                           <s:if test="%{clobFlag.equalsIgnoreCase('F')}"> 
                                            <button type="button"  id="fvuDownloadBtn" <s:property value="%{fvuDownloadBtn}"/> class="download-btn" onclick="callForFvuDownload('/downloadFVUZip?tokenNo=<s:property value="%{tokenUploadObj.tokenNo}"/>');"><i title="Download FVU File" class="fa download" aria-hidden="true"></i></button>         
                                          </s:if>
                                       </s:if>
                                    </div>



                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="button-section col-md-12 my-1 text-center px-0"> 
                                            <button type="button" id="killBtn" <s:property value="%{killBtn}"/> class="form-btn form-red-btn" onclick="killErrorProcessing('tokenNo', this);"><i class="fa kill-process" aria-hidden="true"></i>Kill Process</button>
                                            <button type="button" id="refreshBtn" <s:property value="%{refreshBtn}"/> class="form-btn" onclick="refreshTokenStatus('tokenNo');"><i class="fa refresh" aria-hidden="true"></i>Refresh</button>
                                            <button type="submit" id="viewBtn" <s:property value="%{viewBtn}"/> class="form-btn"><i class="fa fa-eye" aria-hidden="true"></i>View Processed Data</button>
                                            <button type="button" id="fvuTextBtn" <s:property value="%{fvuTextBtn}"/> class="form-btn" onclick="generateFVUFunction(this.id);"><i class="fa generate" aria-hidden="true"></i>Generate FVU Text File</button>
                                            <s:if test="%{#session.CLIENTLOGINLEVEL != null && #session.CLIENTLOGINLEVEL != 6}">
                                                <button type="button" id="fvuBulkTxtBtn" <s:property value="%{fvuBulkTxtBtn}"/> class="form-btn" onclick="generateFvuBulkTextFileAction();"><i class="fa bulk-updation" aria-hidden="true"></i>Generate FVU Bulk Text File</button>
                                            </s:if>
                                            <button type="button" id="fvuBtn" <s:property value="%{fvuBtn}"/> class="form-btn" onclick="generateFVUfiledynamicAction('<s:property value="%{NEW_FVU_GEN_FLAG}" />','<s:property value="%{tokenUploadObj.tokenNo}"/>');" ><i class="fa generate" aria-hidden="true"></i>Generate FVU File</button>                                            
                                          
                                            <s:if test="%{NEW_FVU_GEN_FLAG.equalsIgnoreCase('T')}">
                                             <button type="button" id="fvuDownloadClobBtn" <s:property value="%{fvuDownloadClobBtn}"/> class="form-btn" onclick="callfvudownload();"><i class="fa bulk-updation" aria-hidden="true"></i> FVU File Download</button> 
                                            </s:if>
                                          
                                    </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <hr>
                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center">
                                        <s:a href="tdsProcessError">                                                
                                            <button type="submit" id="processBtn" <s:property value="%{processBtn}"/> class="form-btn"><i class="fa new-error-process-request" aria-hidden="true"></i>New Error Process Request</button>
                                        </s:a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="validationLogModalxxx" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Log View</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="spinner"><!-- Place at bottom of page --></div>
                        <h5> <a class=" btn btn-link text-primary float-right" onclick="requestProcessLog('<s:property value="%{tokenUploadObj.tokenNo}"/>', '<s:property value="%{tokenUploadObj.processStatus}"/>');"><i class="fa fa-refresh mr-1"></i>Refresh</a></h5>
                        <table class="table table-sm table-striped">
                            <tbody>
                                <tr>
                                    <td class="font-weight-bold">Last Modified :</td>
                                    <td id="p_last_modify"></td>
                                </tr>
                                <tr>
                                    <td class="font-weight-bold">Size :</td>
                                    <td id="p_file_size"></td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="log-file-container w-100 border p-3 text-justify" id="p_log_statusx">
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        
               
    <div class="modal fade" id="validationLogModal" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-log" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: flex;align-items: center;">
                <h5 class="modal-title" id="exampleModalCenterTitle">Validation & FVU Generation Log | <span style="color:#fffe03">Token No. : <span><s:property value="%{tokenUploadObj.tokenNo}"/></span></span></h5> 
                <div class="">
                    
                <button type="button" class="close" title="Close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    <span class="inline-block text-white float-right mr-2 refresh-icon" onclick="getvalidationLogModalRefresh('<s:property value="%{tokenUploadObj.tokenNo}"/>', '<s:property value="%{tokenUploadObj.processStatus}"/>');" title="Refresh"><i class="fa fa-refresh"></i></span>
                   
                </div>
                
            </div>
            <!--<div class="modal-body" id="fileStatus">-->
            <div class="modal-body" style="position:relative">
                

                <div id="loader" ></div>
                <div class="log-file-container w-100 border p-3 text-justify" id="p_log_status">

                </div>
            </div>

        </div>
    </div>
</div>
                
            
                
    <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
    <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
    <script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>      
    
</html>
<script>
  var tokenNo =  document.getElementById("tokenNo").value;
  localStorage.setItem("token_no", tokenNo);
  var errorProcessReportCount =  document.getElementById("errorProcessReportCount").value;
  localStorage.setItem("errorProcessReportCount", errorProcessReportCount);
</script>    
  