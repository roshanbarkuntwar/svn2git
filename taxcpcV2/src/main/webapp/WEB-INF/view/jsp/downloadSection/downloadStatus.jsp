<%-- 
    Document   : downloadStatus
    Created on : Jun 5, 2019, 2:59:39 PM
    Author     : sneha.parpelli
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js"></script>
<link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
<div class="page-section">

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href=""><p> Download Status </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="importFile" class="tab-pane show in active">

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

                <form id="downloadStatusForm" action="downloadStatusBrowse" method="POST">
                    <s:hidden value="true" name="search"/>
                    <div class="filter-section">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group">
                                    <div class="col-lg-4">
                                        <s:select list="processTypeMap" name="processTypeForData" headerKey="" headerValue="Select Process Type" class="form-control" title="Process Type"/>
                                    </div> 
                                    <div class="col-lg-4">
                                        <s:select list="downloadStatusMap" name="downloadStatus" headerKey="" headerValue="Select Download Status" class="form-control" title="Download Status"/>
                                    </div> 
                                    <div class="col-lg-4">       
                                        <input type="text" name="tokenNo" class="form-control" title="Token No." placeholder="Token No.">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();" title="Search"><i class="fa fa-search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('downloadStatusForm', 'submit');" title="Clear"><i class="fa fa-eraser"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"
                                        title="Not Available"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                            </div> 
                        </div>  
                    </div>



                    <s:hidden value="%{msg}" id="msg"/>                    
                    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                    <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>                    
                    <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                    <s:hidden value="%{pageNumber}" id="pageNumber"/>
                    <input type="hidden" id="countCall" value="0">
                    <input type="hidden" id="fltrformId"value="downloadStatusForm">
                    <input type="hidden" id="filterFlag" name="filterFlag">
                </form>
                <!--paginator-->
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

                <!-----------------settings------------>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                <div class="table-responsive" id="dataSpan">
                    <!--Ajax Data Here-->
                    <%
                        out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                    %>
                </div>                
            </div>
        </div>
    </div>
</div>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>
<script type="text/javascript">
                                    try {
                                        defaultValues();
                                    } catch (err) {
                                    }

                                    try {
//                                        document.getElementById("paginator_top").style.display = "block";
                                        document.getElementById("bulk_download_btn").style.display = "none";
//                                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                                    } catch (e) {
                                    }
//                                    function downloadFile(index) {
//                                        var fileNamePath = document.getElementById('fileNamePath_' + index).value;
//                                        var newFileName = document.getElementById("newFileName_" + index).value;
//
//                                        var cp = document.getElementById("globalcontextpath").value;
//                                        window.location = cp + "/bulkDownloadAction?action=downloadBulkExl&fileNamePath=" + encodeURIComponent(fileNamePath) + "&fileName=" + encodeURIComponent(newFileName);
//
//                                    }
</script>