<%-- 
    Document   : transactionEntry
    Created on : Jan 28, 2019, 12:47:17 PM
    Author     : ayushi.jain
--%>
<%@page import="globalUtilities.Util"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/transaction/transaction.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js"></script>
<!--<script src="resources/js/global/bootstrap-datetimepicker.js"></script>-->
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/regular/transaction/transaction.js?r=<%=Math.random()%>"></script>
<script src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>"></script>
<style>
    .count-container label {font-size:13px}
    .ui-dialog[aria-describedby='dialog-confirm_certificate_allo'] {z-index:1056 !important;}
    .ui-dialog[aria-describedby='dialog-confirm_certificate_unallo'] {z-index:1056 !important;}
</style>
<% Util utl = new Util();%>
<div class="page-section">


    <s:hidden id="mapFlag" name="mapFlag" value="%{mapFlag}"/>
    <s:hidden id="mapTDSChallanAmt" name="mapTDSChallanAmt" value="%{mapTDSChallanAmt}"/>
    <s:hidden id="mapChallanRowSeq" name="mapChallanRowSeq" value="%{mapChallanRowSeq}"/>
    <s:hidden id="mapAllocatedAmount" name="mapAllocatedAmount" value="%{mapAllocatedAmount}"/>
    <s:hidden id="tdsChallanDate" name="tdsChallanDate" value="%{tdsChallanDate}"/>
    <s:hidden id="tdsChallanNo" name="tdsChallanNo" value="%{tdsChallanNo}"/>
    <s:hidden id="tdsAmount" name="tdsAmount" value="%{tdsAmount}"/>
    <s:hidden id="mapBalanceAmountId" name="balanceAmount" value="%{balanceAmount}"/>
    <s:hidden id="pageNumberId" name="pageNumber" value="%{pageNumber}"/>
    <s:hidden name="AllocateDeducteeFlag" value="%{AllocateDeducteeFlag}"/>

    <s:set name="formatYearBegDate"><s:date name="%{#assessment.getQuarterFromDate()}" format="dd-MM-yyyy"/></s:set>
    <s:set name="formatYearBegDateCal"><s:date name="%{#assessment.getQuarterFromDate()}" format="MM-dd-yyyy"/></s:set>
    <s:set name="formatYearEndDate"><s:date name="%{#assessment.getQuarterToDate()}" format="dd-MM-yyyy"/></s:set>
    <s:set name="formatYearEndDateCal"><s:date name="%{#assessment.getQuarterToDate()}" format="MM-dd-yyyy"/></s:set>
    <s:hidden id="yearBegDate" value="%{#formatYearBegDate}" />
    <s:hidden id="yearEndDate" value="%{#formatYearEndDate}" />
    <s:hidden id="yearBegDateCal" value="%{#formatYearBegDateCal}" />
    <s:hidden id="yearEndDateCal" value="%{#formatYearEndDateCal}" />
    <input type="hidden" id="hTdsDeductReason" value="<s:property value="tdsTranLoad.tds_deduct_reason"/>" /> 

    <s:hidden id="h_tds_code" name="tdsTranLoad.tds_code"/>
    <s:hidden id="h_country_code" name="tdsTranLoad.country_code"/>
    <s:hidden id="htran_ref_date" name="tdsTranLoad.tran_ref_date"/>
    <s:hidden id="htran_deduct_date" name="tdsTranLoad.tds_deduct_date"/>


    <s:if test="%{action.equalsIgnoreCase('add')}">
        <s:set name="dynamic_action">getTDSCRUDEAction?action=save</s:set>
        <s:set name="headerText">Regular > Add TDS Deduct Details</s:set>
        <s:set name="btntxt">Save</s:set>
    </s:if>
    <s:elseif test="%{action.equalsIgnoreCase('edit')}">
        <s:set name="dynamic_action">getTDSCRUDEAction?action=update&flag=updateAlloc</s:set>
        <s:set name="headerText">Regular > Edit TDS Deduct Details</s:set>
        <s:set name="btntxt">Update</s:set>
    </s:elseif>
    <s:elseif test="%{action.equalsIgnoreCase('View')}">
        <s:set name="headerText">Regular > View TDS Deduct Details</s:set>
    </s:elseif>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li  onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="#browse"><p>Transaction Browse </p> </a></li>
            <li onclick="callEntryPage();" class="nav-item"><a class="nav-link active" data-toggle="pill" href="tdsTransactionEntry"><p> Transaction Entry </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>


    <s:hidden id="tds_challan_rowid_seq" name="tdsTranLoad.tds_challan_rowid_seq" value="%{mapChallanRowSeq}"/>
    <s:hidden id="hprocrssFlag" name="procrssFlag" value="%{procrssFlag}"/>


    <s:hidden id="action" name="action" value="%{action}"/>
    <s:form id="tds_entry_form" name="tds_entry_form"  action="%{dynamic_action}" autocomplete="off">
        <s:if test="%{action.equalsIgnoreCase('edit')}">
            <div class="row" style="display:none;">
                <div class="col-md-3">
                    <div class="form-group">                       
                        <label for="exampleInputName2">ID</label>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="form-group">                       
                        <s:textfield id="rowid_seqid" name="tdsTranLoad.rowid_seq" readonly="true" cssClass="form-control" placeholder="ID"/>
                    </div>
                </div>
            </div>
        </s:if>  

        <div class="clearfix"></div>

        <div class="tab-content px-4 py-4">
            <div id="entry" class="tab-pane form-wrapper show in active">
                <div class="container-fluid pt-2">
                    <s:if test="%{mapFlag != null || mapTDSChallanAmt!=null }">
                        <s:if test="%{(mapFlag != null && mapFlag.equalsIgnoreCase('MAPTDSAMT')) || mapTDSChallanAmt!=null }">
                            <div class="clearfix"></div>


                            <%--                            <div class="count-container mt-1 mb-3">
                                                            <div class="row">
                            
                                                                <div class="col-sm-2 text-right">Challan Amount :</div>
                                                                <div class="col-sm-2">
                            <s:if test="%{mapTDSChallanAmt==null ||mapTDSChallanAmt=='' ||mapTDSChallanAmt=='null'}">0.00</s:if>
                            <s:else>
                                <s:set var="mapTDSChallanAmt_value" value="mapTDSChallanAmt"></s:set>
                                <%
                                    String mapchallamt = "0.00";
                                    try {
                                        String tdsChallanVal = (String) pageContext.getAttribute("mapTDSChallanAmt_value");
                                        mapchallamt = utl.getAmountIndianFormat(Double.parseDouble(tdsChallanVal));
                                    } catch (Exception e) {
                                    }
                                %>
                                <%=mapchallamt%>
                            </s:else>
                        </div>
                                <div class="col-sm-2 text-right">Allocated Amount:</div>
                                <div class="col-sm-2">
                            <s:if test="%{mapAllocatedAmount==null ||mapAllocatedAmount=='' ||mapAllocatedAmount=='null'}">0.00</s:if>
                            <s:else>
                                <s:set var="mapAllocatedAmount_value" value="mapAllocatedAmount"></s:set>
                                <%
                                    String mapallocamt = "0.00";
                                    try {
                                        Double allocVal = (Double) pageContext.getAttribute("mapAllocatedAmount_value");
                                        mapallocamt = utl.getAmountIndianFormat(allocVal);
                                    } catch (Exception e) {
                                    }
                                %>
                                <%=mapallocamt%>
                            </s:else>
                                </div>
                                <div class="col-sm-2 text-right">Balance Amount :</div>
                                <div class="col-sm-2">
                            <s:if test="%{balanceAmount==null ||balanceAmount=='' ||balanceAmount=='null'}">0.00</s:if>
                            <s:else>
                                <s:set var="balanceAmount_value" value="balanceAmount"></s:set>
                                <%
                                    String ballanceamt = "0.00";
                                    try {
                                        Double ballamt = (Double) pageContext.getAttribute("balanceAmount_value");
                                        ballanceamt = utl.getAmountIndianFormat(ballamt);
                                    } catch (Exception e) {
                                    }
                                %>
                                <%=ballanceamt%>
                            </s:else>

                                </div>
                        </div>
                </div>--%>
                            <div class=" count-container mb-2 mt-2">
                                <div class="row mb-2">
                                    <div class="col-md-3 seperator">
                                        <label class="mr-1">Challan Number : </label> 
                                        <label> <s:if test="%{tdsChallanNo==null ||tdsChallanNo=='' ||tdsChallanNo=='null'}">0</s:if>
                                            <s:else>
                                                <s:set var="tdsChallanNo_value" value="tdsChallanNo"></s:set>
                                                <%
                                                    String tdsChallanNo = "";
                                                    try {
                                                        tdsChallanNo = (String) pageContext.getAttribute("tdsChallanNo_value");
                                                    } catch (Exception e) {
                                                    }
                                                %>
                                                <%=tdsChallanNo%>
                                            </s:else>
                                        </label>
                                    </div>

                                    <div class="col-md-3 seperator">
                                        <label class="mr-1">Challan Date : </label> 
                                        <label> <s:if test="%{tdsChallanDate==null ||tdsChallanDate=='' ||tdsChallanDate=='null'}">Not Available</s:if>
                                            <s:else>
                                                <s:set var="tdsChallanDate_value" value="tdsChallanDate"></s:set>
                                                <%
                                                    String tdsChallanDate = "";
                                                    try {
                                                        tdsChallanDate = (String) pageContext.getAttribute("tdsChallanDate_value");
                                                    } catch (Exception e) {
                                                    }
                                                %>
                                                <%=tdsChallanDate%>
                                            </s:else>
                                        </label>
                                    </div>
                                    <div class="col-md-3 seperator">
                                        <label class="mr-1">Challan Amount : </label> 
                                        <label >
                                            <s:if test="%{mapTDSChallanAmt==null ||mapTDSChallanAmt=='' ||mapTDSChallanAmt=='null'}">0.00</s:if>
                                            <s:else>
                                                <s:set var="mapTDSChallanAmt_value" value="mapTDSChallanAmt"></s:set>
                                                <%
                                                    String mapchallamt = "0.00";
                                                    try {
                                                        String tdsChallanVal = (String) pageContext.getAttribute("mapTDSChallanAmt_value");
                                                        mapchallamt = utl.getAmountIndianFormat(Double.parseDouble(tdsChallanVal));
                                                    } catch (Exception e) {
                                                    }
                                                %>
                                                <%=mapchallamt%>
                                            </s:else>
                                        </label>
                                        <span class=""></span>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="mr-1">Allocated Amount : </label>
                                        <label >
                                            <s:if test="%{mapAllocatedAmount==null ||mapAllocatedAmount=='' ||mapAllocatedAmount=='null'}">0.00</s:if>
                                            <s:else>
                                                <s:set var="mapAllocatedAmount_value" value="mapAllocatedAmount"></s:set>
                                                <%
                                                    String mapallocamt = "0.00";
                                                    try {
                                                        Double allocVal = (Double) pageContext.getAttribute("mapAllocatedAmount_value");
                                                        mapallocamt = utl.getAmountIndianFormat(allocVal);
                                                    } catch (Exception e) {
                                                    }
                                                %>
                                                <%=mapallocamt%>
                                            </s:else>
                                        </label>
                                        <span class=""></span>
                                    </div>
                                    <div class="col-md-3 ">
                                        <label class="mr-1">Balance Amount : </label> 
                                        <label> <s:if test="%{balanceAmount==null ||balanceAmount=='' ||balanceAmount=='null'}">0.00</s:if>
                                            <s:else>
                                                <s:set var="balanceAmount_value" value="balanceAmount"></s:set>
                                                <%
                                                    String ballanceamt = "0.00";
                                                    try {
                                                        Double ballamt = (Double) pageContext.getAttribute("balanceAmount_value");
                                                        ballanceamt = utl.getAmountIndianFormat(ballamt);
                                                    } catch (Exception e) {
                                                    }
                                                %>
                                                <%=ballanceamt%>
                                            </s:else>
                                        </label>
                                    </div>

                                </div>

                            </div>


                        </s:if>
                    </s:if>

                    <div class="gap3"></div>

                    <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %> 




                    <!---------------end message div ------------------------------------->
                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                TDS Section <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <input type="hidden" id="hTdsDeductReason" value="<s:property value="tdsTranLoad.tds_deduct_reason"/>">
                            <s:hidden id="hidden_tdsTypeCode" name="hidden_tdsTypeCode"/>
                            <input type="hidden" id="hid_tds_deduct_reason">
                            <s:select list="tdsSectionList" cssClass="form-control" title="Section" id="section" name="tdsTranLoad.tds_code" onchange="changeTdsDeductReason();"
                                      onblur="getTDSRate('false');"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Deductee PAN No. <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="text" class="form-control" id="panno" placeholder="Deductee PAN No." name="tdsTranLoad.deductee_panno" 
                                             maxLength="10"  onblur="lhsValidatePAN(this); getDeducteeCode(this);"  cssStyle="text-transform: uppercase;"/>
                                <input type="text" class="form-control" id="verifiedBy" placeholder="PAN NO. Status" title="PAN NO. Status"  readonly >
                                <div class="input-group-prepend">
                                    <div class="input-group-text line-height-2" title="PANNOTAVBL">
                                        <input type="checkbox"  id="checkbox" onclick="getPanNotAvailble();">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Deductee Name <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">

                            <s:textfield  type="text" class="form-control" id="deducteeName" placeholder="Deductee Name" name="tdsTranLoad.deductee_name" onkeypress="return lhsValidateAlphabet(event);" cssStyle="text-transform: uppercase;"/>
                        </div> 
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Account Number <span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">
                            <s:textfield  type="text" class="form-control" id="account_no" placeholder="Account Number" name="tdsTranLoad.account_no" onkeypress="return lhsIsNumber(event);"/>
                        </div> 
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">

                            <label class="control-label">
                                Deductee Reference. No.
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">


                            <s:textfield  type="text" class="form-control" id="refNo" placeholder="Deductee Reference. No." cssStyle="text-transform: uppercase;"
                                          name="tdsTranLoad.deductee_ref_code1" onblur="changededucteeRefNo(this.id);" maxlength="10"/>

                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Transaction Date <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:if test="%{action.equalsIgnoreCase('add')}">  
                                <div class="input-group">
                                    <s:textfield  type="text" class="form-control" id="tranDate" name="tdsTranLoad.tran_ref_date" placeholder="TDS Deduct Date (DD-MM-YYYY)" onkeyup="copyDate(this, 'tdsDate');" onblur="validateDate1(this);checkDateBetweenTwoDates();" autocomplete="off"/>
                                    <div class="input-group-append">
                                        <button type="button"  class="btn btn-primary btn-sm addon-btn" onmouseover="checkDate(this.id, 'tranDate');" onblur="copyDate(tranDate, 'tdsDate');"><i id="tranDateBtn" class="fa fa-calendar"></i></button></div>
                                </div>


                            </s:if>
                            <s:elseif test="%{action.equalsIgnoreCase('edit') || action.equalsIgnoreCase('view')}">
                                <div class="input-group">
                                    <s:textfield  type="text" class="form-control" id="tranDate" name="tdsTranLoad.tran_ref_date" placeholder="TDS Deduct Date (DD-MM-YYYY)"  onkeyup="copyDate(this, 'tdsDate');" onblur="validateDate1(this);" autocomplete="off"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="checkDate(this.id);"><i id="tranDateBtn" class="fa fa-calendar"></i></button></div>
                                </div>


                                <script>
                                    try {
                                        changeTranDateFormat();
                                    } catch (err) {
                                    }
                                </script>
                            </s:elseif>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Transaction Amount <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-4">
                            <s:textfield  type="text" class="form-control" id="tranAmt" placeholder="Transaction Amount" name="tdsTranLoad.tran_amt" 
                                          onblur="remove_comma_from_amt(this.id, true);" onkeypress="return validateNumber(event); remove_comma_from_amt(this.id, true);" />
                        </div>
                    </div>
                    <s:if test="%{#assessment.getTdsTypeCode()=='27EQ'}">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">
                                    Party Bill Amount <span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-4">
                                <s:textfield  type="text" class="form-control" id="partybillamt" placeholder="Party Bill Amount" name="tdsTranLoad.partybillamt" 
                                              onblur="remove_comma_from_amt(this.id, true);" onkeypress="return validateNumber(event); remove_comma_from_amt(this.id, true);" />
                            </div>
                        </div>
                    </s:if> 
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Deduct Date <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield  type="text" class="form-control" id="tdsDate" name="tdsTranLoad.tds_deduct_date" onblur="validateDate1(this);" placeholder="TDS Deduct Date (DD-MM-YYYY)" autocomplete="off"/>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" onmouseover="doOnLoad('tdsDate', 'tdsDateIcon')" ><i id="tdsDateIcon" class="fa fa-calendar"></i></button></div>
                            </div>
                            <script>
                                try {
                                    changeDeductDateFormat();
                                } catch (err) {
                                }
                            </script>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Deduct Reason
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">

                                <s:select list="tdsDeductReasonList" cssClass="form-control" title="TDS Deduct Reason" id="deductReason" name="tdsTranLoad.tds_deduct_reason" onchange="onchangeDeducteReasonNew(this);"/>

                                <input type="hidden" id="hiddenCertif" value="<s:property value="tdsTranLoad.certificate_no"/>" /> 
                                <s:textfield  type="text" class="form-control green-color-placeholder" id="certificateNo" placeholder="Enter Certificate No." name="tdsTranLoad.certificate_no"  readonly="true" cssStyle="display:none"
                                              maxlength="10"/>
                                <div class="input-group-append" style="display:none" id="certificateDiv">
                                    <!--<button type="button" class="btn btn-primary btn-sm addon-btn" id="addTdsSpclRate" data-toggle="modal" aria-expanded="false" onclick="setNewCertificateFields();"><i class="fa fa-plus"></i></button>-->
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" id="addTdsSpclRate" data-toggle="modal" 
                                            aria-expanded="false" onclick="showNewCertificateFields();"><i class="fa fa-plus"></i></button>
                                </div>

                                <s:textfield  type="text" class="form-control green-color-placeholder" id="refrence_no" placeholder="Reference No" name="l_reference_no"  readonly="true" cssStyle="display:none" />
                            </div>
                        </div>
                    </div>

                    <s:if test="%{#assessment.getTdsTypeCode()=='27Q'}">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">Rate Type</label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <s:select list="selectRateType" id="rate_type" name="tdsTranLoad.rate_type" readonly="#readonly" cssClass="form-control" placeholder="Rate Type" cssStyle="width:100%;" onchange="setMandatoryWhenPanNotAvblAnd27Q();"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label">Nature of Remittance</label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <s:select list="selectRemittanceNature" id="nature_of_remittance"  name="tdsTranLoad.nature_of_remittance" readonly="#readonly"  cssClass="form-control" placeholder="Nature Of Remmitance"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">
                                <label class="control-label" Id="countryLbl">Country</label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <s:select list="select_country" id="country_code" name="tdsTranLoad.country_code" cssClass="form-control" cssStyle="width:100%;"></s:select>
                                    <script>
                                        var h_country_code = document.getElementById("h_country_code").value;
                                        if (h_country_code !== null && h_country_code !== "" && h_country_code !== "null") {
                                            document.getElementById("country_code").value = h_country_code;
                                        } else {
                                            document.getElementById("country_code").value = '113';
                                        }
                                    </script>
                                </div>
                            </div>
                    </s:if>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Rate
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield  type="text" class="form-control" id="tdsRate" placeholder="TDS Rate" name="tdsTranLoad.tds_rate" onkeypress="return validateNumber(event);"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Total TDS Amount
                            </label>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group">
                                <s:textfield  class="form-control" id="totalTdsAmt" placeholder="Total TDS Amount" name="tdsTranLoad.tds_amt" onclick="remove_single_zero(this.id);" onblur="getReverseRateAmount(this.id);" onkeypress="return lhsIsNumber(event);" />
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" href="#tds_add_info" data-toggle="collapse" title="Additinal TDS Info."><i class="fa fa-plus"></i></button>
                                </div>
                                <input type="hidden" class="form-control" id="itax_rate"  name="tdsTranLoad.itax_rate">
                                <input type="hidden" class="form-control" id="surcharge_rate"  name="tdsTranLoad.surcharge_rate">
                                <input type="hidden" class="form-control" id="cess_rate"  name="tdsTranLoad.cess_rate">
                            </div>
                        </div>
                    </div>
                </div>



                <div class="collapse" id="tds_add_info">
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                TDS Amount 
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="tds_base_amount" name="tdsTranLoad.tds_base_amt" placeholder="TDS Amount "  onblur="validateTdsRateAmount(this.id);" onkeypress="return validateNumber(event);" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Surcharge Amount
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield  class="form-control" id="surcharge_amount" placeholder="Surcharge Amount" name="tdsTranLoad.surcharge_amt"  onblur="validateTdsRateAmount(this.id);" onkeypress="return validateNumber(event);" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                CESS Amount
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield class="form-control" id="cess_amount" placeholder="CESS Amount"  name="tdsTranLoad.cess_amt" onblur="validateTdsRateAmount(this.id);" onkeypress="return validateNumber(event);" />
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="form-group">
                    <div class="row">

                        <div class="button-section col-md-12 my-1 text-center"> 
                            <s:if test="%{!action.equalsIgnoreCase('View')}">           
                                <s:if test="%{action.equalsIgnoreCase('add')}">
                                    <s:hidden id="saveAndContinue" name="saveAndContinue"/>
                                    <button type="button" class="form-btn" id="saveBtn1" onclick="saveTDSContentData(this);"><i class="fa save" aria-hidden="true"  ></i>Save & Continue</button>
                                    <button type="button" class="form-btn" id="saveBtn" onclick="saveTDSContentData(this);"><i class="fa save" aria-hidden="true" ></i>Save & Exit</button>
                                </s:if> 
                                <s:else> 
                                    <button type="button" class="form-btn" id="updateBtn" onclick="updateTDSContentData(this);"><i class="fa update" aria-hidden="true"  ></i>Update</button>                                 
                                </s:else> 
                            </s:if> 
                            <%--<s:url id="cancelBtn_id" namespace="/" action="tdsTransaction"></s:url>--%>
                            <%--<s:a href="%{cancelBtn_id}">--%>   
                            <button type="button" class="form-btn" onclick="callUrl('/tdsTransaction');"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                            <%--</s:a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</s:form>


<div class="modal fade" id="additionalTDSInfo">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add Tds Special Rate</h5>
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
                <!--Form Validation success-->

                <!--Form Validation Error-->
                <div class="text-center col-md-12" id="errorMsgDivSplRate" style="display:none">
                    <div class="d-inline-block">
                        <div class="form-validation form-validation--error p-1 my-1">
                            <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" id="errorMsgSplRate">Some Error Occured</h5>
                        </div>
                    </div>
                </div>
                <!--Form Validation Error-->

                <!--Form Validation Info-->
                <div class="text-center col-md-12" id="notificationMsgDivSplRate" style="display:none">
                    <div class="d-inline-block">
                        <div class="form-validation form-validation--info p-1 my-1">
                            <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                            <h5 class="d-inline-block" id="notificationMsgSplRate">Please Fill Mandatory Fields</h5>
                        </div>
                    </div>
                </div>

                <s:form id="tdsSplRatePage" name="" action="" autocomplete="off">

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

                            <s:hidden id="jsessionid1" name="tdsSplRateMast.deductee_code"  cssClass="form-control" placeholder="Deductee Name" maxLength="15"></s:hidden>

                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-4  text-right">

                                        <label class="control-label">
                                            Deductee PAN No. <span class="text-danger font-weight-bold ml-1">*</span>
                                        </label>
                                    </div>

                                    <div class="col-lg-7 col-xl-4">

                                    <s:textfield  class="form-control" id="fltrPanNo" placeholder="Deductee PAN No." name="deducteePan"  readonly="true"/>
                                </div>

                            </div>


                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">

                                    <label class="control-label">
                                        Deductee Name
                                    </label>
                                </div>

                                <div class="col-lg-7 col-xl-4">

                                    <s:textfield  class="form-control" id="fltrDeducteeName" placeholder="Deductee Name" name="tdsSplRateMast.deductee_name" readonly="true"/>
                                </div> 
                            </div>


                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">

                                    <label class="control-label">
                                        Certificate No.<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>

                                <div class="col-lg-7 col-xl-4">
                                    <%--<s:textfield  class="form-control" id="certificate_no1" placeholder="Certificate No." name="tdsSplRateMast.certificate_no"
                                                  maxlength="10"/>--%>
                                    <s:select list="certificateNoList" listKey = "certificate_no" listValue = "certificate_no" id="certificate_no" 
                                              value = "%{certificateNoList[#status.index].{certificate_no}}"  headerKey="" headerValue="--Select Certificate--"
                                              class="form-control" onchange="showSelectedCertificateDetails(this.value);"/>
                                </div>
                            </div>


                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">

                                    <label class="control-label">
                                        TDS Section <span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">


                                    <s:select list="tdsSectionList" cssClass="form-control" title="Section" id="tds_code" name="tdsSplRateMast.tds_code" onchange="changeTdsDeductReason();" cssStyle="pointer-events:none"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Certificate From Date <span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <%--<s:if test="%{certificateNoList != null && certificateNoList.size() > 0 && certificateNoList.get(0).from_date != null}">--%>
                                    <%--<s:set var="tdsSplRateMast_from_date"><s:date name="certificateNoList.get(0).from_date" format="dd-MM-yyyy" /></s:set>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                    <%--<s:set var="tdsSplRateMast_from_date"></s:set>--%>
                                    <%--</s:else>--%>

                                    <%--<s:textfield class="form-control" id="certificate_date1" name="tdsSplRateMast.from_date" placeholder="Certificate From Date"/>--%>
                                    <s:textfield class="form-control" id="certificate_date" name="tdsSplRateMast.from_date"                                                 
                                                 placeholder="Certificate From Date" onmouseover="get_tds_certi_from_date(this.id);" onkeyup="copyDate(this, 'tdsDate');"/>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Certificate Upto Date<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <%--<s:if test="%{certificateNoList != null && certificateNoList.size() > 0 && certificateNoList.get(0).certificate_valid_upto != null}">--%>
                                    <%--<s:set var="tdsSplRateMast_certificate_valid_upto"><s:date name="certificateNoList.get(0).certificate_valid_upto" format="dd-MM-yyyy" /></s:set>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                    <%--<s:set var="tdsSplRateMast_certificate_valid_upto"></s:set>--%>
                                    <%--</s:else>--%>
                                    <s:textfield class="form-control" id="certificate_valid_upto" name="tdsSplRateMast.certificate_valid_upto" 
                                                 placeholder="Certificate Upto" onmouseover="get_tds_certi_to_date(this.id);" />
                                </div>
                            </div>



                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        TDS Limit Amount<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield  class="form-control" id="tds_limit_amt" placeholder=" Tds Limit Amount" name="tdsSplRateMast.tds_limit_amt"
                                                  />
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        Amount Consumed <span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-4">
                                    <s:textfield class="form-control" id="amount_consumed" placeholder=" Amount Consumed " name="tdsSplRateMast.amount_consumed"                                                  
                                                 onclick="remove_single_zero(this.id);" onkeypress="return validateNumber(event);"/>

                                </div>
                            </div>


                            <div class="row form-group">
                                <div class="col-lg-4 col-xl-4  text-right">
                                    <label class="control-label">
                                        TDS Rate<span class="text-danger font-weight-bold ml-1">*</span>
                                    </label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield  class="form-control" id="tds_rate1" name="tdsSplRateMast.tds_rate" placeholder="TDS Rate " 
                                                  onkeypress="return validateNumber(event);" />
                                </div>
                            </div>

                            <div class="clearfix"></div>

                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center">                                         

                                        <!--<button type="button" class="form-btn" id="save" onclick="saveTdsSplRateDetails();"><i class="fa save" aria-hidden="true" ></i>Save</button>-->
                                        <button type="button" class="form-btn" onclick="unallocateSingleCertificate();"><i class="fa clear" 
                                                                                                                           aria-hidden="true" ></i>Un-Allocate</button>
                                        <button type="button" class="form-btn" onclick="allocateSingleCertificate();"><i class="fa save" 
                                                                                                                         aria-hidden="true" ></i>Allocate</button>
                                        <button type="button" class="form-btn" data-dismiss="modal"><i class="fa cancel" 
                                                                                                       aria-hidden="true" ></i>Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </s:form>

            </div>

        </div>
         
    </div>
   

                 
</div>
    
    
    
    
    
    
    
    
<s:hidden id="tds_section_value_in_database" name="tdsTranLoad.tds_section"/>
<s:hidden id="hidden_tds_amt" value="%{tdsTranLoad.tds_amt}"/>
<script type="text/javascript">
    <s:if test="%{action.equalsIgnoreCase('edit')}">
    try {
        disableRateFor24Q(document.getElementById("tdsType").value);
    } catch (err) {
    }
    try {
        checkPanNotAvailCheckbox();
    } catch (err) {
    }
    try {
        getEditRefno();
    } catch (err) {
    }
     
    </s:if>
    <s:elseif test="%{action.equalsIgnoreCase('View')}">
    try {
        disableRateFor24Q(document.getElementById("tdsType").value);
    } catch (err) {
        console.log("err.." + err);
    }
    try {
        checkPanNotAvailCheckbox();
    } catch (err) {
        console.log("err.." + err);
    }
    try {
        getEditRefno();
    } catch (err) {
        console.log("err.." + err);
    }
    </s:elseif>
    try {
        changeTdsDeductReason(document.getElementById("action").value);
    } catch (err) {
        console.log("err.." + err);
    }
    try {
        //forSubmitAction();
    } catch (err) {
        console.log("err.." + err);
    }
    document.getElementById("section").focus();
</script>
<script>
    try{
    OnloadPanVarification();
    } catch (err) {
    }
 
    </script>
