<%-- 
    Document   : lowerDeductionCertificateBrowse
    Created on : Feb 4, 2019, 6:36:24 PM
    Author     : ayushi.jain
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>


<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Lower Deduction Certificate Browse </p> </a></li>
            <li onclick="callEntryPage();" class="nav-item"><a class="nav-link" data-toggle="pill" href="#entry"><p> Lower Deduction Certificate Entry </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">


            <s:if test="%{resultMessage!=null && resultMessage!='' && resultMessage !='null'}">
                <div class="text-center col-md-12" id="session_notification" style="position: relative; top: 7px;text-align: center;">
                    <s:div class="%{#session.ERRORCLASS}" >
                        <i class="fa fa-check mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" ><s:property value="resultMessage"/></h5>
                    </s:div>
                </div>
            </s:if>


            <div class="button-section  my-1 pt-1"> 
                <button type="button" class="form-btn" id="g_download" onclick="downloadStaticExcel('LOWER DEDUCTEE DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                <!--<button type="button" class="form-btn"><i class="fa import" aria-hidden="true"></i>Import</button>-->
            </div>

            <s:form name="lowerDeductionForm" id="lowerDeductionForm" method="post" action="tdsLowerDeductionBrowse?search=true" theme="simple"> 
                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" maxLength="10" onkeypress="return lhsValidateAlphaNumericWithoutSpclChars(event);" cssStyle="text-transform: uppercase;" id="panno" placeholder="PAN No." title="PAN No." name="tdsSplRateMastFltrSrch.deductee_panno"/>
                                </div>
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="deducteeRefNo"  name="tdsSplRateMastFltrSrch.certificate_no" placeholder="Certificate No." title="Certificate No."
                                                 maxlength="10"/>

                                </div>

                                <!--                                <div class="col-lg-3">
                                
                                                                    <select class="form-control" title="Error List" name="tdsSplRateMastFltrSrch.tds_type_code">
                                                                        <option value="">Select Tds Type</option>                                       
                                                                        <option value="24Q">24Q (Salary)</option>
                                                                        <option value="26Q">26Q (Non-Salary)</option>
                                                                        <option value="27EQ">27EQ (TCS)</option>
                                                                        <option value="27Q">27Q (Non-Resident)</option>
                                                                    </select>
                                
                                                                </div>-->
                                <div class="col-lg-3">
                                    <%--<s:select list="tdsSectionList" cssClass="form-control" title="Section" id="section" name="tranFilter.section" />--%>

                                </div>    

                            </div>

                            <div class="collapse" id="remainingFilter">

                            </div>
                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();" title="Search"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Clear" onclick="lhsResetform('lowerDeductionForm', 'submit');"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" title="Not available" data-toggle="collapse" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>  
                </div> 

                <input type="hidden" name="filterFlag" id="filterFlag">

            </s:form>
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

            <div class="clearfix"></div>
            <div class="table-responsive mt-2" id="dataSpan">
                <%
                    out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
            </div>
            <!--Table Section-->
            <div class="clearfix"></div> 


        </div>


    </div>
</div>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{dataGridAction}" id="dataGridAction"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<input type="hidden" id="countCall" value="0">
<input type="hidden" id="fltrformId" value="lowerDeductionForm">
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>"></script>
<script src="resources/js/regular/lowerDeduction/lowerDeductionCertificate.js"></script>
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script type="text/javascript">
                                try {
                                    defaultValues();
                                } catch (err) {

                                }
                                try {
//                                    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//        document.getElementById("paginationBlock").style.display = "none";
                                } catch (e) {

                                }


</script>

