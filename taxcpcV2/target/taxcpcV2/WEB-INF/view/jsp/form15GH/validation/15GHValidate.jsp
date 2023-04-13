<%-- 
    Document   : 15GHValidate
    Created on : Feb 27, 2019, 3:34:13 PM
    Author     : ayushi.jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="dao.generic.DbFunctionExecutorAsString"%>
<%@page import="globalUtilities.Util"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hibernateObjects.LhssysFileTran"%>
<%@page import="dao.LhssysFileTranDAO"%>
<%@page import="dao.generic.DAOFactory"%>
<%@page import="hibernateObjects.ViewClientMast"%>
<%@page import="com.lhs.taxcpcv2.bean.Assessment"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <script type="text/javascript" src="resources/js/form15GH/validateError/validation15GH.js?r=<%=Math.random()%>"></script>
        <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
        <title>Validate Error 15GH</title>
        <script>
            $(document).ready(function () {

                $(".hide-processErrorParameters").click(function () {
                    $("#processErrorParameters").hide();
                });

                $(".show-processErrorParameters").click(function () {
                    $("#processErrorParameters").show();
                });
            });
        </script>
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Status</p></a></li>
                    <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>15GH Validation & Error Process Details</p> </a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Validation & Error Process Result</p></a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link" style="pointer-events:none;" href="#"><p>15GH Error Details </p></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <s:form id="processErrors15GHForm" name="processErrors15GHForm" action="errorStatus15GH" 
                    method="POST" autocomplete="off" >
                
                <s:hidden id="h_vauation_right" name="h_vauation_right" value="%{#loginuser.getVauation_right()}"/>                
                <s:hidden id="clientLoginLevel" name="clientLoginLevel"/>
                <s:hidden id="selectedValue" name="selectedValue" value="L"/>
                <s:hidden id="reprocessFlag" name="reprocessFlag" value="T"/>
                <s:hidden name="validate" value="true" />
                <s:hidden id="tokenNo"/>

                <div class="tab-content px-4 py-4">
                    <div class="container-fluid pt-2">
                        <div id="ProcessErrorDetails" class="tab-pane show in active">

                            <div class="row">                                
                                <h4 class=" mb-4 page-title">Process Data Details </h4>
                            </div>
                            <div class="panel-body" id="fileDashboardPanel">
                                <!--Ajax Data Here-->
                            </div>

                            <hr class="my-4">

                            <div class="row">                                
                                <h4 class=" mb-4 page-title">Process Error Details </h4>
                            </div>  
                            <div class="row">
                                <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Unverified PAN</h4>
                                            <div class="count font-weight-bold" id="unverifiedPAN"></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold" onclick="">
                                            <i class="fa fa-check text-white" title="Click Here to Verify"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <hr class="my-4">

                            <div class="row">                                
                                <h4 class=" mb-4 page-title">Process Error Parameters </h4>
                            </div>  
                            <div class="row form-group">
                                <div class="col-lg-5 col-xl-5  text-right">
                                    <label class="control-label">
                                        Process/XML Level
                                    </label>
                                </div>
                                <div class="col-lg-6 col-xl-4">
                                    <s:select list="loginLevelList" cssClass="form-control" name="loginLevel" id="loginLevel"/>    
                                </div>
                            </div>
                            <div class="row form-group" style="display:none">
                                <div class="col-lg-5 col-xl-5  text-right">
                                    <label class="control-label">
                                        Reprocess
                                    </label>
                                </div>
                                <div class="col-lg-6 col-xl-4">
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" class="custom-control-input" name="processCnfRadio" id="processCnfRadioDefault" checked>
                                        <label class="custom-control-label" for="processCnfRadioDefault">Yes</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" class="custom-control-input" name="processCnfRadio" id="processCnfChkBxradio" >
                                        <label class="custom-control-label" for="processCnfChkBxradio">No</label>
                                    </div>
                                    <s:hidden id="processCnfChkBx" name="processCnfChkBx" value="true" />
                                </div>
                            </div>
                            <div class="row"> 
                                <div class="col-md-12 text-center"> 
                                    <button type="button" class="form-btn" onclick="callUrl('/errorStatus15GH');" id="backBtn">
                                        <i class="fa back" aria-hidden="true"></i>Back
                                    </button>
                                    <button type="button" class="form-btn" onclick="checkFileParameter15GHPart2New();" id="upload">
                                        <i class="fa fa-cogs" aria-hidden="true"></i>Process Errors
                                    </button>
                                </div>
                            </div>
                        </div>                        
                    </div>            
                </div>
            </s:form>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("form").bind("keypress", function (e) {
                if (e.keyCode === 13) {
                    return false;
                }
            });
            try {
                fetchFileDashboardDetails15GH();
                getErrorListDetails15GH();
            } catch (err) {
            }
        });
    </script>
</html>