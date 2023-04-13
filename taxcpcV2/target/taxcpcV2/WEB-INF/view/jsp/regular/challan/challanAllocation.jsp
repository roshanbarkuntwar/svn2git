<%-- 
    Document   : challanAllocation
    Created on : Feb 13, 2019, 3:44:57 PM
    Author     : sneha.parpelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="globalUtilities.Util"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<% Util utl = new Util();%>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/template.js" type="text/javascript"></script>
<script src="resources/js/challan/challan.js?r=<%= Math.random()%>"></script>
<style>
    .custom-control-label::before {
    top: 0 !important;
    }
    .custom-control-input{
        width: 100%;
    height: 100%;
    left: 0;
    z-index: 9999999;
    }
    .f-18{
        font-size: 18px;
    }
    .count-container label {font-size:13px}
</style>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="challanBrowsePage()"  class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="#browse"><p>Challan Browse </p> </a></li>
            <li onclick="challanEntryPage()"   class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsChallanEntry"><p> Challan Entry </p></a></li>
            <li onclick=""  class="nav-item"><a class="nav-link active" data-toggle="pill" href="tdsChallanAllocation"><p> Challan Allocation </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="tdsChallanAllocation" class="tab-pane show in active">
            <div class="container-fluid pt-2">

                <div class="row form-group my-2 pt-2">
                    <div class="col-lg-12">
<!--                        <div class="count-container">
                            <label class="mr-1">Challan Number : </label> <label ><s:property value="%{ChallanNo}"/></label>
                            <span class="seperator mx-3"></span>
                            <label class="mr-1">Challan Date : </label> <label ><s:property value="%{ChallanDate}"/></label>
                            <span class="seperator mx-3"></span>
                            <label class="mr-1">Challan Amount: </label> 
                            <s:if test="%{tdsAmount==null ||tdsAmount=='' ||tdsAmount=='null'}">
                                <label  id="foramtTdsAmount">0.00</label>                                    
                            </s:if>
                            <s:else>
                                <s:set var="format_tdsAmount_value" value="tdsAmount"></s:set>
                                <%
                                    String maptdsamt = "0.00";
                                    try {
                                        String tdsAmtVal = (String) pageContext.getAttribute("format_tdsAmount_value");
                                        maptdsamt = utl.getAmountIndianFormat(Double.parseDouble(tdsAmtVal));
                                    } catch (Exception e) {
                                    }
                                %>
                                <label id="foramtTdsAmount"><%=maptdsamt%></label>
                            </s:else>
                            <span class="seperator mx-3"></span>
                            <label class="mr-1">Total Selected: </label> <label id="putCount">0</label>
                            <span class="seperator mx-3"></span>
                            <label class="mr-1">Allocated TDS Amount: </label> <label id="ttlTdsAmount"><s:property value="allocatedAmount != null ? allocatedAmount : 0.00"/></label>
                            <span class="seperator mx-3"></span>
                            <label class="mr-1">Pending For Allocation: </label> <label id="pendingforalloc"><s:property value="PendingForalloc != null ? PendingForalloc : 0.00"/></label>
                        </div>-->
<div class=" count-container mb-2">
                            <div class="row mb-2">
                            <div class="col-md-3 seperator">
                            <label class="mr-1">Challan Number : </label> <label ><s:property value="%{ChallanNo}"/></label>
                            <span class=""></span>
                            </div>
                            <div class="col-md-3 seperator">
                            <label class="mr-1">Challan Date : </label> <label ><s:property value="%{ChallanDate}"/></label>
                            <span class=""></span>
                            </div>
                            <div class="col-md-3 seperator">
                            <label class="mr-1">Challan Amount: </label> 
                            <s:if test="%{tdsAmount==null ||tdsAmount=='' ||tdsAmount=='null'}">
                                <label  id="foramtTdsAmount">0.00</label>                                    
                            </s:if>
                            <s:else>
                                <s:set var="format_tdsAmount_value" value="tdsAmount"></s:set>
                                <%
                                    String maptdsamt = "0.00";
                                    try {
                                        String tdsAmtVal = (String) pageContext.getAttribute("format_tdsAmount_value");
                                        maptdsamt = utl.getAmountIndianFormat(Double.parseDouble(tdsAmtVal));
                                    } catch (Exception e) {
                                    }
                                %>
                                <label id="foramtTdsAmount"><%=maptdsamt%></label>
                            </s:else>
                           
                            </div>
                             <div class="col-md-3">
                            <label class="mr-1">Total Selected: </label> <label id="putCount">0</label>
                            </div>
                            </div>
                            <div class="row">
                           
                            <div class="col-md-3 seperator">
                            <label class="mr-1">Allocated TDS Amount: </label> <label id="ttlTdsAmount"><s:property value="allocatedAmount != null ? allocatedAmount : 0.00"/></label>
                            
                            </div>
                            <div class="col-md-3">
                            <label class="mr-1">Pending For Allocation: </label> <label id="pendingforalloc"><s:property value="PendingForalloc != null ? PendingForalloc : 0.00"/></label>
                        </div></div>
                    </div>
                    </div>
                </div>

                <s:form name="challanAllocationFrm" id="challanAllocationFrm" method="post" action="tdsChallanAllocation?search=true" theme="simple">   

                    <s:hidden id="identifyFlag" name="identifyFlag" value="%{identifyFlag}"/>
                    <s:hidden id="tdsAmount" name="tdsAmount" value="%{tdsAmount}"/>
                    <s:hidden id="allocatedAmount" name="allocatedAmount" value="%{allocatedAmount}"/>
                    <s:hidden id="ParaRowidSeq" name="ParaRowidSeq" value="%{ParaRowidSeq}"/>
                    <s:hidden id="bulk_download_challan_rowid" class="filter_field" name="challan_rowid" value="%{ParaRowidSeq}"/>
                    <s:hidden id="ChallanDate" name="ChallanDate" value="%{ChallanDate}"/>
                    <s:hidden id="htdsSection" name="tdsSection" value="%{tdsSection}"/>
                    <s:hidden id="hChallanMonth" name="hChallanMonth" value="%{ChallanMonth}"/>
                    <s:hidden id="hChallanNo" name="ChallanNo" value="%{ChallanNo}"/>

                    <s:hidden id="h_ParaRowidSeq" name="mapTdsChallanFltrSrch.ParaRowidSeq" value="%{ParaRowidSeq}"/>
                    <s:hidden id="h_identifyFlag" name="mapTdsChallanFltrSrch.identifyFlag" value="%{identifyFlag}"/>                                                                              
                    <s:hidden id="h_challan_date" name="mapTdsChallanFltrSrch.challan_date" value="%{ChallanDate}"/> 
<!--                    <div class="button-section my-1"> 
                        <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('TDS CHALLAN ALLOCATION DETAILS');"><i class="fa download" aria-hidden="true"></i>Capture Page Data in Excel</button>                                
                    </div>-->
                    <div class="filter-section">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:select name="mapTdsChallanFltrSrch.allocationFilter" id="allocationStatus" list="allocationStatusList" cssClass="form-control" title="Allocation Status" cssStyle="color: green; font-weight:bold; !important"/>
                                    </div>

                                    <div class="col-lg-3">
                                        <s:textfield  name="mapTdsChallanFltrSrch.pan_no" id="panno" class="form-control" maxlength="10" title="PAN Number" placeholder="PAN Number" />
                                    </div>

                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="deducteeRefNo"  name="mapTdsChallanFltrSrch.deducteeRefNo" placeholder="Deductee Ref. No." title="Deductee Ref. No." onblur="changededucteeRefNo(this.id);" maxlength="10" onkeypress="return lhsIsNumber(event);"/>
                                    </div>

                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="accountNo"  name="mapTdsChallanFltrSrch.accountNo"  placeholder="Account Number" title="Account Number"/>
                                    </div>
                                </div>

                                <div class="collapse" id="remainingFilter" style="">
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <s:select name="mapTdsChallanFltrSrch.tds_section" id="tdsSection" list="tdsSectionList" cssClass="form-control"  title="Sction" />
                                        </div>


                                        <div class="col-lg-3">
                                            <s:select name="mapTdsChallanFltrSrch.tds_amt_betwn" id="operator" list="operatorList" cssClass="form-control" title="Tds Rate Between" />
                                        </div>
                                        <div class="col-lg-3">
                                            <s:textfield name="mapTdsChallanFltrSrch.tds_amt_from"  id="tdsFromAmount" class="form-control" title="TDS Amount From" placeholder="TDS Amount From" />
                                        </div>
                                        <div class="col-lg-3">
                                            <s:textfield name="mapTdsChallanFltrSrch.tds_amt_to"  id="tdsToAmount" class="form-control" title="TDS Amount To" placeholder="TDS Amount To" />
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <s:textfield  name="mapTdsChallanFltrSrch.deductee_name"  id="deducteeName" class="form-control" title="Deductee Name" placeholder="Deductee Name"/>
                                        </div>

                                        <div class="col-lg-3">
                                            <div class="input-group">
                                                <s:textfield  name="mapTdsChallanFltrSrch.from_date" id="fromDate" class="form-control" title="From Date" onmouseover="" placeholder="From Date"/>
                                                <div class="input-group-append">
                                                    <button type="button" class="btn btn-primary addon-btn" onmouseover="openCalendar('fromDate', 'fromDateCal'); doOnLoad('fromDate', 'fromDateCal')">
                                                        <i class="fa fa-calendar" id="fromDateCal"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="input-group">
                                                <s:textfield name="mapTdsChallanFltrSrch.to_date"  id="toDate" class="form-control" title="To Date"  placeholder="To Date"/>
                                                <div class="input-group-append">
                                                    <button type="button" class="btn btn-primary addon-btn" onmouseover="openCalendar('toDate', 'toDateCal'); doOnLoad('toDate', 'toDateCal')">
                                                        <i class="fa fa-calendar" id="toDateCal"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <s:select name="mapTdsChallanFltrSrch.deducteeLevel" id="deducteeLevel" list="deducteeLevelList" cssClass="form-control" title="Deductee Level" />

                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <s:textfield type="text" cssClass="form-control" id="tdsAmountTo"  name="mapTdsChallanFltrSrch.bankBranchCode" placeholder="Bank Branch Code" title="Bank Branch Code"/>
                                        </div>

                                        <div class="col-lg-3">
                                            <s:select list="tdsDeductReasonList" cssClass="form-control" headerKey="" headerValue="--Select TDS Deduct Reason--" title="TDS Deduct Reeason" id="" name="mapTdsChallanFltrSrch.tdsDeductReason" />    
                                        </div>

                                        <div class="col-lg-3">
                                            <s:select name="mapTdsChallanFltrSrch.pan_4th_char" id="category" list="categoryList" cssClass="form-control" title="Category" />
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between"> 
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="validateChallanAllocationFilter();"><i class="fa search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('challanAllocationFrm', 'submit');" title="Clear"><i class="fa clear"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn collapsed" data-toggle="collapse" title="Show More Filter" data-target="#remainingFilter" aria-expanded="false"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                            </div> 
                        </div>
                    </div>
                </div>
<!--                <div class="row">
                    <div class="col-lg-6 text-left">
                    
                        <!-- <s:checkbox name="allocateAllRecordID" id="allocateAllRecordID" onclick="chkAllocateAllTdsRecord();" cssStyle="opacity:1;"/><span style="font-weight: bold;"> Allocate All TDS Record</span>

                    </div>
              
                </div>-->


                <div class="clearfix"></div>
                <input type="hidden" name="filterFlag" id="filterFlag">
                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{browseAction}" id="browseAction"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>    
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="challanAllocationFrm">
            </s:form>

            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

            <div class="clearfix"></div> 
            <div class="table-responsive mt-2" id="dataSpan">
                <!--Ajax Data Here-->
            </div>
                
            <div class="button-section row my-1 text-center"> 
                <div  class="col-md-4" style="text-align:left;">
                         <i class="fa fa-square mr-1 allocated-icon f-18"></i> <label class="mr-3">Allocated</label>
                        <i class="fa fa-square mr-1 unallocated-icon f-18"></i> <label class="">Unallocated</label>
                </div>
                  <div  class="col-md-4" style="text-align:center;">
                <button type="button" class="form-btn" onclick="callUrl('/tdsChallan')"><i class="fa back" aria-hidden="true"></i>Back</button>
                  </div>
                <div  class="col-md-4"></div>
            </div>

            <div class="action-section bg-white text-center fixed-bottom" id="actiondiv">
                <s:if test="%{identifyFlag=='map_transaction'}">
                    <button type="button" class="action-btn action-btn--approve mr-2" id="btnUpdateChallenRowId"><i class="fa centralize-allocation"></i>Allocate/Unallocate Amount</button>
                </s:if>
            </div>
        </div>
    </div>

</div>    
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>
<script type="text/javascript">
                    try {
                        defaultValues();
                    } catch (err) {
                    }
                    try {
                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                        document.getElementById("paginationBlock").style.display = "none";
                    } catch (e) {
                    }
</script>