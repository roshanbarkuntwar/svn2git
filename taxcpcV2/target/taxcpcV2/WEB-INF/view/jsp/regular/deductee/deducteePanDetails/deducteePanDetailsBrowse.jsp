<%-- 
    Document   : deducteePanDetailsBrowse
    Created on : Oct 1, 2021, 3:22:31 PM
    Author     : kapil.gupta
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/deductee/deductee.js" type="text/javascript"></script>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5"><a class="nav-link "  href="tdsDeductees"><p>Deductee Browse </p> </a></li>
            <li  class="nav-item mr-5"><a class="nav-link "  href="panVerificationOffline"><p> PAN Verification </p> </a></li>
            <li  class="nav-item mr-5"><a class="nav-link active"  href="deducteePanDetails"><p> PAN Verification Status </p> </a></li>

        </ul>

        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <br>
        <div class="button-section  my-1 pt-1"> </div>
        <div id="browse" class="tab-pane show in active">
            <form id="deducteePanDetailsForm" name="deducteePanDetailsForm" action="/taxcpcV2/deducteePanDetails" method="post"> 
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Enter PAN Number <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-5 col-xl-3">
<!--                        <div class="input-group">-->
                            <input type="text" name="panno" id="panno" style="text-transform: uppercase;"  onblur="validatePAN(this);" class="form-control" title="PAN Number" maxlength="10" placeholder="Enter PAN Number">
<!--                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary addon-btn" id="panDetailsBtn" onclick="getPanDetails();" title="Search PAN Details">
                                    <i class="fa fa-search" id="fromDateCal"></i>
                                </button>
                            </div>-->
<!--                        </div>-->
                        
                        
                        
<!--                        <div class="custom-file">
                            <input type="text" name="panno" id="panno" style="text-transform: uppercase;"  onblur="validatePAN(this);" class="form-control" title="PAN Number" maxlength="10" placeholder="Enter PAN Number">
                        </div>-->
                    </div>
                    <div class="col-lg-3 col-xl-3 pl-0 d-flex justify-content-start">
                            <button type="button" class="form-btn mr-2 mt-0" title="Show PAN Details" onclick="getPanDetails();"><i class="fa fa-expand"></i>Show</button>
                            <button type="button" class="form-btn mt-0" title="Reset" onclick="lhsResetform('deducteePanDetailsForm', 'submit');"><i class="fa clear"></i>Reset</button>
                        </div>
<!--                    <div class="col-lg-1 col-xl-4">
                        <button type="button" id="panDetailsBtn" class="form-btn mt-0" onclick="getPanDetails();" ><i class="fa search" aria-hidden="true"></i>Search PAN Details</button>
                    </div>-->
                </div>
<!--                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <button type="button" id="panDetailsBtn" class="form-btn mt-3" onclick="getPanDetails();" ><i class="fa search" aria-hidden="true"></i>Search PAN Details</button>
                        </div>
                    </div>
                </div>-->


                <div class="filter-section" id="panStatus" style="display: none">
                    <div class="sec-bottom">
                        <div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        PAN Status
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control text-success" cssStyle="font-weight:bold"  id="checkPanStatus" placeholder="checkPanStatus" title="checkPanStatus " name="checkPanStatus" readonly="true"/>
                                </div>
                            </div>
<!--                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        PAN Number 
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield cssClass="form-control"  id="panNo" placeholder="PAN No." title="PAN No." name="panNo" readonly="true"/>
                                </div>
                            </div>-->
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        First Name 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control"  id="first_name" cssStyle="font-weight:bold" placeholder="first_name" title="first_name" name="first_name" readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Middle Name
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control"  id="middle_name" placeholder="middle_name" title="middle_name" name="middle_name" readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Last Name 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control" id="last_name" placeholder="last_name" title="last_name" name="last_name" readonly="true"/>
                                </div> 
                            </div>


                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        PAN Allot Date
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control" id="pan_allot_date" placeholder="pan_allot_date" title="pan_allot_date" name="pan_allot_date" readonly="true"/>
                                </div> 
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        PAN Aadhar Status 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control"  id="pan_aadhar_status" placeholder="pan_aadhar_status" title="pan_aadhar_status" name="pan_aadhar_status" readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        AB206 Status 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control" id="ab206_status" placeholder="ab206_status" title="ab206_status" name="ab206_status" readonly="true"/>
                                </div> 
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        NL94 Status 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control"  id="n194_status" placeholder="n194_status" title="n194_status" name="n194_status"  readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Remark 
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control"  id="remark" placeholder="Remark" title="Remark" name="remark" readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group" id="verifiedPan" style="display: none">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Verified Date
                                    </label>
                                </div>
                                <div class="col-lg-5 col-xl-3">
                                    <s:textfield cssClass="form-control" id="verified_date" placeholder="verified_date" title="verified_date" name="verified_date" readonly="true"/>
                                </div>  

                            </div>
                        </div>
                    </div>  
                </div> 

            </form>
        </div>


    </div>
</div>

<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>

<script type="text/javascript">
</script>
