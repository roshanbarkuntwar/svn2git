<%-- 
    Document   : 15GHTransaction
    Created on : Feb 27, 2019, 3:22:03 PM
    Author     : aniket.naik
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/form15GH/deducteeManage15GHDetail.js?r=<%= Math.random()%>"></script>

<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Transaction Browse </p> </a></li>
            <li onclick="callEntryPage();"  class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="deducteesAction15gh?action=add"><p> Transaction Entry </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>

    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">
            <div class="button-section my-1">
                <s:if test="%{#loginuser.getAdd_right() != null &&  #loginuser.getAdd_right() == 1}">                
                    <s:url id="refNo" namespace="/" action="getGenReferenceNoAction">
                        <s:param name="action">RefNoPage</s:param>
                    </s:url>


                </s:if>
                <s:else>
                    <button type="button" title="Sorry ! you have not required privileges." class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Generate Reference No</button>
                </s:else>

                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" 
                        onclick="downloadStaticExcel('TRANSACTION 15G/H DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
            </div> 
            <s:form name="deducteeMgmtFrm" id="deducteeMgmtFrm" method="post" action="deductees15gh?search=true" theme="simple">
                <s:hidden name="filterFlag" id="filterFlag"/>
                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group">
                                <div class="col-lg-3">
                                    <input type="text"  class="form-control" title="PAN No." placeholder="PAN No." name="deducteeSearch.panno">
                                </div>
                                <div class="col-lg-3">
                                    <input type="text" class="form-control" title="Cust Id" placeholder="Cust Id" name="deducteeSearch.CustId">
                                </div>

                                <div class="col-lg-3">
                                    <s:select list="VrifiedFlg" name="deducteeSearch.verifiedby_flag_name" cssClass="form-control"></s:select>
                                    </div>
                                    <div class="col-lg-3">
                                    <s:select list="IncompleteGHFlag" name="deducteeSearch.invalidGHFlag" class="form-control"></s:select>
                                    </div>   
                                </div>

                                <div class="collapse" id="remainingFilter">
                                    <div class="row form-group">
                                        <div class="col-lg-3">
                                            <input type="text"  class="form-control" title="Deductee Name" placeholder="Deductee Name" name="deducteeSearch.deductee_name">
                                        </div>

                                        <div class="col-lg-3">
                                            <input type="text"  class="form-control" title="15 G/H Ref. No" placeholder="15 G/H Ref. No" name="deducteeSearch.UinNo">
                                        </div> 
                                        <div class="col-lg-3">
                                        <s:select list="IncompleteGHAmountFlag" name="deducteeSearch.incompleteGhAmountFlag" class="form-control"></s:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Show" onclick="showRecordsPaginator();"><i class="fa fa-search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Clear"><i class="fa fa-eraser" onclick="lhsResetform('deducteeMgmtFrm', 'submit');"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" 
                                        title="Extra Filters" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                            </div> 
                        </div>  
                    </div>
                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{browseAction}" id="browseAction"/>
                <s:hidden value="%{browseAction}" id="dataGridAction"/>
                <s:hidden value="%{update_data_row}" name="update_data_row" id="update_data_row"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <s:hidden value="0" id="countCall"/>
                <input type="hidden" id="fltrformId" value="deducteeMgmtFrm">
            </s:form>
            <div class="clearfix"></div>  
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 
            <div class="clearfix"></div> 

            <div class="table-responsive mt-2" id="dataSpan">
                <table class="table table-bordered table-striped mb-1" id="table"> 
                    <%
                        out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                    %>
                </table>
            </div>

            <div class="clearfix"></div> 
            <!--Table Section-->
           
                <div class="table-responsive mt-2" id="dataSpan">
                </div>
                <!--Table Section-->
                <div class="clearfix"></div> 
                <div class="action-section  bg-white text-center fixed-bottom" id="actiondiv">
                    <s:if test="%{(#loginuser.edit_right != null) && (#loginuser.edit_right == 1)}">
                        <input type="hidden" id="editFormId" />
                        <button type="button" class="action-btn action-btn--edit mr-2" id="editBtn" onclick="submitChallanForm('edit');"> <i class="fa fa-pencil"></i>Edit</button>
                    </s:if>  
                    <s:if test="%{(#loginuser.delete_right != null) && (#loginuser.delete_right == 1)}">
                        <input type="hidden" id="rowidForDelete" />
                        <input type="hidden" id="deleteFormId" />
                        <button type="button" class="action-btn action-btn--delete mr-2" id="deleteBtn" onclick="deleteTDS();"> <i class="fa fa-trash"></i>Delete</button>
                        <button type="button" class="action-btn action-btn--delete mr-2" id="deleteAllBtn"> <i class="fa fa-trash"></i>Delete All</button>
                    </s:if> 
                    <s:if test="%{(#loginuser.query_right != null) && (#loginuser.query_right == 1)}">
                        <input type="hidden" id="viewFormId" />
                        <button type="button" class="action-btn action-btn--view mr-2" id="viewBtn" onclick="submitChallanForm('view');"> <i class="fa fa-eye"></i>View</button>
                    </s:if>  
                    <s:if test="%{(#loginuser.approve_right != null) && (#loginuser.approve_right == 1)}">
                        <button type="button" class="action-btn action-btn--approve mr-2" id="approveBtn"> <i class="fa fa-check-square"></i>Approve All</button>
                        <button type="button" class="action-btn action-btn--reject mr-2" id="revertBtn"> <i class="fa fa-times-rectangle"></i>Revert All</button>
                    </s:if>

                </div>

          
        </div>
    </div>
</div>

<script>
    try {
        defaultValues();
    } catch (err) {
    }
    try {
//        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//        document.getElementById("paginationBlock").style.display = "none";
    } catch (e) {

    }
</script>

