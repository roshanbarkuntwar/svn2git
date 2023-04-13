<%-- 
    Document   : challanBrowse
    Created on : Jan 28, 2019, 10:15:28 AM
    Author     : aniket.naik
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<meta http-equiv="X-UA-Compatible" content="IE=11">
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">

<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/global/template.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>


<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="javascript:challanBrowsePage()"  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Challan Browse </p> </a></li>
            <li onclick="javascript:challanEntryPage()"  class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsChallanEntry"><p> Challan Entry </p></a></li>
            <li style="cursor: not-allowed;color:black;" title="Allocation is allowed for individual record through browse page only ! " class="nav-item"><a class="nav-link" style="pointer-events:none;" ><p> Challan Allocation </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">

            <div class="button-section my-1 pt-1"> 
                <s:set var="loginuser" value="%{#session.get('LOGINUSER')}"/>

                <s:if test="%{(#loginuser.print_right != null) && (#loginuser.print_right == 1)}">
                    <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('CHALLAN DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                    <!--<button type="button" class="form-btn" onclick="DownloadTemplate('challanBrwseFrm','downloadInTemplate');"><i class="fa fa-download" aria-hidden="true"></i>Download</button>-->
                </s:if>
                <s:else>
                    <button type="button" class="form-btn" disabled="disabled" title="Sorry!You have no rights to download"><i class="fa download" aria-hidden="true"></i>Capture Page Data in Excel</button>
                    <!--<button type="button" class="form-btn" disabled="disabled" title="Sorry!You have no rights to download"><i class="fa fa-download" aria-hidden="true"></i>Download</button>-->
                </s:else>     
                <!--                                    <button type="button" class="form-btn"><i class="fa fa-floppy-o" aria-hidden="true"></i>Save</button>-->
                <!--<button type="button" class="form-btn"><i class="fa fa-filter" aria-hidden="true"></i>Import From File</button>-->         
                <button type="button" class="form-btn" id="refreshAllocationBtn" onclick="refreshAllocation();"><i class="fa refresh" aria-hidden="true"></i>Refresh Allocation</button>
                <!--<button type="button" class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Re-build</button>-->
                <button type="button" id = "verifyChallanBtn" class="form-btn" onclick="onClickVerifyChallan();"><i class="fa verification" aria-hidden="true"></i>Challan Verification</button>
                <button type="button" class="form-btn" onclick="setCentralizedFlag(this);"><i class="fa centralize-allocation" aria-hidden="true"></i>View Centralize Allocation Records</button>                
                <!--<button type="submit" class="form-btn" onclick="bulkFileDownload('challanBrwseFrm');"><i class="fa download" aria-hidden="true"></i>Bulk Download</button>-->                                    
            </div> 
            <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
            <s:form name="challanBrwseFrm" id="challanBrwseFrm" method="post" action="tdsChallan?search=true" theme="simple">
                <s:hidden value="6" id="noOfColumn" name="noOfColumn"/>
                <s:hidden value="challan" id="downloadFlag" name="downloadFlag"/>
                <s:hidden id="fromAllocatedChallan" name="fromAllocatedChallan"/>
                <s:hidden id="centralizedAllocationFlag" name="centralizedAllocation"/>
                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group">
                                <div class="col-lg-3">
                                    <s:select list="selectAllocationStatus" id="f_other_filter" name="tranChallanFltrSrch.allocationStatus" cssClass="form-control" cssStyle="width:100%;" />
                                </div>
                                <div class="col-lg-3">
                                    <div class="input-group">
                                        <s:textfield id="from_date" name="tranChallanFltrSrch.from_date" cssClass="form-control"  placeholder="FROM DATE" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this); changeDateFilter();" maxLength="10"/>
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn" id="fromDateBtn" onmouseover="openCalendar('from_date', 'fromDateCal');">
                                                <i class="fa fa-calendar" id="fromDateCal"></i>
                                            </button>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <s:textfield id="to_date" name="tranChallanFltrSrch.to_date"   cssClass="form-control"  placeholder="TO DATE" onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this);"   maxLength="10"/>  
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-primary addon-btn" id="toDateBtn" onmouseover="openCalendar('to_date', 'toDateCal');">
                                                <i class="fa fa-calendar" id="toDateCal"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3">                         

                                    <s:select list="selectChallanStatus" id="f_month" name="tranChallanFltrSrch.challanStatus" cssClass="form-control" placeholder="Challan Status" />

                                </div>
                            </div>
                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <s:if test="%{selectSection.size>0 && selectSection !=null}">
                                        <div class="col-md-3">
                                            <s:select list="selectSection" id="f_tds_section" name="tranChallanFltrSrch.tds_section" cssClass="form-control"  required="true" placeholder="Section" />
                                        </div>
                                    </s:if>

                                    <div class="col-md-3">
                                        <s:textfield id="f_bank_branch_code" name="tranChallanFltrSrch.bankBranchCode" cssClass="form-control" placeholder="Bank Branch Code"/>
                                    </div>
                                    
                                     <div class="col-md-3">
                                        <s:textfield id="tds_challan_no" name="tranChallanFltrSrch.tds_challan_no" cssClass="form-control" placeholder="Challan No"/>
                                    </div>

                                </div>
                            </div>     
                        </div>     
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();" title="Search"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('challanBrwseFrm', 'submit');" title="Clear"><i class="fa clean"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"
                                  onclick="showFilterData('tdsChallan');"  title="Extra Filters"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="filterFlag" id="filterFlag">
            </s:form>
            <div class="clearfix"></div>  
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 
            <div class="clearfix"></div> 
            <!--Table Section-->

            <div class="table-responsive mt-2" id="dataSpan">
                <!--Ajax data here-->
                <%
                    out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
            </div>

            <!--Table Section-->
            <div class="clearfix"></div> 

            <!--            Challan Verification Details Modal-->
            <div class="modal fade" id="challanVerifyDetails" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
                <div class="modal-dialog modal-dialog-centered" role="document" >
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="header_name">Challan Verification Details</h4>
                            <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true" >&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <div class="row form-group">
                                <div class="col-lg-6 col-xl-6  text-right">
                                    <label class="control-label">Total No. of Challans :</label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield  class="form-control" id="totalChallanID" name=""  readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-6 col-xl-6  text-right">
                                    <label class="control-label">No. of Matched challan :</label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield  class="form-control" id="matchedChallanID" name=""  readonly="true"/>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-lg-6 col-xl-6  text-right">
                                    <label class="control-label">No. of Unmatched challan :</label>
                                </div>
                                <div class="col-lg-7 col-xl-4">
                                    <s:textfield  class="form-control" id="unmatchedChallanID" name=""  readonly="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="button-section col-md-12 my-1 text-center">
                                        <button type="button" class="form-btn" data-dismiss="modal" aria-label="Close"><i class="fa cancel" aria-hidden="true"></i>Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--                        <div class="modal-footer">
                                                    <button type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">Close</button>
                                                </div>-->
                    </div>
                </div> 
            </div> 
            <!--            Challan Verification Details Modal End-->

            <!--            Upload CSI Modal-->
            <div class="modal fade" id="challanUploadCSIFile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document" >
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="header_name">Challan Verification Details Through CSI File</h4>
                            <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true" >&times;</span></button>
                        </div>
                        <s:form id="uploadCSIFiles" name="uploadCSIFiles" action="uploadCSI?validate=true" method="POST" enctype="multipart/form-data" autocomplete="off">
                            <div class="modal-body">
                                <div class="row form-group">
                                    <div class="col-lg-4 col-xl-6 text-right">
                                        <label class="control-label">Upload CSI File : <span class="text-danger font-weight-bold ml-1">*</span></label>
                                    </div>
                                    <div class="col-lg-7 col-xl-4">
                                        <div class="custom-file">
                                            <input type="file" id="csiTemplate" name="csiTemplate" class="custom-file-input form-control"  onchange="$(this).next('.custom-file-label').html(this.files[0].name)" title="Upload Csi File">
                                            <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="button-section col-md-12 my-1 text-center">
                                            <button type="button" class="form-btn" data-dismiss="modal" aria-label="Close"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                                            <button type="button" class="form-btn" id="uploadCSIFileBtn" onclick="uploadCSIFile();"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--                                <div class="modal-footer">
                                                                <button type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">Close</button>
                                                                <button type="button" class="btn btn-success" id="uploadCSIFileBtn" onclick="uploadCSIFile();"><i class="fa upload" aria-hidden="true"></i>&nbsp;&nbsp;Upload</button>
                                                            </div>-->
                        </s:form>
                    </div>
                </div>
            </div> 
        </div> 
        <!--            Upload CSI Modal End-->            
    </div>        
</div> 
</div>     
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{dataGridAction}" id="dataGridAction"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<input type="hidden" id="countCall" value="0">
<input type="hidden" id="fltrformId" value="challanBrwseFrm">

<script src="resources/js/challan/challan.js?r=<%= Math.random()%>" type="text/javascript"></script>
<script src="resources/js/regular/challanVerification/challanVerification.js?r=<%=Math.random()%>" type="text/javascript"></script>
<script src="resources/js/regular/bulkDownload/bulkDownload.js?r=<%=Math.random()%>" type="text/javascript"></script>
<script type="text/javascript">
                                                    try {
                                                        defaultValues();
                                                    } catch (err) {
                                                    }
                                                    try {
                                                        $('#bulk_download_btn').removeClass('d-none');
//                                                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//                                                        document.getElementById("paginationBlock").style.display = "none";
                                                    } catch (e) {

                                                    }
</script>

