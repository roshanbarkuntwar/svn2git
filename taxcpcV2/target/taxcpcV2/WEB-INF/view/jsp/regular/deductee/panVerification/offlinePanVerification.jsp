<%-- 
    Document   : offlinePanVerification
    Created on : Feb 18, 2019, 1:05:05 PM
    Author     : sneha.parpelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>
<!DOCTYPE html>

<div class="tab-section col-md-12 my-1">
    <ul class="nav nav-pills">

        <li  class="nav-item mr-5"><a class="nav-link "  href="tdsDeductees"><p> Deductee Browse </p> </a></li>
        <li  class="nav-item mr-5"><a class="nav-link active"  href="panVerificationOffline"><p> PAN Verification </p> </a></li>
        <li  class="nav-item mr-5"><a class="nav-link "  href="deducteePanDetails"><p> PAN Verification Status </p> </a></li>
        <!--<li class="nav-item"><a class="nav-link" data-toggle="pill" href="#entry"><i class="fa fa-file-o mr-2" aria-hidden="true"></i> <p> Entry </p></a></li>-->

    </ul>

    <div class="clearfix"></div>
</div>
<!--    <div class="col-md-12 my-1"> <h4 class="page-title mb-2">PAN Statistics</h4></div>-->
<div class="tab-content form-wrapper px-4 py-4">
    <div class="container-fluid pt-2">
        <s:if test="%{sessionResult!=null && sessionResult!='' && sessionResult !='null'}">
            <div class="text-center col-md-12" id="session_notification">
                <div class="d-inline-block">
                    <s:div class="%{#session.ERRORCLASS}" >
                        <i class="fa fa-info mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" ><s:property value="sessionResult"/></h5>
                    </s:div>
                </div>
            </div>
        </s:if>
        <div class="row form-group my-2">
            <div class="col-lg-12 text-center">
                <div class="count-container">
                    <label class="mr-1">Unverified PAN Count : </label> <label id="unverifiedPanCount" class="text-danger count font-weight-bold mr-3"><s:property value="%{unverifiedPanCount}"/></label>
                    <button type="button" id="refreshUnverifiedCount" onclick="refreshUnverifiedCount(<s:property value="%{unverifiedPanCount}"/>);" class="form-btn mt-3"  title="Un-verified PAN count refresh"><i class="fa refresh" aria-hidden="true"></i>Un-verified PAN count refresh</button>
                    <!--                    <p><button type="button" id="" class="form-btn" data-toggle="modal" data-target="#generatePan" title="Click Here To Generate Unverified Text/XML File"><i class="fa generate" aria-hidden="true"></i>Generate Unverified Text/XML File</button></p>-->
                    <!--                    <button style="height:auto !important" class="btn btn-primary addon-btn filter-inner-btn py-1 px-2" data-toggle="modal" data-target="#generatePan" title="Click Here To Generate Unverified Text/XML File"><i class="fa generate" aria-hidden="true"></i></button> -->
                </div>
                <button type="button" id="" class="form-btn mt-3" data-toggle="modal" data-target="#generatePan" title="Click Here To Generate Unverified Text/XML File"><i class="fa generate" aria-hidden="true"></i>Generate Unverified Text/XML File</button>
            </div>
        </div>
        <div class="row">
            <div class="button-section col-md-12 my-1 text-center"> 
                <!--<button type="button" class="form-btn" onclick="doGenerateAndDownloadXml(this);" title="Generate and Download XML"><i class="fa fa-download" aria-hidden="true"></i>Generate and Download XML</button>-->
                <!--<button type="button" class="form-btn" onclick="doGenerateAndDownloadTxt(this);" title="Generate and Download Text File"><i class="fa fa-download" aria-hidden="true"></i>Generate and Download Text File</button>-->
                <!--<button type="button" class="form-btn" onclick="" data-toggle="collapse" data-target="#uploadCSV" title="Upload CSV"><i class="fa fa-upload" aria-hidden="true"></i>Upload CSV</button>-->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="generatePan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" aria-modal="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="header_name">Generate</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            </div>

            <div class="modal-body">
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-6 text-right">
                        <label class="control-label">Generate : <span class="text-danger font-weight-bold ml-1">*</span></label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="form-check-inline">
                            <label class="form-check-label mr-5">
                                <input type="radio" class="form-check-input" id="downloadText" name="optradio" checked>Text
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label mr-5">
                                <input type="radio" class="form-check-input" id="downloadXML" name="optradio">XML
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center">
                            <button type="button" id="GenerateAndDownload" onclick="generateAndDownload();" class="form-btn" ><i class="fa upload" aria-hidden="true"></i>Generate</button>
                            <button type="button" class="form-btn" data-dismiss="modal" aria-label="Close"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>    
<script src="resources/js/deductee/deductee.js?r=<%=Math.random()%>" type="text/javascript"></script>
