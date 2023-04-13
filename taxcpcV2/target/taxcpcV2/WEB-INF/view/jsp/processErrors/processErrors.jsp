<%-- 
    Document   : processErrors
    Created on : Mar 12, 2019, 4:45:45 PM
    Author     : neha.bisen
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
        <script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
        <title></title>
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
    <%--
        Util utl = new Util();
        String filedStatus = "";
        Long fvuFileCount = 0l;
        ViewClientMast client = (ViewClientMast) session.getAttribute("WORKINGUSER");
        Assessment asmt = (Assessment) session.getAttribute("ASSESSMENT");
        if (asmt != null) {
            String quarterNumber = asmt.getQuarterNo();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tokenNoQuery = "select lhs_tds.get_tds_reco_details('" + client.getEntity_code() + "', '" + client.getClient_code() + "', '"
                    + asmt.getAccYear() + "', '" + Integer.parseInt(quarterNumber) + "',  to_date('" + sdf.format(asmt.getQuarterFromDate())
                    + "','dd-mm-rrrr'),  to_date('" + sdf.format(asmt.getQuarterToDate()) + "','dd-mm-rrrr'), '" + asmt.getTdsTypeCode()
                    + "', '', 'LHSSYS_TDS_RETURN_TRAN', 'TOKEN_NO') from dual";

            System.out.println("Token no query--->\n" + tokenNoQuery);

            String prnNo = new DbFunctionExecutorAsString().execute_oracle_function_as_string(tokenNoQuery);

            if (!utl.isnull(prnNo)) {
                filedStatus = "Filed";
                // System.out.println("filedStatus---"+filedStatus); 
            }
    --%>
    <%--<s:set var="filedStatus"><%=filedStatus%></s:set>--%>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & FVU Generation Status</p></a></li>
                    <li class="nav-item mr-5"><a class="nav-link active"  href="#"><p>Validation & Error Process Details</p> </a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Validation & Error Process Result</p></a></li>
                    <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" href="#"><p>Error Details</p></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>           

            <!--<form id="tdsProcessErrorForm1" name="tdsProcessErrorForm1" action="tdsProcessError?" method="POST" autocomplete="off" >-->
            <form id="tdsProcessErrorForm" name="tdsProcessErrorForm" action="errorStatus" method="POST" autocomplete="off" >

                <s:hidden id="h_vauation_right" name="h_vauation_right" value="%{#loginuser.getVauation_right()}"/>
                <s:hidden name="BckAction" value="tdsProcessError" />
                <s:hidden name="validate" value="true" />  
                <s:hidden id="reprocessFlag" name="reprocessFlag" value="T"/>
                <s:hidden id="loginLevel" name="loginLevel" value="%{#loginuser.getCode_level()}"/>
                <s:hidden id="loginLevelFlag" name="loginLevelFlag"/>
                <s:hidden name="errorTypeProc" id="errorTypeProc" value="ALL"/>
                <s:hidden name="tokenNo" id="tokenNo"/>

                <div class="tab-content px-4 py-4">
                    <div class="container-fluid pt-2">
                        <div id="ProcessErrorDetails" class="tab-pane show in active">
                            <div class="row">
                                <div class="col-lg-12 text-right">
                                    <label class="font-weight-bold mr-2">Re-Process Error Data : </label>
                                    <div class="custom-control custom-radio custom-control-inline show-processErrorParameters" >
                                        <input type="radio" id="processCnfChkBxError" name="processCnfChkBxError" class="custom-control-input" checked>
                                        <label class="custom-control-label" for="processCnfChkBxError">Yes</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline hide-processErrorParameters">
                                        <input type="radio" id="customRadioInline2" name="processCnfChkBxError" class="custom-control-input" >
                                        <label class="custom-control-label" for="customRadioInline2">No</label>
                                    </div>
                                   
                                </div>
                            </div>
                             <hr>
                             <div class="mb-4" style="background: #f6f9ff;width: 100%;padding: 18px;border: 1px solid #cbdeff;">
                                 <div class="row">
                                
                                <div class="col-lg-6">
                                    <h4 class=" mb-4 page-title">Process Data Details </h4>
                                </div>
                                <div class="col-lg-6">
                                    <div class="text-right">
                                        <a href="#"><button type="button" data-toggle="modal" data-target="#countModal" class="form-btn countModal-btn1"><i class="fa fa-angle-double-down" aria-hidden="true"></i>Get Count</button></a>
                                    </div>
                                </div>
                                
                                
                        <div class="modal" tabindex="-1" id="countModal" role="dialog" data-keyboard="false" data-backdrop="static" aria-modal="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="">Get Count</h5>                        
                                    </div>
                                    <div class="modal-body">  

                                        <div class="row">                            
                                            <div class="col-md-12 text-center">
                                                <p>Are you sure you want to get count?</p>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="modal-footer justify-content-center">
                                        <button type="button" class="form-btn" title="Yes"  onclick="processt1();"><i class="fa fa-check" aria-hidden="true"></i>Yes</button>
                                        <button type="button" class="form-btn" title="No" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            </div>
                                 <div class="row"   id="processDataPanelhide">
                                <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6" id="">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Challan Transaction(s)</h4>
                                            <div class="count font-weight-bold" id=""></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold" >
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl  px-xl-2 mb-lg-3  mb-xl-0 col-lg-6" id="">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Deductee Transaction(s)</h4>
                                            <div class="count font-weight-bold" id=""></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold" >
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                                 <div  id="processDataDashboardPanel">
                                <!--Ajax Data Here-->
                            </div>
                             </div>
                            

                            

                          
                            <div class="mb-4" style="background: #fff6f6;width: 100%;padding: 18px;border: 1px solid #ffdbdb;">
                                <div class="row">
                                <div class="col-md-6"> <h4 class="page-title mb-4">Process Error Details </h4></div>
                                <div class="col-md-6">
                                    <div class="text-right">
                                        <a href="#"><button type="button" data-toggle="modal" data-target="#countModal1" class="form-btn countModal-btn2"><i class="fa fa-angle-double-down" aria-hidden="true"></i>Get Count</button></a>
                                    </div>
                                </div>
                            </div>
                           

                        <div class="modal" tabindex="-1" id="countModal1" role="dialog" data-keyboard="false" data-backdrop="static" aria-modal="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="">Get Count</h5>                        
                                    </div>
                                    <div class="modal-body">  

                                        <div class="row">                            
                                            <div class="col-md-12 text-center">
                                                <p>Are you sure you want to get count?</p>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="modal-footer justify-content-center">
                                        <button type="button" class="form-btn" title="Yes" onclick="processt2();"><i class="fa fa-check" aria-hidden="true"></i>Yes</button>
                                        <button type="button" class="form-btn" title="No" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <div class="row">
                                <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6" id="unverifiedPanBody">
                                    <div class="error-list pl-2 py-3 mb-0" >
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Unverified PAN</h4>
                                            <div class="count font-weight-bold badge badge-primary text-white" id="unverifiedPAN"></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold" onclick="getPanVerificationLink();">
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl  px-xl-2 mb-lg-3  mb-xl-0 col-lg-6" id="unverifiedChallanBody">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Challan Not Verified</h4>
                                            <div class="count font-weight-bold badge badge-primary text-white" id="challannotverified"></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold" onclick="onClickVerifyChallan();">
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl px-xl-2 mb-lg-3 col-lg-6 mb-xl-0" id="deducteeBody">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Deductee Not Allocated</h4>
                                            <div class="count font-weight-bold badge badge-primary text-white" id="deducteenotallocated"></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold">
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl px-xl-2 mb-lg-3 col-lg-6 mb-xl-0" id="lowerDeducteeBody">
                                    <div class="error-list pl-2 py-3 mb-0">
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0">Lower Deduction Not Verified</h4>
                                            <div class="count font-weight-bold badge badge-primary text-white" id="lowerdeductionnotverified"></div> 
                                        </div>
                                        <div class="icon-sec font-weight-bold">
                                            <!--<i class="fa fa-sitemap" title="Click Here to Allocate"></i>-->
                                        </div>
                                    </div>
                                </div>
                            </div> 
                            </div>
                            
                           <div class="mb-4" style="background: #fffaee;width: 100%;padding: 18px;border: 1px solid #fae9b1;">
                            <div class="" style="display:block;" id="processErrorParameters">
                                <h4 class="page-title mb-4">Process Error Parameters</h4>
                                <div class="table-responsive">
                                    <div class="table table-sm">
                                        <table class="table">
                                            <tbody>                                                
<!--                                                <tr>
                                                    <td> Exclude Review Error</td>
                                                    <td>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="excludeType" id="excludeType">
                                                            <label class="custom-control-label" for="excludeType">Yes</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="excludeType" id="excludeTypeDefault" checked>
                                                            <label class="custom-control-label" for="excludeTypeDefault">No</label>
                                                        </div>
                                                        <%--<s:hidden name="l_error_process_type" id="l_error_process_type" value="1"/>--%>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Reprocess After Confirmation</td>
                                                    <td>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="processCnfRadio" id="processCnfRadioDefault" checked>
                                                            <label class="custom-control-label" for="processCnfRadioDefault">Yes</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="processCnfRadio" id="processCnfChkBxradio">
                                                            <label class="custom-control-label" for="processCnfChkBxradio">No</label>
                                                        </div>
                                                        <%--<s:hidden id="processCnfChkBx" name="processCnfChkBx" value="true" />--%>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Process Error Type</td>
                                                    <td>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="errorType" id="errorType" checked>
                                                            <label class="custom-control-label" for="errorType">Structural
                                                            </label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="errorType" id="errorTypeDefault" checked>
                                                            <label class="custom-control-label" for="errorTypeDefault">All(Structural And All validation)</label>
                                                        </div>
                                                    </td>
                                                </tr>-->
                                                <tr>
                                                    <td style="padding: 2px 4px;">Select Client Level For Error Processing</td>
                                                    <td style="padding: 7px 5px;">
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientType" checked>
                                                            <label class="custom-control-label" for="clientType">Login Level
                                                            </label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" name="clientType" id="clientTypeDefault">
                                                            <label class="custom-control-label" for="clientTypeDefault">All (Login & Branch Level)</label>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                             </div>
                            <s:hidden name="l_error_process_type" id="l_error_process_type" value="1"/>
                            <s:hidden id="processCnfChkBx" name="processCnfChkBx" value="true" />

                            <div class="row"> 
                                <div class="col-md-12 text-center"> 
                                    <button type="button" class="form-btn" onclick="callUrl('/errorStatus');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
                                    <button type="button" class="form-btn" onclick="processErrorParameters(this.id);"><i class="fa fa-cogs" aria-hidden="true"></i>Process Errors</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!--Modal for varification-->
        <div class="modal fade" id="additionalTDSInfo">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Update Pan</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="clseBtnId">
                           <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">


                        <div class="text-center col-md-12" id="successMsgDivSplRate" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--success p-1 my-1">
                                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="successMsgSplRate">Data Save Successfully</h5>
                                </div>
                            </div>
                        </div>
                        Form Validation success

                        Form Validation Error
                        <div class="text-center col-md-12" id="errorMsgDivSplRate" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--error p-1 my-1">
                                    <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="errorMsgSplRate">Some Error Occured</h5>
                                </div>
                            </div>
                        </div>
                        Form Validation Error

                        Form Validation Info
                        <div class="text-center col-md-12" id="notificationMsgDivSplRate" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--info p-1 my-1">
                                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="notificationMsgSplRate">Please Fill Mandatory Fields</h5>
                                </div>
                            </div>
                        </div>

                        <s:form id="" name="" action=""  autocomplete="off">
                            <div class="clearfix"></div>

                            <div class="tab-content px-4 py-4">

                                <div class="container-fluid pt-2">

                                    <s:if test="%{sessionResult!=null && sessionResult!='' && sessionResult !='null'}">
                                        <div class="text-center col-md-12" id="session_notification">
                                            <div class="d-inline-block">
                                                <s:div class="%{#session.ERRORCLASS}" >
                                                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                                    <h5 class="d-inline-block" ><s:property value="sessionResult"/></h5>
                                                </s:div>
                                            </div>
                                        </div>
                                    </s:if>
                                    <div class="table-responsive"><b>
                                            <table class="table">
                                                <tr>
                                                    <td>Total  Records</td>
                                                    <td id="totalRecordsData" style="text-align: right"></td>
                                                </tr>
                                            </table></b>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-lg-4 col-xl-4  text-right">
                                            <label class="control-label">
                                                Deductee Name: 
                                            </label>
                                        </div>
                                        <div class="col-lg-7 col-xl-4">
                                            <s:textfield  class="form-control" id="fltrPanNo" placeholder="Deductee PAN No." name="deducteePan"  readonly=""/>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-lg-4 col-xl-4  text-right">

                                            <label class="control-label">
                                                Current PANNo:
                                            </label>
                                        </div>
                                        <div class="col-lg-7 col-xl-4">
                                            <s:textfield  class="form-control" id="fltrDeducteeName" placeholder="Deductee Name" name="tdsSplRateMast.deductee_name" readonly=""/>
                                        </div> 
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-lg-4 col-xl-4  text-right">
                                            <label class="control-label">
                                                Enter New PANNo:
                                            </label>
                                        </div>
                                        <div class="col-lg-7 col-xl-4">
                                            <s:textfield  class="form-control" id="certificate_no1" placeholder="Certificate No." name="tdsSplRateMast.certificate_no" maxlength="10"/>
                                        </div>
                                    </div>                 
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="button-section col-md-12 my-1 text-center">                                         
                                                <button type="button" class="form-btn" id="save" onclick=""><i class="fa update" aria-hidden="true" ></i>Update Pan No</button>
                                            </div>
                                        </div>
                                    </div>                                 
                                </s:form>
                            </div>
                        </div>
                    </div>
                </div>            
            </div> 
        </div>
        <!--Modal for varification-->

    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("form").bind("keypress", function (e) {
                if (e.keyCode === 13) {
                    return false;
                }
            });
            try {
                //getFileDashboardDetails();
               // getErrorDetails();
            } catch (err) {
            }
        });
        $('#processErrorParameters').collapse({
            toggle: false
        });
    </script>
</html>
