<%-- 
    Document   : manageChallan
    Created on : Jan 31, 2019, 4:13:25 PM
    Author     : sneha.parpelli
--%>
<%@page import="globalUtilities.Util" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<!--<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>-->
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<!--<script src="resources/js/global/bootstrap-datetimepicker.js" type="text/javascript"></script>-->
<script src="resources/js/lhs/lhsGlobal.js" type="text/javascript"></script>
<script src="resources/js/global/template.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/challan/challan.js?r=<%=Math.random()%>"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>

<div class="page-section">
    <s:hidden name="objChallan.nil_challan_flag" id="h_nil_challan_flag"/>
    <s:hidden value="%{#login.getClient_type_code()}" id="client_type_code" />
    <s:hidden name="objChallan.book_entry_flag" id="h_book_entry_flag"/>
    <s:hidden name="objChallan.tds_challan_date" id="h_tds_challan_date"/>
    <s:hidden id="hprocrssFlag" name="procrssFlag" value="%{procrssFlag}"/>
    <s:set name="readonly"><s:property value="%{readonly}"/></s:set>
    <s:hidden id="mapAllocatedAmount" name="mapAllocatedAmount" value="%{mapAllocatedAmount}"/>
    <s:hidden id="action" name="action" value="%{action}"/>
   
    <s:set name="formatYearBegDate"><s:date name="%{#assessment.getQuarterFromDate()}" format="dd-MM-yyyy"/></s:set>
    <s:set name="formatYearBegDateCal"><s:date name="%{#assessment.getQuarterFromDate()}" format="MM-dd-yyyy"/></s:set>
    <s:set name="formatYearEndDate"><s:date name="%{#assessment.getQuarterToDate()}" format="dd-MM-yyyy"/></s:set>
    <s:set name="formatYearEndDateCal"><s:date name="%{#assessment.getQuarterToDate()}" format="MM-dd-yyyy"/></s:set>
    <s:hidden id="yearBegDate" value="%{#formatYearBegDate}" />
    <s:hidden id="yearEndDate" value="%{#formatYearEndDate}" />
    <s:hidden id="yearBegDateCal" value="%{#formatYearBegDateCal}" />
    <s:hidden id="yearEndDateCal" value="%{#formatYearEndDateCal}" />
    <% Util utl = new Util();%>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li onclick="challanBrowsePage()"  class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="#browse"><p>Challan Browse </p> </a></li>
            <li  onclick="challanEntryPage()"  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="tdsChallan"><p> Challan Entry </p></a></li>

            <li style="cursor: not-allowed;color:black;" title="Allocation is allowed for individual record through browse page only ! " class="nav-item"><a class="nav-link" style="pointer-events:none;"><p> Challan Allocation </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>
    <s:if test="%{!action.equalsIgnoreCase('add')}">
        <div class="tab-content">      
            <div class="row container-fluid" style="position: relative; margin-top: 2px; padding-bottom: 2px;">

                <div class="col-sm-5" style="text-align: right;font-weight: bold;">Allocated Amount :</div>
                <div class="col-sm-2" style="font-weight: bold;" >
                    <s:if test="%{mapAllocatedAmount==null ||mapAllocatedAmount=='' ||mapAllocatedAmount=='null'}">0.00 <input type="hidden" id="allocated_amt" value="0.0"/></s:if>
                    <s:else>
                        <s:set var="mapAllocatedAmount_value" value="mapAllocatedAmount"></s:set>
                        <%
                            String mapallocamt = "0.00";
                            Double allocVal = 0.0;
                            try {
                                allocVal = (Double) pageContext.getAttribute("mapAllocatedAmount_value");
                                mapallocamt = utl.getAmountIndianFormat(allocVal);
                            } catch (Exception e) {
                            }
                        %>
                        <%=mapallocamt%>
                    </s:else>
                </div>

            </div>
        </div>
        <div class="gap3"></div>
    </s:if>


    <s:form id="challan_entry_page" name="challan_entry_page">
        <s:hidden name="objChallan.rowid_seq" id="h_rowid_seq"/>
<!--    <s:hidden id="client_catg_code"  value="%{client_catg_code}"/>
        <input type="hidden" id="client_catg_codey" value="<s:property value="%{client_catg_code}"/>">-->
          <s:hidden value="%{client_catg_code}" id="client_catg_code" />
        <div class="tab-content px-4 py-4">
            <div id="browse" class="tab-pane show in active">

            </div>

            <div id="entry" class="tab-pane form-wrapper fade active show">
                <div class="container-fluid pt-2">
                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">Challan Entry Type</label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('Edit') && objChallan.nil_challan_flag.equalsIgnoreCase('N') && objChallan.book_entry_flag.equalsIgnoreCase('N')}">
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="challan_entry_flag" id="challan_entry_flag" checked onclick="book_entry_disable_enable(this.id);addDefaultNilReturn(this.id);">Challan Entry
                                    </label>
                                </div>
                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('View') && objChallan.nil_challan_flag.equalsIgnoreCase('N') && objChallan.book_entry_flag.equalsIgnoreCase('N')}">
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="challan_entry_flag" id="challan_entry_flag" checked onclick="book_entry_disable_enable(this.id);addDefaultNilReturn(this.id);">Challan Entry
                                    </label>
                                </div>
                            </s:elseif>
                            <s:else>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="challan_entry_flag" id="challan_entry_flag" onclick="book_entry_disable_enable(this.id);addDefaultNilReturn(this.id);">Challan Entry
                                    </label>
                                </div>
                            </s:else>

                            <div class="form-check-inline">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="objChallan.nil_challan_flag" id="nill_challan_flag" onclick="book_entry_disable_enable(this.id);addDefaultNilReturn(this.id);">Nil Challan
                              
                                </label>
                            </div>
                            <div class="form-check-inline">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="objChallan.book_entry_flag" id="book_entry_flag" onclick="book_entry_disable_enable(this.id);addDefaultNilReturn(this.id);">Book Entry
                                </label>
                            </div>
                        </div>
                        <!--                        <div class="col-lg-11 text-center">
                                                    <div class="custom-control custom-checkbox custom-control-inline">
                                                        <input type="checkbox" name="objChallan.nil_challan_flag" class="custom-control-input" id="nill_challan_flag" onclick="book_entry_disable_enable(this.id);" >
                                                        <label class="custom-control-label" for="nill_challan_flag" >Nill Challan</label>
                                                    </div>
                                                    <div class="custom-control custom-checkbox custom-control-inline">
                                                        <input type="checkbox" name="objChallan.book_entry_flag" id="book_entry_flag" onclick="book_entry_disable_enable(this.id);" class="custom-control-input">
                                                        <label class="custom-control-label" for="book_entry_flag">Book Entry</label>
                                                    </div>
                        
                                                </div>-->
                    </div>
                    <!---------------message div ------------------------->
                    <!--Form Validation success-->
                    <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                        <div class="d-inline-block">
                            <div class="form-validation form-validation--success p-1 my-1">
                                <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                <h5 class="d-inline-block" id="successMsg">Data Save Successfully</h5>
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

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                BSR Code<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="text"  name="objChallan.bank_bsr_code" readonly="#readonly" maxLength="7" cssClass="form-control input-group-input w-25" id="bsr_code" placeholder="Enter BSR Code" onblur="changeBSRCode(this.id);" onkeypress="return validateNumber(event);" autocomplete="off"/>
                                <%--<s:textfield id="bank_name" name="objChallan.bank_name" readonly="true" cssClass="form-control w-75" placeholder="Enter Bank Name/Branch Name" maxLength="50"/>--%>                              
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Challan Date <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:if test="%{action.equalsIgnoreCase('add')}">                           
                                    <s:textfield id="l_challan_date" name="objChallan.tds_challan_date"  cssClass="form-control" placeholder="Enter TDSChallanDate(DD-MM-YYYY)" onblur="validateDate(this);" onkeypress="validateDateOnKeyDown(this, event);" maxLength="10"  />
                                </s:if>
                                <s:else>
                                    <s:textfield id="l_challan_date"  name="objChallan.tds_challan_date"  readonly="#readonly"   cssClass="form-control" placeholder="Enter TDSChallanDate(DD-MM-YYYY)" onblur="validateDate(this);" onkeypress="validateDateOnKeyDown(this, event);" maxLength="10"/>                          
                                </s:else>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" id="calenderBtn" onmouseover="openChallanCalendar('l_challan_date', 'calendarIcon');">
                                        <i class="fa fa-calendar" id="calendarIcon"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                DDO/Challan Serial No.<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="tds_challan_no" name="objChallan.tds_challan_no"  readonly="#readonly" onkeypress='return validateNumber(event);' cssClass="form-control" placeholder=" Enter Challan Serial Number" onblur="changeChalanNo(this.id);" maxLength="5"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Challan Amount<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="tds_challan_tds_amt"  name="objChallan.tds_challan_tds_amt"  readonly="#readonly" onkeypress='return validateNumber(event);' onblur="remove_comma_from_amt_msg(this.id,'Challan Amount');" cssClass="form-control"  placeholder=" Enter Challan Amount" onPaste="paste();"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                TDS Section 
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="selectSection" name="objChallan.tds_code" id="f_tds_section" cssClass="form-control"  placeholder="Section" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Interest On Late Payment
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="tds_challan_int_amt" name="objChallan.tds_challan_int_amt" onkeypress='return validateNumber(event);'  cssClass="form-control" onblur="remove_comma_from_amt_msg(this.id,'Interest On Late Payment');"  placeholder="Enter Interest Late Filing Fee"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Late Payment Fee
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="tds_challan_other_amt" name="objChallan.tds_challan_other_amt" cssClass="form-control" onkeypress='return validateNumber(event);' onblur="remove_comma_from_amt_msg(this.id,'Late Payment Fee');" placeholder="Enter Late Payment Fee"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Minor Head <span id="minorheadspanid" class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="selectMinorHead" id="tds_challan_minor_head" name="objChallan.tds_challan_minor_head" cssClass="form-control" />
                        </div>
                    </div>   
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <s:if test="%{!action.equalsIgnoreCase('View')}">
                                <s:if test="%{action.equalsIgnoreCase('add')}">
                                    <button type="button" id ="saveContinue" class="form-btn" onclick="saveChallanContentData(this.id)"><i class="fa save" aria-hidden="true"></i>Save & Continue</button>
                                    <button type="button" id="saveexit" class="form-btn"  onclick="saveChallanContentData(this.id)"><i class="fa save" aria-hidden="true"></i>Save & Exit</button>
                                    <%--<s:url id="cancelBtn_id" namespace="/" action="tdsChallan"></s:url>--%>
                                    <%--<s:a href="%{cancelBtn_id}">--%>
                                    <button type="button" class="form-btn" onclick="callUrl('/tdsChallan');"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                    <%--</s:a>--%>
                                </s:if> 
                                <s:else>
                                    <s:if test="%{procrssFlag == null}">
                                        <button type="button" class="form-btn" onclick="updateChallanContentData(this);"><i class="fa update" aria-hidden="true"></i>Update</button>
                                    </s:if>      
                                    <s:url id="cancelBtn_id" namespace="/" action="tdsChallan"></s:url>
                                    <s:a href="%{cancelBtn_id}">
                                        <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                    </s:a>
                                    <s:else>
                                        <button type="button" class="form-btn" onclick="updateChallanContentData(this);"><i class="fa update" aria-hidden="true"></i>Update</button>           
                                    </s:else>    
                                </s:else>      
                            </s:if> 
                            <s:else>
                                <s:url id="cancelBtn_id" namespace="/" action="tdsChallan"></s:url>
                                <s:a href="%{cancelBtn_id}">
                                    <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                </s:a>
                            </s:else>
                        </div>
                    </div>
                </div>
            </div>
            <s:hidden id="saveAndContinue" name="saveAndContinue"/>                     
        </s:form>                     
    </div>
</div>
<script>
//    book_entry_disable_enable();
    try {
        //   checkEditCheckResult();
    } catch (err) {
    }
    try {
        //changeDateFormat();
    } catch (err) {
    }
    <s:if test="%{procrssFlag != null}">
    try {
        //window.parent.document.getElementById("challanProcessIndicator").style.visibility = "hidden";
    } catch (err) {
    }
    </s:if>
    <s:elseif test="%{action.equalsIgnoreCase('View')}">
    try {
        //alert('A');
        checkEditCheckResult();
    } catch (err) {
    }
    try {
        changeDateFormat();
    } catch (err) {
    }
        <s:if test="%{procrssFlag != null}">
    try {
        window.parent.document.getElementById("challanProcessIndicator").style.visibility = "hidden";
    } catch (err) {
    }
        </s:if>
    </s:elseif>
    <s:elseif test="%{action.equalsIgnoreCase('Edit')}">
      try {
        changeDateFormat();
    } catch (err) {
    }
    try {
        //alert('A');
        checkEditCheckResult();
    } catch (err) {
    }
      
     </s:elseif>
    try {
        checkTdsLinkResult();
    } catch (err) {
    }
    
    var checkAction =  document.getElementById('action').value;
    if(checkAction === 'add'){
    try{
        onloadCheckRadioBtn();
        
    }catch (err){
        
    }
    }
   </script>
