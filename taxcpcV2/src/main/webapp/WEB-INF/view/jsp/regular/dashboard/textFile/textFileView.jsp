<%-- 
    Document   : textFileEntry
    Created on : Apr 4, 2022, 5:01:16 PM
    Author     : akash.meshram
--%>


<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script> 
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
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5" onclick="javascript:generateFvuBulkTextFileAction();"><a  href="" class="nav-link active" data-toggle="pill" ><p>Generate FVU Bulk Text File Status</p> </a></li>
            <li  class="nav-item mr-5" onclick="javascript:textFileDashboardAction();"><a  href="" class="nav-link" data-toggle="pill"  ><p>Generated Text files Dashboard</p> </a></li>
        </ul>
    </div>
    <div class="clearfix"></div>
    <div class="tab-content px-4 py-4">   
        <div class="container-fluid pt-2">
            <form id="errorStatusForm" name="errorStatusForm" action="tdsProcessError" method="POST">
                <s:hidden id="h_vauation_right" name="h_vauation_right" value="%{#loginuser.getVauation_right()}"/>
                <s:hidden name="validate" value="true" />  
                <s:hidden id="loginLevel" name="loginLevel" value="%{#loginuser.getCode_level()}"/>
                <s:hidden id="loginLevelFlag" name="loginLevelFlag"/>
                <s:hidden name="errorTypeProc" id="errorTypeProc" value="ALL"/>
                <s:hidden name="refreshFlag" id="refreshFlag" value="bulkFVUText"/>





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




                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <button type="button" id="killBtn" <s:property value="%{killBtn}"/> class="form-btn form-red-btn" onclick="killErrorProcessing('tokenNo', this);"><i class="fa kill-process" aria-hidden="true"></i>Kill Process</button>
                            <button type="button" id="refreshBtn" <s:property value="%{refreshBtn}"/> class="form-btn" onclick="refreshTokenStatus('tokenNo');"><i class="fa refresh" aria-hidden="true"></i>Refresh</button>
                            <button type="button" id="fvuBtn" <s:property value="%{fvuBtn}"/> class="form-btn" onclick="textFileDashboardAction();" ><i class="fa generate" aria-hidden="true"></i>View Generate Text File</button>                                            

                            <input type="hidden" name="processLevel" id="processLevel" value="<s:property value="%{tokenUploadObj.processType}"/>">
                        </div>
                    </div>
                </div>
            </form>
            <hr>

            <div class="button-section col-md-12 my-1 text-center">
                <button type="button" id="fvuBulkTxtBtn" <s:property value="%{fvuBulkTxtBtn}"/> class="form-btn" onclick="openLoginLevelModal();"><i class="fa generate" aria-hidden="true"></i>Generate FVU Bulk Text File</button>
            </div>
            <div class="button-section col-md-12 my-1 text-right">
                <button type="button" class="form-btn" onclick="callUrl('/errorStatus');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
            </div>

        </div>
    </div>
            
            
</div> 
            
      <div class="modal fade" id="validationLogModal" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-log" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: flex;align-items: center;">
                <h5 class="modal-title" id="exampleModalCenterTitle">FVU Bulk Text File Log | <span style="color:#fffe03">Token No. : <span><s:property value="%{tokenUploadObj.tokenNo}"/></span></span></h5> 
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
            
            

<!--Login Levl Modal-->
<div class="modal" tabindex="-1" id="loginLevelModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userLevelModalTitle">Select Client Level For Bulk Text File Generation</h5>                        
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
                    <div class="col-md-8">
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
                <button type="button" class="form-btn" onclick="generateFvuBulkTextFileNew();" title="Show"><i class="fa fa-expand" aria-hidden="true"></i>Generate</button>
                <button type="button" class="form-btn"  title="Cancel" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
            </div>
        </div>
    </div>
</div>
<!--Login Levl Modal -->                       


<script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>