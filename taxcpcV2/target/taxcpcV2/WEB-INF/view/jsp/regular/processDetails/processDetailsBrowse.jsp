<%-- 
    Document   : processDetailsBrowse
    Created on : Nov 25, 2019, 3:14:20 PM
    Author     : gaurav.khanzode
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<s:set var="asmt" value="%{#session.get('ASSESSMENT')}"/>
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
            <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#"><p>Process Details</p> </a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">
        <div class="tab-pane show in active">
            <div class="button-section my-1">

            </div>
        </div>
         <input type="hidden" name="currentopentokenno" id="current_open_token_no">
         <input type="hidden" name="currentprocessstatus" id="current_process_status">
        

        <s:form name="processDetailForm" id="processDetailForm" method="POST" action="processDetails?search=true" theme="simple">
            <div class="filter-section pt-1">
                <div class="row sec-bottom">
                    <div class="col-lg-10 col-xl-11">

                        <div class="row form-group mt-2">
                            <div class="col-lg-3">
                                <s:select list="templateTypes" id="template_code" name="detailsBeanObj.template_code" headerKey="" 
                                          headerValue="Select Template Type" cssClass="form-control" onchange="getProcessSequenceNumbersList(this.value);"/>
                            </div>

                            <div class="col-lg-3">
                                <input type="text" list="processSeqList" id="processSeq" name="detailsBeanObj.process_seqno" 
                                       class="form-control" placeholder=" Select Process Sequence Number "/>
                                <datalist id="processSeqList">
                                    <!--Ajax options here-->
                                </datalist>
                            </div>
<!--                            <div class="col-lg-3">
                                <label id="procTimestamp"></label>
                            </div>-->

                        </div>

                    </div>

                    <div class="col-lg-2 col-xl-1 pl-0 mt-2 d-flex justify-content-between">
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();"><i title="Search" class="fa search"></i></button>
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('processDetailForm', 'submit');"><i title="Clear" class="fa clean"></i></button>
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                    </div>
                </div>
            </div>
            <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
            <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
            <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
            <s:hidden value="%{pageNumber}" id="pageNumber"/>
            <input type="hidden" id="countCall" value="0">
            <input type="hidden" id="fltrformId" value="processDetailForm">
            <input type="hidden" name="filterFlag" id="filterFlag">
        </s:form>
        <div class="clearfix"></div>  
        <!--paginator-->
        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %> 

        <!-----------------settings------------>
        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 
        <div class="clearfix"></div> 
        <!--Table Section-->

        <div class="table-responsive mt-2" id="dataSpan">
            <!--Process Details Ajax Data Here-->
            <%
                out.println(GlobalMessage.PAGINATION_SHOW_MORE);
            %>

        </div>
    </div>
            
   
</div>
            
 <div class="modal fade" id="processDetailsLogModal" tabindex="-1" role="dialog" aria-labelledby="fileModal" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-log" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: flex;align-items: center;">
                <h5 class="modal-title" id="exampleModalCenterTitle">Process Details Log | <span style="color:#fffe03">Token No. : <span id="span_token_no"></span></span></h5> 
                <div class="">
                    
                <button type="button" class="close" title="Close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    <span id="process_log_refresh" class="inline-block text-white float-right mr-2 refresh-icon" onclick="processDetailsLogRefresh();" title="Refresh"><i class="fa fa-refresh"></i></span>
                   
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
                       
<script src="resources/js/regular/processDetails/processDetails.js?r=<%= Math.random()%>"></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script> 
<link href="resources/css/excelImport/excelImport.css" rel="stylesheet">
<script type="text/javascript">
                                   
                                    try {
                                        //document.getElementById("paginator_top").style.display = "block";
                                        document.getElementById("bulk_download_btn").style.display = "none";
                                    } catch (e) {
                                    }                              
</script>
