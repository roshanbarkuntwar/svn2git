<%-- 
    Document   : lowDeduCertifiAllocation
    Created on : Feb 18, 2019, 3:52:26 PM
    Author     : aniket.naik
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script src="resources/js/regular/dashboard/ldcAllocation.js" type="text/javascript"></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>

<!DOCTYPE html>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link active" href="#"><p>Lower Deduction - Certificate Allocation</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="col-md-12 my-1">
        <!--<h4 class="page-title mb-2">Lower Deduction - Certificate Allocation</h4>-->
        <div class="tab-content px-4 py-4">
            <div id="browse" class="tab-pane show in active">
                <div class="button-section  my-1">
                    <!--<button type="button" class="form-btn" onclick="submitAllAllocate();"><i class="fa fa-filter" aria-hidden="true"></i>Allocate All</button>-->   
                    <!--<button type="button" class="form-btn" onclick="submitAllNotAllocate();"><i class="fa fa-refresh" aria-hidden="true"></i>Unallocate All</button>-->
                    <!--<button type="button" class="form-btn" onclick="downloadAllocation();"><i class="fa fa-download" aria-hidden="true"></i>Download</button>-->
                </div>   
                
                <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
                <form id="filterForm" action="lowDeduCertifiAllocaBrowse?search=true" method="post">
                    <div class="filter-section pt-1">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group mt-2">
                                    <div class="col-lg-3">
                                        <s:select list="allocatedFlagList" id="allocatedFlag" name="allocatedFlag" onchange="" cssClass="form-control"/>
                                    </div>
                                    <div class="col-lg-2">
                                        <s:textfield id="certificateNo" name="certificateNo" placeholder="Certificate No." cssClass="form-control"/>
                                    </div>
                                    <div class="col-lg-2">
                                        <s:textfield id="deducteePanNo" maxLength="10" onkeyup="convertToUppercase();" onblur="validatePAN(this);" name="deducteePanNo" placeholder="Deductee PAN No." cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 mt-2 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="validateLowDeduCertiFilter();" title="Show"><i class="fa search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('filterForm', 'submit');" title="Clear"><i class="fa clean"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter" title="Not Available"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                            </div>
                        </div>
                    </div>
                    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                    <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                    <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                    <s:hidden value="%{update_data_row}" name="update_data_row" id="update_data_row"/>
                    <input type="hidden" id="countCall" value="0">
                    <input type="hidden" id="fltrformId" value="filterForm">
                    <input type="hidden" name="filterFlag" id="filterFlag">
                </form>                         
            </div>
            <div class="clearfix"></div>  
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 

            <div class="clearfix"></div> 
            <div style="position: relative">
                <form id="lowDeduCertifiAllocationForm" method="post">
                    <div class="table-responsive mt-2"  id="dataSpan">
                        <!--data from ajax-->
                        <%
                        out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                        %>
                    </div>    
                </form>
            </div>
            <div class="gap0"></div>
            
            <div class="clearfix"></div>
            <div class="action-section bg-white text-center fixed-bottom" id="actiondiv">
                <input type="hidden" id="viewCertificateId"/>
                <button type="button" class="action-btn action-btn--view mr-2 cursor-pointer" id="viewCertificateBtn" 
                        onclick="showCertificateList(document.getElementById('viewCertificateId').value);"> <i class="fa view"></i>View</button>
                
                <button type="button" class="action-btn action-btn--edit mr-2 cursor-pointer" id="allocateBtn" 
                        onclick="submitAllAllocate();"> <i class="fa fa-check-square"></i>Allocate</button>
                        
                <button type="button" class="action-btn action-btn--delete mr-2 cursor-pointer" id="unAllocateBtn" 
                        onclick="submitAllNotAllocate();"> <i class="fa fa-times-rectangle"></i>Un-Allocate</button> 
                 <button type="button" class="action-btn action-btn--edit mr-2 cursor-pointer" id="update-rec"  style="display:none;"
                        onclick="updateNotAflagRecord();"> <i class="fa fa-check-square"></i>Update</button>        
            </div>
        </div>
    </div>        
    <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
</div>

<!--Start Show Certificate List-->
<input type="hidden" id="selectedCertificatedRowNo"/>
<div id="showCertificateListModal" class="modal fade" role="dialog" style="top:30%; width: 50%; margin-left: 30%;">    
    <div class="modal-dialog modal-lg" role="document"  style="margin:0px 0px 0px 0px; width: 100%; ">
        <div class="modal-content" style="width: 100%;">
            <div class="modal-header" style="background-color: #29aecc; color: white;height: 10px;">
                <h4 class="modal-title" style="top: -15px;position: relative;width:50%;" id="header_name">Certificate List</h4>
                <div id="close_btn_div_ID" class="taxcpc-modal-close-btn" data-dismiss="modal" title="Close" style="top: 1px;position: absolute;right: 11px;">X</div>
            </div>
            <div class="modal-body" style="position: relative;width: 100%;clear: both;margin: auto;margin-bottom: 2%;">
                <div id="showCertificateListModalDivId">
                    <!--data from ajax-->
                </div>                
            </div>
        </div>    
    </div>      
</div> 
<!--End Show Certificate List -->
<script type="text/javascript">
    defaultValues();
//    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//    document.getElementById("paginationBlock").style.display = "none";
</script>