<%-- 
    Document   : challanErrorDetails
    Created on : Jul 2, 2019, 1:03:12 PM
    Author     : trainee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/errorDetails.css" type="text/css">
        <script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
        <link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
        <!--<script src="resources/js/lhs/lhsGlobal.js?r=<%=Math.random()%>" type="text/javascript"></script>-->
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
                    <button type="button" class="form-btn" id="g_download" onclick="downloadStaticExcel('CHALLAN DETAILS');"><i class="fa fa-download" aria-hidden="true"></i>Capture Page Data in Excel</button>
                    <div class="row form-group my-2">
                        <div class="col-lg-12">
                            <s:set var="loginuser" value="%{#session.get('WORKINGUSER')}"/>
                            <s:hidden id="l_error_type_name" name="l_error_type_name"/>
                            <s:hidden id="l_error_type_code" name="l_error_type_code"/>
                            <s:hidden id="error_code" name="l_error_code"/>
                            <s:hidden id="error_name" name="l_error_name"/>
                            <s:hidden id="table_name" name="table_name"/>
                            <s:hidden id="ReadonlyFlag" name="ReadonlyFlag"/>
                            <s:hidden name="processLevel" value="%{processLevel}" id="h_processLevel"/>
                            <div class="count-container">
                                <table class="table table-bordered table-striped">
                                    <tr>
                                        <td class="font-weight-bold">Error Name :</td><td><s:property value="%{l_error_name}"/></td>

                                        <td class="font-weight-bold">Error Type Name  :</td><td><s:property value="%{l_error_type_name}"/></td>
                                    </tr>
                                </table>
                                <%--                                <label class="mr-1"> Error Name : </label> <label><s:property value="%{l_error_name}"/> </label>
                                                                <span class="seperator mx-3"></span>
                                                                <label class="mr-1"> Error Type Name  : </label> <label><s:property value="%{l_error_type_name}"/></label>                                --%>
                            </div>
                        </div>
                    </div>
                    <!--paginator-->
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                    <!-----------------settings------------>
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                    <s:form name="challanErrorDetails" id="challanErrorDetails" method="POST" action="challanDataManipulationAction?action=ChallanBlkupdate">
                        <div class="" id="dataSpan">
                            <!--Ajax Data Here-->
                        </div>
                        <!--                        <div class="form-group">
                                                    <div class="row">
                                                        <div class="button-section col-md-12 my-1 text-center"> 
                                                            <a href="tdsProcessError?validate=true&selectedUserLevel=<s:property value="%{selectedUserLevel}"/>">
                                                                <button type="button" id="backBtn" class="form-btn <s:property value="%{backBtn}"/>">
                                                                    <i class="fa fa-chevron-left" aria-hidden="true"></i>Back
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>-->
                        <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                        <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                        <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                        <s:hidden value="%{pageNumber}" id="pageNumber"/>
                        <input type="hidden" id="countCall" value="0">
                        <input type="hidden" id="fltrformId" value="errorDetailsForm">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
    <script src="resources/js/global/paginator/globalPaginator.js"></script>
    <script type="text/javascript">
                        try {
                            defaultValues();
                        } catch (err) {
                        }
                        try {
                            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                            document.getElementById("pagination_top").style.display = "block";
                        } catch (e) {

                        }
    </script>
</html>
