<%-- 
    Document   : deducteeErrorDetails
    Created on : Mar 13, 2019, 3:14:32 PM
    Author     : neha.bisen
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/errorDetails.css" type="text/css">
        <script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
        <link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">        
        <title>JSP Page</title>
    </head>

    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & FVU Generation Status</p></a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Details</p> </a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Result</p></a></li>
                    <li class="nav-item mr-5"><a class="nav-link active"  href="#"> <p>Error Details</p></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="tab-content px-4 py-4">
                <div class="container-fluid pt-2">
                    <div id="ErrorDetails" class="tab-pane show in active">
                        <div class="row form-group my-2">
                            <div class="col-lg-12">
                                <s:hidden id="hidden_error_type_code" name="l_error_type_code"/>
                                <div class="count-container">
                                    <div class="table-responsive mt-2">
                                        <table class="table table-bordered table-striped">
                                            <tr>
                                                <td class="font-weight-bold">Error Name :</td><td><s:property value="%{l_error_name}"/></td>

                                                <td class="font-weight-bold">Error Type Name  :</td><td><s:property value="%{l_error_type_name}"/></td>
                                            </tr>

<!--                                    <label class="mr-1"> Error Name : </label> <label><s:property value="%{l_error_name}"/> </label>
<span class="seperator mx-3"></span>
<label class="mr-1"> Error Type Name  : </label> <label><s:property value="%{l_error_type_name}"/></label>-->
                                            <s:if test="%{l_error_type_code != null && l_error_type_code != 'TSE'}">
                                                <tr>
                                                    <td class="font-weight-bold">Transaction Amount  :</td><td><s:property value="%{h_tran_amount}"/></td>

                                                    <td class="font-weight-bold">TDS Amount :</td><td><s:property value="%{h_tds_amt}"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="font-weight-bold">Calculate TDS Amount :</td><td><s:property value="%{h_calc_tda_amt}"/></td>

                                                    <td class="font-weight-bold">Difference TDS Amount :</td><td><s:property value="%{h_diff_tds_amt}"/></td>
                                                </tr>
                                                <!--                                        <span class="seperator mx-3"></span>
                                                                                        <label class="mr-1">Transaction Amount  : </label> <label><s:property value="%{h_tran_amount}"/></label>
                                                                                        <span class="seperator mx-3"></span>
                                                                                        <label class="mr-1">  TDS Amount : </label> <label><s:property value="%{h_tds_amt}"/></label>
                                                                                        <span class="seperator mx-3"></span>
                                                                                        <label class="mr-1"> Calculate TDS Amount : </label> <label><s:property value="%{h_calc_tda_amt}"/></label>
                                                                                        <span class="seperator mx-3"></span>
                                                                                        <label class="mr-1">   Difference TDS Amount : </label> <label><s:property value="%{h_diff_tds_amt}"/></label>-->
                                            </s:if>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <s:form name="errorDetails" id="errorDetailsForm" method="POST" action="errorDetails?search=true" theme="simple">
                            <s:hidden name="l_error_type_code" id="l_error_type_code"/>
                            <s:hidden name="l_error_type_name" id="l_error_type_name"/>
                            <s:hidden name="l_error_name" id="l_error_name"/>
                            <s:hidden name="l_error_code" id="l_error_code"/>
                            <s:hidden name="table_name" id="table_name"/>
                            <s:hidden name="ReadonlyFlag" id="ReadonlyFlag"/>
                            <s:hidden name="e2" id="e2"/>
                            <s:hidden name="tds_code"/>
                            <s:hidden name="deductee_code"/>
                            <s:hidden name="deductee_panno"/>
                            <s:hidden name="h_tran_amount"/>
                            <s:hidden name="h_tds_amt"/>
                            <s:hidden name="h_calc_tda_amt"/>
                            <s:hidden name="h_diff_tds_amt"/>
                            <div class="filter-section">
                                <div class="row sec-bottom">
                                    <div class="col-lg-10 col-xl-11">
                                        <div class="row form-group">
                                            <%--<div class="col-lg-3">
                                                <s:select list="selectConditionOther" name="objErrFilterList.deductee_name_condition" class="form-control" 
                                                          headerValue="--Select Deductee Name Type--" title="Deductee Name Type"/>
                                            </div>--%>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" name="objErrFilterList.deductee_panno" title="Deductee PAN" placeholder="Deductee PAN">
                                            </div> 
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" name="objErrFilterList.deductee_name" title="Deductee Name" placeholder="Deductee Name">
                                            </div>  
                                            <%--<div class="col-lg-3">
                                                <s:select list="selectConditionOther" class="form-control" headerKey="" name="objErrFilterList.deductee_panno_condition" 
                                                          headerValue="--Select Deductee PAN Type--" title="Deductee PAN Type"/>
                                            </div>--%>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" name="objErrFilterList.certificate_no" title="Certificate Number" 
                                                       placeholder="Certificate Number" maxlength="10">
                                            </div> 
                                            <div class="col-lg-3">
                                                <s:select list="selectSection" class="form-control"  
                                                          name="objErrFilterList.tds_code" title="TDS Section"/>
                                            </div> 
                                            <!--                                            <div class="col-lg-3">
                                                                                            <input type="text"  class="form-control" name="objErrFilterList.from_tran_amt" title="Transaction Amount" placeholder="Transaction Amount">
                                                                                        </div> -->
                                        </div>
                                        <div class="collapse" id="remainingFilter">
                                            <div class="row form-group">
                                                <%-- <div class="col-lg-3">
                                                     <s:select list="selectCondition" class="form-control" headerKey="" headerValue="--Select Transaction Ref. Date Type--" 
                                                               name="objErrFilterList.tran_ref_date_condition" title="Transaction Ref. Date Type"/>
                                                 </div>--%>
                                                <div class="input-group col-lg-3">
                                                    <input type="text"  class="form-control" name="objErrFilterList.from_tran_ref_date" id="tranRefDate" onblur="validateDateOnBlur(this);"
                                                           title="Transaction Ref. Date" placeholder="Transaction Ref. Date">
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-primary addon-btn" id="toDateBtn" onmouseover="openCalendar('tranRefDate', 'tranRefDateCal');doOnLoad('tranRefDate', 'tranRefDateCal')">
                                                            <i class="fa fa-calendar" id="tranRefDateCal"></i>
                                                        </button>
                                                    </div> 
                                                </div>
                                                <%--<div class="col-lg-3">
                                                    <s:select list="selectCondition" class="form-control" headerKey="" headerValue="--Select TDS Deduct Date Type--" 
                                                              name="objErrFilterList.tds_deduct_date_condition" title="TDS Deduct Date Type"/>
                                                </div>--%>
                                                <div class="input-group col-lg-3">
                                                    <input type="text"  class="form-control" name="objErrFilterList.from_tds_deduct_date" id="tdsDeductDate" onblur="validateDateOnBlur(this);"
                                                           title="TDS Deduct Date" placeholder="TDS Deduct Date">
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-primary addon-btn" id="tdsDeductDate" onmouseover="openCalendar('tdsDeductDate', 'tdsDeductDateCal');
                                                                doOnLoad('tdsDeductDate', 'tdsDeductDateCal')">
                                                            <i class="fa fa-calendar" id="tdsDeductDateCal"></i>
                                                        </button>
                                                    </div>
                                                </div> 
                                                <div class="col-lg-3">
                                                    <s:select list="selectCondition" class="form-control"  headerKey=""   headerValue="Select TDS Amount Type" 
                                                              name="objErrFilterList.tds_amt_condition" title="TDS Amount Type"/>
                                                </div>
                                                <div class="col-lg-3">
                                                    <input type="text"  class="form-control" name="objErrFilterList.from_tds_amt" title="TDS Amount" placeholder="TDS Amount">
                                                </div> 
                                            </div>
                                            <div class="row form-group">

                                                <%--<div class="col-lg-3">
                                                    <s:select list="selectCondition" class="form-control" headerKey="" headerValue="--Select Transaction Amount Type--" 
                                                              name="objErrFilterList.tran_amt_condition" title="Transaction Amount Type"/>
                                                </div>--%>

                                            </div>
                                            <div class="row form-group">
                                                <%--0<div class="col-lg-3">
                                                    <s:select list="selectConditionOther" class="form-control" headerKey="" headerValue="--Select TDS Section Type--" 
                                                              name="objErrFilterList.tds_code_condition" title="TDS Section Type"/>
                                                </div>--%>

                                                <%--<div class="col-lg-3">
                                                    <s:select list="selectConditionOther" class="form-control" headerKey="" headerValue="--Select Certificate Number Type--" 
                                                              name="objErrFilterList.certificate_no_condition" title="Certificate Number Type"/>
                                                </div>--%>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="showRecords();"><i class="fa fa-search"></i></button>
                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('transactionMgmtForm', 'submit');" title="Clear &amp; Reset"><i class="fa fa-eraser"></i></button>
                                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Show More Filter" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                                    </div> 
                                </div>  
                            </div>
                            <s:hidden name="filterFlag" id="filterFlag" value="true"/>                            
                        </s:form>
                        <!--paginator-->
                        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                        <!-----------------settings------------>
                        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                        <form name="deducteeErrorBugForm" id="deducteeErrorBugForm" action="dynamicErrorDataManipulationAction?action=BulkUpdate" method="POST">                            
                            <div class="" id="dataSpan">
                                <!--Ajax Data Here-->
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center"> 
                                        <button type="button" id="backBtn" class="form-btn <s:property value="%{backBtn}"/>" onclick="goToDetailPage();">
                                            <i class="fa previous" aria-hidden="true"></i>Back
                                        </button>

                                        <button type="button" id="updateBtn" class="form-btn <s:property value="%{updateBtn}"/>" onclick="updateErrorDetailsData();">
                                            <i class="fa update" aria-hidden="true"></i>Update
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <!-- The Modal -->
        <div class="modal" id="errorDetailModal">
            <div class="modal-dialog error-modal">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Error Details</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="row form-group my-2">
                            <div class="col-lg-12">
                                <div class="count-container">
                                    <label class="mr-1"> Error Name : </label> <label>L-TVE-1003 </label>
                                    <span class="seperator mx-3"></span>
                                    <label class="mr-1"> Error Type Name  : </label> <label>05-SEP-2018</label>
                                    <span class="seperator mx-3"></span>
                                    <label class="mr-1">Transaction Amount  : </label> <label>0.00</label>
                                    <span class="seperator mx-3"></span>
                                    <label class="mr-1">  TDS Amount : </label> <label>0</label>
                                    <span class="seperator mx-3"></span>
                                    <label class="mr-1"> Calculate TDS Amount : </label> <label>0</label>
                                    <span class="seperator mx-3"></span>
                                    <label class="mr-1">   Difference TDS Amount : </label> <label>0</label>

                                </div> 
                            </div>
                        </div>
                        <div class="filter-section">
                            <div class="row">
                                <div class="col-lg-10 col-xl-11">
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <select  class="form-control" title="Deductee Name Type">
                                                <option value="">Select Deductee Name Type</option>

                                            </select>

                                        </div>
                                        <div class="col-lg-3">
                                            <input type="text"  class="form-control" title="Deductee Name" placeholder="Deductee Name">
                                        </div>  
                                        <div class="col-lg-3">
                                            <select  class="form-control" title="Deductee PAN Type">
                                                <option value="">Select Deductee PAN Type</option>

                                            </select>

                                        </div>
                                        <div class="col-lg-3">
                                            <input type="text"  class="form-control" title="Deductee PAN" placeholder="Deductee PAN">
                                        </div>   
                                    </div>
                                    <div class="collapse" id="remainingFilter">
                                        <div class="row form-group">
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="Transaction Ref. Date">
                                                    <option value="">Select Transaction Ref. Date Type</option>
                                                </select>                                          
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" title="Transaction Ref. Date" placeholder="Transaction Ref. Date">
                                            </div> 
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="TDS Deduct Date">
                                                    <option value="">Select TDS Deduct Date Type</option>
                                                </select>                                        
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" title="TDS Deduct Date" placeholder="TDS Deduct Date">
                                            </div> 
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="TDS Amount Type">
                                                    <option value="">Select TDS Amount Type</option>
                                                </select>                                          
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" title="TDS Amount" placeholder="TDS Amount">
                                            </div> 
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="Transaction Amount">
                                                    <option value="">Select Transaction Amount Type</option>
                                                </select>                                        
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" title="Transaction Amount" placeholder="Transaction Amount">
                                            </div> 
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="TDS Section Type">
                                                    <option value="">Select TDS Section Type</option>
                                                </select>                                          
                                            </div>
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="TDS Section">
                                                    <option value="">Select TDS Section</option>
                                                </select>   
                                            </div> 
                                            <div class="col-lg-3">
                                                <select  class="form-control" title="Certificate Number">
                                                    <option value="">Select Certificate Number Type</option>
                                                </select>                                        
                                            </div>
                                            <div class="col-lg-3">
                                                <input type="text"  class="form-control" title="Certificate Number" placeholder="Certificate Number">
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="showRecords();"><i class="fa fa-search"></i></button>
                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('transactionMgmtForm', 'submit');" title="Clear &amp; Reset"><i class="fa fa-eraser"></i></button>
                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Show More Filter" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                                </div> 
                            </div>  
                        </div>                        
                        <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                        <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                        <s:hidden value="%{browseAction}" id="browseAction"/>
                        <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                        <s:hidden value="%{pageNumber}" id="pageNumber"/>
                        <input type="hidden" id="countCall" value="0">
                        <input type="hidden" id="fltrformId" value="errorDetailsForm">

                        <!--paginator-->
                        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

                        <!-----------------settings------------>
                        <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                    </div>
                </div>
            </div>
        </div>        
    </body>
    <script src="resources/js/global/paginator/globalPaginator.js?r=<%=Math.random()%>"></script>
    <script type="text/javascript">
                                        try {
                                            defaultValues();
                                        } catch (err) {
                                        }
                                        try {
                                            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//                                            document.getElementById("paginationBlock").style.display = "none";
                                        } catch (e) {

                                        }
    </script>
</html>
