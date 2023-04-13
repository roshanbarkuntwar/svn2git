<%-- 
    Document   : checkerMaker
    Created on : May 18, 2020, 4:27:46 PM
    Author     : pratik.barahate
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link href="resources/css/transaction/transaction.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js"></script>
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>

<style>
    .header .sign:after{
        content:"+";
        display:inline-block;      
    }
    .header.expand .sign:after{
        content:"-";
    }

</style>
<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="#browse"><p>Misc Transaction Browse</p></a></li>
            <li onclick="callEntryPage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Entry</p></a></li>
            <li onclick="callCheckerMakerPage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Authorization</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>

    <div class="tab-content px-4 py-4">
        <s:if test="%{sessionResult != null}">
            <div class="text-center col-md-12" id="session_notification">
                <div class="d-inline-block">
                    <s:div class="%{#session.ERRORCLASS}" >
                        <i class="fa fa-check mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" ><s:property value="sessionResult"/></h5>
                    </s:div>
                </div>
            </div>
        </s:if>

        <div id="browse" class="tab-pane show in active">
            <div class="button-section  my-1"> 
                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('TRANSACTION DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                <!--<button type="button" class="form-btn" onclick="DownloadTemplate('transactionMgmtForm', 'downloadInTemplate');"><i class="fa fa-download" aria-hidden="true"></i>Download</button>-->
                <!--<a href="tdsImportAction"><button type="button" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Import</button></a>-->
                <!--                <button type="button" class="form-btn"><i class="fa fa-check" aria-hidden="true"></i>Approve All</button>
                                <button type="button" class="form-btn"><i class="fa fa-refresh" aria-hidden="true"></i>Revert All</button>-->
            </div>
            <s:form name="checkerMakerDataForm" id="checkerMakerDataForm" method="post" action="checkerMakerData?search=true" theme="simple"> 


                <%--<s:hidden value="32" id="noOfColumn" name="noOfColumn"/>--%>
                <%--<s:hidden value="transaction" id="downloadFlag" name="downloadFlag"/>--%>

                <div class="filter-section">
                    <div class="row sec-bottom">


                        <div class="col-lg-10 col-xl-11">
                            <s:hidden id ="allAssessment" value="%{#session.get('ALLASSESSMENT')}" />

                            <s:if test="%{#session.get('ALLASSESSMENT') !=null && (#session.get('ALLASSESSMENT')).equalsIgnoreCase('T')}">

                                <div class="row form-group">
                                    <div class="col-lg-3">

                                        <s:select label="Select Financial Year" cssClass="form-control" id="assAccYear"
                                                  headerKey="" headerValue="Select Financial Year"
                                                  list="#{'all':'All','16-17':'16-17','17-18':'17-18','18-19':'18-19','19-20':'19-20'}" 
                                                  name="tranFilter.asmt.accYear" 
                                                  value="" />
                                    </div>
                                    <div class="col-lg-3">
                                        <s:select label="Select Quarter" cssClass="form-control" id="assQuarter"
                                                  headerKey="" headerValue="Select Quarter"
                                                  list="#{'all':'All','1':'Q1','2':'Q2','3':'Q3','4':'Q4'}" 
                                                  name="tranFilter.asmt.quarterNo" 
                                                  value="" />

                                    </div>
                                    <div class="col-lg-3">
                                        <s:select label="Select Tds Type Code" cssClass="form-control" id="assTdsType"
                                                  headerKey="" headerValue="Select Tds Type Code"
                                                  list="#{'all':'All','24Q':'24Q','26Q':'26Q','27Q':'27Q','27EQ':'27EQ'}" 
                                                  name="tranFilter.asmt.tdsTypeCode" 
                                                  value="" />
                                    </div>
                                </div>
                            </s:if>




                            <div class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="deductee_panno" placeholder="PAN No." title="PAN No." name="checkerMakerData.deductee_panno"/>
                                </div>

                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="deductee_name" placeholder="Deductee Name." title="Deductee Name." name="checkerMakerData.deductee_name"/>

                                </div>

                                <div class="col-lg-3">
                                    <select id="authStatusFlag" class="form-control" name="authStatusFlag">
                                        <!--<option value="AL">--Select Status--</option>-->
                                        <option value="A">Approved</option>
                                        <option value="R">Unapproved</option>
                                    </select>
                                </div>


                            </div>

                        </div>

                        <!--<div class="button-section">--> 

                        <!--</div>-->
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">

                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="validateCheckerMakerFilter();"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('checkerMakerDataForm', 'submit');"  title="Clear & Reset"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Extra Filters" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>

                        </div> 
                    </div>  
                </div> 



                <s:hidden  name="filterFlag" id="filterFlag" />
                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="checkerMakerDataForm">
            </s:form>

            <div class="clearfix"></div>
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

            <div class="clearfix"></div> 

            <!--Table Section-->
            <div class="" id="dataSpan">
                <!--Ajax data here-->
                <%
                    out.print(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
            </div>  
            <div class="clearfix"></div> 

        </div>
        <div class="action-section  bg-white text-center fixed-bottom" id="actiondiv">

            <s:if test="%{(#loginuser.edit_right != null) && (#loginuser.edit_right == 1)}">


                <input type="hidden" id="editFormId" />
                <button type="button" class="action-btn action-btn--edit mr-2" id="editBtn" onclick="submitForm('edit');"> <i class="fa fa-pencil"></i>Edit</button>

            </s:if>

            <s:if test="%{(#loginuser.delete_right != null) && (#loginuser.delete_right == 1)}">
                <input type="hidden" id="rowidForDelete" />

                <button type="button" class="action-btn action-btn--delete mr-2" id="deleteBtn" onclick="deleteTDS();"> <i class="fa fa-trash"></i>Delete</button>
                <button type="button" class="action-btn action-btn--delete mr-2" id="deleteAllBtn"> <i class="fa fa-trash"></i>Delete All</button>
            </s:if>  
            <s:if test="%{(#loginuser.delete_right != null) && (#loginuser.query_right == 1)}">
                <input type="hidden" id="viewFormId" />
                <button type="button" class="action-btn action-btn--view mr-2" id="viewBtn" onclick="submitForm('view');"> <i class="fa fa-eye"></i>View</button>
            </s:if>  
            <s:if test="%{(#loginuser.approve_right != null) && (#loginuser.approve_right == 1)}">
                <button type="button" class="action-btn action-btn--approve mr-2" id="approveBtn"> <i class="fa fa-check-square"></i>Approve All</button>
                <button type="button" class="action-btn action-btn--reject mr-2" id="revertBtn"> <i class="fa fa-times-rectangle"></i>Revert All</button>
            </s:if>  
            <!--                                                <button type="button" class="action-btn action-btn--action1 mr-2"> <i class="fa fa-times-rectangle"></i>Action1</button>
                                                            <button type="button" class="action-btn action-btn--action2 mr-2"> <i class="fa fa-times-rectangle"></i>Action2</button>
                                                            <button type="button" class="action-btn action-btn--action3 mr-2"> <i class="fa fa-times-rectangle"></i>Action3</button>-->
        </div>
    </div>
</div>

<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/regular/miscCin/checkerMaker.js?r=<%= Math.random()%>"></script>


<script type="text/javascript">
                    try
                    {
                        defaultValues();
                    } catch (err) {

                    }
                    try {
                        document.getElementById("bulk_download_btn").style.display = "none";
//                                    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                    } catch (e) {

                    }

</script> 
