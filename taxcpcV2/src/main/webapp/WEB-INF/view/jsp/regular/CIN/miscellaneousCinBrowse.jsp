<%-- 
    Document   : miscellaneousCinBrowse
    Created on : Apr 27, 2020, 1:16:51 PM
    Author     : pratik.barahate
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/transaction/transaction.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<!--<script src="resources/js/global/bootstrap-datetimepicker.js" type="text/javascript"></script>-->
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
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
    <s:set var="userMast" value="%{#session.get('LOGINUSER')}"/>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Misc Transaction Browse</p> </a></li>
            <li onclick="callEntryPage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Entry</p></a></li>
                <s:if test="%{#userMast.approve_right != null && #userMast.approve_right == 1}">
                <li onclick="callCheckerMakerPage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="tdsTransactionEntry"><p>Misc Transaction Authorization</p></a></li>
                </s:if>
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
            <div class="button-section  my-1 pt-1"> 
                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('TRANSACTION DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
            </div>
            <s:form name="miscelleneousDataForm" id="miscelleneousDataForm" method="post" action="miscData?search=true" theme="simple"> 


                <s:hidden value="32" id="noOfColumn" name="noOfColumn"/>
                <s:hidden value="transaction" id="downloadFlag" name="downloadFlag"/>

                <div class="filter-section">
                    <div class="row sec-bottom">


                        <div class="col-lg-10 col-xl-11">
                            <s:hidden id ="allAssessment" value="%{#session.get('ALLASSESSMENT')}" />

                            <div class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="deductee_panno" placeholder="PAN NUMBER" title="PAN No." name="miscData.deductee_panno"/>
                                </div>

                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="account_number" placeholder="ACCOUNT NUMBER" name="miscData.account_number"/>
                                </div>


                                <div class="col-lg-3">
                                    <!--setBGLSectionRateForBrowse-->
                                    <s:select id="bglCode" list="bglCodeNameList" class="form-control" name="miscData.bgl_code" headerKey="" headerValue="Select BGL Code"
                                              onChange="setBGLSectionBrowse(this.value); "/>
                                </div>

                                <div class="col-lg-3">
                                    <!--onChange="getRateAndTotalAmt(this.value);"-->
                                    <s:select class="form-control" list="selectSectionList" id="tds_code" 
                                              name="miscData.tds_code" cssClass="form-control" cssStyle="width:100%;" 
                                              />
                                </div>  


                            </div>

                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="deductee_name"  name="miscData.deductee_name" placeholder="DEDUCTEE NAME" title="Deductee Name" maxlength="50"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <select id="tdsAmtFlag" class="form-control" name="miscData.tdsAmtFlag"  >
                                            <option value="">Select TDS Amount</option>
                                            <option value=">=">&gt;=</option>
                                            <option value="<=">&lt;=</option>
                                            <option value="=">=</option>
                                            <option value="between">between</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="tds_amt_from" placeholder="TDS FROM AMOUNT" name="miscData.tds_amt_from"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="tds_amt_to" placeholder="TDS TO AMOUNT" name="miscData.tds_amt_to"/>
                                    </div>


                                </div>
                                <div class="row form-group">
                                    <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>
                                    <div class="col-lg-3">
                                        <select class="form-control" name="miscData.month" id="browse_month" onchange="setDateOfTaxDeduction(this.value);">
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
                                    <div class="col-lg-3">
                                        <!--<select id="authStatusFlag" class="form-control" name="miscData.authStatusFlag" onchange="(this.value === 'A') ? $('#fDateDiv, #tDateDiv').css('display', 'block') : $('#fDateDiv, #tDateDiv').css('display', 'none')" >-->
                                        <select id="authStatusFlag" class="form-control" name="miscData.authStatusFlag" onchange="(this.value === 'A') ? $('#fDateDiv, #tDateDiv').css('pointer-events', '') : $('#fDateDiv, #tDateDiv').css('pointer-events', 'none')" >
                                            <option value="">Select Status</option>
                                            <option value="A">Approved</option>
                                            <option value="R">Unapproved</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-3" id="fDateDiv">
                                        <div class="input-group">
                                            <s:textfield type="text" cssClass="form-control" id="from_date"  name="miscData.from_date" placeholder="From Date (DD-MM-YYYY)" title="From Date"/>
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-primary addon-btn" onmouseover="openCalendar('from_date', 'fromDateCal', '887px', '200px');">
                                                    <i class="fa fa-calendar" id="fromDateCal"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-lg-3" id="tDateDiv">
                                        <div class="input-group">
                                            <s:textfield type="text" cssClass="form-control" id="to_date"  name="miscData.to_date" placeholder="To Date (DD-MM-YYYY)" title="To Date"/>
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-primary addon-btn" onmouseover="openCalendar('to_date', 'toDateCal', '1168px', '200px');">
                                                    <i class="fa fa-calendar" id="toDateCal"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="validateMiscFilter();"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('miscelleneousDataForm', 'submit');"  title="Clear & Reset"><i class="fa clear"></i></button>
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
                <input type="hidden" id="fltrformId" value="miscelleneousDataForm">
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
<script src="resources/js/regular/miscCin/miscCin.js?r=<%= Math.random()%>"></script>


<script type="text/javascript">
                    try
                    {
                        defaultValues();
                    } catch (err) {

                    }
                    try {
//                        document.getElementById("bulk_download_btn").style.display = "none";
//                                    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                    } catch (e) {

                    }

</script> 

