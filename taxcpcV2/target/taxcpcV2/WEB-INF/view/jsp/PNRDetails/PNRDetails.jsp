<%-- 
    Document   : PNRDetails
    Created on : Feb 4, 2019, 3:45:27 PM
    Author     : neha.bisen
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">

<div class="page-section">
    <!--    <div class="col-md-12 my-1 main-page-heading">
            <h4 class="page-title mb-2">PRN Details</h4>
        </div>-->
    <div class="tab-section col-md-12 my-1 pt-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href=""><p> PRN Details </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div class="button-section  my-1">
            <button type="button" class="form-btn" id="g_download" onclick="downloadStaticExcel('PRN DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
            <button type="button" class="form-btn" onclick=" $('#dialog-confirm-refresh-append-prn').modal('show');" ><i class="fa refresh" aria-hidden="true"></i>Refresh/Append Records</button>   
<!--            onclick="refreshRecords();"-->
            

            <!--            <div class="custom-control custom-radio custom-control-inline ml-3">
                            <input type="radio" class="custom-control-input" name="clientType" id="clientTypeDefault" checked onclick="setProcessLevel();">
                            <label class="custom-control-label" for="clientTypeDefault">Login Level
                            </label>
                        </div>
            
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" name="clientType" id="clientType" onclick="setProcessLevel();">
                            <label class="custom-control-label" for="clientType">All (Login &amp; Branch Level)</label>
                        </div>-->
        </div>
        <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
            <div class="d-inline-block">
                <div class="form-validation form-validation--info p-1 my-1">
                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                    <h5 class="d-inline-block" id="notificationMsg"></h5>
                </div>
            </div>
        </div>
        <div class="text-center col-md-12" id="errorMsgDiv" style="display:none">
            <div class="d-inline-block">
                <div class="form-validation form-validation--error p-1 my-1">
                    <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                    <h5 class="d-inline-block" id="errorMsg"></h5>
                </div>
            </div>
        </div>
        <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
            <div class="d-inline-block">
                <div class="form-validation form-validation--success p-1 my-1">
                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                    <h5 class="d-inline-block" id="successMsg"></h5>
                </div>
            </div>
        </div>
        <s:form name="prnFilterForm" id="prnFilterForm" method="post" action="prnInfo?search=true" theme="simple">
            <div class="filter-section">
                <div class="row sec-bottom mt-2">
                    <div class="col-lg-10 col-xl-11">
                        <div class="row form-group">
                            <div class="col-lg-3">
                                <s:select class="form-control" list="prnUpdateFlagList" name="prnUpdateFlag" id="prnUpdateFlag"/>
                            </div>
                            <div class="col-lg-3">
                                <s:textfield type="text" name="filterTan" id="filterTan" class="form-control" title="TAN No." placeholder="TAN No." />
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-xl-1 pl-0 mt-2 d-flex justify-content-between">
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();"><i title="Search" class="fa search"></i></button>
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('prnFilterForm', 'submit');"><i title="Clear" class="fa clear"></i></button>                        
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                    </div> 
                </div>                
            </div>       

            <s:hidden value="0" id="processLevel" name="processLevel"/>
            <s:hidden value="%{msg}" id="msg"/>
            <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
            <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
            <s:hidden value="%{browseAction}" id="browseAction"/>
            <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
            <s:hidden value="%{pageNumber}" id="pageNumber"/>
            <input type="hidden" id="countCall" value="0">
            <input type="hidden" id="fltrformId" value="prnFilterForm">
            <input type="hidden" id="filterFlag" name="filterFlag">
        </s:form>
        <div class="clearfix"></div> 
        <!--paginator-->
        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

        <!-----------------settings------------>
        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

        <div class="table-responsive mt-2" id="dataSpan">
            <%
                out.println(GlobalMessage.PAGINATION_SHOW_MORE);
            %>
        </div>
        <!--Table Section-->
        <div class="clearfix"></div> 



    </div>
        
        <!--User Levl Modal-->
        <div class="modal" tabindex="-1" id="dialog-confirm-refresh-append-prn" role="dialog" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="">Process PRN</h5>                        
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
                                <label class="control-label">Process PRN Mode<span class="text-danger font-weight-bold ml-1">*</span>:</label>
                            </div>
                            <div class="col-md-8">
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="refresh_prn" name="process_prn" value="refresh">
                                    <label class="custom-control-label" for="refresh_prn" title="Refresh" style="font-size: 13px;">Refresh</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="append_prn"  checked="checked"  name="process_prn" value="append">
                                    <label class="custom-control-label" for="append_prn" title="Append" style="font-size: 13px;">Append</label>
                                </div>
                            </div>
                        </div>
            </div>
             <div class="modal-footer justify-content-center">
                 <button type="button" class="form-btn" title="Process" onclick="processRecords();"><i class="fa fa-step-forward" aria-hidden="true"></i>Process</button>
                        <button type="button" class="form-btn" title="Cancel" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                    </div>
                </div>
            </div>
        </div>
        <!--User Levl Modal-->
</div>
        
        
        
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/regular/prnInformation/prnInformation.js?r=<%= Math.random()%>"></script>
<script type="text/javascript">
                            try {
                                defaultValues();
                            } catch (err) {

                            }
                            try {
//                                getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//        document.getElementById("paginationBlock").style.display = "none";
                            } catch (e) {
                            }
                            onloadMsg();
</script>
