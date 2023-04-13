<%-- 
    Document   : DeducteeErrorDetails15GH
    Created on : May 7, 2019, 6:20:28 PM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processErrorResult/processErrorResult.css" type="text/css">
        <script src="resources/js/global/paginator/globalPaginator.js"></script>
        <script type="text/javascript" src="resources/js/form15GH/validateError/validation15GH.js?r=<%=Math.random()%>"></script>        
        <script type="text/javascript" src="resources/js/lhs/lhsAjax.js"></script>
        <title></title>
    </head>
    <body>
        <div class="tab-section col-md-12 my-1">
            <ul class="nav nav-pills">
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Status</p></a></li>
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Details</p> </a></li>
                <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Result</p></a></li>
                <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>15GH Error Details</p></a></li>
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
                                <label class="mr-1"> Error Name : </label> <label><s:property value="%{l_error_name}"/> </label>
                                <span class="seperator mx-3"></span>
                                <label class="mr-1"> Error Type Name  : </label> <label><s:property value="%{l_error_type_name}"/></label>
                                <span class="seperator mx-3"></span>
                                <label class="mr-1"> Default Values  : </label> <label><s:property value="%{defaultColumnValue}"/></label>
                            </div>
                        </div>
                    </div>

                    <div class="row mx-0">
                        <div class="button-section my-1">
                            <s:hidden id="loginuserrights" value="%{#loginuser.getEdit_right()}"/>
                            <s:if test="%{#loginuser.getEdit_right() != null && (#loginuser.getEdit_right() == 1)}">
                                <button type="button" class="form-btn" id="g_download" onclick="downloadStaticExcel('15GH PROCESS ERROR SUMMARY');"><i class="fa fa-download" aria-hidden="true"></i>Capture Page Data in Excel</button>
                                <!--                                <button type="button" class="form-btn" onclick="DownloadTemplate('deducteeDetailForm', 'excelDownloadDeductee15GHErrorDetails');">
                                                                    <i class="fa fa-download" aria-hidden="true"></i>
                                                                    Download
                                                                </button>-->
                            </s:if>
                            <s:else>
                                <button type="button" class="form-btn">
                                    <i class="fa fa-download" aria-hidden="true"></i>
                                    Capture Page Data in Excel
                                </button>
                            </s:else>
                            <s:if test="%{#loginuser.getEdit_right() != null && (#loginuser.getEdit_right() == 1)}">
                                <button type="button" class="form-btn" onclick="updateDefaultValueData15GH();">
                                    <i class="fa fa-download" aria-hidden="true"></i>
                                    Update Default Value
                                </button>
                            </s:if>
                            <s:else>                                
                                <button type="button" class="form-btn" onclick="">
                                    <i class="fa fa-download" aria-hidden="true"></i>
                                    Update Default Value
                                </button>
                            </s:else>
                        </div>
                    </div>



                    <!--paginator-->
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                    <!-----------------settings------------>
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

                    <form name="deducteeErrorForm" id="deducteeDetailForm" method="POST" action="">            
                        <div class="table-responsive mt-2" id="dataSpan">
                            <!--data from ajax-->
                        </div>
                        <div class="btn-action-section  bg-white text-center fixed-bottom">
                            <button type="button" class="form-btn" onclick="callUrl('/processErrors15GH?validate=true&selectedUserLevel=<s:property value="%{selectedUserLevel}"/>');"> 
                                <i class="fa back" aria-hidden="true"></i>Back
                            </button>
                        </div>
                        <s:hidden id="l_error_type_name" name="l_error_type_code"/>
                        <s:hidden id="l_error_type_code" name="l_error_type_name"/>
                        <s:hidden id="error_code" name="l_error_code"/>
                        <s:hidden id="error_name" name="l_error_name"/>
                        <s:hidden id="table_name" name="table_name"/>
                        <s:hidden id="defaultColumnValue" name="defaultColumnValue"/>
                        <s:hidden id="processCnfChkBxID" name="processCnfChkBx"/>
                        <s:hidden id="clientLoginLevel" name="clientLoginLevel"/>
                        <s:hidden id="selectedValue" name="selectedValue"/>
                        <s:hidden id="upload_purpose" name="upload_purpose"/>
                        <s:hidden id="l_error_process_type" name="l_error_process_type"/>                        
                        <s:hidden id="loginLevel" name="loginLevel"/>
                        <s:hidden id="selectedUserLevel" name="selectedUserLevel"/>
                    </form>
                </div>
            </div>
        </div>
        <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
        <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
        <s:hidden value="%{browseAction}" id="browseAction"/>
        <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
        <s:hidden value="%{pageNumber}" id="pageNumber"/>
        <input type="hidden" id="countCall" value="0">
        <input type="hidden" id="fltrformId" value="deducteeDetailForm">
    </body>

    <script type="text/javascript">
        try {
            defaultValues();
        } catch (err) {
        }
        try {
            document.getElementById("paginator_top").style.display = "block";
            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
        } catch (e) {
            console.log("e..." + e);
        }
    </script>
</html>
