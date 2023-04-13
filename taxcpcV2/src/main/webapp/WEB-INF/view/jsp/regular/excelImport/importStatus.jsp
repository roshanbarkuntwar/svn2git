<%-- 
    Document   : importStatus
    Created on : Mar 16, 2019, 4:12:54 PM
    Author     : ayushi.jain
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="resources/css/excelImport/excelImport.css" rel="stylesheet"> 
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>
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
            <li   class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#importStatus"><p> Import Status </p></a></li>
            <li style="cursor: not-allowed;"  class="nav-item mr-5"><a class="nav-link disabled"  style="pointer-events:none;"><p>Import File </p> </a></li>
            <li  style="cursor: not-allowed;"  class="nav-item"><a class="nav-link disabled"  style="pointer-events:none;"><p> Processed File Error Result </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">


        <div id="importStatus" class="tab-pane importStatus-container  show in active "> 
            <div class="container-fluid pt-2">



                <%--  <s:if test="%{sessionResult != null}">

                    <div class="text-center col-md-12" id="session_notification" >
                        <div class="d-inline-block">
                            <s:div cssClass="%{#session.ERRORCLASS}">
                                <!--<i class="fa fa-check mr-2" aria-hidden="true"></i>-->
                                <h5 class="d-inline-block" > 
                <%--<s:property value="sessionResult" />
            </h5>
        </s:div>
    </div>
</div>

                </s:if> --%> 
                <input type="hidden" name="seqNo" id="processSeqNo" value="<s:property value="sessionResult" /> " />


                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="">
                            <!--                            <div class="card-header text-white position-relative">
                                                            <i class="fa fa-upload card-header__icon position-absolute" aria-hidden="true"></i> Template Upload
                                                            <div class="card-header__btn-section position-absolute">
                                                                <button class="btn btn-sm refresh-btn" title="Refresh" onclick="refreshStatus('uploadTokenNo');"><i class="fa fa-refresh" aria-hidden="true"></i></button>
                            
                            <s:if test="%{tokenInsertObj == null || tokenInsertObj.tokenNo == null  || !tokenInsertObj.processStatus.equalsIgnoreCase('Running')}">   
                                <button class="btn btn-sm kill-process d-inline-flex" title="Kill Process" onclick="killProcess('uploadTokenNo');"><img class="align-self-center" src="resources/images/global/killProcess.png"></button>
                                <button type="button" class="btn btn-sm btn-danger" onclick="killProcess('uploadTokenNo');" >Kill Process</button> 
                            </s:if>
                            <s:else>
                                <button class="btn btn-sm kill-process d-inline-flex" title="Kill Process" disabled="true"><img class="align-self-center" src="resources/images/global/killProcess.png"></button>

                            </s:else>
                        </div>
                    </div>-->
                            <div class="">
                                <!--                                <div class="row form-group  m-0 justify-content-right ">                              
                                
                                                                    <button type="button" class="form-btn float-right" onclick="callUrl('/tdsImportStatus');"><i class="fa fa-refresh" aria-hidden="true"></i>Refresh</button>
                                
                                                                </div>   -->

                                <div class="row form-group pt-2">
                                    <div class="col-lg-5 col-xl-5 text-right">
                                        <label class="control-label">
                                            Token No <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-7">
                                        <s:hidden name="tokenUploadObj.templateCode" id="uploadTemplateCode" />
                                        <s:hidden name="tokenUploadObj.processType" id="uploadprocessTypeCode" />                                      
                                        <s:hidden name="tokenUploadObj.processStatus" id="uploadprocessStatus" />                                      
                                        <div class="input-group">
                                            <s:textfield  name="tokenUploadObj.tokenNo"  cssClass="form-control" placeholder="Token No." id="uploadTokenNo" />
                                            <div class="input-group-append">
                                                <button   type="button" class="btn btn-primary btn-sm addon-btn" onclick="openTokenStatus('importStatus');" title="Import File Tokens" ><i class="fa fa-link rotate-90"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-lg-5 col-xl-5 text-right">
                                        <label class="control-label">
                                            Template <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-7">
                                        <s:textfield  name="tokenUploadObj.templateName"  cssClass="form-control" placeholder="Process Type" id="uploadProcessType"/>
                                    </div>
                                </div>
                                <s:if test="%{tokenUploadObj.processTypeName !=null}">
                                    <div class="row form-group">
                                        <div class="col-lg-5 col-xl-5 text-right">
                                            <label class="control-label">
                                                Process Insert Mode <span class="font-weight-bold text-danger ml-1">*</span>
                                            </label>
                                        </div>
                                        <div class="col-lg-7 col-xl-7">
                                            <s:textfield  name="tokenUploadObj.processTypeName"  cssClass="form-control"  placeholder="  Process Status" />
                                        </div>
                                    </div>
                                </s:if>
                                <div class="row form-group">

                                    <div class="col-lg-5 col-xl-5 text-right">
                                        <label class="control-label">
                                            Process Start Time <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-7">
                                        <s:textfield  name="tokenUploadObj.processStartTimestamp"  cssClass="form-control" placeholder="  Process Start Timestamp" />
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-lg-5 col-xl-5 text-right">
                                        <label class="control-label">
                                            Process End Time <span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-7">
                                        <s:textfield  name="tokenUploadObj.processEndTimestamp"  cssClass="form-control"  placeholder="Process End Timestamp" />
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-lg-5 col-xl-5 text-right">
                                        <label class="control-label">
                                            Process Remark<span class="font-weight-bold text-danger ml-1">*</span>
                                        </label>
                                    </div>
                                    <div class="col-lg-7 col-xl-7">
                                        <div class="input-group">
                                            <s:textfield  name="tokenUploadObj.processRemark"  cssClass="form-control"  placeholder="Process Remark"/>
                                            <s:if test="%{#loginuser.valuation_right == 1 && tokenUploadObj.processStatus != null}">

                                                <div class="input-group-append">
                                                    <button type="button" class="btn btn-primary btn-sm addon-btn" title="" data-toggle="modal"  onclick="getTextFileData();"><i class="fa fa-file-text"></i></button>
                                                </div>
                                            </s:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group  m-0 justify-content-between">

                                    <div class="col-lg-12 text-center">
                                        <button class="form-btn form-red-btn" id="killBtn" onclick="killProcess('uploadTokenNo');" <s:property value="%{killBtn}"/>><i class="fa kill-process" aria-hidden="true"></i> Kill Process</button>


                                        <button type="button" class="form-btn" onclick="refreshStatus('uploadTokenNo');"><i class="fa refresh" aria-hidden="true"></i> Refresh</button>


                                        <button type="button" id="viewBtn" class="form-btn"  <s:property value="%{viewBtn}"/> onclick="callUrl('/importMasterUploadResultAction?templateCode=<s:property value='%{tokenUploadObj.templateCode}'/>&importSeqNo=<s:property value='%{tokenUploadObj.tokenNo}'/>&processType=<s:property value='%{tokenUploadObj.processType}'/>');"><i class="fa view" aria-hidden="true"></i>View Template Records</button>

                                    </div>




                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <hr>
                <div class="row">
                    <div class="button-section col-md-8 offset-md-2 text-center my-1"> 

                        <button type="button" id="importBtn" class="form-btn mt-0" onclick="callUrl('/tdsImportAction');" <s:property value="%{importBtn}"/> ><i class="fa import" aria-hidden="true"></i>Import New</button>


                    </div>
                </div>

            </div>
        </div>




    </div>



</div>
<div class="modal fade" id="fileModal" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-log" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: flex;align-items: center;">
                <h5 class="modal-title" id="exampleModalCenterTitle">Import Process Log | <span style="color:#fffe03">Token No. : <span><s:property value='%{tokenUploadObj.tokenNo}'/></span></span></h5> 
                <div class=""close>
                    
                <button type="button" class="close" title="Close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    <span class="inline-block text-white float-right mr-2 refresh-icon" onclick="getTextFileDataRefresh();" title="Refresh"><i class="fa fa-refresh"></i></span>
                </div>
                
            </div>
            <!--<div class="modal-body" id="fileStatus">-->
            <div class="modal-body">
                
<!--                <table class="table table-sm table-striped">
                    <tbody>
                        <tr>
                            <td class="font-weight-bold">Last Modified</td>
                            <td id="lastModified"></td>

                        </tr>
                        <tr>
                            <td class="font-weight-bold">Size</td>
                            <td id="size"></td>
                        </tr>

                    </tbody>
                </table>-->
                 <div id="loader" ></div>
                <div class="log-file-container w-100 border p-3 text-justify" id="fileStatus">

                </div>
            </div>

        </div>
    </div>
</div>
<script src="resources/js/regular/excelImport/importStatus.js?<%=Math.random()%>" type="text/javascript"></script>

<script>
                    openNotificationDiv();
</script>
