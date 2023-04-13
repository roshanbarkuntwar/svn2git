<%-- 
    Document   : viewLoginDetailsDashboard
    Created on : May 31, 2022, 12:18:05 PM
    Author     : akash.meshram
--%>


<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<meta http-equiv="X-UA-Compatible" content="IE=11">
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script src="resources/js/global/moment-with-locales.js"></script>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
<!-- JavaScript Bundle with Popper -->
<link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
<link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>-->
<script type="text/javascript" src="resources/js/admin/admin.js"></script>
<!--<script type="text/javascript" src="resources/js/lhs/lhsGlobal.js"></script>-->

<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li  class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled"  href="loginDetails?loginDetailsType=client" id="login_details" style="pointer-events:none;"><p>Login Details </p> </a></li>
            <li  class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link active"   id="login_dashboard" style="pointer-events:none;"><p>Login Dashboard</p> </a></li>
        </ul>
    </div>






    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">




            <s:form  name="loginDetails" id="loginDetails" method="post" action="loginDetails?search=true" theme="simple"> 

                <div class="filter-section mt-2">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group">

                                 <div class="col-lg-3">
                                        <div class="input-group">
                                            <s:textfield  cssClass="form-control" id="to_date"  name="date" placeholder="dd-mm-yyyy" title="Date"  onkeypress="validateDateOnKeyDown(this, event);" onblur="validateDateOnBlur(this); " maxLength="10"/>
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-primary addon-btn"  onmouseover="openCalendar('to_date', 'toDateCal');">
                                                <i class="fa fa-calendar" id="toDateCal"></i>
                                                </button>
                                            </div>
                                        </div>
                                              
                                       
                                    </div>
                               
                                
                                
                            </div>

                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="bulkTextFilevalidateFilter();" title="Search"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('deducteeMgmtFrm', 'submit');" title="Clear"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter" title="Extra Filters"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>  
                </div> 
                <s:hidden name="countCall" id="countCall" value="0" />
                <s:hidden name="filterFlag" id="filterFlag" />
                <s:hidden name="loginDetails" value="loginDetails" id="fltrformId" />
                <s:hidden  value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden name="userCode" id="userCode"/>
                <s:hidden name="clientCode" id="clientCode"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <s:hidden value="%{loginDetailsType}" name="loginDetailsType" id="loginDetailsType" />
            </s:form>
            <div class="clearfix"></div>  
            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %> 

            <!-----------------settings------------>
             <%@include file="/WEB-INF/view/jsp/adminDashboard/globalPaginatorSettings.jsp" %>
            <div class="clearfix"></div> 
            <!--Table Section-->

            <div class="table-responsive mt-2" id="dataSpan">
                <!--Process Details Ajax Data Here-->
                <%
                    out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                %>

            </div>

            <!--Table Section-->
            <div class="clearfix"></div> 


        </div>




        <script type="text/javascript" src="resources/js/regular/bulkDownload/bulkDownload.js?r=0.39592048544387903"></script>
    </div>


    <!-----------------settings------------>  

    <s:if test="%{loginDetailsType.equalsIgnoreCase('clientView')}">
        <div class="col-md-12 text-center"> 

            <button type="button" class="form-btn" onclick="callUrl('/loginDetails?loginDetailsType=client');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>

        </div>
    </s:if>
    <s:if test="%{loginDetailsType.equalsIgnoreCase('userView')}">
        <div class="col-md-12 text-center"> 

            <button type="button" class="form-btn" onclick="callUrl('/loginDetails?loginDetailsType=user');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>

        </div>
    </s:if>  

</div>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
<script>
                try {
                    document.getElementById("bulk_download_btn").style.display = "none";
                } catch (e) {
                }
                try {
                    loginDetailsFilter();
                } catch (e) {
                }
</script>
