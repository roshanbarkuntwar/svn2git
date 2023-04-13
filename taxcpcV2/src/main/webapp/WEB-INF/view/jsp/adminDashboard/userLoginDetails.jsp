<%-- 
    Document   : userLoginDetails
    Created on : Apr 25, 2022, 1:09:17 PM
    Author     : akash.meshram
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<!-- CSS only -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
<!-- JavaScript Bundle with Popper -->
<link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
<!--<link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">-->
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>-->
<script type="text/javascript" src="resources/js/admin/admin.js?<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobal.js"></script>
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

            <li  class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link active" href="loginDetails?loginDetailsType=client" id="login_details" style="pointer-events:none;"><p>Login Details </p> </a></li>
            <li  class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled"    id="login_dashboard" style="pointer-events:none;"><p>Login Dashboard</p> </a></li>
        </ul>
    </div>






    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">
            <div class="login-section button-section  my-1 pt-1"> 
                <div class="row form-group pt-2">
                    <div class="col-lg-4 col-xl-4  text-right">
                        <label class="control-label">Login Details</label>
                    </div>
                    <div class="col-lg-7 col-xl-4">

                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input"  id="clientRadioBtn" onclick="logindetailsReadioBtn(this.id);" checked>Branch Login Details
                            </label>
                        </div>


                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input"  id="userRadioBtn" onclick="logindetailsReadioBtn(this.id);">User Login Details

                            </label>
                        </div>

                    </div>
                </div>                              

            </div>
            <s:form  name="loginDetails" id="loginDetails" method="post" action="loginDetails?search=true" theme="simple"> 
                <s:hidden name="countCall" id="countCall" value="0"/>
                <s:hidden name="filterFlag" id="filterFlag"/>

                <s:hidden name="loginDetails" value="loginDetails" id="fltrformId"/>

                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{loginDetailsType}" name="loginDetailsType" id="loginDetailsType" />


                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div id="clientfiltdiv" style="display:flex"  class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield type="text" name="bankBranchCode" class="form-control" title="Bank Branch Code." placeholder="Bank Branch Code."    onkeypress="return lhsIsNumber(event);" />

                                </div>
                            </div>
                            <div  id="userfiltdiv" class="row form-group" style="display:none">

                                <div class="col-lg-3">
                                    <s:textfield type="text" name="loginId" class="form-control" title="Login ID" placeholder="Login ID" />

                                </div>

                                <div  class="col-lg-3" >

                                    <s:textfield type="text"  name="userCode" class="form-control" title="User Code" placeholder="User Code" />
                                </div>

                            </div>





                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="loginDashbordFilter();" title="Search"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('loginDetails', 'submit');" title="Clear"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter" title="Extra Filters"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div> 

                </div> 
            </s:form>
            <s:hidden value="%{browseAction}" id="browseAction"/>
            <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
            <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
            <s:hidden value="%{pageNumber}" id="pageNumber"/>         


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

  </div>



</div>

<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>

<script src="resources/js/global/paginator/globalPaginator.js?<%= Math.random()%>"></script>

<script>
                                try {
//                                        document.getElementById("paginator_top").style.display = "block";
                                    document.getElementById("bulk_download_btn").style.display = "none";
//                                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                                } catch (e) {
                                }
                                $(document).ready(function () {
                                    loginDetailsFilter();
                                });
</script>
