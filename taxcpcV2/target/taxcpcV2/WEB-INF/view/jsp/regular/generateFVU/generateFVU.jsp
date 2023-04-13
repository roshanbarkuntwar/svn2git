<%-- 
    Document   : generateFVU
    Created on : Feb 18, 2019, 4:47:21 PM
    Author     : sneha.parpelli
--%>

<%@page import="globalUtilities.Util" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link href="resources/css/generateFVU/generateFVU.css" rel="stylesheet">
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#generateFVU"><p>Generate FVU Text File</p> </a></li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="tab-content p-2 form-wrapper">      
                <div class="container-fluid">
                    <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
                    <s:if test="hasActionErrors()">                      
                        <div class="text-center col-md-12" id="session_notification" >
                            <div class="d-inline-block">
                                <s:div cssClass="%{#session.ERRORCLASS}">
                                    <!--<i class="fa fa-check mr-2" aria-hidden="true"></i>-->
                                    <h5 class="d-inline-block" > <s:actionerror/> </h5>
                                </s:div>
                            </div>
                        </div
                    </s:if>
                        <div class="text-right mt-4">
                            <a href="#"><button type="button" data-toggle="modal" data-target="#countModal3" class="form-btn countModal-btn3"><i class="fa fa-angle-double-down" aria-hidden="true"></i>Get Count</button></a>
                        </div>
                        <div class="modal" tabindex="-1" id="countModal3" role="dialog" data-keyboard="false" data-backdrop="static" aria-modal="true">
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
                                        <button type="button" class="form-btn" title="Yes" onclick="processt3();"><i class="fa fa-check" aria-hidden="true"></i>Yes</button>
                                        <button type="button" class="form-btn" title="No" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="fileDashboardPanelhide" class="mt-4">
                            <div class="row"> 
    <div class="col-lg-6 pr-0">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th class="srno">Sr. NO.</th>
                        <th>Challan Details</th>
                        <th class="records">Records</th>

                    </tr>
                </thead>
                <tbody>
                            <tr>
                                <td id="">01</td> 
                                <td id="">CHALLAN TRANSACTION(S)</td> 
                                <td class="text-center">
                                     <span class="badge-pill font-weight-bold badge-primary ">
                                        
                                    </span>
                                </td>                         
                            </tr>
                            <tr>
                                <td id="">02</td> 
                                <td id="">CHALLAN TRANSACTION ERROR(S)</td> 
                                <td class="text-center">
                                    <span class="badge-pill font-weight-bold badge-danger">
                                        
                                    </span>
                                </td>                         
                                
                            </tr>
                        
                            <tr>
                                <td id="">03</td> 
                                <td id="">PROCESSED CHALLAN TRANSACTION(S)</td> 
                                <td class="text-center">
                                <span class="badge-pill font-weight-bold badge-success">
                                        
                                    </span>
                                </td>                         
                            </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-lg-6 pl-0">  
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>

                        <th class="srno">Sr. NO.</th>
                        <th>Deductee Details</th>
                        <th class="records">Records</th>
                    </tr>
                </thead>

                <tbody>
                            <tr>
                                <td id="">04</td> 
                                <td id="">DEDUCTEE TRANSACTION(S)</td> 
                                <td class="text-center">
                                   <span class="badge-pill font-weight-bold badge-primary " id="">
                                       
                                    </span>
                                </td>                         
                               
                            </tr>
                            <tr>
                                <td id="">05</td> 
                                <td id="">DEDUCTEE TRANSACTION ERROR(S)</td> 
                                <td class="text-center">
                                    <span class="badge-pill font-weight-bold badge-danger" id="">
                                        
                                    </span>
                                </td>                         
                            </tr>
                            <tr>
                                <td id="">06</td> 
                                <td id="">PROCESSED DEDUCTEE TRANSACTION(S)</td> 
                                <td class="text-center">
                                    <span class="badge-pill font-weight-bold badge-success" id="">
                                        
                                    </span>
                                </td>                         
                            </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
                            
                            
                            
                        </div>   
                    <div  id="fileDashboardPanel" class="mt-4">
                        
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Whether TDS Return for form type filed in any Previous Quarter: (Yes or No)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text line-height-2">
                                        <s:if test="%{previousQtrPrno!=null && previousQtrPrno!='' && !previousQtrPrno.equalsIgnoreCase('null')}">

                                            <input type="checkbox" id="prnCheck" checked disabled>
                                        </s:if>
                                        <s:else>
                                            <input type="hidden" id="hiddenPrnCheck">
                                            <input type="checkbox" id="prnCheck" onclick="onClickPrnCheckBox();">
                                        </s:else>
                                    </div>
                                </div>
                                <s:if test="%{previousQtrPrno!=null && previousQtrPrno!='' && !previousQtrPrno.equalsIgnoreCase('null')}">

                                    <s:textfield  type="text" cssClass="form-control" placeholder="PRN NO." id="prn_no" name="previousQtrPrno" maxLength="15" disabled="true" />
                                </s:if>
                                <s:else>
                                    <s:textfield  type="text" cssClass="form-control" placeholder="PRN NO." id="prn_no" name="previousQtrPrno" maxLength="15" />

                                </s:else>
                            </div>
                        </div> 
                    </div>   
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Whether Deductor Details are Changed from Last Quarter
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <div class="input-group-prepend">
                            <div class="input-group-text line-height-2" style="border-radius: 0.25rem;">
                                <input type="checkbox" id="deductorCheck" onclick="deductorInfoChange();">
                            </div>
                                    </div>
                                </div>
                                
<!--                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="deductorCheck" onclick="deductorInfoChange();">
                                <label class="custom-control-label" for="deductorCheck"></label>
                            </div>-->
                        </div> 
                    </div>  
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Name of Responsible person <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" placeholder="Enter Name of Responsible person" id="deductor_name" name="clientMast.deductor_name"  disabled="true" />
                        </div> 
                    </div>   
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Pan of Responsible Person <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" placeholder="Enter Pan of Responsible Person" id="deductor_pan" name="clientMast.deductor_panno"   disabled="true" />
                        </div> 
                    </div>   
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Designation of Responsible Person <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" placeholder="Enter Designation of Responsible Person" id="deductor_desig" name="clientMast.deductor_desig"   disabled="true" />
                        </div> 
                    </div>   
                    <div class="row form-group">
                        <div class="col-lg-6 col-xl-6  text-right">
                            <label class="control-label">
                                Mobile of Responsible Person <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" placeholder="Enter Mobile of Responsible Person" id="deductor_mobile" name="clientMast.deductor_mobileno"   disabled = "true" />
                        </div> 
                    </div>   

                    <%--<s:form id="generate_file1" name="generate_file1" action="runBatchFileAction" method="post" theme="css_xhtml"   enctype="multipart/form-data" autocomplete="off" >--%>
                    <s:form id="generate_file" name="generate_file" action="fvuGenerateAjaxAction?action=callFVUTextProcedure" method="POST" autocomplete="off" >
                        <s:if test="%{allowClick!=null && allowClick.equalsIgnoreCase('true') && ((allowCsiUpload==null || allowCsiUpload.equalsIgnoreCase('F')))}">
                            <s:set var="disabledBtn" value="''"/>
                        </s:if>
                        <s:else>
                            <s:set var="disabledBtn" value="'disabled'"/>
                        </s:else>
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <!--<button type="button" id="upload" class="form-btn" onclick="getGeneratedFVUFileLoc();" ><i class="fa fa-cog" aria-hidden="true"></i>Generate & Process </button>-->       
                            <button type="button" id="backBtn" class="form-btn" onclick="callUrl('/errorStatus');" ><i class="fa back" aria-hidden="true"></i>Back</button>       
                            <button type="button" id="upload" class="form-btn" onclick="getGeneratedFVUTextFileLoc();" ><i class="fa generate" aria-hidden="true"></i>Generate Text File</button>       
                        </div>

                        <s:hidden name="jsessionid" /><!--hidden field to validate page redirected from process error bugs-->
                        <s:hidden name="validateFlag" id="validateFlag" />
                        <s:hidden name="tokenNo" id="tokenNo" />
                    </s:form>


<!--                    <div class="row form-group my-2" >
                        <div class="col-lg-12">
                            <div class="count-container">
                            <div id="downloadFileTable">

                            </div>
                            <div class="button-section col-md-12 my-1 text-center"  id="dwnldBtnRow"> 

                            </div>
                            </div>
                        </div>
                    </div>-->

                </div>
            </div>
            <s:hidden name="total" id="totalChallanRecords"/>
            <s:hidden id="hidden_deductor_name"/>
            <s:hidden id="hidden_deductor_pan"/>
            <s:hidden id="hidden_deductor_desig"/>
            <s:hidden id="hidden_deductor_mobile"/>
            <s:hidden value="%{allowClick}" id="allowClick"/>
    </body>
    <script src="resources/js/regular/generateFVU/generateFVU.js?r="></script>
    <script src="resources/js/regular/generateFVU/generateFVUText.js?r=<%=Math.random()%>"></script>
    <script>
                       // fetchFileDashboardDetails();
                        //    getFileDownload();
                        
                        function processt3(){
                         fetchFileDashboardDetails();   
                        }
    </script>
</html>
