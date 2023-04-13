<%-- 
    Document   : branchGenerationBrowse
    Created on : Nov 25, 2020, 1:11:58 PM
    Author     : gaurav.khanzode
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<script src="resources/js/global/paginator/globalPaginator.js"></script>
<script src="resources/js/lhs/lhsAjax.js"></script>


<div class="page-section heading-and-tab-page">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Bank Branch Generation</h4>
    </div>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link active" id="" href="#"><p>Branch Details</p> </a></li>
            <li class="nav-item mr-5"  onclick="callBranchEntry();"><a class="nav-link" id="" ><p>Branch Entry</p> </a></li>
            <li class="nav-item mr-5"><a class="nav-link" id="" href="#branchAddress" disabled><p>Branch Address</p></a></li>
            <li class="nav-item mr-5"><a class="nav-link" id="" href="#responsiblePersonDetails"><p>Responsible Person Details</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">
        <s:if test="%{sessionResult!=null && sessionResult!='' && sessionResult !='null'}">
            <div class="text-center col-md-12 mt-2" id="session_notification">
                <div class="d-inline-block">
                    <s:div class="%{#session.ERRORCLASS}" >
                        <i class="fa fa-info mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" ><s:property value="sessionResult"/></h5>
                    </s:div>
                </div>
            </div>
        </s:if>
        <div class="tab-pane show in active">
            <div class="button-section my-1">

            </div>

            <s:form name="branchGenerationBrowsePage" id="branchGenerationBrowsePage" method="POST" action="branchGenerationBrowse?search=true" theme="simple">
               <s:hidden id="client-code" name="clientCode"/>
                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group mt-2 pt-1">
<!--                                <div class="col-lg-3">       
                                    <input type="text" name="branchDetailsFilters.code_level" id="codeLevel" class="form-control" title="Code Level" placeholder="Code Level">
                                </div>-->
                        <div class="col-lg-3">   
                            <s:select cssClass="form-control" list="loginLevelList" id="codeLevel" headerKey="" headerValue="Select Code Level"
                                      name="branchDetailsFilters.code_level"/>
                        </div>
                                <div class="col-lg-3">
                                    <input type="text" name="branchDetailsFilters.bank_branch_code" onkeyup="convertToUppercase();" id="bankBranchCode" class="form-control text-uppercase" title="Bank Branch Code" placeholder="Bank Branch Code">
                                </div>
                                <div class="col-lg-3">
                                    <input type="text" name="branchDetailsFilters.client_name" id="branchDivisionName" class="form-control text-uppercase" title="Branch Division Name" placeholder="Branch Division Name">
                                </div>
                                <div class="col-lg-3">
                                    <input type="text" name="branchDetailsFilters.parent_code" id="parentCode" class="form-control text-uppercase" title="Parent Code" placeholder="Parent Code">
                                </div>
                            </div>

                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <input type="text" name="branchDetailsFilters.panno" id="panno" class="form-control text-uppercase" title="PAN Number" placeholder="PAN Number">
                                    </div>
                                    <div class="col-lg-3">
                                        <input type="text" name="branchDetailsFilters.tanno" id="tanno" class="form-control text-uppercase" title="TAN Number" placeholder="TAN Number">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex mt-2 justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showRecordsPaginator();"><i title="Search" class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('branchGenerationBrowsePage', 'submit');"><i title="Clear" class="fa clean"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"><i title="Extra Filter" class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>

                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction" name="action"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="branchGenerationBrowsePage">
                <input type="hidden" name="filterFlag" id="filterFlag">
                <input type="hidden" id="generateForAllFlag" value="false">
            </s:form>

            <s:form class="d-none" id="branchAction" action="branchGeneration" method="POST">
                <input type="hidden" name="action" id="actionName">
                <input type="hidden" name="clientCode" id="actionClientCode">
            </s:form>

            <!--            <form class="d-none" id="deleteBranchGeneration" action="branchGeneration" method="POST">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="editClientCode" id="deleteClientCode">
                        </form>-->

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

        </div>
    </div>


</div>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>"></script>
<script type="text/javascript" src="resources/js/regular/branchGeneration/branchGeneration.js?r=<%=Math.random()%>"></script>
<script>
                                try {
                                    defaultValues();
                                } catch (err) {
                                }
                                try {
                                    $('#bulk_download_btn').remove();
                                } catch (e) {

                                }
</script>

