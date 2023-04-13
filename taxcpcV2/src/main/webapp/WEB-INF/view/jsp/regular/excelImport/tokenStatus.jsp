<%-- 
    Document   : tokenStatus
    Created on : Jun 23, 2021, 7:47:53 AM
    Author     : kapil.gupta
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
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
            <!--            <li  style="cursor: not-allowed;" class="nav-item mr-5"><a class="nav-link disabled"  href="tdsImportStatus" style="pointer-events:none;"><p> Import Status </p></a></li>
                        <li  style="cursor: not-allowed;" class="nav-item mr-5"><a class="nav-link disabled" style="pointer-events:none;"><p>Import File </p> </a></li>
                        <li  style="cursor: not-allowed;"  class="nav-item mr-5"><a class="nav-link disabled"  style="pointer-events:none;"><p> Processed File Error Result </p></a></li>-->
            <li  class="nav-item" ><a class="nav-link active"  ><p id="token_status_msg"> <s:property value="headerMessage"/> </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="TokenPage" class="tab-pane importStatus-container  show in active ">
            <div class="container-fluid pt-2">
                <s:if test="hasActionErrors()">
                    <div class="text-center col-md-12" id="errorMsgDiv">
                        <div class="d-inline-block">
                            <div class="form-validation form-validation--error p-1 my-1">
                                <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                                <h5 class="d-inline-block" id="errorMsg"><s:actionerror/></h5>
                            </div>
                        </div>
                    </div>
                </s:if>
                <form id="tokenStatusForm" action="getTokenStatus" method="POST">
                    <s:hidden value="true" name="search"/>
                    <div class="filter-section pt-2">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group">
                                    <s:if test="%{msgFlag != null && msgFlag == 'tokenStatus'}">
                                        <div class="col-lg-4">
                                            <s:select id="mainProcessType" name="mainProcessType" onchange="changeProcessType();" headerKey="" headerValue="Select Main Process Type" list="#{'I':'Import Process','P':'Error Process and FVU Generation'}" class="form-control"></s:select>
                                            </div>
                                    </s:if>
                                    <div class="col-xl-4">
                                        <s:select id="procesType" name="procesType" headerKey="" headerValue="Select Process Type" list="processTypes" class="form-control"></s:select>
                                        </div>
                                    <s:if test="%{msgFlag != null && msgFlag == 'importStatus'}">
                                        <div class="col-lg-4">
                                            <s:select id="templateType" name="templateCode" headerKey="" headerValue="Select Template Type" list="templateTypes" class="form-control"></s:select>
                                            </div>
                                    </s:if>
                                    <s:if test="%{msgFlag != null && msgFlag == 'fvuStatus'}">
                                        <div class="col-lg-4">
                                            <s:select id="loginType"  list="#{'1':'All (Login & Branch Level)','0':'Login Level'}" name="loginType" headerKey="" headerValue="Select User Level"  class="form-control"></s:select>
                                            </div>
                                    </s:if>
                                    <s:if test="%{msgFlag != null && msgFlag == 'tokenStatus'}">
                                        <div class="col-lg-4">
                                            <input type="text" name="tokenNo" id="tokenNo" class="form-control" title="Token No." disabled placeholder="Token No.">
                                        </div> 
                                    </s:if><s:else>
                                        <div class="col-lg-4">
                                            <input type="text" name="tokenNo" id="tokenNo" class="form-control" title="Token No." placeholder="Token No.">
                                        </div> 
                                    </s:else>

                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="validateTokenFilter();" title="Search"><i class="fa fa-search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('tokenStatusForm', 'submit');" title="Refresh"><i class="fa fa-eraser"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"
                                        title="Not Available"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                            </div> 
                        </div>  
                    </div>

                    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                    <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>                    
                    <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                    <s:hidden value="%{pageNumber}" id="pageNumber"/>
                    <input type="hidden" id="countCall" value="0">
                    <input type="hidden" id="fltrformId"value="tokenStatusForm">
                    <input type="hidden" id="filterFlag" name="filterFlag">
                    <s:hidden  id="msgFlag" name="msgFlag"/>
                </form>
            </div>      
            <div class="clearfix"></div>
            <!--paginator-->

            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

            <div class="clearfix"></div> 

            <div class="table-responsive" id="dataSpan">
                <!--Ajax Data Here-->
                <%
                    out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
            </div> 
            <!--            <div class="row">
                            <div class="button-section col-md-8 offset-md-2 text-center my-1"> 
            
                                <button type="button" id="importBtn" class="form-btn mt-0" onclick="lhsResetform('tokenStatusForm', 'submit');"><i class="fa refresh" title="Refresh Token List" aria-hidden="true"></i>Refresh Token List</button>
                                <button type="button" class="form-btn" onclick="callUrl('/dashboard');" title="Back"> 
                                    <i class="fa back" aria-hidden="true"></i>Back
                                </button>
                            </div>
                            <div class="text-info">
                                                    <span style="    left: -47px;
                                                          position: relative;"><sup class="text-danger">**</sup>Its show only last week token</span>
                            </div>
                        </div>-->
        </div>
    </div>
</div>
 <div class="modal fade" id="fileModal" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-log" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: flex;align-items: center;">
                <h5 class="modal-title" id="exampleModalCenterTitle">Token Status Log | <span style="color:#fffe03">Token No. : <span id="token_number"></span></span></h5> 
                <div class="">
                    
                <button type="button" class="close" title="Close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    <span class="inline-block text-white float-right mr-2 refresh-icon" onclick="getTokenStatusRefresh();" title="Refresh"><i class="fa fa-refresh"></i></span>
                   
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
                
<div class="modal fade" id="fileModalL" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Log View</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!--<div class="modal-body" id="fileStatus">-->
            <div class="modal-body">
                <!--<h5> <a class=" btn btn-link text-primary float-right" onclick="refereshStatus();"><i class="fa fa-refresh mr-1"></i>Refresh</a></h5>-->
                <table class="table table-sm table-striped">
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
                </table>

                <div class="log-file-container w-100 border p-3 text-justify" id="fileStatus">

                </div>
            </div>

        </div>
    </div>
</div>
             
                
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/regular/excelImport/importStatus.js?<%=Math.random()%>" type="text/javascript"></script>
<script type="text/javascript">
                    try {
                        defaultValues();
                    } catch (err) {
                    }

                    try {
                        //                            document.getElementById("paginator_top").style.display = "block";
                        document.getElementById("bulk_download_btn").style.display = "none";
                        //                            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                    } catch (e) {
                    }
</script>