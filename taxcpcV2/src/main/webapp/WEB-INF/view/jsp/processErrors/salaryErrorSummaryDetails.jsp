<%-- 
    Document   : salaryErrorSummaryDetails
    Created on : Jul 5, 2019, 12:18:17 PM
    Author     : trainee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/errorDetails.css" type="text/css">
        <script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
        <link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
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
                    <div class="row form-group my-2">
                        <div class="col-lg-12">
                            <s:hidden id="hidden_error_type_code" name="l_error_type_code"/>
                            <div class="count-container">
                                <table class="table table-bordered table-striped">
                                    <tr>
                                        <td class="font-weight-bold">Error Name :</td><td><s:property value="%{l_error_name}"/></td>

                                        <td class="font-weight-bold">Error Type Name  :</td><td><s:property value="%{l_error_type_name}"/></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <form name="salaryErrorDetailForm" id="salaryErrorDetailForm" method="post" action="#">
                        <div class="filter-section">
                            <div class="row sec-bottom mt-2">
                                <div class="col-lg-10 col-xl-11">
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <s:textfield type="text" name="deductee_panno" id="deductee_panno" class="form-control" placeholder="Deductee PAN No." />
                                        </div>
                                        <div class="col-lg-3">
                                            <%--<s:textfield type="text" name="" id="" class="form-control" title="TAN No." placeholder="TAN No." />--%>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-2 col-xl-1 justify-content-between">
                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecords();"><i class="fa search"></i></button>
                                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('salaryErrorDetailForm', 'submit');"><i class="fa clear"></i></button>                        
                                </div> 
                            </div>                
                        </div> 
                        <s:hidden id="l_error_type_name" name="l_error_type_name"/>
                        <s:hidden id="l_error_type_code" name="l_error_type_code"/>
                        <s:hidden id="error_code" name="l_error_code"/>
                        <s:hidden id="error_name" name="l_error_name"/>
                        <s:hidden id="table_name" name="table_name"/>
                        <s:hidden id="l_ReadonlyFlag" name="ReadonlyFlag"/>
                        <s:hidden name="filterFlag" id="filterFlag" value="true"/>                    
                        <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                        <s:hidden value="%{dataGridAction}" id="dataGridAction"/>   
                        <input type="hidden" id="countCall" value="0">
                        <input type="hidden" id="fltrformId" value="salaryErrorDetailForm">
                    </form>
                </div>
                <!--paginator-->
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                <!-----------------settings------------>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                <div class="table-responsive mt-2" id="dataSpan">
                    <!--Ajax data here-->
                </div>

                <div class="clearfix"></div> 
                <div class="row">
                    <s:hidden id="h_processLevel" value="%{processLevel}"/>
                    <div class="button-section col-md-12 my-1 text-center"> 
                        <a href="tdsProcessError?validate=true&selectedUserLevel=<s:property value="%{processLevel}"/>" >
                            <button type="button" id="backBtn" class="form-btn <s:property value="%{backBtn}"/>">
                                <i class="fa fa-chevron-left" aria-hidden="true"></i>Back
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="resources/js/global/paginator/globalPaginator.js?r=<%=Math.random()%>"></script>
    <script type="text/javascript">
                            defaultValues();
                            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
    </script>
</html>
