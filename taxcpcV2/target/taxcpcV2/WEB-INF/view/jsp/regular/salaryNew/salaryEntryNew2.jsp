<%-- 
    Document   : salaryEntryNew2
    Created on : Mar 11, 2020, 12:21:47 PM
    Author     : sukanya.marbade
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<script src="resources/js/global/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<link href="resources/css/salary/salaryNew.css" rel="stylesheet" type="text/css">
<div class="page-section">
    <div class="container-fluid">
        <div class="row position-relative">
            <div class="tab-section col-md-12 my-1">
                <div class="row form-group d-flex align-items-center mb-0">
                    <div class="col-md-6 col-lg-5">
                        <ul class="nav nav-pills">

                            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsSalary"><p> Salary Browse </p> </a></li>
                            <li onclick="salaryEntryNewPage();" class="nav-item"><a class="nav-link active" data-toggle="pill" href="salaryEntryNew2"> <p> Income and Investment Declaration Form </p></a></li>

                        </ul>
                    </div>
                    <div class="col-md-6 col-lg-6 mb-0" style="display: none;" id="top_div">
<!--                        <div class="row form-group">
                            <div class="col-md-1 text-center px-0" style="border-left: 1px solid #000;"><label class="top-label">PAN No :</label></div>
                            <div class="col-md-2">
                                <div class="empname-div">
                                    <span class="emp-data" id="top_emp_panno"><s:property value="%{salaryTranLoad.deductee_panno}"/></span>
                                </div>
                            </div>
                            <div class="col-md-1 text-center px-0"><label class="top-label">Emp Catg: </label></div>
                            <div class="col-md-2">
                                <div class="empname-div">
                                    <span class="emp-data" id="top_emp_catg"><s:property value="%{empCatgMap.get(salaryTranLoad.deductee_catg)}"/></span>
                                </div>
                            </div>
                            <div class="col-md-1 text-center px-0"><label class="top-label">Emp Name:</label></div>
                            <div class="col-md-3" style="">
                                <div class="empname-div">
                                    <span class="emp-data" id="top_emp_name"><s:property value="%{salaryTranLoad.deductee_name}"/></span>
                                </div>
                            </div>
                        </div>-->
<div class="d-flex align-items-center mb-0">
                            <div class=" text-center px-0" style="min-width:10%"><label class="top-label">PAN No: </label></div>
                            <div class="" style="min-width:10%">
                                <div class="empname-div">
                                    <span class="emp-data" style="text-transform: uppercase;" id="top_emp_panno"><s:property value="%{salaryTranLoad.deductee_panno}"/></span>
                                </div>
                            </div>
                            <div class="text-center px-0" style="min-width:10%" ><label class="top-label">Emp Catg: </label></div>
                            <div class="" style="min-width:10%">
                                <div class="empname-div">
                                    <span class="emp-data" id="top_emp_catg"><s:property value="%{empCatgMap.get(salaryTranLoad.deductee_catg)}"/></span>
                                </div>
                            </div>
                            <div class="text-center px-0" style="min-width:10%"><label class="top-label">Emp Name: </label></div>
                            <div class="" style="min-width:10%">
                                <div class="empname-div">
                                    <span class="emp-data" id="top_emp_name"><s:property value="%{salaryTranLoad.deductee_name}"/></span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="clearfix"></div>
                <button type="button" class="btn btn-outline-secondary collapse-all-btn addon-btn filter-inner-btn px-2" data-toggle="collapse" id="all-section"
                        title="Click here to expand/collapse all sections" onclick="expandCollapseAll(this);">
                    <i class="fa fa-plus"></i></button>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="tab-content px-4 py-4">

            <!-----Notification Div----->
            <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--info p-1 my-1">
                        <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="notificationMsg">Please Fill Mandatory Fields</h5>
                    </div>
                </div>
            </div>

            <div class="text-center col-md-12" id="errorMsgDiv" style="display:none">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--error p-1 my-1">
                        <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="errorMsg">Some Error Occurred</h5>
                    </div>
                </div>
            </div>

            <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--success p-1 my-1">
                        <i class="fa fa-check mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="successMsg">Data Save Successfully</h5>
                    </div>
                </div>
            </div>
            <!-----Notification Div----->

            <form id="salaryEntryForm" method="POST">
                <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>
                <s:set name="formatYearBegDate"><s:date name="%{#assessment.getYearBegDate()}" format="dd-MM-yyyy"/></s:set>
                <s:set name="formatYearBegDateCal"><s:date name="%{#assessment.getYearBegDate()}" format="MM-dd-yyyy"/></s:set>
                <s:set name="formatYearEndDate"><s:date name="%{#assessment.getYearEndDate()}" format="dd-MM-yyyy"/></s:set>
                <s:set name="formatYearEndDateCal"><s:date name="%{#assessment.getYearEndDate()}" format="MM-dd-yyyy"/></s:set>
                <s:hidden id="yearBegDate" value="%{#formatYearBegDate}" />
                <s:hidden id="yearEndDate" value="%{#formatYearEndDate}" />
                <s:hidden id="yearBegDateCal" value="%{#formatYearBegDateCal}" />
                <s:hidden id="yearEndDateCal" value="%{#formatYearEndDateCal}" />

                <div class="single-form mt-3">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold"><span class="form-srno">1</span>Employee Details</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-container collapse in show" id="form3">

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Employee PAN (in capital letters) <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:hidden name="salaryTranLoad.rowid_seq" id="rowid_seq"/>
                                <s:hidden name="salaryTranLoad.month" id="month"/>
                                <input type="text" name="salaryTranLoad.deductee_panno" id="panno" class="form-control" autofocus value="<s:property value="%{salaryTranLoad.deductee_panno}"/>"
                                       maxlength="10"   placeholder="Enter Employee PAN No." onblur="get_verified_panno_status(this);validatePANSalaryNew(this);" style="text-transform: uppercase;">

                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Employee PAN No Valid <span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4"> 
                                <s:hidden name="salaryTranLoad.deductee_panno_valid" id="h_panno_valid"/>
                                <input type="text" name="deductee_panno_valid" id="panno_valid" class="form-control" autofocus value="<s:property value="%{salaryTranLoad.deductee_panno_valid != null ? (salaryTranLoad.deductee_panno_valid.equalsIgnoreCase('Y') ? 'Yes' : 'No') : ''}"/>"
                                       placeholder="PAN No. Valid Status" disabled>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Employee PAN No Verified <span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">   
                                <s:hidden name="salaryTranLoad.deductee_panno_verified" id="h_panno_verified"/>
                                <input type="text" name="deductee_panno_verified" id="panno_verified" class="form-control" autofocus value="<s:property value="%{salaryTranLoad.deductee_panno_verified != null ? (salaryTranLoad.deductee_panno_verified.equalsIgnoreCase('Y') ? 'Yes' : 'No') : ''}"/>"
                                       placeholder="PAN No. Verified Status" disabled>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Name of Employee <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.deductee_name" id="deductee_name" class="form-control" 
                                             placeholder="Enter Employee Name" onblur="setDefaultValue(this.value, top_emp_name, 'html');"
                                             cssStyle="text-transform: uppercase;"/>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Employee Category <span class="font-weight-bold text-danger ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:select  list="empCatgMap" id="deductee_catg" cssClass="form-control" headerKey="" headerValue="--Select--" 
                                           name="salaryTranLoad.deductee_catg"  onblur="setDefaultValue(this.options[this.selectedIndex].text, top_emp_catg, 'html');"/>
                            </div>
                        </div>
                        <div id="tax_flag">
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Whether opting for taxation u/s 115BAC [Yes/No] <span class="font-weight-bold text-danger ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:select list="taxFlag" headerKey="" headerValue="--Select--"  name="salaryTranLoad.tax_115bac_flag" id="tax_115bac_flag" class="form-control" 
                                              cssStyle="text-transform: uppercase;"/>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Employee Code 
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.identification_no" id="identification_no" class="form-control" placeholder="Enter Employee Code"
                                             cssStyle="text-transform: uppercase;"/>
                            </div>
                            <div class="col-lg-1 pl-0"> 
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" href="#employeeDetailsForm" data-toggle="collapse" title=""><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div class="collapse" id="employeeDetailsForm">

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Contact Number
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.deductee_phoneno" id="deductee_phoneno" class="form-control" placeholder="Enter Employee Contact Number" 
                                                 maxlength="10" onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Address 
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.deductee_address" id="deductee_address"  class="form-control" placeholder="Enter Employee Address" maxlength="25" cssStyle="text-transform: uppercase;"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Period of Employment From Date 
                                    </label> <span class="font-weight-bold text-danger ml-1">*</span>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:if test="%{salaryTranLoad.getSalary_from_date() != null}">
                                        <%--<s:set name="salFromDate"><s:date name="%{salaryTranLoad.salary_from_date}" format="dd-MMM-yyyy"/></s:set>--%>
                                        <s:set name="salFromDate"><s:property value="%{salaryTranLoad.salary_from_date}"/></s:set>
                                    </s:if>
                                    <s:else>
                                        <s:set name="salFromDate" value=""></s:set>
                                    </s:else>

                                    <div class="input-group">
                                        <s:textfield type="text" name="salaryTranLoad.salary_from_date" id="fromDate"  class="form-control" value="%{#salFromDate}"
                                                     placeholder="Select Salary From Date (DD-MON-YYYY)" onblur="lhsValidateDateOnBlur(this); fromDateChange();"/>
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="doOnLoad('fromDate', 'fromDateCal'); setFrom('fromDate', 'fromDateCal')">
                                                <i id="fromDateCal" class="fa fa-calendar"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Period of Employment To Date 
                                    </label> <span class="font-weight-bold text-danger ml-1">*</span>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <div class="input-group">
                                        <s:if test="%{salaryTranLoad.getSalary_to_date() != null}">
                                            <%--<s:set name="salToDate"><s:date name="%{salaryTranLoad.salary_to_date}" format="dd-MMM-yyyy"/></s:set>--%>
                                            <s:set name="salToDate"><s:property value="%{salaryTranLoad.salary_to_date}"/></s:set>
                                        </s:if>
                                        <s:else>
                                            <s:set name="salToDate" value=""></s:set>
                                        </s:else>
                                        <s:textfield type="text" name="salaryTranLoad.salary_to_date" id="toDate" class="form-control" placeholder="Select Salary To Date (DD-MON-YYYY)"
                                                     value="%{#salToDate}" onblur="lhsValidateDateOnBlur(this);" onkeypress="validateDateOnKeyDown(this, event);"/>
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="doOnLoad('toDate', 'toDateCal'); setTo('toDate', 'toDateCal')">
                                                <i id="toDateCal" class="fa fa-calendar"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold"><span class="form-srno">2</span>Income chargeable under head salaries (System calculated) (21-24)</label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#incomesubHead" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                <label class="show-total" id="afgr2_total_amt"><s:property value="%{salaryTranLoad.afgr2_total_amt !=null ? salaryTranLoad.afgr2_total_amt : 0.0}"/></label>
                                <s:hidden name="salaryTranLoad.afgr2_total_amt" id="h_afgr2_total_amt"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-container subform collapse" id="incomesubHead">
                        <div class="form-header-fixed mb-1">
                            <div class="row ">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">2(a)</span>Total income of salary</label>

                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form2a" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>
                                    <label class="show-total"  id="afg01_total_amt"><s:property value="%{salaryTranLoad.afg01_total_amt != null ? salaryTranLoad.afg01_total_amt : 0.00}"/></label>
                                    <s:hidden name="salaryTranLoad.afg01_total_amt" id="h_afg01_total_amt" />
                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form2a">

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Total Amount of Previous Employer Salary
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg01_pes_amt" id="afg01_pes_amt" class="form-control text-right" onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg01_total_amt, afg01_total_amt, afg01_pes_amt, afg01_ces_amt, afg01_vpce_amt, afg01_plsce_amt);" 
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        (a) Gross Salary of Current Employer 17(1)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg01_ces_amt" id="afg01_ces_amt" class="form-control text-right" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg01_total_amt, afg01_total_amt, afg01_pes_amt, afg01_ces_amt, afg01_vpce_amt, afg01_plsce_amt);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        (b) Value of perquisites of Current Employer 17(2)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>

                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg01_vpce_amt" id="afg01_vpce_amt" class="form-control text-right" 
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg01_total_amt, afg01_total_amt, afg01_pes_amt, afg01_ces_amt, afg01_vpce_amt, afg01_plsce_amt);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        (c) Profits in lieu of salary of Current Employer 17(3)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg01_plsce_amt" id="afg01_plsce_amt" class="form-control text-right" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg01_total_amt, afg01_total_amt, afg01_pes_amt, afg01_ces_amt, afg01_vpce_amt, afg01_plsce_amt);"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-header-fixed mb-1">
                            <div class="row">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">2(b)</span>Eligible Amount for Exemptions under Section - 10</label>
                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form2b" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>
                                    <label class="show-total"  id="afg05_total_amt"><s:property value="%{salaryTranLoad.afg05_total_amt != null ? salaryTranLoad.afg05_total_amt : 0.00}"/></label>
                                    <s:hidden name="salaryTranLoad.afg05_total_amt" id="h_afg05_total_amt" />
                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form2b">

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Travel Concession or Assistance 10(5)
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_tca_amt" id="afg05_tca_amt" class="form-control text-right" onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" 
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Death-cum-retirement Gratuity 10(10)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_drg_amt" id="afg05_drg_amt" class="form-control text-right" 
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Commuted Value of Pension 10(10A) <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_cvp_amt" id="afg05_cvp_amt" class="form-control text-right"
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Cash equivalent of leave salary encashment 10(10AA)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_celse_amt"  id="afg05_celse_amt" class="form-control text-right"
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        House rent Allowance 10(13A)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_hra_amt"  id="afg05_hra_amt" class="form-control text-right"
                                                 onblur="convertToINR(this.value, this);getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Amount of any other exemption under section 10 (Others)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg05_other_amt"  id="afg05_other_amt" class="form-control text-right"
                                                 onblur="convertToINR(this.value, this);
                                                 getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-header-fixed mb-1">
                            <div class="row">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">2(c)</span>Total amount for Deductions under Section 16</label>
                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form2c" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>
                                    <label class="show-total"  id="afg10_total_amt"><s:property value="%{salaryTranLoad.afg10_total_amt != null ? salaryTranLoad.afg10_total_amt : 0.00}"/></label>
                                    <s:hidden name="salaryTranLoad.afg10_total_amt" id="h_afg10_total_amt" />
                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form2c">

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Professional Tax 16(ii)
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg10_pt_amt" id="afg10_pt_amt" class="form-control text-right" 
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg10_total_amt, afg10_total_amt, afg10_pt_amt, afg10_eag_amt, afg10_sd_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Entertainment Allowance (Govt Employees) 16(iii)  <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg10_eag_amt" id="afg10_eag_amt" class="form-control text-right" onblur="convertToINR(this.value, this);
                                                 getGroup1_total_amt_a(h_afg10_total_amt, afg10_total_amt, afg10_pt_amt, afg10_eag_amt, afg10_sd_amt);" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Standard Deduction 16(ia) <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg10_sd_amt" id="afg10_sd_amt" class="form-control text-right" 
                                                 value="%{getText('{0,number,#,##0.00}',{salaryTranLoad.afg10_sd_amt})}"
                                                 onblur="convertToINR(this.value, this); getGroup1_total_amt_a(h_afg10_total_amt, afg10_total_amt, afg10_pt_amt, afg10_eag_amt, afg10_sd_amt);" 
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"/>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold"><span class="form-srno">3</span>Net income Chargeable under the head House Property</label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#thirdForm" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                <label class="show-total"  id="afg15_total_amt"><s:property value="%{salaryTranLoad.afg15_total_amt != null ? salaryTranLoad.afg15_total_amt : 0.00}"/></label>
                                <s:hidden name="salaryTranLoad.afg15_total_amt" id="h_afg15_total_amt" />
                            </div>
                        </div>
                    </div>
                    <div class="form-container subform collapse" id="thirdForm">

                        <div class="form-header-fixed mb-1">
                            <div class="row">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">3 (a)</span>Net income Chargeable under the head House Property</label>
                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form3a" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>                                
                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form3a">
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Rental Income offered for TDS 192 (2B) <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg15_riot_amt" id="afg15_riot_amt" class="form-control text-right"
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" 
                                                 onblur="convertToINR(this.value, this); getGroup2_total_amt(h_afg15_total_amt, afg15_total_amt, afg15_riot_amt, afg15_iphl_amt);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Interest Paid on Home Loans 24 <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg15_iphl_amt"  id="afg15_iphl_amt" class="form-control text-right"
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber1(event);" 
                                                 onblur="convertToINR(this.value, this); getGroup2_total_amt(h_afg15_total_amt, afg15_total_amt, afg15_riot_amt, afg15_iphl_amt);"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">4</span> Income From Other Sources/Capital Gains </label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#fourthForm" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                <label class="show-total"  id="afg20_ios_amt_main"><s:property value="%{salaryTranLoad.afg20_ios_amt != null ? salaryTranLoad.afg20_ios_amt : 0.00}"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-container subform collapse" id="fourthForm">
                        <div class="form-header-fixed mb-1">
                            <div class="row">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">4 (a)</span>Income From Other Sources/Capital Gains</label>
                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form4a" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>

                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form4a">
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Income from Other Sources offered for TDS 192 (2B) <span class="font-weight-bold text-danger ml-1"></span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg20_ios_amt" id="afg20_ios_amt" class="form-control text-right"
                                                 placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); 
                                                 getGroup3_total_amt('',afg20_ios_amt_main,afg20_ios_amt);"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold"><span class="form-srno">5</span>Gross Total Income</label>

                                <label class="show-total" id="afgr3_total_amt"><s:property value="%{salaryTranLoad.afgr3_total_amt != null ? salaryTranLoad.afgr3_total_amt :  0.0 }"/></label>
                                <s:hidden name="salaryTranLoad.afgr3_total_amt" id="h_afgr3_total_amt"/>
                            </div>
                        </div>
                    </div> 

                </div>

                <!-----Form 6--- start----->
                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">6</span>  Total Taxable Income, Subject to Limits under Chapter VI-A </label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#sixthForm" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                    <%--<label class="show-total" id="afg25_total_gross_amt"><s:property value="%{salaryTranLoad.afg25_total_gross_amt != null ? salaryTranLoad.afg25_total_gross_amt :  0.00 }"/></label>--%>
                                <label class="show-total" id="afg26_total_deduct_amt"><s:property value="%{salaryTranLoad.afg26_total_deduct_amt != null ? salaryTranLoad.afg26_total_deduct_amt : 0.00}"/></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-container collapse" id="sixthForm">

                        <div class="row form-group mb-2">
                            <div class="col-lg-4 col-xl-4 text-right"></div>
                            <div class="col-lg-2 col-xl-2 text-center">
                                <label class="sub-form-title">Gross Amount</label>
                            </div>
                            <div class="col-lg-2 col-xl-2 text-center">
                                <label class="sub-form-title">Deductable Amount</label>
                            </div>
                            <div class="col-lg-2 col-xl-2 text-center">
                                <label class="sub-form-title">Qualify Amount</label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Life Insurance premium, Contibution to Provident Fund etc. 80C <span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_lic_gross_amt" id="gross_amt-1" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',1);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_lic_deduct_amt" id="deduct_amt-1" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',1);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <%--<s:textfield type="text" name="" id="qualify_amt-1" class="form-control text-right"--%>
                                <!--placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify',1);"/>-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Contribution to certain pension funds. 80CCC <span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_ccpf_gross_amt" id="gross_amt-2" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',2);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_ccpf_deduct_amt" id="deduct_amt-2" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',2);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <%--<s:textfield type="text" name="" id="qualify_amt-2" class="form-control text-right"--%>
                                <!--placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify',2);"/>-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Contribution by taxpayer to notified pension scheme. 80CCD(1) <span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_ctnps_gross_amt" id="gross_amt-3" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',3);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_ctnps_deduct_amt" id="deduct_amt-3" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',3);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <!--<input type="text" name=""  id="qualify_amt-3" class="form-control text-right" placeholder="Amount in Rs."--> 
                                <!--onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify', 3);">-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Amount paid/deposited under notified pension scheme. 80CCD(1B)<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_adnps_gross_amt" id="gross_amt-4" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('gross',4);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_adnps_deduct_amt" id="deduct_amt-4" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',4);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <!--<input type="text" name=""  id="qualify_amt-4" class="form-control text-right" placeholder="Amount in Rs."--> 
                                <!--onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify', 4);">--> 
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Contribution by employer to notified pension scheme. 80CCD(2)<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_ecnps_gross_amt" id="gross_amt-5" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('gross',5);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_ecnps_deduct_amt" id="deduct_amt-5" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('deduct',5);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <!--<input type="text" name=""  id="qualify_amt-5" class="form-control text-right" placeholder="Amount in Rs."--> 
                                <!--onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify', 5);">-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Health Insurance premium.80D<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_hip_gross_amt" id="gross_amt-6" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('gross',6);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_hip_deduct_amt" id="deduct_amt-6" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('deduct',6);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <!--<input type="text" name=""  id="qualify_amt-6" class="form-control text-right" placeholder="Amount in Rs."--> 
                                <!--onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify', 6);">-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Interest on loan taken for higher education.80E<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_ilhe_gross_amt" id="gross_amt-7" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',7);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_ilhe_deduct_amt" id="deduct_amt-7" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',7);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <!--<input type="text" name=""  id="qualify_amt-7" class="form-control text-right" placeholder="Amount in Rs."--> 
                                <!--onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify', 7);">-->
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Donation to certain funds, charitable institutions,etc.80G<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_dcf_gross_amt" id="gross_amt-8" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',8);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_dcf_deduct_amt" id="deduct_amt-8" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('deduct',8);"/>
                            </div>

                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg27_dcf_qualify_amt" id="qualify_amt-8" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify',8);"/>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Interest on deposits in savings account.80TTA<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_idsa_gross_amt" id="gross_amt-9" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('gross',9);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_idsa_deduct_amt" id="deduct_amt-9" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('deduct',9);"/>
                            </div>

                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg27_idsa_qualify_amt" id="qualify_amt-9" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify',9);"/>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Amounts deductible under any other provision(s) of Chapter VI-A. Others<span class="font-weight-bold text-danger ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_other_gross_amt" id="gross_amt-10" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('gross',10);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_other_deduct_amt" id="deduct_amt-10" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);  getGroup5_amt_total('deduct',10);"/>
                            </div>

                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg27_other_qualify_amt" id="qualify_amt-10" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this); getGroup5_amt_total('qualify',10);"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Total
                                </label>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg25_total_gross_amt" id="gross_amt_total" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);"/>
                            </div>
                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg26_total_deduct_amt" id="deduct_amt_total" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);"/>
                            </div>

                            <div class="col-lg-2 col-xl-2">
                                <s:textfield type="text" name="salaryTranLoad.afg27_total_qualify_amt" id="qualify_amt_total" class="form-control text-right"
                                             placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);" onblur="convertToINR(this.value, this);"/>
                            </div>
                        </div>                  
                    </div>
                </div>

                <!-----Form 6--- end----->

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">7</span>  Total Taxable Income, Subject to Limits under Chapter VI-A </label>
                                <label class="show-total" id="afgr4_total_amt"><s:property value="%{salaryTranLoad.afgr4_total_amt != null ? salaryTranLoad.afgr4_total_amt :  0.00 }"/></label>
                                <s:hidden name="salaryTranLoad.afgr4_total_amt" id="h_afgr4_total_amt" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold"><span class="form-srno">8</span>Tax before Adjustments</label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form7" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                <label class="show-total" id="afg30_total_amt_main"><s:property value="%{salaryTranLoad.afg30_total_amt != null ? salaryTranLoad.afg30_total_amt : 0.00}"/></label>
                                <s:hidden name="salaryTranLoad.afg30_total_amt" id="afg30_total_amt"/>

                            </div>
                        </div>
                    </div>
                    <div class="form-container subform collapse" id="form7">
                        <div class="form-header-fixed mb-1">
                            <div class="row">
                                <div class="col-md-12 text-left">
                                    <label class="form-label font-weight-bold text-green"><span class="form-srno">8(a)</span>Back-end funtion to calculate TDS/SUR/CES</label>
                                    <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form7a" data-toggle="collapse" title="">
                                        <i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="form-container collapse" id="form7a">

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Income Tax on Total Income
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg30_tds_amt" id="afg30_tds_amt"  class="form-control text-right" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"
                                                 onblur="convertToINR(this.value, this);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Surcharge
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <s:textfield type="text" name="salaryTranLoad.afg30_sur_amt" id="afg30_sur_amt"  class="form-control text-right" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"
                                                 onblur="convertToINR(this.value, this);"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4 text-right">
                                    <label class="control-label">
                                        Education Cess
                                    </label>
                                </div>
                                <div class="col-lg-4 col-xl-4">
                                    <!--<div class="input-group">-->
                                    <s:textfield type="text" name="salaryTranLoad.afg30_ces_amt" id="afg30_ces_amt"  class="form-control text-right" placeholder="Amount in Rs." onkeypress="return lhsIsNumber(event);"
                                                 onblur="convertToINR(this.value, this);" />
                                    <!--                                        <div class="input-group-append">
                                                                                <button type="button" class="btn btn-primary btn-sm addon-btn" id="calculateBtn" onclick="getGroup6_total_amt(this);">Auto Calculate</button>
                                                                            </div>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">9</span>Less : Rebate and Reliefs</label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#form8" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                    <s:if test="%{salaryTranLoad.afg35_rus_amt != null && salaryTranLoad.afg40_itr_amt != null}">
                                    <label class="show-total" id="rebate_and_relief_total_amt"><s:property value="%{salaryTranLoad.afg35_rus_amt+salaryTranLoad.afg40_itr_amt}"/></label>
                                </s:if>
                                <s:elseif test="%{salaryTranLoad.afg35_rus_amt != null && salaryTranLoad.afg40_itr_amt == null}">
                                    <label class="show-total" id="rebate_and_relief_total_amt"><s:property value="%{salaryTranLoad.afg35_rus_amt}"/></label>
                                </s:elseif>
                                <s:elseif test="%{salaryTranLoad.afg40_itr_amt != null && salaryTranLoad.afg35_rus_amt == null}">
                                    <label class="show-total" id="rebate_and_relief_total_amt"><s:property value="%{salaryTranLoad.afg40_itr_amt}"/></label>
                                </s:elseif>
                                <s:else>
                                    <label class="show-total" id="rebate_and_relief_total_amt">0.0</label>
                                </s:else>

                            </div>
                        </div>
                    </div>
                    <div class="form-container collapse" id="form8">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Income Tax Relief u/s 89 when salary etc is paid in arrear or advance 89
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.afg35_rus_amt" id="afg35_rus_amt" class="form-control text-right" placeholder="Amount in Rs."
                                             onblur="convertToINR(this.value, this);"  />
                                <!--getGroup7_total_amt(rebate_and_relief_total_amt,afg35_rus_amt,afg40_itr_amt);--> 
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Rebate under section 87A, if applicable 87A
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.afg40_itr_amt" id="afg40_itr_amt"  class="form-control text-right" placeholder="Amount in Rs."
                                             onblur="convertToINR(this.value, this);"/>
                                <!--getGroup7_total_amt(rebate_and_relief_total_amt,afg35_rus_amt,afg40_itr_amt);--> 
                            </div>
                        </div>





                    </div>
                </div>  
                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">10</span>Net Income Tax Payable/(refundable) including Surcharge and H&E Cess</label>
                                <label class="show-total" id="afgr5_total_amt"><s:property value="%{salaryTranLoad.afgr5_total_amt != null ? salaryTranLoad.afgr5_total_amt : 0.0}"/></label>
                                <s:hidden name="salaryTranLoad.afgr5_total_amt" id="h_afgr5_total_amt"/>
                            </div>
                        </div>
                    </div>

                </div> 
                <div class="single-form">
                    <div class="form-header-fixed">
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label class="form-label font-weight-bold mr-2"><span class="form-srno">11</span>Shortfall in tax deduction (+)/Excess tax deduction(-)</label>
                                <button type="button" class="expandable btn btn-outline-secondary addon-btn filter-inner-btn px-2" style="float: right;" href="#tenthform" data-toggle="collapse" title="">
                                    <i class="fa fa-plus"></i></button>
                                <label class="show-total" id="afg46_sftd_amt"><s:property value="%{salaryTranLoad.afg46_sftd_amt != null ? salaryTranLoad.afg46_sftd_amt : 0.00}"/></label>
                                <s:hidden name="salaryTranLoad.afg46_sftd_amt" id="h_afg46_sftd_amt"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-container collapse" id="tenthform">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Total TDS for the whole year by current employer
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.afg45_ttce_amt" id="afg45_ttce_amt"  class="form-control text-right" placeholder="Amount in Rs." 
                                             onblur="getGroup9_total_amt(); convertToINR(this.value, this);"/>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Total TDS for the whole year by previous employer
                                </label>
                            </div>
                            <div class="col-lg-4 col-xl-4">
                                <s:textfield type="text" name="salaryTranLoad.afg45_ttpe_amt" id="afg45_ttpe_amt"  class="form-control text-right" placeholder="Amount in Rs."
                                             onblur="getGroup9_total_amt(); convertToINR(this.value, this);"/>
                            </div>
                        </div>





                    </div>
                </div> 
            </form>
            <div class="col-lg-12 col-xl-12 text-center">
                <s:hidden id="formAction" value="%{action}"/>
                <button type="button" class="form-btn" id="validateButton" disabled="disabled" onclick="callValidateFunction(this);"><i class="fa save" aria-hidden="true"></i>Validate</button>
                <button type="button" class="form-btn" id="saveButton" onclick="callSalarySaveFunction();" >
                    <i class="fa save" aria-hidden="true"></i><s:property value="%{action.equalsIgnoreCase('save') ? 'Save' : 'Update'}"/>
                </button>
                <s:if test="%{action.equalsIgnoreCase('save')}">
                    <button type="button" class="form-btn" onclick="resetSalaryForm();"><i class="fa refresh" aria-hidden="true"></i>Reset</button>
                </s:if>
                <button type="button" class="form-btn" onclick="callUrl('/salaryBrowseNew');"><i class="fa back" aria-hidden="true"></i>Back</button>

            </div>   
        </div>
    </div>
</div>
<script type = "text/javascript">
//    var rowid_seq_value = document.getElementById("rowid_seq").value;
//    if (rowid_seq_value !== null && rowid_seq_value === '') {
//        document.getElementById("tax_flag").style.display = "none";
//    } else {
//        document.getElementById("tax_flag").style.display = "block";
//    }

</script>
<script type = "text/javascript" src="resources/js/regular/salaryNew/salaryNew.js?r=<%=Math.random()%>" ></script>
<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>" ></script>