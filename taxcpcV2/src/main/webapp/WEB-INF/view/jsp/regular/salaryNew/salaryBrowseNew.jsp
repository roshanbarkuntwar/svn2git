<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<!--<link href="resources/css/salary/salary.css" rel="stylesheet">-->
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>


<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="tdsSalary"><p> Salary Browse </p> </a></li>
            <li onclick="salaryEntryNewPage();" class="nav-item"><a class="nav-link" data-toggle="pill" href="salaryEntryNew2"> <p> Income and Investment Declaration Form </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>




    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">

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

            <div class="button-section  my-1 pt-1"> 
                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('SALARY DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                <button type="button" class="form-btn" onclick="refreshSalaryData();"><i class="fa refresh" aria-hidden="true"></i>Refresh Tax Deducted Amount</button>
                <button type="button" class="form-btn" onclick="updateStandardDeduction(this);"><i class="fa duplicate-records" aria-hidden="true"></i>Update Standard Deduction</button>
                <button type="button" class="form-btn" onclick="reCalculateTds(this);"><i class="fa duplicate-records" aria-hidden="true"></i>Re-Calculate TDS</button>
                <!--<button type="button" class="form-btn" onclick="DownloadTemplate('salaryBrowseFltrForm', 'DownloadBasicSalaryDetail?action=missingSalary');"><i class="fa download" aria-hidden="true"></i>Download Missing Records</button>-->
                <!--<button type="button" class="form-btn" onclick="DownloadTemplate('salaryBrowseFltrForm', 'DownloadBasicSalaryDetail?action=BasicSalary');"><i class="fa download" aria-hidden="true"></i>Download Basic Salary</button>-->
                <!--<button type="button" class="form-btn" onclick="DownloadTemplate('salaryBrowseFltrForm', 'DownloadBasicSalaryDetail?action=DetailSalary');"><i class="fa download" aria-hidden="true"></i>Download Detail Salary</button>-->
            </div>


            <s:set value="%{#session.get('ASSESSMENT').getForm().getTds_type_code()}" var="tdsTypeCode"/>
            <s:set value="%{#session.get('ASSESSMENT').getQuarter().getPeriod_name().split('-')[2]}" var="quarterNoVal"/>        
            <s:div cssClass="errorMessage" id="salary_tran_notification" cssStyle="visibility:hidden;"></s:div>
            <%--<s:if test="hasActionErrors()">            
                <s:div cssClass="%{#session.ERRORCLASS}" id="session_notification">
                    <s:actionerror/> 
                </s:div>
            </s:if>
            <s:if test="%{resultMessage!=null && resultMessage!='' && resultMessage !='null'}">
                <s:div class="%{#session.ERRORCLASS}" id="session_notification" >
                    <s:property value="resultMessage"/>
                </s:div>
            </s:if>--%>
            <s:set var="workingUser" value="%{#session.get('WORKINGUSER')}"/>
            <s:hidden value="%{#workingUser.getClient_code()}" id="Logginuser" />
            <s:hidden value="%{#workingUser.getEntity_code()}" id="entity"/>




            <s:form name="salaryBrowseFltrForm" id="salaryBrowseFltrForm" action="salaryBrowseNew?search=true" autocomplete="off">
                <%--<s:textfield type="hidden" name="salaryTranLoadFilter.duplicate_record_flag"  id="duplicateFilterFlag"/>--%>

                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">

                            <div class="row form-group">
                                <div class="col-lg-3">
                                    <s:textfield id="f_deductee_panno" name="salaryTranLoadFilter.deductee_panno" cssClass="form-control" placeholder="DEDUCTEE PAN NO."/>
                                </div>

                                <div class="col-lg-3">
                                    <s:select list="#{'T':'Duplicate Records'}" id="f_duplicate_record" name="salaryTranLoadFilter.duplicate_record_flag" 
                                              headerValue="Select Duplicate Records" headerKey="" cssClass="form-control" placeholder="Challan Status" />
                                </div>
                                <div class="col-md-3">
                                    <s:textfield cssClass="form-control" placeholder="DEDUCTEE NAME" id="f_deductee_name" name="salaryTranLoadFilter.deductee_name" maxLength="75"/>
                                </div>
                                <div class="col-lg-3">
                                    <s:select list="deductionList" cssClass="form-control" id="f_deduction_catg" name="salaryTranLoadFilter.deduction_catg" headerKey="" headerValue="Select Deduction"/>
                                </div>    
                            </div>
                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:select list="deducteeCatgList" cssClass="form-control" id="f_deductee_catg" name="salaryTranLoadFilter.deductee_catg"  />
                                    </div>
                                    <div class="col-lg-3">
                                        <s:select list="filterTaxFlag" cssClass="form-control" id="f_tax_115bac_flag" name="salaryTranLoadFilter.tax_115bac_flag" headerKey="" headerValue="Select Whether opting for taxation u/s 115BAC [Yes/No]"/>
                                    </div> 
                                    <div class="col-lg-3">
                                        <s:textfield id="client_bank_branch_code" name="salaryTranLoadFilter.client_bank_branch_code" cssClass="form-control" placeholder="Branch Code"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:textfield id="challan_bank_branch_code" name="salaryTranLoadFilter.challan_bank_branch_code" cssClass="form-control" placeholder="Challan Branch Code"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="showRecordsPaginator();"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('salaryBrowseFltrForm', 'submit');"  title="Clear & Reset"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Show More Filter" onclick="showFilterData('salaryBrowseNew');" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>
                </div>

                <s:hidden  name="filterFlag" id="filterFlag" />
                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="salaryBrowseFltrForm">
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
                    out.print(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
            </div>
            <div class="clearfix"></div> 

        </div>


    </div>
</div>




<script src="resources/js/regular/salaryNew/salaryNew.js" type="text/javascript"></script>
<script src="resources/js/global/paginator/globalPaginator.js"></script>

<script type="text/javascript">
                                try
                                {
                                    defaultValues();
                                } catch (err) {

                                }
                                try {
//                                    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                                } catch (e) {

                                }

</script>  


