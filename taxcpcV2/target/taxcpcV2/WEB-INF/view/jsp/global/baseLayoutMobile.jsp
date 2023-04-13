<%-- 
    Document   : baseLayoutMobile
    Created on : Feb 4, 2019, 4:45:57 PM
    Author     : sneha.parpelli
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${initParam.BrowserPageTitleText}</title>
        <link rel="icon" href="${initParam.BrowserPageTitleImageURL}" type="images"/>
        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>

        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">   
        <!--Base Layout CSS-->
        <link rel="stylesheet" href="resources/css/main/layout.css" type="text/css">

        <!--Global form,table,button CSS-->
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">

        <!--Multiselect CSS-->
        <link href="resources/css/global/bootstrap-multiselect.css" rel="stylesheet">  
        <script src="resources/js/global/multiSelect-with-search.js"></script>
        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
        <script type="text/javascript" src="resources/js/global/baseLayout.js"></script>
        <script src="resources/js/global/multiSelect-with-search.js"></script>
        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%Math.random();%>" ></script>

    </head>
    <body>
        <s:set var="loginuser" value="%{#session.get('LOGINUSER')}"/>
        <s:set var="activeTab" value="%{#session.get('ACTIVE_TAB')}"/>
        <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>
        <s:set var="module" value="%{#session.get('MODULE')}"/>
        <div class="template-container template-container--mobile">
            <!--Left Section-->
            <div class="left-sidebar">
                <div class="text-center my-1">
                    <img src="resources/images/global/taxcpcLogoWhite.jpg">
                </div>
                <s:if test ="%{#module != null && !(#module.equalsIgnoreCase('R'))}"> 

                    <s:if test="%{#module.equalsIgnoreCase('C')}">

                        <ul class="nav nav-pills flex-column" >
                            <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                            <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                                <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Generate Reference No.</a></li>
                                <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Validate and generate</a></li>
                                <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Download Records</a></li>
                                <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Acknowledgment Update</a></li>
                                <!--                                <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                                                                <div class="collapse mb-0" id="leftNav">
                                                                                                        <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                                                                                            <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Others1</a></li>
                                                                                                            <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Others2</a></li>
                                                                                                            <li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>
                                                                                                        </ul>
                                                                </div>-->
                            </ul>
                    </s:if>
                    <s:elseif test="%{#module.equalsIgnoreCase('G')}">

                        <ul class="nav nav-pills flex-column" >
                            <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                            <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                                <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Generate Reference No.</a></li>
                                <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Validate and generate</a></li>
                                <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Download Records</a></li>
                                <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Acknowledgment Update</a></li>
                                <!--                                <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                                                                <div class="collapse mb-0" id="leftNav">
                                                                                                        <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                                                                                            <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Others1</a></li>
                                                                                                            <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Others2</a></li>
                                                                                                            <li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>
                                                                                                        </ul>
                                                                </div>-->
                            </ul>
                    </s:elseif>
                </s:if><s:else>


                    <ul class="nav nav-pills flex-column">
                        <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                        <li <s:if test="#activeTab == 'tdsDeductorInfo'">class="active"</s:if>> <a href="deductors"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Deductor Info</a></li>
                        <li <s:if test="#activeTab == 'tdsChallan'">class="active"</s:if>><a href="tdsChallan"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Challan</a></li>
                        <li <s:if test="#activeTab == 'tdsDeductees'">class="active"</s:if>><a href="tdsDeductees"><i class="fa fa-inr  mr-2" aria-hidden="true"></i>Deductee</a></li>
                        <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                            <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Validation</a></li>
                            <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Return</a></li>
                            <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>TDS Certificate</a></li>
                            <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                            <div class="collapse mb-0" id="leftNav">
                                <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                    <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Lower Deduction</a></li>
                                    <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>PRN Information</a></li>
                                    <!--<li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>-->
                                </ul>
                            </div>
                        </ul>
                </s:else>
                <div class="position-absolute sessionId-section p-1">
                    <p class="mb-1">Session Id : <span>5656</span> | <span>c101</span></p>
                </div>
            </div>
            <!--Left Section-->

            <!--Right Section-->
            <div class="right-section">
                <div class="right-section__header">
                    <!--Top Navigation-->
                    <div class="client-section-header">
                        <div class="row m-0">
                            <div class="col-md-7 col-lg-6 col-xl-7">
                                <div class="change-deductor-section-outer d-inline-block position-relative text-truncate w-100">
                                    <div class="change-deductor-section px-2 py-1" data-toggle="collapse" data-target="#deductorListDiv" onclick="getDeductorList();">
                                        <span ><s:property value="%{#session.get('DEDUCTOR')}" /></span> <i class="fa fa-caret-down pt-1 ml-3" aria-hidden="true"></i>
                                    </div>
                                    <div class="deductor-list px-2 py-1 position-absolute w-100 collapse" id="deductorListDiv">
                                        <div class="card">
                                            <div class="card-header p-2">
                                                <div class="input-group w-90">
                                                    <input type="text" class="form-control" id="term">
                                                    <div class="input-group-append">
                                                        <button type="submit" class="btn btn-dark addon-btn" onclick="getDeductorList();"><i class="fa fa-search"></i></button>
                                                        <button type="submit" class="btn btn-warning addon-btn text-white" onclick="resetList();"><i class="fa fa-eraser"></i></button>
                                                    </div>
                                                </div>
                                                <a class="text-white close-btn position-absolute" onclick="closeDeductorList();"><i class="fa fa-times" aria-hidden="true"></i></a>
                                            </div>
                                            <div class="card-body p-0">
                                                <ul class="list-group list-group-flush" id="deductorList">

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--                                    <div class="select-acc-field"  id="selectDiv" onclick="changeDeductor();">
                                                                   
                                                                    <select id="business-type"  class="" onclick="changeDeductor();">
                                <option value="<s:property value="%{#loginuser.client_code}"/>"><s:property value="%{#session.get('DEDUCTOR')}"/></option>
                                
                                                                    </select>
                                                                </div>             -->
                            </div>
                            <div class="col-md-5 col-lg-6  col-xl-5 text-left px-lg-3">

                                <div class="dropdown top-dropdown d-inline-block">
                                    <i class="fa fa-clock-o mr-1"> </i> <span>Login Since : </span> 01-02-2019 12:13 PM | <i class="fa fa-user mr-1"> </i> <a href="#" data-toggle="tooltip" title="hi"><s:property value="%{#loginuser.user_name}"/></a><b class="caret"></b>
                                    <!--                                    <ul class="dropdown-menu top-dropdown-menu px-2">
                                                                            <li><a href="#">Change Password</a></li>
                                                                            <div class="dropdown-divider"></div>
                                                                            <li><a href="#">View Profile</a></li>
                                                                        </ul>-->
                                </div>
                                <button type="button" class="btn btn-sm btn-primary logout-btn" title="Logout" onclick="exitUser();"> <i class="fa fa-sign-out"></i>  </button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <!--Top Navigation-->

                    <!--Main Navigation-->


                    <div class="position-fixed taxcpc-process-indicator-outer taxcpc-process-indicator-outer--black " id="global-black-process-indicator" style="display:none">
                        <div class="taxcpc-process-indicator position-absolute text-center">
                            <img class="pulse2" src="resources/images/global/taxcpcProcessIndicator.png">
                            <div class="spinner">
                                <div class="bounce1"></div>
                                <div class="bounce2"></div>
                                <div class="bounce3"></div>
                            </div>
                        </div>
                    </div>
                    <div class="position-fixed taxcpc-process-indicator-outer " id="global-simple-process-indicator" style="display:none">
                        <div class="taxcpc-process-indicator position-absolute text-center">
                            <img class="pulse2" src="<s:property value="#session.CONTEXTPATH" />/resources/images/global/taxcpcProcessIndicator.png">
                            <div class="spinner">
                                <div class="bounce1"></div>
                                <div class="bounce2"></div>
                                <div class="bounce3"></div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="sessionAccYear" value="<s:property value="%{#assessment.getAccYear()}" />"/> 
                    <input type="hidden" id="sessionQuarterNo" value="<s:property value="%{#assessment.getQuarterNo()}" />"/> 
                    <input type="hidden" id="sessionTdsType" value="<s:property value="%{#assessment.getTdsTypeCode()}" />"/> 
                    <input type="hidden" id="defaultDeductor" value="<s:property value="%{#session.get('DEDUCTOR')}" />"/> 
                    <input type="hidden" id="module" value="<s:property value="%{module}" />"/> 
                    <div class="assessment-section-header">
                        <div class="row m-0">
                            <div class="col-md-4">
                                <select class="borderless-select" title="Acc-Year" id="accYear" onchange="changeAssessment();">

                                    <option value="16-17">16-17</option>
                                    <option value="17-18">17-18</option>
                                    <option value="18-19">18-19</option>
                                </select>
                                <select class="borderless-select" title="Quarter" id="quarter" onchange="changeAssessment();">

                                    <option value="1" >Q1</option>
                                    <option value="2" >Q2</option>
                                    <option value="3">Q3</option>
                                    <option value="4">Q4</option>
                                </select>
                                <select class="borderless-select" title="TDS Type" id="tdsType" onchange="changeAssessment();">

                                    <option value="24Q">24Q</option>
                                    <option value="26Q">26Q</option>
                                    <option value="27Q">27Q</option>
                                    <option value="27EQ">27EQ</option>
                                </select>
                            </div>
                            <div class="col-md-8">
                                <ul class="nav nav-pills pull-right main-menu-navigation">
                                    <li class="notification-icon  mr-5"><a class="position-relative" href="notification "><i class="fa fa-bell" aria-hidden="true"></i> <span class="badge-pill badge-danger position-absolute pulsate">5</span></a></li>
                                    <li <s:if test="#module.equalsIgnoreCase('R')"> class="active"</s:if>><a href="dashboard?module=R">Regular</a></li>
                                    <li <s:if test="#module.equalsIgnoreCase('C')"> class="active"</s:if>><a href="dashboard?module=C">Correction</a></li>
                                    <li <s:if test="#module.equalsIgnoreCase('G')"> class="active"</s:if>><a href="dashboard?module=G">15G/H</a></li>
                                        <li class="dropdown">
                                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">MIS <b class="caret"></b></a>
                                            <ul>
                                                <!--<ul class="dropdown-menu top-dropdown-menu px-2">-->
                                                <!--                                                <li><a class="dropdown-item" href="#">Report1</a></li>
                                                                                                <div class="dropdown-divider"></div>
                                                                                                <li><a class="dropdown-item" href="#">Report2</a></li>-->
                                            </ul>
                                        </li>

                                    </ul>
                                    <div class="header d-inline-block"></div>
                                    <input type="checkbox" class="openSidebarMenu" id="openSidebarMenu">
                                    <label for="openSidebarMenu" class="sidebarIconToggle">
                                        <div class="spinner diagonal part-1"></div>
                                        <div class="spinner horizontal"></div>
                                        <div class="spinner diagonal part-2"></div>
                                    </label>
                                    <div id="sidebarMenu">
                                    <s:if test ="%{#module != null && !(#module.equalsIgnoreCase('R'))}"> 

                                        <s:if test="%{#module.equalsIgnoreCase('C')}">

                                            <ul class="sidebarMenuInner" >
                                                <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                                                <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                                                    <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Generate Reference No.</a></li>
                                                    <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Validate and generate</a></li>
                                                    <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Download Records</a></li>
                                                    <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Acknowledgment Update</a></li>
                                                    <!--                                <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                                                                                    <div class="collapse mb-0" id="leftNav">
                                                                                                                            <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                                                                                                                <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Others1</a></li>
                                                                                                                                <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Others2</a></li>
                                                                                                                                <li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>
                                                                                                                            </ul>
                                                                                    </div>-->
                                                </ul>
                                        </s:if>
                                        <s:elseif test="%{#module.equalsIgnoreCase('G')}">

                                            <ul class="sidebarMenuInner" >
                                                <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                                                <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                                                    <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Generate Reference No.</a></li>
                                                    <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Validate and generate</a></li>
                                                    <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Download Records</a></li>
                                                    <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>Acknowledgment Update</a></li>
                                                    <!--                                <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                                                                                    <div class="collapse mb-0" id="leftNav">
                                                                                                                            <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                                                                                                                <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Others1</a></li>
                                                                                                                                <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Others2</a></li>
                                                                                                                                <li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>
                                                                                                                            </ul>
                                                                                    </div>-->
                                                </ul>
                                        </s:elseif>
                                    </s:if><s:else>


                                        <ul class="sidebarMenuInner">
                                            <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Dashboard</a></li>
                                            <li <s:if test="#activeTab == 'tdsDeductorInfo'">class="active"</s:if>> <a href="deductors"><i class="fa fa-user  mr-2" aria-hidden="true"></i>Deductor Info</a></li>
                                            <li <s:if test="#activeTab == 'tdsChallan'">class="active"</s:if>><a href="tdsChallan"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Challan</a></li>
                                            <li <s:if test="#activeTab == 'tdsDeductees'">class="active"</s:if>><a href="tdsDeductees"><i class="fa fa-inr  mr-2" aria-hidden="true"></i>Deductee</a></li>
                                            <li <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="tdsTransaction"><i class="fa fa-credit-card-alt mr-2" aria-hidden="true"></i>Transaction</a></li>
                                                <li><a href="#"><i class="fa fa-check-square  mr-2" aria-hidden="true"></i>Validation</a></li>
                                                <li><a href="#"><i class="fa fa-reply  mr-2" aria-hidden="true"></i>Return</a></li>
                                                <li><a href="#"><i class="fa fa-certificate  mr-2" aria-hidden="true"></i>TDS Certificate</a></li>
                                                <li data-toggle="collapse" data-target="#leftNav"><a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i>Others</a></li>
                                                <div class="collapse mb-0" id="leftNav">
                                                    <ul class="nav nav-pills flex-column" style="background: #396dca;">
                                                        <li><a href="#"><i class="fa fa-tachometer mr-2" aria-hidden="true"></i>Lower Deduction</a></li>
                                                        <li><a href="#"><i class="fa fa-user  mr-2" aria-hidden="true"></i>PRN Information</a></li>
                                                        <!--<li><a href="#"><i class="fa fa-file-text  mr-2" aria-hidden="true"></i>Others3</a></li>-->
                                                    </ul>
                                                </div>
                                            </ul>
                                    </s:else>

                                </div>
                                <div class="clearfix"></div>


                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <!--Main Navigation-->
                </div>
                <div class="header-gap"></div>
                <!--Body Start Here-->



                <tiles:insertAttribute name="body" />


                <s:hidden value="%{#session.CONTEXTPATH}" id="globalcontextpath"/>



                <!--Body Start Here-->
                <div class="clearfix"></div>
                <div class="footer-gap"></div>
                <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
            </div>
            <!--Right Section-->
        </div>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>



    </body>
</html>

