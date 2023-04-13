<%-- 
    Document   : lowerDeductionCertificate
    Created on : Feb 2, 2019, 1:02:24 PM
    Author     : sneha.parpelli
--%>


<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<script src="resources/js/global/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>"></script>

<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="#browse"><p>Lower Deduction Certificate Browse </p> </a></li>
            <li onclick="callEntryPage();" class="nav-item"><a class="nav-link active" data-toggle="pill" href="#entry"><p> Lower Deduction Certificate Entry </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane fade">

        </div>

        <div id="entry" class="tab-pane show in active">
            <div class="container-fluid pt-2">

                <s:if test="%{resultMessage!=null && resultMessage!='' && resultMessage !='null'}">
                    <div class="text-center col-md-12" id="session_notification" style="position: relative; top: 7px;text-align: center;">
                        <s:div class="%{#session.ERRORCLASS}" >
                            <i class="fa fa-check mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" ><s:property value="resultMessage"/></h5>
                        </s:div>
                    </div>
                </s:if>

                <!---------------message div ------------------------->
                <!--Form Validation success-->
                <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                    <div class="d-inline-block">
                        <div class="form-validation form-validation--success p-1 my-1">
                            <i class="fa fa-check mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" id="successMsg"></h5>
                        </div>
                    </div>
                </div>
                <!--Form Validation success-->

                <!--Form Validation Error-->
                <div class="text-center col-md-12" id="errorMsgDiv" style="display:none">
                    <div class="d-inline-block">
                        <div class="form-validation form-validation--error p-1 my-1">
                            <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" id="errorMsg">Some Error Occured</h5>
                        </div>
                    </div>
                </div>
                <!--Form Validation Error-->

                <!--Form Validation Info-->
                <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                    <div class="d-inline-block">
                        <div class="form-validation form-validation--info p-1 my-1">
                            <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" id="notificationMsg">Please Fill Mandatory Fields</h5>
                        </div>
                    </div>
                </div>


                <!---------------end message div ------------------------------------->
                <s:form id="tdsSplRateEntryPage" name="tdsSplRateEntryPage" action="tdsDeductionManupulation?action=%{pageAction}" method="post">   
                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Deductee PAN <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:if test="%{action.equalsIgnoreCase('add') || action.equalsIgnoreCase('save')}">  
                                    <s:textfield type="text" cssClass="form-control" id="panno" placeholder="Enter Deductee PAN" maxLength="10" name="tdsSplRateMast.deductee_panno" cssStyle="text-transform: uppercase;" onblur="validatePAN(this);"/>
                                </s:if>
                                <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                    <s:textfield type="text" cssClass="form-control" id="panno" placeholder="Enter Deductee PAN" maxLength="10" name="tdsSplRateMast.deductee_panno" cssStyle="text-transform: uppercase;" onblur="validatePAN(this);" disabled="true"/>
                                </s:elseif>
<!--                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn">Update From Traces</button>
                                </div>-->
                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Certificate No.<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <%--<s:textfield type="text" cssClass="form-control" id="certificate_no" placeholder="Enter Certificate No." name="tdsSplRateMast.certificate_no" onblur="validateCertificateNo(this);"/>--%>
                            <s:textfield type="text" cssClass="form-control" onblur="validateCertificateNumber(this);" id="certificate_no" placeholder="Enter Certificate No." name="tdsSplRateMast.certificate_no"
                                         maxlength="10"/>
                        </div>
                        <input type="hidden" name="hiddenCertificateNo" value="<s:property value="tdsSplRateMast.certificate_no"/>">
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Section  <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add') || action.equalsIgnoreCase('save')}">  
                                <s:select list="tdsSectionList" cssClass="form-control" title="Section" id="tds_code" name="tdsSplRateMast.tds_code" />
                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                <s:select list="tdsSectionList" cssClass="form-control" title="Section" id="tds_code" name="tdsSplRateMast.tds_code"  disabled="true"/>
                                <s:hidden name="tdsSplRateMast.tds_code"/>
                            </s:elseif>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Certificate From Date <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:if test="%{action.equalsIgnoreCase('add') || action.equalsIgnoreCase('save')}">

                                    <s:textfield type="text" cssClass="form-control" id="from_date" onblur="checkDatesOfFinancialYear(this);"  placeholder="Enter Certificate From Date" name="tdsSplRateMast.from_date"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="OnLoadSetFinancialYear('from_date', 'fromCal')">
                                            <i class="fa fa-calendar" id="fromCal"></i></button>
                                    </div>
                                </s:if>

                                <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                    <s:set name="formateInstDate"><s:date name="tdsSplRateMast.from_date" format="dd-MM-yyyy"/></s:set>
                                    <s:textfield cssClass="form-control" id="from_date"  name="tdsSplRateMast.from_date" readonly="true" placeholder="Certificate From Date" value="%{formateInstDate}"  onkeypress="validateDateOnKeyDown(this, event);" onblur="checkDatesOfFinancialYear(this);validateDateOnBlur(this);" maxLength="10"/>
                                    <s:hidden  name="tdsSplRateMast.from_date" value="%{formateInstDate}"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-sm addon-btn" disabled="" onmouseover="OnLoadSetFinancialYear('from_date', 'fromCal')">
                                            <i class="fa fa-calendar" id="fromCal"></i></button>
                                    </div>
                                </s:elseif>

                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Certificate Valid Upto <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:if test="%{action.equalsIgnoreCase('add') || action.equalsIgnoreCase('save')}"> 

                                    <s:textfield type="text" cssClass="form-control" id="certificate_valid_upto" placeholder="Enter Certificate Valid Upto" name="tdsSplRateMast.certificate_valid_upto" onblur="checkDatesOfFinancialYear(this);checkCertificateValidUpto(this);" maxLength="10"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="OnLoadSetFinancialYear('certificate_valid_upto', 'certifIcon')">
                                            <i class="fa fa-calendar" id="certifIcon"></i></button>
                                    </div>
                                </s:if>

                                <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                    <s:if test="tdsSplRateMast.certificate_valid_upto==null">
                                        <s:textfield id="certificate_valid_upto"  name="tdsSplRateMast.certificate_valid_upto"  value="" cssClass="form-control" placeholder="Certificate Valid Upto" onkeypress="validateDateOnKeyDown(this, event);" onblur="checkDatesOfFinancialYear(this);checkCertificateValidUpto(this);validateDateOnBlur(this);" maxLength="10"/>
                                    </s:if>
                                    <s:else>
                                        <s:set var="formateInstDate"><s:date name="tdsSplRateMast.certificate_valid_upto" format="dd-MM-yyyy"/></s:set> 
                                        <s:textfield id="certificate_valid_upto"  name="tdsSplRateMast.certificate_valid_upto" value="%{#formateInstDate}" cssClass="form-control" placeholder="Certificate Valid Upto" onkeypress="validateDateOnKeyDown(this, event);" onblur="checkDatesOfFinancialYear(this);checkCertificateValidUpto(this);validateDateOnBlur(this);" maxLength="10"/>
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="OnLoadSetFinancialYear('certificate_valid_upto', 'certifIcon')">
                                                <i class="fa fa-calendar" id="certifIcon"></i></button>
                                        </div> 
                                    </s:else>

                                </s:elseif>




                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Limit Amount<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="tds_limit_amt" placeholder="Enter TDS Limit Amount" name="tdsSplRateMast.tds_limit_amt" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Amount Consumed
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="amount_consumed" placeholder="Enter Amount Consumed" name="tdsSplRateMast.amount_consumed" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Rate<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="tds_rate" placeholder="Enter TDS Rate" name="tdsSplRateMast.tds_rate" onblur="validateTDSRate(this.id);"/>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                </div>
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center"> 
                        <s:if test="%{action.equalsIgnoreCase('add') || action.equalsIgnoreCase('save')}">
                            <button type="button" id="saveBtn" class="form-btn" onclick="saveTdsSplRateDetail();"><i class="fa save" aria-hidden="true"></i>Save</button>
                            <!--                            <button type="button" class="form-btn" data-toggle="collapse" data-target="#importTdsDiv" aria-expanded="false" aria-controls="importTdsDiv">
                                                            <i class="fa import" aria-hidden="true"></i>Import From File
                                                        </button>-->
                        </s:if>
                        <s:else>    
                            <button type="button" id="saveBtn" class="form-btn" onclick="updateTdsSplRateDetail();"><i class="fa update" aria-hidden="true"></i>Update</button>
                        </s:else>
                        <s:url id="cancelBtn_id" namespace="/" action="tdsLowerDeductionBrowse">
                            <s:param name="pageNumber" value="%{pageNumber}"/>
                        </s:url>
                        <s:a href="%{cancelBtn_id}">
                            <button type="button" class="form-btn"  ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </s:a>
                    </div>
                </div>

                <s:hidden id="deductee_code" name="tdsSplRateMast.deductee_code"  cssClass="form-control" placeholder="Deductee Name" maxLength="15"></s:hidden>
            </s:form>
        </div>
    </div>

    <div class="clearfix"></div>

    <!--Import TDS Special rate mast div-->
    <%--    <div class="collapse" id="importTdsDiv">
            <div class="card card-body tab-content px-4 py-4 mt-1">
                <s:form id="importTdsForm" name="importTdsForm" action="importTDSRate" method="POST" theme="css_xhtml"
                        enctype="multipart/form-data" autocomplete="off">

                <s:hidden name="action" value="importTds"/>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Attachment <span class="font-weight-bold text-danger ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input form-control" name="obj_import_file.template" id="tdsImport" 
                                   title="Upload only Excel File" accept="<s:property value="%{#session.EXCELFORMAT}"/>">

                            <label class="custom-file-label" for="tdsImport">Choose file...</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center">
                        <button type="submit" class="form-btn"><i class="fa import" aria-hidden="true"></i>Upload</button>
                    </div>
                </div>
            </s:form>
        </div>
    </div>--%>
    <!--Import TDS Special rate mast div-->
</div>

<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>
<script src="resources/js/regular/lowerDeduction/lowerDeductionCertificate.js"></script>

