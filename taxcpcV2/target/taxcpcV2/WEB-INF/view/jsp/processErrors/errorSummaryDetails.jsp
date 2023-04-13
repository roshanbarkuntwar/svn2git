<%-- 
    Document   : errorSummaryDetails
    Created on : Jun 20, 2019, 3:41:52 PM
    Author     : gaurav.khanzode
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>JSP Page</title>

        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/errorDetails.css" type="text/css">
        <link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">

        <script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
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
                                        </table>
                                    </div>
                                    <%--                                    <label class="mr-1"> Error Name : </label> <label><s:property value="%{l_error_name}"/> </label>
                                                                        <span class="seperator mx-3"></span>
                                                                        <label class="mr-1"> Error Type Name  : </label> <label><s:property value="%{l_error_type_name}"/></label>                                    --%>
                                </div>
                            </div>
                        </div>

                        <%--<s:if test="%{l_error_code.equalsIgnoreCase('L-TVE-1005')}">--%>                            
                        <!--<button type="button" class="btn form-btn" title="Update - With TDS Error" onclick="callHigerRateProcedure('tdsWithError');"><i class="fa fa-pencil-square" aria-hidden="true"></i> Update - With TDS Error </button>-->

                        <!--<button type="button" class="btn form-btn" title="Update - Without TDS Error" onclick="callHigerRateProcedure('tdsWithOutError');"><i class="fa fa-pencil-square" aria-hidden="true"></i> Update - Without TDS Error </button>-->
                        <%--</s:if>--%>
                    </div>
                    <form id="errorSummary1">
                        <s:hidden id="l_error_type_name" name="l_error_type_name"/>
                        <s:hidden id="l_error_type_code" name="l_error_type_code"/>
                        <s:hidden id="error_code" name="l_error_code"/>
                        <s:hidden id="h_processLevel" name="processLevel"/>
                        <s:hidden id="error_name" name="l_error_name"/>
                        <s:hidden id="table_name" name="table_name"/>
                        <s:hidden id="l_ReadonlyFlag" name="ReadonlyFlag"/>
                        <s:hidden id="l_e2" name="e2"/>
                        <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                        <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                        <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                         <s:hidden value="%{tokenNo}" id="tokenNo"/>
                        <input type="hidden" id="countCall" value="0">
                        <input type="hidden" id="fltrformId" value="errorSummary1">

                    </form>
                    <!--paginator-->
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                    <!-----------------settings------------>
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                    <div class="" id="dataSpan">
                        <!--Ajax Data Here-->
                        <%
                            out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="resources/js/global/paginator/globalPaginator.js"></script>
    <script>
        try {
            defaultValues();
        } catch (err) {
        }
        try {
//                                    $('#bulk_download_btn').removeClass('d-none');
            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//            document.getElementById("paginationBlock").style.display = "none";
        } catch (e) {

        }
    </script>
</html>
