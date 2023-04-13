<%-- 
    Document   : deducteesEdit15Gh
    Created on : Mar 13, 2019, 12:30:19 PM
    Author     : aniket.naik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--<script src="resources/js/regular/DeducteeManage15ghDetail/deducteeManage15GHDetail.js" type="text/javascript"></script>-->
<script src="resources/js/form15GH/deducteeManage15GHDetail.js" type="text/javascript"></script>
<script src="resources/js/global/template.js?e=<%=Math.random()%>"></script>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/transaction/transaction.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<!--<script src="resources/js/regular/transaction/transaction.js"></script>-->
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>
<script>
    function getOnLoadCal(id, btnid) {
        myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
        myCalendar.setDateFormat("%d-%m-%Y");
        myCalendar.hideTime();
        var yearBegDate = document.getElementById("yearBegDate").value;
        myCalendar.setDate(yearBegDate);
        $("#from_date").blur(function () {
            myCalendar.hide();
        });
        $("#to_date").blur(function () {
            myCalendar.hide();
        });
    }
</script>

<!DOCTYPE html>
<!--<script src="../../../../../resources/js/regular/DeducteeManage15ghDetail/deducteeManage15GHDetail.js" type="text/javascript"></script>-->
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="challanBrowsePage()"  class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="#"><p>Transaction Browse </p> </a></li>
            <li onclick="callEntryPage();" class="nav-item"><a class="nav-link active" data-toggle="pill" href="deducteesAction15gh"><p> Transaction Entry </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <s:set name="readonly"><s:property value="%{readonly}"/></s:set>
    <s:div cssClass="errorMessage" id="deductee_notification" cssStyle="width:60%;"></s:div>
    <s:hidden value="%{#assessment.getTdsTypeCode()}" id="tds_type_code" />
    <s:hidden id="action" name="action" value="%{action}"/>  
    <s:hidden id="htdsCode" name="deducteeMast15gh.tds_code"/>
    <s:hidden id="deductee_client_code" name="deductee_client_code" value="%{deducteeMast15gh.client_code}"/>
    <s:hidden id="rowid_seq" name="deducteeMast15gh.rowid_seq"/>
    <s:hidden id="hcountry_code" name="deducteeMast15gh.country_code"/>
    <s:hidden id="hdeducteeType" name="deducteeMast15gh.deductee_type_code"/>
    <s:hidden id="hidden_tdsTypeCode" name="hidden_tdsTypeCode"/>
    <s:form id="deductee_entry_page" name="deductee_entry_page">
        <s:hidden id="xml_15gh_error" name="xml_15gh_error"/>
        <s:hidden id="deductee_client_code" name="deductee_client_code" value="%{deducteeMast15gh.client_code}" />
        <s:hidden id="rowid_seq" name="rowid_seq"/>
        <s:hidden id="hiddenPanNO" name="hiddenPanNO" value="%{deducteeMast15gh.panno}"/>
        <s:hidden id="PanNoVal" name="PanNoVal"/>
        <s:hidden name="pageNumber"/>
        <s:hidden name="update_data_row"/>
        <div class="tab-content px-4 py-4">
            <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
            
            <div id="entry" class="tab-pane form-wrapper fade active show">
                <div class="container-fluid pt-2">
                    <!---------------message div ------------------------->
                    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Deductee Category<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:select list="deductee_catg_list" readonly="#readonly" id="deductee_catg" name="deducteeMast15gh.deductee_catg" cssClass="form-control" onchange="selectTDSSection();" cssStyle="width:100%;"/>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                PAN No. <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <%--<s:textfield type="text" class="form-control" id="panno" placeholder="ENTER PAN NO." name="tdsTranLoad.deductee_panno" onblur="validatePAN(this); getDeducteeCode(this);" />--%>
                                <s:textfield type="text" readonly="#readonly" cssClass="form-control" placeholder="Enter PAN" id="panno" cssStyle="text-transform: uppercase;" name="deducteeMast15gh.panno" maxlength="10" onblur="check_deductee_type(this.id);"/>
                                <s:textfield id="verifiedBy" name="" cssClass="form-control" maxLength="10" readonly="true" placeholder ="PANNO Status Not Available"/>
                                <div class="input-group-prepend">
                                    <div class="input-group-text line-height-2" title="Pan Not Available">
                                        <input type="checkbox" id="checkbox" onclick="getPanNotAvailble()">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--                    <div class="row form-group">
                                            <div class="col-lg-4 col-xl-4 text-right">
                                                <label class="control-label">
                                                    PAN<span class="text-danger font-weight-bold ml-1">*</span>
                                                </label>
                                            </div>
                                            <div class="col-lg-7 col-xl-4">
                    <%--<s:textfield type="text" readonly="#readonly" cssClass="form-control" placeholder=" Enter PAN" id="panno" name="deducteeMast15gh.panno" maxlength="10" cssStyle="width:100%;text-transform: uppercase;" onblur="check_deductee_type(this.id);"/>--%>
                </div>
                <div class="col-lg-2 col-xl-2 text-left">
                    <div>
                        <input type="checkbox" id="checkbox" onclick="getPanNotAvailble()">
                        <label class="custom-control-label" for="chckbox">Pan Not Available</label>
                    </div>                            
                </div>
                <div class="col-xs-12">
                    <div class="form-group halfTextBoxRight">
                    <%--<s:textfield id="verifiedBy" name="" cssClass="form-control" maxLength="10" readonly="true" placeholder ="PANNO Status Not Available"/>--%>
                </div>
            </div>
        </div>        -->
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Deductee Name.<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" readonly="#readonly" cssClass="form-control" placeholder="ENTER DEDUCTEE NAME" cssStyle="width:100%; text-transform: uppercase;" id="fltrDeducteeName" name="deducteeMast15gh.deductee_name" onkeypress="return ValidateDeducteeNameAlpha(event);" maxLength="75"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Identification Number
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">
                                <s:textfield type="text" cssClass="form-control" placeholder="ENTER IDENTIFICATION NO." cssStyle="width:100%;" id="identificationNO" name="deducteeMast15gh.deduction_ref_no" maxlength="9" onkeypress="return validateNumber(event);"/>
                                <%--<s:textfield type="text" cssClass="form-control" placeholder="ENTER IDENTIFICATION NO." cssStyle="width:100%;" id="identificationNO" name="deducteeMast15gh.deduction_ref_no" maxlength="9" onblur="validateIdentificationNO();"  onkeypress="return validateNumber(event);"/>--%>
                            </s:if>
                            <s:else>
                                <s:textfield type="text" readonly="true" cssClass="form-control" placeholder="ENTER IDENTIFICATION NO." cssStyle="width:100%;" id="identificationNO" name="deducteeMast15gh.deduction_ref_no" maxlength="9"/>
                            </s:else>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Resident Status
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="resident_type" readonly="#readonly" cssClass="form-control" cssStyle="width:100%;" id="resident_status" name="deducteeMast15gh.resident_status" onchange="selectCountry();" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Address 
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder="Enter Address" cssStyle="width:100%;text-transform: uppercase;" id="add1" name="deducteeMast15gh.add1" maxlength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder="Enter Address 2" cssStyle="width:100%;text-transform: uppercase;" id="add2" name="deducteeMast15gh.add2" maxlength="25"/>
                        </div>
                    </div>    
                    <div class="row form-group">
                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder=" Enter Address 3" cssStyle="width:100%;text-transform: uppercase;" id="add3" name="deducteeMast15gh.add3" maxlength="25"/>
                        </div>
                    </div>    
                    <div class="row form-group">
                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder=" Enter Address 4" cssStyle="width:100%;text-transform: uppercase;" id="add4" name="deducteeMast15gh.add4" maxlength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PIN Code
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" onkeypress="return lhsIsNumber(event);" placeholder="Enter PIN Code" cssStyle="width:100%;" id="pin" name="deducteeMast15gh.pin" maxlength="6" onblur="getCityAndStateByPin(this.id);"/>
                        </div>
                    </div>
                    <div id="countryRowID">
                        <div class="row form-group" id="">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label" id="countryLbl">
                                    Country
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <s:select list="select_country" readonly="#readonly" id="country_code" name="deducteeMast15gh.country_code" cssClass="form-control" />
                            </div>
                        </div>      
                    </div>      

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                City
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <%--<s:textfield cssClass="form-control" readonly="#readonly" placeholder="Enter City" cssStyle="width:100%;text-transform: uppercase;" id="city_code" name="deducteeMast15gh.city_code" maxlength="20"/>--%>
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder="Enter City" cssStyle="width:100%;text-transform: uppercase;" id="city_code" name="deducteeMast15gh.city_code" maxlength="20"/>
                        </div>
                    </div>   
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        State
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <%--<s:select list="select_state" readonly="#readonly" id="state_code" name="deducteeMast15gh.state_code" cssClass="form-control" cssStyle="width:100%;"></s:select>--%>
                                    <s:select list="select_state" readonly="#readonly" cssClass="form-control" placeholder=" Enter state" cssStyle="width:100%;text-transform: uppercase;" id="state_code" name="deducteeMast15gh.state_code" maxlength="2" headerKey="" headerValue="Select State" onchange="selectAddressCity();"/>

                                </div>
                            </div>  
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                STD Code/Telephone 
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield cssClass="form-control" readonly="#readonly" placeholder=" Enter STD Code"  id="stdCode" name="deducteeMast15gh.stdcode" maxlength="6"/>
                                <s:textfield cssClass="form-control" readonly="#readonly" placeholder=" Enter Phone No"  id="pnoneNo" name="deducteeMast15gh.phoneno" maxlength="15"/>
                            </div>                         
                        </div>
                    </div>    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Mobile No
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder=" Enter Mobile No" cssStyle="width:100%;" id="mobileno" name="deducteeMast15gh.mobileno" maxlength="10" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Email
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" readonly="#readonly" placeholder="Enter Email" cssStyle="width:100%;text-transform: lowercase;" 
                                         id="email_id" name="deducteeMast15gh.email_id" maxlength="50" onblur="return validateEmail(this);"/>
                        </div>
                    </div> 
                    <div class="col-md-9">
                        <div class="form-group"> 
                            <s:select list="deductee_type" readonly="#readonly" cssClass="select_tag" cssStyle="width:100%;font-weight:bold;display: none;" id="deductee_type" name="deducteeMast15gh.deductee_type_code" headerKey="" headerValue="Select" />
                        </div>
                    </div>                        
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                File Type
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="fileTypeList" readonly="#readonly" id="fileType" name="deducteeMast15gh.bflag" cssClass="form-control" onchange="onChangeFileType();" cssStyle="width:100%;"/>
                        </div>
                    </div>                    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Reference No.
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">                                      
                                <s:textfield placeholder="Refrence No" id="refrence_no" name="deducteeMast15gh.reference_no"  cssClass="form-control" cssStyle="width:103%margin-right:5px; " maxlength="10" />
                            </s:if>
                            <s:else>
                                <s:textfield placeholder="Refrence No" readonly="true" id="refrence_no" name="deducteeMast15gh.reference_no"  cssClass="form-control" cssStyle="width:103%margin-right:5px; " maxlength="10" />                                 
                            </s:else>
                        </div>
                    </div>                        
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PAN Status
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="select_pan_status" readonly="#readonly" id="pan_status" name="deducteeMast15gh.pan_status" cssClass="form-control" cssStyle="width:100%;" headerKey="" headerValue="Select PAN Status"/> 
                        </div>
                    </div>                   
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                DOB
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">                             
                                <%--<s:textfield type="text"  cssClass="form-control" placeholder=" Enter DOB" cssStyle="width:100%;" id="dob" name="deducteeMast15gh.dob" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/>--%>

                                <div class="input-group">
                                    <s:textfield type="text" cssClass="form-control" id="dob"  name="deducteeMast15gh.dob" placeholder="Enter DOB" onblur="validateDateOnBlur(this);" />
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary addon-btn" onmouseover="getOnLoadCal('dob', 'dtobIcon')">
                                            <i class="fa fa-calendar" id="dtobIcon"></i>
                                        </button>
                                    </div>
                                </div>



                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                <s:if test="deducteeMast15gh.dob==null">
                                    <%--<s:textfield type="text" id="dob" name="deducteeMast15gh.dob" cssClass="form-control" placeholder=" Enter DOB" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/><!--date-->-->--%>

                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="dob"  name="deducteeMast15gh.dob" placeholder="Enter DOB" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('dob', 'dobIcon')">
                                                <i class="fa fa-calendar" id="dobIcon"></i>
                                            </button>
                                        </div>
                                    </div>


                                </s:if>
                                <s:else>
                                    <s:set name="formatedob"><s:date name="deducteeMast15gh.dob" format="dd-MM-yyyy"/></s:set>

                                    <%--<s:textfield id="dob" name="deducteeMast15gh.dob" value="%{formatedob}" cssClass="form-control" placeholder="DOB" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/><!--date-->--%>

                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="dob" value="%{formatedob}" name="deducteeMast15gh.dob" placeholder="Enter DOB" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('dob', 'dobIcon')">
                                                <i class="fa fa-calendar" id="dobIcon"></i>
                                            </button>
                                        </div>
                                    </div>

                                </s:else>
                            </s:elseif>
                            <s:elseif test="%{action.equalsIgnoreCase('view')}">
                                <s:if test="deducteeMast15gh.dob==null">
                                    <%--<s:textfield type="text" readonly="#readonly" id="dob" name="deducteeMast15gh.dob" cssClass="form-control" placeholder=" Enter DOB" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/><!--date-->--%>
                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="dob"  name="deducteeMast15gh.dob" placeholder="Enter DOB" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('dob', 'dobIcon')">
                                                <i class="fa fa-calendar" id="dobIcon"></i>
                                            </button>
                                        </div>
                                    </div>

                                </s:if>
                                <s:else>
                                    <s:set name="formatedob"><s:date name="deducteeMast15gh.dob" format="dd-MM-yyyy"/></s:set>
                                    <s:textfield id="dob" readonly="#readonly" name="deducteeMast15gh.dob" value="%{formatedob}" cssClass="form-control" placeholder="DOB" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/><!--date-->

                                </s:else>
                            </s:elseif>        
                        </div>    
                    </div>              
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Whether assessed to tax under the Income Tax Act 1961
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="assessed_tax_list" readonly="#readonly" id="assessment_select" onchange="selectAssessmentYear(); changeAssessmentYear();" cssClass="form-control" cssStyle="width:100%;"/>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Estimated income for declaration
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <%--<s:textfield id="est_income" readonly="#readonly" name="deducteeMast15gh.est_declaration_income" cssClass="form-control" placeholder="Estimated income" cssStyle="width:100%;"  maxlength="10" onkeypress="return validateNumber(event);"/>--%>
                            <s:textfield id="est_income" readonly="#readonly" name="deducteeMast15gh.est_declaration_income" cssClass="form-control" placeholder="Estimated income" cssStyle="width:100%;"  maxlength="10" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>     
                    <div class="row form-group" id="latestAccYearID">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Latest assessment year for which assessed
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="assessment_year" readonly="#readonly" id="assessment_year" name="deducteeMast15gh.latest_assessment_year" cssClass="form-control" placeholder="City" cssStyle="width:100%;text-transform: uppercase;"  maxlength="6" headerKey="" headerValue="Select Latest Assess Year"/>
                        </div>
                    </div>           
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Estimated total income including above
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <%--<s:textfield id="est_total_income" readonly="#readonly" name="deducteeMast15gh.est_total_income" cssClass="form-control" placeholder="Estimated total income" cssStyle="width:100%;text-transform: uppercase;" maxlength="10" onkeypress="return validateNumber(event);"/>--%>
                            <s:textfield id="est_total_income" readonly="#readonly" name="deducteeMast15gh.est_total_income" cssClass="form-control" placeholder="Estimated total income" cssStyle="width:100%;text-transform: uppercase;" maxlength="10" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Total number of form filled(Other than this)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="form_filed" readonly="#readonly" name="deducteeMast15gh.total_no_form" cssClass="form-control" placeholder="Form filed" cssStyle="width:100%;"  maxlength="4"/>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Aggregate amount of income(other than this)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <%--<s:textfield id="aggregate_amount" readonly="#readonly" name="deducteeMast15gh.aggregate_income_amt" cssClass="form-control" placeholder="Aggregate amount" cssStyle="width:100%;" maxlength="10"/>--%>
                            <s:textfield id="aggregate_amount" readonly="#readonly" name="deducteeMast15gh.aggregate_income_amt" cssClass="form-control" placeholder="Aggregate amount" cssStyle="width:100%;" maxlength="10"  onblur="checkMendatoryField();" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Date of Declaration
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">                             
                                <%--<s:textfield id="declaration_date" name="deducteeMast15gh.declaration_date" cssClass="form-control" placeholder="Date of declaration" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/>--%>

                                <div class="input-group">
                                    <s:textfield type="text" cssClass="form-control" id="declaration_date"  name="deducteeMast15gh.declaration_date" placeholder="Date of declaration" onblur="validateDateOnBlur(this);" />
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('declaration_date', 'declaration_dateIcon')">
                                            <i class="fa fa-calendar" id="declaration_dateIcon"></i>
                                        </button>
                                    </div>
                                </div>

                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                <s:set name="LDecDate" value="%{deducteeMast15gh.declaration_date}"></s:set>
                                <s:if test="#LDecDate != null">   
                                    <s:set name="formatedecldate"><s:date name="deducteeMast15gh.declaration_date" format="dd-MM-yyyy"/></s:set>
                                    <%--<s:textfield id="declaration_date" value="%{formatedecldate}" name="deducteeMast15gh.declaration_date" cssClass="form-control" placeholder="Date of declaration" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />--%>

                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="declaration_date" value="%{formatedecldate}" name="deducteeMast15gh.declaration_date" placeholder="Date of declaration" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('declaration_date', 'declaration_dateIcon')">
                                                <i class="fa fa-calendar" id="declaration_dateIcon"></i>
                                            </button>
                                        </div>
                                    </div>

                                </s:if>
                                <s:else>
                                    <%--<s:textfield id="declaration_date" name="deducteeMast15gh.declaration_date" cssClass="form-control" placeholder="Date of declaration" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />--%>
                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="declaration_date"  name="deducteeMast15gh.declaration_date" placeholder="Date of declaration" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('declaration_date', 'declaration_dateIcon')">
                                                <i class="fa fa-calendar" id="declaration_dateIcon"></i>
                                            </button>
                                        </div>
                                    </div>
                                </s:else>
                            </s:elseif>
                            <s:elseif test="%{action.equalsIgnoreCase('view')}">
                                <s:set name="LDecDate" value="%{deducteeMast15gh.declaration_date}"></s:set>
                                <s:if test="#LDecDate != null">   
                                    <s:set name="formatedecldate"><s:date name="deducteeMast15gh.declaration_date" format="dd-MM-yyyy"/></s:set>
                                    <s:textfield id="declaration_date" readonly="#readonly" value="%{formatedecldate}" name="deducteeMast15gh.declaration_date" cssClass="form-control" placeholder="Date of declaration" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />
                                </s:if>
                                <s:else>
                                    <s:textfield id="declaration_date" readonly="#readonly" name="deducteeMast15gh.declaration_date" cssClass="form-control" placeholder="Date of declaration" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />                                 
                                </s:else>
                            </s:elseif>
                        </div>
                    </div>            
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Amount of income paid
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield id="income_paid" readonly="#readonly" name="deducteeMast15gh.income_amount_paid" cssClass="form-control" placeholder="Income paid" cssStyle="width:100%;" maxlength="10" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>                
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Date of income paid
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">                             
                                <%--<s:textfield type="text" id="income_paid_date" name="deducteeMast15gh.income_paid_date" cssClass="form-control" placeholder="Date of income paid" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />--%>

                                <div class="input-group">
                                    <s:textfield type="text" cssClass="form-control" id="income_paid_date"  name="deducteeMast15gh.income_paid_date" placeholder="Date of income paid" onblur="validateDateOnBlur(this);" />
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('income_paid_date', 'income_paid_dateIcon')">
                                            <i class="fa fa-calendar" id="income_paid_dateIcon"></i>
                                        </button>
                                    </div>
                                </div>

                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('edit')}">
                                <s:set name="LIncPadDate" value="%{deducteeMast15gh.income_paid_date}"></s:set>
                                <s:if test="#LIncPadDate != null">   
                                    <s:set name="formateincmdate"><s:date name="deducteeMast15gh.income_paid_date" format="dd-MM-yyyy"/></s:set>
                                    <%-- <s:textfield id="income_paid_date" name="deducteeMast15gh.income_paid_date" value="%{formateincmdate}" cssClass="form-control" placeholder="Date of income paid" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />--%>


                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="income_paid_date" value="%{formateincmdate}" name="deducteeMast15gh.income_paid_date" placeholder="Date of income paid" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('income_paid_date', 'income_paid_dateIcon')">
                                                <i class="fa fa-calendar" id="income_paid_dateIcon"></i>
                                            </button>
                                        </div>
                                    </div>

                                </s:if>
                                <s:else>
                                    <%--<s:textfield id="income_paid_date" name="deducteeMast15gh.income_paid_date" cssClass="form-control" placeholder="Date of income paid" cssStyle="width:100%;"  onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/>--%>

                                    <div class="input-group">
                                        <s:textfield type="text" cssClass="form-control" id="income_paid_date"  name="deducteeMast15gh.income_paid_date" placeholder="Date of income paid" onblur="validateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);" />
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn"  onmouseover="getOnLoadCal('income_paid_date', 'income_paid_dateIcon')">
                                                <i class="fa fa-calendar" id="income_paid_dateIcon"></i>
                                            </button>
                                        </div>
                                    </div>

                                </s:else>
                            </s:elseif>
                            <s:elseif test="%{action.equalsIgnoreCase('view')}">
                                <s:set name="LIncPadDate" value="%{deducteeMast15gh.income_paid_date}"></s:set>
                                <s:if test="#LIncPadDate != null">   
                                    <s:set name="formateincmdate"><s:date name="deducteeMast15gh.income_paid_date" format="dd-MM-yyyy"/></s:set>
                                    <s:textfield id="income_paid_date" readonly="#readonly" name="deducteeMast15gh.income_paid_date" value="%{formateincmdate}" cssClass="form-control" placeholder="Date of income paid" cssStyle="width:100%;" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10" />
                                </s:if>
                                <s:else>
                                    <s:textfield id="income_paid_date" readonly="#readonly" name="deducteeMast15gh.income_paid_date" cssClass="form-control" placeholder="Date of income paid" cssStyle="width:100%;"  onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);" maxLength="10"/>
                                </s:else>
                            </s:elseif>
                        </div>
                    </div>       
                    <div  id="RefDetailDivID">
                        <!--Append Data from AJAX-->
                    </div>  
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center">
                            <s:if test="%{!action.equalsIgnoreCase('view')}">   
                                <s:if test="%{action.equalsIgnoreCase('add')}">              
                                    <button type="button" id ="btnUpdate" onclick="saveDeductee15ghContentData();" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save</button>

                                    <!--<div class="btn btn-success Save-Btn" id="btnUpdate" style="width:100%; " onclick="saveDeductee15ghContentData();">Save</div>-->

                                    <s:url id="cancelBtn_id" namespace="/" action="form15GHTransaction"></s:url>
                                    <s:a href="%{cancelBtn_id}">
                                        <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                    </s:a>
                                </s:if>
                                <s:else>
                                    <button type="button" id="btnUpdate" class="form-btn" onclick="update15ghDeducteeContentData(this);"><i class="fa update" aria-hidden="true"></i>Update</button>
                                    <s:url id="cancelBtn_id" namespace="/" action="form15GHTransaction"></s:url>
                                    <s:a href="%{cancelBtn_id}">
                                        <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                    </s:a>
                                </s:else>        
                            </s:if>
                            <s:else>
                                <s:url id="cancelBtn_id" namespace="/" action="form15GHTransaction"></s:url>
                                <s:a href="%{cancelBtn_id}">
                                    <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                </s:a>
                            </s:else>            
                        </div> 
                    </div>
                </div>
            </div>
        </div>
    </s:form>     
</div>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>    
<script>
    <s:if test="%{action.equalsIgnoreCase('edit')}">
    try {
        check_edit_15gh_deductee_type();
    } catch (err) {
    }
    try {
        var l_pan = document.getElementById("panno").value;
        isPANverified(l_pan);
    } catch (err) {
    }
    try {
        checkCheckedPanNo();
    } catch (err) {
    }
    try {
        var panno = document.getElementById("panno").value;
        var LPanNO = panno.toUpperCase();
        var l_pan_char = LPanNO.charAt(3);
        setPanStatus(l_pan_char);
    } catch (err) {
    }
    </s:if>
    <s:elseif test="%{action.equalsIgnoreCase('view')}">
    try {
        check_edit_15gh_deductee_type();
    } catch (err) {
    }
    try {
        var l_pan = document.getElementById("panno").value;
        isPANverified(l_pan);
    } catch (err) {
    }
    try {
        checkCheckedPanNo();
    } catch (err) {
    }
    try {
        var panno = document.getElementById("panno").value;
        var LPanNO = panno.toUpperCase();
        var l_pan_char = LPanNO.charAt(3);
        setPanStatus(l_pan_char);
    } catch (err) {
    }
    </s:elseif>
    try {
        getDeductee15ghRefAmount();
    } catch (err) {
    }
    try {
        selectCountry();
    } catch (err) {
    }
    try {
        selectTDSSection();
    } catch (err) {
    }

</script>    