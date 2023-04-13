<%-- 
    Document   : salarySevaarthProjection
    Created on : Jul 6, 2021, 11:27:08 PM
    Author     : kapil.gupta
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/regular/salarySevaarth/salarySevaarth.js?<%=Math.random()%>"></script>
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
            <li onclick="" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Salary Sevaarth Projection Browse</p> </a></li>
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
            </div>
            <s:form name="salarySevaarthProjectionDataForm" id="salarySevaarthProjectionDataForm" method="post" action="salarySevaarthProjection?search=true" theme="simple"> 

                <div class="filter-section">
                    <div class="row sec-bottom">


                        <div class="col-lg-10 col-xl-11">

                            <div class="row form-group">
                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="DDO_NO" placeholder="DDO_NO" title="DDO_NO" name="SalSevaarthProjectionFilterData.ddoNo"/>
                                </div>

                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="BILL_ID" placeholder="BILL_ID" title="BILL_ID" name="SalSevaarthProjectionFilterData.billId"/>
                                </div>
                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="SEVARTH_ID" placeholder="SEVARTH_ID" title="SEVARTH_ID" name="SalSevaarthProjectionFilterData.SevarthId"/>
                                </div>

                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="DEDUCTEE_PANNO" placeholder="DEDUCTEE_PANNO" title="DEDUCTEE_PANNO" name="SalSevaarthProjectionFilterData.DeducteePanno"/>
                                </div>

                            </div>

                            <div class="collapse" id="remainingFilter">

                            </div>

                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="validateFilter();"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('salarySevaarthProjectionDataForm', 'submit');"  title="Clear & Reset"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="No Extra Filters" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>  
                </div> 



                <s:hidden  name="filterFlag" id="filterFlag" />
                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="salarySevaarthProjectionDataForm">
            </s:form>

            <div class="clearfix"></div>
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>

            <div class="clearfix"></div> 

            <!--Table Section-->
<!--            <div class="table-responsive mt-2">-->
                <div class="table-responsive mt-2" id="dataSpan">
                    <!--Ajax data here-->
                    <%
                        out.print(GlobalMessage.PAGINATION_SHOW_MORE);
                    %>
                </div>
                <div class="clearfix"></div> 
<!--            </div>  -->
        </div>
    </div>
</div>

<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>


<script type="text/javascript">
                                try
                                {
                                    defaultValues();
                                } catch (err) {

                                }
</script> 

