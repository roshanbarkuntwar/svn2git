<%-- 
    Document   : CIN
    Created on : 21 Apr, 2020, 11:48:30 AM
    Author     : sneha.parpelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">

<script type="text/javascript" src="resources/js/global/calendar/dhtmlxcommon.js"></script>
<script type="text/javascript" src="resources/js/global/calendar/dhtmlxcalendar.js"></script>

<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/regular/miscCin/miscCin.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/global/template.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>


<div class="page-section">


    <s:set var="userMast" value="%{#session.get('LOGINUSER')}"/>
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="#browse"><p>Misc Transaction Browse</p> </a></li>
            <li onclick="callEntryPage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Entry</p></a></li>
                <s:if test="%{#userMast.approve_right != null && #userMast.approve_right == 1}">
                <li onclick="callCheckerMakerPage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Authorization</p></a></li>
                </s:if>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>

    <body>



        <form id="miscCinForm" autocomplete="off">
            <div class="tab-content px-4 py-4">
                <div id="browse" class="tab-pane fade">

                </div>

                <div id="entry" class="tab-pane show in active">
                    <div class="container-fluid pt-2">

                        <div class="text-center col-md-12" id="errorMsgDiv" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--info p-1 my-1">
                                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="errorMsg"></h5>
                                </div>
                            </div>
                        </div>

                        <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--info p-1 my-1">
                                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="notificationMsg"></h5>
                                </div>
                            </div>
                        </div>

                        <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--success p-1 my-1">
                                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="successMsg"></h5>
                                </div>
                            </div>
                        </div>




                        <!--<hr class="dashed-border-bottom">-->
                        <div class="row form-group pt-2">
                            <s:hidden name="tdsMiscTranLoad.rowid_seq" id="rowid_seq" value="%{tdsMiscTranLoad.rowid_seq}"/>
                            <s:hidden name="miscData.formMode" id="actionFlag" value="%{actionFlag}"/>
                            <div class="col-lg-2">
                                <label class="control-label">
                                    Branch Code<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.client_code" id="client_code" class="form-control" value="<s:property value="%{clientMast.bank_branch_code}"/>"
                                       placeholder="Enter Branch Code." readonly>

                            </div>


                            <div class="col-lg-2">
                                <label class="control-label">
                                    TAN No.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>

                            <div class="col-lg-2">
                                <input type="text" name="miscData.client_tanno" id="client_tanno" maxlength="10" class="form-control" value="<s:property value="%{clientMast.tanno}"/>"
                                       placeholder="Enter TAN No." onblur="validateTANNO(this);" style="text-transform: uppercase;" readonly>

                            </div>  

                            <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>
                            <s:hidden id="tdsTypeCode" value="%{#assessment.getTdsTypeCode()}"/>
                            <div class="col-lg-2">
                                <label class="control-label">
                                    Month<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <select class="form-control" name="miscData.month" id="monthlist" autofocus onchange="setDateOfTaxDeduction(this.value);">
                                    <option value="">Select Month</option>
                                    <s:if test="#assessment.getQuarterNo() == 1">
                                        <option value="APR" <s:if test="%{month == 'APR'}">selected</s:if>>APR</option>
                                        <option value="MAY" <s:if test="%{month == 'MAY'}">selected</s:if>>MAY</option>
                                        <option value="JUN" <s:if test="%{month == 'JUN'}">selected</s:if>>JUN</option>
                                    </s:if>
                                    <s:if test="#assessment.getQuarterNo() == 2">
                                        <option value="JUL" <s:if test="%{month == 'JUL'}">selected</s:if>>JUL</option>
                                        <option value="AUG" <s:if test="%{month == 'AUG'}">selected</s:if>>AUG</option>
                                        <option value="SEP" <s:if test="%{month == 'SEP'}">selected</s:if>>SEP</option>
                                    </s:if>
                                    <s:if test="#assessment.getQuarterNo() == 3">
                                        <option value="OCT" <s:if test="%{month == 'OCT'}">selected</s:if>>OCT</option>
                                        <option value="NOV" <s:if test="%{month == 'NOV'}">selected</s:if>>NOV</option>
                                        <option value="DEC" <s:if test="%{month == 'DEC'}">selected</s:if>>DEC</option>
                                    </s:if>
                                    <s:if test="#assessment.getQuarterNo() == 4">
                                        <option value="JAN" <s:if test="%{month == 'JAN'}">selected</s:if>>JAN</option>
                                        <option value="FEB" <s:if test="%{month == 'FEB'}">selected</s:if>>FEB</option>
                                        <option value="MAR" <s:if test="%{month == 'MAR'}">selected</s:if>>MAR</option>
                                    </s:if>
                                </select>
                            </div>            
                        </div>
                        <div class="row form-group">


                            <div class="col-lg-2">
                                <label class="control-label">
                                    Name Of Deductee<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.deductee_name" id="deductee_name" 
                                       onkeypress="return lhsValidateAlphaNumericWithoutSpclChars(event);" maxlength="50" class="form-control" value="<s:property value="%{tdsMiscTranLoad.deductee_name}"/>"
                                       placeholder="Enter Deductee Name." style="text-transform: uppercase;">

                            </div>


                            <div class="col-lg-2">
                                <label class="control-label">
                                    PAN Of Deductee<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>

                            <div class="col-lg-2">
                                <div class="input-group">
                                    <input type="text" name="miscData.deductee_panno" id="deductee_panno" class="form-control" maxlength="10" value="<s:property value="%{tdsMiscTranLoad.deductee_panno}"/>"
                                           placeholder="Enter PAN No." onblur="getPanStatus(this);" style="text-transform: uppercase;">
                                    <div class="input-group-append">
                                        <div type="button" class="btn btn-primary addon-btn" title="PANNOTAVBL">
                                            <input type="checkbox" style="margin-top: 5px;"  id="checkbox" onclick="getMiscPanNotAvailble(event);">
                                        </div>
                                    </div>

                                </div>  
                            </div>  


                            <div class="col-lg-2">
                                <label class="control-label">
                                    PAN Status<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" readonly= "true" id="pan_status" value="<s:property value="%{pan_status}"/>" class="form-control" maxlength="10">
                            </div>           
                        </div>
                        <div class="row form-group">

                            <div class="col-lg-2">
                                <label class="control-label">
                                    Transaction ID<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.tran_ref_no" id="transactionID" class="form-control" value="<s:property value="%{tdsMiscTranLoad.tran_ref_no}"/>"
                                       readonly>
                            </div>           




                            <div class="col-lg-2">
                                <label class="control-label">
                                    Invoice Date<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>

                            <div class="col-lg-2">
                                <div  class="input-group">
                                    <input type="text" class="form-control" id="tran_ref_date" placeholder="DD-MM-YYYY" onblur="validateDateOnBlur(this);" name="miscData.invoice_date" value="<s:property value="%{tdsMiscTranLoad.invoice_date}"/>"
                                           >
                                    <!--onblur="checkSelectedMonthDate(this);"-->
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary addon-btn" id="fromDateBtn" onmouseover="openTaxCalendar('tran_ref_date', 'fromDateCal');">
                                            <i class="fa fa-calendar" id="fromDateCal"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>



                            <div class="col-lg-2">
                                <label class="control-label">
                                    Invoice No.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.invoice_no" id="deductee_ref_code1" maxlength="30" class="form-control" value="<s:property value="%{tdsMiscTranLoad.invoice_no}"/>"
                                       style="text-transform: uppercase;" placeholder="Enter Invoice No."/>

                            </div>          
                        </div>
                        <div class="row form-group">

                            <div class="col-lg-2">
                                <label class="control-label">
                                    BGL Code<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>

                            <div class="col-lg-2">
                                <s:select id="bglCode" list="bglCodeNameList" class="form-control" name="miscData.bgl_code"
                                          onChange="setBGLSectionRate(this.value);" value="edit_bgl_code" onblur="setBGLSectionRate(this.value);"/>

                            </div>             


                            <div class="col-lg-2">
                                <label class="control-label">
                                    Section ID<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">

                                <s:select class="form-control" list="selectSectionList" id="tds_code" 
                                          name="miscData.tds_code" cssClass="form-control" 
                                          cssStyle="width:100%;" 
                                          onchange="getTdsRateAndThresholdLimit();" value="%{edit_section_code}"/>
                            </div>



                            <div class="col-lg-2">
                                <label class="control-label">
                                    TDS Applicable Flag<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <select id="tds_applicable_flag" class="form-control" name="miscData.tds_trantype" onchange="getRateAndTotalAmt();">
                                    <!--<option value="">--Select--</option>-->
                                    <option value="YES" <s:if test="%{tdsMiscTranLoad.tds_trantype != null && tdsMiscTranLoad.tds_trantype.equalsIgnoreCase('Y')}">selected</s:if>>Yes</option>
                                    <option value="NO" <s:if test="%{tdsMiscTranLoad.tds_trantype != null && tdsMiscTranLoad.tds_trantype.equalsIgnoreCase('N')}">selected</s:if>>No</option>
                                    </select>
                                </div>

                            </div>



                            <div class="row form-group" id="lastDiv" style="visibility: <s:if test="%{tdsTypeCode == '27Q'}">visible</s:if><s:else>hidden</s:else>;">
                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Nature of Remittance<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                <s:select class="form-control" list="selectNatureOfRemList" id="nature_of_remittance" 
                                          name="miscData.nature_of_remittance" cssClass="form-control" cssStyle="width:100%;" 
                                          value="natureRem" onchange="getTdsRateAndThresholdLimit();"/>
                            </div>

                            <div class="col-lg-2">
                                <label class="control-label">
                                    Rate Type<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <s:select class="form-control" list="selectRateList" id="rate_type" 
                                          name="miscData.rate_type" cssClass="form-control" cssStyle="width:100%;" 
                                          value="rateType" onchange="getTdsRateAndThresholdLimit();"/>
                            </div>

                            <div class="col-lg-2">
                                <label class="control-label">
                                    Country<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <s:select class="form-control" list="selectCountryList" id="country_code" 
                                          name="miscData.country_code" cssClass="form-control" cssStyle="width:100%;" 

                                          value="country" onchange="getTdsRateAndThresholdLimit();"/>

                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-2">
                                <label class="control-label">
                                    TDS Rate<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.tds_rate" id="tds_rate" class="form-control" value="<s:property value="%{tdsMiscTranLoad.tds_rate}"/>"
                                       readonly>
                            </div>

                            <div class="col-lg-2">
                                <label class="control-label">
                                    Total Paid Amt.<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" readonly="true" id="paid_amt" class="form-control" onkeypress="return isNumberWithDecimal(event);" value="<s:property value="%{tdsMiscTranLoad.tds_rate}"/>"
                                       >
                            </div>


                            <div class="col-lg-2">
                                <label class="control-label">
                                    Total TDS Deducted<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>

                            <div class="col-lg-2">
                                <input type="text" readonly="true" id="tds_deducted" class="form-control" onkeypress="return isNumberWithDecimal(event);" value="<s:property value="%{tdsMiscTranLoad.tds_rate}"/>"
                                       >
                            </div>         
                        </div>

                        <div class="row form-group">
                            <!--new--> 



                            <div class="col-lg-2">
                                <label class="control-label">
                                    Threshold Limit<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" readonly="true" id="threshold_limit" class="form-control" onkeypress="return isNumberWithDecimal(event);" value="<s:property value="%{tdsMiscTranLoad.tds_rate}"/>"
                                       >
                            </div>              
                            <div class="col-lg-2">
                                <label class="control-label">
                                    Basic Payment Amt<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.tran_amt" id="tran_amt" onkeypress="return isNumberWithDecimal(event);" class="form-control" value="<s:property value="%{tdsMiscTranLoad.tran_amt}"/>"
                                       placeholder="Enter Basic Payment Amt." onblur="getRateAndTotalAmt();">

                            </div>

                            <div class="col-lg-2">
                                <label class="control-label">
                                    CGST Amt.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.cgst_amt" id="cgst_amt" onkeypress="return isNumberWithDecimal(event);" class="form-control" value="<s:property value="%{tdsMiscTranLoad.cgst_amt}"/>"
                                       placeholder="Enter CGST Amt." onblur="checkGSTValues(this.id);">

                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-2">
                                <label class="control-label">
                                    SGST Amt.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.sgst_amt" id="sgst_amt" onkeypress="return isNumberWithDecimal(event);" class="form-control" value="<s:property value="%{tdsMiscTranLoad.sgst_amt}"/>"
                                       placeholder="Enter SGST Amt." onblur="checkGSTValues(this.id);">

                            </div>
                            <div class="col-lg-2">
                                <label class="control-label">
                                    IGST Amt.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.igst_amt" id="igst_amt" onkeypress="return isNumberWithDecimal(event);" class="form-control" value="<s:property value="%{tdsMiscTranLoad.igst_amt}"/>"
                                       placeholder="Enter IGST Amt." onblur="checkGSTValues(this.id);" <s:if test="%{actionFlag=='editFlag' && (tdsMiscTranLoad.igst_amt == null || tdsMiscTranLoad.igst_amt == 0)}">readonly</s:if>>

                            </div>
                            <div class="col-lg-2">
                                <label class="control-label">
                                    Total Amt.<span class="text-danger font-weight-bold ml-1"></span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.total_g_b_amt" id="total_amt" class="form-control" value="<s:property value="%{tdsMiscTranLoad.total_g_b_amt}"/>"
                                       readonly>

                            </div>
                        </div>
                        <div class="row form-group">

                            <div class="col-lg-2">
                                <label class="control-label">
                                    TDS Amt.<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.tds_amt" id="tds_amt" class="form-control" onkeypress="return isNumberWithDecimal(event);" value="<s:property value="%{tdsMiscTranLoad.tds_amt}"/>"
                                       readonly>

                            </div>

                            <div class="col-lg-2 pr-0">
                                <label class="control-label">
                                    Lower/Nil Deduction(If Any)
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <s:select class="form-control" list="selectTdsDeductReasonList" id="tds_deduct_reason" 
                                          name="miscData.tds_deduct_reason" cssClass="form-control" cssStyle="width:100%;" 
                                          value="tdsDeductReason" onchange="setCertificateRequired(this.value);"
                                          headerKey="" headerValue="Select Section"/>

                            </div>
                        
                            <div class="col-lg-2">
                                <label class="control-label">
                                    Certificate No.<span class="text-danger font-weight-bold ml-1" style="visibility: hidden;" id="certRequired">*</span>
                                    <s:hidden id="certRequiredFlag" value="false"/>
                                </label>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" name="miscData.certificate_no" id="certificate_no" maxlength="10" class="form-control" onkeypress="return lhsValidateAlphaNumericWithoutSpclChars(event);" value="<s:property value="%{tdsMiscTranLoad.certificate_no}"/>"
                                       placeholder="Enter Certificate No." onblur="getRateAndTotalAmt();">

                            </div>

                        </div>
                        <%--<div id="challanDiv" style="display:<s:if test="%{edit_bgl_code != null}"> none;</s:if><s:elseif test="%{edit_bgl_code != null}"> none;</s:elseif>">--%>
                        <div id="challanDiv" style="display: none;">
                            <s:hidden value="%{challanTranLoad.rowid_seq}" name="miscData.tds_challan_rowid_seq"/>
                            <hr class="dashed-border-bottom">
                            <div class="row form-group">
                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Challan BSR Code<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" name="miscData.tds_challan_bank_bsr_code" id="tds_challan_bank_bsr_code" maxlength="7" onkeypress="return lhsIsNumber(event);" class="form-control" value="<s:property value="%{challanTranLoad.bank_bsr_code}"/>"
                                           placeholder="Enter Challan BSR Code.">
                                </div>
                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Challan Date<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                    <div  class="input-group">
                                        <input type="text" class="form-control" id="tds_error_status5" placeholder="Select Date" name="miscData.tds_error_status5" value="<s:property value="%{challanTranLoad.tds_challan_date}"/>">
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn" id="toDateBtn" onmouseover="openCalendar('tds_error_status5', 'toDateCal');">
                                                <i class="fa fa-calendar" id="toDateCal"></i>
                                            </button>
                                        </div>

                                    </div>
                                </div>

                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Challan No.<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" name="miscData.tds_error_status6" id="tds_error_status6" maxlength="5" onkeypress="return lhsIsNumber(event);" class="form-control" value="<s:property value="%{challanTranLoad.tds_challan_no}"/>"
                                           placeholder="Enter Challan No.">

                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Challan Amount<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" name="miscData.tds_error_status7" id="tds_error_status7" onkeypress="return isNumberWithDecimal(event);" class="form-control" value="<s:property value="%{challanTranLoad.tds_challan_tds_amt}"/>"
                                           placeholder="Enter Challan Amount.">
                                </div>


                                <div class="col-lg-2">
                                    <label class="control-label">
                                        Challan TAN<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-2">
                                    <input type="text" name="miscData.inst_no" id="tds_error_status8" class="form-control"
                                           placeholder="Enter Challan TAN" value="<s:property value="%{challanTranLoad.inst_no}"/>">
                                </div>          
                            </div>
                        </div>


                        <!--<hr class="dashed-border-bottom">-->                 

                        <hr class="dashed-border-bottom" id="bottomLine" style="visibility: <s:if test="%{tdsTypeCode == '27Q'}">visible</s:if><s:else>hidden</s:else>;">
                            <div class="col-md-12">
                                <div class="button-section col-md-12 my-1 text-center"> 
                                <s:if test="actionFlag=='deleteFlag'">
                                    <button type="button" class="form-btn" onclick="deleteMiscCinRecord(rowid_seq)"><i class="fa delete" aria-hidden="true"></i>Delete</button>
                                </s:if>
                                <s:elseif test="actionFlag=='editFlag'">
                                    <button type="button" class="form-btn" onclick="updateMiscCinRecord(rowid_seq)"><i class="fa edit" aria-hidden="true"></i>Update</button>
                                </s:elseif>
                                <s:else>
                                    <button type="button" onclick="saveMiscCINDetails('new');" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save & Continue</button>
                                    <button type="button" onclick="saveMiscCINDetails('exit');" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save & Exit</button>
                                </s:else>  
                                <button type="button" class="form-btn" onclick="goToDashboard()"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>                
    <script type="text/javascript">
        $(document).ready(function () {
            // getTdsDeductReason();
            // getRemittanceNature();
            //getTdsRateTypeValues();
            //getCountryCodeValues();
            //setTransactionID();
            setCurrentMonth();
        });

        function getCurrentMonthName() {
            let month = new Date().toLocaleString('default', {month: 'long'});
            return month.toUpperCase();
        }//end

        function setCurrentMonth() {
            var actionFlag = document.getElementById("actionFlag").value;
            if (lhsIsNull(actionFlag)) {
                let month = getCurrentMonthName().substring(0, 3).toUpperCase();

                var months = document.getElementById("monthlist");
                let optionsArr = months.options;
                for (let i = 0; i < optionsArr.length; i++) {
                    var option1 = optionsArr[i];

                    if (option1.value.toUpperCase() === month) {

                        $('#monthlist').val(month);
                        break;
                    }
                }
            }
        }// end 
        function setDateOfTaxDeduction(selectedMonth) {
            let month = null;
            if (!lhsIsNull(selectedMonth)) {
                month = selectedMonth;
            } else {
                month = getCurrentMonthName().substring(0, 3).toUpperCase();
            }
        }//end 
    </script>

